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
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.client.urlconnection.URLConnectionClientHandler;
import com.sun.jersey.core.header.LinkHeader;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.node.ObjectNode;
import org.gedcomx.Gedcomx;
import org.gedcomx.atom.AtomModel;
import org.gedcomx.atom.Feed;
import org.gedcomx.conclusion.Person;
import org.gedcomx.links.HypermediaEnabledData;
import org.gedcomx.links.Link;
import org.gedcomx.links.SupportsLinks;
import org.gedcomx.rs.Rel;
import org.gedcomx.rs.client.util.EmbeddedLinkLoader;
import org.gedcomx.rs.client.util.HttpWarning;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.rt.json.GedcomJsonProvider;
import org.gedcomx.rt.xml.GedcomxXmlProvider;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Ryan Heaton
 */
public abstract class GedcomxApplicationState<E> {

  protected static final EmbeddedLinkLoader DEFAULT_EMBEDDED_LINK_LOADER = new EmbeddedLinkLoader();

  protected final Map<String, Link> links;
  protected final Client client;
  protected final ClientRequest request;
  protected final ClientResponse response;
  protected final E entity;
  protected String accessToken;

  protected GedcomxApplicationState(ClientRequest request, Client client, String accessToken) {
    this.client = client;
    this.accessToken = accessToken;
    this.request = request;
    this.response = invoke(this.request);
    this.entity = loadEntity(this.response);
    List<Link> links = loadLinks(this.response, this.entity);
    this.links = new TreeMap<String, Link>();
    for (Link link : links) {
      this.links.put(link.getRel(), link);
    }
  }

  protected static Client loadDefaultClient() {
    return new Client(new URLConnectionClientHandler(), new DefaultClientConfig(GedcomJsonProvider.class, GedcomxXmlProvider.class, JacksonJsonProvider.class));
  }

  protected abstract GedcomxApplicationState newApplicationState(ClientRequest request, Client client, String accessToken);

  protected abstract E loadEntity(ClientResponse response);

  protected abstract SupportsLinks getScope();

  protected List<Link> loadLinks(ClientResponse response, E entity) {
    //load link headers.
    List<String> linkHeaders = response.getHeaders().get("Link");
    ArrayList<Link> links = new ArrayList<Link>(linkHeaders.size());
    for (String header : linkHeaders) {
      LinkHeader lh = LinkHeader.valueOf(header);
      for (String rel : lh.getRel()) {
        Link link = new Link(rel, lh.getUri() == null ? null : org.gedcomx.common.URI.create(lh.getUri().toString()));
        link.setTemplate(lh.getParams().getFirst("template"));
        link.setTitle(lh.getParams().getFirst("title"));
        link.setAccept(lh.getParams().getFirst("accept"));
        link.setAllow(lh.getParams().getFirst("allow"));
        link.setHreflang(lh.getParams().getFirst("hreflang"));
        link.setType(lh.getParams().getFirst("type"));
        links.add(link);
      }
    }

    SupportsLinks scope = getScope();
    if (scope != null && scope.getLinks() != null) {
      links.addAll(scope.getLinks());
    }
    return links;
  }

  public Client getClient() {
    return this.client; //we'll just use the descriptors client as ours.
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
  
  public boolean hasClientError() {
    return this.response.getClientResponseStatus().getFamily() == Response.Status.Family.CLIENT_ERROR;
  }

  public boolean hasServerError() {
    return this.response.getClientResponseStatus().getFamily() == Response.Status.Family.SERVER_ERROR;
  }

  public boolean hasError() {
    return hasClientError() || hasServerError();
  }

  public List<HttpWarning> getWarnings() {
    ArrayList<HttpWarning> warnings = new ArrayList<HttpWarning>();
    List<String> warningValues = this.response.getHeaders().get("Warning");
    if (warningValues != null) {
      for (String warningValue : warningValues) {
        warnings.add(HttpWarning.parse(warningValue));
      }
    }
    return warnings;
  }

  public Link getLink(String rel) {
    return this.links.get(rel);
  }

  public GedcomxApplicationState ifSuccessful() {
    if (hasError()) {
      throw new GedcomxApplicationException(this.response);
    }
    return this;
  }

  protected GedcomxApplicationState authenticateViaOAuth2Password(String username, String password, String clientId) {
    return authenticateViaOAuth2Password(username, password, clientId, null);
  }

  protected GedcomxApplicationState authenticateViaOAuth2Password(String username, String password, String clientId, String clientSecret) {
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

  protected GedcomxApplicationState authenticateViaOAuth2AuthCode(String authCode, String redirect, String clientId) {
    return authenticateViaOAuth2Password(authCode, authCode, clientId, null);
  }

  protected GedcomxApplicationState authenticateViaOAuth2AuthCode(String authCode, String redirect, String clientId, String clientSecret) {
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

  protected GedcomxApplicationState authenticateViaOAuth2ClientCredentials(String clientId, String clientSecret) {
    MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
    formData.putSingle("grant_type", "client_credentials");
    formData.putSingle("client_id", clientId);
    if (clientSecret != null) {
      formData.putSingle("client_secret", clientSecret);
    }
    return authenticateViaOAuth2(formData);
  }

  protected GedcomxApplicationState authenticateViaOAuth2(MultivaluedMap<String, String> formData) {
    Link tokenLink = this.links.get(Rel.OAUTH2_TOKEN);
    if (tokenLink == null || tokenLink.getHref() == null) {
      throw new GedcomxApplicationException(String.format("No OAuth2 token URI supplied for resource at %s.", getUri()));
    }
    URI tokenUri = tokenLink.getHref().toURI();

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

      this.accessToken = access_token.getValueAsText();
      return this;
    }
    else {
      throw new GedcomxApplicationException("Unable to obtain an access token.", response);
    }
  }


  protected ClientRequest.Builder createAuthenticatedFeedRequest() {
    return createAuthenticatedRequest().accept(AtomModel.ATOM_GEDCOMX_JSON_MEDIA_TYPE);
  }

  protected ClientRequest.Builder createAuthenticatedGedcomxRequest() {
    return createAuthenticatedRequest().accept(GedcomxConstants.GEDCOMX_JSON_MEDIA_TYPE).type(GedcomxConstants.GEDCOMX_JSON_MEDIA_TYPE);
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
