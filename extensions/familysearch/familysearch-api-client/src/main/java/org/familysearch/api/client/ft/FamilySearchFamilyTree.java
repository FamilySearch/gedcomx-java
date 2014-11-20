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
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.familysearch.api.client.FamilySearchCollectionState;
import org.familysearch.api.client.Rel;
import org.familysearch.api.client.UserState;
import org.familysearch.api.client.util.RequestUtil;
import org.familysearch.platform.FamilySearchPlatform;
import org.familysearch.platform.ct.ChildAndParentsRelationship;
import org.gedcomx.Gedcomx;
import org.gedcomx.common.ResourceReference;
import org.gedcomx.conclusion.Relationship;
import org.gedcomx.links.Link;
import org.gedcomx.rs.client.*;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.types.RelationshipType;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

/**
 * @author Ryan Heaton
 */
public class FamilySearchFamilyTree extends FamilySearchCollectionState {

  public static final String URI = "https://familysearch.org/platform/collections/tree";
  public static final String SANDBOX_URI = "https://sandbox.familysearch.org/platform/collections/tree";

  public FamilySearchFamilyTree() {
    this(false);
  }

  public FamilySearchFamilyTree(boolean sandbox) {
    this(java.net.URI.create(sandbox ? SANDBOX_URI : URI));
  }

  public FamilySearchFamilyTree(URI uri) {
    this(uri, new FamilyTreeStateFactory());
  }

  private FamilySearchFamilyTree(URI uri, FamilyTreeStateFactory stateFactory) {
    this(uri, stateFactory.loadDefaultClient(), stateFactory);
  }

  private FamilySearchFamilyTree(URI uri, Client client, FamilyTreeStateFactory stateFactory) {
    this(ClientRequest.create().accept(GedcomxConstants.GEDCOMX_JSON_MEDIA_TYPE).build(uri, HttpMethod.GET), client, stateFactory);
  }

  private FamilySearchFamilyTree(ClientRequest request, Client client, FamilyTreeStateFactory stateFactory) {
    this(request, client.handle(request), null, stateFactory);
  }

  protected FamilySearchFamilyTree(ClientRequest request, ClientResponse client, String accessToken, FamilyTreeStateFactory stateFactory) {
    super(request, client, accessToken, stateFactory);
  }

  @Override
  protected FamilySearchFamilyTree clone(ClientRequest request, ClientResponse response) {
    return new FamilySearchFamilyTree(request, response, this.accessToken, (FamilyTreeStateFactory) this.stateFactory);
  }

  @Override
  public FamilySearchFamilyTree ifSuccessful() {
    return (FamilySearchFamilyTree) super.ifSuccessful();
  }

  @Override
  public FamilySearchFamilyTree head(StateTransitionOption... options) {
    return (FamilySearchFamilyTree) super.head(options);
  }

  @Override
  public FamilySearchFamilyTree get(StateTransitionOption... options) {
    return (FamilySearchFamilyTree) super.get(options);
  }

  @Override
  public FamilySearchFamilyTree delete(StateTransitionOption... options) {
    return (FamilySearchFamilyTree) super.delete(options);
  }

  @Override
  public FamilySearchFamilyTree put(Gedcomx e, StateTransitionOption... options) {
    return (FamilySearchFamilyTree) super.put(e, options);
  }

  @Override
  public FamilySearchFamilyTree post(Gedcomx entity, StateTransitionOption... options) {
    return (FamilySearchFamilyTree) super.post(entity, options);
  }

  @Override
  public FamilySearchFamilyTree authenticateViaOAuth2Password(String username, String password, String clientId) {
    return (FamilySearchFamilyTree) super.authenticateViaOAuth2Password(username, password, clientId);
  }

  @Override
  public FamilySearchFamilyTree authenticateViaOAuth2Password(String username, String password, String clientId, String clientSecret) {
    return (FamilySearchFamilyTree) super.authenticateViaOAuth2Password(username, password, clientId, clientSecret);
  }

