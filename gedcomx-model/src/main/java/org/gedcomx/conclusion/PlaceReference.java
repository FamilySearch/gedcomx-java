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
import org.gedcomx.common.TextValue;
import org.gedcomx.common.URI;
import org.gedcomx.records.Field;
import org.gedcomx.records.HasFields;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.rt.GedcomxModelVisitor;
import org.gedcomx.rt.RDFRange;
import org.gedcomx.types.ConfidenceLevel;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlType;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;


/**
 * A reference to genealogical place.
 */
@XmlType ( name = "PlaceReference", propOrder = { "original", "normalizedExtensions", "fields" })
@JsonInclude ( JsonInclude.Include.NON_NULL )
public class PlaceReference extends ExtensibleData implements HasFields {

  private String original;
  private URI descriptionRef;
  private URI confidence;
  private List<Field> fields;
  private List<TextValue> normalized;

  public PlaceReference() {
  }

  public PlaceReference(PlaceReference copy) {
    super(copy);
    this.original = copy.original;
    this.descriptionRef = copy.descriptionRef;
    this.confidence = copy.confidence;
    this.fields = copy.fields == null ? null : new ArrayList<>(copy.fields.stream().map(Field::new).toList());
    this.normalized = copy.normalized == null ? null : new ArrayList<>(copy.normalized.stream().map(TextValue::new).toList());
  }

  /**
   * The original value as supplied by the user.
   *
   * @return The original value as supplied by the user.
   */
  public String getOriginal() {
    return original;
  }

  /**
   * The original value as supplied by the user.
   *
   * @param original The original value as supplied by the user.
   */
  public void setOriginal(String original) {
    this.original = original;
  }

  /**
   * Build out this place reference with an original string.
   * 
   * @param original The original string.
   * @return this.
   */
  public PlaceReference original(String original) {
    setOriginal(original);
    return this;
  }

  /**
   * A reference to a description of the place being referenced.
   *
   * @return A reference to a description of the place being referenced.
   */
  @XmlAttribute ( name = "description" )
  @JsonProperty ( "description" )
  @RDFRange (PlaceDescription.class)
  public URI getDescriptionRef() {
    return descriptionRef;
  }

  /**
   * A reference to a description of the place being referenced.
   *
   * @param descriptionRef A reference to a description of the place being referenced.
   */
  @JsonProperty ( "description" )
  public void setDescriptionRef(URI descriptionRef) {
    this.descriptionRef = descriptionRef;
  }

  /**
   * Build out this place reference with a reference to a place description.
   *
   * @param description The reference to the place description.
   * @return this.
   */
  public PlaceReference description(PlaceDescription description) {
    setDescriptionRef(URI.create("#" + description.getId()));
    return this;
  }

  /**
   * Build out this place reference with a reference to a place description.
   *
   * @param ref The reference to the place description.
   * @return this.
   */
  public PlaceReference description(URI ref) {
    setDescriptionRef(ref);
    return this;
  }

  /**
   * The level of confidence the contributor has about the data.
   *
   * @return The level of confidence the contributor has about the data.
   */
  @XmlAttribute
  @XmlQNameEnumRef(ConfidenceLevel.class)
  public URI getConfidence() {
    return confidence;
  }

  /**
   * The level of confidence the contributor has about the data.
   *
   * @param confidence The level of confidence the contributor has about the data.
   */
  public void setConfidence(URI confidence) {
    this.confidence = confidence;
  }

  /**
   * Build up this conclusion with a confidence level.
   *
   * @param confidence The confidence level.
   * @return this.
   */
  public PlaceReference confidence(URI confidence) {
    setConfidence(confidence);
    return this;
  }

  /**
   * Build up this conclusion with a confidence level.
   *
   * @param confidence The confidence level.
   * @return this.
   */
  public PlaceReference confidence(ConfidenceLevel confidence) {
    setKnownConfidenceLevel(confidence);
    return this;
  }

  /**
   * The value of a the known confidence level, or {@link org.gedcomx.types.ConfidenceLevel#OTHER} if not known.
   *
   * @return The value of a the known confidence level, or {@link org.gedcomx.types.ConfidenceLevel#OTHER} if not known.
   */
  @XmlTransient
  @JsonIgnore
  public ConfidenceLevel getKnownConfidenceLevel() {
    return getConfidence() == null ? null : ConfidenceLevel.fromQNameURI(getConfidence());
  }

  /**
   * Set the confidence level from a known enumeration of confidence levels.
   *
   * @param level The known level.
   */
  @JsonIgnore
  public void setKnownConfidenceLevel(ConfidenceLevel level) {
    setConfidence(level == null ? null : level.toQNameURI());
  }

  /**
   * Create a stream for the normalized value extensions.
   *
   * @return a stream for the normalized value extensions.
   */
  public Stream<TextValue> normalizedExtensions() {
    return this.normalized == null ? Stream.empty() : this.normalized.stream();
  }

  /**
   * The list of normalized values for the place, provided for display purposes by the application. Normalized values are
   * not specified by GEDCOM X core, but as extension elements by GEDCOM X RS.
   *
   * @return The list of normalized values for the place, provided for display purposes by the application. Normalized values
   * are not specified by GEDCOM X core, but as extension elements by GEDCOM X RS.
   */
  @XmlElement ( name = "normalized" )
  @JsonProperty ("normalized")
  @Facet ( GedcomxConstants.FACET_GEDCOMX_RS )
  public List<TextValue> getNormalizedExtensions() {
    return normalized;
  }

  /**
   * The list of normalized values for the place, provided for display purposes by the application. Normalized values are
   * not specified by GEDCOM X core, but as extension elements by GEDCOM X RS.
   *
   * @param normalized The list of normalized values for the place, provided for display purposes by the application. Normalized values are
   * not specified by GEDCOM X core, but as extension elements by GEDCOM X RS.
   */
  @JsonProperty ("normalized")
  public void setNormalizedExtensions(List<TextValue> normalized) {
    this.normalized = normalized;
  }

  /**
   * Add a normalized value.
   *
   * @param normalized The normalized value.
   */
  public void addNormalizedExtension(TextValue normalized) {
    if (normalized != null) {
      if (this.normalized == null) {
        this.normalized = new LinkedList<TextValue>();
      }
      this.normalized.add(normalized);
    }
  }

  /**
   * Build out this place reference with an normalized string.
   *
   * @param normalized The normalized string.
   * @return this.
   */
  public PlaceReference normalized(String normalized) {
    addNormalizedExtension(new TextValue(normalized));
    return this;
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
   * Build out this place reference with a field.
   *
   * @param field The field.
   * @return this.
   */
  public PlaceReference field(Field field) {
    addField(field);
    return this;
  }


  public String toString() {
    return "PlaceReference{" + "original='" + original + "', " + "descriptionRef='" + descriptionRef + '\'' + '}';
  }

  /**
   * Accept a visitor.
   *
   * @param visitor The visitor.
   */
  public void accept(GedcomxModelVisitor visitor) {
    visitor.visitPlaceReference(this);
  }
}
