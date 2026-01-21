package org.familysearch.platform.ct;


import java.util.Collection;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class SourceReferenceTagTypeTest {

  private Collection<SourceReferenceTagType> typesTested;
  private Collection<String> typeStrings;

  @Test
  void it() {
    typesTested = new LinkedList<SourceReferenceTagType>();
    typeStrings = new LinkedList<String>();

    // test the contract that the @XmlEnumValue is unique and does not change its value
    testType("http://gedcomx.org/Name", SourceReferenceTagType.Name);
    testType("http://gedcomx.org/Gender", SourceReferenceTagType.Gender);

    // make sure all are tested
    for (SourceReferenceTagType type : SourceReferenceTagType.values()) {
      if ((!typesTested.contains(type)) && (!SourceReferenceTagType.OTHER.equals(type))) {
        fail("Untested SourceReferenceTagType: " + type.name());
      }
    }
  }

  private void testType(String enumStr, SourceReferenceTagType srcRefTagType) {
    assertEquals(SourceReferenceTagType.fromQNameURI(srcRefTagType.toQNameURI()).toQNameURI().toString(), enumStr);
    typesTested.add( srcRefTagType );

    // make sure enum string is unique
    if ( typeStrings.contains(enumStr) ) {
      fail("Duplicate SourceReferenceTagType value: " + enumStr);
    }
    typeStrings.add( enumStr );
  }
}
