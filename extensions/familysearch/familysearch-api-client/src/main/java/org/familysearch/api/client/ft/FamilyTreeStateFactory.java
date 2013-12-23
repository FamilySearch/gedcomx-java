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
package org.familysearch.api.client.ft;

import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import org.familysearch.api.client.FamilySearchStateFactory;

import java.net.URI;

/**
 * @author Ryan Heaton
 */
public class FamilyTreeStateFactory extends FamilySearchStateFactory {

  public FamilyTreeCollectionState newFamilyTreeState() {
    return newFamilyTreeState(true);
  }

  public FamilyTreeCollectionState newFamilyTreeState(boolean production) {
    return (FamilyTreeCollectionState) newCollectionState(URI.create(production ? "https://familysearch.org/platform/collections/tree" : "https://sandbox.familysearch.org/platform/collections/tree"));
  }

  protected ChildAndParentsRelationshipState newChildAndParentsRelationshipState(ClientRequest request, ClientResponse response, String accessToken) {
    return new ChildAndParentsRelationshipState(request, response, accessToken, this);
  }

  @Override
  protected FamilyTreeRelationshipsState newRelationshipsState(ClientRequest request, ClientResponse response, String accessToken) {
    return new FamilyTreeRelationshipsState(request, response, accessToken, this);
  }

  @Override
  protected FamilyTreeCollectionState newCollectionState(ClientRequest request, ClientResponse response, String accessToken) {
    return new FamilyTreeCollectionState(request, response, accessToken, this);
  }

  @Override
  protected FamilyTreePersonState newPersonState(ClientRequest request, ClientResponse response, String accessToken) {
    return new FamilyTreePersonState(request, response, accessToken, this);
  }
}
