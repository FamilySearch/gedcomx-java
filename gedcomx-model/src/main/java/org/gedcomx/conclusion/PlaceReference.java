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

import org.codehaus.enunciate.Facet;
import org.codehaus.enunciate.json.JsonName;
import org.codehaus.jackson.annotate.JsonProperty;
import org.gedcomx.common.EvidenceReference;
import org.gedcomx.common.ExtensibleData;
import org.gedcomx.common.TextValue;
import org.gedcomx.common.URI;
import org.gedcomx.records.Field;
import org.gedcomx.records.HasFields;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.rt.GedcomxModelVisitor;
import org.gedcomx.rt.RDFRange;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.LinkedList;
import java.util.List;


/**
 * A reference to genealogical place.
 */
@XmlType ( name = "PlaceReference", propOrder = { "original", "normalizedExtensions", "fields" })
public class PlaceReference extends ExtensibleData implements HasFields {

  private String original;
  private URI descriptionRef;
  private List<Field> fields;
  private List<TextValue> normalized;

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
   * A reference to a description of the place being referenced.
   *
   * @return A reference to a description of the place being referenced.
   */
  @XmlAttribute ( name = "description" )
  @JsonName ( "description" )
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
   * The list of normalized values for the place, provided for display purposes by the application. Normalized values are
   * not specified by GEDCOM X core, but as extension elements by GEDCOM X RS.
   *
   * @return The list of normalized values for the place, provided for display purposes by the application. Normalized values
   * are not specified by GEDCOM X core, but as extension elements by GEDCOM X RS.
   */
  @XmlElement ( name = "normalized" )
  @JsonProperty ("normalized")
  @Facet ( name = GedcomxConstants.FACET_GEDCOMX_RS )
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
   * Get the fields being used as evidence.
   *
   * @return The references to the record fields being used as evidence.
   */
  @XmlElement( name = "field" )
  @JsonProperty( "fields" )
  @JsonName( "fields" )
  @Facet ( name = GedcomxConstants.FACET_GEDCOMX_RECORD )
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
