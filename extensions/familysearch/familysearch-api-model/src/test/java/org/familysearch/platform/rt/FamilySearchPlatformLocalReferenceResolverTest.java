package org.familysearch.platform.rt;

import org.familysearch.platform.FamilySearchPlatform;
import org.familysearch.platform.ct.Association;
import org.familysearch.platform.ct.AssociationType;
import org.gedcomx.common.ExtensibleData;
import org.gedcomx.common.ResourceReference;
import org.gedcomx.common.URI;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FamilySearchPlatformLocalReferenceResolverTest {

  @Test
  void resolveAssociationById() {
    FamilySearchPlatform doc = new FamilySearchPlatform();
    Association association = new Association();
    association.setId("assoc1");
    association.setPerson1(new ResourceReference(URI.create("urn:person1")));
    association.setPerson2(new ResourceReference(URI.create("urn:person2")));
    association.setKnownAssociationType(AssociationType.Godparent);
    doc.addAssociation(association);

    ExtensibleData resolved = FamilySearchPlatformLocalReferenceResolver.resolve("assoc1", doc);
    assertNotNull(resolved);
    assertSame(association, resolved);
  }

  @Test
  void resolveNonExistentId() {
    FamilySearchPlatform doc = new FamilySearchPlatform();
    Association association = new Association();
    association.setId("assoc1");
    doc.addAssociation(association);

    ExtensibleData resolved = FamilySearchPlatformLocalReferenceResolver.resolve("nonExistent", doc);
    assertNull(resolved);
  }
}
