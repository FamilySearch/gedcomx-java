/**
 * Copyright Intellectual Reserve, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.familysearch.platform;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.familysearch.platform.artifacts.ArtifactMetadata;
import org.familysearch.platform.ct.ChangeInfo;
import org.familysearch.platform.ct.ChildAndParentsRelationship;
import org.familysearch.platform.ct.DiscussionReference;
import org.familysearch.platform.ct.FamilySearchIdentifierType;
import org.familysearch.platform.ct.MatchInfo;
import org.familysearch.platform.ct.Merge;
import org.familysearch.platform.ct.MergeAnalysis;
import org.familysearch.platform.ct.NameFormInfo;
import org.familysearch.platform.ct.PersonInfo;
import org.familysearch.platform.ct.SearchInfo;
import org.familysearch.platform.discussions.Discussion;
import org.familysearch.platform.ordinances.Ordinance;
import org.familysearch.platform.ordinances.OrdinanceParticipant;
import org.familysearch.platform.ordinances.OrdinanceRollup;
import org.familysearch.platform.places.FeedbackInfo;
import org.familysearch.platform.places.PlaceDescriptionInfo;
import org.familysearch.platform.records.AlternateDate;
import org.familysearch.platform.records.AlternatePlaceReference;
import org.familysearch.platform.rt.FamilySearchPlatformModelVisitor;
import org.familysearch.platform.users.User;
import org.familysearch.platform.vocab.VocabConcept;
import org.gedcomx.Gedcomx;
import org.gedcomx.common.ResourceReference;
import org.gedcomx.conclusion.Identifier;
import org.gedcomx.conclusion.Person;
import org.gedcomx.conclusion.Relationship;
import org.gedcomx.rt.DefaultNamespace;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.rt.GedcomxModelVisitor;
import org.gedcomx.rt.MediaTypeDefinition;
import org.gedcomx.rt.Model;
import org.gedcomx.rt.json.JsonElementWrapper;
import org.gedcomx.source.SourceDescription;
import org.gedcomx.types.IdentifierType;
import org.gedcomx.types.RelationshipType;

/**
 * <p>The FamilySearch data types define serialization formats that are specific to the FamilySearch developer platform. These
 * data formats are extensions of the <a href="http://gedcomx.org">GEDCOM X</a> media types and provide concepts and data types
 * that are specific to FamilySearch and therefore haven't been adopted into a formal, more general specification.</p>
 *
 * @author Ryan Heaton
 */
@MediaTypeDefinition (
  name = "FamilySearch",
  description = "The FamilySearch data formats define serialization formats that are specific the FamilySearch developer API.",
  version = "1.0",
  xmlMediaType = FamilySearchPlatform.XML_MEDIA_TYPE,
  jsonMediaType = FamilySearchPlatform.JSON_MEDIA_TYPE,
  models = {
    @Model (
      id = "fs",
      namespace = FamilySearchPlatform.NAMESPACE,
      label = "FamilySearch Model",
      description = "The FamilySearch model defines data types and elements that are specific to FamilySearch."
    )
  }
)
@XmlRootElement ( name = "familysearch" )
@JsonElementWrapper ( name = "familysearch" )
@XmlType ( name = "FamilySearch", propOrder = {"childAndParentsRelationships", "discussions", "users", "merges",
    "mergeAnalyses", "features", "vocabConcepts" } )
@DefaultNamespace ( GedcomxConstants.GEDCOMX_NAMESPACE )
@XmlSeeAlso ( {DiscussionReference.class, Tag.class, ChangeInfo.class, MatchInfo.class, FeedbackInfo.class, PersonInfo.class, SearchInfo.class,
               PlaceDescriptionInfo.class, org.familysearch.platform.Error.class, ArtifactMetadata.class,
               Ordinance.class, OrdinanceRollup.class, NameFormInfo.class, AlternatePlaceReference.class, AlternateDate.class} )
@JsonInclude ( JsonInclude.Include.NON_NULL )
public class FamilySearchPlatform extends Gedcomx {

