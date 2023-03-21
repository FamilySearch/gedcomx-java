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

import org.familysearch.platform.vocab.VocabTranslation;
import org.gedcomx.Gedcomx;
import org.gedcomx.conclusion.Fact;
import org.gedcomx.rt.GedcomxModelVisitorBase;

import jakarta.xml.bind.annotation.XmlTransient;
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
        if (discussion != null) {
          discussion.accept(this);
        }
      }
    }

    this.contextStack.push(fsp);
    List<VocabConcept> vocabConcepts = fsp.getVocabConcepts();
    if (vocabConcepts != null) {
      for (VocabConcept vocabConcept : vocabConcepts) {
        if (vocabConcept != null) {
          vocabConcept.accept(this);
        }
      }
    }

    List<Merge> merges = fsp.getMerges();
    if (merges != null) {
      for (Merge merge : merges) {
        if (merge != null) {
          merge.accept(this);
        }
      }
    }

    List<MergeAnalysis> mergeAnalyses = fsp.getMergeAnalyses();
    if (mergeAnalyses != null) {
      for (MergeAnalysis merge : mergeAnalyses) {
        if (merge != null) {
          merge.accept(this);
        }
      }
    }

    List<ChildAndParentsRelationship> childAndParentsRelationships = fsp.getChildAndParentsRelationships();
    if (childAndParentsRelationships != null) {
      for (ChildAndParentsRelationship pcr : childAndParentsRelationships) {
        if (pcr != null) {
          pcr.accept(this);
        }
      }
    }

    List<User> users = fsp.getUsers();
    if (users != null) {
      for (User user : users) {
        if (user != null) {
          user.accept(this);
        }
      }
    }

    this.contextStack.pop();
  }

  @Override
  public void visitGedcomx(Gedcomx gx) {
    super.visitGedcomx(gx);
    this.contextStack.push(gx);
    List<Discussion> discussions = gx.findExtensionsOfType(Discussion.class);
    if (discussions != null) {
      for (Discussion discussion : discussions) {
        if (discussion != null) {
          discussion.accept(this);
        }
      }
    }

    List<VocabConcept> vocabConcepts = gx.findExtensionsOfType(VocabConcept.class);
    if (vocabConcepts != null) {
      for (VocabConcept vocabConcept : vocabConcepts) {
        if (vocabConcept != null) {
          vocabConcept.accept(this);
        }
      }
    }

    List<Merge> merges = gx.findExtensionsOfType(Merge.class);
    if (merges != null) {
      for (Merge merge : merges) {
        if (merge != null) {
          merge.accept(this);
        }
      }
    }

    List<MergeAnalysis> mergeAnalyses = gx.findExtensionsOfType(MergeAnalysis.class);
    if (mergeAnalyses != null) {
      for (MergeAnalysis merge : mergeAnalyses) {
        if (merge != null) {
          merge.accept(this);
        }
      }
    }

    List<ChildAndParentsRelationship> childAndParentsRelationships = gx.findExtensionsOfType(ChildAndParentsRelationship.class);
    if (childAndParentsRelationships != null) {
      for (ChildAndParentsRelationship pcr : childAndParentsRelationships) {
        if (pcr != null) {
          pcr.accept(this);
        }
      }
    }

    this.contextStack.pop();
  }

  @Override
  public void visitChildAndParentsRelationship(ChildAndParentsRelationship pcr) {
    this.contextStack.push( pcr );
    visitConclusion( pcr );

    List<Fact> facts;

    facts = pcr.getParent1Facts();
    if (facts != null) {
      for (Fact fact : facts) {
        if (fact != null) {
          fact.accept(this);
        }
      }
    }

    facts = pcr.getParent2Facts();
    if (facts != null) {
      for (Fact fact : facts) {
        if (fact != null) {
          fact.accept(this);
        }
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
        if (comment != null) {
          comment.accept(this);
        }
      }
    }
    this.contextStack.pop();
  }

  @Override
  public void visitComment(Comment comment) {
    //no-op.
  }

  @Override
  public void visitUser(User user) {
    //no-op.
  }

  @Override
  public void visitVocabConcept(VocabConcept vocabConcept) {
    this.contextStack.push(vocabConcept);
    List<VocabTerm> vocabTerms = vocabConcept.getVocabTerms();
    if (vocabTerms != null) {
      for (VocabTerm vocabTerm : vocabTerms) {
        if (vocabTerm != null) {
          vocabTerm.accept(this);
        }
      }
    }
    this.contextStack.pop();
  }

  @Override
  public void visitVocabTerm(VocabTerm vocabTerm) {
    //no-op.
  }

  @Override
  public void visitVocabTranslation(VocabTranslation vocabTranslation) {
    //no-op.
  }
}
