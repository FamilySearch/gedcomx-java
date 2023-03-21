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
package org.gedcomx.rs.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import org.gedcomx.Gedcomx;
import org.gedcomx.common.EvidenceReference;
import org.gedcomx.common.Note;
import org.gedcomx.common.ResourceReference;
import org.gedcomx.conclusion.*;
import org.gedcomx.links.Link;
import org.gedcomx.links.SupportsLinks;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.rt.Rel;
import org.gedcomx.source.SourceDescription;
import org.gedcomx.source.SourceReference;
import org.gedcomx.types.RelationshipType;

import jakarta.activation.DataSource;
import jakarta.ws.rs.HttpMethod;
import jakarta.ws.rs.core.MultivaluedMap;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author Ryan Heaton
 */
public class PersonState extends GedcomxApplicationState<Gedcomx> {

  public PersonState(URI uri) {
    this(uri, new StateFactory());
  }

  private PersonState(URI uri, StateFactory stateFactory) {
    this(uri, stateFactory.loadDefaultClient(), stateFactory);
  }

  private PersonState(URI uri, Client client, StateFactory stateFactory) {
    this(ClientRequest.create().accept(GedcomxConstants.GEDCOMX_JSON_MEDIA_TYPE).build(uri, HttpMethod.GET), client, stateFactory);
  }

  private PersonState(ClientRequest request, Client client, StateFactory stateFactory) {
    this(request, client.handle(request), null, stateFactory);
  }

  protected PersonState(ClientRequest request, ClientResponse response, String accessToken, StateFactory stateFactory) {
    super(request, response, accessToken, stateFactory);
  }

  @Override
  public String getSelfRel() {
    return Rel.PERSON;
  }

  @Override
  protected PersonState clone(ClientRequest request, ClientResponse response) {
    return new PersonState(request, response, this.accessToken, this.stateFactory);
  }

  @Override
  public PersonState ifSuccessful() {
    return (PersonState) super.ifSuccessful();
  }

  @Override
  public PersonState head(StateTransitionOption... options) {
    return (PersonState) super.head(options);
  }

  @Override
  public PersonState get(StateTransitionOption... options) {
    return (PersonState) super.get(options);
  }

  @Override
  public PersonState delete(StateTransitionOption... options) {
    return (PersonState) super.delete(options);
  }

  @Override
  public PersonState put(Gedcomx e, StateTransitionOption... options) {
    return (PersonState) super.put(e, options);
  }

  @Override
  public PersonState post(Gedcomx entity, StateTransitionOption... options) {
    return (PersonState) super.post(entity, options);
  }

  @Override
  public PersonState options(StateTransitionOption... options) {
    return (PersonState) super.options(options);
  }

  @Override
  protected Gedcomx loadEntity(ClientResponse response) {
    return response.getEntity(Gedcomx.class);
  }

  @Override
  protected SupportsLinks getMainDataElement() {
    return getPerson();
  }

  public Person getPerson() {
    return getEntity() == null ? null : getEntity().getPersons() == null ? null : getEntity().getPersons().isEmpty() ? null : getEntity().getPersons().get(0);
  }

  public List<Relationship> getRelationships() {
    return getEntity() == null ? null : getEntity().getRelationships();
  }

  public List<Relationship> getSpouseRelationships() {
    List<Relationship> relationships = getRelationships();
    relationships = relationships == null ? null : new ArrayList<Relationship>(relationships);
    if (relationships != null) {
      Iterator<Relationship> it = relationships.iterator();
      while (it.hasNext()) {
        if (it.next().getKnownType() != RelationshipType.Couple) {
          it.remove();
        }
      }
    }
    return relationships;
  }

  public List<Relationship> getChildRelationships() {
    List<Relationship> relationships = getRelationships();
    relationships = relationships == null ? null : new ArrayList<Relationship>(relationships);
    if (relationships != null) {
      Iterator<Relationship> it = relationships.iterator();
      while (it.hasNext()) {
        Relationship relationship = it.next();
        if (relationship.getKnownType() != RelationshipType.ParentChild || !refersToMe(relationship.getPerson1())) {
          it.remove();
        }
      }
    }
    return relationships;
  }

