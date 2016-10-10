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


import com.fasterxml.jackson.annotation.JsonInclude;
import org.gedcomx.common.ResourceReference;
import org.gedcomx.common.URI;
import org.gedcomx.rt.json.JsonElementWrapper;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Information about a match.
 */
@XmlRootElement
@JsonElementWrapper ( name = "feedbackInfo" )
@XmlType ( name = "FeedbackInfo" )
@JsonInclude ( JsonInclude.Include.NON_NULL )
public class FeedbackInfo {

  private URI resolution;
  private URI status;
  private ResourceReference place;
  private String details;

  /**
   * The resolution of the feedback.
   *
   * @return The resolution of the feedback.
   */
  @XmlAttribute
  public URI getResolution() {
    return resolution;
  }

  /**
   * The resolution of the feedback.
   *
   * @param resolution The resolution of the feedback.
   */
  public void setResolution(URI resolution) {
    this.resolution = resolution;
  }

  /**
   * The status of the feedback.
   *
   * @return The status of the feedback.
   */
  @XmlAttribute
  public URI getStatus() {
    return status;
  }

  /**
   * The status of the feedback.
   *
   * @param status The status of the feedback.
   */
  public void setStatus(URI status) {
    this.status = status;
  }

  /**
   * A reference to the place that was created based on this feedback, if any.
   *
   * @return A reference to the place that was created based on this feedback, if any.
   */
  public ResourceReference getPlace() {
    return place;
  }

  /**
   * A reference to the place that was created based on this feedback, if any.
   *
   * @param place A reference to the place that was created based on this feedback, if any.
   */
  public void setPlace(ResourceReference place) {
    this.place = place;
  }

  /**
   * Some additional details about the resolution.
   *
   * @return Some additional details about the resolution.
   */
  public String getDetails() {
    return details;
  }

  /**
   * Some additional details about the resolution.
   *
   * @param details Some additional details about the resolution.
   */
  public void setDetails(String details) {
    this.details = details;
  }
}
