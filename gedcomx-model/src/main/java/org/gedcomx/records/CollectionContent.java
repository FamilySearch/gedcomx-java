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
import org.gedcomx.common.URI;
import org.gedcomx.links.HypermediaEnabledData;
import org.gedcomx.links.Link;
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
@com.webcohesion.enunciate.metadata.Facet( GedcomxConstants.FACET_GEDCOMX_RECORD )
@JsonInclude ( JsonInclude.Include.NON_NULL )
public class CollectionContent extends HypermediaEnabledData {

  private URI resourceType;
  private Integer count;
  private Float completeness;

  @Override
  public CollectionContent id(String id) {
    return (CollectionContent) super.id(id);
  }

  @Override
  public CollectionContent extensionElement(Object element) {
    return (CollectionContent) super.extensionElement(element);
  }

  @Override
  public CollectionContent link(String rel, URI href) {
    return (CollectionContent) super.link(rel, href);
  }

  @Override
  public CollectionContent link(Link link) {
    return (CollectionContent) super.link(link);
  }

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
   * Build out the content with a resource type.
   * @param resourceType The resource type.
   * @return this.
   */
  public CollectionContent resourceType(URI resourceType) {
    setResourceType(resourceType);
    return this;
  }

  /**
   * Build out the content with a resource type.
   * @param resourceType The resource type.
   * @return this.
   */
  public CollectionContent resourceType(ResourceType resourceType) {
    setKnownResourceType(resourceType);
    return this;
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
    setResourceType(type == null ? null : type.toQNameURI());
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
   * Build out this collection content with a count.
   * @param count The count.
   * @return this.
   */
  public CollectionContent count(Integer count) {
    setCount(count);
    return this;
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

  /**
   * Build out this content with completeness.
   * @param completeness The completeness.
   * @return this.
   */
  public CollectionContent completeness(Float completeness) {
    setCompleteness(completeness);
    return this;
  }

}
