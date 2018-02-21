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


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.webcohesion.enunciate.metadata.qname.XmlQNameEnumRef;
import org.gedcomx.common.URI;
import org.gedcomx.rt.json.JsonElementWrapper;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 * Information about a match.
 */
@XmlRootElement
@JsonElementWrapper ( name = "matchInfo" )
@XmlType ( name = "MatchInfo" )
@JsonInclude ( JsonInclude.Include.NON_NULL )
public class MatchInfo {

  private URI collection;
  private URI status;
  private Boolean addsPerson;
  private Boolean addsFact;
  private Boolean addsDateOrPlace;

  public MatchInfo() {
  }

  /**
   * The collection in which this match was found.
   *
   * @return The collection in which this match was found.
   */
  @XmlAttribute
  public URI getCollection() {
    return collection;
  }

  /**
   * The collection in which this match was found.
   *
   * @param collection The collection in which this match was found.
   */
  public void setCollection(URI collection) {
    this.collection = collection;
  }

  /**
   * The system in which this match was found.
   *
   * @return The system in which this match was found.
   * @deprecated Use get/setCollection
   */
  @XmlTransient
  @JsonIgnore @org.codehaus.jackson.annotate.JsonIgnore
  public URI getSystem() {
    return getCollection();
  }

  /**
   * The system in which this match was found.
   *
   * @param system The system in which this match was found.
   * @deprecated Use get/setCollection
   */
  @JsonIgnore @org.codehaus.jackson.annotate.JsonIgnore
  public void setSystem(URI system) {
    setCollection(system);
  }

  /**
   * tells if the match would add a person to the target system
   * @return true if the match would add a person to the target system
   */
  @XmlAttribute
  public Boolean getAddsPerson() {
    return addsPerson;
  }

  /**
   * sets whether the match would add a person to the target system
   * @param addsPerson whether or not the match would add a person to the target system
   */
  public void setAddsPerson(Boolean addsPerson) {
    this.addsPerson = addsPerson;
  }

  /**
   * tells if the match would add a vital fact to the target system
   * @return true if the match would add a vital fact to the target system
   */
  @XmlAttribute
  public Boolean getAddsFact() {
    return addsFact;
  }

  /**
   * sets whether the match would add a vital fact to the target system
   * @param addsFact whether or not the match would add a vital fact to the target system
   */
  public void setAddsFact(Boolean addsFact) {
    this.addsFact = addsFact;
  }

  /**
   * tells if the match would add a date or place to an existing vital fact
   * @return true the match would add a date or place to an existing vital fact
   */
  @XmlAttribute
  public Boolean getAddsDateOrPlace() {
    return addsDateOrPlace;
  }

  /**
   * sets whether the match would add a date or place to an existing vital fact
   * @param addsDateOrPlace whether the match would add a date or place to an existing vital fact
   */
  public void setAddsDateOrPlace(Boolean addsDateOrPlace) {
    this.addsDateOrPlace = addsDateOrPlace;
  }

  /**
   * The enum referencing the known collection of this match.
   *
   * @return The enum referencing the known collection of this match.
   */
  @XmlTransient
  @JsonIgnore @org.codehaus.jackson.annotate.JsonIgnore
  public MatchCollection getKnownCollection() {
    return getCollection() == null ? null : MatchCollection.fromQNameURI(getCollection());
  }

  /**
   * The enum referencing the known collection of this match.
   *
   * @param knownCollection The enum referencing the known collection of this match.
   */
  @JsonIgnore @org.codehaus.jackson.annotate.JsonIgnore
  public void setKnownCollection(MatchCollection knownCollection) {
    setCollection(knownCollection == null ? null : knownCollection.toQNameURI());
  }

  /**
   * The way this match has been resolved.
   *
   * @return The way this match has been resolved.
   */
  @XmlAttribute
  @XmlQNameEnumRef (MatchStatus.class)
  public URI getStatus() {
    return status;
  }

  /**
   * The way this match has been resolved.
   *
   * @param status The way this match has been resolved.
   */
  public void setStatus(URI status) {
    this.status = status;
  }

  /**
   * The enum referencing the known resolution of this match.
   *
   * @return The enum referencing the known resolution of this match.
   */
  @XmlTransient
  @JsonIgnore @org.codehaus.jackson.annotate.JsonIgnore
  public MatchStatus getKnownStatus() {
    return getStatus() == null ? null : MatchStatus.fromQNameURI(getStatus());
  }

  /**
   * The enum referencing the known resolution of this match.
   *
   * @param knownResolution The enum referencing the known resolution of this match.
   */
  @JsonIgnore @org.codehaus.jackson.annotate.JsonIgnore
  public void setKnownStatus(MatchStatus knownResolution) {
    setStatus(knownResolution == null ? null : knownResolution.toQNameURI());
  }
}
