package org.familysearch.api.client;

import com.sun.jersey.api.client.Client;
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

  protected UserState(ClientRequest request, Client client, String accessToken) {
    super(request, client, accessToken);
  }

  @Override
  protected UserState newApplicationState(ClientRequest request, Client client, String accessToken) {
    return new UserState(request, client, accessToken);
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
