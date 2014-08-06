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
    assertEquals("Spanish", LocaleUtil.findClosestLocale(list, new Locale("es")).getValue());
    assertEquals("UK English", LocaleUtil.findClosestLocale(list, new Locale("en-GB")).getValue());
    assertEquals("UK English", LocaleUtil.findClosestLocale(list, new Locale("en")).getValue()); // first English in list
    assertEquals("US English", LocaleUtil.findClosestLocale(list, new Locale("en-US")).getValue()); // first English in list
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
            new Locale("es"),
            new Locale("en-GB"),
            new Locale("en-US"),
            new Locale("ko")));
    assertEquals("es", LocaleUtil.findClosestLocale(list, new Locale("es")).getLanguage());
    assertEquals("en-GB", LocaleUtil.findClosestLocale(list, new Locale("en-GB")).getLanguage());
    assertEquals("en-GB", LocaleUtil.findClosestLocale(list, new Locale("en")).getLanguage()); // first English in list
    assertEquals("ko", LocaleUtil.findClosestLocale(list, Locale.KOREAN).getLanguage()); //ko
    assertEquals("ko", LocaleUtil.findClosestLocale(list, Locale.KOREA).getLanguage()); //ko-KR
    // Test a case where there is no good match to the preferred language, but there is to the default language.
    assertEquals("en-GB", LocaleUtil.findClosestLocale(list, Locale.JAPANESE, Locale.CANADA).getLanguage());
    assertEquals("en-US", LocaleUtil.findClosestLocale(list, Locale.JAPANESE, Locale.US).getLanguage());
  }

}