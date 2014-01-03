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
package org.gedcomx.fileformat;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class GedcomxTimeStampUtil {

  private static final DatatypeFactory DATATYPE_FACTORY;
  private static final TimeZone UTC_TIME_ZONE;

  static {
    try {
      DATATYPE_FACTORY = DatatypeFactory.newInstance();
      UTC_TIME_ZONE = TimeZone.getTimeZone("UTC");
    }
    catch (DatatypeConfigurationException e) {
      throw new RuntimeException(e);
    }
  }

  public static String formatAsXmlUTC(Date date) {
    GregorianCalendar gc = new GregorianCalendar();
    gc.setTime(date);
    gc.setTimeZone(UTC_TIME_ZONE);
    return DATATYPE_FACTORY.newXMLGregorianCalendar(gc).toXMLFormat();
  }

  private GedcomxTimeStampUtil() { }  // added to remove "major" sonar warning
                                      // formatted to minimize impact on code coverage metrics
}
