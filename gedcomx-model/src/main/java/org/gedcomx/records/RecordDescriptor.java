/**
 * Copyright 2011-2012 Intellectual Reserve, Inc.
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
package org.gedcomx.records;

import org.codehaus.enunciate.json.JsonName;
import org.codehaus.jackson.annotate.JsonProperty;
import org.gedcomx.links.HypermediaEnabledData;
import org.gedcomx.rt.GedcomxModelVisitor;

import javax.xml.XMLConstants;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * @author Ryan Heaton
 */
public class RecordDescriptor extends HypermediaEnabledData {

  private String lang;
  private String text;
  private List<FieldDescription> fields;

  /**
   * The language of this record description. See <a href="http://www.w3.org/International/articles/language-tags/">http://www.w3.org/International/articles/language-tags/</a>
   *
   * @return The language of this record description.
   */
  @XmlAttribute ( namespace = XMLConstants.XML_NS_URI )
  public String getLang() {
    return lang;
  }

  /**
   * The language of this record description. See <a href="http://www.w3.org/International/articles/language-tags/">http://www.w3.org/International/articles/language-tags/</a>
   *
   * @param lang The language of this record description.
   */
  public void setLang(String lang) {
    this.lang = lang;
  }

  /**
   * Human-readable text value of this descriptor, used to describe the kinds of records associated with this descriptor.
   *
   * @return Human-readable text value of the description.
   */
  public String getText() {
    return text;
  }

  /**
   * Human-readable text value of this descriptor, used to describe the kinds of records associated with this descriptor.
   *
   * @param text Human-readable text value of the description.
   */
  public void setText(String text) {
    this.text = text;
  }

  /**
   * The fields that are applicable to this record.
   *
   * @return The fields that are applicable to this record.
   */
  @XmlElement (name="field")
  @JsonProperty ("fields")
  @JsonName ("fields")
  public List<FieldDescription> getFields() {
    return fields;
  }

  /**
   * The fields that are applicable to this record.
   *
   * @param fields The fields that are applicable to this record.
   */
  @JsonProperty ("fields")
  public void setFields(List<FieldDescription> fields) {
    this.fields = fields;
  }

  /**
   * Accept a visitor.
   *
   * @param visitor The visitor.
   */
  public void accept(GedcomxModelVisitor visitor) {
    visitor.visitRecordDescriptor(this);
  }
}
