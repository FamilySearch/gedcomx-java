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
package org.familysearch.platform.vocab;

import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.gedcomx.common.TextValue;
import org.gedcomx.common.URI;
import org.gedcomx.links.HypermediaEnabledData;
import org.gedcomx.rt.json.JsonElementWrapper;

import org.familysearch.platform.rt.FamilySearchPlatformModelVisitor;

@XmlRootElement
@JsonElementWrapper(name = "vocabConcepts")
@XmlType(name = "VocabConcept")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "A vocabulary concept.")
public class VocabConcept extends HypermediaEnabledData {

  @Schema(description = "The vocabulary concept description.")
  private String description;

  @Schema(description = "The vocabulary concept note.")
  private String note;

  @Schema(description = "The Gedcomx URI associated with the concept.")
  private URI gedcomxUri;

  @Schema(description = "The terms associated with the concept.")
  private List<VocabTerm> vocabTerms;

  @Schema(description = "The attributes associated with the concept.")
  private List<VocabConceptAttribute> attributes;

  @Schema(description = "The definitions associated with the concept.")
  private List<TextValue> definitions;

  /**
   * Get the vocabulary concept description.
   *
   * @return The vocabulary concept description.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Set the vocabulary concept description.
   *
   * @param description The vocabulary concept description.
   */
  public void setDescription(final String description) {
    this.description = description;
  }

  /**
   * Build out this vocabulary concept by applying a description.
   *
   * @param description The description to apply to this vocabulary concept.
   * @return this.
   */
  public VocabConcept description(final String description) {
    this.setDescription(description);
    return this;
  }

  /**
   * Get the vocabulary concept note.
   *
   * @return The vocabulary concept note.
   */
  public String getNote() {
    return note;
  }

  /**
   * Set the vocabulary concept note.
   *
   * @param note The vocabulary concept note.
   */
  public void setNote(final String note) {
    this.note = note;
  }

  /**
   * Build out this vocabulary concept by applying a note.
   *
   * @param note The note to apply to this vocabulary concept.
   * @return this.
   */
  public VocabConcept note(final String note) {
    this.setNote(note);
    return this;
  }

  /**
   * Get the Gedcomx URI associated with this vocabulary concept.
   *
   * @return The Gedcomx URI associated with this vocabulary concept.
   */
  public URI getGedcomxUri() {
    return gedcomxUri;
  }

  /**
   * Set the Gedcomx URI associated with this vocabulary concept.
   *
   * @param gedcomxUri The Gedcomx URI associated with this vocabulary concept.
   */
  public void setGedcomxUri(final URI gedcomxUri) {
    this.gedcomxUri = gedcomxUri;
  }

  /**
   * Build out this vocabulary concept by applying the given Gedcomx URI.
   *
   * @param gedcomxUri The Gedcomx URI to apply to this vocabulary concept.
   * @return this.
   */
  public VocabConcept gedcomxUri(final URI gedcomxUri) {
    this.setGedcomxUri(gedcomxUri);
    return this;
  }

  /**
   * Get the vocabulary terms associated with this vocabulary concept.
   *
   * @return The vocabulary terms associated with this vocabulary concept.
   */
  public List<VocabTerm> getVocabTerms() {
    return vocabTerms;
  }

  /**
   * Set the vocabulary terms associated with this vocabulary concept.
   *
   * @param vocabTerms The vocabulary terms associated with this vocabulary concept.
   */
  public void setVocabTerms(final List<VocabTerm> vocabTerms) {
    this.vocabTerms = vocabTerms;
  }

  /**
   * Add a vocabulary term.
   *
   * @param vocabTerm The vocabulary term to add.
   */
  public void addVocabTerm(VocabTerm vocabTerm) {
    if (vocabTerms == null) {
      vocabTerms = new ArrayList<>();
    }
    vocabTerms.add(vocabTerm);
  }

  /**
   * Build out this vocabulary concept by applying the given list of vocabulary terms.
   *
   * @param vocabTerms The vocabulary terms to apply to this vocabulary concept.
   * @return this.
   */
  public VocabConcept vocabTerms(final List<VocabTerm> vocabTerms) {
    this.setVocabTerms(vocabTerms);
    return this;
  }

  /**
   * Get the attributes associated with this vocabulary concept.
   *
   * @return The attributes associated with this vocabulary concept.
   */
  public List<VocabConceptAttribute> getAttributes() {
    return attributes;
  }

  /**
   * Set the attributes associated with this vocabulary concept.
   *
   * @param attributes The attributes associated with this vocabulary concept.
   */
  public void setAttributes(final List<VocabConceptAttribute> attributes) {
    this.attributes = attributes;
  }

  /**
   * Build out this vocabulary concept by applying the given list of attributes.
   *
   * @param attributes The attributes to apply to this vocabulary concept.
   * @return this.
   */
  public VocabConcept attributes(final List<VocabConceptAttribute> attributes) {
    this.setAttributes(attributes);
    return this;
  }

  /**
   * Get the definitions associated with this vocabulary concept.
   *
   * @return The definitions associated with this vocabulary concept.
   */
  public List<TextValue> getDefinitions() {
    return definitions;
  }

  /**
   * Set the definitions associated with this vocabulary concept.
   *
   * @param definitions The definitions associated with this vocabulary concept.
   */
  public void setDefinitions(List<TextValue> definitions) {
    this.definitions = definitions;
  }

  /**
   * Build out this vocabulary concept by applying the given list of definitions.
   *
   * @param definitions The definitions to apply to this vocabulary concept.
   * @return this.
   */
  public VocabConcept definitions(final List<TextValue> definitions) {
    this.setDefinitions(definitions);
    return this;
  }

  /**
   * Accept a visitor.
   *
   * @param visitor The visitor to accept.
   */
  public void accept(FamilySearchPlatformModelVisitor visitor) {
    visitor.visitVocabConcept(this);
  }

  public void embed(VocabConcept vocabConcept) {
    List<VocabTerm> vocabTerms = vocabConcept.getVocabTerms();
    if (vocabTerms != null) {
      for (VocabTerm vocabTerm : vocabTerms) {
        boolean found = false;
        if (vocabTerm.getId() != null) {
          if (getVocabTerms() != null) {
            for (VocabTerm target : getVocabTerms()) {
              if (vocabTerm.getId().equals(target.getId())) {
                target.embed(vocabTerm);
                found = true;
                break;
              }
            }
          }
        }

        if (!found) {
          addVocabTerm(vocabTerm);
        }
      }
    }

    super.embed(vocabConcept);
  }

}
