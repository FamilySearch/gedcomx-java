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
import org.gedcomx.common.URI;
import org.gedcomx.links.HypermediaEnabledData;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.rt.GedcomxModelVisitor;
import org.gedcomx.rt.json.JsonElementWrapper;
import org.gedcomx.types.FieldType;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * A field of a record.
 */
@XmlType ( name = "Field" )
@JsonElementWrapper ( name = "fields" )
@org.codehaus.enunciate.Facet ( name = GedcomxConstants.FACET_GEDCOMX_RECORD )
public class Field extends HypermediaEnabledData {

  private URI type;
  private List<FieldValue> values;

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
    setType(type == null ? null : URI.create(org.codehaus.enunciate.XmlQNameEnumUtil.toURIValue(type)));
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

  public void accept(GedcomxModelVisitor visitor) {
    visitor.visitField(this);
  }

}
