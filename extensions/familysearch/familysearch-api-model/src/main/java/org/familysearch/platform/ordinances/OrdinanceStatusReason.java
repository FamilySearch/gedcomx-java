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
package org.familysearch.platform.ordinances;

import com.webcohesion.enunciate.metadata.qname.XmlQNameEnum;
import com.webcohesion.enunciate.metadata.qname.XmlUnknownQNameEnumValue;

import org.familysearch.platform.FamilySearchPlatform;
import org.gedcomx.common.URI;
import org.gedcomx.rt.ControlledVocabulary;
import org.gedcomx.rt.EnumURIMap;

/**
 * Reasons a status might exist.
 */
@XmlQNameEnum(
  base = XmlQNameEnum.BaseType.URI
)
public enum OrdinanceStatusReason implements ControlledVocabulary {

  /** The "sealing to parent" ordinance is not needed when an individual is "born in the covenant". */
  BornInCovenant,

  /** The "sealing to spouse" ordinance requires a couple relationship between the couple. */
  CoupleRelationshipMissing,

  /** The person's death date cannot be before his/her birthdate. */
  DeathBeforeBirth,

  /** Baptism, Confirmation, Initiatory, Endowment, and Sealing to Spouse ordinances are not needed if a person died before the age of eight. */
  DiedBeforeAgeEight,

  /** There's a death or burial date that cannot be standardized.  Only applied when a person was born recently enough (110 years) that the death date should be findable and able to be standardized. */
  DeathDateReformatNeeded,

  /** A person's given name(s) cannot contain one or more invalid words or be represented by a traditional prefix such as Mr., Miss, Mrs., etc. */
  InvalidGivenNamePiece,

  /** This person's name contains one or more invalid words. */
  InvalidName,

  /** This person's name contains an invalid name prefix. */
  InvalidNamePrefix,

  /** This person's name contains an invalid name suffix. */
  InvalidNameSuffix,

  /** A person's name cannot include descriptors such as Child, Baby, Son, Daughter, Sister, Brother, Aunt, Uncle, etc. */
  InvalidSingleNamePiece,

  /** A person's name cannot include invalid characters. */
  InvalidSpecialCharacterName,

  /** A person's surname contains invalid words or characters. A person's surname cannot be a descriptor such as nephew, niece, spouse, twin, etc. */
  InvalidSurname,

  /** A person has a title but no given name. A male person with a title and surname name must have a given name */
  InvalidTitleGivenMissing,

  /** The person's marriage fact date occurs before the person is eight years old */
  MarriedTooYoung,

  /** A person who lived before A.D. 1500. */
  @Deprecated   // Replaced by RESTRICTED_DATE 
  Medieval,

  /** A person must have a standardized date reference. */
  MissingStandardizedDate,

  /** A person must have a standardized place reference. */
  MissingStandardizedPlace,

  /** A person's surname cannot contain the word "Mister". If the person's surname is Mister, then it should be the only word in the last name. */
  MisterAsOnlySurname,

  /** A person cannot have a name that is only initials. */
  NameContainsOnlyInitials,

  /** One or more characters in the name do not match the designated language script of the name. */
  NameLangScriptMismatch,

  /** The language script for the name is undefined and the name contains multiple scripts. */
  NameLangUndWithMultipleScripts,

  /** A person's full name (any name form) cannot be more than 255 characters. */
  NameTooLong,

  /** This ordinance is for a person born too recently and the current user is not an immediate relative. */
  NeedPermission,

  /** A person's record needs a given name or surname. */
  NoName,

  /** A person has been declared not accountable. */
  NotAccountable,

  /** A person's ordinance status is not available. Please contact FamilySearch Support if you are a direct descendant and need more information. */
  NotAvailable,

  /** A person has not been deceased for one year. */
  @Deprecated   // Replaced by TOO_RECENTLY_DECEASED
  NotDeadAtLeastOneYear,

  /** A person must have enough event or relationship information, such as a birthdate and place, a death date and place, etc. A person's record must have enough date or place information for the system to be able to determine whether the ordinance is already done. */
  NotMatchableUsingEvents,

  /** A person must have has enough event or relationship information. A person's record must have enough relationship information for the system to be able to determine whether the ordinance is already done. */
  NotMatchableUsingRelationships,

  /** The ordinance must be a valid temple ordinance. The Family Tree cannot be used to reserve ordinances of this type. */
  NotTempleOrdinance,

  /** This person is still listed as living. */
  OfficialCompletedOrdinanceForLiving,

  /** This is an official completed ordinance. */
  OfficialCompletedOrdinance,

  /** Latin surnames cannot consist of only one letter. */
  OneLatinLetterSurname,

  /** ONE_NAME_PER_SCRIPT_TYPE maps to "one.name.per.script.type" */
  OneNamePerScriptType,

  /** A "sealing to parent" ordinance requires the person to have both child-to-father and child-to-mother relationships. */
  ParentRelationshipMissing,

  /** A "sealing to parent" ordinance requires the father and mother to have a couple relationship. */
  ParentsCoupleRelationshipMissing,

  /** A person's name cannot contain repeated punctuation characters, such as .., --, etc. */
  RepeatingSpecialCharacterName,

  /** The ordinance is reserved. */
  Reserved,

  /** A person who lived before 200 A.D. */
  RestrictedDate,

  /** A sealing to spouse must involve a husband and a wife. A sealing to parents must involve a father and a mother. */
  SameSex,

  /** A person cannot be sealed to themselves. */
  SealingToSelf,

  /** A stillborn person does not need ordinances. */
  Stillborn,

  /** The person has not been deceased long enough to qualify for temple ordinances. */
  TooRecentlyDeceased,

  /** A person must have a known gender, male or female. */
  UnknownGender,

  /** This status reason is unknown */
  @XmlUnknownQNameEnumValue
  OTHER;

  private static final EnumURIMap<OrdinanceStatusReason> URI_MAP = new EnumURIMap<>(OrdinanceStatusReason.class, FamilySearchPlatform.NAMESPACE);

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
  public static OrdinanceStatusReason fromQNameURI(URI qname) {
    return URI_MAP.fromURIValue(qname);
  }

}
