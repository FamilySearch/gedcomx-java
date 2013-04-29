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

import org.gedcomx.rt.GedcomxModelVisitor;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>A facet value is the value that is used by a facet to group related resources.</p>
 */
@XmlType ( name = "FacetValue", propOrder = { "term", "count" })
public class FacetValue extends Topic {

  private Boolean active;
  private String term;
  private Integer count;

  /**
   * Whether this value is currently being applied to the result set (as opposed to just being listed as a possibility).
   *
   * @return Whether this value is currently being applied to the result set.
   */
  @XmlAttribute
  public Boolean getActive() {
    return active;
  }

  /**
   * Whether this value is currently being applied to the result set (as opposed to just being listed as a possibility).
   *
   * @param active Whether this value is currently being applied to the result set.
   */
  public void setActive(Boolean active) {
    this.active = active;
  }

  /**
   * The term to use to apply this value to the facet.
   *
   * @return The term to use to apply this value to the facet.
   */
  public String getTerm() {
    return term;
  }

  /**
   * The term to use to apply this value to the facet.
   *
   * @param term The term to use to apply this value to the facet.
   */
  public void setTerm(String term) {
    this.term = term;
  }

  /**
   * The number of resources grouped into this facet value.
   *
   * @return The number of resources grouped into this facet value.
   */
  public Integer getCount() {
    return count;
  }

  /**
   * The number of resources grouped by this facet value.
   *
   * @param count The number of resources grouped by this facet value.
   */
  public void setCount(Integer count) {
    this.count = count;
  }

  public void accept(GedcomxModelVisitor visitor) {
    visitor.visitFacetValue(this);
  }

}
