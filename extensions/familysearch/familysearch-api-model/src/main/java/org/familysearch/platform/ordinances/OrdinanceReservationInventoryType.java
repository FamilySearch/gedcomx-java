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
package org.familysearch.platform.ordinances;

import com.webcohesion.enunciate.metadata.qname.XmlQNameEnum;
import com.webcohesion.enunciate.metadata.qname.XmlUnknownQNameEnumValue;

import org.gedcomx.common.URI;
import org.gedcomx.rt.ControlledVocabulary;
import org.gedcomx.rt.EnumURIMap;

/**
 * Enumeration of known ordinance types
 *
 */
@XmlQNameEnum(
    namespace = OrdinanceReservationInventoryType.INVENTORY_NAMESPACE,
    base = XmlQNameEnum.BaseType.URI
)
public enum OrdinanceReservationInventoryType implements ControlledVocabulary {

  /**
   * Church inventory
   */
  Church,

  /**
   * Personal inventory
   */
  Personal,

  @XmlUnknownQNameEnumValue
  OTHER;

  static final String INVENTORY_NAMESPACE = "http://www.churchofjesuschrist.org/";

  private static final EnumURIMap<OrdinanceReservationInventoryType> URI_MAP =
      new EnumURIMap<OrdinanceReservationInventoryType>(OrdinanceReservationInventoryType.class, INVENTORY_NAMESPACE);

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
  public static OrdinanceReservationInventoryType fromQNameURI(URI qname) {
    return URI_MAP.fromURIValue(qname);
  }


}