  public List<Relationship> getParentRelationships() {
    List<Relationship> relationships = getRelationships();
    relationships = relationships == null ? null : new ArrayList<Relationship>(relationships);
    if (relationships != null) {
      Iterator<Relationship> it = relationships.iterator();
      while (it.hasNext()) {
        Relationship relationship = it.next();
        if (relationship.getKnownType() != RelationshipType.ParentChild || !refersToMe(relationship.getPerson2())) {
          it.remove();
        }
      }
    }
    return relationships;
  }

  protected boolean refersToMe(ResourceReference ref) {
    return ref != null && ref.getResource() != null && ref.getResource().toString().equals("#" + getLocalSelfId());
  }

  public DisplayProperties getDisplayProperties() {
    Person person = getPerson();
    return person == null ? null : person.getDisplayExtension();
  }

  public Conclusion getConclusion() {
    return getName() != null ? getName() : getGender() != null ? getGender() : getFact() != null ? getFact() : null;
  }

  public Name getName() {
    Person person = getPerson();
    return person == null ? null : person.getNames() == null ? null : person.getNames().isEmpty() ? null : person.getNames().get(0);
  }

  public Gender getGender() {
    Person person = getPerson();
    return person == null ? null : person.getGender();
  }

  public Fact getFact() {
    Person person = getPerson();
    return person == null ? null : person.getFacts() == null ? null : person.getFacts().isEmpty() ? null : person.getFacts().get(0);
  }

  public Note getNote() {
    Person person = getPerson();
    return person == null ? null : person.getNotes() == null ? null : person.getNotes().isEmpty() ? null : person.getNotes().get(0);
  }

  public SourceReference getSourceReference() {
    Person person = getPerson();
    return person == null ? null : person.getSources() == null ? null : person.getSources().isEmpty() ? null : person.getSources().get(0);
  }

  public EvidenceReference getEvidenceReference() {
    Person person = getPerson();
    return person == null ? null : person.getEvidence() == null ? null : person.getEvidence().isEmpty() ? null : person.getEvidence().get(0);
  }

  public EvidenceReference getPersonaReference() {
    return getEvidenceReference();
  }

  public SourceReference getMediaReference() {
    Person person = getPerson();
    return person == null ? null : person.getMedia() == null ? null : person.getMedia().isEmpty() ? null : person.getMedia().get(0);
  }

  @Override
  public PersonState authenticateViaOAuth2Password(String username, String password, String clientId) {
    return (PersonState) super.authenticateViaOAuth2Password(username, password, clientId);
  }

  @Override
  public PersonState authenticateViaOAuth2Password(String username, String password, String clientId, String clientSecret) {
    return (PersonState) super.authenticateViaOAuth2Password(username, password, clientId, clientSecret);
  }

  @Override
  public PersonState authenticateViaOAuth2AuthCode(String authCode, String redirect, String clientId) {
    return (PersonState) super.authenticateViaOAuth2AuthCode(authCode, redirect, clientId);
  }

  @Override
  public PersonState authenticateViaOAuth2AuthCode(String authCode, String redirect, String clientId, String clientSecret) {
    return (PersonState) super.authenticateViaOAuth2AuthCode(authCode, redirect, clientId, clientSecret);
  }

  @Override
  public PersonState authenticateViaOAuth2ClientCredentials(String clientId, String clientSecret) {
    return (PersonState) super.authenticateViaOAuth2ClientCredentials(clientId, clientSecret);
  }

  @Override
  public PersonState authenticateWithAccessToken(String accessToken) {
    return (PersonState) super.authenticateWithAccessToken(accessToken);
  }

  @Override
  public PersonState authenticateViaOAuth2(MultivaluedMap<String, String> formData, StateTransitionOption... options) {
    return (PersonState) super.authenticateViaOAuth2(formData, options);
  }

  public CollectionState readCollection(StateTransitionOption... options) {
    Link link = getLink(Rel.COLLECTION);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return this.stateFactory.newCollectionState(request, invoke(request, options), this.accessToken);
  }

  public AncestryResultsState readAncestry(StateTransitionOption... options) {
    Link link = getLink(Rel.ANCESTRY);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return this.stateFactory.newAncestryResultsState(request, invoke(request, options), this.accessToken);
  }

  public DescendancyResultsState readDescendancy(StateTransitionOption... options) {
    Link link = getLink(Rel.DESCENDANCY);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return this.stateFactory.newDescendancyResultsState(request, invoke(request, options), this.accessToken);
  }

