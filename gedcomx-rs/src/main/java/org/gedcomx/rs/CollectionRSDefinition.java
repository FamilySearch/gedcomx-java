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
 * The collection resource defines a specific collection.
 */
@ResourceDefinition (
  name = "Collection",
  id = CollectionRSDefinition.REL,
  description = "A collections.",
  resourceElement = Gedcomx.class,
  transitions = {
    @StateTransition( rel = RecordsRSDefinition.REL, description = "The records in this collection.", scope = Collection.class, conditional = true, targetResource = RecordsRSDefinition.class ),
    @StateTransition( rel = PersonsRSDefinition.REL, description = "The persons in this collection.", scope = Collection.class, conditional = true, targetResource = PersonsRSDefinition.class ),
    @StateTransition( rel = RelationshipsRSDefinition.REL, description = "The relationships in this collection.", scope = Collection.class, conditional = true, targetResource = RelationshipsRSDefinition.class ),
    @StateTransition( rel = SourceDescriptionsRSDefinition.REL, description = "The source descriptions in this collection.", scope = Collection.class, conditional = true, targetResource = RelationshipsRSDefinition.class ),
    @StateTransition( rel = CollectionsRSDefinition.REL, description = "The subcollections in this collection.", scope = Collection.class, conditional = true, targetResource = CollectionsRSDefinition.class ),
    @StateTransition( rel = CollectionRSDefinition.REL, description = "The collection in which this collection is found, if any.", scope = Collection.class, conditional = true, targetResource = CollectionRSDefinition.class ),
    @StateTransition( rel = Rel.SELF, description = "Self link to this collection.", scope = Collection.class, conditional = true, targetResource = CollectionRSDefinition.class ),
    @StateTransition( rel = PersonSearchQuery.REL, description = "The query to search this collection by person.", scope = Collection.class, targetResource = PersonSearchQuery.class, template = true ),
    @StateTransition( rel = CurrentUserPersonQuery.REL, description = "The person in this collection that represents the current user.", scope = Collection.class, conditional = true, targetResource = CurrentUserPersonQuery.class )
  }
)
public interface CollectionRSDefinition {

  public static final String REL = Rel.COLLECTION;

  /**
   * Read the collection.
   *
   * @return The collection.
   */
  @GET
  @StatusCodes ( {
    @ResponseCode ( code = 200, condition = "Upon a successful read." ),
    @ResponseCode ( code = 204, condition = "Upon a successful query with no results." ),
    @ResponseCode ( code = 404, condition = "The specified person has been moved, deleted, or otherwise not found." )
  } )
  Response get();

  /**
   * Update a collection.
   *
   * @param collection The collection to be updated.
   * @return The appropriate response.
   */
  @POST
  @StatusCodes ( {
    @ResponseCode ( code = 201, condition = "The update was successful." ),
    @ResponseCode ( code = 400, condition = "If the request was unable to be understood by the application." )
  } )
  Response post(Gedcomx collection);

}
