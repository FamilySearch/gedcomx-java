package org.familysearch.platform.ct;

import org.junit.Test;


import java.util.Collection;
import java.util.LinkedList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FamilySearchFactTypeTest {

  private Collection<FamilySearchFactType> typesTested;
  private Collection<String> typeStrings;

  @Test
  public void testIt() {
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

    // association fact types
    //testType("http://familysearch.org/v1/Apprenticeship", FamilySearchFactType.Apprenticeship); // exists as a core Gedcomx FactType
    testType("http://familysearch.org/v1/Association", FamilySearchFactType.Association);
    testType("http://familysearch.org/v1/Emancipation", FamilySearchFactType.Emancipation);
    testType("http://familysearch.org/v1/Employment", FamilySearchFactType.Employment);
    testType("http://familysearch.org/v1/Enslavement", FamilySearchFactType.Enslavement);
    testType("http://familysearch.org/v1/Friendship", FamilySearchFactType.Friendship);
    testType("http://familysearch.org/v1/Generation", FamilySearchFactType.Generation);
    testType("http://familysearch.org/v1/Godparenthood", FamilySearchFactType.Godparenthood);
    testType("http://familysearch.org/v1/Household", FamilySearchFactType.Household);
    testType("http://familysearch.org/v1/Neighborhood", FamilySearchFactType.Neighborhood);
    testType("http://familysearch.org/v1/Relative", FamilySearchFactType.Relative);

    // make sure all are tested
    for (FamilySearchFactType type : FamilySearchFactType.values()) {
      if ((!typesTested.contains(type)) && (!FamilySearchFactType.OTHER.equals(type))) {
        assertTrue("Untested FamilySearchFactType: " + type.name(), false);
      }
    }
  }

  private void testType(String enumStr, FamilySearchFactType srcRefTagType) {
    assertEquals(FamilySearchFactType.fromQNameURI(srcRefTagType.toQNameURI()).toQNameURI().toString(), enumStr);
    typesTested.add( srcRefTagType );

    // make sure enum string is unique
    if ( typeStrings.contains(enumStr) ) {
      assertTrue("Duplicate FamilySearchFactType value: " + enumStr, false);
    }
    typeStrings.add( enumStr );
  }
}
