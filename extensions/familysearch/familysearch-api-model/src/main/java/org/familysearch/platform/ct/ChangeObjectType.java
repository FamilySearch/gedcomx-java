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

import org.codehaus.enunciate.qname.XmlQNameEnum;
import org.codehaus.enunciate.qname.XmlQNameEnumValue;
import org.gedcomx.common.URI;
import org.gedcomx.rt.GedcomxConstants;

/**
 * Enumeration of the possible change object types.
 *
 * @author Ryan Heaton
 */
@XmlQNameEnum (
  base = XmlQNameEnum.BaseType.URI
)
public enum ChangeObjectType {

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
   * A couple-child relationship was changed.
   * @deprecated use {@link #ChildAndParentsRelationship}
   */
  @Deprecated
  CoupleChildRelationship,
  ChildAndParentsRelationship,

  /**
   * The man in a couple relationship was changed.
   */
  Man,

  /**
   * The man in a couple relationship was changed.
   */
  Woman,

  /**
   * The father in a couple-child relationship was changed.
   */
  Father,

  /**
   * The mother in a couple-child relationship was changed.
   */
  Mother,

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
   * A death fact was changed.
   */
  @XmlQNameEnumValue( namespace = GedcomxConstants.GEDCOMX_TYPES_NAMESPACE )
  Death,

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
   * A title of nobility fact was changed.
   */
  TitleOfNobility,

  /**
   * A not-a-match declaration
   */
  NotAMatch;

  /**
   * Return the QName value for this enum.
   *
   * @return The QName value for this enum.
   */
  public URI toQNameURI() {
    return URI.create(org.codehaus.enunciate.XmlQNameEnumUtil.toURIValue(this));
  }

  /**
   * Get the enumeration from the QName.
   *
   * @param qname The qname.
   * @return The enumeration.
   */
  public static ChangeObjectType fromQNameURI(URI qname) {
    return org.codehaus.enunciate.XmlQNameEnumUtil.fromURIValue(qname.toString(), ChangeObjectType.class);
  }



}
