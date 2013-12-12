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
package org.gedcomx.rs.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import org.gedcomx.Gedcomx;
import org.gedcomx.conclusion.Relationship;
import org.gedcomx.links.SupportsLinks;
import org.gedcomx.rt.GedcomxConstants;

import javax.ws.rs.HttpMethod;
import java.net.URI;

/**
 * @author Ryan Heaton
 */
public class RelationshipState extends GedcomxApplicationState<Gedcomx> {

  public RelationshipState(URI discoveryUri) {
    this(discoveryUri, loadDefaultClient());
  }

  public RelationshipState(URI discoveryUri, Client client) {
    this(ClientRequest.create().accept(GedcomxConstants.GEDCOMX_JSON_MEDIA_TYPE).build(discoveryUri, HttpMethod.GET), client, null);
  }

  public RelationshipState(ClientRequest request, Client client, String accessToken) {
    super(request, client, accessToken);
  }

  @Override
  protected RelationshipState newApplicationState(ClientRequest request, Client client, String accessToken) {
    return new RelationshipState(request, client, accessToken);
  }

  @Override
  public RelationshipState ifSuccessfull() {
    return (RelationshipState) super.ifSuccessfull();
  }

  @Override
  protected Gedcomx loadEntity(ClientResponse response) {
    return response.getClientResponseStatus() == ClientResponse.Status.OK ? response.getEntity(Gedcomx.class) : null;
  }

  @Override
  protected SupportsLinks getScope() {
    return getRelationship();
  }

  public Relationship getRelationship() {
    return getEntity() == null ? null : getEntity().getRelationships() == null ? null : getEntity().getRelationships().isEmpty() ? null : getEntity().getRelationships().get(0);
  }

}
