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
 * Enumeration of standard name part qualifiers.
 */
@XmlQNameEnum (
  base = XmlQNameEnum.BaseType.URI
)
@Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
@Schema(description = "NamePartQualifierType", allowableValues = {"http://gedcomx.org/Title",
                                                                  "http://gedcomx.org/Primary",
                                                                  "http://gedcomx.org/Secondary",
                                                                  "http://gedcomx.org/Middle",
                                                                  "http://gedcomx.org/Familiar",
                                                                  "http://gedcomx.org/Religious",
                                                                  "http://gedcomx.org/Family",
                                                                  "http://gedcomx.org/Maiden",
                                                                  "http://gedcomx.org/Patronymic",
                                                                  "http://gedcomx.org/Matronymic",
                                                                  "http://gedcomx.org/Geographic",
                                                                  "http://gedcomx.org/Occupational",
                                                                  "http://gedcomx.org/Characteristic",
                                                                  "http://gedcomx.org/Postnom",
                                                                  "http://gedcomx.org/Particle"})
public enum NamePartQualifierType implements ControlledVocabulary {

  /**
   * A designation for honorifics (e.g. Dr., Rev., His Majesty, Haji), ranks (e.g. Colonel, General, Knight, Esquire), positions (e.g. Count, Chief, Father,
   * King) or other titles (e.g., PhD, MD)
   */
  @Hidden
  Title,

  /**
   * A designation for the name of most prominent in importance among the names of that type (e.g., the primary given name).
   */
  @Hidden
  Primary,

  /**
   * A designation for a name that is not primary in its importance among the names of that type (e.g., a secondary given name).
   */
  @Hidden
  Secondary,

  /**
   * A designation useful for cultures that designate a middle name that is distinct from a given name and a surname.
   */
  @Hidden
  Middle,

  /**
   * A designation for one's familiar name.
   */
  @Hidden
  Familiar,

  /**
   * A designation for a name given for religious purposes.
   */
  @Hidden
  Religious,

  /**
   * A name that associates a person with a group, such as a clan, tribe, or patriarchal hierarchy.
   */
  @Hidden
  Family,

  /**
   * A designation given by women to their original surname after they adopt a new surname upon marriage.
   */
  @Hidden
  Maiden,

  /**
   * A name derived from a paternal ancestor.
   */
  @Hidden
  Patronymic,

  /**
   * A name derived from a maternal ancestor.
   */
  @Hidden
  Matronymic,

  /**
   * A name derived from associated geography.
   */
  @Hidden
  Geographic,

  /**
   * A name derived from one's occupation.
   */
  @Hidden
  Occupational,

  /**
   * A name derived from a characteristic.
   */
  @Hidden
  Characteristic,

  /**
   * A name mandedated by law populations from Congo Free State / Belgian Congo / Congo / Democratic Republic of Congo (formerly Zaire).
   */
  @Hidden
  Postnom,

  /**
   * A grammatical designation for articles (a, the, dem, las, el, etc.), prepositions (of, from, aus, zu, op, etc.), initials (e.g. PhD, MD), annotations (e.g.
   * twin, spouse, infant, unknown), comparators (e.g. Junior, Senior, younger, little), ordinals (e.g. III, eighth), and conjunctions (e.g. and, or, nee, ou,
   * y, o, ne, &amp;).
   */
  @Hidden
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
