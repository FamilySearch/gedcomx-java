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

import org.familysearch.platform.reservations.Reservation;
import org.gedcomx.common.ResourceReference;
import org.gedcomx.common.URI;
import org.gedcomx.conclusion.Date;
import org.gedcomx.rt.json.JsonElementWrapper;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "ordinance")
@JsonElementWrapper(name = "ordinances")
@XmlType( name = "Ordinance" )
public class Ordinance extends Reservation {

  private Boolean living;
  private Date date;
  private String templeCode;

  /**
   * Whether this ordinance was performed during the life of the person.
   *
   * @return Whether this ordinance was performed during the life of the person.
   */
  @XmlAttribute
  public Boolean getLiving() {
    return living;
  }

  /**
   * Whether this ordinance was performed during the life of the person.
   *
   * @param living Whether this ordinance was performed during the life of the person.
   */
  public void setLiving(Boolean living) {
    this.living = living;
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

  @Override
  public Ordinance type(URI type) {
    return (Ordinance) super.type(type);
  }

  @Override
  public Ordinance type(OrdinanceType type) {
    return (Ordinance) super.type(type);
  }

  @Override
  public Ordinance status(URI status) {
    return (Ordinance) super.status(status);
  }

  @Override
  public Ordinance status(OrdinanceStatus status) {
    return (Ordinance) super.status(status);
  }

  @Override
  public Ordinance spouse(ResourceReference spouse) {
    return (Ordinance) super.spouse(spouse);
  }

  @Override
  public Ordinance father(ResourceReference father) {
    return (Ordinance) super.father(father);
  }

  @Override
  public Ordinance mother(ResourceReference mother) {
    return (Ordinance) super.mother(mother);
  }

  @Override
  public Ordinance assignee(ResourceReference assignee) {
    return (Ordinance) super.assignee(assignee);
  }
}
