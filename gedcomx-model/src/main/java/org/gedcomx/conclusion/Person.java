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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.webcohesion.enunciate.metadata.Facet;
import org.gedcomx.common.*;
import org.gedcomx.links.Link;
import org.gedcomx.records.Field;
import org.gedcomx.records.HasFields;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.rt.GedcomxModelVisitor;
import org.gedcomx.rt.json.JsonElementWrapper;
import org.gedcomx.source.SourceDescription;
import org.gedcomx.source.SourceReference;
import org.gedcomx.types.ConfidenceLevel;
import org.gedcomx.types.FactType;
import org.gedcomx.types.GenderType;
import org.gedcomx.types.NameType;

import javax.xml.bind.annotation.*;
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
@JsonInclude ( JsonInclude.Include.NON_NULL )
public class Person extends Subject implements HasFacts, HasFields {

  private Boolean isPrivate;
  private Boolean living;
  private Boolean principal;
  private Gender gender;
  private List<Name> names;
  private List<Fact> facts;
  private List<Field> fields; // person-specific fields, such as used in an extracted historical record.
  private DisplayProperties display;

  @Override
  public Person id(String id) {
    return (Person) super.id(id);
  }

  @Override
  public Person extensionElement(Object element) {
    return (Person) super.extensionElement(element);
  }

  @Override
  public Person link(String rel, URI href) {
    return (Person) super.link(rel, href);
  }

  @Override
  public Person link(Link link) {
    return (Person) super.link(link);
  }

  @Override
  public Person lang(String lang) {
    return (Person) super.lang(lang);
  }

  @Override
  public Person confidence(URI confidence) {
    return (Person) super.confidence(confidence);
  }

  @Override
  public Person confidence(ConfidenceLevel confidence) {
    return (Person) super.confidence(confidence);
  }

  @Override
  public Person source(SourceReference sourceReference) {
    return (Person) super.source(sourceReference);
  }

  @Override
  public Person source(SourceDescription source) {
    return (Person) super.source(source);
  }

  @Override
  public Person note(Note note) {
    return (Person) super.note(note);
  }

  @Override
  public Person analysis(ResourceReference analysis) {
    return (Person) super.analysis(analysis);
  }

  @Override
  public Person attribution(Attribution attribution) {
    return (Person) super.attribution(attribution);
  }

  @Override
  public Person analysis(Document analysis) {
    return (Person) super.analysis(analysis);
  }

  @Override
  public Person analysis(URI analysis) {
    return (Person) super.analysis(analysis);
  }

  @Override
  public Person extracted(Boolean extracted) {
    return (Person) super.extracted(extracted);
  }

  @Override
  public Person identifier(Identifier identifier) {
    return (Person) super.identifier(identifier);
  }

  @Override
  public Person evidence(EvidenceReference evidence) {
    return (Person) super.evidence(evidence);
  }

  public Person evidence(Person evidence) {
    if (evidence.getId() == null) {
      throw new IllegalArgumentException("Unable to add person as evidence: no id.");
    }
    return (Person) super.evidence(new EvidenceReference(URI.create("#" + evidence.getId())));
  }

  /**
   * References to the persona being referenced for this subject.
   *
   * @return References to the persona being referenced.
   */
  @XmlTransient
  @JsonIgnore
  public List<EvidenceReference> getPersonaReferences() {
    return getEvidence();
  }

  /**
   * References to the persona being referenced for this subject.
   *
   * @param refs References to the persona being referenced.
   */
  @JsonIgnore
  public void setPersonaReferences(List<EvidenceReference> refs) {
    setEvidence(refs);
  }

  /**
   * Build up this subject with an persona reference.
   *
   * @param persona The persona reference.
   * @return this.
   */
  public Subject personaReference(EvidenceReference persona) {
    addEvidence(persona);
    return this;
  }

  /**
   * Add an persona reference for this subject.
   *
   * @param personaRef The persona to be added.
   */
  public void addPersona(EvidenceReference personaRef) {
    addEvidence(personaRef);
  }

  @Override
  public Person media(SourceReference media) {
    return (Person) super.media(media);
  }

  @Override
  public Person media(SourceDescription media) {
    return (Person) super.media(media);
  }

  @Override
  public Person sortKey(String sortKey) {
    return (Person) super.sortKey(sortKey);
  }

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
  @Facet( GedcomxConstants.FACET_FS_FT_READ_ONLY )
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
   * Build out this person with a living flag.
   * @param living The flag.
   * @return this.
   */
  public Person living(Boolean living) {
    setLiving(living);
    return this;
  }

