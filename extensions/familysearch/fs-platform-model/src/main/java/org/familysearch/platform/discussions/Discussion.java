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
package org.familysearch.platform.discussions;

import org.codehaus.enunciate.json.JsonName;
import org.codehaus.jackson.annotate.JsonProperty;
import org.familysearch.platform.rt.FamilySearchPlatformModelVisitor;
import org.gedcomx.common.ResourceReference;
import org.gedcomx.links.HypermediaEnabledData;
import org.gedcomx.rt.json.JsonElementWrapper;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A discussion.
 *
 */
@XmlRootElement
@JsonElementWrapper (name = "discussions")
@XmlType ( name = "Discussion", propOrder = { "title", "details", "created", "contributor", "modified", "numberOfComments", "comments" } )
public class Discussion extends HypermediaEnabledData {

  private String title;
  private String details;
  private ResourceReference contributor;
  private Date created;
  private Date modified;    // last date of any change to comments or discussion details
  private Integer numberOfComments;
  private List<Comment> comments;

  //TODO: need to add a URI to point to the specific resource (eg person) that the discussion is about

  /**
   * get the one-line summary or "subject" of the discussion
   * @return the one-line summary text
   */
  public String getTitle() {
    return title;
  }

  /**
   * Set the one-line summary or "subject" of the discussion
   * @param title the one-line summary text
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * Get detailed text of the discussion
   * @return  The text or "message body" of the discussion
   */
  public String getDetails() {
    return details;
  }

  /**
   * Set discussion details, or "message body" of discussion
   * @param details Details
   */
  public void setDetails(String details) {
    this.details = details;
  }

  /**
   * Get the contributor who submitted this discussion
   * @return contributor of discussion
   */
  public ResourceReference getContributor() {
    return contributor;
  }

  /**
   * Set the contributor of this discussion
   * @param contributor reference to who submitted the discussion
   */
  public void setContributor(ResourceReference contributor) {
    this.contributor = contributor;
  }

  /**
   * Get the date of discussion creation
   * @return date of creation
   */
  public Date getCreated() {
    return created;
  }

  /**
   * Set the date the discussion was created
   * @param created date of creation
   */
  public void setCreated(Date created) {
    this.created = created;
  }

  /** Last date of any change to comments or discussion details
   * @return Date of last modification
   */
  public Date getModified() {
    return modified;
  }

  /**
   * Set the modified date of this discussion. Should be the last date of any change to comments or discussion details
   * @param modified date of last modification
   */
  public void setModified(Date modified) {
    this.modified = modified;
  }

  /**
   * Get the number of comments associated with this discussion
   * @return Number of comments
   */
  public Integer getNumberOfComments() {
    return numberOfComments;
  }

  /**
   * Set the number of comments associated with this discussion
   * @param numberOfComments number of comment objects with this discussion
   */
  public void setNumberOfComments(Integer numberOfComments) {
    this.numberOfComments = numberOfComments;
  }

  /**
   * The comments on this discussion.
   *
   * @return The comments on this discussion.
   */
  @XmlElement ( name="comment" )
  @JsonProperty ( "comments" )
  @JsonName ( "comments" )
  public List<Comment> getComments() {
    return comments;
  }

  /**
   * The comments on this discussion.
   *
   * @param comments The comments on this discussion.
   */
  @JsonProperty ( "comments" )
  public void setComments(List<Comment> comments) {
    this.comments = comments;
  }

  public void addComment(Comment comment) {
    if (comments == null) {
      comments = new ArrayList<Comment>();
    }
    comments.add( comment );
  }

  /**
   * Accept a visitor.
   *
   * @param visitor The visitor to accept.
   */
  public void accept(FamilySearchPlatformModelVisitor visitor) {
    visitor.visitDiscussion(this);
  }
}
