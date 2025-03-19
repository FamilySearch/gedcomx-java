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

import java.time.*;
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
      throw new GedcomxDateException("Invalid Date \"" + date + "\"");
    }

    GedcomxDate retVal;
    // This order is critical because the date types are not mutually exclusive
    if(date.charAt(0) == 'R') {
      retVal = new GedcomxDateRecurring(date);
    } else if(date.contains("/")) {
      retVal = new GedcomxDateRange(date);
    } else if(date.charAt(0) == 'A') {
      retVal = new GedcomxDateApproximate(date);
    } else {
      retVal = new GedcomxDateSimple(date);
    }
    return retVal;
  }

  /**
   * Calculates the Duration between two dates
   * @param startDate The start date
   * @param endDate The end date
   * @return The duration
   */
  public static GedcomxDateDuration getDuration(GedcomxDateSimple startDate, GedcomxDateSimple endDate) {

    if (startDate == null || endDate == null) {
      throw new GedcomxDateException("Start and End must be simple dates");
    }

    LocalDateTime start = getMinLocalDateTime(startDate);
    LocalDateTime end = getMaxLocalDateTime(endDate);

    Period p = Period.between(start.toLocalDate(), end.toLocalDate());
    Duration d = Duration.between(start.toLocalTime(), end.toLocalTime());

    // If the time is negative, we need to roll up the day that we date from the date portion
    // if the date is zero or negative, the exception below will be triggered.
    if (d.isNegative()) {
      p = p.minusDays(1);
      d = d.plusDays(1);
    }

    if(p.isNegative() || (p.isZero() && d.isNegative())) {
      throw new GedcomxDateException(String.format("Start Date=%s must be less than End Date=%s", startDate.toFormalString(), endDate.toFormalString()));
    }

    String finalDuration = createDurationStringFromJavaTimeParts(p, d);

    if("P".equals(finalDuration)) {
      throw new GedcomxDateException("The start and end are equal yielding no duration.");
    }

    return new GedcomxDateDuration(finalDuration);

  }

  private static String createDurationStringFromJavaTimeParts(Period p, Duration d) {
    StringBuilder duration = new StringBuilder("P");
    if(!p.isZero()){
      if (p.getYears() > 0) {
        duration.append(p.getYears()).append('Y');
      }
      if (p.getMonths() > 0) {
        duration.append(p.getMonths()).append('M');
      }
      if (p.getDays() > 0) {
        duration.append(p.getDays()).append('D');
      }
    }
    if(!d.isZero()) {
      duration.append('T');
      if (d.toHours() > 0) {
        duration.append(d.toHours()).append('H');
      }
      if (d.toMinutesPart() > 0) {
        duration.append(d.toMinutesPart()).append('M');
      }
      if (d.toSecondsPart() > 0) {
        duration.append(d.toSecondsPart()).append('S');
      }
    }
    return duration.toString();
  }

  private static LocalDateTime getMinLocalDateTime(GedcomxDateSimple startDate) {
    return LocalDateTime.of(
      isNull(startDate.getYear()) ? -9999 : startDate.getYear(),
      isNull(startDate.getMonth()) ? 1 : startDate.getMonth(),
      isNull(startDate.getDay()) ? 1 : startDate.getDay(),
      isNull(startDate.getHours()) ? 0 : startDate.getHours(),
      isNull(startDate.getMinutes()) ? 0 : startDate.getMinutes(),
      isNull(startDate.getSeconds()) ? 0 : startDate.getSeconds());
  }

  private static LocalDateTime getMaxLocalDateTime(GedcomxDateSimple endDate) {

    // pull these out to calculate the day as needed.
    int endYear = isNull(endDate.getYear()) ? 9999 : endDate.getYear();
    int endMonth = isNull(endDate.getMonth()) ? 12 : endDate.getMonth();

    LocalDateTime end = LocalDateTime.of(
      endYear,
      endMonth,
      isNull(endDate.getDay()) ? YearMonth.of(endYear, endMonth).lengthOfMonth() : endDate.getDay(),
      isNull(endDate.getHours()) ? 23 : endDate.getHours(),
      isNull(endDate.getMinutes()) ? 59 : endDate.getMinutes(),
      isNull(endDate.getSeconds()) ? 59 : endDate.getSeconds());

    // Add one second to the last second to trigger a roll up across the entire end time
    // to cause the durations to come out as P1Y instead of P11M30D23H59M59S. (It is off by one second, but looks much better
    // and preserves the previous behavior.)
    //
    if(end.getSecond()==59){
      end = end.plusSeconds(1);
    }
    return end;
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
    LocalDateTime endLocalDateTime = getMinLocalDateTime(startDate)
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
   * @deprecated this method is just a pass through to java.time.YearMonth.lengthOfMonth
   */
  @Deprecated(forRemoval = true)
  public static int daysInMonth(Integer month, Integer year) {
    try {
      return YearMonth.of(year, month).lengthOfMonth();
    } catch (DateTimeException e) {
      throw new GedcomxDateException(e.getMessage(), e);
    }
  }

  /**
   * Converts a java.util.Date to a GedcomxDate object.  The returned GedcomxDate
   * will always be represented in UTC since java.util.Date does not
   * have locale information.
   *
   * <p>The earliest representable date is January 1, 10000 BCE. (-9999)
   * The latest representable date is December 31, 9999 CE.
   * See <a href="https://github.com/FamilySearch/gedcomx/blob/master/specifications/date-format-specification.md#calendaring-system">GEDCOM X Date Format Specification Calendaring System</a></a>
   *
   * @param javaDate The java.util.Date object to convert.
   * @return a GedcomxDate representing the javaDate.
   */
  public static GedcomxDateSimple javaDateToGedcomxDateSimple(java.util.Date javaDate) {
    Optional.ofNullable(javaDate)
        .orElseThrow(() -> new GedcomxDateException("javaDate cannot be null"));

    Optional<Instant> instant = Optional.of(javaDate).map(date -> date.toInstant().truncatedTo(ChronoUnit.SECONDS));
    instant.filter(instantDate -> {
          int year = instantDate.atOffset(ZoneOffset.UTC).getYear();
          return year >= -9999 && year <= 9999;
        })
        .orElseThrow(() -> new GedcomxDateException("Year must be between -9999 and +9999 inclusive"));

    String formattedDate = instant
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
