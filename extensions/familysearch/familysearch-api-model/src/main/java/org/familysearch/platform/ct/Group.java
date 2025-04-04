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
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

import org.gedcomx.rt.json.JsonElementWrapper;


@XmlRootElement
@JsonElementWrapper( name = "group" )
@XmlType( name = "Group" )
@JsonInclude( JsonInclude.Include.NON_NULL )
@Schema(description = "The Group")
public class Group {

  @Schema(description = "The group id.")
  private String id;

  @Schema(description = "The group name.")
  private String name;

  @Schema(description = "The group description.")
  private String description;

  @Schema(description = "The group code of conduct.")
  private String codeOfConduct;

  @Schema(description = "The ids of the trees associated with the group.")
  private Set<String> treeIds;

  @Schema(description = "The members of the group.")
  private List<GroupMember> members;


  /**
   * Get the group id.
   *
   * @return The group id.
   */
  public String getId() {
    return id;
  }

  /**
   * Set the group id.
   *
   * @param id The group id.
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * Build out this group with a group id.
   *
   * @param id The group id.
   * @return this.
   */
  public Group id(String id) {
    setId(id);
    return this;
  }

  /**
   * Get the group name.
   *
   * @return The group name.
   */
  public String getName() {
    return name;
  }

  /**
   * Set the group name.
   *
   * @param name The group name.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Build out this group with a group name.
   *
   * @param name The group name.
   * @return this.
   */
  public Group name(String name) {
    setName(name);
    return this;
  }

  /**
   * Get the group description.
   *
   * @return The group description.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Set the group description.
   *
   * @param description The group description.
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Build out this group with a group description.
   *
   * @param description The group description.
   * @return this.
   */
  public Group description(String description) {
    setDescription(description);
    return this;
  }

  /**
   * Get the group code of conduct.
   *
   * @return The group code of conduct.
   */
  public String getCodeOfConduct() {
    return codeOfConduct;
  }

  /**
   * Set the group code of conduct.
   *
   * @param codeOfConduct The group code of conduct.
   */
  public void setCodeOfConduct(String codeOfConduct) {
    this.codeOfConduct = codeOfConduct;
  }

  /**
   * Build out this group with a group code of conduct.
   *
   * @param codeOfConduct The group code of conduct.
   * @return this.
   */
  public Group codeOfConduct(String codeOfConduct) {
    setCodeOfConduct(codeOfConduct);
    return this;
  }

  /**
   * Get the ids of the trees associated with the group.
   *
   * @return The ids of the trees associated with the group.
   */
  public Set<String> getTreeIds() {
    return treeIds;
  }

  /**
   * Set the ids of the trees associated with the group.
   *
   * @param treeIds The ids of the trees associated with the group.
   */
  public void setTreeIds(Set<String> treeIds) {
    this.treeIds = treeIds;
  }

  /**
   * Build out this group with trees ids.
   *
   * @param treeIds The group's trees ids.
   * @return this.
   */
  public Group treeIds(Set<String> treeIds) {
    setTreeIds(treeIds);
    return this;
  }

  /**
   * Get the members of the group.
   *
   * @return The members of the group.
   */
  public List<GroupMember> getMembers() {
    return members;
  }

  /**
   * Set the members of the group.
   *
   * @param members The members of the group.
   */
  public void setMembers(List<GroupMember> members) {
    this.members = members;
  }

  /**
   * Build out this group with members.
   *
   * @param members The group's members.
   * @return this.
   */
  public Group members(List<GroupMember> members) {
    setMembers(members);
    return this;
  }
}
