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
 * Enumeration of standard event types.
 */
@XmlQNameEnum (
  base = XmlQNameEnum.BaseType.URI
)
@Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
@Schema(description = "EventType", allowableValues = {"http://gedcomx.org/Adoption",
                                                      "http://gedcomx.org/AdultChristening",
                                                      "http://gedcomx.org/Annulment",
                                                      "http://gedcomx.org/Baptism",
                                                      "http://gedcomx.org/BarMitzvah",
                                                      "http://gedcomx.org/BatMitzvah",
                                                      "http://gedcomx.org/Birth",
                                                      "http://gedcomx.org/Blessing",
                                                      "http://gedcomx.org/Burial",
                                                      "http://gedcomx.org/Census",
                                                      "http://gedcomx.org/Christening",
                                                      "http://gedcomx.org/Circumcision",
                                                      "http://gedcomx.org/Confirmation",
                                                      "http://gedcomx.org/Cremation",
                                                      "http://gedcomx.org/Death",
                                                      "http://gedcomx.org/Divorce",
                                                      "http://gedcomx.org/DivorceFiling",
                                                      "http://gedcomx.org/Education",
                                                      "http://gedcomx.org/Engagement",
                                                      "http://gedcomx.org/Emigration",
                                                      "http://gedcomx.org/Enslavement",
                                                      "http://gedcomx.org/Excommunication",
                                                      "http://gedcomx.org/FirstCommunion",
                                                      "http://gedcomx.org/Funeral",
                                                      "http://gedcomx.org/Immigration",
                                                      "http://gedcomx.org/Inquest",
                                                      "http://gedcomx.org/LandTransaction",
                                                      "http://gedcomx.org/Marriage",
                                                      "http://gedcomx.org/MilitaryAward",
                                                      "http://gedcomx.org/MilitaryDischarge",
                                                      "http://gedcomx.org/Mission",
                                                      "http://gedcomx.org/MoveFrom",
                                                      "http://gedcomx.org/MoveTo",
                                                      "http://gedcomx.org/Naturalization",
                                                      "http://gedcomx.org/Ordination",
                                                      "http://gedcomx.org/Retirement"})
public enum EventType implements ControlledVocabulary {

  /**
   * An adoption event.
   */
  @Hidden
  Adoption,

  /**
   * An adult christening event.
   */
  @Hidden
  AdultChristening,

  /**
   * An annulment event of a marriage.
   */
  @Hidden
  Annulment,

  /**
   * A baptism event.
   */
  @Hidden
  Baptism,

  /**
   * A bar mitzvah event.
   */
  @Hidden
  BarMitzvah,

  /**
   * A bat mitzvah event.
   */
  @Hidden
  BatMitzvah,

  /**
   * A birth event.
   */
  @Hidden
  Birth,

  /**
   * A an official blessing event, such as at the hands of a clergy member or at another religious rite.
   */
  @Hidden
  Blessing,

  /**
   * A burial event.
   */
  @Hidden
  Burial,

  /**
   * A census event.
   */
  @Hidden
  Census,

  /**
   * A christening event *at birth*. Note: use `AdultChristening` for a christening event as an adult.
   */
  @Hidden
  Christening,

  /**
   * A circumcision event.
   */
  @Hidden
  Circumcision,

  /**
   * A confirmation event (or other rite of initiation) in a church or religion.
   */
  @Hidden
  Confirmation,

  /**
   * A cremation event after death.
   */
  @Hidden
  Cremation,

  /**
   * A death event.
   */
  @Hidden
  Death,

  /**
   * A divorce event.
   */
  @Hidden
  Divorce,

  /**
   * A divorce filing event.
   */
  @Hidden
  DivorceFiling,

  /**
   * A education or an educational achievement event (e.g. diploma, graduation, scholarship, etc.).
   */
  @Hidden
  Education,

  /**
   * An engagement to be married event.
   */
  @Hidden
  Engagement,

  /**
   * An emigration event.
   */
  @Hidden
  Emigration,

  /**
   * An enslavement event.
   */
  @Hidden
  Enslavement,

  /**
   * An excommunication event from a church.
   */
  @Hidden
  Excommunication,

  /**
   * A first communion event.
   */
  @Hidden
  FirstCommunion,

  /**
   * A funeral event.
   */
  @Hidden
  Funeral,

  /**
   * An immigration event.
   */
  @Hidden
  Immigration,

  /**
   * A legal inquest. Inquests usually only occur when there’s something suspicious about the death. Inquests might in
   * some instances lead to a murder investigation. Most people that die have a death certificate wherein a doctor indicates
   * the cause of death and often indicates when the decedent was last seen by that physician; these require no inquest.
   */
  @Hidden
  Inquest,

  /**
   * A land transaction event.
   */
  @Hidden
  LandTransaction,

  /**
   * A marriage event.
   */
  @Hidden
  Marriage,

  /**
   * A military award event.
   */
  @Hidden
  MilitaryAward,

  /**
   * A military discharge event.
   */
  @Hidden
  MilitaryDischarge,

  /**
   * A mission event.
   */
  @Hidden
  Mission,

  /**
   * An event of a move (i.e. change of residence) from a location.
   */
  @Hidden
  MoveFrom,

  /**
   * An event of a move (i.e. change of residence) to a location.
   */
  @Hidden
  MoveTo,

  /**
   * A naturalization event (i.e. acquisition of citizenship and nationality).
   */
  @Hidden
  Naturalization,

  /**
   * An ordination event.
   */
  @Hidden
  Ordination,

  /**
   * A retirement event.
   */
  @Hidden
  Retirement,


  @XmlUnknownQNameEnumValue
  @Hidden
  OTHER;

  private static final EnumURIMap<EventType> URI_MAP = new EnumURIMap<EventType>(EventType.class, GedcomxConstants.GEDCOMX_TYPES_NAMESPACE);

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
  public static EventType fromQNameURI(URI qname) {
    return URI_MAP.fromURIValue(qname);
  }

}
