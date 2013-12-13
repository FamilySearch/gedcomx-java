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
import org.gedcomx.common.EvidenceReference;
import org.gedcomx.common.Note;
import org.gedcomx.conclusion.*;
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
 * <p>The person resource defines the interface for a person, including the components of a person such as the person's names, gender, facts, source references, and notes.
 * The person resource also includes the relationships in which the person is a member, such as the relationships to parents, spouses, and children.</p>
 *
 * <p>The person resource MAY contain links to other resources that are to be considered "embedded", meaning the resources being linked to are to be considered
 * as components of the person resource and MUST be resolved and embedded in order to fully resolve all of the components of the person.</p>
 *
 * @author Ryan Heaton
 */
@ResourceDefinition (
  name = "Person",
  id = PersonRSDefinition.REL,
  description = "A person.",
  resourceElement = Gedcomx.class,
  transitions = {
    @StateTransition ( rel = AncestryQuery.REL, description = "The ancestry query for this person.", scope = Person.class, conditional = true, targetResource = AncestryQuery.class ),
    @StateTransition ( rel = DescendancyQuery.REL, description = "The descendancy query for this person.", scope = Person.class, conditional = true, targetResource = DescendancyQuery.class ),
    @StateTransition ( rel = CollectionRSDefinition.REL, description = "The collection containing this person.", scope = Person.class, conditional = true, targetResource = CollectionRSDefinition.class ),
    @StateTransition ( rel = ConclusionRSDefinition.REL, description = "A conclusion.", scope = { Name.class, Gender.class, Fact.class }, conditional = true, targetResource = ConclusionRSDefinition.class ),
    @StateTransition ( rel = ConclusionsRSDefinition.REL, description = "The conclusions for the person (embedded link).", scope = Person.class, conditional = true, targetResource = ConclusionsRSDefinition.class ),
    @StateTransition ( rel = SourceReferencesRSDefinition.REL, description = "The source references for the person (embedded link).", scope = Person.class, conditional = true, targetResource = SourceReferencesRSDefinition.class ),
    @StateTransition ( rel = SourceReferenceRSDefinition.REL, description = "A source reference.", scope = SourceReference.class, conditional = true, targetResource = SourceReferenceRSDefinition.class ),
    @StateTransition ( rel = EvidenceReferencesRSDefinition.REL, description = "The evidence references for the person (embedded link).", scope = Person.class, conditional = true, targetResource = EvidenceReferencesRSDefinition.class ),
    @StateTransition ( rel = EvidenceReferenceRSDefinition.REL, description = "A evidence reference.", scope = EvidenceReference.class, conditional = true, targetResource = EvidenceReferenceRSDefinition.class ),
    @StateTransition ( rel = MediaReferencesRSDefinition.REL, description = "The media references for the person (embedded link).", scope = Person.class, conditional = true, targetResource = MediaReferencesRSDefinition.class ),
    @StateTransition ( rel = MediaReferenceRSDefinition.REL, description = "A media reference.", scope = SourceReference.class, conditional = true, targetResource = MediaReferenceRSDefinition.class ),
    @StateTransition ( rel = NotesRSDefinition.REL, description = "The notes for the person (embedded link).", scope = Person.class, conditional = true, targetResource = NotesRSDefinition.class ),
    @StateTransition ( rel = NoteRSDefinition.REL, description = "A note.", scope = Note.class, conditional = true, targetResource = NoteRSDefinition.class ),
    @StateTransition ( rel = RelationshipRSDefinition.REL, description = "A relationship.", scope = Relationship.class, targetResource = RelationshipRSDefinition.class ),
    @StateTransition ( rel = PersonParentRelationshipsRSDefinition.REL, description = "The relationships to the parents of the person (embedded link).", scope = Person.class, conditional = true, targetResource = PersonParentRelationshipsRSDefinition.class ),
    @StateTransition ( rel = PersonChildRelationshipsRSDefinition.REL, description = "The relationships to the children of the person (embedded link).", scope = Person.class, conditional = true, targetResource = PersonChildRelationshipsRSDefinition.class ),
    @StateTransition ( rel = PersonSpouseRelationshipsRSDefinition.REL, description = "The relationships to the spouses of the person (embedded link).", scope = Person.class, conditional = true, targetResource = PersonSpouseRelationshipsRSDefinition.class ),
    @StateTransition ( rel = PersonParentsRSDefinition.REL, description = "The parents of this person.", scope = Person.class, conditional = true, targetResource = PersonParentsRSDefinition.class ),
    @StateTransition ( rel = PersonChildrenRSDefinition.REL, description = "The children of this person.", scope = Person.class, conditional = true, targetResource = PersonChildrenRSDefinition.class ),
    @StateTransition ( rel = PersonSpousesRSDefinition.REL, description = "The spouses of this person.", scope = Person.class, conditional = true, targetResource = PersonSpousesRSDefinition.class )
 }
)
public interface PersonRSDefinition {

  public static final String REL = Rel.PERSON;

  /**
   * Read a person header attributes.
   *
   * @return The header attributes for the person.
   */
  @HEAD
  @StatusCodes({
    @ResponseCode ( code = 200, condition = "Upon a successful read."),
    @ResponseCode ( code = 301, condition = "If the requested person has been merged to another person."),
    @ResponseCode ( code = 404, condition = "If the requested person is not found."),
    @ResponseCode ( code = 410, condition = "If the requested person has been deleted.")
  })
  Response head();

  /**
   * Read a person.
   *
   * @return The person.
   */
  @GET
  @StatusCodes({
    @ResponseCode ( code = 200, condition = "Upon a successful read."),
    @ResponseCode ( code = 301, condition = "If the requested person has been merged into another person."),
    @ResponseCode ( code = 404, condition = "If the requested person is not found."),
    @ResponseCode ( code = 410, condition = "If the requested person has been deleted.")
  })
  Response get();

  /**
   * Update a person.
   *
   * @param person The person to be used for the update.
   *
   */
  @POST
  @StatusCodes({
    @ResponseCode ( code = 204, condition = "The update was successful."),
    @ResponseCode ( code = 301, condition = "If the requested person has been merged into another person."),
    @ResponseCode ( code = 404, condition = "If the requested person is not found."),
    @ResponseCode ( code = 410, condition = "If the requested person has been deleted.")
  })
  Response post(Gedcomx person);

  /**
   * Delete a person.
   *
   */
  @DELETE
  @StatusCodes({
    @ResponseCode ( code = 204, condition = "The delete was successful."),
    @ResponseCode ( code = 301, condition = "If the requested person has been merged into another person."),
    @ResponseCode ( code = 404, condition = "If the requested person is not found."),
    @ResponseCode ( code = 410, condition = "If the requested person has already been deleted.")
  })
  Response delete();

}
