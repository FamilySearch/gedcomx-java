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
package org.familysearch.platform.ordinances;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.webcohesion.enunciate.metadata.qname.XmlQNameEnumRef;

import org.gedcomx.common.ResourceReference;
import org.gedcomx.common.URI;
import org.gedcomx.rt.json.JsonElementWrapper;

//@XmlRootElement(name = "participant")   // not a root element or something that is added as an extension element
@JsonElementWrapper(name = "participants")
@XmlType( name = "OrdinanceParticipant" )
@JsonInclude ( JsonInclude.Include.NON_NULL )
public class OrdinanceParticipant {

  private URI roleType;
  private ResourceReference participant;
  // should there be a gender or any other info or does roleType imply that?


  /**
   * Gets the role type for this participant in the ordinance
   * @return the role type for this participant
   */
  @XmlAttribute
  @XmlQNameEnumRef(OrdinanceRoleType.class)
  public URI getRoleType() {
    return roleType;
  }

  /**
   * Sets the role type for this participant in the ordinance
   * @param roleType the role type for this participant
   */
  public void setRoleType(URI roleType) {
    this.roleType = roleType;
  }

  /**
   * The enum referencing the known ordinance role type, or {@link OrdinanceRoleType#OTHER} if not known.
   *
   * @return The enum referencing the known ordinance role type, or {@link OrdinanceRoleType#OTHER} if not known.
   */
  @XmlTransient
  @JsonIgnore @org.codehaus.jackson.annotate.JsonIgnore
  public OrdinanceRoleType getKnownRoleType() {
    return getRoleType() == null ? null : OrdinanceRoleType.fromQNameURI(getRoleType());
  }

  /**
   * Set the ordinance role type from an enumeration of known ordinance role types.
   *
   * @param knownRoleType The ordinance role type.
   */
  @JsonIgnore @org.codehaus.jackson.annotate.JsonIgnore
  public void setKnownRoleType(OrdinanceRoleType knownRoleType) {
    setRoleType(knownRoleType == null ? null : knownRoleType.toQNameURI());
  }

  public OrdinanceParticipant roleType(URI roleType) {
    setRoleType(roleType);
    return this;
  }

  public OrdinanceParticipant knownRoleType(OrdinanceRoleType knownRoleType) {
    setKnownRoleType(knownRoleType);
    return this;
  }


  /**
   * Get the participant associated with the ordinance
   *
   * @return The participant associated with the ordinance
   */
  public ResourceReference getParticipant() {
    return participant;
  }

  /**
   * Set the participant associated with the ordinance.
   *
   * @param participant The participant associated with the ordinance.
   */
  public void setParticipant(ResourceReference participant) {
    this.participant = participant;
  }

  /**
   * Build out this OrdinanceParticipant with a participant.
   *
   * @param participant The participant.
   * @return this.
   */
  public OrdinanceParticipant participant(ResourceReference participant) {
    setParticipant(participant);
    return this;
  }

}
