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
import org.familysearch.platform.ct.ChildAndParentsRelationship;
import org.gedcomx.Gedcomx;
import org.gedcomx.common.EvidenceReference;
import org.gedcomx.common.Note;
import org.gedcomx.conclusion.*;
import org.gedcomx.links.Link;
import org.gedcomx.rs.client.PersonState;
import org.gedcomx.rs.client.SourceDescriptionState;
import org.gedcomx.source.SourceReference;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MultivaluedMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Ryan Heaton
 */
public class FamilyTreePersonState extends PersonState {

  protected FamilyTreePersonState(ClientRequest request, ClientResponse response, String accessToken, FamilyTreeStateFactory stateFactory) {
    super(request, response, accessToken, stateFactory);
  }

  @Override
  protected FamilyTreePersonState clone(ClientRequest request, ClientResponse response) {
    return new FamilyTreePersonState(request, response, this.accessToken, (FamilyTreeStateFactory) this.stateFactory);
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

  public List<ChildAndParentsRelationship> getChildAndParentsRelationships() {
    return getEntity() == null ? null : getEntity().getChildAndParentsRelationships();
  }

  public List<ChildAndParentsRelationship> getChildAndParentsRelationshipsToParents() {
    List<ChildAndParentsRelationship> relationships = getChildAndParentsRelationships();
    relationships = relationships == null ? null : new ArrayList<ChildAndParentsRelationship>(relationships);
    if (relationships != null) {
      Iterator<ChildAndParentsRelationship> it = relationships.iterator();
      while (it.hasNext()) {
        ChildAndParentsRelationship relationship = it.next();
        if (refersToMe(relationship.getChild())) {
          it.remove();
        }
      }
    }
    return relationships;
  }

  public List<ChildAndParentsRelationship> getChildAndParentsRelationshipsToChildren() {
    List<ChildAndParentsRelationship> relationships = getChildAndParentsRelationships();
    relationships = relationships == null ? null : new ArrayList<ChildAndParentsRelationship>(relationships);
    if (relationships != null) {
      Iterator<ChildAndParentsRelationship> it = relationships.iterator();
      while (it.hasNext()) {
        ChildAndParentsRelationship relationship = it.next();
        if (refersToMe(relationship.getFather()) || refersToMe(relationship.getMother())) {
          it.remove();
        }
      }
    }
    return relationships;
  }

  @Override
  public FamilyTreePersonState ifSuccessful() {
    return (FamilyTreePersonState) super.ifSuccessful();
  }

  @Override
  public FamilyTreePersonState head() {
    return (FamilyTreePersonState) super.head();
  }

  @Override
  public FamilyTreePersonState get() {
    return (FamilyTreePersonState) super.get();
  }

  @Override
  public FamilyTreePersonState delete() {
    return (FamilyTreePersonState) super.delete();
  }

  @Override
  public FamilyTreePersonState put(Gedcomx e) {
    return (FamilyTreePersonState) super.put(e);
  }

  @Override
  public FamilyTreePersonState authenticateViaOAuth2Password(String username, String password, String clientId) {
    return (FamilyTreePersonState) super.authenticateViaOAuth2Password(username, password, clientId);
  }

  @Override
  public FamilyTreePersonState authenticateViaOAuth2Password(String username, String password, String clientId, String clientSecret) {
    return (FamilyTreePersonState) super.authenticateViaOAuth2Password(username, password, clientId, clientSecret);
  }

  @Override
  public FamilyTreePersonState authenticateViaOAuth2AuthCode(String authCode, String redirect, String clientId) {
    return (FamilyTreePersonState) super.authenticateViaOAuth2AuthCode(authCode, redirect, clientId);
  }

  @Override
  public FamilyTreePersonState authenticateViaOAuth2AuthCode(String authCode, String redirect, String clientId, String clientSecret) {
    return (FamilyTreePersonState) super.authenticateViaOAuth2AuthCode(authCode, redirect, clientId, clientSecret);
  }

  @Override
  public FamilyTreePersonState authenticateViaOAuth2ClientCredentials(String clientId, String clientSecret) {
    return (FamilyTreePersonState) super.authenticateViaOAuth2ClientCredentials(clientId, clientSecret);
  }

  @Override
  public FamilyTreePersonState authenticateViaOAuth2(MultivaluedMap<String, String> formData) {
    return (FamilyTreePersonState) super.authenticateViaOAuth2(formData);
  }

  @Override
  public FamilyTreeCollectionState readCollection() {
    return (FamilyTreeCollectionState) super.readCollection();
  }

  @Override
  public FamilyTreePersonState loadEmbeddedResources() {
    return (FamilyTreePersonState) super.loadEmbeddedResources();
  }

  @Override
  public FamilyTreePersonState loadEmbeddedResources(String... rels) {
    return (FamilyTreePersonState) super.loadEmbeddedResources(rels);
  }

  @Override
  public FamilyTreePersonState loadConclusions() {
    return (FamilyTreePersonState) super.loadConclusions();
  }

  @Override
  public FamilyTreePersonState loadSourceReferences() {
    return (FamilyTreePersonState) super.loadSourceReferences();
  }

  @Override
  public FamilyTreePersonState loadEvidenceReferences() {
    return (FamilyTreePersonState) super.loadEvidenceReferences();
  }

  @Override
  public FamilyTreePersonState loadMediaReferences() {
    return (FamilyTreePersonState) super.loadMediaReferences();
  }

  @Override
  public FamilyTreePersonState loadNotes() {
    return (FamilyTreePersonState) super.loadNotes();
  }

  @Override
  public FamilyTreePersonState loadParentRelationships() {
    return (FamilyTreePersonState) super.loadParentRelationships();
  }

  @Override
  public FamilyTreePersonState loadSpouseRelationships() {
    return (FamilyTreePersonState) super.loadSpouseRelationships();
  }

  @Override
  public FamilyTreePersonState loadChildRelationships() {
    return (FamilyTreePersonState) super.loadChildRelationships();
  }

  @Override
  public FamilyTreePersonState addGender(Gender gender) {
    return (FamilyTreePersonState) super.addGender(gender);
  }

  @Override
  public FamilyTreePersonState addName(Name name) {
    return (FamilyTreePersonState) super.addName(name);
  }

  @Override
  public FamilyTreePersonState addNames(Name... names) {
    return (FamilyTreePersonState) super.addNames(names);
  }

  @Override
  public FamilyTreePersonState addFact(Fact fact) {
    return (FamilyTreePersonState) super.addFact(fact);
  }

  @Override
  public FamilyTreePersonState addFacts(Fact... facts) {
    return (FamilyTreePersonState) super.addFacts(facts);
  }

  @Override
  public FamilyTreePersonState updateGender(Gender gender) {
    return (FamilyTreePersonState) super.updateGender(gender);
  }

  @Override
  public FamilyTreePersonState updateName(Name name) {
    return (FamilyTreePersonState) super.updateName(name);
  }

  @Override
  public FamilyTreePersonState updateNames(Name... names) {
    return (FamilyTreePersonState) super.updateNames(names);
  }

  @Override
  public FamilyTreePersonState updateFact(Fact fact) {
    return (FamilyTreePersonState) super.updateFact(fact);
  }

  @Override
  public FamilyTreePersonState updateFacts(Fact... facts) {
    return (FamilyTreePersonState) super.updateFacts(facts);
  }

  @Override
  public FamilyTreePersonState updateConclusions(Person person) {
    return (FamilyTreePersonState) super.updateConclusions(person);
  }

  @Override
  public FamilyTreePersonState updateConclusions(Gedcomx entity) {
    return (FamilyTreePersonState) super.updateConclusions(entity);
  }

  @Override
  public FamilyTreePersonState deleteName(Name name) {
    return (FamilyTreePersonState) super.deleteName(name);
  }

  @Override
  public FamilyTreePersonState deleteGender(Gender gender) {
    return (FamilyTreePersonState) super.deleteGender(gender);
  }

  @Override
  public FamilyTreePersonState deleteFact(Fact fact) {
    return (FamilyTreePersonState) super.deleteFact(fact);
  }

  @Override
  protected FamilyTreePersonState doDeleteConclusion(Conclusion conclusion) {
    return (FamilyTreePersonState) super.doDeleteConclusion(conclusion);
  }

  @Override
  public FamilyTreePersonState addSourceReference(SourceDescriptionState source) {
    return (FamilyTreePersonState) super.addSourceReference(source);
  }

  @Override
  public FamilyTreePersonState addSourceReference(SourceReference reference) {
    return (FamilyTreePersonState) super.addSourceReference(reference);
  }

  @Override
  public FamilyTreePersonState addSourceReferences(SourceReference... refs) {
    return (FamilyTreePersonState) super.addSourceReferences(refs);
  }

  @Override
  public FamilyTreePersonState updateSourceReference(SourceReference reference) {
    return (FamilyTreePersonState) super.updateSourceReference(reference);
  }

  @Override
  public FamilyTreePersonState updateSourceReferences(SourceReference... refs) {
    return (FamilyTreePersonState) super.updateSourceReferences(refs);
  }

  @Override
  public FamilyTreePersonState updateSourceReferences(Person person) {
    return (FamilyTreePersonState) super.updateSourceReferences(person);
  }

  @Override
  public FamilyTreePersonState deleteSourceReference(SourceReference reference) {
    return (FamilyTreePersonState) super.deleteSourceReference(reference);
  }

  @Override
  public FamilyTreePersonState addMediaReference(SourceDescriptionState description) {
    return (FamilyTreePersonState) super.addMediaReference(description);
  }

  @Override
  public FamilyTreePersonState addMediaReference(SourceReference reference) {
    return (FamilyTreePersonState) super.addMediaReference(reference);
  }

  @Override
  public FamilyTreePersonState addMediaReferences(SourceReference... refs) {
    return (FamilyTreePersonState) super.addMediaReferences(refs);
  }

  @Override
  public FamilyTreePersonState updateMediaReference(SourceReference reference) {
    return (FamilyTreePersonState) super.updateMediaReference(reference);
  }

  @Override
  public FamilyTreePersonState updateMediaReferences(SourceReference... refs) {
    return (FamilyTreePersonState) super.updateMediaReferences(refs);
  }

  @Override
  public FamilyTreePersonState updateMediaReferences(Person person) {
    return (FamilyTreePersonState) super.updateMediaReferences(person);
  }

  @Override
  public FamilyTreePersonState deleteMediaReference(SourceReference reference) {
    return (FamilyTreePersonState) super.deleteMediaReference(reference);
  }

  @Override
  public FamilyTreePersonState addEvidenceReference(PersonState evidence) {
    return (FamilyTreePersonState) super.addEvidenceReference(evidence);
  }

  @Override
  public FamilyTreePersonState addEvidenceReference(EvidenceReference reference) {
    return (FamilyTreePersonState) super.addEvidenceReference(reference);
  }

  @Override
  public FamilyTreePersonState addEvidenceReferences(EvidenceReference... refs) {
    return (FamilyTreePersonState) super.addEvidenceReferences(refs);
  }

  @Override
  public FamilyTreePersonState updateEvidenceReference(EvidenceReference reference) {
    return (FamilyTreePersonState) super.updateEvidenceReference(reference);
  }

  @Override
  public FamilyTreePersonState updateEvidenceReferences(EvidenceReference... refs) {
    return (FamilyTreePersonState) super.updateEvidenceReferences(refs);
  }

  @Override
  public FamilyTreePersonState updateEvidenceReferences(Person person) {
    return (FamilyTreePersonState) super.updateEvidenceReferences(person);
  }

  @Override
  public FamilyTreePersonState deleteEvidenceReference(EvidenceReference reference) {
    return (FamilyTreePersonState) super.deleteEvidenceReference(reference);
  }

  @Override
  public FamilyTreePersonState readNote(Note note) {
    return (FamilyTreePersonState) super.readNote(note);
  }

  @Override
  public FamilyTreePersonState addNote(Note note) {
    return (FamilyTreePersonState) super.addNote(note);
  }

  @Override
  public FamilyTreePersonState addNotes(Note... notes) {
    return (FamilyTreePersonState) super.addNotes(notes);
  }

  @Override
  public FamilyTreePersonState updateNote(Note note) {
    return (FamilyTreePersonState) super.updateNote(note);
  }

  @Override
  public FamilyTreePersonState updateNotes(Person person) {
    return (FamilyTreePersonState) super.updateNotes(person);
  }

  @Override
  public FamilyTreePersonState updateNotes(Note... notes) {
    return (FamilyTreePersonState) super.updateNotes(notes);
  }

  @Override
  public FamilyTreePersonState deleteNote(Note note) {
    return (FamilyTreePersonState) super.deleteNote(note);
  }

  @Override
  public FamilyTreePersonState readRelative(Relationship relationship) {
    return (FamilyTreePersonState) super.readRelative(relationship);
  }

  @Override
  public FamilyTreePersonState readFirstSpouse() {
    return (FamilyTreePersonState) super.readFirstSpouse();
  }

  @Override
  public FamilyTreePersonState readSpouse(int index) {
    return (FamilyTreePersonState) super.readSpouse(index);
  }

  @Override
  public FamilyTreePersonState readSpouse(Relationship relationship) {
    return (FamilyTreePersonState) super.readSpouse(relationship);
  }

  @Override
  public FamilyTreePersonState readFirstChild() {
    return (FamilyTreePersonState) super.readFirstChild();
  }

  @Override
  public FamilyTreePersonState readChild(int index) {
    return (FamilyTreePersonState) super.readChild(index);
  }

  @Override
  public FamilyTreePersonState readChild(Relationship relationship) {
    return (FamilyTreePersonState) super.readChild(relationship);
  }

  @Override
  public FamilyTreePersonState readFirstParent() {
    return (FamilyTreePersonState) super.readFirstParent();
  }

  @Override
  public FamilyTreePersonState readParent(int index) {
    return (FamilyTreePersonState) super.readParent(index);
  }

  @Override
  public FamilyTreePersonState readParent(Relationship relationship) {
    return (FamilyTreePersonState) super.readParent(relationship);
  }

  public ChangeHistoryState readChangeHistory() {
    Link link = getLink(Rel.CHANGE_HISTORY);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return ((FamilyTreeStateFactory)this.stateFactory).newChangeHistoryState(request, invoke(request), this.accessToken);
  }

}
