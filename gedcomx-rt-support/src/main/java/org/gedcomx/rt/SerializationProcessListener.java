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
package org.gedcomx.rt;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.xml.bind.JAXBContext;

/**
 * @author Ryan Heaton
 */
public interface SerializationProcessListener {

  /**
   * Some xml was processed.
   *
   * @param reference The object that was being processed.
   * @param instanceClass The class.
   * @param context The context.
   * @param xml The xml.
   */
  void xmlProcessed(Object reference, Class<?> instanceClass, JAXBContext context, String xml);

  /**
   * Some xml was processed.
   *
   * @param reference The object that was being processed.
   * @param instanceClass The class.
   * @param mapper The mapper.
   * @param json The json.
   */
  void jsonProcessed(Object reference, Class<?> instanceClass, ObjectMapper mapper, String json);
}
