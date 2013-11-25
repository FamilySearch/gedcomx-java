/**
 * Copyright 2011-2012 Intellectual Reserve, Inc.
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
import com.sun.jersey.api.client.ClientResponse;
import org.gedcomx.atom.AtomModel;
import org.gedcomx.atom.Feed;
import org.gedcomx.links.Link;
import org.gedcomx.rs.Rel;

import javax.ws.rs.core.CacheControl;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ryan Heaton
 */
public class GedcomxApiDescriptor {

  private final Client client;
  private final Map<String, Link> links;
  private final URI discoveryUri;
  private long expires;

  public GedcomxApiDescriptor(String discoveryUri) {
    this(discoveryUri, new Client());
  }

  public GedcomxApiDescriptor(String discoveryUri, Client client) {
    this.client = client;
    this.discoveryUri = URI.create(discoveryUri);
    this.links = new HashMap<String, Link>();
    refresh();
  }

  public boolean isExpired() {
    return System.currentTimeMillis() > this.expires;
  }

  public void refresh() {
    ClientResponse response = this.client
      .resource(this.discoveryUri)
      .accept(AtomModel.ATOM_XML_MEDIA_TYPE)
      .get(ClientResponse.class);

    long now = System.currentTimeMillis();
    if (response.getClientResponseStatus() != ClientResponse.Status.OK) {
      throw new GedcomxApiException("Unable to read the discovery resource.", response);
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
        throw new GedcomxApiException("Invalid discovery resource: no links.");
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

  private String findHref(String rel) {
    Link link = this.links.get(rel);
    return link != null && link.getHref() != null ? link.getHref().toString() : null;
  }

  public URI getDiscoveryUri() {
    return discoveryUri;
  }

  public Client getClient() {
    return client;
  }

  public String getOAuth2AuthenticationUri() {
    refreshIfNeeded();
    return findHref(Rel.OAUTH2_AUTHORIZE);
  }

  public String getOAuth2TokenUri() {
    refreshIfNeeded();
    return findHref(Rel.OAUTH2_TOKEN);
  }
}
