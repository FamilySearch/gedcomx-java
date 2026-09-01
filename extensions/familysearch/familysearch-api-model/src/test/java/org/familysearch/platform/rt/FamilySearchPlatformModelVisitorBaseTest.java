package org.familysearch.platform.rt;

import org.familysearch.platform.FamilySearchPlatform;
import org.familysearch.platform.ct.Association;
import org.familysearch.platform.ct.AssociationType;
import org.familysearch.platform.ct.ChildAndParentsRelationship;
import org.familysearch.platform.ct.Merge;
import org.familysearch.platform.ct.MergeAnalysis;
import org.familysearch.platform.discussions.Comment;
import org.familysearch.platform.discussions.Discussion;
import org.gedcomx.Gedcomx;
import org.gedcomx.agent.Agent;
import org.gedcomx.common.EvidenceReference;
import org.gedcomx.common.ResourceReference;
import org.gedcomx.common.URI;
import org.gedcomx.conclusion.*;
import org.gedcomx.links.Link;
import org.gedcomx.records.Field;
import org.gedcomx.rt.GedcomxModelVisitor;
import org.gedcomx.source.SourceDescription;
import org.gedcomx.source.SourceReference;
import org.gedcomx.types.FactType;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FamilySearchPlatformModelVisitorBaseTest {
  @Test
  void nullVisitor() throws Exception {
    try {
      FamilySearchPlatform fsp = new FamilySearchPlatform();
      fsp.accept(null);
      fail("Expected: NullPointerException");
    } catch (NullPointerException ex) {
    }
  }

  @Test
  void visitFeed() throws Exception {
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
    discussions = new ArrayList<Discussion>();
    mergeAnalyses = new ArrayList<MergeAnalysis>();
    merges = new ArrayList<Merge>();
    childAndParentsRelationships = new ArrayList<ChildAndParentsRelationship>();
    associations = new ArrayList<Association>();
    fsp.setAgents( new ArrayList<Agent>() );
    fsp.setDiscussions( discussions );
    fsp.setDocuments( new ArrayList<Document>() );
    fsp.setEvents( new ArrayList<Event>() );
    fsp.setExtensionElements( new ArrayList<Object>() );
    fsp.setLinks( new ArrayList<Link>() );
    fsp.setMerges( merges );
    fsp.setMergeAnalyses( mergeAnalyses );
    fsp.setChildAndParentsRelationships( childAndParentsRelationships );
    fsp.setAssociations( associations );
    fsp.setPersons( new ArrayList<Person>() );
    fsp.setPlaces( new ArrayList<PlaceDescription>() );
    fsp.setRelationships( new ArrayList<Relationship>() );
    fsp.setSourceDescriptions( new ArrayList<SourceDescription>() );

    // re-visit feed; populate content; add element to authors and contributors
    discussions.add(new Discussion());
    mergeAnalyses.add( new MergeAnalysis() );
    merges.add( new Merge() );
    childAndParentsRelationships.add(new ChildAndParentsRelationship());
    associations.add(new Association());
    fsp.accept(visitor);

    // re-visit feed; add empty lists to discussions and parent-child relationships
    discussions.get(0).setComments(new ArrayList<Comment>());
    childAndParentsRelationships.get(0).setParent1Facts(new ArrayList<Fact>());
    childAndParentsRelationships.get(0).setParent2Facts(new ArrayList<Fact>());
    fsp.accept(visitor);

    // re-visit feed; add single element to comments and facts lists
    discussions.get(0).getComments().add(new Comment());
    childAndParentsRelationships.get(0).getParent1Facts().add(new Fact());
    childAndParentsRelationships.get(0).getParent2Facts().add(new Fact());
    fsp.accept(visitor);
  }

  @Test
  void visitAssociation() throws Exception {
    CountingVisitor visitor = new CountingVisitor();

    Association association = new Association();
    association.setPerson1(new ResourceReference(URI.create("urn:person1")));
    association.setPerson2(new ResourceReference(URI.create("urn:person2")));
    association.setKnownAssociationType(AssociationType.Godparent);

    // Test dispatch through FamilySearchPlatformModelVisitor entry point
    association.accept((FamilySearchPlatformModelVisitor) visitor);
    assertEquals(1, visitor.associationCount);
    assertEquals(0, visitor.relationshipCount);

    visitor.reset();

    // Test dispatch through GedcomxModelVisitor entry point
    association.accept((GedcomxModelVisitor) visitor);
    assertEquals(1, visitor.associationCount);
    assertEquals(0, visitor.relationshipCount);

    visitor.reset();

    // Test association in FamilySearchPlatform.getAssociations()
    FamilySearchPlatform fsp = new FamilySearchPlatform();
    fsp.addAssociation(association);
    fsp.accept(visitor);
    assertEquals(1, visitor.associationCount);
    assertEquals(0, visitor.relationshipCount);

    visitor.reset();

    // Test association as extension element on plain Gedcomx
    Gedcomx gx = new Gedcomx();
    gx.addExtensionElement(association);
    gx.accept(visitor);
    assertEquals(1, visitor.associationCount);
    assertEquals(0, visitor.relationshipCount);

    visitor.reset();

    // Test that visitAssociation walks the facts list
    Fact fact = new Fact();
    fact.setKnownType(FactType.Residence);
    association.addFact(fact);
    association.accept((FamilySearchPlatformModelVisitor) visitor);
    assertEquals(1, visitor.associationCount);
    assertEquals(1, visitor.factCount);

    visitor.reset();

    // Test that visitAssociation walks fields, evidence, and media
    Field field = new Field();
    association.addField(field);
    EvidenceReference evidenceRef = new EvidenceReference();
    evidenceRef.setResource(URI.create("urn:evidence"));
    association.addEvidence(evidenceRef);
    SourceReference mediaRef = new SourceReference();
    mediaRef.setDescriptionRef(URI.create("urn:media"));
    association.addMedia(mediaRef);
    association.accept((FamilySearchPlatformModelVisitor) visitor);
    assertEquals(1, visitor.associationCount);
    assertEquals(1, visitor.factCount);
    assertEquals(1, visitor.fieldCount);
    assertEquals(1, visitor.evidenceReferenceCount);
    assertEquals(1, visitor.sourceReferenceCount);
  }

  private static class CountingVisitor extends FamilySearchPlatformModelVisitorBase {
    int associationCount = 0;
    int relationshipCount = 0;
    int factCount = 0;
    int fieldCount = 0;
    int evidenceReferenceCount = 0;
    int sourceReferenceCount = 0;

    @Override
    public void visitAssociation(Association association) {
      associationCount++;
      super.visitAssociation(association);
    }

    @Override
    public void visitRelationship(Relationship relationship) {
      relationshipCount++;
      super.visitRelationship(relationship);
    }

    @Override
    public void visitFact(Fact fact) {
      factCount++;
      super.visitFact(fact);
    }

    @Override
    public void visitField(Field field) {
      fieldCount++;
      super.visitField(field);
    }

    @Override
    public void visitEvidenceReference(EvidenceReference evidenceReference) {
      evidenceReferenceCount++;
      super.visitEvidenceReference(evidenceReference);
    }

    @Override
    public void visitSourceReference(SourceReference sourceReference) {
      sourceReferenceCount++;
      super.visitSourceReference(sourceReference);
    }

    void reset() {
      associationCount = 0;
      relationshipCount = 0;
      factCount = 0;
      fieldCount = 0;
      evidenceReferenceCount = 0;
      sourceReferenceCount = 0;
    }
  }
}
