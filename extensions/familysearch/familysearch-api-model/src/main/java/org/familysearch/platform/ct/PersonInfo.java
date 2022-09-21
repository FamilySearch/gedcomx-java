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

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonInclude;

import org.gedcomx.rt.json.JsonElementWrapper;

/**
 * Extra information about a person.
 */
@XmlRootElement
@JsonElementWrapper( name = "personInfo" )
@XmlType( name = "PersonInfo" )
@JsonInclude( JsonInclude.Include.NON_NULL )
public class PersonInfo {
  private Boolean canUserEdit = false;
  private Boolean visibleToAll = true;
  @Deprecated
  private Boolean readOnly = false;
  @Deprecated
  private Boolean privateSpaceRestricted = false;

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
   * @param canUserEdit True if this person is editable by the current user; false otherwise..
   * @return this.
   */
  public PersonInfo canUserEdit(final Boolean canUserEdit) {
    this.canUserEdit = canUserEdit;
    return this;
  }

  /**
   * Set if this person is a readOnly person.
   *
   * @return True if this person is readOnly; false otherwise.
   */
  @Deprecated
  @XmlAttribute
  public Boolean isReadOnly() {
    return readOnly;
  }

  /**
   * Get if this person is a readOnly person.
   *
   * @param readOnly True if this person is readOnly; false otherwise.
   */
  @Deprecated
  public void setReadOnly(Boolean readOnly) {
    this.readOnly = readOnly;
  }

  /**
   * Build out this person with a read only state.
   *
   * @param readOnly The read only state for this person.
   * @return this.
   */
  @Deprecated
  public PersonInfo readOnly(final Boolean readOnly) {
    this.readOnly = readOnly;
    return this;
  }

  /**
   * Get if this person is visible to all FamilySearch users.
   *
   * @return True if this person is visible to all FamilySearch users; false otherwise.
   */
  @XmlAttribute
  public Boolean isVisibleToAll() {
    return visibleToAll;
  }

  /**
   * Set if this person is visible to all FamilySearch users.
   *
   * @param visibleToAll True if this person is visible to all FamilySearch users; false otherwise.
   */
  public void setVisibleToAll(final Boolean visibleToAll) {
    this.visibleToAll = visibleToAll;
  }

  /**
   * Build out this person with a visible to all FamilySearch users state.
   *
   * @param visibleToAll The private space restricted state for this person.
   * @return this.
   */
  public PersonInfo visibleToAll(final Boolean visibleToAll) {
    this.visibleToAll = visibleToAll;
    return this;
  }

  /**
   * Get if this person is a private space restricted person.
   *
   * @return True if this person is a private space restricted person; false otherwise.
   */
  @Deprecated
  @XmlAttribute
  public Boolean isPrivateSpaceRestricted() {
    return privateSpaceRestricted;
  }

  /**
   * Set if this person is a private space restricted person.
   *
   * @param privateSpaceRestricted True if this person is a private space restricted person; false otherwise.
   */
  @Deprecated
  public void setPrivateSpaceRestricted(Boolean privateSpaceRestricted) {
    this.privateSpaceRestricted = privateSpaceRestricted;
  }

  /**
   * Build out this person with a private space restricted state.
   *
   * @param privateSpaceRestricted The private space restricted state for this person.
   * @return this.
   */
  @Deprecated
  public PersonInfo privateSpaceRestricted(final Boolean privateSpaceRestricted) {
    this.privateSpaceRestricted = privateSpaceRestricted;
    return this;
  }
}
