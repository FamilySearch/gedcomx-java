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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.familysearch.platform.artifacts.ArtifactMetadata;
import org.familysearch.platform.ct.*;
import org.familysearch.platform.discussions.Discussion;
import org.familysearch.platform.messages.MessageThread;
import org.familysearch.platform.messages.UserMessageThreadsSummary;
import org.familysearch.platform.ordinances.Ordinance;
import org.familysearch.platform.places.FeedbackInfo;
import org.familysearch.platform.places.PlaceDescriptionInfo;
import org.familysearch.platform.reservations.Reservation;
import org.familysearch.platform.rt.FamilySearchPlatformModelVisitor;
import org.familysearch.platform.users.User;
import org.familysearch.platform.vocab.VocabConcept;

import org.gedcomx.Gedcomx;
import org.gedcomx.common.ResourceReference;
import org.gedcomx.conclusion.Identifier;
import org.gedcomx.conclusion.Person;
import org.gedcomx.conclusion.Relationship;
import org.gedcomx.rt.*;
import org.gedcomx.rt.json.JsonElementWrapper;
import org.gedcomx.source.SourceDescription;
import org.gedcomx.types.IdentifierType;
import org.gedcomx.types.RelationshipType;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import java.util.*;

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
    "mergeAnalyses", "features", "messageThreads", "userMessageThreadsSummaries", "vocabConcepts" } )
