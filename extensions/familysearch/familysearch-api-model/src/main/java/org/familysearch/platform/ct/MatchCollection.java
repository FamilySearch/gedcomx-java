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
import com.webcohesion.enunciate.metadata.qname.XmlQNameEnumValue;
import com.webcohesion.enunciate.metadata.qname.XmlUnknownQNameEnumValue;
import org.familysearch.platform.FamilySearchPlatform;
import org.gedcomx.common.URI;
import org.gedcomx.rt.ControlledVocabulary;
import org.gedcomx.rt.EnumURIMap;

/**
 * Identifiers for a collection that might contain match results.
 */
@XmlQNameEnum (
  base = XmlQNameEnum.BaseType.URI,
  namespace = "https://api.familysearch.org/platform/collections/"
)
public enum MatchCollection implements ControlledVocabulary {

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

  private static final EnumURIMap<MatchCollection> URI_MAP = new EnumURIMap<MatchCollection>(MatchCollection.class, FamilySearchPlatform.NAMESPACE);

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
  public static MatchCollection fromQNameURI(URI qname) {
    return URI_MAP.fromURIValue(qname);
  }
}
