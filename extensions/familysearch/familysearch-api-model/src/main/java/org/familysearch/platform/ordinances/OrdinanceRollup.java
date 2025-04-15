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
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.webcohesion.enunciate.metadata.qname.XmlQNameEnumRef;

import org.gedcomx.common.URI;
import org.gedcomx.conclusion.Conclusion;
import org.gedcomx.rt.json.JsonElementWrapper;

@XmlRootElement(name = "ordinanceRollup")
@JsonElementWrapper(name = "ordinanceRollups")
@XmlType( name = "OrdinanceRollup", propOrder = {"type", "rollupStatus"})
@JsonInclude ( JsonInclude.Include.NON_NULL )
@Schema(description = "An ordinance rollup conclusion.")
public class OrdinanceRollup extends Conclusion {

  @Schema(description = "The type of ordinance.", implementation = OrdinanceType.class, enumAsRef = true)
  private URI type;

  @Schema(description = "The rollup status of this ordinance.")
  private URI rollupStatus;


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
  public OrdinanceRollup type(URI type) {
    setType(type);
    return this;
  }

  /**
   * Build up this ordinance with a known ordinance type.
   *
   * @param knownType The known ordinance type.
   * @return this.
   */
  public OrdinanceRollup type(OrdinanceType knownType) {
    setKnownType(knownType);
    return this;
  }

  /**
   * Get the rollupStatus of this ordinance
   * @return the rollupStatus of this ordinance
   */
  @XmlAttribute
  @XmlQNameEnumRef(OrdinanceRollupStatus.class)
  public URI getRollupStatus() {
    return rollupStatus;
  }

  /**
   * Set the rollupStatus of this ordinance
   * @param rollupStatus the rollupStatus of this ordinance
   */
  public void setRollupStatus(URI rollupStatus) {
    this.rollupStatus = rollupStatus;
  }

  /**
   * The enum referencing the known ordinance rollupStatus
   *
   * @return The enum referencing the known ordinance rollupStatus
   */
  @XmlTransient
  @JsonIgnore
  public OrdinanceRollupStatus getKnownRollupStatus() {
    return getRollupStatus() == null ? null : OrdinanceRollupStatus.fromQNameURI(getRollupStatus());
  }

  /**
   * Set the ordinance rollupStatus from an enumeration of known ordinance rollup statuses.
   *
   * @param knownRollupStatus The ordinance rollupStatus.
   */
  @JsonIgnore
  public void setKnownRollupStatus(OrdinanceRollupStatus knownRollupStatus) {
    setRollupStatus(knownRollupStatus == null ? null : knownRollupStatus.toQNameURI());
  }

  /**
   * Build up this ordinance with an ordinance rollupStatus URI.
   *
   * @param rollupStatus The ordinance rollupStatus.
   * @return this.
   */
  public OrdinanceRollup rollupStatus(URI rollupStatus) {
    setRollupStatus(rollupStatus);
    return this;
  }

  /**
   * Build up this ordinance with a known ordinance rollupStatus.
   *
   * @param knownRollupStatus The known ordinance rollupStatus.
   * @return this.
   */
  public OrdinanceRollup rollupStatus(OrdinanceRollupStatus knownRollupStatus) {
    setKnownRollupStatus(knownRollupStatus);
    return this;
  }

}
