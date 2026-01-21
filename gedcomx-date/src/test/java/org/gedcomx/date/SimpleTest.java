package org.gedcomx.date;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.fail;

/**
 * @author John Clark.
 */
class SimpleTest {

  @Test
  void errorOnBlankString() {
    try {
      new GedcomxDateSimple("");
      fail("GedcomxDateException expected because date must not be empty");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Date: Must have at least [+-]YYYY");
    }
  }

  /**
   * YEAR
   */

  @Test
  void errorOnMissingPlusMinus() {
    try {
      new GedcomxDateSimple("2000-03-01");
      fail("GedcomxDateException expected because missing plus");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Date: Must begin with + or -");
    }
  }

  @Test
  void errorOnMalformedYear() {
    try {
      new GedcomxDateSimple("+1ooo");
      fail("GedcomxDateException expected because o != 0");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Date: Malformed Year");
    }
  }

  @Test
  void successOnPositiveYear() {
    GedcomxDateSimple date = new GedcomxDateSimple("+1000");
    assertThat(date.getYear()).isEqualTo(1000);
  }

  @Test
  void successOnNegativeYear() {
    GedcomxDateSimple date = new GedcomxDateSimple("-0010");
    assertThat(date.getYear()).isEqualTo(-10);
  }

  @Test
  void successOnPositiveYearZero() {
    GedcomxDateSimple date = new GedcomxDateSimple("+0000");
    assertThat(date.getYear())
            .as("The Gedcomx Date specification requires that the year before 1 CE or 1 AD is represented as 0")
            .isEqualTo(0);
  }

  @Test
  void successOnNegativeYearZero() {
    GedcomxDateSimple date = new GedcomxDateSimple("-0000");
    assertThat(date.getYear())
            .as("The Gedcomx Date specification requires that the year before 1 CE or 1 AD is represented as 0")
            .isEqualTo(0);
  }

  @Test
  void successOnYearHour() {
    GedcomxDateSimple date = new GedcomxDateSimple("+1000T10");
    assertThat(date.getYear()).isEqualTo(1000);
    assertThat(date.getHours()).isEqualTo(10);
  }

  @Test
  void errorOnInvalidYearMonthSeparator() {
    try {
      new GedcomxDateSimple("+1000_10");
      fail("GedcomxDateException expected because _ is not the year-month separator");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Date: Invalid Year-Month Separator");
    }
  }

  /**
   * MONTH
   */

  @Test
  void errorOnOneDigitMonth() {
    try {
      new GedcomxDateSimple("+1000-1");
      fail("GedcomxDateException expected because month must be 2 digits");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Date: Month must be 2 digits");
    }
  }

  @Test
  void errorOnInvalidMonth() {
    try {
      new GedcomxDateSimple("+1000-o1-01");
      fail("GedcomxDateException expected because o != 0");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Date: Malformed Month");
    }
  }

  @Test
  void errorOnMonth0() {
    try {
      new GedcomxDateSimple("+1000-00");
      fail("GedcomxDateException expected because there is no month 0");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Date: Month must be between 1 and 12");
    }
  }

  @Test
  void errorOnMonth13() {
    try {
      new GedcomxDateSimple("+1000-13");
      fail("GedcomxDateException expected because there are only 12 months");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Date: Month must be between 1 and 12");
    }
  }

  @Test
  void successOnYearMonth() {
    GedcomxDateSimple date = new GedcomxDateSimple("+0987-04");
    assertThat(date.getYear()).isEqualTo(987);
    assertThat(date.getMonth()).isEqualTo(4);
    assertThat(date.getDay()).isEqualTo(null);
  }

  @Test
  void successOnYearMonthHour() {
    GedcomxDateSimple date = new GedcomxDateSimple("+1000-10T10");
    assertThat(date.getYear()).isEqualTo(1000);
    assertThat(date.getMonth()).isEqualTo(10);
    assertThat(date.getDay()).isEqualTo(null);
    assertThat(date.getHours()).isEqualTo(10);
  }

  @Test
  void errorOnInvalidMonthDaySeparator() {
    try {
      new GedcomxDateSimple("+1000-10=01");
      fail("GedcomxDateException expected because = is not the month-day separator");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Date: Invalid Month-Day Separator");
    }
  }

  /**
   * DAY
   */

  @Test
  void errorOnOneDigitDay() {
    try {
      new GedcomxDateSimple("+1000-10-1");
      fail("GedcomxDateException expected because day must be 2 digits");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Date: Day must be 2 digits");
    }
  }

