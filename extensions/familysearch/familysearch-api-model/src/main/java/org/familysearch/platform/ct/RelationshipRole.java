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
 * Enumeration of relationship roles in the FamilySearch Family Tree.
 *
 * @author Ryan Heaton
 */
@XmlQNameEnum (
  base = XmlQNameEnum.BaseType.URI
)
public enum RelationshipRole implements ControlledVocabulary {

  Parent1,

  Parent2,

  Child,

  Spouse1,

  Spouse2,

  // todo GenericRelationshipTerms cleanup    remove @Deprecated values
  /////////////////////
  /**
   * Deprecated: The father in a relationship was changed.  Use Parent1.
   */
  @Deprecated
  Father,
  /**
   * Deprecated: The mother in a relationship was changed.  Use Parent2.
   */
  @Deprecated
  Mother,
  /**
   * Deprecated: The man in a relationship was changed.  Use Spouse1.
   */
  @Deprecated
  Man,
  /**
   * Deprecated: The woman in a relationship was changed.  Use Spouse2.
   */
  @Deprecated
  Woman,
  /////////////////////

  @XmlUnknownQNameEnumValue
  OTHER;

  private static final EnumURIMap<RelationshipRole> URI_MAP = new EnumURIMap<RelationshipRole>(RelationshipRole.class, FamilySearchPlatform.NAMESPACE);

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
  public static RelationshipRole fromQNameURI(URI qname) {
    return URI_MAP.fromURIValue(qname);
  }



}
