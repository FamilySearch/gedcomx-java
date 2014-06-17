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
package org.familysearch.api.client;

import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import org.gedcomx.Gedcomx;
import org.gedcomx.conclusion.PlaceDescription;
import org.gedcomx.rs.Rel;
import org.gedcomx.rs.client.GedcomxApplicationState;
import org.gedcomx.rs.client.StateFactory;
import org.gedcomx.rs.client.StateTransitionOption;

public class FamilySearchPlaceState extends GedcomxApplicationState<Gedcomx> {

  protected FamilySearchPlaceState(ClientRequest request, ClientResponse response, String accessToken, StateFactory stateFactory) {
    super(request, response, accessToken, stateFactory);
  }

  @Override
  public String getSelfRel() {
    return Rel.PLACE;
  }

  @Override
  protected FamilySearchPlaceState clone(ClientRequest request, ClientResponse response) {
    return new FamilySearchPlaceState(request, response, this.accessToken, this.stateFactory);
  }

  @Override
  public FamilySearchPlaceState ifSuccessful() {
    return (FamilySearchPlaceState) super.ifSuccessful();
  }

  @Override
  public FamilySearchPlaceState head(StateTransitionOption... options) {
    return (FamilySearchPlaceState) super.head(options);
  }

  @Override
  public FamilySearchPlaceState options(StateTransitionOption... options) {
    return (FamilySearchPlaceState) super.options(options);
  }

  @Override
  public FamilySearchPlaceState get(StateTransitionOption... options) {
    return (FamilySearchPlaceState) super.get(options);
  }

  @Override
  public FamilySearchPlaceState delete(StateTransitionOption... options) {
    return (FamilySearchPlaceState) super.delete(options);
  }

  @Override
  public FamilySearchPlaceState put(Gedcomx e, StateTransitionOption... options) {
    return (FamilySearchPlaceState) super.put(e, options);
  }

  @Override
  public FamilySearchPlaceState post(Gedcomx entity, StateTransitionOption... options) {
    return (FamilySearchPlaceState) super.post(entity, options);
  }

  @Override
  protected Gedcomx loadEntity(ClientResponse response) {
    return response.getEntity(Gedcomx.class);
  }

  public PlaceDescription getPlace() {
    return (PlaceDescription) this.anchorElement;
  }

}
