package org.gedcomx.util;

import junit.framework.TestCase;
import org.gedcomx.Gedcomx;
import org.gedcomx.conclusion.Name;
import org.gedcomx.conclusion.Person;

import javax.xml.bind.JAXBException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Class for testing the CleanXmlStreamWriter.
 * User: Randy Wilson
 * Date: 17 September 2015
 */
public class TestCleanXmlStreamWriter extends TestCase {
  public void testCjk() {
    String orig = "\uD850\uDDAC成功";
    String escaped = CleanXMLStreamWriter.escapeCharacters(orig);
    assertEquals(orig, escaped);
  }

  public void testVerticalTab() {
    String orig = "Vertical\u000BTab";
    String escaped = CleanXMLStreamWriter.escapeCharacters(orig);
    // Make sure the vertical tab got replaced with a Unicode "Replacement Character" (0xFFFD)
    assertEquals("Vertical\uFFFDTab", escaped);
  }

  public void testSurrogatePairs() throws JAXBException, IOException {
    String orig = "\uD850\uDDAC成功";
    Gedcomx doc = new Gedcomx();
    Person person = new Person();
    doc.addPerson(person);
    Name name = new Name(orig);
    person.addName(name);

    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    RecordSetWriter cleanWriter = new RecordSetWriter(bos, false);
    cleanWriter.writeRecord(doc);
    cleanWriter.close();

    RecordSetIterator recordSetIterator = new XmlRecordSetIterator(new ByteArrayInputStream(bos.toByteArray()), false);
    Gedcomx doc1 = recordSetIterator.next();
    assertEquals(orig, doc1.getPerson().getName().getNameForm().getFullText());

    bos = new ByteArrayOutputStream();
    RecordSetWriter cleanWriter2 = new RecordSetWriter(bos, true);
    cleanWriter2.writeRecord(doc);
    cleanWriter2.close();
    recordSetIterator = new XmlRecordSetIterator(new ByteArrayInputStream(bos.toByteArray()), false);
    Gedcomx doc2 = recordSetIterator.next();
    String actual = doc2.getPerson().getName().getNameForm().getFullText();
    assertEquals(orig, actual);
  }
}
