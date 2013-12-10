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
import javax.ws.rs.POST;
import javax.ws.rs.core.Response;


/**
 * The source descriptions resource defines the interface for the set of source descriptions in the application.
 */
@ResourceDefinition (
  name = "Source Descriptions",
  id = "source-descriptions",
  description = "The set of source descriptions in the application.",
  resourceElement = Gedcomx.class
)
public interface SourceDescriptionsRSDefinition {

  public static final String REL = Rel.SOURCE_DESCRIPTIONS;

  /**
   * Read the source descriptions.
   *
   * @return The list of source descriptions.
   */
  @GET
  @StatusCodes ( {
    @ResponseCode ( code = 200, condition = "Upon a successful read." ),
    @ResponseCode ( code = 204, condition = "Upon a successful read with no results." )
  } )
  Response get();

  /**
   * Add a source description.
   *
   * @param sourceDescription The source description to be added.
   * @return The appropriate response.
   */
  @POST
  @StatusCodes ( {
    @ResponseCode ( code = 201, condition = "The creation of the source description was successful. Expect a location header specifying the link to the created source description." ),
    @ResponseCode ( code = 400, condition = "If the request was unable to be understood by the application." )
  } )
  Response post(Gedcomx sourceDescription);

}
