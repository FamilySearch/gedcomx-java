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
public class GedcomxDateSimple extends GedcomxDate {

  private Integer year = null;
  private Integer month = null;
  private Integer day = null;
  private Integer hours = null;
  private Integer minutes = null;
  private Integer seconds = null;

  public GedcomxDateSimple(String str) {
    parseDate(str);
  }

  private void parseDate(String str) {
    int end = str.length();
    int offset = 0;
    String num;

    // There is a minimum length of 5 characters
    if(end < 5) {
      throw new GedcomxDateException("Invalid Date: Must have at least [+-]YYYY");
    }

    // Yar
    if(str.charAt(offset) != '+' && str.charAt(offset) != '-') {
      throw new GedcomxDateException("Invalid Date: Must begin with + or -");
    }

    offset++;
    num = str.substring(0,1);
    for(int i=0;i<4;i++) {
      if(!Character.isDigit(str.charAt(offset))) {
        throw new GedcomxDateException("Invalid Date: Malformed Year");
      }
      num += str.charAt(offset++);
    }

    year = Integer.valueOf(num);

    if(offset == end) {
      return;
    }

    // If there is time
    if(str.charAt(offset) == 'T') {
      parseTime(str.substring(offset+1));
      return;
    }

    // Month
    if(str.charAt(offset) != '-') {
      throw new GedcomxDateException("Invalid Date: Invalid Year-Month Separator");
    }

    if(end-offset < 3) {
      throw new GedcomxDateException("Invalid Date: Month must be 2 digits");
    }

    offset++;
    num = "";
    for(int i=0;i<2;i++) {
      if(!Character.isDigit(str.charAt(offset))) {
        throw new GedcomxDateException("Invalid Date: Malformed Month");
      }
      num += str.charAt(offset++);
    }

    month = Integer.valueOf(num);

    if(month < 1 || month > 12) {
      throw new GedcomxDateException("Invalid Date: Month must be between 1 and 12");
    }

    if(offset == end) {
      return;
    }


    // Day
    if(str.charAt(offset) != '-') {
      throw new GedcomxDateException("Invalid Date: Invalid Month-Day Separator");
    }

    if(end-offset < 3) {
      throw new GedcomxDateException("Invalid Date: Day must be 2 digits");
    }

    offset++;
    num = "";
    for(int i=0;i<2;i++) {
      if(!Character.isDigit(str.charAt(offset))) {
        throw new GedcomxDateException("Invalid Date: Malformed Day");
      }
      num += str.charAt(offset++);
    }

    day = Integer.valueOf(num);

    if(day < 1) {
      throw new GedcomxDateException("Invalid Date: Day 0 does not exist");
    }

    int daysInMonth = daysInMonth(year, month);
    if(day > daysInMonth) {
      throw new GedcomxDateException("Invalid Date: There are only "+daysInMonth+" days in Month "+month+" year "+year);
    }

    if(offset == end) {
      return;
    }

    if(str.charAt(offset) == 'T') {
      parseTime(str.substring(offset+1));
    } else {
      throw new GedcomxDateException("Invalid Date: +YYYY-MM-DD must have T before time");
    }

  }

  // TODO more to UTIL and full test
  private int daysInMonth(Integer year, Integer month) {
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

  private void parseTime(String str) {
    System.out.println("STR: "+str);
  }

  @Override
  public GedcomxDateType getType() {
    return GedcomxDateType.SIMPLE;
  }

  @Override
  public boolean isApproximate() {
    return false;
  }

  @Override
  public String toFormalString() {
    return null;
  }

  public Integer getYear() {
    return year;
  }

  public Integer getMonth() {
    return month;
  }

  public Integer getDay() {
    return day;
  }

  public Integer getHours() {
    return hours;
  }
}
