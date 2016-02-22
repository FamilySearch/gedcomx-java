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
 * Enumeration of known fields.
 *
 * @author Ryan Heaton
 */
@XmlQNameEnum (
  base = XmlQNameEnum.BaseType.URI
)
@Facet ( name = GedcomxConstants.FACET_GEDCOMX_RECORD )
public enum FieldType implements ControlledVocabulary {

  //high-level fields
  Age,
  Date,
  Place,
  Gender,
  Name,
  Role,

  //age fields
  Years,
  Months,
  Days,
  Hours,
  Minutes,

  //date fields
  Year,
  Month,
  Day,
  Hour,
  Minute,

  //place fields
  Address,
  Cemetery,
  City,
  Church,
  County,
  Country,
  District,
  Hospital,
  Island,
  MilitaryBase,
  Mortuary,
  Parish,
  PlotNumber,
  PostOffice,
  PostalCode,
  Prison,
  Province,
  Section,
  Ship,
  State,
  Territory,
  Town,
  Township,
  Ward,

  //name types
  Prefix,
  Suffix,
  Given,
  Surname,

  //characteristic fields
  Abusua,
  BatchNumber,
  Caste,
  Clan,
  CommonLawMarriage,
  Education,
  Ethnicity,
  FatherBirthPlace,
  NeverHadChildren,
  NeverMarried,
  NumberOfChildren,
  NumberOfMarriages,
  Household,
  IsHeadOfHousehold,
  MaritalStatus,
  MotherBirthPlace,
  MultipleBirth,
  NameSake,
  NationalId,
  Nationality,
  Occupation,
  PhysicalDescription,
  Property,
  Race,
  Religion,
  RelationshipToHead,
  Stillbirth,
  TitleOfNobility,
  Tribe,

  /**
   * Custom
   */
  @XmlUnknownQNameEnumValue
  OTHER;

  private static final EnumURIMap<FieldType> URI_MAP = new EnumURIMap<FieldType>(FieldType.class, GedcomxConstants.GEDCOMX_TYPES_NAMESPACE);

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
  public static FieldType fromQNameURI(URI qname) {
    return URI_MAP.fromURIValue(qname);
  }

}
