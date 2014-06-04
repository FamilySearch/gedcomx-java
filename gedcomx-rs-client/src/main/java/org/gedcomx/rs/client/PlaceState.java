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
import org.gedcomx.links.SupportsLinks;
import org.gedcomx.rs.Rel;

public class PlaceState extends GedcomxApplicationState<Gedcomx> {

  protected PlaceState(ClientRequest request, ClientResponse response, String accessToken, StateFactory stateFactory) {
    super(request, response, accessToken, stateFactory);
  }

  @Override
  public String getSelfRel() {
    return Rel.PLACE;
  }

  @Override
  protected PlaceState clone(ClientRequest request, ClientResponse response) {
    return new PlaceState(request, response, this.accessToken, this.stateFactory);
  }

  @Override
  public PlaceState ifSuccessful() {
    return (PlaceState) super.ifSuccessful();
  }

  @Override
  public PlaceState head(StateTransitionOption... options) {
    return (PlaceState) super.head(options);
  }

  @Override
  public PlaceState options(StateTransitionOption... options) {
    return (PlaceState) super.options(options);
  }

  @Override
  public PlaceState get(StateTransitionOption... options) {
    return (PlaceState) super.get(options);
  }

  @Override
  public PlaceState delete(StateTransitionOption... options) {
    return (PlaceState) super.delete(options);
  }

  @Override
  public PlaceState put(Gedcomx e, StateTransitionOption... options) {
    return (PlaceState) super.put(e, options);
  }

  @Override
  public PlaceState post(Gedcomx entity, StateTransitionOption... options) {
    return (PlaceState) super.post(entity, options);
  }

  @Override
  protected Gedcomx loadEntity(ClientResponse response) {
    return response.getEntity(Gedcomx.class);
  }

  @Override
  protected SupportsLinks getScope() {
    return getPlace();
  }

  public PlaceDescription getPlace() {
    return getEntity() == null ? null : getEntity().getPlaces() == null ? null : getEntity().getPlaces().isEmpty() ? null : getEntity().getPlaces().get(0);
  }

}