  public static final String PROJECT_ID = "fs-platform";
  public static final String NAMESPACE = "http://familysearch.org/v1/";
  public static final String XML_MEDIA_TYPE = "application/x-fs-v1+xml";
  public static final String JSON_MEDIA_TYPE = "application/x-fs-v1+json";

  private List<MergeAnalysis> mergeAnalyses;
  private List<Merge> merges;
  private List<ChildAndParentsRelationship> childAndParentsRelationships;
  private List<Discussion> discussions;
  private List<User> users;
  private List<Feature> features;
  private List<VocabConcept> vocabConcepts;

  /**
   * The merge analysis results for this data set.
   *
   * @return The merge analysis results for this data set.
   */
  @XmlElement ( name = "mergeAnalysis" )
  @JsonProperty ( "mergeAnalyses" )
  public List<MergeAnalysis> getMergeAnalyses() {
    return mergeAnalyses;
  }

  /**
   * The merge analysis results for this data set.
   *
   * @param mergeAnalyses The merge analysis results for this data set.
   */
  @JsonProperty ( "mergeAnalyses" )
  public void setMergeAnalyses(List<MergeAnalysis> mergeAnalyses) {
    this.mergeAnalyses = mergeAnalyses;
  }

  /**
   * Add a MergeAnalysis to the data set.
   *
   * @param mergeAnalysis The MergeAnalysis to be added.
   */
  public void addMergeAnalysis(MergeAnalysis mergeAnalysis) {
    if (mergeAnalysis != null) {
      if (mergeAnalyses == null) {
        mergeAnalyses = new LinkedList<MergeAnalysis>();
      }
      mergeAnalyses.add(mergeAnalysis);
    }
  }

  /**
   * The merges for this data set.
   *
   * @return The merges for this data set.
   */
  @XmlElement ( name = "merge" )
  @JsonProperty ( "merges" )
  public List<Merge> getMerges() {
    return merges;
  }

  /**
   * The merges for this data set.
   *
   * @param merges The merges for this data set.
   */
  @JsonProperty ( "merges" )
  public void setMerges(List<Merge> merges) {
    this.merges = merges;
  }

  /**
   * Add a merge to the data set.
   *
   * @param merge The merge to be added.
   */
  public void addMerge(Merge merge) {
    if (merge != null) {
      if (merges == null) {
        merges = new LinkedList<Merge>();
      }
      merges.add(merge);
    }
  }

  /**
   * The child-and-parents relationships for this data set.
   *
   * @return The child-and-parents relationships for this data set.
   */
  @XmlElement ( name = "childAndParentsRelationship" )
  @JsonProperty ( "childAndParentsRelationships" )
  public List<ChildAndParentsRelationship> getChildAndParentsRelationships() {
    return childAndParentsRelationships;
  }

  /**
   * The child-and-parents relationships for this data set.
   *
   * @param childAndParentsRelationships The child-and-parents relationships for this data set.
   */
  @JsonProperty ( "childAndParentsRelationships" )
  public void setChildAndParentsRelationships(List<ChildAndParentsRelationship> childAndParentsRelationships) {
    this.childAndParentsRelationships = childAndParentsRelationships;
  }

  /**
   * Add a childAndParentsRelationship to the data set.
   *
   * @param childAndParentsRelationship The childAndParentsRelationship to be added.
   */
  public void addChildAndParentsRelationship(ChildAndParentsRelationship childAndParentsRelationship) {
    if (childAndParentsRelationship != null) {
      if (childAndParentsRelationships == null) {
        childAndParentsRelationships = new LinkedList<ChildAndParentsRelationship>();
      }
      childAndParentsRelationships.add(childAndParentsRelationship);
    }
  }

  public ChildAndParentsRelationship findChildAndParentsRelationship(ResourceReference child, ResourceReference parent1, ResourceReference parent2) {
    if (child != null && getRelationships() != null && (parent1 != null || parent2 != null)) {
      for (ChildAndParentsRelationship relationship : getChildAndParentsRelationships()) {
        if (samePerson(relationship.getChild(), child) &&
            samePerson(relationship.getParent1(), parent1) &&
            samePerson(relationship.getParent2(), parent2)) {
          return relationship;
        }
      }
    }
    return null;
  }

