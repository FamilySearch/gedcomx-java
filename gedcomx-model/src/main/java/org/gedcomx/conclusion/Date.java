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
import com.webcohesion.enunciate.metadata.ClientName;
import com.webcohesion.enunciate.metadata.Facet;
import org.gedcomx.common.ExtensibleData;
import org.gedcomx.common.Qualifier;
import org.gedcomx.common.TextValue;
import org.gedcomx.date.GedcomxDate;
import org.gedcomx.records.Field;
import org.gedcomx.records.HasFields;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.rt.GedcomxModelVisitor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

/**
 * A concluded genealogical date.
 */
@ClientName ("DateInfo")
@XmlType ( name = "Date", propOrder = { "original", "formal", "normalizedExtensions", "fields"})
@JsonInclude ( JsonInclude.Include.NON_NULL )
public class Date extends ExtensibleData implements HasFields {

  private String original;
  private String formal;
  private List<TextValue> normalized;
  private List<Field> fields;

  @Override
  public Date id(String id) {
    return (Date) super.id(id);
  }

  @Override
  public Date extensionElement(Object element) {
    return (Date) super.extensionElement(element);
  }

  /**
   * The original text as supplied by the user.
   *
   * @return The original text as supplied by the user.
   */
  public String getOriginal() {
    return original;
  }

  /**
   * The original text as supplied by the user.
   *
   * @param original The original text as supplied by the user.
   */
  public void setOriginal(String original) {
    this.original = original;
  }

  /**
   * Build up this date with original text as supplied by the user.
   *
   * @param original the original text.
   * @return this.
   */
  public Date original(String original) {
    setOriginal(original);
    return this;
  }

  /**
   * The standardized and/or normalized formal value.
   *
   * @return The formal value.
   */
  public String getFormal() {
    return formal;
  }

  /**
   * The standardized and/or normalized formal value.
   *
   * @param formal The formal value.
   */
  public void setFormal(String formal) {
    this.formal = formal;
  }

  /**
   * The standardized and/or normalized formal value.
   *
   * @param formal The formal value.
   */
  @XmlTransient
  @JsonIgnore
  public void setFormalDate(GedcomxDate formal) {
    if (formal != null) {
      setFormal(formal.toFormalString());
    }
  }

  /**
   * Build up this date with a formal representation of the date.
   *
   * @param formal The formal date.
   * @return this.
   */
  public Date formal(String formal) {
    setFormal(formal);
    return this;
  }

  /**
   * Build up this date with a formal representation of the date.
   *
   * @param formal The formal date.
   * @return this.
   */
  public Date formal(GedcomxDate formal) {
    return formal(formal.toFormalString());
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
   * The list of normalized values for the date, provided for display purposes by the application. Normalized values are
   * not specified by GEDCOM X core, but as extension elements by GEDCOM X RS.
   *
   * @return The list of normalized values for the date, provided for display purposes by the application. Normalized values
   * are not specified by GEDCOM X core, but as extension elements by GEDCOM X RS.
   */
  @XmlElement ( name = "normalized" )
  @JsonProperty ("normalized")
  @Facet ( GedcomxConstants.FACET_GEDCOMX_RS )
  public List<TextValue> getNormalizedExtensions() {
    return normalized;
  }

  /**
   * The list of normalized values for the date, provided for display purposes by the application. Normalized values are
   * not specified by GEDCOM X core, but as extension elements by GEDCOM X RS.
   *
   * @param normalized The list of normalized values for the date, provided for display purposes by the application. Normalized values are
   * not specified by GEDCOM X core, but as extension elements by GEDCOM X RS.
   */
  @JsonProperty ("normalized")
  public void setNormalizedExtensions(List<TextValue> normalized) {
    this.normalized = normalized;
  }

  /**
   * Add a normalized extension to the list.
   *
   * @param normalizedExtension The normalizedExtension to be added.
   */
  public void addNormalizedExtension(TextValue normalizedExtension) {
    if (normalizedExtension != null) {
      if (normalized == null) {
        normalized = new LinkedList<TextValue>();
      }
      normalized.add(normalizedExtension);
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
   * Build up this date with a field.
   *
   * @param field The field.
   * @return this.
   */
  public Date field(Field field) {
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
    return "Date{" + "original='" + original + '\'' + ", formal=" + formal + '}';
  }

  /**
   * Accept a visitor.
   *
   * @param visitor The visitor.
   */
  public void accept(GedcomxModelVisitor visitor) {
    visitor.visitDate(this);
  }
}
