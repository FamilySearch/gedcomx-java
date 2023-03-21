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
import org.gedcomx.rs.client.*;
import org.gedcomx.types.RelationshipType;

import jakarta.ws.rs.HttpMethod;
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
  public FamilyTreeRelationshipsState head(StateTransitionOption... options) {
    return (FamilyTreeRelationshipsState) super.head(options);
  }

  @Override
  public FamilyTreeRelationshipsState get(StateTransitionOption... options) {
    return (FamilyTreeRelationshipsState) super.get(options);
  }

  @Override
  public FamilyTreeRelationshipsState delete(StateTransitionOption... options) {
    return (FamilyTreeRelationshipsState) super.delete(options);
  }

  @Override
  public FamilyTreeRelationshipsState put(Gedcomx e, StateTransitionOption... options) {
    return (FamilyTreeRelationshipsState) super.put(e, options);
  }

  @Override
  public FamilyTreeRelationshipsState post(Gedcomx entity, StateTransitionOption... options) {
    return (FamilyTreeRelationshipsState) super.post(entity, options);
  }

  @Override
  protected Gedcomx loadEntity(ClientResponse response) {
    return response.getClientResponseStatus() == ClientResponse.Status.OK ? response.getEntity(FamilySearchPlatform.class) : null;
  }

  @Override
  public FamilyTreeRelationshipsState readNextPage(StateTransitionOption... options) {
    return (FamilyTreeRelationshipsState) super.readNextPage(options);
  }

  @Override
  public FamilyTreeRelationshipsState readPreviousPage(StateTransitionOption... options) {
    return (FamilyTreeRelationshipsState) super.readPreviousPage(options);
  }

  @Override
  public FamilyTreeRelationshipsState readFirstPage(StateTransitionOption... options) {
    return (FamilyTreeRelationshipsState) super.readFirstPage(options);
  }

  @Override
  public FamilyTreeRelationshipsState readLastPage(StateTransitionOption... options) {
    return (FamilyTreeRelationshipsState) super.readLastPage(options);
  }

  @Override
  public FamilySearchFamilyTree readCollection(StateTransitionOption... options) {
    return (FamilySearchFamilyTree) super.readCollection(options);
  }

  @Override
  public RelationshipState addRelationship(Relationship relationship, StateTransitionOption... options) {
    if (relationship.getKnownType() == RelationshipType.ParentChild) {
      throw new GedcomxApplicationException("FamilySearch Family Tree doesn't support adding parent-child relationships. You must instead add a child-and-parents relationship.");
    }
    return super.addRelationship(relationship, options);
  }

//  public ChildAndParentsRelationshipState addChildAndParentsRelationship(PersonState child, PersonState parent1, PersonState parent2, StateTransitionOption... options) {
//    ChildAndParentsRelationship chap = new ChildAndParentsRelationship();
//    chap.setChild(new ResourceReference(new URI(child.getSelfUri().toString())));
//    if (parent1 != null) {
//      chap.setParent1(new ResourceReference(new URI(parent1.getSelfUri().toString())));
//    }
//    if (parent2 != null) {
//      chap.setParent2(new ResourceReference(new URI(parent2.getSelfUri().toString())));
//    }
//    return addChildAndParentsRelationship(chap, options);
//  }
//
//  public ChildAndParentsRelationshipState addChildAndParentsRelationship(ChildAndParentsRelationship chap, StateTransitionOption... options) {
//    FamilySearchPlatform entity = new FamilySearchPlatform();
//    entity.setChildAndParentsRelationships(Arrays.asList(chap));
//    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest()).build(getSelfUri(), HttpMethod.POST);
//    return ((FamilyTreeStateFactory)this.stateFactory).newChildAndParentsRelationshipState(request, invoke(request, options), this.accessToken);
//  }

}
