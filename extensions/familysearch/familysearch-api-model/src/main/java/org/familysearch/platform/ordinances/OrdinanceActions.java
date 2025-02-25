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

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonInclude;

import org.gedcomx.rt.json.JsonElementWrapper;

//@XmlRootElement(name = "actions")   // not a root element or something that is added as an extension element so not needed
@JsonElementWrapper(name = "actions")
@XmlType( name = "OrdinanceActions" )
@JsonInclude ( JsonInclude.Include.NON_NULL )
@Schema(description = "The actions that can be performed on an ordinance.")
public class OrdinanceActions {

  // These have default values so they will always appear as either "true" or "false"
  @Schema(description = "True if this ordinance is reservable; false otherwise.")
  private Boolean reservable = false;

  @Schema(description = "True if this ordinance is unReservable; false otherwise.")
  private Boolean unReservable = false;

  @Schema(description = "True if this ordinance is shareable; false otherwise.")
  private Boolean shareable = false;

  @Schema(description = "True if this ordinance is unShareable; false otherwise.")
  private Boolean unShareable = false;

  // The private Boolean transferable is not supported yet
  @Schema(description = "True if this ordinance is transferable; false otherwise.")
  private Boolean printable = false;


  /**
   * Get if this Ordinance is reservable.
   *
   * @return true if this Ordinance is reservable; false otherwise.
   */
  @XmlAttribute
  public Boolean isReservable() {
    return reservable;
  }

  /**
   * Set if this Ordinance is reservable.
   *
   * @param reservable true if this Ordinance is reservable; false otherwise.
   */
  public void setReservable(Boolean reservable) {
    this.reservable = reservable;
  }

  /**
   * Build out this OrdinanceActions with a reservable state.
   *
   * @param reservable The reservable state for this Ordinance.
   * @return this.
   */
  public OrdinanceActions reservable(final Boolean reservable) {
    this.reservable = reservable;
    return this;
  }

  /**
   * Get if this Ordinance is unReservable.
   *
   * @return true if this Ordinance is unReservable; false otherwise.
   */
  @XmlAttribute
  public Boolean isUnReservable() {
    return unReservable;
  }

  /**
   * Set if this Ordinance is unReservable.
   *
   * @param unReservable true if this Ordinance is unReservable; false otherwise.
   */
  public void setUnReservable(Boolean unReservable) {
    this.unReservable = unReservable;
  }

  /**
   * Build out this OrdinanceActions with a unReservable state.
   *
   * @param unReservable The unReservable state for this Ordinance.
   * @return this.
   */
  public OrdinanceActions unReservable(final Boolean unReservable) {
    this.unReservable = unReservable;
    return this;
  }

  /**
   * Get if this Ordinance is shareable.
   *
   * @return true if this Ordinance is shareable; false otherwise.
   */
  @XmlAttribute
  public Boolean isShareable() {
    return shareable;
  }

  /**
   * Set if this Ordinance is shareable.
   *
   * @param shareable true if this Ordinance is shareable; false otherwise.
   */
  public void setShareable(Boolean shareable) {
    this.shareable = shareable;
  }

  /**
   * Build out this OrdinanceActions with a shareable state.
   *
   * @param shareable The shareable state for this Ordinance.
   * @return this.
   */
  public OrdinanceActions shareable(final Boolean shareable) {
    this.shareable = shareable;
    return this;
  }

  /**
   * Get if this Ordinance is unShareable.
   *
   * @return true if this Ordinance is unShareable; false otherwise.
   */
  @XmlAttribute
  public Boolean isUnShareable() {
    return unShareable;
  }

  /**
   * Set if this Ordinance is unShareable.
   *
   * @param unShareable true if this Ordinance is unShareable; false otherwise.
   */
  public void setUnShareable(Boolean unShareable) {
    this.unShareable = unShareable;
  }

  /**
   * Build out this OrdinanceActions with a unShareable state.
   *
   * @param unShareable The unShareable state for this Ordinance.
   * @return this.
   */
  public OrdinanceActions unShareable(final Boolean unShareable) {
    this.unShareable = unShareable;
    return this;
  }

  /**
   * Get if this Ordinance is printable.
   *
   * @return true if this Ordinance is printable; false otherwise.
   */
  @XmlAttribute
  public Boolean isPrintable() {
    return printable;
  }

  /**
   * Set if this Ordinance is printable.
   *
   * @param printable true if this Ordinance is printable; false otherwise.
   */
  public void setPrintable(Boolean printable) {
    this.printable = printable;
  }

  /**
   * Build out this OrdinanceActions with a printable state.
   *
   * @param printable The printable state for this Ordinance.
   * @return this.
   */
  public OrdinanceActions printable(final Boolean printable) {
    this.printable = printable;
    return this;
  }
}
