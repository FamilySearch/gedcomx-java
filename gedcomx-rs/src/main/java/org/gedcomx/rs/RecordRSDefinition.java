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
 * The record resource defines a specific record.
 */
@ResourceDefinition (
  name = "Record",
  id = RecordRSDefinition.REL,
  description = "A record.",
  resourceElement = Gedcomx.class
)
public interface RecordRSDefinition {

  public static final String REL = Rel.RECORD;

  /**
   * Read the record.
   *
   * @return The record.
   */
  @GET
  @StatusCodes ( {
    @ResponseCode ( code = 200, condition = "Upon a successful read." ),
    @ResponseCode ( code = 204, condition = "Upon a successful query with no results." ),
    @ResponseCode ( code = 404, condition = "The specified person has been moved, deleted, or otherwise not found." )
  } )
  Response get();

  /**
   * Update a record.
   *
   * @param record The record to be updated.
   * @return The appropriate response.
   */
  @POST
  @StatusCodes ( {
    @ResponseCode ( code = 201, condition = "The update was successful." ),
    @ResponseCode ( code = 400, condition = "If the request was unable to be understood by the application." )
  } )
  Response post(Gedcomx record);

}
