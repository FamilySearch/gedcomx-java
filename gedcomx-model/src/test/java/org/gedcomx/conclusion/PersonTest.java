package org.gedcomx.conclusion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.gedcomx.common.Attribution;
import org.gedcomx.common.ResourceReference;
import org.gedcomx.common.TextValue;
import org.gedcomx.common.URI;
import org.gedcomx.source.SourceReference;
import org.gedcomx.types.ConfidenceLevel;
import org.gedcomx.types.FactType;
import org.gedcomx.types.GenderType;
import org.gedcomx.types.IdentifierType;
import org.gedcomx.types.NamePartType;
import org.gedcomx.types.NameType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.gedcomx.rt.SerializationUtil.processThroughJson;
import static org.gedcomx.rt.SerializationUtil.processThroughXml;


/**
 * @author Ryan Heaton
 */
class PersonTest {

  /**
   * tests processing a WWW person through xml...
   */
  @Test
  void personXml() throws Exception {
    Person person = create();
    person = processThroughXml(person);
    assertPersonEquals(person);
  }

  /**
   * tests processing a WWW person through json...
   */
  @Test
  void personJson() throws Exception {
    Person person = create();
    person = processThroughJson(person);
    assertPersonEquals(person);
  }

  @Test
  void displayProperties() throws Exception {
    Person person = new Person();
    final DisplayProperties display = new DisplayProperties();
    display.setAscendancyNumber("1");
    display.setBirthDate("2");
    display.setBirthPlace("3");
    display.setDeathDate("4");
    display.setDeathPlace("5");
    display.setDescendancyNumber("6");
    display.setGender("7");
    display.setLifespan("8");
    display.setName("9");
    person.setDisplayExtension(display);
    person = processThroughXml(person);
    assertThat(person.getDisplayExtension().getAscendancyNumber()).isEqualTo("1");
    assertThat(person.getDisplayExtension().getBirthDate()).isEqualTo("2");
    assertThat(person.getDisplayExtension().getBirthPlace()).isEqualTo("3");
    assertThat(person.getDisplayExtension().getDeathDate()).isEqualTo("4");
    assertThat(person.getDisplayExtension().getDeathPlace()).isEqualTo("5");
    assertThat(person.getDisplayExtension().getDescendancyNumber()).isEqualTo("6");
    assertThat(person.getDisplayExtension().getGender()).isEqualTo("7");
    assertThat(person.getDisplayExtension().getLifespan()).isEqualTo("8");
    assertThat(person.getDisplayExtension().getName()).isEqualTo("9");

  }

  @Test
  void personPersistentIdHelpers() throws Exception {
    final Person person = create();
    assertPersonEquals(person);
    assertThat(person.getIdentifiers().size()).isEqualTo(2);
    assertThat(person.getPersistentId().toURI().toString()).isEqualTo("pal");

    person.setPersistentId(URI.create("urn:pal"));
    assertThat(person.getPersistentId().toURI().toString()).isEqualTo("urn:pal");

    person.getIdentifiers().clear();
    assertThat(person.getPersistentId()).isNull();

    person.setIdentifiers(null);
    assertThat(person.getPersistentId()).isNull();

    person.setPersistentId(URI.create("urn:pal"));
    assertThat(person.getPersistentId().toURI().toString()).isEqualTo("urn:pal");

    final Person ref = person.persistentId(URI.create("urn:pal"));
    assertThat(person.getPersistentId().toURI().toString()).isEqualTo("urn:pal");
    assertThat(ref).isSameAs(person);
  }

  @Test
  void personGetFirstNameOfType() throws Exception {
    final Person person = create();
    assertPersonEquals(person);
    assertThat(person.getFirstNameOfType(NameType.FormalName).toString()).isEqualTo("type=FormalName,nameForms[0]=primary form,pref=true");
    assertThat(person.getFirstNameOfType(NameType.BirthName)).isNull();
    person.setNames(null);
    assertThat(person.getFirstNameOfType(NameType.FormalName)).isNull();
  }

  @Test
  void personGetPreferredName() throws Exception {
    final Person person = create();
    assertPersonEquals(person);
    assertThat(person.getPreferredName().toString()).isEqualTo("type=FormalName,nameForms[0]=primary form,pref=true");
    person.setNames(null);
    assertThat(person.getPreferredName()).isNull();
  }

