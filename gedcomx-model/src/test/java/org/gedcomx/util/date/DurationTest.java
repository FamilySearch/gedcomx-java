package org.gedcomx.util.date;

import junit.framework.TestCase;

/**
 * Class for testing the GedcomX Duration class (part of the FormalDate utility).
 * User: Randy Wilson
 * Date: 8/20/13
 * Time: 2:26 PM
 */
public class DurationTest extends TestCase {

  /**
   * Test duration strings of the form P[yyyyY][mmM][ddD][T[hhH][mmM][ssS]].
   */
  public void testDuration() {
    // Test valid durations
    tryDuration("P0001Y", 1, null, null, null, null, null);
    tryDuration("P12M", null, 12, null, null, null, null);
    tryDuration("P01M", null, 1, null, null, null, null);
    tryDuration("P31D", null, null, 31, null, null, null);
    tryDuration("P01D", null, null, 1, null, null, null);
    tryDuration("P0061Y12M31D", 61, 12, 31, null, null, null);
    tryDuration("PT22H", null, null, null, 22, null, null);
    tryDuration("PT59M", null, null, null, null, 59, null);
    tryDuration("PT31S", null, null, null, null, null, 31);
    tryDuration("PT22H59M31S", null, null, null, 22, 59, 31);
    tryDuration("P0061Y12M31DT22H59M31S", 61, 12, 31, 22, 59, 31);

    // Test invalid durations to make sure they fail
    tryFail("X12M"); // wrong prefix
    tryFail("12M"); // no prefix
    tryFail("P2Y"); // failed to zero-pad year
    tryFail("P31S"); // failed to include "T" before time elements.
  }

  private void tryDuration(String durationString, Integer year, Integer month, Integer day, Integer hour, Integer minute, Integer second) {
    Duration duration = new Duration(durationString);
    checkInt(year, duration.getYear());
    checkInt(month, duration.getMonth());
    checkInt(day, duration.getDay());
    checkInt(hour, duration.getHour());
    checkInt(minute, duration.getMinute());
    checkInt(second, duration.getSecond());
    assertTrue(duration.isValid());
  }

  private void tryFail(String durationString) {
    try {
      new Duration(durationString);
      fail();
    }
    catch (IllegalArgumentException e) {
      // Good.  We expected a failure.
    }
  }
  private void checkInt(Integer expected, Integer actual) {
    if (expected == null) {
      assertNull(actual);
    }
    else {
      assertNotNull(actual);
      assertEquals(expected.intValue(), actual.intValue());
    }
  }
}
