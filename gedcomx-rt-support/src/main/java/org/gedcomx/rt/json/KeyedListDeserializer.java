/**
 * Copyright Intellectual Reserve, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gedcomx.rt.json;

import java.util.ArrayList;
import java.util.List;

import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonParser;
import tools.jackson.core.JsonToken;
import tools.jackson.databind.DatabindException;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.ValueDeserializer;

/**
 * @author Ryan Heaton
 */
public class KeyedListDeserializer extends ValueDeserializer<List<? extends HasJsonKey>> {

  private final Class<?> itemType;

  public KeyedListDeserializer(Class<?> itemType) {
    if (itemType == null) {
      throw new NullPointerException();
    }
    this.itemType = itemType;
  }

  @Override
  public List<? extends HasJsonKey> deserialize(JsonParser jp, DeserializationContext ctxt) throws JacksonException {
    return deserializeGeneric(jp, this.itemType);
  }

  static List<? extends HasJsonKey> deserializeGeneric(JsonParser jp, Class<?> itemType) throws JacksonException {
    if (jp.currentToken() == JsonToken.START_OBJECT) {
      jp.nextToken();
    }
    else {
      throw DatabindException.from(jp, "Unable to deserialize keyed list: unexpected token: " + jp.currentToken().name());
    }

    ArrayList<HasJsonKey> list = new ArrayList<>();
    for (; jp.currentToken() != JsonToken.END_OBJECT; jp.nextToken()) {
      String key = jp.currentName();
      jp.nextToken();
      if (jp.isExpectedStartArrayToken()) {
        JsonToken t;
        while ((t = jp.nextToken()) != JsonToken.END_ARRAY) {
          HasJsonKey value;

          if (t == JsonToken.VALUE_NULL) {
            value = null;
          }
          else {
            value = (HasJsonKey) jp.readValueAs(itemType);
            if (!KeyedListSerializer.JSON_DEFAULT_KEY.equals(key)) {
              value.setJsonKey(key);
            }
          }

          list.add(value);
        }
      }
      else {
        HasJsonKey value = (HasJsonKey) jp.readValueAs(itemType);
        if (!KeyedListSerializer.JSON_DEFAULT_KEY.equals(key)) {
          value.setJsonKey(key);
        }
        list.add(value);
      }
    }

    return list;
  }
}
