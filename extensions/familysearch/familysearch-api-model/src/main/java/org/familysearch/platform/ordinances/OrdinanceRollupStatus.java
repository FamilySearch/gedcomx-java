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
    namespace = OrdinanceRollupStatus.ROLLUP_NAMESPACE,
    base = XmlQNameEnum.BaseType.URI
)
public enum OrdinanceRollupStatus implements ControlledVocabulary {

  /**
   * This rollup status for the ordinance indicates it can be reserved by the current user.
   */
  Ready,

  /**
   * This rollup status for the ordinance indicates it can not be reserved by the current user because more information is needed about the person.
   */
  NeedMoreInformation,

  /**
   * This rollup status for the ordinance indicates it has been reserved.
   */
  Reserved,

  /**
   * This rollup status for the ordinance indicates it has been completed.
   */
  Completed,


  @XmlUnknownQNameEnumValue
  OTHER;

  static final String ROLLUP_NAMESPACE = FamilySearchPlatform.NAMESPACE + "rollup/";

  private static final EnumURIMap<OrdinanceRollupStatus> URI_MAP = new EnumURIMap<OrdinanceRollupStatus>(OrdinanceRollupStatus.class, ROLLUP_NAMESPACE);

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
  public static OrdinanceRollupStatus fromQNameURI(URI qname) {
    return URI_MAP.fromURIValue(qname);
  }

}
