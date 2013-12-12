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

import com.damnhandy.uri.template.MalformedUriTemplateException;
import com.damnhandy.uri.template.UriTemplate;
import com.damnhandy.uri.template.VariableExpansionException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import org.gedcomx.Gedcomx;
import org.gedcomx.links.Link;
import org.gedcomx.links.SupportsLinks;
import org.gedcomx.records.Collection;
import org.gedcomx.rs.Rel;
import org.gedcomx.rs.client.util.GedcomxSearchQueryBuilder;
import org.gedcomx.rt.GedcomxConstants;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MultivaluedMap;
import java.net.URI;

/**
 * @author Ryan Heaton
 */
public class CollectionState extends GedcomxApplicationState<Gedcomx> {

  public CollectionState(URI discoveryUri) {
    this(discoveryUri, loadDefaultClient());
  }

  public CollectionState(URI discoveryUri, Client client) {
    this(discoveryUri, client, HttpMethod.GET);
  }

  public CollectionState(URI discoveryUri, Client client, String method) {
    this(ClientRequest.create().accept(GedcomxConstants.GEDCOMX_JSON_MEDIA_TYPE).build(discoveryUri, method), client, null);
  }

  public CollectionState(ClientRequest request, Client client, String accessToken) {
    super(request, client, accessToken);
  }

  @Override
  protected CollectionState newApplicationState(ClientRequest request, Client client, String accessToken) {
    return new CollectionState(request, client, accessToken);
  }

  @Override
  protected Gedcomx loadEntity(ClientResponse response) {
    return response.getClientResponseStatus() == ClientResponse.Status.OK ? response.getEntity(Gedcomx.class) : null;
  }

  @Override
  public CollectionState ifSuccessfull() {
    return (CollectionState) super.ifSuccessfull();
  }

  @Override
  protected SupportsLinks getScope() {
    return getCollection();
  }

  public Collection getCollection() {
    return getEntity() == null ? null : getEntity().getCollections() == null ? null : getEntity().getCollections().isEmpty() ? null : getEntity().getCollections().get(0);
  }

  @Override
  public CollectionState authenticateViaOAuth2Password(String username, String password, String clientId) {
    return (CollectionState) super.authenticateViaOAuth2Password(username, password, clientId);
  }

  @Override
  public CollectionState authenticateViaOAuth2Password(String username, String password, String clientId, String clientSecret) {
    return (CollectionState) super.authenticateViaOAuth2Password(username, password, clientId, clientSecret);
  }

  @Override
  public CollectionState authenticateViaOAuth2AuthCode(String authCode, String redirect, String clientId) {
    return (CollectionState) super.authenticateViaOAuth2AuthCode(authCode, redirect, clientId);
  }

  @Override
  public CollectionState authenticateViaOAuth2AuthCode(String authCode, String redirect, String clientId, String clientSecret) {
    return (CollectionState) super.authenticateViaOAuth2AuthCode(authCode, redirect, clientId, clientSecret);
  }

  @Override
  public CollectionState authenticateViaOAuth2ClientCredentials(String clientId, String clientSecret) {
    return (CollectionState) super.authenticateViaOAuth2ClientCredentials(clientId, clientSecret);
  }

  @Override
  public CollectionState authenticateViaOAuth2(MultivaluedMap<String, String> formData) {
    return (CollectionState) super.authenticateViaOAuth2(formData);
  }


  public PersonSearchResultsState searchForPersons(GedcomxSearchQueryBuilder query) {
    return searchForPersons(query.build());
  }

  public PersonSearchResultsState searchForPersons(String query) {
    Link searchLink = this.links.get(Rel.PERSON_SEARCH);
    if (searchLink == null || searchLink.getTemplate() == null) {
      return null;
    }
    String template = searchLink.getTemplate();

    String uri;
    try {
      uri = UriTemplate.fromTemplate(template).set("q", query).expand();
    }
    catch (VariableExpansionException e) {
      throw new GedcomxApplicationException(e);
    }
    catch (MalformedUriTemplateException e) {
      throw new GedcomxApplicationException(e);
    }

    return new PersonSearchResultsState(createAuthenticatedFeedRequest().build(URI.create(uri), HttpMethod.GET), this.client, this.accessToken);
  }

  public PersonState getPersonForCurrentUser() {
    Link currentPersonLink = this.links.get(Rel.CURRENT_USER_PERSON);
    if (currentPersonLink == null || currentPersonLink.getHref() == null) {
      return null;
    }
    URI currentUserPersonUri = currentPersonLink.getHref().toURI();

    return new PersonState(createAuthenticatedGedcomxRequest().build(currentUserPersonUri, HttpMethod.GET), this.client, this.accessToken);
  }

}
