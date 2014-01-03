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
import org.codehaus.enunciate.json.JsonName;
import org.codehaus.enunciate.qname.XmlQNameEnumRef;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.gedcomx.common.*;
import org.gedcomx.links.HypermediaEnabledData;
import org.gedcomx.links.Link;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.source.ReferencesSources;
import org.gedcomx.source.SourceDescription;
import org.gedcomx.source.SourceReference;
import org.gedcomx.types.ConfidenceLevel;

import javax.xml.XMLConstants;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * A genealogical conclusion.
 *
 * @author Ryan Heaton
 */
@XmlType ( name = "Conclusion", propOrder = { "attribution", "sources", "analysis", "notes" })
public abstract class Conclusion extends HypermediaEnabledData implements Attributable, ReferencesSources, HasNotes {

  private String lang;
  private URI confidence;
  private List<SourceReference> sources;
  private List<Note> notes;
  private Attribution attribution;
  private ResourceReference analysis;

  @Override
  public Conclusion id(String id) {
    return (Conclusion) super.id(id);
  }

  @Override
  public Conclusion link(String rel, URI href) {
    return (Conclusion) super.link(rel, href);
  }

  @Override
  public Conclusion link(Link link) {
    return (Conclusion) super.link(link);
  }

  /**
   * The language of the conclusion. See <a href="http://www.w3.org/International/articles/language-tags/">http://www.w3.org/International/articles/language-tags/</a>
   *
   * @return The language of the conclusion.
   */
  @XmlAttribute ( namespace = XMLConstants.XML_NS_URI )
  @Facet ( name = GedcomxConstants.FACET_FS_FT_READ_ONLY )
  public String getLang() {
    return lang;
  }

  /**
   * The language of the conclusion. See <a href="http://www.w3.org/International/articles/language-tags/">http://www.w3.org/International/articles/language-tags/</a>
   *
   * @param lang The language of the conclusion.
   */
  public void setLang(String lang) {
    this.lang = lang;
  }

  /**
   * Build up this conclusion with a lang.
   *
   * @param lang The lang.
   * @return this.
   */
  public Conclusion lang(String lang) {
    this.lang = lang;
    return this;
  }

  /**
   * The level of confidence the contributor has about the data.
   *
   * @return The level of confidence the contributor has about the data.
   */
  @XmlAttribute
  @XmlQNameEnumRef(ConfidenceLevel.class)
  public URI getConfidence() {
    return confidence;
  }

  /**
   * The level of confidence the contributor has about the data.
   *
   * @param confidence The level of confidence the contributor has about the data.
   */
  public void setConfidence(URI confidence) {
    this.confidence = confidence;
  }

  /**
   * Build up this conclusion with a confidence level.
   *
   * @param confidence The confidence level.
   * @return this.
   */
  public Conclusion confidence(URI confidence) {
    setConfidence(confidence);
    return this;
  }

  /**
   * Build up this conclusion with a confidence level.
   *
   * @param confidence The confidence level.
   * @return this.
   */
  public Conclusion confidence(ConfidenceLevel confidence) {
    setKnownConfidenceLevel(confidence);
    return this;
  }

  /**
   * The value of a the known confidence level, or {@link org.gedcomx.types.ConfidenceLevel#OTHER} if not known.
   *
   * @return The value of a the known confidence level, or {@link org.gedcomx.types.ConfidenceLevel#OTHER} if not known.
   */
  @XmlTransient
  @JsonIgnore
  public ConfidenceLevel getKnownConfidenceLevel() {
    return getConfidence() == null ? null : ConfidenceLevel.fromQNameURI(getConfidence());
  }

  /**
   * Set the confidence level from a known enumeration of confidence levels.
   *
   * @param level The known level.
   */
  @JsonIgnore
  public void setKnownConfidenceLevel(ConfidenceLevel level) {
    setConfidence(level == null ? null : URI.create(org.codehaus.enunciate.XmlQNameEnumUtil.toURIValue(level)));
  }

  /**
   * The source references for a conclusion.
   *
   * @return The source references for a conclusion.
   */
  @XmlElement (name="source")
  @JsonProperty ("sources")
  @JsonName ("sources")
  public List<SourceReference> getSources() {
    return sources;
  }

  /**
   * The source references for a conclusion.
   *
   * @param sourceReferences The source references for a conclusion.
   */
  @JsonProperty("sources")
  public void setSources(List<SourceReference> sourceReferences) {
    this.sources = sourceReferences;
  }

