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
package org.gedcomx;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.webcohesion.enunciate.metadata.Facet;
import org.gedcomx.agent.Agent;
import org.gedcomx.common.Attribution;
import org.gedcomx.common.ResourceReference;
import org.gedcomx.common.URI;
import org.gedcomx.conclusion.*;
import org.gedcomx.links.HypermediaEnabledData;
import org.gedcomx.links.Link;
import org.gedcomx.records.Collection;
import org.gedcomx.records.Field;
import org.gedcomx.records.RecordDescriptor;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.rt.GedcomxModelVisitor;
import org.gedcomx.rt.MediaTypeDefinition;
import org.gedcomx.rt.Model;
import org.gedcomx.rt.json.JsonElementWrapper;
import org.gedcomx.source.SourceDescription;
import org.gedcomx.types.RelationshipType;

import javax.xml.XMLConstants;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>The GEDCOM X data formats define the serialization formats of the GEDCOM X conceptual model. The canonical documentation
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
  name = "GEDCOM X",
  description = "The GEDCOM X data formats define the serialization formats of the GEDCOM X conceptual model.",
  version = "1.0",
  xmlMediaType = GedcomxConstants.GEDCOMX_XML_MEDIA_TYPE,
  jsonMediaType = GedcomxConstants.GEDCOMX_JSON_MEDIA_TYPE,
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
@JsonInclude ( JsonInclude.Include.NON_NULL )
@XmlType ( name = "Gedcomx" , propOrder = { "attribution", "persons", "relationships", "sourceDescriptions", "agents", "events", "places", "documents", "collections", "fields", "recordDescriptors" })
public class Gedcomx extends HypermediaEnabledData {

  private String lang;
  private URI descriptionRef;
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
   * Build out this envelope with a lang.
   * 
   * @param lang The lang.
   * @return this.
   */
  public Gedcomx lang(String lang) {
    setLang(lang);
    return this;
  }

  /**
   * A reference to a description of this data set.
   *
   * @return A reference to a description of this data set.
   */
  @XmlAttribute ( name = "description" )
  @JsonProperty ( "description" )
  @Facet ( GedcomxConstants.FACET_GEDCOMX_RECORD )
  public URI getDescriptionRef() {
    return descriptionRef;
  }

  /**
   * A reference to a description of this data set.
   *
   * @param descriptionRef A reference to a description of this data set.
   */
  @JsonProperty ( "description" )
  public void setDescriptionRef(URI descriptionRef) {
    this.descriptionRef = descriptionRef;
  }

