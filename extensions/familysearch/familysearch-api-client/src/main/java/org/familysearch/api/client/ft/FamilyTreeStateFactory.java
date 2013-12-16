package org.familysearch.api.client.ft;

import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import org.familysearch.api.client.FamilySearchStateFactory;

/**
 * @author Ryan Heaton
 */
public class FamilyTreeStateFactory extends FamilySearchStateFactory {

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
