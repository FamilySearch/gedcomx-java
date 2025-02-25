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

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonInclude;

import org.gedcomx.rt.json.JsonElementWrapper;

/**
 * Extra information about a person.
 */
@XmlRootElement
@JsonElementWrapper( name = "personInfo" )
@XmlType( name = "PersonInfo" )
@JsonInclude( JsonInclude.Include.NON_NULL )
@Schema(description = "Extra information about a person.")
public class PersonInfo {

  @Schema(description = "True if the person is editable by the current user; false otherwise.")
  private Boolean canUserEdit = false;

  @Schema(description = "True if the person is visible to all sessions authenticated from any client; false otherwise.")
  private Boolean visibleToAll = true;

  @Schema(description = "True if the person is only visible to sessions authenticated from a FamilySearch client; false otherwise.")
  private Boolean visibleToAllWhenUsingFamilySearchApps = true;

  // The treeId attribute is prototype only and may be removed or changed at any time
  @Schema(description = "The tree id for this person. This attribute is prototype only and may be removed or changed at any time.")
  private String treeId;

  public PersonInfo() {
  }

  /**
   * Get if this person is editable by the current user.
   *
   * @return True if this person is editable by the current user; false otherwise.
   */
  @XmlAttribute
  public Boolean getCanUserEdit() {
    return canUserEdit;
  }

  /**
   * Set if this person is editable by the current user.
   *
   * @param canUserEdit True if this person is editable by the current user; false otherwise.
   */
  public void setCanUserEdit(final Boolean canUserEdit) {
    this.canUserEdit = canUserEdit;
  }

  /**
   * Build out this person with a can user edit state.
   *
   * @param canUserEdit True if this person is editable by the current user; false otherwise.
   * @return this.
   */
  public PersonInfo canUserEdit(final Boolean canUserEdit) {
    this.canUserEdit = canUserEdit;
    return this;
  }

  /**
   * Get if this person is visible to all sessions authenticated from any client.
   *
   * @return True if this person is visible to all sessions authenticated from any client; false otherwise.
   */
  @XmlAttribute
  public Boolean isVisibleToAll() {
    return visibleToAll;
  }

  /**
   * Set if this person is visible to all sessions authenticated from any client.
   *
   * @param visibleToAll True if this person is visible to all sessions authenticated from any client; false or null otherwise
   */
  public void setVisibleToAll(final Boolean visibleToAll) {
    this.visibleToAll = visibleToAll;
  }

  /**
   * Build out this person with visible to all sessions authenticated from any client state.
    *
   * @param visibleToAll True if this person is visible to all sessions authenticated from any client; false otherwise.
   * @return this.
   */
  public PersonInfo visibleToAll(final Boolean visibleToAll) {
    this.visibleToAll = visibleToAll;
    return this;
  }

  /**
   * Get if this person is only visible to sessions authenticated from a FamilySearch client.
   *
   * @return True if this person is only visible to sessions authenticated from a FamilySearch client; false otherwise.
   */
  @XmlAttribute
  public Boolean isVisibleToAllWhenUsingFamilySearchApps() {
    return visibleToAllWhenUsingFamilySearchApps;
  }

  /**
   * Set if this person is only visible to sessions authenticated from a FamilySearch client.
   *
   * @param visibleToAllWhenUsingFamilySearchApps True if this person is only visible to sessions authenticated from a FamilySearch client; false or null
   * otherwise.
   */
  public void setVisibleToAllWhenUsingFamilySearchApps(final Boolean visibleToAllWhenUsingFamilySearchApps) {
    this.visibleToAllWhenUsingFamilySearchApps = visibleToAllWhenUsingFamilySearchApps;
  }

  /**
   * Build out this person with only visible to sessions authenticated from a FamilySearch client state.
   *
   * @param visibleToAllWhenUsingFamilySearchApps True if this person is only visible to sessions authenticated from a FamilySearch client; false otherwise.
   * @return this.
   */
  public PersonInfo visibleToAllWhenUsingFamilySearchApps(final Boolean visibleToAllWhenUsingFamilySearchApps) {
    this.visibleToAllWhenUsingFamilySearchApps = visibleToAllWhenUsingFamilySearchApps;
    return this;
  }

  /**
   * Get the tree id for this person. This attribute is prototype only and may be removed or changed at any time.
   *
   * @return The tree id for this person.
   */
  @XmlAttribute
  public String getTreeId() {
    return treeId;
  }

  /**
   * Set the tree id for this person. This attribute is prototype only and may be removed or changed at any time.
   *
   * @param treeId The tree id for this person.
   */
  public void setTreeId(final String treeId) {
    this.treeId = treeId;
  }

  /**
   * Build out this person with a tree id. This attribute is prototype only and may be removed or changed at any time.
   *
   * @param treeId The tree id for this person.
   * @return this.
   */
  public PersonInfo treeId(final String treeId) {
    this.treeId = treeId;
    return this;
  }
}
