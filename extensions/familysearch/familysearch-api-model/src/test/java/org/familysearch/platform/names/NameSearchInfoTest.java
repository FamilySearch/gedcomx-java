package org.familysearch.platform.names;

import org.gedcomx.types.NamePartType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


class NameSearchInfoTest {

  private static final String TEST_TEXT = "just some text that would be a name";
  private static final String TEST_NAME_ID = "12349874561";
  private static final int TEST_WEIGHT = 83;

  @Test
  void nameSearchInfo() throws Exception {

    NameSearchInfo nameSearchInfo = new NameSearchInfo();
    assertNull(nameSearchInfo.getText());
    assertNull(nameSearchInfo.getNameId());
    assertNull(nameSearchInfo.getNamePartType());
    assertNull(nameSearchInfo.getKnownNamePartType());
    assertNull(nameSearchInfo.getWeight());

    nameSearchInfo.setText(TEST_TEXT);
    nameSearchInfo.setNameId(TEST_NAME_ID);
    nameSearchInfo.setNamePartType(NamePartType.Given.toQNameURI());
    nameSearchInfo.setWeight(TEST_WEIGHT);

    assertEquals(TEST_TEXT, nameSearchInfo.getText());
    assertEquals(TEST_NAME_ID, nameSearchInfo.getNameId());
    assertEquals(nameSearchInfo.getNamePartType(), NamePartType.Given.toQNameURI());
    assertEquals(NamePartType.Given, nameSearchInfo.getKnownNamePartType());
    assertEquals(nameSearchInfo.getWeight(), Integer.valueOf(TEST_WEIGHT));

    nameSearchInfo.setKnownNamePartType(NamePartType.Surname);
    assertEquals(nameSearchInfo.getNamePartType(), NamePartType.Surname.toQNameURI());
    assertEquals(NamePartType.Surname, nameSearchInfo.getKnownNamePartType());

  }
}
