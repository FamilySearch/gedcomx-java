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
 * Enumeration of known record types.
 *
 * @author Ryan Heaton
 */
@XmlQNameEnum (
  base = XmlQNameEnum.BaseType.URI
)
@Facet ( GedcomxConstants.FACET_GEDCOMX_RECORD )
@Schema(description = "RecordType")

public enum RecordType implements ControlledVocabulary {

  /**
   * A record of a person's admission to an institution, society, or other association.
   */
  @JsonProperty(value = "http://gedcomx.org/Admission")
  Admission,

  /**
   * A record of an adoption.
   */
  @JsonProperty(value = "http://gedcomx.org/Adoption")
  Adoption,

  /**
   * An affidavit.
   */
  @JsonProperty(value = "http://gedcomx.org/Affidavit")
  Affidavit,

  /**
   * A person's application to an institution, society or other association.
   */
  @JsonProperty(value = "http://gedcomx.org/Application")
  Application,

  /**
   * A record of a person's arrival at a certain place.
   */
  @JsonProperty(value = "http://gedcomx.org/Arrival")
  Arrival,

  /**
   * A bank record.
   */
  @JsonProperty(value = "http://gedcomx.org/Bank")
  Bank,

  /**
   * A record of a person's baptism.
   */
  @JsonProperty(value = "http://gedcomx.org/Baptism")
  Baptism,

  /**
   * A record of a birth.
   */
  @JsonProperty(value = "http://gedcomx.org/Birth")
  Birth,

  /**
   * A record of a person's burial or interment.
   */
  @JsonProperty(value = "http://gedcomx.org/Burial")
  Burial,

  /**
   * todo: document this type.
   */
  @JsonProperty(value = "http://gedcomx.org/Business")
  Business,

  /**
   * todo: document this type.
   */
  @JsonProperty(value = "http://gedcomx.org/Cemetery")
  Cemetery,

  /**
   * A census record.
   */
  @JsonProperty(value = "http://gedcomx.org/Census")
  Census,

  /**
   * A record of a person's christening.
   */
  @JsonProperty(value = "http://gedcomx.org/Christening")
  Christening,

  /**
   * A Compiled Genealogy
   */
  @JsonProperty(value = "http://gedcomx.org/CompiledGenealogy")
  CompiledGenealogy,

  /**
   * A record of a person's confirmation.
   */
  @JsonProperty(value = "http://gedcomx.org/Confirmation")
  Confirmation,

  /**
   * todo: document this type.
   */
  @JsonProperty(value = "http://gedcomx.org/Correspondence")
  Correspondence,

  /**
   * A death record.
   */
  @JsonProperty(value = "http://gedcomx.org/Death")
  Death,

  /**
   * A record of a person's departure from a certain place.
   */
  @JsonProperty(value = "http://gedcomx.org/Departure")
  Departure,

  /**
   * A divorce record.
   */
  @JsonProperty(value = "http://gedcomx.org/Divorce")
  Divorce,

  /**
   * todo: document this type.
   */
  @JsonProperty(value = "http://gedcomx.org/Duplicate")
  Duplicate,

  /**
   * A draft record.
   */
  @JsonProperty(value = "http://gedcomx.org/Draft")
  Draft,

  /**
   * todo: document this type.
   */
  @JsonProperty(value = "http://gedcomx.org/Estate")
  Estate,

  /**
   * todo: document this type.
   */
  @JsonProperty(value = "http://gedcomx.org/Index")
  Index,

  /**
   * todo: document this type. what's the difference between this an MarrigeBanns?
   */
  @JsonProperty(value = "http://gedcomx.org/IntendedMarriage")
  IntendedMarriage,

  /**
   * A land record.
   */
  @JsonProperty(value = "http://gedcomx.org/Land")
  Land,

  /**
   * A legal record.
   */
  @JsonProperty(value = "http://gedcomx.org/Legal")
  Legal,

  /**
   * A marriage record.
   */
  @JsonProperty(value = "http://gedcomx.org/Marriage")
  Marriage,

