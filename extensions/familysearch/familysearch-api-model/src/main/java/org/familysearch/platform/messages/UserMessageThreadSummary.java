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

import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.gedcomx.rt.json.JsonElementWrapper;

import org.familysearch.platform.rt.FamilySearchPlatformModelVisitor;

/**
 * Class that contains a summary of a message thread that a user is participating in. Note that the id is
 * the id of the message thread whose counters are represented.
 */
@JsonElementWrapper(name = "userMessageThreadSummary")
@XmlType( name = "UserMessageThreadSummary", propOrder = { "userId", "messageCount", "unreadMessageCount" } )
@JsonInclude( JsonInclude.Include.NON_NULL )
public class UserMessageThreadSummary extends AbstractMessageThread<UserMessageThreadSummary> {
  private String userId;
  private int messageCount;
  private int unreadMessageCount;

  @Override
  protected UserMessageThreadSummary self() {
    return this;
  }

  /**
   * Get the id of the user that this user message thread summary is for.
   *
   * @return The id of the user that this user message thread summary is for.
   */
  public String getUserId() {
    return userId;
  }

  /**
   * Set the id of the user that this user message thread summary is for.
   *
   * @param userId The id of the user that this user message thread summary is for.
   */
  public void setUserId(final String userId) {
    this.userId = userId;
  }

  /**
   * Build out this user message thread summary by applying the user id that this user message summary is for.
   *
   * @param userId The user id that this user message summary is for to apply to this user message thread summary.
   * @return this.
   */
  public UserMessageThreadSummary userId(final String userId) {
    setUserId(userId);
    return self();
  }

  /**
   * Get the number of messages in all of the message threads this user is participating in.
   *
   * @return The number of messages in all of the message threads this user is participating in.
   */
  public int getMessageCount() {
    return messageCount;
  }

  /**
   * Set the number of messages in all of the message threads this user is participating in.
   *
   * @param messageCount The number of messages in all of the message threads this user is participating in.
   */
  public void setMessageCount(final int messageCount) {
    this.messageCount = messageCount;
  }

  /**
   * Build out this user message thread summary by applying a message count.
   *
   * @param messageCount The message count to apply to this user message thread summary.
   * @return this.
   */
  public UserMessageThreadSummary messageCount(final int messageCount) {
    setMessageCount(messageCount);
    return self();
  }

  /**
   * Get the number of unread messages in all of the message threads this user is participating in.
   *
   * @return The number of unread messages in all of the message threads this user is participating in.
   */
  public int getUnreadMessageCount() {
    return unreadMessageCount;
  }

  /**
   * Set the number of unread messages in all of the message threads this user is participating in.
   *
   * @param unreadMessageCount The number of unread messages in all of the message threads this user is participating in.
   */
  public void setUnreadMessageCount(final int unreadMessageCount) {
    this.unreadMessageCount = unreadMessageCount;
  }

  /**
   * Build out this user message thread summary by applying an unread message count.
   *
   * @param unreadMessageCount The unread message count to apply to this user message thread summary.
   * @return this.
   */
  public UserMessageThreadSummary unreadMessageCount(final int unreadMessageCount) {
    setUnreadMessageCount(unreadMessageCount);
    return self();
  }

  /**
   * Accept a visitor.
   *
   * @param visitor The visitor to accept.
   */
  public void accept(FamilySearchPlatformModelVisitor visitor) {
    visitor.visitUserMessageThreadSummary(this);
  }

  public void embed(UserMessageThreadSummary userMessageThreadSummary) {
    super.embed(userMessageThreadSummary);
  }

}
