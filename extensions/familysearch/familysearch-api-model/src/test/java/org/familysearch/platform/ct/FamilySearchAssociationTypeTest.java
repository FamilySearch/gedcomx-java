package org.familysearch.platform.ct;


import java.util.Collection;
import java.util.EnumSet;
import java.util.LinkedList;

import org.gedcomx.common.URI;
import org.gedcomx.conclusion.Relationship;
import org.gedcomx.types.RelationshipType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class FamilySearchAssociationTypeTest {

  private Collection<FamilySearchAssociationType> typesTested;
  private Collection<String> typeStrings;

  @Test
  void it() {
    typesTested = new LinkedList<FamilySearchAssociationType>();
    typeStrings = new LinkedList<String>();

    // test the contract that the @XmlEnumValue is unique and does not change its value
    testType("http://familysearch.org/v1/AncestorToDescendant", FamilySearchAssociationType.AncestorToDescendant);
    testType("http://familysearch.org/v1/EmployerToEmployee", FamilySearchAssociationType.EmployerToEmployee);
    testType("http://familysearch.org/v1/SlaveholderToEnslaved", FamilySearchAssociationType.SlaveholderToEnslaved);
    testType("http://familysearch.org/v1/GodparentToGodchild", FamilySearchAssociationType.GodparentToGodchild);
    testType("http://familysearch.org/v1/HeadOfHouseholdToOccupant", FamilySearchAssociationType.HeadOfHouseholdToOccupant);
    testType("http://familysearch.org/v1/MasterToApprentice", FamilySearchAssociationType.MasterToApprentice);
    testType("http://familysearch.org/v1/NeighborToNeighbor", FamilySearchAssociationType.NeighborToNeighbor);
    testType("http://familysearch.org/v1/RelativeToRelative", FamilySearchAssociationType.RelativeToRelative);

    // make sure all are tested. OTHER is skipped rather than tolerated: it has no URI map entry, so
    // toQNameURI() on it throws.
    for (FamilySearchAssociationType type : FamilySearchAssociationType.values()) {
      if ((!typesTested.contains(type)) && (!FamilySearchAssociationType.OTHER.equals(type))) {
        fail("Untested FamilySearchAssociationType: " + type.name());
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
    assertNotEquals(RelationshipType.AncestorDescendant.toQNameURI(), FamilySearchAssociationType.AncestorToDescendant.toQNameURI());
    assertNotEquals(RelationshipType.EnslavedBy.toQNameURI(), FamilySearchAssociationType.SlaveholderToEnslaved.toQNameURI());
    assertNotEquals(RelationshipType.Godparent.toQNameURI(), FamilySearchAssociationType.GodparentToGodchild.toQNameURI());

    assertEquals("http://familysearch.org/v1/AncestorToDescendant", FamilySearchAssociationType.AncestorToDescendant.toQNameURI().toString());
    assertEquals("http://familysearch.org/v1/SlaveholderToEnslaved", FamilySearchAssociationType.SlaveholderToEnslaved.toQNameURI().toString());
    assertEquals("http://familysearch.org/v1/GodparentToGodchild", FamilySearchAssociationType.GodparentToGodchild.toQNameURI().toString());
  }

  /**
   * Unknown input surfaces as OTHER rather than an exception. Couple and ParentChild are core
   * relationship types that are not associations, so they must not have been pulled in.
   */
  @Test
  void unrecognizedUrisResolveToOther() {
    assertEquals(FamilySearchAssociationType.OTHER, FamilySearchAssociationType.fromQNameURI(new URI("http://familysearch.org/v1/NotARelationshipType")));
    assertEquals(FamilySearchAssociationType.OTHER, FamilySearchAssociationType.fromQNameURI(new URI("urn:something-else")));
    assertEquals(FamilySearchAssociationType.OTHER, FamilySearchAssociationType.fromQNameURI(RelationshipType.Couple.toQNameURI()));
    assertEquals(FamilySearchAssociationType.OTHER, FamilySearchAssociationType.fromQNameURI(RelationshipType.ParentChild.toQNameURI()));
  }

  /**
   * Written as an equality so that whoever adds a constant that is not an association is forced to
   * edit this test rather than silently widening the set.
   */
  @Test
  void associationSetCoversEveryDeclaredType() {
    assertEquals(EnumSet.complementOf(EnumSet.of(FamilySearchAssociationType.OTHER)),
                 EnumSet.copyOf(FamilySearchAssociationType.ASSOCIATION_TYPES));

    for (FamilySearchAssociationType type : FamilySearchAssociationType.ASSOCIATION_TYPES) {
      assertTrue(type.isAssociationType(), type.name() + " should be an association type");
    }
    assertFalse(FamilySearchAssociationType.OTHER.isAssociationType());
  }

  /**
   * Every association URI is FamilySearch-namespaced, so core resolves none of them — including the
   * three that supersede deprecated core constants. That makes this enum the only vocabulary that can
   * tell one association from another; a {@code switch} on {@code getKnownType()} cannot.
   */
  @Test
  void coreVocabularyResolvesNoAssociationType() {
    for (FamilySearchAssociationType type : FamilySearchAssociationType.ASSOCIATION_TYPES) {
      Relationship relationship = new Relationship();
      relationship.setType(type.toQNameURI());

      assertEquals(RelationshipType.OTHER, relationship.getKnownType(), type.name() + " should not resolve on the core vocabulary");
      assertEquals(type, FamilySearchAssociationType.fromQNameURI(relationship.getType()), type.name() + " should round-trip through this enum");
    }
  }

  private void testType(String enumStr, FamilySearchAssociationType relationshipType) {
    assertEquals(FamilySearchAssociationType.fromQNameURI(relationshipType.toQNameURI()).toQNameURI().toString(), enumStr);
    typesTested.add( relationshipType );

    // make sure enum string is unique
    if ( typeStrings.contains(enumStr) ) {
      fail("Duplicate FamilySearchAssociationType value: " + enumStr);
    }
    typeStrings.add( enumStr );
  }
}
