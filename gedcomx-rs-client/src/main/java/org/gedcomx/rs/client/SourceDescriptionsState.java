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

import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import org.gedcomx.Gedcomx;
import org.gedcomx.links.Link;
import org.gedcomx.links.SupportsLinks;
import org.gedcomx.rs.Rel;
import org.gedcomx.source.SourceDescription;

import javax.ws.rs.HttpMethod;
import java.util.List;

/**
 * @author Ryan Heaton
 */
public class SourceDescriptionsState<E> extends GedcomxApplicationState<Gedcomx> {

  protected SourceDescriptionsState(ClientRequest request, ClientResponse response, String accessToken, StateFactory stateFactory) {
    super(request, response, accessToken, stateFactory);
  }

  @Override
  protected SourceDescriptionsState clone(ClientRequest request, ClientResponse response) {
    return new SourceDescriptionsState(request, response, this.accessToken, this.stateFactory);
  }

  @Override
  protected Gedcomx loadEntity(ClientResponse response) {
    return response.getEntity(Gedcomx.class);
  }

  @Override
  public SourceDescriptionsState ifSuccessful() {
    return (SourceDescriptionsState) super.ifSuccessful();
  }

  public List<SourceDescription> getSourceDescriptions() {
    return getEntity() == null ? null : getEntity().getSourceDescriptions();
  }

  @Override
  public SourceDescriptionsState head() {
    return (SourceDescriptionsState) super.head();
  }

  @Override
  public SourceDescriptionsState options() {
    return (SourceDescriptionsState) super.options();
  }

  @Override
  public SourceDescriptionsState get() {
    return (SourceDescriptionsState) super.get();
  }

  @Override
  public SourceDescriptionsState delete() {
    return (SourceDescriptionsState) super.delete();
  }

  @Override
  public SourceDescriptionsState put(Gedcomx e) {
    return (SourceDescriptionsState) super.put(e);
  }

  @Override
  protected SupportsLinks getScope() {
    return getEntity();
  }

  @Override
  public SourceDescriptionsState readNextPage() {
    return (SourceDescriptionsState) super.readNextPage();
  }

  @Override
  public SourceDescriptionsState readPreviousPage() {
    return (SourceDescriptionsState) super.readPreviousPage();
  }

  @Override
  public SourceDescriptionsState readFirstPage() {
    return (SourceDescriptionsState) super.readFirstPage();
  }

  @Override
  public SourceDescriptionsState readLastPage() {
    return (SourceDescriptionsState) super.readLastPage();
  }

  public SourceDescriptionState addSourceDescription(SourceDescription source) {
    Gedcomx entity = new Gedcomx();
    entity.addSourceDescription(source);
    ClientRequest request = createAuthenticatedGedcomxRequest().entity(entity).build(getSelfUri(), HttpMethod.POST);
    return this.stateFactory.newSourceDescriptionState(request, invoke(request), this.accessToken);
  }

  public CollectionState readCollection() {
    Link link = getLink(Rel.COLLECTION);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return this.stateFactory.newCollectionState(request, invoke(request), this.accessToken);
  }

}
