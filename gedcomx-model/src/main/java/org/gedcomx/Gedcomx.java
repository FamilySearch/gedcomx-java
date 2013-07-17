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
package org.gedcomx;

import org.codehaus.enunciate.Facet;
import org.codehaus.enunciate.json.JsonName;
import org.codehaus.jackson.annotate.JsonProperty;
import org.gedcomx.agent.Agent;
import org.gedcomx.common.Attribution;
import org.gedcomx.conclusion.*;
import org.gedcomx.links.HypermediaEnabledData;
import org.gedcomx.records.Collection;
import org.gedcomx.records.Field;
import org.gedcomx.records.RecordDescriptor;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.rt.GedcomxModelVisitor;
import org.gedcomx.rt.MediaTypeDefinition;
import org.gedcomx.rt.Model;
import org.gedcomx.rt.json.JsonElementWrapper;
import org.gedcomx.source.SourceDescription;

import javax.xml.XMLConstants;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>The GEDCOM X media types define the serialization formats of the GEDCOM X conceptual model. The canonical documentation
 * is provided by the formal specification documents:</p>
 *
 * <ul>
 *   <li><a href="https://github.com/FamilySearch/gedcomx/blob/master/specifications/conceptual-model-specification.md">The GEDCOM X Conceptual Model, Version 1.0</a></li>
 *   <li><a href="https://github.com/FamilySearch/gedcomx/blob/master/specifications/json-format-specification.md">The GEDCOM X JSON Format, Version 1.0</a></li>
 *   <li><a href="https://github.com/FamilySearch/gedcomx/blob/master/specifications/xml-format-specification.md">The GEDCOM X XML Format, Version 1.0</a></li>
 * </ul>
 *
 * <p>This documentation is provided as a non-normative reference guide.</p>
 *
 * @author Ryan Heaton
 */
@MediaTypeDefinition (
  id = "gx",
  name = "GEDCOM X",
  description = "The GEDCOM X media types define the serialization formats of the GEDCOM X conceptual model.",
  version = "1.0",
  xmlMediaType = GedcomxConstants.GEDCOMX_XML_MEDIA_TYPE,
  jsonMediaType = GedcomxConstants.GEDCOMX_JSON_MEDIA_TYPE,
  projectId = GedcomxConstants.GEDCOMX_PROJECT_ID,
  models = {
    @Model (
      id = "gx",
      namespace = GedcomxConstants.GEDCOMX_NAMESPACE,
      label = "GEDCOM X Model",
      description = "The core model for all GEDCOM X data types and elements."
    ),
    @Model (
      id = "types",
      namespace = GedcomxConstants.GEDCOMX_TYPES_NAMESPACE,
      label = "GEDCOM X Types",
      description = "The types model contains all of the types and constrained vocabulary for GEDCOM X data."
    )
  }
)
@XmlRootElement
@JsonElementWrapper (name = "gedcomx")
@XmlType ( name = "Gedcomx" , propOrder = { "attribution", "persons", "relationships", "sourceDescriptions", "agents", "events", "places", "documents", "collections", "fields", "recordDescriptors" })
public class Gedcomx extends HypermediaEnabledData {

  private String lang;
  private Attribution attribution;
  private List<Person> persons;
  private List<Relationship> relationships;
  private List<SourceDescription> sourceDescriptions;
  private List<Agent> agents;
  private List<Event> events;
  private List<PlaceDescription> places;
  private List<Document> documents;
  private List<Collection> collections;
  private List<Field> fields;
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
   * The attribution of this genealogical data.
   *
   * @return The attribution of this genealogical data.
   */
  public Attribution getAttribution() {
    return attribution;
  }

  /**
   * The attribution of this genealogical data.
   *
   * @param attribution The attribution of this genealogical data.
   */
  public void setAttribution(Attribution attribution) {
    this.attribution = attribution;
  }

  /**
   * The persons included in this genealogical data set.
   * 
   * @return The persons included in this genealogical data set.
   */
  @XmlElement (name="person")
  @JsonProperty ("persons")
  @JsonName ("persons")
  public List<Person> getPersons() {
    return persons;
  }

  /**
   * The persons included in this genealogical data set.
   * 
   * @param persons The persons included in this genealogical data set.
   */
  @JsonProperty ("persons")
  public void setPersons(List<Person> persons) {
    this.persons = persons;
  }


  /**
   * Add a person to the data set.
   *
   * @param person The person to be added.
   */
  public void addPerson( Person person ) {
    if (person != null) {
      if (persons == null)
        persons = new LinkedList<Person>();
      persons.add( person );
    }
  }
  
  /**
   * The relationships included in this genealogical data set.
   * 
   * @return The relationships included in this genealogical data set.
   */
  @XmlElement (name="relationship")
  @JsonProperty ("relationships")
  @JsonName ("relationships")
  public List<Relationship> getRelationships() {
    return relationships;
  }

  /**
   * The relationships included in this genealogical data set.
   * 
   * @param relationships The relationships included in this genealogical data set.
   */
  @JsonProperty ("relationships")
  public void setRelationships(List<Relationship> relationships) {
    this.relationships = relationships;
  }