  /**
   * A marriage affadavit. todo: is this distinguishment necessary? why not just use Marriage?
   */
  @JsonProperty(value = "http://gedcomx.org/MarriageAffidavit")
  MarriageAffidavit,

  /**
   * todo: document this type.
   */
  @JsonProperty(value = "http://gedcomx.org/MarriageAmendment")
  MarriageAmendment,

  /**
   * A record of a person's banns of marriage.
   */
  @JsonProperty(value = "http://gedcomx.org/MarriageBanns")
  MarriageBanns,

  /**
   * todo: document this type. why not just use marriage banns?
   */
  @JsonProperty(value = "http://gedcomx.org/MarriageConsent")
  MarriageConsent,

  /**
   * todo: document this type.
   */
  @JsonProperty(value = "http://gedcomx.org/MarriageDuplicate")
  MarriageDuplicate,

  /**
   * A marriage license. todo: is this distinguishment necessary? why not just use Marriage?
   */
  @JsonProperty(value = "http://gedcomx.org/MarriageLicense")
  MarriageLicense,

  /**
   * todo: document this type. is this distinguishment necessary? why not just use Marriage?
   */
  @JsonProperty(value = "http://gedcomx.org/MarriageReturns")
  MarriageReturns,

  /**
   * todo: document this type. is this distinguishment necessary?
   */
  @JsonProperty(value = "http://gedcomx.org/Membership")
  Membership,

  /**
   * A migration record.
   */
  @JsonProperty(value = "http://gedcomx.org/Migration")
  Migration,

  /**
   * A military record.
   */
  @JsonProperty(value = "http://gedcomx.org/Military")
  Military,

  /**
   * A naturalization record.
   */
  @JsonProperty(value = "http://gedcomx.org/Naturalization")
  Naturalization,

  /**
   * A newspaper article or record.
   */
  @JsonProperty(value = "http://gedcomx.org/Newspaper")
  Newspaper,

  /**
   * A   Obituary record
   */
  @JsonProperty(value = "http://gedcomx.org/Obituary")
  Obituary,

  /**
   * A passenger record.
   */
  @JsonProperty(value = "http://gedcomx.org/Passenger")
  Passenger,

  /**
   * A pension record.
   */
  @JsonProperty(value = "http://gedcomx.org/Pension")
  Pension,

  /**
   * A probate record.
   */
  @JsonProperty(value = "http://gedcomx.org/Probate")
  Probate,

  /**
   * todo: document this type.
   */
  @JsonProperty(value = "http://gedcomx.org/RelatedDocument")
  RelatedDocument,

  /**
   * todo: document this type.
   */
  @JsonProperty(value = "http://gedcomx.org/ReligiousCreeds")
  ReligiousCreeds,

  /**
   * A Residence record
   */
  @JsonProperty(value = "http://gedcomx.org/Residence")
  Residence,

  /**
   * A roll.
   */
  @JsonProperty(value = "http://gedcomx.org/Roll")
  Roll,

  /**
   * A tax record.
   */
  @JsonProperty(value = "http://gedcomx.org/Tax")
  Tax,

  /**
   * A vital record.
   */
  @JsonProperty(value = "http://gedcomx.org/Vital")
  Vital,

  /**
   * A voter registration record.
   */
  @JsonProperty(value = "http://gedcomx.org/VoterRegistration")
  VoterRegistration,

  /**
   * A will.
   */
  @JsonProperty(value = "http://gedcomx.org/Will")
  Will,

  /**
   * Custom
   */
  @XmlUnknownQNameEnumValue
  @Hidden
  OTHER;

  private static final EnumURIMap<RecordType> URI_MAP = new EnumURIMap<RecordType>(RecordType.class, GedcomxConstants.GEDCOMX_TYPES_NAMESPACE);

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
  public static RecordType fromQNameURI(URI qname) {
    return URI_MAP.fromURIValue(qname);
  }

}