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
@Schema(description = "NameType")
public enum NameType implements ControlledVocabulary {

  /**
   * Name given at birth.
   */
  @JsonProperty("http://gedcomx.org/BirthName")
  BirthName,

  /**
   * Name used at the time of death.
   */
  @JsonProperty("http://gedcomx.org/DeathName")
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  DeathName,

  /**
   * Name accepted at marriage.
   */
  @JsonProperty("http://gedcomx.org/MarriedName")
  MarriedName,

  /**
   * "Also known as" name.
   */
  @JsonProperty("http://gedcomx.org/AlsoKnownAs")
  AlsoKnownAs,

  /**
   * Nickname.
   */
  @JsonProperty("http://gedcomx.org/Nickname")
  Nickname,

  /**
   * Name given at adoption.
   */
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  @JsonProperty("http://gedcomx.org/AdoptiveName")
  AdoptiveName,

  /**
   * A formal name, usually given to distinguish it from a name more commonly used.
   */
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  @JsonProperty("http://gedcomx.org/FormalName")
  FormalName,

  /**
   * A name given at a religious rite or ceremony.
   */
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  @JsonProperty("http://gedcomx.org/ReligiousName")
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
