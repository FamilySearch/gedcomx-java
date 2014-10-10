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
import org.gedcomx.common.TextValue;
import org.gedcomx.common.URI;
import org.gedcomx.conclusion.*;
import org.gedcomx.records.*;
import org.gedcomx.source.Coverage;
import org.gedcomx.source.SourceDescription;
import org.gedcomx.types.RecordType;

import java.util.*;

/**
 * Class for helping to deal with connecting field values with record descriptors for historical records
 *   and image browse data.
 * Historical records have both structured data (persons with names, gender, facts; and relationships with facts)
 *   and 'fields'. A field often represents what was actually stated within some area of text on a historical
 *   document (or what was strongly implied by that document, such as the male gender of a father in a birth record).
 * Each field has a list of field values, which can be of FieldValueType.Original, meaning what the document
 *   originally said (or structurally implied); or FieldValueType.Interpreted, meaning that a user or system
 *   interpreted. For example, a 'gender' field might have an original field value of "M", which, because it is
 *   in a Mexican census, means "Mujer", which means "Female". So the "Interpreted" field value might say
 *   "Mujer", or might say "Female"; or it is possible that both are included, since it is a list.
 * These field values will be found within a Field of type "http://gedcomx.org/Gender", and that field will be
 *   found inside of the gender inside of the person it applies to. That gender will have a conclusional 'type'
 *   (e.g., "http://gedcomx.org/Female") that software will typically use.
 * The 'structured data' of the record is typically used when dealing with what the record 'meant' or in displaying
 *   genealogical data to be used in copying over to a conclusion tree.
 * The 'fields' of a record are typically used when display what the record originally 'said', in order to help
 *   communicate to a user some of the genealogical nuances that each record can have, including some that don't
 *   translate directly into the standardized conclusional structure that GedcomX supports.
 * Having structured data with embedded fields (plus a list of fields at the person, relationship and record level)
 *   allows general-purpose genealogical use of 'what the record is telling us' (via the structure) as well as
 *   preservation of the special-purpose nuances of a particular record type (via the fields).
 *
 * A GedcomX document that represents a historical record will often have a SourceDescription that has a
 *   record 'descriptor' reference, which is the URL of a GedcomX document that represents the Collection that
 *   the record is found in, along with a "#" and local id of the record descriptor withing that collection's
 *   document, that describes the display labels to be used for displaying a field-value pair view of the
 *   record's field data.
 * This class takes two GedcomX documents: a record and its collection (or, equivalently, the DocMap for both)
 *   and walks the structure of the record to find all of the field values, and builds the maps necessary to
 *   find all the field labelIds, the localized display labels for each labelId, and the values for each labelId
 *   found in the record. (Note that because a Field can have multiple FieldValues of the same type (original or
 *   interpreted), then each labelId can map to a list of values).
 * Also, Census records are different from other records in that each person in a census household can have the
 *   same list of fields, so when displaying field values from census data, it must be done one person at a time.
 *   Therefore, each record will either support getValues(labelId) [i.e., for non-census] or
 *   getValues(person, labelId) [census], but not both.
 * User: Randy Wilson
 * Date: 7/31/2014
 * Time: 11:07 AM
 */
public class FieldMap {
  // DocMaps for the collection and record.
  private DocMap collectionDocMap;
  private DocMap recordDocMap;
  // Main record descriptor for this record from this collection.
  private RecordDescriptor recordDescriptor;
  // Flag for whether this record was a census record (with person-specific fields) or not.
  // A census collection can have the same field IDs over again for each person.
  private boolean isCensus;
  // Map of labelId -> FieldValueDescriptor for that label id.
  private Map<String, FieldValueDescriptor> labelFieldValueDescriptorMap;

  // map of labelId -> list of Strings that appeared in field values with that labelId.
  private Map<String, List<String>> labelValueMap;
  // map of Person -> labelId -> list of field value Strings (for census records).
  // Includes a 'null' Person for record-level field values, if any.
  private Map<Person, Map<String, List<String>>> personLabelValueMap;

  /**
   * Constructor for a collection and record GedcomX document.
   * @param record - GedcomX document for a record (which is in the given collection).
   * @param collection - GedcomX document for a collection (which contains the RecordDescriptor for the record).
   */
  public FieldMap(Gedcomx record, Gedcomx collection) {
    this(new DocMap(record), new DocMap(collection));
  }

