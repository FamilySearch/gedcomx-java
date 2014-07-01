# GEDCOM X Java RS Client

This is a Java library that provides support for consuming a genealogical Web service API that
conforms to the [GEDCOM X RS Specification](https://github.com/FamilySearch/gedcomx-rs).

# Coordinates

groupId | artifactId
--------|-----------
`org.gedcomx` | `gedcomx-rs-client`

See [the section on using these libraries](../README.md#Use).

# Use

Consuming a Web service API that uses [hypermedia as the engine of application state](http://en.wikipedia.org/wiki/HATEOAS)
feels like browsing the web. As such, the RS client feels like using a screen scraper. The process can generally
be summarized as follows:

#### Step 1: Read the "Home" Collection
 
Web sites have a "home page". GEDCOM X APIs have a "home collection".

#### Step 2: Follow the Right Link

You get stuff done on a web site by following links to where you want to go. Ditto for a GEDCOM X API.

#### Step 3: Repeat

Follow more links to get more stuff done.

## Examples

Need something to sink your teeth into?

* [Read a Collection (Start Here)](#read-collection)
* [Search for Records or Persons](#record-search)
* [Create a Person](#create-person)
* [Add a Source](#add-source)
* [Add an Artifact](#add-artifact)
* [Cite a Record, Artifact or Other Source](#cite-something)
* [Extract Information From a Source](#extract-persona)
* [Add a Persona Reference](#add-persona-reference)
* [Attach a Photo to a Person](#attach-photo)
* [Attach a Photo to Multiple Persons](#attaches-photo)
* [Read the Person for the Current User](#current-user-person)
* [Read Parents, Spouses, or Children](#read-relatives)
* [Read Ancestry or Descendancy](#read-pedigree)
* [Add a Name, Gender, or Fact](#add-conclusion)
* [Update a Name, Gender, or Fact](#update-conclusion)

<a name="read-collection"/>

### Read a Collection (Start Here)

Before you do anything, you need to start by reading the collection that you want
to read or update. Here's how you might read and authenticate to a collection.

```java
//start with the URI to the collection.
URI collectionUri = URI.create("...");

//read the collection.
CollectionState collection = new CollectionState(collectionUri);

//authenticate if you need to.
String username = "...";
String password = "...";
String clientId = "...";
collection.authenticateViaOAuth2Password(username, password, clientId);
```

<a name="record-search"/>

### Search for Records or Persons

Once you have a collection, you can search it for records or persons.

```java
//the collection to search. 
CollectionState collection = ...;

//put together a search query
GedcomxPersonSearchQueryBuilder query = new GedcomxPersonSearchQueryBuilder()
  //for a John Smith
  .name("John Smith")
  //born 1/1/1900
  .birthDate("1 January 1900")
    //son of Peter.
  .fatherName("Peter Smith");

//search the collection
PersonSearchResultsState results = collection.searchForPersons(query);

//iterate through the results...
List<Entry> entries = results.getResults().getEntries();

//read the record of one of the results.
RecordState record = results.readRecord(entries.get(0));

//or, read the person that was considered a "hit"
PersonState person = results.readPerson(entries.get(0));
```

<a name="create-person"/>

### Create a Person

Some collections are designed to hold genealogical data to be updated. Here's how you might
add a person to a collection.

```java
//the collection to which the person is to be added
CollectionState collection = ...;

//add a person
PersonState person = collection.addPerson(new Person()
  //named John Smith
  .name("John Smith")
  //male
  .gender(GenderType.Male)
  //residing in chicago in 1940
  .fact(new Fact(FactType.Residence, "4 April 1940", "Chicago, Illinois")));
```

<a name="add-source"/>

### Add a Source

Some collections allow you to add descriptions of sources.

```java
//the collection to which the source is to be added
CollectionState collection = ...;

//add a source description
SourceDescriptionState source = collection.addSourceDescription(new SourceDescription()
  //with a title.
  .title("Birth Certificate for John Smith")
  //and a citation
  .citation("Citation for the birth certificate")
);
```

<a name="add-artifact"/>

### Add an Artifact

Some collections allow you to upload artifacts such as digital images.

```java
//the collection to which the artifact is to be added
CollectionState collection = ...;
DataSource digitalImage = new FileDataSource("/path/to/img.jpg");

//add an artifact
SourceDescriptionState artifact = collection.addArtifact(new SourceDescription()
  //with a title
  .title("Death Certificate for John Smith")
  //and a citation
  .citation("Citation for the death certificate"), 
  digitalImage
);
```

<a name="cite-something"/>

### Cite a Record or Artifact or Other Source

How you might update a person to cite a record or artifact or other source.

```java
//the person that will be citing the record, source, or artifact.
PersonState person = ...;

RecordState record = ...;
SourceDescriptionState source = ...;
SourceDescriptionState artifact = ...;

person.addSourceReference(record); //cite the record.
person.addSourceReference(source); //cite the source.
person.addSourceReference(artifact); //cite the artifact.
```

<a name="extract-persona"/>

### Extract Information From a Source

Some collections might allow you to extract information about a person (i.e. "persona")
from an artifact or other source.

```java
//the artifact from which a persona will be extracted.
SourceDescriptionState artifact = ...;

//add the persona
PersonState persona = artifact.addPersona(new Person()
  //named John Smith
  .name("John Smith")
  //male
  .gender(GenderType.Male)
  //residing in chicago in 1940
  .fact(new Fact(FactType.Residence, "4 April 1940", "Chicago, Illinois")));
```

<a name="add-persona-reference"/>

### Add a Persona Reference

How you might add a reference from a person to a persona.

```java
//the person that will be referencing the persona.
PersonState person = ...;

//the persona that was extracted from a record or artifact.
PersonState persona = ...;

//add the persona reference.
person.addPersonaReference(persona);
```

<a name="attach-photo"/>

### Attach a Photo to a Person

Some collections might allow you to add a photo to a person.

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

<a name="attaches-photo"/>

### Attach a Photo to Multiple Persons

Here's how you would attach a single photo to multiple persons.

```java
//the collection to which the artifact is to be added
CollectionState collection = ...;

//the persons to which the photo will be attached.
PersonState person1 = ...;
PersonState person2 = ...;
PersonState person3 = ...;
DataSource digitalImage = new FileDataSource("/path/to/img.jpg");

//add an artifact
SourceDescriptionState artifact = collection.addArtifact(new SourceDescription()
  //with a title
  .title("Family of John Smith"), 
  digitalImage
);

person1.addMediaReference(artifact); //attach to person1
person2.addMediaReference(artifact); //attach to person2
person3.addMediaReference(artifact); //attach to person3
```

<a name="current-user-person"/>

### Read the Person for the Current User

The current authentication user may have a person record in a collection.

```java
//the collection containing the person for the current user.
CollectionState collection = ...;

PersonState person = collection.readPersonForCurrentUser();
```

<a name="read-relatives"/>

### Read Parents, Spouses, or Children

```java
//the person for which to read the parents, spouses, children
PersonState person = ...;

PersonChildrenState children = person.readChildren(); //read the children
PersonParentsState parents = person.readParents(); //read the parents
PersonSpousesState spouses = person.readSpouses(); //read the spouses
```

<a name="read-pedigree"/>

### Read Ancestry or Descendancy

Some collections support queries that allows you to read the
ancestry or descendancy of a person.

```java
//the person for which to read the ancestry or descendancy
PersonState person = ...;

person.readAncestry(); //read the ancestry
person.readAncestry(generations(8)); //read 8 generations of the ancestry
person.readDescendancy(); //read the descendancy
person.readDescendancy(generations(3)); //read 3 generations of the descendancy
```

<a name="add-conclusion"/>

### Add a Name, Gender, or Fact

How to add a name, gender, or facts to a person.

```java
//the person to which to add the name, gender, or fact.
PersonState person = ...;

person.addName(new Name("Johnny Smith")); //add name
person.addGender(new Gender(GenderType.Male)); //add gender
person.addFact(new Fact(FactType.Death, "date", "place")); //add death fact
```

<a name="update-conclusion"/>

### Update a Name, Gender, or Fact

How to update and existing name, gender, or facts of a person.

```java
//the person to which to update the name, gender, or fact.
//the person to which to update the name, gender, or fact.
PersonState person = ...;

Name name = person.getName();
name.getNameForm().setFullText("Joanna Smith");
person.updateName(name); //update name

Gender gender = person.getGender();
gender.setKnownType(GenderType.Female);
person.updateGender(gender); //update gender

Fact death = person.getPerson().getFirstFactOfType(FactType.Death);
death.setDate(new Date().original("new date"));
person.updateFact(death);
```
