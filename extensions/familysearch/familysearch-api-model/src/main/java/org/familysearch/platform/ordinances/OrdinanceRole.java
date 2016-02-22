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

import org.codehaus.enunciate.qname.XmlQNameEnumRef;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.gedcomx.common.ResourceReference;
import org.gedcomx.common.URI;
import org.gedcomx.conclusion.Person;
import org.gedcomx.rt.RDFRange;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "OrdinanceRole", propOrder = { "person"} )
public class OrdinanceRole {

  private ResourceReference person;
  private URI type;

  /**
   * Reference to the person playing the role in the ordinance.
   *
   * @return Reference to the person playing the role in the ordinance.
   */
  @RDFRange(Person.class)
  public ResourceReference getPerson() {
    return person;
  }

  /**
   * Reference to the person playing the role in the ordinance.
   *
   * @param person Reference to the person playing the role in the ordinance.
   */
  public void setPerson(ResourceReference person) {
    this.person = person;
  }

  /**
   * gets the type of role the person played in the ordinance
   * @return the type of role the person played in the ordinance
   */
  @XmlAttribute
  @XmlQNameEnumRef(OrdinanceRoleType.class)
  public URI getType() {
    return type;
  }

  public void setType(URI type) {
    this.type = type;
  }

  /**
   * The enum referencing the known type of role, or {@link OrdinanceRoleType#OTHER} if not known.
   *
   * @return The enum referencing the known type of role, or {@link OrdinanceRoleType#OTHER} if not known.
   */
  @XmlTransient
  @JsonIgnore
  public OrdinanceRoleType getKnownType() {
    return getType() == null ? null : OrdinanceRoleType.fromQNameURI(getType());
  }

  /**
   * Set the type of role from a known enumeration of type types.
   *
   * @param knownType the type of role in the ordinance
   */
  @JsonIgnore
  public void setKnownType(OrdinanceRoleType knownType) {
    setType(knownType == null ? null : knownType.toQNameURI());
  }

}
