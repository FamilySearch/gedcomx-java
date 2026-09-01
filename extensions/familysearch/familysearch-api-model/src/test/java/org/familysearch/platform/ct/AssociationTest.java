package org.familysearch.platform.ct;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.gedcomx.common.ResourceReference;
import org.gedcomx.common.URI;
import org.gedcomx.conclusion.Fact;
import org.gedcomx.records.Field;
import org.gedcomx.rt.json.GedcomJacksonModule;
import org.gedcomx.types.FactType;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.json.JsonMapper;

import static org.junit.jupiter.api.Assertions.*;

class AssociationTest {

  @Test
  void roundTrip() {
    assertRoundTrip(AssociationType.Godparent);
    assertRoundTrip(AssociationType.Neighbor);
  }

  private void assertRoundTrip(AssociationType type) {
    Association association = new Association();
    association.setPerson1(new ResourceReference(URI.create("urn:person1")));
    association.setPerson2(new ResourceReference(URI.create("urn:person2")));
    association.setKnownAssociationType(type);
    Fact fact = new Fact();
    fact.setKnownType(FactType.Residence);
    association.addFact(fact);
    Field field = new Field();
    association.addField(field);

    ByteArrayOutputStream outStream = new ByteArrayOutputStream(1024);

    try {
      JAXBContext context = JAXBContext.newInstance(Association.class);
      Marshaller marshaller = context.createMarshaller();
      marshaller.marshal(association, outStream);

      ByteArrayInputStream inStream = new ByteArrayInputStream(outStream.toByteArray());
      Unmarshaller unmarshaller = context.createUnmarshaller();
      Association roundTrippedAssociation = (Association) unmarshaller.unmarshal(inStream);

      assertEquals(association.getType(), roundTrippedAssociation.getType(),
          "XML round-trip failed for " + type);
      assertEquals(type, roundTrippedAssociation.getKnownAssociationType(),
          "XML round-trip failed for " + type);
      assertEquals(association.getPerson1().getResource(), roundTrippedAssociation.getPerson1().getResource(),
          "XML round-trip failed for person1 resource");
      assertEquals(association.getPerson2().getResource(), roundTrippedAssociation.getPerson2().getResource(),
          "XML round-trip failed for person2 resource");
      assertNotNull(roundTrippedAssociation.getFacts(), "XML round-trip failed for facts");
      assertEquals(1, roundTrippedAssociation.getFacts().size(), "XML round-trip failed for facts size");
      assertEquals(FactType.Residence, roundTrippedAssociation.getFacts().get(0).getKnownType(),
          "XML round-trip failed for fact type");
      assertNotNull(roundTrippedAssociation.getFields(), "XML round-trip failed for fields");
      assertEquals(1, roundTrippedAssociation.getFields().size(), "XML round-trip failed for fields size");
    }
    catch (JAXBException e) {
      fail("Failed to marshal XML for " + type + ": " + e);
    }

    outStream.reset();

    JsonMapper mapper = GedcomJacksonModule.createJsonMapper(Association.class);
    mapper.writeValue(outStream, association);

    ByteArrayInputStream inStream = new ByteArrayInputStream(outStream.toByteArray());
    Association roundTrippedAssociation = mapper.readValue(inStream, Association.class);

    assertEquals(association.getType(), roundTrippedAssociation.getType(),
        "JSON round-trip failed for " + type);
    assertEquals(type, roundTrippedAssociation.getKnownAssociationType(),
        "JSON round-trip failed for " + type);
    assertEquals(association.getPerson1().getResource(), roundTrippedAssociation.getPerson1().getResource(),
        "JSON round-trip failed for person1 resource");
    assertEquals(association.getPerson2().getResource(), roundTrippedAssociation.getPerson2().getResource(),
        "JSON round-trip failed for person2 resource");
    assertNotNull(roundTrippedAssociation.getFacts(), "JSON round-trip failed for facts");
    assertEquals(1, roundTrippedAssociation.getFacts().size(), "JSON round-trip failed for facts size");
    assertEquals(FactType.Residence, roundTrippedAssociation.getFacts().get(0).getKnownType(),
        "JSON round-trip failed for fact type");
    assertNotNull(roundTrippedAssociation.getFields(), "JSON round-trip failed for fields");
    assertEquals(1, roundTrippedAssociation.getFields().size(), "JSON round-trip failed for fields size");
  }

  @Test
  void copyConstructor() {
    Association original = new Association();
    original.setPerson1(new ResourceReference(URI.create("urn:person1")));
    original.setPerson2(new ResourceReference(URI.create("urn:person2")));
    original.setKnownAssociationType(AssociationType.Godparent);

    Association copy = new Association(original);

    assertNotNull(copy);
    assertEquals(original.getType(), copy.getType());
    assertEquals(AssociationType.Godparent, copy.getKnownAssociationType());
    assertEquals(original.getPerson1().getResource(), copy.getPerson1().getResource());
    assertEquals(original.getPerson2().getResource(), copy.getPerson2().getResource());
  }
}
