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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.webcohesion.enunciate.metadata.Facet;
import com.webcohesion.enunciate.metadata.qname.XmlQNameEnumRef;
import org.gedcomx.common.ExtensibleData;
import org.gedcomx.common.Qualifier;
import org.gedcomx.common.URI;
import org.gedcomx.records.Field;
import org.gedcomx.records.HasFields;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.rt.GedcomxModelVisitor;
import org.gedcomx.types.NamePartType;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlType;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;


/**
 * A part of a name.
 *
 * @author Ryan Heaton
 */
@XmlType ( name = "NamePart" )
@JsonInclude ( JsonInclude.Include.NON_NULL )
public final class NamePart extends ExtensibleData implements HasFields {

  /**
   * @see org.gedcomx.types.NamePartType
   */
  private URI type;
  private String value;
  private List<Qualifier> qualifiers;
  private List<Field> fields;

  public NamePart() {
  }

  public NamePart(NamePartType type, String text) {
    setKnownType(type);
    setValue(text);
  }

  @Override
  public NamePart id(String id) {
    return (NamePart) super.id(id);
  }

  @Override
  public NamePart extensionElement(Object element) {
    return (NamePart) super.extensionElement(element);
  }

  /**
   * The type of the name part.
   *
   * @return The type of the name part.
   */
  @XmlAttribute
  @XmlQNameEnumRef (NamePartType.class)
  public URI getType() {
    return type;
  }

  /**
   * The type of the name part.
   *
   * @param type The type of the name part.
   */
  public void setType(URI type) {
    this.type = type;
  }

  /**
   * Build out this name part with a type.
   *
   * @param type The type.
   * @return this.
   */
  public NamePart type(URI type) {
    setType(type);
    return this;
  }

  /**
   * Build out this name part with a type.
   *
   * @param type The type.
   * @return this.
   */
  public NamePart type(NamePartType type) {
    setKnownType(type);
    return this;
  }

  /**
   * The enum referencing the known name part type, or {@link org.gedcomx.types.NamePartType#OTHER} if not known.
   *
   * @return The enum referencing the known name part type, or {@link org.gedcomx.types.NamePartType#OTHER} if not known.
   */
  @XmlTransient
  @JsonIgnore
  public NamePartType getKnownType() {
    return getType() == null ? null : NamePartType.fromQNameURI(getType());
  }

  /**
   * Set the type of this name part from an enumeration of known name part types.
   *
   * @param knownType The name part type.
   */
  @JsonIgnore
  public void setKnownType(NamePartType knownType) {
    setType(knownType == null ? null : knownType.toQNameURI());
  }

  /**
   * The value of the name part.
   *
   * @return The value of the name part.
   */
  @XmlAttribute
  public String getValue() {
    return value;
  }

  /**
   * The value of the name part.
   *
   * @param value The value of the name part.
   */
  public void setValue(String value) {
    this.value = value;
  }

  /**
   * Build out this name part with a value.
   *
   * @param value The value.
   * @return this.
   */
  public NamePart value(String value) {
    setValue(value);
    return this;
  }

  /**
   * Create a stream for the qualifiers.
   *
   * @return a stream for the qualifiers.
   */
  public Stream<Qualifier> qualifiers() {
    return this.qualifiers == null ? Stream.empty() : this.qualifiers.stream();
  }

  /**
   * The qualifiers associated with this name part.
   *
   * @return The qualifiers associated with this name part.
   */
  @XmlElement (name = "qualifier")
  @JsonProperty ("qualifiers")
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  public List<Qualifier> getQualifiers() {
    return qualifiers;
  }

  /**
   * Set the qualifiers associated with this name part.
   * @param qualifiers qualifiers to associate with this name part
   */
  @JsonProperty ("qualifiers")
  public void setQualifiers(List<Qualifier> qualifiers) {
    this.qualifiers = qualifiers;
  }

  /**
   * Build out this name part with a qualifier.
   *
   * @param qualifier The qualifier.
   * @return this.
   */
  public NamePart qualifier(Qualifier qualifier) {
    addQualifier(qualifier);
    return this;
  }

  /**
   * Add a qualifier associated with this name part.
   *
   * @param qualifier The qualifier to be added.
   */
  public void addQualifier(Qualifier qualifier) {
    if (qualifier != null) {
      if (qualifiers == null) {
        qualifiers = new LinkedList<Qualifier>();
      }
      qualifiers.add(qualifier);
    }
  }

  /**
   * Get the fields being used as evidence.
   *
   * @return The references to the record fields being used as evidence.
   */
  @XmlElement( name = "field" )
  @JsonProperty( "fields" )
  @Facet ( GedcomxConstants.FACET_GEDCOMX_RECORD )
  public List<Field> getFields() {
    return fields;
  }

  /**
   * Set the list of fields being used as evidence.
   *
   * @param fields - List of fields
   */
  @JsonProperty( "fields" )
  public void setFields(List<Field> fields) {
    this.fields = fields;
  }

  /**
   * Build out this name part with a field.
   * @param field The field.
   * @return this.
   */
  public NamePart field(Field field) {
    addField(field);
    return this;
  }

  /**
   * Add a reference to the record field values being used as evidence.
   *
   * @param field The field to be added.
   */
  public void addField(Field field) {
    if (field != null) {
      if (fields == null) {
        fields = new LinkedList<Field>();
      }
      fields.add(field);
    }
  }

  /**
   * Accept a visitor.
   *
   * @param visitor The visitor.
   */
  public void accept(GedcomxModelVisitor visitor) {
    visitor.visitNamePart(this);
  }
}
