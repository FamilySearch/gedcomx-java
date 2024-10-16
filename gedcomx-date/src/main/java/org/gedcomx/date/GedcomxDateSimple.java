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

import java.time.Instant;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Optional;
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
   * Instantiate a new Simple date based off of raw values. This constructor is package protected as
   * these values are not validated.
   * @param year The year
   * @param month The month
   * @param day The day
   * @param hours The hours
   * @param minutes The minutes
   * @param seconds The seconds
   * @param tzHours The timezone hours
   * @param tzMinutes The timezone minutes
   */
  GedcomxDateSimple(Integer year, Integer month, Integer day, Integer hours, Integer minutes, Integer seconds, Integer tzHours, Integer tzMinutes) {
    this.year = year;
    this.month = month;
    this.day = day;
    this.hours = hours;
    this.minutes = minutes;
    this.seconds = seconds;
    this.tzHours = tzHours;
    this.tzMinutes = tzMinutes;
  }

  /**
   * Parse the date portion of the formal string
   * @param date The date string
   */
  private void parseDate(String date) {

    // There is a minimum length of 5 characters
    if(date.length() < 5) {
      throw new GedcomxDateException("Invalid Date \"" + date + "\": Must have at least [+-]YYYY");
    }

    int end = date.length();
    int offset = 0;
    StringBuilder num;

    // Must start with a + or -
    if(date.charAt(offset) != '+' && date.charAt(offset) != '-') {
      throw new GedcomxDateException("Invalid Date \"" + date + "\": Must begin with + or -");
    }

    offset++;
    num = new StringBuilder(date.charAt(0) == '-' ? "-" : "");
    for(int i=0;i<4;i++) {
      if(!Character.isDigit(date.charAt(offset))) {
        throw new GedcomxDateException("Invalid Date \"" + date + "\": Malformed Year");
      }
      num.append(date.charAt(offset++));
    }

    year = Integer.valueOf(num.toString());

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
      throw new GedcomxDateException("Invalid Date \"" + date + "\": Invalid Year-Month Separator");
    }

    if(end-offset < 3) {
      throw new GedcomxDateException("Invalid Date \"" + date + "\": Month must be 2 digits");
    }

    offset++;
    num = new StringBuilder();
    for(int i=0;i<2;i++) {
      if(!Character.isDigit(date.charAt(offset))) {
        throw new GedcomxDateException("Invalid Date \"" + date + "\": Malformed Month");
      }
      num.append(date.charAt(offset++));
    }

    month = Integer.valueOf(num.toString());

    if(month < 1 || month > 12) {
      throw new GedcomxDateException("Invalid Date \"" + date + "\": Month must be between 1 and 12");
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
      throw new GedcomxDateException("Invalid Date \"" + date + "\": Invalid Month-Day Separator");
    }

    if(end-offset < 3) {
      throw new GedcomxDateException("Invalid Date \"" + date + "\": Day must be 2 digits");
    }

    offset++;
    num = new StringBuilder();
    for(int i=0;i<2;i++) {
      if(!Character.isDigit(date.charAt(offset))) {
        throw new GedcomxDateException("Invalid Date \"" + date + "\": Malformed Day");
      }
      num.append(date.charAt(offset++));
    }

    day = Integer.valueOf(num.toString());

    if(day < 1) {
      throw new GedcomxDateException("Invalid Date \"" + date + "\": Day 0 does not exist");
    }

    int daysInMonth = YearMonth.of(year, month).lengthOfMonth();
    if(day > daysInMonth) {
      throw new GedcomxDateException("Invalid Date \"" + date + "\": There are only "+daysInMonth+" days in Month "+month+" year "+year);
    }

    if(offset == end) {
      return;
    }

    if(date.charAt(offset) == 'T') {
      parseTime(date.substring(offset+1));
    } else {
      throw new GedcomxDateException("Invalid Date \"" + date + "\": +YYYY-MM-DD must have T before time");
    }

  }

  /**
   * Parse the time portion of the formal string
   * @param date The date string (minus the date)
   */
  private void parseTime(String date) {

    int offset = 0;
    int end = date.length();
    StringBuilder num;
    boolean flag24 = false;

    // Always initialize the Timezone to the local offset.
    // It may be overridden if set
    TimeZone tz = TimeZone.getDefault();
    Calendar cal = Calendar.getInstance(tz);
    int offsetInMillis = tz.getOffset(cal.getTimeInMillis());
    tzHours = offsetInMillis / 3600000;
    tzMinutes = (offsetInMillis / 60000) % 60;

    // You must at least have hours
    if(end < 2) {
      throw new GedcomxDateException("Invalid Date \"" + date + "\": Hours must be 2 digits");
    }

    num = new StringBuilder();
    for(int i=0;i<2;i++) {
      if(!Character.isDigit(date.charAt(offset))) {
        throw new GedcomxDateException("Invalid Date \"" + date + "\": Malformed Hours");
      }
      num.append(date.charAt(offset++));
    }

    hours = Integer.valueOf(num.toString());

    if(hours > 24) {
      throw new GedcomxDateException("Invalid Date \"" + date + "\": Hours must be between 0 and 24");
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
      throw new GedcomxDateException("Invalid Date \"" + date + "\": Invalid Hour-Minute Separator");
    }

    if(end-offset < 3) {
      throw new GedcomxDateException("Invalid Date \"" + date + "\": Minutes must be 2 digits");
    }

    offset++;
    num = new StringBuilder();
    for(int i=0;i<2;i++) {
      if(!Character.isDigit(date.charAt(offset))) {
        throw new GedcomxDateException("Invalid Date \"" + date + "\": Malformed Minutes");
      }
      num.append(date.charAt(offset++));
    }

    minutes = Integer.valueOf(num.toString());

    if(minutes > 59) {
      throw new GedcomxDateException("Invalid Date \"" + date + "\": Minutes must be between 0 and 59");
    }

    if(flag24 && minutes != 0) {
      throw new GedcomxDateException("Invalid Date \"" + date + "\": Hours of 24 requires 00 Minutes");
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
      throw new GedcomxDateException("Invalid Date \"" + date + "\": Invalid Minute-Second Separator");
    }

    if(end-offset < 3) {
      throw new GedcomxDateException("Invalid Date \"" + date + "\": Seconds must be 2 digits");
    }

    offset++;
    num = new StringBuilder();
    for(int i=0;i<2;i++) {
      if(!Character.isDigit(date.charAt(offset))) {
        throw new GedcomxDateException("Invalid Date \"" + date + "\": Malformed Seconds");
      }
      num.append(date.charAt(offset++));
    }

    seconds = Integer.valueOf(num.toString());

    if(seconds > 59) {
      throw new GedcomxDateException("Invalid Date \"" + date + "\": Seconds must be between 0 and 59");
    }

    if(flag24 && seconds != 0) {
      throw new GedcomxDateException("Invalid Date \"" + date + "\": Hours of 24 requires 00 Seconds");
    }

    if(offset != end) {
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
    StringBuilder num;

    // If Z we're done
    if(date.charAt(offset) == 'Z') {
      if(end == 1) {
        tzHours = 0;
        tzMinutes = 0;
        return;
      } else {
        throw new GedcomxDateException("Invalid Date \"" + date + "\": Malformed Timezone - No Characters allowed after Z");
      }
    }

    if(end-offset < 3) {
      throw new GedcomxDateException("Invalid Date \"" + date + "\": Malformed Timezone - tzHours must be [+-] followed by 2 digits");
    }

    // Must start with a + or -
    if(date.charAt(offset) != '+' && date.charAt(offset) != '-') {
      throw new GedcomxDateException("Invalid Date \"" + date + "\"\"" + date + "\": TimeZone Hours must begin with + or -");
    }

    offset++;
    num = new StringBuilder(date.charAt(0) == '-' ? "-" : "");
    for(int i=0;i<2;i++) {
      if(!Character.isDigit(date.charAt(offset))) {
        throw new GedcomxDateException("Invalid Date \"" + date + "\": Malformed tzHours");
      }
      num.append(date.charAt(offset++));
    }

    tzHours = Integer.valueOf(num.toString());
    // Set tzMinutes to clear out default local tz offset
    tzMinutes = 0;

    if(offset == end) {
      return;
    }

    if(date.charAt(offset) != ':') {
      throw new GedcomxDateException("Invalid Date \"" + date + "\": Invalid tzHour-tzMinute Separator");
    }

    if(end-offset < 3) {
      throw new GedcomxDateException("Invalid Date \"" + date + "\": tzSecond must be 2 digits");
    }

    offset++;
    num = new StringBuilder();
    for(int i=0;i<2;i++) {
      if(!Character.isDigit(date.charAt(offset))) {
        throw new GedcomxDateException("Invalid Date \"" + date + "\": Malformed tzMinutes");
      }
      num.append(date.charAt(offset++));
    }

    tzMinutes = Integer.valueOf(num.toString());

    if(offset != end) {
      throw new GedcomxDateException("Invalid Date \"" + date + "\": Malformed Timezone - No characters allowed after tzSeconds");
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

  /**
   * Compares this GedcomxDateSimple object with either another GedcomxDateSimple object
   * or a GedcomxDateApproximate object.  Comparison is achieved by using an ISO 8601 date
   * format using the populated temporal fields in this object amd the fields in the other
   * object.  If a field is null it defaults to a "0th" value.  So in the case of the simplest
   * date of only a year field value it would default to January 1 at midnight UTC of the
   * given year.  ISO 8601 conversion occurs for both <code>this</code> and <code>other</code>.
   * In other words, if there is missing field information it will reflect as the earliest
   * possible ISO 8601 representation of the object to use in comparison.
   * @param other the object to be compared.
   * @return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object
   * @throws ClassCastException if other is not of type GedcomxDateSimple or GedcomxDateApproximate
   * @throws NullPointerException if other is null
   */
  @Override
  public int compareTo(GedcomxDate other) {
    if (other == null) {
      throw new NullPointerException();
    }
    GedcomxDateSimple o;
    if (other instanceof GedcomxDateSimple) {
      o = (GedcomxDateSimple) other;
    } else if (other instanceof GedcomxDateApproximate) {
      o = ((GedcomxDateApproximate) other).getSimpleDate();
    } else {
      throw new ClassCastException("other is not an instance of either GedcomxDateSimple or GedcomxDateApproximate");
    }
    String isoFormat = "% 05d-%02d-%02dT%02d:%02d:%02d%+03d:%02d";
    String isoThisDate = String.format(isoFormat,
            this.getYear(),
            Optional.ofNullable(this.getMonth()).orElse(1),
            Optional.ofNullable(this.getDay()).orElse(1),
            Optional.ofNullable(this.getHours()).orElse(0),
            Optional.ofNullable(this.getMinutes()).orElse(0),
            Optional.ofNullable(this.getSeconds()).orElse(0),
            Optional.ofNullable(this.getTzHours()).orElse(0),
            Optional.ofNullable(this.getTzMinutes()).orElse(0)).stripLeading();
    String isoOtherDate = String.format(isoFormat,
            o.getYear(),
            Optional.ofNullable(o.getMonth()).orElse(1),
            Optional.ofNullable(o.getDay()).orElse(1),
            Optional.ofNullable(o.getHours()).orElse(0),
            Optional.ofNullable(o.getMinutes()).orElse(0),
            Optional.ofNullable(o.getSeconds()).orElse(0),
            Optional.ofNullable(o.getTzHours()).orElse(0),
            Optional.ofNullable(o.getTzMinutes()).orElse(0)).stripLeading();
    Instant thisInstant = DateTimeFormatter.ISO_INSTANT.parse(isoThisDate, Instant::from);
    Instant otherInstant = DateTimeFormatter.ISO_INSTANT.parse(isoOtherDate, Instant::from);
    return thisInstant.compareTo(otherInstant);
  }
}
