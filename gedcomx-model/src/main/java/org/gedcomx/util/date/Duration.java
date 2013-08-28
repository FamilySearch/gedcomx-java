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
package org.gedcomx.util.date;

import javax.xml.bind.annotation.XmlTransient;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class representing a Duration in a GedcomX formal date.
 * The form of a Duration string is
 *
 *   P[yyyyY][mmM][ddD][T[hhH][mmM][ssS]]
 *
 * for a duration in years, months, days, hours, minutes and/or seconds.
 *
 * User: Randy Wilson
 * Date: 8/8/13
 * Time: 6:37 PM
 */
@XmlTransient
public class Duration {
  //  P[yyyyY][mmM][ddD][T[hhH][mmM][ssS]]
  private static final Pattern durationPattern = Pattern.compile("P(\\d{4}Y)?(\\d{2}M)?(\\d{2}D)?(?:T(\\d{2}H)?(\\d{2}M)?(\\d{2}S)?)?");
  private Integer year;
  private Integer month;
  private Integer day;
  private Integer hour;
  private Integer minute;
  private Integer second;

  /**
   * Constructor that fully specifies all of the fields.
   * @param year - Duration in years, or null if not set.
   * @param month - Duration in months, or null if not set.
   * @param day - Duration in days, or null if not set.
   * @param hour - Duration in hours, or null if not set.
   * @param minute - Duration in minutes, or null if not set.
   * @param second - Duration in seconds, or null if not set.
   */
  public Duration(Integer year, Integer month, Integer day, Integer hour, Integer minute, Integer second) {
    this.year = year;
    this.month = month;
    this.day = day;
    this.hour = hour;
    this.minute = minute;
    this.second = second;
  }

  private static boolean ok(Integer number, int min, int max) {
    return number == null || (number >= min && number <= max);
  }

  /**
   * Constructor that takes a valid duration string and parses it into a Duration object.
   * @param durationString - String of the form "P[yyyyY][mmM][ddD][T[hhH][mmM][ssS]]" that specifies a duration.
   */
  public Duration(String durationString) {
    Matcher m = durationPattern.matcher(durationString);
    if (m.matches()) {
      this.year = grabInt(m, 1);
      this.month = grabInt(m, 2);
      this.day = grabInt(m, 3);
      this.hour = grabInt(m, 4);
      this.minute = grabInt(m, 5);
      this.second = grabInt(m, 6);
    }
    else {
      throw new IllegalArgumentException("Illegal format for duration: " + durationString + "; must be P[yyyyY][mmM][ddD][T[hhH][mmM][ssS]]");
    }
  }

  /**
   * Convert the string in the given group to an Integer, unless there are not that many groups or the group is null.
   * If the string begins with "+" then this character is stripped off.
   * @param matcher - Matcher matched against the simpleDatePattern.
   * @param group - group index to get an integer from in the matcher.
   * @return null if not enough groups or the group is null, or the parsed Integer otherwise.
   */
  private static Integer grabInt(Matcher matcher, int group) {
    if (matcher.groupCount() < group) {
      return null;
    }
    else {
      String s = matcher.group(group);
      if (s == null || s.length() == 0) {
        return null;
      }
      // Strip off last character (e.g., "12H" -> "12")
      String numberString = s.substring(0, s.length() - 1);
      return Integer.parseInt(numberString);
    }
  }

  /**
   * Get the duration year, or null if not specified.
   * @return the duration year, or null if not specified.
   */
  public Integer getYear() {
    return year;
  }

  /**
   * Set the duration year.
   * @param year - duration year, or null if not specified.
   */
  public void setYear(Integer year) {
    this.year = year;
  }

  /**
   * Get the duration month, or null if not specified.
   * @return the duration month, or null if not specified.
   */
  public Integer getMonth() {
    return month;
  }

  /**
   * Set the duration month.
   * @param month - duration month, or null if not specified.
   */
  public void setMonth(Integer month) {
    this.month = month;
  }

  /**
   * Get the duration day, or null if not specified.
   * @return the duration day, or null if not specified.
   */
  public Integer getDay() {
    return day;
  }

  /**
   * Set the duration day.
   * @param day - duration day, or null if not specified.
   */
  public void setDay(Integer day) {
    this.day = day;
  }

  /**
   * Get the duration hour, or null if not specified.
   * @return the duration hour, or null if not specified.
   */
  public Integer getHour() {
    return hour;
  }

  /**
   * Set the duration hour.
   * @param hour - duration hour, or null if not specified.
   */
  public void setHour(Integer hour) {
    this.hour = hour;
  }

  /**
   * Get the duration minute, or null if not specified.
   * @return the duration minute, or null if not specified.
   */
  public Integer getMinute() {
    return minute;
  }

  /**
   * Set the duration minute.
   * @param minute - duration minute, or null if not specified.
   */
  public void setMinute(Integer minute) {
    this.minute = minute;
  }

  /**
   * Get the duration second, or null if not specified.
   * @return the duration second, or null if not specified.
   */
  public Integer getSecond() {
    return second;
  }

  /**
   * Set the duration second.
   * @param second - duration second, or null if not specified.
   */
  public void setSecond(Integer second) {
    this.second = second;
  }

  /**
   * Convert this Duration to the canonical string for a Duration, of the form:
   *   P[yyyyY][mmM][ddD][T[hhH][mmM][ssS]]
   * for use in a GedcomX formal date string.
   * @return canonical String representation of this Duration.
   */
  public String toString() {
    StringBuilder sb = new StringBuilder("P");
    addNumber(sb, year, 4, "Y");
    addNumber(sb, month, 2, "M");
    addNumber(sb, day, 2, "D");
    if (hour != null || minute != null || second != null) {
      sb.append("T");
      addNumber(sb, hour, 2, "H");
      addNumber(sb, minute, 2, "M");
      addNumber(sb, second, 2, "S");
    }
    return sb.toString();
  }

  /**
   * Add the given number to the StringBuilder, zero-padded to the given number of digits,
   *   and with the given suffix at the end, unless the number is null, in which case nothing
   *   is added.
   * @param sb - StringBuilder to add the number to.
   * @param number - number to add, or null if nothing is to be added.
   * @param digits - number of digits to zero-pad the number to
   * @param suffix - character to append to the end of the number (e.g., "Y" for year).
   */
  private static void addNumber(StringBuilder sb, Integer number, int digits, String suffix) {
    if (number != null) {
      sb.append(String.format("%0" + digits + "d" + suffix, number));
    }
  }

  /**
   * Tell whether the Duration is valid, which means that at least one of its values is non-null,
   * and none exceeds the 2-digit limit (or 4 digits for year).
   * @return true if valid, false otherwise.
   */
  public boolean isValid() {
    if (year == null && month == null && day == null && hour == null && minute == null && second == null) {
      return false; // must have at least one value to be valid.
    }
    // While we might expect the two-digit values to be more constrained (e.g., month=1..12; minute=1..59),
    // the format allows larger values, and perhaps a duration of 55 days is a reasonable thing to express.
    return ok(year, 0, 9999) && ok(month, 0, 99) && ok(day, 0, 99) && ok(hour, 0, 99) && ok(minute, 0, 99) && ok(second, 0, 99);
  }
}