  public SourceDescriptionsState readSourceDescriptions(StateTransitionOption... options) {
    Link link = getLink(Rel.SOURCE_DESCRIPTIONS);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return this.stateFactory.newSourceDescriptionsState(request, invoke(request, options), this.accessToken);
  }

  public PersonState loadEmbeddedResources(StateTransitionOption... options) {
    includeEmbeddedResources(this.entity, options);
    return this;
  }

  public PersonState loadEmbeddedResources(String[] rels, StateTransitionOption... options) {
    for (String rel : rels) {
      Link link = getLink(rel);
      if (this.entity != null && link != null && link.getHref() != null) {
        embed(link, this.entity, options);
      }
    }
    return this;
  }

  public PersonState loadConclusions(StateTransitionOption... options) {
    return loadEmbeddedResources(new String[]{Rel.CONCLUSIONS}, options);
  }

  public PersonState loadSourceReferences(StateTransitionOption... options) {
    return loadEmbeddedResources(new String[]{Rel.SOURCE_REFERENCES}, options);
  }

  public PersonState loadMediaReferences(StateTransitionOption... options) {
    return loadEmbeddedResources(new String[]{Rel.MEDIA_REFERENCES}, options);
  }

  public PersonState loadEvidenceReferences(StateTransitionOption... options) {
    return loadEmbeddedResources(new String[]{Rel.EVIDENCE_REFERENCES}, options);
  }

  public PersonState loadPersonaReferences(StateTransitionOption... options) {
    return loadEvidenceReferences(options);
  }

  public PersonState loadNotes(StateTransitionOption... options) {
    return loadEmbeddedResources(new String[]{Rel.NOTES}, options);
  }

  public PersonState loadParentRelationships(StateTransitionOption... options) {
    return loadEmbeddedResources(new String[]{Rel.PARENT_RELATIONSHIPS}, options);
  }

  public PersonState loadSpouseRelationships(StateTransitionOption... options) {
    return loadEmbeddedResources(new String[]{Rel.SPOUSE_RELATIONSHIPS}, options);
  }

  public PersonState loadChildRelationships(StateTransitionOption... options) {
    return loadEmbeddedResources(new String[]{Rel.CHILD_RELATIONSHIPS}, options);
  }

  protected Person createEmptySelf() {
    Person person = new Person();
    person.setId(getLocalSelfId());
    return person;
  }

  protected String getLocalSelfId() {
    Person me = getPerson();
    return me == null ? null : me.getId();
  }

  public PersonState update(Person person, StateTransitionOption... options) {
    if (getLink(Rel.CONCLUSIONS) != null && (person.getNames() != null || person.getFacts() != null || person.getGender() != null)) {
      updateConclusions(person);
    }

    if (getLink(Rel.EVIDENCE_REFERENCES) != null && person.getEvidence() != null) {
      updateEvidenceReferences(person);
    }

    if (getLink(Rel.MEDIA_REFERENCES) != null && person.getMedia() != null) {
      updateMediaReferences(person);
    }

    if (getLink(Rel.SOURCE_REFERENCES) != null && person.getSources() != null) {
      updateSourceReferences(person);
    }

    if (getLink(Rel.NOTES) != null && person.getNotes() != null) {
      updateNotes(person);
    }

    Gedcomx gx = new Gedcomx();
    gx.setPersons(Arrays.asList(person));
    ClientRequest request = createAuthenticatedGedcomxRequest().entity(gx).build(getSelfUri(), HttpMethod.POST);
    return this.stateFactory.newPersonState(request, invoke(request, options), this.accessToken);
  }

  public PersonState addGender(Gender gender, StateTransitionOption... options) {
    Person person = createEmptySelf();
    person.setGender(gender);
    return updateConclusions(person, options);
  }

  public PersonState addName(Name name, StateTransitionOption... options) {
    return addNames(new Name[]{name}, options);
  }

  public PersonState addNames(Name[] names, StateTransitionOption... options) {
    Person person = createEmptySelf();
    person.setNames(Arrays.asList(names));
    return updateConclusions(person, options);
  }

  public PersonState addFact(Fact fact, StateTransitionOption... options) {
    return addFacts(new Fact[]{fact}, options);
  }

  public PersonState addFacts(Fact[] facts, StateTransitionOption... options) {
    Person person = createEmptySelf();
    person.setFacts(Arrays.asList(facts));
    return updateConclusions(person, options);
  }

