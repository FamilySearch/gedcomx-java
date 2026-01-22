/**
 * Copyright Intellectual Reserve, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gedcomx.rt.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.gedcomx.rt.GedcomNamespaceManager;
import tools.jackson.core.Version;
import tools.jackson.databind.BeanDescription;
import tools.jackson.databind.DeserializationConfig;
import tools.jackson.databind.JacksonModule;
import tools.jackson.databind.SerializationConfig;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.ValueDeserializer;
import tools.jackson.databind.ValueSerializer;
import tools.jackson.databind.deser.Deserializers;
import tools.jackson.databind.introspect.JacksonAnnotationIntrospector;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.databind.jsontype.TypeDeserializer;
import tools.jackson.databind.jsontype.TypeSerializer;
import tools.jackson.databind.ser.Serializers;
import tools.jackson.databind.type.CollectionType;

/**
 * GEDCOM Jackson module for Jackson customizations.
 *
 * @author Ryan Heaton
 */
public class GedcomJacksonModule extends JacksonModule {

  /**
   * Creates a JSON mapper given the specified context classes.
   *
   * @param classes the context classes.
   * @return The JSON mapper.
   */
  public static JsonMapper createJsonMapper(Class<?>... classes) {
    for (Class<?> contextClass : classes) {
      GedcomNamespaceManager.registerKnownJsonType(contextClass);
    }

    return createJsonMapperBuilder(classes).build();
  }

  /**
   * Creates a JSON mapper builder given the specified context classes.
   *
   * @param classes the context classes.
   * @return The JSON mapper builder.
   */
  public static JsonMapper.Builder createJsonMapperBuilder(Class<?>... classes) {
    for (Class<?> contextClass : classes) {
      GedcomNamespaceManager.registerKnownJsonType(contextClass);
    }

    return JsonMapper.builder()
      .annotationIntrospector(new JacksonAnnotationIntrospector())
      .enable(SerializationFeature.INDENT_OUTPUT)
      .changeDefaultPropertyInclusion(
        incl -> JsonInclude.Value.construct(JsonInclude.Include.NON_NULL, JsonInclude.Include.NON_NULL))
      .addModule(new GedcomJacksonModule());
  }

  @Override
  public String getModuleName() {
    return "gedcomx-jackson3";
  }

  @Override
  public Version version() {
    return new Version(1, 0, 0, null, "org.gedcomx", "gedcomx");
  }

  @Override
  public void setupModule(SetupContext context) {
    context.addSerializerModifier(new GedcomValueSerializerModifier());
    context.addDeserializerModifier(new GedcomValueDeserializerModifier());
    context.addDeserializers(new GedcomDeserializers());
    context.addSerializers(new GedcomSerializers());
  }

  protected static class GedcomSerializers extends Serializers.Base {
    @Override
    public ValueSerializer<?> findCollectionSerializer(
      SerializationConfig config,
      CollectionType type,
      BeanDescription.Supplier beanDescRef,
      com.fasterxml.jackson.annotation.JsonFormat.Value formatOverrides,
      TypeSerializer elementTypeSerializer,
      ValueSerializer<Object> elementValueSerializer) {

      if (type.getContentType() != null && HasJsonKey.class.isAssignableFrom(type.getContentType().getRawClass())) {
        return new KeyedListSerializer();
      }

      return super.findCollectionSerializer(config, type, beanDescRef, formatOverrides, elementTypeSerializer, elementValueSerializer);
    }
  }

  protected static class GedcomDeserializers extends Deserializers.Base {
    @Override
    public ValueDeserializer<?> findCollectionDeserializer(
      CollectionType type,
      DeserializationConfig config,
      BeanDescription.Supplier beanDescRef,
      TypeDeserializer elementTypeDeserializer,
      ValueDeserializer<?> elementDeserializer) {

      if (type.getContentType() != null && HasJsonKey.class.isAssignableFrom(type.getContentType().getRawClass())) {
        return new KeyedListDeserializer(type.getContentType().getRawClass());
      }

      return super.findCollectionDeserializer(type, config, beanDescRef, elementTypeDeserializer, elementDeserializer);
    }

    @Override
    public boolean hasDeserializerFor(DeserializationConfig config, Class<?> valueType) {
      return HasJsonKey.class.isAssignableFrom(valueType);
    }
  }

}