  /**
   * Build out this document with a child-and-parents relationship.
   *
   * @param chap The child-and-parents relationship
   * @return this.
   */
  public FamilySearchPlatform childAndParentsRelationship(ChildAndParentsRelationship chap) {
    addChildAndParentsRelationship(chap);
    return this;
  }

  /**
   * The discussions included in this data set.
   *
   * @return The discussions included in this data set.
   */
  @XmlElement ( name = "discussion" )
  @JsonProperty ( "discussions" )
  public List<Discussion> getDiscussions() {
    return discussions;
  }

  /**
   * The discussions included in this data set.
   *
   * @param discussions The discussions included in this data set.
   */
  @JsonProperty ( "discussions" )
  public void setDiscussions(List<Discussion> discussions) {
    this.discussions = discussions;
  }

  /**
   * Add a discussion to the data set.
   *
   * @param discussion The discussion to be added.
   */
  public void addDiscussion(Discussion discussion) {
    if (discussion != null) {
      if (discussions == null) {
        discussions = new LinkedList<Discussion>();
      }
      discussions.add(discussion);
    }
  }

  /**
   * Build out this document with a discussion.
   *
   * @param discussion The discussion to be added.
   * @return this.
   */
  public FamilySearchPlatform discussion(Discussion discussion) {
    addDiscussion(discussion);
    return this;
  }

  /**
   * The vocabulary concepts included in this data set.
   *
   * @return The vocabulary concepts included in this data set.
   */
  @XmlElement ( name = "vocabConcepts" )
  @JsonProperty ( "vocabConcepts" )
  public List<VocabConcept> getVocabConcepts() {
    return vocabConcepts;
  }

  /**
   * The vocabulary concepts included in this data set.
   *
   * @param vocabConcepts The vocabulary concepts included in this data set.
   */
  @JsonProperty ( "vocabConcepts" )
  public void setVocabConcepts(List<VocabConcept> vocabConcepts) {
    this.vocabConcepts = vocabConcepts;
  }

  /**
   * Add a vocabulary concept to the data set.
   *
   * @param vocabConcept The vocabulary concept to be added.
   */
  public void addVocabConcept(VocabConcept vocabConcept) {
    if (vocabConcept != null) {
      if (vocabConcepts == null) {
        vocabConcepts = new LinkedList<>();
      }
      vocabConcepts.add(vocabConcept);
    }
  }

  /**
   * Build out this document with a vocabulary concept.
   *
   * @param vocabConcept The vocabulary concept to be added.
   * @return this.
   */
  public FamilySearchPlatform vocabConcept(VocabConcept vocabConcept) {
    addVocabConcept(vocabConcept);
    return this;
  }

  /**
   * The users included in this data set.
   *
   * @return The users included in this genealogical data set.
   */
  @XmlElement ( name = "user" )
  @JsonProperty ( "users" )
  public List<User> getUsers() {
    return users;
  }

  /**
   * The users included in this data set.
   *
   * @param users The users included in this data set.
   */
  @JsonProperty ( "users" )
  public void setUsers(List<User> users) {
    this.users = users;
  }

  /**
   * Build out this document with a user.
   * @param user The user to be added.
   * @return this.
   */
  public FamilySearchPlatform user(User user) {
    addUser(user);
    return this;
  }

  /**
   * The set of features defined in the platform API.
   *
   * @return The set of features defined in the platform API.
   */
  @XmlElement ( name = "feature" )
  @JsonProperty ( "features" )
  public List<Feature> getFeatures() {
    return features;
  }

  /**
   * The set of features defined in the platform API.
   *
   * @param features The set of features defined in the platform API.
   */
  @JsonProperty ( "features" )
  public void setFeatures(List<Feature> features) {
    this.features = features;
  }

  /**
   * Add a user to the data set.
   *
   * @param user The user to be added.
   */
  public void addUser(User user) {
    if (user != null) {
      if (users == null) {
        users = new LinkedList<User>();
      }
      users.add(user);
    }
  }

