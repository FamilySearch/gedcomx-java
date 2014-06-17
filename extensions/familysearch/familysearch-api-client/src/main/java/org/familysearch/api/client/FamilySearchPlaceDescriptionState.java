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
import org.familysearch.platform.rt.FamilySearchPlatformAnchorFinder;
import org.gedcomx.Gedcomx;
import org.gedcomx.links.AnchorElementSupport;
import org.gedcomx.links.Link;
import org.gedcomx.rs.client.PlaceDescriptionState;
import org.gedcomx.rs.client.StateTransitionOption;

import javax.ws.rs.HttpMethod;

/**
 * @author Ryan Heaton
 */
public class FamilySearchPlaceDescriptionState extends PlaceDescriptionState {

  protected FamilySearchPlaceDescriptionState(ClientRequest request, ClientResponse response, String accessToken, FamilySearchStateFactory stateFactory) {
    super(request, response, accessToken, stateFactory);
  }

  @Override
  protected FamilySearchPlaceDescriptionState clone(ClientRequest request, ClientResponse response) {
    return new FamilySearchPlaceDescriptionState(request, response, this.accessToken, (FamilySearchStateFactory) this.stateFactory);
  }

  @Override
  public FamilySearchPlaceDescriptionState ifSuccessful() {
    return (FamilySearchPlaceDescriptionState) super.ifSuccessful();
  }

  @Override
  public FamilySearchPlaceDescriptionState head(StateTransitionOption... options) {
    return (FamilySearchPlaceDescriptionState) super.head(options);
  }

  @Override
  public FamilySearchPlaceDescriptionState options(StateTransitionOption... options) {
    return (FamilySearchPlaceDescriptionState) super.options(options);
  }

  @Override
  public FamilySearchPlaceDescriptionState get(StateTransitionOption... options) {
    return (FamilySearchPlaceDescriptionState) super.get(options);
  }

  @Override
  public FamilySearchPlaceDescriptionState delete(StateTransitionOption... options) {
    return (FamilySearchPlaceDescriptionState) super.delete(options);
  }

  @Override
  public FamilySearchPlaceDescriptionState put(Gedcomx e, StateTransitionOption... options) {
    return (FamilySearchPlaceDescriptionState) super.put(e, options);
  }

  @Override
  public FamilySearchPlaceDescriptionState post(Gedcomx entity, StateTransitionOption... options) {
    return (FamilySearchPlaceDescriptionState) super.post(entity, options);
  }

  public FamilySearchPlaceState readPlace(StateTransitionOption... options) {
    Link link = this.getLink(Rel.PLACE);
    link = link == null ? this.getLink(Rel.SELF) : link;
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return ((FamilySearchStateFactory)this.stateFactory).newPlaceState(request, invoke(request, options), this.accessToken);
  }

  @Override
  protected AnchorElementSupport findAnchor() {
    AnchorElementSupport anchor = null;
    if (this.entity != null) {
      anchor = FamilySearchPlatformAnchorFinder.findAnchor(this.entity);
    }
    return anchor;
  }
}