@DefaultNamespace ( GedcomxConstants.GEDCOMX_NAMESPACE )
@XmlSeeAlso ( {DiscussionReference.class, Tag.class, ChangeInfo.class, MatchInfo.class, FeedbackInfo.class, PersonInfo.class, SearchInfo.class, PlaceDescriptionInfo.class, org.familysearch.platform.Error.class, ArtifactMetadata.class, Reservation.class, Ordinance.class, NameFormInfo.class} )
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
  private List<MessageThread> messageThreads;
  private List<UserMessageThreadsSummary> userMessageThreadsSummaries;
  private List<VocabConcept> vocabConcepts;

  /**
   * The merge analysis results for this data set.
   *
   * @return The merge analysis results for this data set.
   */
  @XmlElement ( name = "mergeAnalysis" )
  @JsonProperty ( "mergeAnalyses" ) @org.codehaus.jackson.annotate.JsonProperty ( "mergeAnalyses" )
  public List<MergeAnalysis> getMergeAnalyses() {
    return mergeAnalyses;
  }

  /**
   * The merge analysis results for this data set.
   *
   * @param mergeAnalyses The merge analysis results for this data set.
   */
  @JsonProperty ( "mergeAnalyses" ) @org.codehaus.jackson.annotate.JsonProperty ( "mergeAnalyses" )
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
  @JsonProperty ( "merges" ) @org.codehaus.jackson.annotate.JsonProperty ( "merges" )
  public List<Merge> getMerges() {
    return merges;
  }

  /**
   * The merges for this data set.
   *
   * @param merges The merges for this data set.
   */
  @JsonProperty ( "merges" ) @org.codehaus.jackson.annotate.JsonProperty ( "merges" )
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
  @JsonProperty ( "childAndParentsRelationships" ) @org.codehaus.jackson.annotate.JsonProperty ( "childAndParentsRelationships" )
  public List<ChildAndParentsRelationship> getChildAndParentsRelationships() {
    return childAndParentsRelationships;
  }

  /**
   * The child-and-parents relationships for this data set.
   *
   * @param childAndParentsRelationships The child-and-parents relationships for this data set.
   */
  @JsonProperty ( "childAndParentsRelationships" ) @org.codehaus.jackson.annotate.JsonProperty ( "childAndParentsRelationships" )
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
          samePerson(relationship.getFather(), parent1) &&
          samePerson(relationship.getMother(), parent2)) {
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
  @JsonProperty ( "discussions" ) @org.codehaus.jackson.annotate.JsonProperty ( "discussions" )
  public List<Discussion> getDiscussions() {
    return discussions;
  }

  /**
   * The discussions included in this data set.
   *
   * @param discussions The discussions included in this data set.
   */
  @JsonProperty ( "discussions" ) @org.codehaus.jackson.annotate.JsonProperty ( "discussions" )
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
   * The message threads included in this data set.
   *
   * @return The message threads included in this data set.
   */
  @XmlElement ( name = "messageThreads" )
  @JsonProperty ( "messageThreads" ) @org.codehaus.jackson.annotate.JsonProperty ( "messageThreads" )
  public List<MessageThread> getMessageThreads() {
    return messageThreads;
  }

  /**
   * The message threads included in this data set.
   *
   * @param messageThreads The message threads included in this data set.
   */
  @JsonProperty ( "messageThreads" ) @org.codehaus.jackson.annotate.JsonProperty ( "messageThreads" )
  public void setMessageThreads(List<MessageThread> messageThreads) {
    this.messageThreads = messageThreads;
  }

  /**
   * Add a message thread to the data set.
   *
   * @param messageThread The message thread to be added.
   */
  public void addMessageThread(MessageThread messageThread) {
    if (messageThread != null) {
      if (messageThreads == null) {
        messageThreads = new LinkedList<MessageThread>();
      }
      messageThreads.add(messageThread);
    }
  }

  /**
   * Build out this document with a message thread.
   *
   * @param messageThread The message thread to be added.
   * @return this.
   */
  public FamilySearchPlatform messageThread(MessageThread messageThread) {
    addMessageThread(messageThread);
    return this;
  }

  /**
   * The user message thread userMessageThreadSummary included in this data set.
   *
   * @return The user message thread userMessageThreadSummary included in this data set.
   */
  @XmlElement ( name = "userMessageThreadSummaries" )
  @JsonProperty ( "userMessageThreadSummaries" ) @org.codehaus.jackson.annotate.JsonProperty ( "userMessageThreadSummaries" )
  public List<UserMessageThreadsSummary> getUserMessageThreadsSummaries() {
    return userMessageThreadsSummaries;
  }

  /**
   * The user message thread userMessageThreadSummary included in this data set.
   *
   * @param userMessageThreadsSummaries The user message thread userMessageThreadSummary included in this data set.
   */
  @JsonProperty ( "userMessageThreadSummaries" ) @org.codehaus.jackson.annotate.JsonProperty ( "userMessageThreadSummaries" )
  public void setUserMessageThreadsSummaries(List<UserMessageThreadsSummary> userMessageThreadsSummaries) {
    this.userMessageThreadsSummaries = userMessageThreadsSummaries;
  }

  /**
   * Add a user message thread summary to the data set.
   *
   * @param userMessageThreadsSummary The user message thread summary to be added.
   */
  public void addUserMessageThreadsSummary(UserMessageThreadsSummary userMessageThreadsSummary) {
    if (userMessageThreadsSummary != null) {
      if (userMessageThreadsSummaries == null) {
        userMessageThreadsSummaries = new LinkedList<UserMessageThreadsSummary>();
      }
      userMessageThreadsSummaries.add(userMessageThreadsSummary);
    }
  }

  /**
   * Build out this document with a user message thread summary.
   *
   * @param userMessageThreadsSummary The user message thread summary to be added.
   * @return this.
   */
  public FamilySearchPlatform userMessageThreadsSummary(UserMessageThreadsSummary userMessageThreadsSummary) {
    addUserMessageThreadsSummary(userMessageThreadsSummary);
    return this;
  }

  /**
   * The vocabulary concepts included in this data set.
   *
   * @return The vocabulary concepts included in this data set.
   */
  @XmlElement ( name = "vocabConcepts" )
  @JsonProperty ( "vocabConcepts" ) @org.codehaus.jackson.annotate.JsonProperty ( "vocabConcepts" )
  public List<VocabConcept> getVocabConcepts() {
    return vocabConcepts;
  }

  /**
   * The vocabulary concepts included in this data set.
   *
   * @param vocabConcepts The vocabulary concepts included in this data set.
   */
  @JsonProperty ( "vocabConcepts" ) @org.codehaus.jackson.annotate.JsonProperty ( "vocabConcepts" )
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
  @JsonProperty ( "users" ) @org.codehaus.jackson.annotate.JsonProperty ( "users" )
  public List<User> getUsers() {
    return users;
  }

  /**
   * The users included in this data set.
   *
   * @param users The users included in this data set.
   */
  @JsonProperty ( "users" ) @org.codehaus.jackson.annotate.JsonProperty ( "users" )
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
  @JsonProperty ( "features" ) @org.codehaus.jackson.annotate.JsonProperty ( "features" )
  public List<Feature> getFeatures() {
    return features;
  }

  /**
   * The set of features defined in the platform API.
   *
   * @param features The set of features defined in the platform API.
   */
  @JsonProperty ( "features" ) @org.codehaus.jackson.annotate.JsonProperty ( "features" )
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

      List<MessageThread> messageThreads = ((FamilySearchPlatform) gedcomx).getMessageThreads();
      if (messageThreads != null) {
        for (MessageThread messageThread : messageThreads) {
          boolean found = false;
          if (messageThread.getId() != null) {
            if (getMessageThreads() != null) {
              for (MessageThread target : getMessageThreads()) {
                if (messageThread.getId().equals(target.getId())) {
                  target.embed(messageThread);
                  found = true;
                  break;
                }
              }
            }
          }

          if (!found) {
            addMessageThread(messageThread);
          }
        }
      }

      List<UserMessageThreadsSummary> userMessageThreadsSummaries = ((FamilySearchPlatform) gedcomx).getUserMessageThreadsSummaries();
      if (userMessageThreadsSummaries != null) {
        for (UserMessageThreadsSummary userMessageThreadsSummary : userMessageThreadsSummaries) {
          boolean found = false;
          if (userMessageThreadsSummary.getId() != null) {
            if (getUserMessageThreadsSummaries() != null) {
              for (UserMessageThreadsSummary target : getUserMessageThreadsSummaries()) {
                if (userMessageThreadsSummary.getId().equals(target.getId())) {
                  target.embed(userMessageThreadsSummary);
                  found = true;
                  break;
                }
              }
            }
          }

          if (!found) {
            addUserMessageThreadsSummary(userMessageThreadsSummary);
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

        String fatherId = childAndParentsRelationship.getFather() != null ? childAndParentsRelationship.getFather().getResourceId() : null;
        String motherId = childAndParentsRelationship.getMother() != null ? childAndParentsRelationship.getMother().getResourceId() : null;

        Identifier primaryIdentifier = null;
        if (childAndParentsRelationship.getIdentifiers() != null) {
          for (Identifier identifier : childAndParentsRelationship.getIdentifiers()) {
            if (identifier.getKnownType() == IdentifierType.Primary) {
              primaryIdentifier = identifier;
              break;
            }
          }
        }

        if (fatherId != null) {
          Relationship fatherChildRelationship = new Relationship();
          fatherChildRelationship.setId("F" + relationshipId);
          fatherChildRelationship.setKnownType(RelationshipType.ParentChild);
          fatherChildRelationship.setPerson1(childAndParentsRelationship.getFather());
          fatherChildRelationship.setPerson2(childAndParentsRelationship.getChild());
          if (primaryIdentifier != null) {
            fatherChildRelationship.setIdentifiers(new ArrayList<>(1));
            fatherChildRelationship.getIdentifiers().add(new Identifier());
            fatherChildRelationship.getIdentifiers().get(0).setType(FamilySearchIdentifierType.ChildAndParentsRelationship.toQNameURI(), true);
            fatherChildRelationship.getIdentifiers().get(0).setValue(primaryIdentifier.getValue());
          }
          for (Map.Entry<String, Object> transientProperty : childAndParentsRelationship.getTransientProperties().entrySet()) {
            fatherChildRelationship.setTransientProperty(transientProperty.getKey(), transientProperty.getValue());
          }
          fatherChildRelationship.setSortKey(childAndParentsRelationship.getSortKey());
          addRelationship(fatherChildRelationship);
        }

        if (motherId != null) {
          Relationship motherChildRelationship = new Relationship();
          motherChildRelationship.setId("M" + relationshipId);
          motherChildRelationship.setKnownType(RelationshipType.ParentChild);
          motherChildRelationship.setPerson1(childAndParentsRelationship.getMother());
          motherChildRelationship.setPerson2(childAndParentsRelationship.getChild());
          if (primaryIdentifier != null) {
            motherChildRelationship.setIdentifiers(new ArrayList<>(1));
            motherChildRelationship.getIdentifiers().add(new Identifier());
            motherChildRelationship.getIdentifiers().get(0).setType(FamilySearchIdentifierType.ChildAndParentsRelationship.toQNameURI(), true);
            motherChildRelationship.getIdentifiers().get(0).setValue(primaryIdentifier.getValue());
          }
          for (Map.Entry<String, Object> transientProperty : childAndParentsRelationship.getTransientProperties().entrySet()) {
            motherChildRelationship.setTransientProperty(transientProperty.getKey(), transientProperty.getValue());
          }
          motherChildRelationship.setSortKey(childAndParentsRelationship.getSortKey());
          addRelationship(motherChildRelationship);
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
        for (ChildAndParentsRelationship relationship : childAndParentsRelationships) {
          fixId(relationship.getFather(), localId);
          fixId(relationship.getMother(), localId);
          fixId(relationship.getChild(), localId);
          fixupSourceReferences(sds, relationship);
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
      fixId(ordinance.getSpouse(), personId);
      fixId(ordinance.getFather(), personId);
      fixId(ordinance.getMother(), personId);
    }
  }
}
