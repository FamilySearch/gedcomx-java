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
package org.gedcomx.records;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.webcohesion.enunciate.metadata.qname.XmlQNameEnumRef;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.gedcomx.common.Attribution;
import org.gedcomx.common.Note;
import org.gedcomx.common.ResourceReference;
import org.gedcomx.common.URI;
import org.gedcomx.conclusion.Conclusion;
import org.gedcomx.conclusion.Document;
import org.gedcomx.links.Link;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.rt.GedcomxModelVisitor;
import org.gedcomx.source.SourceDescription;
import org.gedcomx.source.SourceReference;
import org.gedcomx.types.ConfidenceLevel;
import org.gedcomx.types.FieldValueStatusType;
import org.gedcomx.types.FieldValueType;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlType;
import java.util.Objects;


/**
 * An element representing a value in a record field.
 */
@XmlType ( name = "FieldValue" )
@com.webcohesion.enunciate.metadata.Facet( GedcomxConstants.FACET_GEDCOMX_RECORD )
@JsonInclude ( JsonInclude.Include.NON_NULL )
public final class FieldValue extends Conclusion {

  /**
   * @see FieldValueType
   */
  private URI type;
  private String labelId;
  private String text;
  private URI datatype;
  private URI resource;
  /**
   * @see FieldValueStatusType
   */
  private URI status;

  public FieldValue() {
  }

  public FieldValue(String text) {
    this.text = text;
  }

  public FieldValue(FieldValue copy) {
    super(copy);
    this.type = copy.type;
    this.labelId = copy.labelId;
    this.text = copy.text;
    this.datatype = copy.datatype;
    this.resource = copy.resource;
    this.status = copy.status;
  }

  @Override
  public FieldValue id(String id) {
    return (FieldValue) super.id(id);
  }

  @Override
  public FieldValue extensionElement(Object element) {
    return (FieldValue) super.extensionElement(element);
  }

  @Override
  public FieldValue link(String rel, URI href) {
    return (FieldValue) super.link(rel, href);
  }

  @Override
  public FieldValue link(Link link) {
    return (FieldValue) super.link(link);
  }

  @Override
  public FieldValue lang(String lang) {
    return (FieldValue) super.lang(lang);
  }

  @Override
  public FieldValue confidence(URI confidence) {
    return (FieldValue) super.confidence(confidence);
  }

  @Override
  public FieldValue confidence(ConfidenceLevel confidence) {
    return (FieldValue) super.confidence(confidence);
  }

  @Override
  public FieldValue source(SourceReference sourceReference) {
    return (FieldValue) super.source(sourceReference);
  }

  @Override
  public FieldValue source(SourceDescription source) {
    return (FieldValue) super.source(source);
  }

  @Override
  public FieldValue note(Note note) {
    return (FieldValue) super.note(note);
  }

  @Override
  public FieldValue attribution(Attribution attribution) {
    return (FieldValue) super.attribution(attribution);
  }

  @Override
  public FieldValue analysis(ResourceReference analysis) {
    return (FieldValue) super.analysis(analysis);
  }

  @Override
  public FieldValue analysis(Document analysis) {
    return (FieldValue) super.analysis(analysis);
  }

  @Override
  public FieldValue analysis(URI analysis) {
    return (FieldValue) super.analysis(analysis);
  }

  @Override
  public FieldValue sortKey(String sortKey) {
    return (FieldValue) super.sortKey(sortKey);
  }

  /**
   * The type of the field value.
   *
   * @return The type of the field value.
   */
  @XmlAttribute
  @XmlQNameEnumRef (FieldValueType.class)
  public URI getType() {
    return type;
  }

  /**
   * The type of the field value.
   *
   * @param type The type of the field value.
   */
  public void setType(URI type) {
    this.type = type;
  }

  /**
   * Build out this field value with a type.
   *
   * @param type The type.
   * @return this.
   */
  public FieldValue type(URI type) {
    setType(type);
    return this;
  }

  /**
   * Build out this field value with a type.
   *
   * @param type The type.
   * @return this.
   */
  public FieldValue type(FieldValueType type) {
    setKnownType(type);
    return this;
  }

