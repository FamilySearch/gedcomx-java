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
import org.gedcomx.rt.Rel;
import org.gedcomx.source.SourceDescription;

import jakarta.ws.rs.HttpMethod;
import java.util.List;

/**
 * @author Ryan Heaton
 */
public class SourceDescriptionsState extends GedcomxApplicationState<Gedcomx> {

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
  public SourceDescriptionsState head(StateTransitionOption... options) {
    return (SourceDescriptionsState) super.head(options);
  }

  @Override
  public SourceDescriptionsState options(StateTransitionOption... options) {
    return (SourceDescriptionsState) super.options(options);
  }

  @Override
  public SourceDescriptionsState get(StateTransitionOption... options) {
    return (SourceDescriptionsState) super.get(options);
  }

  @Override
  public SourceDescriptionsState delete(StateTransitionOption... options) {
    return (SourceDescriptionsState) super.delete(options);
  }

  @Override
  public SourceDescriptionsState put(Gedcomx e, StateTransitionOption... options) {
    return (SourceDescriptionsState) super.put(e, options);
  }

  @Override
  public SourceDescriptionsState post(Gedcomx entity, StateTransitionOption... options) {
    return (SourceDescriptionsState) super.post(entity, options);
  }

  @Override
  protected SupportsLinks getMainDataElement() {
    return getEntity();
  }

  @Override
  public SourceDescriptionsState readNextPage(StateTransitionOption... options) {
    return (SourceDescriptionsState) super.readNextPage(options);
  }

  @Override
  public SourceDescriptionsState readPreviousPage(StateTransitionOption... options) {
    return (SourceDescriptionsState) super.readPreviousPage(options);
  }

  @Override
  public SourceDescriptionsState readFirstPage(StateTransitionOption... options) {
    return (SourceDescriptionsState) super.readFirstPage(options);
  }

  @Override
  public SourceDescriptionsState readLastPage(StateTransitionOption... options) {
    return (SourceDescriptionsState) super.readLastPage(options);
  }

  public SourceDescriptionState addSourceDescription(SourceDescription source, StateTransitionOption... options) {
    Gedcomx entity = new Gedcomx();
    entity.addSourceDescription(source);
    ClientRequest request = createAuthenticatedGedcomxRequest().entity(entity).build(getSelfUri(), HttpMethod.POST);
    return this.stateFactory.newSourceDescriptionState(request, invoke(request, options), this.accessToken);
  }

  public CollectionState readCollection(StateTransitionOption... options) {
    Link link = getLink(Rel.COLLECTION);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return this.stateFactory.newCollectionState(request, invoke(request, options), this.accessToken);
  }

  public SourceDescriptionState readSourceDescription(SourceDescription sourceDescription, StateTransitionOption... options) {
    Link link = sourceDescription.getLink(Rel.DESCRIPTION);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return this.stateFactory.newSourceDescriptionState(request, invoke(request, options), this.accessToken);
  }
}
