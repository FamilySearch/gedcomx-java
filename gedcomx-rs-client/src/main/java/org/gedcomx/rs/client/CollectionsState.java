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
import org.gedcomx.links.Link;
import org.gedcomx.links.SupportsLinks;
import org.gedcomx.records.Collection;
import org.gedcomx.rs.Rel;
import org.gedcomx.rt.GedcomxConstants;

import javax.ws.rs.HttpMethod;
import java.net.URI;
import java.util.List;

/**
 * @author Ryan Heaton
 */
public class CollectionsState extends GedcomxApplicationState<Gedcomx> {

  public CollectionsState(URI discoveryUri) {
    this(discoveryUri, loadDefaultClient());
  }

  public CollectionsState(URI discoveryUri, Client client) {
    this(ClientRequest.create().accept(GedcomxConstants.GEDCOMX_JSON_MEDIA_TYPE).build(discoveryUri, HttpMethod.GET), client, null);
  }

  public CollectionsState(ClientRequest request, Client client, String accessToken) {
    super(request, client, accessToken);
  }

  @Override
  protected CollectionsState newApplicationState(ClientRequest request, Client client, String accessToken) {
    return new CollectionsState(request, client, accessToken);
  }

  @Override
  public CollectionsState ifSuccessful() {
    return (CollectionsState) super.ifSuccessful();
  }

  @Override
  protected Gedcomx loadEntity(ClientResponse response) {
    return response.getClientResponseStatus() == ClientResponse.Status.OK ? response.getEntity(Gedcomx.class) : null;
  }

  @Override
  protected SupportsLinks getScope() {
    return getEntity();
  }

  public List<Collection> getCollections() {
    return this.entity == null ? null : this.entity.getCollections();
  }

  public CollectionState readCollection(Collection collection) {
    Link link = collection.getLink("self");
    if (link == null || link.getHref() == null) {
      return null;
    }

    return new CollectionState(createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET), this.client, this.accessToken);
  }

  public CollectionState readCollection() {
    Link link = getLink(Rel.COLLECTION);
    if (link == null || link.getHref() == null) {
      return null;
    }

    return new CollectionState(createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET), this.client, this.accessToken);
  }

  public CollectionState add(Collection collection) {
    Link link = getLink("self");
    URI href = link == null ? null : link.getHref() == null ? null : link.getHref().toURI();
    href = href == null ? getUri() : href;

    return new CollectionState(createAuthenticatedGedcomxRequest().build(href, HttpMethod.POST), this.client, this.accessToken).ifSuccessful();
  }

}
