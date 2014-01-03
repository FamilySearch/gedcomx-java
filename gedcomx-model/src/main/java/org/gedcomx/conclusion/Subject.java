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
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.gedcomx.common.*;
import org.gedcomx.links.Link;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.source.SourceDescription;
import org.gedcomx.source.SourceReference;
import org.gedcomx.types.ConfidenceLevel;
import org.gedcomx.types.IdentifierType;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * The <tt>Subject</tt> data type defines the abstract concept of a genealogical <em>subject</em>. A <em>subject</em> is something with a unique and
 * intrinsic identity, e.g., a person, a location on the surface of the earth. We identify that <em>subject</em> in time and space using various supporting
 * <em>conclusions</em>, e.g. for a person: things like name, birth date, age, address, etc. We aggregate these supporting <em>conclusions</em> to form an
 * apparently-unique identity by which we can distinguish our <em>subject</em> from all other possible <em>subjects</em>.
 *
 * @author Ryan Heaton
 */
@XmlType ( name = "Subject", propOrder = { "evidence", "media", "identifiers" })
public abstract class Subject extends Conclusion implements Attributable {

  private Boolean extracted;
  private List<Identifier> identifiers;
  private List<SourceReference> media;
  private List<EvidenceReference> evidence;

  @Override
  public Subject id(String id) {
    return (Subject) super.id(id);
  }

  @Override
  public Subject link(String rel, URI href) {
    return (Subject) super.link(rel, href);
  }

  @Override
  public Subject link(Link link) {
    return (Subject) super.link(link);
  }

  @Override
  public Subject lang(String lang) {
    return (Subject) super.lang(lang);
  }

  @Override
  public Subject confidence(URI confidence) {
    return (Subject) super.confidence(confidence);
  }

  @Override
  public Subject confidence(ConfidenceLevel confidence) {
    return (Subject) super.confidence(confidence);
  }

  @Override
  public Subject source(SourceReference sourceReference) {
    return (Subject) super.source(sourceReference);
  }

  @Override
  public Subject source(SourceDescription source) {
    return (Subject) super.source(source);
  }

  @Override
  public Subject note(Note note) {
    return (Subject) super.note(note);
  }

  @Override
  public Subject attribution(Attribution attribution) {
    return (Subject) super.attribution(attribution);
  }

  @Override
  public Subject analysis(ResourceReference analysis) {
    return (Subject) super.analysis(analysis);
  }

  @Override
  public Subject analysis(URI analysis) {
    return (Subject) super.analysis(analysis);
  }

  @Override
  public Subject analysis(Document analysis) {
    return (Subject) super.analysis(analysis);
  }

  /**
   * Whether this subject has been identified as "extracted", meaning it captures information extracted from a single source.
   *
   * @return Whether this subject has been identified as "extracted".
   */
  @XmlAttribute
  @Facet ( name = GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  public Boolean getExtracted() {
    return extracted;
  }

  /**
   * Whether this subject has been identified as "extracted", meaning it captures information extracted from a single source.
   *
   * @param extracted Whether this subject has been identified as "extracted".
   */
  public void setExtracted(Boolean extracted) {
    this.extracted = extracted;
  }

  /**
   * Build up this subject with an extracted flag.
   *
   * @param extracted The extracted flag.
   * @return this.
   */
  public Subject extracted(Boolean extracted) {
    setExtracted(extracted);
    return this;
  }

  /**
   * Find the long-term, persistent identifier for this subject from the list of identifiers.
   *
   * @return The long-term, persistent identifier for this subject.
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
   * A long-term, persistent, globally unique identifier for this subject.
   *
   * @param persistentId A long-term, persistent, globally unique identifier for this subject.
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
   * The list of identifiers for the subject.
   *
   * @return The list of identifiers for the subject.
   */
  @XmlElement (name="identifier")
  @JsonProperty ("identifiers")
  @JsonName ("identifiers")
  public List<Identifier> getIdentifiers() {
    return identifiers;
  }

  /**
   * The list of identifiers of the subject.
   *
   * @param identifiers The list of identifiers of the subject.
   */
  @JsonProperty ("identifiers")
  public void setIdentifiers(List<Identifier> identifiers) {
    this.identifiers = identifiers;
  }

  /**
   * Build up this subject with an identifier.
   *
   * @param identifier The identifier.
   * @return this.
   */
  public Subject identifier(Identifier identifier) {
    addIdentifier(identifier);
    return this;
  }

  /**
   * Add an identifier of the subject.
   *
   * @param identifier The identifier to be added.
   */
  public void addIdentifier(Identifier identifier) {
    if (identifier != null) {
      if (identifiers == null) {
        identifiers = new LinkedList<Identifier>();
      }
      identifiers.add(identifier);
    }
  }

  /**
   * References to the evidence being referenced for this subject.
   *
   * @return References to the evidence being referenced.
   */
  @Facet ( name = GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  public List<EvidenceReference> getEvidence() {
    return evidence;
  }

  /**
   * References to the evidence being referenced for this subject.
   *
   * @param evidence References to the evidence being referenced.
   */
  public void setEvidence(List<EvidenceReference> evidence) {
    this.evidence = evidence;
  }

  /**
   * Build up this subject with an evidence reference.
   *
   * @param evidence The evidence reference.
   * @return this.
   */
  public Subject evidence(EvidenceReference evidence) {
    addEvidence(evidence);
    return this;
  }

  /**
   * Add an evidence reference for this subject.
   *
   * @param evidenceRef The evidence to be added.
   */
  public void addEvidence(EvidenceReference evidenceRef) {
    if (evidenceRef != null) {
      if (evidence == null) {
        evidence = new LinkedList<EvidenceReference>();
      }
      evidence.add(evidenceRef);
    }
  }

  /**
   * References to multimedia resources associated with this subject.
   *
   * @return References to multimedia resources associated with this subject.
   */
  public List<SourceReference> getMedia() {
    return media;
  }

  /**
   * References to multimedia resources associated with this subject.
   *
   * @param media References to multimedia resources associated with this subject.
   */
  public void setMedia(List<SourceReference> media) {
    this.media = media;
  }

  /**
   * Build up this subject with a media reference.
   *
   * @param media The media reference.
   * @return this.
   */
  public Subject media(SourceReference media) {
    addMedia(media);
    return this;
  }

  /**
   * Build up this subject with a media reference.
   *
   * @param media The media reference.
   * @return this.
   */
  public Subject media(SourceDescription media) {
    addMedia(new SourceReference().description(media));
    return this;
  }

  /**
   * Add an media reference associated with this subject.
   *
   * @param mediaRef The reference to the media to be added.
   */
  public void addMedia(SourceReference mediaRef) {
    if (mediaRef != null) {
      if (media == null) {
        media = new LinkedList<SourceReference>();
      }
      media.add(mediaRef);
    }
  }

  protected void embed(Subject subject) {
    this.extracted = this.extracted == null ? subject.extracted : this.extracted;

    if (subject.identifiers != null) {
      this.identifiers = this.identifiers == null ? new ArrayList<Identifier>() : this.identifiers;
      this.identifiers.addAll(subject.identifiers);
    }
    if (subject.media != null) {
      this.media = this.media == null ? new ArrayList<SourceReference>() : this.media;
      this.media.addAll(subject.media);
    }
    if (subject.evidence != null) {
      this.evidence = this.evidence == null ? new ArrayList<EvidenceReference>() : this.evidence;
      this.evidence.addAll(subject.evidence);
    }

    super.embed(subject);
  }
}
