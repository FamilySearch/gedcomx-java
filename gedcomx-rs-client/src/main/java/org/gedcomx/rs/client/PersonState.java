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

import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import org.gedcomx.Gedcomx;
import org.gedcomx.common.EvidenceReference;
import org.gedcomx.common.Note;
import org.gedcomx.common.ResourceReference;
import org.gedcomx.conclusion.*;
import org.gedcomx.links.Link;
import org.gedcomx.links.SupportsLinks;
import org.gedcomx.rs.Rel;
import org.gedcomx.source.SourceReference;
import org.gedcomx.types.RelationshipType;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MultivaluedMap;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author Ryan Heaton
 */
public class PersonState extends GedcomxApplicationState<Gedcomx> {

  protected PersonState(ClientRequest request, ClientResponse response, String accessToken, StateFactory stateFactory) {
    super(request, response, accessToken, stateFactory);
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
  public PersonState head() {
    return (PersonState) super.head();
  }

  @Override
  public PersonState get() {
    return (PersonState) super.get();
  }

  @Override
  public PersonState delete() {
    return (PersonState) super.delete();
  }

  @Override
  public PersonState put(Gedcomx e) {
    return (PersonState) super.put(e);
  }

  @Override
  public PersonState options() {
    return (PersonState) super.options();
  }

  @Override
  protected Gedcomx loadEntity(ClientResponse response) {
    return response.getEntity(Gedcomx.class);
  }

  @Override
  protected SupportsLinks getScope() {
    return getPerson();
  }

  @Override
  public PersonState withLocales(String...locales) {
    return (PersonState) super.withLocales(locales);
  }

  @Override
  public PersonState withAccessToken(String accessToken) {
    return (PersonState) super.withAccessToken(accessToken);
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
  public PersonState authenticateViaOAuth2(MultivaluedMap<String, String> formData) {
    return (PersonState) super.authenticateViaOAuth2(formData);
  }

  public CollectionState readCollection() {
    Link link = getLink(Rel.COLLECTION);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return this.stateFactory.newCollectionState(request, invoke(request), this.accessToken);
  }

  public AncestryResultsState readAncestry() {
    Link link = getLink(Rel.ANCESTRY);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return this.stateFactory.newAncestryResultsState(request, invoke(request), this.accessToken);
  }

  public DescendancyResultsState readDescendancy() {
    Link link = getLink(Rel.DESCENDANCY);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return this.stateFactory.newDescendancyResultsState(request, invoke(request), this.accessToken);
  }

  public PersonState loadEmbeddedResources() {
    includeEmbeddedResources(this.entity);
    return this;
  }

  public PersonState loadEmbeddedResources(String... rels) {
    for (String rel : rels) {
      Link link = getLink(rel);
      if (this.entity != null && link != null && link.getHref() != null) {
        embed(link, this.entity);
      }
    }
    return this;
  }

  public PersonState loadConclusions() {
    return loadEmbeddedResources(Rel.CONCLUSIONS);
  }

  public PersonState loadSourceReferences() {
    return loadEmbeddedResources(Rel.SOURCE_REFERENCES);
  }

  public PersonState loadMediaReferences() {
    return loadEmbeddedResources(Rel.MEDIA_REFERENCES);
  }

  public PersonState loadEvidenceReferences() {
    return loadEmbeddedResources(Rel.EVIDENCE_REFERENCES);
  }

  public PersonState loadNotes() {
    return loadEmbeddedResources(Rel.EVIDENCE_REFERENCES);
  }

  public PersonState loadParentRelationships() {
    return loadEmbeddedResources(Rel.PARENT_RELATIONSHIPS);
  }

  public PersonState loadSpouseRelationships() {
    return loadEmbeddedResources(Rel.SPOUSE_RELATIONSHIPS);
  }

  public PersonState loadChildRelationships() {
    return loadEmbeddedResources(Rel.CHILD_RELATIONSHIPS);
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

  public PersonState addGender(Gender gender) {
    Person person = createEmptySelf();
    person.setGender(gender);
    return updateConclusions(person);
  }

  public PersonState addName(Name name) {
    return addNames(name);
  }

  public PersonState addNames(Name... names) {
    Person person = createEmptySelf();
    person.setNames(Arrays.asList(names));
    return updateConclusions(person);
  }

  public PersonState addFact(Fact fact) {
    return addFacts(fact);
  }

  public PersonState addFacts(Fact... facts) {
    Person person = createEmptySelf();
    person.setFacts(Arrays.asList(facts));
    return updateConclusions(person);
  }

  public PersonState updateGender(Gender gender) {
    Person person = createEmptySelf();
    person.setGender(gender);
    return updateConclusions(person);
  }

  public PersonState updateName(Name name) {
    return updateNames(name);
  }

  public PersonState updateNames(Name... names) {
    Person person = createEmptySelf();
    person.setNames(Arrays.asList(names));
    return updateConclusions(person);
  }

  public PersonState updateFact(Fact fact) {
    return updateFacts(fact);
  }

  public PersonState updateFacts(Fact... facts) {
    Person person = createEmptySelf();
    person.setFacts(Arrays.asList(facts));
    return updateConclusions(person);
  }

  public PersonState updateConclusions(Person person) {
    Gedcomx gx = new Gedcomx();
    gx.setPersons(Arrays.asList(person));

    return updateConclusions(gx);
  }

  public PersonState updateConclusions(Gedcomx gx) {
    URI target = getSelfUri();
    Link conclusionsLink = getLink(Rel.CONCLUSIONS);
    if (conclusionsLink != null && conclusionsLink.getHref() != null) {
      target = conclusionsLink.getHref().toURI();
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().entity(gx).build(target, HttpMethod.POST);
    return this.stateFactory.newPersonState(request, invoke(request), this.accessToken);
  }

  public PersonState deleteName(Name name) {
    return doDeleteConclusion(name);
  }

  public PersonState deleteGender(Gender gender) {
    return doDeleteConclusion(gender);
  }

  public PersonState deleteFact(Fact fact) {
    return doDeleteConclusion(fact);
  }

  protected PersonState doDeleteConclusion(Conclusion conclusion) {
    Link link = conclusion.getLink(Rel.CONCLUSION);
    link = link == null ? conclusion.getLink(Rel.SELF) : link;
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException("Conclusion cannot be deleted: missing link.");
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.DELETE);
    return this.stateFactory.newPersonState(request, invoke(request), this.accessToken);
  }

  public PersonState addSourceReference(SourceDescriptionState source) {
    SourceReference reference = new SourceReference();
    reference.setDescriptionRef(new org.gedcomx.common.URI(source.getSelfUri().toString()));
    return addSourceReference(reference);
  }

  public PersonState addSourceReference(SourceReference reference) {
    return addSourceReferences(reference);
  }

  public PersonState addSourceReferences(SourceReference... refs) {
    Person person = createEmptySelf();
    person.setSources(Arrays.asList(refs));
    return updateSourceReferences(person);
  }

  public PersonState updateSourceReference(SourceReference reference) {
    return updateSourceReferences(reference);
  }

  public PersonState updateSourceReferences(SourceReference... refs) {
    Person person = createEmptySelf();
    person.setSources(Arrays.asList(refs));
    return updateSourceReferences(person);
  }

  protected PersonState updateSourceReferences(Person person) {
    URI target = getSelfUri();
    Link conclusionsLink = getLink(Rel.SOURCE_REFERENCES);
    if (conclusionsLink != null && conclusionsLink.getHref() != null) {
      target = conclusionsLink.getHref().toURI();
    }

    Gedcomx gx = new Gedcomx();
    gx.setPersons(Arrays.asList(person));
    ClientRequest request = createAuthenticatedGedcomxRequest().entity(gx).build(target, HttpMethod.POST);
    return this.stateFactory.newPersonState(request, invoke(request), this.accessToken);
  }

  public PersonState deleteSourceReference(SourceReference reference) {
    Link link = reference.getLink(Rel.SOURCE_REFERENCE);
    link = link == null ? reference.getLink(Rel.SELF) : link;
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException("Source reference cannot be deleted: missing link.");
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.DELETE);
    return this.stateFactory.newPersonState(request, invoke(request), this.accessToken);
  }

  public PersonState addMediaReference(SourceDescriptionState description) {
    SourceReference reference = new SourceReference();
    reference.setDescriptionRef(new org.gedcomx.common.URI(description.getSelfUri().toString()));
    return addMediaReference(reference);
  }

  public PersonState addMediaReference(SourceReference reference) {
    return addMediaReferences(reference);
  }

  public PersonState addMediaReferences(SourceReference... refs) {
    Person person = createEmptySelf();
    person.setMedia(Arrays.asList(refs));
    return updateMediaReferences(person);
  }

  public PersonState updateMediaReference(SourceReference reference) {
    return updateMediaReferences(reference);
  }

  public PersonState updateMediaReferences(SourceReference... refs) {
    Person person = createEmptySelf();
    person.setMedia(Arrays.asList(refs));
    return updateMediaReferences(person);
  }

  protected PersonState updateMediaReferences(Person person) {
    URI target = getSelfUri();
    Link conclusionsLink = getLink(Rel.MEDIA_REFERENCES);
    if (conclusionsLink != null && conclusionsLink.getHref() != null) {
      target = conclusionsLink.getHref().toURI();
    }

    Gedcomx gx = new Gedcomx();
    gx.setPersons(Arrays.asList(person));
    ClientRequest request = createAuthenticatedGedcomxRequest().entity(gx).build(target, HttpMethod.POST);
    return this.stateFactory.newPersonState(request, invoke(request), this.accessToken);
  }

  public PersonState deleteMediaReference(SourceReference reference) {
    Link link = reference.getLink(Rel.MEDIA_REFERENCE);
    link = link == null ? reference.getLink(Rel.SELF) : link;
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException("Media reference cannot be deleted: missing link.");
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.DELETE);
    return this.stateFactory.newPersonState(request, invoke(request), this.accessToken);
  }

  public PersonState addEvidenceReference(PersonState evidence) {
    EvidenceReference reference = new EvidenceReference();
    reference.setResource(new org.gedcomx.common.URI(evidence.getSelfUri().toString()));
    return addEvidenceReference(reference);
  }

  public PersonState addEvidenceReference(EvidenceReference reference) {
    return addEvidenceReferences(reference);
  }

  public PersonState addEvidenceReferences(EvidenceReference... refs) {
    Person person = createEmptySelf();
    person.setEvidence(Arrays.asList(refs));
    return updateEvidenceReferences(person);
  }

  public PersonState updateEvidenceReference(EvidenceReference reference) {
    return updateEvidenceReferences(reference);
  }

  public PersonState updateEvidenceReferences(EvidenceReference... refs) {
    Person person = createEmptySelf();
    person.setEvidence(Arrays.asList(refs));
    return updateEvidenceReferences(person);
  }

  protected PersonState updateEvidenceReferences(Person person) {
    URI target = getSelfUri();
    Link conclusionsLink = getLink(Rel.EVIDENCE_REFERENCES);
    if (conclusionsLink != null && conclusionsLink.getHref() != null) {
      target = conclusionsLink.getHref().toURI();
    }

    Gedcomx gx = new Gedcomx();
    gx.setPersons(Arrays.asList(person));
    ClientRequest request = createAuthenticatedGedcomxRequest().entity(gx).build(target, HttpMethod.POST);
    return this.stateFactory.newPersonState(request, invoke(request), this.accessToken);
  }

  public PersonState deleteEvidenceReference(EvidenceReference reference) {
    Link link = reference.getLink(Rel.EVIDENCE_REFERENCE);
    link = link == null ? reference.getLink(Rel.SELF) : link;
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException("Evidence reference cannot be deleted: missing link.");
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.DELETE);
    return this.stateFactory.newPersonState(request, invoke(request), this.accessToken);
  }