  @Test
  void errorOnInvalidDay() {
    try {
      new GedcomxDateSimple("+1000-01-o1");
      fail("GedcomxDateException expected because o != 0");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Date: Malformed Day");
    }
  }

  @Test
  void errorOnDay0() {
    try {
      new GedcomxDateSimple("+1000-10-00");
      fail("GedcomxDateException expected because there is no day 0");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Date: Day 0 does not exist");
    }
  }

  @Test
  void errorOnDay31InFeb() {
    try {
      new GedcomxDateSimple("+1000-04-31");
      fail("GedcomxDateException expected because there is no day 31 in february");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Date: There are only 30 days in Month 4 year 1000");
    }
  }

  @Test
  void successOnLeapYear() {
    GedcomxDateSimple date = new GedcomxDateSimple("+1600-02-29");
    assertThat(date.getYear()).isEqualTo(1600);
    assertThat(date.getMonth()).isEqualTo(2);
    assertThat(date.getDay()).isEqualTo(29);
    assertThat(date.getHours()).isEqualTo(null);
  }

  @Test
  void errorOnMissingT() {
    try {
      new GedcomxDateSimple("+1492-03-1501:02:03");
      fail("GedcomxDateException expected because T required before time");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Date: +YYYY-MM-DD must have T before time");
    }
  }

  /**
   * HOURS
   */

  @Test
  void errorOnOneDigitHour() {
    try {
      new GedcomxDateSimple("+1000-10-11T1");
      fail("GedcomxDateException expected because hour must be 2 digits");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Date: Hours must be 2 digits");
    }
  }

  @Test
  void errorOnInvalidHour() {
    try {
      new GedcomxDateSimple("+1000-01-11T1o");
      fail("GedcomxDateException expected because o != 0");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Date: Malformed Hours");
    }
  }

  @Test
  void errorOnHour25() {
    try {
      new GedcomxDateSimple("+1000-10-01T25");
      fail("GedcomxDateException expected because there is no hour 25");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Date: Hours must be between 0 and 24");
    }
  }

  @Test
  void successOnHour24() {
    GedcomxDateSimple date = new GedcomxDateSimple("+0987-01-25T24Z");
    assertThat(date.getYear()).isEqualTo(987);
    assertThat(date.getMonth()).isEqualTo(1);
    assertThat(date.getDay()).isEqualTo(25);
    assertThat(date.getHours()).isEqualTo(24);
    assertThat(date.getMinutes()).isEqualTo(null);
    assertThat(date.getSeconds()).isEqualTo(null);
    assertThat(date.getTzHours()).isEqualTo(0);
    assertThat(date.getTzMinutes()).isEqualTo(0);
  }

  @Test
  void errorOnInvalidHourMinuteSeparator() {
    try {
      new GedcomxDateSimple("+1000-10-01T10|30");
      fail("GedcomxDateException expected because | is not the hour-minute separator");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Date: Invalid Hour-Minute Separator");
    }
  }

  /**
   * MINUTES
   */

  @Test
  void errorOnOneDigitMinute() {
    try {
      new GedcomxDateSimple("+1000-10-11T10:1");
      fail("GedcomxDateException expected because minutes must be 2 digits");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Date: Minutes must be 2 digits");
    }
  }

  @Test
  void errorOnInvalidMinute() {
    try {
      new GedcomxDateSimple("+1000-01-11T10:1o");
      fail("GedcomxDateException expected because o != 0");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Date: Malformed Minutes");
    }
  }

  @Test
  void errorOnMinute60() {
    try {
      new GedcomxDateSimple("+1000-10-01T23:60");
      fail("GedcomxDateException expected because there is no minute 60");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Date: Minutes must be between 0 and 59");
    }
  }

  @Test
  void errorOnHour24MinuteNot0() {
    try {
      new GedcomxDateSimple("+1000-10-01T24:15");
      fail("GedcomxDateException expected because there is no minute 15 when hour is 24");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Date: Hours of 24 requires 00 Minutes");
    }
  }

  @Test
  void successOnHour23_59() {
    GedcomxDateSimple date = new GedcomxDateSimple("+0987-01-25T23:59");
    assertThat(date.getYear()).isEqualTo(987);
    assertThat(date.getMonth()).isEqualTo(1);
    assertThat(date.getDay()).isEqualTo(25);
    assertThat(date.getHours()).isEqualTo(23);
    assertThat(date.getMinutes()).isEqualTo(59);
    assertThat(date.getSeconds()).isEqualTo(null);
  }

