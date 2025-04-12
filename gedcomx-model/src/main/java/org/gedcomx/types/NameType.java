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
 * Enumeration of standard name types.
 */
@XmlQNameEnum (
  base = XmlQNameEnum.BaseType.URI
)
@Schema(description = "NameType", allowableValues = {"http://gedcomx.org/BirthName",
                                                     "http://gedcomx.org/DeathName",
                                                     "http://gedcomx.org/MarriedName",
                                                     "http://gedcomx.org/AlsoKnownAs",
                                                     "http://gedcomx.org/Nickname",
                                                     "http://gedcomx.org/AdoptiveName",
                                                     "http://gedcomx.org/FormalName",
                                                     "http://gedcomx.org/ReligiousName"})

public enum NameType implements ControlledVocabulary {

  /**
   * Name given at birth.
   */
  @Hidden
  BirthName,

  /**
   * Name used at the time of death.
   */
  @Hidden
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  DeathName,

  /**
   * Name accepted at marriage.
   */
  @Hidden
  MarriedName,

  /**
   * "Also known as" name.
   */
  @Hidden
  AlsoKnownAs,

  /**
   * Nickname.
   */
  @Hidden
  Nickname,

  /**
   * Name given at adoption.
   */
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  @Hidden
  AdoptiveName,

  /**
   * A formal name, usually given to distinguish it from a name more commonly used.
   */
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  @Hidden
  FormalName,

  /**
   * A name given at a religious rite or ceremony.
   */
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  @Hidden
  ReligiousName,

  @XmlUnknownQNameEnumValue
  @Hidden
  OTHER;

  private static final EnumURIMap<NameType> URI_MAP = new EnumURIMap<NameType>(NameType.class, GedcomxConstants.GEDCOMX_TYPES_NAMESPACE);

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
  public static NameType fromQNameURI(URI qname) {
    return URI_MAP.fromURIValue(qname);
  }

}
