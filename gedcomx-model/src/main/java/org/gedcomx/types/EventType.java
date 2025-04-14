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
 * Enumeration of standard event types.
 */
@XmlQNameEnum(
    base = XmlQNameEnum.BaseType.URI
)
@Facet(GedcomxConstants.FACET_FS_FT_UNSUPPORTED)
@Schema(description = "EventType")
public enum EventType implements ControlledVocabulary {

  /**
   * An adoption event.
   */
  @JsonProperty("http://gedcomx.org/Adoption")
  Adoption,

  /**
   * An adult christening event.
   */
  @JsonProperty("http://gedcomx.org/AdultChristening")
  AdultChristening,

  /**
   * An annulment event of a marriage.
   */
  @JsonProperty("http://gedcomx.org/Annulment")
  Annulment,

  /**
   * A baptism event.
   */
  @JsonProperty("http://gedcomx.org/Baptism")
  Baptism,

  /**
   * A bar mitzvah event.
   */
  @JsonProperty("http://gedcomx.org/BarMitzvah")
  BarMitzvah,

  /**
   * A bat mitzvah event.
   */
  @JsonProperty("http://gedcomx.org/BatMitzvah")
  BatMitzvah,

  /**
   * A birth event.
   */
  @JsonProperty("http://gedcomx.org/Birth")
  Birth,

  /**
   * A an official blessing event, such as at the hands of a clergy member or at another religious rite.
   */
  @JsonProperty("http://gedcomx.org/Blessing")
  Blessing,

  /**
   * A burial event.
   */
  @JsonProperty("http://gedcomx.org/Burial")
  Burial,

  /**
   * A census event.
   */
  @JsonProperty("http://gedcomx.org/Census")
  Census,

  /**
   * A christening event *at birth*. Note: use `AdultChristening` for a christening event as an adult.
   */
  @JsonProperty("http://gedcomx.org/Christening")
  Christening,

  /**
   * A circumcision event.
   */
  @JsonProperty("http://gedcomx.org/Circumcision")
  Circumcision,

  /**
   * A confirmation event (or other rite of initiation) in a church or religion.
   */
  @JsonProperty("http://gedcomx.org/Confirmation")
  Confirmation,

  /**
   * A cremation event after death.
   */
  @JsonProperty("http://gedcomx.org/Cremation")
  Cremation,

  /**
   * A death event.
   */
  @JsonProperty("http://gedcomx.org/Death")
  Death,

  /**
   * A divorce event.
   */
  @JsonProperty("http://gedcomx.org/Divorce")
  Divorce,

  /**
   * A divorce filing event.
   */
  @JsonProperty("http://gedcomx.org/DivorceFiling")
  DivorceFiling,

  /**
   * A education or an educational achievement event (e.g. diploma, graduation, scholarship, etc.).
   */
  @JsonProperty("http://gedcomx.org/Education")
  Education,

  /**
   * An engagement to be married event.
   */
  @JsonProperty("http://gedcomx.org/Engagement")
  Engagement,

  /**
   * An emigration event.
   */
  @JsonProperty("http://gedcomx.org/Emigration")
  Emigration,

  /**
   * An enslavement event.
   */
  @JsonProperty("http://gedcomx.org/Enslavement")
  Enslavement,

  /**
   * An excommunication event from a church.
   */
  @JsonProperty("http://gedcomx.org/Excommunication")
  Excommunication,

  /**
   * A first communion event.
   */
  @JsonProperty("http://gedcomx.org/FirstCommunion")
  FirstCommunion,

  /**
   * A funeral event.
   */
  @JsonProperty("http://gedcomx.org/Funeral")
  Funeral,

  /**
   * An immigration event.
   */
  @JsonProperty("http://gedcomx.org/Immigration")
  Immigration,

  /**
   * A legal inquest. Inquests usually only occur when there’s something suspicious about the death. Inquests might in
   * some instances lead to a murder investigation. Most people that die have a death certificate wherein a doctor indicates
   * the cause of death and often indicates when the decedent was last seen by that physician; these require no inquest.
   */
  @JsonProperty("http://gedcomx.org/Inquest")
  Inquest,

  /**
   * A land transaction event.
   */
  @JsonProperty("http://gedcomx.org/LandTransaction")
  LandTransaction,

  /**
   * A marriage event.
   */
  @JsonProperty("http://gedcomx.org/Marriage")
  Marriage,

  /**
   * A military award event.
   */
  @JsonProperty("http://gedcomx.org/MilitaryAward")
  MilitaryAward,

  /**
   * A military discharge event.
   */
  @JsonProperty("http://gedcomx.org/MilitaryDischarge")
  MilitaryDischarge,

  /**
   * A mission event.
   */
  @JsonProperty("http://gedcomx.org/Mission")
  Mission,

  /**
   * An event of a move (i.e. change of residence) from a location.
   */
  @JsonProperty("http://gedcomx.org/MoveFrom")
  MoveFrom,

  /**
   * An event of a move (i.e. change of residence) to a location.
   */
  @JsonProperty("http://gedcomx.org/MoveTo")
  MoveTo,

  /**
   * A naturalization event (i.e. acquisition of citizenship and nationality).
   */
  @JsonProperty("http://gedcomx.org/Naturalization")
  Naturalization,

  /**
   * An ordination event.
   */
  @JsonProperty("http://gedcomx.org/Ordination")
  Ordination,

  /**
   * A retirement event.
   */
  @JsonProperty("http://gedcomx.org/Retirement")
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
