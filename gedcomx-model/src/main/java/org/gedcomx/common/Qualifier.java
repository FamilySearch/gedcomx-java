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

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.gedcomx.rt.ControlledVocabulary;
import org.gedcomx.rt.json.JsonElementWrapper;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;

/**
 * A data qualifier. Qualifiers are used to "qualify" certain data elements to provide additional context, information, or details.
 *
 * @author Ryan Heaton
 */
@XmlRootElement
@JsonElementWrapper(name = "qualifiers")
@XmlType( name = "Qualifier" )
@JsonInclude ( JsonInclude.Include.NON_NULL )
@Schema(description = "A data qualifier. Qualifiers are used to \"qualify\" certain data elements to provide additional context, information, or details.")
public final class Qualifier {

  @Schema(description = "The name of the qualifier. The name should be an element of a constrained vocabulary and is used to determine meaning of the qualifier.")
  private URI name;

  @Schema(description = "The value of the qualifier. Some qualifiers may not have values, indicating that the qualifier is to be treated more like a \"tag\".")
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
  
  public Qualifier(Qualifier copy) {
    this.name = copy.name;
    this.value = copy.value;
  }

  /**
   * The name of the qualifier. The name should be an element of a constrained vocabulary and is used to determine meaning of the qualifier.
   *
   * @return The name of the qualifier.
   */
  @XmlAttribute
  @JsonProperty ( "name" )
  public URI getName() {
    return name;
  }

  /**
   * The name of the qualifier. The name should be an element of a constrained vocabulary and is used to determine meaning of the qualifier.
   *
   * @param name The name of the qualifier.
   */
  @JsonProperty ( "name" )
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
  @JsonIgnore
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
  public String toString() {
    return "Qualifier{" +
        "name=" + name +
        ", value='" + value + '\'' +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final Qualifier qualifier = (Qualifier) o;
    return Objects.equals(name, qualifier.name) &&
        Objects.equals(value, qualifier.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, value);
  }
}
