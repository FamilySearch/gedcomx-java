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
package org.gedcomx.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.gedcomx.rt.ControlledVocabulary;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

/**
 * A data qualifier. Qualifiers are used to "qualify" certain data elements to provide additional context, information, or details.
 *
 * @author Ryan Heaton
 */
@XmlType( name = "Qualifier" )
@JsonInclude ( JsonInclude.Include.NON_NULL )
public final class Qualifier {

  private URI name;
  private String value;

  public Qualifier() {
  }

  public Qualifier(URI name, String value) {
    setName(name);
    setValue(value);
  }

  public Qualifier(URI name) {
    setName(name);
  }

  public Qualifier(ControlledVocabulary name, String value) {
    setName(name);
    setValue(value);
  }

  public Qualifier(ControlledVocabulary name) {
    setName(name);
  }

  /**
   * The name of the qualifier. The name should be an element of a constrained vocabulary and is used to determine meaning of the qualifier.
   *
   * @return The name of the qualifier.
   */
  @XmlAttribute
  @JsonProperty ( "name" ) @org.codehaus.jackson.annotate.JsonProperty ( "name" )
  public URI getName() {
    return name;
  }

  /**
   * The name of the qualifier. The name should be an element of a constrained vocabulary and is used to determine meaning of the qualifier.
   *
   * @param name The name of the qualifier.
   */
  @JsonProperty ( "name" ) @org.codehaus.jackson.annotate.JsonProperty ( "name" )
  public void setName(URI name) {
    this.name = name;
  }

  /**
   * Build up this qualifier with a name.
   *
   * @param name the name.
   * @return this.
   */
  public Qualifier name(URI name) {
    this.name = name;
    return this;
  }

  /**
   * Set the qualifier name as an element of a constrained vocabulary.
   *
   * @param element The element.
   */
  @XmlTransient
  @JsonIgnore @org.codehaus.jackson.annotate.JsonIgnore
  public void setName(ControlledVocabulary element) {
    this.name = element == null ? null : element.toQNameURI();
  }

  /**
   * Build up this qualifier with a name.
   *
   * @param name the name.
   * @return this.
   */
  public Qualifier name(ControlledVocabulary name) {
    setName(name);
    return this;
  }

  /**
   * The value of the qualifier. Some qualifiers may not have values, indicating that the qualifier is to be treated more like a "tag".
   *
   * @return The value of the qualifier.
   */
  @XmlValue
  public String getValue() {
    return value;
  }

  /**
   * The value of the qualifier. Some qualifiers may not have values, indicating that the qualifier is to be treated more like a "tag".
   *
   * @param value The value of the qualifier.
   */
  public void setValue(String value) {
    this.value = value;
  }

  /**
   * Build up this qualifier with a value.
   *
   * @param value The value of the qualifier.
   * @return this.
   */
  public Qualifier value(String value) {
    setValue(value);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Qualifier qualifier = (Qualifier) o;

    if (!name.equals(qualifier.name)) {
      return false;
    }
    return value.equals(qualifier.value);
  }

  @Override
  public int hashCode() {
    int result = name.hashCode();
    result = 31 * result + value.hashCode();
    return result;
  }
}
