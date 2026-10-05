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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.webcohesion.enunciate.metadata.qname.XmlQNameEnum;
import com.webcohesion.enunciate.metadata.qname.XmlUnknownQNameEnumValue;
import io.swagger.v3.oas.annotations.Hidden;
import org.familysearch.platform.FamilySearchPlatform;
import org.gedcomx.common.URI;
import org.gedcomx.rt.ControlledVocabulary;
import org.gedcomx.rt.EnumURIMap;


/**
 * FamilySearch extension of the {@code org.gedcomx.types.RelationshipType} vocabulary, covering the
 * relationship types FamilySearch recognizes between two persons.
 */
@XmlQNameEnum (
  base = XmlQNameEnum.BaseType.URI
)
public enum FamilySearchRelationshipType implements ControlledVocabulary {

  @JsonProperty(value = "http://familysearch.org/v1/AncestorToDescendant")
  AncestorToDescendant,

  @JsonProperty(value = "http://familysearch.org/v1/EmployerToEmployee")
  EmployerToEmployee,

  @JsonProperty(value = "http://familysearch.org/v1/GodparentToGodchild")
  GodparentToGodchild,

  @JsonProperty(value = "http://familysearch.org/v1/HeadOfHouseholdToOccupant")
  HeadOfHouseholdToOccupant,

  @JsonProperty(value = "http://familysearch.org/v1/MasterToApprentice")
  MasterToApprentice,

  @JsonProperty(value = "http://familysearch.org/v1/NeighborToNeighbor")
  NeighborToNeighbor,

  @JsonProperty(value = "http://familysearch.org/v1/RelativeToRelative")
  RelativeToRelative,

  @JsonProperty(value = "http://familysearch.org/v1/SlaveholderToEnslavedPerson")
  SlaveholderToEnslavedPerson,

  @XmlUnknownQNameEnumValue
  @Hidden
  OTHER;

  private static final EnumURIMap<FamilySearchRelationshipType> URI_MAP = new EnumURIMap<>(FamilySearchRelationshipType.class, FamilySearchPlatform.NAMESPACE);

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
  public static FamilySearchRelationshipType fromQNameURI(URI qname) {
    return URI_MAP.fromURIValue(qname);
  }
}
