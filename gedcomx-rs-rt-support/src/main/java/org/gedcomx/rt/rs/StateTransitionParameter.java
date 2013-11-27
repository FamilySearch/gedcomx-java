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
package org.gedcomx.rt.rs;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Metadata used to describe a parameter when building a state transition, applicable to e.g. path parameters and query parameters.
 *
 * @author Ryan Heaton
 */
@Retention ( RetentionPolicy.RUNTIME )
@Target({ElementType.PARAMETER, ElementType.METHOD, ElementType.FIELD})
public @interface StateTransitionParameter {

  /**
   * The name of the transition parameter. Default value is the name of the path or query parameter.
   *
   * @return The name of the transition parameter.
   */
  String name() default "##default";

  /**
   * Whether this transition parameter is optional when creating a state transition.
   *
   * @return Whether this transition parameter is optional when creating a state transition.
   */
  boolean optional() default true;

}