  public PersonState updateGender(Gender gender, StateTransitionOption... options) {
    Person person = createEmptySelf();
    person.setGender(gender);
    return updateConclusions(person, options);
  }

  public PersonState updateName(Name name, StateTransitionOption... options) {
    return updateNames(new Name[]{name}, options);
  }

  public PersonState updateNames(Name[] names, StateTransitionOption... options) {
    Person person = createEmptySelf();
    person.setNames(Arrays.asList(names));
    return updateConclusions(person);
  }

  public PersonState updateFact(Fact fact, StateTransitionOption... options) {
    return updateFacts(new Fact[]{fact}, options);
  }

  public PersonState updateFacts(Fact[] facts, StateTransitionOption... options) {
    Person person = createEmptySelf();
    person.setFacts(Arrays.asList(facts));
    return updateConclusions(person, options);
  }

  public PersonState updateConclusions(Person person, StateTransitionOption... options) {
    Gedcomx gx = new Gedcomx();
    gx.setPersons(Arrays.asList(person));

    return updateConclusions(gx, options);
  }

  public PersonState updateConclusions(Gedcomx gx, StateTransitionOption... options) {
    URI target = getSelfUri();
    Link conclusionsLink = getLink(Rel.CONCLUSIONS);
    if (conclusionsLink != null && conclusionsLink.getHref() != null) {
      target = conclusionsLink.getHref().toURI();
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().entity(gx).build(target, HttpMethod.POST);
    return this.stateFactory.newPersonState(request, invoke(request, options), this.accessToken);
  }

  public PersonState deleteName(Name name, StateTransitionOption... options) {
    return doDeleteConclusion(name, options);
  }

  public PersonState deleteGender(Gender gender, StateTransitionOption... options) {
    return doDeleteConclusion(gender, options);
  }

  public PersonState deleteFact(Fact fact, StateTransitionOption... options) {
    return doDeleteConclusion(fact, options);
  }

  protected PersonState doDeleteConclusion(Conclusion conclusion, StateTransitionOption... options) {
    Link link = conclusion.getLink(Rel.CONCLUSION);
    link = link == null ? conclusion.getLink(Rel.SELF) : link;
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException("Conclusion cannot be deleted: missing link.");
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.DELETE);
    return this.stateFactory.newPersonState(request, invoke(request, options), this.accessToken);
  }

  public PersonState addSourceReference(SourceDescriptionState source, StateTransitionOption... options) {
    SourceReference reference = new SourceReference();
    reference.setDescriptionRef(new org.gedcomx.common.URI(source.getSelfUri().toString()));
    return addSourceReference(reference, options);
  }

  public PersonState addSourceReference(RecordState source, StateTransitionOption... options) {
    SourceReference reference = new SourceReference();
    reference.setDescriptionRef(new org.gedcomx.common.URI(source.getSelfUri().toString()));
    return addSourceReference(reference, options);
  }

  public PersonState addSourceReference(SourceReference reference, StateTransitionOption... options) {
    return addSourceReferences(new SourceReference[]{reference}, options);
  }

  public PersonState addSourceReferences(SourceReference[] refs, StateTransitionOption... options) {
    Person person = createEmptySelf();
    person.setSources(Arrays.asList(refs));
    return updateSourceReferences(person, options);
  }

  public PersonState updateSourceReference(SourceReference reference, StateTransitionOption... options) {
    return updateSourceReferences(new SourceReference[]{reference}, options);
  }

  public PersonState updateSourceReferences(SourceReference[] refs, StateTransitionOption... options) {
    Person person = createEmptySelf();
    person.setSources(Arrays.asList(refs));
    return updateSourceReferences(person, options);
  }

  public PersonState updateSourceReferences(Person person, StateTransitionOption... options) {
    URI target = getSelfUri();
    Link conclusionsLink = getLink(Rel.SOURCE_REFERENCES);
    if (conclusionsLink != null && conclusionsLink.getHref() != null) {
      target = conclusionsLink.getHref().toURI();
    }

    Gedcomx gx = new Gedcomx();
    gx.setPersons(Arrays.asList(person));
    ClientRequest request = createAuthenticatedGedcomxRequest().entity(gx).build(target, HttpMethod.POST);
    return this.stateFactory.newPersonState(request, invoke(request, options), this.accessToken);
  }

