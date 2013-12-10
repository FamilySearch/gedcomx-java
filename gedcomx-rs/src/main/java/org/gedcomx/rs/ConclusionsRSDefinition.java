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
import org.gedcomx.conclusion.*;
import org.gedcomx.rt.rs.ResourceDefinition;
import org.gedcomx.rt.rs.ResponseCode;
import org.gedcomx.rt.rs.StateTransition;
import org.gedcomx.rt.rs.StatusCodes;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.core.Response;

/**
 * The conclusions resource defines the interface for a list of conclusions that are contained by a specific resource,
 * such as a person or relationship. The conclusions resource is to be considered an embedded resource, any links to this
 * resource are to be treated as embedded links. This means that some implementations MAY choose to bind this interface
 * to the containing resource (person or relationship).
 *
 * @author Ryan Heaton
 */
@ResourceDefinition (
  name = "Conclusions",
  id = ConclusionsRSDefinition.REL,
  description = "The set of conclusions about a person or relationship",
  resourceElement = Gedcomx.class,
  embedded = true,
  transitions = {
    @StateTransition( rel = ConclusionRSDefinition.REL, description = "A conclusion.", scope = { Name.class, Gender.class, Fact.class }, conditional = true, targetResource = ConclusionRSDefinition.class ),
    @StateTransition( rel = PersonRSDefinition.REL, description = "The person containing this set of conclusions.", scope = Person.class, conditional = true, targetResource = PersonRSDefinition.class ),
    @StateTransition( rel = RelationshipRSDefinition.REL, description = "The relationship containing this set of conclusions.", scope = Relationship.class, conditional = true, targetResource = RelationshipRSDefinition.class )
  }
)
public interface ConclusionsRSDefinition {

  public static final String REL = Rel.CONCLUSIONS;

  /**
   * Read a conclusions of an entity.
   *
   * @return The conclusions.
   */
  @GET
  @StatusCodes ({
    @ResponseCode ( code = 200, condition = "Upon a successful read."),
    @ResponseCode ( code = 301, condition = "If the specified person has been merged to another person."),
    @ResponseCode ( code = 204, condition = "Upon a successful query with no results."),
    @ResponseCode ( code = 404, condition = "The specified entity has been moved, deleted, or otherwise not found."),
    @ResponseCode ( code = 410, condition = "If the specified person has been deleted.")
})
  Response get();

  /**
   * Create or update conclusions.
   *
   * @param conclusions The conclusions to be created or updated.
   * @return The appropriate response.
   */
  @POST
  @StatusCodes({
    @ResponseCode ( code = 201, condition = "The creation of the conclusions was successful."),
    @ResponseCode ( code = 301, condition = "If the specified person has been merged to another person."),
    @ResponseCode ( code = 410, condition = "If the specified person has been deleted.")
  })
  Response post(Gedcomx conclusions);
}
