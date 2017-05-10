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
package org.gedcomx.source;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.webcohesion.enunciate.metadata.Facet;
import com.webcohesion.enunciate.metadata.Facets;
import com.webcohesion.enunciate.metadata.qname.XmlQNameEnumRef;
import org.gedcomx.agent.Agent;
import org.gedcomx.common.*;
import org.gedcomx.conclusion.Identifier;
import org.gedcomx.links.HypermediaEnabledData;
import org.gedcomx.links.Link;
import org.gedcomx.records.Field;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.rt.GedcomxModelVisitor;
import org.gedcomx.rt.json.JsonElementWrapper;
import org.gedcomx.types.IdentifierType;
import org.gedcomx.types.ResourceStatusType;
import org.gedcomx.types.ResourceType;

import javax.xml.XMLConstants;
import javax.xml.bind.annotation.*;
import java.util.*;


/**
 * Represents a description of a source.
 */
@XmlRootElement
@XmlType ( name = "SourceDescription", propOrder = {"citations", "mediator", "sources", "analysis", "componentOf", "titles", "titleLabel", "notes", "attribution", "descriptions", "identifiers", "created", "modified", "coverage", "rights", "fields", "repository", "descriptorRef", "replacedBy", "replaces", "statuses"} )
@JsonElementWrapper ( name = "sourceDescriptions" )
@JsonInclude ( JsonInclude.Include.NON_NULL )
public class SourceDescription extends HypermediaEnabledData implements Attributable, HasNotes, ReferencesSources {

  private String lang;
  private List<SourceCitation> citations;
  private String mediaType;
  private URI about;
  private ResourceReference mediator;
  private List<SourceReference> sources;
  private ResourceReference analysis;
  private SourceReference componentOf;
  private List<TextValue> titles;
  private TextValue titleLabel;
  private List<Note> notes;
  private Attribution attribution;
  private URI resourceType;
  private List<URI> rights;
  private String sortKey;
  private List<TextValue> descriptions;
  private List<Identifier> identifiers;
  private Date created;
  private Date modified;
  private List<Coverage> coverage;
  private List<Field> fields;
  private ResourceReference repository;
  private ResourceReference descriptorRef;
  private URI replacedBy;
  private List<URI> replaces;
  private String version;

  /**
   * @see ResourceStatusType
   */
  private List<URI> statuses;

  /**
   * The language of this genealogical data set. See <a href="http://www.w3.org/International/articles/language-tags/">http://www.w3.org/International/articles/language-tags/</a>.
   * Note that some language-enabled elements MAY override the language.
   *
   * @return The language of the genealogical data.
   */
  @XmlAttribute ( namespace = XMLConstants.XML_NS_URI )
  public String getLang() {
    return lang;
  }

  /**
   * The language of this genealogical data set. See <a href="http://www.w3.org/International/articles/language-tags/">http://www.w3.org/International/articles/language-tags/</a>.
   * Note that some language-enabled elements MAY override the language.
   *
   * @param lang The language of this genealogical data.
   */
  public void setLang(String lang) {
    this.lang = lang;
  }

  /**
   * Build out this envelope with a lang.
   *
   * @param lang The lang.
   * @return this.
   */
  public SourceDescription lang(String lang) {
    setLang(lang);
    return this;
  }

  @Override
  public SourceDescription id(String id) {
    return (SourceDescription) super.id(id);
  }

  @Override
  public SourceDescription extensionElement(Object element) {
    return (SourceDescription) super.extensionElement(element);
  }

  @Override
  public SourceDescription link(String rel, URI href) {
    return (SourceDescription) super.link(rel, href);
  }

  @Override
  public SourceDescription link(Link link) {
    return (SourceDescription) super.link(link);
  }

  /**
   * The type of the resource being described.
   *
   * @return The type of the resource being described.
   */
  @XmlAttribute
  @XmlQNameEnumRef ( ResourceType.class )
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
   * Build of this source description with a resource type.
   * @param resourceType The resource type.
   * @return this.
   */
  public SourceDescription resourceType(URI resourceType) {
    setResourceType(resourceType);
    return this;
  }

