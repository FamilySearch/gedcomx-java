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

import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import org.gedcomx.Gedcomx;
import org.gedcomx.agent.Agent;
import org.gedcomx.links.SupportsLinks;
import org.gedcomx.rt.Rel;

/**
 * @author Ryan Heaton
 */
public class AgentState extends GedcomxApplicationState<Gedcomx> {

  protected AgentState(ClientRequest request, ClientResponse response, String accessToken, StateFactory stateFactory) {
    super(request, response, accessToken, stateFactory);
  }

  @Override
  protected AgentState clone(ClientRequest request, ClientResponse response) {
    return new AgentState(request, response, this.accessToken, this.stateFactory);
  }

  @Override
  protected Gedcomx loadEntity(ClientResponse response) {
    return response.getEntity(Gedcomx.class);
  }

  @Override
  public AgentState ifSuccessful() {
    return (AgentState) super.ifSuccessful();
  }

  @Override
  public String getSelfRel() {
    return Rel.AGENT;
  }

  @Override
  public AgentState head(StateTransitionOption... options) {
    return (AgentState) super.head(options);
  }

  @Override
  public AgentState options(StateTransitionOption... options) {
    return (AgentState) super.options(options);
  }

  @Override
  public AgentState get(StateTransitionOption... options) {
    return (AgentState) super.get(options);
  }

  @Override
  public AgentState delete(StateTransitionOption... options) {
    return (AgentState) super.delete(options);
  }

  @Override
  public AgentState put(Gedcomx e, StateTransitionOption... options) {
    return (AgentState) super.put(e, options);
  }

  @Override
  public AgentState post(Gedcomx entity, StateTransitionOption... options) {
    return (AgentState) super.post(entity, options);
  }

  @Override
  protected SupportsLinks getMainDataElement() {
    return getAgent();
  }

  public Agent getAgent() {
    return getEntity() == null ? null : getEntity().getAgents() == null ? null : getEntity().getAgents().isEmpty() ? null : getEntity().getAgents().get(0);
  }

}
