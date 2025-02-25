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

import com.fasterxml.jackson.annotation.JsonInclude;
import org.familysearch.platform.rt.FamilySearchPlatformModelVisitor;
import org.gedcomx.common.ResourceReference;
import org.gedcomx.links.HypermediaEnabledData;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlType;

import java.util.Date;

/**
 */
@XmlType ( name = "Comment", propOrder = { "text", "created", "contributor" } )
@SuppressWarnings("gedcomx:no_id")
@JsonInclude ( JsonInclude.Include.NON_NULL )
@Schema(description = "A comment.")
public class Comment extends HypermediaEnabledData {

  @Schema(description = "The text of the comment.")
  private String text;

  @Schema(description = "The contributor who submitted this comment.")
  private ResourceReference contributor;

  @Schema(description = "The date the comment was created.")
  private Date created;

  public Comment() {
  }

  public Comment(String text) {
    this.text = text;
  }

  /**
   * Get the text of the comment
   * @return  The text or "message body" of the comment
   */
  public String getText() {
    return text;
  }

  /**
   * Set "message body" of comment
   * @param text the comment text, itself
   */
  public void setText(String text) {
    this.text = text;
  }

  /**
   * Update this comment by applying text.
   *
   * @param text The text to apply.
   * @return The comment.
   */
  public Comment text(String text) {
    setText(text);
    return this;
  }

  /**
   * Get the contributor who submitted this comment
   * @return contributor of comment
   */
  public ResourceReference getContributor() {
    return contributor;
  }

  /**
   * Set the contributor of this comment
   * @param contributor reference to who submitted the comment
   */
  public void setContributor(ResourceReference contributor) {
    this.contributor = contributor;
  }

  /**
   * Build this comment by applying a contributor.
   *
   * @param contributor The contributor.
   * @return this.
   */
  public Comment contributor(ResourceReference contributor) {
    setContributor(contributor);
    return this;
  }

  /**
   * Get the date of comment creation
   * @return date of creation
   */
  public Date getCreated() {
    return created;
  }

  /**
   * Set the date the comment was created
   * @param created date of creation
   */
  public void setCreated(Date created) {
    this.created = created;
  }

  /**
   * Build this comment by applying a created date.
   *
   * @param created The created date.
   * @return The comment.
   */
  public Comment created(Date created) {
    setCreated(created);
    return this;
  }

  /**
   * Accept a visitor.
   *
   * @param visitor The visitor to accept.
   */
  public void accept(FamilySearchPlatformModelVisitor visitor) {
    visitor.visitComment(this);
  }

  public void embed(Comment comment) {
    super.embed(comment);
  }
}
