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
import com.sun.jersey.core.header.LinkHeader;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;
import org.gedcomx.Gedcomx;
import org.gedcomx.atom.AtomModel;
import org.gedcomx.common.Attributable;
import org.gedcomx.common.Attribution;
import org.gedcomx.common.ResourceReference;
import org.gedcomx.links.Link;
import org.gedcomx.links.SupportsLinks;
import org.gedcomx.rs.Rel;
import org.gedcomx.rs.client.util.EmbeddedLinkLoader;
import org.gedcomx.rs.client.util.HttpWarning;
import org.gedcomx.rt.GedcomxConstants;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.*;

/**
 * @author Ryan Heaton
 */
public abstract class GedcomxApplicationState<E> {

  protected static final EmbeddedLinkLoader DEFAULT_EMBEDDED_LINK_LOADER = new EmbeddedLinkLoader();

  protected final StateFactory stateFactory;
  protected final Map<String, Link> links;
  protected final Client client;
  protected final ClientRequest request;
  protected final ClientResponse response;
  protected final E entity;
  protected String accessToken;
  private ClientRequest lastEmbeddedRequest;
  private ClientResponse lastEmbeddedResponse;

  protected GedcomxApplicationState(ClientRequest request, ClientResponse response, String accessToken, StateFactory stateFactory) {
    this.request = request;
    this.response = response;
    this.client = response.getClient();
    this.accessToken = accessToken;
    this.stateFactory = stateFactory;
    this.entity = loadEntityConditionally(this.response);
    List<Link> links = loadLinks(this.response, this.entity);
    this.links = new TreeMap<String, Link>();
    for (Link link : links) {
      this.links.put(link.getRel(), link);
    }
  }

  protected E loadEntityConditionally(ClientResponse response) {
    if (!HttpMethod.HEAD.equals(request.getMethod()) && response.getClientResponseStatus() == ClientResponse.Status.OK) {
      return loadEntity(response);
    }
    else {
      return null;
    }
  }

  public GedcomxApplicationState inject(ClientRequest request) {
    return clone(request, invoke(request));
  }

  protected abstract GedcomxApplicationState clone(ClientRequest request, ClientResponse response);

  protected abstract E loadEntity(ClientResponse response);

  protected abstract SupportsLinks getScope();

  protected List<Link> loadLinks(ClientResponse response, E entity) {
    ArrayList<Link> links = new ArrayList<Link>();

    //if there's a location, we'll consider it a "self" link.
    if (response.getLocation() != null) {
      links.add(new Link(Rel.SELF, new org.gedcomx.common.URI(response.getLocation().toString())));
    }

    //load link headers.
    List<String> linkHeaders = response.getHeaders().get("Link");
    if (linkHeaders != null) {
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
    }

    //load additional links from entity
    if(entity instanceof Gedcomx && ((Gedcomx) entity).getLinks() != null) {
      links.addAll(((Gedcomx)entity).getLinks());
    }

    //load the links from the hypermedia scope
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
    return request.clone();
  }

  public ClientRequest getLastEmbeddedRequest() {
    return lastEmbeddedRequest;
  }

  public ClientResponse getResponse() {
    return response;
  }