  public PersonState readNote(Note note) {
    Link link = note.getLink(Rel.NOTE);
    link = link == null ? note.getLink(Rel.SELF) : link;
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException("Note cannot be read: missing link.");
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return this.stateFactory.newPersonState(request, invoke(request), this.accessToken);
  }

  public PersonState addNote(Note note) {
    return addNotes(note);
  }

  public PersonState addNotes(Note... notes) {
    Person person = createEmptySelf();
    person.setNotes(Arrays.asList(notes));
    return updateNotes(person);
  }

  public PersonState updateNote(Note note) {
    return updateNotes(note);
  }

  public PersonState updateNotes(Note... notes) {
    Person person = createEmptySelf();
    person.setNotes(Arrays.asList(notes));
    return updateNotes(person);
  }

  protected PersonState updateNotes(Person person) {
    URI target = getSelfUri();
    Link conclusionsLink = getLink(Rel.NOTES);
    if (conclusionsLink != null && conclusionsLink.getHref() != null) {
      target = conclusionsLink.getHref().toURI();
    }

    Gedcomx gx = new Gedcomx();
    gx.setPersons(Arrays.asList(person));
    ClientRequest request = createAuthenticatedGedcomxRequest().entity(gx).build(target, HttpMethod.POST);
    return this.stateFactory.newPersonState(request, invoke(request), this.accessToken);
  }