  @Test
  void factHelpers() throws Exception {
    final Fact fact = new Fact();

    final Person person = create();
    assertPersonEquals(person);
    person.addFact(fact);
    assertThat(person.getFacts().size()).isEqualTo(3);
    assertThat(person.getFacts(FactType.Adoption).size()).isEqualTo(1);
    assertThat(person.getFacts(FactType.Adoption).get(0).toString()).isEqualTo("type=Adoption,value=null,date=Date{original='original date', formal=normalized date},place=PlaceReference{original='original place', descriptionRef='urn:place'}");
    assertThat(person.getFirstFactOfType(FactType.Adoption).toString()).isEqualTo("type=Adoption,value=null,date=Date{original='original date', formal=normalized date},place=PlaceReference{original='original place', descriptionRef='urn:place'}");
    assertThat(person.getFacts(FactType.Occupation).size()).isEqualTo(1);
    assertThat(person.getFacts(FactType.Occupation).get(0).toString()).isEqualTo("type=Occupation,value=fact-value,date=Date{original='original date', formal=formal},place=PlaceReference{original='original place', descriptionRef='urn:place'}");
    assertThat(person.getFirstFactOfType(FactType.Occupation).toString()).isEqualTo("type=Occupation,value=fact-value,date=Date{original='original date', formal=formal},place=PlaceReference{original='original place', descriptionRef='urn:place'}");

    person.getFacts().clear();
    assertThat(person.getFacts()).isNotNull();
    assertThat(person.getFacts(FactType.Adoption).size()).isEqualTo(0);
    assertThat(person.getFacts(null).size()).isEqualTo(0);
    assertThat(person.getFirstFactOfType(FactType.Adoption)).isNull();
    person.setFacts(null);
    assertThat(person.getFacts()).isNull();
    assertThat(person.getFacts(FactType.Adoption).size()).isEqualTo(0);
    assertThat(person.getFirstFactOfType(FactType.Adoption)).isNull();

    person.addFact(null);
    assertThat(person.getFacts()).isNull();
  }

  static Person create() {
    final Person person = new Person();
    person.setGender(new Gender(GenderType.Male));

    final ArrayList<Identifier> identifiers = new ArrayList<Identifier>();
    Identifier identifier = new Identifier();
    identifier.setKnownType(IdentifierType.Deprecated);
    identifier.setValue(URI.create("forward-value"));
    identifiers.add(identifier);
    identifier = new Identifier();
    identifier.setKnownType(IdentifierType.Persistent);
    identifier.setValue(URI.create("pal"));
    identifiers.add(identifier);
    person.setIdentifiers(identifiers);

    final Fact fact = new Fact();
    fact.setKnownConfidenceLevel(ConfidenceLevel.High);
    fact.setDate(new Date());
    fact.getDate().setOriginal("original date");
    fact.getDate().setFormal("formal");
    fact.getDate().setNormalizedExtensions(Arrays.asList(new TextValue("normalized date")));
    fact.setId("fact-id");
    fact.setKnownType(FactType.Occupation);
    fact.setPlace(new PlaceReference());
    fact.getPlace().setOriginal("original place");
    fact.getPlace().setDescriptionRef(URI.create("urn:place"));
    fact.setValue("fact-value");
    person.addFact(fact);

    final Fact event = new Fact();
    event.setDate(new Date());
    event.getDate().setOriginal("original date");
    event.getDate().setFormal("normalized date");
    event.setId("event-id");
    event.setKnownType(FactType.Adoption);
    event.setPlace(new PlaceReference());
    event.getPlace().setOriginal("original place");
    event.getPlace().setDescriptionRef(URI.create("urn:place"));
    event.setSources(new ArrayList<SourceReference>());
    final SourceReference eventSource = new SourceReference();
    eventSource.setDescriptionRef(URI.create("urn:event-source"));
    eventSource.setAttribution(new Attribution());
    event.getSources().add(eventSource);

    final List<Fact> facts = person.getFacts();
    facts.add(event);
    person.setFacts(facts);

    final Name name = new Name();
    name.setId("name-id");
    name.setPreferred(true);
    name.setKnownType(NameType.FormalName);
    name.setNameForms(new ArrayList<NameForm>());
    name.getNameForms().add(new NameForm());
    name.getNameForms().get(0).setFullText("primary form");
    name.getNameForms().get(0).setParts(new ArrayList<NamePart>());
    name.getNameForms().get(0).getParts().add(new NamePart());
    name.getNameForms().get(0).getParts().get(0).setKnownType(NamePartType.Surname);
    name.getNameForms().get(0).getParts().get(0).setValue("primary surname");
    name.getNameForms().add(new NameForm());
    name.getNameForms().get(1).setFullText("alternate name form");
    name.getNameForms().get(1).setParts(new ArrayList<NamePart>());
    name.getNameForms().get(1).getParts().add(new NamePart());
    name.getNameForms().get(1).getParts().get(0).setKnownType(NamePartType.Given);
    name.getNameForms().get(1).getParts().get(0).setValue("alternate name part");

    final List<Name> names = new ArrayList<Name>();
    names.add(name);
    person.setNames(names);

    final ArrayList<SourceReference> sources = new ArrayList<SourceReference>();
    final SourceReference attributedSourceReference = new SourceReference();
    final Attribution attribution = new Attribution();
    attribution.setContributor(new ResourceReference());
    attribution.getContributor().setResource(URI.create("urn:source-reference-attribution"));
    attributedSourceReference.setAttribution(attribution);
    attributedSourceReference.setDescriptionRef(URI.create("urn:source-description"));
    sources.add(attributedSourceReference);
    person.setSources(sources);

    person.setId("pid");
    person.setAttribution(new Attribution());
    person.getAttribution().setChangeMessage("this person existed.");

    person.setLiving(true);

    return person;
  }

