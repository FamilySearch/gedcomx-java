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
import org.gedcomx.links.SupportsLinks;
import org.gedcomx.rs.client.GedcomxApplicationState;
import org.gedcomx.rs.client.StateTransitionOption;

import java.util.List;

public class UserHistoryState extends GedcomxApplicationState<Feed> {

  public UserHistoryState(ClientRequest request, ClientResponse response, String accessToken, FamilySearchStateFactory stateFactory) {
    super(request, response, accessToken, stateFactory);
  }

  @Override
  protected UserHistoryState clone(ClientRequest request, ClientResponse response) {
    return new UserHistoryState(request, response, this.accessToken, (FamilySearchStateFactory) this.stateFactory);
  }

  @Override
  protected Feed loadEntity(ClientResponse response) {
    return response.getClientResponseStatus() == ClientResponse.Status.OK ? response.getEntity(Feed.class) : null;
  }

  @Override
  protected SupportsLinks getMainDataElement() {
    return getEntity();
  }

  public List<Entry> getUserHistory() {
    return getEntity() == null ? null : getEntity().getEntries();
  }

  @Override
  public UserHistoryState head(StateTransitionOption... options) {
    return (UserHistoryState) super.head(options);
  }

  @Override
  public UserHistoryState get(StateTransitionOption... options) {
    return (UserHistoryState) super.get(options);
  }

  @Override
  public UserHistoryState delete(StateTransitionOption... options) {
    return (UserHistoryState) super.delete(options);
  }

  @Override
  public UserHistoryState put(Feed entity, StateTransitionOption... options) {
    return (UserHistoryState) super.put(entity);
  }

  @Override
  public UserHistoryState post(Feed entity, StateTransitionOption... options) {
    return (UserHistoryState) super.post(entity, options);
  }

  @Override
  public UserHistoryState ifSuccessful() {
    return (UserHistoryState) super.ifSuccessful();
  }
}