  public PersonState deleteSourceReference(SourceReference reference, StateTransitionOption... options) {
    Link link = reference.getLink(Rel.SOURCE_REFERENCE);
    link = link == null ? reference.getLink(Rel.SELF) : link;
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException("Source reference cannot be deleted: missing link.");
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.DELETE);
    return this.stateFactory.newPersonState(request, invoke(request, options), this.accessToken);
  }

  public SourceDescriptionState readSourceDescription(SourceReference sourceReference, StateTransitionOption... options) {
    Link link = sourceReference.getLink(Rel.DESCRIPTION);
    link = link == null ? sourceReference.getLink(Rel.SELF) : link;
    org.gedcomx.common.URI href;
    if (link != null) {
      href = link.getHref();
    }
    else {
      href = sourceReference.getDescriptionRef();
    }
    if (href == null) {
      throw new GedcomxApplicationException("Source description cannot be read: missing link.");
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(href.toURI(), HttpMethod.GET);
    return this.stateFactory.newSourceDescriptionState(request, invoke(request, options), this.accessToken);
  }

  public SourceDescriptionsState readArtifacts(StateTransitionOption... options) {
    Link link = getLink(Rel.ARTIFACTS);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return this.stateFactory.newSourceDescriptionsState(request, invoke(request, options), this.accessToken);
  }

  public SourceDescriptionState addArtifact(DataSource artifact, StateTransitionOption... options) {
    return addArtifact(null, artifact, options);
  }

  public SourceDescriptionState addArtifact(SourceDescription description, DataSource artifact, StateTransitionOption... options) {
    return CollectionState.addArtifact(this, description, artifact, options);
  }

  public PersonState addMediaReference(SourceDescriptionState description, StateTransitionOption... options) {
    SourceReference reference = new SourceReference();
    reference.setDescriptionRef(new org.gedcomx.common.URI(description.getSelfUri().toString()));
    return addMediaReference(reference, options);
  }

  public PersonState addMediaReference(SourceReference reference, StateTransitionOption... options) {
    return addMediaReferences(new SourceReference[]{reference}, options);
  }

  public PersonState addMediaReferences(SourceReference[] refs, StateTransitionOption... options) {
    Person person = createEmptySelf();
    person.setMedia(Arrays.asList(refs));
    return updateMediaReferences(person, options);
  }

  public PersonState updateMediaReference(SourceReference reference, StateTransitionOption... options) {
    return updateMediaReferences(new SourceReference[]{reference}, options);
  }

  public PersonState updateMediaReferences(SourceReference[] refs, StateTransitionOption... options) {
    Person person = createEmptySelf();
    person.setMedia(Arrays.asList(refs));
    return updateMediaReferences(person, options);
  }

  public PersonState updateMediaReferences(Person person, StateTransitionOption... options) {
    URI target = getSelfUri();
    Link conclusionsLink = getLink(Rel.MEDIA_REFERENCES);
    if (conclusionsLink != null && conclusionsLink.getHref() != null) {
      target = conclusionsLink.getHref().toURI();
    }

    Gedcomx gx = new Gedcomx();
    gx.setPersons(Arrays.asList(person));
    ClientRequest request = createAuthenticatedGedcomxRequest().entity(gx).build(target, HttpMethod.POST);
    return this.stateFactory.newPersonState(request, invoke(request, options), this.accessToken);
  }

  public PersonState deleteMediaReference(SourceReference reference, StateTransitionOption... options) {
    Link link = reference.getLink(Rel.MEDIA_REFERENCE);
    link = link == null ? reference.getLink(Rel.SELF) : link;
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException("Media reference cannot be deleted: missing link.");
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.DELETE);
    return this.stateFactory.newPersonState(request, invoke(request, options), this.accessToken);
  }

  public PersonState addEvidenceReference(PersonState evidence, StateTransitionOption... options) {
    EvidenceReference reference = new EvidenceReference();
    reference.setResource(new org.gedcomx.common.URI(evidence.getSelfUri().toString()));
    return addEvidenceReference(reference, options);
  }

  public PersonState addEvidenceReference(EvidenceReference reference, StateTransitionOption... options) {
    return addEvidenceReferences(new EvidenceReference[]{reference}, options);
  }

  public PersonState addEvidenceReferences(EvidenceReference[] refs, StateTransitionOption... options) {
    Person person = createEmptySelf();
    person.setEvidence(Arrays.asList(refs));
    return updateEvidenceReferences(person, options);
  }

  public PersonState updateEvidenceReference(EvidenceReference reference, StateTransitionOption... options) {
    return updateEvidenceReferences(new EvidenceReference[]{reference}, options);
  }

  public PersonState updateEvidenceReferences(EvidenceReference[] refs, StateTransitionOption... options) {
    Person person = createEmptySelf();
    person.setEvidence(Arrays.asList(refs));
    return updateEvidenceReferences(person, options);
  }

  public PersonState updateEvidenceReferences(Person person, StateTransitionOption... options) {
    URI target = getSelfUri();
    Link conclusionsLink = getLink(Rel.EVIDENCE_REFERENCES);
    if (conclusionsLink != null && conclusionsLink.getHref() != null) {
      target = conclusionsLink.getHref().toURI();
    }

    Gedcomx gx = new Gedcomx();
    gx.setPersons(Arrays.asList(person));
    ClientRequest request = createAuthenticatedGedcomxRequest().entity(gx).build(target, HttpMethod.POST);
    return this.stateFactory.newPersonState(request, invoke(request, options), this.accessToken);
  }

  public PersonState deleteEvidenceReference(EvidenceReference reference, StateTransitionOption... options) {
    Link link = reference.getLink(Rel.EVIDENCE_REFERENCE);
    link = link == null ? reference.getLink(Rel.SELF) : link;
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException("Evidence reference cannot be deleted: missing link.");
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.DELETE);
    return this.stateFactory.newPersonState(request, invoke(request, options), this.accessToken);
  }

  public PersonState addPersonaReference(PersonState persona, StateTransitionOption... options) {
    return addEvidenceReference(persona, options);
  }

  public PersonState addPersonaReference(EvidenceReference reference, StateTransitionOption... options) {
    return addEvidenceReference(reference, options);
  }

  public PersonState addPersonaReferences(EvidenceReference[] refs, StateTransitionOption... options) {
    return addEvidenceReferences(refs, options);
  }

  public PersonState updatePersonaReference(EvidenceReference reference, StateTransitionOption... options) {
    return updateEvidenceReference(reference, options);
  }

  public PersonState updatePersonaReferences(EvidenceReference[] refs, StateTransitionOption... options) {
    return updateEvidenceReferences(refs, options);
  }

  public PersonState updatePersonaReferences(Person person, StateTransitionOption... options) {
    return updateEvidenceReferences(person, options);
  }

  public PersonState deletePersonaReference(EvidenceReference reference, StateTransitionOption... options) {
    return deleteEvidenceReference(reference, options);
  }

  public PersonState readNote(Note note, StateTransitionOption... options) {
    Link link = note.getLink(Rel.NOTE);
    link = link == null ? note.getLink(Rel.SELF) : link;
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException("Note cannot be read: missing link.");
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return this.stateFactory.newPersonState(request, invoke(request, options), this.accessToken);
  }

  public PersonState addNote(Note note, StateTransitionOption... options) {
    return addNotes(new Note[]{note}, options);
  }

  public PersonState addNotes(Note[] notes, StateTransitionOption... options) {
    Person person = createEmptySelf();
    person.setNotes(Arrays.asList(notes));
    return updateNotes(person, options);
  }

  public PersonState updateNote(Note note, StateTransitionOption... options) {
    return updateNotes(new Note[]{note}, options);
  }

  public PersonState updateNotes(Note[] notes, StateTransitionOption... options) {
    Person person = createEmptySelf();
    person.setNotes(Arrays.asList(notes));
    return updateNotes(person, options);
  }

  public PersonState updateNotes(Person person, StateTransitionOption... options) {
    URI target = getSelfUri();
    Link conclusionsLink = getLink(Rel.NOTES);
    if (conclusionsLink != null && conclusionsLink.getHref() != null) {
      target = conclusionsLink.getHref().toURI();
    }

    Gedcomx gx = new Gedcomx();
    gx.setPersons(Arrays.asList(person));
    ClientRequest request = createAuthenticatedGedcomxRequest().entity(gx).build(target, HttpMethod.POST);
    return this.stateFactory.newPersonState(request, invoke(request, options), this.accessToken);
  }

  public PersonState deleteNote(Note note, StateTransitionOption... options) {
    Link link = note.getLink(Rel.NOTE);
    link = link == null ? note.getLink(Rel.SELF) : link;
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException("Note cannot be deleted: missing link.");
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.DELETE);
    return this.stateFactory.newPersonState(request, invoke(request, options), this.accessToken);
  }

  public RelationshipState readRelationship(Relationship relationship, StateTransitionOption... options) {
    Link link = relationship.getLink(Rel.RELATIONSHIP);
    link = link == null ? relationship.getLink(Rel.SELF) : link;
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return this.stateFactory.newRelationshipState(request, invoke(request, options), this.accessToken);
  }

  public PersonState readRelative(Relationship relationship, StateTransitionOption... options) {
    ResourceReference reference = null;
    if (refersToMe(relationship.getPerson1())) {
      reference = relationship.getPerson2();
    }
    else if (refersToMe(relationship.getPerson2())) {
      reference = relationship.getPerson1();
    }
    if (reference == null || reference.getResource() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(reference.getResource().toURI(), HttpMethod.GET);
    return this.stateFactory.newPersonState(request, invoke(request, options), this.accessToken);
  }

  public PersonState readFirstSpouse(StateTransitionOption... options) {
    return readSpouse(0, options);
  }

  public PersonState readSpouse(int index, StateTransitionOption... options) {
    List<Relationship> spouseRelationships = getSpouseRelationships();
    if (spouseRelationships.size() <= index) {
      return null;
    }
    return readSpouse(spouseRelationships.get(index), options);
  }

  public PersonState readSpouse(Relationship relationship, StateTransitionOption... options) {
    return readRelative(relationship, options);
  }

  public PersonSpousesState readSpouses(StateTransitionOption... options) {
    Link link = getLink(Rel.SPOUSES);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return this.stateFactory.newPersonSpousesState(request, invoke(request, options), this.accessToken);
  }

  public RelationshipState addSpouse(PersonState person, StateTransitionOption... options) {
    CollectionState collection = readCollection();
    if (collection == null || collection.hasError()) {
      throw new GedcomxApplicationException("Unable to add relationship: collection unavailable.");
    }

    return collection.addSpouseRelationship(this, person, options);
  }

  public PersonState readFirstChild(StateTransitionOption... options) {
    return readChild(0, options);
  }

  public PersonState readChild(int index, StateTransitionOption... options) {
    List<Relationship> childRelationships = getChildRelationships();
    if (childRelationships.size() <= index) {
      return null;
    }
    return readChild(childRelationships.get(index), options);
  }

  public PersonState readChild(Relationship relationship, StateTransitionOption... options) {
    return readRelative(relationship, options);
  }

  public PersonChildrenState readChildren(StateTransitionOption... options) {
    Link link = getLink(Rel.CHILDREN);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return this.stateFactory.newPersonChildrenState(request, invoke(request, options), this.accessToken);
  }

  public RelationshipState addChild(PersonState person, StateTransitionOption... options) {
    CollectionState collection = readCollection();
    if (collection == null || collection.hasError()) {
      throw new GedcomxApplicationException("Unable to add relationship: collection unavailable.");
    }

    return collection.addParentChildRelationship(this, person, options);
  }

  public PersonState readFirstParent(StateTransitionOption... options) {
    return readParent(0, options);
  }

  public PersonState readParent(int index, StateTransitionOption... options) {
    List<Relationship> parentRelationships = getParentRelationships();
    if (parentRelationships.size() <= index) {
      return null;
    }
    return readParent(parentRelationships.get(index), options);
  }

  public PersonState readParent(Relationship relationship, StateTransitionOption... options) {
    return readRelative(relationship, options);
  }

  public PersonParentsState readParents(StateTransitionOption... options) {
    Link link = getLink(Rel.PARENTS);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return this.stateFactory.newPersonParentsState(request, invoke(request, options), this.accessToken);
  }

  public RelationshipState addParent(PersonState person, StateTransitionOption... options) {
    CollectionState collection = readCollection();
    if (collection == null || collection.hasError()) {
      throw new GedcomxApplicationException("Unable to add relationship: collection unavailable.");
    }

    return collection.addParentChildRelationship(person, this, options);
  }


}
