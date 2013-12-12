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

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import org.gedcomx.Gedcomx;
import org.gedcomx.conclusion.Person;
import org.gedcomx.links.Link;
import org.gedcomx.links.SupportsLinks;
import org.gedcomx.rs.Rel;
import org.gedcomx.rt.GedcomxConstants;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MultivaluedMap;
import java.net.URI;

/**
 * @author Ryan Heaton
 */
public class PersonState extends GedcomxApplicationState<Gedcomx> {

  public PersonState(URI discoveryUri) {
    this(discoveryUri, loadDefaultClient());
  }

  public PersonState(URI discoveryUri, Client client) {
    this(discoveryUri, client, HttpMethod.GET);
  }

  public PersonState(URI discoveryUri, Client client, String httpMethod) {
    this(ClientRequest.create().accept(GedcomxConstants.GEDCOMX_JSON_MEDIA_TYPE).build(discoveryUri, httpMethod), client, null);
  }

  public PersonState(ClientRequest request, Client client, String accessToken) {
    super(request, client, accessToken);
  }

  @Override
  protected PersonState newApplicationState(ClientRequest request, Client client, String accessToken) {
    return new PersonState(request, client, accessToken);
  }

  @Override
  public PersonState ifSuccessful() {
    return (PersonState) super.ifSuccessful();
  }

  @Override
  protected Gedcomx loadEntity(ClientResponse response) {
    return response.getClientResponseStatus() == ClientResponse.Status.OK ? response.getEntity(Gedcomx.class) : null;
  }

  @Override
  protected SupportsLinks getScope() {
    return getPerson();
  }

  public Person getPerson() {
    return getEntity() == null ? null : getEntity().getPersons() == null ? null : getEntity().getPersons().isEmpty() ? null : getEntity().getPersons().get(0);
  }

  @Override
  public PersonState authenticateViaOAuth2Password(String username, String password, String clientId) {
    return (PersonState) super.authenticateViaOAuth2Password(username, password, clientId);
  }

  @Override
  public PersonState authenticateViaOAuth2Password(String username, String password, String clientId, String clientSecret) {
    return (PersonState) super.authenticateViaOAuth2Password(username, password, clientId, clientSecret);
  }

  @Override
  public PersonState authenticateViaOAuth2AuthCode(String authCode, String redirect, String clientId) {
    return (PersonState) super.authenticateViaOAuth2AuthCode(authCode, redirect, clientId);
  }

  @Override
  public PersonState authenticateViaOAuth2AuthCode(String authCode, String redirect, String clientId, String clientSecret) {
    return (PersonState) super.authenticateViaOAuth2AuthCode(authCode, redirect, clientId, clientSecret);
  }

  @Override
  public PersonState authenticateViaOAuth2ClientCredentials(String clientId, String clientSecret) {
    return (PersonState) super.authenticateViaOAuth2ClientCredentials(clientId, clientSecret);
  }

  @Override
  public PersonState authenticateViaOAuth2(MultivaluedMap<String, String> formData) {
    return (PersonState) super.authenticateViaOAuth2(formData);
  }

  public PersonState loadEmbeddedResources() {
    includeEmbeddedResources(this.entity);
    return this;
  }

  public AncestryResultsState getAncestry() {
    Link link = this.links.get(Rel.ANCESTRY);
    if (link == null || link.getHref() == null) {
      return null;
    }

    return new AncestryResultsState(createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET), this.client, this.accessToken);
  }

  public DescendancyResultsState getDescendancy() {
    Link link = this.links.get(Rel.DESCENDANCY);
    if (link == null || link.getHref() == null) {
      return null;
    }

    return new DescendancyResultsState(createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET), this.client, this.accessToken);
  }

  public PersonChildrenState getChildren() {
    Link link = this.links.get(Rel.CHILDREN);
    if (link == null || link.getHref() == null) {
      return null;
    }

    return new PersonChildrenState(createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET), this.client, this.accessToken);
  }

  public PersonParentsState getParents() {
    Link link = this.links.get(Rel.PARENTS);
    if (link == null || link.getHref() == null) {
      return null;
    }

    return new PersonParentsState(createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET), this.client, this.accessToken);
  }

  public PersonSpousesState getSpouses() {
    Link link = this.links.get(Rel.SPOUSES);
    if (link == null || link.getHref() == null) {
      return null;
    }

    return new PersonSpousesState(createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET), this.client, this.accessToken);
  }

}
