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
package org.gedcomx.util;

import org.gedcomx.common.TextValue;
import sun.util.locale.BaseLocale;

import java.util.Collection;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class for helping to choose the "best" locale that is available, given a preferred locale (e.g., that is being
 *   used by a particular user at a web site) and a default locale (e.g., English).
 * User: Randy Wilson
 * Date: 7/31/2014
 * Time: 11:44 AM
 */
public class LocaleUtil {
  /**
   * Return the textValue whose language is closest the localeToMatch.
   * @param textValues - Collection of TextValues, each with a value and a language.
   * @param localeToMatch - Preferred locale to match against.
   * @return TextValue in the given collection that is closest to the given locale; or null if there are no values.
   */
  public static TextValue findClosestLocale(Collection<TextValue> textValues, Locale localeToMatch) {
    return findClosestLocale(textValues, localeToMatch, Locale.ENGLISH);
  }

  /**
   * Return the textValue whose language is closest the localeToMatch.
   * @param textValues - Collection of TextValues, each with a value and a language.
   * @param localeToMatch - Preferred locale to match against.
   * @param defaultLocale - Default locale to match against, if none are close to the preferred locale.
   * @return TextValue in the given collection that is closest to the given locale; or null if there are no values.
   */
  public static TextValue findClosestLocale(Collection<TextValue> textValues, Locale localeToMatch, Locale defaultLocale) {
    if (textValues != null) {
      TextValue bestTextValue = null;
      Locale bestLocale = null;
      for (TextValue textValue : textValues) {
        Locale locale = getSimpleLocale(textValue.getLang());
        if (bestTextValue == null || LocaleUtil.isBetterLocaleMatch(localeToMatch, locale, bestLocale, defaultLocale)) {
          bestTextValue = textValue;
          bestLocale = locale;
        }
      }
      return bestTextValue;
    }
    return null;
  }

  public static Locale findClosestLocale(Set<Locale> locales, Locale localeToMatch) {
    return findClosestLocale(locales, localeToMatch, Locale.ENGLISH);
  }

  public static Locale findClosestLocale(Set<Locale> locales, Locale localeToMatch, Locale defaultLocale) {
    if (locales == null) {
      return null;
    }
    if (defaultLocale == null) {
      defaultLocale = Locale.getDefault();
    }
    if (localeToMatch == null) {
      localeToMatch = defaultLocale;
    }
    Locale currentBest = null;
    for (Locale locale : locales) {
      if (locale.equals(localeToMatch)) {
        return locale;
      }
      if (isBetterLocaleMatch(localeToMatch, locale, currentBest, defaultLocale)) {
        currentBest = locale;
      }
    }
    return currentBest;
  }

  /**
   * Decide if the current locale is a better match to the preferred one than the 'best' one found so far.
   * @param preferredLocale - The preferred locale that is being matched against.
   * @param currentLocale - The current locale to check to see if it is closer to the preferred locale than the 'best' one so far.
   * @param bestLocale - The best locale (i.e., closest to 'preferredLocale') found so far, or null if this is the first one.
   * @param defaultLocale - Default locale to
   * @return true if 'currentLocale' is closer to preferredLocale (or, if best and current are both too far, if it is
   *         closer to defaultLocale) than 'best'; false otherwise.
   */
  private static boolean isBetterLocaleMatch(Locale preferredLocale, Locale currentLocale, Locale bestLocale, Locale defaultLocale) {
    if (bestLocale == null) {
      // pick the first one you come to
      return true;
    }
    int currentMatch = matchCount(preferredLocale, currentLocale);
    int bestMatch = matchCount(preferredLocale, bestLocale);
    // if the new locale is better than current best we have
    if (currentMatch > bestMatch) {
      // take the new one
      return true;
    }
    // if neither the current best, nor the current value match the preferred locale at all...
    // (meaning that the preferred locale is something other than the default, and we don't have any string in that locale at all)
    // pick the one that is closest to the default
    return bestMatch == 0 && !defaultLocale.equals(preferredLocale) && matchCount(defaultLocale, currentLocale) > matchCount(defaultLocale, bestLocale);
  }

