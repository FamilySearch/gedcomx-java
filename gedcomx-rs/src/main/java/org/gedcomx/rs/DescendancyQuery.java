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
 * <p>The descendancy query is used to query the application for a descending pedigree rooted at a person and for a specific number of generations. The result
 * of the query provides a list of persons and relationships. Each person may supply a <tt>descendancy number</tt> in the display properties for the person. The
 * <tt>descendancy number</tt> is interpreted as a d'Aboville number which can be used to determine the position of each person in the descendancy. The
 * descendancy query may support the inclusion of the spouses of each person in the descendancy graph. Spouses returned in the query must have an <tt>-S</tt>
 * appended to their <tt>descendancy number</tt> to indicate the person is the spouse of the associated person.</p>
 *
 * <p>The descendancy query must support the following parameters:</p>
 *
 * <ul>
 *   <li>The <tt>person</tt> parameter is the ID for the person at the root of the descendancy. The <tt>person</tt> parameter is required. The <tt>descendancy
 *       number</tt> of the person identified by the person parameter is 1, unless the <tt>spouse</tt> parameter is provided. </li>
 *   <li>The presence of the <tt>spouse</tt> parameter indicates that the descendancy is narrowed to a specific spouse. When the <tt>spouse</tt> parameter is
 *       supplied, the <tt>descendancy number</tt> of both the person and the spouse will be 1.
 *       <ul>
 *         <li>If the value of the <tt>spouse</tt> parameter is empty, the application may select a spouse for the person. </li>
 *         <li>If the value of the <tt>spouse</tt> parameter is not empty, it is interpreted as the ID of the spouse selected for the descendancy. </li>
 *       </ul>
 *   </li>
 *   <li>The <tt>generations</tt> parameter indicates the number of generations of the person's descendancy that is being queried.</li>
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
    @ResponseCode ( code = 200, condition = "The query was successful."),
    @ResponseCode ( code = 301, condition = "If the requested person has been merged to another person."),
    @ResponseCode ( code = 410, condition = "If the requested person has been deleted.")
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
