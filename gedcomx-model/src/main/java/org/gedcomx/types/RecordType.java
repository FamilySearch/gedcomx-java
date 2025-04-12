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
 * Enumeration of known record types.
 *
 * @author Ryan Heaton
 */
@XmlQNameEnum (
  base = XmlQNameEnum.BaseType.URI
)
@Facet ( GedcomxConstants.FACET_GEDCOMX_RECORD )
@Schema(description = "RecordType", allowableValues = {"http://gedcomx.org/Admission",
                                                       "http://gedcomx.org/Adoption",
                                                       "http://gedcomx.org/Affidavit",
                                                       "http://gedcomx.org/Application",
                                                       "http://gedcomx.org/Arrival",
                                                       "http://gedcomx.org/Bank",
                                                       "http://gedcomx.org/Baptism",
                                                       "http://gedcomx.org/Birth",
                                                       "http://gedcomx.org/Burial",
                                                       "http://gedcomx.org/Business",
                                                       "http://gedcomx.org/Cemetery",
                                                       "http://gedcomx.org/Census",
                                                       "http://gedcomx.org/Christening",
                                                       "http://gedcomx.org/CompiledGenealogy",
                                                       "http://gedcomx.org/Confirmation",
                                                       "http://gedcomx.org/Correspondence",
                                                       "http://gedcomx.org/Death",
                                                       "http://gedcomx.org/Departure",
                                                       "http://gedcomx.org/Divorce",
                                                       "http://gedcomx.org/Duplicate",
                                                       "http://gedcomx.org/Draft",
                                                       "http://gedcomx.org/Estate",
                                                       "http://gedcomx.org/Index",
                                                       "http://gedcomx.org/IntendedMarriage",
                                                       "http://gedcomx.org/Land",
                                                       "http://gedcomx.org/Legal",
                                                       "http://gedcomx.org/Marriage",
                                                       "http://gedcomx.org/MarriageAffidavit",
                                                       "http://gedcomx.org/MarriageAmendment",
                                                       "http://gedcomx.org/MarriageBanns",
                                                       "http://gedcomx.org/MarriageConsent",
                                                       "http://gedcomx.org/MarriageDuplicate",
                                                       "http://gedcomx.org/MarriageLicense",
                                                       "http://gedcomx.org/MarriageReturns",
                                                       "http://gedcomx.org/Membership",
                                                       "http://gedcomx.org/Migration",
                                                       "http://gedcomx.org/Military",
                                                       "http://gedcomx.org/Naturalization",
                                                       "http://gedcomx.org/Newspaper",
                                                       "http://gedcomx.org/Obituary",
                                                       "http://gedcomx.org/Passenger",
                                                       "http://gedcomx.org/Pension",
                                                       "http://gedcomx.org/Probate",
                                                       "http://gedcomx.org/RelatedDocument",
                                                       "http://gedcomx.org/ReligiousCreeds",
                                                       "http://gedcomx.org/Residence",
                                                       "http://gedcomx.org/Roll",
                                                       "http://gedcomx.org/Tax",
                                                       "http://gedcomx.org/Vital",
                                                       "http://gedcomx.org/VoterRegistration",
                                                       "http://gedcomx.org/Will"})

public enum RecordType implements ControlledVocabulary {

  /**
   * A record of a person's admission to an institution, society, or other association.
   */
  @Hidden

  Admission,

  /**
   * A record of an adoption.
   */
  @Hidden
  Adoption,

  /**
   * An affidavit.
   */
  @Hidden
  Affidavit,

  /**
   * A person's application to an institution, society or other association.
   */
  @Hidden
  Application,

  /**
   * A record of a person's arrival at a certain place.
   */
  @Hidden
  Arrival,

  /**
   * A bank record.
   */
  @Hidden
  Bank,

  /**
   * A record of a person's baptism.
   */
  @Hidden
  Baptism,

  /**
   * A record of a birth.
   */
  @Hidden
  Birth,

  /**
   * A record of a person's burial or interment.
   */
  @Hidden
  Burial,

  /**
   * todo: document this type.
   */
  @Hidden
  Business,

  /**
   * todo: document this type.
   */
  @Hidden
  Cemetery,

  /**
   * A census record.
   */
  @Hidden
  Census,

  /**
   * A record of a person's christening.
   */
  @Hidden
  Christening,

  /**
   * A Compiled Genealogy
   */
  @Hidden
  CompiledGenealogy,

  /**
   * A record of a person's confirmation.
   */
  @Hidden
  Confirmation,

  /**
   * todo: document this type.
   */
  @Hidden
  Correspondence,

  /**
   * A death record.
   */
  @Hidden
  Death,

  /**
   * A record of a person's departure from a certain place.
   */
  @Hidden
  Departure,

  /**
   * A divorce record.
   */
  @Hidden
  Divorce,

  /**
   * todo: document this type.
   */
  @Hidden
  Duplicate,

  /**
   * A draft record.
   */
  @Hidden
  Draft,

  /**
   * todo: document this type.
   */
  @Hidden
  Estate,

  /**
   * todo: document this type.
   */
  @Hidden
  Index,

  /**
   * todo: document this type. what's the difference between this an MarrigeBanns?
   */
  @Hidden
  IntendedMarriage,

  /**
   * A land record.
   */
  @Hidden
  Land,

  /**
   * A legal record.
   */
  @Hidden
  Legal,

  /**
   * A marriage record.
   */
  @Hidden
  Marriage,

  /**
   * A marriage affadavit. todo: is this distinguishment necessary? why not just use Marriage?
   */
  @Hidden
  MarriageAffidavit,

  /**
   * todo: document this type.
   */
  @Hidden
  MarriageAmendment,

  /**
   * A record of a person's banns of marriage.
   */
  @Hidden
  MarriageBanns,

  /**
   * todo: document this type. why not just use marriage banns?
   */
  @Hidden
  MarriageConsent,

  /**
   * todo: document this type.
   */
  @Hidden
  MarriageDuplicate,

  /**
   * A marriage license. todo: is this distinguishment necessary? why not just use Marriage?
   */
  @Hidden
  MarriageLicense,

  /**
   * todo: document this type. is this distinguishment necessary? why not just use Marriage?
   */
  @Hidden
  MarriageReturns,

  /**
   * todo: document this type. is this distinguishment necessary?
   */
  @Hidden
  Membership,

  /**
   * A migration record.
   */
  @Hidden
  Migration,

  /**
   * A military record.
   */
  @Hidden
  Military,

  /**
   * A naturalization record.
   */
  @Hidden
  Naturalization,

  /**
   * A newspaper article or record.
   */
  @Hidden
  Newspaper,

  /**
   * A   Obituary record
   */
  @Hidden
  Obituary,

  /**
   * A passenger record.
   */
  @Hidden
  Passenger,

  /**
   * A pension record.
   */
  @Hidden
  Pension,

  /**
   * A probate record.
   */
  @Hidden
  Probate,

  /**
   * todo: document this type.
   */
  @Hidden
  RelatedDocument,

  /**
   * todo: document this type.
   */
  @Hidden
  ReligiousCreeds,

  /**
   * A Residence record
   */
  @Hidden
  Residence,

  /**
   * A roll.
   */
  @Hidden
  Roll,

  /**
   * A tax record.
   */
  @Hidden
  Tax,

  /**
   * A vital record.
   */
  @Hidden
  Vital,

  /**
   * A voter registration record.
   */
  @Hidden
  VoterRegistration,

  /**
   * A will.
   */
  @Hidden
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
