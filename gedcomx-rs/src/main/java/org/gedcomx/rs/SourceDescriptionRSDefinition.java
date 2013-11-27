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

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.POST;
import javax.ws.rs.core.Response;


/**
 * The source description resource defines the interface for a source description.
 */
@ResourceDefinition (
  name = "Source Description",
  id = "source-description",
  description = "A source description",
  resourceElement = Gedcomx.class
)
public interface SourceDescriptionRSDefinition {

  public static final String REL = Rel.DESCRIPTION;

  /**
   * Read a source description's header attributes.
   *
   * @return The header attributes for the source description.
   */
  @HEAD
  @StatusCodes ( {
    @ResponseCode ( code = 200, condition = "Upon a successful read." ),
    @ResponseCode ( code = 404, condition = "If the requested source description is not found." )
  } )
  Response head();

  /**
   * Read a source description.
   *
   * @return The source description.
   */
  @GET
  @StatusCodes ( {
    @ResponseCode ( code = 200, condition = "Upon a successful read." ),
    @ResponseCode ( code = 404, condition = "If the requested source description is not found." )
  } )
  Response get();

  /**
   * Update a source description.
   *
   * @param sourceDescription The source description to be used for the update.
   * @return The appropriate response.
   */
  @POST
  @StatusCodes ( {
    @ResponseCode ( code = 204, condition = "The update was successful." ),
    @ResponseCode ( code = 404, condition = "If the requested source description is not found." )
  } )
  Response post(Gedcomx sourceDescription);

  /**
   * Delete a source description.
   *
   * @return The appropriate response.
   */
  @DELETE
  @StatusCodes ( {
    @ResponseCode ( code = 204, condition = "The delete was successful." ),
    @ResponseCode ( code = 404, condition = "If the requested source description is not found." )
  } )
  Response delete();

}
