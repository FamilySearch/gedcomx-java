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
import org.gedcomx.conclusion.PlaceDescription;
import org.gedcomx.links.Link;
import org.gedcomx.links.SupportsLinks;
import org.gedcomx.rs.Rel;

import javax.ws.rs.HttpMethod;
import java.util.List;

/**
 * @author Ryan Heaton
 */
public class PlaceDescriptionsState extends GedcomxApplicationState<Gedcomx> {

  protected PlaceDescriptionsState(ClientRequest request, ClientResponse response, String accessToken, StateFactory stateFactory) {
    super(request, response, accessToken, stateFactory);
  }

  @Override
  protected PlaceDescriptionsState clone(ClientRequest request, ClientResponse response) {
    return new PlaceDescriptionsState(request, response, this.accessToken, this.stateFactory);
  }

  @Override
  protected Gedcomx loadEntity(ClientResponse response) {
    return response.getEntity(Gedcomx.class);
  }

  @Override
  public PlaceDescriptionsState ifSuccessful() {
    return (PlaceDescriptionsState) super.ifSuccessful();
  }

  public List<PlaceDescription> getPlaceDescriptions() {
    return getEntity() == null ? null : getEntity().getPlaces();
  }

  @Override
  public PlaceDescriptionsState head(StateTransitionOption... options) {
    return (PlaceDescriptionsState) super.head(options);
  }

  @Override
  public PlaceDescriptionsState options(StateTransitionOption... options) {
    return (PlaceDescriptionsState) super.options(options);
  }

  @Override
  public PlaceDescriptionsState get(StateTransitionOption... options) {
    return (PlaceDescriptionsState) super.get(options);
  }

  @Override
  public PlaceDescriptionsState delete(StateTransitionOption... options) {
    return (PlaceDescriptionsState) super.delete(options);
  }

  @Override
  public PlaceDescriptionsState put(Gedcomx e, StateTransitionOption... options) {
    return (PlaceDescriptionsState) super.put(e, options);
  }

  @Override
  public PlaceDescriptionsState post(Gedcomx entity, StateTransitionOption... options) {
    return (PlaceDescriptionsState) super.post(entity, options);
  }

  @Override
  protected SupportsLinks getScope() {
    return getEntity();
  }

  @Override
  public PlaceDescriptionsState readNextPage(StateTransitionOption... options) {
    return (PlaceDescriptionsState) super.readNextPage(options);
  }

  @Override
  public PlaceDescriptionsState readPreviousPage(StateTransitionOption... options) {
    return (PlaceDescriptionsState) super.readPreviousPage(options);
  }

  @Override
  public PlaceDescriptionsState readFirstPage(StateTransitionOption... options) {
    return (PlaceDescriptionsState) super.readFirstPage(options);
  }

  @Override
  public PlaceDescriptionsState readLastPage(StateTransitionOption... options) {
    return (PlaceDescriptionsState) super.readLastPage(options);
  }

  public PlaceDescriptionState addPlaceDescription(PlaceDescription place, StateTransitionOption... options) {
    Gedcomx entity = new Gedcomx();
    entity.addPlace(place);
    ClientRequest request = createAuthenticatedGedcomxRequest().entity(entity).build(getSelfUri(), HttpMethod.POST);
    return this.stateFactory.newPlaceDescriptionState(request, invoke(request, options), this.accessToken);
  }

  public CollectionState readCollection(StateTransitionOption... options) {
    Link link = getLink(Rel.COLLECTION);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return this.stateFactory.newCollectionState(request, invoke(request, options), this.accessToken);
  }

}
