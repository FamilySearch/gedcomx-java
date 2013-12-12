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
package org.gedcomx.rs.client.util;

import org.gedcomx.Gedcomx;
import org.gedcomx.conclusion.Person;
import org.gedcomx.conclusion.Relationship;
import org.gedcomx.links.Link;
import org.gedcomx.rs.Rel;

import java.util.*;

/**
 * Engine for loading embedded links.
 *
 * @author Ryan Heaton
 */
public class EmbeddedLinkLoader {

  public static final Set<String> DEFAULT_EMBEDDED_LINK_RELS = Collections.unmodifiableSet(new TreeSet<String>(Arrays.asList(
    Rel.CHILD_RELATIONSHIPS,
    Rel.CONCLUSIONS,
    Rel.EVIDENCE_REFERENCES,
    Rel.MEDIA_REFERENCES,
    Rel.NOTES,
    Rel.PARENT_RELATIONSHIPS,
    Rel.SOURCE_REFERENCES,
    Rel.SPOUSE_RELATIONSHIPS
  )));

  protected Set<String> getEmbeddedLinkRels() {
    return DEFAULT_EMBEDDED_LINK_RELS;
  }

  public List<Link> loadEmbeddedLinks(Gedcomx entity) {
    ArrayList<Link> embeddedLinks = new ArrayList<Link>();
    Set<String> embeddedRels = getEmbeddedLinkRels();

    List<Person> persons = entity.getPersons();
    if (persons != null) {
      for (Person person : persons) {
        for (String embeddedRel : embeddedRels) {
          Link link = person.getLink(embeddedRel);
          if (link != null) {
            embeddedLinks.add(link);
          }
        }
      }
    }

    List<Relationship> relationships = entity.getRelationships();
    if (relationships != null) {
      for (Relationship relationship : relationships) {
        for (String embeddedRel : embeddedRels) {
          Link link = relationship.getLink(embeddedRel);
          if (link != null) {
            embeddedLinks.add(link);
          }
        }
      }
    }

    return embeddedLinks;
  }

}
