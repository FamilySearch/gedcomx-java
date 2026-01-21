package org.familysearch.platform.vocab;

import java.util.Collections;
import java.util.List;

import org.gedcomx.common.ExtensibleData;
import org.gedcomx.common.TextValue;
import org.gedcomx.common.URI;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TermTest {
  private static final String URI_BASE = "http://some.valid.uri/";

  @Test
  void id() {
    final String id = "testId";

    // Test using setter
    ExtensibleData classUnderTest = new VocabTerm();
    classUnderTest.setId(id);

    assertEquals(classUnderTest.getId(), id);

    // Test using builder pattern method
    classUnderTest = new VocabTerm().id(id);

    assertEquals(classUnderTest.getId(), id);
  }

  @Test
  void type() {
    final URI type = new URI(URI_BASE + "type/vocabTypeTest");

    // Test using setter
    VocabTerm classUnderTest = new VocabTerm();
    classUnderTest.setTypeUri(type);

    assertEquals(classUnderTest.getTypeUri(), type);

    // Test using builder pattern method
    classUnderTest = new VocabTerm().type(type);

    assertEquals(classUnderTest.getTypeUri(), type);
  }

  @Test
  void conceptId() {
    final URI vocabConceptUri = new URI(URI_BASE + "vocabConcept/testVocabConceptId");

    // Test using setter
    VocabTerm classUnderTest = new VocabTerm();
    classUnderTest.setVocabConceptUri(vocabConceptUri);

    assertEquals(classUnderTest.getVocabConcept(), vocabConceptUri);

    // Test using builder pattern method
    classUnderTest = new VocabTerm().vocabConcept(vocabConceptUri);

    assertEquals(classUnderTest.getVocabConcept(), vocabConceptUri);
  }

  @Test
  void sublistUri() {
    final URI sublistUri = new URI("http://some.valid.uri/list/testSublistUri");

    // Test using setter
    VocabTerm classUnderTest = new VocabTerm();
    classUnderTest.setSublistUri(sublistUri);

    assertEquals(classUnderTest.getSublistUri(), sublistUri);

    // Test using builder pattern method
    classUnderTest = new VocabTerm().sublist(sublistUri);

    assertEquals(classUnderTest.getSublistUri(), sublistUri);
  }

  @Test
  void sublistPosition() {
    final Integer sublistPosition = 349873;

    // Test using setter
    VocabTerm classUnderTest = new VocabTerm();
    classUnderTest.setSublistPosition(sublistPosition);

    assertEquals(classUnderTest.getSublistPosition(), sublistPosition);

    // Test using builder pattern method
    classUnderTest = new VocabTerm().sublistPosition(sublistPosition);

    assertEquals(classUnderTest.getSublistPosition(), sublistPosition);
  }

  @Test
  void values() {
    final TextValue testTextValue = new TextValue("testValue");
    testTextValue.setLang("testLang");
    List<TextValue> values = Collections.singletonList(testTextValue);

    // Test using setter
    VocabTerm classUnderTest = new VocabTerm();
    classUnderTest.setValues(values);

    assertEquals(classUnderTest.getValues(), values);

    // Test using builder pattern method
    classUnderTest = new VocabTerm().values(values);

    assertEquals(classUnderTest.getValues(), values);
  }

}
