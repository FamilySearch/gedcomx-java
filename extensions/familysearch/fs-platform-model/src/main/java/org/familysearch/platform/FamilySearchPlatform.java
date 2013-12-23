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
package org.familysearch.platform;

import org.codehaus.enunciate.json.JsonName;
import org.codehaus.jackson.annotate.JsonProperty;
import org.familysearch.platform.ct.ChildAndParentsRelationship;
import org.familysearch.platform.ct.Merge;
import org.familysearch.platform.ct.MergeAnalysis;
import org.familysearch.platform.discussions.Discussion;
import org.familysearch.platform.rt.FamilySearchPlatformModelVisitor;
import org.familysearch.platform.users.User;
import org.gedcomx.Gedcomx;
import org.gedcomx.rt.DefaultNamespace;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.rt.MediaTypeDefinition;
import org.gedcomx.rt.Model;
import org.gedcomx.rt.json.JsonElementWrapper;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>The FamilySearch data types define serialization formats that are specific the FamilySearch developer platform. These
 * data formats are extensions of the <a href="http://gedcomx.org">GEDCOM X</a> media types and provide concepts and data types
 * that are specific to FamilySearch and therefore haven't been adopted into a formal, more general specification.</p>
 *
 * @author Ryan Heaton
 */
@MediaTypeDefinition (
  id = "fs",
  name = "FamilySearch",
  description = "The FamilySearch data formats define serialization formats that are specific the FamilySearch developer API.",
  version = "1.0",
  xmlMediaType = FamilySearchConstants.FS_PLATFORM_V1_XML_MEDIA_TYPE,
  jsonMediaType = FamilySearchConstants.FS_PLATFORM_V1_JSON_MEDIA_TYPE,
  projectId = FamilySearchConstants.FS_PROJECT_ID,
  models = {
    @Model (
      id = "fs",
      namespace = FamilySearchConstants.FS_PLATFORM_V1_NAMESPACE,
      label = "FamilySearch Model",
      description = "The FamilySearch model defines data types and elements that are specific to FamilySearch."
    )
  }
)
@XmlRootElement ( name = "familysearch" )
@JsonElementWrapper (name = "familysearch")
@XmlType ( name = "FamilySearch" , propOrder = {"childAndParentsRelationships", "discussions", "users", "merges", "mergeAnalyses", "features" } )
@DefaultNamespace( GedcomxConstants.GEDCOMX_NAMESPACE )
public class FamilySearchPlatform extends Gedcomx {

  public static final String XML_MEDIA_TYPE = FamilySearchConstants.FS_PLATFORM_V1_XML_MEDIA_TYPE;
  public static final String JSON_MEDIA_TYPE = FamilySearchConstants.FS_PLATFORM_V1_JSON_MEDIA_TYPE;

  private List<MergeAnalysis> mergeAnalyses;
  private List<Merge> merges;
  private List<ChildAndParentsRelationship> childAndParentsRelationships;
  private List<Discussion> discussions;
  private List<User> users;
  private List<Feature> features;

  /**
   * The merge analysis results for this data set.
   *
   * @return The merge analysis results for this data set.
   */
  @XmlElement ( name="mergeAnalysis" )
  @JsonProperty ("mergeAnalyses")
  @JsonName ("mergeAnalyses")
  public List<MergeAnalysis> getMergeAnalyses() {
    return mergeAnalyses;
  }

  /**
   * The merge analysis results for this data set.
   *
   * @param mergeAnalyses The merge analysis results for this data set.
   */
  @JsonProperty ("mergeAnalyses")
  public void setMergeAnalyses( List<MergeAnalysis> mergeAnalyses ) {
    this.mergeAnalyses = mergeAnalyses;
  }

  /**
   * Add a MergeAnalysis to the data set.
   *
   * @param mergeAnalysis The MergeAnalysis to be added.
   */
  public void addMergeAnalysis( MergeAnalysis mergeAnalysis ) {
    if (mergeAnalysis != null) {
      if (mergeAnalyses == null)
        mergeAnalyses = new LinkedList<MergeAnalysis>();
      mergeAnalyses.add( mergeAnalysis );
    }
  }

