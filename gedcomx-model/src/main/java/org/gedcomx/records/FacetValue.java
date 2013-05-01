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

import org.gedcomx.links.HypermediaEnabledData;

import javax.xml.bind.annotation.XmlType;

/**
 * <p>A facet value is the value that is used by a facet to group related resources.</p>
 */
@XmlType ( name = "FacetValue", propOrder = {"title", "value", "count" })
public class FacetValue extends HypermediaEnabledData {

  private String title;
  private String value;
  private Integer count;

  /**
   * A title for the facet value.
   *
   * @return A title for the facet value.
   */
  public String getTitle() {
    return title;
  }

  /**
   * A title for the facet value.
   *
   * @param title A title for the facet value.
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * The value.
   *
   * @return The value.
   */
  public String getValue() {
    return value;
  }

  /**
   * The value.
   *
   * @param value The value.
   */
  public void setValue(String value) {
    this.value = value;
  }

  /**
   * The number of resources applicable to this value.
   *
   * @return The number of resources applicable to this value.
   */
  public Integer getCount() {
    return count;
  }

  /**
   * The number of resources applicable to this value.
   *
   * @param count The number of resources applicable to this value.
   */
  public void setCount(Integer count) {
    this.count = count;
  }

}
