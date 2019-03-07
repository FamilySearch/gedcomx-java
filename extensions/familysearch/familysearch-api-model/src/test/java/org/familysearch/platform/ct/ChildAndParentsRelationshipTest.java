/**
 * Copyright 2011 Intellectual Reserve, Inc. All Rights reserved.
 */
package org.familysearch.platform.ct;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import org.gedcomx.common.Note;
import org.gedcomx.common.ResourceReference;
import org.gedcomx.common.URI;
import org.gedcomx.conclusion.Date;
import org.gedcomx.conclusion.Fact;
import org.gedcomx.conclusion.PlaceReference;
import org.gedcomx.rt.json.GedcomJacksonModule;
import org.gedcomx.source.SourceReference;
import org.gedcomx.types.FactType;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

/**
 */
public class ChildAndParentsRelationshipTest {

  @Test
  public void testModel() {
    ArrayList<SourceReference> sources = new ArrayList<SourceReference>();
    ArrayList<Note> notes = new ArrayList<Note>();
    ChildAndParentsRelationship rel = new ChildAndParentsRelationship();
    rel.setParent1(new ResourceReference(URI.create("urn:parent1")));
    rel.setParent2(new ResourceReference(URI.create("urn:parent2")));
    rel.setChild(new ResourceReference(URI.create("urn:child")));
    rel.setSources(sources);
    rel.setNotes(notes);

    assertEquals(URI.create("urn:parent1"), rel.getParent1().getResource());
    assertEquals(URI.create("urn:parent2"), rel.getParent2().getResource());
    assertEquals(URI.create("urn:child"), rel.getChild().getResource());
    assertEquals(sources, rel.getSources());
    assertEquals(notes,rel.getNotes());

    rel.addParent1Fact(null);
    assertNull(rel.getParent1Facts());
    rel.addParent1Fact(new Fact(FactType.Birth, "origBirthValue"));
    rel.addParent1Fact(new Fact(FactType.Death, "origDeathValue"));
    assertNotNull(rel.getParent1Facts());
    assertEquals(rel.getParent1Facts().size(), 2);
    assertEquals(rel.getParent1Facts().get(0).getValue(), "origBirthValue");
    assertEquals(rel.getParent1Facts().get(1).getValue(), "origDeathValue");

    rel.addParent2Fact(null);
    assertNull(rel.getParent2Facts());
    rel.addParent2Fact(new Fact(FactType.Birth, "origBirthValue"));
    rel.addParent2Fact(new Fact(FactType.Death, "origDeathValue"));
    assertNotNull(rel.getParent2Facts());
    assertEquals(rel.getParent2Facts().size(), 2);
    assertEquals(rel.getParent2Facts().get(0).getValue(), "origBirthValue");
    assertEquals(rel.getParent2Facts().get(1).getValue(), "origDeathValue");
  }

  @Test
  public void testDeprecatedModel() {
    ArrayList<SourceReference> sources = new ArrayList<SourceReference>();
    ArrayList<Note> notes = new ArrayList<Note>();
    ChildAndParentsRelationship rel = new ChildAndParentsRelationship();
    rel.setFather(new ResourceReference(URI.create("urn:father")));
    rel.setMother(new ResourceReference(URI.create("urn:mother")));
    rel.setChild(new ResourceReference(URI.create("urn:child")));
    rel.setSources(sources);
    rel.setNotes(notes);

    assertEquals(URI.create("urn:father"), rel.getFather().getResource());
    assertEquals(URI.create("urn:mother"), rel.getMother().getResource());
    assertEquals(URI.create("urn:child"), rel.getChild().getResource());
    assertEquals(sources, rel.getSources());
    assertEquals(notes,rel.getNotes());

    rel.setFatherFacts(null);
    assertNull(rel.getFatherFacts());
    List<Fact> tmpFatherFacts = new ArrayList<Fact>();
    tmpFatherFacts.add(new Fact(FactType.Birth, "origBirthValue"));
    tmpFatherFacts.add(new Fact(FactType.Death, "origDeathValue"));
    rel.setFatherFacts(tmpFatherFacts);
    assertNotNull(rel.getFatherFacts());
    assertEquals(rel.getFatherFacts().size(), 2);
    assertEquals(rel.getFatherFacts().get(0).getValue(), "origBirthValue");
    assertEquals(rel.getFatherFacts().get(1).getValue(), "origDeathValue");

    rel.setMotherFacts(null);
    assertNull(rel.getMotherFacts());
    List<Fact> tmpMotherFacts = new ArrayList<Fact>();
    tmpMotherFacts.add(new Fact(FactType.Birth, "origBirthValue"));
    tmpMotherFacts.add(new Fact(FactType.Death, "origDeathValue"));
    rel.setMotherFacts(tmpMotherFacts);
    assertNotNull(rel.getMotherFacts());
    assertEquals(rel.getMotherFacts().size(), 2);
    assertEquals(rel.getMotherFacts().get(0).getValue(), "origBirthValue");
    assertEquals(rel.getMotherFacts().get(1).getValue(), "origDeathValue");
  }


