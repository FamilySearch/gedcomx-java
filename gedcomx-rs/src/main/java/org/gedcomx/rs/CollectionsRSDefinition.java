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
import org.gedcomx.records.Collection;
import org.gedcomx.rt.rs.ResourceDefinition;
import org.gedcomx.rt.rs.ResponseCode;
import org.gedcomx.rt.rs.StateTransition;
import org.gedcomx.rt.rs.StatusCodes;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.core.Response;


/**
 * The collections resource defines a set of collections. The collections resource is often used as the entry point for a GEDCOM X application,
 * which provides the list of collections that are available.
 */
@ResourceDefinition (
  name = "Collections",
  id = CollectionsRSDefinition.REL,
  description = "A set of collections.",
  resourceElement = Gedcomx.class,
  transitions = {
    @StateTransition( rel = Rel.COLLECTION, description = "The collection in which these collections are found.", conditional = true, targetResource = CollectionRSDefinition.class ),
    @StateTransition( rel = Rel.SELF, description = "A collection.", scope = Collection.class, conditional = true, targetResource = CollectionRSDefinition.class )
  }
)
public interface CollectionsRSDefinition {

  public static final String REL = Rel.SUBCOLLECTIONS;

  /**
   * Read the collections.
   *
   * @return The list of collections.
   */
  @GET
  @StatusCodes ( {
    @ResponseCode ( code = 200, condition = "Upon a successful read." ),
    @ResponseCode ( code = 204, condition = "Upon a successful read with no results." )
  } )
  Response get();

  /**
   * Create a collection.
   *
   * @param collection The collection to be created.
   * @return The appropriate response.
   */
  @POST
  @StatusCodes ( {
    @ResponseCode ( code = 201, condition = "The creation of the collection was successful. Expect a location header specifying the link to the created collection." ),
    @ResponseCode ( code = 400, condition = "If the request was unable to be understood by the application." ),
    @ResponseCode ( code = 404, condition = "The specified person has been moved, deleted, or otherwise not found." )
  } )
  Response post(Gedcomx collection);

}
