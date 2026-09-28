package org.familysearch.platform.ct;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.gedcomx.common.ResourceReference;
import org.gedcomx.common.URI;
import org.gedcomx.conclusion.Fact;
import org.gedcomx.rt.json.GedcomJacksonModule;
import org.gedcomx.types.FactType;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.json.JsonMapper;

import static org.junit.jupiter.api.Assertions.*;

class AssociationTest {

  @Test
  void model() {
    Association association = new Association();
    association.setType(FamilySearchAssociationType.MasterToApprentice.toQNameURI());
    association.setPerson1(new ResourceReference(URI.create("urn:person1")));
    association.setPerson2(new ResourceReference(URI.create("urn:person2")));

    assertEquals(FamilySearchAssociationType.MasterToApprentice.toQNameURI(), association.getType());
    assertEquals(FamilySearchAssociationType.MasterToApprentice, association.getKnownType());
    assertEquals(URI.create("urn:person1"), association.getPerson1().getResource());
    assertEquals(URI.create("urn:person2"), association.getPerson2().getResource());
    assertNull(association.getFacts());

    association.addFact(null);
    assertNull(association.getFacts());

    association.addFact(new Fact(FactType.Occupation, "apprentice"));
    assertNotNull(association.getFacts());
    assertEquals(1, association.getFacts().size());
    assertEquals("apprentice", association.getFacts().get(0).getValue());
  }

  @Test
  void builder() {
    Association association = new Association()
        .type(FamilySearchAssociationType.EmployerToEmployee)
        .person1(new ResourceReference(URI.create("urn:employer")))
        .person2(new ResourceReference(URI.create("urn:employee")))
        .fact(new Fact(FactType.Occupation, "clerk"));

    assertEquals(FamilySearchAssociationType.EmployerToEmployee, association.getKnownType());
    assertEquals(URI.create("urn:employer"), association.getPerson1().getResource());
    assertEquals(URI.create("urn:employee"), association.getPerson2().getResource());
    assertEquals(1, association.getFacts().size());
    assertEquals("clerk", association.getFacts().get(0).getValue());
  }

  @Test
  void knownType() {
    Association association = new Association();

    assertNull(association.getKnownType());

    association.setKnownType(FamilySearchAssociationType.NeighborToNeighbor);
    assertEquals(FamilySearchAssociationType.NeighborToNeighbor, association.getKnownType());

    association.setKnownType(null);
    assertNull(association.getType());
  }

  @Test
  void embed() {
    Association target = new Association();
    target.setId("assoc1");
    target.addFact(new Fact(FactType.Occupation, "original"));

    Association source = new Association();
    source.setId("assoc1");
    source.addFact(new Fact(FactType.Occupation, "added"));

    target.embed(source);
    assertEquals(2, target.getFacts().size());
    assertEquals("original", target.getFacts().get(0).getValue());
    assertEquals("added", target.getFacts().get(1).getValue());
  }

  @Test
  void marshalling() throws Exception {
    Association orig = buildTestAssociation();

    ByteArrayOutputStream outStream = new ByteArrayOutputStream(1024);
    JAXBContext context = JAXBContext.newInstance(Association.class);
    Marshaller marshaller = context.createMarshaller();
    marshaller.marshal(orig, outStream);

    ByteArrayInputStream inStream = new ByteArrayInputStream(outStream.toByteArray());
    Unmarshaller unmarshaller = context.createUnmarshaller();
    Association roundTripped = (Association) unmarshaller.unmarshal(inStream);

    compareAssociation(orig, roundTripped);

    outStream.reset();
    JsonMapper mapper = GedcomJacksonModule.createJsonMapper(Association.class);
    mapper.writeValue(outStream, orig);

    inStream = new ByteArrayInputStream(outStream.toByteArray());
    roundTripped = mapper.readValue(inStream, Association.class);

    compareAssociation(orig, roundTripped);
  }

  private Association buildTestAssociation() {
    Association association = new Association();
    association.setId("assoc1");
    association.setKnownType(FamilySearchAssociationType.MasterToApprentice);
    association.setPerson1(new ResourceReference(URI.create("urn:master"), "masterId"));
    association.setPerson2(new ResourceReference(URI.create("urn:apprentice"), "apprenticeId"));

    Fact fact = new Fact();
    fact.setKnownType(FactType.Occupation);
    fact.setValue("apprentice");
    fact.setId("fact1");
    association.addFact(fact);

    return association;
  }

  private void compareAssociation(Association expected, Association actual) {
    assertNotNull(actual);
    assertEquals(expected.getId(), actual.getId());
    assertEquals(expected.getType(), actual.getType());

    assertNotNull(actual.getPerson1());
    assertEquals(expected.getPerson1().getResource(), actual.getPerson1().getResource());
    assertEquals(expected.getPerson1().getResourceId(), actual.getPerson1().getResourceId());

    assertNotNull(actual.getPerson2());
    assertEquals(expected.getPerson2().getResource(), actual.getPerson2().getResource());
    assertEquals(expected.getPerson2().getResourceId(), actual.getPerson2().getResourceId());

    assertNotNull(actual.getFacts());
    assertEquals(expected.getFacts().size(), actual.getFacts().size());
    assertEquals(expected.getFacts().get(0).getType(), actual.getFacts().get(0).getType());
    assertEquals(expected.getFacts().get(0).getValue(), actual.getFacts().get(0).getValue());
  }
}
