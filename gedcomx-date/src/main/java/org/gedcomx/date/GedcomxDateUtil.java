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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * Static utility functions for handling GedcomX Dates
 * @author John Clark.
 */
public class GedcomxDateUtil {

  private GedcomxDateUtil(){
  }

  /**
   * Parse a String representation of a Formal GedcomX Date
   * @param date The GedcomX Date
   * @return A GedcomxDate
   */
  public static GedcomxDate parse(String date) {

    if(date == null || date.isEmpty()) {
      throw new GedcomxDateException("Invalid Date");
    }

    if(date.charAt(0) == 'R') {
      return new GedcomxDateRecurring(date);
    } else if(date.contains("/")) {
      return new GedcomxDateRange(date);
    } else if(date.charAt(0) == 'A') {
      return new GedcomxDateApproximate(date);
    } else {
      return new GedcomxDateSimple(date);
    }
  }

  /**
   * Calculates the Duration between two dates
   * @param startDate The start date
   * @param endDate The end date
   * @return The duration
   */
  public static GedcomxDateDuration getDuration(GedcomxDateSimple startDate, GedcomxDateSimple endDate) {

    if(startDate == null || endDate == null) {
      throw new GedcomxDateException("Start and End must be simple dates");
    }

    Date start = new Date(startDate, true);
    Date end = new Date(endDate, true);

    if(end.year-start.year < 0) {
      throw new GedcomxDateException("Start Date=" + startDate.toFormalString() + " must be less than End Date=" + endDate.toFormalString());
    }

    boolean hasTime = false;
    StringBuilder duration = new StringBuilder();

    zipDates(start, end);

    // Build the duration backwards so we can grab the correct diff
    // Also we need to roll everything up so we don't generate an invalid max year
    if(end.seconds != null) {
      while(end.seconds-start.seconds < 0) {
        end.minutes -= 1;
        end.seconds += 60;
      }
      if(end.seconds-start.seconds > 0) {
        hasTime = true;
        duration.insert(0,'S').insert(0,String.format("%02d", end.seconds - start.seconds));
      }
    }

    if(end.minutes != null) {
      while(end.minutes-start.minutes < 0) {
        end.hours -= 1;
        end.minutes += 60;
      }
      if(end.minutes-start.minutes > 0) {
        hasTime = true;
        duration.insert(0,'M').insert(0,String.format("%02d", end.minutes-start.minutes));
      }
    }

    if(end.hours != null) {
      while(end.hours-start.hours < 0) {
        end.day -= 1;
        end.hours += 24;
      }
      if(end.hours-start.hours > 0) {
        hasTime = true;
        duration.insert(0,'H').insert(0,String.format("%02d", end.hours-start.hours));
      }
    }

    if(hasTime) {
      duration.insert(0,'T');
    }

    if(end.day != null) {
      while(end.day-start.day < 0) {
        end.day += daysInMonth(end.month == 1 ? 12 : end.month - 1, end.year);
        end.month -= 1;
        if(end.month < 1) {
          end.year -= 1;
          end.month += 12;
        }
      }
      if(end.day-start.day > 0) {
        duration.insert(0,'D').insert(0,String.format("%02d", end.day-start.day));
      }
    }

    if(end.month != null) {
      while(end.month-start.month < 0) {
        end.year -= 1;
        end.month += 12;
      }
      if(end.month-start.month > 0) {
        duration.insert(0,'M').insert(0,String.format("%02d", end.month-start.month));
      }
    }

    if(end.year-start.year > 0) {
      duration.insert(0,'Y').insert(0,String.format("%04d", end.year-start.year));
    }

    String finalDuration = duration.toString();

    if(end.year-start.year < 0 || duration.toString().isEmpty()) {
      throw new GedcomxDateException("Start Date must be less than End Date");
    }

    return new GedcomxDateDuration("P"+finalDuration);
  }