  @Override
  public FamilySearchFamilyTree authenticateViaOAuth2AuthCode(String authCode, String redirect, String clientId, String clientSecret) {
    return (FamilySearchFamilyTree) super.authenticateViaOAuth2AuthCode(authCode, redirect, clientId, clientSecret);
  }

  @Override
  public FamilySearchFamilyTree authenticateViaOAuth2AuthCode(String authCode, String redirect, String clientId) {
    return (FamilySearchFamilyTree) super.authenticateViaOAuth2AuthCode(authCode, redirect, clientId);
  }

  @Override
  public FamilySearchFamilyTree authenticateViaOAuth2ClientCredentials(String clientId, String clientSecret) {
    return (FamilySearchFamilyTree) super.authenticateViaOAuth2ClientCredentials(clientId, clientSecret);
  }

  @Override
  public FamilySearchFamilyTree authenticateViaOAuth2(MultivaluedMap<String, String> formData, StateTransitionOption... options) {
    return (FamilySearchFamilyTree) super.authenticateViaOAuth2(formData, options);
  }

  public FamilySearchFamilyTree authenticateViaUnauthenticatedAccess(String clientId, String ipAddress) {

    MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
    formData.add("grant_type", "unauthenticated_session");
    formData.add("client_id", clientId);
    formData.add("ip_address", ipAddress);

    return this.authenticateViaOAuth2(formData);
  }

  @Override
  public FamilySearchCollectionState readCollection(StateTransitionOption... options) {
    return (FamilySearchCollectionState) super.readCollection(options);
  }

  @Override
  public RelationshipState addRelationship(Relationship relationship, StateTransitionOption... options) {
    if (relationship.getKnownType() == RelationshipType.ParentChild) {
      throw new GedcomxApplicationException("FamilySearch Family Tree doesn't support adding parent-child relationships. You must instead add a child-and-parents relationship.");
    }
    return super.addRelationship(relationship, options);
  }

