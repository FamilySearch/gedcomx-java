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
package org.familysearch.platform.messages;

import java.util.Date;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.gedcomx.agent.Agent;
import org.gedcomx.links.HypermediaEnabledData;

import org.familysearch.platform.rt.FamilySearchPlatformModelVisitor;

/**
 * A single message.
 */
@XmlType(name = "Message", propOrder = { "author", "body", "created"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Message extends HypermediaEnabledData {
  private Agent author;
  private String body;
  private Date created;

  /**
   * Get the author of this message.
   *
   * @return The author of this message.
   */
  public Agent getAuthor() {
    return author;
  }

  /**
   * Set the author of this message.
   *
   * @param author The author of this message.
   */
  public void setAuthor(final Agent author) {
    this.author = author;
  }

  /**
   * Build out this message by applying an author.
   *
   * @param author The author to apply to this message.
   * @return this.
   */
  public Message author(final Agent author) {
    setAuthor(author);
    return this;
  }

  /**
   * Get the message body of this message.
   *
   * @return The message body of this message.
   */
  public String getBody() {
    return body;
  }

  /**
   * Set the message body of this message.
   *
   * @param body The message body of this message.
   */
  public void setBody(final String body) {
    this.body = body;
  }

  /**
   * Build out this message by applying a message body.
   *
   * @param body The message body to apply to this message.
   * @return this.
   */
  public Message body(final String body) {
    setBody(body);
    return this;
  }

  /**
   * Get the date/time this message was created.
   *
   * @return The date/time this message was created.
   */
  public Date getCreated() {
    return created;
  }

  /**
   * Set the date/time this message was created.
   *
   * @param created The date/time this message was created.
   */
  public void setCreated(final Date created) {
    this.created = created;
  }

  /**
   * Build out this message by applying a created date/time.
   *
   * @param created The created date/time to apply to this message.
   * @return this.
   */
  public Message created(final Date created) {
    setCreated(created);
    return this;
  }

  /**
   * Accept a visitor.
   *
   * @param visitor The visitor to accept.
   */
  public void accept(FamilySearchPlatformModelVisitor visitor) {
    visitor.visitMessage(this);
  }

  public void embed(Message message) {
    super.embed(message);
  }

}
