package org.familysearch.platform;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.gedcomx.Gedcomx;
import org.gedcomx.common.ResourceReference;
import org.gedcomx.common.URI;
import org.gedcomx.conclusion.DisplayProperties;
import org.gedcomx.conclusion.Fact;
import org.gedcomx.conclusion.FamilyView;
import org.gedcomx.conclusion.Person;
import org.gedcomx.conclusion.Relationship;
import org.gedcomx.rt.json.GedcomJacksonModule;
import org.gedcomx.rt.json.jackson3.GedcomJackson3Module;
import org.gedcomx.types.FactType;
import org.gedcomx.types.RelationshipType;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.json.JsonMapper;

import org.familysearch.platform.ct.ChildAndParentsRelationship;
import org.familysearch.platform.records.AlternateDate;
import org.familysearch.platform.records.AlternatePlaceReference;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class for testing the FamilySearchPlatform class
 * User: Randy Wilson
 * Date: 20 May 2015
 */
class FamilySearchPlatformTest {

  @Test
  void altDatesPlaces() throws Exception {
    Fact fact = new Fact(FactType.Adoption, "value");
    AlternateDate altDate = new AlternateDate();
    altDate.setOriginal("orig");
    fact.addExtensionElement(altDate);
    AlternatePlaceReference altPlace = new AlternatePlaceReference();
    altPlace.setOriginal("place");
    fact.addExtensionElement(altPlace);
    Gedcomx gx = new Gedcomx().person(new Person().fact(fact));

    ObjectMapper objectMapper = GedcomJacksonModule.createObjectMapper(AlternateDate.class, AlternatePlaceReference.class);
    String value = objectMapper.writeValueAsString(gx);
    //System.out.println(value);
    gx = objectMapper.readValue(value, Gedcomx.class);
    assertEquals("orig", gx.getPerson().getFirstFactOfType(FactType.Adoption).findExtensionOfType(AlternateDate.class).getOriginal());

    //JAXBContext.newInstance(FamilySearchPlatform.class).createMarshaller().marshal(gx, System.out);
  }

  @Test
  void altDatesPlaces_jackson3() {
    Fact fact = new Fact(FactType.Adoption, "value");
    AlternateDate altDate = new AlternateDate();
    altDate.setOriginal("orig");
    fact.addExtensionElement(altDate);
    AlternatePlaceReference altPlace = new AlternatePlaceReference();
    altPlace.setOriginal("place");
    fact.addExtensionElement(altPlace);
    Gedcomx gx = new Gedcomx().person(new Person().fact(fact));

    JsonMapper objectMapper = GedcomJackson3Module.createObjectMapper(AlternateDate.class, AlternatePlaceReference.class);
    String value = objectMapper.writeValueAsString(gx);
    //System.out.println(value);
    gx = objectMapper.readValue(value, Gedcomx.class);
    assertEquals("orig", gx.getPerson().getFirstFactOfType(FactType.Adoption).findExtensionOfType(AlternateDate.class).getOriginal());

    //JAXBContext.newInstance(FamilySearchPlatform.class).createMarshaller().marshal(gx, System.out);
  }

