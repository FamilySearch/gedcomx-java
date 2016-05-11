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
import org.familysearch.api.client.ChangeHistoryState;
import org.familysearch.api.client.Rel;
import org.familysearch.api.client.util.RequestUtil;
import org.familysearch.platform.FamilySearchPlatform;
import org.familysearch.platform.ct.ChildAndParentsRelationship;
import org.gedcomx.common.EvidenceReference;
import org.gedcomx.common.Note;
import org.gedcomx.common.ResourceReference;
import org.gedcomx.conclusion.Conclusion;
import org.gedcomx.conclusion.Fact;
import org.gedcomx.links.Link;
import org.gedcomx.links.SupportsLinks;
import org.gedcomx.rs.client.*;
import org.gedcomx.source.SourceReference;

import javax.ws.rs.HttpMethod;
import java.net.URI;
import java.util.Arrays;

/**
 * @author Ryan Heaton
 */
public class ChildAndParentsRelationshipState extends GedcomxApplicationState<FamilySearchPlatform> implements PreferredRelationshipState {

  protected ChildAndParentsRelationshipState(ClientRequest request, ClientResponse respons, String accessToken, FamilyTreeStateFactory stateFactory) {
    super(request, respons, accessToken, stateFactory);
  }

  @Override
  public String getSelfRel() {
    return Rel.RELATIONSHIP;
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
  public ChildAndParentsRelationshipState head(StateTransitionOption... options) {
    return (ChildAndParentsRelationshipState) super.head(options);
  }

  @Override
  public ChildAndParentsRelationshipState get(StateTransitionOption... options) {
    return (ChildAndParentsRelationshipState) super.get(options);
  }

  @Override
  public ChildAndParentsRelationshipState delete(StateTransitionOption... options) {
    return (ChildAndParentsRelationshipState) super.delete(options);
  }

  @Override
  public ChildAndParentsRelationshipState put(FamilySearchPlatform e, StateTransitionOption... options) {
    return (ChildAndParentsRelationshipState) super.put(e, options);
  }

  @Override
  public ChildAndParentsRelationshipState post(FamilySearchPlatform entity, StateTransitionOption... options) {
    return (ChildAndParentsRelationshipState) super.post(entity, options);
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
  protected SupportsLinks getMainDataElement() {
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

  public FamilySearchFamilyTree readCollection(StateTransitionOption... options) {
    Link link = getLink(Rel.COLLECTION);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return ((FamilyTreeStateFactory)this.stateFactory).newCollectionState(request, invoke(request, options), this.accessToken);
  }

  @Override
  protected ClientRequest.Builder createRequestForEmbeddedResource(String rel) {
    return RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest());
  }

  public ChildAndParentsRelationshipState loadEmbeddedResources(StateTransitionOption... options) {
    includeEmbeddedResources(this.entity, options);
    return this;
  }

  public ChildAndParentsRelationshipState loadEmbeddedResources(String[] rels, StateTransitionOption... options) {
    for (String rel : rels) {
      Link link = getLink(rel);
      if (this.entity != null && link != null && link.getHref() != null) {
        embed(link, this.entity, options);
      }
    }
    return this;
  }

  public ChildAndParentsRelationshipState loadConclusions(StateTransitionOption... options) {
    return loadEmbeddedResources(new String[]{Rel.CONCLUSIONS}, options);
  }

  public ChildAndParentsRelationshipState loadSourceReferences(StateTransitionOption... options) {
    return loadEmbeddedResources(new String[]{Rel.SOURCE_REFERENCES}, options);
  }

  public ChildAndParentsRelationshipState loadMediaReferences(StateTransitionOption... options) {
    return loadEmbeddedResources(new String[]{Rel.MEDIA_REFERENCES}, options);
  }

  public ChildAndParentsRelationshipState loadEvidenceReferences(StateTransitionOption... options) {
    return loadEmbeddedResources(new String[]{Rel.EVIDENCE_REFERENCES}, options);
  }

  public ChildAndParentsRelationshipState loadNotes(StateTransitionOption... options) {
    return loadEmbeddedResources(new String[]{Rel.NOTES}, options);
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

  public ChildAndParentsRelationshipState addFatherFact(Fact fact, StateTransitionOption... options) {
    return addFatherFacts(new Fact[]{fact}, options);
  }

  public ChildAndParentsRelationshipState addFatherFacts(Fact[] facts, StateTransitionOption... options) {
    ChildAndParentsRelationship relationship = createEmptySelf();
    relationship.setFatherFacts(Arrays.asList(facts));
    return updateConclusions(relationship, options);
  }

  public ChildAndParentsRelationshipState updateFatherFact(Fact fact, StateTransitionOption... options) {
    return updateFatherFacts(new Fact[]{fact}, options);
  }

  public ChildAndParentsRelationshipState updateFatherFacts(Fact[] facts, StateTransitionOption... options) {
    ChildAndParentsRelationship relationship = createEmptySelf();
    relationship.setFatherFacts(Arrays.asList(facts));
    return updateConclusions(relationship, options);
  }

  public ChildAndParentsRelationshipState addMotherFact(Fact fact, StateTransitionOption... options) {
    return addMotherFacts(new Fact[]{fact}, options);
  }

  public ChildAndParentsRelationshipState addMotherFacts(Fact[] facts, StateTransitionOption... options) {
    ChildAndParentsRelationship relationship = createEmptySelf();
    relationship.setMotherFacts(Arrays.asList(facts));
    return updateConclusions(relationship, options);
  }

  public ChildAndParentsRelationshipState updateMotherFact(Fact fact, StateTransitionOption... options) {
    return updateMotherFacts(new Fact[]{fact}, options);
  }

  public ChildAndParentsRelationshipState updateMotherFacts(Fact[] facts, StateTransitionOption... options) {
    ChildAndParentsRelationship relationship = createEmptySelf();
    relationship.setMotherFacts(Arrays.asList(facts));
    return updateConclusions(relationship, options);
  }

  protected ChildAndParentsRelationshipState updateConclusions(ChildAndParentsRelationship relationship, StateTransitionOption... options) {
    URI target = getSelfUri();
    Link conclusionsLink = getLink(Rel.CONCLUSIONS);
    if (conclusionsLink != null && conclusionsLink.getHref() != null) {
      target = conclusionsLink.getHref().toURI();
    }

    FamilySearchPlatform gx = new FamilySearchPlatform();
    gx.setChildAndParentsRelationships(Arrays.asList(relationship));
    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest()).entity(gx).build(target, HttpMethod.POST);
    return ((FamilyTreeStateFactory)this.stateFactory).newChildAndParentsRelationshipState(request, invoke(request, options), this.accessToken);
  }

  public ChildAndParentsRelationshipState deleteFact(Fact fact, StateTransitionOption... options) {
    Link link = fact.getLink(Rel.CONCLUSION);
    link = link == null ? fact.getLink(Rel.SELF) : link;
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException("Conclusion cannot be deleted: missing link.");
    }

    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedGedcomxRequest()).build(link.getHref().toURI(), HttpMethod.DELETE);
    return ((FamilyTreeStateFactory)this.stateFactory).newChildAndParentsRelationshipState(request, invoke(request, options), this.accessToken);
  }

