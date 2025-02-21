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
import io.swagger.v3.oas.annotations.media.Schema;

import org.gedcomx.agent.Agent;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.rt.RDFRange;
import org.gedcomx.rt.RDFSubPropertyOf;
import org.gedcomx.rt.json.JsonElementWrapper;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import java.util.Date;


/**
 * Attribution for genealogical information. Attribution is used to model <strong>who</strong> is contributing/modifying
 * information, <strong>when</strong> they contributed it, and <strong>why</strong> they are making the
 * contribution/modification.
 */
@XmlRootElement
@JsonElementWrapper (name = "attribution")
@XmlType ( name = "Attribution", propOrder = { "contributor", "modified", "changeMessage", "changeMessageResource", "creator", "created" } )
@JsonInclude ( JsonInclude.Include.NON_NULL )
@SuppressWarnings("gedcomx:no_id")
@Schema(description = "Attribution for genealogical information. Attribution is used to model who is contributing/modifying information, when they contributed it, and why they are making the contribution/modification.")
public class Attribution extends ExtensibleData {

  @Schema(description = "Reference to the contributor of the attributed data.")
  private ResourceReference contributor;

  @Schema(description = "Reference to the creator of the attributed data. The creator might be different from the contributor if the attributed data has been modified since it was created.")
  private ResourceReference creator;

  @Schema(description = "The modified timestamp for the attributed data.")
  private Date modified;

  @Schema(description = "The created timestamp for the attributed data.")
  private Date created;

  @Schema(description = "The \"change message\" for the attributed data provided by the contributor.")
  private String changeMessage;

  @Schema(description = "A reference to the change message for the attributed data provided by the contributor.")
  private URI changeMessageResource;

  public Attribution() {
  }

  public Attribution(Attribution copy) {
    super(copy);
    this.contributor = copy.contributor == null ? null : new ResourceReference(copy.contributor);
    this.creator = copy.creator == null ? null : new ResourceReference(copy.creator);
    this.modified = copy.modified;
    this.created = copy.created;
    this.changeMessage = copy.changeMessage;
    this.changeMessageResource = copy.changeMessageResource;
  }

  @Override
  public Attribution id(String id) {
    return (Attribution) super.id(id);
  }

  @Override
  public Attribution extensionElement(Object element) {
    return (Attribution) super.extensionElement(element);
  }

  /**
   * Reference to the contributor of the attributed data.
   *
   * @return Reference to the contributor of the attributed data.
   */
  @RDFRange({})
  @RDFSubPropertyOf( "http://purl.org/dc/terms/contributor")
  @Facet( GedcomxConstants.FACET_FS_FT_READ_ONLY )
  public ResourceReference getContributor() {
    return contributor;
  }

  /**
   * Reference to the contributor of the attributed data.
   *
   * @param contributor Reference to the contributor of the attributed data.
   */
  public void setContributor(ResourceReference contributor) {
    this.contributor = contributor;
  }

  /**
   * Build up this attribution with a contributor.
   *
   * @param contributor The contributor.
   * @return this.
   */
  public Attribution contributor(ResourceReference contributor) {
    this.contributor = contributor;
    return this;
  }

  /**
   * Build up this attribution with a contributor.
   *
   * @param contributor The contributor.
   * @return this.
   */
  public Attribution contributor(URI contributor) {
    return contributor(new ResourceReference(contributor));
  }

  /**
   * Build up this attribution with a contributor.
   *
   * @param agent The contributor.
   * @return this.
   */
  public Attribution contributor(Agent agent) {
    if (agent.getId() == null) {
      throw new IllegalArgumentException("Can't reference agent as a contributor: no id.");
    }
    return contributor(URI.create("#" + agent.getId()));
  }

  /**
   * Reference to the creator of the attributed data. The creator might be different from the contributor
   * if the attributed data has been modified since it was created.
   *
   * @return Reference to the creator of the attributed data.
   */
  @RDFRange({})
  @RDFSubPropertyOf( "http://purl.org/dc/terms/creator")
  @Facet( GedcomxConstants.FACET_FS_FT_READ_ONLY )
  public ResourceReference getCreator() {
    return creator;
  }

