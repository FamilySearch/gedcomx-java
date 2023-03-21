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
package org.familysearch.platform.records;

import java.io.Serializable;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonInclude;

import org.gedcomx.common.URI;
import org.gedcomx.rt.json.JsonElementWrapper;

@XmlRootElement
@JsonElementWrapper(name = "fieldInfo")
@XmlType(name = "FieldInfo")
@JsonInclude( JsonInclude.Include.NON_NULL )
public class FieldInfo implements Serializable {
  private String fieldType;
  private String displayLabel;
  private boolean standard;
  private boolean editable;
  private boolean displayable;
  private List<String> elementTypes;
  private URI uri;

  public String getFieldType() {
    return fieldType;
  }

  public void setFieldType(String fieldType) {
    this.fieldType = fieldType;
  }

  public String getDisplayLabel() {
    return displayLabel;
  }

  public void setDisplayLabel(String displayLabel) {
    this.displayLabel = displayLabel;
  }

  public boolean isStandard() {
    return standard;
  }

  public void setStandard(boolean standard) {
    this.standard = standard;
  }

  public boolean isEditable() {
    return editable;
  }

  public void setEditable(boolean editable) {
    this.editable = editable;
  }

  public boolean isDisplayable() {
    return displayable;
  }

  public void setDisplayable(boolean displayable) {
    this.displayable = displayable;
  }

  public List<String> getElementTypes() {
    return elementTypes;
  }

  public void setElementTypes(List<String> elementTypes) {
    this.elementTypes = elementTypes;
  }

  @XmlAttribute
  public URI getUri() {
    return uri;
  }

  public void setUri(URI uri) {
    this.uri = uri;
  }
}
