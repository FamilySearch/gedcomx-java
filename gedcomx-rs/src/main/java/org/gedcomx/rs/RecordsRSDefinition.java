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
import org.gedcomx.atom.Entry;
import org.gedcomx.atom.Feed;
import org.gedcomx.rt.rs.ResourceDefinition;
import org.gedcomx.rt.rs.ResponseCode;
import org.gedcomx.rt.rs.StateTransition;
import org.gedcomx.rt.rs.StatusCodes;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.core.Response;


/**
 * The records resource defines a set of records. The records resource is often used as the entry point for a GEDCOM X application,
 * which provides the list of records that are available.
 */
@ResourceDefinition (
  name = "Records",
  id = RecordsRSDefinition.REL,
  description = "A set of records.",
  resourceElement = Feed.class,
  transitions = {
    @StateTransition( rel = RecordRSDefinition.REL, description = "A record.", scope = Entry.class, conditional = true, targetResource = RecordRSDefinition.class ),
    @StateTransition( rel = RecordRSDefinition.REL, description = "A record.", scope = Gedcomx.class, conditional = true, targetResource = RecordRSDefinition.class )
  }
)
public interface RecordsRSDefinition {

  public static final String REL = Rel.RECORDS;

  /**
   * Read the records.
   *
   * @return The list of records.
   */
  @GET
  @StatusCodes ( {
    @ResponseCode ( code = 200, condition = "Upon a successful read." ),
    @ResponseCode ( code = 204, condition = "Upon a successful read with no results." )
  } )
  Response get();

  /**
   * Create a record.
   *
   * @param record The record to be created.
   * @return The appropriate response.
   */
  @POST
  @StatusCodes ( {
    @ResponseCode ( code = 201, condition = "The creation of the record was successful. Expect a location header specifying the link to the created record." ),
    @ResponseCode ( code = 400, condition = "If the request was unable to be understood by the application." ),
    @ResponseCode ( code = 404, condition = "The specified person has been moved, deleted, or otherwise not found." )
  } )
  Response post(Gedcomx record);

}
