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
package org.gedcomx.rs;

import org.gedcomx.Gedcomx;
import org.gedcomx.rt.rs.ResourceDefinition;
import org.gedcomx.rt.rs.ResponseCode;
import org.gedcomx.rt.rs.StateTransition;
import org.gedcomx.rt.rs.StatusCodes;

import javax.ws.rs.GET;
import javax.ws.rs.core.Response;

/**
 * <p>The person child relationships resource defines a list of relationships to children of a person. The person child relationships
 * resource is to be considered an embedded resource, any links to this resource are to be treated as embedded links. This means
 * that some implementations MAY choose to bind this interface to the containing person.</p>
 */
@ResourceDefinition (
  name = "Person Child Relationships",
  id = PersonChildRelationshipsRSDefinition.REL,
  description = "A set of relationships to children of a person.",
  resourceElement = Gedcomx.class,
  embedded = true,
  transitions = {
    @StateTransition( rel = PersonRSDefinition.REL, description = "The person.", targetResource = PersonRSDefinition.class )
  }
)
public interface PersonChildRelationshipsRSDefinition {

  public static final String REL = Rel.CHILD_RELATIONSHIPS;

  /**
   * Read the set of child relationships for a specific person.
   *
   * @return The set of child relationships.
   */
  @GET
  @StatusCodes({
    @ResponseCode ( code = 200, condition = "Upon a successful read."),
    @ResponseCode ( code = 204, condition = "Upon a successful read and no relationships exist."),
    @ResponseCode ( code = 301, condition = "If the person has been merged into another person."),
    @ResponseCode ( code = 404, condition = "If the person is not found."),
    @ResponseCode ( code = 410, condition = "If the person has been deleted.")
  })
  Response get();
}
