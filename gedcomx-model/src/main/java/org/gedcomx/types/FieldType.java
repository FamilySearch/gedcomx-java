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
 * Enumeration of known fields.
 *
 * @author Ryan Heaton
 */
@XmlQNameEnum (
  base = XmlQNameEnum.BaseType.URI
)
@Facet(GedcomxConstants.FACET_GEDCOMX_RECORD)
@Schema(description = "FieldType")
public enum FieldType implements ControlledVocabulary {

  //high-level fields
  @JsonProperty(value = "http://gedcomx.org/Age")
  Age,
  @JsonProperty(value = "http://gedcomx.org/Date")
  Date,
  @JsonProperty(value = "http://gedcomx.org/Place")
  Place,
  @JsonProperty(value = "http://gedcomx.org/Gender")
  Gender,
  @JsonProperty(value = "http://gedcomx.org/Name")
  Name,
  @JsonProperty(value = "http://gedcomx.org/Role")
  Role,

  //age fields
  @JsonProperty(value = "http://gedcomx.org/Years")
  Years,
  @JsonProperty(value = "http://gedcomx.org/Months")
  Months,
  @JsonProperty(value = "http://gedcomx.org/Days")
  Days,
  @JsonProperty(value = "http://gedcomx.org/Hours")
  Hours,
  @JsonProperty(value = "http://gedcomx.org/Minutes")
  Minutes,

  //date fields
  @JsonProperty(value = "http://gedcomx.org/Year")
  Year,
  @JsonProperty(value = "http://gedcomx.org/Month")
  Month,
  @JsonProperty(value = "http://gedcomx.org/Day")
  Day,
  @JsonProperty(value = "http://gedcomx.org/Hour")
  Hour,
  @JsonProperty(value = "http://gedcomx.org/Minute")
  Minute,

  //place fields
  @JsonProperty(value = "http://gedcomx.org/Address")
  Address,
  @JsonProperty(value = "http://gedcomx.org/Cemetery")
  Cemetery,
  @JsonProperty(value = "http://gedcomx.org/City")
  City,
  @JsonProperty(value = "http://gedcomx.org/Church")
  Church,
  @JsonProperty(value = "http://gedcomx.org/County")
  County,
  @JsonProperty(value = "http://gedcomx.org/Country")
  Country,
  @JsonProperty(value = "http://gedcomx.org/District")
  District,
  @JsonProperty(value = "http://gedcomx.org/Hospital")
  Hospital,
  @JsonProperty(value = "http://gedcomx.org/Island")
  Island,
  @JsonProperty(value = "http://gedcomx.org/MilitaryBase")
  MilitaryBase,
  @JsonProperty(value = "http://gedcomx.org/Mortuary")
  Mortuary,
  @JsonProperty(value = "http://gedcomx.org/Parish")
  Parish,
  @JsonProperty(value = "http://gedcomx.org/PlotNumber")
  PlotNumber,
  @JsonProperty(value = "http://gedcomx.org/PostOffice")
  PostOffice,
  @JsonProperty(value = "http://gedcomx.org/PostalCode")
  PostalCode,
  @JsonProperty(value = "http://gedcomx.org/Prison")
  Prison,
  @JsonProperty(value = "http://gedcomx.org/Province")
  Province,
  @JsonProperty(value = "http://gedcomx.org/Section")
  Section,
  @JsonProperty(value = "http://gedcomx.org/Ship")
  Ship,
  @JsonProperty(value = "http://gedcomx.org/State")
  State,
  @JsonProperty(value = "http://gedcomx.org/Territory")
  Territory,
  @JsonProperty(value = "http://gedcomx.org/Town")
  Town,
  @JsonProperty(value = "http://gedcomx.org/Township")
  Township,
  @JsonProperty(value = "http://gedcomx.org/Ward")
  Ward,

  //name types
  @JsonProperty(value = "http://gedcomx.org/Prefix")
  Prefix,
  @JsonProperty(value = "http://gedcomx.org/Suffix")
  Suffix,
  @JsonProperty(value = "http://gedcomx.org/Given")
  Given,
  @JsonProperty(value = "http://gedcomx.org/Surname")
  Surname,

  //characteristic fields
  @JsonProperty(value = "http://gedcomx.org/Abusua")
  Abusua,
  @JsonProperty(value = "http://gedcomx.org/BatchNumber")
  BatchNumber,
  @JsonProperty(value = "http://gedcomx.org/Caste")
  Caste,
  @JsonProperty(value = "http://gedcomx.org/Clan")
  Clan,
  @JsonProperty(value = "http://gedcomx.org/CommonLawMarriage")
  CommonLawMarriage,
  @JsonProperty(value = "http://gedcomx.org/Education")
  Education,
  @JsonProperty(value = "http://gedcomx.org/Ethnicity")
  Ethnicity,
  @JsonProperty(value = "http://gedcomx.org/FatherBirthPlace")
  FatherBirthPlace,
  @JsonProperty(value = "http://gedcomx.org/NeverHadChildren")
  NeverHadChildren,
  @JsonProperty(value = "http://gedcomx.org/NeverMarried")
  NeverMarried,
  @JsonProperty(value = "http://gedcomx.org/NumberOfChildren")
  NumberOfChildren,
  @JsonProperty(value = "http://gedcomx.org/NumberOfMarriages")
  NumberOfMarriages,
  @JsonProperty(value = "http://gedcomx.org/Household")
  Household,
  @JsonProperty(value = "http://gedcomx.org/IsHeadOfHousehold")
  IsHeadOfHousehold,
  @JsonProperty(value = "http://gedcomx.org/MaritalStatus")
  MaritalStatus,
  @JsonProperty(value = "http://gedcomx.org/MotherBirthPlace")
  MotherBirthPlace,
  @JsonProperty(value = "http://gedcomx.org/MultipleBirth")
  MultipleBirth,
  @JsonProperty(value = "http://gedcomx.org/NameSake")
  NameSake,
  @JsonProperty(value = "http://gedcomx.org/NationalId")
  NationalId,
  @JsonProperty(value = "http://gedcomx.org/Nationality")
  Nationality,
  @JsonProperty(value = "http://gedcomx.org/Occupation")
  Occupation,
  @JsonProperty(value = "http://gedcomx.org/PhysicalDescription")
  PhysicalDescription,
  @JsonProperty(value = "http://gedcomx.org/Property")
  Property,
  @JsonProperty(value = "http://gedcomx.org/Race")
  Race,
  @JsonProperty(value = "http://gedcomx.org/Religion")
  Religion,
  @JsonProperty(value = "http://gedcomx.org/RelationshipToHead")
  RelationshipToHead,
  @JsonProperty(value = "http://gedcomx.org/Stillbirth")
  Stillbirth,
  @JsonProperty(value = "http://gedcomx.org/TitleOfNobility")
  TitleOfNobility,
  @JsonProperty(value = "http://gedcomx.org/Tribe")
  Tribe,

  /**
   * Custom
   */
  @XmlUnknownQNameEnumValue
  @Hidden
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
