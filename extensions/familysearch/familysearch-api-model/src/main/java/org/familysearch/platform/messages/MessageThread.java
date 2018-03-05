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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.gedcomx.rt.json.JsonElementWrapper;

import org.familysearch.platform.rt.FamilySearchPlatformModelVisitor;


/**
 * A thread of related messages.
 */
@XmlRootElement
@JsonElementWrapper(name = "messageThread")
@XmlType(name = "MessageThread", propOrder = {"messages" })
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageThread extends AbstractMessageThread<MessageThread> {
  private List<Message> messages;

  @Override
  protected MessageThread self() {
    return this;
  }

  /**
   * Get the messages associated with this message thread.
   *
   * @return The messages associated with this message thread.
   */
  public List<Message> getMessages() {
    return messages;
  }

  /**
   * Set the messages associated with this message thread.
   *
   * @param messages The messages associated with this message thread.
   */
  public void setMessages(final List<Message> messages) {
    this.messages = messages;
  }

  /**
   * Build out this message thread by applying a list of messages.
   *
   * @param messages The list of messages to apply to this message thread.
   * @return this.
   */
  public MessageThread messages(final List<Message> messages) {
    setMessages(messages);
    return this;
  }

  /**
   * Add a message.
   *
   * @param message The message to add.
   */
  public void addMessage(Message message) {
    if (messages == null) {
      messages = new ArrayList<>();
    }
    messages.add(message);
  }

  /**
   * Accept a visitor.
   *
   * @param visitor The visitor to accept.
   */
  public void accept(FamilySearchPlatformModelVisitor visitor) {
    visitor.visitMessageThread(this);
  }

  public void embed(MessageThread messageThread) {
    List<Message> messages = messageThread.getMessages();
    if (messages != null) {
      for (Message message : messages) {
        boolean found = false;
        if (message.getId() != null) {
          if (getMessages() != null) {
            for (Message target : getMessages()) {
              if (message.getId().equals(target.getId())) {
                target.embed(message);
                found = true;
                break;
              }
            }
          }
        }

        if (!found) {
          addMessage(message);
        }
      }
    }

    super.embed(messageThread);
  }

}