  @Test
  void successOnHour23_59Z() {
    GedcomxDateSimple date = new GedcomxDateSimple("+0987-01-25T23:59Z");
    assertThat(date.getYear()).isEqualTo(987);
    assertThat(date.getMonth()).isEqualTo(1);
    assertThat(date.getDay()).isEqualTo(25);
    assertThat(date.getHours()).isEqualTo(23);
    assertThat(date.getMinutes()).isEqualTo(59);
    assertThat(date.getSeconds()).isEqualTo(null);
    assertThat(date.getTzHours()).isEqualTo(0);
    assertThat(date.getTzMinutes()).isEqualTo(0);
  }

  @Test
  void errorOnInvalidMinuteSecondSeparator() {
    try {
      new GedcomxDateSimple("+1000-10-01T10:30|15");
      fail("GedcomxDateException expected because | is not the minute-second separator");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Date: Invalid Minute-Second Separator");
    }
  }

  /**
   * SECONDS
   */

  @Test
  void errorOnOneDigitSecond() {
    try {
      new GedcomxDateSimple("+1000-10-11T10:15:1");
      fail("GedcomxDateException expected because seconds must be 2 digits");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Date: Seconds must be 2 digits");
    }
  }

  @Test
  void errorOnInvalidSecond() {
    try {
      new GedcomxDateSimple("+1000-01-11T10:15:1o");
      fail("GedcomxDateException expected because o != 0");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Date: Malformed Seconds");
    }
  }

  @Test
  void errorOnSecond60() {
    try {
      new GedcomxDateSimple("+1000-10-01T23:24:60");
      fail("GedcomxDateException expected because there is no second 60");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Date: Seconds must be between 0 and 59");
    }
  }

  @Test
  void errorOnHour24SecondNot0() {
    try {
      new GedcomxDateSimple("+1000-10-01T24:00:01");
      fail("GedcomxDateException expected because there is no second 1 when hour is 24");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Date: Hours of 24 requires 00 Seconds");
    }
  }

  @Test
  void successOnHour23_59_59() {
    GedcomxDateSimple date = new GedcomxDateSimple("+0987-01-25T23:59:59");
    assertThat(date.getYear()).isEqualTo(987);
    assertThat(date.getMonth()).isEqualTo(1);
    assertThat(date.getDay()).isEqualTo(25);
    assertThat(date.getHours()).isEqualTo(23);
    assertThat(date.getMinutes()).isEqualTo(59);
    assertThat(date.getSeconds()).isEqualTo(59);
  }

  @Test
  void successOnHour23_59_59Z() {
    GedcomxDateSimple date = new GedcomxDateSimple("+0987-01-25T23:59:59Z");
    assertThat(date.getYear()).isEqualTo(987);
    assertThat(date.getMonth()).isEqualTo(1);
    assertThat(date.getDay()).isEqualTo(25);
    assertThat(date.getHours()).isEqualTo(23);
    assertThat(date.getMinutes()).isEqualTo(59);
    assertThat(date.getSeconds()).isEqualTo(59);
    assertThat(date.getTzHours()).isEqualTo(0);
    assertThat(date.getTzMinutes()).isEqualTo(0);
  }

  /**
   * TZHOURS
   */

  @Test
  void successOnZ() {
    GedcomxDateSimple date = new GedcomxDateSimple("+1000-01-01T23:15Z");
    assertThat(date.getYear()).isEqualTo(1000);
    assertThat(date.getMonth()).isEqualTo(1);
    assertThat(date.getDay()).isEqualTo(1);
    assertThat(date.getHours()).isEqualTo(23);
    assertThat(date.getMinutes()).isEqualTo(15);
    assertThat(date.getSeconds()).isEqualTo(null);
    assertThat(date.getTzHours()).isEqualTo(0);
    assertThat(date.getTzMinutes()).isEqualTo(0);
  }

  @Test
  void errorOnExtraCharsAfterZ() {
    try {
      new GedcomxDateSimple("+1000-10-01T24:00:00ZSTUFF");
      fail("GedcomxDateException expected because there is no STUFF allowed");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Date: Malformed Timezone - No Characters allowed after Z");
    }
  }

  @Test
  void errorOnMissingTZHours() {
    try {
      new GedcomxDateSimple("+1000-10-01T24:00:00-1");
      fail("GedcomxDateException expected because tzHours must be 2 digits");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Date: Malformed Timezone - tzHours must be [+-] followed by 2 digits");
    }
  }

