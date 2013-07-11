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
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.gedcomx.common.*;
import org.gedcomx.conclusion.Event;
import org.gedcomx.conclusion.Identifier;
import org.gedcomx.conclusion.Person;
import org.gedcomx.links.HypermediaEnabledData;
import org.gedcomx.rt.GedcomxModelVisitor;
import org.gedcomx.rt.json.JsonElementWrapper;
import org.gedcomx.source.SourceReference;
import org.gedcomx.types.IdentifierType;
import org.gedcomx.types.RecordType;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * A "record" describes the set of fields and other conclusions that are directly extracted from a source
 * during field-based indexed record extraction. A record is designed to more closely match the fields and structure
 * of the sources from which the data is being extracted.
 */
@XmlRootElement
@XmlType ( name = "Record", propOrder = { "persons", "principalPersons", "sources", "identifiers", "primaryEvent", "collectionRef", "descriptorRef", "fields", "notes", "attribution" } )
@JsonElementWrapper ( name = "records" )
public class Record extends HypermediaEnabledData implements Attributable, HasNotes {

  private URI type; //in source description coverage
  private List<Person> persons; //in gx root
  private List<ResourceReference> principalPersons; //in event
  private Event primaryEvent; //in gx root.
  private List<SourceReference> sources; //in source description.
  private List<Identifier> identifiers; // todo: add it to source description?
  private List<Field> fields; //in gx root.
  private List<Note> notes; //in source description.
  private Attribution attribution; //in source description.
  private ResourceReference descriptorRef; // todo: add it to source description?
  private ResourceReference collectionRef; // in source description.

  /**
   * The type of the record.
   *
   * @return The type of the record.
   */
  @XmlAttribute
  public URI getType() {
    return type;
  }

  /**
   * The type of the record.
   *
   * @param type The type of the record.
   */
  public void setType(URI type) {
    this.type = type;
  }

  /**
   * The known type of the record.
   *
   * @return The type of the record.
   */
  @XmlTransient
  @JsonIgnore
  public RecordType getKnownType() {
    return getType() == null ? null : RecordType.fromQNameURI(getType());
  }

  /**
   * The type of the record.
   *
   * @param type The type of the record.
   */
  @JsonIgnore
  public void setKnownType(RecordType type) {
    setType(type == null ? null : URI.create(org.codehaus.enunciate.XmlQNameEnumUtil.toURIValue(type)));
  }

  /**
   * The persons that are extracted from the record.
   *
   * @return The persons that are extracted from the record.
   */
  @XmlElement (name="person")
  @JsonProperty ("persons")
  @JsonName ("persons")
  public List<Person> getPersons() {
    return persons;
  }

  /**
   * The persons that are extracted from the record.
   *
   * @param persons The persons that are extracted from the record.
   */
  @JsonProperty ("persons")
  public void setPersons(List<Person> persons) {
    this.persons = persons;
  }

  /**
   * The principal person(s) of this record.
   *
   * @return The principal person(s) of this record.
   */
  @XmlElement ( name = "principalPerson" )
  @JsonName ( "principalPersons" )
  @JsonProperty ( "principalPersons" )
  public List<ResourceReference> getPrincipalPersons() {
    return principalPersons;
  }

  /**
   * The principal person(s) of this record.
   *
   * @param principalPersons The principal person(s) of this record.
   */
  @JsonProperty ( "principalPersons" )
  public void setPrincipalPersons(List<ResourceReference> principalPersons) {
    this.principalPersons = principalPersons;
  }

  /**
   * The primary event of this record.
   *
   * @return The primary event of this record.
   */
  public Event getPrimaryEvent() {
    return primaryEvent;
  }

  /**
   * The primary event of this record.
   *
   * @param primaryEvent The primary event of this record.
   */
  public void setPrimaryEvent(Event primaryEvent) {
    this.primaryEvent = primaryEvent;
  }

  /**
   * The source references for a record.
   *
   * @return The source references for a record.
   */
  @XmlElement (name="source")
  @JsonProperty ("sources")
  @JsonName ("sources")
  public List<SourceReference> getSources() {
    return sources;
  }

  /**
   * The source references for a record.
   *
   * @param sourceReferences The source references for a record.
   */
  @JsonProperty("sources")
  public void setSources(List<SourceReference> sourceReferences) {
    this.sources = sourceReferences;
  }

  /**
   * Add a source reference.
   *
   * @param source The source reference to be added.
   */
  public void addSource(SourceReference source) {
    if (source != null) {
      if (sources == null) {
        sources = new ArrayList<SourceReference>();
      }
      sources.add(source);
    }
  }

