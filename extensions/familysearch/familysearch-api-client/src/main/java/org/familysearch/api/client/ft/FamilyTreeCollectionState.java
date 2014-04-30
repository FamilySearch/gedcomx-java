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
import org.familysearch.api.client.FamilySearchCollectionState;
import org.familysearch.api.client.util.RequestUtil;
import org.familysearch.platform.FamilySearchPlatform;
import org.familysearch.platform.ct.ChildAndParentsRelationship;
import org.gedcomx.Gedcomx;
import org.gedcomx.common.ResourceReference;
import org.gedcomx.conclusion.Relationship;
import org.gedcomx.links.Link;
import org.gedcomx.rs.client.GedcomxApplicationException;
import org.gedcomx.rs.client.PersonState;
import org.gedcomx.rs.client.RelationshipState;
import org.gedcomx.rs.client.RelationshipsState;
import org.gedcomx.types.RelationshipType;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MultivaluedMap;
import java.util.Arrays;
import java.util.List;

/**
 * @author Ryan Heaton
 */
public class FamilyTreeCollectionState extends FamilySearchCollectionState {

  protected FamilyTreeCollectionState(ClientRequest request, ClientResponse client, String accessToken, FamilyTreeStateFactory stateFactory) {
    super(request, client, accessToken, stateFactory);
  }

  @Override
  protected FamilyTreeCollectionState clone(ClientRequest request, ClientResponse response) {
    return new FamilyTreeCollectionState(request, response, this.accessToken, (FamilyTreeStateFactory) this.stateFactory);
  }

  @Override
  public FamilyTreeCollectionState ifSuccessful() {
    return (FamilyTreeCollectionState) super.ifSuccessful();
  }

  @Override
  public FamilyTreeCollectionState head() {
    return (FamilyTreeCollectionState) super.head();
  }

  @Override
  public FamilyTreeCollectionState get() {
    return (FamilyTreeCollectionState) super.get();
  }

  @Override
  public FamilyTreeCollectionState delete() {
    return (FamilyTreeCollectionState) super.delete();
  }

  @Override
  public FamilyTreeCollectionState put(Gedcomx e) {
    return (FamilyTreeCollectionState) super.put(e);
  }

  @Override
  public FamilyTreeCollectionState authenticateViaOAuth2Password(String username, String password, String clientId) {
    return (FamilyTreeCollectionState) super.authenticateViaOAuth2Password(username, password, clientId);
  }

  @Override
  public FamilyTreeCollectionState authenticateViaOAuth2Password(String username, String password, String clientId, String clientSecret) {
    return (FamilyTreeCollectionState) super.authenticateViaOAuth2Password(username, password, clientId, clientSecret);
  }

  @Override
  public FamilyTreeCollectionState authenticateViaOAuth2AuthCode(String authCode, String redirect, String clientId, String clientSecret) {
    return (FamilyTreeCollectionState) super.authenticateViaOAuth2AuthCode(authCode, redirect, clientId, clientSecret);
  }

  @Override
  public FamilyTreeCollectionState authenticateViaOAuth2AuthCode(String authCode, String redirect, String clientId) {
    return (FamilyTreeCollectionState) super.authenticateViaOAuth2AuthCode(authCode, redirect, clientId);
  }

  @Override
  public FamilyTreeCollectionState authenticateViaOAuth2ClientCredentials(String clientId, String clientSecret) {
    return (FamilyTreeCollectionState) super.authenticateViaOAuth2ClientCredentials(clientId, clientSecret);
  }

  @Override
  public FamilyTreeCollectionState authenticateViaOAuth2(MultivaluedMap<String, String> formData) {
    return (FamilyTreeCollectionState) super.authenticateViaOAuth2(formData);
  }

  @Override
  public FamilySearchCollectionState readCollection() {
    return (FamilySearchCollectionState) super.readCollection();
  }

  @Override
  public RelationshipState addRelationship(Relationship relationship) {
    if (relationship.getKnownType() == RelationshipType.ParentChild) {
      throw new GedcomxApplicationException("FamilySearch Family Tree doesn't support adding parent-child relationships. You must instead add a child-and-parents relationship.");
    }
    return super.addRelationship(relationship);
  }

  @Override
  public RelationshipsState addRelationships(List<Relationship> relationships) {
    for (Relationship relationship : relationships) {
      if (relationship.getKnownType() == RelationshipType.ParentChild) {
        throw new GedcomxApplicationException("FamilySearch Family Tree doesn't support adding parent-child relationships. You must instead add a child-and-parents relationship.");
      }
    }
    return super.addRelationships(relationships);
  }

  public ChildAndParentsRelationshipState addChildAndParentsRelationship(PersonState child, PersonState father, PersonState mother) {
    ChildAndParentsRelationship chap = new ChildAndParentsRelationship();
    chap.setChild(new ResourceReference(new org.gedcomx.common.URI(child.getSelfUri().toString())));
    if (father != null) {
      chap.setFather(new ResourceReference(new org.gedcomx.common.URI(father.getSelfUri().toString())));
    }
    if (mother != null) {
      chap.setMother(new ResourceReference(new org.gedcomx.common.URI(mother.getSelfUri().toString())));
    }
    return addChildAndParentsRelationship(chap);
  }

  public ChildAndParentsRelationshipState addChildAndParentsRelationship(ChildAndParentsRelationship chap) {
    Link link = getLink(org.gedcomx.rs.Rel.RELATIONSHIPS);
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException(String.format("FamilySearch Family Tree at %s didn't provide a 'relationships' link.", getUri()));
    }

    FamilySearchPlatform entity = new FamilySearchPlatform();
    entity.setChildAndParentsRelationships(Arrays.asList(chap));
    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest()).build(link.getHref().toURI(), HttpMethod.POST);
    request.setEntity(entity);
    return ((FamilyTreeStateFactory)this.stateFactory).newChildAndParentsRelationshipState(request, invoke(request), this.accessToken);
  }

  public RelationshipsState addChildAndParentsRelationships(List<ChildAndParentsRelationship> chaps) {
    Link link = getLink(org.gedcomx.rs.Rel.RELATIONSHIPS);
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException(String.format("FamilySearch Family Tree at %s didn't provide a 'relationships' link.", getUri()));
    }

    FamilySearchPlatform entity = new FamilySearchPlatform();
    entity.setChildAndParentsRelationships(chaps);
    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest()).build(link.getHref().toURI(), HttpMethod.POST);
    request.setEntity(entity);
    return ((FamilyTreeStateFactory)this.stateFactory).newRelationshipsState(request, invoke(request), this.accessToken);
  }

}
