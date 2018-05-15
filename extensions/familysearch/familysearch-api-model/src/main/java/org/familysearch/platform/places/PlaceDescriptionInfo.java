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
package org.familysearch.platform.places;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.gedcomx.rt.json.JsonElementWrapper;

@XmlRootElement
@XmlType ( name = "PlaceDescriptionInfo", propOrder = { "zoomLevel", "relatedType", "relatedSubType" } )
@JsonElementWrapper( name = "placeDescriptionInfo" )
@JsonInclude( JsonInclude.Include.NON_NULL )
public class PlaceDescriptionInfo {

  private Integer zoomLevel;
  private String relatedType;
  private String relatedSubType;

  /**
   * Get the zoom level for this place description.
   *
   * @return The zoom level for this place description.
   */
  public Integer getZoomLevel() {
    return this.zoomLevel;
  }

  /**
   * Set zoom level for this place description.
   *
   * @param zoomLevel The zoom level for this place description.
   */
  public void setZoomLevel(final Integer zoomLevel) {
    this.zoomLevel = zoomLevel;
  }

  /**
   * Build out this place with a zoom level.
   * descriptions."
   *
   * @param zoomLevel The zoom level for this place description.
   * @return this.
   */
  public PlaceDescriptionInfo zoomLevel(final Integer zoomLevel) {
    this.zoomLevel = zoomLevel;
    return this;
  }

  /**
   * Get the type of this place description. This attribute is only relevant "related place descriptions."
   *
   * @return The type of this related place description.
   */
  public String getRelatedType() {
    return this.relatedType;
  }

  /**
   * Set the type of this related place description. This attribute is only relevant "related place descriptions."
   *
   * @param relatedType The type of this related place description.
   */
  public void setRelatedType(final String relatedType) {
    this.relatedType = relatedType;
  }

  /**
   * Build out this place with a related place description type. This attribute is only relevant "related place
   * descriptions."
   *
   * @param type The type of this related place description.
   * @return this.
   */
  public PlaceDescriptionInfo type(final String type) {
    this.relatedType = type;
    return this;
  }

  /**
   * Get the sub-type of this related place description. This attribute is only relevant "related place descriptions."
   *
   * @return The sub-type of this related place description.
   */
  public String getRelatedSubType() {
    return this.relatedSubType;
  }

  /**
   * Set the sub-type of this related place description. This attribute is only relevant "related place descriptions."
   *
   * @param relatedSubType The sub-type of this related place description.
   */
  public void setRelatedSubType(final String relatedSubType) {
    this.relatedSubType = relatedSubType;
  }

  /**
   * Build out this place with a related place description sub-type. This attribute is only relevant "related place descriptions."
   *
   * @param subType The sub-type of this related place description.
   * @return this.
   */
  public PlaceDescriptionInfo subType(final String subType) {
    this.relatedSubType = subType;
    return this;
  }

}
