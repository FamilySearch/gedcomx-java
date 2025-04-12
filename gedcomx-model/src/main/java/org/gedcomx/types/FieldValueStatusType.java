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

import com.webcohesion.enunciate.metadata.Facet;
import com.webcohesion.enunciate.metadata.qname.XmlQNameEnum;
import com.webcohesion.enunciate.metadata.qname.XmlUnknownQNameEnumValue;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;

import org.gedcomx.common.URI;
import org.gedcomx.rt.ControlledVocabulary;
import org.gedcomx.rt.EnumURIMap;
import org.gedcomx.rt.GedcomxConstants;

/**
 * Class representing the status of a FieldValue. Using a status avoids having to use special strings in the
 *   value itself, such as "_Blank_".
 * User: Randy Wilson
 * Date: 11/25/2014
 * Time: 12:04 PM
 */
@XmlQNameEnum(
        base = XmlQNameEnum.BaseType.URI
)
@Facet( GedcomxConstants.FACET_GEDCOMX_RECORD )
@Schema(description = "FieldValueStatusType", allowableValues = {"http://gedcomx.org/Blank",
                                                                 "http://gedcomx.org/Unreadable"})
public enum FieldValueStatusType implements ControlledVocabulary {
  /**
   * "Intentionally left blank:
   * - For an Original field value, this means the field itself was blank.
   * - For an Interpreted field value, it means that the Original value was bogus or meaningless, so
   *   the field should be treated as if blank.
   */
  @Hidden
  Blank,
  @Hidden
  Unreadable, // The field couldn't be read.


  /**
   * Custom
   */
  @XmlUnknownQNameEnumValue
  @Hidden
  OTHER;

  private static final EnumURIMap<FieldValueStatusType> URI_MAP = new EnumURIMap<FieldValueStatusType>(FieldValueStatusType.class, GedcomxConstants.GEDCOMX_TYPES_NAMESPACE);

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
  public static FieldValueStatusType fromQNameURI(URI qname) {
    return URI_MAP.fromURIValue(qname);
  }
}
