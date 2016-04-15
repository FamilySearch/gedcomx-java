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
import org.familysearch.platform.ordinances.OrdinanceAssignee;
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
@XmlType ( name = "Reservation", propOrder = {"ordinanceType", "spouse", "father", "mother", "assignee" } )
public class Reservation extends Conclusion {

  private URI ordinanceType;
  private ResourceReference spouse;
  private ResourceReference father;
  private ResourceReference mother;
  private ResourceReference assignee;

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

  /**
   * The spouse associated with the ordinance, if the ordinance type is sealing-to-spouse.
   *
   * @return The spouse associated with the ordinance, if the ordinance type is sealing-to-spouse.
   */
  public ResourceReference getSpouse() {
    return spouse;
  }

  /**
   * The spouse associated with the ordinance, if the ordinance type is sealing-to-spouse.
   *
   * @param spouse The spouse associated with the ordinance, if the ordinance type is sealing-to-spouse.
   */
  public void setSpouse(ResourceReference spouse) {
    this.spouse = spouse;
  }

  /**
   * Build out this reservation with a spouse.
   * 
   * @param spouse The spouse.
   * @return this.
   */
  public Reservation spouse(ResourceReference spouse) {
    setSpouse(spouse);
    return this;
  }

  /**
   * The father associated with the ordinance, if the ordinance type is sealing-to-parents.
   *
   * @return The father associated with the ordinance, if the ordinance type is sealing-to-parents.
   */
  public ResourceReference getFather() {
    return father;
  }

  /**
   * The father associated with the ordinance, if the ordinance type is sealing-to-parents.
   * 
   * @param father The father associated with the ordinance, if the ordinance type is sealing-to-parents.
   */
  public void setFather(ResourceReference father) {
    this.father = father;
  }

  /**
   * Build out this reservation with a father.
   *
   * @param father The father.
   * @return this.
   */
  public Reservation father(ResourceReference father) {
    setFather(father);
    return this;
  }

  /**
   * The mother associated with the ordinance, if the ordinance type is sealing-to-parents.
   * 
   * @return The mother associated with the ordinance, if the ordinance type is sealing-to-parents.
   */
  public ResourceReference getMother() {
    return mother;
  }

  /**
   * The mother associated with the ordinance, if the ordinance type is sealing-to-parents.
   * 
   * @param mother The mother associated with the ordinance, if the ordinance type is sealing-to-parents.
   */
  public void setMother(ResourceReference mother) {
    this.mother = mother;
  }

  /**
   * Build out this reservation with a mother.
   *
   * @param mother The mother.
   * @return this.
   */
  public Reservation mother(ResourceReference mother) {
    setMother(mother);
    return this;
  }

  /**
   * The user or entity assigned to fulfill the ordinance work for this reservation. If no assignee is provided, the assignee
   * is assumed to be the owner of the reservation.
   * 
   * @return The user or entity assigned to fulfill the ordinance work for this reservation.
   */
  public ResourceReference getAssignee() {
    return assignee;
  }

  /**
   * The user or entity assigned to fulfill the ordinance work for this reservation. If no assignee is provided, the assignee
   * is assumed to be the owner of the reservation.
   * 
   * @param assignee The user or entity assigned to fulfill the ordinance work for this reservation.
   */
  public void setAssignee(ResourceReference assignee) {
    this.assignee = assignee;
  }

  /**
   * Build out this reservation with an assignee.
   *
   * @param assignee The assignee.
   * @return this.
   */
  public Reservation assignee(ResourceReference assignee) {
    setAssignee(assignee);
    return this;
  }

  /**
   * Whether this reservation is assigned to a specific known ordinance assignee.
   *
   * @param knownAssignee The ordinance assignee.
   * @return Whether this reservation is assigned to a specific known ordinance assignee.
   */
  public boolean isAssignedTo(OrdinanceAssignee knownAssignee) {
    return this.assignee != null && this.assignee.getResource() != null && this.assignee.getResource().equals(knownAssignee.toQNameURI());
  }
}
