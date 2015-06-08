/**
 * Copyright Intellectual Reserve, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gedcomx.util;

import org.codehaus.jackson.map.ObjectMapper;
import org.gedcomx.Gedcomx;
import org.gedcomx.rt.json.GedcomJacksonModule;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.zip.GZIPInputStream;

/**
 * Class for iterating through the 'record' elements (GedcomX documents) in a RecordSet one at a time
 *   from a stream (e.g., a gzipped byte array) without having to inflate all the records at once.
 *   This reads JSON-formatted Records.
 *
 * User: Brent Hale
 * Date: 6/3/2015
 */
public class JsonRecordSetIterator implements Iterator<Gedcomx> {
  private InputStream inputStream;
  private Gedcomx nextRecord;
  private Gedcomx metadata;
  private ObjectMapper objectMapper;
  private String id;
  private boolean noMoreRecords = false;

  /**
   * Constructor for a record iterator that takes a filename of a RecordSet file and iterates through its record elements.
   * @param filename - Filename to read a GedcomX RecordSet file from.
   * @throws IOException
   */
  public JsonRecordSetIterator(String filename) throws IOException {
    this(new FileInputStream(filename), filename.toLowerCase().endsWith(".gz"));
  }

  public JsonRecordSetIterator(InputStream inputStream, boolean isGzipped) throws IOException {
    this(isGzipped ? new GZIPInputStream(inputStream) : inputStream);
  }

  /**
   * Constructor for a record iterator that takes an InputStream of a RecordSet file and
   * iterates through its record elements.  This creates a BufferedInputStream on the
   * InputStream.
   *
   * @param inputStream - InputStream to read a GedcomX RecordSet file from.
   * @throws IOException
   */
  public JsonRecordSetIterator(InputStream inputStream) throws IOException {
    this.inputStream = new BufferedInputStream(inputStream);

    objectMapper = GedcomJacksonModule.createObjectMapper();

    // Read the beginning object brace { plus label: {"metadata":
    int character = inputStream.read();
    assert character == '{';

    // Read until you get to the "records": [ section of the stream
    noMoreRecords = false;
    readUntil(inputStream, "records");

    // Read the opening array bracket [
    readUntilChar(inputStream, '[');

    prepareNext();
  }

  private void readUntil(InputStream inputStream, String untilLabel) throws IOException {
    // Read until we see the first label (meaning opening quote
    String name;

    while (! (name = getName(inputStream)).equals(untilLabel)) {
      if (name.equals(untilLabel)) {
        break;
      }

      // Otherwise look for some other objects.
      if (name.equals("metadata")) {
        readMetadata(inputStream);
      }

      else if (name.equals("id")) {
        id = getName(inputStream, false);
      }
    }
  }

  private void readMetadata(InputStream inputStream) throws IOException {
    // I have to do it this way since if I pass the inputStream to objectMapper.readValue() it leaves
    // the inputStream past the end of the actual metadata object.  Then the next getName() is lost.
    byte[] object = getObjectAsBytes(inputStream);
    metadata = objectMapper.readValue(object, Gedcomx.class);
  }

  /**
   * Read in the current Json object from the stream.  It will read the opening brace til the end brace
   * and return that in a string.  If the first character is a comma then it will ignore it.
   */
  private byte[] getObjectAsBytes(InputStream inputStream) throws IOException {
    int character;
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    int openingBraces = 0;
    int closingBraces = 0;
    boolean firstTime = true;

    while ((character = inputStream.read()) >= 0) {
      if (character == ',') {
        if (firstTime) {
          firstTime = false;
          continue;   // Ignore comma if it is the first read character.
        }
      }
      firstTime = false;

      bos.write(character);

      if (character == '{') {
        openingBraces++;
      }
      else if (character == '}') {
        closingBraces++;
      }

      if ((openingBraces > 0) && (openingBraces == closingBraces)) {
        break;
      }
    }

    return bos.toByteArray();
  }

  /**
   * Read from the stream until it finds an opening quote.  Then read the characters until the next quote mark
   * as the name.
   * This will also consume the trailing colon character (:).
   */
  private String getName(InputStream inputStream) throws IOException {
    return getName(inputStream, true);
  }

  private String getName(InputStream inputStream, boolean consumeColon) throws IOException {
    StringBuilder name = new StringBuilder();
    readUntilChar(inputStream, '"');
    int character;
    while ((character = inputStream.read()) != '"') {
      name.append((char)character);
    }
    if (consumeColon) {
      readUntilChar(inputStream, ':');
    }

    return name.toString();
  }

  private void readUntilChar(InputStream inputStream, char c) throws IOException {
    int character;
    while ((character = inputStream.read()) != c) {
      if (character < 0) {
        close();
        break;    // End of file.
      }
    }
  }

  /**
   * Tell whether the RecordIterator has another GedcomX record to return.
   * @return true if there is another record to read; false otherwise.
   */
  @Override
  synchronized public boolean hasNext() {
    return nextRecord != null;
  }

  /**
   * Prepare the next record to be retrieved.  Sets 'nextRecord' to the parsed record, if any, or null
   *   if there are no more.  Consumes bytes from the xmlStreamReader.
   * This does not close the inputStream once there are no more records to read.  The metadata
   * may be after the Records.
   */
  synchronized private void prepareNext() throws IOException {
    // I have to do it this way since if I pass the inputStream to objectMapper.readValue() it leaves
    // the inputStream past the end of the actual record object.  Then the next get is lost.
    if (noMoreRecords) {
      nextRecord = null;
      return;
    }

    byte[] object = getObjectAsBytes(inputStream);
    nextRecord = objectMapper.readValue(object, Gedcomx.class);

    // Need to read past the next comma separating records.
    // We also might see the end of the array bracket ].
    int character;
    while ((character = inputStream.read()) != ',') {
      if (character == ']') {
        noMoreRecords = true;
        break;
      }
    }
  }

  @Override
  synchronized public Gedcomx next() {
    try {
      if (nextRecord == null) {
        return null;
      }
      Gedcomx record = nextRecord;
      prepareNext();
      return record;
    }
    catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * This should be read last as you cannot be sure of when the metadata will appear in the data stream.
   *
   * @return The Metadata document.
   */
  synchronized public Gedcomx getMetadata() throws IOException {
    if (metadata == null) {
      readUntil(inputStream, JsonRecordSetWriter.METADATA_STR);
      readMetadata(inputStream);
    }
    return metadata;
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException();
  }

  /**
   * Close the input stream and accompanying reader if they are still open.
   * If you want to get the metadata and id of the RecordSet, then get them before you close().
   */
  public void close() throws IOException {
    if (inputStream != null) {
      inputStream.close();
      inputStream = null;
    }
  }

  public String getId() throws IOException {
    if (id == null) {
      readUntil(inputStream, JsonRecordSetWriter.ID_STR);
      id = getName(inputStream, false);
    }
    return id;
  }
}
