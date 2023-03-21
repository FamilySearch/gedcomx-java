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
import org.gedcomx.rt.Rel;

import jakarta.ws.rs.HttpMethod;

public class PlaceDescriptionState extends GedcomxApplicationState<Gedcomx> {

  protected PlaceDescriptionState(ClientRequest request, ClientResponse response, String accessToken, StateFactory stateFactory) {
    super(request, response, accessToken, stateFactory);
  }

  @Override
  public String getSelfRel() {
    return Rel.DESCRIPTION;
  }

  @Override
  protected PlaceDescriptionState clone(ClientRequest request, ClientResponse response) {
    return new PlaceDescriptionState(request, response, this.accessToken, this.stateFactory);
  }

  @Override
  public PlaceDescriptionState ifSuccessful() {
    return (PlaceDescriptionState) super.ifSuccessful();
  }

  @Override
  public PlaceDescriptionState head(StateTransitionOption... options) {
    return (PlaceDescriptionState) super.head(options);
  }

  @Override
  public PlaceDescriptionState options(StateTransitionOption... options) {
    return (PlaceDescriptionState) super.options(options);
  }

  @Override
  public PlaceDescriptionState get(StateTransitionOption... options) {
    return (PlaceDescriptionState) super.get(options);
  }

  @Override
  public PlaceDescriptionState delete(StateTransitionOption... options) {
    return (PlaceDescriptionState) super.delete(options);
  }

  @Override
  public PlaceDescriptionState put(Gedcomx e, StateTransitionOption... options) {
    return (PlaceDescriptionState) super.put(e, options);
  }

  @Override
  public PlaceDescriptionState post(Gedcomx entity, StateTransitionOption... options) {
    return (PlaceDescriptionState) super.post(entity, options);
  }

  @Override
  protected Gedcomx loadEntity(ClientResponse response) {
    return response.getEntity(Gedcomx.class);
  }

  @Override
  protected SupportsLinks getMainDataElement() {
    return getPlaceDescription();
  }

  public PlaceDescription getPlaceDescription() {
    return getEntity() == null ? null : getEntity().getPlaces() == null ? null : getEntity().getPlaces().isEmpty() ? null : getEntity().getPlaces().get(0);
  }

  public PlaceDescriptionsState readChildren(StateTransitionOption... options) {
    Link link = getLink(Rel.CHILDREN);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return this.stateFactory.newPlaceDescriptionsState(request, invoke(request, options), this.accessToken);
  }

}
