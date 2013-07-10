/**
 * Copyright 2011-2012 Intellectual Reserve, Inc.
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
package org.gedcomx.records;

import org.codehaus.enunciate.qname.XmlQNameEnumRef;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.gedcomx.common.URI;
import org.gedcomx.conclusion.Date;
import org.gedcomx.conclusion.PlaceReference;
import org.gedcomx.links.HypermediaEnabledData;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.rt.json.JsonElementWrapper;
import org.gedcomx.types.RecordType;
import org.gedcomx.types.ResourceType;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 * A description of the coverage of a collection by resource type.
 *
 * @author Ryan Heaton
 */
@XmlRootElement
@JsonElementWrapper ( name = "collectionCoverage" )
@XmlType ( name = "CollectionCoverage" )
@org.codehaus.enunciate.Facet ( name = GedcomxConstants.FACET_GEDCOMX_RECORD )
public class CollectionCoverage extends HypermediaEnabledData {

  private PlaceReference spatial;
  private Date temporal;
  private URI resourceType;
  private URI recordType;
  private Integer count;
  private Float completeness;

  /**
   * Spatial coverage. The geographic area(s) covered by the collection. If the resource type and/or record type is provided,
   * this spatial coverage describes the coverage of the resources in the collection of that type. Otherwise,
   * the spatial coverage is applicable to the whole collection.
   *
   * @return Spatial coverage.
   */
  public PlaceReference getSpatial() {
    return spatial;
  }

  /**
   * Spatial coverage. The geographic area(s) covered by the collection. If the resource type and/or record type is provided,
   * this spatial coverage describes the coverage of the resources in the collection of that type. Otherwise,
   * the spatial coverage is applicable to the whole collection.
   *
   * @param spatial Spatial coverage.
   */
  public void setSpatial(PlaceReference spatial) {
    this.spatial = spatial;
  }

  /**
   * Temporal coverage. The time period(s) covered by the collection. If the resource type and/or record type is provided,
   * this temporal coverage describes the coverage of the resources in the collection of that type. Otherwise,
   * the temporal coverage is applicable to the whole collection.
   *
   * @return Temporal coverage.
   */
  public Date getTemporal() {
    return temporal;
  }

  /**
   * Temporal coverage. The time period(s) covered by the collection. If the resource type and/or record type is provided,
   * this temporal coverage describes the coverage of the resources in the collection of that type. Otherwise,
   * the temporal coverage is applicable to the whole collection.
   *
   * @param temporal Temporal coverage.
   */
  public void setTemporal(Date temporal) {
    this.temporal = temporal;
  }

  /**
   * The type of resource being covered in this collection.
   *
   * @return The type of resource being covered in this collection.
   */
  @XmlQNameEnumRef(ResourceType.class)
  public URI getResourceType() {
    return resourceType;
  }

  /**
   * The type of resource being covered in this collection.
   *
   * @param resourceType The type of resource being covered in this collection.
   */
  public void setResourceType(URI resourceType) {
    this.resourceType = resourceType;
  }

  /**
   * The type of resource being covered in this collection.
   *
   * @return The type of resource being covered in this collection.
   */
  @XmlTransient
  @JsonIgnore
  public ResourceType getKnownResourceType() {
    return getResourceType() == null ? null : ResourceType.fromQNameURI(getResourceType());
  }

  /**
   * The type of resource being covered in this collection.
   *
   * @param type The type of resource being covered in this collection.
   */
  @JsonIgnore
  public void setKnownResourceType(ResourceType type) {
    setResourceType(type == null ? null : URI.create(org.codehaus.enunciate.XmlQNameEnumUtil.toURIValue(type)));
  }

  /**
   * The type of record being covered in this collection, if any.
   *
   * @return The type of record being covered in this collection.
   */
  @XmlQNameEnumRef (RecordType.class)
  public URI getRecordType() {
    return recordType;
  }

  /**
   * The type of record being covered in this collection, if any.
   *
   * @param recordType The type of record being covered in this collection.
   */
  public void setRecordType(URI recordType) {
    this.recordType = recordType;
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
    setRecordType(type == null ? null : URI.create(org.codehaus.enunciate.XmlQNameEnumUtil.toURIValue(type)));
  }

  /**
   * The count of the items applicable to this coverage aspect.
   *
   * @return The count of the items applicable to this coverage aspect.
   */
  public Integer getCount() {
    return count;
  }

  /**
   * The count of the items applicable to this coverage aspect.
   *
   * @param count The count of the items applicable to this coverage aspect.
   */
  public void setCount(Integer count) {
    this.count = count;
  }

  /**
   * A completeness factor for this coverage; i.e. what percentage of the total number of items in the collection is included in this coverage aspect. The
   * completeness factor is a value between 0 and 1.
   *
   * @return A completeness factor for this coverage aspect, a value between 0 and 1.
   */
  public Float getCompleteness() {
    return completeness;
  }

  /**
   * A completeness factor for this coverage; i.e. what percentage of the total number of items in the collection is included in this coverage aspect. The
   * completeness factor is a value between 0 and 1.
   *
   * @param completeness A completeness factor for this coverage aspect, a value between 0 and 1.
   */
  public void setCompleteness(Float completeness) {
    this.completeness = completeness;
  }

}
