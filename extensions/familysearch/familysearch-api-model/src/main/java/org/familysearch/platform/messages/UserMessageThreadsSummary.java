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

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.gedcomx.links.HypermediaEnabledData;
import org.gedcomx.rt.json.JsonElementWrapper;

import org.familysearch.platform.rt.FamilySearchPlatformModelVisitor;

/**
 * Class that contains summary information for all message threads that a user is participating in and a list of
 * userMessageThreadSummary for each of those threads. Note that the id is not used.
 */
// TODO: Should the userId go in the id?
@JsonElementWrapper(name = "userMessageThreadsSummary")
@XmlType( name = "UserMessageThreadsSummary", propOrder = { "userId", "messageCount", "unreadMessageCount", "threadCount", "unreadThreadCount", "userMessageThreadSummaries" } )
@JsonInclude( JsonInclude.Include.NON_NULL )
public class UserMessageThreadsSummary extends HypermediaEnabledData {
  private String userId;
  private int messageCount;
  private int unreadMessageCount;
  private int threadCount;
  private int unreadThreadCount;
  private List<UserMessageThreadSummary> userMessageThreadSummaries;

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
  public UserMessageThreadsSummary userId(final String userId) {
    setUserId(userId);
    return this;
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
  public UserMessageThreadsSummary messageCount(final int messageCount) {
    setMessageCount(messageCount);
    return this;
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
  public UserMessageThreadsSummary unreadMessageCount(final int unreadMessageCount) {
    setUnreadMessageCount(unreadMessageCount);
    return this;
  }

  /**
   * Get the number of message threads this user is participating in.
   *
   * @return The number of message threads this user is participating in.
   */
  public int getThreadCount() {
    return threadCount;
  }

  /**
   * Set the number of message threads this user is participating in.
   *
   * @param threadCount The number of message threads this user is participating in.
   */
  public void setThreadCount(final int threadCount) {
    this.threadCount = threadCount;
  }

  /**
   * Build out this user message thread summary by applying a thread count.
   *
   * @param thread The thread count to apply to this user message thread summary.
   * @return this.
   */
  public UserMessageThreadsSummary threadCount(final int thread) {
    setThreadCount(thread);
    return this;
  }

  /**
   * Get the number of unread message threads this user is participating in.
   *
   * @return The number of unread message threads this user is participating in.
   */
  public int getUnreadThreadCount() {
    return unreadThreadCount;
  }

  /**
   * Set the number of unread message threads this user is participating in.
   *
   * @param unreadThreadCount The number of unread message threads this user is participating in.
   */
  public void setUnreadThreadCount(final int unreadThreadCount) {
    this.unreadThreadCount = unreadThreadCount;
  }

  /**
   * Build out this user message thread summary by applying an unread thread count.
   *
   * @param unreadThreadCount The unread thread count to apply to this user message thread summary.
   * @return this.
   */
  public UserMessageThreadsSummary unreadThreadCount(final int unreadThreadCount) {
    setUnreadThreadCount(unreadThreadCount);
    return this;
  }

  /**
   * Get the thread userMessageThreadSummary of message threads this user is participating in.
   *
   * @return The thread userMessageThreadSummary of message threads this user is participating in.
   */
  public List<UserMessageThreadSummary> getUserMessageThreadSummaries() {
    return userMessageThreadSummaries;
  }

  /**
   * Set the thread userMessageThreadSummary of message threads this user is participating in.
   *
   * @param userMessageThreadSummaries The thread userMessageThreadSummary of message threads this user is participating in.
   */
  public void setUserMessageThreadSummaries(final List<UserMessageThreadSummary> userMessageThreadSummaries) {
    this.userMessageThreadSummaries = userMessageThreadSummaries;
  }

  /**
   * Build out this user message thread summary by applying a list of thread userMessageThreadSummary.
   *
   * @param userMessageThreadSummaries The list of thread userMessageThreadSummary to apply to this user message thread userMessageThreadSummary.
   * @return this.
   */
  public UserMessageThreadsSummary userMessageThreadSummary(final List<UserMessageThreadSummary> userMessageThreadSummaries) {
    setUserMessageThreadSummaries(userMessageThreadSummaries);
    return this;
  }

  /**
   * Add a user message thread summary.
   *
   * @param userMessageThreadSummary The user message thread summary to add.
   */
  public void addUserMessageThreadSummary(UserMessageThreadSummary userMessageThreadSummary) {
    if (userMessageThreadSummaries == null) {
      userMessageThreadSummaries = new ArrayList<>();
    }
    userMessageThreadSummaries.add(userMessageThreadSummary);
  }

  /**
   * Accept a visitor.
   *
   * @param visitor The visitor to accept.
   */
  public void accept(FamilySearchPlatformModelVisitor visitor) {
    visitor.visitUserMessageThreadsSummary(this);
  }

  public void embed(UserMessageThreadsSummary userMessageThreadsSummary) {
    List<UserMessageThreadSummary> userMessageThreadSummaries = userMessageThreadsSummary.getUserMessageThreadSummaries();
    if (userMessageThreadSummaries != null) {
      for (UserMessageThreadSummary userMessageThreadSummary : userMessageThreadSummaries) {
        boolean found = false;
        if (userMessageThreadSummary.getId() != null) {
          if (getUserMessageThreadSummaries() != null) {
            for (UserMessageThreadSummary target : getUserMessageThreadSummaries()) {
              if (userMessageThreadSummary.getId().equals(target.getId())) {
                target.embed(userMessageThreadSummary);
                found = true;
                break;
              }
            }
          }
        }

        if (!found) {
          addUserMessageThreadSummary(userMessageThreadSummary);
        }
      }
    }

    super.embed(userMessageThreadsSummary);
  }

}
