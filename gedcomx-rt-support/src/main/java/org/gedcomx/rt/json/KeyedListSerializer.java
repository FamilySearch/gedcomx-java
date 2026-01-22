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
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.DatabindException;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ValueSerializer;

/**
 * @author Ryan Heaton
 */
public class KeyedListSerializer extends ValueSerializer<Collection<? extends HasJsonKey>> {

  public static final String JSON_DEFAULT_KEY = "$";

  @Override
  public void serialize(Collection<? extends HasJsonKey> value, JsonGenerator jgen, SerializationContext provider)
    throws JacksonException {

    serializeGeneric(value, jgen, provider);
  }

  static void serializeGeneric(Collection<?> value, JsonGenerator jgen, SerializationContext provider)
    throws JacksonException {

    if (value == null) {
      jgen.writeNull();
    }
    else {
      jgen.writeStartObject();
      Map<String, List<Object>> bykey = new HashMap<>();
      for (Object keyed : value) {
        String jsonKey = ((HasJsonKey) keyed).getJsonKey();
        if (jsonKey == null) {
          jsonKey = JSON_DEFAULT_KEY;
        }

        List<Object> keyedList = bykey.computeIfAbsent(jsonKey, k -> new ArrayList<>());
        keyedList.add(keyed);

        boolean unique = ((HasJsonKey) keyed).isHasUniqueKey();
        if (unique && keyedList.size() > 1) {
          throw DatabindException.from(jgen, "Attempt to serialize " + keyed + " failed because it's key '" + jsonKey + "' is not unique.");
        }
      }

      for (Map.Entry<String, List<Object>> keyedObjects : bykey.entrySet()) {
        String jsonKey = keyedObjects.getKey();
        jgen.writeName(jsonKey);
        boolean notUnique = keyedObjects.getValue().size() != 1 || !((HasJsonKey)keyedObjects.getValue().get(0)).isHasUniqueKey();
        if (notUnique) {
          jgen.writeStartArray();
        }

        for (Object keyed : keyedObjects.getValue()) {
          provider.findTypedValueSerializer(keyed.getClass(), true).serialize(keyed, jgen, provider);
        }

        if (notUnique) {
          jgen.writeEndArray();
        }
      }
      jgen.writeEndObject();
    }
  }
}
