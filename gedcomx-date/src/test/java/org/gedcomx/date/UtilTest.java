package org.gedcomx.date;

import java.time.*;
import org.assertj.core.api.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author John Clark.
 */
class UtilTest {

  /**
   * Parse
   */

  @Test
  void errorOnParseNullDate() {
    var e = assertThrows(GedcomxDateException.class, ()->GedcomxDateUtil.parse(null));
    assertThat(e.getMessage()).isEqualTo("Invalid Date \"null\"");
  }

  @Test
  void errorOnParseInvalidDate() {
    var e = assertThrows(GedcomxDateException.class, ()->GedcomxDateUtil.parse(""));
    assertThat(e.getMessage()).isEqualTo("Invalid Date \"\"");
  }

  @Test
  void shouldParseRecurring() {
    GedcomxDate date = GedcomxDateUtil.parse("R3/+1000/P1Y");

    assertThat(date).isInstanceOf(GedcomxDateRecurring.class);
    assertThat(date.getType()).isEqualTo(GedcomxDateType.RECURRING);
  }

  @Test
  void shouldParseRange() {
    GedcomxDate date = GedcomxDateUtil.parse("A+1000/P1Y");

    assertThat(date).isInstanceOf(GedcomxDateRange.class);
    assertThat(date.getType()).isEqualTo(GedcomxDateType.RANGE);
  }

  @Test
  void shouldParseSingleDayRange() {
    GedcomxDate date = GedcomxDateUtil.parse("+1934-08-31/+1934-09-01");

    assertThat(date).isInstanceOf(GedcomxDateRange.class);
    assertThat(date.getType()).isEqualTo(GedcomxDateType.RANGE);
    // All of the 31st and all of the 1st
    assertThat(((GedcomxDateRange)date).getDuration().getDays()).isEqualTo(2);
  }

  @Test
  void shouldParseApproximate() {
    GedcomxDate date = GedcomxDateUtil.parse("A+1000");

    assertThat(date).isInstanceOf(GedcomxDateApproximate.class);
    assertThat(date.getType()).isEqualTo(GedcomxDateType.APPROXIMATE);
  }

  @Test
  void shouldParseSimple() {
    GedcomxDate date = GedcomxDateUtil.parse("+1000");

    assertThat(date).isInstanceOf(GedcomxDateSimple.class);
    assertThat(date.getType()).isEqualTo(GedcomxDateType.SIMPLE);
  }

  static Stream<Arguments> dropGranularityForMonth() {
    return Stream.of(
      // All of July->All of December = 6M
      Arguments.of("+1788-07/P6M", "+1788-07/+1788"),
      // July 22th->July 31st = 10D counting the 22nd and the 31st
      Arguments.of("+1788-07-22/P10D", "+1788-07-22/+1788-07"),
      // July 22th 1788 -> All of December = 10D counting the 22nd and the 31st and the 5 remaining months
      Arguments.of("+1788-07-22/P5M10D", "+1788-07-22/+1788"));
  }

  @ParameterizedTest
  @MethodSource
  void dropGranularityForMonth(String expected, String dateStr) {
    GedcomxDate date = GedcomxDateUtil.parse(dateStr);
    assertThat(date).isInstanceOf(GedcomxDateRange.class);
    assertThat(date.getType()).isEqualTo(GedcomxDateType.RANGE);
    assertThat(date.toFormalString()).isEqualTo(expected);
  }

  /**
   * getDuration
   */

  @Test
  void errorOnInvalidStart() {
    var date = new GedcomxDateSimple("+1000");
    var e = assertThrows(GedcomxDateException.class, ()->GedcomxDateUtil.getDuration(null, date));
    assertThat(e.getMessage()).isEqualTo("Start and End must be simple dates");
  }

  @Test
  void errorOnInvalidEnd() {
    var date = new GedcomxDateSimple("+1000");
    var e = assertThrows(GedcomxDateException.class, ()->GedcomxDateUtil.getDuration(date, null));
    assertThat(e.getMessage()).isEqualTo("Start and End must be simple dates");
  }

