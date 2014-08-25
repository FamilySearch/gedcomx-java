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
 * @author John Clark.
 */
public class GedcomxDateUtil {

  public static GedcomxDate parse(String date) {

    return null;
  }

  /**
   * Calculates the Duration between two dates
   * @param startDate The start date
   * @param endDate The end date
   * @return The duration
   */
  public static GedcomxDateDuration getDuration(GedcomxDateSimple startDate, GedcomxDateSimple endDate) {

    if(startDate == null || endDate == null) {
      throw new GedcomxDateException("Start and End must be simple dates");
    }

    Date start = new Date(startDate, true);
    Date end = new Date(endDate, true);
    boolean hasTime = false;
    StringBuilder duration = new StringBuilder();

    zipDates(start, end);

    // Build the duration backwards so we can grab the correct diff
    // Also we need to roll everything up so we don't generate an invalid max year
    if(end.seconds != null) {
      while(end.seconds-start.seconds < 0) {
        end.minutes -= 1;
        end.seconds += 60;
      }
      if(end.seconds-start.seconds > 0) {
        hasTime = true;
        duration.insert(0,'S').insert(0,String.format("%02d", end.seconds - start.seconds));
      }
    }

    if(end.minutes != null) {
      while(end.minutes-start.minutes < 0) {
        end.hours -= 1;
        end.minutes += 60;
      }
      if(end.minutes-start.minutes > 0) {
        hasTime = true;
        duration.insert(0,'M').insert(0,String.format("%02d", end.minutes-start.minutes));
      }
    }

    if(end.hours != null) {
      while(end.hours-start.hours < 0) {
        end.day -= 1;
        end.hours += 24;
      }
      if(end.hours-start.hours > 0) {
        hasTime = true;
        duration.insert(0,'H').insert(0,String.format("%02d", end.hours-start.hours));
      }
    }

    if(hasTime) {
      duration.insert(0,'T');
    }

    if(end.day != null) {
      while(end.day-start.day < 0) {
        end.day += daysInMonth(end.month, end.year);
        end.month -= 1;
        if(end.month < 1) {
          end.year -= 1;
          end.month += 12;
        }
      }
      if(end.day-start.day > 0) {
        duration.insert(0,'D').insert(0,String.format("%02d", end.day-start.day));
      }
    }

    if(end.month != null) {
      while(end.month-start.month < 0) {
        end.year -= 1;
        end.month += 12;
      }
      if(end.month-start.month > 0) {
        duration.insert(0,'M').insert(0,String.format("%02d", end.month-start.month));
      }
    }

    if(end.year-start.year > 0) {
      duration.insert(0,'Y').insert(0,String.format("%04d", end.year-start.year));
    }

    String finalDuration = duration.toString();

    if(end.year-start.year < 0 || duration.equals("")) {
      throw new GedcomxDateException("Start Date must be less than End Date");
    }

    return new GedcomxDateDuration("P"+finalDuration);
  }


  public static GedcomxDateSimple addDuration(GedcomxDateSimple startDate, GedcomxDateDuration duration) {

    if(startDate == null) {
      throw new GedcomxDateException("Invalid Start Date");
    }

    if(duration == null) {
      throw new GedcomxDateException("Invalid Duration");
    }

    Date end = new Date(startDate, false);
    StringBuilder endString = new StringBuilder();

    // Initialize all the values we need in end based on the duration
    zipDuration(end, duration);

    // Add Timezone offset to endString
    if(startDate.getTzHours() != null) {
      endString.append(startDate.getTzHours() >= 0 ? "+" : "-").append(String.format("%02d", Math.abs(startDate.getTzHours())));
      endString.append(":").append(String.format("%02d", startDate.getTzMinutes()));
    }

    if(end.seconds != null) {
      if (duration.getSeconds() != null) {
        end.seconds += duration.getSeconds();
      }
      while (end.seconds >= 60) {
        end.seconds -= 60;
        end.minutes += 1;
      }
      endString.insert(0, String.format("%02d", end.seconds)).insert(0, ":");
    }

    if(end.minutes != null) {
      if (duration.getMinutes() != null) {
        end.minutes += duration.getMinutes();
      }
      while (end.minutes >= 60) {
        end.minutes -= 60;
        end.hours += 1;
      }
      endString.insert(0, String.format("%02d", end.minutes)).insert(0, ":");
    }

    if(end.hours != null) {
      if (duration.getHours() != null) {
        end.hours += duration.getHours();
      }
      while (end.hours >= 24) {
        end.hours -= 24;
        end.day += 1;
      }
      endString.insert(0, String.format("%02d", end.hours)).insert(0, "T");
    }

    if(end.day != null) {
      if (duration.getDays() != null) {
        end.day += duration.getDays();
      }
      while (end.day >= GedcomxDateUtil.daysInMonth(end.month, end.year)) {
        end.month += 1;
        if(end.month > 12) {
          end.month -= 12;
          end.year += 1;
        }
        end.day -= GedcomxDateUtil.daysInMonth(end.month, end.year);
      }
      endString.insert(0, String.format("%02d", end.day)).insert(0, "-");
    }

    if(end.month != null) {
      if (duration.getMonths() != null) {
        end.month += duration.getMonths();
      }
      while (end.month > 12) {
        end.month -= 12;
        end.year += 1;
      }
      endString.insert(0, String.format("%02d", end.month)).insert(0, "-");
    }

    if(duration.getYears() != null) {
      end.year += duration.getYears();
    }

    // After adding months to this year we could have bumped into or out of a non leap year
    // TODO fix this

    if(end.year > 9999) {
      throw new GedcomxDateException("New date out of range");
    }

    if(end.year != null) {
      endString.insert(0, String.format("%04d", Math.abs(end.year))).insert(0,end.year >= 0 ? "+" : "-");
    }

System.out.println(endString.toString());

    return new GedcomxDateSimple(endString.toString());
  }

