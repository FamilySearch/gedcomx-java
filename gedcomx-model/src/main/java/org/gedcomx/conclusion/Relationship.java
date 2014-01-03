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
import org.codehaus.enunciate.qname.XmlQNameEnumRef;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.gedcomx.common.*;
import org.gedcomx.links.Link;
import org.gedcomx.records.Field;
import org.gedcomx.records.HasFields;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.rt.GedcomxModelVisitor;
import org.gedcomx.rt.RDFRange;
import org.gedcomx.rt.RDFSubPropertyOf;
import org.gedcomx.rt.json.JsonElementWrapper;
import org.gedcomx.source.SourceDescription;
import org.gedcomx.source.SourceReference;
import org.gedcomx.types.ConfidenceLevel;
import org.gedcomx.types.RelationshipType;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * A relationship between two or more persons.
 *
 * @author Ryan Heaton
 */
@XmlRootElement
@JsonElementWrapper ( name = "relationships" )
@XmlType ( name = "Relationship", propOrder = { "person1", "person2", "facts", "fields"} )
public class Relationship extends Subject implements HasFacts, HasFields {

  private URI type;
  private ResourceReference person1;
  private ResourceReference person2;
  private List<Fact> facts;
  private List<Field> fields;

  @Override
  public Relationship id(String id) {
    return (Relationship) super.id(id);
  }

  @Override
  public Relationship link(String rel, URI href) {
    return (Relationship) super.link(rel, href);
  }

  @Override
  public Relationship link(Link link) {
    return (Relationship) super.link(link);
  }

  @Override
  public Relationship lang(String lang) {
    return (Relationship) super.lang(lang);
  }

  @Override
  public Relationship confidence(URI confidence) {
    return (Relationship) super.confidence(confidence);
  }

  @Override
  public Relationship confidence(ConfidenceLevel confidence) {
    return (Relationship) super.confidence(confidence);
  }

  @Override
  public Relationship source(SourceReference sourceReference) {
    return (Relationship) super.source(sourceReference);
  }

  @Override
  public Relationship source(SourceDescription source) {
    return (Relationship) super.source(source);
  }

  @Override
  public Relationship note(Note note) {
    return (Relationship) super.note(note);
  }

  @Override
  public Relationship analysis(ResourceReference analysis) {
    return (Relationship) super.analysis(analysis);
  }

  @Override
  public Relationship attribution(Attribution attribution) {
    return (Relationship) super.attribution(attribution);
  }

  @Override
  public Relationship analysis(Document analysis) {
    return (Relationship) super.analysis(analysis);
  }

  @Override
  public Relationship analysis(URI analysis) {
    return (Relationship) super.analysis(analysis);
  }

  @Override
  public Relationship extracted(Boolean extracted) {
    return (Relationship) super.extracted(extracted);
  }

  @Override
  public Relationship identifier(Identifier identifier) {
    return (Relationship) super.identifier(identifier);
  }

  @Override
  public Relationship evidence(EvidenceReference evidence) {
    return (Relationship) super.evidence(evidence);
  }

  public Relationship evidence(Relationship evidence) {
    if (evidence.getId() == null) {
      throw new IllegalArgumentException("Unable to add relationship as evidence: no id.");
    }
    return (Relationship) super.evidence(new EvidenceReference(URI.create("#" + evidence.getId())));
  }

  @Override
  public Relationship media(SourceReference media) {
    return (Relationship) super.media(media);
  }

  @Override
  public Relationship media(SourceDescription media) {
    return (Relationship) super.media(media);
  }

  /**
   * The type of this relationship.
   *
   * @return The type of this relationship.
   */
  @XmlAttribute
  @XmlQNameEnumRef (RelationshipType.class)
  public URI getType() {
    return type;
  }

  /**
   * The type of this relationship.
   *
   * @param type The type of this relationship.
   */
  public void setType(URI type) {
    this.type = type;
  }

  /**
   * Build out this relationship with a type.
   * @param type The type.
   * @return this.
   */
  public Relationship type(URI type) {
    setType(type);
    return this;
  }

  /**
   * Build out this relationship with a type.
   * @param type The type.
   * @return this.
   */
  public Relationship type(RelationshipType type) {
    setKnownType(type);
    return this;
  }

  /**
   * The enum referencing the known type of the relationship, or {@link org.gedcomx.types.RelationshipType#OTHER} if not known.
   *
   * @return The enum referencing the known type of the relationship, or {@link org.gedcomx.types.RelationshipType#OTHER} if not known.
   */
  @XmlTransient
  @JsonIgnore
  public RelationshipType getKnownType() {
    return getType() == null ? null : RelationshipType.fromQNameURI(getType());
  }

  /**
   * Set the relationship type from a known enumeration of relationship types.
   *
   * @param type The relationship type.
   */
  @JsonIgnore
  public void setKnownType(RelationshipType type) {
    setType(type == null ? null : URI.create(org.codehaus.enunciate.XmlQNameEnumUtil.toURIValue(type)));
  }

