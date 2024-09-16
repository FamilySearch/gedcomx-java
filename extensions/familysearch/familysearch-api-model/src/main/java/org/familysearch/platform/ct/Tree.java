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

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.gedcomx.rt.json.JsonElementWrapper;


@XmlRootElement
@JsonElementWrapper( name = "tree" )
@XmlType( name = "Tree" )
@JsonInclude( JsonInclude.Include.NON_NULL )
public class Tree {
  private String id;
  private List<String> groupIds;
  private String name;
  private String description;
  private String startingPersonId;
  private Boolean hidden;
  // Can't use 'private' since it is a keyword in Java
  private Boolean isPrivate;
  private String collectionId;
  private ThirdPartyAccess ownerAccess;
  private ThirdPartyAccess groupAccess;


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
   * Get the ids of the groups this tree belongs to.
   *
   * @return The ids of the groups this tree belongs to.
   */
  public List<String> getGroupIds() {
    return groupIds;
  }

  /**
   * Set the ids of the groups this tree belongs to.
   *
   * @param groupIds The ids of the groups this tree belongs to.
   */
  public void setGroupIds(List<String> groupIds) {
    this.groupIds = groupIds;
  }

  /**
   * Build out this tree with the ids of the groups this tree belongs to.
   *
   * @param groupIds The ids of the groups this tree belongs to.
   * @return this.
   */
  public Tree groupIds(List<String> groupIds) {
    setGroupIds(groupIds);
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
   * Get the hidden state of the tree.
   *
   * @return The hidden state of the tree.
   */
  public Boolean getHidden() {
    return hidden;
  }

  /**
   * Set the hidden state of the tree.
   *
   * @param hidden The hidden state of the tree.
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
  public Tree hidden(Boolean hidden) {
    setHidden(hidden);
    return this;
  }

  /**
   * Get the private state of the tree.
   *
   * @return The private state of the tree.
   */
  public Boolean getPrivate(){
    return isPrivate;
  }

  /**
   * Set the private state of the tree.
   *
   * @param isPrivate The private state of the tree.
   */
  public void setPrivate(Boolean isPrivate){
    this.isPrivate = isPrivate;
  }

  /**
   * Build out this tree with the private state of the tree.
   * Note: Method name can't be private() since it is a keyword in Java
   *
   * @param isPrivate The private state of the tree.
   * @return this.
   */
  public Tree isPrivate(Boolean isPrivate) {
    setPrivate(isPrivate);
    return this;
  }

  /**
   * Get the id of the collection the tree belongs to.
   *
   * @return The id of the collection the tree belongs to.
   */
  public String getCollectionId(){
    return collectionId;
  }

  /**
   * Set the id the collection the tree belongs to.
   *
   * @param collectionId The id the collection the tree belongs to.
   */
  public void setCollectionId(String collectionId){
    this.collectionId = collectionId;
  }

  /**
   * Build out this tree with the collection the tree belongs to.
   *
   * @param collectionId The collection the tree belongs to.
   * @return this.
   */
  public Tree collectionId(String collectionId) {
    setCollectionId(collectionId);
    return this;
  }

  /**
   * Get the owner third party access state of the tree.
   *
   * @return The owner access state of the tree.
   */
  public ThirdPartyAccess getOwnerAccess() { return this.ownerAccess; }


  /**
   * Set owner third party access state of the tree. Group owner access cannot be set to None and must have the same or less restricted access than the group
   * member access.
   *
   * @param ownerAccess The owner third party access state of the tree.
   */
  public void setOwnerAccess(ThirdPartyAccess ownerAccess) { this.ownerAccess = ownerAccess;  }

  /**
   * Build out this tree with the owner third party access state of the tree.
   *
   * @param ownerAccess The owner third party access state of the tree.
   * @return this.
   */
  public Tree ownerAccess(ThirdPartyAccess ownerAccess) {
    setOwnerAccess(ownerAccess);
    return this;
  }

  /**
   * Get the group third party access state of the tree.
   *
   * @return The group access state of the tree.
   */
  public ThirdPartyAccess getGroupAccess() { return this.groupAccess; }


  /**
   * Set group third party access state of the tree.
   *
   * @param groupAccess The group third party access state of the tree.
   */
  public void setGroupAccess(ThirdPartyAccess groupAccess) { this.groupAccess = groupAccess;  }

  /**
   * Build out this tree with the group third party access state of the tree.
   *
   * @param groupAccess The group third party access state of the tree.
   * @return this.
   */
  public Tree groupAccess(ThirdPartyAccess groupAccess) {
    setGroupAccess(groupAccess);
    return this;
  }
}
