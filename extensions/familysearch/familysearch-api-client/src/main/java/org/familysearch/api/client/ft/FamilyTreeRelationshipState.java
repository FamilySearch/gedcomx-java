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
import org.familysearch.platform.FamilySearchPlatform;
import org.gedcomx.Gedcomx;
import org.gedcomx.common.EvidenceReference;
import org.gedcomx.common.Note;
import org.gedcomx.conclusion.Fact;
import org.gedcomx.conclusion.Relationship;
import org.gedcomx.links.Link;
import org.gedcomx.rs.client.RelationshipState;
import org.gedcomx.rs.client.SourceDescriptionState;
import org.gedcomx.source.SourceReference;

import javax.ws.rs.HttpMethod;

/**
 * @author Ryan Heaton
 */
public class FamilyTreeRelationshipState extends RelationshipState {

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
          || response.getClientResponseStatus() == ClientResponse.Status.GONE)) {
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
  public FamilyTreeRelationshipState head() {
    return (FamilyTreeRelationshipState) super.head();
  }

  @Override
  public FamilyTreeRelationshipState get() {
    return (FamilyTreeRelationshipState) super.get();
  }

  @Override
  public FamilyTreeRelationshipState delete() {
    return (FamilyTreeRelationshipState) super.delete();
  }

  @Override
  public FamilyTreeRelationshipState put(Gedcomx e) {
    return (FamilyTreeRelationshipState) super.put(e);
  }

  @Override
  public FamilyTreeCollectionState readCollection() {
    return (FamilyTreeCollectionState) super.readCollection();
  }

  @Override
  public FamilyTreeRelationshipState loadEmbeddedResources() {
    return (FamilyTreeRelationshipState) super.loadEmbeddedResources();
  }

  @Override
  public FamilyTreeRelationshipState loadEmbeddedResources(String... rels) {
    return (FamilyTreeRelationshipState) super.loadEmbeddedResources(rels);
  }

  @Override
  public FamilyTreeRelationshipState loadConclusions() {
    return (FamilyTreeRelationshipState) super.loadConclusions();
  }

  @Override
  public FamilyTreeRelationshipState loadSourceReferences() {
    return (FamilyTreeRelationshipState) super.loadSourceReferences();
  }

  @Override
  public FamilyTreeRelationshipState loadEvidenceReferences() {
    return (FamilyTreeRelationshipState) super.loadEvidenceReferences();
  }

  @Override
  public FamilyTreeRelationshipState loadMediaReferences() {
    return (FamilyTreeRelationshipState) super.loadMediaReferences();
  }

  @Override
  public FamilyTreeRelationshipState loadNotes() {
    return (FamilyTreeRelationshipState) super.loadNotes();
  }

  @Override
  public FamilyTreeRelationshipState updateFact(Fact fact) {
    return (FamilyTreeRelationshipState) super.updateFact(fact);
  }

  @Override
  public FamilyTreeRelationshipState updateFacts(Fact... facts) {
    return (FamilyTreeRelationshipState) super.updateFacts(facts);
  }

  @Override
  public FamilyTreeRelationshipState updateConclusions(Relationship relationship) {
    return (FamilyTreeRelationshipState) super.updateConclusions(relationship);
  }

  @Override
  public FamilyTreeRelationshipState deleteFact(Fact fact) {
    return (FamilyTreeRelationshipState) super.deleteFact(fact);
  }

  @Override
  public FamilyTreeRelationshipState addSourceReference(SourceDescriptionState source) {
    return (FamilyTreeRelationshipState) super.addSourceReference(source);
  }

  @Override
  public FamilyTreeRelationshipState addSourceReference(SourceReference reference) {
    return (FamilyTreeRelationshipState) super.addSourceReference(reference);
  }

  @Override
  public FamilyTreeRelationshipState addSourceReferences(SourceReference... refs) {
    return (FamilyTreeRelationshipState) super.addSourceReferences(refs);
  }

  @Override
  public FamilyTreeRelationshipState updateSourceReference(SourceReference reference) {
    return (FamilyTreeRelationshipState) super.updateSourceReference(reference);
  }

  @Override
  public FamilyTreeRelationshipState updateSourceReferences(SourceReference... refs) {
    return (FamilyTreeRelationshipState) super.updateSourceReferences(refs);
  }

  @Override
  public FamilyTreeRelationshipState updateSourceReferences(Relationship relationship) {
    return (FamilyTreeRelationshipState) super.updateSourceReferences(relationship);
  }

  @Override
  public FamilyTreeRelationshipState deleteSourceReference(SourceReference reference) {
    return (FamilyTreeRelationshipState) super.deleteSourceReference(reference);
  }

  @Override
  public FamilyTreeRelationshipState addMediaReference(SourceDescriptionState description) {
    return (FamilyTreeRelationshipState) super.addMediaReference(description);
  }

  @Override
  public FamilyTreeRelationshipState addMediaReference(SourceReference reference) {
    return (FamilyTreeRelationshipState) super.addMediaReference(reference);
  }

  @Override
  public FamilyTreeRelationshipState addMediaReferences(SourceReference... refs) {
    return (FamilyTreeRelationshipState) super.addMediaReferences(refs);
  }

  @Override
  public FamilyTreeRelationshipState updateMediaReference(SourceReference reference) {
    return (FamilyTreeRelationshipState) super.updateMediaReference(reference);
  }

  @Override
  public FamilyTreeRelationshipState updateMediaReferences(SourceReference... refs) {
    return (FamilyTreeRelationshipState) super.updateMediaReferences(refs);
  }

  @Override
  public FamilyTreeRelationshipState updateMediaReferences(Relationship relationship) {
    return (FamilyTreeRelationshipState) super.updateMediaReferences(relationship);
  }

  @Override
  public FamilyTreeRelationshipState deleteMediaReference(SourceReference reference) {
    return (FamilyTreeRelationshipState) super.deleteMediaReference(reference);
  }

  @Override
  public FamilyTreeRelationshipState addEvidenceReference(RelationshipState evidence) {
    return (FamilyTreeRelationshipState) super.addEvidenceReference(evidence);
  }

  @Override
  public FamilyTreeRelationshipState addEvidenceReference(EvidenceReference reference) {
    return (FamilyTreeRelationshipState) super.addEvidenceReference(reference);
  }

  @Override
  public FamilyTreeRelationshipState addEvidenceReferences(EvidenceReference... refs) {
    return (FamilyTreeRelationshipState) super.addEvidenceReferences(refs);
  }

  @Override
  public FamilyTreeRelationshipState updateEvidenceReference(EvidenceReference reference) {
    return (FamilyTreeRelationshipState) super.updateEvidenceReference(reference);
  }

  @Override
  public FamilyTreeRelationshipState updateEvidenceReferences(EvidenceReference... refs) {
    return (FamilyTreeRelationshipState) super.updateEvidenceReferences(refs);
  }

  @Override
  public FamilyTreeRelationshipState updateEvidenceReferences(Relationship relationship) {
    return (FamilyTreeRelationshipState) super.updateEvidenceReferences(relationship);
  }

  @Override
  public FamilyTreeRelationshipState deleteEvidenceReference(EvidenceReference reference) {
    return (FamilyTreeRelationshipState) super.deleteEvidenceReference(reference);
  }

  @Override
  public FamilyTreeRelationshipState readNote(Note note) {
    return (FamilyTreeRelationshipState) super.readNote(note);
  }

  @Override
  public FamilyTreeRelationshipState addNote(Note note) {
    return (FamilyTreeRelationshipState) super.addNote(note);
  }

  @Override
  public FamilyTreeRelationshipState addNotes(Note... notes) {
    return (FamilyTreeRelationshipState) super.addNotes(notes);
  }

  @Override
  public FamilyTreeRelationshipState updateNote(Note note) {
    return (FamilyTreeRelationshipState) super.updateNote(note);
  }

  @Override
  public FamilyTreeRelationshipState updateNotes(Relationship relationship) {
    return (FamilyTreeRelationshipState) super.updateNotes(relationship);
  }

  @Override
  public FamilyTreeRelationshipState updateNotes(Note... notes) {
    return (FamilyTreeRelationshipState) super.updateNotes(notes);
  }

  @Override
  public FamilyTreeRelationshipState deleteNote(Note note) {
    return (FamilyTreeRelationshipState) super.deleteNote(note);
  }

  public ChangeHistoryState readChangeHistory() {
    Link link = getLink(Rel.CHANGE_HISTORY);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedFeedRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return ((FamilyTreeStateFactory)this.stateFactory).newChangeHistoryState(request, invoke(request), this.accessToken);
  }

}
