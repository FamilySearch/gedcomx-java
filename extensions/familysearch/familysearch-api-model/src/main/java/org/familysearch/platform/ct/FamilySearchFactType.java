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
 * Enumeration of FamilySearch-specific fact types.
 */
@XmlQNameEnum (
  base = XmlQNameEnum.BaseType.URI
)
public enum FamilySearchFactType implements ControlledVocabulary {

  // Person fact types

  /**
   * Person fact type: Affiliation to something.
   */
  Affiliation,

  /**
   * Person fact type: Person died before age eight.
   */
  DiedBeforeEight,

  /**
   * Person fact type: Person's "life sketch" summary.
   */
  LifeSketch,

  /**
   * Person fact type: Person had no children.
   */
  NoChildren,

  /**
   * Person fact type: Person has no couple relationship.
   */
  NoCoupleRelationships,

  /**
   * Person fact type: Person's title of nobility.
   */
  TitleOfNobility,

  /**
   * Person fact type: Person's tribe name.
   */
  TribeName,


  // Couple fact types

  /**
   * Couple fact type: Couple never had children.
   */
  CoupleNeverHadChildren,

  /**
   * Couple fact type: Couple lived together.
   */
  LivedTogether,


  // Parent-child fact types

  /**
   * Parent-child fact type: A child's birth order to parents.
   */
  BirthOrder,


  // Association fact types

  /**
   * Association fact type: An apprenticeship between two persons.
   */
  Apprenticeship,

  /**
   * Association fact type: An emancipation of one person by another.
   */
  Emancipation,

  /**
   * Association fact type: An employment relationship between two persons.
   */
  Employment,

  /**
   * Association fact type: An enslavement of one person by another.
   */
  Enslavement,

  /**
   * Association fact type: The generation distance between two related persons.
   */
  Generation,

  /**
   * Association fact type: A godparent relationship between two persons.
   */
  Godparenthood,

  /**
   * Association fact type: A household relationship between two persons.
   */
  Household,

  /**
   * Association fact type: A neighborhood relationship between two persons.
   */
  Neighborhood,

  /**
   * Association fact type: A general kin relation between two persons.
   */
  Relation,

  @XmlUnknownQNameEnumValue
  OTHER;

  private static final EnumURIMap<FamilySearchFactType> URI_MAP = new EnumURIMap<>(FamilySearchFactType.class, FamilySearchPlatform.NAMESPACE);

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
