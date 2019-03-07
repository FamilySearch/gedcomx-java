# GEDCOM X Java Model

This is where the Java classes that correspond to the GEDCOM X data types defined by the
[GEDCOM X Conceptual Model](https://github.com/FamilySearch/gedcomx/blob/master/specifications/conceptual-model-specification.md) live.
You can use these classes to read and write both [GEDCOM X XML](https://github.com/FamilySearch/gedcomx/blob/master/specifications/xml-format-specification.md)
and [GEDCOM X JSON](https://github.com/FamilySearch/gedcomx/blob/master/specifications/json-format-specification.md).

# Coordinates

groupId | artifactId
--------|-----------
`org.gedcomx` | `gedcomx-model`

See [the section on using these libraries](../README.md#Use).

To determine the latest version, [read the Maven Metadata](https://repository-gedcom.forge.cloudbees.com/release/org/gedcomx/gedcomx-model/maven-metadata.xml)
and use the "release" version.

# Use

## Building the Model

You can use the GEDCOM X model classes to build out a GEDCOM X document.

```java
SourceDescription sourceDescription = new SourceDescription() //describe a source
  .title("Birth Certificate") //with a title
  .citation(new SourceCitation().value("Citation for Birth Certificate")) //and a citation
  .resourceType(ResourceType.PhysicalArtifact) //of a physical artifact
  .created(parse("1888-08-08")); //created on August 8, 1888

Person person = new Person() //create a person
  .source(sourceDescription) //citing the source
  .name("Jane Smith") //named Jane Smith
  .gender(GenderType.Female) //female
  .fact(new Fact(FactType.Birth, "August 8, 1888", "England")); //born 8/8/1888 in England

Person parent1 = new Person() //create a parent1
  .source(sourceDescription) //citing the source
  .name("William Smith") //named William Smith
  .fact(new Fact(FactType.Occupation, "Toll Collector")); //toll collector

Person parent2 = new Person() //create a parent2
  .source(sourceDescription) //citing the source
  .name("Sarah Smith"); //named Sarah Smith

Relationship parent1Relationship = new Relationship() //create a relationship
  .type(RelationshipType.ParentChild) //of type parent-child
  .person1(parent1) //between the parent1
  .person2(person); //and the person

Relationship parent2Relationship = new Relationship() //create a relationship
  .type(RelationshipType.ParentChild) //of type parent-child
  .person1(parent2) //between the parent2
  .person2(person); //and the person
  
Gedcomx gx = new Gedcomx() //create a GEDCOM X document
  .person(person) //with the person
  .person(parent1) //and the parent1
  .person(parent2) //and the parent2
  .relationship(parent1Relationship) //and the parent1 relationship
  .relationship(parent2Relationship); //and the parent2 relationship

```

## XML

Here's how you might write out some GEDCOM X XML:

```java
Gedcomx gx = ...; //construct the document
OutputStream out = ...; //figure out where you want to write the XML

//construct an XML context and a marshaller.
//(presumably, you'll want to reuse these.)
JAXBContext context = JAXBContext.newInstance(Gedcomx.class);
Marshaller marshaller = context.createMarshaller();

//write the document to the stream:
marshaller.marshal(gx, out);

```

Here's how you might read some GEDCOM X XML:

```java
InputStream in = ...; //find the XML

//construct an XML context and an unmarshaller.
//(presumably, you'll want to reuse these.)
JAXBContext context = JAXBContext.newInstance(Gedcomx.class);
Unmarshaller unmarshaller = context.createUnmarshaller();

//read the document
Gedcomx gx = (Gedcomx) unmarshaller.unmarshal(in);

```

## JSON

Here's how you might write out some GEDCOM X JSON:

```java
Gedcomx gx = ...; //construct the document
OutputStream out = ...; //figure out where you want to write the JSON

//construct an object mapper.
//(presumably, you'll want to reuse this.)
ObjectMapper mapper = GedcomJacksonModule.createObjectMapper(Gedcomx.class);

//write the document to the stream:
mapper.writeValue(gx, out);

```

## JSON

Here's how you might read some GEDCOM X JSON:

```java
InputStream in = ...; //find the JSON

//construct an object mapper.
//(presumably, you'll want to reuse this.)
ObjectMapper mapper = GedcomJacksonModule.createObjectMapper(Gedcomx.class);
Gedcomx gx = mapper.readValue(in, Gedcomx.class);
```

## The Example Test Suite

For a suite of examples on how to use the model classes, see 
[the `org.gedcomx.examples` test suite](./src/test/java/org/gedcomx/examples/). Many of the tests have
an associated document in [the GEDCOM X recipe book](http://www.gedcomx.org/Recipe-Book.html).
