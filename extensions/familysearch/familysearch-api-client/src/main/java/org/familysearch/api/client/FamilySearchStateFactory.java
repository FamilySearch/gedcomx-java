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
