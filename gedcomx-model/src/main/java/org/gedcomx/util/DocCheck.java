/**
 * Copyright Intellectual Reserve, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gedcomx.util;

import org.gedcomx.Gedcomx;
import org.gedcomx.common.ResourceReference;
import org.gedcomx.conclusion.*;
import org.gedcomx.records.Field;
import org.gedcomx.records.FieldValue;
import org.gedcomx.records.RecordSet;
import org.gedcomx.source.SourceDescription;
import org.gedcomx.source.SourceReference;

import java.util.List;

/**
 * Class for checking a GedcomX document to make sure its references are consistent.
 * User: Randy Wilson
 * Date: 10/2/2014
 * Time: 3:49 PM
 */
public class DocCheck {
  /**
   * Check a GedcomX document for problems. Check the following:
   * - There is a SourceDescription for the document, referenced by 'description'.
   * - All SourceReference's point to a valid, local SourceDescription.
   *   - SourceReferences can appear as componentOf in a SourceDescription,
   *   - and also in any Conclusion
   * - All relationships point to persons who are either (a) inside the doc, referenced by "#" + local ID, or
   *   (b) outside the doc, referenced by full URL.
   * - All persons, records and collections have an identifier of type "Persistent" or "Primary".
   * - PlaceReference references a valid PlaceDescription.
   *
   * @param doc - Document to check
   * @return String containing a list of errors, or null if there were no errors.
   */
  public static String checkDocument(Gedcomx doc) {
    StringBuilder errors = new StringBuilder();
    DocMap docMap = new DocMap(doc);

    // Make sure main source description exists
    if (docMap.getMainSourceDescription() == null) {
      errors.append("Error 1: Missing main source description reference. Each GedcomX document should have a SourceDescription describing what the document is about, and 'description' should reference it.\n");
    }

    // Test all source descriptions:
    //  - componentOf and sources (and any sources referenced by fields) point to valid, included SourceDescriptions
    if (doc.getSourceDescriptions() != null) {
      for (SourceDescription sourceDescription : doc.getSourceDescriptions()) {
        if (sourceDescription.getComponentOf() != null && docMap.getSourceDescription(sourceDescription.getComponentOf()) == null) {
          errors.append("Error 2: ").append(getSourceDescriptionName(sourceDescription))
                  .append(" has componentOf that references ")
                  .append(getSourceReferenceName(sourceDescription.getComponentOf()))
                  .append(" but it cannot be found in the document.\n");
        }
        if (sourceDescription.getSources() != null) {
          checkSources(errors, "Error 3: " + getSourceDescriptionName(sourceDescription), sourceDescription.getSources(), null, sourceDescription.getFields(), docMap);
        }
      }
    }

    // Test relationships
    // - There is at least one person in the relationship.
    // - Any persons referenced are either (a) local and referenced by local id (starting with "#");
    //     or (b) NOT local, and referenced by full URL.
    // - sources (and any sources referenced by fields, facts, or fields of facts) point to a valid, included SourceDescription.
    if (doc.getRelationships() != null) {
      for (Relationship relationship : doc.getRelationships()) {
        boolean foundPerson = false;
        for (ResourceReference ref : new ResourceReference[]{relationship.getPerson1(), relationship.getPerson2()}) {
          if (ref != null) {
            if (ref.getResource() == null && ref.getResourceId() != null) {
              errors.append("Error 4: ResourceId without resource URI in relationship. Must specify always resource URI, even if optional resourceId is included.\n");
            }
            else {
              Person person = docMap.getPerson(ref);
              if (person == null) {
                // This is expected (and, indeed, required) if the person in the relationship is outside of the document.
                // But not if the URI begins with "#", in which case it is a reference to someone within the document.
                if (ref.getResource() != null && ref.getResource().toString().startsWith("#")) {
                  errors.append("Error 5: Local person id '").append(ref.getResource().toString()).append("' not found.\n");
                }
              }
              else {
                foundPerson = true;
                // A person found locally should be referenced by a local URI instead of a full URI.
                if (!ref.getResource().toString().startsWith("#")) {
                  errors.append("Warning 6: Relationship should use local id (")
                          .append(person.getId() != null ? "'#" + person.getId() + "'" : "though the person does not have one")
                          .append(") for person instead of full URI ").append(ref.getResource().toString()).append("\n");
                }
              }
            }
          }
        }
        if (!foundPerson) {
          errors.append("Error 7: A relationship failed to reference anyone inside the document. At least one person must be in the document.\n");
        }
        checkSources(errors, "Error 8: relationship", relationship.getSources(), relationship.getFacts(), relationship.getFields(), docMap);
        checkSources(errors, "Error 9: relationship (media)", relationship.getMedia(), null, null, docMap);
      }
    }

    /**
     * Check sources (and agents on attributions) to make sure they all reference properly.
     */
    if (doc.getPersons() != null) {
      for (Person person : doc.getPersons()) {
        String personString = getPersonString(person);
        checkSources(errors, "Error 10: " + personString, person.getSources(), person.getFacts(), person.getFields(), docMap);
        // Check the 'media' list on the person, if any.
        checkSources(errors, "Error 11: " + personString + " (media)", person.getMedia(), null, null, docMap);
        if (person.getGender() != null) {
          checkSources(errors, "Error 12: " + personString + " (gender)", person.getGender().getSources(), null, person.getGender().getFields(), docMap);
        }
      }
    }

    return errors.length() == 0 ? null : errors.toString();
  }

