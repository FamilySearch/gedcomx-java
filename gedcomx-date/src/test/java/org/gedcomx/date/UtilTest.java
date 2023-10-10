package org.gedcomx.date;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.time.temporal.ChronoUnit;
import java.util.Date;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.fail;

/**
 * @author John Clark.
 */
public class UtilTest {

  /**
   * Parse
   */

  @Test
  public void errorOnParseNullDate() {
    try {
      GedcomxDateUtil.parse(null);
      fail("GedcomxDateException expected because date is null");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Date");
    }
  }

  @Test
  public void errorOnParseInvalidDate() {
    try {
      GedcomxDateUtil.parse("");
      fail("GedcomxDateException expected because date is null");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Date");
    }
  }

  @Test
  public void shouldParseRecurring() {
    GedcomxDate date = GedcomxDateUtil.parse("R3/+1000/P1Y");

    assertThat(date).isInstanceOf(GedcomxDateRecurring.class);
    assertThat(date.getType()).isEqualTo(GedcomxDateType.RECURRING);
  }

  @Test
  public void shouldParseRange() {
    GedcomxDate date = GedcomxDateUtil.parse("A+1000/P1Y");

    assertThat(date).isInstanceOf(GedcomxDateRange.class);
    assertThat(date.getType()).isEqualTo(GedcomxDateType.RANGE);
  }

  @Test
  public void shouldParseSingleDayRange() {
    GedcomxDate date = GedcomxDateUtil.parse("+1934-08-31/+1934-09-01");

    assertThat(date).isInstanceOf(GedcomxDateRange.class);
    assertThat(date.getType()).isEqualTo(GedcomxDateType.RANGE);
    assertThat(((GedcomxDateRange)date).getDuration().getDays()).isEqualTo(1);
  }

  @Test
  public void shouldParseApproximate() {
    GedcomxDate date = GedcomxDateUtil.parse("A+1000");

    assertThat(date).isInstanceOf(GedcomxDateApproximate.class);
    assertThat(date.getType()).isEqualTo(GedcomxDateType.APPROXIMATE);
  }

  @Test
  public void shouldParseSimple() {
    GedcomxDate date = GedcomxDateUtil.parse("+1000");

    assertThat(date).isInstanceOf(GedcomxDateSimple.class);
    assertThat(date.getType()).isEqualTo(GedcomxDateType.SIMPLE);
  }

  @Test
  public void secondPartOfRangeLosesGranularityForMonth(){
    GedcomxDate date = GedcomxDateUtil.parse("+1788-07/+1788");
    assertThat(date).isInstanceOf(GedcomxDateRange.class);
    assertThat(date.getType()).isEqualTo(GedcomxDateType.RANGE);
    assertThat(date.toFormalString()).isEqualTo("+1788-07/P5M");
  }

  @Test
  public void secondPartOfRangeLosesGranularityForDay(){
    GedcomxDate date = GedcomxDateUtil.parse("+1788-07-07/+1788-07");
    assertThat(date).isInstanceOf(GedcomxDateRange.class);
    assertThat(date.getType()).isEqualTo(GedcomxDateType.RANGE);
    assertThat(date.toFormalString()).isEqualTo("+1788-07-07/P24D");
  }

  @Test
  public void secondPartOfRangeLosesGranularityForMonthAndDay(){
    GedcomxDate date = GedcomxDateUtil.parse("+1788-07-07/+1788");
    assertThat(date).isInstanceOf(GedcomxDateRange.class);
    assertThat(date.getType()).isEqualTo(GedcomxDateType.RANGE);
    assertThat(date.toFormalString()).isEqualTo("+1788-07-07/P5M24D");
  }

  /**
   * getDuration
   */

  @Test
  public void errorOnInvalidStart() {
    try {
      GedcomxDateUtil.getDuration(null, new GedcomxDateSimple("+1000"));
      fail("GedcomxDateException expected because start is null");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Start and End must be simple dates");
    }
  }

  @Test
  public void errorOnInvalidEnd() {
    try {
      GedcomxDateUtil.getDuration(new GedcomxDateSimple("+1000"), null);
      fail("GedcomxDateException expected because end is null");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Start and End must be simple dates");
    }
  }

