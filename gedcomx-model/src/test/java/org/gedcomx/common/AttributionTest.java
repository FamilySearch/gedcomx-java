package org.gedcomx.common;

import org.gedcomx.rt.GedcomNamespaceManager;
import org.junit.jupiter.api.Test;

import jakarta.xml.bind.JAXBContext;
import java.util.Date;

import static org.gedcomx.rt.SerializationUtil.processThroughJson;
import static org.gedcomx.rt.SerializationUtil.processThroughXml;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


/**
 * @author Ryan Heaton
 */
class AttributionTest {

  static {
    GedcomNamespaceManager.registerKnownJsonType(CustomEntity.class);
  }

  /**
   * tests attribution xml
   */
  @Test
  void attributionXml() throws Exception {
    Date ts = new Date();

    Attribution attribution = new Attribution();
    attribution.setContributor(new ResourceReference());
    attribution.getContributor().setResource(URI.create("urn:someid"));
    attribution.setModified(ts);
    attribution.setChangeMessage("hello, there.");
    attribution.addExtensionElement(new CustomEntity("alt1"));
    attribution.addExtensionElement(new CustomEntity("alt2"));

    attribution = processThroughXml(attribution, Attribution.class, JAXBContext.newInstance(Attribution.class, CustomEntity.class));
    assertEquals("urn:someid", attribution.getContributor().getResource().toString());
    assertEquals(ts, attribution.getModified());
    assertEquals("hello, there.", attribution.getChangeMessage());
    assertEquals("alt1", ((CustomEntity) attribution.getExtensionElements().get(0)).getId());
    assertEquals("alt2", ((CustomEntity) attribution.getExtensionElements().get(1)).getId());
    assertNull(attribution.findExtensionOfType(String.class));
    assertEquals("alt1", attribution.findExtensionOfType(CustomEntity.class).getId());
    assertEquals(0, attribution.findExtensionsOfType(String.class).size());
    assertEquals(2, attribution.findExtensionsOfType(CustomEntity.class).size());
    assertEquals("alt2", attribution.findExtensionsOfType(CustomEntity.class).get(1).getId());

    attribution.setExtensionElements(null);
    assertNull(attribution.findExtensionOfType(CustomEntity.class));
    assertEquals(0, attribution.findExtensionsOfType(CustomEntity.class).size());
  }

  /**
   * tests attribution json
   */
  @Test
  void attributionJson() throws Exception {
    Date ts = new Date();

    Attribution attribution = new Attribution();
    attribution.setContributor(new ResourceReference());
    attribution.getContributor().setResource(URI.create("urn:someid"));
    attribution.setModified(ts);
    attribution.setChangeMessage("hello, there.");
    attribution.addExtensionElement(new CustomEntity("alt1"));
    attribution.addExtensionElement(new CustomEntity("alt2"));

    attribution = processThroughJson(attribution);
    assertEquals("urn:someid", attribution.getContributor().getResource().toString());
    assertEquals(ts, attribution.getModified());
    assertEquals("hello, there.", attribution.getChangeMessage());
    assertEquals("alt1", ((CustomEntity) attribution.getExtensionElements().get(0)).getId());
    assertEquals("alt2", ((CustomEntity) attribution.getExtensionElements().get(1)).getId());
    assertNull(attribution.findExtensionOfType(String.class));
    assertEquals("alt1", attribution.findExtensionOfType(CustomEntity.class).getId());
    assertEquals(0, attribution.findExtensionsOfType(String.class).size());
    assertEquals(2, attribution.findExtensionsOfType(CustomEntity.class).size());
    assertEquals("alt2", attribution.findExtensionsOfType(CustomEntity.class).get(1).getId());

    assertEquals("urn:someid", attribution.toString());
    attribution.setContributor(null);
    assertEquals("", attribution.toString());
  }

}
