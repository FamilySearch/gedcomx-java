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
package org.familysearch.api.client.gens;

import com.damnhandy.uri.template.MalformedUriTemplateException;
import com.damnhandy.uri.template.UriTemplate;
import com.damnhandy.uri.template.VariableExpansionException;
import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import org.familysearch.api.client.DiscussionState;
import org.familysearch.api.client.FamilySearchPersonState;
import org.familysearch.api.client.util.RequestUtil;
import org.familysearch.api.rt.Rel;
import org.familysearch.platform.ct.DiscussionReference;
import org.gedcomx.Gedcomx;
import org.gedcomx.common.EvidenceReference;
import org.gedcomx.common.Note;
import org.gedcomx.conclusion.*;
import org.gedcomx.links.Link;
import org.gedcomx.rs.client.*;
import org.gedcomx.source.SourceReference;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MultivaluedMap;
import java.net.URI;

public class GenealogiesPersonState extends FamilySearchPersonState {

  public GenealogiesPersonState(URI uri) {
    super(uri, new GenealogiesStateFactory());
  }

  protected GenealogiesPersonState(ClientRequest request, ClientResponse response, String accessToken, GenealogiesStateFactory stateFactory) {
    super(request, response, accessToken, stateFactory);
  }

  protected GenealogiesPersonState(ClientRequest request, ClientResponse response, String accessToken, String selfRel, GenealogiesStateFactory stateFactory) {
    super(request, response, accessToken, selfRel, stateFactory);
  }

  @Override
  protected GenealogiesPersonState clone(ClientRequest request, ClientResponse response) {
    return new GenealogiesPersonState(request, response, this.accessToken, getSelfRel(), (GenealogiesStateFactory) this.stateFactory);
  }

  @Override
  public GenealogiesPersonState ifSuccessful() {
    return (GenealogiesPersonState) super.ifSuccessful();
  }

  @Override
  public GenealogiesPersonState head(StateTransitionOption... options) {
    return (GenealogiesPersonState) super.head(options);
  }

  @Override
  public GenealogiesPersonState get(StateTransitionOption... options) {
    return (GenealogiesPersonState) super.get(options);
  }

  @Override
  public GenealogiesPersonState delete(StateTransitionOption... options) {
    return (GenealogiesPersonState) super.delete(options);
  }

  @Override
  public GenealogiesPersonState put(Gedcomx e, StateTransitionOption... options) {
    return (GenealogiesPersonState) super.put(e, options);
  }

  @Override
  public GenealogiesPersonState post(Gedcomx entity, StateTransitionOption... options) {
    return (GenealogiesPersonState) super.post(entity, options);
  }

  @Override
  public GenealogiesPersonState authenticateViaOAuth2Password(String username, String password, String clientId) {
    return (GenealogiesPersonState) super.authenticateViaOAuth2Password(username, password, clientId);
  }

  @Override
  public GenealogiesPersonState authenticateViaOAuth2Password(String username, String password, String clientId, String clientSecret) {
    return (GenealogiesPersonState) super.authenticateViaOAuth2Password(username, password, clientId, clientSecret);
  }

  @Override
  public GenealogiesPersonState authenticateViaOAuth2AuthCode(String authCode, String redirect, String clientId) {
    return (GenealogiesPersonState) super.authenticateViaOAuth2AuthCode(authCode, redirect, clientId);
  }

  @Override
  public GenealogiesPersonState authenticateViaOAuth2AuthCode(String authCode, String redirect, String clientId, String clientSecret) {
    return (GenealogiesPersonState) super.authenticateViaOAuth2AuthCode(authCode, redirect, clientId, clientSecret);
  }

  @Override
  public GenealogiesPersonState authenticateViaOAuth2ClientCredentials(String clientId, String clientSecret) {
    return (GenealogiesPersonState) super.authenticateViaOAuth2ClientCredentials(clientId, clientSecret);
  }

  @Override
  public GenealogiesPersonState authenticateViaOAuth2(MultivaluedMap<String, String> formData, StateTransitionOption... options) {
    return (GenealogiesPersonState) super.authenticateViaOAuth2(formData, options);
  }

  @Override
  public GenealogiesTreeState readCollection(StateTransitionOption... options) {
    return (GenealogiesTreeState) super.readCollection(options);
  }

  @Override
  public GenealogiesPersonState loadEmbeddedResources(StateTransitionOption... options) {
    return (GenealogiesPersonState) super.loadEmbeddedResources(options);
  }

  @Override
  public GenealogiesPersonState loadEmbeddedResources(String[] rels, StateTransitionOption... options) {
    return (GenealogiesPersonState) super.loadEmbeddedResources(rels, options);
  }

  @Override
  public GenealogiesPersonState loadConclusions(StateTransitionOption... options) {
    return (GenealogiesPersonState) super.loadConclusions(options);
  }

