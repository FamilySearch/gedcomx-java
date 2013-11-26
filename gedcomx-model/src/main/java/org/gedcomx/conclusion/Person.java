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
package org.gedcomx.conclusion;

import org.codehaus.enunciate.Facet;
import org.codehaus.enunciate.json.JsonName;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.gedcomx.records.Field;
import org.gedcomx.records.HasFields;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.rt.GedcomxModelVisitor;
import org.gedcomx.rt.json.JsonElementWrapper;
import org.gedcomx.types.FactType;
import org.gedcomx.types.NameType;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * A person.
 *
 * @author Ryan Heaton
 */
@XmlRootElement
@JsonElementWrapper (name = "persons")
@XmlType ( name = "Person", propOrder = { "private", "living", "principal", "gender", "names", "facts", "fields", "displayExtension" } )
public class Person extends Subject implements HasFacts, HasFields {

  private Boolean isPrivate;
  private Boolean living;
  private Boolean principal;
  private Gender gender;
  private List<Name> names;
  private List<Fact> facts;
  private List<Field> fields; // person-specific fields, such as used in an extracted historical record.
  private DisplayProperties display;

  /**
   * Whether this person has been designated for limited distribution or display.
   *
   * @return Whether this person has been designated for limited distribution or display.
   */
  @XmlAttribute
  public Boolean getPrivate() {
    return isPrivate;
  }

  /**
   * Whether this person has been designated for limited distribution or display.
   *
   * @param isPrivate Whether this person has been designated for limited distribution or display.
   */
  public void setPrivate(Boolean isPrivate) {
    this.isPrivate = isPrivate;
  }

  /**
   * Living status of the person as treated by the system. The value of this property is intended
   * to be based on a system-specific calculation and therefore has limited portability. Conclusions
   * about the living status of a person can be modeled with a fact.
   *
   * @return Living status of the person as treated by the system.
   */
  @Facet( name = GedcomxConstants.FACET_FS_FT_READ_ONLY )
  public Boolean getLiving() {
    return living;
  }

  /**
   * Living status of the person as treated by the system. The value of this property is intended
   * to be based on a system-specific calculation and therefore has limited portability. Conclusions
   * about the living status of a person can be modeled with a fact.
   *
   * @param living Living status of the person as treated by the system.
   */
  public void setLiving(Boolean living) {
    this.living = living;
  }

  /**
   * Indicator of whether this person is the "principal" person extracted from the record. Applicable
   * only to extracted persons. The meaning of this flag outside the scope of an extracted person is undefined.
   *
   * @return Whether this person is the "principal" person extracted from the record.
   */
  @XmlAttribute
  @Facet( name = GedcomxConstants.FACET_GEDCOMX_RECORD)
  public Boolean getPrincipal() {
    return principal;
  }

  /**
   * Indicator of whether this person is the "principal" person extracted from the record. Applicable
   * only to extracted persons. The meaning of this flag outside the scope of an extracted person is undefined.
   *
   * @param principal Whether this person is the "principal" person extracted from the record.
   */
  public void setPrincipal(Boolean principal) {
    this.principal = principal;
  }

  /**
   * The gender conclusion for the person.
   *
   * @return The gender conclusion for the person.
   */
  public Gender getGender() {
    return gender;
  }

  /**
   * The gender conclusion for the person.
   *
   * @param gender The gender conclusion for the person.
   */
  public void setGender(Gender gender) {
    this.gender = gender;
  }

  /**
   * The name conclusions for the person.
   *
   * @return The name conclusions for the person.
   */
  @XmlElement(name="name")
  @JsonProperty("names")
  @JsonName("names")
  public List<Name> getNames() {
    return names;
  }

  /**
   * Get the first name of the specified type.
   *
   * @param type The type.
   * @return the first name in the name list of the specified type, or null if none.
   */
  @JsonIgnore
  public Name getFirstNameOfType(NameType type) {
    if (this.names == null) {
      return null;
    }

    for (Name name : this.names) {
      if (type.equals(name.getKnownType())) {
        return name;
      }
    }

    return null;
  }

