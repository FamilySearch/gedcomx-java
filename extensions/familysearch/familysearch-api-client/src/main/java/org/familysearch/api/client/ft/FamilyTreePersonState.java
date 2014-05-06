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
import org.gedcomx.rs.client.*;
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
  public FamilyTreePersonState head(StateTransitionOption... options) {
    return (FamilyTreePersonState) super.head(options);
  }

  @Override
  public FamilyTreePersonState get(StateTransitionOption... options) {
    return (FamilyTreePersonState) super.get(options);
  }

  @Override
  public FamilyTreePersonState delete(StateTransitionOption... options) {
    return (FamilyTreePersonState) super.delete(options);
  }

  @Override
  public FamilyTreePersonState put(Gedcomx e, StateTransitionOption... options) {
    return (FamilyTreePersonState) super.put(e, options);
  }

  @Override
  public FamilyTreePersonState post(Gedcomx entity, StateTransitionOption... options) {
    return (FamilyTreePersonState) super.post(entity, options);
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
  public FamilyTreePersonState authenticateViaOAuth2(MultivaluedMap<String, String> formData, StateTransitionOption... options) {
    return (FamilyTreePersonState) super.authenticateViaOAuth2(formData, options);
  }

  @Override
  public FamilyTreeCollectionState readCollection(StateTransitionOption... options) {
    return (FamilyTreeCollectionState) super.readCollection(options);
  }

  @Override
  public FamilyTreePersonState loadEmbeddedResources(StateTransitionOption... options) {
    return (FamilyTreePersonState) super.loadEmbeddedResources(options);
  }

  @Override
  public FamilyTreePersonState loadEmbeddedResources(String[] rels, StateTransitionOption... options) {
    return (FamilyTreePersonState) super.loadEmbeddedResources(rels, options);
  }

  @Override
  public FamilyTreePersonState loadConclusions(StateTransitionOption... options) {
    return (FamilyTreePersonState) super.loadConclusions(options);
  }

  @Override
  public FamilyTreePersonState loadSourceReferences(StateTransitionOption... options) {
    return (FamilyTreePersonState) super.loadSourceReferences(options);
  }

  @Override
  public FamilyTreePersonState loadEvidenceReferences(StateTransitionOption... options) {
    return (FamilyTreePersonState) super.loadEvidenceReferences(options);
  }

  @Override
  public FamilyTreePersonState loadMediaReferences(StateTransitionOption... options) {
    return (FamilyTreePersonState) super.loadMediaReferences(options);
  }

  @Override
  public FamilyTreePersonState loadNotes(StateTransitionOption... options) {
    return (FamilyTreePersonState) super.loadNotes(options);
  }

  @Override
  public FamilyTreePersonState loadParentRelationships(StateTransitionOption... options) {
    return (FamilyTreePersonState) super.loadParentRelationships(options);
  }

  @Override
  public FamilyTreePersonState loadSpouseRelationships(StateTransitionOption... options) {
    return (FamilyTreePersonState) super.loadSpouseRelationships(options);
  }

  @Override
  public FamilyTreePersonState loadChildRelationships(StateTransitionOption... options) {
    return (FamilyTreePersonState) super.loadChildRelationships(options);
  }

  @Override
  public FamilyTreePersonState addGender(Gender gender, StateTransitionOption... options) {
    return (FamilyTreePersonState) super.addGender(gender, options);
  }

  @Override
  public FamilyTreePersonState addName(Name name, StateTransitionOption... options) {
    return (FamilyTreePersonState) super.addName(name, options);
  }

  @Override
  public FamilyTreePersonState addNames(Name[] names, StateTransitionOption... options) {
    return (FamilyTreePersonState) super.addNames(names, options);
  }

  @Override
  public FamilyTreePersonState addFact(Fact fact, StateTransitionOption... options) {
    return (FamilyTreePersonState) super.addFact(fact, options);
  }

  @Override
  public FamilyTreePersonState addFacts(Fact[] facts, StateTransitionOption... options) {
    return (FamilyTreePersonState) super.addFacts(facts, options);
  }

  @Override
  public FamilyTreePersonState updateGender(Gender gender, StateTransitionOption... options) {
    return (FamilyTreePersonState) super.updateGender(gender, options);
  }

  @Override
  public FamilyTreePersonState updateName(Name name, StateTransitionOption... options) {
    return (FamilyTreePersonState) super.updateName(name, options);
  }

  @Override
  public FamilyTreePersonState updateNames(Name[] names, StateTransitionOption... options) {
    return (FamilyTreePersonState) super.updateNames(names, options);
  }

  @Override
  public FamilyTreePersonState updateFact(Fact fact, StateTransitionOption... options) {
    return (FamilyTreePersonState) super.updateFact(fact, options);
  }

  @Override
  public FamilyTreePersonState updateFacts(Fact[] facts, StateTransitionOption... options) {
    return (FamilyTreePersonState) super.updateFacts(facts, options);
  }

  @Override
  public FamilyTreePersonState updateConclusions(Person person, StateTransitionOption... options) {
    return (FamilyTreePersonState) super.updateConclusions(person, options);
  }

  @Override
  public FamilyTreePersonState updateConclusions(Gedcomx entity, StateTransitionOption... options) {
    return (FamilyTreePersonState) super.updateConclusions(entity, options);
  }

  @Override
  public FamilyTreePersonState deleteName(Name name, StateTransitionOption... options) {
    return (FamilyTreePersonState) super.deleteName(name, options);
  }

  @Override
  public FamilyTreePersonState deleteGender(Gender gender, StateTransitionOption... options) {
    return (FamilyTreePersonState) super.deleteGender(gender, options);
  }

  @Override
  public FamilyTreePersonState deleteFact(Fact fact, StateTransitionOption... options) {
    return (FamilyTreePersonState) super.deleteFact(fact, options);
  }

  @Override
  protected FamilyTreePersonState doDeleteConclusion(Conclusion conclusion, StateTransitionOption... options) {
    return (FamilyTreePersonState) super.doDeleteConclusion(conclusion, options);
  }

  @Override
  public FamilyTreePersonState addSourceReference(SourceDescriptionState source, StateTransitionOption... options) {
    return (FamilyTreePersonState) super.addSourceReference(source, options);
  }

  @Override
  public FamilyTreePersonState addSourceReference(SourceReference reference, StateTransitionOption... options) {
    return (FamilyTreePersonState) super.addSourceReference(reference, options);
  }

  @Override
  public FamilyTreePersonState addSourceReferences(SourceReference[] refs, StateTransitionOption... options) {
    return (FamilyTreePersonState) super.addSourceReferences(refs, options);
  }

  @Override
  public FamilyTreePersonState updateSourceReference(SourceReference reference, StateTransitionOption... options) {
    return (FamilyTreePersonState) super.updateSourceReference(reference, options);
  }

  @Override
  public FamilyTreePersonState updateSourceReferences(SourceReference[] refs, StateTransitionOption... options) {
    return (FamilyTreePersonState) super.updateSourceReferences(refs, options);
  }

  @Override
  public FamilyTreePersonState updateSourceReferences(Person person, StateTransitionOption... options) {
    return (FamilyTreePersonState) super.updateSourceReferences(person, options);
  }

  @Override
  public FamilyTreePersonState deleteSourceReference(SourceReference reference, StateTransitionOption... options) {
    return (FamilyTreePersonState) super.deleteSourceReference(reference, options);
  }

  @Override
  public FamilyTreePersonState addMediaReference(SourceDescriptionState description, StateTransitionOption... options) {
    return (FamilyTreePersonState) super.addMediaReference(description, options);
  }

  @Override
  public FamilyTreePersonState addMediaReference(SourceReference reference, StateTransitionOption... options) {
    return (FamilyTreePersonState) super.addMediaReference(reference, options);
  }

  @Override
  public FamilyTreePersonState addMediaReferences(SourceReference[] refs, StateTransitionOption... options) {
    return (FamilyTreePersonState) super.addMediaReferences(refs, options);
  }

  @Override
  public FamilyTreePersonState updateMediaReference(SourceReference reference, StateTransitionOption... options) {
    return (FamilyTreePersonState) super.updateMediaReference(reference, options);
  }

  @Override
  public FamilyTreePersonState updateMediaReferences(SourceReference[] refs, StateTransitionOption... options) {
    return (FamilyTreePersonState) super.updateMediaReferences(refs, options);
  }

  @Override
  public FamilyTreePersonState updateMediaReferences(Person person, StateTransitionOption... options) {
    return (FamilyTreePersonState) super.updateMediaReferences(person, options);
  }

  @Override
  public FamilyTreePersonState deleteMediaReference(SourceReference reference, StateTransitionOption... options) {
    return (FamilyTreePersonState) super.deleteMediaReference(reference, options);
  }

  @Override
  public FamilyTreePersonState addEvidenceReference(PersonState evidence, StateTransitionOption... options) {
    return (FamilyTreePersonState) super.addEvidenceReference(evidence, options);
  }

  @Override
  public FamilyTreePersonState addEvidenceReference(EvidenceReference reference, StateTransitionOption... options) {
    return (FamilyTreePersonState) super.addEvidenceReference(reference, options);
  }

  @Override
  public FamilyTreePersonState addEvidenceReferences(EvidenceReference[] refs, StateTransitionOption... options) {
    return (FamilyTreePersonState) super.addEvidenceReferences(refs, options);
  }

  @Override
  public FamilyTreePersonState updateEvidenceReference(EvidenceReference reference, StateTransitionOption... options) {
    return (FamilyTreePersonState) super.updateEvidenceReference(reference, options);
  }

  @Override
  public FamilyTreePersonState updateEvidenceReferences(EvidenceReference[] refs, StateTransitionOption... options) {
    return (FamilyTreePersonState) super.updateEvidenceReferences(refs, options);
  }

  @Override
  public FamilyTreePersonState updateEvidenceReferences(Person person, StateTransitionOption... options) {
    return (FamilyTreePersonState) super.updateEvidenceReferences(person, options);
  }

  @Override
  public FamilyTreePersonState deleteEvidenceReference(EvidenceReference reference, StateTransitionOption... options) {
    return (FamilyTreePersonState) super.deleteEvidenceReference(reference, options);
  }

  @Override
  public FamilyTreePersonState readNote(Note note, StateTransitionOption... options) {
    return (FamilyTreePersonState) super.readNote(note, options);
  }

  @Override
  public FamilyTreePersonState addNote(Note note, StateTransitionOption... options) {
    return (FamilyTreePersonState) super.addNote(note, options);
  }

  @Override
  public FamilyTreePersonState addNotes(Note[] notes, StateTransitionOption... options) {
    return (FamilyTreePersonState) super.addNotes(notes, options);
  }

  @Override
  public FamilyTreePersonState updateNote(Note note, StateTransitionOption... options) {
    return (FamilyTreePersonState) super.updateNote(note, options);
  }

  @Override
  public FamilyTreePersonState updateNotes(Person person, StateTransitionOption... options) {
    return (FamilyTreePersonState) super.updateNotes(person, options);
  }

  @Override
  public FamilyTreePersonState updateNotes(Note[] notes, StateTransitionOption... options) {
    return (FamilyTreePersonState) super.updateNotes(notes, options);
  }

  @Override
  public FamilyTreePersonState deleteNote(Note note, StateTransitionOption... options) {
    return (FamilyTreePersonState) super.deleteNote(note, options);
  }

  @Override
  public FamilyTreePersonState readRelative(Relationship relationship, StateTransitionOption... options) {
    return (FamilyTreePersonState) super.readRelative(relationship, options);
  }

  @Override
  public FamilyTreePersonState readFirstSpouse(StateTransitionOption... options) {
    return (FamilyTreePersonState) super.readFirstSpouse(options);
  }

  @Override
  public FamilyTreePersonState readSpouse(int index, StateTransitionOption... options) {
    return (FamilyTreePersonState) super.readSpouse(index, options);
  }

  @Override
  public FamilyTreePersonState readSpouse(Relationship relationship, StateTransitionOption... options) {
    return (FamilyTreePersonState) super.readSpouse(relationship, options);
  }

  @Override
  public FamilyTreePersonState readFirstChild(StateTransitionOption... options) {
    return (FamilyTreePersonState) super.readFirstChild(options);
  }

  @Override
  public FamilyTreePersonState readChild(int index, StateTransitionOption... options) {
    return (FamilyTreePersonState) super.readChild(index, options);
  }

  @Override
  public FamilyTreePersonState readChild(Relationship relationship, StateTransitionOption... options) {
    return (FamilyTreePersonState) super.readChild(relationship, options);
  }

  @Override
  public FamilyTreePersonState readFirstParent(StateTransitionOption... options) {
    return (FamilyTreePersonState) super.readFirstParent(options);
  }

  @Override
  public FamilyTreePersonState readParent(int index, StateTransitionOption... options) {
    return (FamilyTreePersonState) super.readParent(index, options);
  }

  @Override
  public FamilyTreePersonState readParent(Relationship relationship, StateTransitionOption... options) {
    return (FamilyTreePersonState) super.readParent(relationship, options);
  }

  @Override
  public FamilyTreePersonParentsState readParents(StateTransitionOption... options) {
    return (FamilyTreePersonParentsState) super.readParents(options);
  }

  @Override
  public FamilyTreePersonChildrenState readChildren(StateTransitionOption... options) {
    return (FamilyTreePersonChildrenState) super.readChildren(options);
  }

  public ChangeHistoryState readChangeHistory(StateTransitionOption... options) {
    Link link = getLink(Rel.CHANGE_HISTORY);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedFeedRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return ((FamilyTreeStateFactory)this.stateFactory).newChangeHistoryState(request, invoke(request, options), this.accessToken);
  }

  public FamilyTreePersonState restore(StateTransitionOption... options) {
    Link link = getLink(Rel.RESTORE);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.POST);
    return ((FamilyTreeStateFactory)this.stateFactory).newPersonState(request, invoke(request, options), this.accessToken);
  }

}