  /**
   * Build up this conclusion with a source reference.
   *
   * @param sourceReference The source reference.
   * @return this.
   */
  public Conclusion source(SourceReference sourceReference) {
    addSource(sourceReference);
    return this;
  }

  /**
   * Build up this conclusion with a source reference.
   *
   * @param source The source description being referenced.
   * @return this.
   */
  public Conclusion source(SourceDescription source) {
    return source(new SourceReference().description(source));
  }

  /**
   * Add a source reference.
   *
   * @param source The source reference to be added.
   */
  public void addSource(SourceReference source) {
    if (source != null) {
      if (sources == null) {
        sources = new ArrayList<SourceReference>();
      }
      sources.add(source);
    }
  }

  /**
   * Notes about a person.
   *
   * @return Notes about a person.
   */
  @XmlElement (name = "note")
  @JsonProperty ("notes")
  @JsonName ("notes")
  public List<Note> getNotes() {
    return notes;
  }

  /**
   * Notes about a person.
   *
   * @param notes Notes about a person.
   */
  @JsonProperty ("notes")
  public void setNotes(List<Note> notes) {
    this.notes = notes;
  }

  /**
   * Build up this conclusion with a note.
   *
   * @param note The note.
   * @return this.
   */
  public Conclusion note(Note note) {
    addNote(note);
    return this;
  }

  /**
   * Add a note.
   *
   * @param note The note to be added.
   */
  public void addNote(Note note) {
    if (note != null) {
      if (notes == null) {
        notes = new ArrayList<Note>();
      }
      notes.add(note);
    }
  }

  /**
   * Attribution metadata for a conclusion.
   *
   * @return Attribution metadata for a conclusion.
   */
  @Override
  public Attribution getAttribution() {
    return attribution;
  }

  /**
   * Attribution metadata for a conclusion.
   *
   * @param attribution Attribution metadata for a conclusion.
   */
  @Override
  public void setAttribution(Attribution attribution) {
    this.attribution = attribution;
  }

  /**
   * Build up this conclusion with attribution.
   *
   * @param attribution The attribution.
   * @return this.
   */
  public Conclusion attribution(Attribution attribution) {
    setAttribution(attribution);
    return this;
  }

  /**
   * A reference to the analysis document explaining the analysis that went into this conclusion.
   *
   * @return A reference to the analysis document explaining the analysis that went into this conclusion.
   */
  @Facet( name = GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  public ResourceReference getAnalysis() {
    return analysis;
  }

  /**
   * A reference to the analysis document explaining the analysis that went into this conclusion.
   *
   * @param analysis A reference to the analysis document explaining the analysis that went into this conclusion.
   */
  public void setAnalysis(ResourceReference analysis) {
    this.analysis = analysis;
  }

  /**
   * Add a reference to the analysis for this conclusion.
   *
   * @param analysis The analysis.
   * @return this.
   */
  public Conclusion analysis(ResourceReference analysis) {
    setAnalysis(analysis);
    return this;
  }

  /**
   * Add a reference to the analysis for this conclusion.
   *
   * @param analysis The analysis.
   * @return this.
   */
  public Conclusion analysis(URI analysis) {
    return analysis(new ResourceReference(analysis));
  }

  /**
   * Add a reference to the analysis for this conclusion.
   *
   * @param analysis The analysis.
   * @return this.
   */
  public Conclusion analysis(Document analysis) {
    if (analysis.getId() == null) {
      throw new IllegalArgumentException("Cannot reference analysis: no id.");
    }

    return analysis(URI.create("#" + analysis.getId()));
  }

  /**
   * Provide a simple toString() method.
   */
  @Override
  public String toString() {
    return (getId() == null) ? "" : getId();
  }

  protected void embed(Conclusion conclusion) {
    this.lang = this.lang == null ? conclusion.lang : this.lang;
    this.confidence = this.confidence == null ? conclusion.confidence : this.confidence;
    this.attribution = this.attribution == null ? conclusion.attribution : this.attribution;
    this.analysis = this.analysis == null ? conclusion.analysis : this.analysis;
    if (conclusion.notes != null) {
      this.notes = this.notes == null ? new ArrayList<Note>() : this.notes;
      this.notes.addAll(conclusion.notes);
    }
    if (conclusion.sources != null) {
      this.sources = this.sources == null ? new ArrayList<SourceReference>() : this.sources;
      this.sources.addAll(conclusion.sources);
    }
    super.embed(conclusion);
  }
}