  /**
   * Reference to the creator of the attributed data. The creator might be different from the contributor
   * if the attributed data has been modified since it was created.
   *
   * @param creator Reference to the creator of the attributed data.
   */
  public void setCreator(ResourceReference creator) {
    this.creator = creator;
  }

  /**
   * Build up this attribution with a creator.
   *
   * @param creator The creator.
   * @return this.
   */
  public Attribution creator(ResourceReference creator) {
    this.creator = creator;
    return this;
  }

  /**
   * Build up this attribution with a creator.
   *
   * @param creator The creator.
   * @return this.
   */
  public Attribution creator(URI creator) {
    return creator(new ResourceReference(creator));
  }

  /**
   * Build up this attribution with a creator.
   *
   * @param agent The creator.
   * @return this.
   */
  public Attribution creator(Agent agent) {
    if (agent.getId() == null) {
      throw new IllegalArgumentException("Can't reference agent as a creator: no id.");
    }
    return creator(URI.create("#" + agent.getId()));
  }

  /**
   * The modified timestamp for the attributed data.
   *
   * @return The modified timestamp for the attributed data.
   */
  @RDFSubPropertyOf( "http://purl.org/dc/terms/modified")
  @Facet( GedcomxConstants.FACET_FS_FT_READ_ONLY )
  public Date getModified() {
    return modified;
  }

  /**
   * The modified timestamp for the attributed data.
   *
   * @param modified The modified timestamp for the attributed data.
   */
  public void setModified(Date modified) {
    this.modified = modified;
  }

  /**
   * Build up this attribution with a modified date.
   *
   * @param modified The modified date.
   * @return this.
   */
  public Attribution modified(Date modified) {
    this.modified = modified;
    return this;
  }

  /**
   * The created timestamp for the attributed data.
   *
   * @return The created timestamp for the attributed data.
   */
  @RDFSubPropertyOf( "http://purl.org/dc/terms/created")
  @Facet( GedcomxConstants.FACET_FS_FT_READ_ONLY )
  public Date getCreated() {
    return created;
  }

  /**
   * The created timestamp for the attributed data.
   *
   * @param created The created timestamp for the attributed data.
   */
  public void setCreated(Date created) {
    this.created = created;
  }

  /**
   * Build up this attribution with a created date.
   *
   * @param created The created date.
   * @return this.
   */
  public Attribution created(Date created) {
    this.created = created;
    return this;
  }

  /**
   * The "change message" for the attributed data provided by the contributor.
   *
   * @return The "change message" for the attributed data provided by the contributor.
   */
  @RDFSubPropertyOf( "http://purl.org/dc/terms/description")
  public String getChangeMessage() {
    return changeMessage;
  }

  /**
   * The "change message" for the attributed data provided by the contributor.
   *
   * @param changeMessage The "change message" for the attributed data provided by the contributor.
   */
  public void setChangeMessage(String changeMessage) {
    this.changeMessage = changeMessage;
  }

  /**
   * Build up this attribution with a change message.
   *
   * @param changeMessage The change message.
   * @return this.
   */
  public Attribution changeMessage(String changeMessage) {
    this.changeMessage = changeMessage;
    return this;
  }

  /**
   * A reference to the change message for the attributed data provided by the contributor.
   *
   * @return A reference to the change message for the attributed data provided by the contributor.
   */
  public URI getChangeMessageResource() {
    return changeMessageResource;
  }

  /**
   * A reference to the change message for the attributed data provided by the contributor.
   *
   * @param changeMessageResource A reference to the change message for the attributed data provided by the contributor.
   */
  public void setChangeMessageResource(URI changeMessageResource) {
    this.changeMessageResource = changeMessageResource;
  }

  /**
   * Build up this attribution with a change message resource.
   *
   * @param changeMessageResource The change message resource.
   * @return this.
   */
  public Attribution changeMessageResource(URI changeMessageResource) {
    this.changeMessageResource = changeMessageResource;
    return this;
  }

  /**
   * Provide a simple toString() method.
   */
  @Override
  public String toString() {
    return (contributor == null) ? "" : contributor.toString();
  }
}
