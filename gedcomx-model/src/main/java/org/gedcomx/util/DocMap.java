package org.gedcomx.util;

import org.gedcomx.Gedcomx;
import org.gedcomx.common.URI;
import org.gedcomx.conclusion.Person;
import org.gedcomx.source.SourceDescription;
import org.gedcomx.source.SourceReference;

import java.util.Map;

/**
 * Class for...
 * User: Randy Wilson
 * Date: 6/9/14
 * Time: 3:53 PM
 */
public class DocMap {

  private Map<String, SourceDescription> sourceDescriptionMap;
  private Map<String, Person> personMap;

  public DocMap(Gedcomx doc) {
    sourceDescriptionMap = getSourceDescriptionMap(doc);
    personMap = getPersonMap(doc);
  }

  public Person getPerson(String idOrUrl) {
    return personMap.get(idOrUrl);
  }

  public Person getPerson(URI uri) {
    return uri == null ? null : getPerson(uri.toString());
  }

  public SourceDescription getSourceDescription(String idOrUrl) {
    return sourceDescriptionMap.get(idOrUrl);
  }

  public SourceDescription getSourceDescription(URI uri) {
    return uri == null ? null : getSourceDescription(uri.toString());
  }

  public SourceDescription getSourceDescription(SourceReference sourceReference) {
    return sourceReference == null ? null : getSourceDescription(sourceReference.getDescriptionRef());
  }
}
