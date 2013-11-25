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
import org.gedcomx.conclusion.Person;
import org.gedcomx.conclusion.Relationship;
import org.gedcomx.rt.rs.ResourceDefinition;
import org.gedcomx.rt.rs.ResponseCode;
import org.gedcomx.rt.rs.StateTransition;
import org.gedcomx.rt.rs.StatusCodes;
import org.gedcomx.source.SourceReference;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.core.Response;


/**
 * The source references resource defines a collection of source references on an entity (e.g. person, relationship). The source references
 * resource is to be considered an embedded resource, any links to this resource are to be treated as embedded links. This means that some
 * implementations MAY choose to bind this interface to the containing resource (person or relationship).
 */
@ResourceDefinition (
  name = "Source References",
  id = SourceReferencesRSDefinition.REL,
  description = "A set of source references on a conclusion.",
  resourceElement = Gedcomx.class,
  embedded = true,
  transitions = {
    @StateTransition( rel = SourceReferenceRSDefinition.REL, description = "A source reference.", scope = SourceReference.class, conditional = true, targetResource = SourceReferenceRSDefinition.class ),
    @StateTransition( rel = PersonRSDefinition.REL, description = "The person.", scope = Person.class, conditional = true, targetResource = PersonRSDefinition.class ),
    @StateTransition( rel = RelationshipRSDefinition.REL, description = "The relationship.", scope = Relationship.class, conditional = true, targetResource = RelationshipRSDefinition.class )
  }
)
public interface SourceReferencesRSDefinition {

  public static final String REL = Rel.SOURCE_REFERENCES;

  /**
   * Read the references to sources.
   *
   * @return The list of source references.
   */
  @GET
  @StatusCodes ( {
    @ResponseCode ( code = 200, condition = "Upon a successful read." ),
    @ResponseCode ( code = 204, condition = "Upon a successful query with no results." ),
    @ResponseCode ( code = 404, condition = "The specified person has been moved, deleted, or otherwise not found." )
  } )
  Response get();

  /**
   * Create a source reference.
   *
   * @param sourceReference The source reference to be created.
   * @return The appropriate response.
   */
  @POST
  @StatusCodes ( {
    @ResponseCode ( code = 201, condition = "The creation of the source reference was successful. Expect a location header specifying the link to the created source reference." ),
    @ResponseCode ( code = 400, condition = "If the request was unable to be understood by the application." ),
    @ResponseCode ( code = 404, condition = "The specified person has been moved, deleted, or otherwise not found." )
  } )
  Response post(Gedcomx sourceReference);

}