  /**
   * Constructor using a DocMap for a collection and record. Use this constructor if you've already built a DocMap
   *   for the record and/or collection.
   * @param recordDocMap - DocMap for a GedcomX document for a record (which is in the given collection)
   * @param collectionDocMap - DocMap for a GedcomX document for a collection (which contains the RecordDescriptor for the record).
   */
  public FieldMap(DocMap recordDocMap, DocMap collectionDocMap) {
    this.collectionDocMap = collectionDocMap;
    this.recordDocMap = recordDocMap;
    recordDescriptor = getRecordDescriptor(collectionDocMap, recordDocMap);
    labelFieldValueDescriptorMap = getLabelFieldValueDescriptorMap(recordDescriptor);
    isCensus = isCensus(recordDocMap);
    if (isCensus) {
      personLabelValueMap = getPersonLabelValueMap(recordDocMap.getDocument());
    }
    else {
      labelValueMap = getLabelValuesMap(getAllFields(recordDocMap.getDocument()));
    }
  }

  /**
   * Get the DocMap for the collection that the record is found in.
   * @return DocMap for the collection that the record is found in.
   */
  public DocMap getCollectionDocMap() {
    return collectionDocMap;
  }

  /**
   * Get the DocMap for the record.
   * @return DocMap for the record.
   */
  public DocMap getRecordDocMap() {
    return recordDocMap;
  }

  /**
   * Get the GedcomX document for the collection that the record is found in.
   * @return GedcomX document for the collection that the record is found in.
   */
  public Gedcomx getCollection() {
    return collectionDocMap.getDocument();
  }

  /**
   * Get the GedcomX document for the record.
   * @return GedcomX document for the record.
   */
  public Gedcomx getRecord() {
    return collectionDocMap.getDocument();
  }

  /**
   * Get the display label for the given labelId in the closest language available to the one given.
   * @param labelId - labelId to get the display value for (e.g., "PR_NAME")
   * @param language - Preferred language to get the display label in. null => use en-US.
   * @return Display label to use for the labelId, or null if there is not one.
   */
  public String getDisplayLabel(String labelId, String language) {
    if (labelFieldValueDescriptorMap != null) {
      FieldValueDescriptor fieldValueDescriptor = labelFieldValueDescriptorMap.get(labelId);
      if (fieldValueDescriptor != null && fieldValueDescriptor.getDisplayLabels() != null) {
        TextValue bestValue = LocaleUtil.findClosestLocale(fieldValueDescriptor.getDisplayLabels(), new Locale(language));
        return bestValue == null ? null : bestValue.getValue();
      }
    }
    return null;
  }

  /**
   * Get a list of values that had the given labelId in the record. Must be a non-census record, or else the person
   *   must be specified.
   * @param labelId - LabelId to get the values for
   * @return List of values for the given labelId, or null if none.
   */
  public List<String> getValues(String labelId) {
    if (isCensus) {
      throw new IllegalArgumentException("Can't call getValues(labelId) on census collection. Use getValues(person, labelId) instead.");
    }
    return labelValueMap.get(labelId);
  }

  /**
   * Get a list of values that had the given labelId in the record for the given person.
   * Must be a census record.
   * @param person - person to get values for (null => get record-level values for the given labelId, if any)
   * @param labelId - LabelId to get the values for
   * @return List of values for the given labelId, or null if none.
   */
  public List<String> getValues(Person person, String labelId) {
    if (!isCensus) {
      throw new IllegalArgumentException("Can't call getValues(person, labelId) on non-census collection");
    }
    Map<String, List<String>> labelValueMap = personLabelValueMap.get(person);
    return labelValueMap == null ? null : labelValueMap.get(labelId);
  }

  /**
   * Get the FieldValueDescriptor for the given field value label ID.
   * @param labelId - Label ID to find the FieldValueDescriptor for.
   * @return FieldValueDescriptor with the given label ID, or null if not found.
   */
  public FieldValueDescriptor getFieldValueDescriptor(String labelId) {
    return labelFieldValueDescriptorMap == null ? null : labelFieldValueDescriptorMap.get(labelId);
  }

  /**
   * Get a map of Person to labelId to list of Strings for field values that had that label ID within that person.
   * Includes a null Person in the map if there were any record-level field values.
   * @param record - Record to build map for.
   * @return map of Person to labelId to list of field values.
   */
  private static Map<Person, Map<String, List<String>>> getPersonLabelValueMap(Gedcomx record) {
    Map<Person, List<Field>> personFieldMap = getPersonFieldMap(record);
    Map<Person, Map<String, List<String>>> personMap = new LinkedHashMap<Person, Map<String, List<String>>>();

    for (Person person : personFieldMap.keySet()) {
      personMap.put(person, getLabelValuesMap(personFieldMap.get(person)));
    }
    return personMap;
  }

