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

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientRequest;
import org.gedcomx.rs.client.CollectionState;

import java.net.URI;

/**
 * @author Ryan Heaton
 */
public class FamilyTreeCollectionState extends CollectionState {

  public FamilyTreeCollectionState(URI discoveryUri) {
    super(discoveryUri);
  }

  public FamilyTreeCollectionState(URI discoveryUri, Client client) {
    super(discoveryUri, client);
  }

  public FamilyTreeCollectionState(URI discoveryUri, Client client, String method) {
    super(discoveryUri, client, method);
  }

  public FamilyTreeCollectionState(ClientRequest request, Client client, String accessToken) {
    super(request, client, accessToken);
  }
}
