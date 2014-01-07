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

import org.codehaus.enunciate.qname.XmlQNameEnum;
import org.codehaus.enunciate.qname.XmlQNameEnumValue;
import org.codehaus.enunciate.qname.XmlUnknownQNameEnumValue;
import org.gedcomx.common.URI;

/**
 * Identifiers for a system that might contain match results.
 */
@XmlQNameEnum (
  base = XmlQNameEnum.BaseType.URI
)
public enum MatchSystem {

  /**
   * The FamilySearch Family Tree.
   */
  tree,

  /**
   * The FamilySearch Record Set.
   */
  records,

  /**
   * The FamilySearch User-Submitted Trees.
   */
  @XmlQNameEnumValue ( localPart = "trees" )
  lls,

  /**
   * The FamilySearch Temple System.
   */
  @XmlQNameEnumValue ( localPart = "temple" )
  tss,

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
  public static MatchSystem fromQNameURI(URI qname) {
    return org.codehaus.enunciate.XmlQNameEnumUtil.fromURIValue(qname.toString(), MatchSystem.class);
  }
}
