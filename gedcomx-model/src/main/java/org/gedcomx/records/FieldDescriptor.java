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
import org.codehaus.jackson.annotate.JsonProperty;
import org.gedcomx.common.TextValue;
import org.gedcomx.links.HypermediaEnabledData;
import org.gedcomx.rt.GedcomxConstants;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * A description of a field in a record.
 *
 * @author Ryan Heaton
 */
@XmlType ( name = "FieldDescriptor", propOrder = {"fieldId", "originalLabel", "description", "values"})
@org.codehaus.enunciate.Facet ( name = GedcomxConstants.FACET_GEDCOMX_RECORD )
public class FieldDescriptor extends HypermediaEnabledData {

  private String fieldId; // Computer-understandable (and thus non-localized) field ID, like "PR_NAME"
  private String originalLabel; // what the original form said, e.g,. "Nombre:"
  private List<TextValue> description; // localized description of this field ("Relationship of the person to the head of household").
  private List<FieldValueDescriptor> values; // localized display labels for the field values

  /**
   * A system-assigned label for the field.
   *
   * @return A system-assigned label for the field.
   */
  @XmlAttribute
  public String getFieldId() {
    return fieldId;
  }

  /**
   * A system-assigned label for the field.
   *
   * @param fieldId A system-assigned label for the field.
   */
  public void setFieldId(String fieldId) {
    this.fieldId = fieldId;
  }

  /**
   * The original label for the field, as stated on the original record.
   *
   * @return The original label for the field, as stated on the original record.
   */
  public String getOriginalLabel() {
    return originalLabel;
  }

  /**
   * The original label for the field, as stated on the original record.
   *
   * @param originalLabel The original label for the field, as stated on the original record.
   */
  public void setOriginalLabel(String originalLabel) {
    this.originalLabel = originalLabel;
  }

  /**
   * The description of the field.
   *
   * @return The description of the field.
   */
  public List<TextValue> getDescription() {
    return description;
  }

  /**
   * The description of the field.
   *
   * @param description The description of the field.
   */
  public void setDescription(List<TextValue> description) {
    this.description = description;
  }

  /**
   * Descriptors of the values that are applicable to the field.
   *
   * @return Descriptors of the values that are applicable to the field.
   */
  @XmlElement (name="value")
  @JsonProperty ("values")
  @JsonName ("values")
  public List<FieldValueDescriptor> getValues() {
    return values;
  }

  /**
   * Descriptors of the values that are applicable to the field.
   *
   * @param values Descriptors of the values that are applicable to the field.
   */
  @JsonProperty ("values")
  public void setValues(List<FieldValueDescriptor> values) {
    this.values = values;
  }

}
