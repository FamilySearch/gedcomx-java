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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * The duration between two simple dates
 * @author John Clark.
 */
public class GedcomxDateDuration extends GedcomxDate {

  private Integer years = null;
  private Integer months = null;
  private Integer days = null;
  private Integer hours = null;
  private Integer minutes = null;
  private Integer seconds = null;

  /**
   * Create a new duration from the formal string
   * @param str The formal duration string
   */
  public GedcomxDateDuration(String str) {

    // Durations must start with P
    if(str == null || str.length() < 1 || str.charAt(0) != 'P') {
      throw new GedcomxDateException("Invalid Duration: Must start with P");
    }

    String duration = str.substring(1);

    if(duration.length() < 1) {
      throw new GedcomxDateException("Invalid Duration: You must have a duration value");
    }

    // 5.3.2 allows for NON normalized durations
    // We assume that if there is a space, it is non-normalized
    if(duration.contains(" ")) {
      // When we implement non normalized durations we can call parseNonNormalizedDuration(duration)
      throw new GedcomxDateException("Invalid Duration: Non normalized durations are not implemented yet");
    } else {
      parseNormalizedDuration(duration);
    }

  }

  /**
   * Parse the normalized duration
   * @param duration the formal duration string
   */
  private void parseNormalizedDuration(String duration) {

    String currentNum = "";
    boolean inTime = false;
    HashSet<String> seen = new HashSet<String>();
    List valid = Arrays.asList("Y", "Mo", "D", "T", "H", "Mi", "S");

    for(char character : duration.toCharArray()) {

      if(Character.isDigit(character)) {
        currentNum += character;
        continue;
      }

      switch(character) {
        case 'Y':
          if(currentNum.length() < 1) {
            throw new GedcomxDateException("Invalid Duration: Invalid years");
          }
          if(seen.contains("Y")) {
            throw new GedcomxDateException("Invalid Duration: Duplicate years");
          }
          if(!valid.contains("Y")) {
            throw new GedcomxDateException("Invalid Duration: Years out of order");
          }
          this.years = Integer.valueOf(currentNum);
          seen.add("Y");
          valid = valid.subList(valid.indexOf("Y")+1,valid.size());
          currentNum = "";
          break;
        case 'M':
          if(inTime) {
            if(currentNum.length() < 1) {
              throw new GedcomxDateException("Invalid Duration: Invalid minutes");
            }
            if(seen.contains("Mi")) {
              throw new GedcomxDateException("Invalid Duration: Duplicate minutes");
            }
            if(!valid.contains("Mi")) {
              throw new GedcomxDateException("Invalid Duration: Minutes out of order");
            }
            this.minutes = Integer.valueOf(currentNum);
            seen.add("Mi");
            valid = valid.subList(valid.indexOf("Mi")+1,valid.size());
            currentNum = "";
          } else {
            if(currentNum.length() < 1) {
              throw new GedcomxDateException("Invalid Duration: Invalid months");
            }
            if(seen.contains("Mo")) {
              throw new GedcomxDateException("Invalid Duration: Duplicate months");
            }
            if(!valid.contains("Mo")) {
              throw new GedcomxDateException("Invalid Duration: Months out of order");
            }
            this.months = Integer.valueOf(currentNum);
            seen.add("Mo");
            valid = valid.subList(valid.indexOf("Mo")+1,valid.size());
            currentNum = "";
          }
          break;
        case 'D':
          if(currentNum.length() < 1) {
            throw new GedcomxDateException("Invalid Duration: Invalid days");
          }
          if(seen.contains("D")) {
            throw new GedcomxDateException("Invalid Duration: Duplicate days");
          }
          if(!valid.contains("D")) {
            throw new GedcomxDateException("Invalid Duration: Days out of order");
          }
          this.days = Integer.valueOf(currentNum);
          seen.add("D");
          valid = valid.subList(valid.indexOf("D")+1,valid.size());
          currentNum = "";
          break;
        case 'H':
          if(!inTime) {
            throw new GedcomxDateException("Invalid Duration: Missing T before hours");
          }
          if(currentNum.length() < 1) {
            throw new GedcomxDateException("Invalid Duration: Invalid hours");
          }
          if(seen.contains("H")) {
            throw new GedcomxDateException("Invalid Duration: Duplicate hours");
          }
          if(!valid.contains("H")) {
            throw new GedcomxDateException("Invalid Duration: Hours out of order");
          }
          this.hours = Integer.valueOf(currentNum);
          seen.add("H");
          valid = valid.subList(valid.indexOf("H")+1,valid.size());
          currentNum = "";
          break;
        case 'S':
          if(!inTime) {
            throw new GedcomxDateException("Invalid Duration: Missing T before seconds");
          }
          if(currentNum.length() < 1) {
            throw new GedcomxDateException("Invalid Duration: Invalid seconds");
          }
          if(seen.contains("S")) {
            throw new GedcomxDateException("Invalid Duration: Duplicate seconds");
          }
          // You cannot have seconds out of order because it's last
          this.seconds = Integer.valueOf(currentNum);
          seen.add("S");
          valid = new ArrayList<String>();
          currentNum = "";
          break;
        case 'T':
          if(seen.contains("T")) {
            throw new GedcomxDateException("Invalid Duration: Duplicate T");
          }
          inTime = true;
          seen.add("T");
          valid = valid.subList(valid.indexOf("T")+1,valid.size());
          break;
        default:
          throw new GedcomxDateException("Invalid Duration: Unknown letter "+character);
      }
    }

    // If there is any leftover we have an invalid duration
    if(!currentNum.equals("")) {
      throw new GedcomxDateException("Invalid Duration: No letter after "+currentNum);
    }

  }

  /**
   * The type of this date
   * @return The date type
   */
  @Override
  public GedcomxDateType getType() {
    return GedcomxDateType.DURATION;
  }

  /**
   * A Duration is NEVER Approximate
   * @return True if the duration is approximate (It never is)
   */
  @Override
  public boolean isApproximate() {
    return false;
  }

  /**
   * The formal string representation of the duration
   * @return The formal string
   */
  @Override
  public String toFormalString() {
    StringBuilder duration = new StringBuilder("P");

    if(years != null) {
      duration.append(years).append('Y');
    }

    if(months != null) {
      duration.append(months).append('M');
    }

    if(days != null) {
      duration.append(days).append('D');
    }

    if(hours != null || minutes != null || seconds != null) {

      duration.append('T');

      if (hours != null) {
        duration.append(hours).append('H');
      }

      if (minutes != null) {
        duration.append(minutes).append('M');
      }

      if (seconds != null) {
        duration.append(seconds).append('S');
      }
    }

    return duration.toString();
  }

  /**
   * Get the years
   * @return The Years
   */
  public Integer getYears() {
    return years;
  }

  /**
   * Get the months
   * @return The Months
   */
  public Integer getMonths() {
    return months;
  }

  /**
   * Get the days
   * @return The Days
   */
  public Integer getDays() {
    return days;
  }

  /**
   * Get the hours
   * @return The Hours
   */
  public Integer getHours() {
    return hours;
  }

  /**
   * Get the minutes
   * @return The Minutes
   */
  public Integer getMinutes() {
    return minutes;
  }

  /**
   * Get the seconds
   * @return The Seconds
   */
  public Integer getSeconds() {
    return seconds;
  }
}
