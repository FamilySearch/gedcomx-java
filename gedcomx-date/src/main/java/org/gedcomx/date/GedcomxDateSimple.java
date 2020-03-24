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
 * A Simple Date
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

  /**
   * Instantiate a new Simple date based off of a formal date string.
   * @param date The date
   */
  public GedcomxDateSimple(String date) {
    parseDate(date);
  }

  /**
   * Parse the date portion of the formal string
   * @param date The date string
   */
  private void parseDate(String date) {

    // There is a minimum length of 5 characters
    if(date.length() < 5) {
      throw new GedcomxDateException("Invalid Date: Must have at least [+-]YYYY");
    }

    int end = date.length();
    int offset = 0;
    String num;

    // Must start with a + or -
    if(date.charAt(offset) != '+' && date.charAt(offset) != '-') {
      throw new GedcomxDateException("Invalid Date: Must begin with + or -");
    }

    offset++;
    num = date.charAt(0) == '-' ? "-" : "";
    for(int i=0;i<4;i++) {
      if(!Character.isDigit(date.charAt(offset))) {
        throw new GedcomxDateException("Invalid Date: Malformed Year");
      }
      num += date.charAt(offset++);
    }

    year = Integer.valueOf(num);

    if(year == 0) {
      throw new GedcomxDateException("Invalid Date: Year 0000 does not exist in Anno Domini (AD) system");
    }

    if(offset == end) {
      return;
    }

    // If there is time
    if(date.charAt(offset) == 'T') {
      parseTime(date.substring(offset+1));
      return;
    }

    // Month
    if(date.charAt(offset) != '-') {
      throw new GedcomxDateException("Invalid Date: Invalid Year-Month Separator");
    }

    if(end-offset < 3) {
      throw new GedcomxDateException("Invalid Date: Month must be 2 digits");
    }

    offset++;
    num = "";
    for(int i=0;i<2;i++) {
      if(!Character.isDigit(date.charAt(offset))) {
        throw new GedcomxDateException("Invalid Date: Malformed Month");
      }
      num += date.charAt(offset++);
    }

    month = Integer.valueOf(num);

    if(month < 1 || month > 12) {
      throw new GedcomxDateException("Invalid Date: Month must be between 1 and 12");
    }

    if(offset == end) {
      return;
    }

    // If there is time
    if(date.charAt(offset) == 'T') {
      parseTime(date.substring(offset+1));
      return;
    }

    // Day
    if(date.charAt(offset) != '-') {
      throw new GedcomxDateException("Invalid Date: Invalid Month-Day Separator");
    }

    if(end-offset < 3) {
      throw new GedcomxDateException("Invalid Date: Day must be 2 digits");
    }

    offset++;
    num = "";
    for(int i=0;i<2;i++) {
      if(!Character.isDigit(date.charAt(offset))) {
        throw new GedcomxDateException("Invalid Date: Malformed Day");
      }
      num += date.charAt(offset++);
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

    if(date.charAt(offset) == 'T') {
      parseTime(date.substring(offset+1));
    } else {
      throw new GedcomxDateException("Invalid Date: +YYYY-MM-DD must have T before time");
    }

  }

  /**
   * Parse the time portion of the formal string
   * @param date The date string (minus the date)
   */
  private void parseTime(String date) {

    int offset = 0;
    int end = date.length();
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
      if(!Character.isDigit(date.charAt(offset))) {
        throw new GedcomxDateException("Invalid Date: Malformed Hours");
      }
      num += date.charAt(offset++);
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
    if(date.charAt(offset) == '+' || date.charAt(offset) == '-' || date.charAt(offset) == 'Z') {
      parseTimezone(date.substring(offset)); // Don't remove the character when calling
      return;
    }

    if(date.charAt(offset) != ':') {
      throw new GedcomxDateException("Invalid Date: Invalid Hour-Minute Separator");
    }

    if(end-offset < 3) {
      throw new GedcomxDateException("Invalid Date: Minutes must be 2 digits");
    }

    offset++;
    num = "";
    for(int i=0;i<2;i++) {
      if(!Character.isDigit(date.charAt(offset))) {
        throw new GedcomxDateException("Invalid Date: Malformed Minutes");
      }
      num += date.charAt(offset++);
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
    if(date.charAt(offset) == '+' || date.charAt(offset) == '-' || date.charAt(offset) == 'Z') {
      parseTimezone(date.substring(offset)); // Don't remove the character when calling
      return;
    }

    if(date.charAt(offset) != ':') {
      throw new GedcomxDateException("Invalid Date: Invalid Minute-Second Separator");
    }

    if(end-offset < 3) {
      throw new GedcomxDateException("Invalid Date: Seconds must be 2 digits");
    }

    offset++;
    num = "";
    for(int i=0;i<2;i++) {
      if(!Character.isDigit(date.charAt(offset))) {
        throw new GedcomxDateException("Invalid Date: Malformed Seconds");
      }
      num += date.charAt(offset++);
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
      parseTimezone(date.substring(offset)); // Don't remove the character when calling
    }

  }

  /**
   * Parse the timezone portion of the formal string
   * @param date The date string (minus the date and time)
   */
  private void parseTimezone(String date) {
    int offset = 0;
    int end = date.length();
    String num;

    // If Z we're done
    if(date.charAt(offset) == 'Z') {
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
    if(date.charAt(offset) != '+' && date.charAt(offset) != '-') {
      throw new GedcomxDateException("Invalid Date: TimeZone Hours must begin with + or -");
    }

    offset++;
    num = date.charAt(0) == '-' ? "-" : "";
    for(int i=0;i<2;i++) {
      if(!Character.isDigit(date.charAt(offset))) {
        throw new GedcomxDateException("Invalid Date: Malformed tzHours");
      }
      num += date.charAt(offset++);
    }

    tzHours = Integer.valueOf(num);
    // Set tzMinutes to clear out default local tz offset
    tzMinutes = 0;

    if(offset == end) {
      return;
    }

    if(date.charAt(offset) != ':') {
      throw new GedcomxDateException("Invalid Date: Invalid tzHour-tzMinute Separator");
    }

    if(end-offset < 3) {
      throw new GedcomxDateException("Invalid Date: tzSecond must be 2 digits");
    }

    offset++;
    num = "";
    for(int i=0;i<2;i++) {
      if(!Character.isDigit(date.charAt(offset))) {
        throw new GedcomxDateException("Invalid Date: Malformed tzMinutes");
      }
      num += date.charAt(offset++);
    }

    tzMinutes = Integer.valueOf(num);

    if(offset == end) {
      return;
    } else {
      throw new GedcomxDateException("Invalid Date: Malformed Timezone - No characters allowed after tzSeconds");
    }

  }

  /**
   * Get the Date Type
   * @return The type
   */
  @Override
  public GedcomxDateType getType() {
    return GedcomxDateType.SIMPLE;
  }

  /**
   * Whether or not this date can be considered approximate
   * @return True if this is approximate
   */
  @Override
  public boolean isApproximate() {
    return false;
  }

  /**
   * Output the formal string for this date
   * @return The formal date string
   */
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

  /**
   * Get the year
   * @return The Year
   */
  public Integer getYear() {
    return year;
  }

  /**
   * Get the month
   * @return The Month
   */
  public Integer getMonth() {
    return month;
  }

  /**
   * Get the day
   * @return The Day
   */
  public Integer getDay() {
    return day;
  }

  /**
   * Get the hours
   * @return The Hours
   */
  public Integer getHours() {
    return hours;
  }

  /**
   * Get the minutes
   * @return The Minutes
   */
  public Integer getMinutes() {
    return minutes;
  }

  /**
   * Get the seconds
   * @return The seconds
   */
  public Integer getSeconds() {
    return seconds;
  }

  /**
   * Get the timezone hours
   * @return The Timezone Hours
   */
  public Integer getTzHours() {
    return tzHours;
  }

  /**
   * Get the timezone minutes
   * @return The Timezone Minutes
   */
  public Integer getTzMinutes() {
    return tzMinutes;
  }
}
