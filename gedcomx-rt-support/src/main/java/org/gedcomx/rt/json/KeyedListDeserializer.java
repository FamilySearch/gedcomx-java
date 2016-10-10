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


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ryan Heaton
 */
public class KeyedListDeserializer extends JsonDeserializer<List<? extends HasJsonKey>> {

  private final Class<?> itemType;

  public KeyedListDeserializer(Class<?> itemType) {
    if (itemType == null) {
      throw new NullPointerException();
    }
    this.itemType = itemType;
  }

  @Override
  public List<? extends HasJsonKey> deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
    return deserializeGeneric(jp, ctxt, this.itemType);
  }

  static List<? extends HasJsonKey> deserializeGeneric(JsonParser jp, DeserializationContext ctxt, Class<?> itemType) throws IOException, JsonProcessingException {
    if (jp.getCurrentToken() == JsonToken.START_OBJECT) {
      jp.nextToken();
    }
    else {
      throw new JsonMappingException(jp, "Unable to deserialize keyed list: unexpected token: " + jp.getCurrentToken().name());
    }

    ArrayList<HasJsonKey> list = new ArrayList<HasJsonKey>();
    for (; jp.getCurrentToken() != JsonToken.END_OBJECT; jp.nextToken()) {
      String key = jp.getCurrentName();
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
