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
import org.gedcomx.agent.Agent;
import org.gedcomx.links.SupportsLinks;
import org.gedcomx.rt.GedcomxConstants;

import javax.ws.rs.HttpMethod;
import java.net.URI;

/**
 * @author Ryan Heaton
 */
public class AgentState<E> extends GedcomxApplicationState<Gedcomx> {

  public AgentState(URI discoveryUri) {
    this(discoveryUri, loadDefaultClient());
  }

  public AgentState(URI discoveryUri, Client client) {
    this(ClientRequest.create().accept(GedcomxConstants.GEDCOMX_JSON_MEDIA_TYPE).build(discoveryUri, HttpMethod.GET), client, null);
  }

  public AgentState(ClientRequest request, Client client, String accessToken) {
    super(request, client, accessToken);
  }

  @Override
  protected AgentState newApplicationState(ClientRequest request, Client client, String accessToken) {
    return new AgentState(request, client, accessToken);
  }

  @Override
  protected Gedcomx loadEntity(ClientResponse response) {
    return response.getClientResponseStatus() == ClientResponse.Status.OK ? response.getEntity(Gedcomx.class) : null;
  }

  @Override
  public AgentState ifSuccessful() {
    return (AgentState) super.ifSuccessful();
  }

  @Override
  public AgentState head() {
    return (AgentState) super.head();
  }

  @Override
  public AgentState get() {
    return (AgentState) super.get();
  }

  @Override
  public AgentState delete() {
    return (AgentState) super.delete();
  }

  @Override
  public AgentState put(Gedcomx e) {
    return (AgentState) super.put(e);
  }

  @Override
  protected SupportsLinks getScope() {
    return getAgent();
  }

  public Agent getAgent() {
    return getEntity() == null ? null : getEntity().getAgents() == null ? null : getEntity().getAgents().isEmpty() ? null : getEntity().getAgents().get(0);
  }

}
