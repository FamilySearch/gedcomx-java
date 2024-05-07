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
   *   Indicates access to the tree from any third-party app for owner/group members
   */
  ANY,

  /**
   * Indicates only the third-party application associated with the tree is allowed to read/write to the tree
   */
  USE_ALLOW_LIST,

  /**
   * Disallows all third-party access to the tree.
   */
  NONE,

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