  /**
   * Get a string that describes which person this is, using the local id, first identifier, or name, in that order.
   * @param person - person to get identifying string for
   * @return Identifying string for the given person.
   */
  private static String getPersonString(Person person) {
    if (person.getId() != null) {
      return "Person #" + person.getId();
    }
    if (person.getIdentifiers() != null) {
      for (Identifier identifier : person.getIdentifiers()) {
        if (identifier.getValue() != null) {
          return "Person " + identifier.getValue().toString();
        }
      }
    }
    if (person.getName() != null) {
      NameForm nameForm = person.getName().getNameForm();
      if (nameForm != null) {
        if (nameForm.getFullText() != null) {
          return nameForm.getFullText();
        }
        if (nameForm.getParts() != null) {
          StringBuilder fullName = new StringBuilder();
          boolean isFirst = true;
          for (NamePart namePart : nameForm.getParts()) {
            if (namePart.getValue() != null && namePart.getValue().length() > 0) {
              fullName.append(namePart.getValue());
            }
            if (isFirst) {
              isFirst = false;
            } else {
              fullName.append(" ");
            }
          }
          if (fullName.length() > 0) {
            return fullName.toString();
          }
        }
      }
    }
    return "<Unidentified Person>";
  }

  /**
   * Make sure that the given list of SourceReferences all reference SourceDescriptions that can be found in the DocMap.
   * Also make sure any sources referenced from the given fields, facts or fields in those facts are also found.
   * Finally, also make sure that any places referenced in those facts have corresponding place descriptions as well.
   * @param errors - StringBuilder to append errors to.
   * @param whereReferencedFrom - Name of the object that had this list of sources.
   * @param sources - List of sources to check (ignored if null)
   * @param fields - List of fields to check (ignored if null)
   * @param docMap - DocMap for the GedcomX document being validated.
   */
  private static void checkSources(StringBuilder errors, String whereReferencedFrom, List<SourceReference> sources, List<Fact> facts, List<Field> fields, DocMap docMap) {
    if (sources != null) {
      for (SourceReference source : sources) {
        SourceDescription sd = docMap.getSourceDescription(source);
        if (sd == null) {
          errors.append("Could not find referenced source ").append(getSourceReferenceName(source)).append(" from ").append(whereReferencedFrom).append("\n");
        }
      }
    }
    if (facts != null) {
      for (Fact fact : facts) {
        checkSources(errors, whereReferencedFrom + " (fact)", fact.getSources(), null, fact.getFields(), docMap);
        if (fact.getPlace() != null) {
          // Make sure place references that refer to local URIs have a corresponding PlaceDescription
          if (fact.getPlace().getDescriptionRef() != null && fact.getPlace().getDescriptionRef().toString().startsWith("#")) {
            if(docMap.getPlaceDescription(fact.getPlace().getDescriptionRef()) == null) {
              errors.append("Could not find referenced place ").append(fact.getPlace().getDescriptionRef().toString()).append("\n");
            }
          }
        }
      }
    }
    if (fields != null) {
      for (Field field : fields) {
        if (field.getValues() != null) {
          for (FieldValue fieldValue : field.getValues()) {
            checkSources(errors, whereReferencedFrom + " (field value)", fieldValue.getSources(), null, null, docMap);
          }
        }
      }
    }
  }


  /**
   * Get a String to use as the name of a SourceReference (usually the String of its descriptionRef URI).
   * @param sourceReference - SourceReference to get a display string for.
   * @return String of the SourceReference's description reference URI, "&lt;null&gt;" if null.
   */
  private static String getSourceReferenceName(SourceReference sourceReference) {
    if (sourceReference == null) {
      return "<null>";
    }
    if (sourceReference.getDescriptionRef() == null) {
      return "<no description ref>";
    }
    return sourceReference.getDescriptionRef().toString();
  }

