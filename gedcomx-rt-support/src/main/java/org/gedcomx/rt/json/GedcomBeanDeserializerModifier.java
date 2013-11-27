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

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.deser.BeanDeserializer;
import org.codehaus.jackson.map.deser.BeanDeserializerModifier;
import org.codehaus.jackson.map.introspect.BasicBeanDescription;

/**
 * Modifications for GEDCOM bean serializers.
 *
 * @author Ryan Heaton
 */
public class GedcomBeanDeserializerModifier extends BeanDeserializerModifier {
  @Override
  public JsonDeserializer<?> modifyDeserializer(DeserializationConfig config, BasicBeanDescription beanDesc, JsonDeserializer<?> deserializer) {
    return deserializer instanceof BeanDeserializer ? new ExtensibleObjectDeserializer((BeanDeserializer) deserializer) : deserializer;
  }
}