  /**
   * Build out this with a description ref.
   * 
   * @param descriptionRef The description ref.
   * @return this.
   */
  public Gedcomx descriptionRef(URI descriptionRef) {
    setDescriptionRef(descriptionRef);
    return this;
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
   * Build this out with an attribution.
   * @param attribution The attribution.
   * @return this.
   */
  public Gedcomx attribution(Attribution attribution) {
    setAttribution(attribution);
    return this;
  }

  /**
   * Get the first person in the document.
   *
   * @return The first person in the document.
   */
  @XmlTransient
  @JsonIgnore
  public Person getPerson() {
    return this.persons != null && this.persons.size() > 0 ? this.persons.get(0) : null;
  }

  /**
   * The persons included in this genealogical data set.
   * 
   * @return The persons included in this genealogical data set.
   */
  @XmlElement (name="person")
  @JsonProperty ("persons")
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
   * Build this out with a person.
   * @param person The person.
   * @return this.
   */
  public Gedcomx person(Person person) {
    addPerson(person);
    return this;
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
   * Get the list of couple relationships in the document.
   *
   * @return The list of couple relationships in the document.
   */
  @XmlTransient
  @JsonIgnore
  public List<Relationship> getCoupleRelationships() {
    ArrayList<Relationship> filtered = null;
    if (this.relationships != null) {
      filtered = new ArrayList<Relationship>();
      for (Relationship relationship : this.relationships) {
        if (relationship.getKnownType() == RelationshipType.Couple) {
          filtered.add(relationship);
        }
      }
    }
    return filtered;
  }

  /**
   * Get the list of parent-child relationships in the document.
   *
   * @return The list of parent-child relationships in the document.
   */
  @XmlTransient
  @JsonIgnore
  public List<Relationship> getParentChildRelationships() {
    ArrayList<Relationship> filtered = null;
    if (this.relationships != null) {
      filtered = new ArrayList<Relationship>();
      for (Relationship relationship : this.relationships) {
        if (relationship.getKnownType() == RelationshipType.ParentChild) {
          filtered.add(relationship);
        }
      }
    }
    return filtered;
  }

  /**
   * Find the couple relationship (if any) that corresponds to the relationship between the parents in the given family.
   * @param family - Family to find the couple relationship for.
   * @return the couple relationship for the parents in the family, if any, or null if there isn't one (or if there are not two parents).
   */
  public Relationship findCoupleRelationship(FamilyView family) {
    return family == null ? null : findCoupleRelationship(family.getParent1(), family.getParent2());
  }

  /**
   * Find the couple relationship (if any) that corresponds to the relationship between the people with the given IDs.
   * @param person1 - person1 to find (i.e., husband)
   * @param person2 - person2 to find (i.e., wife)
   * @return the couple relationship for the parents in the family, if any, or null if there isn't one (or if there are not two parents).
   */
  public Relationship findCoupleRelationship(ResourceReference person1, ResourceReference person2) {
    if (getRelationships() != null) {
      for (Relationship relationship : getRelationships()) {
        if (relationship.getKnownType() == RelationshipType.Couple &&
                samePerson(person1, relationship.getPerson1()) &&
                samePerson(person2, relationship.getPerson2())) {
          return relationship;
        }
      }
    }
    return null;
  }

  /**
   * Find the parent-child relationship between the given two persons.
   * @param parent - Reference to the parent to find.
   * @param child - Reference to the child to find.
   * @return parent-child relationship for the given parent and child, or null if not found in the document.
   */
  public Relationship findParentChildRelationship(ResourceReference parent, ResourceReference child) {
    if (parent != null && child != null && getRelationships() != null &&
            parent.getResource() != null && child.getResource() != null) {
      for (Relationship relationship : getRelationships()) {
        if (relationship.getKnownType().equals(RelationshipType.ParentChild) &&
                samePerson(relationship.getPerson1(), parent) &&
                samePerson(relationship.getPerson2(), child)) {
          return relationship;
        }
      }
    }
    return null;
  }

  /**
   * Tell whether the given resource reference is referencing the current person
   * @param ref1 - Local reference to a person URI.
   * @return true if the personReference is referencing this person (or both are null). False otherwise.
   */
  protected static boolean samePerson(ResourceReference ref1, ResourceReference ref2) {
    return ref1 == ref2 ||
            (ref1 != null && ref1.getResource() != null &&
             ref2 != null && ref2.getResource() != null &&
             ref1.getResource().equals(ref2.getResource()));
  }

  /**
   * The relationships included in this genealogical data set.
   * 
   * @return The relationships included in this genealogical data set.
   */
  @XmlElement (name="relationship")
  @JsonProperty ("relationships")
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
   * Build this out with a relationship.
   * @param relationship The relationship.
   * @return this.
   */
  public Gedcomx relationship(Relationship relationship) {
    addRelationship(relationship);
    return this;
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
   * Get the first source description in the document.
   *
   * @return The first source description in the document.
   */
  @XmlTransient
  @JsonIgnore
  public SourceDescription getSourceDescription() {
    return this.sourceDescriptions != null && this.sourceDescriptions.size() > 0 ? this.sourceDescriptions.get(0) : null;
  }

  /**
   * Get the first source description in the document with the type that is specified.
   *
   * @param resourceType The URI resource type of the SourceDescription you are trying to find.
   *
   * @return The first source description in the document with the type that is specified..
   */
  public SourceDescription getSourceDescription(URI resourceType) {
    if (this.sourceDescriptions != null && this.sourceDescriptions.size() > 0) {
      for (SourceDescription sourceDescription : this.sourceDescriptions) {
        if (sourceDescription.getResourceType().equals(resourceType)) {
          return sourceDescription;
        }
      }
    }

    return null;
  }

  /**
   * The descriptions of sources included in this genealogical data set.
   * 
   * @return The descriptions of sources included in this genealogical data set.
   */
  @XmlElement (name="sourceDescription")
  @JsonProperty ("sourceDescriptions")
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
   * Build this out with a source description.
   * @param sourceDescription The source description.
   * @return this.
   */
  public Gedcomx sourceDescription(SourceDescription sourceDescription) {
    addSourceDescription(sourceDescription);
    return this;
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
   * Build this out with a agent.
   * @param agent The agent.
   * @return this.
   */
  public Gedcomx agent(Agent agent) {
    addAgent(agent);
    return this;
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
   * Build this out with a event.
   * @param event The event.
   * @return this.
   */
  public Gedcomx event(Event event) {
    addEvent(event);
    return this;
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
   * Build this out with a place.
   * @param place The place.
   * @return this.
   */
  public Gedcomx place(PlaceDescription place) {
    addPlace(place);
    return this;
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
   * Build this out with a document.
   * @param document The document.
   * @return this.
   */
  public Gedcomx document(Document document) {
    addDocument(document);
    return this;
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
  @Facet ( GedcomxConstants.FACET_GEDCOMX_RECORD )
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
   * Build this out with a collection.
   * @param collection The collection.
   * @return this.
   */
  public Gedcomx collection(Collection collection) {
    addCollection(collection);
    return this;
  }

  /**
   * Add a collection to the data set.
   *
   * @param collection The collection to be added.
   */
  public void addCollection( Collection collection ) {
    if (collection != null) {
      if (collections == null)
        collections = new LinkedList<Collection>();
      collections.add( collection );
    }
  }

  /**
   * The extracted fields included in this genealogical data set.  Fields that apply to a particular person,
   *   relationship or value should be included within that person or value, respectively.
   * Remaining fields that did not have a place within the person or relationship structure can be included here.
   * Also, fields that were extracted but not yet fit into a structure can also be included here.
   *
   * @return The extracted fields included in this genealogical data set.
   */
  @XmlElement (name="field")
  @JsonProperty ("fields")
  @com.webcohesion.enunciate.metadata.Facet( GedcomxConstants.FACET_GEDCOMX_RECORD )
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
   * Build this out with a field.
   * @param field The field.
   * @return this.
   */
  public Gedcomx field(Field field) {
    addField(field);
    return this;
  }

  /**
   * Add a field to the data set.
   *
   * @param field The field to be added.
   */
  public void addField( Field field ) {
    if (field != null) {
      if (fields == null)
        fields = new LinkedList<Field>();
      fields.add( field );
    }
  }

  /**
   * The record descriptors included in this genealogical data set.
   *
   * @return The record descriptors included in this genealogical data set.
   */
  @XmlElement (name="recordDescriptor")
  @JsonProperty ("recordDescriptors")
  @Facet ( GedcomxConstants.FACET_GEDCOMX_RECORD )
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
   * Build this out with a record descriptor.
   *
   * @param recordDescriptor The record descriptor.
   * @return this.
   */
  public Gedcomx recordDescriptor(RecordDescriptor recordDescriptor) {
    addRecordDescriptor(recordDescriptor);
    return this;
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

  public void embed(Gedcomx gedcomx) {
    List<Link> links = gedcomx.getLinks();
    if (links != null) {
      for (Link link : links) {
        boolean found = false;
        if (link.getRel() != null) {
          if (getLinks() != null) {
            for (Link target : getLinks()) {
              if (link.getRel().equals(target.getRel())) {
                found = true;
                break;
              }
            }
          }
        }

        if (!found) {
          addLink(link);
        }
      }
    }

    List<Person> persons = gedcomx.getPersons();
    if (persons != null) {
      for (Person person : persons) {
        boolean found = false;
        if (person.getId() != null) {
          if (getPersons() != null) {
            for (Person target : getPersons()) {
              if (person.getId().equals(target.getId())) {
                target.embed(person);
                found = true;
                break;
              }
            }
          }
        }

        if (!found) {
          addPerson(person);
        }
      }
    }

    List<Relationship> relationships = gedcomx.getRelationships();
    if (relationships != null) {
      for (Relationship relationship : relationships) {
        boolean found = false;
        if (relationship.getId() != null) {
          if (getRelationships() != null) {
            for (Relationship target : getRelationships()) {
              if (relationship.getId().equals(target.getId())) {
                target.embed(relationship);
                found = true;
                break;
              }
            }
          }
        }

        if (!found) {
          addRelationship(relationship);
        }
      }
    }

    List<SourceDescription> sourceDescriptions = gedcomx.getSourceDescriptions();
    if (sourceDescriptions != null) {
      for (SourceDescription sourceDescription : sourceDescriptions) {
        boolean found = false;
        if (sourceDescription.getId() != null) {
          if (getSourceDescriptions() != null) {
            for (SourceDescription target : getSourceDescriptions()) {
              if (sourceDescription.getId().equals(target.getId())) {
                target.embed(sourceDescription);
                found = true;
                break;
              }
            }
          }
        }

        if (!found) {
          addSourceDescription(sourceDescription);
        }
      }
    }

    List<Agent> agents = gedcomx.getAgents();
    if (agents != null) {
      for (Agent agent : agents) {
        boolean found = false;
        if (agent.getId() != null) {
          if (getAgents() != null) {
            for (Agent target : getAgents()) {
              if (agent.getId().equals(target.getId())) {
                target.embed(agent);
                found = true;
                break;
              }
            }
          }
        }

        if (!found) {
          addAgent(agent);
        }
      }
    }

    List<Event> events = gedcomx.getEvents();
    if (events != null) {
      for (Event event : events) {
        boolean found = false;
        if (event.getId() != null) {
          if (getEvents() != null) {
            for (Event target : getEvents()) {
              if (event.getId().equals(target.getId())) {
                target.embed(event);
                found = true;
                break;
              }
            }
          }
        }

        if (!found) {
          addEvent(event);
        }
      }
    }

    List<PlaceDescription> placeDescriptions = gedcomx.getPlaces();
    if (placeDescriptions != null) {
      for (PlaceDescription placeDescription : placeDescriptions) {
        boolean found = false;
        if (placeDescription.getId() != null) {
          if (getPlaces() != null) {
            for (PlaceDescription target : getPlaces()) {
              if (placeDescription.getId().equals(target.getId())) {
                target.embed(placeDescription);
                found = true;
                break;
              }
            }
          }
        }

        if (!found) {
          addPlace(placeDescription);
        }
      }
    }

    List<Document> documents = gedcomx.getDocuments();
    if (documents != null) {
      for (Document document : documents) {
        boolean found = false;
        if (document.getId() != null) {
          if (getDocuments() != null) {
            for (Document target : getDocuments()) {
              if (document.getId().equals(target.getId())) {
                target.embed(document);
                found = true;
                break;
              }
            }
          }
        }

        if (!found) {
          addDocument(document);
        }
      }
    }

    List<Collection> collections = gedcomx.getCollections();
    if (collections != null) {
      for (Collection collection : collections) {
        boolean found = false;
        if (collection.getId() != null) {
          if (getCollections() != null) {
            for (Collection target : getCollections()) {
              if (collection.getId().equals(target.getId())) {
                target.embed(collection);
                found = true;
                break;
              }
            }
          }
        }

        if (!found) {
          addCollection(collection);
        }
      }
    }

    List<Field> fields = gedcomx.getFields();
    if (fields != null) {
      for (Field field : fields) {
        boolean found = false;
        if (field.getId() != null) {
          if (getFields() != null) {
            for (Field target : getFields()) {
              if (field.getId().equals(target.getId())) {
                found = true;
                break;
              }
            }
          }
        }

        if (!found) {
          addField(field);
        }
      }
    }

    List<RecordDescriptor> recordDescriptors = gedcomx.getRecordDescriptors();
    if (recordDescriptors != null) {
      for (RecordDescriptor recordDescriptor : recordDescriptors) {
        boolean found = false;
        if (recordDescriptor.getId() != null) {
          if (getRecordDescriptors() != null) {
            for (RecordDescriptor target : getRecordDescriptors()) {
              if (recordDescriptor.getId().equals(target.getId())) {
                target.embed(recordDescriptor);
                found = true;
                break;
              }
            }
          }
        }

        if (!found) {
          addRecordDescriptor(recordDescriptor);
        }
      }
    }
  }

}