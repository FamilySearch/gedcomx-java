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
package org.gedcomx.conclusion;

import org.gedcomx.types.CalendarType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.webcohesion.enunciate.metadata.qname.XmlQNameEnumRef;

/**
 * Represents a date expressed in an alternate calendar system.
 * <p>
 * An {@code AlternateCalendarDate} is included within a {@link Date} object's alternateCalendars list to express the
 * same date in a calendar other than the proleptic Gregorian calendar. The main {@code Date} should always be
 * in the proleptic Gregorian calendar, and any alternate representations (e.g., Hebrew, Rumi, Julian)
 * should be provided as {@code AlternateCalendarDate} instances.
 * </p>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AlternateCalendarDate extends Date {
  public AlternateCalendarDate() {
    // Default constructor
  }

  /**
   * The calendar system used to interpret this date (e.g., Hebrew, Rumi, Julian).
   * This value indicates which calendar should be used to interpret the date values in this object.
   */
  private CalendarType calendar;

  public AlternateCalendarDate(CalendarType calendar) {
    this.calendar = calendar;
  }

  public AlternateCalendarDate(AlternateCalendarDate copy) {
    super(copy);
    this.calendar = copy.calendar;
  }

  /**
   * Gets the calendar system used to interpret this date.
   *
   * @return The calendar system for this alternate date.
   */
  @XmlQNameEnumRef(CalendarType.class)
  public CalendarType getCalendar() {
    return calendar;
  }

  /**
   * Sets the calendar system used to interpret this date.
   *
   * @param calendar The calendar system for this alternate date.
   */
  public void setCalendar(CalendarType calendar) {
    this.calendar = calendar;
  }

  /**
   * Build up the AlternateCalendarDate with the calendar system.
   *
   * @param calendar The calendar system for this alternate date.
   * @return this.
   */
  public AlternateCalendarDate calendar(CalendarType calendar) {
    setCalendar(calendar);
    return this;
  }

  @Override
  public String toString() {
    return "Date{" + "original='" + getOriginal() + '\'' + ", formal=" + getFormal() + ", calendar=" + calendar + '}';
  }
}
