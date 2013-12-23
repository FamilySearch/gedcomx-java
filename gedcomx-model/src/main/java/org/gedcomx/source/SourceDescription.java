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

import org.codehaus.enunciate.Facet;
import org.codehaus.enunciate.json.JsonName;
import org.codehaus.enunciate.qname.XmlQNameEnumRef;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.gedcomx.common.*;
import org.gedcomx.conclusion.Identifier;
import org.gedcomx.links.HypermediaEnabledData;
import org.gedcomx.records.FieldValue;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.rt.GedcomxModelVisitor;
import org.gedcomx.rt.json.JsonElementWrapper;
import org.gedcomx.types.IdentifierType;
import org.gedcomx.types.ResourceType;

import javax.xml.XMLConstants;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


/**
 * Represents a description of a source.
 */
@XmlRootElement
@XmlType ( name = "SourceDescription", propOrder = { "citations", "mediator", "sources", "analysis", "componentOf", "titles", "notes", "attribution", "sortKey", "description", "identifiers", "created", "modified", "coverage", "facets", "repository", "descriptorRef" } )
@JsonElementWrapper ( name = "sourceDescriptions" )
public class SourceDescription extends HypermediaEnabledData implements Attributable, HasNotes, ReferencesSources {

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
  private String sortKey;
  private List<TextValue> description;
  private List<Identifier> identifiers;
  private Date created;
  private Date modified;
  private List<Coverage> coverage;
  private List<FieldValue> facets; //todo: facets need to refer to their facet descriptors?
  private ResourceReference repository;
  private ResourceReference descriptorRef;

  /**
   * The type of the resource being described.
   *
   * @return The type of the resource being described.
   */
  @XmlAttribute
  @XmlQNameEnumRef (ResourceType.class)
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

  /**
   * Find the long-term, persistent identifier for this source from the list of identifiers.
   *
   * @return The long-term, persistent identifier for this source.
   */
  @XmlTransient
  @JsonIgnore
  public URI getPersistentId() {
    URI identifier = null;
    if (this.identifiers != null) {
      for (Identifier id : this.identifiers) {
        if (IdentifierType.Persistent.equals(id.getKnownType())) {
          identifier = id.getValue();
          break;
        }
      }
    }
    return identifier;
  }

  /**
   * A long-term, persistent, globally unique identifier for this source.
   *
   * @param persistentId A long-term, persistent, globally unique identifier for this source.
   */
  @JsonIgnore
  public void setPersistentId(URI persistentId) {
    if (this.identifiers == null) {
      this.identifiers = new ArrayList<Identifier>();
    }

    //clear out any other primary ids.
    Iterator<Identifier> it = this.identifiers.iterator();
    while (it.hasNext()) {
      if (IdentifierType.Persistent.equals(it.next().getKnownType())) {
        it.remove();
      }
    }

    Identifier identifier = new Identifier();
    identifier.setKnownType(IdentifierType.Persistent);
    identifier.setValue(persistentId);
    this.identifiers.add(identifier);
  }

  /**
   * The list of identifiers for the source.
   *
   * @return The list of identifiers for the source.
   */
  @XmlElement (name="identifier")
  @JsonProperty ("identifiers")
  @JsonName ("identifiers")
  public List<Identifier> getIdentifiers() {
    return identifiers;
  }

  /**
   * The list of identifiers of the source.
   *
   * @param identifiers The list of identifiers of the source.
   */
  @JsonProperty ("identifiers")
  public void setIdentifiers(List<Identifier> identifiers) {
    this.identifiers = identifiers;
  }

  /**
   * A sort key to be used in determining the position of this source relative to other sources in the same collection.
   * 
   * @return A sort key to be used in determining the position of this source relative to other sources in the same collection.
   */
  @Facet( name = GedcomxConstants.FACET_GEDCOMX_RECORD)
  public String getSortKey() {
    return sortKey;
  }

  /**
   * A sort key to be used in determining the position of this source relative to other sources in the same collection.
   * 
   * @param sortKey A sort key to be used in determining the position of this source relative to other sources in the same collection.
   */
  public void setSortKey(String sortKey) {
    this.sortKey = sortKey;
  }

  /**
   * Human-readable descriptions of the source.
   * 
   * @return Human-readable descriptions of the source.
   */
  @Facet( name = GedcomxConstants.FACET_GEDCOMX_RECORD)
  public List<TextValue> getDescription() {
    return description;
  }

  /**
   * Human-readable descriptions of the source.
   * 
   * @param description Human-readable descriptions of the source.
   */
  public void setDescription(List<TextValue> description) {
    this.description = description;
  }

  /**
   * The date the source was created.
   * 
   * @return The date the source was created.
   */
  @Facet( name = GedcomxConstants.FACET_GEDCOMX_RECORD)
  public Date getCreated() {
    return created;
  }

  /**
   * The date the source was created.
   * 
   * @param created The date the source was created.
   */
  public void setCreated(Date created) {
    this.created = created;
  }

