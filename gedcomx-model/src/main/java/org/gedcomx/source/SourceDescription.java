/**
 * Copyright 2011-2012 Intellectual Reserve, Inc.
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

import org.codehaus.enunciate.json.JsonName;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.gedcomx.common.*;
import org.gedcomx.links.HypermediaEnabledData;
import org.gedcomx.rt.GedcomxModelVisitor;
import org.gedcomx.rt.json.JsonElementWrapper;
import org.gedcomx.types.ResourceType;

import javax.xml.XMLConstants;
import javax.xml.bind.annotation.*;
import java.util.List;


/**
 * Represents a description of a source.
 */
@XmlRootElement
@XmlType ( name = "SourceDescription", propOrder = { "citations", "mediator", "sources", "analysis", "componentOf", "titles", "notes", "attribution" } )
@JsonElementWrapper ( name = "sourceDescriptions" )
public class SourceDescription extends HypermediaEnabledData implements Attributable, HasNotes, ReferencesSources {

  private String id;
  private List<SourceCitation> citations;
  private String mediaType;
  private URI about;
  private ResourceReference mediator;
  private List<SourceReference> sources;
  private ResourceReference analysis;
  private SourceReference componentOf;
  private List<TextValue> titles;
  private List<Note> notes;
  private Attribution attribution;
  private URI resourceType;

  /**
   * A local, context-specific id for the data.
   *
   * @return A local, context-specific id for the data.
   */
  @XmlID
  @XmlAttribute
  public String getId() {
    return id;
  }

  /**
   * A local, context-specific id for the data.
   * @param id A local, context-specific id for the data.
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * The type of the resource being described.
   *
   * @return The type of the resource being described.
   */
  @XmlAttribute
  public URI getResourceType() {
    return resourceType;
  }

  /**
   * The type of the resource being described.
   *
   * @param resourceType The type of the resource being described.
   */
  public void setResourceType(URI resourceType) {
    this.resourceType = resourceType;
  }

  /**
   * The type of the resource being described.
   *
   * @return The type of the resource being described.
   */
  @XmlTransient
  @JsonIgnore
  public ResourceType getKnownType() {
    return getResourceType() == null ? null : ResourceType.fromQNameURI(getResourceType());
  }

  /**
   * The type of the resource being described.
   *
   * @param type The type of the resource being described.
   */
  @JsonIgnore
  public void setKnownType(ResourceType type) {
    setResourceType(type == null ? null : URI.create(org.codehaus.enunciate.XmlQNameEnumUtil.toURIValue(type)));
  }

  /**
   * The preferred bibliographic citation for this source.
   *
   * @return The preferred bibliographic citation for this source.
   */
  @XmlTransient
  @JsonIgnore
  public SourceCitation getCitation() {
    return citations == null || citations.isEmpty() ? null : citations.get(0);
  }

  /**
   * The bibliographic citations for this source.
   *
   * @return The bibliographic citations for this source.
   */
  @XmlElement (name="citation")
  @JsonProperty ("citations")
  @JsonName ("citations")
  public List<SourceCitation> getCitations() {
    return citations;
  }

  /**
   * Hint about the media (MIME) type of the resource being described.
   *
   * @return Hint about the media (MIME) type of the resource being described.
   */
  @XmlAttribute
  public String getMediaType() {
    return mediaType;
  }

  /**
   * Hint about the media (MIME) type of the resource being described.
   *
   * @param mediaType Hint about the media (MIME) type of the resource being described.
   */
  public void setMediaType(String mediaType) {
    this.mediaType = mediaType;
  }

  /**
   * The bibliographic citations for this source.
   *
   * @param citations The bibliographic citations for this source.
   */
  @JsonProperty ("citations")
  public void setCitations(List<SourceCitation> citations) {
    this.citations = citations;
  }

  /**
   * The URI (if applicable) of the actual source.
   *
   * @return The URI (if applicable) of the actual source.
   */
  @XmlAttribute
  @XmlSchemaType (name = "anyURI", namespace = XMLConstants.W3C_XML_SCHEMA_NS_URI)
  public URI getAbout() {
    return about;
  }

