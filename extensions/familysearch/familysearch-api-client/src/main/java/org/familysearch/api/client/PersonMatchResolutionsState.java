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

public class PersonMatchResolutionsState extends GedcomxApplicationState<Feed> {

  public PersonMatchResolutionsState(ClientRequest request, ClientResponse response, String accessToken, StateFactory stateFactory) {
    super(request, response, accessToken, stateFactory);
  }

  protected PersonMatchResolutionsState clone(ClientRequest request, ClientResponse response) {
    return new PersonMatchResolutionsState(request, response, this.accessToken, this.stateFactory);
  }

  protected Feed loadEntity(ClientResponse response) {
    return response.getEntity(Feed.class);
  }

  protected SupportsLinks getMainDataElement() {
    return getEntity();
  }
}
