package org.gedcomx.util;

import org.gedcomx.Gedcomx;
import org.gedcomx.agent.Agent;
import org.gedcomx.common.URI;
import org.gedcomx.conclusion.Identifier;
import org.gedcomx.conclusion.Person;
import org.gedcomx.records.RecordDescriptor;
import org.gedcomx.source.SourceDescription;
import org.gedcomx.source.SourceReference;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for simplifying the lookup of Person, SourceDescription, RecordDescription or Agent objects
 *   in a GedcomX document.
 * Each object can be looked up by its local 'id' (with or without a preceding "#"), using a String or URI.
 * Persons can also be looked up by any of their identifiers, and SourceDescriptions can be looked up by
 *   any of the identifiers of the entity they are describing.
 * User: Randy Wilson
 * Date: 6/9/14
 * Time: 3:53 PM
 */
public class DocMap {
  private SourceDescription mainSourceDescription;
  private Map<String, SourceDescription> sourceDescriptionMap;
  private Map<String, Person> personMap;
  private Map<String, RecordDescriptor> recordDescriptorMap;
  private Map<String, Agent> agentMap;

  /**
   * Constructor. Create an object that allows convenient lookup of things in a GedcomX document.
   * @param doc - GedcomX document to build maps for.
   */
  public DocMap(Gedcomx doc) {
    // Build all the maps.
    update(doc);
  }

  /**
   * Update the maps with the given GedcomX document (e.g., when the document has changed).
   * @param doc - GedcomX document to rebuild maps for.
   */
  public void update(Gedcomx doc) {
    sourceDescriptionMap = getSourceDescriptionMap(doc);
    personMap = getPersonMap(doc);
    recordDescriptorMap = getRecordDescriptorMap(doc);
    agentMap = getAgentMap(doc);
    mainSourceDescription = getSourceDescription(doc.getDescriptionRef());
  }

  /**
   * Get the SourceDescription referenced by the GedcomX document's descriptionRef.
   * @return SourceDescription referenced by the GedcomX document's descriptionRef, or null if none.
   */
  public SourceDescription getMainSourceDescription() {
    return mainSourceDescription;
  }

  /**
   * Get the Person described by the main SourceDescription.
   * Returns null if there is no main SourceDescription or if this document's main thing is not a Person.
   * @return Person referenced by the main SourceDescription.
   */
  public Person getMainPerson() {
    return mainSourceDescription == null ? null : getPerson(mainSourceDescription.getAbout());
  }

  /**
   * Get the person from the GedcomX document that has the given id (with or without "#") or an identifier
   *   that matches the given idOrUrl.
   * Can look a person up by "p1", "#p1" or "http://whatever.com/persons/12345".
   * @param idOrUrl - local person id (with or without "#") or any person identifier
   * @return Person with the given id or identifier.
   */
  public Person getPerson(String idOrUrl) {
    return personMap.get(idOrUrl);
  }

  /**
   * Get the person from the GedcomX document that has the given id (with or without "#") or identifier.
   * Can look a person up by "p1", "#p1" or "http://whatever.com/persons/12345".
   * @param uri - local person id (with or without "#") or any person identifier
   * @return Person with the given id or identifier.
   */
  public Person getPerson(URI uri) {
    return uri == null ? null : getPerson(uri.toString());
  }

  /**
   * Get the SourceDescription from the GedcomX document that has the given id (with or without "#") or an identifier
   *   that matches the given idOrUrl.
   * Can look a source description up by "sd1", "#sd1" or "http://whatever.com/records/12345", if that is an identifier that
   *   the source description is describing and has listed as one of the identifiers.
   * @param idOrUrl - local person id (with or without "#") or any person identifier
   * @return Person with the given id or identifier.
   */
  public SourceDescription getSourceDescription(String idOrUrl) {
    return sourceDescriptionMap.get(idOrUrl);
  }

  /**
   * Get the SourceDescription from the GedcomX document that has the given id (with or without "#") or an identifier
   *   that matches the given idOrUrl.
   * Can look a source description up by "sd1", "#sd1" or "http://whatever.com/records/12345", if that is an identifier that
   *   the source description is describing and has listed as one of the identifiers.
   * @param uri - local source description id (with or without "#") or any identifier in the source description.
   * @return Person with the given id or identifier.
   */
  public SourceDescription getSourceDescription(URI uri) {
    return uri == null ? null : getSourceDescription(uri.toString());
  }

  /**
   * Get the SourceDescription referenced by the given SourceReference. Typically the SourceReference will reference
   *  the SourceDescription by "#" + its local id.
   * @param sourceReference - SourceReference that points to a SourceDescription.
   * @return SourceDescription referenced by the given SourceReference.
   */
  public SourceDescription getSourceDescription(SourceReference sourceReference) {
    return sourceReference == null ? null : getSourceDescription(sourceReference.getDescriptionRef());
  }

