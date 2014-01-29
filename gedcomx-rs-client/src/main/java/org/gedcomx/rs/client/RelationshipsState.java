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
import org.gedcomx.common.ResourceReference;
import org.gedcomx.conclusion.Relationship;
import org.gedcomx.links.Link;
import org.gedcomx.links.SupportsLinks;
import org.gedcomx.rs.Rel;
import org.gedcomx.types.RelationshipType;

import javax.ws.rs.HttpMethod;
import java.util.List;

/**
 * @author Ryan Heaton
 */
public class RelationshipsState extends GedcomxApplicationState<Gedcomx> {

  protected RelationshipsState(ClientRequest request, ClientResponse response, String accessToken, StateFactory stateFactory) {
    super(request, response, accessToken, stateFactory);
  }

  @Override
  protected RelationshipsState clone(ClientRequest request, ClientResponse response) {
    return new RelationshipsState(request, response, this.accessToken, this.stateFactory);
  }

  @Override
  public RelationshipsState ifSuccessful() {
    return (RelationshipsState) super.ifSuccessful();
  }

  @Override
  public RelationshipsState head() {
    return (RelationshipsState) super.head();
  }

  @Override
  public RelationshipsState options() {
    return (RelationshipsState) super.options();
  }

  @Override
  public RelationshipsState get() {
    return (RelationshipsState) super.get();
  }

  @Override
  public RelationshipsState delete() {
    return (RelationshipsState) super.delete();
  }

  @Override
  public RelationshipsState put(Gedcomx e) {
    return (RelationshipsState) super.put(e);
  }

  @Override
  protected Gedcomx loadEntity(ClientResponse response) {
    return response.getEntity(Gedcomx.class);
  }

  public List<Relationship> getRelationships() {
    return getEntity() == null ? null : getEntity().getRelationships();
  }

  @Override
  protected SupportsLinks getScope() {
    return getEntity();
  }

  @Override
  public RelationshipsState readNextPage() {
    return (RelationshipsState) super.readNextPage();
  }

  @Override
  public RelationshipsState readPreviousPage() {
    return (RelationshipsState) super.readPreviousPage();
  }

  @Override
  public RelationshipsState readFirstPage() {
    return (RelationshipsState) super.readFirstPage();
  }

  @Override
  public RelationshipsState readLastPage() {
    return (RelationshipsState) super.readLastPage();
  }

  public CollectionState readCollection() {
    Link link = getLink(Rel.COLLECTION);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return this.stateFactory.newCollectionState(request, invoke(request), this.accessToken);
  }

  public RelationshipState addSpouseRelationship(PersonState person1, PersonState person2) {
    Relationship relationship = new Relationship();
    relationship.setPerson1(new ResourceReference(new org.gedcomx.common.URI(person1.getSelfUri().toString())));
    relationship.setPerson2(new ResourceReference(new org.gedcomx.common.URI(person2.getSelfUri().toString())));
    relationship.setKnownType(RelationshipType.Couple);
    return addRelationship(relationship);
  }

  public RelationshipState addParentChildRelationship(PersonState parent, PersonState child) {
    Relationship relationship = new Relationship();
    relationship.setPerson1(new ResourceReference(new org.gedcomx.common.URI(parent.getSelfUri().toString())));
    relationship.setPerson2(new ResourceReference(new org.gedcomx.common.URI(child.getSelfUri().toString())));
    relationship.setKnownType(RelationshipType.ParentChild);
    return addRelationship(relationship);
  }

  public RelationshipState addRelationship(Relationship relationship) {
    Gedcomx entity = new Gedcomx();
    entity.addRelationship(relationship);
    ClientRequest request = createAuthenticatedGedcomxRequest().entity(entity).build(getSelfUri(), HttpMethod.POST);
    return this.stateFactory.newRelationshipState(request, invoke(request), this.accessToken);
  }

}
