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
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;
import org.gedcomx.Gedcomx;
import org.gedcomx.conclusion.Person;
import org.gedcomx.links.Link;
import org.gedcomx.rs.Rel;
import org.gedcomx.rt.GedcomxConstants;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

/**
 * @author Ryan Heaton
 */
public class GedcomxApplicationState<E> {

  protected static final EmbeddedLinkLoader DEFAULT_EMBEDDED_LINK_LOADER = new EmbeddedLinkLoader();

  protected final GedcomxApplicationDescriptor descriptor;
  protected final String accessToken;
  protected final ClientRequest request;
  protected final ClientResponse response;
  protected final E entity;

  public GedcomxApplicationState(URI discoveryUri) {
    this(new GedcomxApplicationDescriptor(discoveryUri));
  }

  public GedcomxApplicationState(URI discoveryUri, Client client) {
    this(new GedcomxApplicationDescriptor(discoveryUri, client));
  }

  public GedcomxApplicationState(GedcomxApplicationDescriptor descriptor) {
    this.descriptor = descriptor;
    this.accessToken = null;
    this.request = descriptor.getRequest();
    this.response = descriptor.getResponse();
    this.entity = null;
  }

  protected GedcomxApplicationState(GedcomxApplicationDescriptor descriptor, String accessToken, ClientRequest request, ClientResponse response, E entity) {
    this.descriptor = descriptor;
    this.accessToken = accessToken;
    this.request = request;
    this.response = response;
    this.entity = entity;
  }




  public Client getClient() {
    return this.descriptor.getClient(); //we'll just use the descriptors client as ours.
  }

