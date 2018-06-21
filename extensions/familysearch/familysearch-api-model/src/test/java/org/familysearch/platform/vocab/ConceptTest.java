package org.familysearch.platform.vocab;

import java.util.Collections;
import java.util.List;

import org.gedcomx.common.ExtensibleData;
import org.gedcomx.common.TextValue;
import org.gedcomx.common.URI;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ConceptTest {

  @Test
  public void testId() {
    final String id = "testId";

    // Test using setter
    ExtensibleData classUnderTest = new Concept();
    classUnderTest.setId(id);

    assertEquals(classUnderTest.getId(), id);

    // Test using builder pattern method
    classUnderTest = new Concept().id(id);

    assertEquals(classUnderTest.getId(), id);
  }

  @Test
  public void testDescription() {
    final String description = "testDescription";

    // Test using setter
    Concept classUnderTest = new Concept();
    classUnderTest.setDescription(description);

    assertEquals(classUnderTest.getDescription(), description);

    // Test using builder pattern method
    classUnderTest = new Concept().description(description);

    assertEquals(classUnderTest.getDescription(), description);
  }

  @Test
  public void testNote() {
    final String note = "testNote";

    // Test using setter
    Concept classUnderTest = new Concept();
    classUnderTest.setNote(note);

    assertEquals(classUnderTest.getNote(), note);

    // Test using builder pattern method
    classUnderTest = new Concept().note(note);

    assertEquals(classUnderTest.getNote(), note);
  }

  @Test
  public void testTerms() {
    final String uriBase = "http://some.valid.uri/";
    final TextValue testTextValue = new TextValue("testValue");
    testTextValue.setLang("testLang");
    final Term testTerm = (Term) new Term()
        .concept(new URI(uriBase + "concept/testConceptId"))
        .sublist(new URI(uriBase + "term/testTerm"))
        .sublistPosition(4736)
        .type(new URI(uriBase + "type/testType"))
        .values(Collections.singletonList(testTextValue))
        .id("testId");
    List<Term> testTerms = Collections.singletonList(testTerm);

    // Test using setter
    Concept classUnderTest = new Concept();
    classUnderTest.setTerms(testTerms);

    assertEquals(classUnderTest.getTerms(), testTerms);

    // Test using builder pattern method
    classUnderTest = new Concept().terms(testTerms);

    assertEquals(classUnderTest.getTerms(), testTerms);
  }

  @Test
  public void testAttributes() {
    final ConceptAttribute testConceptAttribute = new ConceptAttribute()
        .id("testId")
        .name("testName")
        .value("testValue");
    List<ConceptAttribute> testConceptAttributes = Collections.singletonList(testConceptAttribute);

    // Test using setter
    Concept classUnderTest = new Concept();
    classUnderTest.setAttributes(testConceptAttributes);

    assertEquals(classUnderTest.getAttributes(), testConceptAttributes);

    // Test using builder pattern method
    classUnderTest = new Concept().attributes(testConceptAttributes);

    assertEquals(classUnderTest.getAttributes(), testConceptAttributes);
  }

  @Test
  public void testGedcomxUri() {
    final URI uri = new URI("http://some.valid.url/test");

    // Test using setter
    Concept classUnderTest = new Concept();
    classUnderTest.setGedcomxUri(uri);

    assertEquals(classUnderTest.getGedcomxUri(), uri);

    // Test using builder pattern method
    classUnderTest = new Concept().gedcomxUri(uri);

    assertEquals(classUnderTest.getGedcomxUri(), uri);
  }

}
