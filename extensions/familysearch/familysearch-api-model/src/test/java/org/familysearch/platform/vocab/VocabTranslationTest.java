package org.familysearch.platform.vocab;

import org.gedcomx.common.TextValue;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VocabTranslationTest {

    private final String lang = "de";
    private final String text = "die Prüfung"; // "the test"
    private final TextValue textValue = new TextValue(text).lang(lang);

    @Test
    public void testLang() {
        // Test using setter
        VocabTranslation classUnderTest = new VocabTranslation();
        classUnderTest.setLang(lang);

        assertEquals(classUnderTest.getLang(), lang);

        // Test using builder pattern method
        classUnderTest = new VocabTranslation().lang(lang);

        assertEquals(classUnderTest.getLang(), lang);

        // Test using TextValue constructor
        classUnderTest = new VocabTranslation(textValue);

        assertEquals(classUnderTest.getLang(), lang);

        // Test constructor with parameters
        classUnderTest = new VocabTranslation(text, lang);

        assertEquals(classUnderTest.getLang(), lang);
    }

    @Test
    public void testText() {
        // Test using setter
        VocabTranslation classUnderTest = new VocabTranslation();
        classUnderTest.setText(text);

        assertEquals(classUnderTest.getText(), text);

        // Test using builder pattern method
        classUnderTest = new VocabTranslation().text(text);

        assertEquals(classUnderTest.getText(), text);

        // Test using TextValue constructor
        classUnderTest = new VocabTranslation(textValue);

        assertEquals(classUnderTest.getText(), text);
    }

}