  private static String getSourceDescriptionName(SourceDescription sd) {
    if (sd.getId() != null && sd.getId().length() > 0) {
      return "SourceDescription with id=" + sd.getId();
    }
    if (sd.getAbout() != null) {
      return "SourceDescription with about=" + sd.getAbout().toString();
    }
    if (sd.getIdentifiers() != null) {
      for (Identifier identifier : sd.getIdentifiers()) {
        if (identifier.getValue() != null) {
          return "SourceDescription for URI " + identifier.getValue().toString();
        }
      }
    }
    if (sd.getTitle() != null) {
      return "SourceDescription with title " + sd.getTitle().getValue();
    }
    if (sd.getCitation() != null) {
      return "SourceDescription with citation " + sd.getCitation().getValue();
    }
    return "SourceDescription with no identifiers, title or citation";
  }

  /**
   * Check a GedcomX document for problems. Calls checkDocument(doc), but further makes sure that all
   *   field values with a labelId can find the labelId in the RecordDescriptor in the accompanying collection doc.
   * @param doc - Historical Record document to check.
   * @param collection - Collection document to find RecordDescriptor in.
   * @return String containing a list of errors, if any, or null if there were no errors.
   */
  public static String checkDocument(Gedcomx doc, Gedcomx collection) {
    FieldMap fieldMap = new FieldMap(doc, collection);
    StringBuilder errors = new StringBuilder();
    addIfNeeded(errors, checkDocument(doc));
    checkFields(errors, "Record fields", doc.getFields(), fieldMap);
    if (doc.getPersons() != null) {
      for (Person person : doc.getPersons()) {
        String personString = getPersonString(person);
        checkFields(errors, "Person fields for person " + personString, person.getFields(), fieldMap);
        if (person.getGender() != null) {
          checkFields(errors, "Gender for person " + personString, person.getGender().getFields(), fieldMap);
        }
        if (person.getFacts() != null) {
          for (Fact fact : person.getFacts()) {
            checkFields(errors, "Facts for person " + personString, fact.getFields(), fieldMap);
          }
        }
        if (person.getNames() != null) {
          for (Name name : person.getNames()) {
            if (name.getNameForms() != null) {
              for (NameForm nameForm : name.getNameForms()) {
                checkFields(errors, "Names for person " + personString, nameForm.getFields(), fieldMap);
                if (nameForm.getParts() != null) {
                  for (NamePart namePart : nameForm.getParts()) {
                    checkFields(errors, "Name parts for person " + personString, namePart.getFields(), fieldMap);
                  }
                }
              }
            }
          }
        }
      }
    }
    return errors.length() == 0 ? null : errors.toString();
  }

  private static void checkFields(StringBuilder errors, String context, List<Field> fields, FieldMap fieldMap) {
    if (fields != null) {
      for (Field field : fields) {
        if (field.getValues() != null) {
          for (FieldValue fieldValue : field.getValues()) {
            if (fieldValue.getLabelId() != null && fieldMap.getFieldValueDescriptor(fieldValue.getLabelId()) == null) {
              errors.append(context).append(": Field with labelId '").append(fieldValue.getLabelId()).append("' had no FieldValueDescriptor\n");
            }
          }
        }
      }
    }
  }

  /**
   * Check a GedcomX RecordSet for problems. Calls checkDocument(doc, collection) on each record.
   * @param records - Set of GedcomX documents to check.
   * @param collection - Collection to use to check RecordDescriptors and labelIds.
   *                     null => use records.getMetadata(), if any, or else don't check label IDs.
   * @return String containing a list of errors, if any, or null if there were no errors.
   */
  public static String checkRecordSet(RecordSet records, Gedcomx collection) {
    StringBuilder errors = new StringBuilder();
    Gedcomx metadata = collection == null ? records.getMetadata() : collection;
    if (metadata != null) {
      addIfNeeded(errors, checkDocument(metadata));
    }
    if (records.getRecords() != null) {
      for (Gedcomx record : records.getRecords()) {
        addIfNeeded(errors, checkDocument(record, metadata));
      }
    }
    return errors.length() == 0 ? null : errors.toString();
  }

  private static void addIfNeeded(StringBuilder errors, String result) {
    if (result != null) {
      errors.append(result);
    }
  }
}