  @Override
  public RelationshipsState addRelationships(List<Relationship> relationships, StateTransitionOption... options) {
    for (Relationship relationship : relationships) {
      if (relationship.getKnownType() == RelationshipType.ParentChild) {
        throw new GedcomxApplicationException("FamilySearch Family Tree doesn't support adding parent-child relationships. You must instead add a child-and-parents relationship.");
      }
    }
    return super.addRelationships(relationships, options);
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

    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest()).build(java.net.URI.create(uri), HttpMethod.GET);
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
      uri = UriTemplate.fromTemplate(template).set("person", id).expand();
    }
    catch (VariableExpansionException e) {
      throw new GedcomxApplicationException(e);
    }
    catch (MalformedUriTemplateException e) {
      throw new GedcomxApplicationException(e);
    }

    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest()).build(java.net.URI.create(uri), HttpMethod.GET);
    return ((FamilyTreeStateFactory)this.stateFactory).newPersonState(request, invoke(request, options), this.accessToken);
  }

  public PreferredRelationshipState readPreferredSpouseRelationship(UserState user, FamilyTreePersonState person, StateTransitionOption... options) {
    return readPreferredRelationship(Rel.PREFERRED_SPOUSE_RELATIONSHIP, user.getUser().getTreeUserId(), person.getPerson().getId(), options);
  }

  public PreferredRelationshipState readPreferredParentRelationship(UserState user, FamilyTreePersonState person, StateTransitionOption... options) {
    return readPreferredRelationship(Rel.PREFERRED_PARENT_RELATIONSHIP, user.getUser().getTreeUserId(), person.getPerson().getId(), options);
  }

  public PreferredRelationshipState readPreferredSpouseRelationship(String treeUserId, FamilyTreePersonState person, StateTransitionOption... options) {
    return readPreferredRelationship(Rel.PREFERRED_SPOUSE_RELATIONSHIP, treeUserId, person.getPerson().getId(), options);
  }

  public PreferredRelationshipState readPreferredParentRelationship(String treeUserId, FamilyTreePersonState person, StateTransitionOption... options) {
    return readPreferredRelationship(Rel.PREFERRED_PARENT_RELATIONSHIP, treeUserId, person.getPerson().getId(), options);
  }

  public PreferredRelationshipState readPreferredSpouseRelationship(String treeUserId, String personId, StateTransitionOption... options) {
    return readPreferredRelationship(Rel.PREFERRED_SPOUSE_RELATIONSHIP, treeUserId, personId, options);
  }

  public PreferredRelationshipState readPreferredParentRelationship(String treeUserId, String personId, StateTransitionOption... options) {
    return readPreferredRelationship(Rel.PREFERRED_PARENT_RELATIONSHIP, treeUserId, personId, options);
  }

  protected PreferredRelationshipState readPreferredRelationship(String rel, String treeUserId, String personId, StateTransitionOption[] options) {
    Link link = getLink(rel);
    if (link == null || link.getTemplate() == null) {
      return null;
    }

    String template = link.getTemplate();
    String uri;
    try{
      uri = UriTemplate.fromTemplate(template).set("pid", personId).set("uid", treeUserId).expand();
    }
    catch (VariableExpansionException e) {
      throw new GedcomxApplicationException(e);
    }
    catch (MalformedUriTemplateException e) {
      throw new GedcomxApplicationException(e);
    }

    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest()).build(java.net.URI.create(uri), HttpMethod.GET);
    ClientResponse response = invoke(request, options);
    if (response.getClientResponseStatus() == ClientResponse.Status.NO_CONTENT) {
      return null;
    }

    response.bufferEntity();
    FamilySearchPlatform fsp = response.getEntity(FamilySearchPlatform.class);
    try {
      response.getEntityInputStream().reset();
    }
    catch (IOException e) {
      throw new RuntimeException(e);
    }

    if (fsp.getChildAndParentsRelationships() != null && fsp.getChildAndParentsRelationships().size() > 0) {
      return ((FamilyTreeStateFactory) this.stateFactory).newChildAndParentsRelationshipState(request, response, this.accessToken);
    }
    else {
      return ((FamilyTreeStateFactory)this.stateFactory).newRelationshipState(request, response, this.accessToken);
    }
  }

  public FamilyTreePersonState updatePreferredSpouseRelationship(UserState user, FamilyTreePersonState person, PreferredRelationshipState relationshipState, StateTransitionOption... options) {
    return updatePreferredRelationship(Rel.PREFERRED_SPOUSE_RELATIONSHIP, user.getUser().getTreeUserId(), person.getPerson().getId(), relationshipState, options);
  }

  public FamilyTreePersonState updatePreferredParentRelationship(UserState user, FamilyTreePersonState person, PreferredRelationshipState relationshipState, StateTransitionOption... options) {
    return updatePreferredRelationship(Rel.PREFERRED_PARENT_RELATIONSHIP, user.getUser().getTreeUserId(), person.getPerson().getId(), relationshipState, options);
  }

  public FamilyTreePersonState updatePreferredSpouseRelationship(String treeUserId, FamilyTreePersonState person, PreferredRelationshipState relationshipState, StateTransitionOption... options) {
    return updatePreferredRelationship(Rel.PREFERRED_SPOUSE_RELATIONSHIP, treeUserId, person.getPerson().getId(), relationshipState, options);
  }

  public FamilyTreePersonState updatePreferredParentRelationship(String treeUserId, FamilyTreePersonState person, PreferredRelationshipState relationshipState, StateTransitionOption... options) {
    return updatePreferredRelationship(Rel.PREFERRED_PARENT_RELATIONSHIP, treeUserId, person.getPerson().getId(), relationshipState, options);
  }

  public FamilyTreePersonState updatePreferredSpouseRelationship(String treeUserId, String personId, PreferredRelationshipState relationshipState, StateTransitionOption... options) {
    return updatePreferredRelationship(Rel.PREFERRED_SPOUSE_RELATIONSHIP, treeUserId, personId, relationshipState, options);
  }

  public FamilyTreePersonState updatePreferredParentRelationship(String treeUserId, String personId, PreferredRelationshipState relationshipState, StateTransitionOption... options) {
    return updatePreferredRelationship(Rel.PREFERRED_PARENT_RELATIONSHIP, treeUserId, personId, relationshipState, options);
  }

  protected FamilyTreePersonState updatePreferredRelationship(String rel, String treeUserId, String personId, PreferredRelationshipState relationshipState, StateTransitionOption[] options) {
    Link link = getLink(rel);
    if (link == null || link.getTemplate() == null) {
      return null;
    }

    String template = link.getTemplate();
    String uri;
    try{
      uri = UriTemplate.fromTemplate(template).set("pid", personId).set("uid", treeUserId).expand();
    }
    catch (VariableExpansionException e) {
      throw new GedcomxApplicationException(e);
    }
    catch (MalformedUriTemplateException e) {
      throw new GedcomxApplicationException(e);
    }

    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest()).header("Location", relationshipState.getSelfUri()).build(java.net.URI.create(uri), HttpMethod.PUT);
    return ((FamilyTreeStateFactory)this.stateFactory).newPersonState(request, invoke(request, options), this.accessToken);
  }

  public FamilyTreePersonState deletePreferredSpouseRelationship(UserState user, FamilyTreePersonState person, StateTransitionOption... options) {
    return deletePreferredRelationship(user.getUser().getTreeUserId(), person.getPerson().getId(), Rel.PREFERRED_SPOUSE_RELATIONSHIP, options);
  }

  public FamilyTreePersonState deletePreferredParentRelationship(UserState user, FamilyTreePersonState person, StateTransitionOption... options) {
    return deletePreferredRelationship(user.getUser().getTreeUserId(), person.getPerson().getId(), Rel.PREFERRED_PARENT_RELATIONSHIP, options);
  }

  public FamilyTreePersonState deletePreferredSpouseRelationship(String treeUserId, FamilyTreePersonState person, StateTransitionOption... options) {
    return deletePreferredRelationship(treeUserId, person.getPerson().getId(), Rel.PREFERRED_SPOUSE_RELATIONSHIP, options);
  }

  public FamilyTreePersonState deletePreferredParentRelationship(String treeUserId, FamilyTreePersonState person, StateTransitionOption... options) {
    return deletePreferredRelationship(treeUserId, person.getPerson().getId(), Rel.PREFERRED_PARENT_RELATIONSHIP, options);
  }

  public FamilyTreePersonState deletePreferredSpouseRelationship(String treeUserId, String personId, StateTransitionOption... options) {
    return deletePreferredRelationship(treeUserId, personId, Rel.PREFERRED_SPOUSE_RELATIONSHIP, options);
  }

  public FamilyTreePersonState deletePreferredParentRelationship(String treeUserId, String personId, StateTransitionOption... options) {
    return deletePreferredRelationship(treeUserId, personId, Rel.PREFERRED_PARENT_RELATIONSHIP, options);
  }

  protected FamilyTreePersonState deletePreferredRelationship(String treeUserId, String personId, String rel, StateTransitionOption[] options) {
    Link link = getLink(rel);
    if (link == null || link.getTemplate() == null) {
      return null;
    }

    String template = link.getTemplate();
    String uri;
    try{
      uri = UriTemplate.fromTemplate(template).set("pid", personId).set("uid", treeUserId).expand();
    }
    catch (VariableExpansionException e) {
      throw new GedcomxApplicationException(e);
    }
    catch (MalformedUriTemplateException e) {
      throw new GedcomxApplicationException(e);
    }

    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest()).build(java.net.URI.create(uri), HttpMethod.DELETE);
    return ((FamilyTreeStateFactory)this.stateFactory).newPersonState(request, invoke(request, options), this.accessToken);
  }

}