  static void assertPersonEquals(Person person) {
    Fact fact;
    Fact event;
    Name name;
    SourceReference sr;
    assertThat(person.getGender().getKnownType()).isEqualTo(GenderType.Male);

    assertThat(person.getIdentifiers().size()).isEqualTo(2);
    final Identifier identifier1 = person.getIdentifiers().get(0);
    final Identifier identifier2 = person.getIdentifiers().get(1);
    final Identifier deprecatedIdentifier = identifier1.getKnownType() == IdentifierType.Deprecated ? identifier1 : identifier2;
    final Identifier persistentIdentifier = identifier1.getKnownType() == IdentifierType.Persistent ? identifier1 : identifier2;

    assertThat(deprecatedIdentifier.getKnownType()).isEqualTo(IdentifierType.Deprecated);
    assertThat(deprecatedIdentifier.getValue().toString()).isEqualTo("forward-value");
    assertThat(persistentIdentifier.getKnownType()).isEqualTo(IdentifierType.Persistent);
    assertThat(persistentIdentifier.getValue().toString()).isEqualTo("pal");

    assertThat(person.getFacts().size()).isEqualTo(2);
    fact = person.getFirstFactOfType(FactType.Occupation);
    assertThat(fact.getKnownConfidenceLevel()).isEqualTo(ConfidenceLevel.High);
    assertThat(fact.getDate().getOriginal()).isEqualTo("original date");
    assertThat(fact.getDate().getFormal()).isEqualTo("formal");
    assertThat(fact.getDate().getNormalizedExtensions().get(0).getValue()).isEqualTo("normalized date");
    assertThat(fact.getId()).isEqualTo("fact-id");
    assertThat(fact.getKnownType()).isEqualTo(FactType.Occupation);
    assertThat(fact.getPlace().getOriginal()).isEqualTo("original place");
    assertThat(fact.getPlace().getDescriptionRef().toURI().toString()).isEqualTo("urn:place");
    assertThat(fact.getValue()).isEqualTo("fact-value");

    event = person.getFirstFactOfType(FactType.Adoption);
    assertThat(event.getDate().getOriginal()).isEqualTo("original date");
    assertThat(event.getDate().getFormal()).isEqualTo("normalized date");
    assertThat(event.getId()).isEqualTo("event-id");
    assertThat(event.getKnownType()).isEqualTo(FactType.Adoption);
    assertThat(event.getPlace().getOriginal()).isEqualTo("original place");
    assertThat(event.getPlace().getDescriptionRef().toURI().toString()).isEqualTo("urn:place");

    assertThat(person.getNames().size()).isEqualTo(1);
    name = person.getNames().iterator().next();
    assertThat(name.getPreferred()).isTrue();
    assertThat(name.getNameForms().size()).isEqualTo(2);
    assertThat(name.getNameForms().get(1).getFullText()).isEqualTo("alternate name form");
    assertThat(name.getNameForms().get(1).getParts().size()).isEqualTo(1);
    assertThat(name.getNameForms().get(1).getParts().get(0).getValue()).isEqualTo("alternate name part");
    assertThat(name.getNameForms().get(1).getParts().get(0).getKnownType()).isEqualTo(NamePartType.Given);
    assertThat(name.getId()).isEqualTo("name-id");
    assertThat(name.getKnownType()).isEqualTo(NameType.FormalName);
    assertThat(name.getNameForms().get(0).getFullText()).isEqualTo("primary form");
    assertThat(name.getNameForms().get(0).getParts().size()).isEqualTo(1);
    assertThat(name.getNameForms().get(0).getParts().get(0).getValue()).isEqualTo("primary surname");
    assertThat(name.getNameForms().get(0).getParts().get(0).getKnownType()).isEqualTo(NamePartType.Surname);

    assertThat(person.getPersistentId().toString()).isEqualTo("pal");

    assertThat(person.getSources().size()).isEqualTo(1);
    sr = person.getSources().iterator().next();
    assertThat(sr.getAttribution().getContributor().getResource().toString()).isEqualTo("urn:source-reference-attribution");
    assertThat(sr.getDescriptionRef().toString()).isEqualTo("urn:source-description");

    assertThat(person.getId()).isEqualTo("pid");
    assertThat(person.getAttribution().getChangeMessage()).isEqualTo("this person existed.");

    assertThat(person.getLiving()).isTrue();
  }

}
