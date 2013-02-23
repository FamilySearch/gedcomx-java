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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 * <p>The descendancy query is used to query the application for an descending pedigree rooted at a person for a specific number of generations.
 * The result of the query should provide a list of persons and relationships. Each person MAY supply a descendancy number in the display
 * properties for the person. The descendancy number is interpreted as an d'Aboville number which can be used to determine the position of
 * each person in the descendancy. The descendancy query MAY support the inclusion of the spouses of each person in the descendancy graph. Spouses
 * returned in the query MUST have a "-S" appended to their descendancy number to indicate the person is the spouse of the associated person.</p>
 *
 * <p>The descendancy query MUST support the following parameters:</p>
 *
 * <ul>
 *   <li>The "person" parameter is the id for the person at the root of the descendancy. Unless the "spouse" parameter is provided,
 *       The descendancy number of the person identified by the "person" parameter will be "1". The "person" parameter is REQUIRED.</li>
 *   <li>The presence of the "spouse" parameter indicates that the descendancy will be narrowed to a specific spouse.
 *       If the value of the "spouse" parameter is empty, the application may select a spouse for the person. If the value of the
 *       "spouse" parameter is not empty, it is interpreted as the id of the spouse selected for the descendancy. When the "spouse"
 *       parameter is supplied, the descendancy number of both the person and the spouse will be "1".</li>
 *   <li>The "generations" parameter indicates the number of generations of the person's descendancy is being queried.</li>
 * </ul>
 */
@ResourceDefinition (
  name = "Descendancy",
  id = DescendancyQuery.REL,
  description = "The query for the descendancy of a person.",
  resourceElement = Gedcomx.class
)
public interface DescendancyQuery {

  public static final String REL = "descendancy";

  /**
   * Query for a person and the descendants of the person for a number of generations.
   *
   * @return A person and the ancestors of a person for a number of generations.
   */
  @GET
  @StatusCodes({
    @ResponseCode ( code = 200, condition = "The query was successful.")
  })
  Response get();

  /**
   * The id of the person whose descendancy is being queried.
   *
   * @param personId The id of the person whose descendancy is being queried.
   */
  @QueryParam("person")
  void setPersonId(String personId);

  /**
   *  If provided, the descendancy will be narrowed to a specific spouse. An empty value indicates the system will choose a spouse.
   *
   * @param spouseId  If provided, the descendancy will be narrowed to a specific spouse. An empty value indicates the system will choose a spouse.
   */
  @QueryParam("spouse")
  void setSpouseId(String spouseId);

  /**
   * The number of generations being queried.
   *
   * @param generations The number of generations being queried.
   */
  @QueryParam("generations")
  void setGenerations(String generations);
}