  @Test
  void errorOnInvalidTZHoursPrefix() {
    try {
      new GedcomxDateSimple("+1000-10-01T24:00:00_10");
      fail("GedcomxDateException expected because tzHours must start with + or -");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Date: TimeZone Hours must begin with + or -");
    }
  }

  @Test
  void errorOnInvalidTZHours() {
    try {
      new GedcomxDateSimple("+1000-01-11T10:15:10+1o");
      fail("GedcomxDateException expected because o != 0");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Date: Malformed tzHours");
    }
  }

  @Test
  void successOnPositiveTZHours() {
    GedcomxDateSimple date = new GedcomxDateSimple("+1000-01-01T23:15+15");
    assertThat(date.getYear()).isEqualTo(1000);
    assertThat(date.getMonth()).isEqualTo(1);
    assertThat(date.getDay()).isEqualTo(1);
    assertThat(date.getHours()).isEqualTo(23);
    assertThat(date.getMinutes()).isEqualTo(15);
    assertThat(date.getSeconds()).isEqualTo(null);
    assertThat(date.getTzHours()).isEqualTo(15);
    assertThat(date.getTzMinutes()).isEqualTo(0);
  }

  @Test
  void successOnNegativeTZHours() {
    GedcomxDateSimple date = new GedcomxDateSimple("+1000-01-01T23:15-02");
    assertThat(date.getYear()).isEqualTo(1000);
    assertThat(date.getMonth()).isEqualTo(1);
    assertThat(date.getDay()).isEqualTo(1);
    assertThat(date.getHours()).isEqualTo(23);
    assertThat(date.getMinutes()).isEqualTo(15);
    assertThat(date.getSeconds()).isEqualTo(null);
    assertThat(date.getTzHours()).isEqualTo(-2);
    assertThat(date.getTzMinutes()).isEqualTo(0);
  }

  @Test
  void successOnZeroTZHours() {
    GedcomxDateSimple date = new GedcomxDateSimple("+1000-01-01T23:15-00");
    assertThat(date.getYear()).isEqualTo(1000);
    assertThat(date.getMonth()).isEqualTo(1);
    assertThat(date.getDay()).isEqualTo(1);
    assertThat(date.getHours()).isEqualTo(23);
    assertThat(date.getMinutes()).isEqualTo(15);
    assertThat(date.getSeconds()).isEqualTo(null);
    assertThat(date.getTzHours()).isEqualTo(0);
    assertThat(date.getTzMinutes()).isEqualTo(0);
  }

  @Test
  void errorOnInvalidTZHourTZMinuteSeparator() {
    try {
      new GedcomxDateSimple("+1000-10-01T10:30:15-06-30");
      fail("GedcomxDateException expected because | is not the tzHour-tzMinute separator");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Date: Invalid tzHour-tzMinute Separator");
    }
  }

  /**
   * TZMINUTES
   */
  @Test
  void errorOnMissingTZMinutes() {
    try {
      new GedcomxDateSimple("+1000-10-01T24:00:00-10:1");
      fail("GedcomxDateException expected because tzHours must be 2 digits");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Date: tzSecond must be 2 digits");
    }
  }

  @Test
  void errorOnInvalidTZMinutes() {
    try {
      new GedcomxDateSimple("+1000-01-11T10:15:10+10:1o");
      fail("GedcomxDateException expected because o != 0");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Date: Malformed tzMinutes");
    }
  }

  @Test
  void errorOnExtraCharsAfterTZSeconds() {
    try {
      new GedcomxDateSimple("+1000-01-11T10:15:10+10:10blah");
      fail("GedcomxDateException expected because nothing is allowed after tzSeconds");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Date: Malformed Timezone - No characters allowed after tzSeconds");
    }
  }

  @Test
  void successOnTZMinutes() {
    GedcomxDateSimple date = new GedcomxDateSimple("+1000-01-01T23:15-00:30");
    assertThat(date.getYear()).isEqualTo(1000);
    assertThat(date.getMonth()).isEqualTo(1);
    assertThat(date.getDay()).isEqualTo(1);
    assertThat(date.getHours()).isEqualTo(23);
    assertThat(date.getMinutes()).isEqualTo(15);
    assertThat(date.getSeconds()).isEqualTo(null);
    assertThat(date.getTzHours()).isEqualTo(0);
    assertThat(date.getTzMinutes()).isEqualTo(30);
  }


  /**
   * Other Methods
   */

