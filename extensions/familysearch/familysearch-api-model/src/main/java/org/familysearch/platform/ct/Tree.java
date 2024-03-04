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
package org.familysearch.platform.ct;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

import org.gedcomx.rt.json.JsonElementWrapper;


@XmlRootElement
@JsonElementWrapper( name = "tree" )
@XmlType( name = "Tree" )
@JsonInclude( JsonInclude.Include.NON_NULL )
public class Tree {
  private String id;
  private String name;
  private String description;
  private String startingPersonId;
  private Boolean hidden;

  /**
   * Get the tree id.
   *
   * @return The tree id.
   */
  public String getId() {
    return id;
  }

  /**
   * Set the tree id.
   *
   * @param id The tree id.
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * Build out this tree with the tree id.
   *
   * @param id The tree id.
   * @return this.
   */
  public Tree id(String id) {
    setId(id);
    return this;
  }

  /**
   * Get the tree name.
   *
   * @return The tree name.
   */
  public String getName() {
    return name;
  }

  /**
   * Set the tree name.
   *
   * @param name The tree name.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Build out this tree with the tree name.
   *
   * @param name The tree name.
   * @return this.
   */
  public Tree name(String name) {
    setName(name);
    return this;
  }

  /**
   * Get the tree description.
   *
   * @return The tree description.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Set the tree description.
   *
   * @param description The tree description.
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Build out this tree with the tree description.
   *
   * @param description The tree description.
   * @return this.
   */
  public Tree description(String description) {
    setDescription(description);
    return this;
  }

  /**
   * Get the tree starting person id.
   *
   * @return The tree starting person id.
   */
  public String getStartingPersonId() {
    return startingPersonId;
  }

  /**
   * Set the tree starting person id.
   *
   * @param startingPersonId The tree starting person id.
   */
  public void setStartingPersonId(String startingPersonId) {
    this.startingPersonId = startingPersonId;
  }

  /**
   * Build out this tree with the starting person id.
   *
   * @param startingPersonId The tree starting person id.
   * @return this.
   */
  public Tree startingPersonId(String startingPersonId) {
    setStartingPersonId(startingPersonId);
    return this;
  }

  /**
   * Get whether the tree is in the hidden state.
   *
   * @return The hidden state of the tree.
   */
  public Boolean isHidden() {
    return hidden;
  }

  /**
   * Set the hidden state of the tree.
   *
   * @param hidden The initializing state of the tree.
   */
  public void setHidden(Boolean hidden) {
    this.hidden = hidden;
  }

  /**
   * Build out this tree with the hidden state of the tree.
   *
   * @param hidden The hidden state of the tree.
   * @return this.
   */
  public Tree setTreeHidden(Boolean hidden) {
    setHidden(hidden);
    return this;
  }
}
