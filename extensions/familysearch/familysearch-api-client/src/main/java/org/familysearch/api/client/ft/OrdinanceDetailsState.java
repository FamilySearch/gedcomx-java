package org.familysearch.api.client.ft;

import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import org.gedcomx.Gedcomx;
import org.gedcomx.links.SupportsLinks;
import org.gedcomx.rs.client.GedcomxApplicationState;
import org.gedcomx.rs.client.StateFactory;

public class OrdinanceDetailsState extends GedcomxApplicationState<Gedcomx> {

  protected OrdinanceDetailsState(ClientRequest request, ClientResponse response, String accessToken, StateFactory stateFactory) {
    super(request, response, accessToken, stateFactory);
  }

  protected OrdinanceDetailsState clone(ClientRequest request, ClientResponse response) {
    return new OrdinanceDetailsState(request, response, this.accessToken, this.stateFactory);
  }

  protected Gedcomx loadEntity(ClientResponse response) {
    return response.getEntity(Gedcomx.class);
  }

  protected SupportsLinks getMainDataElement() {
    return getEntity();
  }

}
