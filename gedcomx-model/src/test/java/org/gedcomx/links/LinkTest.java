package org.gedcomx.links;

import org.gedcomx.common.URI;
import org.junit.jupiter.api.Test;

import static org.gedcomx.rt.SerializationUtil.processThroughJson;
import static org.gedcomx.rt.SerializationUtil.processThroughXml;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Ryan Heaton
 */
class LinkTest {

  /**
   * tests link xml
   */
  @Test
  void linkXml() throws Exception {
    Link link = createLink();
    link = processThroughXml(link);
    assertLink(link);
    assertEquals("rel", link.getRel());
    assertEquals("rel", link.getJsonKey());
    assertEquals("<urn:link>; rel=\"rel\"; template=\"template\"; type=\"application/xml\"; accept=\"text/plain\"; allow=\"GET\"; hreflang=\"en\"; title=\"title\"", link.getHttpHeaderValue());
    assertEquals("<urn:link>; rel=\"rel\"; template=\"template\"; type=\"application/xml\"; accept=\"text/plain\"; allow=\"GET\"; hreflang=\"en\"; title=\"title\"", link.toString());
  }

  /**
   * tests link json
   */
  @Test
  void linkJson() throws Exception {
    Link link = createLink();
    link = processThroughJson(link);
    assertLink(link);
    assertEquals("<urn:link>; template=\"template\"; type=\"application/xml\"; accept=\"text/plain\"; allow=\"GET\"; hreflang=\"en\"; title=\"title\"", link.getHttpHeaderValue());
    assertEquals("<urn:link>; template=\"template\"; type=\"application/xml\"; accept=\"text/plain\"; allow=\"GET\"; hreflang=\"en\"; title=\"title\"", link.toString());
  }

  /**
   * tests link json
   */
  @Test
  void headerValueForUninitializedLink() throws Exception {
    Link link = new Link();
    assertEquals("<>", link.getHttpHeaderValue());
  }

  private void assertLink(Link link) {
    assertEquals(URI.create("urn:link"), link.getHref());
    assertEquals("template", link.getTemplate());
    assertEquals("text/plain", link.getAccept());
    assertEquals("GET", link.getAllow());
    assertEquals("en", link.getHreflang());
    assertEquals("title", link.getTitle());
    assertEquals("application/xml", link.getType());
  }

  private Link createLink() {
    Link link = new Link();
    link.setHref(URI.create("urn:link"));
    link.setRel("rel");
    link.setTemplate("template");
    link.setAccept("text/plain");
    link.setAllow("GET");
    link.setHreflang("en");
    link.setJsonKey("rel");
    link.setTitle("title");
    link.setType("application/xml");
    return link;
  }

}
