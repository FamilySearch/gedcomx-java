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

import org.codehaus.enunciate.qname.XmlQNameEnum;
import org.codehaus.enunciate.qname.XmlUnknownQNameEnumValue;
import org.familysearch.platform.FamilySearchPlatform;
import org.gedcomx.common.URI;
import org.gedcomx.rt.ControlledVocabulary;
import org.gedcomx.rt.EnumURIMap;

@XmlQNameEnum(
  base = XmlQNameEnum.BaseType.URI
)
public enum OrdinanceStatus implements ControlledVocabulary {

  /**
   * Ordinance is not reserved and ready to be performed.
   */
  Ready,

  /**
   * Ordinance is not ready to be performed.
   */
  NotReady,

  /**
   * Ordinance is reserved for temple work.
   */
  Reserved,

  /**
   * Ordinance needs more information before it can be reserved.
   */
  NeedMoreInformation,

  /**
   * Ordinance is not available to be performed.
   */
  NotAvailable,

  /**
   * Ordinance is completed.
   */
  Completed,

  /**
   * Ordinance not needed.
   */
  NotNeeded,

  /**
   * Ordinance not needed; person was born in covenant.
   */
  NotNeededBornInCovenant,

  /**
   * Ordinance is in progress.
   */
  InProgress,

  /**
   * Ordinance needs special permission to be performed.
   */
  NeedPermission,

  /**
   * Ordinance is cancelled.
   */
  Cancelled,

  /**
   * Ordinance is deleted.
   */
  Deleted,

  /**
   * Ordinance is invalid.
   */
  Invalid,

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
