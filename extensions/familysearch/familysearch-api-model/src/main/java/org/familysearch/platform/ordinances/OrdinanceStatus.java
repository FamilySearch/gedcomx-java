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

@XmlQNameEnum(
  base = XmlQNameEnum.BaseType.URI
)
public enum OrdinanceStatus implements ControlledVocabulary {

  /**
   * The ordinance can not be reserved because it is not needed according to the policies of the Church.
   */
  NotNeeded,

  /**
   * The ordinance can not be reserved because it is not needed because the person was born in the covenant.
   */
  NotNeededBornInCovenant,

  /**
   * The ordinance has been reserved by the current user and can be submitted/printed.
   */
  ReservedBySelf,

  /**
   * The ordinance has been reserved by a different user.
   */
  ReservedByOther,

  /**
   * The ordinance can be reserved by the current user.
   */
  Ready,

  /**
   * The ordinance can not currently be reserved by the current user, but it is expected that the ordinance will eventually become <code>Ready</code> after a period of time.
   */
  NotReady,

  /**
   * The ordinance has been completed.
   */
  Completed,

  /**
   * The ordinance is not available to be reserved by the current user.
   */
  NotAvailable,

  /**
   * The ordinance can not be reserved by the current user because more information is needed about the person.
   */
  NeedMoreInformation,

  /**
   * The ordinance is currently in progress of the completion process by the current user and can be resubmitted/printed by the current user.
   */
  InProgressBySelf,

  /**
   * The ordinance is currently in progress of the completion process by a different.
   */
  InProgressByOther,

  /**
   * The ordinance can not be reserved by the current user without special permission.
   */
  NeedPermission,

  @XmlUnknownQNameEnumValue
  OTHER;

  private static final EnumURIMap<OrdinanceStatus> URI_MAP = new EnumURIMap<OrdinanceStatus>(OrdinanceStatus.class, FamilySearchPlatform.NAMESPACE);

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
  public static OrdinanceStatus fromQNameURI(URI qname) {
    return URI_MAP.fromURIValue(qname);
  }

}
