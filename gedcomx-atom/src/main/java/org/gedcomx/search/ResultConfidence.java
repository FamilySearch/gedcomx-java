/**
 * Copyright 2011-2012 Intellectual Reserve, Inc.
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
package org.gedcomx.search;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * The levels of confidence of a search result.
 *
 * @author Ryan Heaton
 */
@XmlType ( name = "ResultConfidence" )
@XmlEnum ( Integer.class )
public enum ResultConfidence {

  /**
   * Result confidence level 1 (lowest)
   */
  @XmlEnumValue("1")
  one,

  /**
   * Result confidence level 2.
   */
  @XmlEnumValue("2")
  two,

  /**
   * Result confidence level 3.
   */
  @XmlEnumValue("3")
  three,

  /**
   * Result confidence level 4.
   */
  @XmlEnumValue("4")
  four,

  /**
   * Result confidence level 5.
   */
  @XmlEnumValue("5")
  five

}
