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

import org.codehaus.enunciate.XmlQNameEnumUtil;
import org.codehaus.jackson.annotate.JsonIgnore;

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

  public Qualifier(Enum name, String value) {
    setName(name);
    setValue(value);
  }

  public Qualifier(Enum name) {
    setName(name);
  }

  /**
   * The name of the qualifier. The name should be an element of a constrained vocabulary and is used to determine meaning of the qualifier.
   *
   * @return The name of the qualifier.
   */
  @XmlAttribute
  public URI getName() {
    return name;
  }

  /**
   * The name of the qualifier. The name should be an element of a constrained vocabulary and is used to determine meaning of the qualifier.
   *
   * @param name The name of the qualifier.
   */
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
   * Get the name as an element of a constrained vocabulary.
   *
   * @param vocabulary The enum containing the constrained vocabulary.
   * @return The vocabulary element.
   */
  public <E extends Enum> E getName(Class<E> vocabulary) {
    return this.name == null ? null : (E) XmlQNameEnumUtil.fromURI(this.name.toURI(), vocabulary);
  }

  /**
   * Set the qualifier name as an element of a constrained vocabulary.
   *
   * @param element The element.
   */
  @XmlTransient
  @JsonIgnore
  public void setName(Enum element) {
    this.name = element == null ? null : new URI(XmlQNameEnumUtil.toURI(element).toString());
  }

  /**
   * Build up this qualifier with a name.
   *
   * @param name the name.
   * @return this.
   */
  public Qualifier name(Enum name) {
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
}
