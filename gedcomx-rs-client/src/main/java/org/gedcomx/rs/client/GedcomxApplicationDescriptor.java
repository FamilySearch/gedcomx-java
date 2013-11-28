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
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.gedcomx.atom.AtomModel;
import org.gedcomx.atom.Feed;
import org.gedcomx.links.Link;
import org.gedcomx.rs.Rel;
import org.gedcomx.rt.json.GedcomJsonProvider;
import org.gedcomx.rt.xml.GedcomxXmlProvider;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ryan Heaton
 */
public class GedcomxApplicationDescriptor {

  private final Map<String, Link> links;
  private final URI discoveryUri;
  private final Client client;
  private ClientRequest request;
  private ClientResponse response;
  private long expires;

  public GedcomxApplicationDescriptor(URI discoveryUri) {
    this(discoveryUri, loadDefaultClient());
  }

  protected static Client loadDefaultClient() {
    return new Client(new URLConnectionClientHandler(), new DefaultClientConfig(GedcomJsonProvider.class, GedcomxXmlProvider.class, JacksonJsonProvider.class));
  }

  public GedcomxApplicationDescriptor(URI discoveryUri, Client client) {
    this.client = client;
    this.discoveryUri = discoveryUri;
    this.links = new HashMap<String, Link>();
    refresh();
  }

  public boolean isExpired() {
    return System.currentTimeMillis() > this.expires;
  }

  public void refresh() {
    this.request = ClientRequest.create().accept(AtomModel.ATOM_XML_MEDIA_TYPE).build(this.discoveryUri, HttpMethod.GET);
    this.response = this.client.handle(request);

    long now = System.currentTimeMillis();
    if (this.response.getClientResponseStatus() != ClientResponse.Status.OK) {
      throw new GedcomxApplicationException("Unable to read the discovery resource.", response);
    }

    long expirationPeriod = 1000 * 60 * 60 * 24 * 7; //default to an expiration in a week.
    String cacheControlValue = response.getHeaders().getFirst("Cache-Control");
    if (cacheControlValue != null) {
      CacheControl cacheControl = CacheControl.valueOf(cacheControlValue);
      int maxAge = cacheControl.getMaxAge();
      if (maxAge != -1) {
        expirationPeriod = maxAge * 1000;
      }
    }
    this.expires = now + expirationPeriod;


    this.links.clear();
    Feed feed = response.getEntity(Feed.class);
    if (feed != null) {
      List<Link> links = feed.getLinks();
      if (links == null) {
        throw new GedcomxApplicationException("Invalid discovery resource: no links.");
      }

      for (Link link : links) {
        if (link.getRel() != null) {
          this.links.put(link.getRel(), link);
        }
      }
    }
  }

  private void refreshIfNeeded() {
    if (isExpired()) {
      refresh();
    }
  }

  private URI findHref(String rel) {
    Link link = this.links.get(rel);
    return link != null && link.getHref() != null ? link.getHref().toURI() : null;
  }

  public URI getDiscoveryUri() {
    return discoveryUri;
  }

  public Client getClient() {
    return client;
  }

  public ClientRequest getRequest() {
    return request;
  }

  public ClientResponse getResponse() {
    return response;
  }

  public URI getOAuth2AuthenticationUri() {
    refreshIfNeeded();
    return findHref(Rel.OAUTH2_AUTHORIZE);
  }

  public URI buildOAuth2AuthenticationUri(String clientId, String redirectUri) {
    URI authenticationUri = getOAuth2AuthenticationUri();
    if (authenticationUri == null) {
      throw new GedcomxApplicationException(String.format("No OAuth2 authentication URI supplied for API at %s.", getDiscoveryUri()));
    }
    return UriBuilder.fromUri(authenticationUri).queryParam("response_type", "code").queryParam("client_id", clientId).queryParam("redirect_uri", redirectUri).build();
  }

  public URI getOAuth2TokenUri() {
    refreshIfNeeded();
    return findHref(Rel.OAUTH2_TOKEN);
  }

  public URI getCurrentUserPersonUri() {
    refreshIfNeeded();
    return findHref(Rel.CURRENT_USER_PERSON);
  }
}
