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
package org.gedcomx.rs;

import org.gedcomx.Gedcomx;
import org.gedcomx.rt.rs.ResourceDefinition;
import org.gedcomx.rt.rs.ResponseCode;
import org.gedcomx.rt.rs.StateTransition;
import org.gedcomx.rt.rs.StatusCodes;

import javax.ws.rs.GET;
import javax.ws.rs.core.Response;

/**
 * The person relationships resource defines the interface for a set of relationships that reference a specific person. Examples might include
 * the set of relationships to the spouses of a person, the set of relationships to the parents of a person, and the set of relationships
 * to the children of a person. The person relationships resource is to be considered an embedded resource, any links to this
 * resource are to be treated as embedded links. This means that some implementations MAY choose to bind this interface
 * to the containing resource (person or relationship).
 */
@ResourceDefinition (
  name = "Person Relationships",
  id = PersonRelationshipsRSDefinition.REL,
  description = "A set of relationships that reference a specific person, such as the spouse relationships, parent relationships, or child relationships.",
  resourceElement = Gedcomx.class,
  transitions = {
    @StateTransition( rel = PersonRSDefinition.REL, description = "The person.", targetResource = PersonRSDefinition.class )
  }
)
public interface PersonRelationshipsRSDefinition {

  public static final String REL = "person-relationships";
  public static final String SPOUSE_RELATIONSHIPS_REL = "spouse-relationships";
  public static final String PARENT_RELATIONSHIPS_REL = "parent-relationships";
  public static final String CHILD_RELATIONSHIPS_REL = "child-relationships";

  /**
   * Read the set of relationships for a specific person.
   *
   * @return The set of relationships.
   */
  @GET
  @StatusCodes({
    @ResponseCode ( code = 200, condition = "Upon a successful read."),
    @ResponseCode ( code = 404, condition = "If the requested resource is not found.")
  })
  Response get();
}
