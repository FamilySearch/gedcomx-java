package org.gedcomx.util;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.gedcomx.common.TextValue;

import java.util.*;

/**
 * Class for...
 * User: Randy Wilson
 * Date: 7/31/2014
 * Time: 12:03 PM
 */
public class TestLocaleUtil extends TestCase {

  public void testTextValueLocales() {
    List<TextValue> list = Arrays.asList(
            value("Spanish", "es"),
            value("UK English", "en-GB"),
            value("US English", "en-US"),
            value("Korean", "ko"));
    assertEquals("Spanish", LocaleUtil.findClosestLocale(list, Locale.forLanguageTag("es")).getValue());
    assertEquals("UK English", LocaleUtil.findClosestLocale(list, Locale.forLanguageTag("en-GB")).getValue());
    assertEquals("UK English", LocaleUtil.findClosestLocale(list, Locale.forLanguageTag("en")).getValue()); // first English in list
    assertEquals("US English", LocaleUtil.findClosestLocale(list, Locale.forLanguageTag("en-US")).getValue()); // first English in list
    assertEquals("Korean", LocaleUtil.findClosestLocale(list, Locale.KOREAN).getValue()); //ko
    assertEquals("Korean", LocaleUtil.findClosestLocale(list, Locale.KOREA).getValue()); //ko-KR
    // Test a case where there is no good match to the preferred language, but there is to the default language.
    assertEquals("UK English", LocaleUtil.findClosestLocale(list, Locale.JAPANESE, Locale.CANADA).getValue());
    assertEquals("US English", LocaleUtil.findClosestLocale(list, Locale.JAPANESE, Locale.US).getValue());
  }

  private static TextValue value(String text, String lang) {
    TextValue v = new TextValue(text);
    v.setLang(lang);
    return v;
  }

  public void testLocales() {
    Set<Locale> list = new LinkedHashSet<Locale>(Arrays.asList(
            Locale.forLanguageTag("es"),
            Locale.forLanguageTag("en-GB"),
            Locale.forLanguageTag("en-US"),
            Locale.forLanguageTag("ko")));
    assertEquals("es", LocaleUtil.findClosestLocale(list, Locale.forLanguageTag("es")).toLanguageTag());
    assertEquals("en-GB", LocaleUtil.findClosestLocale(list, Locale.forLanguageTag("en-GB")).toLanguageTag());
    assertEquals("en-GB", LocaleUtil.findClosestLocale(list, Locale.forLanguageTag("en")).toLanguageTag()); // first English in list
    assertEquals("ko", LocaleUtil.findClosestLocale(list, Locale.KOREAN).toLanguageTag()); //ko
    assertEquals("ko", LocaleUtil.findClosestLocale(list, Locale.KOREA).toLanguageTag()); //ko-KR
    // Test a case where there is no good match to the preferred language, but there is to the default language.
    assertEquals("en-GB", LocaleUtil.findClosestLocale(list, Locale.JAPANESE, Locale.CANADA).toLanguageTag());
    assertEquals("en-US", LocaleUtil.findClosestLocale(list, Locale.JAPANESE, Locale.US).toLanguageTag());
  }

}