  /**
   * Add a relationship to the data set.
   *
   * @param relationship The relationship to be added.
   */
  public void addRelationship( Relationship relationship ) {
    if (relationship != null) {
      if (relationships == null)
        relationships = new LinkedList<Relationship>();
      relationships.add( relationship );
    }
  }

  /**
   * The descriptions of sources included in this genealogical data set.
   * 
   * @return The descriptions of sources included in this genealogical data set.
   */
  @XmlElement (name="sourceDescription")
  @JsonProperty ("sourceDescriptions")
  @JsonName ("sourceDescriptions")
  public List<SourceDescription> getSourceDescriptions() {
    return sourceDescriptions;
  }

  /**
   * The descriptions of sources included in this genealogical data set.
   * 
   * @param sourceDescriptions The descriptions of sources included in this genealogical data set.
   */
  @JsonProperty ("sourceDescriptions")
  public void setSourceDescriptions(List<SourceDescription> sourceDescriptions) {
    this.sourceDescriptions = sourceDescriptions;
  }

  /**
   * Add a source description to the data set.
   *
   * @param sourceDescription The source description to be added.
   */
  public void addSourceDescription( SourceDescription sourceDescription ) {
    if (sourceDescription != null) {
      if (sourceDescriptions == null)
        sourceDescriptions = new LinkedList<SourceDescription>();
      sourceDescriptions.add( sourceDescription );
    }
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

  /**
   * Add a agent to the data set.
   *
   * @param agent The agent to be added.
   */
  public void addAgent( Agent agent ) {
    if (agent != null) {
      if (agents == null)
        agents = new LinkedList<Agent>();
      agents.add( agent );
    }
  }

  /**
   * The events included in this genealogical data set.
   *
   * @return The events included in this genealogical data set.
   */
  @XmlElement (name="event")
  @JsonProperty ("events")
  @JsonName ("events")
  public List<Event> getEvents() {
    return events;
  }

  /**
   * The events included in this genealogical data set.
   *
   * @param events The events included in this genealogical data set.
   */
  @JsonProperty ("events")
  public void setEvents(List<Event> events) {
    this.events = events;
  }

  /**
   * Add a event to the data set.
   *
   * @param event The event to be added.
   */
  public void addEvent( Event event ) {
    if (event != null) {
      if (events == null)
        events = new LinkedList<Event>();
      events.add( event );
    }
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
   * Add a place to the data set.
   *
   * @param place The place to be added.
   */
  public void addPlace( PlaceDescription place ) {
    if (place != null) {
      if (places == null)
        places = new LinkedList<PlaceDescription>();
      places.add( place );
    }
  }

  /**
   * The documents included in this genealogical data set.
   *
   * @return The documents included in this genealogical data set.
   */
  @XmlElement (name="document")
  @JsonProperty ("documents")
  @JsonName ("documents")
  public List<Document> getDocuments() {
    return documents;
  }

  /**
   * The documents included in this genealogical data set.
   *
   * @param documents The documents included in this genealogical data set.
   */
  @JsonProperty ("documents")
  public void setDocuments(List<Document> documents) {
    this.documents = documents;
  }

  /**
   * Add a document to the data set.
   *
   * @param document The document to be added.
   */
  public void addDocument( Document document ) {
    if (document != null) {
      if (documents == null)
        documents = new LinkedList<Document>();
      documents.add( document );
    }
  }

  /**
   * The collections included in this genealogical data set.
   *
   * @return The collections included in this genealogical data set.
   */
  @XmlElement (name="collection")
  @JsonProperty ("collections")
  @JsonName ("collections")
  @Facet ( name = GedcomxConstants.FACET_GEDCOMX_RECORD )
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
   * The extracted fields included in this genealogical data set.
   *
   * @return The extracted fields included in this genealogical data set.
   */
  @XmlElement (name="field")
  @JsonProperty ("fields")
  @JsonName ("fields")
  @org.codehaus.enunciate.Facet ( name = GedcomxConstants.FACET_GEDCOMX_RECORD )
  public List<Field> getFields() {
    return fields;
  }

  /**
   * The extracted fields included in this genealogical data set.
   *
   * @param fields The extracted fields included in this genealogical data set.
   */
  @JsonProperty ("fields")
  public void setFields(List<Field> fields) {
    this.fields = fields;
  }

  /**
   * The record descriptors included in this genealogical data set.
   *
   * @return The record descriptors included in this genealogical data set.
   */
  @XmlElement (name="recordDescriptor")
  @JsonProperty ("recordDescriptors")
  @JsonName ("recordDescriptors")
  @Facet ( name = GedcomxConstants.FACET_GEDCOMX_RECORD )
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
   * Add a recordDescriptor to the data set.
   *
   * @param recordDescriptor The recordDescriptor to be added.
   */
  public void addRecordDescriptor( RecordDescriptor recordDescriptor ) {
    if (recordDescriptor != null) {
      if (recordDescriptors == null)
        recordDescriptors = new LinkedList<RecordDescriptor>();
      recordDescriptors.add( recordDescriptor );
    }
  }

  /**
   * Accept a visitor.
   *
   * @param visitor The visitor.
   */
  public void accept(GedcomxModelVisitor visitor) {
    visitor.visitGedcomx(this);
  }

}