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
package org.familysearch.api.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientRequest;
import org.familysearch.api.client.util.RequestUtil;
import org.gedcomx.Gedcomx;
import org.gedcomx.conclusion.Relationship;
import org.gedcomx.links.Link;
import org.gedcomx.rs.client.CollectionState;
import org.gedcomx.rs.client.GedcomxApplicationException;
import org.gedcomx.rs.client.PersonState;
import org.gedcomx.rs.client.RelationshipState;
import org.gedcomx.types.RelationshipType;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MultivaluedMap;
import java.net.URI;

/**
 * @author Ryan Heaton
 */
public class FamilyTreeCollectionState extends CollectionState {

  public FamilyTreeCollectionState() {
    this(URI.create("https://familysearch.org/platform/collections/tree"));
  }

  public FamilyTreeCollectionState(URI discoveryUri) {
    super(discoveryUri);
  }

  public FamilyTreeCollectionState(URI discoveryUri, Client client) {
    super(discoveryUri, client);
  }

  public FamilyTreeCollectionState(URI discoveryUri, Client client, String method) {
    super(discoveryUri, client, method);
  }

  public FamilyTreeCollectionState(ClientRequest request, Client client, String accessToken) {
    super(request, client, accessToken);
  }

  @Override
  protected FamilyTreeCollectionState newApplicationState(ClientRequest request, Client client, String accessToken) {
    return new FamilyTreeCollectionState(request, client, accessToken);
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
  public RelationshipState addParentChildRelationship(PersonState parent, PersonState child) {
    throw new GedcomxApplicationException("FamilySearch Family Tree doesn't support adding two-person parent-child relationships.");
  }

  @Override
  public RelationshipState addRelationship(Relationship relationship) {
    if (relationship.getKnownType() == RelationshipType.ParentChild) {
      throw new GedcomxApplicationException("FamilySearch Family Tree doesn't support adding two-person parent-child relationships.");
    }
    return super.addRelationship(relationship);
  }

  public UserState readCurrentUser() {
    Link link = getLink(Rel.CURRENT_USER);
    if (link == null || link.getHref() == null) {
      return null;
    }

    return new UserState(RequestUtil.applyFamilySearchContent(createAuthenticatedRequest()).build(link.getHref().toURI(), HttpMethod.GET), this.client, this.accessToken);
  }
}
