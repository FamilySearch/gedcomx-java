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
package org.gedcomx.types;

import org.codehaus.enunciate.qname.XmlQNameEnum;
import org.codehaus.enunciate.qname.XmlUnknownQNameEnumValue;
import org.gedcomx.common.URI;


/**
 * Enumeration of standard source reference qualifiers.
 */
@XmlQNameEnum (
  base = XmlQNameEnum.BaseType.URI
)
public enum SourceReferenceQualifierType {

  /**
   * A region of text in a digital document, in the form of `a,b` where `a` is the start character and `b` is the end character.
   */
  CharacterRegion,

  /**
   * A rectangular region of a digital image. The value of the qualifier is interpreted as a series of four comma-separated numbers.
   * If all of the numbers is less than 1, the value is interpreted in the form of `x1,y1,x2,y2` where `x1,y1` is the relative percentage-based
   * coordinates of the top-left corner of the rectangle and `x2,y2` is the relative percentage-based coordinates of the bottom-right corner
   * of the rectangle. If any of the numbers is more than 1, the value is interpreted in the form of `x,y,w,h` where `x` is the point on the
   * X axis of the image in pixels, `y` is the point on the Y axis in pixels, `w` is the width of the rectangle in pixels, and `h` in the
   * height of the rectangle in pixels.
   */
  RectangleRegion,

  /**
   * A region of time of an audio or video recording, in the form of `a,b` where `a` is the starting point in milliseconds
   * and `b` is the ending point in milliseconds.
   */
  TimeRegion,

  @XmlUnknownQNameEnumValue
  OTHER;

  /**
   * Return the QName value for this enum.
   *
   * @return The QName value for this enum.
   */
  public URI toQNameURI() {
    return URI.create(org.codehaus.enunciate.XmlQNameEnumUtil.toURIValue(this));
  }

  /**
   * Get the enumeration from the QName.
   *
   * @param qname The qname.
   * @return The enumeration.
   */
  public static SourceReferenceQualifierType fromQNameURI(URI qname) {
    return org.codehaus.enunciate.XmlQNameEnumUtil.fromURIValue(qname.toString(), SourceReferenceQualifierType.class);
  }

}
