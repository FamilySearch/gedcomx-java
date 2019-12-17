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

  /**
   * The "sealing to parent" ordinance is not needed when an individual is "born in the covenant".  Parent relationships are checked.
   */
  BornInCovenantRule,

  /**
   * The "sealing to spouse" ordinance requires a spousal relationship between the couple.
   */
  CoupleRelationshipMissingRule,

  /**
   * Baptism, Confirmation, Initiatory, Endowment, and Sealing to Spouse ordinances are not needed if a person died before the age of eight.
   */
  DiedBeforeAgeEightRule,

  /**
   * A person's given name(s) cannot contain one or more invalid words or be represented by a traditional prefix such as Mr., Miss, Mrs., etc.
   */
  InvalidGivenNamePieceRule,

  /**
   * This person's name contains one or more invalid words.
   */
  InvalidNameRule,

  /**
   * A person's name cannot include descriptors such as Child, Baby, Son, Daughter, Sister, Brother, Aunt, Uncle, etc.
   */
  InvalidSingleNamePieceRule,

  /**
   * A person's name cannot include invalid characters.
   */
  InvalidSpecialCharacterNameRule,

  /**
   * A person's surname contains invalid words or characters.
   * A person's surname cannot be a descriptor such as nephew, niece, spouse, twin, etc.
   */
  InvalidSurnameRule,

  /**
   * A person has a title but no given name.
   * A male person with a title and surname name must have a given name
   */
  InvalidTitleGivenMissingRule,

  /**
   * A person who lived before A.D. 1500.
   */
  MedievalRule,

  /**
   * A person must have a standardized place reference.
   */
  MissingStandardizedPlaceRule,

  /**
   * A person must have a standardized date reference.
   */
  MissingStandardizedDateRule,

  /**
   * A person's surname cannot contain the word "Mister". If the person's surname is Mister, then it should be the only word in the last name.
   */
  MisterAsOnlySurnameRule,

  /**
   * A person cannot have a name that is only initials.
   */
  NameContainsOnlyInitialsRule,

  /**
   * A person's full name (any name form) cannot be more than 255 characters.
   */
  NameTooLongRule,

  /**
   * A person's record needs a given name or surname.
   */
  NoNameRule,

  /**
   * A person has been declared not accountable.
   */
  NotAccountableRule,

  /**
   * A person's ordinance status is not available. Please contact FamilySearch Support if you are a direct descendant and need more information.
   */
  NotAvailable,

  /**
   * A person has not been deceased for one year.
   */
  NotDeadAtLeastOneYearRule,

  /**
   * There's an unstandardizable string in the death or burial date.  Only applied when a person was born recently
   * enough (110 years) that the death date should be findable and standardizable.
   */
  DeathDateReformatNeeded,

  /**
   * A person must have enough event or relationship information, such as a birth date and place, a death date and place, etc.
   * A person's record must have enough date or place information for the system to be able to determine whether the ordinance is already done.
   */
  NotMatchableUsingEventsRule,

  /**
   * A person must have has enough event or relationship information.
   * A person's record must have enough relationship information for the system to be able to determine whether the ordinance is already done.
   */
  NotMatchableUsingRelationshipsRule,

  /**
   * The ordinance must be a valid temple ordinance. The Family Tree cannot be used to reserve ordinances of this type.
   */
  NotTempleOrdinanceRule,

  /**
   *  This is an official completed ordinance.
   */
  OfficialCompletedOrdinanceRule,

  /**
   * This person is still listed as living.
   */
  OfficialCompletedOrdinanceForLivingRule,

  /**
   * Latin surnames cannot consist on only one letter.
   */
  OneLatinLetterSurnameRule,

  /**
   * ONE_NAME_PER_SCRIPT_TYPE maps to "one.name.per.script.type"
   */
  OneNamePerScriptTypeRule,

  /**
   * A "sealing to parent" ordinances requires the person to have both child-to-father and child-to-mother relationships.
   */
  ParentRelationshipMissingRule,

  /**
   * Personal born in covenant rule. distinct from "Born In Covenant" rule because parent relationships are NOT checked.
   */
  PersonalBornInCovenantRule,

  /**
   * A person's name cannot contain repeated punctuation characters, such as .., --, etc.
   */
  RepeatingSpecialCharacterNameRule,

  /**
   * A sealing to spouse must involve a husband and a wife. A sealing to parents must involve a father and a mother.
   */
  SameSexRule,

  /**
   * A person cannot be sealed to themselves.
   */
  SealingToSelfRule,

  /**
   * A stillborn (actually dead at birth) person does not need ordinances.
   */
  StillbornRule,

  /**
   * A person must have a known gender, male or female.
   */
  UnknownGenderRule,

  /**
   * This ordinance is for a person born too recently, and the current user is not an immediate relation.
   */
  NeedPermission,

  /**
   * This person satisfies all qualification rules for ordinances to be performed.
   */
  None,

  /**
   * This status reason is unknown
   */
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
