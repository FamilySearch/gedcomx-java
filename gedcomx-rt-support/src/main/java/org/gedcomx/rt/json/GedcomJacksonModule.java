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
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.gedcomx.rt.GedcomNamespaceManager;

/**
 * GEDCOM Jackson module for Jackson customizations.
 *
 * @author Ryan Heaton
 */
public class GedcomJacksonModule extends Module {

  /**
   * Creates an object mapper given the specified context classes.
   *
   * @param classes the context classes.
   * @return The object mapper.
   */
  public static ObjectMapper createObjectMapper(Class<?>... classes) {
    AnnotationIntrospector introspector = new JacksonAnnotationIntrospector();
    ObjectMapper mapper = new ObjectMapper()
      .setAnnotationIntrospector(introspector)
      .enable(SerializationFeature.INDENT_OUTPUT)
      .setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.registerModule(new GedcomJacksonModule());
    for (Class<?> contextClass : classes) {
      GedcomNamespaceManager.registerKnownJsonType(contextClass);
    }
    return mapper;
  }

  @Override
  public String getModuleName() {
    return "gedcomx";
  }

  @Override
  public Version version() {
    return new Version(1, 0, 0, null, "org.gedcomx", "gedcomx");
  }

  @Override
  public void setupModule(SetupContext context) {
    context.addBeanSerializerModifier(new GedcomBeanSerializerModifier());
    context.addBeanDeserializerModifier(new GedcomBeanDeserializerModifier());
    context.addDeserializers(new GedcomDeserializers());
    context.addSerializers(new GedcomSerializers());
  }

  protected static class GedcomSerializers extends Serializers.Base {
    @Override
    public JsonSerializer<?> findCollectionSerializer(SerializationConfig config, CollectionType type, BeanDescription beanDesc, TypeSerializer elementTypeSerializer, JsonSerializer<Object> elementValueSerializer) {
      if (type.getContentType() != null && HasJsonKey.class.isAssignableFrom(type.getContentType().getRawClass())) {
        return new KeyedListSerializer();
      }

      return super.findCollectionSerializer(config, type, beanDesc, elementTypeSerializer, elementValueSerializer);
    }
  }

  protected static class GedcomDeserializers extends Deserializers.Base {

    @Override
    public JsonDeserializer<?> findCollectionDeserializer(CollectionType type, DeserializationConfig config, BeanDescription beanDesc, TypeDeserializer elementTypeDeserializer, JsonDeserializer<?> elementDeserializer) throws JsonMappingException {
      if (type.getContentType() != null && HasJsonKey.class.isAssignableFrom(type.getContentType().getRawClass())) {
        return new KeyedListDeserializer(type.getContentType().getRawClass());
      }

      return super.findCollectionDeserializer(type, config, beanDesc, elementTypeDeserializer, elementDeserializer);
    }

  }

}
