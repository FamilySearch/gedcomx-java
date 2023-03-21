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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.webcohesion.enunciate.metadata.Facet;
import org.gedcomx.common.Attribution;
import org.gedcomx.common.URI;
import org.gedcomx.links.HypermediaEnabledData;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.rt.json.JsonElementWrapper;

import javax.xml.XMLConstants;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;

/**
 */
@XmlRootElement (name = "discussion-reference")
@JsonElementWrapper (name = "discussion-references")
@XmlType ( name = "DiscussionReference" )
@JsonInclude ( JsonInclude.Include.NON_NULL )
public final class DiscussionReference extends HypermediaEnabledData {

  private URI resource;
  private Attribution attribution;
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
  @Facet( GedcomxConstants.FACET_FS_FT_READ_ONLY )
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
   * @see <a href="http://www.w3.org/TR/webarch/#identification">http://www.w3.org/TR/webarch/#identification</a>
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
   * @see <a href="http://www.w3.org/TR/webarch/#identification">http://www.w3.org/TR/webarch/#identification</a>
   * @param resource The URI to the resource.
   */
  public void setResource(URI resource) {
    this.resource = resource;
  }

  /**
   * The attribution metadata for this discussion reference.
   *
   * @return The attribution metadata for this discussion reference.
   */
  public Attribution getAttribution() {
    return attribution;
  }

  /**
   * The attribution metadata for this discussion reference.
   *
   * @param attribution The attribution metadata for this discussion reference.
   */
  public void setAttribution(Attribution attribution) {
    this.attribution = attribution;
  }

  /**
   * Build up this discussion reference with attribution.
   *
   * @param attribution The attribution.
   * @return this.
   */
  public DiscussionReference attribution(Attribution attribution) {
    setAttribution(attribution);
    return this;
  }

  /**
   * Provide a simple toString() method.
   */
  @Override
  public String toString() {
    return (resource == null) ? "" : resource.toString();
  }

}
