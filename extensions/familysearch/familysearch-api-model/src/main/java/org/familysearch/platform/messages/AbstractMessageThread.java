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
import java.util.List;
import javax.xml.bind.annotation.XmlType;

import org.gedcomx.agent.Agent;
import org.gedcomx.common.URI;
import org.gedcomx.links.HypermediaEnabledData;

/**
 * A representation of attributes associated with a message thread that are shared among more than one concrete class.
 * Note that the id in the ExtensibleData base class is the message thread id.
 */
@XmlType( name = "AbstractMessageThread" )
public abstract class AbstractMessageThread<T extends AbstractMessageThread<T>> extends HypermediaEnabledData {
  private Agent author;
  private List<Agent> participants;
  private String subject;
  private String about;
  private URI aboutUri;
  private Date lastModified;

  /**
   * Get the instance super class. Used for builder pattern methods so that builder pattern methods in this class
   * return the instance that extends this class.
   *
   * @return the instance super class.
   */
  protected abstract T self();

  /**
   * Get the author of this message thread.
   *
   * @return The author of this message thread.
   */
  public Agent getAuthor() {
    return author;
  }

  /**
   * Set the author of this message thread.
   *
   * @param author The author of this message thread.
   */
  public void setAuthor(final Agent author) {
    this.author = author;
  }

  /**
   * Build out this message thread by applying an author.
   *
   * @param author The author to apply to this message thread.
   * @return this.
   */
  public T author(final Agent author) {
    setAuthor(author);
    return self();
  }

  /**
   * Get the participants in this message thread. Note that the author is considered a participant by default and is
   * not included in the list of participants.
   *
   * @return The participants in this message thread.
   */
  public List<Agent> getParticipants() {
    return participants;
  }

  /**
   * Set the participants in this message thread. Note that currently, only one participant (in addition to the author)
   * is allowed. Also note that the author is considered a participant by default and does not have to be included in
   * the list of participants.
   *
   * @param participants The participants in this message thread.
   */
  public void setParticipants(final List<Agent> participants) {
    this.participants = participants;
  }

  /**
   * Build out this message thread by applying a list of participants.
   *
   * @param participants The participants to apply to this message thread.
   * @return this.
   */
  public T participants(final List<Agent> participants) {
    setParticipants(participants);
    return self();
  }

  /**
   * Get the subject of this message thread.
   *
   * @return The subject of this message thread.
   */
  public String getSubject() {
    return subject;
  }

  /**
   * Set the subject of this message thread.
   *
   * @param subject The subject of this message thread.
   */
  public void setSubject(final String subject) {
    this.subject = subject;
  }

  /**
   * Build out this message thread by applying a subject.
   *
   * @param subject The subject to apply to this message thread.
   * @return this.
   */
  public T subject(final String subject) {
    setSubject(subject);
    return self();
  }

  /**
   * Get the name of the tree person this message thread is about.
   *
   * @return The name of the tree person this message thread is about.
   */
  public String getAbout() {
    return about;
  }

  /**
   * Set the name of the tree person this message thread is about.
   *
   * @param about The name of the tree person this message thread is about.
   */
  public void setAbout(final String about) {
    this.about = about;
  }

  /**
   * Build out this message thread by applying the name of the tree person this message thread is about.
   *
   * @param about The name of the tree person this message thread is about to apply to this message thread.
   * @return this.
   */
  public T about(final String about) {
    setAbout(about);
    return self();
  }

  /**
   * Get the URI for the tree person this message thread is about.
   *
   * @return The URI for the tree person this message thread is about.
   */
  public URI getAboutUri() {
    return aboutUri;
  }

  /**
   * Set the URI for the tree person this message is about.
   *
   * @param aboutUri The URI for the tree person this message is about.
   */
  public void setAboutUri(final URI aboutUri) {
    this.aboutUri = aboutUri;
  }

  /**
   * Build out this message thread by applying the URI for the tree person this message is about.
   *
   * @param aboutUri The URI for the tree person this message is about to apply to this message thread.
   * @return this.
   */
  public T aboutUri(final URI aboutUri) {
    setAboutUri(aboutUri);
    return self();
  }

  /**
   * Get the date/time that this message thread was last modified.
   *
   * @return The date/time that this message thread was last modified.
   */
  public Date getLastModified() {
    return lastModified;
  }

  /**
   * Set the date/time that this message thread was last modified.
   *
   * @param lastModified The date/time that this message thread was last modified.
   */
  public void setLastModified(final Date lastModified) {
    this.lastModified = lastModified;
  }

  /**
   * Build out this message thread by applying the date/time that this message thread was last modified.
   *
   * @param lastModified The date/time that this message thread was last modified.
   * @return this.
   */
  public T lastModified(final Date lastModified) {
    setLastModified(lastModified);
    return self();
  }

}