  @Override
  public GenealogiesPersonState loadSourceReferences(StateTransitionOption... options) {
    return (GenealogiesPersonState) super.loadSourceReferences(options);
  }

  public GenealogiesPersonState loadDiscussionReferences(StateTransitionOption... options) {
    return (GenealogiesPersonState) super.loadDiscussionReferences(options);
  }

  @Override
  public GenealogiesPersonState loadEvidenceReferences(StateTransitionOption... options) {
    return (GenealogiesPersonState) super.loadEvidenceReferences(options);
  }

  @Override
  public GenealogiesPersonState loadMediaReferences(StateTransitionOption... options) {
    return (GenealogiesPersonState) super.loadMediaReferences(options);
  }

  @Override
  public GenealogiesPersonState loadNotes(StateTransitionOption... options) {
    return (GenealogiesPersonState) super.loadNotes(options);
  }

  @Override
  public GenealogiesPersonState loadParentRelationships(StateTransitionOption... options) {
    return (GenealogiesPersonState) super.loadParentRelationships(options);
  }

  @Override
  public GenealogiesPersonState loadSpouseRelationships(StateTransitionOption... options) {
    return (GenealogiesPersonState) super.loadSpouseRelationships(options);
  }

  @Override
  public GenealogiesPersonState loadChildRelationships(StateTransitionOption... options) {
    return (GenealogiesPersonState) super.loadChildRelationships(options);
  }

  @Override
  public GenealogiesPersonState addGender(Gender gender, StateTransitionOption... options) {
    return (GenealogiesPersonState) super.addGender(gender, options);
  }

  @Override
  public GenealogiesPersonState addName(Name name, StateTransitionOption... options) {
    return (GenealogiesPersonState) super.addName(name, options);
  }

  @Override
  public GenealogiesPersonState addNames(Name[] names, StateTransitionOption... options) {
    return (GenealogiesPersonState) super.addNames(names, options);
  }

  @Override
  public GenealogiesPersonState addFact(Fact fact, StateTransitionOption... options) {
    return (GenealogiesPersonState) super.addFact(fact, options);
  }

  @Override
  public GenealogiesPersonState addFacts(Fact[] facts, StateTransitionOption... options) {
    return (GenealogiesPersonState) super.addFacts(facts, options);
  }

  @Override
  public GenealogiesPersonState updateGender(Gender gender, StateTransitionOption... options) {
    return (GenealogiesPersonState) super.updateGender(gender, options);
  }

  @Override
  public GenealogiesPersonState updateName(Name name, StateTransitionOption... options) {
    return (GenealogiesPersonState) super.updateName(name, options);
  }

  @Override
  public GenealogiesPersonState updateNames(Name[] names, StateTransitionOption... options) {
    return (GenealogiesPersonState) super.updateNames(names, options);
  }

  @Override
  public GenealogiesPersonState updateFact(Fact fact, StateTransitionOption... options) {
    return (GenealogiesPersonState) super.updateFact(fact, options);
  }

  @Override
  public GenealogiesPersonState updateFacts(Fact[] facts, StateTransitionOption... options) {
    return (GenealogiesPersonState) super.updateFacts(facts, options);
  }

  @Override
  public GenealogiesPersonState updateConclusions(Person person, StateTransitionOption... options) {
    return (GenealogiesPersonState) super.updateConclusions(person, options);
  }

  @Override
  public GenealogiesPersonState updateConclusions(Gedcomx entity, StateTransitionOption... options) {
    return (GenealogiesPersonState) super.updateConclusions(entity, options);
  }

  @Override
  public GenealogiesPersonState deleteName(Name name, StateTransitionOption... options) {
    return (GenealogiesPersonState) super.deleteName(name, options);
  }

  @Override
  public GenealogiesPersonState deleteGender(Gender gender, StateTransitionOption... options) {
    return (GenealogiesPersonState) super.deleteGender(gender, options);
  }

  @Override
  public GenealogiesPersonState deleteFact(Fact fact, StateTransitionOption... options) {
    return (GenealogiesPersonState) super.deleteFact(fact, options);
  }

  @Override
  protected GenealogiesPersonState doDeleteConclusion(Conclusion conclusion, StateTransitionOption... options) {
    return (GenealogiesPersonState) super.doDeleteConclusion(conclusion, options);
  }

  @Override
  public GenealogiesPersonState addSourceReference(SourceDescriptionState source, StateTransitionOption... options) {
    return (GenealogiesPersonState) super.addSourceReference(source, options);
  }

  @Override
  public GenealogiesPersonState addSourceReference(RecordState source, StateTransitionOption... options) {
    return (GenealogiesPersonState) super.addSourceReference(source, options);
  }

  @Override
  public GenealogiesPersonState addSourceReference(SourceReference reference, StateTransitionOption... options) {
    return (GenealogiesPersonState) super.addSourceReference(reference, options);
  }

