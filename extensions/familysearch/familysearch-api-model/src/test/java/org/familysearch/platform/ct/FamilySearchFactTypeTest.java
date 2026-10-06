package org.familysearch.platform.ct;


import java.util.Collection;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class FamilySearchFactTypeTest {

  private Collection<FamilySearchFactType> typesTested;
  private Collection<String> typeStrings;

  @Test
  void it() {
    typesTested = new LinkedList<FamilySearchFactType>();
    typeStrings = new LinkedList<String>();

    // test the contract that the @XmlEnumValue is unique and does not change its value
    testType("http://familysearch.org/v1/Affiliation", FamilySearchFactType.Affiliation);
    testType("http://familysearch.org/v1/BirthOrder", FamilySearchFactType.BirthOrder);
    testType("http://familysearch.org/v1/CoupleNeverHadChildren", FamilySearchFactType.CoupleNeverHadChildren);
    testType("http://familysearch.org/v1/DiedBeforeEight", FamilySearchFactType.DiedBeforeEight);
    testType("http://familysearch.org/v1/LifeSketch", FamilySearchFactType.LifeSketch);
    testType("http://familysearch.org/v1/LivedTogether", FamilySearchFactType.LivedTogether);
    testType("http://familysearch.org/v1/NoChildren", FamilySearchFactType.NoChildren);
    testType("http://familysearch.org/v1/NoCoupleRelationships", FamilySearchFactType.NoCoupleRelationships);
    testType("http://familysearch.org/v1/TitleOfNobility", FamilySearchFactType.TitleOfNobility);
    testType("http://familysearch.org/v1/TribeName", FamilySearchFactType.TribeName);
    testType("http://familysearch.org/v1/Apprenticeship", FamilySearchFactType.Apprenticeship);
    testType("http://familysearch.org/v1/Emancipation", FamilySearchFactType.Emancipation);
    testType("http://familysearch.org/v1/Employment", FamilySearchFactType.Employment);
    testType("http://familysearch.org/v1/Enslavement", FamilySearchFactType.Enslavement);
    testType("http://familysearch.org/v1/Generation", FamilySearchFactType.Generation);
    testType("http://familysearch.org/v1/Godparenthood", FamilySearchFactType.Godparenthood);
    testType("http://familysearch.org/v1/Household", FamilySearchFactType.Household);
    testType("http://familysearch.org/v1/Neighborhood", FamilySearchFactType.Neighborhood);
    testType("http://familysearch.org/v1/Relation", FamilySearchFactType.Relation);

    // make sure all are tested
    for (FamilySearchFactType type : FamilySearchFactType.values()) {
      if ((!typesTested.contains(type)) && (!FamilySearchFactType.OTHER.equals(type))) {
        fail("Untested FamilySearchFactType: " + type.name());
      }
    }
  }

  private void testType(String enumStr, FamilySearchFactType srcRefTagType) {
    assertEquals(FamilySearchFactType.fromQNameURI(srcRefTagType.toQNameURI()).toQNameURI().toString(), enumStr);
    typesTested.add( srcRefTagType );

    // make sure enum string is unique
    if ( typeStrings.contains(enumStr) ) {
      fail("Duplicate FamilySearchFactType value: " + enumStr);
    }
    typeStrings.add( enumStr );
  }
}
