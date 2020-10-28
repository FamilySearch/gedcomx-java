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

import java.util.Date;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
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
  private Date reserveDate;                 // a java.util.Date
  private Date updateDate;                  // a java.util.Date
  private Date expirationDate;              // a java.util.Date
  private URI claimType;
  private URI assigneeType;

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
   * The reserve timestamp for the ordinance reservation.
   *
   * @return The reserve timestamp for the ordinance reservation.
   */
  public Date getReserveDate() {
    return reserveDate;
  }

  public void setReserveDate(Date reserveDate) {
    this.reserveDate = reserveDate;
  }

  /**
   * Build up this ordinance reservation with a reserve date.
   *
   * @param reserveDate The reserveDate.
   * @return this.
   */
  public OrdinanceReservation reserveDate(Date reserveDate) {
    this.reserveDate = reserveDate;
    return this;
  }

  /**
   * The update timestamp for the ordinance reservation.
   *
   * @return The update timestamp for the ordinance reservation.
   */
  public Date getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(Date updateDate) {
    this.updateDate = updateDate;
  }

  /**
   * Build up this ordinance reservation with an update date.
   *
   * @param updateDate The modified date.
   * @return this.
   */
  public OrdinanceReservation updateDate(Date updateDate) {
    this.updateDate = updateDate;
    return this;
  }

  /**
   * The expiration timestamp for the ordinance reservation.  This is the timestamp when this ordinance reservation will expire.
   *
   * @return The expiration timestamp for the ordinance reservation.
   */
  public Date getExpirationDate() {
    return expirationDate;
  }

  public void setExpirationDate(Date expirationDate) {
    this.expirationDate = expirationDate;
  }

  /**
   * Build up this ordinance reservation with a expiration date.
   *
   * @param expirationDate The expiration date.
   * @return this.
   */
  public OrdinanceReservation expirationDate(Date expirationDate) {
    this.expirationDate = expirationDate;
    return this;
  }

  /**
   * Get the claimType indicating how this reservation was reserved.
   * @return The claimType indicating how this reservation was reserved.
   */
  @XmlAttribute
  @XmlQNameEnumRef(OrdinanceReservationClaimType.class)
  public URI getClaimType() {
    return claimType;
  }

  /**
   * Set the claimType indicating how this reservation was reserved.
   * @param claimType The claimType indicating how this reservation was reserved.
   */
  public void setClaimType(URI claimType) {
    this.claimType = claimType;
  }

  /**
   * Build out this reservation with the claimType indicating how this reservation was reserved.
   *
   * @param claimType The claimType indicating how this reservation was reserved.
   * @return this.
   */
  public OrdinanceReservation claimType(URI claimType) {
    setClaimType(claimType);
    return this;
  }

  /**
   * The enum referencing the claimType indicating how this reservation was reserved.
   *
   * @return The enum referencing the claimType indicating how this reservation was reserved.
   */
  @XmlTransient
  @JsonIgnore
  public OrdinanceReservationClaimType getKnownClaimType() {
    return getClaimType() == null ? null : OrdinanceReservationClaimType.fromQNameURI(getClaimType());
  }

  /**
   * Set the claimType indicating how this reservation was reserved from an enumeration of known ordinance reservation claim types.
   *
   * @param knownClaimType The enum referencing the claimType indicating how this reservation was reserved.
   */
  @JsonIgnore
  public void setKnownClaimType(OrdinanceReservationClaimType knownClaimType) {
    setClaimType(knownClaimType == null ? null : knownClaimType.toQNameURI());
  }

  /**
   * Build out this reservation with a known claimType indicating how this reservation was reserved.
   *
   * @param knownClaimType The known claimType.
   * @return this.
   */
  public OrdinanceReservation claimType(OrdinanceReservationClaimType knownClaimType) {
    setKnownClaimType(knownClaimType);
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

}
