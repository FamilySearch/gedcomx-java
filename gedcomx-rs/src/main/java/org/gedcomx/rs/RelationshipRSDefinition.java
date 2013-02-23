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
import org.gedcomx.conclusion.Fact;
import org.gedcomx.conclusion.Gender;
import org.gedcomx.conclusion.Name;
import org.gedcomx.conclusion.Relationship;
import org.gedcomx.rt.rs.ResourceDefinition;
import org.gedcomx.rt.rs.ResponseCode;
import org.gedcomx.rt.rs.StateTransition;
import org.gedcomx.rt.rs.StatusCodes;
import org.gedcomx.source.SourceReference;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.POST;
import javax.ws.rs.core.Response;

/**
 * <p>The relationship resource defines the interface for a relationship, including the components of a relationship such as the relationship's facts, source references, and notes.</p>
 *
 * <p>The relationship resource MAY contain links to other resources that are to be considered "embedded", meaning the resources being linked to are to be considered
 * as components of the relationship resource and MUST be resolved and embedded in order to fully resolve all of the components of the relationship.</p>
 */
@ResourceDefinition (
  name = "Relationship",
  id = RelationshipRSDefinition.REL,
  description = "A relationship.",
  resourceElement = Gedcomx.class,
  transitions = {
    @StateTransition ( rel = ConclusionRSDefinition.REL, description = "A conclusion.", scope = { Name.class, Gender.class, Fact.class }, conditional = true, targetResource = ConclusionRSDefinition.class ),
    @StateTransition ( rel = ConclusionsRSDefinition.REL, description = "The conclusions for the relationship (embedded link).", scope = Relationship.class, conditional = true, targetResource = ConclusionsRSDefinition.class ),
    @StateTransition ( rel = SourceReferenceRSDefinition.REL, description = "A source reference.", scope = SourceReference.class, conditional = true, targetResource = SourceReferenceRSDefinition.class ),
    @StateTransition ( rel = SourceReferencesRSDefinition.REL, description = "The source references for the relationship (embedded link).", scope = Relationship.class, conditional = true, targetResource = SourceReferencesRSDefinition.class ),
    @StateTransition ( rel = NoteRSDefinition.REL, description = "A note.", scope = Note.class, conditional = true, targetResource = NoteRSDefinition.class ),
    @StateTransition ( rel = NotesRSDefinition.REL, description = "The notes for the relationship (embedded link).", scope = Relationship.class, conditional = true, targetResource = NotesRSDefinition.class ),
    @StateTransition ( rel = RelationshipRSDefinition.REL_PERSON1, description = "Person 1 in the relationship.", scope = Relationship.class, targetResource = PersonRSDefinition.class ),
    @StateTransition ( rel = RelationshipRSDefinition.REL_PERSON2, description = "Person 2 in the relationship.", scope = Relationship.class, targetResource = PersonRSDefinition.class )
  }
)
public interface RelationshipRSDefinition {

  public static final String REL = "relationship";
  public static final String REL_PERSON1 = "person1";
  public static final String REL_PERSON2 = "person2";

  /**
   * Read a relationship header attributes.
   *
   * @return The header attributes for the relationship.
   */
  @HEAD
  @StatusCodes({
    @ResponseCode ( code = 200, condition = "Upon a successful read."),
    @ResponseCode ( code = 404, condition = "If the requested relationship is not found."),
    @ResponseCode ( code = 410, condition = "If the requested relationship has been deleted.")
  })
  Response head();

  /**
   * Read a relationship.
   *
   * @return The relationship.
   */
  @GET
  @StatusCodes({
    @ResponseCode ( code = 200, condition = "Upon a successful read."),
    @ResponseCode ( code = 404, condition = "If the requested relationship is not found."),
    @ResponseCode ( code = 410, condition = "If the requested relationship has been deleted.")
  })
  Response get();

  /**
   * Update a relationship.
   *
   * @param relationship The relationship to be used for the update.
   *
   */
  @POST
  @StatusCodes({
    @ResponseCode ( code = 204, condition = "The update was successful."),
    @ResponseCode ( code = 404, condition = "If the requested relationship is not found."),
    @ResponseCode ( code = 410, condition = "If the requested relationship has been deleted.")
})
  Response post(Gedcomx relationship);

  /**
   * Delete a relationship.
   *
   */
  @DELETE
  @StatusCodes({
    @ResponseCode ( code = 204, condition = "The delete was successful."),
    @ResponseCode ( code = 404, condition = "If the requested relationship is not found."),
    @ResponseCode ( code = 410, condition = "If the requested relationship has been deleted.")
})
  Response delete();
}