  @Override
  public GenealogiesPersonState addSourceReferences(SourceReference[] refs, StateTransitionOption... options) {
    return (GenealogiesPersonState) super.addSourceReferences(refs, options);
  }

  @Override
  public GenealogiesPersonState updateSourceReference(SourceReference reference, StateTransitionOption... options) {
    return (GenealogiesPersonState) super.updateSourceReference(reference, options);
  }

  @Override
  public GenealogiesPersonState updateSourceReferences(SourceReference[] refs, StateTransitionOption... options) {
    return (GenealogiesPersonState) super.updateSourceReferences(refs, options);
  }

  @Override
  public GenealogiesPersonState updateSourceReferences(Person person, StateTransitionOption... options) {
    return (GenealogiesPersonState) super.updateSourceReferences(person, options);
  }

  @Override
  public GenealogiesPersonState deleteSourceReference(SourceReference reference, StateTransitionOption... options) {
    return (GenealogiesPersonState) super.deleteSourceReference(reference, options);
  }

  @Override
  public GenealogiesPersonState addDiscussionReference(DiscussionState discussion, StateTransitionOption... options) {
    return (GenealogiesPersonState) super.addDiscussionReference(discussion, options);
  }

  @Override
  public GenealogiesPersonState addDiscussionReference(DiscussionReference reference, StateTransitionOption... options) {
    return (GenealogiesPersonState) super.addDiscussionReference(reference, options);
  }

  @Override
  public GenealogiesPersonState addDiscussionReference(DiscussionReference[] refs, StateTransitionOption... options) {
    return (GenealogiesPersonState) super.addDiscussionReference(refs, options);
  }

  @Override
  public GenealogiesPersonState updateDiscussionReference(DiscussionReference reference, StateTransitionOption... options) {
    return (GenealogiesPersonState) super.updateDiscussionReference(reference, options);
  }

  @Override
  public GenealogiesPersonState updateDiscussionReference(DiscussionReference[] refs, StateTransitionOption... options) {
    return (GenealogiesPersonState) super.updateDiscussionReference(refs, options);
  }

  @Override
  public GenealogiesPersonState updateDiscussionReference(Person person, StateTransitionOption... options) {
    return (GenealogiesPersonState) super.updateDiscussionReference(person, options);
  }

  @Override
  public GenealogiesPersonState deleteDiscussionReference(DiscussionReference reference, StateTransitionOption... options) {
    return (GenealogiesPersonState) super.deleteDiscussionReference(reference, options);
  }

  @Override
  public GenealogiesPersonState addMediaReference(SourceDescriptionState description, StateTransitionOption... options) {
    return (GenealogiesPersonState) super.addMediaReference(description, options);
  }

  @Override
  public GenealogiesPersonState addMediaReference(SourceReference reference, StateTransitionOption... options) {
    return (GenealogiesPersonState) super.addMediaReference(reference, options);
  }

  @Override
  public GenealogiesPersonState addMediaReferences(SourceReference[] refs, StateTransitionOption... options) {
    return (GenealogiesPersonState) super.addMediaReferences(refs, options);
  }

  @Override
  public GenealogiesPersonState updateMediaReference(SourceReference reference, StateTransitionOption... options) {
    return (GenealogiesPersonState) super.updateMediaReference(reference, options);
  }

  @Override
  public GenealogiesPersonState updateMediaReferences(SourceReference[] refs, StateTransitionOption... options) {
    return (GenealogiesPersonState) super.updateMediaReferences(refs, options);
  }

  @Override
  public GenealogiesPersonState updateMediaReferences(Person person, StateTransitionOption... options) {
    return (GenealogiesPersonState) super.updateMediaReferences(person, options);
  }

  @Override
  public GenealogiesPersonState deleteMediaReference(SourceReference reference, StateTransitionOption... options) {
    return (GenealogiesPersonState) super.deleteMediaReference(reference, options);
  }

  @Override
  public GenealogiesPersonState addEvidenceReference(PersonState evidence, StateTransitionOption... options) {
    return (GenealogiesPersonState) super.addEvidenceReference(evidence, options);
  }

  @Override
  public GenealogiesPersonState addEvidenceReference(EvidenceReference reference, StateTransitionOption... options) {
    return (GenealogiesPersonState) super.addEvidenceReference(reference, options);
  }

  @Override
  public GenealogiesPersonState addEvidenceReferences(EvidenceReference[] refs, StateTransitionOption... options) {
    return (GenealogiesPersonState) super.addEvidenceReferences(refs, options);
  }

  @Override
  public GenealogiesPersonState updateEvidenceReference(EvidenceReference reference, StateTransitionOption... options) {
    return (GenealogiesPersonState) super.updateEvidenceReference(reference, options);
  }