  /**
   * Add a duration to a simple date
   * @param startDate The date to start from
   * @param duration The duration to add
   * @return a new simple date
   */
  public static GedcomxDateSimple addDuration(GedcomxDateSimple startDate, GedcomxDateDuration duration) {

    if(startDate == null) {
      throw new GedcomxDateException("Invalid Start Date");
    }

    if(duration == null) {
      throw new GedcomxDateException("Invalid Duration");
    }

    // start with the start date, LocalDateTimes, don't support null values
    // fill in the blanks with 0's and reset the empties when building the gedcomx date
    // to return
    LocalDateTime endLocalDateTime = LocalDateTime.of(
      startDate.getYear(), isNull(startDate.getMonth()) ? 1 : startDate.getMonth(),
        isNull(startDate.getDay()) ? 1 : startDate.getDay(),
        isNull(startDate.getHours()) ? 0 : startDate.getHours(),
        isNull(startDate.getMinutes()) ? 0 : startDate.getMinutes(),
        isNull(startDate.getSeconds()) ? 0 : startDate.getSeconds())
      // apply the duration to get the end date.
      .plusSeconds(isNull(duration.getSeconds()) ? 0 : duration.getSeconds())
      .plusMinutes(isNull(duration.getMinutes()) ? 0 : duration.getMinutes())
      .plusHours(isNull(duration.getHours()) ? 0 : duration.getHours())
      .plusDays(isNull(duration.getDays()) ? 0 : duration.getDays())
      .plusMonths(isNull(duration.getMonths()) ? 0 : duration.getMonths())
      .plusYears(isNull(duration.getYears()) ? 0 : duration.getYears());

    if(endLocalDateTime.getYear() > 9999) {
      throw new GedcomxDateException("New date out of range");
    }

    return buildGedcomxDateFromLocalTimeDate(startDate, duration, endLocalDateTime);
    }

    /**
     * Helper method to build an end GedcomxDateSimple from a GedcomxSimple, GedcomxDateDuration and LocalDateTime,
     * that has been offset by the specified duration
     * @param startDate The date to start from
     * @param duration The duration to subtract
     * @return a new simple date
     */
  private static GedcomxDateSimple buildGedcomxDateFromLocalTimeDate(GedcomxDateSimple startDate, GedcomxDateDuration duration, LocalDateTime endLocalDateTime) {
    // The end date should only contain the fields that are present in the start date or modified by the duration
    //
    Integer endMonth = null;
    Integer endDay = null;
    Integer endHours = null;
    Integer endMinutes = null;
    Integer endSeconds = null;
    boolean foundDuration=false;
    if(nonNull(startDate.getSeconds()) || nonNull(duration.getSeconds())) {
      endSeconds = endLocalDateTime.getSecond();
      foundDuration=true;
    }
    if(foundDuration || nonNull(startDate.getMinutes()) || nonNull(duration.getMinutes())) {
      endMinutes = endLocalDateTime.getMinute();
      foundDuration=true;
    }
    if(foundDuration || nonNull(startDate.getHours()) || nonNull(duration.getHours())) {
      endHours = endLocalDateTime.getHour();
      foundDuration=true;
    }
    if(foundDuration || nonNull(startDate.getDay()) || nonNull(duration.getDays())) {
      endDay = endLocalDateTime.getDayOfMonth();
      foundDuration=true;
    }
    if(foundDuration || nonNull(startDate.getMonth()) || nonNull(duration.getMonths())) {
      endMonth = endLocalDateTime.getMonthValue();
    }
    return new GedcomxDateSimple(endLocalDateTime.getYear(), endMonth, endDay, endHours, endMinutes, endSeconds, startDate.getTzHours(), startDate.getTzMinutes());
  }

  /**
   * Multiple a duration by a fixed number
   * @param duration The duration to multiply
   * @param multiplier The amount to multiply by
   * @return The new, multiplied duration
   */
  public static GedcomxDateDuration multiplyDuration(GedcomxDateDuration duration, int multiplier) {

    if(duration == null) {
      throw new GedcomxDateException("Invalid Duration");
    }

    if(multiplier <= 0) {
      throw new GedcomxDateException("Invalid Multiplier");
    }

    StringBuilder newDuration = new StringBuilder("P");

    if(duration.getYears() != null) {
      newDuration.append(duration.getYears()*multiplier).append('Y');
    }

    if(duration.getMonths() != null) {
      newDuration.append(duration.getMonths()*multiplier).append('M');
    }

    if(duration.getDays() != null) {
      newDuration.append(duration.getDays()*multiplier).append('D');
    }

    if(duration.getHours() != null || duration.getMinutes() != null || duration.getSeconds() != null) {
      newDuration.append('T');

      if(duration.getHours() != null) {
        newDuration.append(duration.getHours()*multiplier).append('H');
      }

      if(duration.getMinutes() != null) {
        newDuration.append(duration.getMinutes()*multiplier).append('M');
      }

      if(duration.getSeconds() != null) {
        newDuration.append(duration.getSeconds()*multiplier).append('S');
      }

    }

    return new GedcomxDateDuration(newDuration.toString());
  }

