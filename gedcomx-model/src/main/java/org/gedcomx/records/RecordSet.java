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
import org.gedcomx.Gedcomx;
import org.gedcomx.links.HypermediaEnabledData;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.rt.MediaTypeDefinition;
import org.gedcomx.rt.Model;
import org.gedcomx.rt.json.JsonElementWrapper;

import javax.xml.XMLConstants;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * The GEDCOM X bulk record media types are used to exchange bulk genealogical data sets, grouped into records.
 *
 * @author Ryan Heaton
 */
@MediaTypeDefinition (
  id = "gxbr",
  name = "GEDCOM X Bulk Record",
  description = "The GEDCOM X bulk record media types are used to exchange bulk genealogical data sets, assembled into records.",
  version = "1.0",
  xmlMediaType = GedcomxConstants.GEDCOMX_BULK_RECORDS_XML_MEDIA_TYPE,
  jsonMediaType = GedcomxConstants.GEDCOMX_BULK_RECORDS_JSON_MEDIA_TYPE,
  projectId = "gedcomx-br",
  models = {
    @Model (
      id = "gxbr",
      namespace = GedcomxConstants.GEDCOMX_BULK_RECORDS_NAMESPACE,
      label = "GEDCOM X Bulk Record Model",
      description = "The model for bulk record data."
    )
  }
)
@XmlRootElement ( namespace = GedcomxConstants.GEDCOMX_BULK_RECORDS_NAMESPACE, name = "records" )
@JsonElementWrapper (name = "records")
@XmlType ( name = "RecordSet", namespace = GedcomxConstants.GEDCOMX_BULK_RECORDS_NAMESPACE, propOrder = { "metadata", "records" })
@org.codehaus.enunciate.Facet ( name = GedcomxConstants.FACET_GEDCOMX_RECORD )
public class RecordSet extends HypermediaEnabledData {

  private String lang;
  private Gedcomx metadata;
  private List<Gedcomx> records;

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
   * The records included in this genealogical data set.
   *
   * @return The records included in this genealogical data set.
   */
  @XmlElement (name="record")
  @JsonProperty ("records")
  @JsonName ("records")
  public List<Gedcomx> getRecords() {
    return records;
  }

  /**
   * The records included in this genealogical data set.
   *
   * @param records The records included in this genealogical data set.
   */
  @JsonProperty ("records")
  public void setRecords(List<Gedcomx> records) {
    this.records = records;
  }

}