  public PersonState deleteNote(Note note) {
    Link link = note.getLink(Rel.NOTE);
    link = link == null ? note.getLink(Rel.SELF) : link;
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException("Note cannot be deleted: missing link.");
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.DELETE);
    return this.stateFactory.newPersonState(request, invoke(request), this.accessToken);
  }
  
  public RelationshipState readRelationship(Relationship relationship) {
    Link link = relationship.getLink(Rel.RELATIONSHIP);
    link = link == null ? relationship.getLink(Rel.SELF) : link;
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return this.stateFactory.newRelationshipState(request, invoke(request), this.accessToken);
  }

  public PersonState readRelative(Relationship relationship) {
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
    return this.stateFactory.newPersonState(request, invoke(request), this.accessToken);
  }
  
  public PersonState readFirstSpouse() {
    return readSpouse(0);
  }
  
  public PersonState readSpouse(int index) {
    List<Relationship> spouseRelationships = getSpouseRelationships();
    if (spouseRelationships.size() <= index) {
      return null;
    }
    return readSpouse(spouseRelationships.get(index));
  }
  
  public PersonState readSpouse(Relationship relationship) {
    return readRelative(relationship);
  }

  public PersonSpousesState readSpouses() {
    Link link = getLink(Rel.SPOUSES);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return this.stateFactory.newPersonSpousesState(request, invoke(request), this.accessToken);
  }

  public RelationshipState addSpouse(PersonState person) {
    CollectionState collection = readCollection();
    if (collection == null || collection.hasError()) {
      throw new GedcomxApplicationException("Unable to add relationship: collection unavailable.");
    }

    return collection.addSpouseRelationship(this, person);
  }

  public PersonState readFirstChild() {
    return readChild(0);
  }

  public PersonState readChild(int index) {
    List<Relationship> childRelationships = getChildRelationships();
    if (childRelationships.size() <= index) {
      return null;
    }
    return readChild(childRelationships.get(index));
  }

  public PersonState readChild(Relationship relationship) {
    return readRelative(relationship);
  }

  public PersonChildrenState readChildren() {
    Link link = getLink(Rel.CHILDREN);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return this.stateFactory.newPersonChildrenState(request, invoke(request), this.accessToken);
  }

  public RelationshipState addChild(PersonState person) {
    CollectionState collection = readCollection();
    if (collection == null || collection.hasError()) {
      throw new GedcomxApplicationException("Unable to add relationship: collection unavailable.");
    }

    return collection.addParentChildRelationship(this, person);
  }

  public PersonState readFirstParent() {
    return readParent(0);
  }

  public PersonState readParent(int index) {
    List<Relationship> parentRelationships = getParentRelationships();
    if (parentRelationships.size() <= index) {
      return null;
    }
    return readParent(parentRelationships.get(index));
  }

  public PersonState readParent(Relationship relationship) {
    return readRelative(relationship);
  }

  public PersonParentsState readParents() {
    Link link = getLink(Rel.PARENTS);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return this.stateFactory.newPersonParentsState(request, invoke(request), this.accessToken);
  }

  public RelationshipState addParent(PersonState person) {
    CollectionState collection = readCollection();
    if (collection == null || collection.hasError()) {
      throw new GedcomxApplicationException("Unable to add relationship: collection unavailable.");
    }

    return collection.addParentChildRelationship(person, this);
  }


}
