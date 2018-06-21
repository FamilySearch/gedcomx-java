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
import org.gedcomx.common.TextValue;
import org.gedcomx.common.URI;
import org.gedcomx.links.HypermediaEnabledData;
import org.gedcomx.rt.json.JsonElementWrapper;

@XmlRootElement
@JsonElementWrapper(name = "term")
@XmlType(name = "Term")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Term extends HypermediaEnabledData {

  private URI type;
  private URI concept;
  private URI sublist;
  private Integer sublistPosition;
  private List<TextValue> values;


  /**
   * Get the vocabulary term type.
   *
   * @return the vocabulary term type.
   */
  public URI getType() {
    return type;
  }

  /**
   * Set the vocabulary term type.
   *
   * @param type The vocabulary term type.
   */
  public void setType(URI type) {
    this.type = type;
  }

  /**
   * Build out this vocabulary term by applying a type.
   *
   * @param type The type to apply to this vocabulary term.
   * @return this.
   */
  public Term type(final URI type) {
    this.setType(type);
    return this;
  }

  /**
   * Get the URI of the concept this vocabulary term is associated with.
   *
   * @return The URI of the concept this vocabulary term is associated with.
   */
  public URI getConcept() {
    return concept;
  }

  /**
   * Set the URI of the concept this vocabulary term is associated with.
   *
   * @param concept The URI of the concept this vocabulary term is associated with.
   */
  public void setConcept(final URI concept) {
    this.concept = concept;
  }

  /**
   * Build out this vocabulary term by applying a concept id.
   *
   * @param concept The concept id to apply to this vocabulary term.
   * @return this.
   */
  public Term concept(final URI concept) {
    this.setConcept(concept);
    return this;
  }

  /**
   * Get the URI of the sublist this vocabulary term is associated with.
   *
   * @return The URI of the sublist this vocabulary term is associated with.
   */
  public URI getSublist() {
    return sublist;
  }

  /**
   * Set the URI of the sublist this vocabulary term is associated with.
   *
   * @param sublist the URI of the sublist this vocabulary term is associated with.
   */
  public void setSublist(final URI sublist) {
    this.sublist = sublist;
  }

  /**
   * Build out this vocabulary term by applying a sublist URI.
   *
   * @param sublist The sublist URI to apply to this vocabulary term.
   * @return this.
   */
  public Term sublist(final URI sublist) {
    this.setSublist(sublist);
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
  public Term sublistPosition(final Integer sublistPosition) {
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
  public Term values(final List<TextValue> values) {
    this.setValues(values);
    return this;
  }

}
