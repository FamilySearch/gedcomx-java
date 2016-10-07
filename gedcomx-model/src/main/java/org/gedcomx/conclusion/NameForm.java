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
import com.fasterxml.jackson.annotation.JsonProperty;
import org.gedcomx.common.ExtensibleData;
import org.gedcomx.records.Field;
import org.gedcomx.records.HasFields;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.rt.GedcomxModelVisitor;
import org.gedcomx.types.NamePartType;

import javax.xml.XMLConstants;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.LinkedList;
import java.util.List;

/**
 * A form of a name.
 *
 * @author Ryan Heaton
 */
@XmlType ( name = "NameForm", propOrder = { "fullText", "parts", "fields"})
public class NameForm extends ExtensibleData implements HasFields {

  private String lang;
  private String fullText;
  private List<NamePart> parts;
  private List<Field> fields;

  public NameForm() {
  }

  public NameForm(String fullText, NamePart... parts) {
    this.fullText = fullText;
    for (NamePart part : parts) {
      addPart(part);
    }
  }

  @Override
  public NameForm id(String id) {
    return (NameForm) super.id(id);
  }

  @Override
  public NameForm extensionElement(Object element) {
    return (NameForm) super.extensionElement(element);
  }

  /**
   * The language of the conclusion. See <a href="http://www.w3.org/International/articles/language-tags/">http://www.w3.org/International/articles/language-tags/</a>
   *
   * @return The language of the conclusion.
   */
  @XmlAttribute ( namespace = XMLConstants.XML_NS_URI )
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
   * Build up this name form with a lang.
   *
   * @param lang The lang.
   * @return this.
   */
  public NameForm lang(String lang) {
    setLang(lang);
    return this;
  }

  /**
   * The full text of the name form.
   *
   * @return The full text of the name form.
   */
  public String getFullText() {
    return fullText;
  }

  /**
   * The full text of the name form.
   *
   * @param fullText The full text of the name form.
   */
  public void setFullText(String fullText) {
    this.fullText = fullText;
  }

  /**
   * Build up this name form with full text.
   *
   * @param fullText The full text.
   * @return this
   */
  public NameForm fullText(String fullText) {
    setFullText(fullText);
    return this;
  }

  /**
   * The different parts of the name form.
   *
   * @return The different parts of the name form.
   */
  @XmlElement (name = "part")
  @JsonName ("parts")
  @JsonProperty ("parts")
  public List<NamePart> getParts() {
    return parts;
  }

  /**
   * The different parts of the name form.
   *
   * @param parts The different parts of the name form.
   */
  @JsonProperty ("parts")
  public void setParts(List<NamePart> parts) {
    this.parts = parts;
  }

  /**
   * Build up this name form with a part.
   *
   * @param part The part.
   * @return this.
   */
  public NameForm part(NamePart part) {
    addPart(part);
    return this;
  }

  /**
   * Build up this name form with a part.
   *
   * @param partType The part type.
   * @param value The value.
   * @return this.
   */
  public NameForm part(NamePartType partType, String value) {
    addPart(new NamePart(partType, value));
    return this;
  }

  /**
   * Add a name part the list of name parts for this name form.
   *
   * @param part The name part to be added.
   */
  public void addPart(NamePart part) {
    if (part != null) {
      if (parts == null) {
        parts = new LinkedList<NamePart>();
      }
      parts.add(part);
    }
  }


  /**
   * Get the fields being used as evidence.
   *
   * @return The references to the record fields being used as evidence.
   */
  @XmlElement( name = "field" )
  @JsonProperty( "fields" )
  @JsonName( "fields" )
  @Facet ( name = GedcomxConstants.FACET_GEDCOMX_RECORD )
  public List<Field> getFields() {
    return fields;
  }

  /**
   * Set the list of fields being used as evidence.
   *
   * @param fields - List of fields
   */
  @JsonProperty( "fields" )
  public void setFields(List<Field> fields) {
    this.fields = fields;
  }

  /**
   * Build up this name form with a field.
   *
   * @param field The field.
   * @return this.
   */
  public NameForm field(Field field) {
    addField(field);
    return this;
  }

  /**
   * Add a reference to the record field values being used as evidence.
   *
   * @param field The field to be added.
   */
  public void addField(Field field) {
    if (field != null) {
      if (fields == null) {
        fields = new LinkedList<Field>();
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
    visitor.visitNameForm(this);
  }
}
