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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.webcohesion.enunciate.metadata.qname.XmlQNameEnum;
import com.webcohesion.enunciate.metadata.qname.XmlUnknownQNameEnumValue;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;

import org.gedcomx.common.URI;
import org.gedcomx.rt.ControlledVocabulary;
import org.gedcomx.rt.EnumURIMap;
import org.gedcomx.rt.GedcomxConstants;


/**
 * Enumeration of standard source reference qualifiers.
 */
@XmlQNameEnum (
  base = XmlQNameEnum.BaseType.URI
)
@Schema(description = "SourceReferenceQualifierType")
public enum SourceReferenceQualifierType implements ControlledVocabulary {

  /**
   * A region of text in a digital document, in the form of `a,b` where `a` is the start character and `b` is the end character.
   */
  @JsonProperty(value = "http://gedcomx.org/CharacterRegion")
  CharacterRegion,

  /**
   * A rectangular region of a digital image. The value of the qualifier is interpreted as a series of four comma-separated numbers.
   * If all of the numbers is less than 1, the value is interpreted in the form of `x1,y1,x2,y2` where `x1,y1` is the relative percentage-based
   * coordinates of the top-left corner of the rectangle and `x2,y2` is the relative percentage-based coordinates of the bottom-right corner
   * of the rectangle. If any of the numbers is more than 1, the value is interpreted in the form of `x,y,w,h` where `x` is the point on the
   * X axis of the image in pixels, `y` is the point on the Y axis in pixels, `w` is the width of the rectangle in pixels, and `h` in the
   * height of the rectangle in pixels.
   */
  @JsonProperty(value = "http://gedcomx.org/RectangleRegion")
  RectangleRegion,

  /**
   * A region of time of an audio or video recording, in the form of `a,b` where `a` is the starting point in milliseconds
   * and `b` is the ending point in milliseconds.
   */
  @JsonProperty(value = "http://gedcomx.org/TimeRegion")
  TimeRegion,

  @XmlUnknownQNameEnumValue
  @Hidden
  OTHER;

  private static final EnumURIMap<SourceReferenceQualifierType> URI_MAP = new EnumURIMap<SourceReferenceQualifierType>(SourceReferenceQualifierType.class, GedcomxConstants.GEDCOMX_TYPES_NAMESPACE);

  /**
   * Return the QName value for this enum.
   *
   * @return The QName value for this enum.
   */
  public URI toQNameURI() {
    return URI_MAP.toURIValue(this);
  }

  /**
   * Get the enumeration from the QName.
   *
   * @param qname The qname.
   * @return The enumeration.
   */
  public static SourceReferenceQualifierType fromQNameURI(URI qname) {
    return URI_MAP.fromURIValue(qname);
  }

}
