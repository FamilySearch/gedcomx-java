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
import org.familysearch.platform.discussions.Discussion;
import org.gedcomx.Gedcomx;
import org.gedcomx.agent.Agent;
import org.gedcomx.conclusion.*;
import org.gedcomx.links.AnchorElementSupport;
import org.gedcomx.records.Collection;
import org.gedcomx.records.RecordDescriptor;
import org.gedcomx.source.SourceDescription;

/**
 * @author Ryan Heaton
 */
public class FamilySearchPlatformAnchorFinder extends FamilySearchPlatformModelVisitorBase {

  protected AnchorElementSupport resource;

  public FamilySearchPlatformAnchorFinder() {
  }

  public static AnchorElementSupport findAnchor(Gedcomx document) {
    FamilySearchPlatformAnchorFinder visitor = new FamilySearchPlatformAnchorFinder();
    document.accept(visitor);
    return visitor.getResource();
  }

  public AnchorElementSupport getResource() {
    return resource;
  }

  protected void bindIfNeeded(AnchorElementSupport candidate) {
    if (resource == null && candidate.getAnchor() != null && candidate.getAnchor()) {
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
  public void visitRelationship(Relationship relationship) {
    bindIfNeeded(relationship);
    super.visitRelationship(relationship);
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
}
