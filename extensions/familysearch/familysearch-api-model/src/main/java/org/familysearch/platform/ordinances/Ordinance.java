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

import org.codehaus.enunciate.json.JsonName;
import org.codehaus.enunciate.qname.XmlQNameEnumRef;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.gedcomx.common.URI;
import org.gedcomx.conclusion.Date;
import org.gedcomx.links.HypermediaEnabledData;
import org.gedcomx.rt.json.JsonElementWrapper;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "ordinance")
@JsonElementWrapper(name = "ordinances")
@XmlType( name = "Ordinance" )
public class Ordinance extends HypermediaEnabledData {

  private URI type;
  private Boolean livingOrdinance;
  private Date createDate;
  private Date performedDate;
  private String templeCode;
  private URI status;
  private List<OrdinanceRole> roles;

  /**
   * gets the type of ordinance
   * @return the type of ordinance
   */
  @XmlAttribute
  @XmlQNameEnumRef(OrdinanceType.class)
  public URI getType() {
    return type;
  }

  /**
   * sets the type of ordinance
   * @param type the type of ordinance
   */
  public void setType(URI type) {
    this.type = type;
  }

  /**
   * The enum referencing the known ordinance type, or {@link org.familysearch.platform.ordinances.OrdinanceType#OTHER} if not known.
   *
   * @return The enum referencing the known ordinance type, or {@link org.familysearch.platform.ordinances.OrdinanceType#OTHER} if not known.
   */
  @XmlTransient
  @JsonIgnore
  public OrdinanceType getKnownType() {
    return getType() == null ? null : OrdinanceType.fromQNameURI(getType());
  }

  /**
   * Set the ordinance type from an enumeration of known ordinance types.
   *
   * @param knownType The ordinance type.
   */
  @JsonIgnore
  public void setKnownType(OrdinanceType knownType) {
    setType(knownType == null ? null : knownType.toQNameURI());
  }

  /**
   * indicator if this ordinance was done in life for this person
   * @return true if this ordinance was done in life for this person
   */
  @XmlAttribute
  public Boolean getLivingOrdinance() {
    return livingOrdinance;
  }

  public void setLivingOrdinance(Boolean livingOrdinance) {
    this.livingOrdinance = livingOrdinance;
  }

  /**
   * gets the date this ordinance was created
   * @return the date this ordinance was created
   */
  public Date getCreateDate() {
    return createDate;
  }

  /**
   * sets the date this ordinance was created
   * @param createDate the date this ordinance was created
   */
  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  /**
   * gets the date this ordinance was performed
   * @return the date this ordinance was performed
   */
  public Date getPerformedDate() {
    return performedDate;
  }

  /**
   * sets the date this ordinance was performed
   * @param performedDate the date this ordinance was performed
   */
  public void setPerformedDate(Date performedDate) {
    this.performedDate = performedDate;
  }

  /**
   * gets the Temple Code for where this ordinance was performed
   * @return the Temple Code for where this ordinance was performed
   */
  public String getTempleCode() {
    return templeCode;
  }

  /**
   * sets the Temple Code for where this ordinance was performed
   * @param templeCode the Temple Code for where this ordinance was performed
   */
  public void setTempleCode(String templeCode) {
    this.templeCode = templeCode;
  }

  /**
   * gets the status of this ordinance
   * @return the status of this ordinance
   */
  @XmlAttribute
  @XmlQNameEnumRef(OrdinanceStatus.class)
  public URI getStatus() {
    return status;
  }

  /**
   * sets the status of this ordinance
   * @param status the status of this ordinance
   */
  public void setStatus(URI status) {
    this.status = status;
  }

  /**
   * The enum referencing the known ordinance status, or {@link org.familysearch.platform.ordinances.OrdinanceStatus#OTHER} if not known.
   *
   * @return The enum referencing the known ordinance status, or {@link org.familysearch.platform.ordinances.OrdinanceStatus#OTHER} if not known.
   */
  @XmlTransient
  @JsonIgnore
  public OrdinanceStatus getKnownStatus() {
    return getStatus() == null ? null : OrdinanceStatus.fromQNameURI(getStatus());
  }

  /**
   * Set the ordinance status from an enumeration of known ordinance statuses.
   *
   * @param knownStatus The ordinance status.
   */
  @JsonIgnore
  public void setKnownStatus(OrdinanceStatus knownStatus) {
    setStatus(knownStatus == null ? null : knownStatus.toQNameURI());
  }

  /**
   * The roles played by participants in this ordinance.
   *
   * @return The roles played by participants in this ordinance.
   */
  @XmlElement(name="role")
  @JsonProperty("roles")
  @JsonName("roles")
  public List<OrdinanceRole> getRoles() {
    return roles;
  }

  /**
   * The roles played by participants in this ordinance.
   *
   * @param roles The roles played by the participants in this ordinance.
   */
  public void setRoles(List<OrdinanceRole> roles) {
    this.roles = roles;
  }

}
