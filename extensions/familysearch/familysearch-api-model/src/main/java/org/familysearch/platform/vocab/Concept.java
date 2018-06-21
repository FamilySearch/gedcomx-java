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

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.gedcomx.common.URI;
import org.gedcomx.links.HypermediaEnabledData;
import org.gedcomx.rt.json.JsonElementWrapper;

@XmlRootElement
@JsonElementWrapper(name = "concept")
@XmlType(name = "Concept")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Concept extends HypermediaEnabledData {

  private String description;
  private String note;
  private URI gedcomxUri;
  private List<Term> terms;
  private List<ConceptAttribute> attributes;

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
  public Concept description(final String description) {
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
  public Concept note(final String note) {
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
  public Concept gedcomxUri(final URI gedcomxUri) {
    this.setGedcomxUri(gedcomxUri);
    return this;
  }

  /**
   * Get the vocabulary terms associated with this vocabulary concept.
   *
   * @return The vocabulary terms associated with this vocabulary concept.
   */
  public List<Term> getTerms() {
    return terms;
  }

  /**
   * Set the vocabulary terms associated with this vocabulary concept.
   *
   * @param terms The vocabulary terms associated with this vocabulary concept.
   */
  public void setTerms(final List<Term> terms) {
    this.terms = terms;
  }

  /**
   * Build out this vocabulary concept by applying the given list of vocabulary terms.
   *
   * @param terms The vocabulary terms to apply to this vocabulary concept.
   * @return this.
   */
  public Concept terms(final List<Term> terms) {
    this.setTerms(terms);
    return this;
  }

  /**
   * Get the attributes associated with this vocabulary concept.
   *
   * @return The attributes associated with this vocabulary concept.
   */
  public List<ConceptAttribute> getAttributes() {
    return attributes;
  }

  /**
   * Set the attributes associated with this vocabulary concept.
   *
   * @param attributes The attributes associated with this vocabulary concept.
   */
  public void setAttributes(final List<ConceptAttribute> attributes) {
    this.attributes = attributes;
  }

  /**
   * Build out this vocabulary concept by applying the given list of attributes.
   *
   * @param attributes The attributes to apply to this vocabulary concept.
   * @return this.
   */
  public Concept attributes(final List<ConceptAttribute> attributes) {
    this.setAttributes(attributes);
    return this;
  }

}
