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
  private String displaySortKey; // a sort key for sorting the field values by how they should be displayed for viewing
  private String entrySortKey; // a sort key for sorting the field values by how they should be entered for editing
  private Boolean entryRequired; // whether some kind of entry is required when entering data for editing.
  private Boolean editable; //whether the field value is editable (as opposed to composed from other values by the system).
  private String parentLabelId; //the label id of the "parent" field value. E.g. The parent of a "given name" field value might be the "name" field value.

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

  /**
   * A sort key for sorting this field value relative to other field values according to how they should be displayed for viewing.
   *
   * @return A sort key for sorting this field value relative to other field values according to how they should be displayed for viewing.
   */
  public String getDisplaySortKey() {
    return displaySortKey;
  }

  /**
   * A sort key for sorting this field value relative to other field values according to how they should be displayed for viewing.
   *
   * @param displaySortKey A sort key for sorting this field value relative to other field values according to how they should be displayed for viewing.
   */
  public void setDisplaySortKey(String displaySortKey) {
    this.displaySortKey = displaySortKey;
  }

  /**
   * A sort key for sorting this field value relative to other field values according to how they should be entered for editing.
   *
   * @return A sort key for sorting this field value relative to other field values according to how they should be entered for editing.
   */
  public String getEntrySortKey() {
    return entrySortKey;
  }

  /**
   * A sort key for sorting this field value relative to other field values according to how they should be entered for editing.
   *
   * @param entrySortKey A sort key for sorting this field value relative to other field values according to how they should be entered for editing.
   */
  public void setEntrySortKey(String entrySortKey) {
    this.entrySortKey = entrySortKey;
  }

  /**
   * Whether some kind of entry is required when entering data for editing.
   *
   * @return Whether some kind of entry is required when entering data for editing.
   */
  public Boolean getEntryRequired() {
    return entryRequired;
  }

  /**
   * Whether some kind of entry is required when entering data for editing.
   *
   * @param entryRequired Whether some kind of entry is required when entering data for editing.
   */
  public void setEntryRequired(Boolean entryRequired) {
    this.entryRequired = entryRequired;
  }

  /**
   * Whether the field value is editable. Some field values might be composed from other field values or otherwise calculated by the system.
   *
   * @return Whether the field value is editable. Some field values might be composed from other field values or otherwise calculated by the system.
   */
  @XmlAttribute
  public Boolean getEditable() {
    return editable;
  }

  /**
   * Whether the field value is editable. Some field values might be composed from other field values or otherwise calculated by the system.
   *
   * @param editable Whether the field value is editable. Some field values might be composed from other field values or otherwise calculated by the system.
   */
  public void setEditable(Boolean editable) {
    this.editable = editable;
  }

  /**
   * The id of the label for the "parent" field value. For example, the parent field value of a "given name" field value might be the "name" field value.
   *
   * @return The id of the label for the "parent" field value. For example, the parent field value of a "given name" field value might be the "name" field value.
   */
  public String getParentLabelId() {
    return parentLabelId;
  }

  /**
   * The id of the label for the "parent" field value. For example, the parent field value of a "given name" field value might be the "name" field value.
   *
   * @param parentLabelId The id of the label for the "parent" field value. For example, the parent field value of a "given name" field value might be the "name" field value.
   */
  public void setParentLabelId(String parentLabelId) {
    this.parentLabelId = parentLabelId;
  }
}
