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
package org.gedcomx.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.webcohesion.enunciate.metadata.Facet;
import org.gedcomx.links.HypermediaEnabledData;
import org.gedcomx.links.Link;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.rt.GedcomxModelVisitor;
import org.gedcomx.rt.json.JsonElementWrapper;

import javax.xml.XMLConstants;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;

/**
 * A reference to a resource that is being used as evidence.
 *
 * @author Ryan Heaton
 */
@XmlRootElement
@XmlType ( name = "EvidenceReference" )
@JsonElementWrapper ( name = "evidence" )
@JsonInclude ( JsonInclude.Include.NON_NULL )
public final class EvidenceReference extends HypermediaEnabledData implements Attributable {

  private URI resource;
  private String resourceId;
  private Attribution attribution;

  public EvidenceReference() {
  }

  public EvidenceReference(URI resource) {
    this.resource = resource;
  }

  public EvidenceReference(URI resource, String resourceId) {
    this.resource = resource;
    this.resourceId = resourceId;
  }

  @Override
  public EvidenceReference link(Link link) {
    return (EvidenceReference) super.link(link);
  }

  @Override
  public EvidenceReference link(String rel, URI href) {
    return (EvidenceReference) super.link(rel, href);
  }

  @Override
  public EvidenceReference id(String id) {
    return (EvidenceReference) super.id(id);
  }

  @Override
  public EvidenceReference extensionElement(Object element) {
    return (EvidenceReference) super.extensionElement(element);
  }

  /**
   * The resource id of the resource being referenced. Used as an extension attribute when resolving the resource is inconvenient.
   *
   * @return The resource id of the resource being referenced.
   */
  @XmlAttribute
  @Facet ( GedcomxConstants.FACET_GEDCOMX_RS )
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
   * Build up this reference with a resource id.
   *
   * @param resourceId The resource id.
   * @return this.
   */
  public EvidenceReference resourceId(String resourceId) {
    this.resourceId = resourceId;
    return this;
  }

  /**
   * The URI to the resource being referenced as evidence.
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
   * The URI to the resource being referenced as evidence.
   *
   * @see <a href="http://www.w3.org/TR/webarch/#identification">http://www.w3.org/TR/webarch/#identification</a>
   * @param resource The URI to the resource.
   */
  public void setResource(URI resource) {
    this.resource = resource;
  }

  /**
   * Build up this reference with a resource URI.
   *
   * @param resource The resource.
   * @return this
   */
  public EvidenceReference resource(URI resource) {
    this.resource = resource;
    return this;
  }

  /**
   * Attribution metadata for this evidence reference.
   *
   * @return Attribution metadata for evidence reference.
   */
  @Override
  public Attribution getAttribution() {
    return attribution;
  }

  /**
   * Attribution metadata for evidence reference.
   *
   * @param attribution Attribution metadata for evidence reference.
   */
  @Override
  public void setAttribution(Attribution attribution) {
    this.attribution = attribution;
  }

  /**
   * Build up this evidence reference with an attribution.
   *
   * @param attribution The attribution.
   * @return this.
   */
  public EvidenceReference attribution(Attribution attribution) {
    this.attribution = attribution;
    return this;
  }

  /**
   * Accept a visitor.
   *
   * @param visitor The visitor.
   */
  public void accept(GedcomxModelVisitor visitor) {
    visitor.visitEvidenceReference(this);
  }

  /**
   * Provide a simple toString() method.
   */
  @Override
  public String toString() {
    return (resource == null) ? "" : resource.toString();
  }
}