  @Test
  void shouldOverflowSeconds() {
    GedcomxDateDuration duration = GedcomxDateUtil.getDuration(
            new GedcomxDateSimple("+0999-12-31T23:59:59"),
            new GedcomxDateSimple("+1000-01-01T00:00:00"));

    assertThat(duration.getYears()).isEqualTo(null);
    assertThat(duration.getMonths()).isEqualTo(null);
    assertThat(duration.getDays()).isEqualTo(null);
    assertThat(duration.getHours()).isEqualTo(null);
    assertThat(duration.getMinutes()).isEqualTo(null);
    assertThat(duration.getSeconds()).isEqualTo(1);
  }

  @Test
  void shouldOverflowMinutes() {
    GedcomxDateDuration duration = GedcomxDateUtil.getDuration(
            new GedcomxDateSimple("+0999-12-31T23:59:00"),
            new GedcomxDateSimple("+1000-01-01T00:00:00"));

    assertThat(duration.getYears()).isEqualTo(null);
    assertThat(duration.getMonths()).isEqualTo(null);
    assertThat(duration.getDays()).isEqualTo(null);
    assertThat(duration.getHours()).isEqualTo(null);
    assertThat(duration.getMinutes()).isEqualTo(1);
    assertThat(duration.getSeconds()).isEqualTo(null);
  }

  @Test
  void shouldOverflowHours() {
    GedcomxDateDuration duration = GedcomxDateUtil.getDuration(
            new GedcomxDateSimple("+0999-12-31T23:00:00"),
            new GedcomxDateSimple("+1000-01-01T00:00:00"));

    assertThat(duration.getYears()).isEqualTo(null);
    assertThat(duration.getMonths()).isEqualTo(null);
    assertThat(duration.getDays()).isEqualTo(null);
    assertThat(duration.getHours()).isEqualTo(1);
    assertThat(duration.getMinutes()).isEqualTo(null);
    assertThat(duration.getSeconds()).isEqualTo(null);
  }

  @Test
  void shouldOverflowDays() {
    GedcomxDateDuration duration = GedcomxDateUtil.getDuration(
            new GedcomxDateSimple("+0999-12-31T00:00:00"),
            new GedcomxDateSimple("+1000-01-01T00:00:00"));

    assertThat(duration.getYears()).isEqualTo(null);
    assertThat(duration.getMonths()).isEqualTo(null);
    assertThat(duration.getDays()).isEqualTo(1);
    assertThat(duration.getHours()).isEqualTo(null);
    assertThat(duration.getMinutes()).isEqualTo(null);
    assertThat(duration.getSeconds()).isEqualTo(null);
  }

  @Test
  void shouldOverflowMonths() {
    GedcomxDateDuration duration = GedcomxDateUtil.getDuration(
            new GedcomxDateSimple("+0999-12-01T00:00:00"),
            new GedcomxDateSimple("+1000-01-01T00:00:00"));

    assertThat(duration.getYears()).isEqualTo(null);
    assertThat(duration.getMonths()).isEqualTo(1);
    assertThat(duration.getDays()).isEqualTo(null);
    assertThat(duration.getHours()).isEqualTo(null);
    assertThat(duration.getMinutes()).isEqualTo(null);
    assertThat(duration.getSeconds()).isEqualTo(null);
  }

  @Test
  void shouldOverflowYears() {
    GedcomxDateDuration duration = GedcomxDateUtil.getDuration(
            new GedcomxDateSimple("+0999-01-01T00:00:00"),
            new GedcomxDateSimple("+1000-01-01T00:00:00"));

    assertThat(duration.getYears()).isEqualTo(1);
    assertThat(duration.getMonths()).isEqualTo(null);
    assertThat(duration.getDays()).isEqualTo(null);
    assertThat(duration.getHours()).isEqualTo(null);
    assertThat(duration.getMinutes()).isEqualTo(null);
    assertThat(duration.getSeconds()).isEqualTo(null);
  }

