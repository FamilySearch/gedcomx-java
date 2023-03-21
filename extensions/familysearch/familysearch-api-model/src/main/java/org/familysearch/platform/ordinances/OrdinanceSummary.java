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
package org.familysearch.platform.ordinances;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonInclude;

import org.gedcomx.rt.json.JsonElementWrapper;

/**
 * Summary information regarding Ordinances.
 */
@XmlRootElement(name = "ordinanceSummary")
@JsonElementWrapper(name = "ordinanceSummaries")
@XmlType(name = "OrdinanceSummary", propOrder = {"notSharedReservationCount", "notSharedReservationLimit", "sharedReservationCount"})
@JsonInclude (JsonInclude.Include.NON_NULL)
public class OrdinanceSummary {

  private Integer notSharedReservationCount;
  private Integer notSharedReservationLimit;
  private Integer sharedReservationCount;

  /**
   * Get the current number of reservations which have not been shared that a user has on their reservation list.
   * @return The current number of reservations which have not been shared that a user has on their reservation list.
   */
  public Integer getNotSharedReservationCount() {
    return notSharedReservationCount;
  }

  /**
   * Set the current number of reservations which have not been shared that a user has on their reservation list.
   * @param notSharedReservationCount The current number of reservations which have not been shared that a user has on their reservation list.
   */
  public void setNotSharedReservationCount(Integer notSharedReservationCount) {
    this.notSharedReservationCount = notSharedReservationCount;
  }

  /**
   * Get the maximum number of reservations which have not been shared that a user is allowed to have on their reservation list.
   * @return The maximum number of reservations which have not been shared that is allowed.
   */
  public Integer getNotSharedReservationLimit() {
    return notSharedReservationLimit;
  }

  /**
   * Set the maximum number of reservations which have not been shared that a user is allowed to have on their reservation list.
   * @param notSharedReservationLimit The maximum number of reservations which have not been shared that is allowed.
   */
  public void setNotSharedReservationLimit(Integer notSharedReservationLimit) {
    this.notSharedReservationLimit = notSharedReservationLimit;
  }

  /**
   * Get the current number of shared reservations for a user.
   * @return The current number of shared reservations for a user.
   */
  public Integer getSharedReservationCount() {
    return sharedReservationCount;
  }

  /**
   * Set the current number of shared reservations for a user.
   * @param sharedReservationCount The current number of shared reservations for a user.
   */
  public void setSharedReservationCount(Integer sharedReservationCount) {
    this.sharedReservationCount = sharedReservationCount;
  }

}
