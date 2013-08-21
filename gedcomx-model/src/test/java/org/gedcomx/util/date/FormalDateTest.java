package org.gedcomx.util.date;

import junit.framework.TestCase;

/**
 * Class for testing the GedcomX FormalDate utility class.
 * User: Randy Wilson
 * Date: 8/20/13
 * Time: 2:28 PM
 */
public class FormalDateTest extends TestCase {
  /*
   * Simple date:
   *   (+|-)YYYY[-MM[-DD[Thh[:mm[:ss[(+\-)hh[:mm]|Z]]]]]]
   *
   * Duration:
   *   P[yyyyY][mmM][ddD][T[hhH][mmM][ssS]]
   *
   * Closed date Range:
   *   [simpleDate]/[simpleDate|Duration]
   * Open-ended date range:
   *   [simpleDate]/
   *   /[simpleDate]
   * Recurring date
   *   R[repetitions]/simpleDate/(simpleDate|Duration)
   * Approximate date or date range
   *   A(simpleDate)
   *   A(dateRange)
   *
   * => ([A](simpleDate|dateRange) | R[repetitions]/simpleDate/(simpleDate|Duration))
   * => [A]simpleDate
   *    [A]simpleDate/[simpleDate|Duration]
   *    [A]/simpleDate
   *    R[repetitions]/simpleDate/(simpleDate|Duration)
   */

  public void testFormalDate() {
    // Simple date
    checkFormalDate("+1820", false, 1820, false, null, null, null);
    checkFormalDate("A+1820", true, 1820, false, null, null, null);
    failFormalDate("1820"); // no "+"
    failFormalDate("+1820--12"); // no month

    // Ranges
    checkFormalDate("A+1820/", true, 1820, true, null, null, null);
    checkFormalDate("A+1820/+1830", true, 1820, true, 1830, null, null);
    checkFormalDate("A/+1830", true, null, true, 1830, null, null);
    checkFormalDate("/+1830", false, null, true, 1830, null, null);

    // Duration
    checkFormalDate("+1820/P0061Y", false, 1820, true, null, 61, null);
    checkFormalDate("A+1820/P0061Y", true, 1820, true, null, 61, null);
    failFormalDate("/P0061Y"); // no start date
    failFormalDate("A+1820/P61Y"); // need to 0-pad duration

    // Repetition
    checkFormalDate("R/+1820/+1821", false, 1820, true, 1821, null, 0);
    checkFormalDate("R42/+1820/+1821", false, 1820, true, 1821, null, 42);
    checkFormalDate("R42/+1820/P0001Y", false, 1820, true, null, 1, 42);
    checkFormalDate("R/+1820/P0061Y", false, 1820, true, null, 61, 0);
    failFormalDate("AR/+1820/P0061Y"); // can't have approximate recurring date
    failFormalDate("RA/+1820/P0061Y"); // can't have approximate recurring date
  }


  private void failFormalDate(String formalDateString) {
    try {
      new FormalDate(formalDateString);
      fail("Should have failed to parse formal date string: " + formalDateString);
    }
    catch (IllegalArgumentException e) {
      // ok
    }
  }
  private FormalDate checkFormalDate(String formalDateString, boolean isApproximate, Integer startYear, boolean isRange, Integer endYear, Integer durationYears, Integer repetitions) {
    FormalDate formalDate = new FormalDate(formalDateString);
    assertEquals(isApproximate, formalDate.isApproximate());
    if (startYear == null) {
      assertNull(formalDate.getStart());
    }
    else {
      checkSimpleDate(formalDate.getStart(), startYear);
    }
    assertEquals(isRange, formalDate.isRange());
    if (endYear == null) {
      assertNull(formalDate.getEnd());
    }
    else {
      checkSimpleDate(formalDate.getEnd(), endYear);
    }
    if (durationYears == null) {
      assertNull(formalDate.getDuration());
    }
    else {
      assertEquals(durationYears, formalDate.getDuration().getYear());
    }
    if (repetitions == null) {
      assertFalse(formalDate.isRecurring());
    }
    else {
      assertTrue(formalDate.isRecurring());
      if (repetitions == 0) {
        assertNull(formalDate.getNumRepetitions());
      }
      else {
        assertEquals(repetitions, formalDate.getNumRepetitions());
      }
    }
    assertEquals(formalDateString, formalDate.toString());
    assertTrue(formalDate.isValid());
    return formalDate;
  }

  public void testSimpleDate() {
    FormalDate formalDate = roundTrip("+1830-04-06");
    checkSimpleDate(formalDate.getStart(), 1830, 4, 6, null, null, null, false, null, null);

    formalDate = new FormalDate("A+1820-12-31T23:59:01Z");
    assertTrue(formalDate.isApproximate());
    checkSimpleDate(formalDate.getStart(), 1820,12, 31, 23, 59, 1, true, null, null);
  }

  private void checkSimpleDate(SimpleDate simpleDate, int year) {
    checkSimpleDate(simpleDate, year, null, null, null, null, null, false, null, null);
  }

  private void checkSimpleDate(SimpleDate simpleDate, int year, Integer month, Integer day, Integer hour, Integer minute, Integer second, boolean isUTC, Integer tzHour, Integer tzMinute) {
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

  private FormalDate roundTrip(String formalDateString) {
    FormalDate formalDate = new FormalDate(formalDateString);
    assertEquals(formalDateString, formalDate.toString());
    return formalDate;
  }

}