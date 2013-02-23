/**
 * Copyright 2011-2012 Intellectual Reserve, Inc.
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
 * Metadata used to describe a resource and the way the HTTP operations apply to the resource. A resource
 * definition does not dictate the paths at which the resources are bound, but describes the resource in generic
 * terms such as the representation model, {@link StatusCodes status codes}, {@link Warnings warnings}, and
 * {@link StateTransition resource relationship}s.
 *
 * @author Ryan Heaton
 */
@Retention ( RetentionPolicy.RUNTIME )
@Target ({ ElementType.TYPE })
public @interface ResourceDefinition {

  /**
   * A simple id for the resource definition. This idea, appended to the namespace provides the (full) identifier for the resource definition and associated
   * profile.
   *
   * @return The simple, relative id for the resource definition.
   */
  String id();

  /**
   * A name for this resource definition.
   *
   * @return A name for this resource definition.
   */
  String name();

  /**
   * A human-readable description of this resource.
   *
   * @return A human-readable description of this resource.
   */
  String description();

  /**
   * The class defining the element used as the representation model for the resource. The
   * resource element should be a JAXB {@link javax.xml.bind.annotation.XmlRootElement}.
   *
   * @return The class defining the element used as the representation model for the resource.
   */
  Class<?>[] resourceElement() default {};

  /**
   * The transitions to other resource definitions.
   *
   * @return The transitions to other resource definitions.
   */
  StateTransition[] transitions() default {};

  /**
   * Whether this resource is to be considered an embedded resource, within the context of another resource.
   *
   * @return Whether this resource is to be considered an embedded resource, within the context of another resource.
   */
  boolean embedded() default false;

}
