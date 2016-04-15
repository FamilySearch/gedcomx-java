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

import org.codehaus.enunciate.json.JsonName;
import org.codehaus.enunciate.qname.XmlQNameEnumRef;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.gedcomx.common.ExtensibleData;
import org.gedcomx.common.URI;
import org.gedcomx.links.HypermediaEnabledData;
import org.gedcomx.links.Link;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.rt.GedcomxModelVisitor;
import org.gedcomx.rt.json.JsonElementWrapper;
import org.gedcomx.types.FieldType;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import java.util.LinkedList;
import java.util.List;

/**
 * A field of a record.
 */
@XmlType ( name = "Field" )
@JsonElementWrapper ( name = "fields" )
@org.codehaus.enunciate.Facet ( name = GedcomxConstants.FACET_GEDCOMX_RECORD )
public class Field extends HypermediaEnabledData {

  /**
   * @see org.gedcomx.types.FieldType
   */
  private URI type;
  private List<FieldValue> values;

  @Override
  public Field id(String id) {
    return (Field) super.id(id);
  }

  @Override
  public Field extensionElement(Object element) {
    return (Field) super.extensionElement(element);
  }

  @Override
  public Field link(Link link) {
    return (Field) super.link(link);
  }

  @Override
  public Field link(String rel, URI href) {
    return (Field) super.link(rel, href);
  }

  /**
   * The type of the gender.
   *
   * @return The type of the gender.
   */
  @XmlAttribute
  @XmlQNameEnumRef ( FieldType.class )
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
   * Build out this field with a type.
   * 
   * @param type The type.
   * @return this.
   */
  public Field type(URI type) {
    setType(type);
    return this;
  }

  /**
   * Build out this field with a type.
   * 
   * @param type The type.
   * @return this.
   */
  public Field type(FieldType type) {
    setKnownType(type);
    return this;
  }

  /**
   * The known type of the field.
   *
   * @return The type of the field.
   */
  @XmlTransient
  @JsonIgnore
  public FieldType getKnownType() {
    return getType() == null ? null : FieldType.fromQNameURI(getType());
  }

  /**
   * The type of the field.
   *
   * @param type The type of the field.
   */
  @JsonIgnore
  public void setKnownType(FieldType type) {
    setType(type == null ? null : type.toQNameURI());
  }


  /**
   * The set of values for the field.
   *
   * @return The set of values for the field.
   */
  @XmlElement (name="value")
  @JsonProperty ("values")
  @JsonName ("values")
  public List<FieldValue> getValues() {
    return values;
  }

  /**
   * The set of values for the field.
   *
   * @param values The set of values for the field.
   */
  @JsonProperty ("values")
  public void setValues(List<FieldValue> values) {
    this.values = values;
  }

  /**
   * Build out this field with a field value.
   * @param value The value.
   * @return this.
   */
  public Field value(FieldValue value) {
    addValue(value);
    return this;
  }

  /**
   * Add a reference to the record value values being used as evidence.
   *
   * @param value The value to be added.
   */
  public void addValue(FieldValue value) {
    if (value != null) {
      if (values == null) {
        values = new LinkedList<FieldValue>();
      }
      values.add(value);
    }
  }

  public void accept(GedcomxModelVisitor visitor) {
    visitor.visitField(this);
  }

  /**
   * Provide a simple toString() method.
   */
  @Override
  public String toString() {
    if ((values != null) && (values.size() > 0)) {
      return values.get(0).toString();
    }

    return "";
  }
}
