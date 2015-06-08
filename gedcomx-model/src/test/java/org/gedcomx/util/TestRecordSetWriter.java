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
    for (int metadataPos = -1; metadataPos <= 1; metadataPos++) {
      for (boolean isGzipped : new boolean[]{false, true}) {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("gedcomx-recordset.xml");
        int numRecords = 0;
        RecordSetIterator recordIterator = new RecordSetIterator(inputStream, false);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        OutputStream outputStream = isGzipped ? new GZIPOutputStream(bos) : bos;
        RecordSetWriter writer = new RecordSetWriter(outputStream);

        Gedcomx metadata = testWriteMetadata(metadataPos, writer);

        Gedcomx record;
        List<String> recordIds = new ArrayList<String>();
        List<Gedcomx> records = new ArrayList<Gedcomx>();

        String[] expectedRecordIds = new String[]{"r_14946444", "r_21837581269", "r_731503667"};
        boolean isFirst = true;
        // Read a record from the xml input file.
        while ((record = recordIterator.next()) != null) {
          assertEquals(expectedRecordIds[numRecords++], record.getId());

          writer.writeRecord(record);

          records.add(record);
          recordIds.add(record.getId());

          if (metadataPos == 1 && isFirst) {
            // try writing metadata in the middle of the records, to make sure it ends up at the end like it should.
            writer.setMetadata(metadata);
            isFirst = false;
          }
        }
        inputStream.close();

        if (metadataPos == 2) {
          writer.setMetadata(metadata);
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
        Gedcomx metadata2 = recordIterator.getMetadata();
        if (metadataPos != 0) {
          assertEquals(metadata2.getSourceDescription().getTitle().getValue(), metadata.getSourceDescription().getTitle().getValue());
        }
        else {
          assertNull(metadata2);
        }
        assertNull(recordIterator.next());
      }
    }
  }

  private Gedcomx testWriteMetadata(int metadataPos, RecordSetWriter recordSetWriter) throws JAXBException, IOException {
    Gedcomx metadata = null;
    if (metadataPos != 0) {
      metadata = getMetadataFromFile();
      if (metadataPos == -1) {
        // Write metadata at the beginning of the stream.
        recordSetWriter.setMetadata(metadata);
        // Make sure we can't write it twice
        try {
          recordSetWriter.setMetadata(metadata);
          fail("Should have thrown an exception when trying to write metadata twice.");
        }
        catch (IllegalStateException e) {
          // ok
        }
      }
    }
    return metadata;
  }

  public static Gedcomx getMetadataFromFile() throws JAXBException, IOException {
    Gedcomx metadata;
    InputStream metadataInputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("gedcomx-collection.xml");
    metadata = (Gedcomx) MarshalUtil.createUnmarshaller().unmarshal(metadataInputStream);
    metadataInputStream.close();
    return metadata;
  }

}
