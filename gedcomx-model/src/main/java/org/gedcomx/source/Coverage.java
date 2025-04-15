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
package org.gedcomx.source;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.webcohesion.enunciate.metadata.Facet;
import com.webcohesion.enunciate.metadata.qname.XmlQNameEnumRef;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.gedcomx.common.URI;
import org.gedcomx.conclusion.Date;
import org.gedcomx.conclusion.PlaceReference;
import org.gedcomx.links.HypermediaEnabledData;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.rt.json.JsonElementWrapper;
import org.gedcomx.types.RecordType;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlType;

/**
 * A description of the coverage of a resource.
 *
 * @author Ryan Heaton
 */
@XmlRootElement
@JsonElementWrapper ( name = "coverage" )
@XmlType ( name = "Coverage" )
@JsonInclude ( JsonInclude.Include.NON_NULL )
@Schema(description = "A description of the coverage of a resource.")
public class Coverage extends HypermediaEnabledData {

  @Schema(description = "Spatial coverage.")
  private PlaceReference spatial;

  @Schema(description = "Temporal coverage.")
  private Date temporal;
  /**
   * @see org.gedcomx.types.RecordType
   */
  @Schema(description = "The type of record being covered, if any.", implementation = RecordType.class, enumAsRef = true)
  private URI recordType;

  public Coverage() {
  }

  public Coverage(Coverage copy) {
    super(copy);
    this.spatial = copy.spatial == null ? null : new PlaceReference(copy.spatial);
    this.temporal = copy.temporal == null ? null : new Date(copy.temporal);
    this.recordType = copy.recordType;
  }

  /**
   * Spatial coverage.
   *
   * @return Spatial coverage.
   */
  public PlaceReference getSpatial() {
    return spatial;
  }

  /**
   * Spatial coverage.
   *
   * @param spatial Spatial coverage.
   */
  public void setSpatial(PlaceReference spatial) {
    this.spatial = spatial;
  }

  /**
   * Build out this coverage with spatial coverage.
   *
   * @param spatial The spatial coverage.
   * @return this.
   */
  public Coverage spatial(PlaceReference spatial) {
    setSpatial(spatial);
    return this;
  }

  /**
   * Temporal coverage.
   *
   * @return Temporal coverage.
   */
  public Date getTemporal() {
    return temporal;
  }

  /**
   * Temporal coverage.
   *
   * @param temporal Temporal coverage.
   */
  public void setTemporal(Date temporal) {
    this.temporal = temporal;
  }

  /**
   * Build out this coverage with temporal coverage.
   * @param temporal The temporal coverage.
   * @return This.
   */
  public Coverage temporal(Date temporal) {
    setTemporal(temporal);
    return this;
  }

  /**
   * The type of record being covered, if any.
   *
   * @return The type of record being covered.
   */
  @Facet ( GedcomxConstants.FACET_GEDCOMX_RECORD )
  @XmlQNameEnumRef(RecordType.class)
  public URI getRecordType() {
    return recordType;
  }

  /**
   * The type of record being covered, if any.
   *
   * @param recordType The type of record being covered.
   */
  public void setRecordType(URI recordType) {
    this.recordType = recordType;
  }

  /**
   * Build out this coverage with a record type.
   *
   * @param recordType The record type.
   * @return this.
   */
  public Coverage recordType(URI recordType) {
    setRecordType(recordType);
    return this;
  }

  /**
   * Build out this coverage with a record type.
   *
   * @param recordType The record type.
   * @return this.
   */
  public Coverage recordType(RecordType recordType) {
    setKnownRecordType(recordType);
    return this;
  }

  /**
   * The type of record being covered in this collection, if any.
   *
   * @return The type of record being covered in this collection, if any.
   */
  @XmlTransient
  @JsonIgnore
  public RecordType getKnownRecordType() {
    return getRecordType() == null ? null : RecordType.fromQNameURI(getRecordType());
  }

  /**
   * The type of record being covered in this collection, if any.
   *
   * @param type The type of record being covered in this collection, if any.
   */
  @JsonIgnore
  public void setKnownRecordType(RecordType type) {
    setRecordType(type == null ? null : type.toQNameURI());
  }

}
