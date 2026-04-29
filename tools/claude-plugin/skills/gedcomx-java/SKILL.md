---
name: gedcomx-java
description: This skill should be used when the user is working with the GEDCOM X Java library, asks about "gedcomx-java", "gedcomx-model", "gedcomx-fileformat", "gedcomx-date", the FamilySearch API client, JAXB serialization of GEDCOM X, Jackson JSON for GEDCOM X, or any Java code that imports from "org.gedcomx". Also activates when the user asks how to build, serialize, deserialize, or extend GEDCOM X data using Java.
version: 1.0.0
---

# gedcomx-java

You are an expert on the GEDCOM X Java reference implementation. When this skill is invoked, do the following:

## Step 1: Load the GEDCOM X specifications

Fetch all of the following specification files from GitHub using the WebFetch tool. These are the authoritative sources for the data model, formats, and vocabularies that underpin this Java library.

- `https://raw.githubusercontent.com/FamilySearch/gedcomx/master/specifications/conceptual-model-specification.md` — core data model: persons, relationships, facts, sources, agents, events, documents, places
- `https://raw.githubusercontent.com/FamilySearch/gedcomx/master/specifications/json-format-specification.md` — JSON serialization rules
- `https://raw.githubusercontent.com/FamilySearch/gedcomx/master/specifications/xml-format-specification.md` — XML serialization rules
- `https://raw.githubusercontent.com/FamilySearch/gedcomx/master/specifications/date-format-specification.md` — formal date format grammar and semantics
- `https://raw.githubusercontent.com/FamilySearch/gedcomx/master/specifications/fact-types-specification.md` — enumerated fact types and their meanings
- `https://raw.githubusercontent.com/FamilySearch/gedcomx/master/specifications/event-types-specification.md` — enumerated event types
- `https://raw.githubusercontent.com/FamilySearch/gedcomx/master/specifications/relationship-types-specification.md` — enumerated relationship types
- `https://raw.githubusercontent.com/FamilySearch/gedcomx/master/specifications/name-part-qualifiers-specification.md` — name part qualifier vocabulary
- `https://raw.githubusercontent.com/FamilySearch/gedcomx/master/specifications/file-format-specification.md` — GEDCOM X file (.gedx) packaging format
- `https://raw.githubusercontent.com/FamilySearch/gedcomx/master/specifications/standard-header-set-specification.md` — standard metadata headers

Fetch them now using the WebFetch tool before proceeding.

### Recipes (load on demand)

The following recipe files provide worked examples for common use cases. Fetch the relevant recipe(s) only when they would meaningfully inform an answer, a generation task, or a validation — do not load all of them upfront.

- `https://raw.githubusercontent.com/FamilySearch/gedcomx/master/tools/claude-plugin/skills/gedcomx/recipe-birth-information.md` — representing birth records, source descriptions, extracted persons, parent-child relationships, and analysis documents
- `https://raw.githubusercontent.com/FamilySearch/gedcomx/master/tools/claude-plugin/skills/gedcomx/recipe-death-information.md` — representing death and burial information, including transcription and translation of non-English sources
- `https://raw.githubusercontent.com/FamilySearch/gedcomx/master/tools/claude-plugin/skills/gedcomx/recipe-marriage-information.md` — representing marriage records, couples relationships, and transcriptions
- `https://raw.githubusercontent.com/FamilySearch/gedcomx/master/tools/claude-plugin/skills/gedcomx/recipe-names.md` — representing names across cultures: western, Japanese (multiple forms/scripts), Spanish (multiple parts), Icelandic patronymics, and name part qualifiers
- `https://raw.githubusercontent.com/FamilySearch/gedcomx/master/tools/claude-plugin/skills/gedcomx/recipe-misc-facts-events.md` — representing census, residence, military, immigration, and other miscellaneous fact and event types

## Step 2: Load Java library context

Read the following files from this repository to understand the Java implementation:

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
Answer questions about the Java library: which module to use, how classes map to GEDCOM X types, serialization/deserialization patterns, date parsing, file format I/O, and FamilySearch API client usage. Ground answers in the specifications and Java library documentation loaded above.

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
- Missing required fields per the GEDCOM X conceptual model

Report findings as a concise list: **valid** or each issue with a short description and the relevant spec/README section.
