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
import org.gedcomx.rt.GedcomxConstants;

/**
 * Enumeration of known record types.
 *
 * @author Ryan Heaton
 */
@XmlQNameEnum (
  base = XmlQNameEnum.BaseType.URI
)
@Facet ( name = GedcomxConstants.FACET_GEDCOMX_RECORD )
public enum RecordType {

  /**
   * A record of a person's admission to an institution, society, or other association.
   */
  Admission,

  /**
   * A record of an adoption.
   */
  Adoption,

  /**
   * An affidavit.
   */
  Affidavit,

  /**
   * A person's application to an institution, society or other association.
   */
  Application,

  /**
   * A record of a person's arrival at a certain place.
   */
  Arrival,

  /**
   * A bank record.
   */
  Bank,

  /**
   * A record of a person's baptism.
   */
  Baptism,

  /**
   * A record of a birth.
   */
  Birth,

  /**
   * A record of a person's burial or interment.
   */
  Burial,

  /**
   * todo: document this type.
   */
  Business,

  /**
   * todo: document this type.
   */
  Cemetery,

  /**
   * A census record.
   */
  Census,

  /**
   * A record of a person's christening.
   */
  Christening,

  /**
   * A record of a person's confirmation.
   */
  Confirmation,

  /**
   * todo: document this type.
   */
  Correspondence,

  /**
   * A death record.
   */
  Death,

  /**
   * A record of a person's departure from a certain place.
   */
  Departure,

  /**
   * A divorce record.
   */
  Divorce,

  /**
   * todo: document this type.
   */
  Duplicate,

  /**
   * A draft record.
   */
  Draft,

  /**
   * todo: document this type.
   */
  Estate,

  /**
   * todo: document this type.
   */
  Index,

  /**
   * todo: document this type. what's the difference between this an MarrigeBanns?
   */
  IntendedMarriage,

  /**
   * A legal inquest. Inquests usually only occur when there’s something suspicious about the death. Inquests might in
   * some instances lead to a murder investigation. Most people that die have a death certificate wherein a doctor indicates
   * the cause of death and often indicates when the decedent was last seen by that physician; these require no inquest.
   */
  Inquest,

  /**
   * A land record.
   */
  Land,

  /**
   * A legal record.
   */
  Legal,

  /**
   * A marriage record.
   */
  Marriage,

  /**
   * A marriage affadavit. todo: is this distinguishment necessary? why not just use Marriage?
   */
  MarriageAffidavit,

  /**
   * todo: document this type.
   */
  MarriageAmendment,

  /**
   * A record of a person's banns of marriage.
   */
  MarriageBanns,

  /**
   * todo: document this type. why not just use marriage banns?
   */
  MarriageConsent,

  /**
   * todo: document this type.
   */
  MarriageDuplicate,

  /**
   * A marriage license. todo: is this distinguishment necessary? why not just use Marriage?
   */
  MarriageLicense,

  /**
   * todo: document this type. is this distinguishment necessary? why not just use Marriage?
   */
  MarriageReturns,

  /**
   * todo: document this type. is this distinguishment necessary?
   */
  Membership,

  /**
   * A migration record.
   */
  Migration,

  /**
   * A military record.
   */
  Military,

  /**
   * A naturalization record.
   */
  Naturalization,

  /**
   * A passenger record.
   */
  Passenger,

  /**
   * A pension record.
   */
  Pension,

  /**
   * A probate record.
   */
  Probate,

  /**
   * todo: document this type.
   */
  RelatedDocument,

  /**
   * todo: document this type.
   */
  ReligiousCreeds,

  /**
   * A roll.
   */
  Roll,

  /**
   * A tax record.
   */
  Tax,

  /**
   * A vital record.
   */
  Vital,

  /**
   * Custom
   */
  @XmlUnknownQNameEnumValue
  OTHER;

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
  public static RecordType fromQNameURI(URI qname) {
    return org.codehaus.enunciate.XmlQNameEnumUtil.fromURIValue(qname.toString(), RecordType.class);
  }

}
