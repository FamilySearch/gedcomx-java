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
package org.gedcomx.types;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

import com.webcohesion.enunciate.metadata.qname.XmlQNameEnum;
import com.webcohesion.enunciate.metadata.qname.XmlUnknownQNameEnumValue;
import org.gedcomx.common.URI;
import org.gedcomx.rt.ControlledVocabulary;
import org.gedcomx.rt.EnumURIMap;
import org.gedcomx.rt.GedcomxConstants;

/**
 * Enumeration of standard relationship types.
 */
@XmlQNameEnum (
  base = XmlQNameEnum.BaseType.URI
)
public enum RelationshipType implements ControlledVocabulary {

  AncestorDescendant,
  Apprentice,
  Associate,
  Couple,
  Employer,
  EnslavedBy,
  Friend,
  Godparent,
  HeadOfHousehold,
  HonoredAncestor,
  Neighbor,
  ParentChild,
  Relative,
  @XmlUnknownQNameEnumValue
  OTHER;

  public final static Set<RelationshipType> ASSOCIATION_RELATIONSHIP_TYPES = Collections.unmodifiableSet(EnumSet.of(AncestorDescendant, Apprentice, Associate,
                                                                                                                    Employer, EnslavedBy, Friend, Godparent,
                                                                                                                    HeadOfHousehold, HonoredAncestor, Neighbor,
                                                                                                                    Relative));
  public boolean isAssociationRelationshipType() {
    return ASSOCIATION_RELATIONSHIP_TYPES.contains(this);
  }

  private static final EnumURIMap<RelationshipType> URI_MAP = new EnumURIMap<RelationshipType>(RelationshipType.class, GedcomxConstants.GEDCOMX_TYPES_NAMESPACE);

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
  public static RelationshipType fromQNameURI(URI qname) {
    return URI_MAP.fromURIValue(qname);
  }

}
