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
 * Enumeration of standard event roles.
 */
@XmlQNameEnum (
  base = XmlQNameEnum.BaseType.URI
)
@Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
@Schema(description = "EventRoleType", allowableValues = {"http://gedcomx.org/Principal",
                                                          "http://gedcomx.org/Participant",
                                                          "http://gedcomx.org/Official",
                                                          "http://gedcomx.org/Witness"})
public enum EventRoleType implements ControlledVocabulary {

  @Hidden
  Principal,

  @Hidden
  Participant,

  @Hidden
  Official,

  @Hidden
  Witness,

  @XmlUnknownQNameEnumValue
  @Hidden
  OTHER;

  private static final EnumURIMap<EventRoleType> URI_MAP = new EnumURIMap<EventRoleType>(EventRoleType.class, GedcomxConstants.GEDCOMX_TYPES_NAMESPACE);

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
  public static EventRoleType fromQNameURI(URI qname) {
    return URI_MAP.fromURIValue(qname);
  }

}