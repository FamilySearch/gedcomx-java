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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.familysearch.platform.rt.FamilySearchPlatformModelVisitor;
import org.gedcomx.common.ResourceReference;
import org.gedcomx.common.URI;
import org.gedcomx.conclusion.Fact;
import org.gedcomx.conclusion.Person;
import org.gedcomx.conclusion.Subject;
import org.gedcomx.rt.json.JsonElementWrapper;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * The FamilySearch-proprietary model for a relationship between a child and a pair of parents.
 */
@XmlRootElement
@JsonElementWrapper( name = "child-and-parents-relationships" )
@XmlType ( name = "ChildAndParentsRelationship", propOrder = { "parent1", "parent2", "child", "parent1Facts", "parent2Facts", "father", "mother", "fatherFacts", "motherFacts" } )
@JsonInclude ( JsonInclude.Include.NON_NULL )
public class ChildAndParentsRelationship extends Subject {

  private ResourceReference parent1;
  private ResourceReference parent2;
  private ResourceReference child;
  private List<Fact> parent1Facts;
  private List<Fact> parent2Facts;

  // todo GenericRelationshipTerms cleanup    remove @Deprecated values and methods
  @Deprecated
  private ResourceReference father;
  @Deprecated
  private ResourceReference mother;
  @Deprecated
  private List<Fact> fatherFacts;
  @Deprecated
  private List<Fact> motherFacts;

  /**
   * The parent1 of the child.
   *
   * @return The parent1 of the child.
   */
  public ResourceReference getParent1() {
    return parent1;
  }

  /**
   * Set the parent1 of the child.
   *
   * @param parent1 The parent1 of the child.
   */
  public void setParent1(ResourceReference parent1) {
    this.parent1 = parent1;
  }

  /**
   * Build out this relationship with a reference to the parent1.
   *
   * @param parent1 the parent1.
   * @return this.
   */
  public ChildAndParentsRelationship parent1(ResourceReference parent1) {
    setParent1(parent1);
    return this;
  }

  /**
   * Build out this relationship with a reference to the parent1.
   *
   * @param parent1 the parent1.
   * @return this.
   */
  public ChildAndParentsRelationship parent1(Person parent1) {
    if (parent1.getId() == null) {
      throw new IllegalStateException("Cannot reference parent1: no id.");
    }
    setParent1(new ResourceReference(URI.create("#" + parent1.getId())));
    return this;
  }

  /**
   * The parent2 of the child.
   *
   * @return The parent2 of the child.
   */
  public ResourceReference getParent2() {
    return parent2;
  }

  /**
   * Set the parent2 of the child.
   *
   * @param parent2 The parent2 of the child.
   */
  public void setParent2(ResourceReference parent2) {
    this.parent2 = parent2;
  }

  /**
   * Build out this relationship with a reference to the parent2.
   *
   * @param parent2 the parent2.
   * @return this.
   */
  public ChildAndParentsRelationship parent2(ResourceReference parent2) {
    setParent2(parent2);
    return this;
  }

  /**
   * Build out this relationship with a reference to the parent2.
   *
   * @param parent2 the parent2.
   * @return this.
   */
  public ChildAndParentsRelationship parent2(Person parent2) {
    if (parent2.getId() == null) {
      throw new IllegalStateException("Cannot reference parent2: no id.");
    }
    setParent2(new ResourceReference(URI.create("#" + parent2.getId())));
    return this;
  }

  /**
   * The child in the relationship.
   * @return child in the relationship.
   */
  public ResourceReference getChild() {
    return child;
  }

  /**
   * Set the child in the relationship.
   * @param  child child in the relationship.
   */
  public void setChild(ResourceReference child) {
    this.child = child;
  }

  /**
   * Build out this relationship with a reference to the child.
   *
   * @param child the child.
   * @return this.
   */
  public ChildAndParentsRelationship child(ResourceReference child) {
    setChild(child);
    return this;
  }

