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
import org.gedcomx.common.*;
import org.gedcomx.links.Link;
import org.gedcomx.records.Field;
import org.gedcomx.records.HasFields;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.rt.GedcomxModelVisitor;
import org.gedcomx.rt.json.JsonElementWrapper;
import org.gedcomx.source.SourceDescription;
import org.gedcomx.source.SourceReference;
import org.gedcomx.types.ConfidenceLevel;
import org.gedcomx.types.FactType;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

/**
 * A conclusion about a fact applicable to a person or relationship.
 */
@XmlType ( name = "Fact", propOrder = {"date", "place", "value", "qualifiers", "fields"} )
@XmlRootElement
@JsonElementWrapper ( name = "facts" )
@JsonInclude ( JsonInclude.Include.NON_NULL )
@Schema(description = "A conclusion about a fact applicable to a person or relationship.")
public class Fact extends Conclusion implements HasDateAndPlace, HasFields {

  /**
   * @see org.gedcomx.types.FactType
   */
  @Schema(description = "The type of the fact.")
  private URI type;

  @Schema(description = "The date of applicability of this fact.")
  private Date date;

  @Schema(description = "The place of applicability of this fact.")
  private PlaceReference place;

  @Schema(description = "The value as supplied by the user.")
  private String value;

  @Schema(description = "The qualifiers associated with this fact.")
  private List<Qualifier> qualifiers;

  @Schema(description = "The references to the record fields being used as evidence.")
  private List<Field> fields;

  @Schema(description = "Indicator of whether this fact is the \"primary\" fact of the record from which the subject was extracted. " +
      "Applicable only to extracted persons/relationships. The meaning of this flag outside the scope of an extracted subject is undefined.")
  private Boolean primary;

  /**
   * Create a fact.
   */
  public Fact() {
  }

  /**
   * Copy a fact.
   * 
   * @param copy The copy.
   */
  public Fact(Fact copy) {
    super(copy);
    this.type = copy.type;
    this.date = copy.date == null ? null : new Date(copy.date);
    this.place = copy.place == null ? null : new PlaceReference(copy.place);
    this.value = copy.value;
    this.qualifiers = copy.qualifiers == null ? null : new ArrayList<>(copy.qualifiers.stream().map(Qualifier::new).toList());
    this.fields = copy.fields == null ? null : new ArrayList<>(copy.fields.stream().map(Field::new).toList());
    this.primary = copy.primary;
  }

  /**
   * Create a fact with the passed in type and values.
   *
   * @param factType the fact type.
   * @param value    The value as supplied by the user.
   */
  public Fact(FactType factType, String value) {
    setKnownType(factType);
    setValue(value);
  }

  /**
   * Create a date/place fact with the passed in type and values.
   *
   * @param factType the fact type.
   * @param date     The date of applicability of this fact.
   * @param place    The place of applicability of this fact.
   */
  public Fact(FactType factType, String date, String place) {
    this(factType, new Date().original(date), new PlaceReference().original(place), null);
  }

  /**
   * Create a date/place fact with the passed in type and values.
   *
   * @param factType the fact type.
   * @param date     The date of applicability of this fact.
   * @param place    The place of applicability of this fact.
   */
  public Fact(FactType factType, Date date, PlaceReference place) {
    this(factType, date, place, null);
  }

  /**
   * Create a date/place fact with the passed in type and values.
   *
   * @param factType the fact type.
   * @param date     The date of applicability of this fact.
   * @param place    The place of applicability of this fact.
   * @param value    The value as supplied by the user.
   */
  public Fact(FactType factType, Date date, PlaceReference place, String value) {
    setKnownType(factType);
    setDate(date);
    setPlace(place);
    setValue(value);
  }

  @Override
  public Fact id(String id) {
    return (Fact) super.id(id);
  }

  @Override
  public Fact extensionElement(Object element) {
    return (Fact) super.extensionElement(element);
  }

  @Override
  public Fact link(String rel, URI href) {
    return (Fact) super.link(rel, href);
  }

  @Override
  public Fact link(Link link) {
    return (Fact) super.link(link);
  }

  @Override
  public Fact lang(String lang) {
    return (Fact) super.lang(lang);
  }

  @Override
  public Fact confidence(URI confidence) {
    return (Fact) super.confidence(confidence);
  }

  @Override
  public Fact confidence(ConfidenceLevel confidence) {
    return (Fact) super.confidence(confidence);
  }

  @Override
  public Fact source(SourceReference sourceReference) {
    return (Fact) super.source(sourceReference);
  }

  @Override
  public Fact source(SourceDescription source) {
    return (Fact) super.source(source);
  }

  @Override
  public Fact note(Note note) {
    return (Fact) super.note(note);
  }

  @Override
  public Fact attribution(Attribution attribution) {
    return (Fact) super.attribution(attribution);
  }

  @Override
  public Fact analysis(ResourceReference analysis) {
    return (Fact) super.analysis(analysis);
  }

  @Override
  public Fact analysis(Document analysis) {
    return (Fact) super.analysis(analysis);
  }

  @Override
  public Fact analysis(URI analysis) {
    return (Fact) super.analysis(analysis);
  }

  @Override
  public Fact sortKey(String sortKey) {
    return (Fact) super.sortKey(sortKey);
  }

  /**
   * The type of the fact.
   *
   * @return The type of the fact.
   */
  @XmlAttribute
  @XmlQNameEnumRef ( FactType.class )
  public URI getType() {
    return type;
  }

  /**
   * The type of the fact.
   *
   * @param type The type of the fact.
   */
  public void setType(URI type) {
    this.type = type;
  }

