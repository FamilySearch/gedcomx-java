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

import org.codehaus.jackson.annotate.JsonIgnore;
import org.gedcomx.common.URI;
import org.gedcomx.links.HypermediaEnabledData;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.rt.json.JsonElementWrapper;
import org.gedcomx.types.ResourceType;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 * A description of the content of a collection by resource type.
 *
 * @author Ryan Heaton
 */
@XmlRootElement
@JsonElementWrapper ( name = "collectionContent" )
@XmlType ( name = "CollectionContent" )
@org.codehaus.enunciate.Facet ( name = GedcomxConstants.FACET_GEDCOMX_RECORD )
public class CollectionContent extends HypermediaEnabledData {

  private URI resourceType;
  private Integer count;
  private Float completeness;

  /**
   * The type of resource being covered in this collection.
   *
   * @return The type of resource being covered in this collection.
   */
  public URI getResourceType() {
    return resourceType;
  }

  /**
   * The type of resource being covered in this collection.
   *
   * @param resourceType The type of resource being covered in this collection.
   */
  public void setResourceType(URI resourceType) {
    this.resourceType = resourceType;
  }

  /**
   * The type of resource being covered in this collection.
   *
   * @return The type of resource being covered in this collection.
   */
  @XmlTransient
  @JsonIgnore
  public ResourceType getKnownResourceType() {
    return getResourceType() == null ? null : ResourceType.fromQNameURI(getResourceType());
  }

  /**
   * The type of resource being covered in this collection.
   *
   * @param type The type of resource being covered in this collection.
   */
  @JsonIgnore
  public void setKnownResourceType(ResourceType type) {
    setResourceType(type == null ? null : URI.create(org.codehaus.enunciate.XmlQNameEnumUtil.toURIValue(type)));
  }

  /**
   * The count of the items applicable to this content aspect.
   *
   * @return The count of the items applicable to this content aspect.
   */
  public Integer getCount() {
    return count;
  }

  /**
   * The count of the items applicable to this content aspect.
   *
   * @param count The count of the items applicable to this content aspect.
   */
  public void setCount(Integer count) {
    this.count = count;
  }

  /**
   * A completeness factor for this content; i.e. what percentage of the total number of items in the collection is included in this content aspect. The
   * completeness factor is a value between 0 and 1.
   *
   * @return A completeness factor for this content aspect, a value between 0 and 1.
   */
  public Float getCompleteness() {
    return completeness;
  }

  /**
   * A completeness factor for this content; i.e. what percentage of the total number of items in the collection is included in this content aspect. The
   * completeness factor is a value between 0 and 1.
   *
   * @param completeness A completeness factor for this content aspect, a value between 0 and 1.
   */
  public void setCompleteness(Float completeness) {
    this.completeness = completeness;
  }

}
