package org.familysearch.platform.rt;

import org.familysearch.platform.FamilySearchPlatform;
import org.familysearch.platform.ct.Association;
import org.familysearch.platform.ct.ChildAndParentsRelationship;
import org.familysearch.platform.ct.Merge;
import org.familysearch.platform.ct.MergeAnalysis;
import org.familysearch.platform.discussions.Comment;
import org.familysearch.platform.discussions.Discussion;
import org.gedcomx.conclusion.*;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FamilySearchPlatformModelVisitorBaseTest {
  @Test
  void nullVisitor() {
    try {
      FamilySearchPlatform fsp = new FamilySearchPlatform();
      fsp.accept(null);
      fail("Expected: NullPointerException");
    } catch (NullPointerException ex) {
    }
  }

  @Test
  void visitFeed() {
    FamilySearchPlatformModelVisitorBase visitor = new FamilySearchPlatformModelVisitorBase();
    assertNotNull(visitor.getContextStack());
    assertEquals(0, visitor.getContextStack().size());

    FamilySearchPlatform fsp = new FamilySearchPlatform();

    // visit empty feed
    fsp.accept(visitor);

    ArrayList<Discussion> discussions;
    ArrayList<MergeAnalysis> mergeAnalyses;
    ArrayList<Merge> merges;
    ArrayList<ChildAndParentsRelationship> childAndParentsRelationships;
    ArrayList<Association> associations;

    // re-visit feed; empty lists
    discussions = new ArrayList<>();
    mergeAnalyses = new ArrayList<>();
    merges = new ArrayList<>();
    childAndParentsRelationships = new ArrayList<>();
    associations = new ArrayList<>();
    fsp.setAgents(new ArrayList<>());
    fsp.setDiscussions( discussions );
    fsp.setDocuments(new ArrayList<>());
    fsp.setEvents(new ArrayList<>());
    fsp.setExtensionElements(new ArrayList<>());
    fsp.setLinks(new ArrayList<>());
    fsp.setMerges( merges );
    fsp.setMergeAnalyses( mergeAnalyses );
    fsp.setChildAndParentsRelationships( childAndParentsRelationships );
    fsp.setAssociations( associations );
    fsp.setPersons(new ArrayList<>());
    fsp.setPlaces(new ArrayList<>());
    fsp.setRelationships(new ArrayList<>());
    fsp.setSourceDescriptions(new ArrayList<>());

    // re-visit feed; populate content; add element to authors and contributors
    discussions.add(new Discussion());
    mergeAnalyses.add( new MergeAnalysis() );
    merges.add( new Merge() );
    childAndParentsRelationships.add(new ChildAndParentsRelationship());
    associations.add(new Association());
    fsp.accept(visitor);

    // re-visit feed; add empty lists to discussions and parent-child relationships
    discussions.get(0).setComments(new ArrayList<>());
    childAndParentsRelationships.get(0).setParent1Facts(new ArrayList<>());
    childAndParentsRelationships.get(0).setParent2Facts(new ArrayList<>());
    associations.get(0).setFacts(new ArrayList<>());
    fsp.accept(visitor);

    // re-visit feed; add single element to comments and facts lists
    discussions.get(0).getComments().add(new Comment());
    childAndParentsRelationships.get(0).getParent1Facts().add(new Fact());
    childAndParentsRelationships.get(0).getParent2Facts().add(new Fact());
    associations.get(0).getFacts().add(new Fact());
    fsp.accept(visitor);
  }
}
