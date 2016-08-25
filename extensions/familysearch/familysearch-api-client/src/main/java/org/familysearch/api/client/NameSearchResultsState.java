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
import org.gedcomx.atom.Feed;
import org.gedcomx.links.SupportsLinks;
import org.gedcomx.rs.client.GedcomxApplicationState;
import org.gedcomx.rs.client.StateFactory;
import org.gedcomx.rs.client.StateTransitionOption;

public class NameSearchResultsState extends GedcomxApplicationState<Feed> {

  protected NameSearchResultsState(ClientRequest request, ClientResponse response, String accessToken, StateFactory stateFactory) {
    super(request, response, accessToken, stateFactory);
  }

  @Override
  protected NameSearchResultsState clone(ClientRequest request, ClientResponse response) {
    return new NameSearchResultsState(request, response, this.accessToken, this.stateFactory);
  }

  @Override
  protected Feed loadEntity(ClientResponse response) {
    return response.getEntity(Feed.class);
  }

  @Override
  public NameSearchResultsState ifSuccessful() {
    return (NameSearchResultsState) super.ifSuccessful();
  }

  @Override
  public NameSearchResultsState head(StateTransitionOption... options) {
    return (NameSearchResultsState) super.head(options);
  }

  @Override
  public NameSearchResultsState options(StateTransitionOption... options) {
    return (NameSearchResultsState) super.options(options);
  }

  @Override
  public NameSearchResultsState get(StateTransitionOption... options) {
    return (NameSearchResultsState) super.get(options);
  }

  @Override
  public NameSearchResultsState delete(StateTransitionOption... options) {
    return (NameSearchResultsState) super.delete(options);
  }

  @Override
  public NameSearchResultsState put(Feed e, StateTransitionOption... options) {
    return (NameSearchResultsState) super.put(e, options);
  }

  @Override
  public NameSearchResultsState post(Feed entity, StateTransitionOption... options) {
    return (NameSearchResultsState) super.post(entity, options);
  }

  @Override
  protected SupportsLinks getMainDataElement() {
    return getEntity();
  }

  public Feed getResults() {
    return getEntity();
  }

  @Override
  public NameSearchResultsState readNextPage(StateTransitionOption... options) {
    return (NameSearchResultsState) super.readNextPage(options);
  }

  @Override
  public NameSearchResultsState readPreviousPage(StateTransitionOption... options) {
    return (NameSearchResultsState) super.readPreviousPage(options);
  }

  @Override
  public NameSearchResultsState readFirstPage(StateTransitionOption... options) {
    return (NameSearchResultsState) super.readFirstPage(options);
  }

  @Override
  public NameSearchResultsState readLastPage(StateTransitionOption... options) {
    return (NameSearchResultsState) super.readLastPage(options);
  }
}
