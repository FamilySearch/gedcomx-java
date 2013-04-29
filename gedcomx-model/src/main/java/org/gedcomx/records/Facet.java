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
import org.gedcomx.links.HypermediaEnabledData;
import org.gedcomx.rt.GedcomxModelVisitor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * <p>A <tt>facet</tt> is a logical grouping of resources by specific criteria, used for convenience in browsing a collection or a set of search results.</p>
 */
@XmlType ( name = "Facet", propOrder = {"title", "description", "key", "values" })
public class Facet extends HypermediaEnabledData {

  private String title;
  private String description;
  private String key;
  private List<FacetValue> values;

  /**
   * A title for the facet.
   *
   * @return A title for the facet.
   */
  public String getTitle() {
    return title;
  }

  /**
   * A title for the facet.
   *
   * @param title A title for the facet.
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * A description for the facet.
   *
   * @return A description for the facet.
   */
  public String getDescription() {
    return description;
  }

  /**
   * A description for the facet.
   *
   * @param description A description for the facet.
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * A key used to query this facet.
   *
   * @return A key used to query this facet.
   */
  public String getKey() {
    return key;
  }

  /**
   * A key used to query this facet.
   *
   * @param key A key used to query this facet.
   */
  public void setKey(String key) {
    this.key = key;
  }

  /**
   * The set of values for the field.
   *
   * @return The set of values for the field.
   */
  @XmlElement (name="value")
  @JsonProperty ("values")
  @JsonName ("values")
  public List<FacetValue> getValues() {
    return values;
  }

  /**
   * The set of values for the field.
   *
   * @param values The set of values for the field.
   */
  @JsonProperty ("values")
  public void setValues(List<FacetValue> values) {
    this.values = values;
  }

  public void accept(GedcomxModelVisitor visitor) {
    visitor.visitFacet(this);
  }

}