  /**
   * Get a map of labelId to list of values for that labelId for the given person.
   * Used only with census collections.
   * @param person - Person to get the map for
   * @return map of labelId to list of values for that labelId for the given person.
   */
  public Map<String, List<String>> getPersonLabelValueMap(Person person) {
    if (!isCensus) {
      throw new IllegalArgumentException("Can't call getPersonLabelValueMap(person) on non-census collection");
    }
    return personLabelValueMap.get(person);
  }

  /**
   * Get a map of labelId to list of values for that labelId.
   * Used only with non-census collections.
   * @return map of labelId to list of values for that labelId for the given person.
   */
  public Map<String, List<String>> getLabelValueMap() {
    if (isCensus) {
      throw new IllegalArgumentException("Can't call getLabelValueMap() on census collection. Use getPersonLabelValueMap(person), and use person=null for record-level values.");
    }
    return labelValueMap;
  }

  /**
   * Get the map of labelId to FieldValueDescriptor used by this FieldMap.
   * @return  map of labelId to FieldValueDescriptor used by this FieldMap, or null if there were no FieldDescriptors.
   */
  public Map<String, FieldValueDescriptor> getLabelFieldValueDescriptorMap() {
    return labelFieldValueDescriptorMap;
  }

  /**
   * Get the RecordDescriptor that goes with this record.
   * @return RecordDescriptor that goes with this record.
   */
  public RecordDescriptor getRecordDescriptor() {
    return recordDescriptor;
  }

  /**
   * Find the RecordDescriptor from the collection document that is referenced by the main source description
   *   from the record document, i.e., find the record's record descriptor in the collection.
   * @param collectionDocMap - DocMap for the collection GedcomX document.
   * @param recordDocMap - DocMap for the record GedcomX document.
   * @return Record's RecordDescriptor, or null if not found.
   */
  public static RecordDescriptor getRecordDescriptor(DocMap collectionDocMap, DocMap recordDocMap) {
    ResourceReference ref = recordDocMap.getMainSourceDescription().getDescriptorRef();
    if (ref != null && ref.getResource() != null) {
      String uri = ref.getResource().toString();
      if (uri != null) {
        int pos = uri.indexOf("#");
        if (pos > 0) {
          return collectionDocMap.getRecordDescriptor(uri.substring(pos + 1));
        }
      }
    }
    return null;
  }

  /**
   * Get a map of labelId to FieldValueDescriptor for that label id.
   * @param recordDescriptor - RecordDescriptor to build the map from.
   * @return Map of labelId to FieldValueDescriptor, or null if the RecordDescriptor had no fields.
   */
  public static Map<String, FieldValueDescriptor> getLabelFieldValueDescriptorMap(RecordDescriptor recordDescriptor) {
    if (recordDescriptor != null && recordDescriptor.getFields() != null) {
      Map<String, FieldValueDescriptor> map = new LinkedHashMap<String, FieldValueDescriptor>();
      for (FieldDescriptor fieldDescriptor : recordDescriptor.getFields()) {
        if (fieldDescriptor.getValues() != null) {
          for (FieldValueDescriptor fieldValueDescriptor : fieldDescriptor.getValues()) {
            if (map.get(fieldValueDescriptor.getLabelId()) != null) {
              throw new IllegalStateException("Multiple field value descriptors for label id '" + fieldValueDescriptor.getLabelId() + "'");
            }
            map.put(fieldValueDescriptor.getLabelId(), fieldValueDescriptor);
          }
        }
      }
      return map;
    }
    return null;
  }

  /**
   * Get a map of Person to the list of Fields for that person.  A 'null' person is also
   *   included if there are any record-level fields.
   * @param record - record to get field map from
   * @return map of Person (or null for record-level fields) to the list of fields for that person.
   */
  public static Map<Person, List<Field>> getPersonFieldMap(Gedcomx record) {
    Map<Person, List<Field>> personFieldsMap = new LinkedHashMap<Person, List<Field>>();
    addFields(record.getFields(), null, personFieldsMap);
    if (record.getPersons() != null) {
      for (Person person : record.getPersons()) {
        addFields(person.getFields(), person, personFieldsMap);
        if (person.getGender() != null) {
          addFields(person.getGender().getFields(), person, personFieldsMap);
        }
        if (person.getNames() != null) {
          for (Name name : person.getNames()) {
            if (name.getNameForms() != null) {
              for (NameForm nameForm : name.getNameForms()) {
                addFields(nameForm.getFields(), person, personFieldsMap);
                if (nameForm.getParts() != null) {
                  for (NamePart namePart : nameForm.getParts()) {
                    addFields(namePart.getFields(), person, personFieldsMap);
                  }
                }
              }
            }
          }
        }
        if (person.getFacts() != null) {
          for (Fact fact : person.getFacts()) {
            addFields(fact.getFields(), person, personFieldsMap);
            if (fact.getDate() != null) {
              addFields(fact.getDate().getFields(), person, personFieldsMap);
            }
            if (fact.getPlace() != null) {
              addFields(fact.getPlace().getFields(), person, personFieldsMap);
            }
          }
        }
      }
    }
    return personFieldsMap;
  }