  public GedcomxApplicationDescriptor getDescriptor() {
    return descriptor;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public boolean isAuthenticated() {
    return this.accessToken != null;
  }

  public ClientRequest getRequest() {
    return request;
  }

  public ClientResponse getResponse() {
    return response;
  }

  public E getEntity() {
    return entity;
  }

  public URI getUri() {
    return this.request.getURI();
  }




  public GedcomxApplicationState<E> authenticateViaOAuth2Password(String username, String password, String clientId) {
    return authenticateViaOAuth2Password(username, password, clientId, null);
  }

  public GedcomxApplicationState<E> authenticateViaOAuth2Password(String username, String password, String clientId, String clientSecret) {
    MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
    formData.putSingle("grant_type", "password");
    formData.putSingle("username", username);
    formData.putSingle("password", password);
    formData.putSingle("client_id", clientId);
    if (clientSecret != null) {
      formData.putSingle("client_secret", clientSecret);
    }
    return authenticateViaOAuth2(formData);
  }

  public GedcomxApplicationState<E> authenticateViaOAuth2AuthCode(String authCode, String redirect, String clientId) {
    return authenticateViaOAuth2Password(authCode, authCode, clientId, null);
  }

  public GedcomxApplicationState<E> authenticateViaOAuth2AuthCode(String authCode, String redirect, String clientId, String clientSecret) {
    MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
    formData.putSingle("grant_type", "authorization_code");
    formData.putSingle("code", authCode);
    formData.putSingle("redirect_uri", redirect);
    formData.putSingle("client_id", clientId);
    if (clientSecret != null) {
      formData.putSingle("client_secret", clientSecret);
    }
    return authenticateViaOAuth2(formData);
  }

  public GedcomxApplicationState<E> authenticateViaOAuth2ClientCredentials(String clientId, String clientSecret) {
    MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
    formData.putSingle("grant_type", "client_credentials");
    formData.putSingle("client_id", clientId);
    if (clientSecret != null) {
      formData.putSingle("client_secret", clientSecret);
    }
    return authenticateViaOAuth2(formData);
  }

  public GedcomxApplicationState<E> authenticateViaOAuth2(MultivaluedMap<String, String> formData) {
    URI tokenUri = this.descriptor.getOAuth2TokenUri();
    if (tokenUri == null) {
      throw new GedcomxApplicationException(String.format("No OAuth2 token URI supplied for API at %s.", this.descriptor.getDiscoveryUri()));
    }

    ClientRequest request = createRequest()
      .accept(MediaType.APPLICATION_JSON_TYPE)
      .type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
      .entity(formData)
      .build(tokenUri, HttpMethod.POST);
    ClientResponse response = invoke(request);

    if (response.getClientResponseStatus().getFamily() == Response.Status.Family.SUCCESSFUL) {
      ObjectNode accessToken = response.getEntity(ObjectNode.class);
      JsonNode access_token = accessToken.get("access_token");

      if (access_token == null) {
        //workaround to accommodate providers that were built on an older version of the oauth2 specification.
        access_token = accessToken.get("token");
      }

      if (access_token == null) {
        throw new GedcomxApplicationException("Illegal access token response: no access_token provided.", response);
      }

      return newApplicationState(this.descriptor, access_token.getValueAsText(), request, response, entity);
    }
    else {
      throw new GedcomxApplicationException("Unable to obtain an access token.", response);
    }
  }



  public GedcomxApplicationState<? extends Gedcomx> getGedcomxResource(URI personUri) {
    return getGedcomxResource(personUri, true);
  }

  public GedcomxApplicationState<? extends Gedcomx> getGedcomxResource(URI personUri, boolean includeEmbedded) {
    ClientRequest request = createAuthenticatedGedcomxRequest().build(personUri, HttpMethod.GET);
    ClientResponse response = invoke(request);

    Gedcomx entity = null;
    ClientResponse.Status responseCode = response.getClientResponseStatus();
    switch (responseCode) {
      case OK:
        entity = response.getEntity(Gedcomx.class);
        break;
      default:
        switch (responseCode.getFamily()) {
          case CLIENT_ERROR:
          case SERVER_ERROR:
            throw new GedcomxApplicationException(responseCode.getReasonPhrase(), response);
        }
    }

    if (entity != null && includeEmbedded) {
      includeEmbeddedResources(entity);
    }

    return newApplicationState(this.descriptor, this.accessToken, request, response, entity);
  }

  public GedcomxApplicationState<? extends Gedcomx> getAncestry() {
    Person person = null;
    if (this.entity instanceof Gedcomx) {
      person = ((Gedcomx) this.entity).getPerson();
    }

    if (person == null) {
      throw new GedcomxApplicationException("Unable to determine children: state does not include a person.");
    }

    return getAncestry(person);
  }

  public GedcomxApplicationState<? extends Gedcomx> getAncestry(Person person) {
    return getAncestry(person, true);
  }

  public GedcomxApplicationState<? extends Gedcomx> getAncestry(Person person, boolean includeEmbedded) {
    Link link = person.getLink(Rel.ANCESTRY);
    if (link == null || link.getHref() == null) {
      return null;
    }

    return getGedcomxResource(link.getHref().toURI(), includeEmbedded);
  }

  public GedcomxApplicationState<? extends Gedcomx> getDescendancy() {
    Person person = null;
    if (this.entity instanceof Gedcomx) {
      person = ((Gedcomx) this.entity).getPerson();
    }

    if (person == null) {
      throw new GedcomxApplicationException("Unable to determine children: state does not include a person.");
    }

    return getDescendancy(person);
  }

  public GedcomxApplicationState<? extends Gedcomx> getDescendancy(Person person) {
    return getDescendancy(person, true);
  }

  public GedcomxApplicationState<? extends Gedcomx> getDescendancy(Person person, boolean includeEmbedded) {
    Link link = person.getLink(Rel.DESCENDANCY);
    if (link == null || link.getHref() == null) {
      return null;
    }

    return getGedcomxResource(link.getHref().toURI(), includeEmbedded);
  }

  public GedcomxApplicationState<? extends Gedcomx> getPersonForCurrentUser() {
    return getPersonForCurrentUser(true);
  }

  public GedcomxApplicationState<? extends Gedcomx> getPersonForCurrentUser(boolean includeEmbedded) {
    URI currentUserPersonUri = this.descriptor.getCurrentUserPersonUri();
    if (currentUserPersonUri == null) {
      throw new GedcomxApplicationException(String.format("No current user person URI supplied for API at %s.", this.descriptor.getDiscoveryUri()));
    }

    return getGedcomxResource(currentUserPersonUri, includeEmbedded);
  }

  public GedcomxApplicationState<? extends Gedcomx> getPerson(URI personUri) {
    return getGedcomxResource(personUri, true);
  }

  public GedcomxApplicationState<? extends Gedcomx> getParents() {
    Person person = null;
    if (this.entity instanceof Gedcomx) {
      person = ((Gedcomx) this.entity).getPerson();
    }

    if (person == null) {
      throw new GedcomxApplicationException("Unable to determine parents: state does not include a person.");
    }

    return getParents(person);
  }

  public GedcomxApplicationState<? extends Gedcomx> getParents(Person person) {
    return getParents(person, true);
  }

  public GedcomxApplicationState<? extends Gedcomx> getParents(Person person, boolean includeEmbedded) {
    Link link = person.getLink(Rel.PARENTS);
    if (link == null || link.getHref() == null) {
      return null;
    }

    return getGedcomxResource(link.getHref().toURI(), includeEmbedded);
  }

  public GedcomxApplicationState<? extends Gedcomx> getChildren() {
    Person person = null;
    if (this.entity instanceof Gedcomx) {
      person = ((Gedcomx) this.entity).getPerson();
    }

    if (person == null) {
      throw new GedcomxApplicationException("Unable to determine children: state does not include a person.");
    }

    return getChildren(person);
  }

  public GedcomxApplicationState<? extends Gedcomx> getChildren(Person person) {
    return getChildren(person, true);
  }

  public GedcomxApplicationState<? extends Gedcomx> getChildren(Person person, boolean includeEmbedded) {
    Link link = person.getLink(Rel.CHILDREN);
    if (link == null || link.getHref() == null) {
      return null;
    }

    return getGedcomxResource(link.getHref().toURI(), includeEmbedded);
  }

  public GedcomxApplicationState<? extends Gedcomx> getSpouses() {
    Person person = null;
    if (this.entity instanceof Gedcomx) {
      person = ((Gedcomx) this.entity).getPerson();
    }

    if (person == null) {
      throw new GedcomxApplicationException("Unable to determine spouses: state does not include a person.");
    }

    return getSpouses(person);
  }

  public GedcomxApplicationState<? extends Gedcomx> getSpouses(Person person) {
    return getSpouses(person, true);
  }

  public GedcomxApplicationState<? extends Gedcomx> getSpouses(Person person, boolean includeEmbedded) {
    Link link = person.getLink(Rel.SPOUSES);
    if (link == null || link.getHref() == null) {
      return null;
    }

    return getGedcomxResource(link.getHref().toURI(), includeEmbedded);
  }


  protected <T> GedcomxApplicationState<T> newApplicationState(GedcomxApplicationDescriptor descriptor, String token, ClientRequest request, ClientResponse response, T entity) {
    return new GedcomxApplicationState<T>(descriptor, token, request, response, entity);
  }

  protected ClientRequest.Builder createAuthenticatedGedcomxRequest() {
    return createAuthenticatedRequest().accept(GedcomxConstants.GEDCOMX_JSON_MEDIA_TYPE);
  }

  protected ClientResponse invoke(ClientRequest request) {
    return getClient().handle(request);
  }

  protected ClientRequest.Builder createRequest() {
    return ClientRequest.create();
  }

  protected ClientRequest.Builder createAuthenticatedRequest() {
    ClientRequest.Builder request = createRequest();
    if (this.accessToken != null) {
      request = request.header("Authorization", "Bearer " + this.accessToken);
    }
    return request;
  }

  protected void includeEmbeddedResources(Gedcomx entity) {
    embed(getEmbeddedLinkLoader().loadEmbeddedLinks(entity), entity);
  }

  protected void embed(List<Link> links, Gedcomx entity) {
    for (Link link : links) {
      embed(link, entity);
    }
  }

  protected EmbeddedLinkLoader getEmbeddedLinkLoader() {
    return DEFAULT_EMBEDDED_LINK_LOADER;
  }

  protected void embed(Link link, Gedcomx entity) {
    if (link.getHref() != null) {
      ClientResponse response = invoke(createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET));
      if (response.getClientResponseStatus() == ClientResponse.Status.OK) {
        entity.embed(response.getEntity(Gedcomx.class));
      }
      else {
        //todo: log a warning? throw an error?
      }
    }
  }

}
