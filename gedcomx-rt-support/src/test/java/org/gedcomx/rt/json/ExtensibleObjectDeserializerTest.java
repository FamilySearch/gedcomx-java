package org.gedcomx.rt.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.gedcomx.rt.GedcomNamespaceManager;
import org.gedcomx.rt.SupportsExtensionElements;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.exc.UnrecognizedPropertyException;
import tools.jackson.databind.json.JsonMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests that ExtensibleObjectDeserializer only intercepts properties it can actually turn into
 * GedcomX extensions, and hands everything else back to Jackson.
 *
 * GedcomValueDeserializerModifier wraps every BeanDeserializer, not just the deserializers of
 * extensible GedcomX types, so the fall-through in handleUnknownProperty is reached for ordinary
 * beans too. Skipping the property there bypassed the configured DeserializationProblemHandlers
 * and FAIL_ON_UNKNOWN_PROPERTIES: a mapper that had explicitly enabled the feature dropped unknown
 * properties silently, with no way to observe it.
 */
class ExtensibleObjectDeserializerTest {

  static class PlainBean {
    private String value1;
    private String value2;
    private String value3;
    private String value4;
    private String value5;
    private String value6;
    private String value7;

    public String getValue1() { return value1; }
    public void setValue1(String v) { value1 = v; }
    public String getValue2() { return value2; }
    public void setValue2(String v) { value2 = v; }
    public String getValue3() { return value3; }
    public void setValue3(String v) { value3 = v; }
    public String getValue4() { return value4; }
    public void setValue4(String v) { value4 = v; }
    public String getValue5() { return value5; }
    public void setValue5(String v) { value5 = v; }
    public String getValue6() { return value6; }
    public void setValue6(String v) { value6 = v; }
    public String getValue7() { return value7; }
    public void setValue7(String v) { value7 = v; }
  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  static class LenientBean extends PlainBean {
  }

  @JsonElementWrapper(name = "testExtension")
  static class TestExtension {
    private String data;

    public String getData() { return data; }
    public void setData(String d) { data = d; }
  }

  static class ExtensibleBean extends PlainBean implements SupportsExtensionElements {
    private final List<Object> extensionElements = new ArrayList<>();

    @Override
    public List<Object> getExtensionElements() {
      return extensionElements;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <E> E findExtensionOfType(Class<E> clazz) {
      return (E) extensionElements.stream().filter(clazz::isInstance).findFirst().orElse(null);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <E> List<E> findExtensionsOfType(Class<E> clazz) {
      return (List<E>) extensionElements.stream().filter(clazz::isInstance).toList();
    }

    @Override
    public void addExtensionElement(Object element) {
      extensionElements.add(element);
    }
  }

  private static JsonMapper mapper(boolean failOnUnknown) {
    JsonMapper.Builder builder = GedcomJacksonModule.createJsonMapperBuilder(TestExtension.class);
    if (failOnUnknown) {
      builder.enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }
    return builder.build();
  }

  @Test
  void unknownPropertyOnPlainBeanIsReportedWhenFailOnUnknownEnabled() {
    UnrecognizedPropertyException e = assertThrows(UnrecognizedPropertyException.class,
      () -> mapper(true).readValue("{\"value1\":\"a\",\"notAProperty\":\"b\"}", PlainBean.class));
    assertEquals("notAProperty", e.getPropertyName());
  }

  @Test
  void unknownPropertyOnPlainBeanIsIgnoredWhenFailOnUnknownDisabled() {
    // FAIL_ON_UNKNOWN_PROPERTIES is disabled by default in Jackson 3, so consumers that take the
    // mapper GedcomJacksonModule builds for them see no behavior change.
    PlainBean bean = mapper(false).readValue("{\"value1\":\"a\",\"notAProperty\":\"b\"}", PlainBean.class);
    assertEquals("a", bean.getValue1());
  }

  @Test
  void ignoreUnknownAnnotationIsStillHonored() {
    LenientBean bean = mapper(true).readValue("{\"value1\":\"a\",\"notAProperty\":\"b\"}", LenientBean.class);
    assertEquals("a", bean.getValue1());
  }

  @Test
  void registeredExtensionElementsAreStillCollected() {
    String jsonName = GedcomNamespaceManager.getJsonName(TestExtension.class);
    assertNotNull(jsonName, "TestExtension should resolve to a JSON name");

    ExtensibleBean bean = mapper(true).readValue(
      "{\"value1\":\"a\",\"" + jsonName + "\":[{\"data\":\"x\"}]}", ExtensibleBean.class);

    assertEquals("a", bean.getValue1());
    assertEquals(1, bean.getExtensionElements().size());
    TestExtension extension = assertInstanceOf(TestExtension.class, bean.getExtensionElements().get(0));
    assertEquals("x", extension.getData());
  }

  @Test
  void unrecognizedPropertyOnExtensibleBeanIsReportedWhenFailOnUnknownEnabled() {
    // An extensible type still fails on a property that resolves to no registered extension type:
    // there is nowhere to put the value, so it would otherwise be dropped without a trace.
    UnrecognizedPropertyException e = assertThrows(UnrecognizedPropertyException.class,
      () -> mapper(true).readValue("{\"value1\":\"a\",\"notAnExtension\":{\"data\":\"x\"}}", ExtensibleBean.class));
    assertEquals("notAnExtension", e.getPropertyName());
    assertTrue(e.getMessage().contains("notAnExtension"));
  }
}
