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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.webcohesion.enunciate.metadata.qname.XmlQNameEnumRef;

import org.gedcomx.common.ResourceReference;
import org.gedcomx.common.URI;
import org.gedcomx.rt.json.JsonElementWrapper;

//@XmlRootElement(name = "reservation")   // not a root element or something that is added as an extension element so not needed
@JsonElementWrapper(name = "reservation")
@XmlType( name = "OrdinanceReservation" )
@JsonInclude ( JsonInclude.Include.NON_NULL )
public class OrdinanceReservation {

  private ResourceReference owner;
  private Date reserved;
  private Date modified;
  private Date expired;
  private URI assigneeType;
  private List<URI> possibleAssigneeTypes;

  /**
   * Get the owner associated with the ordinance reservation
   *
   * @return The owner associated with the ordinance reservation
   */
  public ResourceReference getOwner() {
    return owner;
  }

  /**
   * Set owner associated with the ordinance reservation
   *
   * @param owner The owner associated with the ordinance reservation
   */
  public void setOwner(ResourceReference owner) {
    this.owner = owner;
  }

  /**
   * Build out this OrdinanceReservation with a owner.
   *
   * @param owner The owner.
   * @return this.
   */
  public OrdinanceReservation owner(ResourceReference owner) {
    setOwner(owner);
    return this;
  }

  /**
   * The reserved timestamp for the ordinance reservation.
   *
   * @return The reserved timestamp for the ordinance reservation.
   */
  public Date getReserved() {
    return reserved;
  }

  public void setReserved(Date reserved) {
    this.reserved = reserved;
  }

  /**
   * Build up this ordinance reservation with a reserved date.
   *
   * @param reserved The reserved date.
   * @return this.
   */
  public OrdinanceReservation reserved(Date reserved) {
    this.reserved = reserved;
    return this;
  }

  /**
   * The modified timestamp for the ordinance reservation.
   *
   * @return The modified timestamp for the ordinance reservation.
   */
  public Date getModified() {
    return modified;
  }

  public void setModified(Date modified) {
    this.modified = modified;
  }

  /**
   * Build up this ordinance reservation with a modified date.
   *
   * @param modified The modified date.
   * @return this.
   */
  public OrdinanceReservation modified(Date modified) {
    this.modified = modified;
    return this;
  }

  /**
   * The expired timestamp for the ordinance reservation.  This is the timestamp when the ordinance reservation will expire.
   *
   * @return The expired timestamp for the ordinance reservation.
   */
  public Date getExpired() {
    return expired;
  }

  public void setExpired(Date expired) {
    this.expired = expired;
  }

  /**
   * Build up this ordinance reservation with a expired date.
   *
   * @param expired The expired date.
   * @return this.
   */
  public OrdinanceReservation expired(Date expired) {
    this.expired = expired;
    return this;
  }

  /**
   * Get the assigneeType this reservation is assigned to.
   * @return The assigneeType this reservation is assigned to.
   */
  @XmlAttribute
  @XmlQNameEnumRef(OrdinanceReservationAssigneeType.class)
  public URI getAssigneeType() {
    return assigneeType;
  }

  /**
   * Set the assigneeType this reservation is assigned to.
   * @param assigneeType The assigneeType this reservation is assigned to.
   */
  public void setAssigneeType(URI assigneeType) {
    this.assigneeType = assigneeType;
  }

  /**
   * Build out this reservation with the assigneeType this reservation is assigned to.
   *
   * @param assigneeType The assigneeType this reservation is assigned to.
   * @return this.
   */
  public OrdinanceReservation assigneeType(URI assigneeType) {
    setAssigneeType(assigneeType);
    return this;
  }

  /**
   * The enum referencing the assigneeType this reservation is assigned to.
   *
   * @return The enum referencing the assigneeType this reservation is assigned to.
   */
  @XmlTransient
  @JsonIgnore
  public OrdinanceReservationAssigneeType getKnownAssigneeType() {
    return getAssigneeType() == null ? null : OrdinanceReservationAssigneeType.fromQNameURI(getAssigneeType());
  }

  /**
   * Set the assigneeType this reservation is assigned to from an enumeration of known ordinance reservation assignee types.
   *
   * @param knownAssigneeType The enum referencing the assigneeType this reservation is assigned to.
   */
  @JsonIgnore
  public void setKnownAssigneeType(OrdinanceReservationAssigneeType knownAssigneeType) {
    setAssigneeType(knownAssigneeType == null ? null : knownAssigneeType.toQNameURI());
  }

  /**
   * Build out this reservation with a known assigneeType this reservation is assigned to.
   *
   * @param knownAssigneeType The known assigneeType.
   * @return this.
   */
  public OrdinanceReservation assigneeType(OrdinanceReservationAssigneeType knownAssigneeType) {
    setKnownAssigneeType(knownAssigneeType);
    return this;
  }

  /**
   * The possible assigneeTypes the reservation could be assigned to.
   *
   * @return The possible assigneeTypes the reservation could be assigned to.
   */
  @XmlElement(name="possibleAssigneeType")
  @JsonProperty("possibleAssigneeTypes")
  // Do not include this annotation: @XmlQNameEnumRef(OrdinanceReservationAssigneeType.class)  There is a bug in enunciate for Collection<URI/String>
  public List<URI> getPossibleAssigneeTypes() {
    return possibleAssigneeTypes;
  }

  @JsonProperty("possibleAssigneeTypes")
  public void setPossibleAssigneeTypes(List<URI> possibleAssigneeTypes) {
    this.possibleAssigneeTypes = possibleAssigneeTypes;
  }

  public void addPossibleAssigneeType(URI assigneeType) {
    if (assigneeType != null) {
      if (possibleAssigneeTypes == null) {
        possibleAssigneeTypes = new ArrayList<>();
      }
      possibleAssigneeTypes.add(assigneeType);
    }
  }

  public void addKnownPossibleAssigneeType(OrdinanceReservationAssigneeType knownAssigneeType) {
    addPossibleAssigneeType(knownAssigneeType == null ? null : knownAssigneeType.toQNameURI());
  }

  /**
   * Build out the possible assigneeTypes the reservation could be assigned to.
   *
   * @param assigneeType The URI for the possible assigneeType the reservation could be assigned to.
   *
   * @return this.
   */
  public OrdinanceReservation possibleAssigneeType(URI assigneeType) {
    addPossibleAssigneeType(assigneeType);
    return this;
  }

  public OrdinanceReservation possibleAssigneeType(OrdinanceReservationAssigneeType knownAssigneeType) {
    addKnownPossibleAssigneeType(knownAssigneeType);
    return this;
  }


}
