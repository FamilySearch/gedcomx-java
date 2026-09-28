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
package org.familysearch.platform.ct;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.webcohesion.enunciate.metadata.qname.XmlQNameEnumRef;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlType;

import org.familysearch.platform.rt.FamilySearchPlatformModelVisitor;
import org.gedcomx.common.ResourceReference;
import org.gedcomx.common.URI;
import org.gedcomx.conclusion.Fact;
import org.gedcomx.conclusion.HasFacts;
import org.gedcomx.conclusion.Person;
import org.gedcomx.conclusion.Subject;
import org.gedcomx.rt.json.JsonElementWrapper;

/**
 * A FamilySearch-specific association between two persons representing a non-genealogical relationship.
 */
@XmlRootElement
@JsonElementWrapper ( name = "associations" )
@XmlType ( name = "Association", propOrder = { "person1", "person2", "facts" } )
@JsonInclude ( JsonInclude.Include.NON_NULL )
@Schema(description = "A FamilySearch-specific association between two persons representing a non-genealogical relationship.")
public class Association extends Subject implements HasFacts {

  @Schema(description = "The type of the association.")
  private URI type;

  @Schema(description = "Person 1 of the association.")
  private ResourceReference person1;

  @Schema(description = "Person 2 of the association.")
  private ResourceReference person2;

  @Schema(description = "The fact conclusions about this association.")
  private List<Fact> facts;

  /**
   * The type of the association.
   *
   * @return The type of the association.
   */
  @XmlAttribute
  @XmlQNameEnumRef ( FamilySearchAssociationType.class )
  public URI getType() {
    return type;
  }

  /**
   * The type of the association.
   *
   * @param type The type of the association.
   */
  public void setType(URI type) {
    this.type = type;
  }

  /**
   * Build out this association with a type.
   *
   * @param type The type.
   * @return this
   */
  public Association type(URI type) {
    setType(type);
    return this;
  }

  /**
   * Build out this association with a known type.
   *
   * @param type The known type.
   * @return this
   */
  public Association type(FamilySearchAssociationType type) {
    setKnownType(type);
    return this;
  }

  /**
   * The enum referencing the known type of the association, or {@link FamilySearchAssociationType#OTHER} if not known.
   *
   * @return The enum referencing the known type of the association.
   */
  @XmlTransient
  @JsonIgnore
  public FamilySearchAssociationType getKnownType() {
    return getType() == null ? null : FamilySearchAssociationType.fromQNameURI(getType());
  }

  /**
   * Set the type of this association from a known enumeration of association types.
   *
   * @param knownType The association type.
   */
  @JsonIgnore
  public void setKnownType(FamilySearchAssociationType knownType) {
    setType(knownType == null ? null : knownType.toQNameURI());
  }

  /**
   * Person 1 of the association.
   *
   * @return Person 1 of the association.
   */
  public ResourceReference getPerson1() {
    return person1;
  }

  /**
   * Person 1 of the association.
   *
   * @param person1 Person 1 of the association.
   */
  public void setPerson1(ResourceReference person1) {
    this.person1 = person1;
  }

  /**
   * Build out this association with a reference to person 1.
   *
   * @param person1 The person 1 reference.
   * @return this.
   */
  public Association person1(ResourceReference person1) {
    setPerson1(person1);
    return this;
  }

  /**
   * Build out this association with a reference to person 1.
   *
   * @param person1 The person 1.
   * @return this.
   */
  public Association person1(Person person1) {
    if (person1.getId() == null) {
      throw new IllegalStateException("Cannot reference person1: no id.");
    }
    setPerson1(new ResourceReference(URI.create("#" + person1.getId())));
    return this;
  }

  /**
   * Person 2 of the association.
   *
   * @return Person 2 of the association.
   */
  public ResourceReference getPerson2() {
    return person2;
  }

  /**
   * Person 2 of the association.
   *
   * @param person2 Person 2 of the association.
   */
  public void setPerson2(ResourceReference person2) {
    this.person2 = person2;
  }

  /**
   * Build out this association with a reference to person 2.
   *
   * @param person2 The person 2 reference.
   * @return this.
   */
  public Association person2(ResourceReference person2) {
    setPerson2(person2);
    return this;
  }

  /**
   * Build out this association with a reference to person 2.
   *
   * @param person2 The person 2.
   * @return this.
   */
  public Association person2(Person person2) {
    if (person2.getId() == null) {
      throw new IllegalStateException("Cannot reference person2: no id.");
    }
    setPerson2(new ResourceReference(URI.create("#" + person2.getId())));
    return this;
  }

  /**
   * The fact conclusions about this association.
   *
   * @return The fact conclusions about this association.
   */
  @XmlElement ( name = "fact" )
  @JsonProperty ( "facts" )
  public List<Fact> getFacts() {
    return facts;
  }

  /**
   * The fact conclusions about this association.
   *
   * @param facts The fact conclusions about this association.
   */
  @JsonProperty ( "facts" )
  public void setFacts(List<Fact> facts) {
    this.facts = facts;
  }

  /**
   * Build out this association with a fact.
   *
   * @param fact The fact.
   * @return this.
   */
  public Association fact(Fact fact) {
    addFact(fact);
    return this;
  }

  /**
   * Add a fact conclusion to this association.
   *
   * @param fact The fact conclusion to be added.
   */
  public void addFact(Fact fact) {
    if (fact != null) {
      if (facts == null) {
        facts = new ArrayList<>();
      }
      facts.add(fact);
    }
  }

  /**
   * Accept a visitor.
   *
   * @param visitor The visitor to accept.
   */
  public void accept(FamilySearchPlatformModelVisitor visitor) {
    visitor.visitAssociation(this);
  }

  public void embed(Association association) {
    if (association.facts != null) {
      this.facts = this.facts == null ? new ArrayList<>() : this.facts;
      this.facts.addAll(association.facts);
    }
    super.embed(association);
  }
}
