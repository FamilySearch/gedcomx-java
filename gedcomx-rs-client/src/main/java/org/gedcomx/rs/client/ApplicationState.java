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
