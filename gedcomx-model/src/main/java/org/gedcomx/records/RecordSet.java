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
import org.gedcomx.Gedcomx;
import org.gedcomx.common.URI;
import org.gedcomx.links.HypermediaEnabledData;
import org.gedcomx.links.Link;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.rt.MediaTypeDefinition;
import org.gedcomx.rt.Model;
import org.gedcomx.rt.json.JsonElementWrapper;

import javax.xml.XMLConstants;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * The GEDCOM X bulk record data formats are used to exchange bulk genealogical data sets, grouped into records.
 *
 * @author Ryan Heaton
 */
@MediaTypeDefinition (
  name = "GEDCOM X Record Set",
  description = "The GEDCOM X record set data format is used to exchange multiple, distinct genealogical data sets.",
  version = "1.0",
  xmlMediaType = GedcomxConstants.GEDCOMX_RECORDSET_XML_MEDIA_TYPE,
  jsonMediaType = GedcomxConstants.GEDCOMX_RECORDSET_JSON_MEDIA_TYPE,
  models = {
    @Model (
      id = "gxrecs",
      namespace = "http://gedcomx.org/recordset/v1/",
      label = "GEDCOM X Record Set Model",
      description = "The model for defining a set of related records."
    )
  }
)
@XmlRootElement (name = "records")
@JsonElementWrapper (name = "records")
@XmlType ( name = "RecordSet", propOrder = { "metadata", "records" })
@com.webcohesion.enunciate.metadata.Facet( GedcomxConstants.FACET_GEDCOMX_RECORD )
@JsonInclude ( JsonInclude.Include.NON_NULL )
public class RecordSet extends HypermediaEnabledData {

  private String lang;
  private Gedcomx metadata;
  private List<Gedcomx> records;

  @Override
  public RecordSet id(String id) {
    return (RecordSet) super.id(id);
  }

  @Override
  public RecordSet extensionElement(Object element) {
    return (RecordSet) super.extensionElement(element);
  }

  @Override
  public RecordSet link(String rel, URI href) {
    return (RecordSet) super.link(rel, href);
  }

  @Override
  public RecordSet link(Link link) {
    return (RecordSet) super.link(link);
  }

  /**
   * The language of this genealogical data set. See <a href="http://www.w3.org/International/articles/language-tags/">http://www.w3.org/International/articles/language-tags/</a>.
   * Note that some language-enabled elements MAY override the language. 
   *
   * @return The language of the genealogical data.
   */
  @XmlAttribute ( namespace = XMLConstants.XML_NS_URI )
  public String getLang() {
    return lang;
  }

  /**
   * The language of this genealogical data set. See <a href="http://www.w3.org/International/articles/language-tags/">http://www.w3.org/International/articles/language-tags/</a>.
   * Note that some language-enabled elements MAY override the language.
   *
   * @param lang The language of this genealogical data.
   */
  public void setLang(String lang) {
    this.lang = lang;
  }

  /**
   * Build out this record set with a lang.
   * @param lang The lang.
   * @return this.
   */
  public RecordSet lang(String lang) {
    setLang(lang);
    return this;
  }

  /**
   * Metadata about this record set; shared among all records in the set.
   *
   * @return Metadata about this record set; shared among all records in the set.
   */
  public Gedcomx getMetadata() {
    return metadata;
  }

  /**
   * Metadata about this record set; shared among all records in the set.
   *
   * @param metadata Metadata about this record set; shared among all records in the set.
   */
  public void setMetadata(Gedcomx metadata) {
    this.metadata = metadata;
  }

  /**
   * Build out this record set with metadata.
   *
   * @param metadata The metadata.
   * @return this.
   */
  public RecordSet metadata(Gedcomx metadata) {
    setMetadata(metadata);
    return this;
  }

  /**
   * The records included in this genealogical data set.
   *
   * @return The records included in this genealogical data set.
   */
  @XmlElement (name="record")
  @JsonProperty ("records") @org.codehaus.jackson.annotate.JsonProperty ("records")
  public List<Gedcomx> getRecords() {
    return records;
  }

  /**
   * The records included in this genealogical data set.
   *
   * @param records The records included in this genealogical data set.
   */
  @JsonProperty ("records") @org.codehaus.jackson.annotate.JsonProperty ("records")
  public void setRecords(List<Gedcomx> records) {
    this.records = records;
  }

  /**
   * Build out this record set with a record.
   * @param record The record.
   * @return this.
   */
  public RecordSet record(Gedcomx record) {
    if (this.records == null) {
      this.records = new ArrayList<Gedcomx>();
    }
    this.records.add(record);
    return this;
  }

}