  /**
   * Build of this source description with a resource type.
   * @param resourceType The resource type.
   * @return this.
   */
  public SourceDescription resourceType(ResourceType resourceType) {
    setKnownType(resourceType);
    return this;
  }

  /**
   * The type of the resource being described.
   *
   * @return The type of the resource being described.
   */
  @XmlTransient
  @JsonIgnore @org.codehaus.jackson.annotate.JsonIgnore
  public ResourceType getKnownType() {
    return getResourceType() == null ? null : ResourceType.fromQNameURI(getResourceType());
  }

  /**
   * The type of the resource being described.
   *
   * @param type The type of the resource being described.
   */
  @JsonIgnore @org.codehaus.jackson.annotate.JsonIgnore
  public void setKnownType(ResourceType type) {
    setResourceType(type == null ? null : type.toQNameURI());
  }

  /**
   * The rights for this source.
   *
   * @return The rights for this source.
   */
  @XmlElement ( name = "rights" )
  @JsonProperty ( "rights" ) @org.codehaus.jackson.annotate.JsonProperty ( "rights" )
  public List<URI> getRights() {
    return rights;
  }

  /**
   * The rights for this source.
   *
   * @param rights The rights for this source.
   */
  @JsonProperty ( "rights" ) @org.codehaus.jackson.annotate.JsonProperty ( "rights" )
  public void setRights(List<URI> rights) {
    this.rights = rights;
  }

  /**
   * Build out this source description with a rights.
   * @param rights The rights.
   * @return this.
   */
  public SourceDescription rights(URI rights) {
    addRights(rights);
    return this;
  }

  /**
   * Add a rights.
   *
   * @param rights The rights to be added.
   */
  public void addRights(URI rights) {
    if (rights != null) {
      if (this.rights == null) {
        this.rights = new LinkedList<URI>();
      }
      this.rights.add(rights);
    }
  }

  /**
   * The preferred bibliographic citation for this source.
   *
   * @return The preferred bibliographic citation for this source.
   */
  @XmlTransient
  @JsonIgnore @org.codehaus.jackson.annotate.JsonIgnore
  public SourceCitation getCitation() {
    return citations == null || citations.isEmpty() ? null : citations.get(0);
  }

  /**
   * The bibliographic citations for this source.
   *
   * @return The bibliographic citations for this source.
   */
  @XmlElement ( name = "citation" )
  @JsonProperty ( "citations" ) @org.codehaus.jackson.annotate.JsonProperty ( "citations" )
  public List<SourceCitation> getCitations() {
    return citations;
  }

  /**
   * The bibliographic citations for this source.
   *
   * @param citations The bibliographic citations for this source.
   */
  @JsonProperty ( "citations" ) @org.codehaus.jackson.annotate.JsonProperty ( "citations" )
  public void setCitations(List<SourceCitation> citations) {
    this.citations = citations;
  }

  /**
   * Build out this source description with a citation.
   * @param citation The citation.
   * @return this.
   */
  public SourceDescription citation(SourceCitation citation) {
    addCitation(citation);
    return this;
  }

  /**
   * Build out this source description with a citation.
   * @param citation The citation.
   * @return this.
   */
  public SourceDescription citation(String citation) {
    addCitation(new SourceCitation().value(citation));
    return this;
  }