  /**
   * Accept a visitor.
   *
   * @param visitor The visitor.
   */
  public void accept(FamilySearchPlatformModelVisitor visitor) {
    visitor.visitFamilySearchPlatform(this);
  }

  @Override
  public void accept(GedcomxModelVisitor visitor) {
    if (visitor instanceof FamilySearchPlatformModelVisitor) {
      ((FamilySearchPlatformModelVisitor) visitor).visitFamilySearchPlatform(this);
    }
    else {
      super.accept(visitor);
    }
  }

  @Override
  public void embed(Gedcomx gedcomx) {
    super.embed(gedcomx);

    if (gedcomx instanceof FamilySearchPlatform) {
      List<ChildAndParentsRelationship> relationships = ((FamilySearchPlatform) gedcomx).getChildAndParentsRelationships();
      if (relationships != null) {
        for (ChildAndParentsRelationship relationship : relationships) {
          boolean found = false;
          if (relationship.getId() != null) {
            if (getChildAndParentsRelationships() != null) {
              for (ChildAndParentsRelationship target : getChildAndParentsRelationships()) {
                if (relationship.getId().equals(target.getId())) {
                  target.embed(relationship);
                  found = true;
                  break;
                }
              }
            }
          }

          if (!found) {
            addChildAndParentsRelationship(relationship);
          }
        }
      }

      List<Discussion> discussions = ((FamilySearchPlatform) gedcomx).getDiscussions();
      if (discussions != null) {
        for (Discussion discussion : discussions) {
          boolean found = false;
          if (discussion.getId() != null) {
            if (getDiscussions() != null) {
              for (Discussion target : getDiscussions()) {
                if (discussion.getId().equals(target.getId())) {
                  target.embed(discussion);
                  found = true;
                  break;
                }
              }
            }
          }

          if (!found) {
            addDiscussion(discussion);
          }
        }
      }
    }

    List<VocabConcept> localVocabConcepts = ((FamilySearchPlatform) gedcomx).getVocabConcepts();
    if (localVocabConcepts != null) {
      for (VocabConcept vocabConcept : localVocabConcepts) {
        boolean found = false;
        if (vocabConcept.getId() != null) {
          if (getVocabConcepts() != null) {
            for (VocabConcept target : getVocabConcepts()) {
              if (vocabConcept.getId().equals(target.getId())) {
                target.embed(vocabConcept);
                found = true;
                break;
              }
            }
          }
        }

        if (!found) {
          addVocabConcept(vocabConcept);
        }
      }
    }
  }

