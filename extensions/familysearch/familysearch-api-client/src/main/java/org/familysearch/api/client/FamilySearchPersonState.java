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
package org.familysearch.api.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import org.familysearch.api.client.util.RequestUtil;
import org.familysearch.platform.FamilySearchPlatform;
import org.familysearch.platform.artifacts.ArtifactMetadata;
import org.familysearch.platform.artifacts.ArtifactType;
import org.familysearch.platform.ct.DiscussionReference;
import org.gedcomx.Gedcomx;
import org.gedcomx.common.EvidenceReference;
import org.gedcomx.common.Note;
import org.gedcomx.conclusion.*;
import org.gedcomx.links.Link;
import org.gedcomx.rs.client.*;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.source.SourceDescription;
import org.gedcomx.source.SourceReference;

import javax.activation.DataSource;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MultivaluedMap;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.familysearch.api.client.util.FamilySearchOptions.artifactType;

/**
 * @author Ryan Heaton
 */
public class FamilySearchPersonState extends PersonState {

  private final String selfRel;

  public FamilySearchPersonState(URI uri) {
    this(uri, new FamilySearchStateFactory());
  }

  protected FamilySearchPersonState(URI uri, FamilySearchStateFactory stateFactory) {
    this(uri, stateFactory.loadDefaultClient(), stateFactory);
  }

  private FamilySearchPersonState(URI uri, Client client, FamilySearchStateFactory stateFactory) {
    this(ClientRequest.create().accept(GedcomxConstants.GEDCOMX_JSON_MEDIA_TYPE).build(uri, HttpMethod.GET), client, stateFactory);
  }

  private FamilySearchPersonState(ClientRequest request, Client client, FamilySearchStateFactory stateFactory) {
    this(request, client.handle(request), null, stateFactory);
  }

  protected FamilySearchPersonState(ClientRequest request, ClientResponse response, String accessToken, FamilySearchStateFactory stateFactory) {
    this(request, response, accessToken, Rel.PERSON, stateFactory);
  }

  protected FamilySearchPersonState(ClientRequest request, ClientResponse response, String accessToken, String selfRel, FamilySearchStateFactory stateFactory) {
    super(request, response, accessToken, stateFactory);
    this.selfRel = selfRel;
  }

  @Override
  protected FamilySearchPersonState clone(ClientRequest request, ClientResponse response) {
    return new FamilySearchPersonState(request, response, this.accessToken, this.selfRel, (FamilySearchStateFactory) this.stateFactory);
  }

