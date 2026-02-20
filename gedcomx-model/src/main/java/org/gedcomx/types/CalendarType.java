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
package org.gedcomx.types;

import com.webcohesion.enunciate.metadata.qname.XmlQNameEnum;

/**
 * Enumeration of  calendar systems used for AlternateDate representations.
 * <p>
 * Notes:
 * <ul>
 *   <li>Descriptions here are concise overviews; actual conversion logic should be implemented
 *       in a dedicated date standardization service.</li>
 *   <li>Examples include original scripts with romanization and translation for clarity.</li>
 * </ul>
 */
@XmlQNameEnum(
    base = XmlQNameEnum.BaseType.URI
)
public enum CalendarType {

  /**
   * GREGORIAN — Proleptic Gregorian calendar.
   * <p>
   * The standard civil calendar used in most of the world today, and the default calendar
   * for GEDCOM X dates. This calendar is used for all “formal” GedcomX dates, and is
   * assumed whenever a date does not explicitly specify a calendar type.
   * </p>
   * <p>
   * The Gregorian calendar was introduced in 1582 to replace the Julian calendar.
   * Different countries adopted it at different times, but in computational
   * contexts it is treated as <i>proleptic</i>, meaning that its rules are
   * extended backward to dates earlier than its historical adoption. Leap years
   * occur in years divisible by 4, except century years not divisible by 400
   * (e.g., 1900 is a common year; 2000 is a leap year).
   * </p>
   * <p><b>Example:</b> October 15, 2025 (Gregorian)</p>
   */
  Gregorian,

  /**
   * JULIAN — Julian calendar.
   * <p>
   * A solar calendar introduced by Julius Caesar in 45 BCE, with a simple leap-year
   * rule: every fourth year has 366 days. It was used throughout Europe until it was
   * gradually replaced by the Gregorian calendar at different times in different
   * regions.
   * </p>
   * <p>
   * In genealogical records, dates in the early modern period may appear in “Old Style”
   * (Julian) form, sometimes written as dual dates (e.g., 10 February 1740/41) because
   * the legal new year began on March 25. Any such interpretation or conversion is
   * handled by a date standardization service.
   * </p>
   * <p><b>Example:</b> March 1, 1600 (Julian)</p>
   */
  Julian,

  /**
   * FRENCH_REPUBLIC — French Republican calendar.
   * <p>
   * A solar calendar introduced in 1792 with 12 months of 30 days named after seasonal features
   * (e.g., Vendémiaire, Brumaire, Floréal), followed by 5 or 6 complementary days (sans‑culottides).
   * Weeks are décades of 10 days.
   * </p>
   * <p><b>Example (French):</b> 10 Floréal an II
   * (dix Floréal an deux; 10 Floréal, Year 2)</p>
   */
  FrenchRepublic,

  /**
   * CHINESE IMPERIAL — Traditional Chinese imperial (regnal) dating based on the lunisolar calendar.
   * <p>
   * Dates are expressed with reign era names 年号 (niánhào; reign title), counting years within
   * an emperor’s reign. Months and days follow the traditional lunisolar calendar, which can include
   * leap months 闰月 (rùnyuè; leap month).
   * </p>
   * <p><b>Example (Chinese):</b> 康熙四十五年 八月 初三
   * (Kāngxī sìshíwǔ nián bāyuè chū sān; Kangxi year 45, 8th month, day 3)</p>
   */
  ChineseImperial,

  /**
   * JAPANESE IMPERIAL — Japanese era-based regnal dating alongside Gregorian.
   * <p>
   * Uses era names 元号 (gengō; era name) to count years within an emperor’s reign; the current era is
   * 令和 (Reiwa; Beautiful Harmony) since 2019. Dates are written as
   * “[era] [year]年 [month]月 [day]日”.
   * </p>
   * <p><b>Example (Japanese):</b> 令和7年 3月 1日
   * (Reiwa 7‑nen 3‑gatsu 1‑nichi; March 1, Reiwa 7)</p>
   */
  JapaneseImperial,

  /**
   * KOREAN IMPERIAL — Korean imperial (regnal) calendar.
   * <p>
   * Uses era names from the Daehan Empire for regnal dating, expressed as
   * “[era name] [year] [month] [day]”. The empire name: 대한제국 (Daehan Jeguk; Great Han Empire).
   * Format mirrors East Asian regnal styles.
   * </p>
   * <p><b>Example (Hangul):</b> 광무 5년 3월 1일 (Gwangmu 5‑nyeon 3‑wol 1‑il; Gwangmu year 5, March 1)</p>
   */
  KoreanImperial,

  /**
   * THAI SOLAR — Thai Buddhist solar calendar.
   * <p>
   * Solar calendar aligned with Gregorian months and days; the year is counted in
   * พุทธศักราช (Phutthasakkarat; Buddhist Era), where BE = CE + 543.
   * </p>
   * <p><b>Example (Thai):</b> วันที่ 1 มกราคม พ.ศ. 2568
   * (Wan thī 1 Mokkarakhom Phō.Sō. 2568; 1 January, BE 2568)</p>
   */
  ThaiSolar,

  /**
   * Hebrew — Jewish lunisolar calendar.
   * <p>
   * The Hebrew calendar הלוח העברי (ha-luach ha-ivri; the Hebrew calendar) is lunisolar:
   * 12 months with a leap month added 7 times in a 19-year cycle (Metonic) to align
   * lunar months with the solar year. Months begin at the new moon; religious observances
   * follow the calendar’s rules for postponements and year lengths.
   * </p>
   * <p><b>Example (Hebrew):</b> י״ד תשרי תשפ״ה
   * (14 Tishri 5785; 14th of Tishri, year 5785)</p>
   */
  Hebrew,

  /**
   * ISLAMIC — Islamic (Hijri) lunar calendar.
   * <p>
   * A purely lunar calendar: 12 months of 29 or 30 days, totaling 354 or 355 days per year.
   * Month starts traditionally depending on local crescent sighting; civil variants may use
   * astronomical calculation. Common name: اَلْتَقْوِيمُ ٱلْهِجْرِي (al‑taqwīm al‑hijrī; the Hijri calendar).
   * </p>
   * <p><b>Example (Arabic):</b> ١ رمضان ١٤٤٧ هـ (1 Ramaḍān 1447 AH; 1st of Ramadan, year 1447 of the Hijra)</p>
   */
  Islamic,

  /**
   * RUMI — Rumi solar calendar (Turkish Ottoman Empire).
   * <p>
   * A solar calendar used in late Ottoman administration, originally based on the Julian calendar.
   * It employed fiscal year numbering and, in later reforms, incorporated Gregorian‑style adjustments
   * while keeping Rumi year numbers. The name رومي (Rūmī; Roman/Byzantine) reflects its provenance.
   * </p>
   * <p><b>Example:</b> Rûmî 1325, Kanun‑i Evvel 1
   * (Rûmî 1325; December 1 in the Rumi year 1325)</p>
   */
  Rumi;
}

