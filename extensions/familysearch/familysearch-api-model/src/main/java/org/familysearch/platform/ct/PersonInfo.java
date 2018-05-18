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
  private Boolean readOnly = false;
  private Boolean privateSpaceRestricted = false;

  public PersonInfo() {
  }

  public PersonInfo(Boolean readOnly) {
    this.readOnly = readOnly;
  }

  /**
   * Set if this person is a readOnly person.
   *
   * @return True if this person is readOnly; false otherwise.
   */
  @XmlAttribute
  public Boolean isReadOnly() {
    return readOnly;
  }

  /**
   * Get if this person is a readOnly person.
   *
   * @param readOnly True if this person is readOnly; false otherwise.
   */
  public void setReadOnly(Boolean readOnly) {
    this.readOnly = readOnly;
  }

  /**
   * Build out this person with a read only state.
   *
   * @param readOnly The read only state for this person.
   * @return this.
   */
  public PersonInfo readOnly(final Boolean readOnly) {
    this.readOnly = readOnly;
    return this;
  }

  /**
   * Get if this person is a private space restricted person.
   *
   * @return True if this person is a private space restricted person; false otherwise.
   */
  @XmlAttribute
  public Boolean isPrivateSpaceRestricted() {
    return privateSpaceRestricted;
  }

  /**
   * Set if this person is a private space restricted person.
   *
   * @param privateSpaceRestricted True if this person is a private space restricted person; false otherwise.
   */
  public void setPrivateSpaceRestricted(Boolean privateSpaceRestricted) {
    this.privateSpaceRestricted = privateSpaceRestricted;
  }

  /**
   * Build out this person with a private space restricted state.
   *
   * @param privateSpaceRestricted The private space restricted state for this person.
   * @return this.
   */
  public PersonInfo privateSpaceRestricted(final Boolean privateSpaceRestricted) {
    this.privateSpaceRestricted = privateSpaceRestricted;
    return this;
  }

}
