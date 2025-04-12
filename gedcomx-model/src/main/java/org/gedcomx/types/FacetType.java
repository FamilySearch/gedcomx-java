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
 * Enumeration of known facet types.
 *
 * @author Ryan Heaton
 */
@XmlQNameEnum (
  base = XmlQNameEnum.BaseType.URI
)
@Facet ( GedcomxConstants.FACET_GEDCOMX_RECORD )
@Schema(description = "FacetType", allowableValues = {"http://gedcomx.org/Year",
                                                      "http://gedcomx.org/State",
                                                      "http://gedcomx.org/Province",
                                                      "http://gedcomx.org/Country",
                                                      "http://gedcomx.org/City",
                                                      "http://gedcomx.org/Parish",
                                                      "http://gedcomx.org/Township",
                                                      "http://gedcomx.org/Page",
                                                      "http://gedcomx.org/Volume",
                                                      "http://gedcomx.org/Date",
                                                      "http://gedcomx.org/Place",
                                                      "http://gedcomx.org/Name",
                                                      "http://gedcomx.org/Gender"})
public enum FacetType implements ControlledVocabulary {

  /**
   * A year.
   */
  @Hidden
  Year,

  /**
   * A (geographic) state.
   */
  @Hidden
  State,

  /**
   * A province.
   */
  @Hidden
  Province,

  /**
   * A country.
   */
  @Hidden
  Country,

  /**
   * A city.
   */
  @Hidden
  City,

  /**
   * A parish
   */
  @Hidden
  Parish,

  /**
   * A township.
   */
  @Hidden
  Township,

  /**
   * A page.
   */
  @Hidden
  Page,

  /**
   * A volume.
   */
  @Hidden
  Volume,

  /**
   * A date.
   */
  @Hidden
  Date,

  /**
   * A place.
   */
  @Hidden
  Place,

  /**
   * A name.
   */
  @Hidden
  Name,

  /**
   * A gender.
   */
  @Hidden
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
