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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.webcohesion.enunciate.metadata.qname.XmlQNameEnumRef;
import org.gedcomx.common.TextValue;
import org.gedcomx.common.URI;
import org.gedcomx.links.HypermediaEnabledData;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.types.FieldValueType;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * A way a field is to be displayed to a user.
 *
 * @author Ryan Heaton
 */
@XmlType ( name = "FieldValueDescriptor" )
@com.webcohesion.enunciate.metadata.Facet( GedcomxConstants.FACET_GEDCOMX_RECORD )
@JsonInclude ( JsonInclude.Include.NON_NULL )
public class FieldValueDescriptor extends HypermediaEnabledData {

  private String labelId;
  private URI type;
  private Boolean optional;
  private List<TextValue> displayLabels;

  /**
   * The id of the label applicable to the field value.
   *
   * @return The id of the label applicable to the field value
   */
  @XmlAttribute
  public String getLabelId() {
    return labelId;
  }

  /**
   * The language of this display information. See <a href="http://www.w3.org/International/articles/language-tags/">http://www.w3.org/International/articles/language-tags/</a>
   *
   * @param labelId The language of this display information.
   */
  public void setLabelId(String labelId) {
    this.labelId = labelId;
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
  @JsonIgnore @org.codehaus.jackson.annotate.JsonIgnore
  public FieldValueType getKnownType() {
    return getType() == null ? null : FieldValueType.fromQNameURI(getType());
  }

  /**
   * The type of the field value.
   *
   * @param type The type of the field value.
   */
  @JsonIgnore @org.codehaus.jackson.annotate.JsonIgnore
  public void setKnownType(FieldValueType type) {
    setType(type == null ? null : type.toQNameURI());
  }

  /**
   * Whether the treatment of the field value is optional. Used to determine whether it should be displayed even if the value is empty.
   *
   * @return Whether the treatment of the field value is optional. Used to determine whether it should be displayed even if the value is empty.
   */
  @XmlAttribute
  public Boolean getOptional() {
    return optional;
  }

  /**
   * Whether the treatment of the field value is optional. Used to determine whether it should be displayed even if the value is empty.
   *
   * @param optional Whether the treatment of the field value is optional. Used to determine whether it should be displayed even if the value is empty.
   */
  public void setOptional(Boolean optional) {
    this.optional = optional;
  }

  /**
   * The labels to be used for display purposes.
   *
   * @return The labels to be used for display purposes.
   */
  @XmlElement (name="label")
  @JsonProperty ("labels") @org.codehaus.jackson.annotate.JsonProperty ("labels")
  public List<TextValue> getDisplayLabels() {
    return displayLabels;
  }

  /**
   * The labels to be used for display purposes.
   *
   * @param displayLabels The labels to be used for display purposes.
   */
  @JsonProperty ("labels") @org.codehaus.jackson.annotate.JsonProperty ("labels")
  public void setDisplayLabels(List<TextValue> displayLabels) {
    this.displayLabels = displayLabels;
  }
}
