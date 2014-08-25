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
public class GedcomxDateUtil {

  public static GedcomxDate parse(String date) {

    return null;
  }

  public static GedcomxDateDuration getDuration(GedcomxDateSimple start, GedcomxDateSimple end) {
    return null;
  }

  public static GedcomxDateSimple addDuration(GedcomxDateSimple start, GedcomxDateDuration duration) {
    return null;
  }

  public static GedcomxDateDuration multiplyDuration(GedcomxDateDuration duration) {
    return null;
  }

  public static int daysInMonth(Integer year, Integer month) {
    switch(month) {
      case 1:
      case 3:
      case 5:
      case 7:
      case 8:
      case 10:
      case 12:
        return 31;
      case 4:
      case 6:
      case 9:
      case 11:
        return 30;
      case 2:
        boolean leapYear;
        if(year % 4 != 0) {
          leapYear = false;
        } else if(year % 100 != 0) {
          leapYear = true;
        } else if(year % 400 != 0) {
          leapYear = false;
        } else {
          leapYear = true;
        }
        if(leapYear) {
          return 29;
        } else {
          return 28;
        }
      default:
        throw new GedcomxDateException("UnknownMonth");
    }
  }
}
