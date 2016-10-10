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

import com.fasterxml.jackson.annotation.JsonInclude;
import org.codehaus.enunciate.qname.XmlQNameEnumRef;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.familysearch.platform.ordinances.OrdinanceAssignee;
import org.familysearch.platform.ordinances.OrdinanceStatus;
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
 * An ordinance reservation.
 *
 */
@XmlRootElement
@JsonElementWrapper (name = "reservations")
@XmlType ( name = "Reservation", propOrder = {"ordinanceType", "type", "status", "spouse", "father", "mother", "assignee" } )
@JsonInclude ( JsonInclude.Include.NON_NULL )
public class Reservation extends Conclusion {

  private URI type;
  private URI status;
  private ResourceReference spouse;
  private ResourceReference father;
  private ResourceReference mother;
  private ResourceReference assignee;

  /**
   * gets the type of ordinance
   * @return the type of ordinance
   */
  @XmlAttribute
  @XmlQNameEnumRef(OrdinanceType.class)
  public URI getType() {
    return type;
  }

  /**
   * sets the type of ordinance
   * @param type the type of ordinance
   */
  public void setType(URI type) {
    this.type = type;
  }

  /**
   * The enum referencing the known ordinance type, or {@link OrdinanceType#OTHER} if not known.
   *
   * @return The enum referencing the known ordinance type, or {@link OrdinanceType#OTHER} if not known.
   */
  @XmlTransient
  @JsonIgnore
  public OrdinanceType getKnownType() {
    return getType() == null ? null : OrdinanceType.fromQNameURI(getType());
  }

  /**
   * Set the ordinance type from an enumeration of known ordinance types.
   *
   * @param knownType The ordinance type.
   */
  @JsonIgnore
  public void setKnownType(OrdinanceType knownType) {
    setType(knownType == null ? null : knownType.toQNameURI());
  }

  /**
   * Build up this ordinance with a ordinance type.
   *
   * @param type The ordinance type.
   * @return this.
   */
  public Reservation type(URI type) {
    setType(type);
    return this;
  }

  /**
   * Build up this ordinance with a ordinance type.
   *
   * @param type The ordinance type.
   * @return this.
   */
  public Reservation type(OrdinanceType type) {
    setKnownType(type);
    return this;
  }

  @Deprecated
  @XmlAttribute
  @XmlQNameEnumRef(OrdinanceType.class)
  @org.codehaus.enunciate.json.JsonIgnore
  public URI getOrdinanceType() {
    return null;
  }

  @Deprecated
  public void setOrdinanceType(URI ordinanceType) {
    setType(ordinanceType);
  }

  /**
   * gets the status of this ordinance
   * @return the status of this ordinance
   */
  @XmlAttribute
  @XmlQNameEnumRef(OrdinanceStatus.class)
  public URI getStatus() {
    return status;
  }

  /**
   * sets the status of this ordinance
   * @param status the status of this ordinance
   */
  public void setStatus(URI status) {
    this.status = status;
  }

  /**
   * The enum referencing the known ordinance status, or {@link OrdinanceStatus#OTHER} if not known.
   *
   * @return The enum referencing the known ordinance status, or {@link OrdinanceStatus#OTHER} if not known.
   */
  @XmlTransient
  @JsonIgnore
  public OrdinanceStatus getKnownStatus() {
    return getStatus() == null ? null : OrdinanceStatus.fromQNameURI(getStatus());
  }

  /**
   * Set the ordinance status from an enumeration of known ordinance statuses.
   *
   * @param knownStatus The ordinance status.
   */
  @JsonIgnore
  public void setKnownStatus(OrdinanceStatus knownStatus) {
    setStatus(knownStatus == null ? null : knownStatus.toQNameURI());
  }

  /**
   * Build up this ordinance with a ordinance status.
   *
   * @param status The ordinance status.
   * @return this.
   */
  public Reservation status(URI status) {
    setStatus(status);
    return this;
  }

  /**
   * Build up this ordinance with a ordinance status.
   *
   * @param status The ordinance status.
   * @return this.
   */
  public Reservation status(OrdinanceStatus status) {
    setKnownStatus(status);
    return this;
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
