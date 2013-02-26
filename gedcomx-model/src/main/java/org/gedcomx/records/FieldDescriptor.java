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

import javax.xml.bind.annotation.XmlAttribute;

/**
 * A description of a field in a record.
 *
 * @author Ryan Heaton
 */
public class FieldDescriptor {

  private boolean displayOriginalValue; //flag indicating whether the original value should be displayed
  private String description;
  private String systemLabel;
  private String originalLabel;
  private String displayLabel;

  /**
   * Whether the original value should be used when displaying the field.
   *
   * @return Whether the original value should be used when displaying the field.
   */
  @XmlAttribute
  public boolean isDisplayOriginalValue() {
    return displayOriginalValue;
  }

  /**
   * Whether the original value should be used when displaying the field.
   *
   * @param displayOriginalValue Whether the original value should be used when displaying the field.
   */
  public void setDisplayOriginalValue(boolean displayOriginalValue) {
    this.displayOriginalValue = displayOriginalValue;
  }

  /**
   * Human-readable description of the field.
   *
   * @return Human-readable description of the field.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Human-readable description of the field.
   *
   * @param description Human-readable description of the field.
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * A system-specific identifier for the field.
   *
   * @return A system-specific identifier for the field.
   */
  public String getSystemLabel() {
    return systemLabel;
  }

  /**
   * A system-specific identifier for the field.
   *
   * @param systemLabel A system-specific identifier for the field.
   */
  public void setSystemLabel(String systemLabel) {
    this.systemLabel = systemLabel;
  }

  /**
   * The original value of the field label, as it appears on the record.
   *
   * @return The original value of the field label, as it appears on the record.
   */
  public String getOriginalLabel() {
    return originalLabel;
  }

  /**
   * The original value of the field label, as it appears on the record.
   *
   * @param originalLabel The original value of the field label, as it appears on the record.
   */
  public void setOriginalLabel(String originalLabel) {
    this.originalLabel = originalLabel;
  }

  /**
   * The way the label of the field should be displayed, taking into account e.g. the language of the consumer.
   *
   * @return The way the label of the field should be displayed, taking into account e.g. the language of the consumer.
   */
  public String getDisplayLabel() {
    return displayLabel;
  }

  /**
   * The way the label of the field should be displayed, taking into account e.g. the language of the consumer.
   *
   * @param displayLabel The way the label of the field should be displayed, taking into account e.g. the language of the consumer.
   */
  public void setDisplayLabel(String displayLabel) {
    this.displayLabel = displayLabel;
  }
}
