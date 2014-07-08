package org.gedcomx.util;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.gedcomx.Gedcomx;
import org.gedcomx.agent.Agent;
import org.gedcomx.common.URI;
import org.gedcomx.conclusion.Identifier;
import org.gedcomx.conclusion.Person;
import org.gedcomx.records.RecordDescriptor;
import org.gedcomx.source.SourceDescription;
import org.gedcomx.source.SourceReference;
import org.gedcomx.types.IdentifierType;

import java.util.Arrays;

/**
 * Class for...
 * User: Randy Wilson
 * Date: 6/10/14
 * Time: 12:19 PM
 */
public class TestDocMap extends TestCase {

  public void testStuff() {
    Gedcomx doc = new Gedcomx();
    Person p1 = new Person();
    p1.setId("p1");
    p1.addIdentifier(new Identifier(new URI("http://test.com/person1"), IdentifierType.Primary));
    // Alternate id for the same person resource.
    p1.addIdentifier(new Identifier(new URI("http://alternate.com/oldPerson1"), null));
    doc.addPerson(p1);

    SourceDescription sd1 = new SourceDescription();
    sd1.setId("sd1");
    sd1.setAbout(new URI("http://test.com/person1"));
    sd1.setIdentifiers(p1.getIdentifiers()); // copy the same list over from person p1.
    doc.setDescriptionRef(new URI("#sd1"));
    doc.addSourceDescription(sd1);

    SourceDescription sd2 = new SourceDescription();
    sd2.setId("sd2");
    sd2.setAbout(new URI("http://test.com/image123"));
    sd2.setIdentifiers(Arrays.asList(new Identifier(new URI("http://test.com/image123"), IdentifierType.Primary)));
    SourceReference sr = new SourceReference();
    sr.setDescriptionRef(new URI("#sd2"));
    sd1.setSources(Arrays.asList(sr));
    doc.addSourceDescription(sd2);

    SourceDescription sd3 = new SourceDescription();
    sd3.setId("sd3");
    sd3.setAbout(new URI("http://test.com/record1"));
    sd3.setIdentifiers(Arrays.asList(new Identifier(new URI("http://test.com/record1"), IdentifierType.Primary)));
    SourceReference componentOf = new SourceReference();
    componentOf.setDescriptionRef(new URI("#sd3"));
    sd1.setComponentOf(componentOf);
    doc.addSourceDescription(sd3);

    Agent agent = new Agent();
    agent.setId("agent1");
    doc.addAgent(agent);

    RecordDescriptor rd = new RecordDescriptor();
    rd.setId("rd1");
    doc.addRecordDescriptor(rd);

    DocMap docMap = new DocMap(doc);

    assertEquals("p1", docMap.getPerson("p1").getId());
    assertEquals("p1", docMap.getPerson("#p1").getId());
    assertEquals("p1", docMap.getPerson("http://test.com/person1").getId());
    assertEquals("p1", docMap.getPerson("http://alternate.com/oldPerson1").getId());
    assertEquals("sd1", docMap.getSourceDescription("sd1").getId());
    assertEquals("sd1", docMap.getSourceDescription("#sd1").getId());
    assertEquals("sd1", docMap.getSourceDescription("http://test.com/person1").getId());
    assertEquals("sd1", docMap.getSourceDescription("http://alternate.com/oldPerson1").getId());
    assertEquals("sd2", docMap.getSourceDescription("#sd2").getId());
    assertEquals("sd1", docMap.getSourceDescription(doc.getDescriptionRef()).getId());
    assertEquals("p1", docMap.getPerson(docMap.getSourceDescription(doc.getDescriptionRef()).getAbout()).getId());
    assertEquals("agent1", docMap.getAgent("agent1").getId());
    assertEquals("agent1", docMap.getAgent("#agent1").getId());
    assertEquals("rd1", docMap.getRecordDescriptor("rd1").getId());
    assertEquals("rd1", docMap.getRecordDescriptor("#rd1").getId());
    assertEquals("rd1", docMap.getRecordDescriptor("https://whatever.com/collections/12345#rd1").getId());
    assertEquals("sd1", docMap.getMainSourceDescription().getId());
    assertEquals("p1", docMap.getMainPerson().getId());
  }

}