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
 * <p>The ancestry query is used to query the application for an ascending pedigree rooted at a person for a specific number of generations.
 * The result of the query should provide a list of persons and relationships. Each person MAY supply an ancestor number in the display
 * properties for the person. The ancestor number is interpreted as an Ahnentafel number which can be used to determine the position of
 * each person in the pedigree.</p>
 *
 * <p>The ancestry query MUST support the following parameters:</p>
 *
 * <ul>
 *   <li>The "person" parameter is the id for the person at the root of the pedigree. Unless the "spouse" parameter is provided,
 *       The ancestry number of the person identified by the "person" parameter will be "1". The "person" parameter is REQUIRED.</li>
 *   <li>The presence of the "spouse" parameter indicates that the pedigree of both the person AND the person's spouse is requested.
 *       If the value of the "spouse" parameter is empty, the application may select a spouse for the person. If the value of the
 *       "spouse" parameter is not empty, it is interpreted as the id of the spouse selected for the pedigree. When the "spouse"
 *       parameter is supplied, the ancestry number of the person and the spouse will be either "2" or "3", depending on the role
 *       of the person.</li>
 *   <li>The "generations" parameter indicates the number of generations of the person's ancestry is being queried.</li>
 * </ul>
 */
@ResourceDefinition (
  name = "Ancestry",
  id = AncestryQuery.REL,
  description = "The query for the ancestry of a person.",
  resourceElement = Gedcomx.class
)
public interface AncestryQuery {

  public static final String REL = "ancestry";

  /**
   * Query for a person and the ancestors of a person for a number of generations.
   *
   * @return A person and the ancestors of a person for a number of generations.
   */
  @GET
  @StatusCodes({
    @ResponseCode ( code = 200, condition = "The query was successful.")
  })
  Response get();

  /**
   * The id of the person whose ancestry is being queried.
   *
   * @param personId The id of the person whose ancestry is being queried.
   */
  @QueryParam("person")
  void setPersonId(String personId);

  /**
   * If provided, the ancestry of the spouse will be included. An empty value indicates the system will choose a spouse.
   *
   * @param spouseId If provided, the ancestry of the spouse will be included. An empty value indicates the system will choose a spouse.
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
