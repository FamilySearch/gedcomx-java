package org.gedcomx.util;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.gedcomx.Gedcomx;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.io.InputStream;
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
  }
}