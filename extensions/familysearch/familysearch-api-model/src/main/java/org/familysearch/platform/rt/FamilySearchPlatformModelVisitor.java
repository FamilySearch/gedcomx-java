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

import org.familysearch.platform.FamilySearchPlatform;
import org.familysearch.platform.ct.ChildAndParentsRelationship;
import org.familysearch.platform.ct.Merge;
import org.familysearch.platform.ct.MergeAnalysis;
import org.familysearch.platform.discussions.Comment;
import org.familysearch.platform.discussions.Discussion;
import org.familysearch.platform.users.User;
import org.familysearch.platform.vocab.VocabConcept;
import org.familysearch.platform.vocab.VocabTerm;

import org.gedcomx.rt.GedcomxModelVisitor;

/**
 * @author Ryan Heaton
 */
public interface FamilySearchPlatformModelVisitor extends GedcomxModelVisitor {
  void visitFamilySearchPlatform(FamilySearchPlatform fsp);

  void visitChildAndParentsRelationship(ChildAndParentsRelationship pcr);

  void visitMerge(MergeAnalysis merge);

  void visitMerge(Merge merge);

  void visitDiscussion(Discussion discussion);

  void visitComment(Comment comment);

  void visitUser(User user);

  void visitVocabConcept(VocabConcept vocabConcept);

  void visitVocabTerm(VocabTerm vocabTerm);

}
