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
package org.gedcomx.records;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.gedcomx.links.HypermediaEnabledData;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.rt.GedcomxModelVisitor;
import org.gedcomx.rt.json.JsonElementWrapper;

import javax.xml.XMLConstants;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * A descriptor for a common set of records.
 *
 * @author Ryan Heaton
 */
@XmlType( name = "RecordDescriptor" )
@JsonElementWrapper ( name = "recordDescriptors" )
@com.webcohesion.enunciate.metadata.Facet( GedcomxConstants.FACET_GEDCOMX_RECORD )
@JsonInclude ( JsonInclude.Include.NON_NULL )
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
   * Create a stream for the fields.
   *
   * @return a stream for the fields.
   */
  public Stream<FieldDescriptor> fields() {
    return this.fields == null ? Stream.empty() : this.fields.stream();
  }

  /**
   * Descriptors of the fields that are applicable to this record.
   *
   * @return Descriptors of the fields that are applicable to this record.
   */
  @XmlElement (name="field")
  @JsonProperty ("fields")
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

  /**
   * Embed another record descriptor.
   * 
   * @param recordDescriptor The descriptor to embed.
   */
  public void embed(RecordDescriptor recordDescriptor) {
    this.lang = this.lang == null ? recordDescriptor.lang : this.lang;
    if (recordDescriptor.fields != null) {
      this.fields = this.fields == null ? new ArrayList<FieldDescriptor>() : this.fields;
      this.fields.addAll(recordDescriptor.fields);
    }
    super.embed(recordDescriptor);
  }
}
