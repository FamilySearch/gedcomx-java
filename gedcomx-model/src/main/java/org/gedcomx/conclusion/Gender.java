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
import org.gedcomx.common.Attribution;
import org.gedcomx.common.Note;
import org.gedcomx.common.ResourceReference;
import org.gedcomx.common.URI;
import org.gedcomx.links.Link;
import org.gedcomx.records.Field;
import org.gedcomx.records.HasFields;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.rt.GedcomxModelVisitor;
import org.gedcomx.rt.json.JsonElementWrapper;
import org.gedcomx.source.SourceDescription;
import org.gedcomx.source.SourceReference;
import org.gedcomx.types.ConfidenceLevel;
import org.gedcomx.types.GenderType;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * A gender conclusion.
 *
 * @author Ryan Heaton
 */
@XmlType ( name = "Gender" )
@XmlRootElement
@JsonElementWrapper ( name = "genders" )
@JsonInclude ( JsonInclude.Include.NON_NULL )
@Schema(description = "A gender conclusion.")
public class Gender extends Conclusion implements HasFields {

  @Schema(description = "The type")
  private URI type;

  @Schema(description = "The references to the record fields being used as evidence.")
  private List<Field> fields;

  /**
   * Default constructor.
   */
  public Gender() {
  }

  /**
   * Constructs a new gender object with the passed in type.
   *
   * @param type The type of the gender.
   */
  public Gender(GenderType type) {
    setKnownType(type);
  }

  public Gender(Gender copy) {
    super(copy);
    this.type = copy.type;
    this.fields = copy.fields == null ? null : new ArrayList<>(copy.fields.stream().map(Field::new).toList());
  }

  @Override
  public Gender id(String id) {
    return (Gender) super.id(id);
  }

  @Override
  public Gender extensionElement(Object element) {
    return (Gender) super.extensionElement(element);
  }

  @Override
  public Gender link(String rel, URI href) {
    return (Gender) super.link(rel, href);
  }

  @Override
  public Gender link(Link link) {
    return (Gender) super.link(link);
  }

  @Override
  public Gender lang(String lang) {
    return (Gender) super.lang(lang);
  }

  @Override
  public Gender confidence(URI confidence) {
    return (Gender) super.confidence(confidence);
  }

  @Override
  public Gender confidence(ConfidenceLevel confidence) {
    return (Gender) super.confidence(confidence);
  }

  @Override
  public Gender source(SourceReference sourceReference) {
    return (Gender) super.source(sourceReference);
  }

  @Override
  public Gender source(SourceDescription source) {
    return (Gender) super.source(source);
  }

  @Override
  public Gender note(Note note) {
    return (Gender) super.note(note);
  }

  @Override
  public Gender attribution(Attribution attribution) {
    return (Gender) super.attribution(attribution);
  }

  @Override
  public Gender analysis(ResourceReference analysis) {
    return (Gender) super.analysis(analysis);
  }

  @Override
  public Gender analysis(Document analysis) {
    return (Gender) super.analysis(analysis);
  }

  @Override
  public Gender analysis(URI analysis) {
    return (Gender) super.analysis(analysis);
  }

  @Override
  public Gender sortKey(String sortKey) {
    return (Gender) super.sortKey(sortKey);
  }

  /**
   * The type of the gender.
   *
   * @return The type of the gender.
   */
  @XmlAttribute
  @XmlQNameEnumRef (GenderType.class)
  public URI getType() {
    return type;
  }

  /**
   * The type of the gender.
   *
   * @param type The type of the gender.
   */
  public void setType(URI type) {
    this.type = type;
  }

  /**
   * Build up this gender with a type.
   *
   * @param type The type.
   * @return this.
   */
  public Gender type(URI type) {
    setType(type);
    return this;
  }

  /**
   * Build up this gender with a type.
   *
   * @param type The type.
   * @return this.
   */
  public Gender type(GenderType type) {
    setKnownType(type);
    return this;
  }

  /**
   * The known type of the gender.
   *
   * @return The type of the gender.
   */
  @XmlTransient
  @JsonIgnore
  public GenderType getKnownType() {
    return getType() == null ? null : GenderType.fromQNameURI(getType());
  }

  /**
   * The type of the gender.
   *
   * @param type The type of the gender.
   */
  @JsonIgnore
  public void setKnownType(GenderType type) {
    setType(type == null ? null : type.toQNameURI());
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
   * Build up this gender with a field.
   *
   * @param field The field.
   * @return this.
   */
  public Gender field(Field field) {
    addField(field);
    return this;
  }

  /**
   * Add a record field being used as evidence.
   *
   * @param field The evidence to be added.
   */
  public void addField(Field field) {
    if (field != null) {
      if (fields == null) {
        fields = new LinkedList<Field>();
      }
      fields.add(field);
    }
  }

  @Override
  public String toString() {
    return "type=" + getKnownType();
  }

  /**
   * Accept a visitor.
   *
   * @param visitor The visitor.
   */
  public void accept(GedcomxModelVisitor visitor) {
    visitor.visitGender(this);
  }
}
