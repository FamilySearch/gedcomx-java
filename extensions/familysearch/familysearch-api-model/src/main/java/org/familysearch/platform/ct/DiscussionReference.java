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
package org.familysearch.platform.ct;

import org.codehaus.enunciate.Facet;
import org.codehaus.enunciate.Facets;
import org.gedcomx.common.URI;
import org.gedcomx.links.HypermediaEnabledData;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.rt.json.JsonElementWrapper;

import javax.xml.XMLConstants;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

/**
 */
@XmlRootElement (name = "discussion-reference")
@JsonElementWrapper (name = "discussion-references")
@XmlType ( name = "DiscussionReference" )
public final class DiscussionReference extends HypermediaEnabledData {

  private URI resource;
  private String resourceId;

  public DiscussionReference() {
  }

  public DiscussionReference(URI resource) {
    this.resource = resource;
  }

  public DiscussionReference(URI resource, String resourceId) {
    this.resource = resource;
    this.resourceId = resourceId;
  }

  /**
   * The id of the discussion being referenced.
   *
   * @return The id of the discussion being referenced.
   */
  @XmlAttribute
  @Facet( name = GedcomxConstants.FACET_FS_FT_READ_ONLY )
  public String getResourceId() {
    return resourceId;
  }

  /**
   * The id of the discussion being referenced.
   *
   * @param resourceId The id of the discussion being referenced.
   */
  public void setResourceId(String resourceId) {
    this.resourceId = resourceId;
  }

  /**
   * Build up this discussion reference with a resource id.
   *
   * @param resourceId The resource id.
   * @return this.
   */
  public DiscussionReference resourceId(String resourceId) {
    setResourceId(resourceId);
    return this;
  }


  /**
   * The URI to the resource. For more information, see <a href="http://www.w3.org/TR/webarch/#identification">Architecture of the World
   * Wide Web, Volume One, Section 2</a>
   *
   * @link http://www.w3.org/TR/webarch/#identification
   * @return The URI to the resource.
   */
  @XmlAttribute
  @XmlSchemaType (name = "anyURI", namespace = XMLConstants.W3C_XML_SCHEMA_NS_URI)
  public URI getResource() {
    return resource;
  }

  /**
   * The URI to the resource. For more information, see <a href="http://www.w3.org/TR/webarch/#identification">Architecture of the World
   * Wide Web, Volume One, Section 2</a>
   *
   * @link http://www.w3.org/TR/webarch/#identification
   * @param resource The URI to the resource.
   */
  public void setResource(URI resource) {
    this.resource = resource;
  }

  /**
   * Provide a simple toString() method.
   */
  @Override
  public String toString() {
    return (resource == null) ? "" : resource.toString();
  }

}
