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
import org.gedcomx.conclusion.Person;
import org.gedcomx.rt.rs.ResourceDefinition;
import org.gedcomx.rt.rs.ResponseCode;
import org.gedcomx.rt.rs.StateTransition;
import org.gedcomx.rt.rs.StatusCodes;

import javax.ws.rs.GET;
import javax.ws.rs.core.Response;

/**
 * <p>The person spouses resource defines the set of spouses for a person. This includes the spouses, the relationships to each spouse, and
 * the spouse relationships of the spouses.</p>
 */
@ResourceDefinition (
  name = "Person Spouses",
  id = "person-spouses",
  description = "The set of spouses for a person.",
  resourceElement = Gedcomx.class,
  transitions = {
    @StateTransition( rel = PersonRSDefinition.REL, description = "The person.", targetResource = PersonRSDefinition.class ),
    @StateTransition( rel = RelationshipRSDefinition.REL, description = "The relationship.", targetResource = RelationshipRSDefinition.class ),
    @StateTransition( rel = PersonRSDefinition.REL, description = "The spouse.", scope = Person.class, targetResource = PersonRSDefinition.class )
  }
)
public interface PersonSpousesRSDefinition {

  public static final String REL = Rel.SPOUSES;

  /**
   * Read the set of relationships for a specific person.
   *
   * @return The set of relationships.
   */
  @GET
  @StatusCodes({
    @ResponseCode ( code = 200, condition = "Upon a successful read."),
    @ResponseCode ( code = 204, condition = "Upon a successful read and no relationships exist."),
    @ResponseCode ( code = 404, condition = "If the requested resource is not found.")
  })
  Response get();
}
