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
package org.gedcomx.util.date;

import javax.xml.bind.annotation.XmlTransient;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * "Simple date" string is generally:
 *   (+|-)YYYY-MM-DDThh:mm:ss((+\-)hh:mm|Z)
 * or, using square brackets around optional parts:
 *   (+|-)YYYY[-MM[-DD[Thh[:mm[:ss[(+\-)hh[:mm]|Z]]]]]]
 */
@XmlTransient
public class SimpleDate {
  private int year; // -9999 = 10000 B.C.; -1 = 2 B.C.; 0 = 1 B.C.; 1 = 1 A.D.; 9999 = 9999 A.D.
  private Integer month; // 1..12 = January..December; null => none
  private Integer day; // 1..31; null => none
  private Integer hour, minute, second;
  private boolean isUTC; // true => "Z"
  private Integer timeZoneHours; // null => no time zone
  private Integer timeZoneMinutes; // must be null if timeZoneHours is null.  null => 0.

  //(+|-)YYYY[-MM[-DD[Thh[:mm[:ss]][(+\-)hh[:mm]|Z]]]]
  private static final Pattern simpleDatePattern = Pattern.compile(
          "((?:\\+|-)\\d{4})" + // (+|-)YYYY
                  "(?:-(\\d{2})(?:-(\\d{2})" + //[-MM[-DD...]]
                  "(?:T(\\d{2})(?::(\\d{2})(?::(\\d{2})?)?)?" + // [Thh[:mm[:ss]]...]
                  "(Z|((?:\\+|-)\\d{2})(?::(\\d{2}))?)?" + // [Z|(+|-)hh[:mm]] +
                  ")?" + // ...]
                  ")?)?"); // ...]]

  /**
   * Constructure that takes only a year, where
   *  -9999 = 10000 B.C.; -1 = 2 B.C.; 0 = 1 B.C.; 1 = 1 A.D.; 9999 = 9999 A.D.
   * @param year - year
   */
  public SimpleDate(int year) {
    this.year = year;
  }

  /**
   * Tell whether this SimpleDate is valid.  In particular, make sure that there are no
   *   specific date or time parts for which the more general parts are null,
   *   and make sure that if isUTC is set, then the time zone hours and minutes are null.
   * @return true if the date looks valid, false if there is a problem.
   */
  public boolean isValid() {
    if (timeZoneMinutes != null) {
      if (timeZoneMinutes < 0 || timeZoneMinutes > 59 || timeZoneHours == null || isUTC) {
        return false;
      }
    }
    if (timeZoneHours != null) {
      if (timeZoneHours < -23 || timeZoneHours > 23 || isUTC) {
        return false;
      }
    }
    if (hasProblem(second, 0, 59, minute) || hasProblem(minute, 0, 59, hour) || hasProblem(hour, 0, 23, day) ||
        hasProblem(day, 1, 31, month) || hasProblem(month, 1, 12, new Integer(year))) {
      return false;
    }
    return true; // looks ok
  }

  /**
   * Tell whether the number has a problem, meaning that either it is non-null and
   *   is outside the bounds given, or else its more general "parent" is non-null.
   * @param number - number to check
   * @param min - minimum allowable value for the number.
   * @param max - maximum allowable value for the number.
   * @param moreGeneral - more general number (e.g., a month if the number is for a day)
   * @return true if everything looks
   */
  private static boolean hasProblem(Integer number, Integer min, Integer max, Integer moreGeneral) {
    return number != null && (number < min || number > max || moreGeneral == null);
  }

  /**
   * Parse a GedcomX "simple date" string of the form:
   *   (+|-)YYYY[-MM[-DD[Thh[:mm[:ss]][Z|(+|-)hh[:mm]]]]]
   * @param simpleDateString - string to parse
   */
  public SimpleDate(String simpleDateString) {
    Matcher m = simpleDatePattern.matcher(simpleDateString);
    if (!m.matches()) {
      throw new IllegalArgumentException("Malformed simple date string.  Must be (+|-)YYYY[-MM[-DD[Thh[:mm[:ss]][Z|(+|-)hh[:mm]]]]]");
    }
    year = grabInt(m, 1);
    month = grabInt(m, 2);
    day = grabInt(m, 3);
    hour = grabInt(m, 4);
    minute = grabInt(m, 5);
    second = grabInt(m, 6);
    if (m.groupCount() >= 7 && m.group(7) != null && m.group(7).equals("Z")) {
      isUTC = true;
    }
    else {
      isUTC = false;
      timeZoneHours = grabInt(m, 8);
      timeZoneMinutes = grabInt(m, 9);
    }
  }

