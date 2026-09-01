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
import org.gedcomx.rt.GedcomxConstants;

/**
 * Enumeration of association types.
 */
@XmlQNameEnum (
  base = XmlQNameEnum.BaseType.URI
)
public enum AssociationType implements ControlledVocabulary {

  /**
   * Association type: Ancestor to descendant relationship. This is a standard GEDCOM X type
   * whose URI intentionally uses the gedcomx namespace. Person1 is the ancestor, person2 is the descendant.
   */
  @XmlQNameEnumValue( namespace = GedcomxConstants.GEDCOMX_TYPES_NAMESPACE )
  AncestorDescendant,

  /**
   * Association type: Employer to employee relationship. Person1 is the employer, person2 is the employee.
   */
  EmployerEmployee,

  /**
   * Association type: Enslaved person to slaveholder relationship. This is a standard GEDCOM X type
   * whose URI intentionally uses the gedcomx namespace. Person1 is the enslaved person, person2 is the slaveholder.
   * Note that the name "EnslavedBy" reads as "person1 is enslaved by person2" — the inverse of the Tree Foundation
   * constant SLAVEHOLDER_TO_ENSLAVED_PERSON's ordering. Callers mapping from Tree Foundation must swap person1/person2.
   * The direction is still to be confirmed against the GEDCOM X spec.
   */
  @XmlQNameEnumValue( namespace = GedcomxConstants.GEDCOMX_TYPES_NAMESPACE )
  EnslavedBy,

  /**
   * Association type: Godparent to godchild relationship. This is a standard GEDCOM X type
   * whose URI intentionally uses the gedcomx namespace. Person1 is the godparent, person2 is the godchild.
   */
  @XmlQNameEnumValue( namespace = GedcomxConstants.GEDCOMX_TYPES_NAMESPACE )
  Godparent,

  /**
   * Association type: Head of household to occupant relationship. Person1 is the head of household, person2 is the occupant.
   */
  HeadOfHouseholdOccupant,

  /**
   * Association type: Master to apprentice relationship. Person1 is the master, person2 is the apprentice.
   */
  MasterApprentice,

  /**
   * Association type: Neighbor to neighbor relationship. This is a symmetric relationship.
   */
  Neighbor,

  /**
   * Association type: Relative to relative relationship. This is a symmetric relationship.
   */
  Relative,

  @XmlUnknownQNameEnumValue
  OTHER;

  private static final EnumURIMap<AssociationType> URI_MAP = new EnumURIMap<AssociationType>(AssociationType.class, FamilySearchPlatform.NAMESPACE);

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
  public static AssociationType fromQNameURI(URI qname) {
    return URI_MAP.fromURIValue(qname);
  }

}
