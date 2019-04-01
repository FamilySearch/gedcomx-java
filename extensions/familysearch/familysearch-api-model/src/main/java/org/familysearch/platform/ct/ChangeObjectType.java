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
package org.familysearch.platform.ct;

import com.webcohesion.enunciate.metadata.qname.XmlQNameEnum;
import com.webcohesion.enunciate.metadata.qname.XmlQNameEnumValue;
import org.familysearch.platform.FamilySearchPlatform;
import org.gedcomx.common.URI;
import org.gedcomx.rt.ControlledVocabulary;
import org.gedcomx.rt.EnumURIMap;
import org.gedcomx.rt.GedcomxConstants;

/**
 * Enumeration of the possible change object types.
 *
 * @author Ryan Heaton
 */
@XmlQNameEnum (
  base = XmlQNameEnum.BaseType.URI
)
public enum ChangeObjectType implements ControlledVocabulary {

  /**
   * A person was changed.
   */
  @XmlQNameEnumValue( namespace = GedcomxConstants.GEDCOMX_TYPES_NAMESPACE )
  Person,

  /**
   * A couple relationship was changed.
   */
  @XmlQNameEnumValue( namespace = GedcomxConstants.GEDCOMX_TYPES_NAMESPACE )
  Couple,

  /**
   * A child-and-parents relationship was changed.
   */
  ChildAndParentsRelationship,

  /**
   * The Spouse1 in a couple relationship was changed.
   */
  Spouse1,

  /**
   * The Spouse2 in a couple relationship was changed.
   */
  Spouse2,

  /**
   * The Parent1 in a couple-child relationship was changed.
   */
  Parent1,

  /**
   * The Parent2 in a couple-child relationship was changed.
   */
  Parent2,


  // todo GenericRelationshipTerms cleanup    remove @Deprecated values
  /////////////////////
  /**
   * Deprecated: The man in a couple relationship was changed.  Use Spouse1.
   */
  @Deprecated
  Man,
  /**
   * Deprecated: The woman in a couple relationship was changed.  Use Spouse2.
   */
  @Deprecated
  Woman,
  /**
   * Deprecated: The father in a couple-child relationship was changed.  Use Parent1.
   */
  @Deprecated
  Father,
  /**
   * Deprecated: The mother in a couple-child relationship was changed.  Use Parent2.
   */
  @Deprecated
  Mother,
  /////////////////////

  /**
   * The child in a couple-child relationship was changed.
   */
  Child,

  /**
   * A source reference was changed.
   */
  @XmlQNameEnumValue( namespace = GedcomxConstants.GEDCOMX_TYPES_NAMESPACE )
  SourceReference,

  /**
   * A discussion reference was changed.
   */
  DiscussionReference,

  /**
   * An evidence reference was changed.
   */
  EvidenceReference,

  /**
   * An affiliation fact was changed.
   */
  Affiliation,

  /**
   * An annulment fact was changed.
   */
  @XmlQNameEnumValue( namespace = GedcomxConstants.GEDCOMX_TYPES_NAMESPACE )
  Annulment,

  /**
   * A bar mitzvah was changed.
   */
  @XmlQNameEnumValue( namespace = GedcomxConstants.GEDCOMX_TYPES_NAMESPACE )
  BarMitzvah,

  /**
   * A bar mitzvah was changed.
   */
  @XmlQNameEnumValue( namespace = GedcomxConstants.GEDCOMX_TYPES_NAMESPACE )
  BatMitzvah,

  /**
   * A birth fact was changed.
   */
  @XmlQNameEnumValue( namespace = GedcomxConstants.GEDCOMX_TYPES_NAMESPACE )
  Birth,

  /**
   * A burial fact was changed.
   */
  @XmlQNameEnumValue( namespace = GedcomxConstants.GEDCOMX_TYPES_NAMESPACE )
  Burial,

  /**
   * A christening fact was changed.
   */
  @XmlQNameEnumValue( namespace = GedcomxConstants.GEDCOMX_TYPES_NAMESPACE )
  Christening,

  /**
   * A cremation fact was changed.
   */
  @XmlQNameEnumValue( namespace = GedcomxConstants.GEDCOMX_TYPES_NAMESPACE )
  Cremation,

  /**
   * A common law marriage fact was changed.
   */
  @XmlQNameEnumValue( namespace = GedcomxConstants.GEDCOMX_TYPES_NAMESPACE )
  CommonLawMarriage,

  /**
   * A death fact was changed.
   */
  @XmlQNameEnumValue( namespace = GedcomxConstants.GEDCOMX_TYPES_NAMESPACE )
  Death,

  /**
   * A divorce fact was changed.
   */
  @XmlQNameEnumValue( namespace = GedcomxConstants.GEDCOMX_TYPES_NAMESPACE )
  Divorce,

  /**
   * A marriage fact was changed.
   */
  @XmlQNameEnumValue( namespace = GedcomxConstants.GEDCOMX_TYPES_NAMESPACE )
  Marriage,

