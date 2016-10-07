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
package org.gedcomx.conclusion;

import org.codehaus.enunciate.qname.XmlQNameEnumRef;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import org.gedcomx.common.URI;
import org.gedcomx.rt.json.HasJsonKey;
import org.gedcomx.types.IdentifierType;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

/**
 * An identifier for a resource.
 *
 * @author Ryan Heaton
 */
@XmlType ( name = "Identifier" )
public final class Identifier implements HasJsonKey {

  private boolean hasUniqueKey = false;
  private URI value;
  /**
   * @see org.gedcomx.types.IdentifierType
   */
  private URI type;

  public Identifier() {
  }

  @JsonCreator
  public Identifier(URI value) {
    this.value = value;
  }

  public Identifier(URI value, IdentifierType knownType) {
    this.value = value;
    setKnownType( knownType );
  }

  /**
   * The id value.
   *
   * @return The id value.
   */
  @XmlValue
  @JsonValue
  public URI getValue() {
    return value;
  }

  /**
   * The id value.
   *
   * @param value The id value.
   */
  @JsonValue
  public void setValue(URI value) {
    this.value = value;
  }

  /**
   * Build up this identifier with a value.
   *
   * @param value The value.
   * @return this.
   */
  public Identifier value(URI value) {
    setValue(value);
    return this;
  }

  /**
   * The type of the id.
   *
   * @return The type of the id.
   */
  @XmlAttribute
  @JsonIgnore
  @org.codehaus.enunciate.json.JsonIgnore
  @XmlQNameEnumRef (IdentifierType.class)
  public URI getType() {
    return type;
  }

  /**
   * The type of the id.
   *
   * @param type The type of the id.
   */
  @JsonIgnore
  public void setType(URI type) {
    this.type = type;
  }

  /**
   * Build up this identifier with a type.
   * @param type The type.
   * @return this.
   */
  public Identifier type(URI type) {
    setType(type);
    return this;
  }

  /**
   * Build up this identifier with a type.
   * @param type The type.
   * @return this.
   */
  public Identifier type(IdentifierType type) {
    setKnownType(type);
    return this;
  }

  /**
   * The type of the id.
   *
   * @param type The type of the id.
   * @param unique Whether the type of this identifier implies that the value is unique among all other identifiers of the same type.
   */
  public void setType(URI type, boolean unique) {
    this.type = type;
    this.hasUniqueKey = unique;
  }

  /**
   * The enum referencing a known identifier type.
   *
   * @return The enum referencing a known identifier type, or {@link org.gedcomx.types.IdentifierType#OTHER} if not known.
   */
  @XmlTransient
  @JsonIgnore
  public IdentifierType getKnownType() {
    return getType() == null ? null : IdentifierType.fromQNameURI(getType());
  }

  /**
   * Set the value of the id type from a known identifier type.
   *
   * @param knownType The known identifier type.
   */
  @JsonIgnore
  public void setKnownType(IdentifierType knownType) {
    setType(knownType == null ? null : knownType.toQNameURI());
  }

  @XmlTransient
  @JsonIgnore
  @org.codehaus.enunciate.json.JsonIgnore
  @Override
  public boolean isHasUniqueKey() {
    return this.hasUniqueKey;
  }

  @XmlTransient
  @JsonIgnore
  @Override
  public String getJsonKey() {
    return this.type == null ? null : this.type.toString();
  }

  @JsonIgnore
  @Override
  public void setJsonKey(String jsonKey) {
    this.type = new URI(jsonKey);
  }

  /**
   * Provide a simple toString() method.
   */
  @Override
  public String toString() {
    return (value == null) ? "" : value.toString();
  }

}
