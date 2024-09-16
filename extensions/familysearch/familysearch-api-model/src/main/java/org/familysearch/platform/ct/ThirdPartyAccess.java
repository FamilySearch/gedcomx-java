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
package org.familysearch.platform.ct;

import com.webcohesion.enunciate.metadata.qname.XmlQNameEnum;
import com.webcohesion.enunciate.metadata.qname.XmlUnknownQNameEnumValue;

import org.familysearch.platform.FamilySearchPlatform;
import org.gedcomx.common.URI;
import org.gedcomx.rt.ControlledVocabulary;
import org.gedcomx.rt.EnumURIMap;

/**
 * The set of Third Party Access restriction that can be set on a tree.
 *
 * @author Erik Wilford
 */
@XmlQNameEnum(
    base = XmlQNameEnum.BaseType.URI
)
public enum ThirdPartyAccess implements ControlledVocabulary {

  /**
   * Allows the group owner or group members to access the tree from any third-party application. Group owner access cannot be more restrictive than group
   * member access.
   */
  AnyApps,

  /**
   * Restricts the group owner or group members to access tree only from applications owned by the company that owns the application that created the tree.
   */
  CompanyApps,

  /**
   * Disallows the group members access to the tree from all third-party applications. Owner access cannot be set to None.
   */
  None,

  /**
   * Custom
   */
  @XmlUnknownQNameEnumValue
  OTHER;

  private static final EnumURIMap<ThirdPartyAccess> URI_MAP = new EnumURIMap<>(ThirdPartyAccess.class, FamilySearchPlatform.NAMESPACE);

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
  public static ThirdPartyAccess fromQNameURI(URI qname) {
    return URI_MAP.fromURIValue(qname);
  }

}
