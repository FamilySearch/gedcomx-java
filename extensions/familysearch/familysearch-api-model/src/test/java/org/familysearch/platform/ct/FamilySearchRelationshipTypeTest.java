package org.familysearch.platform.ct;


import java.util.Collection;
import java.util.EnumSet;
import java.util.LinkedList;

import org.gedcomx.common.URI;
import org.gedcomx.conclusion.Relationship;
import org.gedcomx.types.RelationshipType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class FamilySearchRelationshipTypeTest {

  private Collection<FamilySearchRelationshipType> typesTested;
  private Collection<String> typeStrings;

  @Test
  void it() {
    typesTested = new LinkedList<FamilySearchRelationshipType>();
    typeStrings = new LinkedList<String>();

    // test the contract that the @XmlEnumValue is unique and does not change its value
    testType("http://familysearch.org/v1/AncestorToDescendant", FamilySearchRelationshipType.AncestorToDescendant);
    testType("http://familysearch.org/v1/EmployerToEmployee", FamilySearchRelationshipType.EmployerToEmployee);
    testType("http://familysearch.org/v1/SlaveholderToEnslavedPerson", FamilySearchRelationshipType.SlaveholderToEnslavedPerson);
    testType("http://familysearch.org/v1/GodparentToGodchild", FamilySearchRelationshipType.GodparentToGodchild);
    testType("http://familysearch.org/v1/HeadOfHouseholdToOccupant", FamilySearchRelationshipType.HeadOfHouseholdToOccupant);
    testType("http://familysearch.org/v1/MasterToApprentice", FamilySearchRelationshipType.MasterToApprentice);
    testType("http://familysearch.org/v1/NeighborToNeighbor", FamilySearchRelationshipType.NeighborToNeighbor);
    testType("http://familysearch.org/v1/RelativeToRelative", FamilySearchRelationshipType.RelativeToRelative);

    // make sure all are tested. OTHER is skipped rather than tolerated: it has no URI map entry, so
    // toQNameURI() on it throws.
    for (FamilySearchRelationshipType type : FamilySearchRelationshipType.values()) {
      if ((!typesTested.contains(type)) && (!FamilySearchRelationshipType.OTHER.equals(type))) {
        fail("Untested FamilySearchRelationshipType: " + type.name());
      }
    }
  }

  /**
   * The three types that supersede deprecated core constants mint new FamilySearch URIs rather than
   * aliasing the core ones, so migrating off a deprecated constant is a wire-format change. Pinned as
   * inequality plus the literal replacement URI: if someone later decides these should alias core
   * (via {@code @XmlQNameEnumValue}), this test forces the decision into the open instead of letting
   * the URI silently change under existing data.
   */
  @Test
  @SuppressWarnings("deprecation")
  void supersedingTypesMintNewUris() {
    assertEquals("http://familysearch.org/v1/AncestorToDescendant", FamilySearchRelationshipType.AncestorToDescendant.toQNameURI().toString());
    assertEquals("http://familysearch.org/v1/SlaveholderToEnslavedPerson", FamilySearchRelationshipType.SlaveholderToEnslavedPerson.toQNameURI().toString());
    assertEquals("http://familysearch.org/v1/GodparentToGodchild", FamilySearchRelationshipType.GodparentToGodchild.toQNameURI().toString());
  }

  /**
   * Unknown input surfaces as OTHER rather than an exception. Couple and ParentChild are core
   * relationship types that are not associations, so they must not have been pulled in.
   */
  @Test
  void unrecognizedUrisResolveToOther() {
    assertEquals(FamilySearchRelationshipType.OTHER, FamilySearchRelationshipType.fromQNameURI(new URI("http://familysearch.org/v1/NotARelationshipType")));
    assertEquals(FamilySearchRelationshipType.OTHER, FamilySearchRelationshipType.fromQNameURI(new URI("urn:something-else")));
    assertEquals(FamilySearchRelationshipType.OTHER, FamilySearchRelationshipType.fromQNameURI(RelationshipType.Couple.toQNameURI()));
    assertEquals(FamilySearchRelationshipType.OTHER, FamilySearchRelationshipType.fromQNameURI(RelationshipType.ParentChild.toQNameURI()));
  }

  /**
   * Every association URI is FamilySearch-namespaced, so core resolves none of them — including the
   * three that supersede deprecated core constants. That makes this enum the only vocabulary that can
   * tell one association from another; a {@code switch} on {@code getKnownType()} cannot.
   */
  @Test
  void coreVocabularyResolvesNoAssociationType() {
    for (FamilySearchRelationshipType type : EnumSet.complementOf(EnumSet.of(FamilySearchRelationshipType.OTHER))) {
      Relationship relationship = new Relationship();
      relationship.setType(type.toQNameURI());

      assertEquals(RelationshipType.OTHER, relationship.getKnownType(), type.name() + " should not resolve on the core vocabulary");
      assertEquals(type, FamilySearchRelationshipType.fromQNameURI(relationship.getType()), type.name() + " should round-trip through this enum");
    }
  }

  private void testType(String enumStr, FamilySearchRelationshipType relationshipType) {
    assertEquals(FamilySearchRelationshipType.fromQNameURI(relationshipType.toQNameURI()).toQNameURI().toString(), enumStr);
    typesTested.add( relationshipType );

    // make sure enum string is unique
    if ( typeStrings.contains(enumStr) ) {
      fail("Duplicate FamilySearchRelationshipType value: " + enumStr);
    }
    typeStrings.add( enumStr );
  }
}
