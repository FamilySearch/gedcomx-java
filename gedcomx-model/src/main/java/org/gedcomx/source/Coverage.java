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

import org.codehaus.enunciate.Facet;
import org.codehaus.enunciate.qname.XmlQNameEnumRef;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.gedcomx.common.URI;
import org.gedcomx.conclusion.Date;
import org.gedcomx.conclusion.PlaceReference;
import org.gedcomx.links.HypermediaEnabledData;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.rt.json.JsonElementWrapper;
import org.gedcomx.types.RecordType;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 * A description of the coverage of a resource.
 *
 * @author Ryan Heaton
 */
@XmlRootElement
@JsonElementWrapper ( name = "coverage" )
@XmlType ( name = "Coverage" )
public class Coverage extends HypermediaEnabledData {

  private PlaceReference spatial;
  private Date temporal;
  /**
   * @see org.gedcomx.types.RecordType
   */
  private URI recordType;

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
  @Facet ( name = GedcomxConstants.FACET_GEDCOMX_RECORD )
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