  @Test
  void getType() {
    GedcomxDateSimple simple = new GedcomxDateSimple("+1000");
    assertThat(simple.getType()).isEqualTo(GedcomxDateType.SIMPLE);
  }

  @Test
  void isApproximate() {
    GedcomxDateSimple simple = new GedcomxDateSimple("+1000");
    assertThat(simple.isApproximate()).isEqualTo(false);
  }

  @Test
  void toFormalString() {
    List<String> tests = Arrays.asList("+1000-01-01T24:00:00Z","-1000-01-01T23:15:15-06:30","+0001-12","-0090");
    for(String test: tests) {
      GedcomxDateSimple simple = new GedcomxDateSimple(test);
      assertThat(simple.toFormalString()).isEqualTo(test);
    }
  }

  @Test
  void compareTo() {
    GedcomxDateSimple before = new GedcomxDateSimple("+1000-01-01T10:00:00Z");
    GedcomxDateSimple after = new GedcomxDateSimple("+1000-01-02T10:00:00Z");
    org.assertj.core.api.Assertions.assertThat(before.compareTo(after))
            .isLessThan(0);
    org.assertj.core.api.Assertions.assertThat(after.compareTo(before))
            .isGreaterThan(0);
    org.assertj.core.api.Assertions.assertThat(before.compareTo(before))
            .isEqualTo(0);
  }

  @Test
  void compareToPartialDateYear() {
    GedcomxDateSimple before = new GedcomxDateSimple("+1000");
    GedcomxDateSimple after = new GedcomxDateSimple("+1001");
    org.assertj.core.api.Assertions.assertThat(before.compareTo(after))
            .isLessThan(0);
    org.assertj.core.api.Assertions.assertThat(after.compareTo(before))
            .isGreaterThan(0);
    org.assertj.core.api.Assertions.assertThat(before.compareTo(before))
            .isEqualTo(0);
  }

  @Test
  void compareToPartialDateYearMonth() {
    GedcomxDateSimple before = new GedcomxDateSimple("+1000-01");
    GedcomxDateSimple after = new GedcomxDateSimple("+1000-02");
    org.assertj.core.api.Assertions.assertThat(before.compareTo(after))
            .isLessThan(0);
    org.assertj.core.api.Assertions.assertThat(after.compareTo(before))
            .isGreaterThan(0);
    org.assertj.core.api.Assertions.assertThat(before.compareTo(before))
            .isEqualTo(0);
  }

  @Test
  void compareToPartialDateYearMonthDay() {
    GedcomxDateSimple before = new GedcomxDateSimple("+1000-01-01");
    GedcomxDateSimple after = new GedcomxDateSimple("+1000-01-02");
    org.assertj.core.api.Assertions.assertThat(before.compareTo(after))
            .isLessThan(0);
    org.assertj.core.api.Assertions.assertThat(after.compareTo(before))
            .isGreaterThan(0);
    org.assertj.core.api.Assertions.assertThat(before.compareTo(before))
            .isEqualTo(0);
  }

  @Test
  void compareToPartialDateYearMonthDayHour() {
    GedcomxDateSimple before = new GedcomxDateSimple("+1000-01-01T08");
    GedcomxDateSimple after = new GedcomxDateSimple("+1000-01-01T09");
    org.assertj.core.api.Assertions.assertThat(before.compareTo(after))
            .isLessThan(0);
    org.assertj.core.api.Assertions.assertThat(after.compareTo(before))
            .isGreaterThan(0);
    org.assertj.core.api.Assertions.assertThat(before.compareTo(before))
            .isEqualTo(0);
  }

  @Test
  void compareToPartialDateYearMonthDayHourMinute() {
    GedcomxDateSimple before = new GedcomxDateSimple("+1000-01-01T08:10");
    GedcomxDateSimple after = new GedcomxDateSimple("+1000-01-01T08:12");
    org.assertj.core.api.Assertions.assertThat(before.compareTo(after))
            .isLessThan(0);
    org.assertj.core.api.Assertions.assertThat(after.compareTo(before))
            .isGreaterThan(0);
    org.assertj.core.api.Assertions.assertThat(before.compareTo(before))
            .isEqualTo(0);
  }

  @Test
  void compareToPartialDateYearMonthDayHourMinuteSeconds() {
    GedcomxDateSimple before = new GedcomxDateSimple("+1000-01-01T08:10:00");
    GedcomxDateSimple after = new GedcomxDateSimple("+1000-01-01T08:10:01");
    org.assertj.core.api.Assertions.assertThat(before.compareTo(after))
            .isLessThan(0);
    org.assertj.core.api.Assertions.assertThat(after.compareTo(before))
            .isGreaterThan(0);
    org.assertj.core.api.Assertions.assertThat(before.compareTo(before))
            .isEqualTo(0);
  }

