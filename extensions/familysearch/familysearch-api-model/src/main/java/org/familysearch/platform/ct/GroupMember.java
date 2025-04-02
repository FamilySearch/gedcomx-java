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
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

import org.gedcomx.rt.json.JsonElementWrapper;

@XmlRootElement
@JsonElementWrapper( name = "groupMember" )
@XmlType( name = "GroupMember" )
@JsonInclude( JsonInclude.Include.NON_NULL )
@Schema(description = "A Member of a Group")
public class GroupMember {

  @Schema(description = "The id of the group.")
  private String groupId;

  @Schema(description = "The cisId of the group member.")
  private String cisId;

  @Schema(description = "The contact name of the group member.")
  private String contactName;

  @Schema(description = "The status of the group member.")
  private String status;


  /**
   * Get the group id.
   *
   * @return The group id.
   */
  public String getGroupId() {
    return groupId;
  }

  /**
   * Set the group id.
   *
   * @param groupId The group id.
   */
  public void setGroupId(String groupId) {
    this.groupId = groupId;
  }

  /**
   * Build out this group member with a group id.
   *
   * @param groupId The group id.
   * @return this.
   */
  public GroupMember groupId(String groupId) {
    setGroupId(groupId);
    return this;
  }

  /**
   * Get the cisId of the group member.
   *
   * @return The cisId of the group member.
   */
  public String getCisId() {
    return cisId;
  }

  /**
   * Set the cisId of the group member.
   *
   * @param cisId The cisId of the group member.
   */
  public void setCisId(String cisId) {
    this.cisId = cisId;
  }

  /**
   * Build out this group member with a cisId.
   *
   * @param cisId The cisId of the group member.
   * @return this.
   */
  public GroupMember cisId(String cisId) {
    setCisId(cisId);
    return this;
  }

  /**
   * Get the contact name of the group member.
   *
   * @return The contact name of the group member.
   */
  public String getContactName() {
    return contactName;
  }

  /**
   * Set the contact name of the group member.
   *
   * @param contactName The contact name of the group member.
   */
  public void setContactName(String contactName) {
    this.contactName = contactName;
  }

  /**
   * Build out this group member with a contact name.
   *
   * @param contactName The contact name of the group member.
   * @return this.
   */
  public GroupMember contactName(String contactName) {
    setContactName(contactName);
    return this;
  }

  /**
   * Get the status of the group member.
   *
   * @return The status of the group member.
   */
  public String getStatus() {
    return status;
  }

  /**
   * Set the status of the group member.
   *
   * @param status The status of the group member.
   */
  public void setStatus(String status) {
    this.status = status;
  }

  /**
   * Build out this group member with a status.
   *
   * @param status The status of the group member.
   * @return this.
   */
  public GroupMember status(String status) {
    setStatus(status);
    return this;
  }

}
