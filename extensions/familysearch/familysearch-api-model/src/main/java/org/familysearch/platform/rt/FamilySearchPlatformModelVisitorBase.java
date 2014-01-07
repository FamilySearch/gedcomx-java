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
import org.gedcomx.conclusion.Fact;
import org.gedcomx.rt.GedcomxModelVisitorBase;

import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

/**
 * @author Ryan Heaton
 */
@XmlTransient
public class FamilySearchPlatformModelVisitorBase extends GedcomxModelVisitorBase implements FamilySearchPlatformModelVisitor {

  @Override
  public void visitFamilySearchPlatform(FamilySearchPlatform fsp) {
    visitGedcomx(fsp);

    this.contextStack.push(fsp);
    List<Discussion> discussions = fsp.getDiscussions();
    if (discussions != null) {
      for (Discussion discussion : discussions) {
        discussion.accept(this);
      }
    }

    List<Merge> merges = fsp.getMerges();
    if (merges != null) {
      for (Merge merge : merges) {
        merge.accept(this);
      }
    }

    List<MergeAnalysis> mergeAnalyses = fsp.getMergeAnalyses();
    if (mergeAnalyses != null) {
      for (MergeAnalysis merge : mergeAnalyses) {
        merge.accept(this);
      }
    }

    List<ChildAndParentsRelationship> childAndParentsRelationships = fsp.getChildAndParentsRelationships();
    if (childAndParentsRelationships != null) {
      for (ChildAndParentsRelationship pcr : childAndParentsRelationships) {
        pcr.accept(this);
      }
    }

    this.contextStack.pop();
  }

  @Override
  public void visitChildAndParentsRelationship(ChildAndParentsRelationship pcr) {
    this.contextStack.push( pcr );
    visitConclusion( pcr );

    List<Fact> facts;

    facts = pcr.getFatherFacts();
    if (facts != null) {
      for (Fact fact : facts) {
        fact.accept(this);
      }
    }

    facts = pcr.getMotherFacts();
    if (facts != null) {
      for (Fact fact : facts) {
        fact.accept(this);
      }
    }

    this.contextStack.pop();
  }

  @Override
  public void visitMerge(MergeAnalysis merge) {
    //no-op.
  }

  @Override
  public void visitMerge(Merge merge) {
    //no-op.
  }

  @Override
  public void visitDiscussion(Discussion discussion) {
    this.contextStack.push(discussion);
    List<Comment> comments = discussion.getComments();
    if (comments != null) {
      for (Comment comment : comments) {
        comment.accept(this);
      }
    }
    this.contextStack.pop();
  }

  @Override
  public void visitComment(Comment comment) {
    //no-op.
  }

}
