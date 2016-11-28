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
package org.gedcomx.source;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.gedcomx.common.Attributable;
import org.gedcomx.common.Attribution;
import org.gedcomx.common.Qualifier;
import org.gedcomx.common.URI;
import org.gedcomx.links.HypermediaEnabledData;
import org.gedcomx.links.Link;
import org.gedcomx.rt.GedcomxModelVisitor;
import org.gedcomx.rt.json.JsonElementWrapper;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * An attributable reference to a description of a source.
 *
 * @author Ryan Heaton
 */
@XmlRootElement ( name = "sourceReference" )
@JsonElementWrapper ( name = "sourceReferences" )
@XmlType ( name = "SourceReference" )
@JsonInclude ( JsonInclude.Include.NON_NULL )
public class SourceReference extends HypermediaEnabledData implements Attributable {

  private URI descriptionRef;
  private String descriptionId;
  private Attribution attribution;
  private List<Qualifier> qualifiers;

  @Override
  public SourceReference id(String id) {
    return (SourceReference) super.id(id);
  }

  @Override
  public SourceReference extensionElement(Object element) {
    return (SourceReference) super.extensionElement(element);
  }

  @Override
  public SourceReference link(Link link) {
    return (SourceReference) super.link(link);
  }

  @Override
  public SourceReference link(String rel, URI href) {
    return (SourceReference) super.link(rel, href);
  }

  /**
   * The attribution metadata for this source reference.
   *
   * @return The attribution metadata for this source reference.
   */
  public Attribution getAttribution() {
    return attribution;
  }

  /**
   * The attribution metadata for this source reference.
   *
   * @param attribution The attribution metadata for this source reference.
   */
  public void setAttribution(Attribution attribution) {
    this.attribution = attribution;
  }

  /**
   * Build up this source reference with attribution.
   *
   * @param attribution The attribution.
   * @return this.
   */
  public SourceReference attribution(Attribution attribution) {
    setAttribution(attribution);
    return this;
  }

  /**
   * A reference to a description of the source being referenced.
   *
   * @return A reference to a description of the source being referenced.
   */
  @XmlAttribute ( name = "description" )
  @JsonProperty ( "description" ) @org.codehaus.jackson.annotate.JsonProperty ( "description" )
  public URI getDescriptionRef() {
    return descriptionRef;
  }

  /**
   * A reference to a description of the source being referenced.
   *
   * @param descriptionRef A reference to a description of the source being referenced.
   */
  @JsonProperty ( "description" ) @org.codehaus.jackson.annotate.JsonProperty ( "description" )
  public void setDescriptionRef(URI descriptionRef) {
    this.descriptionRef = descriptionRef;
  }

  /**
   * Build up this source reference with a description reference.
   *
   * @param descriptionRef The description ref.
   * @return this.
   */
  public SourceReference descriptionRef(URI descriptionRef) {
    setDescriptionRef(descriptionRef);
    return this;
  }

  /**
   * Id of the source being referenced.
   *
   * @return Id of the source being referenced.
   */
  @XmlAttribute ( name = "descriptionId" )
  @JsonProperty ( "descriptionId" ) @org.codehaus.jackson.annotate.JsonProperty ( "descriptionId" )
  public String getDescriptionId() {
    return descriptionId;
  }

  /**
   * Id of the source being referenced.
   *
   * @param descriptionId Id of the source being referenced.
   */
  @JsonProperty ( "descriptionId" ) @org.codehaus.jackson.annotate.JsonProperty ( "descriptionId" )
  public void setDescriptionId(String descriptionId) {
    this.descriptionId = descriptionId;
  }

  /**
   * Build up this source iderence with a description iderence.
   *
   * @param descriptionId The description id.
   * @return this.
   */
  public SourceReference descriptionId(String descriptionId) {
    setDescriptionId(descriptionId);
    return this;
  }

  /**
   * Build up this source reference with a description reference.
   *
   * @param description The description.
   * @return this.
   */
  public SourceReference description(SourceDescription description) {
    if (description.getId() == null) {
      throw new IllegalArgumentException("Cannot reference description: no id.");
    }
    return descriptionRef(URI.create("#" + description.getId())).descriptionId(description.getId());
  }

  /**
   * The qualifiers associated with this source reference.
   *
   * @return The qualifiers associated with this source reference.
   */
  @XmlElement (name = "qualifier")
  @JsonProperty ("qualifiers") @org.codehaus.jackson.annotate.JsonProperty ("qualifiers")
  public List<Qualifier> getQualifiers() {
    return qualifiers;
  }

  /**
   * Set the qualifiers associated with this source reference.
   *
   * @param qualifiers qualifiers to associate with this source reference.
   */
  @JsonProperty ("qualifiers") @org.codehaus.jackson.annotate.JsonProperty ("qualifiers")
  public void setQualifiers(List<Qualifier> qualifiers) {
    this.qualifiers = qualifiers;
  }

  /**
   * Build up this source reference with a qualifier.
   *
   * @param qualifier The qualifier.
   * @return this.
   */
  public SourceReference qualifier(Qualifier qualifier) {
    addQualifier(qualifier);
    return this;
  }

  /**
   * Add a qualifier.
   *
   * @param qualifier The qualifier.
   */
  public void addQualifier(Qualifier qualifier) {
    if (qualifiers == null) {
      qualifiers = new ArrayList<Qualifier>();
    }
    qualifiers.add( qualifier );
  }

  /**
   * Accept a visitor.
   *
   * @param visitor The visitor.
   */
  public void accept(GedcomxModelVisitor visitor) {
    visitor.visitSourceReference(this);
  }
}
