package org.gedcomx.date;

import org.testng.annotations.Test;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.fail;

/**
 * @author John Clark.
 */
public class SimpleTest {

  @Test
   public void errorOnBlankString() {
    try {
      new GedcomxDateSimple("");
      fail("GedcomxDateException expected because date must not be empty");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Date: Must have at least [+-]YYYY");
    }
  }

  @Test
  public void errorOnMissingPlusMinus() {
    try {
      new GedcomxDateSimple("2000-03-01");
      fail("GedcomxDateException expected because missing plus");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Date: Must begin with + or -");
    }
  }

  @Test
  public void errorOnMalformedYear() {
    try {
      new GedcomxDateSimple("+1ooo");
      fail("GedcomxDateException expected because o != 0");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Date: Malformed Year");
    }
  }

  @Test
  public void successOnPositiveYear() {
    GedcomxDateSimple date = new GedcomxDateSimple("+1000");
    assertThat(date.getYear()).isEqualTo(1000);
  }

  @Test
  public void successOnNegativeYear() {
    GedcomxDateSimple date = new GedcomxDateSimple("-0010");
    assertThat(date.getYear()).isEqualTo(-10);
  }

  @Test
  public void successOnNegativeYearZero() {
    GedcomxDateSimple date = new GedcomxDateSimple("-0000");
    assertThat(date.getYear()).isEqualTo(0);
  }

  @Test
  public void successOnYearHour() {
    GedcomxDateSimple date = new GedcomxDateSimple("+1000T10");
    assertThat(date.getYear()).isEqualTo(1000);
    assertThat(date.getHours()).isEqualTo(null);
  }

  @Test
  public void errorOnInvalidYearMonthSeparator() {
    try {
      new GedcomxDateSimple("+1000_10");
      fail("GedcomxDateException expected because _ is not the year-month separator");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Date: Invalid Year-Month Separator");
    }
  }

  @Test
  public void errorOnOneDigitMonth() {
    try {
      new GedcomxDateSimple("+1000-1");
      fail("GedcomxDateException expected because month must be 2 digits");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Date: Month must be 2 digits");
    }
  }

  @Test
  public void errorOnInvalidMonth() {
    try {
      new GedcomxDateSimple("+1000-o1-01");
      fail("GedcomxDateException expected because o != 0");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Date: Malformed Month");
    }
  }

  @Test
  public void errorOnMonth0() {
    try {
      new GedcomxDateSimple("+1000-00");
      fail("GedcomxDateException expected because there is no month 0");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Date: Month must be between 1 and 12");
    }
  }

  @Test
  public void errorOnMonth13() {
    try {
      new GedcomxDateSimple("+1000-13");
      fail("GedcomxDateException expected because there are only 12 months");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Date: Month must be between 1 and 12");
    }
  }

  @Test
  public void successOnYearMonth() {
    GedcomxDateSimple date = new GedcomxDateSimple("+0987-04");
    assertThat(date.getYear()).isEqualTo(987);
    assertThat(date.getMonth()).isEqualTo(4);
    assertThat(date.getDay()).isEqualTo(null);
  }

  @Test
  public void errorOnInvalidMonthDaySeparator() {
    try {
      new GedcomxDateSimple("+1000-10=01");
      fail("GedcomxDateException expected because = is not the month-day separator");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Date: Invalid Month-Day Separator");
    }
  }

  @Test
  public void errorOnOneDigitDay() {
    try {
      new GedcomxDateSimple("+1000-10-1");
      fail("GedcomxDateException expected because day must be 2 digits");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Date: Day must be 2 digits");
    }
  }

  @Test
  public void errorOnInvalidDay() {
    try {
      new GedcomxDateSimple("+1000-01-o1");
      fail("GedcomxDateException expected because o != 0");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Date: Malformed Day");
    }
  }

  @Test
  public void errorOnDay0() {
    try {
      new GedcomxDateSimple("+1000-10-00");
      fail("GedcomxDateException expected because there is no day 0");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Date: Day 0 does not exist");
    }
  }

  @Test
  public void errorOnDay31InFeb() {
    try {
      new GedcomxDateSimple("+1000-04-31");
      fail("GedcomxDateException expected because there is no day 31 in february");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Date: There are only 30 days in Month 4 year 1000");
    }
  }

  @Test
  public void successOnLeapYear() {
    GedcomxDateSimple date = new GedcomxDateSimple("+1600-02-29");
    assertThat(date.getYear()).isEqualTo(1600);
    assertThat(date.getMonth()).isEqualTo(2);
    assertThat(date.getDay()).isEqualTo(29);
    assertThat(date.getHours()).isEqualTo(null);
  }

  @Test
  public void errorOn() {
    try {
      new GedcomxDateSimple("+1492-03-1501:02:03");
      fail("GedcomxDateException expected because T required before time");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("Invalid Date: +YYYY-MM-DD must have T before time");
    }
  }

/*
  @Test
  public void errorOn() {
    try {
      new GedcomxDateSimple("");
      fail("GedcomxDateException expected because ");
    } catch(GedcomxDateException e) {
      assertThat(e.getMessage()).isEqualTo("");
    }
  }

  @Test
  public void successOn() {
    GedcomxDateSimple date = new GedcomxDateSimple("");
    assertThat(date.getYear()).isEqualTo(null);
  }
*/

}
