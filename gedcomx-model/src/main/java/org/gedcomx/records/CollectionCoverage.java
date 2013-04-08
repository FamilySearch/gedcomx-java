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

import org.gedcomx.common.URI;
import org.gedcomx.conclusion.Date;
import org.gedcomx.conclusion.PlaceReference;
import org.gedcomx.links.HypermediaEnabledData;
import org.gedcomx.rt.json.JsonElementWrapper;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * A collection of genealogical data.
 *
 * @author Ryan Heaton
 */
@XmlRootElement
@JsonElementWrapper ( name = "collectionCoverage" )
@XmlType ( name = "CollectionCoverage" )
public class CollectionCoverage extends HypermediaEnabledData {

  private PlaceReference spatial;
  private Date temporal;
  private List<URI> resourceType;

  /**
   * Spatial coverage. The geographic area(s) covered by the collection.
   *
   * @return Spatial coverage.
   */
  public PlaceReference getSpatial() {
    return spatial;
  }

  /**
   * Spatial coverage. The geographic area(s) covered by the collection.
   *
   * @param spatial Spatial coverage.
   */
  public void setSpatial(PlaceReference spatial) {
    this.spatial = spatial;
  }

  /**
   * Temporal coverage. The time period(s) covered by the collection.
   *
   * @return Temporal coverage.
   */
  public Date getTemporal() {
    return temporal;
  }

  /**
   * Temporal coverage. The time period(s) covered by the collection.
   *
   * @param temporal Temporal coverage.
   */
  public void setTemporal(Date temporal) {
    this.temporal = temporal;
  }

  /**
   * Resource type coverage. The types of resources enclosed by the collection.
   *
   * @return Resource type coverage.
   */
  public List<URI> getResourceType() {
    return resourceType;
  }

  /**
   * Resource type coverage. The types of resources enclosed by the collection.
   *
   * @param resourceType Resource type coverage.
   */
  public void setResourceType(List<URI> resourceType) {
    this.resourceType = resourceType;
  }
}
