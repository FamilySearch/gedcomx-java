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
import org.junit.jupiter.api.Test;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class for testing the RecordIterator
 *
 * User: Randy Wilson
 * Date: 11/21/13
 * Time: 1:46 PM
 */
class TestRecordSetIterator {

  @Test
  void xmlStreamReaderCjk() throws Exception {
    String a = "\uD842\uDFB7"; // Unicode "surrogate pair" Chinese character.
    String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                     "<root a1='" + a + "' a2='" + a + "'></root>\n";
    ByteArrayInputStream bis = new ByteArrayInputStream(xml.getBytes("UTF-8"));
    XMLStreamReader xmlr = XMLInputFactory.newFactory().createXMLStreamReader(bis, "UTF-8");

    assertEquals(XMLStreamConstants.START_ELEMENT, xmlr.next());
    assertEquals(a, xmlr.getAttributeValue(0));
    assertEquals(a, xmlr.getAttributeValue(1));
  }

  @Test
  void parser() throws Exception {
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream("gedcomx-recordset.xml");
    RecordSetIterator recordIterator = new XmlRecordSetIterator(inputStream);
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

  @Test
  void parserWithMetadata() throws Exception {
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream("gedcomx-recordset2.xml");
    RecordSetIterator recordIterator = new XmlRecordSetIterator(inputStream);
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

  @Test
  void close() throws Exception {
    URL url = getClass().getClassLoader().getResource("gedcomx-recordset2.xml");
    assertNotNull(url);

    RecordSetIterator recordIterator = new XmlRecordSetIterator(url.getFile());

    assertTrue(recordIterator.hasNext());

    // Close it before it reaches the end of the file to test close.
    recordIterator.close();
  }
}
