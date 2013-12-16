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
import org.familysearch.api.client.util.RequestUtil;
import org.familysearch.platform.FamilySearchPlatform;
import org.familysearch.platform.ct.ChildAndParentsRelationship;
import org.gedcomx.common.EvidenceReference;
import org.gedcomx.common.Note;
import org.gedcomx.conclusion.Conclusion;
import org.gedcomx.conclusion.Fact;
import org.gedcomx.links.Link;
import org.gedcomx.links.SupportsLinks;
import org.gedcomx.rs.Rel;
import org.gedcomx.rs.client.GedcomxApplicationException;
import org.gedcomx.rs.client.GedcomxApplicationState;
import org.gedcomx.source.SourceReference;

import javax.ws.rs.HttpMethod;
import java.net.URI;
import java.util.Arrays;

/**
 * @author Ryan Heaton
 */
public class ChildAndParentsRelationshipState extends GedcomxApplicationState<FamilySearchPlatform> {

  protected ChildAndParentsRelationshipState(ClientRequest request, ClientResponse respons, String accessToken, FamilyTreeStateFactory stateFactory) {
    super(request, respons, accessToken, stateFactory);
  }

  @Override
  protected ChildAndParentsRelationshipState clone(ClientRequest request, ClientResponse response) {
    return new ChildAndParentsRelationshipState(request, response, this.accessToken, (FamilyTreeStateFactory) this.stateFactory);
  }

  @Override
  public ChildAndParentsRelationshipState ifSuccessful() {
    return (ChildAndParentsRelationshipState) super.ifSuccessful();
  }

  @Override
  public ChildAndParentsRelationshipState head() {
    return (ChildAndParentsRelationshipState) super.head();
  }

  @Override
  public ChildAndParentsRelationshipState get() {
    return (ChildAndParentsRelationshipState) super.get();
  }

  @Override
  public ChildAndParentsRelationshipState delete() {
    return (ChildAndParentsRelationshipState) super.delete();
  }

  @Override
  public ChildAndParentsRelationshipState put(FamilySearchPlatform e) {
    return (ChildAndParentsRelationshipState) super.put(e);
  }

  @Override
  protected FamilySearchPlatform loadEntity(ClientResponse response) {
    return response.getClientResponseStatus() == ClientResponse.Status.OK ? response.getEntity(FamilySearchPlatform.class) : null;
  }

  @Override
  protected SupportsLinks getScope() {
    return getRelationship();
  }

  public ChildAndParentsRelationship getRelationship() {
    return getEntity() == null ? null : getEntity().getChildAndParentsRelationships() == null ? null : getEntity().getChildAndParentsRelationships().isEmpty() ? null : getEntity().getChildAndParentsRelationships().get(0);
  }

  public Conclusion getConclusion() {
    return getFatherFact() != null ? getFatherFact() : getMotherFact() != null ? getMotherFact() : null;
  }

  public Fact getFatherFact() {
    ChildAndParentsRelationship relationship = getRelationship();
    return relationship == null ? null : relationship.getFatherFacts() == null ? null : relationship.getFatherFacts().isEmpty() ? null : relationship.getFatherFacts().get(0);
  }

  public Fact getMotherFact() {
    ChildAndParentsRelationship relationship = getRelationship();
    return relationship == null ? null : relationship.getMotherFacts() == null ? null : relationship.getMotherFacts().isEmpty() ? null : relationship.getMotherFacts().get(0);
  }

  public Note getNote() {
    ChildAndParentsRelationship relationship = getRelationship();
    return relationship == null ? null : relationship.getNotes() == null ? null : relationship.getNotes().isEmpty() ? null : relationship.getNotes().get(0);
  }

  public SourceReference getSourceReference() {
    ChildAndParentsRelationship relationship = getRelationship();
    return relationship == null ? null : relationship.getSources() == null ? null : relationship.getSources().isEmpty() ? null : relationship.getSources().get(0);
  }

  public EvidenceReference getEvidenceReference() {
    ChildAndParentsRelationship relationship = getRelationship();
    return relationship == null ? null : relationship.getEvidence() == null ? null : relationship.getEvidence().isEmpty() ? null : relationship.getEvidence().get(0);
  }

