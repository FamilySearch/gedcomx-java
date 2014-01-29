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
import org.gedcomx.conclusion.Conclusion;
import org.gedcomx.conclusion.Fact;
import org.gedcomx.conclusion.Relationship;
import org.gedcomx.links.Link;
import org.gedcomx.links.SupportsLinks;
import org.gedcomx.rs.Rel;
import org.gedcomx.source.SourceReference;

import javax.ws.rs.HttpMethod;
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
  protected RelationshipState clone(ClientRequest request, ClientResponse response) {
    return new RelationshipState(request, response, this.accessToken, this.stateFactory);
  }

  @Override
  public RelationshipState ifSuccessful() {
    return (RelationshipState) super.ifSuccessful();
  }

  @Override
  public RelationshipState head() {
    return (RelationshipState) super.head();
  }

  @Override
  public RelationshipState options() {
    return (RelationshipState) super.options();
  }

  @Override
  public RelationshipState get() {
    return (RelationshipState) super.get();
  }

  @Override
  public RelationshipState delete() {
    return (RelationshipState) super.delete();
  }

  @Override
  public RelationshipState put(Gedcomx e) {
    return (RelationshipState) super.put(e);
  }

  @Override
  protected Gedcomx loadEntity(ClientResponse response) {
    return response.getEntity(Gedcomx.class);
  }

  @Override
  protected SupportsLinks getScope() {
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

  public CollectionState readCollection() {
    Link link = getLink(Rel.COLLECTION);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return this.stateFactory.newCollectionState(request, invoke(request), this.accessToken);
  }

  public RelationshipState loadEmbeddedResources() {
    includeEmbeddedResources(this.entity);
    return this;
  }

  public RelationshipState loadEmbeddedResources(String... rels) {
    for (String rel : rels) {
      Link link = getLink(rel);
      if (this.entity != null && link != null && link.getHref() != null) {
        embed(link, this.entity);
      }
    }
    return this;
  }

  public RelationshipState loadConclusions() {
    return loadEmbeddedResources(Rel.CONCLUSIONS);
  }

  public RelationshipState loadSourceReferences() {
    return loadEmbeddedResources(Rel.SOURCE_REFERENCES);
  }

  public RelationshipState loadMediaReferences() {
    return loadEmbeddedResources(Rel.MEDIA_REFERENCES);
  }

  public RelationshipState loadEvidenceReferences() {
    return loadEmbeddedResources(Rel.EVIDENCE_REFERENCES);
  }

  public RelationshipState loadNotes() {
    return loadEmbeddedResources(Rel.EVIDENCE_REFERENCES);
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

  public RelationshipState addFact(Fact fact) {
    return addFacts(fact);
  }

  public RelationshipState addFacts(Fact... facts) {
    Relationship relationship = createEmptySelf();
    relationship.setFacts(Arrays.asList(facts));
    return updateConclusions(relationship);
  }

  public RelationshipState updateFact(Fact fact) {
    return updateFacts(fact);
  }

  public RelationshipState updateFacts(Fact... facts) {
    Relationship relationship = createEmptySelf();
    relationship.setFacts(Arrays.asList(facts));
    return updateConclusions(relationship);
  }

  protected RelationshipState updateConclusions(Relationship relationship) {
    URI target = getSelfUri();
    Link conclusionsLink = getLink(Rel.CONCLUSIONS);
    if (conclusionsLink != null && conclusionsLink.getHref() != null) {
      target = conclusionsLink.getHref().toURI();
    }

    Gedcomx gx = new Gedcomx();
    gx.setRelationships(Arrays.asList(relationship));
    ClientRequest request = createAuthenticatedGedcomxRequest().entity(gx).build(target, HttpMethod.POST);
    return this.stateFactory.newRelationshipState(request, invoke(request), this.accessToken);
  }

  public RelationshipState deleteFact(Fact fact) {
    Link link = fact.getLink(Rel.CONCLUSION);
    link = link == null ? fact.getLink(Rel.SELF) : link;
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException("Conclusion cannot be deleted: missing link.");
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.DELETE);
    return this.stateFactory.newRelationshipState(request, invoke(request), this.accessToken);
  }

  public RelationshipState addSourceReference(SourceReference reference) {
    return addSourceReferences(reference);
  }

  public RelationshipState addSourceReferences(SourceReference... refs) {
    Relationship relationship = createEmptySelf();
    relationship.setSources(Arrays.asList(refs));
    return updateSourceReferences(relationship);
  }

  public RelationshipState updateSourceReference(SourceReference reference) {
    return updateSourceReferences(reference);
  }

  public RelationshipState updateSourceReferences(SourceReference... refs) {
    Relationship relationship = createEmptySelf();
    relationship.setSources(Arrays.asList(refs));
    return updateSourceReferences(relationship);
  }

  protected RelationshipState updateSourceReferences(Relationship relationship) {
    URI target = getSelfUri();
    Link conclusionsLink = getLink(Rel.SOURCE_REFERENCES);
    if (conclusionsLink != null && conclusionsLink.getHref() != null) {
      target = conclusionsLink.getHref().toURI();
    }

    Gedcomx gx = new Gedcomx();
    gx.setRelationships(Arrays.asList(relationship));
    ClientRequest request = createAuthenticatedGedcomxRequest().entity(gx).build(target, HttpMethod.POST);
    return this.stateFactory.newRelationshipState(request, invoke(request), this.accessToken);
  }

  public RelationshipState deleteSourceReference(SourceReference reference) {
    Link link = reference.getLink(Rel.SOURCE_REFERENCE);
    link = link == null ? reference.getLink(Rel.SELF) : link;
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException("Source reference cannot be deleted: missing link.");
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.DELETE);
    return this.stateFactory.newRelationshipState(request, invoke(request), this.accessToken);
  }

  public RelationshipState addMediaReference(SourceReference reference) {
    return addMediaReferences(reference);
  }

  public RelationshipState addMediaReferences(SourceReference... refs) {
    Relationship relationship = createEmptySelf();
    relationship.setMedia(Arrays.asList(refs));
    return updateMediaReferences(relationship);
  }

  public RelationshipState updateMediaReference(SourceReference reference) {
    return updateMediaReferences(reference);
  }

  public RelationshipState updateMediaReferences(SourceReference... refs) {
    Relationship relationship = createEmptySelf();
    relationship.setMedia(Arrays.asList(refs));
    return updateMediaReferences(relationship);
  }

  protected RelationshipState updateMediaReferences(Relationship relationship) {
    URI target = getSelfUri();
    Link conclusionsLink = getLink(Rel.MEDIA_REFERENCES);
    if (conclusionsLink != null && conclusionsLink.getHref() != null) {
      target = conclusionsLink.getHref().toURI();
    }

    Gedcomx gx = new Gedcomx();
    gx.setRelationships(Arrays.asList(relationship));
    ClientRequest request = createAuthenticatedGedcomxRequest().entity(gx).build(target, HttpMethod.POST);
    return this.stateFactory.newRelationshipState(request, invoke(request), this.accessToken);
  }

  public RelationshipState deleteMediaReference(SourceReference reference) {
    Link link = reference.getLink(Rel.MEDIA_REFERENCE);
    link = link == null ? reference.getLink(Rel.SELF) : link;
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException("Media reference cannot be deleted: missing link.");
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.DELETE);
    return this.stateFactory.newRelationshipState(request, invoke(request), this.accessToken);
  }

  public RelationshipState addEvidenceReference(EvidenceReference reference) {
    return addEvidenceReferences(reference);
  }

  public RelationshipState addEvidenceReferences(EvidenceReference... refs) {
    Relationship relationship = createEmptySelf();
    relationship.setEvidence(Arrays.asList(refs));
    return updateEvidenceReferences(relationship);
  }

  public RelationshipState updateEvidenceReference(EvidenceReference reference) {
    return updateEvidenceReferences(reference);
  }

  public RelationshipState updateEvidenceReferences(EvidenceReference... refs) {
    Relationship relationship = createEmptySelf();
    relationship.setEvidence(Arrays.asList(refs));
    return updateEvidenceReferences(relationship);
  }

  protected RelationshipState updateEvidenceReferences(Relationship relationship) {
    URI target = getSelfUri();
    Link conclusionsLink = getLink(Rel.EVIDENCE_REFERENCES);
    if (conclusionsLink != null && conclusionsLink.getHref() != null) {
      target = conclusionsLink.getHref().toURI();
    }

    Gedcomx gx = new Gedcomx();
    gx.setRelationships(Arrays.asList(relationship));
    ClientRequest request = createAuthenticatedGedcomxRequest().entity(gx).build(target, HttpMethod.POST);
    return this.stateFactory.newRelationshipState(request, invoke(request), this.accessToken);
  }

  public RelationshipState deleteEvidenceReference(EvidenceReference reference) {
    Link link = reference.getLink(Rel.EVIDENCE_REFERENCE);
    link = link == null ? reference.getLink(Rel.SELF) : link;
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException("Evidence reference cannot be deleted: missing link.");
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.DELETE);
    return this.stateFactory.newRelationshipState(request, invoke(request), this.accessToken);
  }

  public RelationshipState readNote(Note note) {
    Link link = note.getLink(Rel.NOTE);
    link = link == null ? note.getLink(Rel.SELF) : link;
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException("Note cannot be read: missing link.");
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return this.stateFactory.newRelationshipState(request, invoke(request), this.accessToken);
  }

  public RelationshipState addNote(Note note) {
    return addNotes(note);
  }

  public RelationshipState addNotes(Note... notes) {
    Relationship relationship = createEmptySelf();
    relationship.setNotes(Arrays.asList(notes));
    return updateNotes(relationship);
  }

  public RelationshipState updateNote(Note note) {
    return updateNotes(note);
  }

  public RelationshipState updateNotes(Note... notes) {
    Relationship relationship = createEmptySelf();
    relationship.setNotes(Arrays.asList(notes));
    return updateNotes(relationship);
  }

  protected RelationshipState updateNotes(Relationship relationship) {
    URI target = getSelfUri();
    Link conclusionsLink = getLink(Rel.NOTES);
    if (conclusionsLink != null && conclusionsLink.getHref() != null) {
      target = conclusionsLink.getHref().toURI();
    }

    Gedcomx gx = new Gedcomx();
    gx.setRelationships(Arrays.asList(relationship));
    ClientRequest request = createAuthenticatedGedcomxRequest().entity(gx).build(target, HttpMethod.POST);
    return this.stateFactory.newRelationshipState(request, invoke(request), this.accessToken);
  }

  public RelationshipState deleteNote(Note note) {
    Link link = note.getLink(Rel.NOTE);
    link = link == null ? note.getLink(Rel.SELF) : link;
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException("Note cannot be deleted: missing link.");
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.DELETE);
    return this.stateFactory.newRelationshipState(request, invoke(request), this.accessToken);
  }
}