  /**
   * The URI (if applicable) of the actual source.
   *
   * @param about The URI (if applicable) of the actual source.
   */
  public void setAbout(URI about) {
    this.about = about;
  }

  /**
   * A reference to the entity that mediates access to the described source.
   *
   * @return A reference to the entity that mediates access to the described source.
   */
  public ResourceReference getMediator() {
    return mediator;
  }

  /**
   * A reference to the entity that mediates access to the described source.
   *
   * @param mediator A reference to the entity that mediates access to the described source.
   */
  public void setMediator(ResourceReference mediator) {
    this.mediator = mediator;
  }

  /**
   * A reference to the entity that mediates access to the described source.
   *
   * @param mediator A reference to the entity that mediates access to the described source.
   */
  @XmlTransient
  @JsonIgnore
  public void setMediatorURI(URI mediator) {
    this.mediator = mediator != null ? new ResourceReference(mediator) : null;
  }

  /**
   * References to any sources to which this source is related (usually applicable to sources that are derived from or contained in another source).
   *
   * @return References to any sources to which this source is related (usually applicable to sources that are derived from or contained in another source).
   */
  @XmlElement (name="source")
  @JsonProperty ("sources")
  @JsonName ("sources")
  public List<SourceReference> getSources() {
    return sources;
  }

  /**
   * References to any sources to which this source is related (usually applicable to sources that are derived from or contained in another source).
   *
   * @param sources References to any sources to which this source is related (usually applicable to sources that are derived from or contained in another source).
   */
  @JsonProperty ("sources")
  public void setSources(List<SourceReference> sources) {
    this.sources = sources;
  }

  /**
   * A reference to the analysis document explaining the analysis that went into this description of the source.
   *
   * @return A reference to the analysis document explaining the analysis that went into this description of the source.
   */
  public ResourceReference getAnalysis() {
    return analysis;
  }

  /**
   * A reference to the analysis document explaining the analysis that went into this description of the source.
   *
   * @param analysis A reference to the analysis document explaining the analysis that went into this description of the source.
   */
  public void setAnalysis(ResourceReference analysis) {
    this.analysis = analysis;
  }

  /**
   * A reference to the source that contains this source.
   *
   * @return A reference to the source that contains this source.
   */
  public SourceReference getComponentOf() {
    return componentOf;
  }

  /**
   * A reference to the source that contains this source.
   *
   * @param componentOf A reference to the source that contains this source.
   */
  public void setComponentOf(SourceReference componentOf) {
    this.componentOf = componentOf;
  }

  /**
   * The preferred title for this source description.
   *
   * @return The preferred title for this source description.
   */
  @XmlTransient
  @JsonIgnore
  public TextValue getTitle() {
    return this.titles == null || this.titles.isEmpty() ? null : this.titles.get(0);
  }

  /**
   * A list of titles for this source.
   *
   * @return A list of titles for this source.
   */
  @XmlElement (name="title")
  @JsonProperty ("titles")
  @JsonName ("titles")
  public List<TextValue> getTitles() {
    return titles;
  }

  /**
   * A list of titles for this source.
   *
   * @param titles A list of titles for this source.
   */
  @JsonProperty ("titles")
  public void setTitles(List<TextValue> titles) {
    this.titles = titles;
  }

  /**
   * Notes about a source.
   *
   * @return Notes about a source.
   */
  @XmlElement (name="note")
  @JsonProperty ("notes")
  @JsonName ("notes")
  public List<Note> getNotes() {
    return notes;
  }

  /**
   * Notes about a source.
   *
   * @param notes Notes about a source.
   */
  @JsonProperty ("notes")
  public void setNotes(List<Note> notes) {
    this.notes = notes;
  }

  /**
   * The attribution metadata for this source description.
   *
   * @return The attribution metadata for this source description.
   */
  public Attribution getAttribution() {
    return attribution;
  }

  /**
   * The attribution metadata for this source description.
   *
   * @param attribution The attribution metadata for this source description.
   */
  public void setAttribution(Attribution attribution) {
    this.attribution = attribution;
  }

  /**
   * Accept a visitor.
   *
   * @param visitor The visitor.
   */
  public void accept(GedcomxModelVisitor visitor) {
    visitor.visitSourceDescription(this);
  }
}