  /**
   * The merges for this data set.
   *
   * @return The merges for this data set.
   */
  @XmlElement ( name="merge" )
  @JsonProperty ("merges")
  @JsonName ("merges")
  public List<Merge> getMerges() {
    return merges;
  }

  /**
   * The merges for this data set.
   *
   * @param merges The merges for this data set.
   */
  @JsonProperty ("merges")
  public void setMerges(List<Merge> merges) {
    this.merges = merges;
  }

  /**
   * Add a merge to the data set.
   *
   * @param merge The merge to be added.
   */
  public void addMerge( Merge merge ) {
    if (merge != null) {
      if (merges == null)
        merges = new LinkedList<Merge>();
      merges.add( merge );
    }
  }

  /**
   * The child-and-parents relationships for this data set.
   *
   * @return The child-and-parents relationships for this data set.
   */
  @XmlElement ( name="childAndParentsRelationship" )
  @JsonProperty ("childAndParentsRelationships")
  @JsonName ("childAndParentsRelationships")
  public List<ChildAndParentsRelationship> getChildAndParentsRelationships() {
    return childAndParentsRelationships;
  }

  /**
   * The child-and-parents relationships for this data set.
   *
   * @param childAndParentsRelationships The child-and-parents relationships for this data set.
   */
  @JsonProperty ("childAndParentsRelationships")
  public void setChildAndParentsRelationships(List<ChildAndParentsRelationship> childAndParentsRelationships) {
    this.childAndParentsRelationships = childAndParentsRelationships;
  }

  /**
   * Add a childAndParentsRelationship to the data set.
   *
   * @param childAndParentsRelationship The childAndParentsRelationship to be added.
   */
  public void addChildandparentsrelationship( ChildAndParentsRelationship childAndParentsRelationship ) {
    if (childAndParentsRelationship != null) {
      if (childAndParentsRelationships == null)
        childAndParentsRelationships = new LinkedList<ChildAndParentsRelationship>();
      childAndParentsRelationships.add( childAndParentsRelationship );
    }
  }

  /**
   * The discussions included in this data set.
   *
   * @return The discussions included in this data set.
   */
  @XmlElement ( name="discussion" )
  @JsonProperty ("discussions")
  @JsonName ("discussions")
  public List<Discussion> getDiscussions() {
    return discussions;
  }

  /**
   * The discussions included in this data set.
   *
   * @param discussions The discussions included in this data set.
   */
  @JsonProperty ("discussions")
  public void setDiscussions(List<Discussion> discussions) {
    this.discussions = discussions;
  }

  /**
   * Add a discussion to the data set.
   *
   * @param discussion The discussion to be added.
   */
  public void addDiscussion( Discussion discussion ) {
    if (discussion != null) {
      if (discussions == null)
        discussions = new LinkedList<Discussion>();
      discussions.add( discussion );
    }
  }

  /**
   * The users included in this data set.
   *
   * @return The users included in this genealogical data set.
   */
  @XmlElement (name="user")
  @JsonProperty ("users")
  @JsonName ("users")
  public List<User> getUsers() {
    return users;
  }

  /**
   * The users included in this data set.
   *
   * @param users The users included in this data set.
   */
  @JsonProperty ("users")
  public void setUsers(List<User> users) {
    this.users = users;
  }

  /**
   * The set of features defined in the platform API.
   *
   * @return The set of features defined in the platform API.
   */
  @XmlElement ( name="feature" )
  @JsonProperty ("features")
  @JsonName ("features")
  public List<Feature> getFeatures() {
    return features;
  }

  /**
   * The set of features defined in the platform API.
   *
   * @param features The set of features defined in the platform API.
   */
  @JsonProperty ("features")
  public void setFeatures(List<Feature> features) {
    this.features = features;
  }

  /**
   * Add a user to the data set.
   *
   * @param user The user to be added.
   */
  public void addUser( User user ) {
    if (user != null) {
      if (users == null)
        users = new LinkedList<User>();
      users.add( user );
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
}
