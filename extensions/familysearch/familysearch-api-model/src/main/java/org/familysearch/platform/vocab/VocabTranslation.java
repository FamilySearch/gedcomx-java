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

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.familysearch.platform.rt.FamilySearchPlatformModelVisitor;
import org.gedcomx.common.TextValue;
import org.gedcomx.links.HypermediaEnabledData;
import org.gedcomx.rt.json.JsonElementWrapper;

@XmlRootElement
@JsonElementWrapper(name = "vocabTranslations")
@XmlType(name = "VocabTranslation")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "A vocabulary translation.")
public class VocabTranslation extends HypermediaEnabledData {

  @Schema(description = "The translation of the vocabulary term.")
  private TextValue translation;

  public VocabTranslation() {
    translation = new TextValue();
  }

  public VocabTranslation(TextValue translation) {
    this.translation = translation;
  }

  public VocabTranslation(String text, String language) {
    translation = new TextValue().lang(language).value(text);
  }


  /**
   * Get the language of the vocabulary translation. See
   * <a href="http://www.w3.org/International/articles/language-tags/">http://www.w3.org/International/articles/language-tags/</a>
   *
   * @return the language for the translation.
   */
  public String getLang() {
    return translation.getLang();
  }

  /**
   * Set the language for the vocabulary translation. See
   * <a href="http://www.w3.org/International/articles/language-tags/">http://www.w3.org/International/articles/language-tags/</a>
   *
   * @param lang The language for the vocabulary translation
   */
  public void setLang(String lang) {
    translation.setLang(lang);
  }

  /**
   * Build up this vocabulary translation with a specified language. See
   * <a href="http://www.w3.org/International/articles/language-tags/">http://www.w3.org/International/articles/language-tags/</a>
   *
   * @param lang The language for the vocabulary translation.
   * @return this.
   */
  public VocabTranslation lang(String lang) {
    translation.setLang(lang);
    return this;
  }


  /**
   * Get the text for this vocabulary translation.
   *
   * @return the translation text.
   */
  public String getText() {
    return translation.getValue();
  }

  /**
   * Set the text for this vocabulary translation
   *
   * @param text The translation text
   */
  public void setText(String text) {
    translation.setValue(text);
  }

  /**
   * Build up this vocabulary translation with a translation text.
   *
   * @param text The text value.
   * @return this.
   */
  public VocabTranslation text(String text) {
    translation.setValue(text);
    return this;
  }

    /**
     * Accept a visitor.
     *
     * @param visitor The visitor to accept.
     */
  public void accept(FamilySearchPlatformModelVisitor visitor) {
    visitor.visitVocabTranslation(this);
  }

  public void embed(VocabTranslation vocabTerm) {
    super.embed(vocabTerm);
  }

}