  public FamilySearchPlatform addParentChildRelationshipForEachChildAndParentsRelationship() {
    //for each child-and-parents relationship, add a parent-child relationship.
    List<ChildAndParentsRelationship> parentRelationships = getChildAndParentsRelationships();
    if (parentRelationships != null) {
      for (ChildAndParentsRelationship childAndParentsRelationship : parentRelationships) {
        String relationshipId = childAndParentsRelationship.getId();
        if (relationshipId == null) {
          continue;
        }

        String childId = childAndParentsRelationship.getChild() != null ? childAndParentsRelationship.getChild().getResourceId() : null;
        if (childId == null) {
          continue;
        }

        ResourceReference capParent1 = childAndParentsRelationship.getParent1();
        ResourceReference capParent2 = childAndParentsRelationship.getParent2();
        String parent1Id = capParent1 != null ? capParent1.getResourceId() : null;
        String parent2Id = capParent2 != null ? capParent2.getResourceId() : null;

        Identifier primaryIdentifier = null;
        if (childAndParentsRelationship.getIdentifiers() != null) {
          for (Identifier identifier : childAndParentsRelationship.getIdentifiers()) {
            if (identifier.getKnownType() == IdentifierType.Primary) {
              primaryIdentifier = identifier;
              break;
            }
          }
        }

        if (parent1Id != null) {
          Relationship parent1ChildRelationship = new Relationship();
          parent1ChildRelationship.setId("P1" + relationshipId);
          parent1ChildRelationship.setKnownType(RelationshipType.ParentChild);
          parent1ChildRelationship.setPerson1(capParent1);
          parent1ChildRelationship.setPerson2(childAndParentsRelationship.getChild());
          if (primaryIdentifier != null) {
            parent1ChildRelationship.setIdentifiers(new ArrayList<>(1));
            parent1ChildRelationship.getIdentifiers().add(new Identifier());
            parent1ChildRelationship.getIdentifiers().get(0).setType(FamilySearchIdentifierType.ChildAndParentsRelationship.toQNameURI(), true);
            parent1ChildRelationship.getIdentifiers().get(0).setValue(primaryIdentifier.getValue());
          }
          for (Map.Entry<String, Object> transientProperty : childAndParentsRelationship.getTransientProperties().entrySet()) {
            parent1ChildRelationship.setTransientProperty(transientProperty.getKey(), transientProperty.getValue());
          }
          parent1ChildRelationship.setSortKey(childAndParentsRelationship.getSortKey());
          addRelationship(parent1ChildRelationship);
        }

        if (parent2Id != null) {
          Relationship parent2ChildRelationship = new Relationship();
          parent2ChildRelationship.setId("P2" + relationshipId);
          parent2ChildRelationship.setKnownType(RelationshipType.ParentChild);
          parent2ChildRelationship.setPerson1(capParent2);
          parent2ChildRelationship.setPerson2(childAndParentsRelationship.getChild());
          if (primaryIdentifier != null) {
            parent2ChildRelationship.setIdentifiers(new ArrayList<>(1));
            parent2ChildRelationship.getIdentifiers().add(new Identifier());
            parent2ChildRelationship.getIdentifiers().get(0).setType(FamilySearchIdentifierType.ChildAndParentsRelationship.toQNameURI(), true);
            parent2ChildRelationship.getIdentifiers().get(0).setValue(primaryIdentifier.getValue());
          }
          for (Map.Entry<String, Object> transientProperty : childAndParentsRelationship.getTransientProperties().entrySet()) {
            parent2ChildRelationship.setTransientProperty(transientProperty.getKey(), transientProperty.getValue());
          }
          parent2ChildRelationship.setSortKey(childAndParentsRelationship.getSortKey());
          addRelationship(parent2ChildRelationship);
        }
      }
    }
    return this;
  }

  @Override
  public FamilySearchPlatform fixLocalReferences() {
    List<Person> locals = getPersons() == null ? Collections.emptyList() : getPersons();
    List<ChildAndParentsRelationship> childAndParentsRelationships = getChildAndParentsRelationships() != null ? getChildAndParentsRelationships() : Collections.emptyList();
    List<Ordinance> ordinances = getOrdinances(this);
    List<SourceDescription> sds = getSourceDescriptions() == null ? Collections.emptyList() : getSourceDescriptions();

    for (Person local : locals) {
      String localId = local.getId();
      if (localId != null) {
        for (ChildAndParentsRelationship capRelationship : childAndParentsRelationships) {
          fixId(capRelationship.getParent1(), localId);
          fixId(capRelationship.getParent2(), localId);
          fixId(capRelationship.getChild(), localId);
          fixupSourceReferences(sds, capRelationship);
        }
        fixupPersonReferencesInOrdinances(ordinances, localId);
      }
    }

    return (FamilySearchPlatform) super.fixLocalReferences();
  }

  protected static List<Ordinance> getOrdinances(Gedcomx gx) {
    List<Ordinance> rtn = new ArrayList<>();
    if (gx.getPersons() != null) {
      for (Person person : gx.getPersons()) {
        if (person.getExtensionElements() != null) {
          for (Object extension : person.getExtensionElements()) {
            if (extension instanceof List) {
              for (Object item : (List) extension) {
                if (item instanceof Ordinance) {
                  rtn.add((Ordinance) item);
                }
              }
            }
          }
        }
      }
    }
    return rtn;
  }

  protected static void fixupPersonReferencesInOrdinances(List<Ordinance> ordinances, String personId) {
    for (Ordinance ordinance : ordinances) {
      if (ordinance.getParticipants() != null) {
        for (OrdinanceParticipant participant: ordinance.getParticipants()) {
          fixId(participant.getParticipant(), personId);
        }
      }

    }
  }
}