  /**
   * Build up this fact with a type.
   *
   * @param type The type.
   * @return this
   */
  public Fact type(URI type) {
    setType(type);
    return this;
  }

  /**
   * Build up this fact with a type.
   *
   * @param type The type.
   * @return this
   */
  public Fact type(org.gedcomx.types.FactType type) {
    setKnownType(type);
    return this;
  }

  /**
   * The enum referencing the known type of the fact, or {@link org.gedcomx.types.FactType#OTHER} if not known.
   *
   * @return The enum referencing the known type of the fact, or {@link org.gedcomx.types.FactType#OTHER} if not known.
   */
  @XmlTransient
  @JsonIgnore
  public org.gedcomx.types.FactType getKnownType() {
    return getType() == null ? null : FactType.fromQNameURI(getType());
  }

  /**
   * Set the type of this fact from a known enumeration of fact types.
   *
   * @param knownType the fact type.
   */
  @JsonIgnore
  public void setKnownType(org.gedcomx.types.FactType knownType) {
    setType(knownType == null ? null : knownType.toQNameURI());
  }

  /**
   * Indicator of whether this fact is the "primary" fact of the record from which the subject was extracted. Applicable
   * only to extracted persons/relationships. The meaning of this flag outside the scope of an extracted subject is undefined.
   *
   * @return Whether this fact is the primary fact of the record from which the subject was extracted.
   */
  @XmlAttribute
  @Facet ( GedcomxConstants.FACET_GEDCOMX_RECORD )
  public Boolean getPrimary() {
    return primary;
  }

  /**
   * Indicator of whether this fact is the "primary" fact of the record from which the subject was extracted. Applicable
   * only to extracted persons/relationships. The meaning of this flag outside the scope of an extracted subject is undefined.
   *
   * @param primary Whether this fact is the primary fact of the record from which the subject was extracted.
   */
  public void setPrimary(Boolean primary) {
    this.primary = primary;
  }

  /**
   * Build up this fact with a 'primary' flag.
   *
   * @param primary The primary flag.
   * @return this.
   */
  public Fact primary(Boolean primary) {
    setPrimary(primary);
    return this;
  }

  /**
   * The date of applicability of this fact.
   *
   * @return The date of applicability of this fact.
   */
  public Date getDate() {
    return date;
  }

  /**
   * The date of applicability of this fact.
   *
   * @param date The date of applicability of this fact.
   */
  public void setDate(Date date) {
    this.date = date;
  }

  /**
   * Build up this fact with a date.
   *
   * @param date the date.
   * @return this.
   */
  public Fact date(Date date) {
    setDate(date);
    return this;
  }

  /**
   * The place of applicability of this fact.
   *
   * @return The place of applicability of this fact.
   */
  public PlaceReference getPlace() {
    return place;
  }

  /**
   * The place of applicability of this fact.
   *
   * @param place The place of applicability of this fact.
   */
  public void setPlace(PlaceReference place) {
    this.place = place;
  }

  /**
   * Build up this fact with a place.
   *
   * @param place The place.
   * @return this.
   */
  public Fact place(PlaceReference place) {
    setPlace(place);
    return this;
  }

  /**
   * The value as supplied by the user.
   *
   * @return The value as supplied by the user.
   */
  public String getValue() {
    return value;
  }

  /**
   * The value as supplied by the user.
   *
   * @param value The value as supplied by the user.
   */
  public void setValue(String value) {
    this.value = value;
  }

  /**
   * Build up this fact with a value.
   *
   * @param value The value.
   * @return this.
   */
  public Fact value(String value) {
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
   * The qualifiers associated with this fact.
   *
   * @return The qualifiers associated with this fact.
   */
  @XmlElement ( name = "qualifier" )
  @JsonProperty ( "qualifiers" )
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  public List<Qualifier> getQualifiers() {
    return qualifiers;
  }

  /**
   * Set the qualifiers associated with this fact.
   *
   * @param qualifiers qualifiers to associate with this fact.
   */
  @JsonProperty ( "qualifiers" )
  public void setQualifiers(List<Qualifier> qualifiers) {
    this.qualifiers = qualifiers;
  }

  /**
   * Build up this fact with a qualifier.
   *
   * @param qualifier The qualifier.
   * @return this.
   */
  public Fact qualifier(Qualifier qualifier) {
    addQualifier(qualifier);
    return this;
  }

  /**
   * Add a qualifier.
   *
   * @param qualifier The qualifier.
   */
  public void addQualifier(Qualifier qualifier) {
    if (this.qualifiers == null) {
      this.qualifiers = new ArrayList<Qualifier>();
    }
    this.qualifiers.add(qualifier);
  }

  /**
   * Get the fields being used as evidence.
   *
   * @return The references to the record fields being used as evidence.
   */
  @XmlElement ( name = "field" )
  @JsonProperty ( "fields" )
  @Facet ( GedcomxConstants.FACET_GEDCOMX_RECORD )
  public List<Field> getFields() {
    return fields;
  }

  /**
   * Set the list of fields being used as evidence.
   *
   * @param fields - List of fields
   */
  @JsonProperty ( "fields" )
  public void setFields(List<Field> fields) {
    this.fields = fields;
  }

  /**
   * Build up this fact with a field.
   *
   * @param field The field.
   * @return this.
   */
  public Fact field(Field field) {
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

  @Override
  public String toString() {
    return "type=" + getKnownType() + ",value=" + getValue() + ",date=" + getDate() + ",place=" + getPlace();
  }

  /**
   * Accept a visitor.
   *
   * @param visitor The visitor.
   */
  public void accept(GedcomxModelVisitor visitor) {
    visitor.visitFact(this);
  }
}
