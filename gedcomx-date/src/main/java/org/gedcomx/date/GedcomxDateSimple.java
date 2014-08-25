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

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

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
  private Integer tzHours = null;
  private Integer tzMinutes = null;

  public GedcomxDateSimple(String str) {
    parseDate(str);
  }

  private void parseDate(String str) {

    // There is a minimum length of 5 characters
    if(str.length() < 5) {
      throw new GedcomxDateException("Invalid Date: Must have at least [+-]YYYY");
    }

    int end = str.length();
    int offset = 0;
    String num;

    // Must start with a + or -
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

    // If there is time
    if(str.charAt(offset) == 'T') {
      parseTime(str.substring(offset+1));
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

    int daysInMonth = GedcomxDateUtil.daysInMonth(month, year);
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

  private void parseTime(String str) {

    int offset = 0;
    int end = str.length();
    String num;
    boolean flag24 = false;

    // Always initialize the Timezone to the local offset.
    // It may be overridden if set
    TimeZone tz = TimeZone.getDefault();
    Calendar cal = GregorianCalendar.getInstance(tz);
    int offsetInMillis = tz.getOffset(cal.getTimeInMillis());
    tzHours = offsetInMillis / 3600000;
    tzMinutes = (offsetInMillis / 60000) % 60;

    // You must at least have hours
    if(end < 2) {
      throw new GedcomxDateException("Invalid Date: Hours must be 2 digits");
    }

    num = "";
    for(int i=0;i<2;i++) {
      if(!Character.isDigit(str.charAt(offset))) {
        throw new GedcomxDateException("Invalid Date: Malformed Hours");
      }
      num += str.charAt(offset++);
    }

    hours = Integer.valueOf(num);

    if(hours > 24) {
      throw new GedcomxDateException("Invalid Date: Hours must be between 0 and 24");
    }

    if(hours == 24) {
      flag24 = true;
    }

    if(offset == end) {
      return;
    }

    // If there is a timezone offset
    if(str.charAt(offset) == '+' || str.charAt(offset) == '-' || str.charAt(offset) == 'Z') {
      parseTimezone(str.substring(offset)); // Don't remove the character when calling
      return;
    }

    if(str.charAt(offset) != ':') {
      throw new GedcomxDateException("Invalid Date: Invalid Hour-Minute Separator");
    }

    if(end-offset < 3) {
      throw new GedcomxDateException("Invalid Date: Minutes must be 2 digits");
    }

    offset++;
    num = "";
    for(int i=0;i<2;i++) {
      if(!Character.isDigit(str.charAt(offset))) {
        throw new GedcomxDateException("Invalid Date: Malformed Minutes");
      }
      num += str.charAt(offset++);
    }

    minutes = Integer.valueOf(num);

    if(minutes > 59) {
      throw new GedcomxDateException("Invalid Date: Minutes must be between 0 and 59");
    }

    if(flag24 && minutes != 0) {
      throw new GedcomxDateException("Invalid Date: Hours of 24 requires 00 Minutes");
    }

    if(offset == end) {
      return;
    }

    // If there is a timezone offset
    if(str.charAt(offset) == '+' || str.charAt(offset) == '-' || str.charAt(offset) == 'Z') {
      parseTimezone(str.substring(offset)); // Don't remove the character when calling
      return;
    }

    if(str.charAt(offset) != ':') {
      throw new GedcomxDateException("Invalid Date: Invalid Minute-Second Separator");
    }

    if(end-offset < 3) {
      throw new GedcomxDateException("Invalid Date: Seconds must be 2 digits");
    }

    offset++;
    num = "";
    for(int i=0;i<2;i++) {
      if(!Character.isDigit(str.charAt(offset))) {
        throw new GedcomxDateException("Invalid Date: Malformed Seconds");
      }
      num += str.charAt(offset++);
    }

    seconds = Integer.valueOf(num);

    if(seconds > 59) {
      throw new GedcomxDateException("Invalid Date: Seconds must be between 0 and 59");
    }

    if(flag24 && seconds != 0) {
      throw new GedcomxDateException("Invalid Date: Hours of 24 requires 00 Seconds");
    }

    if(offset == end) {
      return;
    } else {
      parseTimezone(str.substring(offset)); // Don't remove the character when calling
    }

  }

  private void parseTimezone(String str) {
    int offset = 0;
    int end = str.length();
    String num;

    // If Z we're done
    if(str.charAt(offset) == 'Z') {
      if(end == 1) {
        tzHours = 0;
        tzMinutes = 0;
        return;
      } else {
        throw new GedcomxDateException("Invalid Date: Malformed Timezone - No Characters allowed after Z");
      }
    }

    if(end-offset < 3) {
      throw new GedcomxDateException("Invalid Date: Malformed Timezone - tzHours must be [+-] followed by 2 digits");
    }

    // Must start with a + or -
    if(str.charAt(offset) != '+' && str.charAt(offset) != '-') {
      throw new GedcomxDateException("Invalid Date: TimeZone Hours must begin with + or -");
    }

    offset++;
    num = str.substring(0,1);
    for(int i=0;i<2;i++) {
      if(!Character.isDigit(str.charAt(offset))) {
        throw new GedcomxDateException("Invalid Date: Malformed tzHours");
      }
      num += str.charAt(offset++);
    }

    tzHours = Integer.valueOf(num);
    // Set tzMinutes to clear out default local tz offset
    tzMinutes = 0;

    if(offset == end) {
      return;
    }

    if(str.charAt(offset) != ':') {
      throw new GedcomxDateException("Invalid Date: Invalid tzHour-tzMinute Separator");
    }

    if(end-offset < 3) {
      throw new GedcomxDateException("Invalid Date: tzSecond must be 2 digits");
    }

    offset++;
    num = "";
    for(int i=0;i<2;i++) {
      if(!Character.isDigit(str.charAt(offset))) {
        throw new GedcomxDateException("Invalid Date: Malformed tzMinutes");
      }
      num += str.charAt(offset++);
    }

    tzMinutes = Integer.valueOf(num);

    if(offset == end) {
      return;
    } else {
      throw new GedcomxDateException("Invalid Date: Malformed Timezone - No characters allowed after tzSeconds");
    }

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
    StringBuilder simple = new StringBuilder();

    simple.append(year >= 0 ? "+" : "-").append(String.format("%04d", Math.abs(year)));

    if(month != null) {
      simple.append("-").append(String.format("%02d", month));
    }

    if(day != null) {
      simple.append("-").append(String.format("%02d", day));
    }

    if(hours != null) {
      simple.append("T").append(String.format("%02d", hours));

      if(minutes != null) {
        simple.append(":").append(String.format("%02d", minutes));
      }

      if(seconds != null) {
        simple.append(":").append(String.format("%02d", seconds));
      }

      // If we have time we always have tz
      if(tzHours == 0 && tzMinutes == 0) {
        simple.append("Z");
      } else {
        simple.append(tzHours >= 0 ? "+" : "-").append(String.format("%02d", Math.abs(tzHours)));
        simple.append(":").append(String.format("%02d", tzMinutes));
      }
    }



    return simple.toString();
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

  public Integer getMinutes() {
    return minutes;
  }

  public Integer getSeconds() {
    return seconds;
  }

  public Integer getTzHours() {
    return tzHours;
  }

  public Integer getTzMinutes() {
    return tzMinutes;
  }
}
