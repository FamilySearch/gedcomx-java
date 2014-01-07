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
import org.gedcomx.common.URI;
import org.gedcomx.rt.GedcomxConstants;

/**
 * Enumeration of the possible change object modifiers.
 *
 * @author Ryan Heaton
 */
@XmlQNameEnum (
  base = XmlQNameEnum.BaseType.URI
)
public enum ChangeObjectModifier {

  /**
   * The person.
   */
  @XmlQNameEnumValue( namespace = GedcomxConstants.GEDCOMX_TYPES_NAMESPACE )
  Person,

  /**
   * The couple.
   */
  @XmlQNameEnumValue( namespace = GedcomxConstants.GEDCOMX_TYPES_NAMESPACE )
  Couple,

  /**
   * The child-and-parents relationship.
   */
  ChildAndParentsRelationship;

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
  public static ChangeObjectModifier fromQNameURI(URI qname) {
    return org.codehaus.enunciate.XmlQNameEnumUtil.fromURIValue(qname.toString(), ChangeObjectModifier.class);
  }



}
