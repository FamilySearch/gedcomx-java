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
import org.gedcomx.source.SourceDescription;

import java.util.List;

public class PlaceGroupState extends GedcomxApplicationState<Gedcomx> {

  protected PlaceGroupState(ClientRequest request, ClientResponse response, String accessToken, StateFactory stateFactory) {
    super(request, response, accessToken, stateFactory);
  }

  @Override
  public String getSelfRel() {
    return Rel.PLACE_GROUP;
  }

  @Override
  protected PlaceGroupState clone(ClientRequest request, ClientResponse response) {
    return new PlaceGroupState(request, response, this.accessToken, this.stateFactory);
  }

  @Override
  public PlaceGroupState ifSuccessful() {
    return (PlaceGroupState) super.ifSuccessful();
  }

  @Override
  public PlaceGroupState head(StateTransitionOption... options) {
    return (PlaceGroupState) super.head(options);
  }

  @Override
  public PlaceGroupState options(StateTransitionOption... options) {
    return (PlaceGroupState) super.options(options);
  }

  @Override
  public PlaceGroupState get(StateTransitionOption... options) {
    return (PlaceGroupState) super.get(options);
  }

  @Override
  protected Gedcomx loadEntity(ClientResponse response) {
    return response.getEntity(Gedcomx.class);
  }

  @Override
  protected SupportsLinks getMainDataElement() {
    return getPlaceGroup();
  }

  /**
   * Get the place group
   *
   * @return the place group associated with this place group application state
   */
  public SourceDescription getPlaceGroup() {
    return getEntity() == null ? null : getEntity().getSourceDescription();
  }

}
