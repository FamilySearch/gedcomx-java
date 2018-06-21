package org.familysearch.platform.vocab;

import java.util.Collections;
import java.util.List;

import org.gedcomx.common.ExtensibleData;
import org.gedcomx.common.TextValue;
import org.gedcomx.common.URI;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TermTest {
  private static final String URI_BASE = "http://some.valid.uri/";

  @Test
  public void testId() {
    final String id = "testId";

    // Test using setter
    ExtensibleData classUnderTest = new Term();
    classUnderTest.setId(id);

    assertEquals(classUnderTest.getId(), id);

    // Test using builder pattern method
    classUnderTest = new Term().id(id);

    assertEquals(classUnderTest.getId(), id);
  }

  @Test
  public void testType() {
    final URI type = new URI(URI_BASE + "type/typeTest");

    // Test using setter
    Term classUnderTest = new Term();
    classUnderTest.setType(type);

    assertEquals(classUnderTest.getType(), type);

    // Test using builder pattern method
    classUnderTest = new Term().type(type);

    assertEquals(classUnderTest.getType(), type);
  }

  @Test
  public void testConceptId() {
    final URI concept = new URI(URI_BASE + "concept/testConceptId");

    // Test using setter
    Term classUnderTest = new Term();
    classUnderTest.setConcept(concept);

    assertEquals(classUnderTest.getConcept(), concept);

    // Test using builder pattern method
    classUnderTest = new Term().concept(concept);

    assertEquals(classUnderTest.getConcept(), concept);
  }

  @Test
  public void testSublistId() {
    final URI sublistId = new URI("http://some.valid.uri/list/testSublistId");

    // Test using setter
    Term classUnderTest = new Term();
    classUnderTest.setSublist(sublistId);

    assertEquals(classUnderTest.getSublist(), sublistId);

    // Test using builder pattern method
    classUnderTest = new Term().sublist(sublistId);

    assertEquals(classUnderTest.getSublist(), sublistId);
  }

  @Test
  public void testSublistPosition() {
    final Integer sublistPosition = 349873;

    // Test using setter
    Term classUnderTest = new Term();
    classUnderTest.setSublistPosition(sublistPosition);

    assertEquals(classUnderTest.getSublistPosition(), sublistPosition);

    // Test using builder pattern method
    classUnderTest = new Term().sublistPosition(sublistPosition);

    assertEquals(classUnderTest.getSublistPosition(), sublistPosition);
  }

  @Test
  public void testValues() {
    final TextValue testTextValue = new TextValue("testValue");
    testTextValue.setLang("testLang");
    List<TextValue> values = Collections.singletonList(testTextValue);

    // Test using setter
    Term classUnderTest = new Term();
    classUnderTest.setValues(values);

    assertEquals(classUnderTest.getValues(), values);

    // Test using builder pattern method
    classUnderTest = new Term().values(values);

    assertEquals(classUnderTest.getValues(), values);
  }

}