  /**
   * The date the source was last modified.
   * 
   * @return The date the source was last modified.
   */
  @Facet( name = GedcomxConstants.FACET_GEDCOMX_RECORD)
  public Date getModified() {
    return modified;
  }

  /**
   * The date the source was last modified.
   * 
   * @param modified The date the source was last modified.
   */
  public void setModified(Date modified) {
    this.modified = modified;
  }

  /**
   * Declarations of the coverage of the source.
   *
   * @return Declarations of the coverage of the source.
   */
  @Facet( name = GedcomxConstants.FACET_GEDCOMX_RECORD)
  public List<Coverage> getCoverage() {
    return this.coverage;
  }

  /**
   * Declarations of the coverage of the source.
   *
   * @param coverage Declarations of the coverage of the source.
   */
  public void setCoverage(List<Coverage> coverage) {
    this.coverage = coverage;
  }

  /**
   * The facets of the resource.
   *
   * @return The facets of the resource.
   */
  @Facet( name = GedcomxConstants.FACET_GEDCOMX_RECORD)
  public List<FieldValue> getFacets() {
    return this.facets;
  }

  /**
   * The facets of the resource.
   *
   * @param facets The facets of the resource.
   */
  public void setFacets(List<FieldValue> facets) {
    this.facets = facets;
  }

  /**
   * Reference to an agent describing the repository in which the source is found.
   *
   * @return Reference to an agent describing the repository in which the source is found.
   */
  @Facet( name = GedcomxConstants.FACET_GEDCOMX_RECORD)
  public ResourceReference getRepository() {
    return repository;
  }

  /**
   * Reference to an agent describing the repository in which the source is found.
   *
   * @param repository Reference to an agent describing the repository in which the source is found.
   */
  public void setRepository(ResourceReference repository) {
    this.repository = repository;
  }

  /**
   * Reference to a descriptor of fields and type of data that can be expected to be extracted from the source.
   *
   * @return Reference to a descriptor of fields and type of data that can be expected to be extracted from the source.
   */
  @XmlElement (name="descriptor")
  @JsonProperty ("descriptor")
  @JsonName ("descriptor")
  @Facet( name = GedcomxConstants.FACET_GEDCOMX_RECORD)
  public ResourceReference getDescriptorRef() {
    return descriptorRef;
  }

  /**
   * Reference to a descriptor of fields and type of data that can be expected to be extracted from the source.
   *
   * @param descriptorRef Reference to a descriptor of fields and type of data that can be expected to be extracted from the source.
   */
  @JsonProperty ("descriptor")
  public void setDescriptorRef(ResourceReference descriptorRef) {
    this.descriptorRef = descriptorRef;
  }

  /**
   * Embed the specified description.
   *
   * @param description The description to embed.
   */
  public void embed(SourceDescription description) {
    if (description.citations != null) {
      this.citations = this.citations == null ? new ArrayList<SourceCitation>() : this.citations;
      this.citations.addAll(description.citations);
    }

    this.mediaType = this.mediaType == null ? description.mediaType : this.mediaType;
    this.about = this.about == null ? description.about : this.about;
    this.mediator = this.mediator == null ? description.mediator : this.mediator;

    if (description.sources != null) {
      this.sources = this.sources == null ? new ArrayList<SourceReference>() : this.sources;
      this.sources.addAll(description.sources);
    }

    this.analysis = this.analysis == null ? description.analysis : this.analysis;
    this.componentOf = this.componentOf == null ? description.componentOf : this.componentOf;
    if (description.titles != null) {
      this.titles = this.titles == null ? new ArrayList<TextValue>() : this.titles;
      this.titles.addAll(description.titles);
    }
    if (description.notes != null) {
      this.notes = this.notes == null ? new ArrayList<Note>() : this.notes;
      this.notes.addAll(description.notes);
    }
    this.attribution = this.attribution == null ? description.attribution : this.attribution;
    this.resourceType = this.resourceType == null ? description.resourceType : this.resourceType;
    this.sortKey = this.sortKey == null ? description.sortKey : this.sortKey;
    if (description.description != null) {
      this.description = this.description == null ? new ArrayList<TextValue>() : this.description;
      this.description.addAll(description.description);
    }
    if (description.identifiers != null) {
      this.identifiers = this.identifiers == null ? new ArrayList<Identifier>() : this.identifiers;
      this.identifiers.addAll(description.identifiers);
    }
    this.created = this.created == null ? description.created : this.created;
    this.modified = this.modified == null ? description.modified : this.modified;
    this.modified = this.modified == null ? description.modified : this.modified;
    if (description.coverage != null) {
      this.coverage = this.coverage == null ? new ArrayList<Coverage>() : this.coverage;
      this.coverage.addAll(description.coverage);
    }
    if (description.facets != null) {
      this.facets = this.facets == null ? new ArrayList<FieldValue>() : this.facets;
      this.facets.addAll(description.facets);
    }
    this.repository = this.repository == null ? description.repository : this.repository;
    this.descriptorRef = this.descriptorRef == null ? description.descriptorRef : this.descriptorRef;

    super.embed(description);
  }
}
