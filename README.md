# Welcome

This project hosts the Java implementation of the [GEDCOM X](http://www.gedcomx.org) project and serves as the
reference implementation of GEDCOM X. The modules of this project each
address specific aspects of the [GEDCOM X Specification Set](http://www.gedcomx.org/Specifications.html),
including:

* Readers and writers for the [GEDCOM X XML Serialization Format](https://github.com/FamilySearch/gedcomx/blob/master/specifications/xml-format-specification.md).
* Readers and writers for the [GEDCOM X JSON Serialization Format](https://github.com/FamilySearch/gedcomx/blob/master/specifications/json-format-specification.md).
* Readers and writers for the [GEDCOM X File Format](https://github.com/FamilySearch/gedcomx/blob/master/specifications/file-format-specification.md).
* Client-side libraries for reading and writing a GEDCOM X Web service API that conforms to the [GEDCOM X RS Specification](https://github.com/FamilySearch/gedcomx-rs).

[![Build Status](https://travis-ci.org/FamilySearch/gedcomx-java.svg?branch=master)](https://travis-ci.org/FamilySearch/gedcomx-java)

## Reading and Writing XML and JSON

The [`gedcomx-model`](./gedcomx-model/README.md) subproject provides Java classes that correspond to the data types defined by
the [GEDCOM X Conceptual Model](https://github.com/FamilySearch/gedcomx/blob/master/specifications/conceptual-model-specification.md).
These classes are instrumented such that they can be used to read and write both
[XML](https://github.com/FamilySearch/gedcomx/blob/master/specifications/xml-format-specification.md) and
[JSON](https://github.com/FamilySearch/gedcomx/blob/master/specifications/json-format-specification.md).

For more information about reading and writing GEDCOM X XML and JSON, see the [`gedcomx-model`](./gedcomx-model/) module.

## GEDCOM X Web Services

The [`gedcomx-rs-client`](./gedcomx-rs-client/README.md) module provides support for reading from and writing to a GEDCOM X 
Web service API that conforms to the [GEDCOM X RS Specification](https://github.com/FamilySearch/gedcomx-rs).

## GEDCOM X File Format

The [`gedcomx-fileformat`](./gedcomx-fileformat/README.md) subproject provides support for reading and writing the
[GEDCOM X File Format](https://github.com/FamilySearch/gedcomx/blob/master/specifications/file-format-specification.md).

## GEDCOM X Extensions

The [`extensions`](./extensions/README.md) module provides a place for extensions to GEDCOM X. [FamilySearch](https://familysearch.org) has defined
a set of extensions to the GEDCOM X Conceptual Model and to the GEDCOM X RS specification that comprise the definition of 
[the FamilySearch API](https://developer.familysearch.org/).
 
The [FamilySearch API Client](./extensions/familysearch/familysearch-api-client/README.md) comprises the developer SDK for the FamilySearch API.

<a name="Use"/>

# Use

Here's how you might use this project.

## Maven Repositories

The GEDCOM X Java artifacts are provided via a [Maven repository structure](http://maven.apache.org/guides/introduction/introduction-to-repositories.html).
Most Java-based build systems (Ant, Maven, Gradle, etc.) have support for Maven repositories.

Here's some snippets of what the dependency declarations might look like in some of the most common build systems. Note that
these snippets declare a dependency on the [`gedcomx-model`](./gedcomx-model/README.md) artifact, but you may want to declare dependencies on
`gedcomx-fileformat` or `gedcomx-rs-client` or whatever.

##### Finding the Latest Version

To find the latest version of the libraries, [read the Maven Metadata](https://repository-gedcom.forge.cloudbees.com/release/org/gedcomx/gedcomx-parent/maven-metadata.xml)
and use the "release" version.

#### Maven

```xml
<project>
  ...
  <dependencies>
    ...
    <dependency>
      <groupId>org.gedcomx</groupId>
      <artifactId>gedcomx-model</artifactId>
      <version>${gedcomx.version}</version>
    </dependency>
    ...
  </dependencies>
  ...
</project>
```

#### Apache Ivy

```xml
<ivy-module>
...
  <dependencies>
    ...
    <dependency org="org.gedcomx" name="gedcomx-model" rev="${gedcomx.version}"/>
    ...
  </dependencies>
...
</ivy-module>
```

#### Gradle

```groovy
...
dependencies {
  runtime group: 'org.gedcomx', name: 'gedcomx-model', version: gedcomxVersion
}
...
```

#### Apache Buildr

```ruby
...
compile.with transitive('org.gedcomx:gedcomx-model:#{gedcomx-version}')
...
```

# Build

Here's how you build this project from source:

```
git clone https://github.com/FamilySearch/gedcomx-java.git
cd gedcomx-java
mvn install
```

The build for this project is automated via [Travis CI](https://travis-ci.org/FamilySearch/gedcomx-java), which automatically
validates any updates to the code. When a release is needed, a [Jenkins build server](https://gedcom.ci.cloudbees.com/)
generously hosted by [CloudBees](https://www.cloudbees.com/) is used to perform the release.