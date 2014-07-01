# GEDCOM X Java File Format

This project provides support for reading and writing the
[GEDCOM X File Format](https://github.com/FamilySearch/gedcomx/blob/master/specifications/file-format-specification.md).

# Coordinates

groupId | artifactId
--------|-----------
`org.gedcomx` | `gedcomx-fileformat`

See [the section on using these libraries](../README.md#Use).

# Use

Here's how you might write out a GEDCOM X file. See the [`gedcomx-model` documentation](../gedcomx-model/README.md)
for more information about how to build out the model.

```java
Gedcomx gx = ...;
File file = new File("/path/to/file.gedx");

GedcomxOutputStream out = new GedcomxOutputStream(new FileOutputStream(file));
out.addResource(gx);
out.close();
```

Here's how you might read a GEDCOM X file:

```java
File file = new File("/path/to/file.gedx");
GedcomxFile gxFile = new GedcomxFile(new JarFile(file));
Iterable<GedcomxFileEntry> entries = gxFile.getEntries();
for (GedcomxFileEntry entry : entries) {
  //for each entry, read the model.
  Gedcomx gx = (Gedcomx) gxFile.readResource(entry);
}

```