  @Test
  void family() {
    FamilySearchPlatform g = makeDoc();
    FamilyView family = g.getPerson().getDisplayExtension().getFamiliesAsChild().get(0);

    // parent1-parent2 relationship
    Relationship couple = g.findCoupleRelationship(family);
    assertEquals(RelationshipType.Couple, couple.getKnownType());
    assertEquals("#parent1", couple.getPerson1().getResource().toString());
    assertEquals("#parent2", couple.getPerson2().getResource().toString());

    // parent1-kid1 relationship
    Relationship pcRel = g.findParentChildRelationship(family.getParent1(), family.getChildren().get(0));
    assertEquals(RelationshipType.ParentChild, pcRel.getKnownType());
    assertEquals("#parent1", pcRel.getPerson1().getResource().toString());
    assertEquals("#kid1", pcRel.getPerson2().getResource().toString());
    assertEquals(FactType.AdoptiveParent, pcRel.getFacts().get(0).getKnownType());
    assertNull(pcRel.getFacts().get(0).getValue());

    // parent2-kid1 relationship
    pcRel = g.findParentChildRelationship(family.getParent2(), family.getChildren().get(0));
    assertEquals(RelationshipType.ParentChild, pcRel.getKnownType());
    assertEquals("#parent2", pcRel.getPerson1().getResource().toString());
    assertEquals("#kid1", pcRel.getPerson2().getResource().toString());
    assertEquals(FactType.BiologicalParent, pcRel.getFacts().get(0).getKnownType());

    // parent2-kid2 relationship
    pcRel = g.findParentChildRelationship(family.getParent2(), family.getChildren().get(1));
    assertEquals(RelationshipType.ParentChild, pcRel.getKnownType());
    assertEquals("#parent2", pcRel.getPerson1().getResource().toString());
    assertEquals("#kid2", pcRel.getPerson2().getResource().toString());
    assertNull(pcRel.getFacts());

    // Now also look up ChildAndParentsRelationship
    ChildAndParentsRelationship rel = g.findChildAndParentsRelationship(family.getChildren().get(0), family.getParent1(), family.getParent2());
    assertEquals("#parent1", rel.getParent1().getResource().toString());
    assertEquals("#parent2", rel.getParent2().getResource().toString());
    assertEquals("#kid1", rel.getChild().getResource().toString());
    assertEquals(FactType.AdoptiveParent, rel.getParent1Facts().get(0).getKnownType());
    assertEquals(FactType.BiologicalParent, rel.getParent2Facts().get(0).getKnownType());

    rel = g.findChildAndParentsRelationship(family.getChildren().get(1), family.getParent1(), family.getParent2());
    assertEquals("#parent1", rel.getParent1().getResource().toString());
    assertEquals("#parent2", rel.getParent2().getResource().toString());
    assertEquals("#kid2", rel.getChild().getResource().toString());
    assertNull(rel.getParent1Facts());
    assertNull(rel.getParent2Facts());

    // Test single-parent family
    FamilyView fam2 = g.getPerson().getDisplayExtension().getFamiliesAsChild().get(1);
    rel = g.findChildAndParentsRelationship(fam2.getChildren().get(0), fam2.getParent1(), fam2.getParent2());
    assertEquals("#parent1", rel.getParent1().getResource().toString());
    assertNull(rel.getParent2());
    assertEquals("#kid3", rel.getChild().getResource().toString());

    assertNull(g.findCoupleRelationship(fam2));
  }

  private FamilySearchPlatform makeDoc() {
    FamilySearchPlatform g = new FamilySearchPlatform();
    g.addPerson(makePerson());

    g.addRelationship(makeRel("parent1", "parent2", RelationshipType.Couple));
    addChild(g, "parent1", "parent2", "kid1", FactType.AdoptiveParent, FactType.BiologicalParent);
    addChild(g, "parent1", "parent2", "kid2", null, null);

    // Add single-parent family
    g.getPerson().getDisplayExtension().addFamilyAsChild(makeFam("parent1", null, "kid3"));
    addChild(g, "parent1", null, "kid3", null, null);

    return g;
  }

  private Person makePerson() {
    Person person = new Person();
    person.setDisplayExtension(new DisplayProperties());
    person.getDisplayExtension().addFamilyAsChild(makeFam("parent1", "parent2", "kid1", "kid2"));
    return person;
  }

  private static void addChild(FamilySearchPlatform doc, String parent1Id, String parent2Id, String childId,
                               FactType parent1FactType, FactType parent2FactType) {
    ChildAndParentsRelationship rel = new ChildAndParentsRelationship();
    if (parent1Id != null) {
      doc.addRelationship(kidRel(parent1Id, childId, parent1FactType));
      rel.setParent1(makeRef(parent1Id));
    }
    if (parent2Id != null) {
      doc.addRelationship(kidRel(parent2Id, childId, parent2FactType));
      rel.setParent2(makeRef(parent2Id));
    }
    rel.setChild(makeRef(childId));
    if (parent1FactType != null) {
      rel.addParent1Fact(new Fact(parent1FactType, null));
    }
    if (parent2FactType != null) {
      rel.addParent2Fact(new Fact(parent2FactType, null));
    }
    doc.addChildAndParentsRelationship(rel);
  }

  private static FamilyView makeFam(String parent1Id, String parent2Id, String... kidIds) {
    FamilyView family = new FamilyView();
    family.setParent1(makeRef(parent1Id));
    family.setParent2(makeRef(parent2Id));
    if (kidIds != null) {
      for (String kidId : kidIds) {
        family.addChild(makeRef(kidId));
      }
    }
    return family;
  }

  protected static Relationship kidRel(String parentId, String kidId, FactType lineageType) {
    Relationship relationship = makeRel(parentId, kidId, RelationshipType.ParentChild);
    if (lineageType != null) {
      relationship.addFact(new Fact(lineageType, null));
    }
    return relationship;
  }

  protected static Relationship makeRel(String id1, String id2, RelationshipType relationshipType) {
    Relationship relationship = new Relationship();
    relationship.setKnownType(relationshipType);
    relationship.setPerson1(makeRef(id1));
    relationship.setPerson2(makeRef(id2));
    return relationship;
  }

  protected static ResourceReference makeRef(String id) {
    return id == null ? null : new ResourceReference(new URI("#" + id));
  }

}
