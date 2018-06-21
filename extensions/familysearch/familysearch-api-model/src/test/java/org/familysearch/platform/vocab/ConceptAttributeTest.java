package org.familysearch.platform.vocab;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ConceptAttributeTest {

  @Test
  public void testId() {
    final String id = "testId";

    // Test using setter
    ConceptAttribute classUnderTest = new ConceptAttribute();
    classUnderTest.setId(id);

    assertEquals(classUnderTest.getId(), id);

    // Test using builder pattern method
    classUnderTest = new ConceptAttribute().id(id);

    assertEquals(classUnderTest.getId(), id);
  }

  @Test
  public void testName() {
    final String name = "testName";

    // Test using setter
    ConceptAttribute classUnderTest = new ConceptAttribute();
    classUnderTest.setName(name);

    assertEquals(classUnderTest.getName(), name);

    // Test using builder pattern method
    classUnderTest = new ConceptAttribute().name(name);

    assertEquals(classUnderTest.getName(), name);
  }

  @Test
  public void testValue() {
    final String value = "testValue";

    // Test using setter
    ConceptAttribute classUnderTest = new ConceptAttribute();
    classUnderTest.setValue(value);

    assertEquals(classUnderTest.getValue(), value);

    // Test using builder pattern method
    classUnderTest = new ConceptAttribute().value(value);

    assertEquals(classUnderTest.getValue(), value);
  }

}