  /**
   * The name conclusions for the person.
   *
   * @param names The name conclusions for the person.
   */
  @JsonProperty("names")
  public void setNames(List<Name> names) {
    this.names = names;
  }

  /**
   * Add a name conclusion to the person.
   *
   * @param name The name conclusion to be added.
   */
  public void addName(Name name) {
    if (name != null) {
      if (names == null) {
        names = new LinkedList<Name>();
      }
      names.add(name);
    }
  }

  /**
   * The fact conclusions for the person.
   *
   * @return The fact conclusions for the person.
   */
  @XmlElement(name="fact")
  @JsonProperty("facts")
  @JsonName("facts")
  public List<Fact> getFacts() {
    return facts;
  }

  /**
   * Get the first fact of the specified type.
   *
   * @param type The type.
   * @return the first fact in the fact list of the specified type, or null if none.
   */
  @JsonIgnore
  public Fact getFirstFactOfType(FactType type) {
    if (this.facts == null) {
      return null;
    }
    
    for (Fact fact : this.facts) {
      if (type.equals(fact.getKnownType())) {
        return fact;
      }
    }
    
    return null;
  }

  /**
   * Helper method for obtaining specific fact conclusions.
   *
   * @param factType The type of facts to return.
   * @return The fact conclusions that match the factType. An empty list will be returned if no facts are found.
   */
  @JsonIgnore
  public List<Fact> getFacts(FactType factType) {
    ArrayList<Fact> factsToReturn = new ArrayList<Fact>();
    if (facts != null && factType != null) {
      for (Fact fact : facts) {
        if (fact.getKnownType() != null && fact.getKnownType().equals(factType)) {
          factsToReturn.add(fact);
        }
      }
    }
    return factsToReturn;
  }

  /**
   * The fact conclusions for the person.
   *
   * @param facts The fact conclusions for the person.
   */
  @JsonProperty("facts")
  public void setFacts(List<Fact> facts) {
    this.facts = facts;
  }

  /**
   * Add a fact conclusion to the person.
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
   * Display properties for the person. Display properties are not specified by GEDCOM X core, but as extension elements by GEDCOM X RS.
   *
   * @return Display properties for the person. Display properties are not specified by GEDCOM X core, but as extension elements by GEDCOM X RS.
   */
  @XmlElement(name = "display")
  @JsonProperty("display")
  @Facet ( name = GedcomxConstants.FACET_GEDCOMX_RS )
  public DisplayProperties getDisplayExtension() {
    return display;
  }

  /**
   * Display properties for the person. Display properties are not specified by GEDCOM X core, but as extension elements by GEDCOM X RS.
   *
   * @param display Display properties for the person. Display properties are not specified by GEDCOM X core, but as extension elements by GEDCOM X RS.
   */
  @JsonProperty("display")
  public void setDisplayExtension(DisplayProperties display) {
    this.display = display;
  }

  /**
   * Accept a visitor.
   *
   * @param visitor The visitor.
   */
  public void accept(GedcomxModelVisitor visitor) {
    visitor.visitPerson(this);
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
   * Embed the specified person into this one.
   * 
   * @param person The person to embed.
   */
  public void embed(Person person) {
    this.isPrivate = this.isPrivate == null ? person.isPrivate : this.isPrivate;
    this.living = this.living == null ? person.living : this.living;
    this.principal = this.principal == null ? person.principal : this.principal;
    this.gender = this.gender == null ? person.gender : this.gender;
    this.display = this.display == null ? person.display : this.display;
    if (person.names != null) {
      this.names = this.names == null ? new ArrayList<Name>() : this.names;
      this.names.addAll(person.names);
    }
    if (person.facts != null) {
      this.facts = this.facts == null ? new ArrayList<Fact>() : this.facts;
      this.facts.addAll(person.facts);
    }
    if (person.fields != null) {
      this.fields = this.fields == null ? new ArrayList<Field>() : this.fields;
      this.fields.addAll(person.fields);
    }
    super.embed(person);

  }
}