  public ChildAndParentsRelationshipState addSourceReference(SourceDescriptionState source, StateTransitionOption... options) {
    SourceReference reference = new SourceReference();
    reference.setDescriptionRef(new org.gedcomx.common.URI(source.getSelfUri().toString()));
    return addSourceReference(reference, options);
  }

  public ChildAndParentsRelationshipState addSourceReference(SourceReference reference, StateTransitionOption... options) {
    return addSourceReferences(new SourceReference[]{reference});
  }

  public ChildAndParentsRelationshipState addSourceReferences(SourceReference[] refs, StateTransitionOption... options) {
    ChildAndParentsRelationship relationship = createEmptySelf();
    relationship.setSources(Arrays.asList(refs));
    return updateSourceReferences(relationship, options);
  }

  public ChildAndParentsRelationshipState updateSourceReference(SourceReference reference, StateTransitionOption... options) {
    return updateSourceReferences(new SourceReference[]{reference}, options);
  }

  public ChildAndParentsRelationshipState updateSourceReferences(SourceReference[] refs, StateTransitionOption... options) {
    ChildAndParentsRelationship relationship = createEmptySelf();
    relationship.setSources(Arrays.asList(refs));
    return updateSourceReferences(relationship, options);
  }

  protected ChildAndParentsRelationshipState updateSourceReferences(ChildAndParentsRelationship relationship, StateTransitionOption... options) {
    URI target = getSelfUri();
    Link conclusionsLink = getLink(Rel.SOURCE_REFERENCES);
    if (conclusionsLink != null && conclusionsLink.getHref() != null) {
      target = conclusionsLink.getHref().toURI();
    }

    FamilySearchPlatform gx = new FamilySearchPlatform();
    gx.setChildAndParentsRelationships(Arrays.asList(relationship));
    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest()).entity(gx).build(target, HttpMethod.POST);
    return ((FamilyTreeStateFactory)this.stateFactory).newChildAndParentsRelationshipState(request, invoke(request, options), this.accessToken);
  }

  public ChildAndParentsRelationshipState deleteSourceReference(SourceReference reference, StateTransitionOption... options) {
    Link link = reference.getLink(Rel.SOURCE_REFERENCE);
    link = link == null ? reference.getLink(Rel.SELF) : link;
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException("Source reference cannot be deleted: missing link.");
    }

    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest()).build(link.getHref().toURI(), HttpMethod.DELETE);
    return ((FamilyTreeStateFactory)this.stateFactory).newChildAndParentsRelationshipState(request, invoke(request, options), this.accessToken);
  }

  public SourceDescriptionState readSourceDescription(SourceReference sourceReference, StateTransitionOption... options) {
    Link link = sourceReference.getLink(org.gedcomx.rs.Rel.DESCRIPTION);
    link = link == null ? sourceReference.getLink(org.gedcomx.rs.Rel.SELF) : link;
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException("Source description cannot be read: missing link.");
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return ((FamilyTreeStateFactory)this.stateFactory).newSourceDescriptionState(request, invoke(request, options), this.accessToken);
  }

  public ChildAndParentsRelationshipState addMediaReference(SourceReference reference, StateTransitionOption... options) {
    return addMediaReferences(new SourceReference[]{reference}, options);
  }

  public ChildAndParentsRelationshipState addMediaReferences(SourceReference[] refs, StateTransitionOption... options) {
    ChildAndParentsRelationship relationship = createEmptySelf();
    relationship.setMedia(Arrays.asList(refs));
    return updateMediaReferences(relationship, options);
  }

  public ChildAndParentsRelationshipState updateMediaReference(SourceReference reference, StateTransitionOption... options) {
    return updateMediaReferences(new SourceReference[]{reference}, options);
  }

  public ChildAndParentsRelationshipState updateMediaReferences(SourceReference[] refs, StateTransitionOption... options) {
    ChildAndParentsRelationship relationship = createEmptySelf();
    relationship.setMedia(Arrays.asList(refs));
    return updateMediaReferences(relationship, options);
  }

  protected ChildAndParentsRelationshipState updateMediaReferences(ChildAndParentsRelationship relationship, StateTransitionOption... options) {
    URI target = getSelfUri();
    Link conclusionsLink = getLink(Rel.MEDIA_REFERENCES);
    if (conclusionsLink != null && conclusionsLink.getHref() != null) {
      target = conclusionsLink.getHref().toURI();
    }

    FamilySearchPlatform gx = new FamilySearchPlatform();
    gx.setChildAndParentsRelationships(Arrays.asList(relationship));
    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest()).entity(gx).build(target, HttpMethod.POST);
    return ((FamilyTreeStateFactory)this.stateFactory).newChildAndParentsRelationshipState(request, invoke(request, options), this.accessToken);
  }

  public ChildAndParentsRelationshipState deleteMediaReference(SourceReference reference, StateTransitionOption... options) {
    Link link = reference.getLink(Rel.MEDIA_REFERENCE);
    link = link == null ? reference.getLink(Rel.SELF) : link;
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException("Media reference cannot be deleted: missing link.");
    }

    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest()).build(link.getHref().toURI(), HttpMethod.DELETE);
    return ((FamilyTreeStateFactory)this.stateFactory).newChildAndParentsRelationshipState(request, invoke(request, options), this.accessToken);
  }

  public ChildAndParentsRelationshipState addEvidenceReference(EvidenceReference reference, StateTransitionOption... options) {
    return addEvidenceReferences(new EvidenceReference[]{reference}, options);
  }

  public ChildAndParentsRelationshipState addEvidenceReferences(EvidenceReference[] refs, StateTransitionOption... options) {
    ChildAndParentsRelationship relationship = createEmptySelf();
    relationship.setEvidence(Arrays.asList(refs));
    return updateEvidenceReferences(relationship, options);
  }

  public ChildAndParentsRelationshipState updateEvidenceReference(EvidenceReference reference, StateTransitionOption... options) {
    return updateEvidenceReferences(new EvidenceReference[]{reference}, options);
  }

  public ChildAndParentsRelationshipState updateEvidenceReferences(EvidenceReference[] refs, StateTransitionOption... options) {
    ChildAndParentsRelationship relationship = createEmptySelf();
    relationship.setEvidence(Arrays.asList(refs));
    return updateEvidenceReferences(relationship, options);
  }

  protected ChildAndParentsRelationshipState updateEvidenceReferences(ChildAndParentsRelationship relationship, StateTransitionOption... options) {
    URI target = getSelfUri();
    Link conclusionsLink = getLink(Rel.EVIDENCE_REFERENCES);
    if (conclusionsLink != null && conclusionsLink.getHref() != null) {
      target = conclusionsLink.getHref().toURI();
    }

    FamilySearchPlatform gx = new FamilySearchPlatform();
    gx.setChildAndParentsRelationships(Arrays.asList(relationship));
    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest()).entity(gx).build(target, HttpMethod.POST);
    return ((FamilyTreeStateFactory)this.stateFactory).newChildAndParentsRelationshipState(request, invoke(request, options), this.accessToken);
  }

  public ChildAndParentsRelationshipState deleteEvidenceReference(EvidenceReference reference, StateTransitionOption... options) {
    Link link = reference.getLink(Rel.EVIDENCE_REFERENCE);
    link = link == null ? reference.getLink(Rel.SELF) : link;
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException("Evidence reference cannot be deleted: missing link.");
    }

    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest()).build(link.getHref().toURI(), HttpMethod.DELETE);
    return ((FamilyTreeStateFactory)this.stateFactory).newChildAndParentsRelationshipState(request, invoke(request, options), this.accessToken);
  }

  public ChildAndParentsRelationshipState readNote(Note note, StateTransitionOption... options) {
    Link link = note.getLink(Rel.NOTE);
    link = link == null ? note.getLink(Rel.SELF) : link;
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException("Note cannot be read: missing link.");
    }

    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest()).build(link.getHref().toURI(), HttpMethod.GET);
    return ((FamilyTreeStateFactory)this.stateFactory).newChildAndParentsRelationshipState(request, invoke(request, options), this.accessToken);
  }

  public ChildAndParentsRelationshipState addNote(Note note, StateTransitionOption... options) {
    return addNotes(new Note[]{note}, options);
  }

  public ChildAndParentsRelationshipState addNotes(Note[] notes, StateTransitionOption... options) {
    ChildAndParentsRelationship relationship = createEmptySelf();
    relationship.setNotes(Arrays.asList(notes));
    return updateNotes(relationship);
  }

  public ChildAndParentsRelationshipState updateNote(Note note, StateTransitionOption... options) {
    return updateNotes(new Note[]{note}, options);
  }

  public ChildAndParentsRelationshipState updateNotes(Note[] notes, StateTransitionOption... options) {
    ChildAndParentsRelationship relationship = createEmptySelf();
    relationship.setNotes(Arrays.asList(notes));
    return updateNotes(relationship, options);
  }

  protected ChildAndParentsRelationshipState updateNotes(ChildAndParentsRelationship relationship, StateTransitionOption... options) {
    URI target = getSelfUri();
    Link conclusionsLink = getLink(Rel.NOTES);
    if (conclusionsLink != null && conclusionsLink.getHref() != null) {
      target = conclusionsLink.getHref().toURI();
    }

    FamilySearchPlatform gx = new FamilySearchPlatform();
    gx.setChildAndParentsRelationships(Arrays.asList(relationship));
    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest()).entity(gx).build(target, HttpMethod.POST);
    return ((FamilyTreeStateFactory)this.stateFactory).newChildAndParentsRelationshipState(request, invoke(request, options), this.accessToken);
  }

  public ChildAndParentsRelationshipState deleteNote(Note note, StateTransitionOption... options) {
    Link link = note.getLink(Rel.NOTE);
    link = link == null ? note.getLink(Rel.SELF) : link;
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException("Note cannot be deleted: missing link.");
    }

    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest()).build(link.getHref().toURI(), HttpMethod.DELETE);
    return ((FamilyTreeStateFactory)this.stateFactory).newChildAndParentsRelationshipState(request, invoke(request, options), this.accessToken);
  }

  public ChangeHistoryState readChangeHistory(StateTransitionOption... options) {
    Link link = getLink(org.familysearch.api.client.Rel.CHANGE_HISTORY);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedFeedRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return ((FamilyTreeStateFactory)this.stateFactory).newChangeHistoryState(request, invoke(request, options), this.accessToken);
  }

  public PersonState readChild(StateTransitionOption... options) {
    ChildAndParentsRelationship relationship = getRelationship();
    if (relationship == null) {
      return null;
    }

    ResourceReference child = relationship.getChild();
    if (child == null || child.getResource() == null) {
      return null;
    }

    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest()).build(child.getResource().toURI(), HttpMethod.GET);
    return ((FamilyTreeStateFactory)this.stateFactory).newPersonState(request, invoke(request, options), this.accessToken);
  }

  public PersonState readFather(StateTransitionOption... options) {
    ChildAndParentsRelationship relationship = getRelationship();
    if (relationship == null) {
      return null;
    }

    ResourceReference father = relationship.getFather();
    if (father == null || father.getResource() == null) {
      return null;
    }

    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest()).build(father.getResource().toURI(), HttpMethod.GET);
    return ((FamilyTreeStateFactory)this.stateFactory).newPersonState(request, invoke(request, options), this.accessToken);
  }

  public ChildAndParentsRelationshipState updateFather(PersonState father, StateTransitionOption... options) {
    return updateFather(father.getSelfUri(), options);
  }

  public ChildAndParentsRelationshipState updateFather(URI fatherId, StateTransitionOption... options) {
    ChildAndParentsRelationship relationship = createEmptySelf().father(new ResourceReference(fatherId));
    FamilySearchPlatform fsp = new FamilySearchPlatform();
    fsp.addChildAndParentsRelationship(relationship);
    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest()).entity(fsp).build(getSelfUri(), HttpMethod.POST);
    return ((FamilyTreeStateFactory)this.stateFactory).newChildAndParentsRelationshipState(request, invoke(request, options), this.accessToken);
  }

  public ChildAndParentsRelationshipState deleteFather(StateTransitionOption... options) {
    Link link = getLink(org.familysearch.api.client.Rel.FATHER_ROLE);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest()).build(link.getHref().toURI(), HttpMethod.DELETE);
    return ((FamilyTreeStateFactory)this.stateFactory).newChildAndParentsRelationshipState(request, invoke(request, options), this.accessToken);
  }

  public PersonState readMother(StateTransitionOption... options) {
    ChildAndParentsRelationship relationship = getRelationship();
    if (relationship == null) {
      return null;
    }

    ResourceReference mother = relationship.getMother();
    if (mother == null || mother.getResource() == null) {
      return null;
    }

    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest()).build(mother.getResource().toURI(), HttpMethod.GET);
    return ((FamilyTreeStateFactory)this.stateFactory).newPersonState(request, invoke(request, options), this.accessToken);
  }

  public ChildAndParentsRelationshipState updateMother(PersonState mother, StateTransitionOption... options) {
    return updateMother(mother.getSelfUri(), options);
  }

  public ChildAndParentsRelationshipState updateMother(URI motherId, StateTransitionOption... options) {
    ChildAndParentsRelationship relationship = createEmptySelf().mother(new ResourceReference(motherId));
    FamilySearchPlatform fsp = new FamilySearchPlatform();
    fsp.addChildAndParentsRelationship(relationship);
    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest()).entity(fsp).build(getSelfUri(), HttpMethod.POST);
    return ((FamilyTreeStateFactory)this.stateFactory).newChildAndParentsRelationshipState(request, invoke(request, options), this.accessToken);
  }

  public ChildAndParentsRelationshipState deleteMother(StateTransitionOption... options) {
    Link link = getLink(org.familysearch.api.client.Rel.MOTHER_ROLE);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest()).build(link.getHref().toURI(), HttpMethod.DELETE);
    return ((FamilyTreeStateFactory)this.stateFactory).newChildAndParentsRelationshipState(request, invoke(request, options), this.accessToken);
  }

  public ChildAndParentsRelationshipState restore(StateTransitionOption... options) {
    Link link = getLink(org.familysearch.api.client.Rel.RESTORE);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest()).build(link.getHref().toURI(), HttpMethod.POST);
    return ((FamilyTreeStateFactory)this.stateFactory).newChildAndParentsRelationshipState(request, invoke(request, options), this.accessToken);
  }

}
