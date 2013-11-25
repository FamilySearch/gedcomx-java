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
import org.gedcomx.common.Note;
import org.gedcomx.conclusion.Person;
import org.gedcomx.conclusion.Relationship;
import org.gedcomx.rt.rs.ResourceDefinition;
import org.gedcomx.rt.rs.ResponseCode;
import org.gedcomx.rt.rs.StateTransition;
import org.gedcomx.rt.rs.StatusCodes;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.core.Response;


/**
 * The note resource defines the interface for a note. The note resource is to be considered an embedded resource, any links to this
 * resource are to be treated as embedded links. This means that some implementations MAY choose to bind this interface
 * to the containing resource (person or relationship).
 */
@ResourceDefinition(
  name = "Note",
  id = NoteRSDefinition.REL,
  description = "A note on a person or relationship.",
  resourceElement = Gedcomx.class,
  embedded = true,
  transitions = {
    @StateTransition( rel = NotesRSDefinition.REL, description = "The list containing the note.", scope = Note.class, conditional = true, targetResource = NotesRSDefinition.class ),
    @StateTransition( rel = PersonRSDefinition.REL, description = "The person to which the note is attached.", scope = Person.class, conditional = true, targetResource = PersonRSDefinition.class ),
    @StateTransition( rel = RelationshipRSDefinition.REL, description = "The relationship to which the note is attached.", scope = Relationship.class, conditional = true, targetResource = RelationshipRSDefinition.class )
  }
)
public interface NoteRSDefinition {

  public static final String REL = Rel.NOTE;

  /**
   * Read a note.
   *
   * @return The note.
   */
  @GET
  @StatusCodes({
      @ResponseCode ( code = 200, condition = "Upon a successful read."),
      @ResponseCode ( code = 404, condition = "If the requested note is not found.")
  })
  Response get();

  /**
   * Update a note.
   *
   * @param note The note to be used for the update.
   * @return The appropriate response.
   */
  @POST
  @StatusCodes({
      @ResponseCode ( code = 204, condition = "The update was successful."),
      @ResponseCode ( code = 404, condition = "If the requested note is not found.")
  })
  Response post( Gedcomx note );

  /**
   * Delete a note.
   *
   * @return The appropriate response.
   */
  @DELETE
  @StatusCodes({
      @ResponseCode ( code = 204, condition = "The delete was successful."),
      @ResponseCode ( code = 404, condition = "If the requested note is not found.")
  })
  Response delete();

}
