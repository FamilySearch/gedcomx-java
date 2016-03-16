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
package org.familysearch.api.client.ft;

import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import org.familysearch.api.client.Rel;
import org.familysearch.api.client.util.RequestUtil;
import org.familysearch.platform.FamilySearchPlatform;
import org.familysearch.platform.ordinances.OrdinanceType;
import org.familysearch.platform.reservations.Reservation;
import org.gedcomx.Gedcomx;
import org.gedcomx.common.EvidenceReference;
import org.gedcomx.common.Note;
import org.gedcomx.conclusion.Fact;
import org.gedcomx.conclusion.Relationship;
import org.gedcomx.links.Link;
import org.gedcomx.rs.client.*;
import org.gedcomx.source.SourceReference;

import javax.ws.rs.HttpMethod;
import java.util.Collections;

/**
 * @author Ryan Heaton
 */
public class FamilyTreeRelationshipState extends RelationshipState implements PreferredRelationshipState {

  protected FamilyTreeRelationshipState(ClientRequest request, ClientResponse response, String accessToken, FamilyTreeStateFactory stateFactory) {
    super(request, response, accessToken, stateFactory);
  }

  @Override
  protected FamilyTreeRelationshipState clone(ClientRequest request, ClientResponse response) {
    return new FamilyTreeRelationshipState(request, response, this.accessToken, (FamilyTreeStateFactory) this.stateFactory);
  }

  @Override
  protected FamilySearchPlatform loadEntityConditionally(ClientResponse response) {
    if (HttpMethod.GET.equals(request.getMethod()) && (response.getClientResponseStatus() == ClientResponse.Status.OK
          || response.getClientResponseStatus() == ClientResponse.Status.GONE)
            || response.getClientResponseStatus() == ClientResponse.Status.PRECONDITION_FAILED) {
      return loadEntity(response);
    }
    else {
      return null;
    }
  }

  @Override
  protected FamilySearchPlatform loadEntity(ClientResponse response) {
    return response.getEntity(FamilySearchPlatform.class);
  }

  @Override
  public FamilySearchPlatform getEntity() {
    return (FamilySearchPlatform) super.getEntity();
  }

  @Override
  public FamilyTreeRelationshipState ifSuccessful() {
    return (FamilyTreeRelationshipState) super.ifSuccessful();
  }

  @Override
  public FamilyTreeRelationshipState head(StateTransitionOption... options) {
    return (FamilyTreeRelationshipState) super.head(options);
  }

  @Override
  public FamilyTreeRelationshipState get(StateTransitionOption... options) {
    return (FamilyTreeRelationshipState) super.get(options);
  }

  @Override
  public FamilyTreeRelationshipState delete(StateTransitionOption... options) {
    return (FamilyTreeRelationshipState) super.delete(options);
  }

  @Override
  public FamilyTreeRelationshipState put(Gedcomx e, StateTransitionOption... options) {
    return (FamilyTreeRelationshipState) super.put(e, options);
  }

  @Override
  public FamilyTreeRelationshipState post(Gedcomx entity, StateTransitionOption... options) {
    return (FamilyTreeRelationshipState) super.post(entity, options);
  }

  @Override
  public FamilySearchFamilyTree readCollection(StateTransitionOption... options) {
    return (FamilySearchFamilyTree) super.readCollection(options);
  }

  @Override
  public FamilyTreePersonState readPerson1(StateTransitionOption... options) {
    return (FamilyTreePersonState) super.readPerson1(options);
  }

  @Override
  public FamilyTreePersonState readPerson2(StateTransitionOption... options) {
    return (FamilyTreePersonState) super.readPerson2(options);
  }

