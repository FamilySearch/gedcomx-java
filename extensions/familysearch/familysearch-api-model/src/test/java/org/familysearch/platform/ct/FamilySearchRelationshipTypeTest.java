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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class FamilySearchRelationshipTypeTest {

  private Collection<FamilySearchRelationshipType> typesTested;
  private Collection<String> typeStrings;

  @Test
  void it() {
    typesTested = new LinkedList<FamilySearchRelationshipType>();
    typeStrings = new LinkedList<String>();

    // test the contract that the @XmlEnumValue is unique and does not change its value
    testType("http://gedcomx.org/AncestorDescendant", FamilySearchRelationshipType.AncestorToDescendant);
    testType("http://familysearch.org/v1/EmployerEmployee", FamilySearchRelationshipType.EmployerToEmployee);
    testType("http://gedcomx.org/EnslavedBy", FamilySearchRelationshipType.SlaveholderToEnslaved);
    testType("http://gedcomx.org/Godparent", FamilySearchRelationshipType.GodparentToGodchild);
    testType("http://familysearch.org/v1/HeadOfHouseholdOccupant", FamilySearchRelationshipType.HeadOfHouseholdToOccupant);
    testType("http://familysearch.org/v1/MasterApprentice", FamilySearchRelationshipType.MasterToApprentice);
    testType("http://familysearch.org/v1/Neighbor", FamilySearchRelationshipType.NeighborToNeighbor);
    testType("http://familysearch.org/v1/Relative", FamilySearchRelationshipType.RelativeToRelative);

    // make sure all are tested. OTHER is skipped rather than tolerated: it has no URI map entry, so
    // toQNameURI() on it throws.
    for (FamilySearchRelationshipType type : FamilySearchRelationshipType.values()) {
      if ((!typesTested.contains(type)) && (!FamilySearchRelationshipType.OTHER.equals(type))) {
        fail("Untested FamilySearchRelationshipType: " + type.name());
      }
    }
  }

  /**
   * The types redeclared from the core vocabulary must emit the very URI core emits — redirecting
   * the namespace mints no new URI. Fails from either side: a dropped @XmlQNameEnumValue here, or a
   * rename/re-namespace in core.
   */
  @Test
  void redirectedTypesMatchCoreVocabulary() {
    assertEquals(RelationshipType.AncestorDescendant.toQNameURI(), FamilySearchRelationshipType.AncestorToDescendant.toQNameURI());
    assertEquals(RelationshipType.EnslavedBy.toQNameURI(), FamilySearchRelationshipType.SlaveholderToEnslaved.toQNameURI());
    assertEquals(RelationshipType.Godparent.toQNameURI(), FamilySearchRelationshipType.GodparentToGodchild.toQNameURI());
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
   * Written as an equality so that whoever adds a constant that is not an association is forced to
   * edit this test rather than silently widening the set.
   */
  @Test
  void associationSetCoversEveryDeclaredType() {
    assertEquals(EnumSet.complementOf(EnumSet.of(FamilySearchRelationshipType.OTHER)),
                 EnumSet.copyOf(FamilySearchRelationshipType.ASSOCIATION_TYPES));

    for (FamilySearchRelationshipType type : FamilySearchRelationshipType.ASSOCIATION_TYPES) {
      assertTrue(type.isAssociationType(), type.name() + " should be an association type");
    }
    assertFalse(FamilySearchRelationshipType.OTHER.isAssociationType());
  }

  /**
   * Pins the dual-resolution trap: a redirected type resolves on both vocabularies, so filtering on
   * {@code getKnownType() == OTHER} before consulting the FamilySearch enum skips those types.
   */
  @Test
  void relationshipResolutionIsDualForRedirectedTypes() {
    Relationship fsOnly = new Relationship();
    fsOnly.setType(FamilySearchRelationshipType.MasterToApprentice.toQNameURI());
    assertEquals(RelationshipType.OTHER, fsOnly.getKnownType());
    assertEquals(FamilySearchRelationshipType.MasterToApprentice, FamilySearchRelationshipType.fromQNameURI(fsOnly.getType()));

    Relationship redirected = new Relationship();
    redirected.setType(FamilySearchRelationshipType.AncestorToDescendant.toQNameURI());
    assertEquals(RelationshipType.AncestorDescendant, redirected.getKnownType());
    assertEquals(FamilySearchRelationshipType.AncestorToDescendant, FamilySearchRelationshipType.fromQNameURI(redirected.getType()));
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