  @Test
  public void shouldOverflowSeconds() {
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
  public void shouldOverflowMinutes() {
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
  public void shouldOverflowHours() {
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
  public void shouldOverflowDays() {
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
  public void shouldOverflowMonths() {
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
  public void shouldOverflowYears() {
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
  public void errorOnStartGreaterThanEnd() {
    try {
      GedcomxDateUtil.getDuration(
              new GedcomxDateSimple("+1000-01-01T00:00:00"),
              new GedcomxDateSimple("+0999-01-01T00:00:00"));
      fail("GedcomxDateException expected because start > end");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Start Date=+1000-01-01T00:00:00-06:00 must be less than End Date=+0999-01-01T00:00:00-06:00");
    }
  }

  @Test
  public void errorOnStartGreaterOrEqualToEnd() {
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
  public void shouldZipStart() {
    GedcomxDateDuration duration = GedcomxDateUtil.getDuration(
            new GedcomxDateSimple("+0999-01-01T00:00:00Z"),
            new GedcomxDateSimple("+1000"));

    assertThat(duration.getYears()).isEqualTo(1);
    assertThat(duration.getMonths()).isEqualTo(11);
    assertThat(duration.getDays()).isEqualTo(30);
    assertThat(duration.getHours()).isEqualTo(23);
    assertThat(duration.getMinutes()).isEqualTo(59);
    assertThat(duration.getSeconds()).isEqualTo(59);
  }

  @Test
  public void shouldZipEnd() {
    GedcomxDateDuration duration = GedcomxDateUtil.getDuration(
            new GedcomxDateSimple("+0999"),
            new GedcomxDateSimple("+1000-01-01T00:00:00Z"));

    assertThat(duration.getYears()).isEqualTo(1);
    assertThat(duration.getMonths()).isEqualTo(null);
    assertThat(duration.getDays()).isEqualTo(null);
    assertThat(duration.getHours()).isEqualTo(null);
    assertThat(duration.getMinutes()).isEqualTo(null);
    assertThat(duration.getSeconds()).isEqualTo(null);
  }

  @Test
  public void shouldCalcTrickyDuration() {
    GedcomxDateDuration duration = GedcomxDateUtil.getDuration(
            new GedcomxDateSimple("+1970-01-31"),
            new GedcomxDateSimple("+1973-02-01"));

    assertThat(duration.getYears()).isEqualTo(3);
    assertThat(duration.getMonths()).isEqualTo(null);
    assertThat(duration.getDays()).isEqualTo(1);
    assertThat(duration.getHours()).isEqualTo(null);
    assertThat(duration.getMinutes()).isEqualTo(null);
    assertThat(duration.getSeconds()).isEqualTo(null);
  }

  @Test
  public void shouldCalcSimpleDuration() {
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
  public void errorOnInvalidAddDurationStartDate() {
    try {
      GedcomxDateUtil.addDuration(null, new GedcomxDateDuration("P1Y"));
      fail("GedcomxDateException expected because start date is null");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Start Date");
    }
  }

  @Test
  public void errorOnInvalidAddDurationDuration() {
    try {
      GedcomxDateUtil.addDuration(new GedcomxDateSimple("+1000"), null);
      fail("GedcomxDateException expected because duration is null");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Duration");
    }
  }

  @Test
  public void shouldPassThroughNegativeTZ() {
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
  public void shouldPassThroughPositiveTZ() {
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
  public void shouldAddSeconds() {
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
  public void shouldAddMinutes() {
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
  public void shouldAddHours() {
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
  public void shouldAddDays() {
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
  public void shouldAddYearsWorthOfDays() {
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
  public void shouldAddMonths() {
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
    public void shouldAddYears() {
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
  public void shouldAddNegativeYears() {
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
  public void errorOnToManyYears() {
    try {
      GedcomxDateSimple simple = GedcomxDateUtil.addDuration(
              new GedcomxDateSimple("+9999"),
              new GedcomxDateDuration("P1Y"));
      fail("GedcomxDateException expected because years exceed 9999");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("New date out of range");
    }
  }

  /**
   * multipleDuration
   */

  @Test
  public void errorOnInvalidDuration() {
    try {
      GedcomxDateUtil.multiplyDuration(null, 1);
      fail("GedcomxDateException expected because duration is null");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Duration");
    }
  }

  @Test
  public void errorOnInvalidMultiplier() {
    try {
      GedcomxDateUtil.multiplyDuration(new GedcomxDateDuration("P100Y"), 0);
      fail("GedcomxDateException expected because multiplier is 0");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Multiplier");
    }
  }

  @Test
  public void successOnValidDurations() {

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
  public void errorOnInvalidMonth() {
    try {
      GedcomxDateUtil.daysInMonth(13, 2000);
      fail("GedcomxDateException expected because 13 is not a month");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Unknown Month");
    }
  }

  @Test
  public void successOnValidStuff() {

    assertThat(GedcomxDateUtil.daysInMonth(1, 2001)).isEqualTo(31);
    assertThat(GedcomxDateUtil.daysInMonth(2, 2001)).isEqualTo(28);
    assertThat(GedcomxDateUtil.daysInMonth(3, 2001)).isEqualTo(31);
    assertThat(GedcomxDateUtil.daysInMonth(4, 2001)).isEqualTo(30);
    assertThat(GedcomxDateUtil.daysInMonth(5, 2001)).isEqualTo(31);
    assertThat(GedcomxDateUtil.daysInMonth(6, 2001)).isEqualTo(30);
    assertThat(GedcomxDateUtil.daysInMonth(7, 2001)).isEqualTo(31);
    assertThat(GedcomxDateUtil.daysInMonth(8, 2001)).isEqualTo(31);
    assertThat(GedcomxDateUtil.daysInMonth(9, 2001)).isEqualTo(30);
    assertThat(GedcomxDateUtil.daysInMonth(10, 2001)).isEqualTo(31);
    assertThat(GedcomxDateUtil.daysInMonth(11, 2001)).isEqualTo(30);
    assertThat(GedcomxDateUtil.daysInMonth(12, 2001)).isEqualTo(31);

    // Test each branch of the leapyear calculation
    assertThat(GedcomxDateUtil.daysInMonth(2, 2003)).isEqualTo(28);
    assertThat(GedcomxDateUtil.daysInMonth(2, 2004)).isEqualTo(29);
    assertThat(GedcomxDateUtil.daysInMonth(2, 1900)).isEqualTo(28);
    assertThat(GedcomxDateUtil.daysInMonth(2, 2000)).isEqualTo(29);
  }

  @Test
  public void successOnZipStartMonth() {
    GedcomxDateUtil.Date start = new GedcomxDateUtil.Date();
    GedcomxDateUtil.Date end = new GedcomxDateUtil.Date();
    start.month = 5;

    GedcomxDateUtil.zipDates(start, end);

    assertThat(end.month).isEqualTo(12);
  }

  @Test
  public void successOnZipEndMonth() {
    GedcomxDateUtil.Date start = new GedcomxDateUtil.Date();
    GedcomxDateUtil.Date end = new GedcomxDateUtil.Date();
    end.month = 5;

    GedcomxDateUtil.zipDates(start, end);

    assertThat(start.month).isEqualTo(1);
  }

  @Test
  public void successOnZipStartDay() {
    GedcomxDateUtil.Date start = new GedcomxDateUtil.Date();
    GedcomxDateUtil.Date end = new GedcomxDateUtil.Date();
    start.day = 5;

    GedcomxDateUtil.zipDates(start, end);

    assertThat(end.day).isEqualTo(5);
  }

  @Test
  public void successOnZipEndDay() {
    GedcomxDateUtil.Date start = new GedcomxDateUtil.Date();
    GedcomxDateUtil.Date end = new GedcomxDateUtil.Date();
    end.day = 5;

    GedcomxDateUtil.zipDates(start, end);

    assertThat(start.day).isEqualTo(1);
  }

  @Test
  public void successOnZipStartHour() {
    GedcomxDateUtil.Date start = new GedcomxDateUtil.Date();
    GedcomxDateUtil.Date end = new GedcomxDateUtil.Date();
    start.hours = 5;

    GedcomxDateUtil.zipDates(start, end);

    assertThat(end.hours).isEqualTo(23);
  }

  @Test
  public void successOnZipEndHour() {
    GedcomxDateUtil.Date start = new GedcomxDateUtil.Date();
    GedcomxDateUtil.Date end = new GedcomxDateUtil.Date();
    end.hours = 5;

    GedcomxDateUtil.zipDates(start, end);

    assertThat(start.hours).isEqualTo(0);
  }

  @Test
  public void successOnZipStartMinute() {
    GedcomxDateUtil.Date start = new GedcomxDateUtil.Date();
    GedcomxDateUtil.Date end = new GedcomxDateUtil.Date();
    start.minutes = 5;

    GedcomxDateUtil.zipDates(start, end);

    assertThat(end.minutes).isEqualTo(59);
  }

  @Test
  public void successOnZipEndMinute() {
    GedcomxDateUtil.Date start = new GedcomxDateUtil.Date();
    GedcomxDateUtil.Date end = new GedcomxDateUtil.Date();
    end.minutes = 5;

    GedcomxDateUtil.zipDates(start, end);

    assertThat(start.minutes).isEqualTo(0);
  }

  @Test
  public void successOnZipStartSecond() {
    GedcomxDateUtil.Date start = new GedcomxDateUtil.Date();
    GedcomxDateUtil.Date end = new GedcomxDateUtil.Date();
    start.seconds = 5;

    GedcomxDateUtil.zipDates(start, end);

    assertThat(end.seconds).isEqualTo(59);
  }

  @Test
  public void successOnZipEndSecond() {
    GedcomxDateUtil.Date start = new GedcomxDateUtil.Date();
    GedcomxDateUtil.Date end = new GedcomxDateUtil.Date();
    end.seconds = 5;

    GedcomxDateUtil.zipDates(start, end);

    assertThat(start.seconds).isEqualTo(0);
  }

  /**
   * zipDuration
   */

  @Test
  public void successOnZipDurationSeconds() {
    GedcomxDateUtil.Date date = new GedcomxDateUtil.Date();
    date.year = 2000;
    GedcomxDateDuration duration = new GedcomxDateDuration("PT1S");

    GedcomxDateUtil.zipDuration(date, duration);

    assertThat(date.seconds).isEqualTo(0);
    assertThat(date.minutes).isEqualTo(0);
    assertThat(date.hours).isEqualTo(0);
    assertThat(date.day).isEqualTo(1);
    assertThat(date.month).isEqualTo(1);
    assertThat(date.year).isEqualTo(2000);
  }

  @Test
  public void successOnZipDurationMinutes() {
    GedcomxDateUtil.Date date = new GedcomxDateUtil.Date();
    date.year = 2000;
    GedcomxDateDuration duration = new GedcomxDateDuration("PT1M");

    GedcomxDateUtil.zipDuration(date, duration);

    assertThat(date.seconds).isEqualTo(null);
    assertThat(date.minutes).isEqualTo(0);
    assertThat(date.hours).isEqualTo(0);
    assertThat(date.day).isEqualTo(1);
    assertThat(date.month).isEqualTo(1);
    assertThat(date.year).isEqualTo(2000);
  }

  @Test
  public void successOnZipDurationHours() {
    GedcomxDateUtil.Date date = new GedcomxDateUtil.Date();
    date.year = 2000;
    GedcomxDateDuration duration = new GedcomxDateDuration("PT1H");

    GedcomxDateUtil.zipDuration(date, duration);

    assertThat(date.seconds).isEqualTo(null);
    assertThat(date.minutes).isEqualTo(null);
    assertThat(date.hours).isEqualTo(0);
    assertThat(date.day).isEqualTo(1);
    assertThat(date.month).isEqualTo(1);
    assertThat(date.year).isEqualTo(2000);
  }

  @Test
  public void successOnZipDurationDays() {
    GedcomxDateUtil.Date date = new GedcomxDateUtil.Date();
    date.year = 2000;
    GedcomxDateDuration duration = new GedcomxDateDuration("P1D");

    GedcomxDateUtil.zipDuration(date, duration);

    assertThat(date.seconds).isEqualTo(null);
    assertThat(date.minutes).isEqualTo(null);
    assertThat(date.hours).isEqualTo(null);
    assertThat(date.day).isEqualTo(1);
    assertThat(date.month).isEqualTo(1);
    assertThat(date.year).isEqualTo(2000);
  }

  @Test
  public void successOnZipDurationMonths() {
    GedcomxDateUtil.Date date = new GedcomxDateUtil.Date();
    date.year = 2000;
    GedcomxDateDuration duration = new GedcomxDateDuration("P1M");

    GedcomxDateUtil.zipDuration(date, duration);

    assertThat(date.seconds).isEqualTo(null);
    assertThat(date.minutes).isEqualTo(null);
    assertThat(date.hours).isEqualTo(null);
    assertThat(date.day).isEqualTo(null);
    assertThat(date.month).isEqualTo(1);
    assertThat(date.year).isEqualTo(2000);
  }

  @Test
  public void successOnZipDurationNothing() {
    GedcomxDateUtil.Date date = new GedcomxDateUtil.Date();
    date.year = 2000;
    GedcomxDateDuration duration = new GedcomxDateDuration("P1Y");

    GedcomxDateUtil.zipDuration(date, duration);

    assertThat(date.seconds).isEqualTo(null);
    assertThat(date.minutes).isEqualTo(null);
    assertThat(date.hours).isEqualTo(null);
    assertThat(date.day).isEqualTo(null);
    assertThat(date.month).isEqualTo(null);
    assertThat(date.year).isEqualTo(2000);
  }


  @Test
  public void testJavaDateToGedcomxDateSimpleEpoch() {
    Date javaEpochDate = new Date(0L);
    GedcomxDateSimple gedcomxDate = GedcomxDateUtil.javaDateToGedcomxDateSimple(javaEpochDate);
    Assertions.assertThat(gedcomxDate).isNotNull();
    Assertions.assertThat(gedcomxDate.toFormalString()).isEqualTo("+1970-01-01T00:00:00Z");
  }

  @Test
  public void testJavaDateToGedcomxDateSimpleJustAfterEpoch() {
    Date javaJustAfterEpochDate = new Date(1L);
    GedcomxDateSimple gedcomxDate = GedcomxDateUtil.javaDateToGedcomxDateSimple(javaJustAfterEpochDate);
    Assertions.assertThat(gedcomxDate).isNotNull();
    Assertions.assertThat(gedcomxDate.toFormalString()).isEqualTo("+1970-01-01T00:00:00Z");
  }

  @Test
  public void testJavaDateToGedcomxDateSimpleJustBeforeEpoch() {
    Date javaJustBeforeEpochDate = new Date(-1L);
    GedcomxDateSimple gedcomxDate = GedcomxDateUtil.javaDateToGedcomxDateSimple(javaJustBeforeEpochDate);
    Assertions.assertThat(gedcomxDate).isNotNull();
    Assertions.assertThat(gedcomxDate.toFormalString()).isEqualTo("+1969-12-31T23:59:59Z");
  }

  @Test
  public void testJavaDateToGedcomxDateSimpleBCEDate() {
    Date javaBCEDate = new Date(-62451363600000L);
    GedcomxDateSimple gedcomxDate = GedcomxDateUtil.javaDateToGedcomxDateSimple(javaBCEDate);
    Assertions.assertThat(gedcomxDate).isNotNull();
    Assertions.assertThat(gedcomxDate.toFormalString()).isEqualTo("-0010-12-30T07:00:00Z");
  }

  @Test
  public void testJavaDateToGedcomxDateSimpleNullDate() {
    Assertions.assertThatExceptionOfType(GedcomxDateException.class)
            .isThrownBy(() -> GedcomxDateUtil.javaDateToGedcomxDateSimple(null))
            .withMessage("javaDate cannot be null");
  }

  @Test
  public void testJavaDatesToGedcomxDateRange() {
    Date javaEpochDate = new Date(0L);
    Date javaEpochDatePlusOne = Date.from(javaEpochDate.toInstant().plus(1, ChronoUnit.DAYS));
    GedcomxDateRange gedcomxDate = GedcomxDateUtil.javaDatesToGedcomxDateRange(javaEpochDate, javaEpochDatePlusOne);
    Assertions.assertThat(gedcomxDate).isNotNull();
    Assertions.assertThat(gedcomxDate.toFormalString()).isEqualTo("+1970-01-01T00:00:00Z/P1D");
  }

  @Test
  public void testJavaDatesToGedcomxDateRangeNoFromDate() {
    Date javaEpochDate = new Date(0L);
    Assertions.assertThatExceptionOfType(GedcomxDateException.class)
            .isThrownBy(() -> GedcomxDateUtil.javaDatesToGedcomxDateRange(null, javaEpochDate))
            .withMessage("fromDate cannot be null");
  }

  @Test
  public void testJavaDatesToGedcomxDateRangeNoToDate() {
    Date javaEpochDate = new Date(0L);
    GedcomxDateRange gedcomxDate = GedcomxDateUtil.javaDatesToGedcomxDateRange(javaEpochDate, null);
    Assertions.assertThat(gedcomxDate).isNotNull();
    Assertions.assertThat(gedcomxDate.toFormalString()).isEqualTo("+1970-01-01T00:00:00Z/");
  }

  @Test
  public void testParsingZeroYear() {
    String yearZero = "+0000";
    Assertions.assertThatNoException().isThrownBy(() -> GedcomxDateUtil.parse(yearZero));
  }
}
