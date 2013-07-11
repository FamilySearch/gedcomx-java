/**
 * Copyright 2011-2012 Intellectual Reserve, Inc.
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

import org.codehaus.enunciate.json.JsonName;
import org.codehaus.enunciate.qname.XmlQNameEnumRef;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.gedcomx.common.EvidenceReference;
import org.gedcomx.common.URI;
import org.gedcomx.conclusion.Conclusion;
import org.gedcomx.rt.GedcomxModelVisitor;
import org.gedcomx.types.FieldValueType;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import java.util.List;


/**
 * An element representing a value in a record field.
 */
@XmlType ( name = "FieldValue" )
public final class FieldValue extends Conclusion implements HasFieldBasedEvidence {

  /**
   * @see FieldValueType
   */
  private URI type;
  private String text;
  private URI datatype;
  private URI resource;
  private List<EvidenceReference> fieldValueReferences;

  public FieldValue() {
  }

  public FieldValue(String text) {
    this.text = text;
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
    setType(type == null ? null : URI.create(org.codehaus.enunciate.XmlQNameEnumUtil.toURIValue(type)));
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
   * The references to the record field values being used as evidence.
   *
   * @return The references to the record field values being used as evidence.
   */
  @XmlElement( name = "fieldValue" )
  @JsonProperty( "fieldValues" )
  @JsonName( "fieldValues" )
  public List<EvidenceReference> getFieldValueReferences() {
    return fieldValueReferences;
  }

  /**
   * The references to the record field values being used as evidence.
   *
   * @param fieldValueReferences The references to the record field values being used as evidence.
   */
  @JsonProperty( "fieldValues" )
  public void setFieldValueReferences(List<EvidenceReference> fieldValueReferences) {
    this.fieldValueReferences = fieldValueReferences;
  }

  /**
   * Accept a visitor.
   *
   * @param visitor The visitor to accept.
   */
  public void accept(GedcomxModelVisitor visitor) {
    visitor.visitFieldValue(this);
  }
}
