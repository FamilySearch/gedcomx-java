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
import org.gedcomx.agent.Agent;
import org.gedcomx.conclusion.PlaceDescription;
import org.gedcomx.links.HypermediaEnabledData;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.rt.MediaTypeDefinition;
import org.gedcomx.rt.Model;
import org.gedcomx.rt.json.JsonElementWrapper;
import org.gedcomx.source.SourceDescription;

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
@XmlRootElement (namespace = GedcomxConstants.GEDCOMX_BULK_RECORDS_NAMESPACE)
@JsonElementWrapper (name = "records")
@XmlType ( name = "RecordSet", namespace = GedcomxConstants.GEDCOMX_BULK_RECORDS_NAMESPACE, propOrder = { "records", "collections", "places", "agents", "recordDescriptors" })
public class RecordSet extends HypermediaEnabledData {

  private String lang;
  private List<Record> records;
  private List<SourceDescription> sourceDescriptions;
  private List<Collection> collections;
  private List<PlaceDescription> places;
  private List<Agent> agents;
  private List<RecordDescriptor> recordDescriptors;

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
   * The collections included in this genealogical data set.
   *
   * @return The collections included in this genealogical data set.
   */
  @XmlElement (name="collection")
  @JsonProperty ("collections")
  @JsonName ("collections")
  public List<Collection> getCollections() {
    return collections;
  }

  /**
   * The collections included in this genealogical data set.
   *
   * @param collections The collections included in this genealogical data set.
   */
  @JsonProperty ("collections")
  public void setCollections(List<Collection> collections) {
    this.collections = collections;
  }

  /**
   * The records included in this genealogical data set.
   *
   * @return The records included in this genealogical data set.
   */
  @XmlElement (name="record")
  @JsonProperty ("records")
  @JsonName ("records")
  public List<Record> getRecords() {
    return records;
  }

  /**
   * The records included in this genealogical data set.
   *
   * @param records The records included in this genealogical data set.
   */
  @JsonProperty ("records")
  public void setRecords(List<Record> records) {
    this.records = records;
  }

  /**
   * The places included in this genealogical data set.
   *
   * @return The places included in this genealogical data set.
   */
  @XmlElement (name="place")
  @JsonProperty ("places")
  @JsonName ("places")
  public List<PlaceDescription> getPlaces() {
    return places;
  }

  /**
   * The places included in this genealogical data set.
   *
   * @param places The places included in this genealogical data set.
   */
  @JsonProperty ("places")
  public void setPlaces(List<PlaceDescription> places) {
    this.places = places;
  }

  /**
   * The record descriptors included in this genealogical data set.
   *
   * @return The record descriptors included in this genealogical data set.
   */
  @XmlElement (name="recordDescriptor")
  @JsonProperty ("recordDescriptors")
  @JsonName ("recordDescriptors")
  public List<RecordDescriptor> getRecordDescriptors() {
    return recordDescriptors;
  }

  /**
   * The record descriptors included in this genealogical data set.
   *
   * @param recordDescriptors The record descriptors included in this genealogical data set.
   */
  @JsonProperty ("recordDescriptors")
  public void setRecordDescriptors(List<RecordDescriptor> recordDescriptors) {
    this.recordDescriptors = recordDescriptors;
  }

  /**
   * The agents included in this genealogical data set.
   *
   * @return The agents included in this genealogical data set.
   */
  @XmlElement (name="agent")
  @JsonProperty ("agents")
  @JsonName ("agents")
  public List<Agent> getAgents() {
    return agents;
  }

  /**
   * The agents included in this genealogical data set.
   *
   * @param agents The agents included in this genealogical data set.
   */
  @JsonProperty ("agents")
  public void setAgents(List<Agent> agents) {
    this.agents = agents;
  }

}