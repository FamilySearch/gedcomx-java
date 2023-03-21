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
import org.gedcomx.conclusion.Conclusion;
import org.gedcomx.conclusion.Fact;
import org.gedcomx.conclusion.Relationship;
import org.gedcomx.links.Link;
import org.gedcomx.links.SupportsLinks;
import org.gedcomx.rt.Rel;
import org.gedcomx.source.SourceReference;

import jakarta.ws.rs.HttpMethod;
import java.net.URI;
import java.util.Arrays;

/**
 * @author Ryan Heaton
 */
public class RelationshipState extends GedcomxApplicationState<Gedcomx> {

  protected RelationshipState(ClientRequest request, ClientResponse response, String accessToken, StateFactory stateFactory) {
    super(request, response, accessToken, stateFactory);
  }

  @Override
  public String getSelfRel() {
    return Rel.RELATIONSHIP;
  }

  @Override
  protected RelationshipState clone(ClientRequest request, ClientResponse response) {
    return new RelationshipState(request, response, this.accessToken, this.stateFactory);
  }

  @Override
  public RelationshipState ifSuccessful() {
    return (RelationshipState) super.ifSuccessful();
  }

  @Override
  public RelationshipState head(StateTransitionOption... options) {
    return (RelationshipState) super.head(options);
  }

  @Override
  public RelationshipState options(StateTransitionOption... options) {
    return (RelationshipState) super.options(options);
  }

  @Override
  public RelationshipState get(StateTransitionOption... options) {
    return (RelationshipState) super.get(options);
  }

  @Override
  public RelationshipState delete(StateTransitionOption... options) {
    return (RelationshipState) super.delete(options);
  }

  @Override
  public RelationshipState put(Gedcomx e, StateTransitionOption... options) {
    return (RelationshipState) super.put(e, options);
  }

  @Override
  public RelationshipState post(Gedcomx entity, StateTransitionOption... options) {
    return (RelationshipState) super.post(entity, options);
  }

  @Override
  public RelationshipState inject(ClientRequest request) {
    return (RelationshipState) super.inject(request);
  }

  @Override
  protected Gedcomx loadEntity(ClientResponse response) {
    return response.getEntity(Gedcomx.class);
  }

  @Override
  protected SupportsLinks getMainDataElement() {
    return getRelationship();
  }

  public Relationship getRelationship() {
    return getEntity() == null ? null : getEntity().getRelationships() == null ? null : getEntity().getRelationships().isEmpty() ? null : getEntity().getRelationships().get(0);
  }

  public Conclusion getConclusion() {
    return getFact() != null ? getFact() : null;
  }

  public Fact getFact() {
    Relationship relationship = getRelationship();
    return relationship == null ? null : relationship.getFacts() == null ? null : relationship.getFacts().isEmpty() ? null : relationship.getFacts().get(0);
  }

  public Note getNote() {
    Relationship relationship = getRelationship();
    return relationship == null ? null : relationship.getNotes() == null ? null : relationship.getNotes().isEmpty() ? null : relationship.getNotes().get(0);
  }

  public SourceReference getSourceReference() {
    Relationship relationship = getRelationship();
    return relationship == null ? null : relationship.getSources() == null ? null : relationship.getSources().isEmpty() ? null : relationship.getSources().get(0);
  }

  public EvidenceReference getEvidenceReference() {
    Relationship relationship = getRelationship();
    return relationship == null ? null : relationship.getEvidence() == null ? null : relationship.getEvidence().isEmpty() ? null : relationship.getEvidence().get(0);
  }

  public SourceReference getMediaReference() {
    Relationship relationship = getRelationship();
    return relationship == null ? null : relationship.getMedia() == null ? null : relationship.getMedia().isEmpty() ? null : relationship.getMedia().get(0);
  }

  public CollectionState readCollection(StateTransitionOption... options) {
    Link link = getLink(Rel.COLLECTION);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return this.stateFactory.newCollectionState(request, invoke(request, options), this.accessToken);
  }

