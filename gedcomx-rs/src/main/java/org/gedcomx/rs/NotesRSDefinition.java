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
import org.gedcomx.common.Note;
import org.gedcomx.conclusion.Person;
import org.gedcomx.conclusion.Relationship;
import org.gedcomx.rt.rs.ResourceDefinition;
import org.gedcomx.rt.rs.ResponseCode;
import org.gedcomx.rt.rs.StateTransition;
import org.gedcomx.rt.rs.StatusCodes;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.core.Response;

/**
 * The notes resource defines the interface for a list of notes on an entity (e.g. person, relationship). The notes resource is to be considered an
 * embedded resource, any links to this resource are to be treated as embedded links. This means that some implementations MAY choose to bind this interface
 * to the containing resource (person or relationship).
 */
@ResourceDefinition (
  name = "Notes",
  id = NotesRSDefinition.REL,
  description = "The set of notes attached to a person or relationship.",
  resourceElement = Gedcomx.class,
  embedded = true,
  transitions = {
    @StateTransition( rel = NoteRSDefinition.REL, description = "A note.", scope = Note.class, conditional = true, targetResource = NoteRSDefinition.class ),
    @StateTransition( rel = PersonRSDefinition.REL, description = "The person.", scope = Person.class, conditional = true, targetResource = PersonRSDefinition.class ),
    @StateTransition( rel = RelationshipRSDefinition.REL, description = "The relationship.", scope = Relationship.class, conditional = true, targetResource = RelationshipRSDefinition.class )
  }
)
public interface NotesRSDefinition {

  public static final String REL = Rel.NOTES;

  /**
   * Read the list of notes on the entity.
   *
   * @return The list of notes.
   */
  @GET
  @StatusCodes ({
    @ResponseCode ( code = 200, condition = "Upon a successful read."),
    @ResponseCode( code = 204, condition = "Upon a successful query with no results."),
    @ResponseCode( code = 404, condition = "The specified entity has been moved, deleted, or otherwise not found.")
  })
  Response get();

  /**
   * Create a note.
   *
   * @param note The note to be created.
   * @return The appropriate response.
   */
  @POST
  @StatusCodes({
    @ResponseCode( code = 201, condition = "The creation of the note was successful. Expect a location header specifying the link to the note."),
    @ResponseCode( code = 400, condition = "If the request was unable to be understood by the application.")
  })
  Response post( Gedcomx note );

}
