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
package org.gedcomx.date;

/**
 * An Approximate Date
 * @author John Clark.
 */
public class GedcomxDateApproximate extends GedcomxDate {

  private GedcomxDateSimple simpleDate;

  /**
   * Instantiate a new approximate date
   * @param date The formal date string
   */
  public GedcomxDateApproximate(String date) {

    if(date == null || date.length() < 1 || date.charAt(0) != 'A') {
      throw new GedcomxDateException("Invalid Approximate Date: Must start with A");
    }

    simpleDate = new GedcomxDateSimple(date.substring(1));

  }

  /**
   * Return the underlying simple date
   * @return The Simple Date
   */
  public GedcomxDateSimple getSimpleDate() {
    return simpleDate;
  }

  /**
   * The type of this date
   * @return The Type
   */
  @Override
  public GedcomxDateType getType() {
    return GedcomxDateType.APPROXIMATE;
  }

  /**
   * Whether or not this date is approximate
   * @return True
   */
  @Override
  public boolean isApproximate() {
    return true;
  }

  /**
   * Returns the formal representation of this date
   * @return The formal String
   */
  @Override
  public String toFormalString() {
    return "A"+simpleDate.toFormalString();
  }

  /**
   * Get the year
   * @return The Year
   */
  public Integer getYear() {
    return simpleDate.getYear();
  }

  /**
   * Get the month
   * @return The Month
   */
  public Integer getMonth() {
    return simpleDate.getMonth();
  }

  /**
   * Get the day
   * @return The Day
   */
  public Integer getDay() {
    return simpleDate.getDay();
  }

  /**
   * Get the hours
   * @return The Hours
   */
  public Integer getHours() {
    return simpleDate.getHours();
  }

  /**
   * Get the minutes
   * @return The Minutes
   */
  public Integer getMinutes() {
    return simpleDate.getMinutes();
  }

  /**
   * Get the seconds
   * @return The Seconds
   */
  public Integer getSeconds() {
    return simpleDate.getSeconds();
  }

  /**
   * Get the timezone hours
   * @return The Timezone Hours
   */
  public Integer getTzHours() {
    return simpleDate.getTzHours();
  }

  /**
   * Get the timezone minutes
   * @return The Timezone Minutes
   */
  public Integer getTzMinutes() {
    return simpleDate.getTzMinutes();
  }
}