  @Override
  public GenealogiesPersonState updateEvidenceReferences(EvidenceReference[] refs, StateTransitionOption... options) {
    return (GenealogiesPersonState) super.updateEvidenceReferences(refs, options);
  }

  @Override
  public GenealogiesPersonState updateEvidenceReferences(Person person, StateTransitionOption... options) {
    return (GenealogiesPersonState) super.updateEvidenceReferences(person, options);
  }

  @Override
  public GenealogiesPersonState deleteEvidenceReference(EvidenceReference reference, StateTransitionOption... options) {
    return (GenealogiesPersonState) super.deleteEvidenceReference(reference, options);
  }

  @Override
  public GenealogiesPersonState readNote(Note note, StateTransitionOption... options) {
    return (GenealogiesPersonState) super.readNote(note, options);
  }

  @Override
  public GenealogiesPersonState addNote(Note note, StateTransitionOption... options) {
    return (GenealogiesPersonState) super.addNote(note, options);
  }

  @Override
  public GenealogiesPersonState addNotes(Note[] notes, StateTransitionOption... options) {
    return (GenealogiesPersonState) super.addNotes(notes, options);
  }

  @Override
  public GenealogiesPersonState updateNote(Note note, StateTransitionOption... options) {
    return (GenealogiesPersonState) super.updateNote(note, options);
  }

  @Override
  public GenealogiesPersonState updateNotes(Person person, StateTransitionOption... options) {
    return (GenealogiesPersonState) super.updateNotes(person, options);
  }

  @Override
  public GenealogiesPersonState updateNotes(Note[] notes, StateTransitionOption... options) {
    return (GenealogiesPersonState) super.updateNotes(notes, options);
  }

  @Override
  public GenealogiesPersonState deleteNote(Note note, StateTransitionOption... options) {
    return (GenealogiesPersonState) super.deleteNote(note, options);
  }

  @Override
  public GenealogiesPersonState readRelative(Relationship relationship, StateTransitionOption... options) {
    return (GenealogiesPersonState) super.readRelative(relationship, options);
  }

  @Override
  public GenealogiesPersonState readFirstSpouse(StateTransitionOption... options) {
    return (GenealogiesPersonState) super.readFirstSpouse(options);
  }

  @Override
  public GenealogiesPersonState readSpouse(int index, StateTransitionOption... options) {
    return (GenealogiesPersonState) super.readSpouse(index, options);
  }

  @Override
  public GenealogiesPersonState readSpouse(Relationship relationship, StateTransitionOption... options) {
    return (GenealogiesPersonState) super.readSpouse(relationship, options);
  }

  @Override
  public GenealogiesPersonState readFirstChild(StateTransitionOption... options) {
    return (GenealogiesPersonState) super.readFirstChild(options);
  }

  @Override
  public GenealogiesPersonState readChild(int index, StateTransitionOption... options) {
    return (GenealogiesPersonState) super.readChild(index, options);
  }

  @Override
  public GenealogiesPersonState readChild(Relationship relationship, StateTransitionOption... options) {
    return (GenealogiesPersonState) super.readChild(relationship, options);
  }

  @Override
  public GenealogiesPersonState readFirstParent(StateTransitionOption... options) {
    return (GenealogiesPersonState) super.readFirstParent(options);
  }

  @Override
  public GenealogiesPersonState readParent(int index, StateTransitionOption... options) {
    return (GenealogiesPersonState) super.readParent(index, options);
  }

  @Override
  public GenealogiesPersonState readParent(Relationship relationship, StateTransitionOption... options) {
    return (GenealogiesPersonState) super.readParent(relationship, options);
  }

  @Override
  public GenealogiesPersonState restore(StateTransitionOption... options) {
    return (GenealogiesPersonState) super.restore(options);
  }

  public GenealogiesPersonState doMerge(GenealogiesPersonState candidate, StateTransitionOption... options) {
    Link link = getLink(Rel.MERGE);
    if (link == null || link.getTemplate() == null) {
      return null;
    }
    Person person = getPerson();
    if (person == null || person.getId() == null) {
      throw new IllegalArgumentException("Cannot merge: no person id available.");
    }
    String personId = person.getId();

    person = candidate.getPerson();
    if (person == null || person.getId() == null) {
      throw new IllegalArgumentException("Cannot merge: no person id provided on the candidate.");
    }
    String candidateId = person.getId();

    String template = link.getTemplate();

    String uri;
    try {
      uri = UriTemplate.fromTemplate(template).set("pid", personId).set("dpid", candidateId).expand();
    }
    catch (VariableExpansionException e) {
      throw new GedcomxApplicationException(e);
    }
    catch (MalformedUriTemplateException e) {
      throw new GedcomxApplicationException(e);
    }

    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest()).build(URI.create(uri), HttpMethod.POST);
    return clone(request, invoke(request, options));
  }

}
