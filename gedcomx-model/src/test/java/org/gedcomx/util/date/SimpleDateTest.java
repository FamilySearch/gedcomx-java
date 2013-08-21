package org.gedcomx.util.date;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Class for testing the SimpleDate class
 * User: Randy Wilson
 * Date: 8/12/13
 * Time: 9:45 AM
 */
public class SimpleDateTest extends TestCase {

  /**
   * Make sure we can parse GedcomX formal date "simple dates" of the form
   *   (+|-)YYYY[-MM[-DD[Thh[:mm[:ss]][Z|(+\-)hh[:mm]]]]]
   */
  public void testSimpleDate() {
    tryDate("+1820", 1820, null, null, null, null, null, false, null, null);
    tryDate("-0090", -90, null, null, null, null, null, false, null, null);
    tryDate("+1820-12", 1820, 12, null, null, null, null, false, null, null);
    tryDate("+1820-12-31", 1820, 12, 31, null, null, null, false, null, null);
    tryDate("+1820-12-31T12", 1820, 12, 31, 12, null, null, false, null, null);
    tryDate("+1820-12-31T00", 1820, 12, 31, 0, null, null, false, null, null);
    tryDate("+1820-12-31T23", 1820, 12, 31, 23, null, null, false, null, null);
    tryDate("+1820-12-31T23:59", 1820, 12, 31, 23, 59, null, false, null, null);
    tryDate("+1820-12-31T23:59:01", 1820, 12, 31, 23, 59, 1, false, null, null);
    tryDate("+1820-12-31T23:59:01Z", 1820, 12, 31, 23, 59, 1, true, null, null);
    tryDate("+1820-12-31T23:59:01+12", 1820, 12, 31, 23, 59, 1, false, 12, null);
    tryDate("+1820-12-31T23:59:01+12:11", 1820, 12, 31, 23, 59, 1, false, 12, 11);
    tryDate("+1820-12-31T23:59:01+01", 1820, 12, 31, 23, 59, 1, false, 1, null);
    tryDate("+1820-12-31T23:59:01-01:30", 1820, 12, 31, 23, 59, 1, false, -1, 30);
    tryDate("+1820-12-31T23:59+12:11", 1820, 12, 31, 23, 59, null, false, 12, 11);
    tryDate("+1820-12-31T23+12:11", 1820, 12, 31, 23, null, null, false, 12, 11);

    // Make sure invalid dates throw an exception
    failDate("1820"); // forgot the "+"
    failDate("+1820-12-31TZ"); // no time before the time zone.
    failDate("T12Z"); // no year
    failDate("+978-12-31"); // need to 0-pad the year to 4 digits.
    failDate("+1978-3-03"); // need to 0-pad the month to 2 digits
    failDate("+1978-12-3"); // need to 0-pad the day to 2 digits
  }

  private void failDate(String date) {
    try {
      new SimpleDate(date);
      fail("Expected IllegalArgumentException for invalid simple date string: " + date);
    }
    catch (IllegalArgumentException e) {
      // Good: an invalid string is supposed to cause an exception.
    }
  }

  private void tryDate(String date, int year, Integer month, Integer day, Integer hour, Integer minute, Integer second, boolean isUTC, Integer tzHour, Integer tzMinute) {
    SimpleDate simpleDate = new SimpleDate(date);
    checkInt(year, simpleDate.getYear());
    checkInt(month, simpleDate.getMonth());
    checkInt(day, simpleDate.getDay());
    checkInt(hour, simpleDate.getHour());
    checkInt(minute, simpleDate.getMinute());
    checkInt(second, simpleDate.getSecond());
    checkInt(tzHour, simpleDate.getTimeZoneHours());
    checkInt(tzMinute, simpleDate.getTimeZoneMinutes());
    assertEquals(isUTC, simpleDate.isUTC());
    if (isUTC) {
      assertNull(simpleDate.getTimeZoneHours());
      assertNull(simpleDate.getTimeZoneMinutes());
    }

    // Test that round-tripping works.
    assertEquals(date, simpleDate.toString());
    assertTrue(simpleDate.isValid());
  }

  private void checkInt(Integer a, Integer b) {
    if (a == null) {
      assertNull(b);
    }
    else {
      assertNotNull(b);
      assertEquals(a.intValue(), b.intValue());
    }
  }
}