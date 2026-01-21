package org.gedcomx.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class TextValueTest {
  @Test
  void defaultCtor() throws Exception {
    TextValue literal = new TextValue();
    literal.setLang("en-US");
    literal.setValue("value");

    assertEquals("en-US", literal.getLang());
    assertEquals("value", literal.getValue());
  }

  @Test
  void valueCtor() throws Exception {
    TextValue literal = new TextValue("value");

    assertNull(literal.getLang());
    assertEquals("value", literal.getValue());
  }

  @Test
  void equalsAndHash() throws Exception {
    TextValue literal1 = new TextValue("value");
    literal1.setLang("lang");
    TextValue literal2 = new TextValue("value");
    literal2.setLang("lang");
    TextValue literal3 = new TextValue("not-matching");
    TextValue literal4 = new TextValue("not-matching");
    literal4.setLang("lang");

    assertEquals(literal1, literal1);
    assertNotEquals(null, literal1);
    assertEquals(literal1, literal1);
    assertEquals(literal1, literal2);
    assertEquals(literal1.hashCode(), literal2.hashCode());
    assertNotEquals(literal1, literal3);
    assertNotEquals(literal1, literal4);

    assertTrue(literal1.toString().contains("value"));
    assertTrue(literal2.toString().contains("lang"));
  }
}