  /**
   * Find the long-term, persistent identifier for this person from the list of identifiers.
   *
   * @return The long-term, persistent identifier for this person.
   */
  @XmlTransient
  @JsonIgnore
  public URI getPersistentId() {
    URI identifier = null;
    if (this.identifiers != null) {
      for (Identifier id : this.identifiers) {
        if (IdentifierType.Persistent.equals(id.getKnownType())) {
          identifier = id.getValue();
          break;
        }
      }
    }
    return identifier;
  }

  /**
   * A long-term, persistent, globally unique identifier for this person.
   *
   * @param persistentId A long-term, persistent, globally unique identifier for this person.
   */
  @JsonIgnore
  public void setPersistentId(URI persistentId) {
    if (this.identifiers == null) {
      this.identifiers = new ArrayList<Identifier>();
    }

    //clear out any other primary ids.
    Iterator<Identifier> it = this.identifiers.iterator();
    while (it.hasNext()) {
      if (IdentifierType.Persistent.equals(it.next().getKnownType())) {
        it.remove();
      }
    }

    Identifier identifier = new Identifier();
    identifier.setKnownType(IdentifierType.Persistent);
    identifier.setValue(persistentId);
    this.identifiers.add(identifier);
  }

  /**
   * The list of identifiers for the person.
   *
   * @return The list of identifiers for the person.
   */
  @XmlElement (name="identifier")
  @JsonProperty ("identifiers")
  @JsonName ("identifiers")
  public List<Identifier> getIdentifiers() {
    return identifiers;
  }

  /**
   * The list of identifiers of the person.
   *
   * @param identifiers The list of identifiers of the person.
   */
  @JsonProperty ("identifiers")
  public void setIdentifiers(List<Identifier> identifiers) {
    this.identifiers = identifiers;
  }

  /**
   * The fields that were extracted from the source of this record.
   *
   * @return The fields that were extracted from the source of this record.
   */
  @XmlElement (name="field")
  @JsonProperty ("fields")
  @JsonName ("fields")
  public List<Field> getFields() {
    return fields;
  }

  /**
   * The fields that were extracted from the source of this record.
   *
   * @param fields The fields that were extracted from the source of this record.
   */
  @JsonProperty ("fields")
  public void setFields(List<Field> fields) {
    this.fields = fields;
  }

  /**
   * Notes about a source.
   *
   * @return Notes about a source.
   */
  @XmlElement (name="note")
  @JsonProperty ("notes")
  @JsonName ("notes")
  public List<Note> getNotes() {
    return notes;
  }

  /**
   * Notes about a source.
   *
   * @param notes Notes about a source.
   */
  @JsonProperty ("notes")
  public void setNotes(List<Note> notes) {
    this.notes = notes;
  }

  /**
   * The attribution metadata for this source description.
   *
   * @return The attribution metadata for this source description.
   */
  public Attribution getAttribution() {
    return attribution;
  }

  /**
   * The attribution metadata for this source description.
   *
   * @param attribution The attribution metadata for this source description.
   */
  public void setAttribution(Attribution attribution) {
    this.attribution = attribution;
  }

  /**
   * A reference to a descriptor for the record.
   *
   * @return A reference to a descriptor for the record.
   */
  @XmlElement ( name = "descriptor" )
  @JsonName ( "descriptor" )
  @JsonProperty ( "descriptor" )
  public ResourceReference getDescriptorRef() {
    return descriptorRef;
  }

  /**
   * A reference to a descriptor for the record.
   *
   * @param descriptorRef A reference to a descriptor for the record.
   */
  @JsonProperty ( "descriptor" )
  public void setDescriptorRef(ResourceReference descriptorRef) {
    this.descriptorRef = descriptorRef;
  }

  /**
   * A reference to the collection containing the record.
   *
   * @return A reference to the collection containing the record.
   */
  @XmlElement ( name = "collection" )
  @JsonName ( "collection" )
  @JsonProperty ( "collection" )
  public ResourceReference getCollectionRef() {
    return collectionRef;
  }

  /**
   * A reference to the collection containing the record.
   *
   * @param collectionRef A reference to the collection containing the record.
   */
  @JsonProperty ( "collection" )
  public void setCollectionRef(ResourceReference collectionRef) {
    this.collectionRef = collectionRef;
  }

  /**
   * Accept a visitor.
   *
   * @param visitor The visitor.
   */
  public void accept(GedcomxModelVisitor visitor) {
    visitor.visitRecord(this);
  }
}