  public SourceReference getMediaReference() {
    ChildAndParentsRelationship relationship = getRelationship();
    return relationship == null ? null : relationship.getMedia() == null ? null : relationship.getMedia().isEmpty() ? null : relationship.getMedia().get(0);
  }

  public FamilyTreeCollectionState readCollection() {
    Link link = getLink(Rel.COLLECTION);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return ((FamilyTreeStateFactory)this.stateFactory).newCollectionState(request, invoke(request), this.accessToken);
  }

  public ChildAndParentsRelationshipState loadEmbeddedResources() {
    includeEmbeddedResources(this.entity);
    return this;
  }

  public ChildAndParentsRelationshipState loadEmbeddedResources(String... rels) {
    for (String rel : rels) {
      Link link = getLink(rel);
      if (this.entity != null && link != null && link.getHref() != null) {
        embed(link, this.entity);
      }
    }
    return this;
  }

  public ChildAndParentsRelationshipState loadConclusions() {
    return loadEmbeddedResources(Rel.CONCLUSIONS);
  }

  public ChildAndParentsRelationshipState loadSourceReferences() {
    return loadEmbeddedResources(Rel.SOURCE_REFERENCES);
  }

  public ChildAndParentsRelationshipState loadMediaReferences() {
    return loadEmbeddedResources(Rel.MEDIA_REFERENCES);
  }

  public ChildAndParentsRelationshipState loadEvidenceReferences() {
    return loadEmbeddedResources(Rel.EVIDENCE_REFERENCES);
  }

  public ChildAndParentsRelationshipState loadNotes() {
    return loadEmbeddedResources(Rel.EVIDENCE_REFERENCES);
  }

  protected ChildAndParentsRelationship createEmptySelf() {
    ChildAndParentsRelationship relationship = new ChildAndParentsRelationship();
    relationship.setId(getLocalSelfId());
    return relationship;
  }

  protected String getLocalSelfId() {
    ChildAndParentsRelationship me = getRelationship();
    return me == null ? null : me.getId();
  }

  public ChildAndParentsRelationshipState addFatherFact(Fact fact) {
    return addFatherFacts(fact);
  }

  public ChildAndParentsRelationshipState addFatherFacts(Fact... facts) {
    ChildAndParentsRelationship relationship = createEmptySelf();
    relationship.setFatherFacts(Arrays.asList(facts));
    return updateConclusions(relationship);
  }

  public ChildAndParentsRelationshipState updateFatherFact(Fact fact) {
    return updateFatherFacts(fact);
  }

  public ChildAndParentsRelationshipState updateFatherFacts(Fact... facts) {
    ChildAndParentsRelationship relationship = createEmptySelf();
    relationship.setFatherFacts(Arrays.asList(facts));
    return updateConclusions(relationship);
  }

  public ChildAndParentsRelationshipState addMotherFact(Fact fact) {
    return addMotherFacts(fact);
  }

  public ChildAndParentsRelationshipState addMotherFacts(Fact... facts) {
    ChildAndParentsRelationship relationship = createEmptySelf();
    relationship.setMotherFacts(Arrays.asList(facts));
    return updateConclusions(relationship);
  }

  public ChildAndParentsRelationshipState updateMotherFact(Fact fact) {
    return updateMotherFacts(fact);
  }

  public ChildAndParentsRelationshipState updateMotherFacts(Fact... facts) {
    ChildAndParentsRelationship relationship = createEmptySelf();
    relationship.setMotherFacts(Arrays.asList(facts));
    return updateConclusions(relationship);
  }

