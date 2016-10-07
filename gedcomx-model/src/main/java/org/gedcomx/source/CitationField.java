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
package org.gedcomx.source;

import org.codehaus.enunciate.Facet;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.gedcomx.common.URI;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.rt.json.HasJsonKey;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * Represents a citation field -- its name and value.
 */
@XmlType ( name = "CitationField" )
@Facet ( name = GedcomxConstants.FACET_GEDCOMX_CITATION )
public class CitationField implements HasJsonKey {

  private URI name;
  private String value;

  public CitationField() {
  }

  public CitationField(URI name, String value) {
    setName(name);
    setValue(value);
  }

  public CitationField(String name, String value) {
    setNameValue(name);
    setValue(value);
  }

  @XmlTransient
  @JsonIgnore
  @org.codehaus.enunciate.json.JsonIgnore
  @Override
  public boolean isHasUniqueKey() {
    return true;
  }

  /**
   * The citation field's name.
   *
   * @return The citation field's name.
   */
  @XmlAttribute
  public URI getName() {
    return name;
  }

  /**
   * The citation field's name.
   *
   * @param name The citation field's name.
   */
  public void setName(URI name) {
    this.name = name;
  }

  /**
   * Build out this citation field with a name.
   *
   * @param name the name.
   * @return this.
   */
  public CitationField name(URI name) {
    setName(name);
    return this;
  }

  /**
   * The citation field's name.
   *
   * @param name The citation field's name.
   */
  @XmlTransient
  @JsonIgnore
  public void setNameValue(String name) {
    this.name = name != null ? new URI(name) : null;
  }

  /**
   * Build out this citation field with a name.
   * @param name The name.
   * @return this.
   */
  public CitationField nameValue(String name) {
    setNameValue(name);
    return this;
  }

  /**
   * The json key that is used define this link in a map.
   *
   * @return The json key that is used define this link in a map.
   */
  @XmlTransient
  @JsonIgnore
  @Override
  public String getJsonKey() {
    return getName().toString();
  }

  /**
   * The json key that is used define this link in a map.
   *
   * @param jsonKey The json key that is used define this link in a map.
   */
  @JsonIgnore
  @Override
  public void setJsonKey(String jsonKey) {
    setNameValue(jsonKey);
  }

  /**
   * The citation field's value.
   *
   * @return The citation field's value.
   */
  @XmlValue
  public String getValue() {
    return value;
  }

  /**
   * The citation field's value.
   *
   * @param value The citation field's value.
   */
  public void setValue(String value) {
    this.value = value;
  }

  /**
   * Build out this citation field with a value.
   * @param value The value.
   * @return this.
   */
  public CitationField value(String value) {
    setValue(value);
    return this;
  }
}
