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
import javax.ws.rs.core.Response;


/**
 * The evidence reference resource defines the interface for a evidence reference.
 */
@ResourceDefinition (
  name = "Evidence Reference",
  id = EvidenceReferenceRSDefinition.REL,
  description = "A evidence reference.",
  resourceElement = Gedcomx.class
)
public interface EvidenceReferenceRSDefinition {

  public static final String REL = Rel.EVIDENCE_REFERENCE;

  /**
   * Delete a evidence reference.
   *
   * @return The appropriate response.
   */
  @DELETE
  @StatusCodes ( {
    @ResponseCode ( code = 204, condition = "The delete was successful." ),
    @ResponseCode ( code = 404, condition = "If the requested evidence reference is not found." )
  } )
  Response delete();

}
