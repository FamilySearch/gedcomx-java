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
package org.familysearch.api.client.gens;

import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import org.familysearch.api.client.FamilySearchStateFactory;

import java.net.URI;

public class GenealogiesStateFactory extends FamilySearchStateFactory {

  public FamilySearchGenealogies newGenealogiesState() {
    return newGenealogiesState(true);
  }

  public FamilySearchGenealogies newGenealogiesState(boolean production) {
    return (FamilySearchGenealogies) newCollectionState(URI.create(production ? FamilySearchGenealogies.URI : FamilySearchGenealogies.SANDBOX_URI));
  }

  public FamilySearchGenealogies newGenealogiesState(URI discoveryUri) {
    return (FamilySearchGenealogies) super.newCollectionState(discoveryUri);
  }

  @Override
  protected GenealogiesTreeState newCollectionState(ClientRequest request, ClientResponse response, String accessToken) {
    return new GenealogiesTreeState(request, response, accessToken, this);
  }

  @Override
  protected GenealogiesPersonState newPersonState(ClientRequest request, ClientResponse response, String accessToken) {
    return new GenealogiesPersonState(request, response, accessToken, this);
  }

}
