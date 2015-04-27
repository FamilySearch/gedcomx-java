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

import org.gedcomx.Gedcomx;
import org.gedcomx.records.RecordSet;
import org.gedcomx.rt.GedcomxConstants;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.*;
import java.util.Iterator;
import java.util.zip.GZIPInputStream;

/**
 * Class for iterating through the 'record' elements (GedcomX documents) in a RecordSet one at a time
 *   from a stream (e.g., a gzipped byte array) without having to inflate all the records at once.
 * User: Randy Wilson
 * Date: 7/31/2014
 * Time: 1:58 PM
 */
public class RecordSetIterator implements Iterator<Gedcomx> {
  private static final QName recordName = new QName(GedcomxConstants.GEDCOMX_NAMESPACE, "record");
  private static final QName metadataName = new QName(GedcomxConstants.GEDCOMX_NAMESPACE, "metadata");
  private static JAXBContext jaxbContext = null;
  private BufferedReader reader;
  private XMLStreamReader xmlStreamReader;
  private Gedcomx nextRecord;
  private Unmarshaller unmarshaller;
  private Gedcomx metadata;

  static {
    try {
      jaxbContext = JAXBContext.newInstance(RecordSet.class, Gedcomx.class);
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  /**
   * Constructor for a record iterator that takes a filename of a RecordSet file and iterates through its record elements.
   * @param filename - Filename to read a GedcomX RecordSet file from.
   * @throws java.io.IOException
   */
  public RecordSetIterator(String filename) throws IOException {
    this(new FileInputStream(filename), filename.toLowerCase().endsWith(".gz"));
  }

  public RecordSetIterator(InputStream inputStream, boolean isGzipped) throws IOException {
    this(isGzipped ? new GZIPInputStream(inputStream) : inputStream);
  }

  /**
   * Constructor for a record iterator that takes an InputStream of a RecordSet file and iterates through its record elements.
   * @param inputStream - InputStream to read a GedcomX RecordSet file from.
   * @throws IOException
   */
  public RecordSetIterator(InputStream inputStream) throws IOException {
    this(new BufferedReader(new InputStreamReader(inputStream, "UTF-8")));
  }

  /**
   * Constructor for a record iterator that takes a BufferedReader of a RecordSet file and iterates through its record elements.
   * @param reader - BufferedReader to read a GedcomX RecordSet file from.
   * @throws IOException
   */
  public RecordSetIterator(BufferedReader reader) throws IOException {
    try {
      this.reader = reader;
      xmlStreamReader = XMLInputFactory.newFactory().createXMLStreamReader(reader);
      unmarshaller = jaxbContext.createUnmarshaller();
      prepareNext();
    } catch (JAXBException e) {
      throw new RuntimeException(e);
    } catch (XMLStreamException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Tell whether the RecordIterator has another GedcomX record to return.
   * @return true if there is another record to read; false otherwise.
   */
  synchronized public boolean hasNext() {
    return nextRecord != null;
  }

  /**
   * Prepare the next record to be retrieved.  Sets 'nextRecord' to the parsed record, if any, or null
   *   if there are no more.  Consumes bytes from the xmlStreamReader.
   * If there are no more records, then closes the reader and xmlStreamReader and sets them both to null.
   * @throws XMLStreamException
   * @throws JAXBException
   */
  synchronized private void prepareNext() throws XMLStreamException, JAXBException, IOException {
    nextRecord = null;
    while (xmlStreamReader.hasNext() && nextRecord == null) {
      if (xmlStreamReader.isStartElement() && xmlStreamReader.getName().equals(recordName)) {
        nextRecord = unmarshaller.unmarshal(xmlStreamReader, Gedcomx.class).getValue();
      }
      else if (xmlStreamReader.isStartElement() && xmlStreamReader.getName().equals(metadataName)) {
        if (metadata != null) {
          throw new IllegalStateException("Cannot have two metadata elements in a RecordSet");
        }
        metadata = unmarshaller.unmarshal(xmlStreamReader, Gedcomx.class).getValue();
        // nextRecord will still be null, so we will go around again.
      }
      else {
        xmlStreamReader.next();
      }
    }
    if (nextRecord == null) {
      xmlStreamReader.close();
      xmlStreamReader = null;
      reader.close();
      reader = null;
    }
  }

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

  synchronized public Gedcomx getMetadata() {
    return metadata;
  }

  public void remove() {
    throw new UnsupportedOperationException();
  }

  /**
   * Close the input stream and accompanying reader if they are still open.
   */
  public void close() throws XMLStreamException, IOException {
    if (xmlStreamReader != null) {
      xmlStreamReader.close();
      xmlStreamReader = null;
    }

    if (reader != null) {
      reader.close();
      reader = null;
    }
  }
}