  /**
   * Build out this relationship with a reference to the child.
   *
   * @param child the child.
   * @return this.
   */
  public ChildAndParentsRelationship child(Person child) {
    if (child.getId() == null) {
      throw new IllegalStateException("Cannot reference child: no id.");
    }
    setChild(new ResourceReference(URI.create("#" + child.getId())));
    return this;
  }

  /**
   * The fact conclusions for the parent1.
   *
   * @return The fact conclusions for the parent1.
   */
  @XmlElement (name="parent1Fact")
  @JsonProperty ("parent1Facts") @org.codehaus.jackson.annotate.JsonProperty ("parent1Facts")
  public List<Fact> getParent1Facts() {
    return parent1Facts;
  }

  /**
   * The fact conclusions for the parent1.
   *
   * @param facts The fact conclusions for the parent1.
   */
  @JsonProperty("parent1Facts") @org.codehaus.jackson.annotate.JsonProperty("parent1Facts")
  public void setParent1Facts(List<Fact> facts) {
    this.parent1Facts = facts;
  }

  /**
   * Build out this relationship with a parent1 fact.
   *
   * @param fact The parent1 fact.
   * @return The parent1 fact.
   */
  public ChildAndParentsRelationship parent1Fact(Fact fact) {
    this.addParent1Fact(fact);
    return this;
  }

  /**
   * Add a fact conclusion for the parent1.
   *
   * @param fact The fact conclusion to be added.
   */
  public void addParent1Fact(Fact fact) {
    if (fact != null) {
      if (parent1Facts == null) {
        parent1Facts = new ArrayList<Fact>();
      }
      parent1Facts.add(fact);
    }
  }

  /**
   * The fact conclusions for the parent2.
   *
   * @return The fact conclusions for the parent2.
   */
  @XmlElement (name="parent2Fact")
  @JsonProperty ("parent2Facts") @org.codehaus.jackson.annotate.JsonProperty ("parent2Facts")
  public List<Fact> getParent2Facts() {
    return parent2Facts;
  }

  /**
   * The fact conclusions for the parent2.
   *
   * @param facts The fact conclusions for the parent2.
   */
  @JsonProperty("parent2Facts") @org.codehaus.jackson.annotate.JsonProperty("parent2Facts")
  public void setParent2Facts(List<Fact> facts) {
    this.parent2Facts = facts;
  }

  /**
   * Build out this relationship with a parent2 fact.
   *
   * @param fact The parent2 fact.
   * @return The parent2 fact.
   */
  public ChildAndParentsRelationship parent2Fact(Fact fact) {
    this.addParent2Fact(fact);
    return this;
  }

  /**
   * Add a fact conclusion for the parent2.
   *
   * @param fact The fact conclusion to be added.
   */
  public void addParent2Fact(Fact fact) {
    if (fact != null) {
      if (parent2Facts == null) {
        parent2Facts = new ArrayList<Fact>();
      }
      parent2Facts.add(fact);
    }
  }

  /**
   * Accept a visitor.
   *
   * @param visitor The visitor to accept.
   */
  public void accept(FamilySearchPlatformModelVisitor visitor) {
    visitor.visitChildAndParentsRelationship(this);
  }

  public void embed(ChildAndParentsRelationship relationship) {
    if (relationship.parent2Facts != null) {
      this.parent2Facts = this.parent2Facts == null ? new ArrayList<Fact>() : this.parent2Facts;
      this.parent2Facts.addAll(relationship.parent2Facts);
    }
    if (relationship.parent1Facts != null) {
      this.parent1Facts = this.parent1Facts == null ? new ArrayList<Fact>() : this.parent1Facts;
      this.parent1Facts.addAll(relationship.parent1Facts);
    }
    super.embed(relationship);
  }

  /////////////////////////////////////////////////////////////////////////////////////////////////
  /////////////////////////////////////////////////////////////////////////////////////////////////
  // Methods below are Deprecated
  /////////////////////////////////////////////////////////////////////////////////////////////////
  /////////////////////////////////////////////////////////////////////////////////////////////////