  /**
   * Get a score for "nearness" between two locales
   *
   * @param locale1 the first locale
   * @param locale2 the second locale
   * @return the score (10 for each match on part, 1 for mismatched parts when either one is an empty string, so "en_US" matches "en" better than "en_CA"
   */
  private static int matchCount(Locale locale1, Locale locale2) {
    int value = 0;
    String l1 = locale1.getLanguage();
    String l2 = locale2.getLanguage();
    if (l1.equals(l2)) {
      value += 10;
      String c1 = locale1.getCountry();
      String c2 = locale2.getCountry();
      if (c1.equals(c2)) {
        value += 10;
        String v1 = locale1.getVariant();
        String v2 = locale2.getVariant();
        if (v1.equals(v2)) {
          value += 10;
        }
        else if (v1.equals("") || v2.equals("")) {
          value++;
        }
      }
      else if (c1.equals("") || c2.equals("")) {
        value++;
      }
    }
    return value;
  }

  // Regex for BCP47 language tags (ignoring case, and allowing "_" instead of "-", since Java does that).
  // Full regex looks like this:
  // ^
  // (
  //  (
  //   (?<language>
  //    ([A-Za-z]{2,3}
  //     (-(?<extlang>[A-Za-z]{3}
  //       (-[A-Za-z]{3}){0,2}))?
  //    )
  //    |[A-Za-z]{4}|[A-Za-z]{5,8}
  //   )
  //   (-(?<script>[A-Za-z]{4}))?
  //   (-(?<region>[A-Za-z]{2}|[0-9]{3}))?
  //   (-(?<variant>[A-Za-z0-9]{5,8}|[0-9][A-Za-z0-9]{3}))*
  //   (-(?<extension>[0-9A-WY-Za-wy-z](-[A-Za-z0-9]{2,8})+))*
  //   (-(?<privateUse>x(-[A-Za-z0-9]{1,8})+))?
  //  )
  // |(?<privateUse>x(-[A-Za-z0-9]{1,8})+)
  // |(?<grandfathered>(en-GB-oed|i-ami|i-bnn|i-default|i-enochian
  //   |i-hak|i-klingon|i-lux|i-mingo|i-navajo|i-pwn|i-tao|i-tay
  //   |i-tsu|sgn-BE-FR|sgn-BE-NL|sgn-CH-DE)
  //   |(art-lojban|cel-gaulish|no-bok|no-nyn|zh-guoyu|zh-hakka|zh-min|zh-min-nan|zh-xiang))
  // )$
  // But that's a little beyond the scope here. We'll handle language, script and region:
  private static final Pattern bcp47 = Pattern.compile(
          "([A-Za-z]{2,3}(?:-[A-Za-z]{3}(?:-[A-Za-z]{3}){0,2})?|[A-Za-z]{4,8})" + // language
          "(?:[-_]([A-Za-z]{4}))?" + // script
          "(?:[-_]([A-Za-z]{2}|[0-9]{3}))?" + //region
          "(?:[-_]([A-Za-z0-9]{5,8}|[0-9][A-Za-z0-9]{3}))*" + // variants
          "(?:[-_](?:[0-9A-WY-Za-wy-z](-[A-Za-z0-9]{2,8})+))*" + // extensions
          "(?:[-_](?:x(-[A-Za-z0-9]{1,8})+))?"); // private use

  /**
   * Parse the given languageString (e.g., "en-us", "en-US", "en_us", "en_US") and create a Locale from it.
   * Parse but then ignore script, variants, extensions and private use.
   * Keep only the language and region.
   * This should not be needed in Java 1.7, which has Locale.forLangaugeTag(languageString), which does the same thing only better.
   * @param languageString - BCP47 or Java language string ("en", "en-us", "en_US", etc.)
   * @return Locale for that language.
   */
  public static Locale getSimpleLocale(String languageString) {
    Matcher m = bcp47.matcher(languageString);
    if (m.matches()) {
      String language = m.group(1);
      String script = m.group(2);
      String region = m.group(3);
      String variant = m.group(4);
      // Java 1.6 doesn't have a constructor that supports script...
      if (region != null) {
        if (variant != null) {
          return new Locale(language, region, variant);
        }
        else {
          return new Locale(language, region);
        }
      }
      return new Locale(language);
    }
    throw new IllegalArgumentException();
  }
}