  public PersonState readPerson1(StateTransitionOption... options) {
    Relationship relationship = getRelationship();
    if (relationship == null) {
      return null;
    }

    ResourceReference person1 = relationship.getPerson1();
    if (person1 == null || person1.getResource() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(person1.getResource().toURI(), HttpMethod.GET);
    return this.stateFactory.newPersonState(request, invoke(request, options), this.accessToken);
  }

  public PersonState readPerson2(StateTransitionOption... options) {
    Relationship relationship = getRelationship();
    if (relationship == null) {
      return null;
    }

    ResourceReference person2 = relationship.getPerson2();
    if (person2 == null || person2.getResource() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(person2.getResource().toURI(), HttpMethod.GET);
    return this.stateFactory.newPersonState(request, invoke(request, options), this.accessToken);
  }

  public RelationshipState loadEmbeddedResources(StateTransitionOption... options) {
    includeEmbeddedResources(this.entity);
    return this;
  }

  public RelationshipState loadEmbeddedResources(String[] rels, StateTransitionOption... options) {
    for (String rel : rels) {
      Link link = getLink(rel);
      if (this.entity != null && link != null && link.getHref() != null) {
        embed(link, this.entity, options);
      }
    }
    return this;
  }

  public RelationshipState loadConclusions(StateTransitionOption... options) {
    return loadEmbeddedResources(new String[]{Rel.CONCLUSIONS}, options);
  }

  public RelationshipState loadSourceReferences(StateTransitionOption... options) {
    return loadEmbeddedResources(new String[]{Rel.SOURCE_REFERENCES}, options);
  }

  public RelationshipState loadMediaReferences(StateTransitionOption... options) {
    return loadEmbeddedResources(new String[]{Rel.MEDIA_REFERENCES}, options);
  }

  public RelationshipState loadEvidenceReferences(StateTransitionOption... options) {
    return loadEmbeddedResources(new String[]{Rel.EVIDENCE_REFERENCES}, options);
  }

  public RelationshipState loadNotes(StateTransitionOption... options) {
    return loadEmbeddedResources(new String[]{Rel.NOTES}, options);
  }

  protected Relationship createEmptySelf() {
    Relationship relationship = new Relationship();
    relationship.setId(getLocalSelfId());
    return relationship;
  }

  protected String getLocalSelfId() {
    Relationship me = getRelationship();
    return me == null ? null : me.getId();
  }

  public RelationshipState addFact(Fact fact, StateTransitionOption... options) {
    return addFacts(new Fact[]{fact}, options);
  }

  public RelationshipState addFacts(Fact[] facts, StateTransitionOption... options) {
    Relationship relationship = createEmptySelf();
    relationship.setFacts(Arrays.asList(facts));
    return updateConclusions(relationship, options);
  }

  public RelationshipState updateFact(Fact fact, StateTransitionOption... options) {
    return updateFacts(new Fact[]{fact}, options);
  }

  public RelationshipState updateFacts(Fact[] facts, StateTransitionOption... options) {
    Relationship relationship = createEmptySelf();
    relationship.setFacts(Arrays.asList(facts));
    return updateConclusions(relationship, options);
  }

  protected RelationshipState updateConclusions(Relationship relationship, StateTransitionOption... options) {
    URI target = getSelfUri();
    Link conclusionsLink = getLink(Rel.CONCLUSIONS);
    if (conclusionsLink != null && conclusionsLink.getHref() != null) {
      target = conclusionsLink.getHref().toURI();
    }

    Gedcomx gx = new Gedcomx();
    gx.setRelationships(Arrays.asList(relationship));
    ClientRequest request = createAuthenticatedGedcomxRequest().entity(gx).build(target, HttpMethod.POST);
    return this.stateFactory.newRelationshipState(request, invoke(request, options), this.accessToken);
  }

  public RelationshipState deleteFact(Fact fact, StateTransitionOption... options) {
    Link link = fact.getLink(Rel.CONCLUSION);
    link = link == null ? fact.getLink(Rel.SELF) : link;
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException("Conclusion cannot be deleted: missing link.");
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.DELETE);
    return this.stateFactory.newRelationshipState(request, invoke(request, options), this.accessToken);
  }

  public RelationshipState addSourceReference(SourceDescriptionState source, StateTransitionOption... options) {
    SourceReference reference = new SourceReference();
    reference.setDescriptionRef(new org.gedcomx.common.URI(source.getSelfUri().toString()));
    return addSourceReference(reference, options);
  }

  public RelationshipState addSourceReference(SourceReference reference, StateTransitionOption... options) {
    return addSourceReferences(new SourceReference[]{reference}, options);
  }

  public RelationshipState addSourceReferences(SourceReference[] refs, StateTransitionOption... options) {
    Relationship relationship = createEmptySelf();
    relationship.setSources(Arrays.asList(refs));
    return updateSourceReferences(relationship, options);
  }

  public RelationshipState updateSourceReference(SourceReference reference, StateTransitionOption... options) {
    return updateSourceReferences(new SourceReference[]{reference}, options);
  }

  public RelationshipState updateSourceReferences(SourceReference[] refs, StateTransitionOption... options) {
    Relationship relationship = createEmptySelf();
    relationship.setSources(Arrays.asList(refs));
    return updateSourceReferences(relationship, options);
  }

  protected RelationshipState updateSourceReferences(Relationship relationship, StateTransitionOption... options) {
    URI target = getSelfUri();
    Link conclusionsLink = getLink(Rel.SOURCE_REFERENCES);
    if (conclusionsLink != null && conclusionsLink.getHref() != null) {
      target = conclusionsLink.getHref().toURI();
    }

    Gedcomx gx = new Gedcomx();
    gx.setRelationships(Arrays.asList(relationship));
    ClientRequest request = createAuthenticatedGedcomxRequest().entity(gx).build(target, HttpMethod.POST);
    return this.stateFactory.newRelationshipState(request, invoke(request, options), this.accessToken);
  }

  public RelationshipState deleteSourceReference(SourceReference reference, StateTransitionOption... options) {
    Link link = reference.getLink(Rel.SOURCE_REFERENCE);
    link = link == null ? reference.getLink(Rel.SELF) : link;
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException("Source reference cannot be deleted: missing link.");
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.DELETE);
    return this.stateFactory.newRelationshipState(request, invoke(request, options), this.accessToken);
  }

