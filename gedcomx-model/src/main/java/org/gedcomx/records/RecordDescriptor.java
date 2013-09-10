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
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.rt.GedcomxModelVisitor;
import org.gedcomx.rt.json.JsonElementWrapper;

import javax.xml.XMLConstants;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * A descriptor for a common set of records.
 *
 * @author Ryan Heaton
 */
@XmlType( name = "RecordDescriptor" )
@JsonElementWrapper ( name = "recordDescriptors" )
@org.codehaus.enunciate.Facet ( name = GedcomxConstants.FACET_GEDCOMX_RECORD )
public class RecordDescriptor extends HypermediaEnabledData {

  private String lang;
  private List<FieldDescriptor> fields;

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
   * Descriptors of the fields that are applicable to this record.
   *
   * @return Descriptors of the fields that are applicable to this record.
   */
  @XmlElement (name="field")
  @JsonProperty ("fields")
  @JsonName ("fields")
  public List<FieldDescriptor> getFields() {
    return fields;
  }

  /**
   * Descriptors of the fields that are applicable to this record.
   *
   * @param fields Descriptors of the fields that are applicable to this record.
   */
  @JsonProperty ("fields")
  public void setFields(List<FieldDescriptor> fields) {
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
