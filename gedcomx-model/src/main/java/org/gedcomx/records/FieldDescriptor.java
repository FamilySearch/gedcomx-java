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
import org.codehaus.jackson.annotate.JsonProperty;
import org.gedcomx.common.ExtensibleData;
import org.gedcomx.common.TextValue;
import org.gedcomx.common.URI;
import org.gedcomx.links.HypermediaEnabledData;
import org.gedcomx.links.Link;
import org.gedcomx.rt.GedcomxConstants;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.LinkedList;
import java.util.List;

/**
 * A description of a field in a record.
 *
 * @author Ryan Heaton
 */
@XmlType ( name = "FieldDescriptor", propOrder = { "originalLabel", "descriptions", "values"})
@org.codehaus.enunciate.Facet ( name = GedcomxConstants.FACET_GEDCOMX_RECORD )
public class FieldDescriptor extends HypermediaEnabledData {

  private String originalLabel; // what the original form said, e.g,. "Nombre:"
  private List<TextValue> descriptions; // localized description of this field ("Relationship of the person to the head of household").
  private List<FieldValueDescriptor> values; // localized display labels for the field values

  @Override
  public FieldDescriptor id(String id) {
    return (FieldDescriptor) super.id(id);
  }

  @Override
  public FieldDescriptor extensionElement(Object element) {
    return (FieldDescriptor) super.extensionElement(element);
  }

  @Override
  public FieldDescriptor link(Link link) {
    return (FieldDescriptor) super.link(link);
  }

  @Override
  public FieldDescriptor link(String rel, URI href) {
    return (FieldDescriptor) super.link(rel, href);
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
   * Build out this field descriptor with an original label.
   *
   * @param originalLabel The original label.
   * @return this.
   */
  public FieldDescriptor originalLabel(String originalLabel) {
    setOriginalLabel(originalLabel);
    return this;
  }

  /**
   * The description of the field.
   *
   * @return The description of the field.
   */
  @XmlElement (name="description")
  @JsonProperty ("descriptions")
  @JsonName ("descriptions")
  public List<TextValue> getDescriptions() {
    return descriptions;
  }

  /**
   * The description of the field.
   *
   * @param descriptions The description of the field.
   */
  public void setDescriptions(List<TextValue> descriptions) {
    this.descriptions = descriptions;
  }

  /**
   * Build out this descriptor with a description.
   * @param description The description.
   * @return this.
   */
  public FieldDescriptor description(TextValue description) {
    addDescription(description);
    return this;
  }

  /**
   * Build out this descriptor with a description.
   * @param description The description.
   * @return this.
   */
  public FieldDescriptor description(String description) {
    addDescription(new TextValue(description));
    return this;
  }

  /**
   * Add a description.
   *
   * @param description The description to be added.
   */
  public void addDescription(TextValue description) {
    if (description != null) {
      if (this.descriptions == null) {
        this.descriptions = new LinkedList<TextValue>();
      }
      this.descriptions.add(description);
    }
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