  public SourceDescriptionState readSourceDescription(SourceReference sourceReference, StateTransitionOption... options) {
    Link link = sourceReference.getLink(Rel.DESCRIPTION);
    link = link == null ? sourceReference.getLink(Rel.SELF) : link;
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException("Source description cannot be read: missing link.");
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return this.stateFactory.newSourceDescriptionState(request, invoke(request, options), this.accessToken);
  }

  public RelationshipState addMediaReference(SourceDescriptionState description, StateTransitionOption... options) {
    SourceReference reference = new SourceReference();
    reference.setDescriptionRef(new org.gedcomx.common.URI(description.getSelfUri().toString()));
    return addMediaReference(reference, options);
  }

  public RelationshipState addMediaReference(SourceReference reference, StateTransitionOption... options) {
    return addMediaReferences(new SourceReference[]{reference}, options);
  }

  public RelationshipState addMediaReferences(SourceReference[] refs, StateTransitionOption... options) {
    Relationship relationship = createEmptySelf();
    relationship.setMedia(Arrays.asList(refs));
    return updateMediaReferences(relationship, options);
  }

  public RelationshipState updateMediaReference(SourceReference reference, StateTransitionOption... options) {
    return updateMediaReferences(new SourceReference[]{reference}, options);
  }

  public RelationshipState updateMediaReferences(SourceReference[] refs, StateTransitionOption... options) {
    Relationship relationship = createEmptySelf();
    relationship.setMedia(Arrays.asList(refs));
    return updateMediaReferences(relationship, options);
  }

  protected RelationshipState updateMediaReferences(Relationship relationship, StateTransitionOption... options) {
    URI target = getSelfUri();
    Link conclusionsLink = getLink(Rel.MEDIA_REFERENCES);
    if (conclusionsLink != null && conclusionsLink.getHref() != null) {
      target = conclusionsLink.getHref().toURI();
    }

    Gedcomx gx = new Gedcomx();
    gx.setRelationships(Arrays.asList(relationship));
    ClientRequest request = createAuthenticatedGedcomxRequest().entity(gx).build(target, HttpMethod.POST);
    return this.stateFactory.newRelationshipState(request, invoke(request, options), this.accessToken);
  }

