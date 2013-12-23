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
import org.gedcomx.Gedcomx;
import org.gedcomx.common.ResourceReference;
import org.gedcomx.common.URI;
import org.gedcomx.conclusion.Relationship;
import org.gedcomx.links.Link;
import org.gedcomx.rs.Rel;
import org.gedcomx.rs.client.GedcomxApplicationException;
import org.gedcomx.rs.client.PersonState;
import org.gedcomx.rs.client.RelationshipState;
import org.gedcomx.rs.client.RelationshipsState;
import org.gedcomx.types.RelationshipType;

import javax.ws.rs.HttpMethod;
import java.util.Arrays;
import java.util.List;

/**
 * @author Ryan Heaton
 */
public class FamilyTreeRelationshipsState extends RelationshipsState {

  protected FamilyTreeRelationshipsState(ClientRequest request, ClientResponse response, String accessToken, FamilyTreeStateFactory stateFactory) {
    super(request, response, accessToken, stateFactory);
  }

  @Override
  protected FamilyTreeRelationshipsState clone(ClientRequest request, ClientResponse response) {
    return new FamilyTreeRelationshipsState(request, response, this.accessToken, (FamilyTreeStateFactory) this.stateFactory);
  }

  public List<ChildAndParentsRelationship> getChildAndParentsRelationships() {
    return getEntity() == null ? null : ((FamilySearchPlatform)getEntity()).getChildAndParentsRelationships();
  }

  @Override
  public FamilyTreeRelationshipsState ifSuccessful() {
    return (FamilyTreeRelationshipsState) super.ifSuccessful();
  }

  @Override
  public FamilyTreeRelationshipsState head() {
    return (FamilyTreeRelationshipsState) super.head();
  }

  @Override
  public FamilyTreeRelationshipsState get() {
    return (FamilyTreeRelationshipsState) super.get();
  }

  @Override
  public FamilyTreeRelationshipsState delete() {
    return (FamilyTreeRelationshipsState) super.delete();
  }

  @Override
  public FamilyTreeRelationshipsState put(Gedcomx e) {
    return (FamilyTreeRelationshipsState) super.put(e);
  }

  @Override
  protected Gedcomx loadEntity(ClientResponse response) {
    return response.getClientResponseStatus() == ClientResponse.Status.OK ? response.getEntity(FamilySearchPlatform.class) : null;
  }

  @Override
  public FamilyTreeRelationshipsState readNextPage() {
    return (FamilyTreeRelationshipsState) super.readNextPage();
  }

  @Override
  public FamilyTreeRelationshipsState readPreviousPage() {
    return (FamilyTreeRelationshipsState) super.readPreviousPage();
  }

  @Override
  public FamilyTreeRelationshipsState readFirstPage() {
    return (FamilyTreeRelationshipsState) super.readFirstPage();
  }

  @Override
  public FamilyTreeRelationshipsState readLastPage() {
    return (FamilyTreeRelationshipsState) super.readLastPage();
  }

  @Override
  public FamilyTreeCollectionState readCollection() {
    return (FamilyTreeCollectionState) super.readCollection();
  }

  @Override
  public RelationshipState addRelationship(Relationship relationship) {
    if (relationship.getKnownType() == RelationshipType.ParentChild) {
      throw new GedcomxApplicationException("FamilySearch Family Tree doesn't support adding parent-child relationships. You must instead add a child-and-parents relationship.");
    }
    return super.addRelationship(relationship);
  }

  public ChildAndParentsRelationshipState addChildAndParentsRelationship(PersonState child, PersonState father, PersonState mother) {
    ChildAndParentsRelationship chap = new ChildAndParentsRelationship();
    chap.setChild(new ResourceReference(new URI(child.getSelfUri().toString())));
    if (father != null) {
      chap.setFather(new ResourceReference(new URI(father.getSelfUri().toString())));
    }
    if (mother != null) {
      chap.setMother(new ResourceReference(new URI(mother.getSelfUri().toString())));
    }
    return addChildAndParentsRelationship(chap);
  }

  public ChildAndParentsRelationshipState addChildAndParentsRelationship(ChildAndParentsRelationship chap) {
    FamilySearchPlatform entity = new FamilySearchPlatform();
    entity.setChildAndParentsRelationships(Arrays.asList(chap));
    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest()).build(getSelfUri(), HttpMethod.POST);
    return ((FamilyTreeStateFactory)this.stateFactory).newChildAndParentsRelationshipState(request, invoke(request), this.accessToken);
  }

}
