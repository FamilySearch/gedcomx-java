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

import javax.ws.rs.POST;
import javax.ws.rs.core.Response;
import java.io.InputStream;

/**
 * <p>The Artifacts resource provides the interface for the set of artifacts in a collection.</p>
 *
 * @author Ryan Heaton
 */
@ResourceDefinition (
  name = "Artifacts",
  id = ArtifactsRSDefinition.REL,
  description = "The set of artifacts in a collection.",
  resourceElement = Gedcomx.class
)
public interface ArtifactsRSDefinition {

  public static final String REL = Rel.ARTIFACTS;

  /**
   * Create a artifact.
   *
   * @param artifact The artifact to be created.
   * @return The appropriate response.
   */
  @POST
  @StatusCodes({
    @ResponseCode ( code = 201, condition = "The creation of the artifact was successful. Expect a location header specifying the link to the created artifact.")
  })
  Response post(InputStream artifact);

}