  public RelationshipState deleteMediaReference(SourceReference reference, StateTransitionOption... options) {
    Link link = reference.getLink(Rel.MEDIA_REFERENCE);
    link = link == null ? reference.getLink(Rel.SELF) : link;
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException("Media reference cannot be deleted: missing link.");
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.DELETE);
    return this.stateFactory.newRelationshipState(request, invoke(request, options), this.accessToken);
  }

  public RelationshipState addEvidenceReference(RelationshipState evidence, StateTransitionOption... options) {
    EvidenceReference reference = new EvidenceReference();
    reference.setResource(new org.gedcomx.common.URI(evidence.getSelfUri().toString()));
    return addEvidenceReference(reference, options);
  }

  public RelationshipState addEvidenceReference(EvidenceReference reference, StateTransitionOption... options) {
    return addEvidenceReferences(new EvidenceReference[]{reference}, options);
  }

  public RelationshipState addEvidenceReferences(EvidenceReference[] refs, StateTransitionOption... options) {
    Relationship relationship = createEmptySelf();
    relationship.setEvidence(Arrays.asList(refs));
    return updateEvidenceReferences(relationship, options);
  }

  public RelationshipState updateEvidenceReference(EvidenceReference reference, StateTransitionOption... options) {
    return updateEvidenceReferences(new EvidenceReference[]{reference}, options);
  }

  public RelationshipState updateEvidenceReferences(EvidenceReference[] refs, StateTransitionOption... options) {
    Relationship relationship = createEmptySelf();
    relationship.setEvidence(Arrays.asList(refs));
    return updateEvidenceReferences(relationship, options);
  }

  protected RelationshipState updateEvidenceReferences(Relationship relationship, StateTransitionOption... options) {
    URI target = getSelfUri();
    Link conclusionsLink = getLink(Rel.EVIDENCE_REFERENCES);
    if (conclusionsLink != null && conclusionsLink.getHref() != null) {
      target = conclusionsLink.getHref().toURI();
    }

    Gedcomx gx = new Gedcomx();
    gx.setRelationships(Arrays.asList(relationship));
    ClientRequest request = createAuthenticatedGedcomxRequest().entity(gx).build(target, HttpMethod.POST);
    return this.stateFactory.newRelationshipState(request, invoke(request, options), this.accessToken);
  }

  public RelationshipState deleteEvidenceReference(EvidenceReference reference, StateTransitionOption... options) {
    Link link = reference.getLink(Rel.EVIDENCE_REFERENCE);
    link = link == null ? reference.getLink(Rel.SELF) : link;
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException("Evidence reference cannot be deleted: missing link.");
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.DELETE);
    return this.stateFactory.newRelationshipState(request, invoke(request, options), this.accessToken);
  }

  public RelationshipState readNote(Note note, StateTransitionOption... options) {
    Link link = note.getLink(Rel.NOTE);
    link = link == null ? note.getLink(Rel.SELF) : link;
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException("Note cannot be read: missing link.");
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return this.stateFactory.newRelationshipState(request, invoke(request, options), this.accessToken);
  }

  public RelationshipState addNote(Note note, StateTransitionOption... options) {
    return addNotes(new Note[]{note}, options);
  }

  public RelationshipState addNotes(Note[] notes, StateTransitionOption... options) {
    Relationship relationship = createEmptySelf();
    relationship.setNotes(Arrays.asList(notes));
    return updateNotes(relationship, options);
  }

  public RelationshipState updateNote(Note note, StateTransitionOption... options) {
    return updateNotes(new Note[]{note}, options);
  }

  public RelationshipState updateNotes(Note[] notes, StateTransitionOption... options) {
    Relationship relationship = createEmptySelf();
    relationship.setNotes(Arrays.asList(notes));
    return updateNotes(relationship, options);
  }

  protected RelationshipState updateNotes(Relationship relationship, StateTransitionOption... options) {
    URI target = getSelfUri();
    Link conclusionsLink = getLink(Rel.NOTES);
    if (conclusionsLink != null && conclusionsLink.getHref() != null) {
      target = conclusionsLink.getHref().toURI();
    }

    Gedcomx gx = new Gedcomx();
    gx.setRelationships(Arrays.asList(relationship));
    ClientRequest request = createAuthenticatedGedcomxRequest().entity(gx).build(target, HttpMethod.POST);
    return this.stateFactory.newRelationshipState(request, invoke(request, options), this.accessToken);
  }

  public RelationshipState deleteNote(Note note, StateTransitionOption... options) {
    Link link = note.getLink(Rel.NOTE);
    link = link == null ? note.getLink(Rel.SELF) : link;
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException("Note cannot be deleted: missing link.");
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.DELETE);
    return this.stateFactory.newRelationshipState(request, invoke(request, options), this.accessToken);
  }
}
