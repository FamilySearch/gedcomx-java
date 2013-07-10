/**
 * Copyright 2011-2012 Intellectual Reserve, Inc.
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
package org.gedcomx.rs;

import org.gedcomx.Gedcomx;
import org.gedcomx.rt.rs.ResourceDefinition;
import org.gedcomx.rt.rs.ResponseCode;
import org.gedcomx.rt.rs.StatusCodes;

import javax.ws.rs.GET;
import javax.ws.rs.core.Response;

/**
 * <p>The Agent resource defines the interface for an agent, such as a user, a system, or an organization.</p>
 */
@ResourceDefinition (
  name = "Agent",
  id = AgentRSDefinition.REL,
  description = "An agent, such as a user or other contributor.",
  resourceElement = Gedcomx.class
)
public interface AgentRSDefinition {

  public static final String REL = "agent";

  /**
   * Read an agent.
   *
   * @return The agent.
   */
  @GET
  @StatusCodes ( {
    @ResponseCode ( code = 200, condition = "Upon a successful read." ),
    @ResponseCode ( code = 404, condition = "If the requested agent is not found." )
   } )
  Response get();
}
