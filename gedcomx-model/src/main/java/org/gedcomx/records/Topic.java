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
 * <p>A <tt>topic</tt> is a grouping of related facets of a collection or set of search results, used for convenience in browsing.</p>
 */
@XmlType ( name = "Topic", propOrder = {"title", "description", "facets" })
public class Topic extends HypermediaEnabledData {

  private String title;
  private String description;
  private List<Facet> facets;

  /**
   * A title for the topic.
   *
   * @return A title for the topic.
   */
  public String getTitle() {
    return title;
  }

  /**
   * A title for the topic.
   *
   * @param title A title for the topic.
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * A description for the topic.
   *
   * @return A description for the topic.
   */
  public String getDescription() {
    return description;
  }

  /**
   * A description for the topic.
   *
   * @param description A description for the topic.
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * The set of facets under this topic.
   *
   * @return The set of facets under this topic.
   */
  @XmlElement (name="facet")
  @JsonProperty ("facets")
  @JsonName ("facets")
  public List<Facet> getFacets() {
    return facets;
  }

  /**
   * The set of facets under this topic.
   *
   * @param facets The set of facets under this topic.
   */
  @JsonProperty ("facets")
  public void setFacets(List<Facet> facets) {
    this.facets = facets;
  }

  public void accept(GedcomxModelVisitor visitor) {
    visitor.visitTopic(this);
  }

}
