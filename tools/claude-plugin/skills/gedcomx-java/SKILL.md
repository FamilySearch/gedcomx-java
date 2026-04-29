---
name: gedcomx-java
description: This skill should be used when the user is working with the GEDCOM X Java library, asks about "gedcomx-java", "gedcomx-model", "gedcomx-fileformat", "gedcomx-date", the FamilySearch API client, JAXB serialization of GEDCOM X, Jackson JSON for GEDCOM X, or any Java code that imports from "org.gedcomx". Also activates when the user asks how to build, serialize, deserialize, or extend GEDCOM X data using Java.
version: 1.0.0
---

# gedcomx-java

You are an expert on the GEDCOM X Java reference implementation. When this skill is invoked, do the following:

## Step 1: Invoke the gedcomx dependency skill

Invoke the `gedcomx` skill from the `gedcomx` dependency plugin. This loads the GEDCOM X specifications into context and provides the authoritative data model, format, and vocabulary knowledge that underpins everything in this Java library.

## Step 2: Load Java library context

After the `gedcomx` skill completes, read the following files from this repository to understand the Java implementation:

- `README.md` — module overview, Maven/Gradle/Ivy dependency coordinates, build instructions
- `gedcomx-model/README.md` — Java model classes, XML (JAXB) and JSON (Jackson) serialization examples
- `gedcomx-fileformat/README.md` — reading and writing `.gedx` files with `GedcomxOutputStream` and `GedcomxFile`
- `gedcomx-date/README.md` — `GedcomxDate` type hierarchy, `GedcomxDateUtil` utilities, and exception handling

Read them now using the Read tool before proceeding.

## Step 3: Determine intent

If the user supplied a clear intent (e.g., "generate Java code to represent a birth record", "how do I read a .gedx file", "validate this GEDCOM X JSON"), proceed directly to the appropriate mode.

If the user supplied no intent, enter **Knowledge mode** and inform the user you have loaded both the GEDCOM X specifications and the Java library documentation, then wait for their next message.

## Modes

### Knowledge mode (default)
Answer questions about the Java library: which module to use, how classes map to GEDCOM X types, serialization/deserialization patterns, date parsing, file format I/O, and FamilySearch API client usage. Ground answers in the specifications loaded by the `gedcomx` skill and the Java library documentation loaded above.

### Java Generation mode
Produce Java code that uses this library correctly.

Best practices to follow:
- Use the fluent builder-style API (method chaining) as shown in the `gedcomx-model` README.
- Declare Maven coordinates using `groupId: org.gedcomx` and the appropriate `artifactId` (`gedcomx-model`, `gedcomx-fileformat`, `gedcomx-date`).
- Reuse `JAXBContext` and `JsonMapper` instances — they are expensive to construct.
- Use `GedcomJacksonModule.createJsonMapper(Gedcomx.class)` for JSON, not a plain `ObjectMapper`.
- Use `GedcomxDateUtil.parse()` for date strings; handle `GedcomxDateException` (it is a runtime exception).
- Prefer `GedcomxOutputStream` / `GedcomxFile` for `.gedx` file I/O.
- Use well-formed URI strings for `id` values (e.g., `#person-1`, `#rel-1`).
- After generating, note any assumptions made (inferred types, omitted optional fields).

### Validation mode
Review Java code that uses this library and identify issues:
- Incorrect module/artifact chosen for the task
- Misuse of JAXB vs. Jackson APIs
- `JAXBContext` or `JsonMapper` instantiated per-call instead of reused
- Invalid or non-spec-compliant `type` URIs passed to `FactType`, `RelationshipType`, etc.
- Date strings that would fail `GedcomxDateUtil.parse()` per the date format spec
- Missing required fields per the GEDCOM X conceptual model (loaded by the `gedcomx` skill)

Report findings as a concise list: **valid** or each issue with a short description and the relevant spec/README section.
