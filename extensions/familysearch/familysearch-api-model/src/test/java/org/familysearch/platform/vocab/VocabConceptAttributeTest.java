package org.familysearch.platform.vocab;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VocabConceptAttributeTest {

  @Test
  void id() {
    final String id = "testId";

    // Test using setter
    VocabConceptAttribute classUnderTest = new VocabConceptAttribute();
    classUnderTest.setId(id);

    assertEquals(classUnderTest.getId(), id);

    // Test using builder pattern method
    classUnderTest = new VocabConceptAttribute().id(id);

    assertEquals(classUnderTest.getId(), id);
  }

  @Test
  void name() {
    final String name = "testName";

    // Test using setter
    VocabConceptAttribute classUnderTest = new VocabConceptAttribute();
    classUnderTest.setName(name);

    assertEquals(classUnderTest.getName(), name);

    // Test using builder pattern method
    classUnderTest = new VocabConceptAttribute().name(name);

    assertEquals(classUnderTest.getName(), name);
  }

  @Test
  void value() {
    final String value = "testValue";

    // Test using setter
    VocabConceptAttribute classUnderTest = new VocabConceptAttribute();
    classUnderTest.setValue(value);

    assertEquals(classUnderTest.getValue(), value);

    // Test using builder pattern method
    classUnderTest = new VocabConceptAttribute().value(value);

    assertEquals(classUnderTest.getValue(), value);
  }

}
