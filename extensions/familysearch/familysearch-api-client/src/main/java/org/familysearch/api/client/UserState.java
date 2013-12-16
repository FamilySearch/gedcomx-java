package org.familysearch.api.client;

import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import org.familysearch.platform.FamilySearchPlatform;
import org.familysearch.platform.users.User;
import org.gedcomx.links.SupportsLinks;
import org.gedcomx.rs.client.GedcomxApplicationState;

/**
 * @author Ryan Heaton
 */
public class UserState extends GedcomxApplicationState<FamilySearchPlatform> {

  public UserState(ClientRequest request, ClientResponse response, String accessToken, FamilySearchStateFactory stateFactory) {
    super(request, response, accessToken, stateFactory);
  }

  @Override
  protected UserState clone(ClientRequest request, ClientResponse response) {
    return new UserState(request, response, this.accessToken, (FamilySearchStateFactory) this.stateFactory);
  }

  @Override
  protected FamilySearchPlatform loadEntity(ClientResponse response) {
    return response.getClientResponseStatus() == ClientResponse.Status.OK ? response.getEntity(FamilySearchPlatform.class) : null;
  }

  @Override
  protected SupportsLinks getScope() {
    return getUser();
  }

  public User getUser() {
    return getEntity() == null ? null : getEntity().getUsers().isEmpty() ? null : getEntity().getUsers().get(0);
  }

  @Override
  public UserState head() {
    return (UserState) super.head();
  }

  @Override
  public UserState get() {
    return (UserState) super.get();
  }

  @Override
  public UserState delete() {
    return (UserState) super.delete();
  }

  @Override
  public UserState put(FamilySearchPlatform entity) {
    return (UserState) super.put(entity);
  }
}