  @Override
  protected ClientRequest.Builder createRequestForEmbeddedResource(String rel) {
    if (org.gedcomx.rs.Rel.DISCUSSION_REFERENCES.equals(rel)) {
      return RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest());
    }
    else {
      return super.createRequestForEmbeddedResource(rel);
    }
  }

  @Override
  public FamilyTreeRelationshipState loadEmbeddedResources(StateTransitionOption... options) {
    return (FamilyTreeRelationshipState) super.loadEmbeddedResources(options);
  }

  @Override
  public FamilyTreeRelationshipState loadEmbeddedResources(String[] rels, StateTransitionOption... options) {
    return (FamilyTreeRelationshipState) super.loadEmbeddedResources(rels, options);
  }

  @Override
  public FamilyTreeRelationshipState loadConclusions(StateTransitionOption... options) {
    return (FamilyTreeRelationshipState) super.loadConclusions(options);
  }

  @Override
  public FamilyTreeRelationshipState loadSourceReferences(StateTransitionOption... options) {
    return (FamilyTreeRelationshipState) super.loadSourceReferences(options);
  }

  public FamilyTreeRelationshipState loadDiscussionReferences(StateTransitionOption... options) {
    return (FamilyTreeRelationshipState) super.loadEmbeddedResources(new String[]{org.gedcomx.rs.Rel.DISCUSSION_REFERENCES}, options);
  }

  @Override
  public FamilyTreeRelationshipState loadEvidenceReferences(StateTransitionOption... options) {
    return (FamilyTreeRelationshipState) super.loadEvidenceReferences(options);
  }

  @Override
  public FamilyTreeRelationshipState loadMediaReferences(StateTransitionOption... options) {
    return (FamilyTreeRelationshipState) super.loadMediaReferences(options);
  }

  @Override
  public FamilyTreeRelationshipState loadNotes(StateTransitionOption... options) {
    return (FamilyTreeRelationshipState) super.loadNotes(options);
  }

  @Override
  public FamilyTreeRelationshipState updateFact(Fact fact, StateTransitionOption... options) {
    return (FamilyTreeRelationshipState) super.updateFact(fact, options);
  }

  @Override
  public FamilyTreeRelationshipState updateFacts(Fact[] facts, StateTransitionOption... options) {
    return (FamilyTreeRelationshipState) super.updateFacts(facts, options);
  }

  @Override
  public FamilyTreeRelationshipState updateConclusions(Relationship relationship, StateTransitionOption... options) {
    return (FamilyTreeRelationshipState) super.updateConclusions(relationship, options);
  }

  @Override
  public FamilyTreeRelationshipState deleteFact(Fact fact, StateTransitionOption... options) {
    return (FamilyTreeRelationshipState) super.deleteFact(fact, options);
  }

  @Override
  public FamilyTreeRelationshipState addSourceReference(SourceDescriptionState source, StateTransitionOption... options) {
    return (FamilyTreeRelationshipState) super.addSourceReference(source, options);
  }

  @Override
  public FamilyTreeRelationshipState addSourceReference(SourceReference reference, StateTransitionOption... options) {
    return (FamilyTreeRelationshipState) super.addSourceReference(reference, options);
  }

  @Override
  public FamilyTreeRelationshipState addSourceReferences(SourceReference[] refs, StateTransitionOption... options) {
    return (FamilyTreeRelationshipState) super.addSourceReferences(refs, options);
  }

  @Override
  public FamilyTreeRelationshipState updateSourceReference(SourceReference reference, StateTransitionOption... options) {
    return (FamilyTreeRelationshipState) super.updateSourceReference(reference, options);
  }

  @Override
  public FamilyTreeRelationshipState updateSourceReferences(SourceReference[] refs, StateTransitionOption... options) {
    return (FamilyTreeRelationshipState) super.updateSourceReferences(refs, options);
  }

  @Override
  public FamilyTreeRelationshipState updateSourceReferences(Relationship relationship, StateTransitionOption... options) {
    return (FamilyTreeRelationshipState) super.updateSourceReferences(relationship, options);
  }

  @Override
  public FamilyTreeRelationshipState deleteSourceReference(SourceReference reference, StateTransitionOption... options) {
    return (FamilyTreeRelationshipState) super.deleteSourceReference(reference, options);
  }

  @Override
  public FamilyTreeRelationshipState addMediaReference(SourceDescriptionState description, StateTransitionOption... options) {
    return (FamilyTreeRelationshipState) super.addMediaReference(description, options);
  }

  @Override
  public FamilyTreeRelationshipState addMediaReference(SourceReference reference, StateTransitionOption... options) {
    return (FamilyTreeRelationshipState) super.addMediaReference(reference, options);
  }

  @Override
  public FamilyTreeRelationshipState addMediaReferences(SourceReference[] refs, StateTransitionOption... options) {
    return (FamilyTreeRelationshipState) super.addMediaReferences(refs, options);
  }

  @Override
  public FamilyTreeRelationshipState updateMediaReference(SourceReference reference, StateTransitionOption... options) {
    return (FamilyTreeRelationshipState) super.updateMediaReference(reference, options);
  }

  @Override
  public FamilyTreeRelationshipState updateMediaReferences(SourceReference[] refs, StateTransitionOption... options) {
    return (FamilyTreeRelationshipState) super.updateMediaReferences(refs, options);
  }

  @Override
  public FamilyTreeRelationshipState updateMediaReferences(Relationship relationship, StateTransitionOption... options) {
    return (FamilyTreeRelationshipState) super.updateMediaReferences(relationship, options);
  }

  @Override
  public FamilyTreeRelationshipState deleteMediaReference(SourceReference reference, StateTransitionOption... options) {
    return (FamilyTreeRelationshipState) super.deleteMediaReference(reference, options);
  }

  @Override
  public FamilyTreeRelationshipState addEvidenceReference(RelationshipState evidence, StateTransitionOption... options) {
    return (FamilyTreeRelationshipState) super.addEvidenceReference(evidence, options);
  }

  @Override
  public FamilyTreeRelationshipState addEvidenceReference(EvidenceReference reference, StateTransitionOption... options) {
    return (FamilyTreeRelationshipState) super.addEvidenceReference(reference, options);
  }

  @Override
  public FamilyTreeRelationshipState addEvidenceReferences(EvidenceReference[] refs, StateTransitionOption... options) {
    return (FamilyTreeRelationshipState) super.addEvidenceReferences(refs, options);
  }

  @Override
  public FamilyTreeRelationshipState updateEvidenceReference(EvidenceReference reference, StateTransitionOption... options) {
    return (FamilyTreeRelationshipState) super.updateEvidenceReference(reference, options);
  }

  @Override
  public FamilyTreeRelationshipState updateEvidenceReferences(EvidenceReference[] refs, StateTransitionOption... options) {
    return (FamilyTreeRelationshipState) super.updateEvidenceReferences(refs, options);
  }

  @Override
  public FamilyTreeRelationshipState updateEvidenceReferences(Relationship relationship, StateTransitionOption... options) {
    return (FamilyTreeRelationshipState) super.updateEvidenceReferences(relationship, options);
  }

  @Override
  public FamilyTreeRelationshipState deleteEvidenceReference(EvidenceReference reference, StateTransitionOption... options) {
    return (FamilyTreeRelationshipState) super.deleteEvidenceReference(reference, options);
  }

  @Override
  public FamilyTreeRelationshipState readNote(Note note, StateTransitionOption... options) {
    return (FamilyTreeRelationshipState) super.readNote(note, options);
  }

  @Override
  public FamilyTreeRelationshipState addNote(Note note, StateTransitionOption... options) {
    return (FamilyTreeRelationshipState) super.addNote(note, options);
  }

  @Override
  public FamilyTreeRelationshipState addNotes(Note[] notes, StateTransitionOption... options) {
    return (FamilyTreeRelationshipState) super.addNotes(notes, options);
  }

  @Override
  public FamilyTreeRelationshipState updateNote(Note note, StateTransitionOption... options) {
    return (FamilyTreeRelationshipState) super.updateNote(note, options);
  }

  @Override
  public FamilyTreeRelationshipState updateNotes(Relationship relationship, StateTransitionOption... options) {
    return (FamilyTreeRelationshipState) super.updateNotes(relationship, options);
  }

  @Override
  public FamilyTreeRelationshipState updateNotes(Note[] notes, StateTransitionOption... options) {
    return (FamilyTreeRelationshipState) super.updateNotes(notes, options);
  }

  @Override
  public FamilyTreeRelationshipState deleteNote(Note note, StateTransitionOption... options) {
    return (FamilyTreeRelationshipState) super.deleteNote(note, options);
  }

  public ChangeHistoryState readChangeHistory(StateTransitionOption... options) {
    Link link = getLink(Rel.CHANGE_HISTORY);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedFeedRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return ((FamilyTreeStateFactory)this.stateFactory).newChangeHistoryState(request, invoke(request, options), this.accessToken);
  }

  public FamilyTreeRelationshipState restore(StateTransitionOption... options) {
    Link link = getLink(Rel.RESTORE);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest()).build(link.getHref().toURI(), HttpMethod.POST);
    return ((FamilyTreeStateFactory)this.stateFactory).newRelationshipState(request, invoke(request, options), this.accessToken);
  }

  public OrdinanceReservationsState reserveOrdinance(StateTransitionOption... options) {
    Link link = getLink(Rel.RESERVATION);
    if (link == null || link.getHref() == null) {
      return null;
    }

    FamilySearchPlatform entity = new FamilySearchPlatform();
    Reservation reservation = new Reservation();
    reservation.setPerson(getRelationship().getPerson1());
    reservation.setSpouse(getRelationship().getPerson2());
    reservation.setKnownOrdinanceType(OrdinanceType.Sealing_To_Spouse);
    entity.setReservations(Collections.singletonList(reservation));
    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest()).entity(entity).build(link.getHref().toURI(), HttpMethod.POST);
    return ((FamilyTreeStateFactory)this.stateFactory).newOrdinanceReservationsState(request, invoke(request, options), this.accessToken);
  }

  public OrdinanceReservationsState readReservation(StateTransitionOption... options) {
    Link link = getLink(Rel.RESERVATION);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest()).build(link.getHref().toURI(), HttpMethod.GET);
    return ((FamilyTreeStateFactory)this.stateFactory).newOrdinanceReservationsState(request, invoke(request, options), this.accessToken);
  }

  public OrdinanceStatusState readOrdinanceStatus(StateTransitionOption... options) {
    Link link = getLink(Rel.ORDINANCE_STATUS);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest()).build(link.getHref().toURI(), HttpMethod.GET);
    return ((FamilyTreeStateFactory)this.stateFactory).newOrdinanceStatusState(request, invoke(request, options), this.accessToken);
  }

}
