package org.gedcomx.util.date;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class representing a fully parsed GedcomX standard date, for the purpose of creating or understanding
 *   GedcomX formal date strings.
 * Format of a GedcomX formal dates are made up by strings of these types:
 *
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
 * User: Randy Wilson
 * Date: 8/7/13
 * Time: 12:53 PM
 */
public class FormalDate {


  // Flag for whether this date or range is approximate
  private boolean isApproximate;
  // Flag for whether this FormalDate is a range.  If true, then a null 'end' and 'duration' indicates an open-ended range.
  private boolean isRange;
  // Starting time of a range, or the whole time when not a range.
  private SimpleDate start;
  // End of a range (unless duration is used).  Must be null if !isRange or duration is non-null.
  // If isRange and duration is null and 'end' is null, then this is an open-ended range.
  private SimpleDate end;
  // Duration of range.  Must be null if !isRange or 'end' is non-null.
  private Duration duration;
  // Flag for whether this is a repeating date or not
  private boolean isRecurring;
  // number of repetitions.  null => no limit.
  private Integer numRepetitions;

  private static final Pattern formalDatePattern = Pattern.compile("(A|R([0-9]*)/)?([^/]*)(/([^/]*))?");

  /**
   * Constructor that parses a formal date string.
   * @param formalDateString - Formal date string to parse.
   */
  public FormalDate(String formalDateString) {
    Matcher m = formalDatePattern.matcher(formalDateString);
    if (!m.matches()) {
      throw new IllegalArgumentException("Does not match formal date string format");
    }
    // group 1: A or R[numRepetitions]
    if (m.group(1) != null) {
      if (m.group(1).equals("A")) {
        isApproximate = true;
      }
      else if (m.group(1).startsWith("R")) {
        isRecurring = true;
        // Group 2: numRepetitions
        if (m.group(2) != null && m.group(2).length() > 0) {
          numRepetitions = Integer.parseInt(m.group(2));
        }
      }
    }
    // Group 3: starting simpleDate
    if (m.group(3) != null && m.group(3).length() > 0) {
      start = new SimpleDate(m.group(3));
    }
    // Group 4: "/" and ending simpleDate or duration
    if (m.group(4) != null) {
      // Had at least a "/"
      isRange = true;
      if (m.group(5) != null && m.group(5).length() > 0) {
        // Group 5: ending date or duration (beginning with "P").
        if (m.group(5).startsWith("P")) {
          if (start == null) {
            throw new IllegalArgumentException("Error: Cannot have a duration without a starting date.");
          }
          duration = new Duration(m.group(5));
        }
        else {
          end = new SimpleDate(m.group(5));
        }
      }
    }
  }

  /**
   * Tell whether this FormalDate is approximate.
   * @return true if this FormalDate is approximate, false otherwise.
   */
  public boolean isApproximate() {
    return isApproximate;
  }

  /**
   * Set the flag for whether this date is approximate.  Note that it is illegal to have an approximate repeating date.
   * @param approximate - flag for whether this date is approximate.
   */
  public void setApproximate(boolean approximate) {
    isApproximate = approximate;
  }

  /**
   * Tell whether this date is a range.
   * @return true if this FormalDate is a range, false otherwise.
   */
  public boolean isRange() {
    return isRange;
  }

  /**
   * Set the flag for whether this FormalDate is a range.
   * @param range - flag for whether this date is a range.
   */
  public void setRange(boolean range) {
    isRange = range;
  }

  /**
   * Get the starting date for this FormalDate.
   * If the date is not a range, this is the whole date.
   * If the date is a range, and this is null, then it implies "any time before the ending date".
   * Must not be null if this FormalDate is not a range.
   * @return Starting date for this FormalDate.
   */
  public SimpleDate getStart() {
    return start;
  }

  /**
   * Set the starting date.  (If the date is not a range, this is the whole date).
   * @param start - Starting date.
   */
  public void setStart(SimpleDate start) {
    this.start = start;
  }

  /**
   * Get the ending date for this FormalDate range.  If the starting date is null, then the FormalDate
   *   is interpreted as "any time up to this ending date".
   * If the date is a range, and the end is null, then it means "any time from the starting date or later."
   * If the date is not a range, the end must be null.
   * @return ending date.
   */
  public SimpleDate getEnd() {
    return end;
  }