  /**
   * Add a citation.
   *
   * @param citation The citation to be added.
   */
  public void addCitation(SourceCitation citation) {
    if (citation != null) {
      if (citations == null) {
        citations = new LinkedList<SourceCitation>();
      }
      citations.add(citation);
    }
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
   * Build out this source description with a media type.
   * @param mediaType The media type.
   * @return this.
   */
  public SourceDescription mediaType(String mediaType) {
    setMediaType(mediaType);
    return this;
  }

  /**
   * The URI (if applicable) of the actual source.
   *
   * @return The URI (if applicable) of the actual source.
   */
  @XmlAttribute
  @XmlSchemaType ( name = "anyURI", namespace = XMLConstants.W3C_XML_SCHEMA_NS_URI )
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
   * Build out this source description with an about reference.
   * @param about the about.
   * @return this.
   */
  public SourceDescription about(URI about) {
    setAbout(about);
    return this;
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
   * Build out this source description with a mediator.
   *
   * @param mediator The mediator.
   * @return this.
   */
  public SourceDescription mediator(ResourceReference mediator) {
    setMediator(mediator);
    return this;
  }

  /**
   * A reference to the entity that mediates access to the described source.
   *
   * @param mediator A reference to the entity that mediates access to the described source.
   */
  @XmlTransient
  @JsonIgnore @org.codehaus.jackson.annotate.JsonIgnore
  public void setMediatorURI(URI mediator) {
    this.mediator = mediator != null ? new ResourceReference(mediator) : null;
  }

  /**
   * References to any sources to which this source is related (usually applicable to sources that are derived from or contained in another source).
   *
   * @return References to any sources to which this source is related (usually applicable to sources that are derived from or contained in another source).
   */
  @XmlElement ( name = "source" )
  @JsonProperty ( "sources" ) @org.codehaus.jackson.annotate.JsonProperty ( "sources" )
  public List<SourceReference> getSources() {
    return sources;
  }

  /**
   * References to any sources to which this source is related (usually applicable to sources that are derived from or contained in another source).
   *
   * @param sources References to any sources to which this source is related (usually applicable to sources that are derived from or contained in another source).
   */
  @JsonProperty ( "sources" ) @org.codehaus.jackson.annotate.JsonProperty ( "sources" )
  public void setSources(List<SourceReference> sources) {
    this.sources = sources;
  }

  /**
   * Build out this source description by adding a source reference.
   * @param source The source reference.
   * @return this.
   */
  public SourceDescription source(SourceReference source) {
    if (this.sources == null) {
      this.sources = new ArrayList<SourceReference>();
    }
    this.sources.add(source);
    return this;
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
   * Build out this source description with an analyis.
   * @param analysis The analysis.
   * @return this.
   */
  public SourceDescription analysis(ResourceReference analysis) {
    setAnalysis(analysis);
    return this;
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
   * Build out this source description with a component of.
   * @param componentOf The component of.
   * @return this.
   */
  public SourceDescription componentOf(SourceReference componentOf) {
    setComponentOf(componentOf);
    return this;
  }

  /**
   * Build out this source description with a component of.
   * @param componentOf The component of.
   * @return this.
   */
  public SourceDescription componentOf(SourceDescription componentOf) {
    setComponentOf(new SourceReference().description(componentOf));
    return this;
  }

  /**
   * The preferred title for this source description.
   *
   * @return The preferred title for this source description.
   */
  @XmlTransient
  @JsonIgnore @org.codehaus.jackson.annotate.JsonIgnore
  public TextValue getTitle() {
    return this.titles == null || this.titles.isEmpty() ? null : this.titles.get(0);
  }

  /**
   * A list of titles for this source.
   *
   * @return A list of titles for this source.
   */
  @XmlElement ( name = "title" )
  @JsonProperty ( "titles" ) @org.codehaus.jackson.annotate.JsonProperty ( "titles" )
  public List<TextValue> getTitles() {
    return titles;
  }

  /**
   * A list of titles for this source.
   *
   * @param titles A list of titles for this source.
   */
  @JsonProperty ( "titles" ) @org.codehaus.jackson.annotate.JsonProperty ( "titles" )
  public void setTitles(List<TextValue> titles) {
    this.titles = titles;
  }

  /**
   * Build out this source description with a title.
   * @param title The title. 
   * @return this.
   */
  public SourceDescription title(TextValue title) {
    if (this.titles == null) {
      this.titles = new ArrayList<TextValue>();
    }

    this.titles.add(title);
    return this;
  }

  /**
   * Build out this source description with a title.
   * @param title The title. 
   * @return this.
   */
  public SourceDescription title(String title) {
    return title(new TextValue(title));
  }

  /**
   * A label for the title of this description.
   *
   * @return A label for the title of this description.
   */
  @Facets ( {@Facet ( GedcomxConstants.FACET_GEDCOMX_RECORD ), @Facet ( GedcomxConstants.FACET_GEDCOMX_RS )} )
  public TextValue getTitleLabel() {
    return titleLabel;
  }

  /**
   * A label for the title of this description.
   *
   * @param titleLabel A label for the title of this description.
   */
  public void setTitleLabel(TextValue titleLabel) {
    this.titleLabel = titleLabel;
  }

  /**
   * Build this out by applying a label for the title of this description.
   * @param titleLabel The title label.
   * @return this.
   */
  public SourceDescription titleLabel(TextValue titleLabel) {
    setTitleLabel(titleLabel);
    return this;
  }

  /**
   * Notes about a source.
   *
   * @return Notes about a source.
   */
  @XmlElement ( name = "note" )
  @JsonProperty ( "notes" ) @org.codehaus.jackson.annotate.JsonProperty ( "notes" )
  public List<Note> getNotes() {
    return notes;
  }

  /**
   * Notes about a source.
   *
   * @param notes Notes about a source.
   */
  @JsonProperty ( "notes" ) @org.codehaus.jackson.annotate.JsonProperty ( "notes" )
  public void setNotes(List<Note> notes) {
    this.notes = notes;
  }

  /**
   * Build out this source description with a note.
   * @param note the note.
   * @return this.
   */
  public SourceDescription note(Note note) {
    if (this.notes == null) {
      this.notes = new ArrayList<Note>();
    }
    this.notes.add(note);
    return this;
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
   * Build out this source description with attribution.
   * @param attribution The attribution.
   * @return this.
   */
  public SourceDescription attribution(Attribution attribution) {
    setAttribution(attribution);
    return this;
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
  @JsonIgnore @org.codehaus.jackson.annotate.JsonIgnore
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
  @JsonIgnore @org.codehaus.jackson.annotate.JsonIgnore
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
   * The URI that this resource has been replaced by.
   * @return What this description is replaced by.
   */
  public URI getReplacedBy() {
    return replacedBy;
  }

  /**
   * The URI that this resource has been replaced by. This resource is some kind of duplicate of that other one.
   *   This happens when a newer or better version is found, and this one is thus deprecated;
   *   or when this resource is merged into another one and this one is thus tombstoned or deleted.
   * @param replacedBy - URI of a resource that replaces this one.
   */
  public void setReplacedBy(URI replacedBy) {
    this.replacedBy = replacedBy;
  }

  /**
   * The list of resources that this resource replaces.
   *
   * @return The list of identifiers for the source.
   */
  @XmlElement ( name = "replaces" )
  @JsonProperty ( "replaces" ) @org.codehaus.jackson.annotate.JsonProperty ( "replaces" )
  public List<URI> getReplaces() {
    return replaces;
  }

  /**
   * The list of resources that this resource replaces.
   *
   * @param replaces The list of identifiers of the source.
   */
  @JsonProperty ( "replaces" ) @org.codehaus.jackson.annotate.JsonProperty ( "replaces" )
  public void setReplaces(List<URI> replaces) {
    this.replaces = replaces;
  }

  /**
   * The list of status types for the source.
   *
   * @return The list of status types for the source.
   */
  @XmlElement ( name = "status" )
  @JsonProperty ( "statuses" ) @org.codehaus.jackson.annotate.JsonProperty ( "statuses" )
  public List<URI> getStatuses() {
    return statuses;
  }

  /**
   * The list of status types for the source.
   *
   * @param statuses The list of identifiers of the source.
   */
  @JsonProperty ( "statuses" ) @org.codehaus.jackson.annotate.JsonProperty ( "statuses" )
  public void setStatuses(List<URI> statuses) {
    this.statuses = statuses;
  }

  /**
   * Add the given ResourceStatusType's URI to the list of statuses.
   * @param status - ResourceStatusType to add to the statuses list.
   */
  public void addKnownStatus(ResourceStatusType status) {
    if (status != null) {
      addStatus(status.toQNameURI());
    }
  }

  /**
   * Add the given resource status type URI to the list of statuses.
   * @param status - URI to add to the list of statuses.
   */
  public void addStatus(URI status) {
    if (status != null) {
      if (statuses == null) {
        statuses = new ArrayList<URI>();
      }
      if (!statuses.contains(status)) {
        statuses.add(status);
      }
    }
  }

  /**
   * The list of identifiers for the source.
   *
   * @return The list of identifiers for the source.
   */
  @XmlElement ( name = "identifier" )
  @JsonProperty ( "identifiers" ) @org.codehaus.jackson.annotate.JsonProperty ( "identifiers" )
  public List<Identifier> getIdentifiers() {
    return identifiers;
  }

  /**
   * The list of identifiers of the source.
   *
   * @param identifiers The list of identifiers of the source.
   */
  @JsonProperty ( "identifiers" ) @org.codehaus.jackson.annotate.JsonProperty ( "identifiers" )
  public void setIdentifiers(List<Identifier> identifiers) {
    this.identifiers = identifiers;
  }

  /**
   * Build out this source description with an identifier.
   * @param identifier the identifier.
   * @return this.
   */
  public SourceDescription identifier(Identifier identifier) {
    if (this.identifiers == null) {
      this.identifiers = new ArrayList<Identifier>();
    }
    this.identifiers.add(identifier);
    return this;
  }

  /**
   * A sort key to be used in determining the position of this source relative to other sources in the same collection.
   *
   * @return A sort key to be used in determining the position of this source relative to other sources in the same collection.
   */
  @Facet ( GedcomxConstants.FACET_GEDCOMX_RECORD )
  @XmlAttribute
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
   * Build out this source description with a sort key.
   * @param sortKey The sort key.
   * @return This.
   */
  public SourceDescription sortKey(String sortKey) {
    setSortKey(sortKey);
    return this;
  }

  /**
   * Human-readable descriptions of the source.
   *
   * @return Human-readable descriptions of the source.
   */
  @XmlElement ( name = "description" )
  @JsonProperty ( "descriptions" ) @org.codehaus.jackson.annotate.JsonProperty ( "descriptions" )
  @Facet ( GedcomxConstants.FACET_GEDCOMX_RECORD )
  public List<TextValue> getDescriptions() {
    return descriptions;
  }

  /**
   * Human-readable descriptions of the source.
   *
   * @param descriptions Human-readable descriptions of the source.
   */
  public void setDescriptions(List<TextValue> descriptions) {
    this.descriptions = descriptions;
  }

  /**
   * Build out this source description with a description.
   * @param description The description. 
   * @return this.
   */
  public SourceDescription description(TextValue description) {
    if (this.descriptions == null) {
      this.descriptions = new ArrayList<TextValue>();
    }

    this.descriptions.add(description);
    return this;
  }

  /**
   * Build out this source description with a description.
   * @param description The description. 
   * @return this.
   */
  public SourceDescription description(String description) {
    return description(new TextValue(description));
  }

  /**
   * The date the source was created.
   *
   * @return The date the source was created.
   */
  @Facet ( GedcomxConstants.FACET_GEDCOMX_RECORD )
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
   * Build out this source description with a created date.
   *
   * @param created The created date.
   * @return this.
   */
  public SourceDescription created(Date created) {
    setCreated(created);
    return this;
  }

  /**
   * The date the source was last modified.
   *
   * @return The date the source was last modified.
   */
  @Facet ( GedcomxConstants.FACET_GEDCOMX_RECORD )
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
   * Build out this source description with a modified date.
   * @param modified the modified date.
   * @return this
   */
  public SourceDescription modified(Date modified) {
    setModified(modified);
    return this;
  }

  /**
   * Declarations of the coverage of the source.
   *
   * @return Declarations of the coverage of the source.
   */
  @Facet ( GedcomxConstants.FACET_GEDCOMX_RECORD )
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
   * Build out this source description with coverage.
   * @param coverage The coverage.
   * @return this.
   */
  public SourceDescription coverage(Coverage coverage) {
    if (this.coverage == null) {
      this.coverage = new ArrayList<Coverage>();
    }

    this.coverage.add(coverage);
    return this;
  }

  /**
   * The fields that are applicable to the resource being described.
   *
   * @return The fields that are applicable to the resource being described.
   */
  @XmlElement ( name = "field" )
  @JsonProperty ( "fields" ) @org.codehaus.jackson.annotate.JsonProperty ( "fields" )
  @com.webcohesion.enunciate.metadata.Facet ( GedcomxConstants.FACET_GEDCOMX_RECORD )
  public List<Field> getFields() {
    return fields;
  }

  /**
   * The fields that are applicable to the resource being described.
   *
   * @param fields The fields that are applicable to the resource being described.
   */
  @JsonProperty ( "fields" ) @org.codehaus.jackson.annotate.JsonProperty ( "fields" )
  public void setFields(List<Field> fields) {
    this.fields = fields;
  }

  /**
   * Build this out with a field.
   * @param field The field.
   * @return this.
   */
  public SourceDescription field(Field field) {
    addField(field);
    return this;
  }

  /**
   * Add a field to the source description.
   *
   * @param field The field to be added.
   */
  public void addField(Field field) {
    if (field != null) {
      if (fields == null) {
        fields = new LinkedList<Field>();
      }
      fields.add(field);
    }
  }

  /**
   * Reference to an agent describing the repository in which the source is found.
   *
   * @return Reference to an agent describing the repository in which the source is found.
   */
  @Facet ( GedcomxConstants.FACET_GEDCOMX_RECORD )
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
   * Build out this source description with a repository.
   *
   * @param repository The repository.
   * @return this.
   */
  public SourceDescription repository(Agent repository) {
    return repository(new ResourceReference(URI.create("#" + repository.getId())));
  }

  /**
   * Build out this source description with a repository.
   *
   * @param repository The repository.
   * @return this.
   */
  public SourceDescription repository(ResourceReference repository) {
    setRepository(repository);
    return this;
  }

  /**
   * Reference to a descriptor of fields and type of data that can be expected to be extracted from the source.
   *
   * @return Reference to a descriptor of fields and type of data that can be expected to be extracted from the source.
   */
  @XmlElement ( name = "descriptor" )
  @JsonProperty ( "descriptor" ) @org.codehaus.jackson.annotate.JsonProperty ( "descriptor" )
  @Facet ( GedcomxConstants.FACET_GEDCOMX_RECORD )
  public ResourceReference getDescriptorRef() {
    return descriptorRef;
  }

  /**
   * Reference to a descriptor of fields and type of data that can be expected to be extracted from the source.
   *
   * @param descriptorRef Reference to a descriptor of fields and type of data that can be expected to be extracted from the source.
   */
  @JsonProperty ( "descriptor" ) @org.codehaus.jackson.annotate.JsonProperty ( "descriptor" )
  public void setDescriptorRef(ResourceReference descriptorRef) {
    this.descriptorRef = descriptorRef;
  }

  /**
   * Build out this source description with a descriptor ref.
   *
   * @param descriptorRef The descriptor ref.
   * @return this.
   */
  public SourceDescription descriptorRef(ResourceReference descriptorRef) {
    setDescriptorRef(descriptorRef);
    return this;
  }

  /**
   * gets the version of this resource
   *
   * @return The version of this resource
   */
  @XmlAttribute
  public String getVersion() {
    return version;
  }

  /**
   * sets the version of this resource
   *
   * @param version the version of this resource
   */
  public void setVersion(String version) {
    this.version = version;
  }

  /**
   * Build out this source description with a version.
   * @param version the version of this resource
   * @return this.
   */
  public SourceDescription version(String version) {
    setVersion(version);
    return this;
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
    if (description.descriptions != null) {
      this.descriptions = this.descriptions == null ? new ArrayList<TextValue>() : this.descriptions;
      this.descriptions.addAll(description.descriptions);
    }
    if (description.rights != null) {
      this.rights = this.rights == null ? new ArrayList<URI>() : this.rights;
      this.rights.addAll(description.rights);
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
    if (description.fields != null) {
      this.fields = this.fields == null ? new ArrayList<Field>() : this.fields;
      this.fields.addAll(description.fields);
    }
    this.repository = this.repository == null ? description.repository : this.repository;
    this.descriptorRef = this.descriptorRef == null ? description.descriptorRef : this.descriptorRef;

    super.embed(description);
  }

  /**
   * Provide a simple toString() method.
   */
  @Override
  public String toString() {
    return getId() + ": " + this.resourceType;
  }
}
