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
 * Enumeration of known fields.
 *
 * @author Ryan Heaton
 */
@XmlQNameEnum (
  base = XmlQNameEnum.BaseType.URI
)
@Facet ( GedcomxConstants.FACET_GEDCOMX_RECORD )
@Schema(description = "FieldType", allowableValues = {"http://gedcomx.org/Age",
                                                      "http://gedcomx.org/Date",
                                                      "http://gedcomx.org/Place",
                                                      "http://gedcomx.org/Gender",
                                                      "http://gedcomx.org/Name",
                                                      "http://gedcomx.org/Role",
                                                      "http://gedcomx.org/Years",
                                                      "http://gedcomx.org/Months",
                                                      "http://gedcomx.org/Days",
                                                      "http://gedcomx.org/Hours",
                                                      "http://gedcomx.org/Minutes",
                                                      "http://gedcomx.org/Year",
                                                      "http://gedcomx.org/Month",
                                                      "http://gedcomx.org/Day",
                                                      "http://gedcomx.org/Hour",
                                                      "http://gedcomx.org/Minute",
                                                      "http://gedcomx.org/Address",
                                                      "http://gedcomx.org/Cemetery",
                                                      "http://gedcomx.org/City",
                                                      "http://gedcomx.org/Church",
                                                      "http://gedcomx.org/County",
                                                      "http://gedcomx.org/Country",
                                                      "http://gedcomx.org/District",
                                                      "http://gedcomx.org/Hospital",
                                                      "http://gedcomx.org/Island",
                                                      "http://gedcomx.org/MilitaryBase",
                                                      "http://gedcomx.org/Mortuary",
                                                      "http://gedcomx.org/Parish",
                                                      "http://gedcomx.org/PlotNumber",
                                                      "http://gedcomx.org/PostOffice",
                                                      "http://gedcomx.org/PostalCode",
                                                      "http://gedcomx.org/Prison",
                                                      "http://gedcomx.org/Province",
                                                      "http://gedcomx.org/Section",
                                                      "http://gedcomx.org/Ship",
                                                      "http://gedcomx.org/State",
                                                      "http://gedcomx.org/Territory",
                                                      "http://gedcomx.org/Town",
                                                      "http://gedcomx.org/Township",
                                                      "http://gedcomx.org/Ward",
                                                      "http://gedcomx.org/Prefix",
                                                      "http://gedcomx.org/Suffix",
                                                      "http://gedcomx.org/Given",
                                                      "http://gedcomx.org/Surname",
                                                      "http://gedcomx.org/Abusua",
                                                      "http://gedcomx.org/BatchNumber",
                                                      "http://gedcomx.org/Caste",
                                                      "http://gedcomx.org/Clan",
                                                      "http://gedcomx.org/CommonLawMarriage",
                                                      "http://gedcomx.org/Education",
                                                      "http://gedcomx.org/Ethnicity",
                                                      "http://gedcomx.org/FatherBirthPlace",
                                                      "http://gedcomx.org/NeverHadChildren",
                                                      "http://gedcomx.org/NeverMarried",
                                                      "http://gedcomx.org/NumberOfChildren",
                                                      "http://gedcomx.org/NumberOfMarriages",
                                                      "http://gedcomx.org/Household",
                                                      "http://gedcomx.org/IsHeadOfHousehold",
                                                      "http://gedcomx.org/MaritalStatus",
                                                      "http://gedcomx.org/MotherBirthPlace",
                                                      "http://gedcomx.org/MultipleBirth",
                                                      "http://gedcomx.org/NameSake",
                                                      "http://gedcomx.org/NationalId",
                                                      "http://gedcomx.org/Nationality",
                                                      "http://gedcomx.org/Occupation",
                                                      "http://gedcomx.org/PhysicalDescription",
                                                      "http://gedcomx.org/Property",
                                                      "http://gedcomx.org/Race",
                                                      "http://gedcomx.org/Religion",
                                                      "http://gedcomx.org/RelationshipToHead",
                                                      "http://gedcomx.org/Stillbirth",
                                                      "http://gedcomx.org/TitleOfNobility",
                                                      "http://gedcomx.org/Tribe"})

public enum FieldType implements ControlledVocabulary {

  //high-level fields
  @Hidden
  Age,
  @Hidden
  Date,
  @Hidden
  Place,
  @Hidden
  Gender,
  @Hidden
  Name,
  @Hidden
  Role,

  //age fields
  @Hidden
  Years,
  @Hidden
  Months,
  @Hidden
  Days,
  @Hidden
  Hours,
  @Hidden
  Minutes,

  //date fields
  @Hidden
  Year,
  @Hidden
  Month,
  @Hidden
  Day,
  @Hidden
  Hour,
  @Hidden
  Minute,

  //place fields
  @Hidden
  Address,
  @Hidden
  Cemetery,
  @Hidden
  City,
  @Hidden
  Church,
  @Hidden
  County,
  @Hidden
  Country,
  @Hidden
  District,
  @Hidden
  Hospital,
  @Hidden
  Island,
  @Hidden
  MilitaryBase,
  @Hidden
  Mortuary,
  @Hidden
  Parish,
  @Hidden
  PlotNumber,
  @Hidden
  PostOffice,
  @Hidden
  PostalCode,
  @Hidden
  Prison,
  @Hidden
  Province,
  @Hidden
  Section,
  @Hidden
  Ship,
  @Hidden
  State,
  @Hidden
  Territory,
  @Hidden
  Town,
  @Hidden
  Township,
  @Hidden
  Ward,

  //name types
  @Hidden
  Prefix,
  @Hidden
  Suffix,
  @Hidden
  Given,
  @Hidden
  Surname,

  //characteristic fields
  @Hidden
  Abusua,
  @Hidden
  BatchNumber,
  @Hidden
  Caste,
  @Hidden
  Clan,
  @Hidden
  CommonLawMarriage,
  @Hidden
  Education,
  @Hidden
  Ethnicity,
  @Hidden
  FatherBirthPlace,
  @Hidden
  NeverHadChildren,
  @Hidden
  NeverMarried,
  @Hidden
  NumberOfChildren,
  @Hidden
  NumberOfMarriages,
  @Hidden
  Household,
  @Hidden
  IsHeadOfHousehold,
  @Hidden
  MaritalStatus,
  @Hidden
  MotherBirthPlace,
  @Hidden
  MultipleBirth,
  @Hidden
  NameSake,
  @Hidden
  NationalId,
  @Hidden
  Nationality,
  @Hidden
  Occupation,
  @Hidden
  PhysicalDescription,
  @Hidden
  Property,
  @Hidden
  Race,
  @Hidden
  Religion,
  @Hidden
  RelationshipToHead,
  @Hidden
  Stillbirth,
  @Hidden
  TitleOfNobility,
  @Hidden
  Tribe,

  /**
   * Custom
   */
  @XmlUnknownQNameEnumValue@Hidden
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