  /**
   * Find the number of days in a month within a given year
   * @param month The month
   * @param year The year
   * @return The number of days in the month
   */
  public static int daysInMonth(Integer month, Integer year) {
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
        } else
          leapYear = year % 400 == 0;
        if(leapYear) {
          return 29;
        } else {
          return 28;
        }
      default:
        throw new GedcomxDateException("Unknown Month=" + month);
    }
  }

  /**
   * Ensures that both start and end have values where the other has values.
   * For example, if start has minutes but end does not, this function
   * will initialize minutes in end.
   * @param start The start date
   * @param end The end date
   */
  protected static void zipDates(Date start, Date end) {
    if(start.month == null && end.month != null) {
      start.month = 1;
    }
    if(start.month != null && end.month == null) {
      end.month = 12;
    }

    if(start.day == null && end.day != null) {
      start.day = 1;
    }
    if(start.day != null && end.day == null) {
      if(end.month != null && end.year !=null ) {
        end.day = daysInMonth(start.month, start.year);
      } else {
        end.day = start.day;
      }
    }

    if(start.hours == null && end.hours != null) {
      start.hours = 0;
    }
    if(start.hours != null && end.hours == null) {
      end.hours = 23;
    }

    if(start.minutes == null && end.minutes != null) {
      start.minutes = 0;
    }
    if(start.minutes != null && end.minutes == null) {
      end.minutes = 59;
    }

    if(start.seconds == null && end.seconds != null) {
      start.seconds = 0;
    }
    if(start.seconds != null && end.seconds == null) {
      end.seconds = 59;
    }
  }

  /**
   * A simplified representation of a date.
   * Used as a bag-o-properties when performing caluclations
   */
  protected static class Date {
    public Integer year = null;
    public Integer month = null;
    public Integer day = null;
    public Integer hours = null;
    public Integer minutes = null;
    public Integer seconds = null;

    public Date() {}

    public Date(GedcomxDateSimple simple, boolean adjustTimezone) {
      year = simple.getYear();
      month = simple.getMonth();
      day = simple.getDay();
      hours = simple.getHours();
      minutes = simple.getMinutes();
      seconds = simple.getSeconds();

      if(adjustTimezone) {
        if(hours != null && simple.getTzHours() != null) {
          hours += simple.getTzHours();
        }

        if(minutes != null && simple.getTzMinutes() != null) {
          minutes += simple.getTzMinutes();
        }
      }
    }
  }

  /**
   * Converts a java.util.Date to a GedcomxDate object.  The returned GedcomxDate
   * will always be represented as UTC since java.util.Date does not
   * have locale information and is defaulted to represent UTC.
   * @param javaDate The java.util.Date object to convert.
   * @return a GedcomxDate representing the CE javaDate.
   */
  public static GedcomxDateSimple javaDateToGedcomxDateSimple(java.util.Date javaDate) {
    String formattedDate = Optional.ofNullable(javaDate)
            .map(date -> date.toInstant().truncatedTo(ChronoUnit.SECONDS))
            .map(DateTimeFormatter.ISO_INSTANT::format)
            .map(formattedString -> formattedString.startsWith("-") ? formattedString : "+" + formattedString)
            .orElseThrow(() -> new GedcomxDateException("javaDate cannot be null"));
    return new GedcomxDateSimple(formattedDate);
  }

  /**
   * Converts two java.util.Date objects to a GedcomxDateRange object.  If toDate
   * is null then the GedcomxDateRange will be an open-ended range.  The fromDate
   * and toDate will both be UTC since that is what a java.util.Date object represents.
   * @param fromDate the start of the range.  This cannot be null.
   * @param toDate the end of the range.  This may be null.
   * @return a GedcomxDateRange that represents the range between the fromDate and toDate
   * @throws GedcomxDateException if fromDate is null.
   */
  public static GedcomxDateRange javaDatesToGedcomxDateRange(java.util.Date fromDate, java.util.Date toDate) {
    String fromDateFormalString = Optional.ofNullable(fromDate)
            .map(GedcomxDateUtil::javaDateToGedcomxDateSimple)
            .map(GedcomxDate::toFormalString)
            .orElseThrow(() -> new GedcomxDateException("fromDate cannot be null"));
    String toDateFormalString = Optional.ofNullable(toDate)
            .map(GedcomxDateUtil::javaDateToGedcomxDateSimple)
            .map(GedcomxDate::toFormalString)
            .orElse("");
    return new GedcomxDateRange(fromDateFormalString+"/"+toDateFormalString);
  }
}