  @Test
  public void testMarshalling() {
    ChildAndParentsRelationship origChildAndParentsRelationship = createChildAndParentsRelationship();
    ByteArrayOutputStream outStream = new ByteArrayOutputStream(1024); //figure out where you want to write the XML

    try {
      JAXBContext context = JAXBContext.newInstance(ChildAndParentsRelationship.class);
      Marshaller marshaller = context.createMarshaller();
      marshaller.marshal(origChildAndParentsRelationship, outStream);
      //marshaller.setProperty("jaxb.formatted.output", Boolean.TRUE);

      ByteArrayInputStream inStream = new ByteArrayInputStream(outStream.toByteArray());
      Unmarshaller unmarshaller = context.createUnmarshaller();
      ChildAndParentsRelationship roundTrippedChildAndParentsRelationship = (ChildAndParentsRelationship)unmarshaller.unmarshal(inStream);

      compareChildAndParentsRelationship(roundTrippedChildAndParentsRelationship, origChildAndParentsRelationship);
    }
    catch (JAXBException e) {
      fail("Failed to marshall XML " + e.toString());
    }

    outStream.reset();

    try {
      ObjectMapper mapper = GedcomJacksonModule.createObjectMapper(ChildAndParentsRelationship.class);
      mapper.writeValue(outStream, origChildAndParentsRelationship);

      ByteArrayInputStream inStream = new ByteArrayInputStream(outStream.toByteArray());
      ChildAndParentsRelationship roundTrippedChildAndParentsRelationship = mapper.readValue(inStream, ChildAndParentsRelationship.class);

      compareChildAndParentsRelationship(roundTrippedChildAndParentsRelationship, origChildAndParentsRelationship);
    }
    catch (IOException e) {
      fail("Failed to marshall Json " + e.toString());
    }
  }

  private void compareChildAndParentsRelationship(ChildAndParentsRelationship actual, ChildAndParentsRelationship expected) {
    if ((actual == null) || (expected == null)) {
      fail("ChildAndParentsRelationship should not be null");
    }
    compareResourceReference("Child ResourceReference", actual.getChild(), expected.getChild());
    compareResourceReference("Parent1 ResourceReference", actual.getParent1(), expected.getParent1());
    compareResourceReference("Parent2 ResourceReference", actual.getParent2(), expected.getParent2());

    compareFactsList("Parent1 Facts", actual.getParent1Facts(), expected.getParent1Facts());
    compareFactsList("Parent2 Facts", actual.getParent2Facts(), expected.getParent2Facts());
  }

  private void compareResourceReference(String msg, ResourceReference actual, ResourceReference expected) {
    if ((actual != null) && (expected != null)) {
      // We don't have apache commons lang routines - keeping it basic libraries.
      final String actualResource = (actual.getResource() == null) ? "" : actual.getResource().toString();
      final String expectedResource = (expected.getResource() == null) ? "" : expected.getResource().toString();
      final String actualResourceId = (actual.getResourceId() == null) ? "" : actual.getResourceId();
      final String expectedResourceId = (expected.getResourceId() == null) ? "" : expected.getResourceId();

      assertThat(msg, actualResource, is(expectedResource));
      assertThat(msg, actualResourceId, is(expectedResourceId));
    }
    if (((actual != null) && (expected == null)) || ((actual == null) && (expected != null))) {
      fail(msg + " single null ResourceReference exists");
    }
  }

  /**
   * For now this assumes that both facts are in the exact same order.
   */
  private void compareFactsList(String msg, List<Fact> actual, List<Fact> expected) {
    final int actualSize = (actual == null) ? 0 : actual.size();
    final int expectedSize = (expected == null) ? 0 : expected.size();

    assertThat(msg + " size", actualSize, is(expectedSize));
    for (int ii = 0; ii < actualSize; ii++) {
      compareFact(msg+"["+ii+"]", actual.get(ii), expected.get(ii));
    }
  }

