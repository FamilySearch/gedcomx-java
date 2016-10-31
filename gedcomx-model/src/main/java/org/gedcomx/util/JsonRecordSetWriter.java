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

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.gedcomx.Gedcomx;
import org.gedcomx.rt.json.GedcomJacksonModule;

import java.io.IOException;
import java.io.OutputStream;

public class JsonRecordSetWriter {
  // Stream to write data to
  private OutputStream outputStream;
  private ObjectMapper objectMapper;
  private long numOfRecords = 0;

  public static final String ID_STR = "id";
  public static final String METADATA_STR = "metadata";
  public static final String RECORDS_STR = "records";

  /**
   * Constructor. Prepares to write GedcomX document records to the given output stream (which may well be a
   *   GZIPOutputStream), so that only one such document needs to be fully instantiated in memory at once.
   *
   * @param outputStream - OutputStream to write the Gedcomx document to.
   * @param metadata - The metadata associated with this RecordSet.  It will be written to the stream first.
   */
  public JsonRecordSetWriter(OutputStream outputStream, Gedcomx metadata) {
    try {
      this.outputStream = outputStream;

      objectMapper = GedcomJacksonModule.createObjectMapper();
      // When the outputStream is gzipped calling writeValue() below will close the OutputStream.
      // These configurations will disable that feature.  See javadoc for writeValue().
      objectMapper.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);
      objectMapper.configure(JsonGenerator.Feature.AUTO_CLOSE_JSON_CONTENT, false);

      outputStream.write("{\n".getBytes());      // Begin JSON Object for the RecordSet

      writeLabel(outputStream, METADATA_STR);
      objectMapper.writeValue(outputStream, metadata);  // This insures that the metadata is at the top of the file.
      outputStream.write(",\n".getBytes());

      writeLabel(outputStream, RECORDS_STR);
      outputStream.write(" [\n".getBytes());      // Begin the array of Records

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private void writeLabel(OutputStream outputStream, String label) throws IOException {
    outputStream.write('"');
    outputStream.write(label.getBytes());
    outputStream.write("\":".getBytes());
  }

  public void writeRecord(Gedcomx record) throws IOException {
      if (numOfRecords > 0) {
        outputStream.write(',');
      }
      objectMapper.writeValue(outputStream, record);

//    if (! label.equals(METADATA_STR)) {
      numOfRecords++;
//    }
  }

  /**
   * Finish writing the file, including metadata (if set), and the closing tag. Closes the writers and output stream.
   */
  public void close() throws IOException {
    if (outputStream != null) {
      outputStream.write("]\n}".getBytes());    // End the JSON records array and the RecordSet object
      outputStream.close();
    }
  }

  public long getNumOfRecords() {
    return numOfRecords;
  }

}
