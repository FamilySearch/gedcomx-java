package org.gedcomx.conclusion;

import org.gedcomx.common.URI;
import org.gedcomx.types.IdentifierType;
import org.junit.jupiter.api.Test;

import static org.gedcomx.rt.SerializationUtil.processThroughJson;
import static org.gedcomx.rt.SerializationUtil.processThroughXml;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Ryan Heaton
 */
class IdentifierTest {

  /**
   * tests identifier xml
   */
  @Test
  void idXml() throws Exception {
    Identifier id = new Identifier();
    id.setKnownType(IdentifierType.Deprecated);
    id.setValue(URI.create("value"));
    id = processThroughXml(id);
    assertEquals(IdentifierType.Deprecated, id.getKnownType());
    assertEquals("value", id.getValue().toString());
  }

  /**
   * tests identifier json
   */
  @Test
  void idJson() throws Exception {
    Identifier id = new Identifier();
    id.setKnownType(IdentifierType.Deprecated);
    id.setValue(URI.create("value"));
    id = processThroughJson(id);
//    assertEquals(IdentifierType.Deprecated, id.getKnownType());
    assertEquals("value", id.getValue().toString());
  }

}