  /**
   * Find the RecordDescriptor referenced by the given id (with or without a "#"), or URL (with "#" and a local id).
   * @param recordDescriptorIdOrUrl - local id of a RecordDescriptor (with or without a "#"), or URL of recordDescriptor (with "#" + local id at end).
   * @return RecordDescriptor referenced by the given id or URL.
   */
  public RecordDescriptor getRecordDescriptor(String recordDescriptorIdOrUrl) {
    if (recordDescriptorIdOrUrl == null) {
      return null;
    }
    if (recordDescriptorIdOrUrl.contains("#")) {
      recordDescriptorIdOrUrl = recordDescriptorIdOrUrl.substring(recordDescriptorIdOrUrl.indexOf("#") + 1);
    }
    return recordDescriptorMap.get(recordDescriptorIdOrUrl);
  }

  /**
   * Find the RecordDescriptor referenced by the given id (with or without a "#").
   * @param recordDescriptorId - URI containing the local id of a RecordDescriptor (with or without a "#").
   * @return RecordDescriptor referenced by the given id.
   */
  public RecordDescriptor getRecordDescriptor(URI recordDescriptorId) {
    return recordDescriptorId == null ? null : getRecordDescriptor(recordDescriptorId.toString());
  }

  /**
   * Find the Agent with the given local id.
   * @param agentId - id of an agent (with or without an initial "#").
   * @return Agent with the given id.
   */
  public Agent getAgent(String agentId) {
    return agentMap.get(agentId);
  }

  /**
   * Find the Agent with the given local id.
   * @param agentId - URI containing the id of an agent (with or without an initial "#").
   * @return Agent with the given id.
   */
  public Agent getAgent(URI agentId) {
    return agentId == null ? null : getAgent(agentId.toString());
  }


  /**
   * Create a map of id (and "#" + id) and all identifier URI strings to SourceDescription with that ID,
   *   to make it easier to look up SourceDescriptions that are referenced elsewhere.
   * @param doc - GedcomX document to create a map for.
   * @return map of id (and "#" + id) to SourceDescription.
   */
  public static Map<String, SourceDescription> getSourceDescriptionMap(Gedcomx doc) {
    Map<String, SourceDescription> map = new HashMap<String, SourceDescription>();
    if (doc.getSourceDescriptions() != null) {
      for (SourceDescription sourceDescription : doc.getSourceDescriptions()) {
        map.put(sourceDescription.getId(), sourceDescription);
        map.put("#" + sourceDescription.getId(), sourceDescription);
        if (sourceDescription.getIdentifiers() != null) {
          for (Identifier identifier : sourceDescription.getIdentifiers()) {
            if (identifier.getValue() != null) {
              map.put(identifier.getValue().toString(), sourceDescription);
            }
          }
        }
      }
    }
    return map;
  }

  /**
   * Create a map of local id (and "#" + id) as well as all person identifiers to the local Person object
   *  with that id or identifier.
   * @param doc - GedcomX document to create a map for.
   * @return map of local id, "#" + id, and all URIs for each Person in the document to that Person.
   */
  public static Map<String, Person> getPersonMap(Gedcomx doc) {
    Map<String, Person> map = new HashMap<String, Person>();
    if (doc.getPersons() != null) {
      for (Person person : doc.getPersons()) {
        if (person.getId() != null) {
          map.put(person.getId(), person);
          map.put("#" + person.getId(), person);
        }
        if (person.getIdentifiers() != null) {
          for (Identifier identifier : person.getIdentifiers()) {
            if (identifier.getValue() != null) {
              map.put(identifier.getValue().toString(), person);
            }
          }
        }
      }
    }
    return map;
  }

  /**
   * Create a map of local id (and "#" + id) to the Agent that has that id.
   * If there are no agents, an empty (but non-null) map is returned.
   * @param doc - document to find agents for
   * @return Map of local id (and "#" + id) to each Agent in the doc.
   */
  public static Map<String, Agent> getAgentMap(Gedcomx doc) {
    Map<String, Agent> map = new HashMap<String, Agent>();
    if (doc.getAgents() != null) {
      for (Agent agent : doc.getAgents()) {
        map.put(agent.getId(), agent);
        map.put("#" + agent.getId(), agent);
      }
    }
    return map;
  }

  /**
   * Create a map of local id (and "#" + id) to the RecordDescriptor that has that id.
   * If there are no record descriptors, an empty (but non-null) map is returned.
   * @param doc - document to find record descriptors for
   * @return Map of local id (and "#" + id) to each RecordDescriptor in the doc.
   */
  public static Map<String, RecordDescriptor> getRecordDescriptorMap(Gedcomx doc) {
    Map<String, RecordDescriptor> map = new HashMap<String, RecordDescriptor>();
    if (doc.getRecordDescriptors() != null) {
      for (RecordDescriptor recordDescriptor : doc.getRecordDescriptors()) {
        map.put(recordDescriptor.getId(), recordDescriptor);
        map.put("#" + recordDescriptor.getId(), recordDescriptor);
      }
    }
    return map;
  }
}
