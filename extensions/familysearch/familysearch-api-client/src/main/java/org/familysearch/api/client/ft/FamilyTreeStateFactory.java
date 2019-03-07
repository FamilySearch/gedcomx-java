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
import org.familysearch.api.client.*;
import org.gedcomx.rs.client.SourceDescriptionsState;

import java.net.URI;

/**
 * @author Ryan Heaton
 */
public class FamilyTreeStateFactory extends FamilySearchStateFactory {

  public FamilySearchFamilyTree newFamilyTreeState() {
    return newFamilyTreeState(true);
  }

  public FamilySearchFamilyTree newFamilyTreeState(boolean production) {
    return (FamilySearchFamilyTree) newCollectionState(URI.create(production ? FamilySearchFamilyTree.URI : FamilySearchFamilyTree.SANDBOX_URI));
  }

  public FamilySearchFamilyTree newFamilyTreeState(URI discoveryUri) {
    return (FamilySearchFamilyTree) super.newCollectionState(discoveryUri);
  }

  protected ChildAndParentsRelationshipState newChildAndParentsRelationshipState(ClientRequest request, ClientResponse response, String accessToken) {
    return new ChildAndParentsRelationshipState(request, response, accessToken, this);
  }

  @Override
  protected FamilyTreeRelationshipsState newRelationshipsState(ClientRequest request, ClientResponse response, String accessToken) {
    return new FamilyTreeRelationshipsState(request, response, accessToken, this);
  }

  @Override
  protected FamilySearchFamilyTree newCollectionState(ClientRequest request, ClientResponse response, String accessToken) {
    return new FamilySearchFamilyTree(request, response, accessToken, this);
  }

  @Override
  protected FamilyTreePersonState newPersonState(ClientRequest request, ClientResponse response, String accessToken) {
    return new FamilyTreePersonState(request, response, accessToken, this);
  }

  protected FamilyTreePersonState newPersonWithRelationshipsState(ClientRequest request, ClientResponse response, String accessToken) {
    return new FamilyTreePersonState(request, response, accessToken, Rel.PERSON_WITH_RELATIONSHIPS, this);
  }

  @Override
  protected FamilyTreePersonsState newPersonsState(ClientRequest request, ClientResponse response, String accessToken) {
    return new FamilyTreePersonsState(request, response, accessToken, this);
  }

  @Override
  protected FamilyTreeRelationshipState newRelationshipState(ClientRequest request, ClientResponse response, String accessToken) {
    return new FamilyTreeRelationshipState(request, response, accessToken, this);
  }

  @Override
  protected FamilyTreePersonParentsState newPersonParentsState(ClientRequest request, ClientResponse response, String accessToken) {
    return new FamilyTreePersonParentsState(request, response, accessToken, this);
  }

  @Override
  protected FamilyTreePersonChildrenState newPersonChildrenState(ClientRequest request, ClientResponse response, String accessToken) {
    return new FamilyTreePersonChildrenState(request, response, accessToken, this);
  }

  @Override
  protected PersonNonMatchesState newPersonNonMatchesState(ClientRequest request, ClientResponse response, String accessToken) {
    return super.newPersonNonMatchesState(request, response, accessToken);
  }

  @Override
  protected ChangeHistoryState newChangeHistoryState(ClientRequest request, ClientResponse response, String accessToken) {
    return super.newChangeHistoryState(request, response, accessToken);
  }

  @Override
  protected PersonMatchResultsState newPersonMatchResultsState(ClientRequest request, ClientResponse response, String accessToken) {
    return new FamilyTreePersonMatchResultsState(request, response, accessToken, this);
  }

  protected FamilyTreePersonMergeState newPersonMergeState(ClientRequest request, ClientResponse response, String accessToken) {
    return new FamilyTreePersonMergeState(request, response, accessToken, this);
  }

  @Override
  protected FamilySearchSourceDescriptionState newSourceDescriptionState(ClientRequest request, ClientResponse response, String accessToken) {
    return super.newSourceDescriptionState(request, response, accessToken);
  }

  @Override
  protected SourceDescriptionsState newSourceDescriptionsState(ClientRequest request, ClientResponse response, String accessToken) {
    return super.newSourceDescriptionsState(request, response, accessToken);
  }

  @Override
  protected OrdinanceReservationsState newOrdinanceReservationsState(ClientRequest request, ClientResponse response, String accessToken) {
    return super.newOrdinanceReservationsState(request, response, accessToken);
  }

  @Override
  protected PersonOrdinancesState newPersonOrdinancesState(ClientRequest request, ClientResponse response, String accessToken) {
    return super.newPersonOrdinancesState(request, response, accessToken);
  }

  protected PersonMatchResolutionsState newPersonMatchResolutionsState(ClientRequest request, ClientResponse response, String accessToken) {
    return new PersonMatchResolutionsState(request, response, accessToken, this);
  }

  protected FamilyTreePersonFamiliesState newPersonFamiliesState(ClientRequest request, ClientResponse response, String accessToken) {
    return new FamilyTreePersonFamiliesState(request, response, accessToken, this);
  }

}