  /**
   * A reference to a person in the relationship. The name "person1" is used only to distinguish it from
   * the other person in this relationship and implies neither order nor role. When the relationship type
   * implies direction, it goes from "person1" to "person2".
   *
   * @return A reference to a person in the relationship. The name "person1" is used only to distinguish it from
   * the other person in this relationship and implies neither order nor role. When the relationship type
   * implies direction, it goes from "person1" to "person2".
   */
  @RDFRange (Person.class)
  @RDFSubPropertyOf ( "http://purl.org/dc/terms/hasPart")
  public ResourceReference getPerson1() {
    return person1;
  }

  /**
   * A reference to a person in the relationship. The name "person1" is used only to distinguish it from
   * the other person in this relationship and implies neither order nor role. When the relationship type
   * implies direction, it goes from "person1" to "person2".
   *
   * @param person1 A reference to a person in the relationship. The name "person1" is used only to distinguish it from
   * the other person in this relationship and implies neither order nor role. When the relationship type
   * implies direction, it goes from "person1" to "person2".
   */
  public void setPerson1(ResourceReference person1) {
    this.person1 = person1;
  }

  /**
   * Build out this relationship with a reference to person 1.
   * 
   * @param person1 person 1.
   * @return this.
   */
  public Relationship person1(ResourceReference person1) {
    setPerson1(person1);
    return this;
  }

  /**
   * Build out this relationship with a reference to person 1.
   * 
   * @param person1 person 1.
   * @return this.
   */
  public Relationship person1(Person person1) {
    if (person1.getId() == null) {
      throw new IllegalStateException("Cannot reference person1: no id.");
    }
    setPerson1(new ResourceReference(URI.create("#" + person1.getId())));
    return this;
  }

  /**
   * A reference to a person in the relationship. The name "person2" is used only to distinguish it from
   * the other person in this relationship and implies neither order nor role. When the relationship type
   * implies direction, it goes from "person1" to "person2".
   *
   * @return A reference to a person in the relationship. The name "person2" is used only to distinguish it from
   * the other person in this relationship and implies neither order nor role. When the relationship type
   * implies direction, it goes from "person1" to "person2".
   */
  @RDFRange (Person.class)
  @RDFSubPropertyOf ( "http://purl.org/dc/terms/hasPart")
  public ResourceReference getPerson2() {
    return person2;
  }

  /**
   * A reference to a person in the relationship. The name "person2" is used only to distinguish it from
   * the other person in this relationship and implies neither order nor role. When the relationship type
   * implies direction, it goes from "person1" to "person2".
   *
   * @param person2 A reference to a person in the relationship. The name "person2" is used only to distinguish it from
   * the other person in this relationship and implies neither order nor role. When the relationship type
   * implies direction, it goes from "person1" to "person2".
   */
  public void setPerson2(ResourceReference person2) {
    this.person2 = person2;
  }

  /**
   * Build out this relationship with a reference to person 2.
   *
   * @param person2 person 2.
   * @return this.
   */
  public Relationship person2(ResourceReference person2) {
    setPerson2(person2);
    return this;
  }

  /**
   * Build out this relationship with a reference to person 2.
   *
   * @param person2 person 2.
   * @return this.
   */
  public Relationship person2(Person person2) {
    if (person2.getId() == null) {
      throw new IllegalStateException("Cannot reference person2: no id.");
    }
    setPerson2(new ResourceReference(URI.create("#" + person2.getId())));
    return this;
  }

  /**
   * The fact conclusions for the relationship.
   *
   * @return The fact conclusions for the relationship.
   */
  @XmlElement(name="fact")
  @JsonProperty("facts")
  @JsonName("facts")
  public List<Fact> getFacts() {
    return facts;
  }

  /**
   * The fact conclusions for the relationship.
   *
   * @param facts The fact conclusions for the relationship.
   */
  @JsonProperty("facts")
  public void setFacts(List<Fact> facts) {
    this.facts = facts;
  }

  /**
   * Build out this relationship with a fact.
   * @param fact The fact.
   * @return this
   */
  public Relationship fact(Fact fact) {
    addFact(fact);
    return this;
  }

  /**
   * Add a fact conclusion.
   *
   * @param fact The fact conclusion to be added.
   */
  public void addFact(Fact fact) {
    if (fact != null) {
      if (facts == null) {
        facts = new ArrayList<Fact>();
      }
      facts.add(fact);
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
   * Build out this relationship with a field.
   *
   * @param field The field.
   * @return this.
   */
  public Relationship field(Field field) {
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
    visitor.visitRelationship(this);
  }

  public void embed(Relationship relationship) {
    if (relationship.facts != null) {
      this.facts = this.facts == null ? new ArrayList<Fact>() : this.facts;
      this.facts.addAll(relationship.facts);
    }
    if (relationship.fields != null) {
      this.fields = this.fields == null ? new ArrayList<Field>() : this.fields;
      this.fields.addAll(relationship.fields);
    }
    super.embed(relationship);
  }
}