  @Override
  public String getSelfRel() {
    return this.selfRel;
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

  public List<Person> getPersons() {
    return getEntity() == null ? null : getEntity().getPersons();
  }

  @Override
  public FamilySearchPersonState ifSuccessful() {
    return (FamilySearchPersonState) super.ifSuccessful();
  }

  @Override
  public FamilySearchPersonState head(StateTransitionOption... options) {
    return (FamilySearchPersonState) super.head(options);
  }

  @Override
  public FamilySearchPersonState get(StateTransitionOption... options) {
    return (FamilySearchPersonState) super.get(options);
  }

  @Override
  public FamilySearchPersonState delete(StateTransitionOption... options) {
    return (FamilySearchPersonState) super.delete(options);
  }

  @Override
  public FamilySearchPersonState put(Gedcomx e, StateTransitionOption... options) {
    return (FamilySearchPersonState) super.put(e, options);
  }

  @Override
  public FamilySearchPersonState post(Gedcomx entity, StateTransitionOption... options) {
    return (FamilySearchPersonState)super.post(entity, options);
  }

  @Override
  public FamilySearchPersonState authenticateViaOAuth2Password(String username, String password, String clientId) {
    return (FamilySearchPersonState) super.authenticateViaOAuth2Password(username, password, clientId);
  }

  @Override
  public FamilySearchPersonState authenticateViaOAuth2Password(String username, String password, String clientId, String clientSecret) {
    return (FamilySearchPersonState) super.authenticateViaOAuth2Password(username, password, clientId, clientSecret);
  }

  @Override
  public FamilySearchPersonState authenticateViaOAuth2AuthCode(String authCode, String redirect, String clientId) {
    return (FamilySearchPersonState) super.authenticateViaOAuth2AuthCode(authCode, redirect, clientId);
  }

  @Override
  public FamilySearchPersonState authenticateViaOAuth2AuthCode(String authCode, String redirect, String clientId, String clientSecret) {
    return (FamilySearchPersonState) super.authenticateViaOAuth2AuthCode(authCode, redirect, clientId, clientSecret);
  }

  @Override
  public FamilySearchPersonState authenticateViaOAuth2ClientCredentials(String clientId, String clientSecret) {
    return (FamilySearchPersonState) super.authenticateViaOAuth2ClientCredentials(clientId, clientSecret);
  }

  @Override
  public FamilySearchPersonState authenticateViaOAuth2(MultivaluedMap<String, String> formData, StateTransitionOption... options) {
    return (FamilySearchPersonState) super.authenticateViaOAuth2(formData, options);
  }

  @Override
  public FamilySearchCollectionState readCollection(StateTransitionOption... options) {
    return (FamilySearchCollectionState) super.readCollection(options);
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
  public FamilySearchPersonState loadEmbeddedResources(StateTransitionOption... options) {
    return (FamilySearchPersonState) super.loadEmbeddedResources(options);
  }

  @Override
  public FamilySearchPersonState loadEmbeddedResources(String[] rels, StateTransitionOption... options) {
    return (FamilySearchPersonState) super.loadEmbeddedResources(rels, options);
  }

  @Override
  public FamilySearchPersonState loadConclusions(StateTransitionOption... options) {
    return (FamilySearchPersonState) super.loadConclusions(options);
  }

  @Override
  public FamilySearchPersonState loadSourceReferences(StateTransitionOption... options) {
    return (FamilySearchPersonState) super.loadSourceReferences(options);
  }

  public FamilySearchPersonState loadDiscussionReferences(StateTransitionOption... options) {
    return (FamilySearchPersonState) super.loadEmbeddedResources(new String[]{org.gedcomx.rs.Rel.DISCUSSION_REFERENCES}, options);
  }

  @Override
  public FamilySearchPersonState loadEvidenceReferences(StateTransitionOption... options) {
    return (FamilySearchPersonState) super.loadEvidenceReferences(options);
  }

  @Override
  public FamilySearchPersonState loadMediaReferences(StateTransitionOption... options) {
    return (FamilySearchPersonState) super.loadMediaReferences(options);
  }

  @Override
  public FamilySearchPersonState loadNotes(StateTransitionOption... options) {
    return (FamilySearchPersonState) super.loadNotes(options);
  }

  @Override
  public FamilySearchPersonState loadParentRelationships(StateTransitionOption... options) {
    return (FamilySearchPersonState) super.loadParentRelationships(options);
  }

  @Override
  public FamilySearchPersonState loadSpouseRelationships(StateTransitionOption... options) {
    return (FamilySearchPersonState) super.loadSpouseRelationships(options);
  }

  @Override
  public FamilySearchPersonState loadChildRelationships(StateTransitionOption... options) {
    return (FamilySearchPersonState) super.loadChildRelationships(options);
  }

  @Override
  public FamilySearchPersonState addGender(Gender gender, StateTransitionOption... options) {
    return (FamilySearchPersonState) super.addGender(gender, options);
  }

  @Override
  public FamilySearchPersonState addName(Name name, StateTransitionOption... options) {
    return (FamilySearchPersonState) super.addName(name, options);
  }

  @Override
  public FamilySearchPersonState addNames(Name[] names, StateTransitionOption... options) {
    return (FamilySearchPersonState) super.addNames(names, options);
  }

  @Override
  public FamilySearchPersonState addFact(Fact fact, StateTransitionOption... options) {
    return (FamilySearchPersonState) super.addFact(fact, options);
  }

  @Override
  public FamilySearchPersonState addFacts(Fact[] facts, StateTransitionOption... options) {
    return (FamilySearchPersonState) super.addFacts(facts, options);
  }

  @Override
  public FamilySearchPersonState updateGender(Gender gender, StateTransitionOption... options) {
    return (FamilySearchPersonState) super.updateGender(gender, options);
  }

  @Override
  public FamilySearchPersonState updateName(Name name, StateTransitionOption... options) {
    return (FamilySearchPersonState) super.updateName(name, options);
  }

  @Override
  public FamilySearchPersonState updateNames(Name[] names, StateTransitionOption... options) {
    return (FamilySearchPersonState) super.updateNames(names, options);
  }

  @Override
  public FamilySearchPersonState updateFact(Fact fact, StateTransitionOption... options) {
    return (FamilySearchPersonState) super.updateFact(fact, options);
  }

  @Override
  public FamilySearchPersonState updateFacts(Fact[] facts, StateTransitionOption... options) {
    return (FamilySearchPersonState) super.updateFacts(facts, options);
  }

  @Override
  public FamilySearchPersonState updateConclusions(Person person, StateTransitionOption... options) {
    return (FamilySearchPersonState) super.updateConclusions(person, options);
  }

  @Override
  public FamilySearchPersonState updateConclusions(Gedcomx entity, StateTransitionOption... options) {
    return (FamilySearchPersonState) super.updateConclusions(entity, options);
  }

  @Override
  public FamilySearchPersonState deleteName(Name name, StateTransitionOption... options) {
    return (FamilySearchPersonState) super.deleteName(name, options);
  }

  @Override
  public FamilySearchPersonState deleteGender(Gender gender, StateTransitionOption... options) {
    return (FamilySearchPersonState) super.deleteGender(gender, options);
  }

  @Override
  public FamilySearchPersonState deleteFact(Fact fact, StateTransitionOption... options) {
    return (FamilySearchPersonState) super.deleteFact(fact, options);
  }

  @Override
  protected FamilySearchPersonState doDeleteConclusion(Conclusion conclusion, StateTransitionOption... options) {
    return (FamilySearchPersonState) super.doDeleteConclusion(conclusion, options);
  }

  @Override
  public FamilySearchPersonState addSourceReference(SourceDescriptionState source, StateTransitionOption... options) {
    return (FamilySearchPersonState) super.addSourceReference(source, options);
  }

  @Override
  public FamilySearchPersonState addSourceReference(RecordState source, StateTransitionOption... options) {
    return (FamilySearchPersonState) super.addSourceReference(source, options);
  }

  @Override
  public FamilySearchPersonState addSourceReference(SourceReference reference, StateTransitionOption... options) {
    return (FamilySearchPersonState) super.addSourceReference(reference, options);
  }

  @Override
  public FamilySearchPersonState addSourceReferences(SourceReference[] refs, StateTransitionOption... options) {
    return (FamilySearchPersonState) super.addSourceReferences(refs, options);
  }

  @Override
  public FamilySearchPersonState updateSourceReference(SourceReference reference, StateTransitionOption... options) {
    return (FamilySearchPersonState) super.updateSourceReference(reference, options);
  }

  @Override
  public FamilySearchPersonState updateSourceReferences(SourceReference[] refs, StateTransitionOption... options) {
    return (FamilySearchPersonState) super.updateSourceReferences(refs, options);
  }

  @Override
  public FamilySearchPersonState updateSourceReferences(Person person, StateTransitionOption... options) {
    return (FamilySearchPersonState) super.updateSourceReferences(person, options);
  }

  @Override
  public FamilySearchPersonState deleteSourceReference(SourceReference reference, StateTransitionOption... options) {
    return (FamilySearchPersonState) super.deleteSourceReference(reference, options);
  }

  public SourceDescriptionsState readPortraits(StateTransitionOption... options) {
    Link link = getLink(Rel.PORTRAITS);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return ((FamilySearchStateFactory)this.stateFactory).newSourceDescriptionsState(request, invoke(request, options), this.accessToken);
  }

  public ClientResponse readPortrait(StateTransitionOption... options) {
    Link link = getLink(Rel.PORTRAIT);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return invoke(request, options);
  }

  public FamilySearchPersonState addDiscussionReference(DiscussionState discussion, StateTransitionOption... options) {
    DiscussionReference reference = new DiscussionReference();
    reference.setResource(new org.gedcomx.common.URI(discussion.getSelfUri().toString()));
    return addDiscussionReference(reference, options);
  }

  public FamilySearchPersonState addDiscussionReference(DiscussionReference reference, StateTransitionOption... options) {
    return addDiscussionReference(new DiscussionReference[]{reference}, options);
  }

  public FamilySearchPersonState addDiscussionReference(DiscussionReference[] refs, StateTransitionOption... options) {
    Person person = createEmptySelf();
    for (DiscussionReference ref : refs) {
      person.addExtensionElement(ref);
    }
    return updateDiscussionReference(person, options);
  }

  public FamilySearchPersonState updateDiscussionReference(DiscussionReference reference, StateTransitionOption... options) {
    return updateDiscussionReference(new DiscussionReference[]{reference}, options);
  }

  public FamilySearchPersonState updateDiscussionReference(DiscussionReference[] refs, StateTransitionOption... options) {
    Person person = createEmptySelf();
    for (DiscussionReference ref : refs) {
      person.addExtensionElement(ref);
    }
    return updateDiscussionReference(person, options);
  }

  public FamilySearchPersonState updateDiscussionReference(Person person, StateTransitionOption... options) {
    URI target = getSelfUri();
    Link discussionsLink = getLink(org.gedcomx.rs.Rel.DISCUSSION_REFERENCES);
    if (discussionsLink != null && discussionsLink.getHref() != null) {
      target = discussionsLink.getHref().toURI();
    }

    Gedcomx gx = new Gedcomx();
    gx.setPersons(Arrays.asList(person));
    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedGedcomxRequest()).entity(gx).build(target, HttpMethod.POST);
    return ((FamilySearchStateFactory)this.stateFactory).newPersonState(request, invoke(request, options), this.accessToken);
  }

  public FamilySearchPersonState deleteDiscussionReference(DiscussionReference reference, StateTransitionOption... options) {
    Link link = reference.getLink(Rel.DISCUSSION_REFERENCE);
    link = link == null ? reference.getLink(org.gedcomx.rs.Rel.SELF) : link;
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException("Discussion reference cannot be deleted: missing link.");
    }

    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedGedcomxRequest()).build(link.getHref().toURI(), HttpMethod.DELETE);
    return ((FamilySearchStateFactory)this.stateFactory).newPersonState(request, invoke(request, options), this.accessToken);
  }

  @Override
  public FamilySearchPersonState addMediaReference(SourceDescriptionState description, StateTransitionOption... options) {
    return (FamilySearchPersonState) super.addMediaReference(description, options);
  }

  @Override
  public FamilySearchPersonState addMediaReference(SourceReference reference, StateTransitionOption... options) {
    return (FamilySearchPersonState) super.addMediaReference(reference, options);
  }

  @Override
  public FamilySearchPersonState addMediaReferences(SourceReference[] refs, StateTransitionOption... options) {
    return (FamilySearchPersonState) super.addMediaReferences(refs, options);
  }

  @Override
  public FamilySearchPersonState updateMediaReference(SourceReference reference, StateTransitionOption... options) {
    return (FamilySearchPersonState) super.updateMediaReference(reference, options);
  }

  @Override
  public FamilySearchPersonState updateMediaReferences(SourceReference[] refs, StateTransitionOption... options) {
    return (FamilySearchPersonState) super.updateMediaReferences(refs, options);
  }

  @Override
  public FamilySearchPersonState updateMediaReferences(Person person, StateTransitionOption... options) {
    return (FamilySearchPersonState) super.updateMediaReferences(person, options);
  }

  @Override
  public FamilySearchPersonState deleteMediaReference(SourceReference reference, StateTransitionOption... options) {
    return (FamilySearchPersonState) super.deleteMediaReference(reference, options);
  }

  @Override
  public SourceDescriptionState addArtifact(SourceDescription description, DataSource artifact, StateTransitionOption... options) {
    if (description != null) {
      ArtifactMetadata artifactMetadata = description.findExtensionOfType(ArtifactMetadata.class);
      if (artifactMetadata != null) {
        ArtifactType type = artifactMetadata.getKnownType();
        if (type != null) {
          ArrayList<StateTransitionOption> newOptions = new ArrayList<StateTransitionOption>(Arrays.asList(options));
          newOptions.add(artifactType(type));
          options = newOptions.toArray(new StateTransitionOption[newOptions.size()]);
        }
      }
    }
    return super.addArtifact(description, artifact, options);
  }

  @Override
  public FamilySearchPersonState addEvidenceReference(PersonState evidence, StateTransitionOption... options) {
    return (FamilySearchPersonState) super.addEvidenceReference(evidence, options);
  }

  @Override
  public FamilySearchPersonState addEvidenceReference(EvidenceReference reference, StateTransitionOption... options) {
    return (FamilySearchPersonState) super.addEvidenceReference(reference, options);
  }

  @Override
  public FamilySearchPersonState addEvidenceReferences(EvidenceReference[] refs, StateTransitionOption... options) {
    return (FamilySearchPersonState) super.addEvidenceReferences(refs, options);
  }

  @Override
  public FamilySearchPersonState updateEvidenceReference(EvidenceReference reference, StateTransitionOption... options) {
    return (FamilySearchPersonState) super.updateEvidenceReference(reference, options);
  }

  @Override
  public FamilySearchPersonState updateEvidenceReferences(EvidenceReference[] refs, StateTransitionOption... options) {
    return (FamilySearchPersonState) super.updateEvidenceReferences(refs, options);
  }

  @Override
  public FamilySearchPersonState updateEvidenceReferences(Person person, StateTransitionOption... options) {
    return (FamilySearchPersonState) super.updateEvidenceReferences(person, options);
  }

  @Override
  public FamilySearchPersonState deleteEvidenceReference(EvidenceReference reference, StateTransitionOption... options) {
    return (FamilySearchPersonState) super.deleteEvidenceReference(reference, options);
  }

  @Override
  public FamilySearchPersonState readNote(Note note, StateTransitionOption... options) {
    return (FamilySearchPersonState) super.readNote(note, options);
  }

  @Override
  public FamilySearchPersonState addNote(Note note, StateTransitionOption... options) {
    return (FamilySearchPersonState) super.addNote(note, options);
  }

  @Override
  public FamilySearchPersonState addNotes(Note[] notes, StateTransitionOption... options) {
    return (FamilySearchPersonState) super.addNotes(notes, options);
  }

  @Override
  public FamilySearchPersonState updateNote(Note note, StateTransitionOption... options) {
    return (FamilySearchPersonState) super.updateNote(note, options);
  }

  @Override
  public FamilySearchPersonState updateNotes(Person person, StateTransitionOption... options) {
    return (FamilySearchPersonState) super.updateNotes(person, options);
  }

  @Override
  public FamilySearchPersonState updateNotes(Note[] notes, StateTransitionOption... options) {
    return (FamilySearchPersonState) super.updateNotes(notes, options);
  }

  @Override
  public FamilySearchPersonState deleteNote(Note note, StateTransitionOption... options) {
    return (FamilySearchPersonState) super.deleteNote(note, options);
  }

  @Override
  public FamilySearchPersonState readRelative(Relationship relationship, StateTransitionOption... options) {
    return (FamilySearchPersonState) super.readRelative(relationship, options);
  }

  @Override
  public FamilySearchPersonState readFirstSpouse(StateTransitionOption... options) {
    return (FamilySearchPersonState) super.readFirstSpouse(options);
  }

  @Override
  public FamilySearchPersonState readSpouse(int index, StateTransitionOption... options) {
    return (FamilySearchPersonState) super.readSpouse(index, options);
  }

  @Override
  public FamilySearchPersonState readSpouse(Relationship relationship, StateTransitionOption... options) {
    return (FamilySearchPersonState) super.readSpouse(relationship, options);
  }

  @Override
  public FamilySearchPersonState readFirstChild(StateTransitionOption... options) {
    return (FamilySearchPersonState) super.readFirstChild(options);
  }

  @Override
  public FamilySearchPersonState readChild(int index, StateTransitionOption... options) {
    return (FamilySearchPersonState) super.readChild(index, options);
  }

  @Override
  public FamilySearchPersonState readChild(Relationship relationship, StateTransitionOption... options) {
    return (FamilySearchPersonState) super.readChild(relationship, options);
  }

  @Override
  public FamilySearchPersonState readFirstParent(StateTransitionOption... options) {
    return (FamilySearchPersonState) super.readFirstParent(options);
  }

  @Override
  public FamilySearchPersonState readParent(int index, StateTransitionOption... options) {
    return (FamilySearchPersonState) super.readParent(index, options);
  }

  @Override
  public FamilySearchPersonState readParent(Relationship relationship, StateTransitionOption... options) {
    return (FamilySearchPersonState) super.readParent(relationship, options);
  }

  public ChangeHistoryState readChangeHistory(StateTransitionOption... options) {
    Link link = getLink(Rel.CHANGE_HISTORY);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedFeedRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return ((FamilySearchStateFactory)this.stateFactory).newChangeHistoryState(request, invoke(request, options), this.accessToken);
  }

  public PersonMatchResultsState readMatches(StateTransitionOption... options) {
    Link link = getLink(Rel.MATCHES);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedFeedRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return ((FamilySearchStateFactory)this.stateFactory).newPersonMatchResultsState(request, invoke(request, options), this.accessToken);
  }

  public PersonNonMatchesState readNonMatches(StateTransitionOption... options) {
    Link link = getLink(Rel.NOT_A_MATCHES);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedFeedRequest()).build(link.getHref().toURI(), HttpMethod.GET);
    return ((FamilySearchStateFactory)this.stateFactory).newPersonNonMatchesState(request, invoke(request, options), this.accessToken);
  }

  public FamilySearchPersonState restore(StateTransitionOption... options) {
    Link link = getLink(Rel.RESTORE);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest()).build(link.getHref().toURI(), HttpMethod.POST);
    return ((FamilySearchStateFactory)this.stateFactory).newPersonState(request, invoke(request, options), this.accessToken);
  }

  public PersonNonMatchesState addNonMatch(FamilySearchPersonState person, StateTransitionOption... options) {
    return addNonMatch(person.getPerson(), options);
  }

  public PersonNonMatchesState addNonMatch(Person person, StateTransitionOption... options) {
    Link link = getLink(Rel.NOT_A_MATCHES);
    if (link == null || link.getHref() == null) {
      return null;
    }

    Gedcomx entity = new Gedcomx();
    entity.addPerson(person);
    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest()).entity(entity).build(link.getHref().toURI(), HttpMethod.POST);
    return ((FamilySearchStateFactory)this.stateFactory).newPersonNonMatchesState(request, invoke(request, options), this.accessToken);
  }

}