  /**
   * Does not test every property, just some of them
   */
  private void compareFact(String msg, Fact actual, Fact expected) {
    // does not test every possible
    final String actualURIType = (actual.getType() == null) ? "" : actual.getType().toString();
    final String actualDate = (actual.getDate() == null) ? "" : actual.getDate().toString();
    final String actualPlace = (actual.getPlace() == null) ? "" : actual.getPlace().toString();
    final String actualValue = (actual.getValue() == null) ? "" : actual.getValue();
    final int actualQualifierCnt = (actual.getQualifiers() == null) ? 0 : actual.getQualifiers().size();    // not a deep compare
    final int actualFieldCnt = (actual.getFields() == null) ? 0 : actual.getFields().size();                // not a deep compare
    final String actualPrimary = (actual.getPrimary() == null) ? "" : actual.getPrimary().toString();
    final String actualId = (actual.getId() == null) ? "" : actual.getId();
    final String actualLang = (actual.getLang() == null) ? "" : actual.getLang();
    final String actualSortKey = (actual.getSortKey() == null) ? "" : actual.getSortKey();

    final String expectedURIType = (expected.getType() == null) ? "" : expected.getType().toString();
    final String expectedDate = (expected.getDate() == null) ? "" : expected.getDate().toString();
    final String expectedPlace = (expected.getPlace() == null) ? "" : expected.getPlace().toString();
    final String expectedValue = (expected.getValue() == null) ? "" : expected.getValue();
    final int expectedQualifierCnt = (expected.getQualifiers() == null) ? 0 : expected.getQualifiers().size();    // not a deep compare
    final int expectedFieldCnt = (expected.getFields() == null) ? 0 : expected.getFields().size();                // not a deep compare
    final String expectedPrimary = (expected.getPrimary() == null) ? "" : expected.getPrimary().toString();
    final String expectedId = (expected.getId() == null) ? "" : expected.getId();
    final String expectedLang = (expected.getLang() == null) ? "" : expected.getLang();
    final String expectedSortKey = (expected.getSortKey() == null) ? "" : expected.getSortKey();

    assertThat(msg, actualURIType, is(expectedURIType));
    assertThat(msg, actualDate, is(expectedDate));
    assertThat(msg, actualPlace, is(expectedPlace));
    assertThat(msg, actualValue, is(expectedValue));
    assertThat(msg, actualQualifierCnt, is(expectedQualifierCnt));
    assertThat(msg, actualFieldCnt, is(expectedFieldCnt));
    assertThat(msg, actualPrimary, is(expectedPrimary));

    assertThat(msg, actualId, is(expectedId));
    assertThat(msg, actualLang, is(expectedLang));
    assertThat(msg, actualSortKey, is(expectedSortKey));
  }

  private ChildAndParentsRelationship createChildAndParentsRelationship() {
    ChildAndParentsRelationship childAndParentsRelationship = new ChildAndParentsRelationship();

    ResourceReference childResourceReference = new ResourceReference(URI.create("urn:child"), "childId");
    ResourceReference parent1ResourceReference = new ResourceReference(URI.create("urn:parent1"), "parent1Id");
    ResourceReference parent2ResourceReference = new ResourceReference(URI.create("urn:parent2"), "parent2Id");

    Fact fact1a = new Fact();
    fact1a.setKnownType(FactType.Birth);
    fact1a.setValue("fact1a Value");
    fact1a.setId("fact1a Id");
    fact1a.setLang("fact1a lang");
    fact1a.setSortKey("fact1a sortKey");
    fact1a.setDate(new Date());
    fact1a.getDate().setOriginal("February 22, 1732");
    fact1a.getDate().setFormal("+1732-02-22");
    fact1a.setPlace(new PlaceReference());
    fact1a.getPlace().setOriginal("fact1a original place");
    fact1a.getPlace().setDescriptionRef(URI.create("#fact1a placeId"));

    Fact fact1b = new Fact();
    fact1b.setKnownType(FactType.Death);
    fact1b.setValue("fact1b Value");
    fact1b.setId("fact1b Id");
    fact1b.setLang("fact1b lang");
    fact1b.setSortKey("fact1b sortKey");
    fact1b.setDate(new Date());
    fact1b.getDate().setOriginal("February 25, 1765");
    fact1b.getDate().setFormal("+1765-02-25");
    fact1b.setPlace(new PlaceReference());
    fact1b.getPlace().setOriginal("fact1b original place");
    fact1b.getPlace().setDescriptionRef(URI.create("#fact1b placeId"));

    Fact fact2a = new Fact();
    fact1a.setKnownType(FactType.Confirmation);
    fact1a.setValue("fact1a Value");
    fact1a.setId("fact1a Id");
    fact1a.setLang("fact1a lang");
    fact1a.setSortKey("fact1a sortKey");
    fact1a.setDate(new Date());
    fact1a.getDate().setOriginal("April 17, 1836");
    fact1a.getDate().setFormal("+1836-04-17");
    fact1a.setPlace(new PlaceReference());
    fact1a.getPlace().setOriginal("fact1a original place");
    fact1a.getPlace().setDescriptionRef(URI.create("#fact1a placeId"));

    Fact fact2b = new Fact();
    fact2b.setKnownType(FactType.Cremation);
    fact2b.setValue("fact2b Value");
    fact2b.setId("fact2b Id");
    fact2b.setLang("fact2b lang");
    fact2b.setSortKey("fact2b sortKey");
    fact2b.setDate(new Date());
    fact2b.getDate().setOriginal("June 11, 1840");
    fact2b.getDate().setFormal("+1840-06-11");
    fact2b.setPlace(new PlaceReference());
    fact2b.getPlace().setOriginal("fact2b original place");
    fact2b.getPlace().setDescriptionRef(URI.create("#fact2b placeId"));


    childAndParentsRelationship.setChild(childResourceReference);
    childAndParentsRelationship.setParent1(parent1ResourceReference);
    childAndParentsRelationship.setParent2(parent2ResourceReference);

    childAndParentsRelationship.addParent1Fact(fact1a);
    childAndParentsRelationship.addParent1Fact(fact1b);
    childAndParentsRelationship.addParent2Fact(fact2a);
    childAndParentsRelationship.addParent2Fact(fact2b);

    return childAndParentsRelationship;
  }

}
