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
import org.familysearch.platform.FamilySearchPlatform;
import org.familysearch.platform.users.User;
import org.gedcomx.links.SupportsLinks;
import org.gedcomx.rs.client.GedcomxApplicationState;
import org.gedcomx.rs.client.StateTransitionOption;

/**
 * @author Ryan Heaton
 */
public class UserState extends GedcomxApplicationState<FamilySearchPlatform> {

  public UserState(ClientRequest request, ClientResponse response, String accessToken, FamilySearchStateFactory stateFactory) {
    super(request, response, accessToken, stateFactory);
  }

  @Override
  protected UserState clone(ClientRequest request, ClientResponse response) {
    return new UserState(request, response, this.accessToken, (FamilySearchStateFactory) this.stateFactory);
  }

  @Override
  protected FamilySearchPlatform loadEntity(ClientResponse response) {
    return response.getClientResponseStatus() == ClientResponse.Status.OK ? response.getEntity(FamilySearchPlatform.class) : null;
  }

  @Override
  protected SupportsLinks getScope() {
    return getUser();
  }

  public User getUser() {
    return getEntity() == null ? null : getEntity().getUsers().isEmpty() ? null : getEntity().getUsers().get(0);
  }

  @Override
  public UserState head(StateTransitionOption... options) {
    return (UserState) super.head(options);
  }

  @Override
  public UserState get(StateTransitionOption... options) {
    return (UserState) super.get(options);
  }

  @Override
  public UserState delete(StateTransitionOption... options) {
    return (UserState) super.delete(options);
  }

  @Override
  public UserState put(FamilySearchPlatform entity, StateTransitionOption... options) {
    return (UserState) super.put(entity);
  }
}
