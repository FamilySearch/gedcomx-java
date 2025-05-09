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
 * Enumeration of standard name part qualifiers.
 */
@XmlQNameEnum (
  base = XmlQNameEnum.BaseType.URI
)
@Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
@Schema(description = "NamePartQualifierType")
public enum NamePartQualifierType implements ControlledVocabulary {

  /**
   * A designation for honorifics (e.g. Dr., Rev., His Majesty, Haji),
   * ranks (e.g. Colonel, General, Knight, Esquire),
   * positions (e.g. Count, Chief, Father, King) or other titles (e.g., PhD, MD)
   */
  @JsonProperty(value = "http://gedcomx.org/Title")
  Title,

  /**
   * A designation for the name of most prominent in importance among the names of that type (e.g., the primary given name).
   */
  @JsonProperty(value = "http://gedcomx.org/Primary")
  Primary,

  /**
   * A designation for a name that is not primary in its importance among the names of that type (e.g., a secondary given name).
   */
  @JsonProperty(value = "http://gedcomx.org/Secondary")
  Secondary,

  /**
   * A designation useful for cultures that designate a middle name that is distinct from a given name and a surname.
   */
  @JsonProperty(value = "http://gedcomx.org/Middle")
  Middle,

  /**
   * A designation for one's familiar name.
   */
  @JsonProperty(value = "http://gedcomx.org/Familiar")
  Familiar,

  /**
   * A designation for a name given for religious purposes.
   */
  @JsonProperty(value = "http://gedcomx.org/Religious")
  Religious,

  /**
   * A name that associates a person with a group, such as a clan, tribe, or patriarchal hierarchy.
   */
  @JsonProperty(value = "http://gedcomx.org/Family")
  Family,

  /**
   * A designation given by women to their original surname after they adopt a new surname upon marriage.
   */
  @JsonProperty(value = "http://gedcomx.org/Maiden")
  Maiden,

  /**
   * A name derived from a paternal ancestor.
   */
  @JsonProperty(value = "http://gedcomx.org/Patronymic")
  Patronymic,

  /**
   * A name derived from a maternal ancestor.
   */
  @JsonProperty(value = "http://gedcomx.org/Matronymic")
  Matronymic,

  /**
   * A name derived from associated geography.
   */
  @JsonProperty(value = "http://gedcomx.org/Geographic")
  Geographic,

  /**
   * A name derived from one's occupation.
   */
  @JsonProperty(value = "http://gedcomx.org/Occupational")
  Occupational,

  /**
   * A name derived from a characteristic.
   */
  @JsonProperty(value = "http://gedcomx.org/Characteristic")
  Characteristic,

  /**
   * A name mandedated by law populations from Congo Free State / Belgian Congo / Congo / Democratic Republic of Congo (formerly Zaire).
   */
  @JsonProperty(value = "http://gedcomx.org/Postnom")
  Postnom,

  /**
   * A grammatical designation for articles (a, the, dem, las, el, etc.),
   * prepositions (of, from, aus, zu, op, etc.), initials (e.g. PhD, MD),
   * annotations (e.g. twin, spouse, infant, unknown),
   * comparators (e.g. Junior, Senior, younger, little), ordinals (e.g. III, eighth),
   * and conjunctions (e.g. and, or, nee, ou, y, o, ne, &amp;).
   */
  @JsonProperty(value = "http://gedcomx.org/Particle")
  Particle,

  @XmlUnknownQNameEnumValue
  @Hidden
  OTHER;

  private static final EnumURIMap<NamePartQualifierType> URI_MAP = new EnumURIMap<NamePartQualifierType>(NamePartQualifierType.class, GedcomxConstants.GEDCOMX_TYPES_NAMESPACE);

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
  public static NamePartQualifierType fromQNameURI(URI qname) {
    return URI_MAP.fromURIValue(qname);
  }

}
