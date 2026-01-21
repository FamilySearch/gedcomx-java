package org.gedcomx.records;

import org.gedcomx.rt.SerializationUtil;
import org.gedcomx.types.FieldValueStatusType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Class for testing the FieldValue class.
 * User: Randy Wilson
 * Date: 11/25/2014
 * Time: 2:55 PM
 */
class TestFieldValue {

  @Test
  void xml() throws Exception {
    FieldValue fieldValue = new FieldValue();
    fieldValue.setKnownStatus(FieldValueStatusType.Unreadable);

    fieldValue = SerializationUtil.processThroughXml(fieldValue);

    assertEquals(FieldValueStatusType.Unreadable, fieldValue.getKnownStatus());
  }

}
