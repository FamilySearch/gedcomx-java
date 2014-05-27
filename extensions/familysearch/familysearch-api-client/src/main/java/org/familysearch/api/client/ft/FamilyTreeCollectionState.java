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

import com.damnhandy.uri.template.MalformedUriTemplateException;
import com.damnhandy.uri.template.UriTemplate;
import com.damnhandy.uri.template.VariableExpansionException;
import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.familysearch.api.client.FamilySearchCollectionState;
import org.familysearch.api.client.Rel;
import org.familysearch.api.client.util.RequestUtil;
import org.familysearch.platform.FamilySearchPlatform;
import org.familysearch.platform.ct.ChildAndParentsRelationship;
import org.gedcomx.Gedcomx;
import org.gedcomx.common.ResourceReference;
import org.gedcomx.conclusion.Relationship;
import org.gedcomx.links.Link;
import org.gedcomx.rs.client.*;
import org.gedcomx.types.RelationshipType;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MultivaluedMap;
import java.net.URI;
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
  public FamilyTreeCollectionState head(StateTransitionOption... options) {
    return (FamilyTreeCollectionState) super.head(options);
  }

  @Override
  public FamilyTreeCollectionState get(StateTransitionOption... options) {
    return (FamilyTreeCollectionState) super.get(options);
  }

  @Override
  public FamilyTreeCollectionState delete(StateTransitionOption... options) {
    return (FamilyTreeCollectionState) super.delete(options);
  }

  @Override
  public FamilyTreeCollectionState put(Gedcomx e, StateTransitionOption... options) {
    return (FamilyTreeCollectionState) super.put(e, options);
  }

  @Override
  public FamilyTreeCollectionState post(Gedcomx entity, StateTransitionOption... options) {
    return (FamilyTreeCollectionState) super.post(entity, options);
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
  public FamilyTreeCollectionState authenticateViaOAuth2(MultivaluedMap<String, String> formData, StateTransitionOption... options) {
    return (FamilyTreeCollectionState) super.authenticateViaOAuth2(formData);
  }

  public FamilyTreeCollectionState authenticateViaUnauthenticatedAccess(String clientId, String ipAddress) {

    MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
    formData.add("grant_type", "unauthenticated_session");
    formData.add("client_id", clientId);
    formData.add("ip_address", ipAddress);

    return this.authenticateViaOAuth2(formData);
  }

  @Override
  public FamilySearchCollectionState readCollection(StateTransitionOption... options) {
    return (FamilySearchCollectionState) super.readCollection();
  }

  @Override
  public RelationshipState addRelationship(Relationship relationship, StateTransitionOption... options) {
    if (relationship.getKnownType() == RelationshipType.ParentChild) {
      throw new GedcomxApplicationException("FamilySearch Family Tree doesn't support adding parent-child relationships. You must instead add a child-and-parents relationship.");
    }
    return super.addRelationship(relationship);
  }

  @Override
  public RelationshipsState addRelationships(List<Relationship> relationships, StateTransitionOption... options) {
    for (Relationship relationship : relationships) {
      if (relationship.getKnownType() == RelationshipType.ParentChild) {
        throw new GedcomxApplicationException("FamilySearch Family Tree doesn't support adding parent-child relationships. You must instead add a child-and-parents relationship.");
      }
    }
    return super.addRelationships(relationships);
  }

  public ChildAndParentsRelationshipState addChildAndParentsRelationship(PersonState child, PersonState father, PersonState mother, StateTransitionOption... options) {
    ChildAndParentsRelationship chap = new ChildAndParentsRelationship();
    chap.setChild(new ResourceReference(new org.gedcomx.common.URI(child.getSelfUri().toString())));
    if (father != null) {
      chap.setFather(new ResourceReference(new org.gedcomx.common.URI(father.getSelfUri().toString())));
    }
    if (mother != null) {
      chap.setMother(new ResourceReference(new org.gedcomx.common.URI(mother.getSelfUri().toString())));
    }
    return addChildAndParentsRelationship(chap, options);
  }

  public ChildAndParentsRelationshipState addChildAndParentsRelationship(ChildAndParentsRelationship chap, StateTransitionOption... options) {
    Link link = getLink(org.gedcomx.rs.Rel.RELATIONSHIPS);
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException(String.format("FamilySearch Family Tree at %s didn't provide a 'relationships' link.", getUri()));
    }

    FamilySearchPlatform entity = new FamilySearchPlatform();
    entity.setChildAndParentsRelationships(Arrays.asList(chap));
    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest()).build(link.getHref().toURI(), HttpMethod.POST);
    request.setEntity(entity);
    return ((FamilyTreeStateFactory)this.stateFactory).newChildAndParentsRelationshipState(request, invoke(request, options), this.accessToken);
  }

  public RelationshipsState addChildAndParentsRelationships(List<ChildAndParentsRelationship> chaps, StateTransitionOption... options) {
    Link link = getLink(org.gedcomx.rs.Rel.RELATIONSHIPS);
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException(String.format("FamilySearch Family Tree at %s didn't provide a 'relationships' link.", getUri()));
    }

    FamilySearchPlatform entity = new FamilySearchPlatform();
    entity.setChildAndParentsRelationships(chaps);
    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest()).build(link.getHref().toURI(), HttpMethod.POST);
    request.setEntity(entity);
    return ((FamilyTreeStateFactory)this.stateFactory).newRelationshipsState(request, invoke(request, options), this.accessToken);
  }

  public DiscoveryState readDiscoveryDocument(StateTransitionOption... options) {
    ClientRequest request = createAuthenticatedFeedRequest().build(getSelfUri().resolve("/.well-known/app-meta"), HttpMethod.GET);
    return ((FamilyTreeStateFactory)this.stateFactory).newDiscoveryState(request, invoke(request, options), this.accessToken);
  }

  public FamilyTreePersonState readPersonById(String id, StateTransitionOption... options) {
    Link link = getLink(Rel.PERSON);
    if (link == null || link.getTemplate() == null) {
      return null;
    }

    String template = link.getTemplate();
    String uri;
    try{
      uri = UriTemplate.fromTemplate(template).set("pid", id).expand();
    }
    catch (VariableExpansionException e) {
      throw new GedcomxApplicationException(e);
    }
    catch (MalformedUriTemplateException e) {
      throw new GedcomxApplicationException(e);
    }

    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest()).build(URI.create(uri), HttpMethod.GET);
    return ((FamilyTreeStateFactory)this.stateFactory).newPersonState(request, invoke(request, options), this.accessToken);
  }

  public FamilyTreePersonState readPersonWithRelationshipsById(String id, StateTransitionOption... options) {
    Link link = getLink(Rel.PERSON_WITH_RELATIONSHIPS);
    if (link == null || link.getTemplate() == null) {
      return null;
    }

    String template = link.getTemplate();
    String uri;
    try{
      uri = UriTemplate.fromTemplate(template).set("pid", id).expand();
    }
    catch (VariableExpansionException e) {
      throw new GedcomxApplicationException(e);
    }
    catch (MalformedUriTemplateException e) {
      throw new GedcomxApplicationException(e);
    }

    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest()).build(URI.create(uri), HttpMethod.GET);
    return ((FamilyTreeStateFactory)this.stateFactory).newPersonState(request, invoke(request, options), this.accessToken);
  }

}
