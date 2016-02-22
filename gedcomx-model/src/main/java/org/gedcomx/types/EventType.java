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

import org.codehaus.enunciate.Facet;
import org.codehaus.enunciate.qname.XmlQNameEnum;
import org.codehaus.enunciate.qname.XmlUnknownQNameEnumValue;
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
@Facet ( name = GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
public enum EventType implements ControlledVocabulary {

  /**
   * An adoption event.
   */
  Adoption,

  /**
   * An adult christening event.
   */
  AdultChristening,

  /**
   * An annulment event of a marriage.
   */
  Annulment,

  /**
   * A baptism event.
   */
  Baptism,

  /**
   * A bar mitzvah event.
   */
  BarMitzvah,

  /**
   * A bat mitzvah event.
   */
  BatMitzvah,

  /**
   * A birth event.
   */
  Birth,

  /**
   * A an official blessing event, such as at the hands of a clergy member or at another religious rite.
   */
  Blessing,

  /**
   * A burial event.
   */
  Burial,

  /**
   * A census event.
   */
  Census,

  /**
   * A christening event *at birth*. Note: use `AdultChristening` for a christening event as an adult.
   */
  Christening,

  /**
   * A circumcision event.
   */
  Circumcision,

  /**
   * A confirmation event (or other rite of initiation) in a church or religion.
   */
  Confirmation,

  /**
   * A cremation event after death.
   */
  Cremation,

  /**
   * A death event.
   */
  Death,

  /**
   * A divorce event.
   */
  Divorce,

  /**
   * A divorce filing event.
   */
  DivorceFiling,

  /**
   * A education or an educational achievement event (e.g. diploma, graduation, scholarship, etc.).
   */
  Education,

  /**
   * An engagement to be married event.
   */
  Engagement,

  /**
   * An emigration event.
   */
  Emigration,

  /**
   * An excommunication event from a church.
   */
  Excommunication,

  /**
   * A first communion event.
   */
  FirstCommunion,

  /**
   * A funeral event.
   */
  Funeral,

  /**
   * An immigration event.
   */
  Immigration,

  /**
   * A legal inquest. Inquests usually only occur when there’s something suspicious about the death. Inquests might in
   * some instances lead to a murder investigation. Most people that die have a death certificate wherein a doctor indicates
   * the cause of death and often indicates when the decedent was last seen by that physician; these require no inquest.
   */
  Inquest,

  /**
   * A land transaction event.
   */
  LandTransaction,

  /**
   * A marriage event.
   */
  Marriage,

  /**
   * A military award event.
   */
  MilitaryAward,

  /**
   * A military discharge event.
   */
  MilitaryDischarge,

  /**
   * A mission event.
   */
  Mission,

  /**
   * An event of a move (i.e. change of residence) from a location.
   */
  MoveFrom,

  /**
   * An event of a move (i.e. change of residence) to a location.
   */
  MoveTo,

  /**
   * A naturalization event (i.e. acquisition of citizenship and nationality).
   */
  Naturalization,

  /**
   * An ordination event.
   */
  Ordination,

  /**
   * A retirement event.
   */
  Retirement,


  @XmlUnknownQNameEnumValue
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