  /**
   * A military service fact was changed.
   */
  @XmlQNameEnumValue( namespace = GedcomxConstants.GEDCOMX_TYPES_NAMESPACE )
  MilitaryService,

  /**
   * A naturalization fact was changed.
   */
  @XmlQNameEnumValue( namespace = GedcomxConstants.GEDCOMX_TYPES_NAMESPACE )
  Naturalization,

  /**
   * An occupation fact was changed.
   */
  @XmlQNameEnumValue( namespace = GedcomxConstants.GEDCOMX_TYPES_NAMESPACE )
  Occupation,

  /**
   * A religion fact was changed.
   */
  @XmlQNameEnumValue( namespace = GedcomxConstants.GEDCOMX_TYPES_NAMESPACE )
  Religion,

  /**
   * A residence fact was changed.
   */
  @XmlQNameEnumValue( namespace = GedcomxConstants.GEDCOMX_TYPES_NAMESPACE )
  Residence,

  /**
   * A stillbirth fact was changed.
   */
  @XmlQNameEnumValue( namespace = GedcomxConstants.GEDCOMX_TYPES_NAMESPACE )
  Stillbirth,

  /**
   * A fact was changed.
   */
  @XmlQNameEnumValue( namespace = GedcomxConstants.GEDCOMX_TYPES_NAMESPACE )
  Fact,

  /**
   * A caste fact was changed.
   */
  @XmlQNameEnumValue( namespace = GedcomxConstants.GEDCOMX_TYPES_NAMESPACE )
  Caste,

  /**
   * A clan fact was changed.
   */
  @XmlQNameEnumValue( namespace = GedcomxConstants.GEDCOMX_TYPES_NAMESPACE )
  Clan,

  /**
   * A national id fact was changed.
   */
  @XmlQNameEnumValue( namespace = GedcomxConstants.GEDCOMX_TYPES_NAMESPACE )
  NationalId,

  /**
   * A nationality fact was changed.
   */
  @XmlQNameEnumValue( namespace = GedcomxConstants.GEDCOMX_TYPES_NAMESPACE )
  Nationality,

  /**
   * A physical description fact was changed.
   */
  @XmlQNameEnumValue( namespace = GedcomxConstants.GEDCOMX_TYPES_NAMESPACE )
  PhysicalDescription,

  /**
   * An ethnicity fact was changed.
   */
  @XmlQNameEnumValue( namespace = GedcomxConstants.GEDCOMX_TYPES_NAMESPACE )
  Ethnicity,

  /**
   * A gender was changed.
   */
  @XmlQNameEnumValue( namespace = GedcomxConstants.GEDCOMX_TYPES_NAMESPACE )
  Gender,

  /**
   * A note was changed.
   */
  @XmlQNameEnumValue( namespace = GedcomxConstants.GEDCOMX_TYPES_NAMESPACE )
  Note,

  /**
   * Name was changed.
   */
  @XmlQNameEnumValue( namespace = GedcomxConstants.GEDCOMX_TYPES_NAMESPACE )
  Name,

  /**
   * A birth name was changed.
   */
  @XmlQNameEnumValue( namespace = GedcomxConstants.GEDCOMX_TYPES_NAMESPACE )
  BirthName,

  /**
   * An AKA name was changed.
   */
  @XmlQNameEnumValue( namespace = GedcomxConstants.GEDCOMX_TYPES_NAMESPACE )
  AlsoKnownAs,

  /**
   * An married name was changed.
   */
  @XmlQNameEnumValue( namespace = GedcomxConstants.GEDCOMX_TYPES_NAMESPACE )
  MarriedName,

  /**
   * An nickname was changed.
   */
  @XmlQNameEnumValue( namespace = GedcomxConstants.GEDCOMX_TYPES_NAMESPACE )
  Nickname,

  /**
   * A died before eight fact was changed.
   */
  DiedBeforeEight,

  /**
   * A tribe name fact was changed.
   */
  TribeName,

  /**
   * A birth order fact was changed.
   */
  BirthOrder,

  /**
   * A life sketch was changed.
   */
  LifeSketch,

  /**
   * A living status was changed.
   */
  LivingStatus,

  /**
   * A title of nobility fact was changed.
   */
  TitleOfNobility,

  /**
   * LDS baptism ordinance.
   */
  Baptism,

  /**
   * LDS confirmation ordinance.
   */
  Confirmation,

  /**
   * LDS confirmation ordinance.
   */
  Initiatory,

  /**
   * LDS endowment ordinance.
   */
  Endowment,

  /**
   * LDS sealing ordinance.
   */
  Sealing,

  /**
   * A not-a-match declaration
   */
  NotAMatch;

  private static final EnumURIMap<ChangeObjectType> URI_MAP = new EnumURIMap<ChangeObjectType>(ChangeObjectType.class, FamilySearchPlatform.NAMESPACE);

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
  public static ChangeObjectType fromQNameURI(URI qname) {
    return URI_MAP.fromURIValue(qname);
  }



}