  /**
   * Set the ending date of this FormalDate range.  If the starting date is null, then the date
   *   is interpreted as "any time up to this ending time".
   * If the date is a range, and the end is null, then it means "any time from the starting date or later".
   * If the date is not a range, the end must be null.
   * If the date is a range, then either the start or end must be non-null.
   * @param end
   */
  public void setEnd(SimpleDate end) {
    this.end = end;
  }

  /**
   * Get the Duration portion of a FormalDate, or null if there is no duration.
   * If the duration is non-null, then the starting date must be non-null.
   * A duration is used in a range or in a recurring date.
   * Must be null if the end is non-null.
   * @return
   */
  public Duration getDuration() {
    return duration;
  }

  /**
   * Set the duration of a FormalDate.
   * @param duration
   */
  public void setDuration(Duration duration) {
    this.duration = duration;
  }

  /**
   * Get the flag for whether this date is a repeating date.  If so, then there must be
   *   a start date, and either an end date or a duration (but not both).
   * @return flag for whether this is a recurring date.
   */
  public boolean isRecurring() {
    return isRecurring;
  }

  /**
   * Set the flag for whether this is a recurring date, in which case there must be
   *   a start date and either an end date or a duration (but not both).
   * @param recurring - flag for whether this is a recurring date.
   */
  public void setRecurring(boolean recurring) {
    isRecurring = recurring;
  }

  /**
   * Get the number of repetitions for a recurring date, or null if none.
   * @return number of repetitions for a recurring date, or null if none.
   */
  public Integer getNumRepetitions() {
    return numRepetitions;
  }

  /**
   * Set the number of repetitions for a recurring date.  Ignored if the date is not recurring.
   * If null, a recurring date is assumed to have no limit on the number of repetitions.
   * @param numRepetitions - Number of repetitions for a recurring date, or null for no limit.
   */
  public void setNumRepetitions(Integer numRepetitions) {
    this.numRepetitions = numRepetitions;
  }

  /**
   * Tell whether the current state of the date is valid for a GedcomX formal date.
   * In particular, make sure it follows one of the following patterns:
   *    [A]simpleDate
   *    [A]simpleDate/[simpleDate|Duration]
   *    [A]/simpleDate
   *    R[repetitions]/simpleDate/(simpleDate|Duration)
   * @return true if the date is completely valid, false if there are inconsistencies.
   */
  public boolean isValid() {
    if (isRecurring) {
      // Handle R[repetitions]/simpleDate/(simpleDate|Duration)
      return !isApproximate && start != null && start.isValid() && ((end != null && end.isValid()) || (end == null && duration != null && duration.isValid()));
    }
    else {
      if (numRepetitions != null) {
        return false; // shouldn't have number of repetitions on a non-recurring date.
      }
      if (start != null && !isRange) {
        // Handle [A]simpleDate
        return end == null && duration == null && start.isValid();
      }
      if (isRange) {
        if (start == null) {
          // Handle [A]/simpleDate
          return end != null && end.isValid() && duration == null;
        }
        else {
          // Handle [A]simpleDate/[simpleDate|Duration]
          return (end == null || duration == null) &&
                  (end == null || end.isValid()) &&
                  (duration == null || duration.isValid());
        }
      }
    }
    return false; // couldn't find a valid pattern to follow.
  }

  /**
   * Convert a FormalDate to a string
   * @return canonical GedcomX formal date string.
   */
  public String toString() {
    StringBuilder sb = new StringBuilder();
    if (isRecurring) {
      sb.append("R");
      if (numRepetitions != null) {
        sb.append(numRepetitions);
      }
      sb.append("/");
    }
    else if (isApproximate) {
      sb.append("A");
    }
    if (start != null) {
      sb.append(start.toString());
    }
    if (isRange) {
      sb.append("/");
    }
    if (end != null) {
      sb.append(end.toString());
    }
    else if (duration != null) {
      sb.append(duration.toString());
    }
    return sb.toString();
  }
}

