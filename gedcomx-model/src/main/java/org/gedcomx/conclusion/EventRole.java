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
package org.gedcomx.conclusion;

import org.codehaus.enunciate.Facet;
import org.codehaus.enunciate.qname.XmlQNameEnumRef;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.gedcomx.common.Attribution;
import org.gedcomx.common.Note;
import org.gedcomx.common.ResourceReference;
import org.gedcomx.common.URI;
import org.gedcomx.links.Link;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.rt.GedcomxModelVisitor;
import org.gedcomx.rt.RDFRange;
import org.gedcomx.source.SourceDescription;
import org.gedcomx.source.SourceReference;
import org.gedcomx.types.ConfidenceLevel;
import org.gedcomx.types.EventRoleType;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 * A role that a specific person plays in an event.
 *
 * @author Ryan Heaton
 */
@XmlType ( name = "EventRole", propOrder = { "person", "details" } )
@Facet ( name = GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
public class EventRole extends Conclusion {

  private ResourceReference person;
  private URI type;
  private String details;

  @Override
  public EventRole id(String id) {
    return (EventRole) super.id(id);
  }

  @Override
  public EventRole extensionElement(Object element) {
    return (EventRole) super.extensionElement(element);
  }

  @Override
  public EventRole link(String rel, URI href) {
    return (EventRole) super.link(rel, href);
  }

  @Override
  public EventRole link(Link link) {
    return (EventRole) super.link(link);
  }

  @Override
  public EventRole lang(String lang) {
    return (EventRole) super.lang(lang);
  }

  @Override
  public EventRole confidence(URI confidence) {
    return (EventRole) super.confidence(confidence);
  }

  @Override
  public EventRole confidence(ConfidenceLevel confidence) {
    return (EventRole) super.confidence(confidence);
  }

  @Override
  public EventRole source(SourceReference sourceReference) {
    return (EventRole) super.source(sourceReference);
  }

  @Override
  public EventRole source(SourceDescription source) {
    return (EventRole) super.source(source);
  }

  @Override
  public EventRole note(Note note) {
    return (EventRole) super.note(note);
  }

  @Override
  public EventRole attribution(Attribution attribution) {
    return (EventRole) super.attribution(attribution);
  }

  @Override
  public EventRole analysis(ResourceReference analysis) {
    return (EventRole) super.analysis(analysis);
  }

  @Override
  public EventRole analysis(URI analysis) {
    return (EventRole) super.analysis(analysis);
  }

  @Override
  public EventRole analysis(Document analysis) {
    return (EventRole) super.analysis(analysis);
  }

  @Override
  public EventRole sortKey(String sortKey) {
    return (EventRole) super.sortKey(sortKey);
  }

  /**
   * Reference to the person playing the role in the event.
   *
   * @return Reference to the person playing the role in the event.
   */
  @RDFRange (Person.class)
  public ResourceReference getPerson() {
    return person;
  }

  /**
   * Reference to the person playing the role in the event.
   *
   * @param person Reference to the person playing the role in the event.
   */
  public void setPerson(ResourceReference person) {
    this.person = person;
  }

  /**
   * Build up this event role with a person.
   * @param person The person.
   * @return this.
   */
  public EventRole person(ResourceReference person) {
    setPerson(person);
    return this;
  }

  /**
   * Build up this event role with a person.
   * @param person The person.
   * @return this.
   */
  public EventRole person(Person person) {
    if (person.getId() == null) {
      throw new IllegalStateException("Cannot reference person: no id.");
    }
    setPerson(new ResourceReference(URI.create("#" + person.getId())));
    return this;
  }

  /**
   * The role type.
   *
   * @return The role type.
   */
  @XmlAttribute
  @XmlQNameEnumRef (EventRoleType.class)
  public URI getType() {
    return type;
  }

  /**
   * The role type.
   *
   * @param type The role type.
   */
  public void setType(URI type) {
    this.type = type;
  }

  /**
   * Build up this role with a type.
   *
   * @param type The type.
   * @return this.
   */
  public EventRole type(URI type) {
    setType(type);
    return this;
  }

  /**
   * Build up this role with a type.
   *
   * @param type The type.
   * @return this.
   */
  public EventRole type(EventRoleType type) {
    setKnownType(type);
    return this;
  }

  /**
   * The enum referencing the known role type, or {@link org.gedcomx.types.EventRoleType#OTHER} if not known.
   *
   * @return The enum referencing the known role type, or {@link org.gedcomx.types.EventRoleType#OTHER} if not known.
   */
  @XmlTransient
  @JsonIgnore
  public EventRoleType getKnownType() {
    return getType() == null ? null : EventRoleType.fromQNameURI(getType());
  }

  /**
   * Set the role type from an enumeration of known role types.
   *
   * @param knownType The role type.
   */
  @JsonIgnore
  public void setKnownType(EventRoleType knownType) {
    setType(knownType == null ? null : knownType.toQNameURI());
  }

  /**
   * Details about the role of the person in the event.
   *
   * @return Details about the role of the person in the event.
   */
  public String getDetails() {
    return details;
  }

  /**
   * Details about the role of the person in the event.
   *
   * @param details Details about the role of the person in the event.
   */
  public void setDetails(String details) {
    this.details = details;
  }

  /**
   * Build up this event role with details.
   *
   *
   * @param details The details.
   * @return this.
   */
  public EventRole details(String details) {
    setDetails(details);
    return this;
  }

  /**
   * Provide a simple toString() method.
   */
  @Override
  public String toString() {
    return (person == null) ? "" : person.toString();
  }

  /**
   * Accept a visitor.
   *
   * @param visitor The visitor.
   */
  public void accept(GedcomxModelVisitor visitor) {
    visitor.visitEventRole(this);
  }
}
