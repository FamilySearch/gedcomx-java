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

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.gedcomx.rt.json.JsonElementWrapper;

@XmlRootElement
@JsonElementWrapper(name = "vocabConceptAttributes")
@XmlType(name = "VocabConceptAttribute", propOrder = {"id", "name", "value" })
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "A vocabulary concept attribute.")
public class VocabConceptAttribute {

  @Schema(description = "The id of the attribute.")
  private String id;

  @Schema(description = "The name of the attribute.")
  private String name;

  @Schema(description = "The value of the attribute.")
  private String value;

  /**
   * Get the vocabulary concept attribute id.
   *
   * @return The vocabulary concept attribute id.
   */
  public String getId() {
    return id;
  }

  /**
   * Set the vocabulary concept attribute id.
   *
   *@param id The vocabulary concept attribute id.
   */
  public void setId(final String id) {
    this.id = id;
  }

  /**
   * Build out this vocabulary concept attribute by applying an id.
   *
   * @param id The id to apply to this vocabulary concept attribute.
   * @return this.
   */
  public VocabConceptAttribute id(final String id) {
    this.setId(id);
    return this;
  }

  /**
   * Get the vocabulary concept attribute name.
   *
   * @return The vocabulary concept attribute name.
   */
  public String getName() {
    return name;
  }

  /**
   * Set the vocabulary concept attribute name.
   *
   * @param name The vocabulary concept attribute name.
   */
  public void setName(final String name) {
    this.name = name;
  }

  /**
   * Build out this vocabulary concept attribute by applying a name.
   *
   * @param name The name to apply to this vocabulary concept attribute.
   * @return this.
   */
  public VocabConceptAttribute name(final String name) {
    this.setName(name);
    return this;
  }

  /**
   * Get the vocabulary concept attribute value.
   *
   * @return The vocabulary concept attribute value.
   */
  public String getValue() {
    return value;
  }

  /**
   * Set the vocabulary concept attribute value.
   *
   * @param value The vocabulary concept attribute value.
   */
  public void setValue(final String value) {
    this.value = value;
  }

  /**
   * Build out this vocabulary concept attribute by applying a value.
   *
   * @param value The value to apply to this vocabulary concept attribute.
   * @return this.
   */
  public VocabConceptAttribute value(final String value) {
    this.setValue(value);
    return this;
  }

}
