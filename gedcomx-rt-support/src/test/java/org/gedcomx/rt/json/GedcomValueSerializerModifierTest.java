package org.gedcomx.rt.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ValueSerializer;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.databind.ser.SerializationContextExt;
import tools.jackson.databind.ser.UnrolledBeanSerializer;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Regression tests for GedcomValueSerializerModifier (PR #132).
 *
 * Jackson 3 selects UnrolledBeanSerializer for classes with few bean properties
 * (up to 6). GedcomValueSerializerModifier.modifySerializer() must handle
 * UnrolledBeanSerializer in addition to BeanSerializer, or small objects
 * like Tag will not be wrapped in ExtensibleObjectSerializer.
 */
class GedcomValueSerializerModifierTest {

  /**
   * A minimal two-property bean that Jackson 3 will serialize using
   * UnrolledBeanSerializer (the small-object fast path, up to 6 props).
   */
  @XmlRootElement
  @JsonInclude(JsonInclude.Include.NON_NULL)
  static class SmallBean {
    private String value1;
    private String value2;

    public String getValue1() { return value1; }
    public void setValue1(String v) { value1 = v; }
    public String getValue2() { return value2; }
    public void setValue2(String v) { value2 = v; }
  }

  @Test
  void smallObjectUsesUnrolledBeanSerializer() {
    // Verify the precondition: Jackson 3 selects UnrolledBeanSerializer for small
    // beans *before* any GedcomX module modifier runs. Use a plain JsonMapper so
    // GedcomValueSerializerModifier doesn't intercept the result. If this fails,
    // Jackson changed its selection strategy and the other tests may need updating.
    JsonMapper mapper = JsonMapper.builder().build();
    SerializationContextExt ctx = mapper._serializationContext();
    ValueSerializer<Object> ser = ctx.findRootValueSerializer(SmallBean.class);
    assertTrue(ser instanceof UnrolledBeanSerializer,
      "Jackson 3 should select UnrolledBeanSerializer for small beans. Actual: " + ser.getClass().getName());
  }

  @Test
  void modifySerializerWrapsUnrolledBeanSerializer() {
    // GedcomValueSerializerModifier must convert UnrolledBeanSerializer into
    // ExtensibleObjectSerializer so that extension elements/attributes are handled.
    // Without the fix in PR #132, only BeanSerializer instances were intercepted,
    // leaving UnrolledBeanSerializer (used for Tag and other small objects) unwrapped.
    JsonMapper mapper = GedcomJacksonModule.createJsonMapper(SmallBean.class);
    SerializationContextExt ctx = mapper._serializationContext();
    ValueSerializer<Object> ser = ctx.findRootValueSerializer(SmallBean.class);
    assertTrue(ser instanceof ExtensibleObjectSerializer,
      "GedcomValueSerializerModifier must wrap UnrolledBeanSerializer in ExtensibleObjectSerializer. " +
      "Actual: " + ser.getClass().getName());
  }

  @Test
  void smallBeanSerializesCorrectly() throws Exception {
    JsonMapper mapper = GedcomJacksonModule.createJsonMapper(SmallBean.class);

    SmallBean bean = new SmallBean();
    bean.setValue1("hello");
    bean.setValue2("world");

    String json = mapper.writeValueAsString(bean);
    assertNotNull(json);

    JsonNode node = mapper.readTree(json);
    assertEquals("hello", node.get("value1").asText());
    assertEquals("world", node.get("value2").asText());
  }
}
