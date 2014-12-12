# FamilySearch API SDK

This is a Java library that provides support for consuming the [FamilySearch API](https://developer.familysearch.org/).

The FamilySearch API is an implementation of the [GEDCOM X RS Specification](https://github.com/FamilySearch/gedcomx-rs),
plus some custom FamilySearch extensions. As such, the FamilySearch API SDK extends the
[GEDCOM X RS Client](../../../gedcomx-rs-client/README.md).

# Coordinates

groupId | artifactId
--------|-----------
`org.gedcomx.extensions.familysearch` | `familysearch-api-client`

See [the section on using these libraries](../../../README.md#Use).

# Use

The FamilySearch API SDK extends the [GEDCOM X RS Client](../../../gedcomx-rs-client/README.md). In addition to the
base functionality of the [GEDCOM X RS Client](../../../gedcomx-rs-client/README.md), the
FamilySearch API SDK provides additional model classes, convenience classes, and methods to support FamilySearch-specific
functionality.

The FamilySearch API uses [hypermedia as the engine of application state](http://en.wikipedia.org/wiki/HATEOAS)
and so using a client feels like browsing the web. As such, the SDK feels like using a screen scraper. The process can generally
be summarized as follows:

#### Step 1: Read the "Home" Collection
 
Web sites have a "home page". The FamilySearch API has "home collections". Examples of collections include the FamilySearch Family Tree,
FamilySearch Memories, and FamilySearch Records.

#### Step 2: Follow the Right Link

You get stuff done on a web site by following links to where you want to go. Ditto for the FamilySearch API.

#### Step 3: Repeat

Follow more links to get more stuff done.

<a name="exampmles"/>

## Examples

Need something to sink your teeth into?

* [Read the FamilySearch Family Tree (Start Here)](#read-ft)
* [Read a Family Tree Person by Persistent ID](#read-person-by-persistent-id)
* [Read a Family Tree Person by Family Tree ID](#read-person-by-pid)
* [Read a Family Tree Person by Family Tree ID, Including Relationships](#read-pwr-by-pid)
* [Search for Persons or Person Matches in the Family Tree](#search-ft)
* [Create a Person in the Family Tree](#create-ft-person)
* [Create a Couple Relationship in the Family Tree](#create-ft-couple)
* [Create a Child-and-Parents Relationship in the Family Tree](#create-ft-chap)
* [Create a Source](#create-source)
* [Create a Source Reference](#create-source-reference)
* [Read Everything Attached to a Source](#read-references-to-source)
* [Read Person for the Current User](#read-current-user-person)
* [Read Source References](#read-source-references)
* [Read Persona References](#read-evidence-references)
* [Read Discussion References](#read-discussion-references)
* [Read Notes](#read-notes)
* [Read Parents, Children, or Spouses](#read-relatives)
* [Read Ancestry or Descendancy](#read-pedigree)
* [Read Person Matches (i.e., Possible Duplicates)](#read-matches)
* [Declare Not a Match](#declare-not-a-match)
* [Add a Name or Fact](#add-conclusion)
* [Update a Name, Gender, or Fact](#update-conclusion)
* [Create a Discussion](#create-discussion)
* [Attach a Discussion](#create-discussion-reference)
* [Attach a Photo to a Person](#attach-photo)
* [Read FamilySearch Memories](#read-fs-memories)
* [Upload Photo or Story or Document](#add-an-artifact)
* [Create a Memory Persona](#create-memory-persona)
* [Create a Persona Reference](#create-persona-reference)
* [Attach a Photo to Multiple Persons](#multi-person-photo-attach)


<a name="read-ft"/>

### Read the FamilySearch Family Tree (Start Here)

Before you do anything, you need to start by reading the collection that you want
to read or update.

May we suggest you start with the FamilySearch Family Tree?

```java
boolean useSandbox = true; //whether to use the sandbox reference.
String username = "...";
String password = "...";
String developerKey = "...";

//read the Family Tree
FamilySearchFamilyTree ft = new FamilySearchFamilyTree(useSandbox)
  //and authenticate.
  .authenticateViaOAuth2Password(username, password, developerKey);
```

<a name="read-person-by-persistent-id"/>

### Read a Family Tree Person by Persistent ID

```java
String username = "...";
String password = "...";
String developerKey = "...";

String ark = ...; //e.g. "https://familysearch.org/ark:/61903/4:1:KW8W-RF8"
FamilyTreePersonState person = new FamilyTreePersonState(URI.create(ark))
  .authenticateViaOAuth2Password(username, password, developerKey);
```

<a name="read-person-by-pid"/>

### Read a Family Tree Person by Family Tree ID

```java
String pid = ...; //e.g. "KW8W-RF8"

FamilySearchFamilyTree ft = ...;
FamilyTreePersonState person = ft.readPersonById(pid);
```

<a name="read-pwr-by-pid"/>

### Read a Family Tree Person by Family Tree ID, Including Relationships

```java
String pid = ...; //e.g. "KW8W-RF8"

FamilySearchFamilyTree ft = ...;
FamilyTreePersonState person = ft.readPersonWithRelationshipsById(pid);
```

<a name="search-ft"/>

### Search for Persons or Person Matches in the Family Tree

```java
FamilySearchFamilyTree ft = ...;

//put together a search query
GedcomxPersonSearchQueryBuilder query = new GedcomxPersonSearchQueryBuilder()
  //for a John Smith
  .name("John Smith")
  //born 1/1/1900
  .birthDate("1 January 1900")
  //son of Peter.
  .fatherName("Peter Smith");

//search the collection
PersonSearchResultsState results = ft.searchForPersons(query);
//iterate through the results...
List<Entry> entries = results.getResults().getEntries();
//read the person that was hit
PersonState person = results.readPerson(entries.get(0));

//search the collection for matches
PersonMatchResultsState matches = ft.searchForPersonMatches(query);
//iterate through the results...
entries = results.getResults().getEntries();
//read the person that was matched
person = results.readPerson(entries.get(0));
```

<a name="create-ft-person"/>

### Create a Person in the Family Tree

```java
FamilySearchFamilyTree ft = ...;

//add a person
PersonState person = ft.addPerson(new Person()
  //named John Smith
  .name(new Name("John Smith", new NamePart(NamePartType.Given, "John"), new NamePart(NamePartType.Surname, "Smith")))
  //male
  .gender(GenderType.Male)
  //born in chicago in 1920
  .fact(new Fact(FactType.Birth, "1 January 1920", "Chicago, Illinois"))
  //died in new york 1980
  .fact(new Fact(FactType.Death, "1 January 1980", "New York, New York")),
  //with a change message.
  reason("Because I said so.")
);
```

<a name="create-ft-couple"/>

### Create a Couple Relationship in the Family Tree

```java
FamilySearchFamilyTree ft = ...;

PersonState husband = ...;
PersonState wife = ...;

RelationshipState coupleRelationship = ft.addSpouseRelationship(husband, wife, reason("Because I said so."));
```

<a name="create-ft-chap"/>

### Create a Child-and-Parents Relationship in the Family Tree

```java
FamilySearchFamilyTree ft = ...;

PersonState father = ...;
PersonState mother = ...;
PersonState child = ...;

ChildAndParentsRelationshipState chap = ft.addChildAndParentsRelationship(child, father, mother, reason("Because I said so."));
```

<a name="create-source"/>

### Create a Source

```java
FamilySearchFamilyTree ft = ...;

//add a source description
SourceDescriptionState source = ft.addSourceDescription(new SourceDescription()
  //about some resource.
  .about(org.gedcomx.common.URI.create("http://familysearch.org/ark:/..."))
  //with a title.
  .title("Birth Certificate for John Smith")
  //and a citation
  .citation("Citation for the birth certificate")
  //and a note
  .note(new Note().text("Some note for the source.")),
  //with a change message.
  reason("Because I said so.")
);
```

<a name="create-source-reference"/>

### Create a Source Reference

```java
//the person that will be citing the record, source, or artifact.
PersonState person = ...;

SourceDescriptionState source = ...;

person.addSourceReference(source, reason("Because I said so.")); //cite the source.
```

<a name="read-references-to-source"/>

### Read Everything Attached to a Source

```java
//the source.
SourceDescriptionState source = ...;

SourceDescriptionState attachedReferences = ((FamilySearchSourceDescriptionState) source).queryAttachedReferences();

//iterate through the persons attached to the source
List<Person> persons = attachedReferences.getEntity().getPersons();
```

<a name="read-current-user-person"/>

### Read Person for the Current User

```java
FamilySearchFamilyTree ft = ...;

PersonState person = ft.readPersonForCurrentUser();
```

<a name="read-source-references"/>

### Read Source References

```java
//the person on which to read the source references.
PersonState person = ...;

//load the source references for the person.
person.loadSourceReferences();

//read the source references.
List<SourceReference> sourceRefs = person.getPerson().getSources();
```

<a name="read-evidence-references"/>

### Read Persona References

```java
//the person on which to read the persona references.
PersonState person = ...;

//load the persona references for the person.
person.loadPersonaReferences();

//read the persona references.
List<EvidenceReference> personaRefs = person.getPerson().getEvidence();
```

<a name="read-discussion-references"/>

### Read Discussion References

```java
//the person on which to read the discussion references.
PersonState person = ...;

//load the discussion references for the person.
((FamilyTreePersonState) person).loadDiscussionReferences();

//read the discussion references.
List<DiscussionReference> discussionRefs = person.getPerson().findExtensionsOfType(DiscussionReference.class);
```

<a name="read-notes"/>

### Read Notes

```java
//the person on which to read the notes.
PersonState person = ...;

//load the notes for the person.
person.loadNotes();

//read the discussion references.
List<Note> notes = person.getPerson().getNotes();
```

<a name="read-relatives"/>

### Read Parents, Children, or Spouses

```java
//the person for which to read the parents, spouses, children
PersonState person = ...;

PersonChildrenState children = person.readChildren(); //read the children
PersonParentsState parents = person.readParents(); //read the parents
PersonSpousesState spouses = person.readSpouses(); //read the spouses
```

<a name="read-pedigree"/>

### Read Ancestry or Descendancy

```java
//the person for which to read the ancestry or descendancy
PersonState person = ...;

person.readAncestry(); //read the ancestry
person.readAncestry(generations(8)); //read 8 generations of the ancestry
person.readDescendancy(); //read the descendancy
person.readDescendancy(generations(3)); //read 3 generations of the descendancy
```

<a name="read-matches"/>

### Read Person Matches (i.e., Possible Duplicates)

```java
//the person for which to read the matches
PersonState person = ...;

PersonMatchResultsState matches = ((FamilyTreePersonState) person).readMatches();

//iterate through the matches.
List<Entry> entries = matches.getResults().getEntries();
```

<a name="declare-not-a-match"/>

### Declare Not a Match

```java
//the match results
PersonMatchResultsState matches = ...;

//iterate through the matches.
List<Entry> entries = matches.getResults().getEntries();

matches.addNonMatch(entries.get(2), reason("Because I said so."));
```

<a name="add-conclusion"/>

### Add a Name or Fact

```java
//the person to which to add the name, gender, or fact.
PersonState person = ...;

Name name = ...;
person.addName(name.type(NameType.AlsoKnownAs), reason("Because I said so.")); //add name
person.addFact(new Fact(FactType.Death, "date", "place"), reason("Because I said so.")); //add death fact
```

<a name="update-conclusion"/>

### Update a Name, Gender, or Fact

```java
//the person to which to update the name, gender, or fact.
PersonState person = ...;

Name name = person.getName();
name.getNameForm().setFullText("Joanna Smith");
person.updateName(name, reason("Because I said so.")); //update name

Gender gender = person.getGender();
gender.setKnownType(GenderType.Female);
person.updateGender(gender, reason("Because I said so.")); //update gender

Fact death = person.getPerson().getFirstFactOfType(FactType.Death);
death.setDate(new Date().original("new date"));
person.updateFact(death, reason("Because I said so."));
```

<a name="create-discussion"/>

### Create a Discussion

```java
FamilySearchFamilyTree ft = ...;

//add a discussion description
DiscussionState discussion = ft.addDiscussion(new Discussion()
  //with a title.
  .title("What about this"),
  //with a change message.
  reason("Because I said so.")
);
```

<a name="create-discussion-reference"/>

### Attach a Discussion

```java
//the person that will be referencing the discussion.
PersonState person = ...;

DiscussionState discussion = ...;

((FamilyTreePersonState) person).addDiscussionReference(discussion, reason("Because I said so.")); //reference the discussion.
```

<a name="attach-photo"/>

### Attach a Photo to a Person

```java
//the person to which the photo will be attached.
PersonState person = ...;
DataSource digitalImage = new FileDataSource("/path/to/img.jpg");

//add an artifact
SourceDescriptionState artifact = person.addArtifact(new SourceDescription()
  //with a title
  .title("Portrait of John Smith"),
  digitalImage
);
```

<a name="read-fs-memories"/>

### Read FamilySearch Memories

```java
boolean useSandbox = true; //whether to use the sandbox reference.
String username = "...";
String password = "...";
String developerKey = "...";

//read the Family Tree
FamilySearchMemories fsMemories = new FamilySearchMemories(useSandbox)
  //and authenticate.
  .authenticateViaOAuth2Password(username, password, developerKey);
```

<a name="add-an-artifact"/>

### Upload Photo or Story or Document

```java
FamilySearchMemories fsMemories = ...;
DataSource digitalImage = new FileDataSource("/path/to/img.jpg");

//add an artifact
SourceDescriptionState artifact = fsMemories.addArtifact(new SourceDescription()
  //with a title
  .title("Death Certificate for John Smith")
  //and a citation
  .citation("Citation for the death certificate"), 
  digitalImage
);
```

<a name="create-memory-persona"/>

### Create a Memory Persona

```java
//the artifact from which a persona will be extracted.
SourceDescriptionState artifact = ...;

//add the persona
PersonState persona = artifact.addPersona(new Person()
  //named John Smith
  .name("John Smith"));
```

<a name="create-persona-reference"/>

### Create a Persona Reference

```java
//the person that will be citing the record, source, or artifact.
PersonState person = ...;

//the persona that was extracted from a record or artifact.
PersonState persona = ...;

//add the persona reference.
person.addPersonaReference(persona);
```

<a name="multi-person-photo-attach"/>

### Attach a Photo to Multiple Persons

```java
//the collection to which the artifact is to be added
CollectionState fsMemories = ...;

//the persons to which the photo will be attached.
PersonState person1 = ...;
PersonState person2 = ...;
PersonState person3 = ...;
DataSource digitalImage = new FileDataSource("/path/to/img.jpg");

//add an artifact
SourceDescriptionState artifact = fsMemories.addArtifact(new SourceDescription()
  //with a title
  .title("Family of John Smith"),
  digitalImage
);

person1.addMediaReference(artifact); //attach to person1
person2.addMediaReference(artifact); //attach to person2
person3.addMediaReference(artifact); //attach to person3
```