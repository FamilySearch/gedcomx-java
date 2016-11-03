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
package org.gedcomx.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.webcohesion.enunciate.metadata.Ignore;

import java.util.List;
import java.util.Map;

/**
 * Class is used for documentation purposes only.
 *
 * @author Ryan Heaton
 */
@Ignore
abstract class HasIdentifiersMixin {

  /**
   * The list of identifiers for the agent.
   *
   * @return The list of identifiers for the agent.
   */
  @JsonProperty ("identifiers")
  public abstract Map<String, List<String>> getIdentifiers();

  /**
   * The list of identifiers of the agent.
   *
   * @param identifiers The list of identifiers of the agent.
   */
  @JsonProperty ("identifiers")
  public abstract void setIdentifiers(Map<String, List<String>> identifiers);

}
