package org.gedcomx.util;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.gedcomx.Gedcomx;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;

/**
 * Class for testing the RecordIterator
 * User: Randy Wilson
 * Date: 11/21/13
 * Time: 1:46 PM
 */
public class TestRecordSetIterator extends TestCase {

  public void testParser() throws IOException, XMLStreamException {
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream("gedcomx-recordset.xml");
    Iterator<Gedcomx> recordIterator = new RecordSetIterator(inputStream);
    assertTrue(recordIterator.hasNext());
    Gedcomx record1 = recordIterator.next();
    Gedcomx record2 = recordIterator.next();
    Gedcomx record3 = recordIterator.next();
    assertEquals("r_14946444", record1.getId());
    assertEquals("r_21837581269", record2.getId());
    assertEquals("r_731503667", record3.getId());
    assertNotNull(record1);
    assertNotNull(record2);
    assertNotNull(record3);
    assertEquals(6, record3.getPersons().size());
    assertFalse(recordIterator.hasNext());
    assertNull(recordIterator.next());
    assertNull(recordIterator.next());
    inputStream.close();
  }

  public void testParserWithMetadata() throws IOException, XMLStreamException {
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream("gedcomx-recordset2.xml");
    RecordSetIterator recordIterator = new RecordSetIterator(inputStream);
    assertTrue(recordIterator.hasNext());
    // Haven't hit it yet.
    assertNull(recordIterator.getMetadata());
    Gedcomx record1 = recordIterator.next();
    // Haven't hit it yet.
    assertNull(recordIterator.getMetadata());
    Gedcomx record2 = recordIterator.next();
    // Haven't hit it yet.
    assertNull(recordIterator.getMetadata());
    Gedcomx record3 = recordIterator.next();
    // By the time we've read the last record, we have seen the metadata and know there are no more records.
    assertNotNull(recordIterator.getMetadata());
    assertEquals("r_14946444", record1.getId());
    assertEquals("r_21837581269", record2.getId());
    assertEquals("r_731503667", record3.getId());
    assertNotNull(record1);
    assertNotNull(record2);
    assertNotNull(record3);
    assertEquals(6, record3.getPersons().size());
    assertFalse(recordIterator.hasNext());
    assertNull(recordIterator.next());
    assertNull(recordIterator.next());
    assertNotNull(recordIterator.getMetadata());
    recordIterator.close();
  }

  public void testClose() throws IOException, XMLStreamException {
    URL url = getClass().getClassLoader().getResource("gedcomx-recordset2.xml");
    RecordSetIterator recordIterator = new RecordSetIterator(url.getFile());
    assertTrue(recordIterator.hasNext());
    // Haven't hit it yet.
    assertNull(recordIterator.getMetadata());
    Gedcomx record1 = recordIterator.next();
    // Haven't hit it yet.
    assertNull(recordIterator.getMetadata());
    Gedcomx record2 = recordIterator.next();

    // Close it before it reaches the end of the file to test close.
    recordIterator.close();
  }
}