/**
 * Copyright Intellectual Reserve, Inc. All Rights reserved.
 */
package org.gedcomx.types;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 */
class TypesTest {
  @Test
  void toQNameURI() throws Exception {
    // NOTE: not a full test, but gets some code coverage

    assertEquals("http://gedcomx.org/Low", ConfidenceLevel.fromQNameURI(ConfidenceLevel.Low.toQNameURI()).toQNameURI().toString());
    assertEquals("http://gedcomx.org/Analysis", DocumentType.fromQNameURI(DocumentType.Analysis.toQNameURI()).toQNameURI().toString());
    assertEquals("http://gedcomx.org/Principal", EventRoleType.fromQNameURI(EventRoleType.Principal.toQNameURI()).toQNameURI().toString());
    assertEquals("http://gedcomx.org/Burial", EventType.fromQNameURI(EventType.Burial.toQNameURI()).toQNameURI().toString());
    assertEquals("http://gedcomx.org/Marriage", FactType.fromQNameURI(FactType.Marriage.toQNameURI()).toQNameURI().toString());
    assertEquals("http://gedcomx.org/Birth", FactType.fromQNameURI(FactType.Birth.toQNameURI()).toQNameURI().toString());
    assertEquals("http://gedcomx.org/Baptism", FactType.fromQNameURI(FactType.Baptism.toQNameURI()).toQNameURI().toString());
    assertEquals("http://gedcomx.org/Male", GenderType.fromQNameURI(GenderType.Male.toQNameURI()).toQNameURI().toString());
    assertEquals("http://gedcomx.org/Primary", IdentifierType.fromQNameURI(IdentifierType.Primary.toQNameURI()).toQNameURI().toString());
    assertEquals("http://gedcomx.org/Primary", NamePartQualifierType.fromQNameURI(NamePartQualifierType.Primary.toQNameURI()).toQNameURI().toString());
    assertEquals("http://gedcomx.org/Given", NamePartType.fromQNameURI(NamePartType.Given.toQNameURI()).toQNameURI().toString());
    assertEquals("http://gedcomx.org/FormalName", NameType.fromQNameURI(NameType.FormalName.toQNameURI()).toQNameURI().toString());
    assertEquals("http://gedcomx.org/Couple", RelationshipType.fromQNameURI(RelationshipType.Couple.toQNameURI()).toQNameURI().toString());
    assertEquals("http://gedcomx.org/Unreadable", FieldValueStatusType.fromQNameURI(FieldValueStatusType.Unreadable.toQNameURI()).toQNameURI().toString());
    assertEquals("http://gedcomx.org/Deprecated", ResourceStatusType.fromQNameURI(ResourceStatusType.Deprecated.toQNameURI()).toQNameURI().toString());
  }

  @Test
  void factTypeIsLike() throws Exception {
    // NOTE: not a full test, but gets some code coverage

    assertTrue(FactType.Christening.isBirthLike());
    assertTrue(FactType.Burial.isDeathLike());
    assertTrue(FactType.MarriageBanns.isMarriageLike());
    assertTrue(FactType.DivorceFiling.isDivorceLike());
    assertTrue(FactType.Naturalization.isMigrationLike());
  }

  @Test
  void factTypeInnerClasses() throws Exception {
    // NOTE: not a full test, but gets some code coverage

    assertTrue(FactType.Person.isApplicable(FactType.Will));
    assertTrue(FactType.Couple.isApplicable(FactType.Separation));
    assertTrue(FactType.ParentChild.isApplicable(FactType.GuardianParent));
  }

}
