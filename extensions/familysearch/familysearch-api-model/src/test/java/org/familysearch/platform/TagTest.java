package org.familysearch.platform;

import org.gedcomx.common.URI;
import org.gedcomx.conclusion.Person;
import org.gedcomx.rt.json.ExtensibleObjectSerializer;
import org.gedcomx.rt.json.GedcomJacksonModule;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.ValueSerializer;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.databind.ser.SerializationContextExt;
import tools.jackson.databind.ser.UnrolledBeanSerializer;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Regression tests for Tag JSON serialization (PR #132).
 *
 * Tag has only two properties, so Jackson 3 selects UnrolledBeanSerializer for it.
 * GedcomValueSerializerModifier must handle UnrolledBeanSerializer (not just
 * BeanSerializer) or Tag will not be wrapped in ExtensibleObjectSerializer.
 */
class TagTest {

  @Test
  void tagUsesUnrolledBeanSerializerPrecondition() {
    // Confirm Jackson 3 uses UnrolledBeanSerializer for Tag *before* any GedcomX modifier
    // runs. Use a plain JsonMapper so GedcomValueSerializerModifier doesn't intercept.
    // If this fails, Jackson changed its threshold and the bug may no longer apply.
    JsonMapper mapper = JsonMapper.builder().build();
    SerializationContextExt ctx = mapper._serializationContext();
    ValueSerializer<Object> ser = ctx.findRootValueSerializer(Tag.class);
    assertTrue(ser instanceof UnrolledBeanSerializer,
      "Tag has only 2 properties, so Jackson 3 should select UnrolledBeanSerializer. Actual: " + ser.getClass().getName());
  }

  @Test
  void tagSerializerIsWrappedInExtensibleObjectSerializer() {
    // GedcomValueSerializerModifier must wrap Tag's serializer in ExtensibleObjectSerializer.
    // Before PR #132, only BeanSerializer was intercepted; UnrolledBeanSerializer was returned
    // as-is, bypassing the GedcomX extension mechanism.
    JsonMapper mapper = GedcomJacksonModule.createJsonMapper(Tag.class);
    SerializationContextExt ctx = mapper._serializationContext();
    ValueSerializer<Object> ser = ctx.findRootValueSerializer(Tag.class);
    assertTrue(ser instanceof ExtensibleObjectSerializer,
      "Tag's serializer must be wrapped in ExtensibleObjectSerializer. Actual: " + ser.getClass().getName());
  }

  @Test
  void tagSerializesAsExtensionElementOnPerson() throws Exception {
    // Integration test: Tag embedded as an extension element in a Person serializes
    // and round-trips correctly through JSON.
    JsonMapper mapper = GedcomJacksonModule.createJsonMapper(Tag.class);

    Tag tag = new Tag();
    tag.setResource(URI.create("http://gedcomx.org/Birth"));

    Person person = new Person();
    person.setId("P-1");
    person.addExtensionElement(tag);

    FamilySearchPlatform fsp = new FamilySearchPlatform();
    fsp.addPerson(person);

    String json = mapper.writeValueAsString(fsp);
    assertNotNull(json);
    assertTrue(json.contains("tags"), "Serialized JSON should contain 'tags' array");
    assertTrue(json.contains("http://gedcomx.org/Birth"), "Serialized JSON should contain the tag resource URI");

    FamilySearchPlatform roundTripped = mapper.readValue(json, FamilySearchPlatform.class);
    assertNotNull(roundTripped);
    assertNotNull(roundTripped.getPerson());
  }

  @Test
  void tagWithConclusionIdSerializesAsExtensionElement() throws Exception {
    JsonMapper mapper = GedcomJacksonModule.createJsonMapper(Tag.class);

    Tag tag = new Tag("CONC-001");

    Person person = new Person();
    person.setId("P-1");
    person.addExtensionElement(tag);

    FamilySearchPlatform fsp = new FamilySearchPlatform();
    fsp.addPerson(person);

    String json = mapper.writeValueAsString(fsp);
    assertNotNull(json);
    assertTrue(json.contains("CONC-001"), "Serialized JSON should contain the conclusionId");
  }
}
