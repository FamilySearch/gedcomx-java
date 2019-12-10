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
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.webcohesion.enunciate.metadata.json.JsonSeeAlso;
import com.webcohesion.enunciate.metadata.qname.XmlQNameEnumRef;

import org.gedcomx.common.ResourceReference;
import org.gedcomx.common.URI;
import org.gedcomx.conclusion.Conclusion;
import org.gedcomx.conclusion.Date;
import org.gedcomx.rt.json.JsonElementWrapper;

@XmlRootElement(name = "ordinance")
@JsonElementWrapper(name = "ordinances")
@XmlType( name = "Ordinance", propOrder = {"type", "status", "statusReasons", "participants", "reservation", "date", "templeCode"})
@JsonInclude ( JsonInclude.Include.NON_NULL )
//
// @XmlQNameEnumRef(OrdinanceStatusReason.class)   Cannot be used on getStatusReasons() because of enunciate bug and the OrdinanceStatusReason docs
//    were not included by enunciate.  In order to get those OrdinanceStatusReason docs we add @XmlSeeAlso and @JsonSeeAlso at the class level.
@XmlSeeAlso(OrdinanceStatusReason.class)
@JsonSeeAlso(OrdinanceStatusReason.class)
public class Ordinance extends Conclusion {

  private URI type;
  private URI status;

  private List<URI> statusReasons;
  private List<OrdinanceParticipant> participants;
  private OrdinanceReservation reservation;

  private Date date;
  private String templeCode;

  /**
   * Gets the type of ordinance
   * @return the type of ordinance
   */
  @XmlAttribute
  @XmlQNameEnumRef(OrdinanceType.class)
  public URI getType() {
    return type;
  }

  /**
   * Sets the type of ordinance
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
   * Build up this ordinance with an ordinance type URI.
   *
   * @param type The ordinance type.
   * @return this.
   */
  public Ordinance type(URI type) {
    setType(type);
    return this;
  }

  /**
   * Build up this ordinance with a known ordinance type.
   *
   * @param knownType The known ordinance type.
   * @return this.
   */
  public Ordinance type(OrdinanceType knownType) {
    setKnownType(knownType);
    return this;
  }

  /**
   * Get the status of this ordinance
   * @return the status of this ordinance
   */
  @XmlAttribute
  @XmlQNameEnumRef(OrdinanceStatus.class)
  public URI getStatus() {
    return status;
  }

  /**
   * Set the status of this ordinance
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
   * Build up this ordinance with an ordinance status URI.
   *
   * @param status The ordinance status.
   * @return this.
   */
  public Ordinance status(URI status) {
    setStatus(status);
    return this;
  }

  /**
   * Build up this ordinance with a known ordinance status.
   *
   * @param knownStatus The known ordinance status.
   * @return this.
   */
  public Ordinance status(OrdinanceStatus knownStatus) {
    setKnownStatus(knownStatus);
    return this;
  }

  /**
   * Additional information regarding the ordinance status.
   *
   * @return The status reasons for the ordinance status.
   */
  @XmlElement(name="statusReason")
  @JsonProperty("statusReasons")
  // Do not include this annotation: @XmlQNameEnumRef(OrdinanceStatusReason.class)  There is a bug in enunciate for Collection<URI/String>
  public List<URI> getStatusReasons() {
    return statusReasons;
  }

  @JsonProperty("statusReasons")
  public void setStatusReasons(List<URI> statusReasons) {
    this.statusReasons = statusReasons;
  }

  public void addStatusReason(URI statusReason) {
    if (statusReason != null) {
      if (statusReasons == null) {
        statusReasons = new ArrayList<URI>();
      }
      statusReasons.add(statusReason);
    }
  }

  public void addKnownStatusReason(OrdinanceStatusReason knownStatusReason) {
    addStatusReason(knownStatusReason == null ? null : knownStatusReason.toQNameURI());
  }

  /**
   * Build out the status reasons.
   *
   * @param statusReason The URI for the status reason.
   * @return this.
   */
  public Ordinance statusReason(URI statusReason) {
    addStatusReason(statusReason);
    return this;
  }

  public Ordinance statusReason(OrdinanceStatusReason knownStatusReason) {
    addKnownStatusReason(knownStatusReason);
    return this;
  }

  /**
   * The participants for this ordinance.
   *
   * @return The participants for this ordinance.
   */
  @XmlElement(name="participant")
  @JsonProperty("participants")
  public List<OrdinanceParticipant> getParticipants() {
    return participants;
  }

  @JsonProperty("participants")
  public void setParticipants(List<OrdinanceParticipant> participants) {
    this.participants = participants;
  }

  public void addParticipant(OrdinanceParticipant participant) {
    if (participant != null) {
      if (participants == null) {
        participants = new ArrayList<OrdinanceParticipant>();
      }
      participants.add(participant);
    }
  }

  /**
   * Build out the participants.
   *
   * @param  participant the participant.
   * @return this.
   */
  public Ordinance participant(OrdinanceParticipant participant) {
    addParticipant(participant);
    return this;
  }

  /**
   * Reservation for this ordinance
   * @return the reservation for this ordinance
   */
  @XmlElement(name = "reservation")
  @JsonProperty("reservation")
  public OrdinanceReservation getReservation() {
    return reservation;
  }

  @JsonProperty("reservation")
  public void setReservation(OrdinanceReservation reservation) {
    this.reservation = reservation;
  }

  /**
   * Build out this ordinance with a reservation.
   *
   * @param reservation the ordinance reservation.
   * @return this
   */
  public Ordinance reservation(OrdinanceReservation reservation) {
    setReservation(reservation);
    return this;
  }

  /**
   * The date of this ordinance.
   *
   * @return The date of this ordinance.
   */
  public Date getDate() {
    return date;
  }

  /**
   * The date of this ordinance.
   *
   * @param date The date of this ordinance.
   */
  public void setDate(Date date) {
    this.date = date;
  }

  public Ordinance date(Date date) {
    setDate(date);
    return this;
  }

  /**
   * The code for the temple at which the ordinance was performed.
   *
   * @return The code for the temple at which the ordinance was performed.
   */
  public String getTempleCode() {
    return templeCode;
  }

  /**
   * The code for the temple at which the ordinance was performed.
   *
   * @param templeCode The code for the temple at which the ordinance was performed.
   */
  public void setTempleCode(String templeCode) {
    this.templeCode = templeCode;
  }

  public Ordinance templeCode(String templeCode) {
    setTempleCode(templeCode);
    return this;
  }
}
