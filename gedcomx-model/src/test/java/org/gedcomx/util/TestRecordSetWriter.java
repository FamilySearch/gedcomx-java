package org.gedcomx.util;

import junit.framework.TestCase;
import org.gedcomx.Gedcomx;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPOutputStream;

/**
 * Class for testing the RecordSetWriter and RecordSetIterator class.
 * User: Randy Wilson
 * Date: 12/4/13
 * Time: 3:39 PM
 */
public class TestRecordSetWriter extends TestCase {

  public void testRecordSetWriter() throws IOException, JAXBException {
    for (boolean isGzipped : new boolean[]{false, true}) {
      InputStream inputStream = getClass().getClassLoader().getResourceAsStream("gedcomx-recordset.xml");
      int numRecords = 0;
      RecordSetIterator recordIterator = new RecordSetIterator(inputStream, false);
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      OutputStream outputStream = isGzipped ? new GZIPOutputStream(bos) : bos;
      RecordSetWriter writer = new RecordSetWriter(outputStream);
      Gedcomx record;
      List<String> recordIds = new ArrayList<String>();
      List<Gedcomx> records = new ArrayList<Gedcomx>();

      String[] expectedRecordIds = new String[]{"r_14946444", "r_21837581269", "r_731503667"};
      while ((record = recordIterator.next()) != null) {
        assertEquals(expectedRecordIds[numRecords++], record.getId());
        writer.writeRecord(record);
        records.add(record);
        recordIds.add(record.getId());
      }
      writer.close();
      assertEquals(3, records.size());

      byte[] bytes = bos.toByteArray();
      recordIterator = new RecordSetIterator(new ByteArrayInputStream(bytes), isGzipped);
      for (int i = 0; i < numRecords; i++) {
        record = recordIterator.next();
        assertNotNull(record);
        assertEquals(recordIds.get(i), record.getId());
      }
      assertNull(recordIterator.next());
    }
  }
}