  /**
   * Constructor with a complete set of parameters.
   * @param year: -9999 = 10000 B.C.; -1 = 2 B.C.; 0 = 1 B.C.; 1 = 1 A.D.; 9999 = 9999 A.D.
   * @param month: 1..12, or null
   * @param day: 1..31, or null (must be null if month is null)
   * @param hour: 0..23 (must be null if day is null)
   * @param minute: 0..59 (must be null if hour is null)
   * @param second: 0..59 (must be null if minute is null)
   * @param isUTC: true => UTC time zone (i.e., use "Z" in string).
   * @param timeZoneHours: -23..23 (must be null if isUTC, or if hour is null)
   * @param timeZoneMinutes: 00..59 (can be null if 0; must be null if timeZoneHours is null)
   */
  public SimpleDate(int year, Integer month, Integer day, Integer hour, Integer minute, Integer second, boolean isUTC, Integer timeZoneHours, Integer timeZoneMinutes) {
    this.year = year;
    this.month = month;
    this.day = day;
    this.hour = hour;
    this.minute = minute;
    this.second = second;
    this.isUTC = isUTC;
    this.timeZoneHours = timeZoneHours;
    this.timeZoneMinutes = timeZoneMinutes;
  }

  /**
   * Convert the SimpleDate to a formal GedcomX simple date string, of the form:
   *   (+|-)YYYY[-MM[-DD[Thh[:mm[:ss]][(+\-)hh[:mm]|Z]]]]
   * @return formal GedcomX simple date string.
   */
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(year >= 0 ? "+" : "-").append(String.format("%04d", Math.abs(year)));
    if (month != null) {
      sb.append("-").append(String.format("%02d", month));
      if (day != null) {
        sb.append("-").append(String.format("%02d", day));
        if (hour != null) {
          sb.append("T").append(String.format("%02d", hour));
          if (minute != null) {
            sb.append(":").append(String.format("%02d", minute));
            if (second != null) {
              sb.append(":").append(String.format("%02d", second));
            }
          }
          if (isUTC) {
            sb.append("Z");
          }
          else if (timeZoneHours != null) {
            sb.append(timeZoneHours >= 0 ? "+" : "-").append(String.format("%02d", Math.abs(timeZoneHours)));
            if (timeZoneMinutes != null) {
              sb.append(":").append(timeZoneMinutes);
            }
          }
        }
      }
    }
    return sb.toString();
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
      if (s == null) {
        return null;
      }
      if (s.startsWith("+")) {
        s = s.substring(1);
      }
      return Integer.parseInt(s);
    }
  }

  /**
   * Get the year as an integer.  Positive years are treated as C.E. (A.D.);
   * Negative years are one less than the B.C.E. (B.C.) year.
   * For example, -9999 = 10000 B.C.; -1 = 2 B.C.; 0 = 1 B.C.; 1 = 1 A.D.; 2000 = 2000 A.D.; 9999 = 9999 A.D.
   * @return year portion of a GedcomX "simple" date.
   */
  public int getYear() {
    return year;
  }

  /**
   * Set the year as an integer.  Positive years are treated as C.E. (A.D.);
   * Negative years are one less than the B.C.E. (B.C.) year.
   * For example, -9999 = 10000 B.C.; -1 = 2 B.C.; 0 = 1 B.C.; 1 = 1 A.D.; 2000 = 2000 A.D.; 9999 = 9999 A.D.
   * @param year - year portion of a GedcomX simple date.
   */
  public void setYear(int year) {
    this.year = year;
  }

  /**
   * Get the month as an Integer, where 1=January and 12=December.
   * @return Month number (1..12), or null if there is no month.
   */
  public Integer getMonth() {
    return month;
  }

  /**
   * Set the month number as an integer, where 1=January and 12=December.
   * @param month - Month number (1..12), or null if there is no month.
   */
  public void setMonth(Integer month) {
    if (month != null && (month < 1 || month > 12)) {
      throw new IllegalArgumentException("Month must be from 1 to 12, or null if there is no month.");
    }
    this.month = month;
  }

  /**
   * Get the day of the month as an integer from 1..31.
   * @return Day number (1..31), or null if there is no day.
   */
  public Integer getDay() {
    return day;
  }

  /**
   * Get the day of the month as an integer from 1..31.
   * @param day - Day number (1..31), or null if there is no day.
   */
  public void setDay(Integer day) {
    if (day != null && (day < 1 || day > 31)) {
      throw new IllegalArgumentException("Day must be from 1 to 31, or null if there is no day");
    }
    this.day = day;
  }

  /**
   * Get the hour of the day as an integer from 0 (=midnight) to 23(=11 p.m.)
   * @return Hour of the day (0..23) or null if there is no hour.
   */
  public Integer getHour() {
    return hour;
  }

  /**
   * Set the hour of the day as an Integer from 0 (=midnight) to 23 (=11 p.m.).
   * Must be null if there is no day.
   * @param hour - Hour of the day (0..23) or null if there is no hour.
   */
  public void setHour(Integer hour) {
    this.hour = hour;
  }

  /**
   * Get the minute of the hour as an Integer (0..59).
   * Must be null if there is no hour.
   * @return Minute (0..59), or null if there is no minute.
   */
  public Integer getMinute() {
    return minute;
  }

  /**
   * Set the minute of the hour as an Integer (0..59)
   * Must be null if there is no hour.
   * @param minute - Minute of the hour (0..59), or null if there is no minute.
   */
  public void setMinute(Integer minute) {
    this.minute = minute;
  }

  /**
   * Get the second of the minute as an Integer (0..59).
   * Must be null if there is no minute.
   * @return Second (0..59), or null if there is no second.
   */
  public Integer getSecond() {
    return second;
  }

  /**
   * Set the second of the minute as an Integer (0..59)
   * Must be null if there is no minute.
   * @param second - Minute of the minute (0..59), or null if there is no second.
   */
  public void setSecond(Integer second) {
    this.second = second;
  }

  /**
   * Get a flag for whether this SimpleDate uses a time in Universal Time Code (UTC),
   *   in which case "Z" is used in the string, and the time zone hours and minutes are ignored.
   * @return true if this SimpleDate uses UTC; false otherwise.
   */
  public boolean isUTC() {
    return isUTC;
  }

  /**
   * Set the flag for whether this SimpleDate uses a time in Universal Time Code (UTC),
   *   in which case "Z" is used in the string, and the time zone hours and minutes are ignored.
   */
  public void setUTC(boolean UTC) {
    isUTC = UTC;
  }

  /**
   * Get the hour offset from GMT of the time zone.  Ignored if isUTC or if the hours is null.
   * @return Time zone hours, or null if not set.
   */
  public Integer getTimeZoneHours() {
    return timeZoneHours;
  }

  /**
   * Set the hour offset from GMT of the time zone.  Ignored if isUTC or if the hours is null.
   * @param timeZoneHours - Time zone hours, or null if not used.
   */
  public void setTimeZoneHours(Integer timeZoneHours) {
    this.timeZoneHours = timeZoneHours;
  }

  /**
   * Get the hour minutes of the time zone.  Ignored if isUTC or if the timeZoneHours is null.
   * @return Time zone minutes, or null if not set.
   */
  public Integer getTimeZoneMinutes() {
    return timeZoneMinutes;
  }

  /**
   * Set the minute offset of the time zone.  Ignored if isUTC or if the timeZoneHours is null.
   * @param timeZoneMinutes - Time zone minutes, or null if not used.
   */
  public void setTimeZoneMinutes(Integer timeZoneMinutes) {
    this.timeZoneMinutes = timeZoneMinutes;
  }
}

