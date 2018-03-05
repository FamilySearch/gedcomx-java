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
package org.familysearch.platform.rt;

import org.familysearch.platform.ct.ChildAndParentsRelationship;
import org.familysearch.platform.discussions.Comment;
import org.familysearch.platform.discussions.Discussion;
import org.familysearch.platform.messages.Message;
import org.familysearch.platform.messages.MessageThread;
import org.familysearch.platform.messages.UserMessageThreadSummary;
import org.familysearch.platform.users.User;
import org.gedcomx.Gedcomx;
import org.gedcomx.agent.Agent;
import org.gedcomx.common.*;
import org.gedcomx.conclusion.*;
import org.gedcomx.records.Collection;
import org.gedcomx.records.Field;
import org.gedcomx.records.FieldValue;
import org.gedcomx.records.RecordDescriptor;
import org.gedcomx.source.SourceCitation;
import org.gedcomx.source.SourceDescription;
import org.gedcomx.source.SourceReference;

/**
 * @author Ryan Heaton
 */
public class FamilySearchPlatformLocalReferenceResolver extends FamilySearchPlatformModelVisitorBase {

  private final String resourceId;
  protected ExtensibleData resource;

  public FamilySearchPlatformLocalReferenceResolver(String resourceId) {
    this.resourceId = resourceId;
  }

  public static ExtensibleData resolve(ResourceReference ref, Gedcomx document) {
    if (ref.getResource() == null) {
      return null;
    }

    return resolve(ref.getResource(), document);
  }

  public static ExtensibleData resolve(URI ref, Gedcomx document) {
    if (!ref.toString().startsWith("#")) {
      return null;
    }

    return resolve(ref.toString().substring(1), document);
  }

  public static ExtensibleData resolve(String resourceId, Gedcomx document) {
    if (resourceId == null) {
      return null;
    }

    FamilySearchPlatformLocalReferenceResolver visitor = new FamilySearchPlatformLocalReferenceResolver(resourceId);
    document.accept(visitor);
    return visitor.getResource();
  }

  public ExtensibleData getResource() {
    return resource;
  }

  protected void bindIfNeeded(ExtensibleData candidate) {
    if (resource == null && this.resourceId.equals(candidate.getId())) {
      this.resource = candidate;
    }
  }

  @Override
  public void visitChildAndParentsRelationship(ChildAndParentsRelationship pcr) {
    bindIfNeeded(pcr);
    super.visitChildAndParentsRelationship(pcr);
  }

  @Override
  public void visitDiscussion(Discussion discussion) {
    bindIfNeeded(discussion);
    super.visitDiscussion(discussion);
  }

  @Override
  public void visitComment(Comment comment) {
    bindIfNeeded(comment);
    super.visitComment(comment);
  }

  @Override
  public void visitMessageThread(MessageThread messageThread) {
    bindIfNeeded(messageThread);
    super.visitMessageThread(messageThread);
  }

  @Override
  public void visitUserMessageThreadSummary(UserMessageThreadSummary userMessageThreadSummary) {
    bindIfNeeded(userMessageThreadSummary);
    super.visitUserMessageThreadSummary(userMessageThreadSummary);
  }

  @Override
  public void visitMessage(Message message) {
    bindIfNeeded(message);
    super.visitMessage(message);
  }

  @Override
  public void visitGedcomx(Gedcomx gx) {
    bindIfNeeded(gx);
    super.visitGedcomx(gx);
  }

  @Override
  public void visitDocument(Document document) {
    bindIfNeeded(document);
    super.visitDocument(document);
  }

  @Override
  public void visitPlaceDescription(PlaceDescription place) {
    bindIfNeeded(place);
    super.visitPlaceDescription(place);
  }

  @Override
  public void visitEvent(Event event) {
    bindIfNeeded(event);
    super.visitEvent(event);
  }

  @Override
  public void visitEventRole(EventRole role) {
    bindIfNeeded(role);
    super.visitEventRole(role);
  }

  @Override
  public void visitAgent(Agent agent) {
    bindIfNeeded(agent);
    super.visitAgent(agent);
  }

  @Override
  public void visitSourceDescription(SourceDescription sourceDescription) {
    bindIfNeeded(sourceDescription);
    super.visitSourceDescription(sourceDescription);
  }

  @Override
  public void visitSourceCitation(SourceCitation citation) {
    bindIfNeeded(citation);
    super.visitSourceCitation(citation);
  }

  @Override
  public void visitCollection(Collection collection) {
    bindIfNeeded(collection);
    super.visitCollection(collection);
  }

  @Override
  public void visitRecordDescriptor(RecordDescriptor recordDescriptor) {
    bindIfNeeded(recordDescriptor);
    super.visitRecordDescriptor(recordDescriptor);
  }

  @Override
  public void visitField(Field field) {
    bindIfNeeded(field);
    super.visitField(field);
  }

  @Override
  public void visitFieldValue(FieldValue fieldValue) {
    bindIfNeeded(fieldValue);
    super.visitFieldValue(fieldValue);
  }

  @Override
  public void visitRelationship(Relationship relationship) {
    bindIfNeeded(relationship);
    super.visitRelationship(relationship);
  }

  @Override
  protected void visitConclusion(Conclusion conclusion) {
    bindIfNeeded(conclusion);
    super.visitConclusion(conclusion);
  }

  @Override
  protected void visitSubject(Subject subject) {
    bindIfNeeded(subject);
    super.visitSubject(subject);
  }

  @Override
  public void visitPerson(Person person) {
    bindIfNeeded(person);
    super.visitPerson(person);
  }

  @Override
  public void visitFact(Fact fact) {
    bindIfNeeded(fact);
    super.visitFact(fact);
  }

  @Override
  public void visitPlaceReference(PlaceReference place) {
    bindIfNeeded(place);
    super.visitPlaceReference(place);
  }

  @Override
  public void visitDate(Date date) {
    bindIfNeeded(date);
    super.visitDate(date);
  }

  @Override
  public void visitName(Name name) {
    bindIfNeeded(name);
    super.visitName(name);
  }

  @Override
  public void visitNameForm(NameForm form) {
    bindIfNeeded(form);
    super.visitNameForm(form);
  }

  @Override
  public void visitNamePart(NamePart part) {
    bindIfNeeded(part);
    super.visitNamePart(part);
  }

  @Override
  public void visitGender(Gender gender) {
    bindIfNeeded(gender);
    super.visitGender(gender);
  }

  @Override
  public void visitSourceReference(SourceReference sourceReference) {
    bindIfNeeded(sourceReference);
    super.visitSourceReference(sourceReference);
  }

  @Override
  public void visitNote(Note note) {
    bindIfNeeded(note);
    super.visitNote(note);
  }

  @Override
  public void visitEvidenceReference(EvidenceReference evidenceReference) {
    bindIfNeeded(evidenceReference);
    super.visitEvidenceReference(evidenceReference);
  }

  @Override
  public void visitUser(User user) {
    bindIfNeeded(user);
    super.visitUser(user);
  }
}