  public static GedcomxDateDuration multiplyDuration(GedcomxDateDuration duration, int multiplier) {

    if(duration == null) {
      throw new GedcomxDateException("Invalid Duration");
    }

    if(multiplier <= 0) {
      throw new GedcomxDateException("Invalid Multiplier");
    }

    StringBuilder newDuration = new StringBuilder("P");

    if(duration.getYears() != null) {
      newDuration.append(duration.getYears()*multiplier).append('Y');
    }

    if(duration.getMonths() != null) {
      newDuration.append(duration.getMonths()*multiplier).append('M');
    }

    if(duration.getDays() != null) {
      newDuration.append(duration.getDays()*multiplier).append('D');
    }

    if(duration.getHours() != null || duration.getMinutes() != null || duration.getSeconds() != null) {
      newDuration.append('T');

      if(duration.getHours() != null) {
        newDuration.append(duration.getHours()*multiplier).append('H');
      }

      if(duration.getMinutes() != null) {
        newDuration.append(duration.getMinutes()*multiplier).append('M');
      }

      if(duration.getSeconds() != null) {
        newDuration.append(duration.getSeconds()*multiplier).append('S');
      }

    }

    return new GedcomxDateDuration(newDuration.toString());
  }

  public static int daysInMonth(Integer month, Integer year) {
    switch(month) {
      case 1:
      case 3:
      case 5:
      case 7:
      case 8:
      case 10:
      case 12:
        return 31;
      case 4:
      case 6:
      case 9:
      case 11:
        return 30;
      case 2:
        boolean leapYear;
        if(year % 4 != 0) {
          leapYear = false;
        } else if(year % 100 != 0) {
          leapYear = true;
        } else if(year % 400 != 0) {
          leapYear = false;
        } else {
          leapYear = true;
        }
        if(leapYear) {
          return 29;
        } else {
          return 28;
        }
      default:
        throw new GedcomxDateException("Unknown Month");
    }
  }


  /**
   * Ensures that both start and end have values where the other has values.
   * For example, if start has minutes but end does not, this function
   * will initialize minutes in end.
   */
  protected static void zipDates(Date start, Date end) {
    if(start.month == null && end.month != null) {
      start.month = 1;
    }
    if(start.month != null && end.month == null) {
      end.month = 1;
    }

    if(start.day == null && end.day != null) {
      start.day = 1;
    }
    if(start.day != null && end.day == null) {
      end.day = 1;
    }

    if(start.hours == null && end.hours != null) {
      start.hours = 0;
    }
    if(start.hours != null && end.hours == null) {
      end.hours = 0;
    }

    if(start.minutes == null && end.minutes != null) {
      start.minutes = 0;
    }
    if(start.minutes != null && end.minutes == null) {
      end.minutes = 0;
    }

    if(start.seconds == null && end.seconds != null) {
      start.seconds = 0;
    }
    if(start.seconds != null && end.seconds == null) {
      end.seconds = 0;
    }
  }

  /**
   * Ensures that date has its properties initialized based on what the duration has.
   * For example, if date does not have minutes and duration does, this will
   * initialize minutes in the date.
   */
  protected static void zipDuration(Date date, GedcomxDateDuration duration) {
    boolean seconds = false;
    boolean minutes = false;
    boolean hours = false;
    boolean days = false;
    boolean months = false;

    if(duration.getSeconds() != null) {
      seconds = true;
      minutes = true;
      hours = true;
      days = true;
      months = true;
    } else if(duration.getMinutes() != null) {
      minutes = true;
      hours = true;
      days = true;
      months = true;
    } else if(duration.getHours() != null) {
      hours = true;
      days = true;
      months = true;
    } else if(duration.getDays() != null) {
      days = true;
      months = true;
    } else if(duration.getMonths() != null) {
      months = true;
    } else {
      return;
    }

    if(seconds && date.seconds == null) {
      date.seconds = 0;
    }

    if(minutes && date.minutes == null) {
      date.minutes = 0;
    }

    if(hours && date.hours == null) {
      date.hours = 0;
    }

    if(days && date.day == null) {
      date.day = 1;
    }

    if(months && date.month == null) {
      date.month = 1;
    }
  }

  protected static class Date {
    public Integer year = null;
    public Integer month = null;
    public Integer day = null;
    public Integer hours = null;
    public Integer minutes = null;
    public Integer seconds = null;

    public Date() {}

    public Date(GedcomxDateSimple simple, boolean adjustTimezone) {
      year = simple.getYear();
      month = simple.getMonth();
      day = simple.getDay();
      hours = simple.getHours();
      minutes = simple.getMinutes();
      seconds = simple.getSeconds();

      if(adjustTimezone) {
        if(hours != null && simple.getTzHours() != null) {
          hours += simple.getTzHours();
        }

        if(minutes != null && simple.getTzMinutes() != null) {
          minutes += simple.getTzMinutes();
        }
      }
    }
  }
}
