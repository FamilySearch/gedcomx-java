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

import tools.jackson.databind.BeanDescription;
import tools.jackson.databind.DeserializationConfig;
import tools.jackson.databind.ValueDeserializer;
import tools.jackson.databind.deser.ValueDeserializerModifier;
import tools.jackson.databind.deser.bean.BeanDeserializer;

/**
 * Modifications for GEDCOM value serializers.
 *
 * @author Ryan Heaton
 */
public class GedcomValueDeserializerModifier extends ValueDeserializerModifier {

  @Override
  public ValueDeserializer<?> modifyDeserializer(
    DeserializationConfig config,
    BeanDescription.Supplier beanDescRef,
    ValueDeserializer<?> deserializer) {

    return deserializer instanceof BeanDeserializer beanDeserializer ?
      new ExtensibleObjectDeserializer(beanDeserializer) :
      deserializer;
  }

}
