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

import com.fasterxml.jackson.annotation.JsonInclude;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.gedcomx.rt.json.JsonElementWrapper;

@XmlRootElement
@JsonElementWrapper(name = "vocabConcepts")
@XmlType(name = "VocabConcepts")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VocabConcepts {

  private List<VocabConcept> vocabConcepts;

  /**
   * Get the list of vocabulary concepts.
   *
   * @return The list of vocabulary concepts.
   */
  public List<VocabConcept> getVocabConcepts() {
    return vocabConcepts;
  }

  /**
   * Set the list of vocabulary concepts.
   *
   * @param vocabConcepts The list of vocabulary concepts.
   */
  public void setVocabConcepts(final List<VocabConcept> vocabConcepts) {
    this.vocabConcepts = vocabConcepts;
  }

  /**
   * Build out this vocabulary concepts list by applying adding concept.
   *
   * @param vocabConcepts The concepts to add.
   * @return this.
   */
  public VocabConcepts vocabConcepts(final List<VocabConcept> vocabConcepts) {
    setVocabConcepts(vocabConcepts);
    return this;
  }

}
