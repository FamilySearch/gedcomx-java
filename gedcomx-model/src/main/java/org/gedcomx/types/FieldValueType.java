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
 * Enumeration of known field value types.
 *
 * @author Ryan Heaton
 */
@XmlQNameEnum (
  base = XmlQNameEnum.BaseType.URI
)
@Facet ( GedcomxConstants.FACET_GEDCOMX_RECORD )
@Schema(description = "FieldValueType", allowableValues = {"http://gedcomx.org/Original",
                                                          "http://gedcomx.org/Interpreted"})
public enum FieldValueType implements ControlledVocabulary {

  /**
   * The field value is original, extracted directly from the record. What you see is what you get, including misspellings and other errors in the record.
   */
  @Hidden
  Original,

  /**
   * The field value is interpreted, meaning a user or other automated process applied some reasoning to interpret the value.
   */
  @Hidden
  Interpreted,

  /**
   * Custom
   */
  @XmlUnknownQNameEnumValue
  @Hidden
  OTHER;

  private static final EnumURIMap<FieldValueType> URI_MAP = new EnumURIMap<FieldValueType>(FieldValueType.class, GedcomxConstants.GEDCOMX_TYPES_NAMESPACE);

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
  public static FieldValueType fromQNameURI(URI qname) {
    return URI_MAP.fromURIValue(qname);
  }

}
