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
package org.familysearch.platform.vocab;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.gedcomx.rt.json.JsonElementWrapper;

@XmlRootElement
@JsonElementWrapper(name = "conceptAttribute")
@XmlType(name = "ConceptAttribute", propOrder = {"id", "name", "value" })
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConceptAttribute {

  private String id;
  private String name;
  private String value;

  /**
   * Get the concept attribute id.
   *
   * @return The concept attribute id.
   */
  public String getId() {
    return id;
  }

  /**
   * Set the concept attribute id.
   *
   *@param id The concept attribute id.
   */
  public void setId(final String id) {
    this.id = id;
  }

  /**
   * Build out this concept attribute by applying an id.
   *
   * @param id The id to apply to this concept attribute.
   * @return this.
   */
  public ConceptAttribute id(final String id) {
    this.setId(id);
    return this;
  }

  /**
   * Get the concept attribute name.
   *
   * @return The concept attribute name.
   */
  public String getName() {
    return name;
  }

  /**
   * Set the concept attribute name.
   *
   * @param name The concept attribute name.
   */
  public void setName(final String name) {
    this.name = name;
  }

  /**
   * Build out this concept attribute by applying a name.
   *
   * @param name The name to apply to this concept attribute.
   * @return this.
   */
  public ConceptAttribute name(final String name) {
    this.setName(name);
    return this;
  }

  /**
   * Get the concept attribute value.
   *
   * @return The concept attribute value.
   */
  public String getValue() {
    return value;
  }

  /**
   * Set the concept attribute value.
   *
   * @param value The concept attribute value.
   */
  public void setValue(final String value) {
    this.value = value;
  }

  /**
   * Build out this concept attribute by applying a value.
   *
   * @param value The value to apply to this concept attribute.
   * @return this.
   */
  public ConceptAttribute value(final String value) {
    this.setValue(value);
    return this;
  }

}