  /**
   * Indicator of whether this person is the "principal" person extracted from the record. Applicable
   * only to extracted persons. The meaning of this flag outside the scope of an extracted person is undefined.
   *
   * @return Whether this person is the "principal" person extracted from the record.
   */
  @XmlAttribute
  @Facet( GedcomxConstants.FACET_GEDCOMX_RECORD)
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
   * Build out this person with a principal flag.
   *
   * @param principal The principal flag.
   * @return this
   */
  public Person principal(Boolean principal) {
    setPrincipal(principal);
    return this;
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
   * Build out this person with a gender.
   * @param gender The gender.
   * @return this.
   */
  public Person gender(Gender gender) {
    setGender(gender);
    return this;
  }

  /**
   * Build out this person with a gender.
   * @param gender The gender.
   * @return this.
   */
  public Person gender(GenderType gender) {
    setGender(new Gender().type(gender));
    return this;
  }

  /**
   * The name conclusions for the person.
   *
   * @return The name conclusions for the person.
   */
  @XmlElement(name="name")
  @JsonProperty("names")
  public List<Name> getNames() {
    return names;
  }

  /**
   * Get the first name of this person.
   *
   * @return The first name of this person.
   */
  @XmlTransient
  @JsonIgnore
  public Name getName() {
    return this.names != null && this.names.size() > 0 ? this.names.get(0) : null;
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
   * Get the preferred name of the person. If no preferred name is specified, then the first name is returned.
   *
   * @return the preferred name of the person or first name if there is no preferred name.
   */
  @XmlTransient
  @JsonIgnore
  public Name getPreferredName() {
    if(this.names == null || this.names.size() <= 0) {
      return null;
    }

    for(Name name : this.names) {
      if(name.getPreferred() != null) {
        if(name.getPreferred()) {
          return name;
        }
      }
    }

    return this.names.get(0);
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
   * Build out this person with a name.
   * @param name The name.
   * @return this.
   */
  public Person name(Name name) {
    addName(name);
    return this;
  }

  /**
   * Build out this person with a name.
   * @param name The name.
   * @return this.
   */
  public Person name(String name) {
    addName(new Name().nameForm(new NameForm().fullText(name)));
    return this;
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
   * Build out this person with a fact.
   *
   * @param fact The fact.
   * @return this.
   */
  public Person fact(Fact fact) {
    addFact(fact);
    return this;
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
  @Facet ( GedcomxConstants.FACET_GEDCOMX_RS )
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
   * Build out this person with a display exension.
   *
   * @param display the display.
   * @return this
   */
  public Person displayExtension(DisplayProperties display) {
    setDisplayExtension(display);
    return this;
  }

  /**
   * Tell whether the given resource reference is referencing the current person
   * @param personReference - Local reference to a person URI.
   * @return true if the personReference is referencing this person. False otherwise.
   */
  private boolean isReferencedPerson(ResourceReference personReference) {
    if (personReference != null && personReference.getResource() != null) {
      String uri = personReference.getResource().toString();
      if (!uri.startsWith("#")) {
        throw new IllegalArgumentException("Must use local references to persons that are included in a GedcomX document.");
      }
      if (getId() != null && personReference.getResource().toString().equals("#" + getId())) {
        return true;
      }
    }
    return false;
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
  @Facet ( GedcomxConstants.FACET_GEDCOMX_RECORD )
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
   * Build out this person with a field.
   * @param field The field.
   * @return this.
   */
  public Person field(Field field) {
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
   * Embed the specified person into this one.
   *
   * @param person The person to embed.
   */
  public void embed(Person person) {
    this.isPrivate = this.isPrivate == null ? person.isPrivate : this.isPrivate;
    this.living = this.living == null ? person.living : this.living;
    this.principal = this.principal == null ? person.principal : this.principal;
    this.gender = this.gender == null ? person.gender : this.gender;
    if (this.display != null && person.display != null) {
      this.display.embed(person.display);
    }
    else if (person.display != null) {
      this.display = person.display;
    }
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

  /**
   * Provide a simple toString() method.
   */
  @Override
  public String toString() {
    if ((names != null) && (names.size() > 0)) {
      return getId() + ": " + names.get(0).toString();
    }

    return super.toString();
  }
}
