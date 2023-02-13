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
import org.gedcomx.atom.Entry;
import org.gedcomx.atom.Feed;
import org.gedcomx.links.Link;
import org.gedcomx.links.SupportsLinks;
import org.gedcomx.rs.client.GedcomxApplicationState;
import org.gedcomx.rs.client.PlaceDescriptionState;
import org.gedcomx.rs.client.StateFactory;
import org.gedcomx.rs.client.StateTransitionOption;
import org.gedcomx.rt.Rel;

import javax.ws.rs.HttpMethod;

public class PlaceSearchResultsState extends GedcomxApplicationState<Feed> {

  protected PlaceSearchResultsState(ClientRequest request, ClientResponse response, String accessToken, StateFactory stateFactory) {
    super(request, response, accessToken, stateFactory);
  }

  @Override
  protected PlaceSearchResultsState clone(ClientRequest request, ClientResponse response) {
    return new PlaceSearchResultsState(request, response, this.accessToken, this.stateFactory);
  }

  @Override
  protected Feed loadEntity(ClientResponse response) {
    return response.getEntity(Feed.class);
  }

  @Override
  public PlaceSearchResultsState ifSuccessful() {
    return (PlaceSearchResultsState) super.ifSuccessful();
  }

  @Override
  public PlaceSearchResultsState head(StateTransitionOption... options) {
    return (PlaceSearchResultsState) super.head(options);
  }

  @Override
  public PlaceSearchResultsState options(StateTransitionOption... options) {
    return (PlaceSearchResultsState) super.options(options);
  }

  @Override
  public PlaceSearchResultsState get(StateTransitionOption... options) {
    return (PlaceSearchResultsState) super.get(options);
  }

  @Override
  public PlaceSearchResultsState delete(StateTransitionOption... options) {
    return (PlaceSearchResultsState) super.delete(options);
  }

  @Override
  public PlaceSearchResultsState put(Feed e, StateTransitionOption... options) {
    return (PlaceSearchResultsState) super.put(e, options);
  }

  @Override
  public PlaceSearchResultsState post(Feed entity, StateTransitionOption... options) {
    return (PlaceSearchResultsState) super.post(entity, options);
  }

  @Override
  protected SupportsLinks getMainDataElement() {
    return getEntity();
  }

  public Feed getResults() {
    return getEntity();
  }

  public PlaceDescriptionState readPlaceDescription(Entry place, StateTransitionOption... options) {
    Link link = place.getLink(Rel.DESCRIPTION);
    link = link == null ? place.getLink(Rel.SELF) : link;
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return ((FamilySearchStateFactory)this.stateFactory).newPlaceDescriptionState(request, invoke(request, options), this.accessToken);
  }

  @Override
  public PlaceSearchResultsState readNextPage(StateTransitionOption... options) {
    return (PlaceSearchResultsState) super.readNextPage(options);
  }

  @Override
  public PlaceSearchResultsState readPreviousPage(StateTransitionOption... options) {
    return (PlaceSearchResultsState) super.readPreviousPage(options);
  }

  @Override
  public PlaceSearchResultsState readFirstPage(StateTransitionOption... options) {
    return (PlaceSearchResultsState) super.readFirstPage(options);
  }

  @Override
  public PlaceSearchResultsState readLastPage(StateTransitionOption... options) {
    return (PlaceSearchResultsState) super.readLastPage(options);
  }
}
