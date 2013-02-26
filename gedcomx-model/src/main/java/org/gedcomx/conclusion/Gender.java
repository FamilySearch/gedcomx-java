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
package org.gedcomx.conclusion;

import org.codehaus.enunciate.json.JsonName;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.gedcomx.common.ResourceReference;
import org.gedcomx.common.URI;
import org.gedcomx.records.HasFieldValueSources;
import org.gedcomx.rt.GedcomxModelVisitor;
import org.gedcomx.rt.json.JsonElementWrapper;
import org.gedcomx.types.GenderType;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * A gender conclusion.
 *
 * @author Ryan Heaton
 */
@XmlType ( name = "Gender" )
@XmlRootElement
@JsonElementWrapper ( name = "genders" )
public class Gender extends Conclusion implements HasFieldValueSources {

  private URI type;
  private List<ResourceReference> fieldValueSources;

  /**
   * Default constructor.
   */
  public Gender() {
  }

  /**
   * Constructs a new gender object with the passed in type.
   *
   * @param type The type of the gender.
   */
  public Gender(GenderType type) {
    setKnownType(type);
  }

  /**
   * The type of the gender.
   *
   * @return The type of the gender.
   */
  @XmlAttribute
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
   * The known type of the gender.
   *
   * @return The type of the gender.
   */
  @XmlTransient
  @JsonIgnore
  public GenderType getKnownType() {
    return getType() == null ? null : GenderType.fromQNameURI(getType());
  }

  /**
   * The type of the gender.
   *
   * @param type The type of the gender.
   */
  @JsonIgnore
  public void setKnownType(GenderType type) {
    setType(type == null ? null : URI.create(org.codehaus.enunciate.XmlQNameEnumUtil.toURIValue(type)));
  }

  /**
   * The list of field values that are being used to support this data.
   *
   * @return The list of field values that are being used to support this data.
   */
  @Override
  @XmlElement (name = "sourceFieldValue")
  @JsonProperty ("sourceFieldValues")
  @JsonName ("sourceFieldValues")
  public List<ResourceReference> getFieldValueSources() {
    return fieldValueSources;
  }

  /**
   * The list of field values that are being used to support this data.
   *
   * @param supportingFieldValues The list of field values that are being used to support this data.
   */
  @Override
  @JsonProperty ("sourceFieldValues")
  public void setFieldValueSources(List<ResourceReference> supportingFieldValues) {
    this.fieldValueSources = supportingFieldValues;
  }

  @Override
  public String toString() {
    return "type=" + getKnownType();
  }

  /**
   * Accept a visitor.
   *
   * @param visitor The visitor.
   */
  public void accept(GedcomxModelVisitor visitor) {
    visitor.visitGender(this);
  }
}
