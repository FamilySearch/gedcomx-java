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

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.gedcomx.common.TextValue;
import org.gedcomx.common.URI;
import org.gedcomx.links.HypermediaEnabledData;
import org.gedcomx.rt.json.JsonElementWrapper;

import org.familysearch.platform.rt.FamilySearchPlatformModelVisitor;

@XmlRootElement
@JsonElementWrapper(name = "vocabTerms")
@XmlType(name = "VocabTerm")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VocabTerm extends HypermediaEnabledData {
  private URI typeUri;
  private URI conceptUri;
  private URI sublistUri;
  private Integer sublistPosition;
  private List<TextValue> values;

  /**
   * Get the vocabulary term type.
   *
   * @return the vocabulary term type.
   */
  public URI getTypeUri() {
    return typeUri;
  }

  /**
   * Set the vocabulary term type.
   *
   * @param typeUri The vocabulary term type.
   */
  public void setTypeUri(URI typeUri) {
    this.typeUri = typeUri;
  }

  /**
   * Build out this vocabulary term by applying a type.
   *
   * @param type The type to apply to this vocabulary term.
   * @return this.
   */
  public VocabTerm type(final URI type) {
    this.setTypeUri(type);
    return this;
  }

  /**
   * Get the URI of the vocabulary concept this vocabulary term is associated with.
   *
   * @return The URI of the vocabulary concept this vocabulary term is associated with.
   */
  public URI getVocabConcept() {
    return conceptUri;
  }

  /**
   * Set the URI of the vocabular concept this vocabulary term is associated with.
   *
   * @param vocabConceptUri The URI of the vocabulary concept this vocabulary term is associated with.
   */
  public void setVocabConceptUri(final URI vocabConceptUri) {
    this.conceptUri = vocabConceptUri;
  }

  /**
   * Build out this vocabulary term by applying a vocabulary concept URI.
   *
   * @param vocabConceptUri The vocabulary concept URI to apply to this vocabulary term.
   * @return this.
   */
  public VocabTerm vocabConcept(final URI vocabConceptUri) {
    this.setVocabConceptUri(vocabConceptUri);
    return this;
  }

  /**
   * Get the URI of the sublist this vocabulary term is associated with.
   *
   * @return The URI of the sublist this vocabulary term is associated with.
   */
  public URI getSublistUri() {
    return sublistUri;
  }

  /**
   * Set the URI of the sublist this vocabulary term is associated with.
   *
   * @param sublistUri the URI of the sublist this vocabulary term is associated with.
   */
  public void setSublistUri(final URI sublistUri) {
    this.sublistUri = sublistUri;
  }

  /**
   * Build out this vocabulary term by applying a sublist URI.
   *
   * @param sublist The sublist URI to apply to this vocabulary term.
   * @return this.
   */
  public VocabTerm sublist(final URI sublist) {
    this.setSublistUri(sublist);
    return this;
  }

  /**
   * Get the position of this vocabulary term within it's associated sublist.
   *
   * @return The position of this vocabulary term within it's associated sublist.
   */
  public Integer getSublistPosition() {
    return sublistPosition;
  }

  /**
   * Set the position of this vocabulary term within it's associated sublist.
   *
   * @param sublistPosition The position of this vocabulary term within it's associated sublist.
   */
  public void setSublistPosition(Integer sublistPosition) {
    this.sublistPosition = sublistPosition;
  }

  /**
   * Build out this vocabulary term by applying a sublist position.
   *
   * @param sublistPosition The sublist position id to apply to this vocabulary term.
   * @return this.
   */
  public VocabTerm sublistPosition(final Integer sublistPosition) {
    this.setSublistPosition(sublistPosition);
    return this;
  }

  /**
   * Get the vocabulary term values.
   *
   * @return The vocabulary term values.
   */
  public List<TextValue> getValues() {
    return values;
  }

  /**
   * Set the vocabulary term values.
   *
   * @param values The vocabulary term values.
   */
  public void setValues(final List<TextValue> values) {
    this.values = values;
  }

  /**
   * Build out this vocabulary term by applying the given values.
   *
   * @param values The values to apply to this vocabulary term.
   * @return this.
   */
  public VocabTerm values(final List<TextValue> values) {
    this.setValues(values);
    return this;
  }

  /**
   * Accept a visitor.
   *
   * @param visitor The visitor to accept.
   */
  public void accept(FamilySearchPlatformModelVisitor visitor) {
    visitor.visitVocabTerm(this);
  }

  public void embed(VocabTerm vocabTerm) {
    super.embed(vocabTerm);
  }

}
