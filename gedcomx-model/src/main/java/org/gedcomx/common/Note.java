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
package org.gedcomx.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.webcohesion.enunciate.metadata.Facet;
import org.gedcomx.links.HypermediaEnabledData;
import org.gedcomx.links.Link;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.rt.GedcomxModelVisitor;
import org.gedcomx.rt.json.JsonElementWrapper;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import javax.xml.XMLConstants;
import java.util.Objects;


/**
 * A note about a genealogical resource (e.g. conclusion or source).
 *
 * @author Ryan Heaton
 */
@XmlRootElement
@JsonElementWrapper ( name = "notes" )
@XmlType ( name = "Note", propOrder = {"subject", "text", "attribution"} )
@JsonInclude ( JsonInclude.Include.NON_NULL )
public class Note extends HypermediaEnabledData implements Attributable, HasText {

  private String lang;
  private String subject;
  private String text;
  private Attribution attribution;

  @Override
  public Note id(String id) {
    return (Note) super.id(id);
  }

  @Override
  public Note extensionElement(Object element) {
    return (Note) super.extensionElement(element);
  }

  @Override
  public Note link(String rel, URI href) {
    return (Note) super.link(rel, href);
  }

  @Override
  public Note link(Link link) {
    return (Note) super.link(link);
  }

  /**
   * The language of the note. See <a href="http://www.w3.org/International/articles/language-tags/">http://www.w3.org/International/articles/language-tags/</a>
   *
   * @return The language of the note.
   */
  @XmlAttribute ( namespace = XMLConstants.XML_NS_URI )
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  public String getLang() {
    return lang;
  }

  /**
   * The language of the note. See <a href="http://www.w3.org/International/articles/language-tags/">http://www.w3.org/International/articles/language-tags/</a>
   *
   * @param lang The language of the note.
   */
  public void setLang(String lang) {
    this.lang = lang;
  }

  /**
   * Build up this note with a locale.
   *
   * @param lang The locale.
   * @return this.
   */
  public Note lang(String lang) {
    this.lang = lang;
    return this;
  }

  /**
   * The subject of the note. This is a short title describing the contents of the note text.
   *
   * @return The subject of the note.
   */
  public String getSubject() {
    return subject;
  }

  /**
   * The subject of the note.
   *
   * @param text The subject of the note.
   */
  public void setSubject(String text) {
    this.subject = text;
  }

  /**
   * Build up this note with a subject.
   *
   * @param text The subject.
   * @return this.
   */
  public Note subject(String text) {
    this.subject = text;
    return this;
  }

  /**
   * The text of the note.
   *
   * @return The text of the note.
   */
  @Override
  public String getText() {
    return text;
  }

  /**
   * The text of the note.
   *
   * @param text The text of the note.
   */
  @Override
  public void setText(String text) {
    this.text = text;
  }

  /**
   * Build up this note with some text.
   *
   * @param text The text.
   * @return this.
   */
  public Note text(String text) {
    this.text = text;
    return this;
  }

  /**
   * Attribution metadata for a note.
   *
   * @return Attribution metadata for a note.
   */
  @Override
  public Attribution getAttribution() {
    return attribution;
  }

  /**
   * Attribution metadata for a note.
   *
   * @param attribution Attribution metadata for a note.
   */
  @Override
  public void setAttribution(Attribution attribution) {
    this.attribution = attribution;
  }

  /**
   * Build up this note with attribution.
   *
   * @param attribution The attribution.
   * @return this.
   */
  public Note attribution(Attribution attribution) {
    this.attribution = attribution;
    return this;
  }

  @Override
  public String toString() {
    return "Note{" +
      "subject=" + getTextBrief(subject) +
      ", text=" + getTextBrief(text) +
      ", attribution=" + attribution +
      '}';
  }

  private String getTextBrief(String text) {
    if (text != null) {
      final int substrLen = 40;
      if (text.length() > substrLen) {
        return text.substring(0, substrLen) + "...";
      }
      return text;
    }
    return null;
  }

  /**
   * Accept a visitor.
   *
   * @param visitor The visitor.
   */
  public void accept(GedcomxModelVisitor visitor) {
    visitor.visitNote(this);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final Note that = (Note) o;
    return Objects.equals(attribution, that.attribution) &&
           Objects.equals(lang, that.lang) &&
           Objects.equals(subject, that.subject) &&
           Objects.equals(text, that.text);
  }

  @Override
  public int hashCode() {
    return Objects.hash(attribution, lang, subject, text);
  }
}
