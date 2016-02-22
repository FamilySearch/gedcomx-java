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
package org.familysearch.platform.reservations;

import org.codehaus.enunciate.qname.XmlQNameEnumRef;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.familysearch.platform.ordinances.OrdinanceType;
import org.gedcomx.common.ResourceReference;
import org.gedcomx.common.URI;
import org.gedcomx.conclusion.Conclusion;
import org.gedcomx.rt.json.JsonElementWrapper;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 * A reservation.
 *
 */
@XmlRootElement
@JsonElementWrapper (name = "reservations")
@XmlType ( name = "Reservation", propOrder = {"ordinanceType", "person", "spouse", "father", "mother" } )
public class Reservation extends Conclusion {

  private URI ordinanceType;
  private ResourceReference person;
  private ResourceReference spouse;
  private ResourceReference father;
  private ResourceReference mother;

  /**
   * The ordinanceType of the ordinance.
   *
   * @return The ordinanceType of the ordinance.
   */
  @XmlAttribute
  @XmlQNameEnumRef(OrdinanceType.class)
  public URI getOrdinanceType() {
    return ordinanceType;
  }

  /**
   * The ordinanceType of the ordinance.
   *
   * @param ordinanceType The ordinanceType of the ordinance.
   */
  public void setOrdinanceType(URI ordinanceType) {
    this.ordinanceType = ordinanceType;
  }

  /**
   * Build up this ordinance with a ordinanceType.
   *
   * @param type The ordinanceType.
   * @return this.
   */
  public Reservation type(URI type) {
    setOrdinanceType(type);
    return this;
  }

  /**
   * Build up this ordinance with a ordinanceType.
   *
   * @param type The ordinanceType.
   * @return this.
   */
  public Reservation type(OrdinanceType type) {
    setKnownOrdinanceType(type);
    return this;
  }

  /**
   * The known ordinanceType of the ordinance.
   *
   * @return The ordinanceType of the ordinance.
   */
  @XmlTransient
  @JsonIgnore
  public OrdinanceType getKnownOrdinanceType() {
    return getOrdinanceType() == null ? null : OrdinanceType.fromQNameURI(getOrdinanceType());
  }

  /**
   * The ordinanceType of the ordinance.
   *
   * @param type The ordinanceType of the ordinance.
   */
  @JsonIgnore
  public void setKnownOrdinanceType(OrdinanceType type) {
    setOrdinanceType(type == null ? null : type.toQNameURI());
  }

  public ResourceReference getPerson() {
    return person;
  }

  public void setPerson(ResourceReference person) {
    this.person = person;
  }

  public ResourceReference getSpouse() {
    return spouse;
  }

  public void setSpouse(ResourceReference spouse) {
    this.spouse = spouse;
  }

  public ResourceReference getFather() {
    return father;
  }

  public void setFather(ResourceReference father) {
    this.father = father;
  }

  public ResourceReference getMother() {
    return mother;
  }

  public void setMother(ResourceReference mother) {
    this.mother = mother;
  }
}