  @Test
  void errorOnStartGreaterThanEnd() {
    try {
      GedcomxDateUtil.getDuration(
              new GedcomxDateSimple("+1000-01-01T00:00:00"),
              new GedcomxDateSimple("+0999-01-01T00:00:00"));
      fail("GedcomxDateException expected because start > end");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).contains("must be less than End Date");
    }
  }

  @Test
  void errorOnStartGreaterOrEqualToEnd() {
    try {
      GedcomxDateUtil.getDuration(
              new GedcomxDateSimple("+1900-07-03"),
              new GedcomxDateSimple("+1899-05-10"));
      fail("GedcomxDateException expected because start > end");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Start Date=+1900-07-03 must be less than End Date=+1899-05-10");
    }
  }

  @Test
  void shouldZipStart() {
    GedcomxDateDuration duration = GedcomxDateUtil.getDuration(
            new GedcomxDateSimple("+0999-01-01T00:00:00Z"),
            new GedcomxDateSimple("+1000"));

    // this is now consistent with the formalString being 2Y
    assertEquals(2,duration.getYears());
    assertNull(duration.getMonths());
    assertNull(duration.getDays());
    assertNull(duration.getHours());
    assertNull(duration.getMinutes());
    assertNull(duration.getSeconds());
  }

  @Test
  void shouldZipEnd() {
    GedcomxDateDuration duration = GedcomxDateUtil.getDuration(
            new GedcomxDateSimple("+0999"),
            new GedcomxDateSimple("+1000-01-01T00:00:00Z"));

    // this is now consistent with the formalString being 1Y
    assertEquals(1, duration.getYears());
    assertNull(duration.getMonths());
    assertNull(duration.getDays());
    assertNull(duration.getHours());
    assertNull(duration.getMinutes());
    assertNull(duration.getSeconds());
  }

  @Test
  void shouldCalcTrickyDuration() {
    GedcomxDateDuration duration = GedcomxDateUtil.getDuration(
            new GedcomxDateSimple("+1970-01-31"),
            new GedcomxDateSimple("+1973-02-01"));

    assertThat(duration.getYears()).isEqualTo(3);
    assertThat(duration.getMonths()).isEqualTo(null);
    // All of the 31st and all of the 1st.
    assertThat(duration.getDays()).isEqualTo(2);
    assertThat(duration.getHours()).isEqualTo(null);
    assertThat(duration.getMinutes()).isEqualTo(null);
    assertThat(duration.getSeconds()).isEqualTo(null);
  }

  @Test
  void shouldCalcSimpleDuration() {
    GedcomxDate date = GedcomxDateUtil.parse("+1970-01-31/P01D");
    assertThat(date.getType()).isEqualTo(GedcomxDateType.RANGE);

    GedcomxDateDuration duration = ((GedcomxDateRange)date).getDuration();

    assertThat(duration.getYears()).isEqualTo(null);
    assertThat(duration.getMonths()).isEqualTo(null);
    assertThat(duration.getDays()).isEqualTo(1);
    assertThat(duration.getHours()).isEqualTo(null);
    assertThat(duration.getMinutes()).isEqualTo(null);
    assertThat(duration.getSeconds()).isEqualTo(null);

    GedcomxDateSimple endDate = ((GedcomxDateRange)date).getEnd();
    assertThat(endDate.getYear()).isEqualTo(1970);
    assertThat(endDate.getMonth()).isEqualTo(2);
    assertThat(endDate.getDay()).isEqualTo(1);
  }

  /**
   * addDuration
   */

  @Test
  void errorOnInvalidAddDurationStartDate() {
    var duration = new GedcomxDateDuration("P1Y");
    var e= assertThrows(GedcomxDateException.class, ()->GedcomxDateUtil.addDuration(null, duration));
    assertThat(e.getMessage()).isEqualTo("Invalid Start Date");
  }

  @Test
  void errorOnInvalidAddDurationDuration() {
    var date = new GedcomxDateSimple("+1000");
    var e = assertThrows(GedcomxDateException.class, ()->GedcomxDateUtil.addDuration(date, null));
    assertThat(e.getMessage()).isEqualTo("Invalid Duration");
  }

  @Test
  void shouldPassThroughNegativeTZ() {
    GedcomxDateSimple simple = GedcomxDateUtil.addDuration(
            new GedcomxDateSimple("+1000-01-01T12:00:00-10:30"),
            new GedcomxDateDuration("PT90S"));

    assertThat(simple.getYear()).isEqualTo(1000);
    assertThat(simple.getMonth()).isEqualTo(1);
    assertThat(simple.getDay()).isEqualTo(1);
    assertThat(simple.getHours()).isEqualTo(12);
    assertThat(simple.getMinutes()).isEqualTo(1);
    assertThat(simple.getSeconds()).isEqualTo(30);
    assertThat(simple.getTzHours()).isEqualTo(-10);
    assertThat(simple.getTzMinutes()).isEqualTo(30);
  }

  @Test
  void shouldPassThroughPositiveTZ() {
    GedcomxDateSimple simple = GedcomxDateUtil.addDuration(
            new GedcomxDateSimple("+1000-01-01T12:00:00+10:00"),
            new GedcomxDateDuration("PT90S"));

    assertThat(simple.getYear()).isEqualTo(1000);
    assertThat(simple.getMonth()).isEqualTo(1);
    assertThat(simple.getDay()).isEqualTo(1);
    assertThat(simple.getHours()).isEqualTo(12);
    assertThat(simple.getMinutes()).isEqualTo(1);
    assertThat(simple.getSeconds()).isEqualTo(30);
    assertThat(simple.getTzHours()).isEqualTo(10);
    assertThat(simple.getTzMinutes()).isEqualTo(0);
  }

  @Test
  void shouldAddSeconds() {
    GedcomxDateSimple simple = GedcomxDateUtil.addDuration(
            new GedcomxDateSimple("+1000"),
            new GedcomxDateDuration("PT90S"));

    assertThat(simple.getYear()).isEqualTo(1000);
    assertThat(simple.getMonth()).isEqualTo(1);
    assertThat(simple.getDay()).isEqualTo(1);
    assertThat(simple.getHours()).isEqualTo(0);
    assertThat(simple.getMinutes()).isEqualTo(1);
    assertThat(simple.getSeconds()).isEqualTo(30);
  }

  @Test
  void shouldAddMinutes() {
    GedcomxDateSimple simple = GedcomxDateUtil.addDuration(
            new GedcomxDateSimple("+1000"),
            new GedcomxDateDuration("PT90M"));

    assertThat(simple.getYear()).isEqualTo(1000);
    assertThat(simple.getMonth()).isEqualTo(1);
    assertThat(simple.getDay()).isEqualTo(1);
    assertThat(simple.getHours()).isEqualTo(1);
    assertThat(simple.getMinutes()).isEqualTo(30);
    assertThat(simple.getSeconds()).isEqualTo(null);
  }

  @Test
  void shouldAddHours() {
    GedcomxDateSimple simple = GedcomxDateUtil.addDuration(
            new GedcomxDateSimple("+1000"),
            new GedcomxDateDuration("PT50H"));

    assertThat(simple.getYear()).isEqualTo(1000);
    assertThat(simple.getMonth()).isEqualTo(1);
    assertThat(simple.getDay()).isEqualTo(3);
    assertThat(simple.getHours()).isEqualTo(2);
    assertThat(simple.getMinutes()).isEqualTo(null);
    assertThat(simple.getSeconds()).isEqualTo(null);
  }

  @Test
  void shouldAddDays() {
    GedcomxDateSimple simple = GedcomxDateUtil.addDuration(
            new GedcomxDateSimple("+2004"),
            new GedcomxDateDuration("P62D"));

    assertThat(simple.getYear()).isEqualTo(2004);
    assertThat(simple.getMonth()).isEqualTo(3);
    assertThat(simple.getDay()).isEqualTo(3);
    assertThat(simple.getHours()).isEqualTo(null);
    assertThat(simple.getMinutes()).isEqualTo(null);
    assertThat(simple.getSeconds()).isEqualTo(null);
  }

  @Test
  void shouldAddYearsWorthOfDays() {
    GedcomxDateSimple simple = GedcomxDateUtil.addDuration(
            new GedcomxDateSimple("+2001"),
            new GedcomxDateDuration("P366D"));

    assertThat(simple.getYear()).isEqualTo(2002);
    assertThat(simple.getMonth()).isEqualTo(1);
    assertThat(simple.getDay()).isEqualTo(2);
    assertThat(simple.getHours()).isEqualTo(null);
    assertThat(simple.getMinutes()).isEqualTo(null);
    assertThat(simple.getSeconds()).isEqualTo(null);
  }

  @Test
  void shouldAddMonths() {
    GedcomxDateSimple simple = GedcomxDateUtil.addDuration(
            new GedcomxDateSimple("+1000"),
            new GedcomxDateDuration("P25M"));

    assertThat(simple.getYear()).isEqualTo(1002);
    assertThat(simple.getMonth()).isEqualTo(2);
    assertThat(simple.getDay()).isEqualTo(null);
    assertThat(simple.getHours()).isEqualTo(null);
    assertThat(simple.getMinutes()).isEqualTo(null);
    assertThat(simple.getSeconds()).isEqualTo(null);
  }

  @Test
  void shouldAddYears() {
    GedcomxDateSimple simple = GedcomxDateUtil.addDuration(
            new GedcomxDateSimple("-0001"),
            new GedcomxDateDuration("P20Y"));

    assertThat(simple.getYear()).isEqualTo(19);
    assertThat(simple.getMonth()).isEqualTo(null);
    assertThat(simple.getDay()).isEqualTo(null);
    assertThat(simple.getHours()).isEqualTo(null);
    assertThat(simple.getMinutes()).isEqualTo(null);
    assertThat(simple.getSeconds()).isEqualTo(null);
  }

  @Test
  void shouldAddNegativeYears() {
    GedcomxDateSimple simple = GedcomxDateUtil.addDuration(
            new GedcomxDateSimple("-1000"),
            new GedcomxDateDuration("P200Y"));

    assertThat(simple.getYear()).isEqualTo(-800);
    assertThat(simple.getMonth()).isEqualTo(null);
    assertThat(simple.getDay()).isEqualTo(null);
    assertThat(simple.getHours()).isEqualTo(null);
    assertThat(simple.getMinutes()).isEqualTo(null);
    assertThat(simple.getSeconds()).isEqualTo(null);
  }

  @Test
  void errorOnToManyYears() {
    var date = new GedcomxDateSimple("+9999");
    var duration = new GedcomxDateDuration("P1Y");
    var e = assertThrows(GedcomxDateException.class, ()->GedcomxDateUtil.addDuration( date, duration));
    assertThat(e.getMessage()).isEqualTo("New date out of range");
  }

  /**
   * multipleDuration
   */

  @Test
  void errorOnInvalidDuration() {
    var e = assertThrows(GedcomxDateException.class, ()->GedcomxDateUtil.multiplyDuration(null, 1));
    assertThat(e.getMessage()).isEqualTo("Invalid Duration");
  }

  @Test
  void errorOnInvalidMultiplier() {
    var duration = new GedcomxDateDuration("P100Y");
    var e = assertThrows(GedcomxDateException.class, ()->GedcomxDateUtil.multiplyDuration(duration, 0));
    assertThat(e.getMessage()).isEqualTo("Invalid Multiplier");
  }

  @Test
  void successOnValidDurations() {

    GedcomxDateDuration duration;

    duration = GedcomxDateUtil.multiplyDuration(new GedcomxDateDuration("P100Y"), 1);
    assertThat(duration.toFormalString()).isEqualTo("P100Y");

    duration = GedcomxDateUtil.multiplyDuration(new GedcomxDateDuration("P100Y"), 2);
    assertThat(duration.toFormalString()).isEqualTo("P200Y");

    duration = GedcomxDateUtil.multiplyDuration(new GedcomxDateDuration("P100Y2M3DT5H3M12S"), 3);
    assertThat(duration.toFormalString()).isEqualTo("P300Y6M9DT15H9M36S");
  }

  /**
   * daysInMonth
   */

  @Test
  void errorOnInvalidMonth() {
    try {
      GedcomxDateUtil.daysInMonth(13, 2000);
      fail("GedcomxDateException expected because 13 is not a month");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid value for MonthOfYear (valid values 1 - 12): 13");
    }
  }

  @Test
  void javaDateToGedcomxDateSimpleEpoch() {
    Date javaEpochDate = new Date(0L);
    GedcomxDateSimple gedcomxDate = GedcomxDateUtil.javaDateToGedcomxDateSimple(javaEpochDate);
    Assertions.assertThat(gedcomxDate).isNotNull();
    Assertions.assertThat(gedcomxDate.toFormalString()).isEqualTo("+1970-01-01T00:00:00Z");
  }

  @Test
  void javaDateToGedcomxDateSimpleJustAfterEpoch() {
    Date javaJustAfterEpochDate = new Date(1L);
    GedcomxDateSimple gedcomxDate = GedcomxDateUtil.javaDateToGedcomxDateSimple(javaJustAfterEpochDate);
    Assertions.assertThat(gedcomxDate).isNotNull();
    Assertions.assertThat(gedcomxDate.toFormalString()).isEqualTo("+1970-01-01T00:00:00Z");
  }

  @Test
  void javaDateToGedcomxDateSimpleJustBeforeEpoch() {
    Date javaJustBeforeEpochDate = new Date(-1L);
    GedcomxDateSimple gedcomxDate = GedcomxDateUtil.javaDateToGedcomxDateSimple(javaJustBeforeEpochDate);
    Assertions.assertThat(gedcomxDate).isNotNull();
    Assertions.assertThat(gedcomxDate.toFormalString()).isEqualTo("+1969-12-31T23:59:59Z");
  }

  @Test
  void javaDateToGedcomxDateSimpleBCEDate() {
    Date javaBCEDate = new Date(-62451363600000L);
    GedcomxDateSimple gedcomxDate = GedcomxDateUtil.javaDateToGedcomxDateSimple(javaBCEDate);
    Assertions.assertThat(gedcomxDate).isNotNull();
    Assertions.assertThat(gedcomxDate.toFormalString()).isEqualTo("-0010-12-30T07:00:00Z");
  }

  @Test
  void javaDateToGedcomxDateSimpleNullDate() {
    assertThatThrowsExceptionWhenJavaDateToGedcomxDateSimple(null)
            .withMessage("javaDate cannot be null");
  }

  /**
   * The earliest representable date is January 1, 10000 BCE. (-9999)
   * The latest representable date is December 31, 9999 CE.
   * See https://github.com/FamilySearch/gedcomx/blob/master/specifications/date-format-specification.md#calendaring-system
   */
  @Test
  void javaDateToGedcomxDateSimpleEdgeCases() {
    assertThatThrowsExceptionWhenJavaDateToGedcomxDateSimple(javaUtilDateWithYear(-75_000))
        .withMessage("Year must be between -9999 and +9999 inclusive"); // the Toba supervolcano in Indonesia erupted
    assertThatThrowsExceptionWhenJavaDateToGedcomxDateSimple(javaUtilDateWithYear(-10_000))
        .withMessage("Year must be between -9999 and +9999 inclusive");

    Assertions.assertThat(GedcomxDateUtil.javaDateToGedcomxDateSimple(javaUtilDateWithYear(-9999)).toFormalString())
        .isEqualTo("-9999-01-01T00:00:00Z");
    Assertions.assertThat(GedcomxDateUtil.javaDateToGedcomxDateSimple(javaUtilDateWithYear(-1)).toFormalString())
        .isEqualTo("-0001-01-01T00:00:00Z");
    Assertions.assertThat(GedcomxDateUtil.javaDateToGedcomxDateSimple(javaUtilDateWithYear(0)).toFormalString())
        .isEqualTo("+0000-01-01T00:00:00Z");
    Assertions.assertThat(GedcomxDateUtil.javaDateToGedcomxDateSimple(javaUtilDateWithYear(1)).toFormalString())
        .isEqualTo("+0001-01-01T00:00:00Z");
    Assertions.assertThat(GedcomxDateUtil.javaDateToGedcomxDateSimple(javaUtilDateWithYear(9999)).toFormalString())
        .isEqualTo("+9999-01-01T00:00:00Z");

    assertThatThrowsExceptionWhenJavaDateToGedcomxDateSimple(javaUtilDateWithYear(10_000))
        .withMessage("Year must be between -9999 and +9999 inclusive");
    assertThatThrowsExceptionWhenJavaDateToGedcomxDateSimple(javaUtilDateWithYear(802_701))
        .withMessage("Year must be between -9999 and +9999 inclusive"); // H.G. Wells' The Time Machine
  }

  private static Date javaUtilDateWithYear(int year) {
    return Date.from(OffsetDateTime.parse("1970-01-01T00:00:00.0Z").withYear(year).toInstant());
  }

  private static ThrowableAssertAlternative<GedcomxDateException> assertThatThrowsExceptionWhenJavaDateToGedcomxDateSimple(Date javaDate) {
    return Assertions.assertThatExceptionOfType(GedcomxDateException.class)
        .describedAs(String.format("Expected exception when converting javaDate %s to Gedcomx date", javaDate))
        .isThrownBy(() -> GedcomxDateUtil.javaDateToGedcomxDateSimple(javaDate));
  }

  @Test
  void javaDatesToGedcomxDateRange() {
    Date javaEpochDate = new Date(0L);
    Date javaEpochDatePlusOne = Date.from(javaEpochDate.toInstant().plus(1, ChronoUnit.DAYS));
    GedcomxDateRange gedcomxDate = GedcomxDateUtil.javaDatesToGedcomxDateRange(javaEpochDate, javaEpochDatePlusOne);
    Assertions.assertThat(gedcomxDate).isNotNull();
    Assertions.assertThat(gedcomxDate.toFormalString()).isEqualTo("+1970-01-01T00:00:00Z/P1D");
  }

  @Test
  void javaDatesToGedcomxDateRangeNoFromDate() {
    Date javaEpochDate = new Date(0L);
    Assertions.assertThatExceptionOfType(GedcomxDateException.class)
            .isThrownBy(() -> GedcomxDateUtil.javaDatesToGedcomxDateRange(null, javaEpochDate))
            .withMessage("fromDate cannot be null");
  }

  @Test
  void javaDatesToGedcomxDateRangeNoToDate() {
    Date javaEpochDate = new Date(0L);
    GedcomxDateRange gedcomxDate = GedcomxDateUtil.javaDatesToGedcomxDateRange(javaEpochDate, null);
    Assertions.assertThat(gedcomxDate).isNotNull();
    Assertions.assertThat(gedcomxDate.toFormalString()).isEqualTo("+1970-01-01T00:00:00Z/");
  }

  @Test
  void parsingZeroYear() {
    String yearZero = "+0000";
    Assertions.assertThatNoException().isThrownBy(() -> GedcomxDateUtil.parse(yearZero));
  }

  static Stream<Arguments> testEqualStartAndEndDate(){
    return Stream.of(
      Arguments.of("+1000/P1Y", GedcomxDateType.RANGE, "+1000/+1000"),
      Arguments.of("+1000-01-01/P1D", GedcomxDateType.RANGE, "+1000-01-01/+1000-01-01"),
      Arguments.of("+1000-01-01/P1Y", GedcomxDateType.RANGE, "+1000-01-01/+1000"),
      // All of January, and All of Feb 1.
      Arguments.of("+1000/P1M1D", GedcomxDateType.RANGE, "+1000/+1000-02-01"),
      // All of 1000 and all of 1001
      Arguments.of("+1000/P2Y", GedcomxDateType.RANGE, "+1000/+1001"),
      Arguments.of("A+1000/P1Y", GedcomxDateType.RANGE, "A+1000/+1000"),
      Arguments.of("/+1000", GedcomxDateType.RANGE, "/+1000"),
      Arguments.of("+1000/", GedcomxDateType.RANGE, "+1000/")
    );

  }

  @ParameterizedTest
  @MethodSource
  void testEqualStartAndEndDate(String expectString, GedcomxDateType expectedType, String input){
    GedcomxDate result = assertDoesNotThrow(()->GedcomxDateUtil.parse(input));
    assertEquals(expectString, result.toFormalString());
    assertEquals(expectedType, result.getType() );
  }
}