  public ClientResponse getLastEmbeddedResponse() {
    return lastEmbeddedResponse;
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

  public boolean hasStatus(ClientResponse.Status status) {
    return this.response.getClientResponseStatus().equals(status);
  }

  public EntityTag getETag() {
    return this.response.getEntityTag();
  }

  public Date getLastModified() {
    return this.response.getLastModified();
  }

  public MultivaluedMap<String, String> getHeaders() {
    return response.getHeaders();
  }

  public URI getSelfUri() {
    Link link = getLink(Rel.SELF);
    URI self = link == null ? null : link.getHref() == null ? null : link.getHref().toURI();
    return self == null ? getUri() : self;
  }

  public GedcomxApplicationState head(StateTransitionOption... options) {
    ClientRequest.Builder builder = createAuthenticatedRequest();
    Object accept = this.request.getHeaders().getFirst("Accept");
    if (accept != null) {
      builder = builder.accept(String.valueOf(accept));
    }

    ClientRequest request = builder.build(getSelfUri(), HttpMethod.HEAD);
    return clone(request, invoke(request, options));
  }

  public GedcomxApplicationState get(StateTransitionOption... options) {
    ClientRequest.Builder builder = createAuthenticatedRequest();
    Object accept = this.request.getHeaders().getFirst("Accept");
    if (accept != null) {
      builder = builder.accept(String.valueOf(accept));
    }

    ClientRequest request = builder.build(getSelfUri(), HttpMethod.GET);
    ClientResponse response = invoke(request, options);
    return clone(request, response);
  }

  public GedcomxApplicationState delete(StateTransitionOption... options) {
    ClientRequest.Builder builder = createAuthenticatedRequest();
    Object accept = this.request.getHeaders().getFirst("Accept");
    if (accept != null) {
      builder = builder.accept(String.valueOf(accept));
    }
    ClientRequest request = builder.build(getSelfUri(), HttpMethod.DELETE);
    return clone(request, invoke(request, options));
  }

  public GedcomxApplicationState options(StateTransitionOption... options) {
    ClientRequest.Builder builder = createAuthenticatedRequest();
    Object accept = this.request.getHeaders().getFirst("Accept");
    if (accept != null) {
      builder = builder.accept(String.valueOf(accept));
    }
    ClientRequest request = builder.build(getSelfUri(), HttpMethod.OPTIONS);
    return clone(request, invoke(request, options));
  }

  public GedcomxApplicationState put(E entity, StateTransitionOption... options) {
    ClientRequest.Builder builder = createAuthenticatedRequest();
    Object accept = this.request.getHeaders().getFirst("Accept");
    Object contentType = this.request.getHeaders().getFirst("Content-Type");
    if (accept != null) {
      builder = builder.accept(String.valueOf(accept));
    }
    if (contentType != null) {
      builder = builder.type(String.valueOf(contentType));
    }
    ClientRequest request = builder.entity(entity).build(getSelfUri(), HttpMethod.PUT);
    return clone(request, invoke(request, options));
  }

  public GedcomxApplicationState post(E entity, StateTransitionOption... options) {
    ClientRequest.Builder builder = createAuthenticatedRequest();
    Object accept = this.request.getHeaders().getFirst("Accept");
    Object contentType = this.request.getHeaders().getFirst("Content-Type");
    if (accept != null) {
      builder = builder.accept(String.valueOf(accept));
    }
    if (contentType != null) {
      builder = builder.type(String.valueOf(contentType));
    }
    ClientRequest request = builder.entity(entity).build(getSelfUri(), HttpMethod.POST);
    return clone(request, invoke(request, options));
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

  public List<Link> getLinks() {
    return Arrays.asList(this.links.values().toArray(new Link[links.size()]));
  }

  public GedcomxApplicationState ifSuccessful() {
    if (hasError()) {
      throw new GedcomxApplicationException(String.format("Unsuccessful %s to %s", this.request.getMethod(), getUri()), this.response);
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

  protected GedcomxApplicationState authenticateWithAccessToken(String accessToken) {
    this.accessToken = accessToken;
    return this;
  }

  protected GedcomxApplicationState authenticateViaOAuth2(MultivaluedMap<String, String> formData, StateTransitionOption... options) {
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
    ClientResponse response = invoke(request, options);

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

      return authenticateWithAccessToken(access_token.getValueAsText());
    }
    else {
      throw new GedcomxApplicationException("Unable to obtain an access token.", response);
    }
  }

  protected GedcomxApplicationState readPage(String rel, StateTransitionOption... options) {
    Link link = getLink(rel);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest.Builder builder = createAuthenticatedRequest();
    Object accept = this.request.getHeaders().getFirst("Accept");
    Object contentType = this.request.getHeaders().getFirst("Content-Type");
    if (accept != null) {
      builder = builder.accept(String.valueOf(accept));
    }
    if (contentType != null) {
      builder = builder.type(String.valueOf(contentType));
    }
    ClientRequest request = builder.build(link.getHref().toURI(), HttpMethod.GET);
    return clone(request, invoke(request, options));
  }

  protected GedcomxApplicationState readNextPage(StateTransitionOption... options) {
    return readPage(Rel.NEXT);
  }

  protected GedcomxApplicationState readPreviousPage(StateTransitionOption... options) {
    return readPage(Rel.PREVIOUS);
  }

  protected GedcomxApplicationState readFirstPage(StateTransitionOption... options) {
    return readPage(Rel.FIRST);
  }

  protected GedcomxApplicationState readLastPage(StateTransitionOption... options) {
    return readPage(Rel.LAST);
  }

  protected ClientRequest.Builder createAuthenticatedFeedRequest() {
    return createAuthenticatedRequest().accept(AtomModel.ATOM_GEDCOMX_JSON_MEDIA_TYPE);
  }

  protected ClientRequest.Builder createAuthenticatedGedcomxRequest() {
    return createAuthenticatedRequest().accept(GedcomxConstants.GEDCOMX_JSON_MEDIA_TYPE).type(GedcomxConstants.GEDCOMX_JSON_MEDIA_TYPE);
  }

  protected ClientResponse invoke(ClientRequest request, StateTransitionOption... options) {
    for (StateTransitionOption option : options) {
      option.apply(request);
    }
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

  protected void includeEmbeddedResources(Gedcomx entity, StateTransitionOption... options) {
    embed(getEmbeddedLinkLoader().loadEmbeddedLinks(entity), entity, options);
  }

  protected void embed(List<Link> links, Gedcomx entity, StateTransitionOption... options) {
    for (Link link : links) {
      embed(link, entity, options);
    }
  }

  protected EmbeddedLinkLoader getEmbeddedLinkLoader() {
    return DEFAULT_EMBEDDED_LINK_LOADER;
  }

  protected void embed(Link link, Gedcomx entity, StateTransitionOption... options) {
    if (link.getHref() != null) {
      lastEmbeddedRequest = createRequestForEmbeddedResource(link.getRel()).build(link.getHref().toURI(), HttpMethod.GET);
      lastEmbeddedResponse = invoke(lastEmbeddedRequest, options);
      if (lastEmbeddedResponse.getClientResponseStatus() == ClientResponse.Status.OK) {
        entity.embed(lastEmbeddedResponse.getEntity(Gedcomx.class));
      }
      else if (lastEmbeddedResponse.getClientResponseStatus().getFamily() == Response.Status.Family.SERVER_ERROR) {
        throw new GedcomxApplicationException(String.format("Unable to load embedded resources: server says \"%s\" at %s.", lastEmbeddedResponse.getClientResponseStatus().getReasonPhrase(), lastEmbeddedRequest.getURI()), lastEmbeddedResponse);
      }
      else {
        //todo: log a warning? throw an error?
      }
    }
  }

  protected ClientRequest.Builder createRequestForEmbeddedResource(String rel) {
    return createAuthenticatedGedcomxRequest();
  }

  public AgentState readContributor(StateTransitionOption... options) {
    SupportsLinks scope = getScope();
    if (scope instanceof Attributable) {
      return readContributor((Attributable) scope, options);
    }
    else {
      return null;
    }
  }

  public AgentState readContributor(Attributable attributable, StateTransitionOption... options) {
    Attribution attribution = attributable.getAttribution();
    if (attribution == null) {
      return null;
    }

    return readContributor(attribution.getContributor(), options);
  }

  public AgentState readContributor(ResourceReference contributor, StateTransitionOption... options) {
    if (contributor == null || contributor.getResource() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(contributor.getResource().toURI(), HttpMethod.GET);
    return this.stateFactory.newAgentState(request, invoke(request, options), this.accessToken);
  }

}
