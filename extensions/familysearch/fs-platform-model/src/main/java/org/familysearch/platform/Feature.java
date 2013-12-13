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
package org.familysearch.platform;

import javax.xml.bind.annotation.XmlType;
import java.util.Date;

/**
 * A description of a FamilySearch feature.
 *
 * @author Ryan Heaton
 */
@XmlType ( name = "FeatureSet", propOrder = {"name", "description", "enabled", "activationDate"})
public class Feature {

  private String name;
  private String description;
  private Boolean enabled;
  private Date activationDate;

  /**
   * The name of the feature.
   *
   * @return The name of the feature.
   */
  public String getName() {
    return name;
  }

  /**
   * The name of the feature.
   *
   * @param name The name of the feature.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * A description of the feature.
   *
   * @return A description of the feature.
   */
  public String getDescription() {
    return description;
  }

  /**
   * A description of the feature.
   *
   * @param description A description of the feature.
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Whether the feature is enabled for the current request.
   *
   * @return Whether the feature is enabled for the current request.
   */
  public Boolean getEnabled() {
    return enabled;
  }

  /**
   * Whether the feature is enabled for the current request.
   *
   * @param enabled Whether the feature is enabled for the current request.
   */
  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }

  /**
   * The date that this feature is scheduled to activate permanently.
   *
   * @return The date that this feature is scheduled to activate permanently.
   */
  public Date getActivationDate() {
    return activationDate;
  }

  /**
   * The date that this feature is scheduled to activate permanently.
   *
   * @param activationDate The date that this feature is scheduled to activate permanently.
   */
  public void setActivationDate(Date activationDate) {
    this.activationDate = activationDate;
  }
}
