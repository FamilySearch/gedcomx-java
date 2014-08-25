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
 * @author John Clark.
 */
public class GedcomxDateApproximate extends GedcomxDate {

  private GedcomxDateSimple simpleDate;

  public GedcomxDateApproximate(String str) {

    if(str.length() < 1 || str.charAt(0) != 'A') {
      throw new GedcomxDateException("Invalid Approximate Date: Must start with A");
    }

    simpleDate = new GedcomxDateSimple(str.substring(1));

  }

  public GedcomxDateSimple getSimpleDate() {
    return simpleDate;
  }

  @Override
  public GedcomxDateType getType() {
    return GedcomxDateType.APPROXIMATE;
  }

  @Override
  public boolean isApproximate() {
    return true;
  }

  @Override
  public String toFormalString() {
    return "A"+simpleDate.toFormalString();
  }

  public Integer getYear() {
    return simpleDate.getYear();
  }

  public Integer getMonth() {
    return simpleDate.getMonth();
  }

  public Integer getDay() {
    return simpleDate.getDay();
  }

  public Integer getHours() {
    return simpleDate.getHours();
  }

  public Integer getMinutes() {
    return simpleDate.getMinutes();
  }

  public Integer getSeconds() {
    return simpleDate.getSeconds();
  }

  public Integer getTzHours() {
    return simpleDate.getTzHours();
  }

  public Integer getTzMinutes() {
    return simpleDate.getTzMinutes();
  }
}
