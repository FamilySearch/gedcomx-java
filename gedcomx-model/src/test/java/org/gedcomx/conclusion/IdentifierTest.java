package org.gedcomx.conclusion;

import org.gedcomx.common.URI;
import org.gedcomx.types.IdentifierType;

import java.util.Collections;
import org.junit.Test;

import static org.gedcomx.rt.SerializationUtil.processThroughJson;
import static org.gedcomx.rt.SerializationUtil.processThroughXml;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author Ryan Heaton
 */
public class IdentifierTest {

  /**
   * tests identifier xml
   */
  @Test
  public void testIdXml() throws Exception {
    Identifier id = new Identifier();
    id.setKnownType(IdentifierType.Deprecated);
    id.setValue(URI.create("value"));
    id.setTransientProperty("test", "1234-abc");
    assertEquals("1234-abc", id.getTransientProperty("test"));
    id = processThroughXml(id);
    assertEquals(IdentifierType.Deprecated, id.getKnownType());
    assertEquals("value", id.getValue().toString());
    assertEquals(Collections.emptyMap(), id.getTransientProperties());
  }

  /**
   * tests identifier json
   */
  @Test
  public void testIdJson() throws Exception {
    Identifier id = new Identifier();
    id.setKnownType(IdentifierType.Deprecated);
    id.setValue(URI.create("value"));
    id.setTransientProperty("test", "1234-abc");
    assertEquals("1234-abc", id.getTransientProperty("test"));
    id = processThroughJson(id);
//    assertEquals(IdentifierType.Deprecated, id.getKnownType());
    assertEquals("value", id.getValue().toString());
    assertEquals(Collections.emptyMap(), id.getTransientProperties());
  }

}
