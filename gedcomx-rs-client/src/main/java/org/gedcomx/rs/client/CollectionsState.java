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
import org.gedcomx.records.Collection;
import org.gedcomx.rs.Rel;
import org.gedcomx.source.SourceDescription;

import javax.ws.rs.HttpMethod;
import java.net.URI;
import java.util.List;

/**
 * @author Ryan Heaton
 */
public class CollectionsState extends GedcomxApplicationState<Gedcomx> {

  protected CollectionsState(ClientRequest request, ClientResponse response, String accessToken, StateFactory stateFactory) {
    super(request, response, accessToken, stateFactory);
  }

  @Override
  protected CollectionsState clone(ClientRequest request, ClientResponse response) {
    return new CollectionsState(request, response, this.accessToken, this.stateFactory);
  }

  @Override
  public CollectionsState ifSuccessful() {
    return (CollectionsState) super.ifSuccessful();
  }

  @Override
  protected Gedcomx loadEntity(ClientResponse response) {
    return response.getEntity(Gedcomx.class);
  }

  @Override
  protected SupportsLinks getMainDataElement() {
    return getEntity();
  }

  @Override
  public CollectionsState head(StateTransitionOption... options) {
    return (CollectionsState) super.head(options);
  }

  @Override
  public CollectionsState options(StateTransitionOption... options) {
    return (CollectionsState) super.options(options);
  }

  @Override
  public CollectionsState get(StateTransitionOption... options) {
    return (CollectionsState) super.get(options);
  }

  @Override
  public CollectionsState delete(StateTransitionOption... options) {
    return (CollectionsState) super.delete(options);
  }

  @Override
  public CollectionsState put(Gedcomx e, StateTransitionOption... options) {
    return (CollectionsState) super.put(e, options);
  }

  @Override
  public CollectionsState post(Gedcomx entity, StateTransitionOption... options) {
    return (CollectionsState) super.post(entity, options);
  }

  public List<Collection> getCollections() {
    return this.entity == null ? null : this.entity.getCollections();
  }

  public List<SourceDescription> getSourceDescriptions() {
    return this.entity == null ? null : this.entity.getSourceDescriptions();
  }

  public CollectionState readCollection(Collection collection, StateTransitionOption... options) {
    Link link = collection.getLink("self");
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return this.stateFactory.newCollectionState(request, invoke(request, options), this.accessToken);
  }

  public CollectionState updateCollection(Collection collection, StateTransitionOption... options) {
    Link link = collection.getLink("self");
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.POST);
    return this.stateFactory.newCollectionState(request, invoke(request, options), this.accessToken);
  }

  public CollectionState readCollection(SourceDescription sourceDescription, StateTransitionOption... options) {
    ClientRequest request = createAuthenticatedGedcomxRequest().build(sourceDescription.getAbout().toURI(), HttpMethod.GET);
    return this.stateFactory.newCollectionState(request, invoke(request, options), this.accessToken);

  }

  public CollectionState readCollection(StateTransitionOption... options) {
    Link link = getLink(Rel.COLLECTION);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return this.stateFactory.newCollectionState(request, invoke(request, options), this.accessToken);
  }

  public CollectionState addCollection(Collection collection, StateTransitionOption... options) {
    return addCollection(collection, null, options);
  }

  public CollectionState addCollection(SourceDescription description, StateTransitionOption... options) {
    return addCollection(null, description, options);
  }

  public CollectionState addCollection(Collection collection, SourceDescription sourceDescription, StateTransitionOption... options) {
    Link link = getLink(Rel.SUBCOLLECTIONS);
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException(String.format("Collection at %s doesn't support adding subcollections.", getUri()));
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().entity(new Gedcomx().collection(collection).sourceDescription(sourceDescription)).build(link.getHref().toURI(), HttpMethod.POST);
    return this.stateFactory.newCollectionState(request, invoke(request, options), this.accessToken);
  }

  @Override
  public CollectionState readNextPage(StateTransitionOption... options) {
    return (CollectionState) super.readNextPage(options);
  }

  @Override
  public CollectionState readPreviousPage(StateTransitionOption... options) {
    return (CollectionState) super.readPreviousPage(options);
  }

  @Override
  public CollectionState readFirstPage(StateTransitionOption... options) {
    return (CollectionState) super.readFirstPage(options);
  }

  @Override
  public CollectionState readLastPage(StateTransitionOption... options) {
    return (CollectionState) super.readLastPage(options);
  }
}
