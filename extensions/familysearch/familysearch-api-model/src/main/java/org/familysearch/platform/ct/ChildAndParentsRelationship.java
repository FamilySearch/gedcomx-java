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

import org.codehaus.enunciate.json.JsonName;
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
@XmlType ( name = "ChildAndParentsRelationship", propOrder = { "father", "mother", "child", "fatherFacts", "motherFacts" } )
public class ChildAndParentsRelationship extends Subject {

  private ResourceReference father;
  private ResourceReference mother;
  private ResourceReference child;
  private List<Fact> fatherFacts;
  private List<Fact> motherFacts;

  /**
   * The father of the child.
   *
   * @return The father of the child.
   */
  public ResourceReference getFather() {
    return father;
  }

  /**
   * Set the father of the child.
   *
   * @param father The father of the child.
   */
  public void setFather(ResourceReference father) {
    this.father = father;
  }

  /**
   * Build out this relationship with a reference to the father.
   *
   * @param father the father.
   * @return this.
   */
  public ChildAndParentsRelationship father(ResourceReference father) {
    setFather(father);
    return this;
  }

  /**
   * Build out this relationship with a reference to the father.
   *
   * @param father the father.
   * @return this.
   */
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
  public ResourceReference getMother() {
    return mother;
  }

  /**
   * Set the mother of the child.
   *
   * @param mother The mother of the child.
   */
  public void setMother(ResourceReference mother) {
    this.mother = mother;
  }

  /**
   * Build out this relationship with a reference to the mother.
   *
   * @param mother the mother.
   * @return this.
   */
  public ChildAndParentsRelationship mother(ResourceReference mother) {
    setMother(mother);
    return this;
  }

  /**
   * Build out this relationship with a reference to the mother.
   *
   * @param mother the mother.
   * @return this.
   */
  public ChildAndParentsRelationship mother(Person mother) {
    if (mother.getId() == null) {
      throw new IllegalStateException("Cannot reference mother: no id.");
    }
    setMother(new ResourceReference(URI.create("#" + mother.getId())));
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
   * The fact conclusions for the father.
   *
   * @return The fact conclusions for the father.
   */
  @XmlElement (name="fatherFact")
  @JsonProperty ("fatherFacts")
  @JsonName ("fatherFacts")
  public List<Fact> getFatherFacts() {
    return fatherFacts;
  }

  /**
   * The fact conclusions for the father.
   *
   * @param facts The fact conclusions for the father.
   */
  @JsonProperty("fatherFacts")
  public void setFatherFacts(List<Fact> facts) {
    this.fatherFacts = facts;
  }

  /**
   * Build out this relationship with a father fact.
   * 
   * @param fact The father fact.
   * @return The father fact.
   */
  public ChildAndParentsRelationship fatherFact(Fact fact) {
    this.addFatherFact(fact);
    return this;
  }

  /**
   * Add a fact conclusion for the father.
   *
   * @param fact The fact conclusion to be added.
   */
  public void addFatherFact(Fact fact) {
    if (fact != null) {
      if (fatherFacts == null) {
        fatherFacts = new ArrayList<Fact>();
      }
      fatherFacts.add(fact);
    }
  }

  /**
   * The fact conclusions for the mother.
   *
   * @return The fact conclusions for the mother.
   */
  @XmlElement (name="motherFact")
  @JsonProperty ("motherFacts")
  @JsonName ("motherFacts")
  public List<Fact> getMotherFacts() {
    return motherFacts;
  }

  /**
   * The fact conclusions for the mother.
   *
   * @param facts The fact conclusions for the mother.
   */
  @JsonProperty("motherFacts")
  public void setMotherFacts(List<Fact> facts) {
    this.motherFacts = facts;
  }

  /**
   * Build out this relationship with a mother fact.
   *
   * @param fact The mother fact.
   * @return The mother fact.
   */
  public ChildAndParentsRelationship motherFact(Fact fact) {
    this.addMotherFact(fact);
    return this;
  }

  /**
   * Add a fact conclusion for the mother.
   *
   * @param fact The fact conclusion to be added.
   */
  public void addMotherFact(Fact fact) {
    if (fact != null) {
      if (motherFacts == null) {
        motherFacts = new ArrayList<Fact>();
      }
      motherFacts.add(fact);
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
    if (relationship.motherFacts != null) {
      this.motherFacts = this.motherFacts == null ? new ArrayList<Fact>() : this.motherFacts;
      this.motherFacts.addAll(relationship.motherFacts);
    }
    if (relationship.fatherFacts != null) {
      this.fatherFacts = this.fatherFacts == null ? new ArrayList<Fact>() : this.fatherFacts;
      this.fatherFacts.addAll(relationship.fatherFacts);
    }
    super.embed(relationship);
  }
}
