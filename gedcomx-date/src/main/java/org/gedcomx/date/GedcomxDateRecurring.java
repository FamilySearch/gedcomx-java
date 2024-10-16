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

/**
 * A Recurring Date
 * @author John Clark.
 */
public class GedcomxDateRecurring extends GedcomxDate {

  private Integer count = null;
  private GedcomxDateRange range;
  private GedcomxDateSimple end = null;

  /**
   * Instantiate a new Recurring date from the formal date string
   * @param date The formal date string
   */
  public GedcomxDateRecurring(String date) {

    if(date == null || date.length() < 3) {
      throw new GedcomxDateException("Invalid Recurring Date \"" + date + "\"");
    }

    if(date.charAt(0) != 'R') {
      throw new GedcomxDateException("Invalid Recurring Date \"" + date + "\": Must start with R");
    }

    String[] parts = date.split("/");

    if(parts.length != 3) {
      throw new GedcomxDateException("Invalid Recurring Date \"" + date + "\": Must contain 3 parts");
    }

    // We must have a start and end
    if(parts[1].equals("") || parts[2].equals("")) {
      throw new GedcomxDateException("Invalid Recurring Date \"" + date + "\": Range must have a start and an end");
    }

    String countNum = parts[0].substring(1);
    char[] countNumChars = parts[0].substring(1).toCharArray();

    if(countNumChars.length > 0) {
      for (char c : countNumChars) {
        if (!Character.isDigit(c)) {
          throw new GedcomxDateException("Invalid Recurring Date \"" + date + "\": Malformed Count");
        }
      }
      count = Integer.valueOf(countNum);
    }
    try {
      range = new GedcomxDateRange(parts[1] + "/" + parts[2]);
    } catch(GedcomxDateException e) {
      throw new GedcomxDateException(e.getMessage() + " in Recurring Range");
    }

    // If we have a count set end
    if(count != null) {
      end = getNth(count);
    }
  }

  /**
   * Get the count
   * @return The Count
   */
  public Integer getCount() {
    return count;
  }

  /**
   * Get the range
   * @return The Range
   */
  public GedcomxDateRange getRange() {
    return range;
  }

  /**
   * Get the start date
   * @return The Start Date
   */
  public GedcomxDateSimple getStart() {
    return range.getStart();
  }

  /**
   * Get the duration
   * @return The Duration
   */
  public GedcomxDateDuration getDuration() {
    return range.getDuration();
  }

  /**
   * Get the end date
   * @return The End Date
   */
  public GedcomxDateSimple getEnd() {
    return end;
  }

  /**
   * Get the nth instance of this recurring date
   * @param count The nth instance
   * @return The date of the nth instance
   */
  public GedcomxDateSimple getNth(Integer count) {

    GedcomxDateDuration duration = GedcomxDateUtil.multiplyDuration(range.getDuration(), count);

    return GedcomxDateUtil.addDuration(range.getStart(), duration);
  }

  /**
   * Get the date type
   * @return The Date Type
   */
  @Override
  public GedcomxDateType getType() {
    return GedcomxDateType.RECURRING;
  }

  /**
   * Whether or not this date is considered approximate
   * @return True if this date is approximate
   */
  @Override
  public boolean isApproximate() {
    return false;
  }

  /**
   * Return the formal string for this date
   * @return The formal date string
   */
  @Override
  public String toFormalString() {
    if(count != null) {
      return "R"+count+"/"+range.toFormalString();
    } else {
      return "R/"+range.toFormalString();
    }
  }
}