  /**
   * Create a list of all of the fields occurring in the given GedcomX record, including those found
   *   within the various values.  Note that this is a flat list that can't distinguish between the
   *   fields with the same label that belong to different persons, so only call when you're sure there
   *   will be no duplicates (as with a non-census record or a mapping Template).
   * @param record - GedcomX to get fields for.
   * @return list of fields occurring in the GedcomX record.
   */
  public static List<Field> getAllFields(Gedcomx record) {
    List<Field> fields = new ArrayList<Field>();
    Map<Person, List<Field>> personFieldMap = getPersonFieldMap(record);
    if (record.getPersons() != null) {
      for (Person person : record.getPersons()) {
        addFields(personFieldMap.get(person), fields);
      }
    }
    // Add record-level fields.
    addFields(personFieldMap.get(null), fields);
    return fields;
  }




  /**
   * Get a map of labelId to values from all of the FieldValues that appear in the given list of Fields.
   * If two FieldValues have the same labelId, then they are put into the same list.
   * @param fields - list of Fields to look in for FieldValues.
   * @return map of labelId to FieldValue values.
   */
  public static Map<String, List<String>> getLabelValuesMap(List<Field> fields) {
    Map<String, List<String>> labelValueMap = new LinkedHashMap<String, List<String>>();
    if (fields != null) {
      for (Field field : fields) {
        if (field.getValues() != null) {
          for (FieldValue fieldValue : field.getValues()) {
            List<String> values = labelValueMap.get(fieldValue.getLabelId());
            if (values == null) {
              values = new ArrayList<String>();
              labelValueMap.put(fieldValue.getLabelId(), values);
            }
            values.add(fieldValue.getText());
          }
        }
      }
    }
    return labelValueMap;
  }

  /**
   * Add the given list of Fields (if not empty or null) to the list of fields for the given person,
   *   in the personFieldMap.  If there is no list for the given person, then one is created and
   *   added to the map.
   * @param listToAdd - List of Fields to add (or null if none).
   * @param person - Person to whose list the fields should be added (null => record-level fields)
   * @param personFieldMap - Map to add the list of fields to.
   */
  private static void addFields(List<Field> listToAdd, Person person, Map<Person, List<Field>> personFieldMap) {
    if (listToAdd != null) {
      List<Field> fieldList = personFieldMap.get(person);
      if (fieldList == null) {
        fieldList = new ArrayList<Field>();
        personFieldMap.put(person, fieldList);
      }
      fieldList.addAll(listToAdd);
    }
  }

  /**
   * Add the given list of fields to the master list.
   * @param listToAdd - List of fields to add.
   * @param allFields - List to add the fields to.
   */
  private static void addFields(List<Field> listToAdd, List<Field> allFields) {
    if (listToAdd != null) {
      allFields.addAll(listToAdd);
    }
  }

  /**
   * Tell whether the given record is a Census record, i.e., if it has a SourceDescription with a Coverage with
   *   a RecordType of Census.
   * @param record - GedcomX record to examine
   * @return true if this is a census record, false otherwise.
   */
  public static boolean isCensus(Gedcomx record) {
    URI descriptionRef = record.getDescriptionRef();
    if (descriptionRef != null) {
      for (SourceDescription sourceDescription : record.getSourceDescriptions()) {
        if (("#" + sourceDescription.getId()).equals(descriptionRef.toString())) {
          return isCensus(sourceDescription);
        }
      }
    }
    return false;
  }

  /**
   * Tell whether the record with the given DocMap is a Census record, i.e., if it has a SourceDescription with a Coverage with
   *   a RecordType of Census.
   * @param recordDocMap - DocMap of the GedcomX record to examine
   * @return true if this is a census record, false otherwise.
   */
  public static boolean isCensus(DocMap recordDocMap) {
    return isCensus(recordDocMap.getMainSourceDescription());
  }

  /**
   * Tell whether the given SourceDescription has 'coverage' with a RecordType of census.
   * @param sourceDescription - SourceDescription to examine for coverage.
   * @return true if this is a census record, false otherwise.
   */
  public static boolean isCensus(SourceDescription sourceDescription) {
    if (sourceDescription != null && sourceDescription.getCoverage() != null) {
      for (Coverage coverage : sourceDescription.getCoverage()) {
        if (coverage.getKnownRecordType() == RecordType.Census) {
          return true;
        }
      }
    }
    return false;
  }
}
