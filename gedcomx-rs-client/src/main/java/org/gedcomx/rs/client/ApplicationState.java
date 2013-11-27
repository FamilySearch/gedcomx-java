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

import com.sun.jersey.api.client.ClientResponse;
import org.gedcomx.links.HypermediaEnabledData;

/**
 * @author Ryan Heaton
 */
public class ApplicationState<R extends HypermediaEnabledData> {

  private final ClientResponse response;
  private final R entity;

  public ApplicationState(ClientResponse response, R entity) {
    this.response = response;
    this.entity = entity;
  }

  public ClientResponse getResponse() {
    return response;
  }

  public R getEntity() {
    return entity;
  }
}
