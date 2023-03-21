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
package org.familysearch.platform.names;


import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.webcohesion.enunciate.metadata.qname.XmlQNameEnumRef;

import org.gedcomx.common.URI;
import org.gedcomx.rt.json.JsonElementWrapper;
import org.gedcomx.types.NamePartType;

/**
 * Information about a Names search result.
 */
@XmlRootElement
@JsonElementWrapper ( name = "namesSearchInfo" )
@XmlType ( name = "NamesSearchInfo" )
@JsonInclude ( JsonInclude.Include.NON_NULL )
public class NameSearchInfo {

  private String text;
  private String nameId;          // future: if the link to the resource is required use a ResourceReference and this field could be deprecated
  private URI namePartType;
  private Integer weight;

  /**
   * The text of the search result.
   *
   * @return The text of the search result.
   */
  public String getText() {
    return text;
  }

  /**
   * The text of the search result.
   *
   * @param text The text of the search result.
   */
  public void setText(String text) {
    this.text = text;
  }

  /**
   * The name id of the search result.
   *
   * @return The name id of the search result.
   */
  public String getNameId() {
    return nameId;
  }

  /**
   * The name id of the search result.
   *
   * @param nameId The name id of the search result.
   */
  public void setNameId(String nameId) {
    this.nameId = nameId;
  }

  /**
   * The name part type for the text of the search result.
   *
   * @return TThe name part type for the text of the search result.
   */
  @XmlAttribute
  @XmlQNameEnumRef (NamePartType.class)
  public URI getNamePartType() {
    return namePartType;
  }

  /**
   * The name part type for the text of the search result.
   *
   * @param namePartType The name part type for the text of the search result.
   */
  public void setNamePartType(URI namePartType) {
    this.namePartType = namePartType;
  }

  /**
   * The enum referencing the known name part type.
   *
   * @return The enum referencing the known name part type.
   */
  @XmlTransient
  @JsonIgnore
  public NamePartType getKnownNamePartType() {
    return getNamePartType() == null ? null : NamePartType.fromQNameURI(getNamePartType());
  }

  /**
   * Set the name part type from an enumeration of known name part types.
   *
   * @param knownNamePartType The name part type.
   */
  @JsonIgnore
  public void setKnownNamePartType(NamePartType knownNamePartType) {
    setNamePartType(knownNamePartType == null ? null : knownNamePartType.toQNameURI());
  }

  /**
   * The weight of the search result.
   *
   * @return The weight of the search result.
   */
  public Integer getWeight() {
    return weight;
  }

  /**
   * The weight of the search result.
   *
   * @param weight The weight of the search result.
   */
  public void setWeight(Integer weight) {
    this.weight = weight;
  }

}
