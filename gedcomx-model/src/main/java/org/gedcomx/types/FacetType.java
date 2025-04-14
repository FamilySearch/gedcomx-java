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
 * Enumeration of known facet types.
 *
 * @author Ryan Heaton
 */
@XmlQNameEnum (
  base = XmlQNameEnum.BaseType.URI
)
@Facet ( GedcomxConstants.FACET_GEDCOMX_RECORD )
@Schema(description = "FacetType")
public enum FacetType implements ControlledVocabulary {

  /**
   * A year.
   */
  @JsonProperty("http://gedcomx.org/Year")
  Year,

  /**
   * A (geographic) state.
   */
  @JsonProperty("http://gedcomx.org/State")
  State,

  /**
   * A province.
   */
  @JsonProperty("http://gedcomx.org/Province")
  Province,

  /**
   * A country.
   */
  @JsonProperty("http://gedcomx.org/Country")
  Country,

  /**
   * A city.
   */
  @JsonProperty("http://gedcomx.org/City")
  City,

  /**
   * A parish
   */
  @JsonProperty("http://gedcomx.org/Parish")
  Parish,

  /**
   * A township.
   */
  @JsonProperty("http://gedcomx.org/Township")
  Township,

  /**
   * A page.
   */
  @JsonProperty("http://gedcomx.org/Page")
  Page,

  /**
   * A volume.
   */
  @JsonProperty("http://gedcomx.org/Volume")
  Volume,

  /**
   * A date.
   */
  @JsonProperty("http://gedcomx.org/Date")
  Date,

  /**
   * A place.
   */
  @JsonProperty("http://gedcomx.org/Place")
  Place,

  /**
   * A name.
   */
  @JsonProperty("http://gedcomx.org/Name")
  Name,

  /**
   * A gender.
   */
  @JsonProperty("http://gedcomx.org/Gender")
  Gender,

  /**
   * Custom
   */
  @XmlUnknownQNameEnumValue
  @Hidden
  OTHER;

  private static final EnumURIMap<FacetType> URI_MAP = new EnumURIMap<FacetType>(FacetType.class, GedcomxConstants.GEDCOMX_TYPES_NAMESPACE);

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
  public static FacetType fromQNameURI(URI qname) {
    return URI_MAP.fromURIValue(qname);
  }

}
