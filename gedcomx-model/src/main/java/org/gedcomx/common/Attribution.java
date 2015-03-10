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

import org.codehaus.enunciate.Facet;
import org.gedcomx.agent.Agent;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.rt.RDFRange;
import org.gedcomx.rt.RDFSubPropertyOf;
import org.gedcomx.rt.json.JsonElementWrapper;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;


/**
 * Attribution for genealogical information. Attribution is used to model <strong>who</strong> is contributing/modifying
 * information, <strong>when</strong> they contributed it, and <strong>why</strong> they are making the
 * contribution/modification.
 */
@XmlRootElement
@JsonElementWrapper (name = "attribution")
@XmlType ( name = "Attribution", propOrder = { "contributor", "modified", "changeMessage" } )
@SuppressWarnings("gedcomx:no_id")
public final class Attribution extends ExtensibleData {

  private ResourceReference contributor;
  private Date modified;
  private String changeMessage;

  @Override
  public Attribution id(String id) {
    return (Attribution) super.id(id);
  }

  /**
   * Reference to the contributor of the attributed data.
   *
   * @return Reference to the contributor of the attributed data.
   */
  @RDFRange({})
  @RDFSubPropertyOf( "http://purl.org/dc/terms/contributor")
  @Facet( name = GedcomxConstants.FACET_FS_FT_READ_ONLY )
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
   * The modified timestamp for the attributed data.
   *
   * @return The modified timestamp for the attributed data.
   */
  @RDFSubPropertyOf( "http://purl.org/dc/terms/modified")
  @Facet( name = GedcomxConstants.FACET_FS_FT_READ_ONLY )
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
   * Provide a simple toString() method.
   */
  @Override
  public String toString() {
    return (contributor == null) ? "" : contributor.toString();
  }
}
