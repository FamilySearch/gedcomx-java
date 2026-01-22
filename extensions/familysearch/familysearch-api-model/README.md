# FamilySearch Model Extensions

This is where the Java classes that correspond to the FamilySearch extensions to GEDCOM X live.

# Coordinates

groupId | artifactId
--------|-----------
`org.gedcomx.extensions.familysearch` | `familysearch-api-model`

See [the section on using these libraries](../../../README.md#Use).

To determine the latest version, [read the Maven Metadata](https://repository-gedcom.forge.cloudbees.com/release/org/gedcomx/extensions/familysearch/familysearch-api-model/maven-metadata.xml)
and use the "release" version.

# Use

## Building the Model

Here's a sample of FamilySearch model extensions you might use: 

```java
//create a person.
Person person = new Person() //create a person
  .name("...something...") //named something
  .gender(GenderType.Male) //male
  .fact(new Fact(FactType.Birth, "...sometime...", "...someplace")); //born sometime, someplace

Discussion discussion = new Discussion()//create a discussion
  .title("...some title..."); //with some title.

DiscussionReference discussionReference = new DiscussionReference() //create a reference
  .setResource(...); //to a discussion.
person.addExtensionElement(discussionReference); //add a discussion reference to the person.

User user = new User(); //create a FamilySearch user.

ChildAndParentsRelationship childAndParentsRelationship = new ChildAndParentsRelationship() //create a child-and-parents relationship
  .child(child) //between a child
  .parent1(parent1) //parent1
  .parent2(parent2); //and parent2

FamilySearchPlatform doc = new FamilySearchPlatform()
  .discussion(discussion)
  .user(user)
  .childAndParentsRelationship(childAndParentsRelationship)
  .person(person);
```

## XML

Here's how you might write out some XML:

```java
FamilySearchPlatform fsp = ...; //construct the document
OutputStream out = ...; //figure out where you want to write the XML

//construct an XML context and a marshaller.
//(presumably, you'll want to reuse these.)
JAXBContext context = JAXBContext.newInstance(FamilySearchPlatform.class);
Marshaller marshaller = context.createMarshaller();

//write the document to the stream:
marshaller.marshal(fsp, out);

```

Here's how you might read some GEDCOM X XML:

```java
InputStream in = ...; //find the XML

//construct an XML context and an unmarshaller.
//(presumably, you'll want to reuse these.)
JAXBContext context = JAXBContext.newInstance(FamilySearchPlatform.class);
Unmarshaller unmarshaller = context.createUnmarshaller();

//read the document
FamilySearchPlatform fsp = (FamilySearchPlatform) unmarshaller.unmarshal(in);

```

## JSON

Here's how you might write out some GEDCOM X JSON:

```java
FamilySearchPlatform fsp = ...; //construct the document
OutputStream out = ...; //figure out where you want to write the JSON

//construct an object mapper.
//(presumably, you'll want to reuse this.)
JsonMapper mapper = GedcomJacksonModule.createJsonMapper(FamilySearchPlatform.class);

//write the document to the stream:
mapper.writeValue(fsp, out);

```

## JSON

Here's how you might read some GEDCOM X JSON:

```java
InputStream in = ...; //find the JSON

//construct an object mapper.
//(presumably, you'll want to reuse this.)
JsonMapper mapper = GedcomJacksonModule.createJsonMapper(FamilySearchPlatform.class);
FamilySearchPlatform fsp = mapper.readValue(in, FamilySearchPlatform.class);
```
