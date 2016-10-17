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

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Metadata about a specific object model.
 *
 * @author Ryan Heaton
 */
@Retention( RetentionPolicy.RUNTIME )
@Target( {} )
public @interface Model {

  /**
   * The id of the model. Used to for things like naming the schema file and assigning an xml prefix.
   *
   * @return The id of the model.
   */
  String id();

  /**
   * The namespace of the model.
   *
   * @return The namespace of the model.
   */
  String namespace();

  /**
   * A label associated with the model, used to identify the model in the user documentation.
   *
   * @return A label associated with the model.
   */
  String label() default "";

  /**
   * A description of the model, used for user documentation.
   *
   * @return A description of the model, used for user documentation.
   */
  String description() default "";

}
