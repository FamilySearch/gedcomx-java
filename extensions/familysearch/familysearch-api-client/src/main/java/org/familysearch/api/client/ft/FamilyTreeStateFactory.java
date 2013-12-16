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
