/**
 * Copyright Intellectual Reserve, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gedcomx.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.webcohesion.enunciate.metadata.Facet;
import com.webcohesion.enunciate.metadata.Facets;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.rt.json.JsonElementWrapper;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import javax.xml.XMLConstants;
import java.util.Objects;

/**
 * A generic reference to a resource.
 *
 * @author Ryan Heaton
 */
@XmlRootElement
@XmlType ( name = "ResourceReference" )
@JsonElementWrapper ( name = "resourceReference" )
@JsonInclude ( JsonInclude.Include.NON_NULL )
public final class ResourceReference {

  private URI resource;
  private String resourceId;

  public ResourceReference() {
  }

  public ResourceReference(URI resource) {
    this.resource = resource;
  }

  public ResourceReference(java.net.URI resource) {
    this.resource = URI.create(resource.toString());
  }

  public ResourceReference(URI resource, String resourceId) {
    this.resource = resource;
    this.resourceId = resourceId;
  }
  
  public ResourceReference(ResourceReference copy) {
    this.resource = copy.resource;
    this.resourceId = copy.resourceId;
  }
  
  /**
   * The resource id of the resource being referenced. Used as an extension attribute when resolving the resource is inconvenient.
   *
   * @return The resource id of the resource being referenced.
   */
  @XmlAttribute
  @Facets ( {
    @Facet ( GedcomxConstants.FACET_GEDCOMX_RS ),
    @Facet ( GedcomxConstants.FACET_FS_FT_READ_ONLY )
  } )
  public String getResourceId() {
    return resourceId;
  }

  /**
   * The resource id of the resource being referenced. Used as an extension attribute when resolving the resource is inconvenient.
   *
   * @param resourceId The resource id of the resource being referenced.
   */
  public void setResourceId(String resourceId) {
    this.resourceId = resourceId;
  }

  /**
   * Build up this resource reference with a resource id.
   *
   * @param resourceId The resource id.
   * @return this.
   */
  public ResourceReference resourceId(String resourceId) {
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
  @XmlSchemaType ( name = "anyURI", namespace = XMLConstants.W3C_XML_SCHEMA_NS_URI )
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
   * Build up this resource reference with a resource.
   *
   * @param resource The resource.
   * @return this.
   */
  public ResourceReference resource(URI resource) {
    setResource(resource);
    return this;
  }

  /**
   * Provide a simple toString() method.
   */
  @Override
  public String toString() {
    return (resource == null) ? "" : resource.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final ResourceReference that = (ResourceReference) o;
    return Objects.equals(resource, that.resource) &&
           Objects.equals(resourceId, that.resourceId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(resource, resourceId);
  }
}
