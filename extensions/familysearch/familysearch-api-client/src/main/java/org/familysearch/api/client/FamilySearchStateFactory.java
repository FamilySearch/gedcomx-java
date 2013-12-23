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
import org.gedcomx.rs.client.StateFactory;

/**
 * @author Ryan Heaton
 */
public class FamilySearchStateFactory extends StateFactory {

  protected CommentsState newCommentsState(ClientRequest request, ClientResponse response, String accessToken) {
    return new CommentsState(request, response, accessToken, this);
  }

  protected DiscussionsState newDiscussionsState(ClientRequest request, ClientResponse response, String accessToken) {
    return new DiscussionsState(request, response, accessToken, this);
  }

  protected DiscussionState newDiscussionState(ClientRequest request, ClientResponse response, String accessToken) {
    return new DiscussionState(request, response, accessToken, this);
  }

  protected UserState newUserState(ClientRequest request, ClientResponse response, String accessToken) {
    return new UserState(request, response, accessToken, this);
  }

  protected PersonMatchResultsState newPersonMatchResultsState(ClientRequest request, ClientResponse response, String accessToken) {
    return new PersonMatchResultsState(request, response, accessToken, this);
  }

  @Override
  protected FamilySearchCollectionState newCollectionState(ClientRequest request, ClientResponse response, String accessToken) {
    return new FamilySearchCollectionState(request, response, accessToken, this);
  }

}
