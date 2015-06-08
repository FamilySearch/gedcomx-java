package org.gedcomx.util;

import junit.framework.TestCase;
import org.gedcomx.Gedcomx;

import javax.xml.bind.JAXBException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPOutputStream;

/**
 * User: brenthale
 * Date: 6/8/2015
 * <p/>
 * Copyright FamilySearch.org
 */
public class JsonRecordSetWriterTest extends TestCase {

  public void testRecordSetWriterJson() throws IOException, JAXBException {
    for (boolean isGzipped : new boolean[]{false, true}) {
      InputStream inputStream = getClass().getClassLoader().getResourceAsStream("gedcomx-recordset.json");

      int numRecords = 0;
      JsonRecordSetIterator jsonRecordSetIterator1 = new JsonRecordSetIterator(inputStream, false);

      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      OutputStream outputStream = isGzipped ? new GZIPOutputStream(bos) : bos;

      Gedcomx metadata = TestRecordSetWriter.getMetadataFromFile();

      JsonRecordSetWriter jsonRecordSetWriter = new JsonRecordSetWriter(outputStream, metadata);

      Gedcomx record;
      List<String> recordIds = new ArrayList<String>();
      List<Gedcomx> records = new ArrayList<Gedcomx>();

      String[] expectedRecordIds = new String[]{"r_14946444", "r_21837581269", "r_731503667"};
      // Read a record from the json input file.
      while ((record = jsonRecordSetIterator1.next()) != null) {
        assertEquals(expectedRecordIds[numRecords++], record.getId());

        // Try and write it to the json writer.
        jsonRecordSetWriter.writeRecord(record);

        records.add(record);
        recordIds.add(record.getId());
      }
      jsonRecordSetIterator1.close();

      assertEquals(3, jsonRecordSetWriter.getNumOfRecords());

      jsonRecordSetWriter.close();
      assertEquals(3, records.size());

      // Now try and read back in from the JSON.
      byte[] bytes = bos.toByteArray();
      JsonRecordSetIterator jsonRecordSetIterator2 = new JsonRecordSetIterator(new ByteArrayInputStream(bytes), isGzipped);
      for (int i = 0; i < numRecords; i++) {
        record = jsonRecordSetIterator2.next();
        assertNotNull(record);
        assertEquals(recordIds.get(i), record.getId());
      }
      Gedcomx metadata2 = jsonRecordSetIterator2.getMetadata();
      assertEquals(metadata2.getSourceDescription().getTitle().getValue(), metadata.getSourceDescription().getTitle().getValue());
      assertNull(jsonRecordSetIterator2.next());
      jsonRecordSetIterator2.close();
    }
  }
}