  @Test
  void compareToPartialDateYearMonthDayHourMinuteSecondsTzHours() {
    GedcomxDateSimple before = new GedcomxDateSimple("+1000-01-01T08:10:00+00");
    GedcomxDateSimple after = new GedcomxDateSimple("+1000-01-01T08:09:01-01");
    org.assertj.core.api.Assertions.assertThat(before.compareTo(after))
            .isLessThan(0);
    org.assertj.core.api.Assertions.assertThat(after.compareTo(before))
            .isGreaterThan(0);
    org.assertj.core.api.Assertions.assertThat(before.compareTo(before))
            .isEqualTo(0);
  }

  @Test
  void compareToPartialDateYearMonthDayHourMinuteSecondsTzHoursTzMinutes() {
    GedcomxDateSimple before = new GedcomxDateSimple("+1000-01-01T08:10:00-01:00");
    GedcomxDateSimple after = new GedcomxDateSimple("+1000-01-01T08:09:31-01:30");
    org.assertj.core.api.Assertions.assertThat(before.compareTo(after))
            .isLessThan(0);
    org.assertj.core.api.Assertions.assertThat(after.compareTo(before))
            .isGreaterThan(0);
    org.assertj.core.api.Assertions.assertThat(before.compareTo(before))
            .isEqualTo(0);
  }

  @Test
  void compareToBCEYear() {
    GedcomxDateSimple before = new GedcomxDateSimple("-1001");
    GedcomxDateSimple after = new GedcomxDateSimple("-1000");
    org.assertj.core.api.Assertions.assertThat(before.compareTo(after))
            .isLessThan(0);
    org.assertj.core.api.Assertions.assertThat(after.compareTo(before))
            .isGreaterThan(0);
    org.assertj.core.api.Assertions.assertThat(before.compareTo(before))
            .isEqualTo(0);
  }

  @Test
  void compareToApproximateYear() {
    GedcomxDateSimple before = new GedcomxDateSimple("+1000");
    GedcomxDateApproximate after = new GedcomxDateApproximate("A+1001");
    org.assertj.core.api.Assertions.assertThat(before.compareTo(after))
            .isLessThan(0);
    org.assertj.core.api.Assertions.assertThat(after.compareTo(before))
            .isGreaterThan(0);
    org.assertj.core.api.Assertions.assertThat(before.compareTo(before))
            .isEqualTo(0);
  }

  @Test
  void baseClassCompareToApproximateYear() {
    GedcomxDate before = GedcomxDateUtil.parse("+1000");
    GedcomxDate after = GedcomxDateUtil.parse("A+1001");
    org.assertj.core.api.Assertions.assertThat(before.compareTo(after))
            .isLessThan(0);
    org.assertj.core.api.Assertions.assertThat(after.compareTo(before))
            .isGreaterThan(0);
    org.assertj.core.api.Assertions.assertThat(before.compareTo(before))
            .isEqualTo(0);
  }

  @Test
  void baseClassCompareToClassCastException() {
    GedcomxDate before = GedcomxDateUtil.parse("+1000");
    GedcomxDate after = GedcomxDateUtil.parse("A+1001/+1002");
    org.assertj.core.api.Assertions.assertThatExceptionOfType(ClassCastException.class)
            .isThrownBy(() -> before.compareTo(after))
            .withMessage("other is not an instance of either GedcomxDateSimple or GedcomxDateApproximate");
  }

  @Test
  void baseClassCompareToNullPointerException() {
    GedcomxDate before = GedcomxDateUtil.parse("+1000");
    org.assertj.core.api.Assertions.assertThatExceptionOfType(NullPointerException.class)
            .isThrownBy(() -> before.compareTo(null));
  }

  @Test
  void baseClassCompareToSingleDigitYear() {
    GedcomxDate before = GedcomxDateUtil.parse("-0001");
    GedcomxDate after = GedcomxDateUtil.parse("+0001");
    org.assertj.core.api.Assertions.assertThat(before.compareTo(after))
            .isLessThan(0);
    org.assertj.core.api.Assertions.assertThat(after.compareTo(before))
            .isGreaterThan(0);
    org.assertj.core.api.Assertions.assertThat(before.compareTo(before))
            .isEqualTo(0);
  }
}