  protected ChildAndParentsRelationshipState updateConclusions(ChildAndParentsRelationship relationship) {
    URI target = getSelfUri();
    Link conclusionsLink = getLink(Rel.CONCLUSIONS);
    if (conclusionsLink != null && conclusionsLink.getHref() != null) {
      target = conclusionsLink.getHref().toURI();
    }

    FamilySearchPlatform gx = new FamilySearchPlatform();
    gx.setChildAndParentsRelationships(Arrays.asList(relationship));
    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest()).entity(gx).build(target, HttpMethod.POST);
    return ((FamilyTreeStateFactory)this.stateFactory).newChildAndParentsRelationshipState(request, invoke(request), this.accessToken);
  }

  public ChildAndParentsRelationshipState deleteFact(Fact fact) {
    Link link = fact.getLink(Rel.CONCLUSION);
    link = link == null ? fact.getLink(Rel.SELF) : link;
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException("Conclusion cannot be deleted: missing link.");
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.DELETE);
    return ((FamilyTreeStateFactory)this.stateFactory).newChildAndParentsRelationshipState(request, invoke(request), this.accessToken);
  }

  public ChildAndParentsRelationshipState addSourceReference(SourceReference reference) {
    return addSourceReferences(reference);
  }

  public ChildAndParentsRelationshipState addSourceReferences(SourceReference... refs) {
    ChildAndParentsRelationship relationship = createEmptySelf();
    relationship.setSources(Arrays.asList(refs));
    return updateSourceReferences(relationship);
  }

  public ChildAndParentsRelationshipState updateSourceReference(SourceReference reference) {
    return updateSourceReferences(reference);
  }

  public ChildAndParentsRelationshipState updateSourceReferences(SourceReference... refs) {
    ChildAndParentsRelationship relationship = createEmptySelf();
    relationship.setSources(Arrays.asList(refs));
    return updateSourceReferences(relationship);
  }

  protected ChildAndParentsRelationshipState updateSourceReferences(ChildAndParentsRelationship relationship) {
    URI target = getSelfUri();
    Link conclusionsLink = getLink(Rel.SOURCE_REFERENCES);
    if (conclusionsLink != null && conclusionsLink.getHref() != null) {
      target = conclusionsLink.getHref().toURI();
    }

    FamilySearchPlatform gx = new FamilySearchPlatform();
    gx.setChildAndParentsRelationships(Arrays.asList(relationship));
    ClientRequest request = createAuthenticatedGedcomxRequest().entity(gx).build(target, HttpMethod.POST);
    return ((FamilyTreeStateFactory)this.stateFactory).newChildAndParentsRelationshipState(request, invoke(request), this.accessToken);
  }

  public ChildAndParentsRelationshipState deleteSourceReference(SourceReference reference) {
    Link link = reference.getLink(Rel.SOURCE_REFERENCE);
    link = link == null ? reference.getLink(Rel.SELF) : link;
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException("Source reference cannot be deleted: missing link.");
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.DELETE);
    return ((FamilyTreeStateFactory)this.stateFactory).newChildAndParentsRelationshipState(request, invoke(request), this.accessToken);
  }

  public ChildAndParentsRelationshipState addMediaReference(SourceReference reference) {
    return addMediaReferences(reference);
  }

  public ChildAndParentsRelationshipState addMediaReferences(SourceReference... refs) {
    ChildAndParentsRelationship relationship = createEmptySelf();
    relationship.setMedia(Arrays.asList(refs));
    return updateMediaReferences(relationship);
  }

  public ChildAndParentsRelationshipState updateMediaReference(SourceReference reference) {
    return updateMediaReferences(reference);
  }

  public ChildAndParentsRelationshipState updateMediaReferences(SourceReference... refs) {
    ChildAndParentsRelationship relationship = createEmptySelf();
    relationship.setMedia(Arrays.asList(refs));
    return updateMediaReferences(relationship);
  }

  protected ChildAndParentsRelationshipState updateMediaReferences(ChildAndParentsRelationship relationship) {
    URI target = getSelfUri();
    Link conclusionsLink = getLink(Rel.MEDIA_REFERENCES);
    if (conclusionsLink != null && conclusionsLink.getHref() != null) {
      target = conclusionsLink.getHref().toURI();
    }

    FamilySearchPlatform gx = new FamilySearchPlatform();
    gx.setChildAndParentsRelationships(Arrays.asList(relationship));
    ClientRequest request = createAuthenticatedGedcomxRequest().entity(gx).build(target, HttpMethod.POST);
    return ((FamilyTreeStateFactory)this.stateFactory).newChildAndParentsRelationshipState(request, invoke(request), this.accessToken);
  }

  public ChildAndParentsRelationshipState deleteMediaReference(SourceReference reference) {
    Link link = reference.getLink(Rel.MEDIA_REFERENCE);
    link = link == null ? reference.getLink(Rel.SELF) : link;
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException("Media reference cannot be deleted: missing link.");
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.DELETE);
    return ((FamilyTreeStateFactory)this.stateFactory).newChildAndParentsRelationshipState(request, invoke(request), this.accessToken);
  }

  public ChildAndParentsRelationshipState addEvidenceReference(EvidenceReference reference) {
    return addEvidenceReferences(reference);
  }

  public ChildAndParentsRelationshipState addEvidenceReferences(EvidenceReference... refs) {
    ChildAndParentsRelationship relationship = createEmptySelf();
    relationship.setEvidence(Arrays.asList(refs));
    return updateEvidenceReferences(relationship);
  }

  public ChildAndParentsRelationshipState updateEvidenceReference(EvidenceReference reference) {
    return updateEvidenceReferences(reference);
  }

  public ChildAndParentsRelationshipState updateEvidenceReferences(EvidenceReference... refs) {
    ChildAndParentsRelationship relationship = createEmptySelf();
    relationship.setEvidence(Arrays.asList(refs));
    return updateEvidenceReferences(relationship);
  }

  protected ChildAndParentsRelationshipState updateEvidenceReferences(ChildAndParentsRelationship relationship) {
    URI target = getSelfUri();
    Link conclusionsLink = getLink(Rel.EVIDENCE_REFERENCES);
    if (conclusionsLink != null && conclusionsLink.getHref() != null) {
      target = conclusionsLink.getHref().toURI();
    }

    FamilySearchPlatform gx = new FamilySearchPlatform();
    gx.setChildAndParentsRelationships(Arrays.asList(relationship));
    ClientRequest request = createAuthenticatedGedcomxRequest().entity(gx).build(target, HttpMethod.POST);
    return ((FamilyTreeStateFactory)this.stateFactory).newChildAndParentsRelationshipState(request, invoke(request), this.accessToken);
  }

  public ChildAndParentsRelationshipState deleteEvidenceReference(EvidenceReference reference) {
    Link link = reference.getLink(Rel.EVIDENCE_REFERENCE);
    link = link == null ? reference.getLink(Rel.SELF) : link;
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException("Evidence reference cannot be deleted: missing link.");
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.DELETE);
    return ((FamilyTreeStateFactory)this.stateFactory).newChildAndParentsRelationshipState(request, invoke(request), this.accessToken);
  }

  public ChildAndParentsRelationshipState readNote(Note note) {
    Link link = note.getLink(Rel.NOTE);
    link = link == null ? note.getLink(Rel.SELF) : link;
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException("Note cannot be read: missing link.");
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return ((FamilyTreeStateFactory)this.stateFactory).newChildAndParentsRelationshipState(request, invoke(request), this.accessToken);
  }

  public ChildAndParentsRelationshipState addNote(Note note) {
    return addNotes(note);
  }

  public ChildAndParentsRelationshipState addNotes(Note... notes) {
    ChildAndParentsRelationship relationship = createEmptySelf();
    relationship.setNotes(Arrays.asList(notes));
    return updateNotes(relationship);
  }

  public ChildAndParentsRelationshipState updateNote(Note note) {
    return updateNotes(note);
  }

  public ChildAndParentsRelationshipState updateNotes(Note... notes) {
    ChildAndParentsRelationship relationship = createEmptySelf();
    relationship.setNotes(Arrays.asList(notes));
    return updateNotes(relationship);
  }

  protected ChildAndParentsRelationshipState updateNotes(ChildAndParentsRelationship relationship) {
    URI target = getSelfUri();
    Link conclusionsLink = getLink(Rel.NOTES);
    if (conclusionsLink != null && conclusionsLink.getHref() != null) {
      target = conclusionsLink.getHref().toURI();
    }

    FamilySearchPlatform gx = new FamilySearchPlatform();
    gx.setChildAndParentsRelationships(Arrays.asList(relationship));
    ClientRequest request = createAuthenticatedGedcomxRequest().entity(gx).build(target, HttpMethod.POST);
    return ((FamilyTreeStateFactory)this.stateFactory).newChildAndParentsRelationshipState(request, invoke(request), this.accessToken);
  }

  public ChildAndParentsRelationshipState deleteNote(Note note) {
    Link link = note.getLink(Rel.NOTE);
    link = link == null ? note.getLink(Rel.SELF) : link;
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException("Note cannot be deleted: missing link.");
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.DELETE);
    return ((FamilyTreeStateFactory)this.stateFactory).newChildAndParentsRelationshipState(request, invoke(request), this.accessToken);
  }
}
