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


import org.codehaus.enunciate.json.JsonIgnore;
import org.codehaus.enunciate.qname.XmlQNameEnumRef;
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
public class MatchInfo {

  private URI collection;
  private URI status;

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
  @JsonIgnore
  @org.codehaus.jackson.annotate.JsonIgnore
  public URI getSystem() {
    return getCollection();
  }

  /**
   * The system in which this match was found.
   *
   * @param system The system in which this match was found.
   * @deprecated Use get/setCollection
   */
  @JsonIgnore
  @org.codehaus.jackson.annotate.JsonIgnore
  public void setSystem(URI system) {
    setCollection(system);
  }

  /**
   * The enum referencing the known system of this match.
   *
   * @return The enum referencing the known system of this match.
   * @deprecated Use get/setKnownCollection
   */
  @XmlTransient
  @JsonIgnore
  @org.codehaus.jackson.annotate.JsonIgnore
  public MatchSystem getKnownSystem() {
    return getKnownCollection();
  }

  /**
   * The enum referencing the known system of this match.
   *
   * @param knownSystem The enum referencing the known system of this match.
   * @deprecated Use get/setKnownCollection
   */
  @JsonIgnore
  @org.codehaus.jackson.annotate.JsonIgnore
  public void setKnownSystem(MatchSystem knownSystem) {
    setKnownCollection(knownSystem);
  }

  /**
   * The enum referencing the known collection of this match.
   *
   * @return The enum referencing the known collection of this match.
   */
  @XmlTransient
  @JsonIgnore
  @org.codehaus.jackson.annotate.JsonIgnore
  public MatchSystem getKnownCollection() {
    return getCollection() == null ? null : MatchSystem.fromQNameURI(getCollection());
  }

  /**
   * The enum referencing the known collection of this match.
   *
   * @param knownCollection The enum referencing the known collection of this match.
   */
  @JsonIgnore
  @org.codehaus.jackson.annotate.JsonIgnore
  public void setKnownCollection(MatchSystem knownCollection) {
    setCollection(knownCollection == null ? null : URI.create(org.codehaus.enunciate.XmlQNameEnumUtil.toURIValue(knownCollection)));
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
  @JsonIgnore
  @org.codehaus.jackson.annotate.JsonIgnore
  public MatchStatus getKnownStatus() {
    return getStatus() == null ? null : MatchStatus.fromQNameURI(getStatus());
  }

  /**
   * The enum referencing the known resolution of this match.
   *
   * @param knownResolution The enum referencing the known resolution of this match.
   */
  @JsonIgnore
  @org.codehaus.jackson.annotate.JsonIgnore
  public void setKnownStatus(MatchStatus knownResolution) {
    setStatus(knownResolution == null ? null : URI.create(org.codehaus.enunciate.XmlQNameEnumUtil.toURIValue(knownResolution)));
  }
}
