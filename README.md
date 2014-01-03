# Welcome

This project hosts the Java implementation of the [GEDCOM X](http://www.gedcomx.org) project. The project includes
subprojects that address specific aspects of the [GEDCOM X Specification Set](http://www.gedcomx.org/Specifications.html),
including:

* Readers and writers for the [GEDCOM X XML Serialization Format](https://github.com/FamilySearch/gedcomx/blob/master/specifications/xml-format-specification.md).
* Readers and writers for the [GEDCOM X JSON Serialization Format](https://github.com/FamilySearch/gedcomx/blob/master/specifications/json-format-specification.md).
* Readers and writers for the [GEDCOM X File Format](https://github.com/FamilySearch/gedcomx/blob/master/specifications/file-format-specification.md).
* Client-side libraries for reading and writing a GEDCOM X Web service API that conforms to the [GEDCOM X RS Specification](https://github.com/FamilySearch/gedcomx-rs).

## GEDCOM X Model

The [`gedcomx-model`](./gedcomx-model/) subproject provides Java classes that correspond to the data types defined by
the [GEDCOM X Conceptual Model](https://github.com/FamilySearch/gedcomx/blob/master/specifications/conceptual-model-specification.md).
These classes are instrumented such that they can be used to read and write both
[XML](https://github.com/FamilySearch/gedcomx/blob/master/specifications/xml-format-specification.md) and
[JSON](https://github.com/FamilySearch/gedcomx/blob/master/specifications/json-format-specification.md).

## GEDCOM X File Format

The [`gedcomx-fileformat`](./gedcomx-fileformat/) subproject provides support for reading and writing the
[GEDCOM X File Format](https://github.com/FamilySearch/gedcomx/blob/master/specifications/file-format-specification.md).

## GEDCOM X Web Service Client

The [`gedcomx-rs-client`](./gedcomx-rs-client/) subproject provides support for consuming a GEDCOM X Web service API that
conforms to the [GEDCOM X RS Specification](https://github.com/FamilySearch/gedcomx-rs).

# Use

Here's how you might use this project.

## Maven Repositories

The GEDCOM X Java artifacts are provided via a [Maven repository structure](http://maven.apache.org/guides/introduction/introduction-to-repositories.html).
Most Java-based build systems (Ant, Maven, Gradle, etc.) have support for Maven repositories.
The official Maven repositories are generously hosted by [CloudBees](http://cloudbees.com/).

Here's some snippets of what the dependency declarations might look like in some of the most common build systems. Note that
these snippets declare a dependency on the [`gedcomx-model`](./gedcomx-model) artifact, but you may want to declare dependencies on
`gedcomx-fileformat` or `gedcomx-rs-client` or whatever.

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
  <repositories>
    <repository>
      <id>gedcomx-release-repo</id>
      <name>GEDCOM X Release Repository</name>
      <url>https://repository-gedcom.forge.cloudbees.com/release/</url>
    </repository>
  </repositories>
  ...
</project>

#### Apache Ivy

```xml
<ivysettings>
...
  <settings defaultResolver="chain"/>
  <resolvers>
    <chain name="chain">
      <ibiblio name="central" m2compatible="true"/>
      <ibiblio name="gedcomx" m2compatible="true" root="https://repository-gedcom.forge.cloudbees.com/release/"/>
    </chain>
  </resolvers>
...
</ivysettings>
```

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
repositories {
  maven {
    url "https://repository-gedcom.forge.cloudbees.com/release/"
  }
}
...
dependencies {
  runtime group: 'org.gedcomx', name: 'gedcomx-model', version: gedcomxVersion
}
...
```

#### Apache Buildr

```ruby
...
repositories.remote << 'https://repository-gedcom.forge.cloudbees.com/release/'
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