  /**
   * The known type of the field value.
   *
   * @return The type of the field value.
   */
  @XmlTransient
  @JsonIgnore
  public FieldValueType getKnownType() {
    return getType() == null ? null : FieldValueType.fromQNameURI(getType());
  }

  /**
   * The type of the field value.
   *
   * @param type The type of the field value.
   */
  @JsonIgnore
  public void setKnownType(FieldValueType type) {
    setType(type == null ? null : type.toQNameURI());
  }

  /**
   * The id of the label applicable to this field value.
   *
   * @return The id of the label applicable to this field value.
   */
  @XmlAttribute
  public String getLabelId() {
    return labelId;
  }

  /**
   * The id of the label applicable to this field value.
   *
   * @param labelId The id of the label applicable to this field value.
   */
  public void setLabelId(String labelId) {
    this.labelId = labelId;
  }

  /**
   * Build out this field value with a label id.
   *
   * @param labelId The label id.
   * @return this.
   */
  public FieldValue labelId(String labelId) {
    setLabelId(labelId);
    return this;
  }

  /**
   * The text value.
   *
   * @return The text value.
   */
  public String getText() {
    return text;
  }

  /**
   * The text value.
   *
   * @param text The text value.
   */
  public void setText(String text) {
    this.text = text;
  }

  /**
   * Build out this field value with text.
   * @param text the text.
   * @return this.
   */
  public FieldValue text(String text) {
    setText(text);
    return this;
  }

  /**
   * The datatype of the text value of the field.
   *
   * @return The datatype of the text value of the field.
   */
  @XmlAttribute
  public URI getDatatype() {
    return datatype;
  }

  /**
   * The datatype of the text value of the field.
   *
   * @param datatype The datatype of the text value of the field.
   */
  public void setDatatype(URI datatype) {
    this.datatype = datatype;
  }

  /**
   * Build out this field value with a data type.
   * @param datatype The data type.
   * @return this
   */
  public FieldValue datatype(URI datatype) {
    setDatatype(datatype);
    return this;
  }

  /**
   * URI that resolves to the value of the field.
   *
   * @return URI that resolves to the value of the field.
   */
  @XmlAttribute
  public URI getResource() {
    return resource;
  }

  /**
   * URI that resolves to the value of the field.
   *
   * @param resource URI that resolves to the value of the field.
   */
  public void setResource(URI resource) {
    this.resource = resource;
  }

  /**
   * Build out this field value with a resource.
   * @param resource The resource.
   * @return this.
   */
  public FieldValue resource(URI resource) {
    setResource(resource);
    return this;
  }

  /**
   * The status of this FieldValue.
   *
   * @see org.gedcomx.types.FieldValueStatusType
   * @return URI of the field value status type of this field value.
   */
  public URI getStatus() {
    return status;
  }

  /**
   * The status of this FieldValue.
   *
   * @param status The status.
   * @see org.gedcomx.types.FieldValueStatusType
   */
  public void setStatus(URI status) {
    this.status = status;
  }

  /**
   * The known type of the field.
   *
   * @return The type of the field.
   */
  @XmlTransient
  @JsonIgnore
  public FieldValueStatusType getKnownStatus() {
    return getType() == null ? null : FieldValueStatusType.fromQNameURI(getType());
  }

  /**
   * The type of the field.
   *
   * @param status The field value status type of the field.
   */
  @JsonIgnore
  public void setKnownStatus(FieldValueStatusType status) {
    setType(status == null ? null : status.toQNameURI());
  }

  /**
   * Accept a visitor.
   *
   * @param visitor The visitor to accept.
   */
  public void accept(GedcomxModelVisitor visitor) {
    visitor.visitFieldValue(this);
  }

  /**
   * Provide a simple toString() method.
   */
  @Override
  public String toString() {
    return labelId + ": " + text;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final FieldValue that = (FieldValue) o;
    return Objects.equals(datatype, that.datatype) &&
           Objects.equals(labelId, that.labelId) &&
           Objects.equals(resource, that.resource) &&
           Objects.equals(status, that.status) &&
           Objects.equals(text, that.text) &&
           Objects.equals(type, that.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(datatype, labelId, resource, status, text, type);
  }
}
