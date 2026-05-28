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
import tools.jackson.databind.SerializationConfig;
import tools.jackson.databind.ValueSerializer;
import tools.jackson.databind.ser.BeanSerializer;
import tools.jackson.databind.ser.UnrolledBeanSerializer;
import tools.jackson.databind.ser.ValueSerializerModifier;
import tools.jackson.databind.ser.bean.BeanSerializerBase;

/**
 * Modifications for GEDCOM value serializers.
 *
 * @author Ryan Heaton
 */
public class GedcomValueSerializerModifier extends ValueSerializerModifier {

  @Override
  public ValueSerializer<?> modifySerializer(
    SerializationConfig config,
    BeanDescription.Supplier beanDescRef,
    ValueSerializer<?> serializer) {

    if (serializer instanceof UnrolledBeanSerializer) {
      serializer = new PublicBeanSerializer((BeanSerializerBase) serializer);
    }

    return serializer instanceof BeanSerializer beanSerializer ?
      new ExtensibleObjectSerializer(beanSerializer) :
      serializer;
  }

  /**
   * Public wrapper around BeanSerializer to expose the protected constructor.
   *
   * BeanSerializer has a protected constructor that accepts BeanSerializerBase,
   * which is needed to replace UnrolledBeanSerializer. This subclass makes that
   * constructor accessible.
   */
  private static class PublicBeanSerializer extends BeanSerializer {
    /**
     * Constructs a BeanSerializer from an existing BeanSerializerBase.
     * <p>
     * This constructor delegates to the protected BeanSerializer(BeanSerializerBase)
     * constructor, allowing us to convert UnrolledBeanSerializer instances to
     * standard BeanSerializer instances.
     *
     * @param src the source serializer (typically an UnrolledBeanSerializer)
     */
    public PublicBeanSerializer(BeanSerializerBase src) {
      super(src);
    }
  }

}