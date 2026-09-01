package org.familysearch.platform.ct;

import java.util.Collection;
import java.util.LinkedList;

import org.gedcomx.types.RelationshipType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class AssociationTypeTest {

  private Collection<AssociationType> typesTested;
  private Collection<String> typeStrings;

  @Test
  void testUniqueURIs() {
    typesTested = new LinkedList<AssociationType>();
    typeStrings = new LinkedList<String>();

    // test the contract that the @XmlEnumValue is unique and does not change its value
    testType("http://gedcomx.org/AncestorDescendant", AssociationType.AncestorDescendant);
    testType("http://familysearch.org/v1/EmployerEmployee", AssociationType.EmployerEmployee);
    testType("http://gedcomx.org/EnslavedBy", AssociationType.EnslavedBy);
    testType("http://gedcomx.org/Godparent", AssociationType.Godparent);
    testType("http://familysearch.org/v1/HeadOfHouseholdOccupant", AssociationType.HeadOfHouseholdOccupant);
    testType("http://familysearch.org/v1/MasterApprentice", AssociationType.MasterApprentice);
    testType("http://familysearch.org/v1/Neighbor", AssociationType.Neighbor);
    testType("http://familysearch.org/v1/Relative", AssociationType.Relative);

    // make sure all are tested
    for (AssociationType type : AssociationType.values()) {
      if ((!typesTested.contains(type)) && (!AssociationType.OTHER.equals(type))) {
        fail("Untested AssociationType: " + type.name());
      }
    }
  }

  @Test
  void testVocabularyAlignment() {
    // gedcomx-namespace constants resolve to their real RelationshipType counterpart
    assertEquals(RelationshipType.AncestorDescendant,
        RelationshipType.fromQNameURI(AssociationType.AncestorDescendant.toQNameURI()));
    assertEquals(RelationshipType.EnslavedBy,
        RelationshipType.fromQNameURI(AssociationType.EnslavedBy.toQNameURI()));
    assertEquals(RelationshipType.Godparent,
        RelationshipType.fromQNameURI(AssociationType.Godparent.toQNameURI()));

    // FamilySearch-namespace constants resolve to RelationshipType.OTHER
    assertEquals(RelationshipType.OTHER,
        RelationshipType.fromQNameURI(AssociationType.EmployerEmployee.toQNameURI()));
    assertEquals(RelationshipType.OTHER,
        RelationshipType.fromQNameURI(AssociationType.HeadOfHouseholdOccupant.toQNameURI()));
    assertEquals(RelationshipType.OTHER,
        RelationshipType.fromQNameURI(AssociationType.MasterApprentice.toQNameURI()));
    assertEquals(RelationshipType.OTHER,
        RelationshipType.fromQNameURI(AssociationType.Neighbor.toQNameURI()));
    assertEquals(RelationshipType.OTHER,
        RelationshipType.fromQNameURI(AssociationType.Relative.toQNameURI()));
  }

  private void testType(String enumStr, AssociationType type) {
    assertEquals(enumStr, AssociationType.fromQNameURI(type.toQNameURI()).toQNameURI().toString());
    typesTested.add(type);

    // make sure enum string is unique
    if (typeStrings.contains(enumStr)) {
      fail("Duplicate AssociationType value: " + enumStr);
    }
    typeStrings.add(enumStr);
  }
}
