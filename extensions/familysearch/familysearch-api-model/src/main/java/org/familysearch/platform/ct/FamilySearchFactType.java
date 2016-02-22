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
import org.codehaus.enunciate.qname.XmlUnknownQNameEnumValue;
import org.familysearch.platform.FamilySearchPlatform;
import org.gedcomx.common.URI;
import org.gedcomx.rt.ControlledVocabulary;
import org.gedcomx.rt.EnumURIMap;

/**
 * Enumeration of FamilySearch-specific fact types.
 */
@XmlQNameEnum (
  base = XmlQNameEnum.BaseType.URI
)
public enum FamilySearchFactType implements ControlledVocabulary {

  /**
   * Affiliation to something.
   */
  Affiliation,

  /**
   * Person died before age eight.
   */
  DiedBeforeEight,

  /**
   * Person's "life sketch" summary.
   */
  LifeSketch,

  /**
   * Person's title of nobility.
   */
  TitleOfNobility,

  /**
   * Person's tribe name.
   */
  TribeName,

  /**
   * A child's birth order to parents.
   */
  BirthOrder,

  @XmlUnknownQNameEnumValue
  OTHER;

  private static final EnumURIMap<FamilySearchFactType> URI_MAP = new EnumURIMap<FamilySearchFactType>(FamilySearchFactType.class, FamilySearchPlatform.NAMESPACE);

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
  public static FamilySearchFactType fromQNameURI(URI qname) {
    return URI_MAP.fromURIValue(qname);
  }

  }
