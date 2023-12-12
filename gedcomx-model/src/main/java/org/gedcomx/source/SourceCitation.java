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
package org.gedcomx.source;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.webcohesion.enunciate.metadata.Facet;
import org.gedcomx.common.ResourceReference;
import org.gedcomx.common.URI;
import org.gedcomx.links.HypermediaEnabledData;
import org.gedcomx.links.Link;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.rt.GedcomxModelVisitor;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import javax.xml.XMLConstants;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;


/**
 * Represents a source citation.
 */
@XmlType ( name = "SourceCitation" )
@JsonInclude ( JsonInclude.Include.NON_NULL )
public class SourceCitation extends HypermediaEnabledData {

  private String lang;
  private String value;
  private ResourceReference citationTemplate;
  private List<CitationField> fields;

  @Override
  public SourceCitation id(String id) {
    return (SourceCitation) super.id(id);
  }

  @Override
  public SourceCitation extensionElement(Object element) {
    return (SourceCitation) super.extensionElement(element);
  }

  @Override
  public SourceCitation link(Link link) {
    return (SourceCitation) super.link(link);
  }

  @Override
  public SourceCitation link(String rel, URI href) {
    return (SourceCitation) super.link(rel, href);
  }

  /**
   * The language of the citation. See <a href="http://www.w3.org/International/articles/language-tags/">http://www.w3.org/International/articles/language-tags/</a>
   *
   * @return The language of the note.
   */
  @XmlAttribute ( namespace = XMLConstants.XML_NS_URI )
  public String getLang() {
    return lang;
  }

  /**
   * The language of the citation. See <a href="http://www.w3.org/International/articles/language-tags/">http://www.w3.org/International/articles/language-tags/</a>
   *
   * @param lang The language of the citation.
   */
  public void setLang(String lang) {
    this.lang = lang;
  }

  /**
   * Build out this source citation with a lang.
   * @param lang The lang.
   * @return this.
   */
  public SourceCitation lang(String lang) {
    setLang(lang);
    return this;
  }

  /**
   * A rendering (as a string) of a source citation.  This rendering should be the most complete rendering available.
   *
   * @return A rendering (as a string) of a source citation.  This rendering should be the most complete rendering available.
   */
  public String getValue() {
    return value;
  }

  /**
   * A rendering (as a string) of a source citation.  This rendering should be the most complete rendering available.
   *
   * @param value A rendering (as a string) of a source citation.  This rendering should be the most complete rendering available.
   */
  public void setValue(String value) {
    this.value = value;
  }

  /**
   * Build out this source citation with a value.
   *
   * @param value The value.
   * @return this.
   */
  public SourceCitation value(String value) {
    setValue(value);
    return this;
  }

  /**
   * A reference to the citation template for this citation.
   *
   * @return A reference to the citation template for this citation.
   */
  @Facet ( GedcomxConstants.FACET_GEDCOMX_CITATION )
  public ResourceReference getCitationTemplate() {
    return citationTemplate;
  }


  /**
   * A reference to the citation template for this citation.
   *
   * @param citationTemplate A reference to the citation template for this citation.
   */
  public void setCitationTemplate(ResourceReference citationTemplate) {
    this.citationTemplate = citationTemplate;
  }

  /**
   * Build out this source citation with a template.
   * @param citationTemplate The template.
   * @return this.
   */
  public SourceCitation citationTemplate(ResourceReference citationTemplate) {
    setCitationTemplate(citationTemplate);
    return this;
  }

  /**
   * The list of citation fields.
   *
   * @return The list of citation fields.
   */
  @XmlElement (name="field")
  @JsonProperty ("fields")
  @Facet ( GedcomxConstants.FACET_GEDCOMX_CITATION )
  public List<CitationField> getFields() {
    return fields;
  }

  /**
   * The list of citation fields.
   *
   * @param fields The list of citation fields.
   */
  @JsonProperty ("fields")
  public void setFields(List<CitationField> fields) {
    this.fields = fields;
  }

  /**
   * Build out this citation with a field.
   * @param fields The field.
   * @return This.
   */
  public SourceCitation field(CitationField fields) {
    addField(fields);
    return this;
  }

  /**
   * Add a reference to the citation fields.
   *
   * @param field The field to be added.
   */
  public void addField(CitationField field) {
    if (field != null) {
      if (fields == null) {
        fields = new LinkedList<CitationField>();
      }
      fields.add(field);
    }
  }

  /**
   * Accept a visitor.
   *
   * @param visitor The visitor.
   */
  public void accept(GedcomxModelVisitor visitor) {
    visitor.visitSourceCitation(this);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final SourceCitation that = (SourceCitation) o;
    return Objects.equals(citationTemplate, that.citationTemplate) &&
           Objects.equals(fields, that.fields) &&
           Objects.equals(lang, that.lang) &&
           Objects.equals(value, that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(citationTemplate, fields, lang, value);
  }
}