  /**
   * The father of the child.
   *
   * @return The father of the child.
   * @deprecated use {@link #getParent1()}
   */
  @Deprecated
  public ResourceReference getFather() {
    return father;
  }

  /**
   * Set the father of the child.
   *
   * @param father The father of the child.
   * @deprecated use {@link #setParent1(ResourceReference)}
   */
  @Deprecated
  public void setFather(ResourceReference father) {
    this.father = father;
  }
  @Deprecated
  public ChildAndParentsRelationship father(ResourceReference father) {
    setFather(father);
    return this;
  }
  @Deprecated
  public ChildAndParentsRelationship father(Person father) {
    if (father.getId() == null) {
      throw new IllegalStateException("Cannot reference father: no id.");
    }
    setFather(new ResourceReference(URI.create("#" + father.getId())));
    return this;
  }

  /**
   * The mother of the child.
   *
   * @return The mother of the child.
   */
  @Deprecated
  public ResourceReference getMother() {
    return mother;
  }

  /**
   * Set the mother of the child.
   *
   * @param mother The mother of the child.
   */
  @Deprecated
  public void setMother(ResourceReference mother) {
    this.mother = mother;
  }
  @Deprecated
  public ChildAndParentsRelationship mother(ResourceReference mother) {
    setMother(mother);
    return this;
  }
  @Deprecated
  public ChildAndParentsRelationship mother(Person mother) {
    if (mother.getId() == null) {
      throw new IllegalStateException("Cannot reference mother: no id.");
    }
    setMother(new ResourceReference(URI.create("#" + mother.getId())));
    return this;
  }

  /**
   * The fact conclusions for the father.
   *
   * @return The fact conclusions for the father.
   */
  @XmlElement (name="fatherFact")
  @JsonProperty ("fatherFacts") @org.codehaus.jackson.annotate.JsonProperty ("fatherFacts")
  @Deprecated
  public List<Fact> getFatherFacts() {
    return fatherFacts;
  }

  /**
   * The fact conclusions for the father.
   *
   * @param facts The fact conclusions for the father.
   */
  @JsonProperty("fatherFacts") @org.codehaus.jackson.annotate.JsonProperty("fatherFacts")
  @Deprecated
  public void setFatherFacts(List<Fact> facts) {
    this.fatherFacts = facts;
  }
  @Deprecated
  public void addFatherFact(Fact fact) {
    if (fact != null) {
      if (fatherFacts == null) {
        fatherFacts = new ArrayList<Fact>();
      }
      fatherFacts.add(fact);
    }
  }
  @Deprecated
  public ChildAndParentsRelationship fatherFact(Fact fact) {
    this.addFatherFact(fact);
    return this;
  }

  /**
   * The fact conclusions for the mother.
   *
   * @return The fact conclusions for the mother.
   */
  @XmlElement (name="motherFact")
  @JsonProperty ("motherFacts") @org.codehaus.jackson.annotate.JsonProperty ("motherFacts")
  @Deprecated
  public List<Fact> getMotherFacts() {
    return motherFacts;
  }

  /**
   * The fact conclusions for the mother.
   *
   * @param facts The fact conclusions for the mother.
   */
  @JsonProperty("motherFacts") @org.codehaus.jackson.annotate.JsonProperty("motherFacts")
  @Deprecated
  public void setMotherFacts(List<Fact> facts) {
    this.motherFacts = facts;
  }
  @Deprecated
  public void addMotherFact(Fact fact) {
    if (fact != null) {
      if (motherFacts == null) {
        motherFacts = new ArrayList<Fact>();
      }
      motherFacts.add(fact);
    }
  }
  @Deprecated
  public ChildAndParentsRelationship motherFact(Fact fact) {
    this.addMotherFact(fact);
    return this;
  }

}
