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

import org.familysearch.platform.rt.FamilySearchPlatformModelVisitor;
import org.gedcomx.common.ResourceReference;
import org.gedcomx.links.HypermediaEnabledData;

import javax.xml.bind.annotation.XmlType;
import java.util.Date;

/**
 */
@XmlType ( name = "Comment", propOrder = { "text", "created", "contributor" } )
@SuppressWarnings("gedcomx:no_id")
public class Comment extends HypermediaEnabledData {
  private String text;
  private ResourceReference contributor;
  private Date created;

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
   * Accept a visitor.
   *
   * @param visitor The visitor to accept.
   */
  public void accept(FamilySearchPlatformModelVisitor visitor) {
    visitor.visitComment(this);
  }
}
