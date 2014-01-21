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

  private URI system;
  private URI resolution;

  public MatchInfo() {
  }

  /**
   * The system in which this match was found.
   *
   * @return The system in which this match was found.
   */
  @XmlAttribute
  @XmlQNameEnumRef (MatchSystem.class)
  public URI getSystem() {
    return system;
  }

  /**
   * The system in which this match was found.
   *
   * @param system The system in which this match was found.
   */
  public void setSystem(URI system) {
    this.system = system;
  }

  /**
   * The enum referencing the known system of this match.
   *
   * @return The enum referencing the known system of this match.
   */
  @XmlTransient
  @JsonIgnore
  @org.codehaus.jackson.annotate.JsonIgnore
  public MatchSystem getKnownSystem() {
    return getSystem() == null ? null : MatchSystem.fromQNameURI(getSystem());
  }

  /**
   * The enum referencing the known system of this match.
   *
   * @param knownSystem The enum referencing the known system of this match.
   */
  @JsonIgnore
  @org.codehaus.jackson.annotate.JsonIgnore
  public void setKnownSystem(MatchSystem knownSystem) {
    setSystem(knownSystem == null ? null : URI.create(org.codehaus.enunciate.XmlQNameEnumUtil.toURIValue(knownSystem)));
  }

  /**
   * The way this match has been resolved.
   *
   * @return The way this match has been resolved.
   */
  @XmlAttribute
  @XmlQNameEnumRef (MatchResolution.class)
  public URI getResolution() {
    return resolution;
  }

  /**
   * The way this match has been resolved.
   *
   * @param resolution The way this match has been resolved.
   */
  public void setResolution(URI resolution) {
    this.resolution = resolution;
  }

  /**
   * The enum referencing the known resolution of this match.
   *
   * @return The enum referencing the known resolution of this match.
   */
  @XmlTransient
  @JsonIgnore
  @org.codehaus.jackson.annotate.JsonIgnore
  public MatchResolution getKnownResolution() {
    return getResolution() == null ? null : MatchResolution.fromQNameURI(getResolution());
  }

  /**
   * The enum referencing the known resolution of this match.
   *
   * @param knownResolution The enum referencing the known resolution of this match.
   */
  @JsonIgnore
  @org.codehaus.jackson.annotate.JsonIgnore
  public void setKnownResolution(MatchResolution knownResolution) {
    setResolution(knownResolution == null ? null : URI.create(org.codehaus.enunciate.XmlQNameEnumUtil.toURIValue(knownResolution)));
  }
}
