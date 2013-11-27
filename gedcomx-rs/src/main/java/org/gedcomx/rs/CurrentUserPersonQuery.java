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
package org.gedcomx.rs;

import org.gedcomx.Gedcomx;
import org.gedcomx.rt.rs.ResourceDefinition;
import org.gedcomx.rt.rs.ResponseCode;
import org.gedcomx.rt.rs.StatusCodes;

import javax.ws.rs.GET;
import javax.ws.rs.core.Response;

/**
 * <p>The Current User Person query is used to query the application to retrieve the Person that represents the current user.</p>
 */
@ResourceDefinition (
  name = "Current User Person",
  id = CurrentUserPersonQuery.REL,
  description = "The query for the person for the current user.",
  resourceElement = Gedcomx.class
)
public interface CurrentUserPersonQuery {

  public static final String REL = Rel.CURRENT_USER_PERSON;

  /**
   * Read a person and their relationships.
   *
   * @return The person and relationships.
   */
  @GET
  @StatusCodes({
    @ResponseCode ( code = 303, condition = "The query was successful. The location of the person for the current user is provided in the Location header.")
  })
  Response get();
}
