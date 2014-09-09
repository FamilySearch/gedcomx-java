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

import java.util.List;

import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import org.gedcomx.Gedcomx;
import org.gedcomx.links.SupportsLinks;
import org.gedcomx.rs.client.GedcomxApplicationState;
import org.gedcomx.rs.client.StateTransitionOption;
import org.gedcomx.source.SourceDescription;

import org.familysearch.platform.FamilySearchPlatform;
import org.familysearch.platform.users.User;

/**
 */
public class UserHistoryState extends GedcomxApplicationState<Gedcomx> {

  public UserHistoryState(ClientRequest request, ClientResponse response, String accessToken, FamilySearchStateFactory stateFactory) {
    super(request, response, accessToken, stateFactory);
  }

  @Override
  protected UserHistoryState clone(ClientRequest request, ClientResponse response) {
    return new UserHistoryState(request, response, this.accessToken, (FamilySearchStateFactory) this.stateFactory);
  }

  @Override
  protected FamilySearchPlatform loadEntity(ClientResponse response) {
    return response.getClientResponseStatus() == ClientResponse.Status.OK ? response.getEntity(FamilySearchPlatform.class) : null;
  }

  @Override
  protected SupportsLinks getMainDataElement() {
    return getEntity();
  }

  public List<SourceDescription> getUserHistory() {
    return getEntity() == null ? null : getEntity().getSourceDescriptions();
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
  public UserHistoryState put(Gedcomx entity, StateTransitionOption... options) {
    return (UserHistoryState) super.put(entity);
  }

  @Override
  public UserHistoryState post(Gedcomx entity, StateTransitionOption... options) {
    return (UserHistoryState) super.post(entity, options);
  }

  @Override
  public UserHistoryState ifSuccessful() {
    return (UserHistoryState) super.ifSuccessful();
  }
}
