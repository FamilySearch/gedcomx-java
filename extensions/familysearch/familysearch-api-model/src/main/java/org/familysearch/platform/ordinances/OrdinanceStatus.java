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

  /** The ordinance is not needed because the person was born in the covenant. */
  BornInCovenant,

  /** The ordinance has been completed. */
  Completed,

  /** The ordinance can not be reserved because more information is needed about the person. */
  NeedMoreInformation,

  /** The ordinance can not be reserved without special permission. */
  NeedPermission,

  /** The ordinance is not available to be reserved. */
  NotAvailable,

  /** The ordinance can not be reserved because it is not needed according to the policies of the Church. */
  NotNeeded,

  /** The ordinance can not currently be reserved, but it is expected that the ordinance will eventually become <code>Ready</code> after a period of time. */
  NotReady,

  /** The ordinance can be reserved. */
  Ready,

  /** The ordinance has been reserved by a different user. */
  ReservedByOther,

  /** The ordinance has been reserved and printed by a different user.  It is currently in progress of completion. */
  ReservedByOtherPrinted,

  /** The ordinance was reserved by a different user and shared with or assigned to Church inventory. It may be reserved by the current user. */
  ReservedByOtherSharedReady,

  /** The ordinance has been reserved by a different user and is waiting for prerequisite ordinances to be completed. */
  ReservedByOtherWaiting,

  /** The ordinance has been reserved by the current user and can be printed. */
  ReservedBySelf,

  /** The ordinance has been reserved and printed by the current user.  It is currently in progress of completion. */
  ReservedBySelfPrinted,

  /** The ordinance has been reserved by the current user and shared with or assigned to Church inventory. */
  ReservedBySelfShared,

  /** The ordinance was reserved, shared with or assigned to Church inventory, and has been reserved and printed by a different user. */
  ReservedBySelfSharedThenPrintedByOther,

  /** The ordinance has been reserved by the current user and is waiting for prerequisite ordinances to be completed. */
  ReservedBySelfWaiting,


  // todo the following 3 Ordinance status have been renamed.  These names are deprecated and will be removed.
  @Deprecated
  InProgressByOther,
  @Deprecated
  InProgressBySelf,
  @Deprecated
  NotNeededBornInCovenant,

  @XmlUnknownQNameEnumValue
  OTHER;

  private static final EnumURIMap<OrdinanceStatus> URI_MAP = new EnumURIMap<>(OrdinanceStatus.class, FamilySearchPlatform.NAMESPACE);

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
