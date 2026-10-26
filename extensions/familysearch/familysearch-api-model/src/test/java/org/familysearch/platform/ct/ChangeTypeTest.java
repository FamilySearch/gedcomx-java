package org.familysearch.platform.ct;

import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.gedcomx.common.URI;

import static org.junit.jupiter.api.Assertions.*;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;

class ChangeTypeTest {

  private Collection<ChangeType> typesTested;
  private Set<ChangeType> expectedAssociationTypes;

  @Test
  void it() {
    typesTested = new LinkedList<>();

    // test the contract that the @XmlEnumValue is unique and does not change its value
    testChangeType( "Person Created", ChangeType.CREATE_PERSON );
    testChangeType( "Couple Relationship Created", ChangeType.CREATE_COUPLE_RELATIONSHIP );
    testChangeType( "Child and Parents Relationship Created", ChangeType.CREATE_CHILD_AND_PARENTS_RELATIONSHIP);
    testChangeType( "Person Removed", ChangeType.DELETE_PERSON );
    testChangeType( "Couple Relationship Removed", ChangeType.DELETE_COUPLE_RELATIONSHIP );
    testChangeType( "Child and Parents Relationship Removed", ChangeType.DELETE_CHILD_AND_PARENTS_RELATIONSHIP);
    testChangeType( "Spouse1 Added", ChangeType.ADD_SPOUSE1 );
    testChangeType( "Spouse2 Added", ChangeType.ADD_SPOUSE2 );
    testChangeType( "Parent1 Added", ChangeType.ADD_PARENT1 );
    testChangeType( "Parent2 Added", ChangeType.ADD_PARENT2 );
    testChangeType( "Child Added", ChangeType.ADD_CHILD );
    testChangeType( "Spouse1 Changed", ChangeType.EDIT_SPOUSE1 );
    testChangeType( "Spouse2 Changed", ChangeType.EDIT_SPOUSE2 );
    testChangeType( "Parent1 Changed", ChangeType.EDIT_PARENT1 );
    testChangeType( "Parent2 Changed", ChangeType.EDIT_PARENT2 );
    testChangeType( "Child Changed", ChangeType.EDIT_CHILD );
    testChangeType( "Parent1 Removed", ChangeType.REMOVE_PARENT1 );
    testChangeType( "Parent2 Removed", ChangeType.REMOVE_PARENT2 );
    testChangeType( "Person Merged", ChangeType.MERGE_PERSON );
    testChangeType( "Couple Relationship Merged", ChangeType.MERGE_COUPLE_RELATIONSHIP );
    testChangeType( "Child and Parents Relationship Merged", ChangeType.MERGE_CHILD_AND_PARENTS_RELATIONSHIP);
    testChangeType( "Person Unmerged", ChangeType.UNMERGE_PERSON );
    testChangeType( "Couple Relationship Unmerged", ChangeType.UNMERGE_COUPLE_RELATIONSHIP );
    testChangeType( "Child and Parents Relationship Unmerged", ChangeType.UNMERGE_CHILD_AND_PARENTS_RELATIONSHIP);
    testChangeType( "Person Restored", ChangeType.UNTOMBSTONE_PERSON );
    testChangeType( "Couple Relationship Restored", ChangeType.UNTOMBSTONE_COUPLE_RELATIONSHIP );
    testChangeType( "Child and Parents Relationship Restored", ChangeType.UNTOMBSTONE_CHILD_AND_PARENTS_RELATIONSHIP);
    /* Not currently supported
    testChangeType( "PersonAccessControl Changed", ChangeType.EDIT_PERSON_ACCESS_CONTROL );
    testChangeType( "Couple Relationship AccessControl Changed", ChangeType.EDIT_COUPLE_RELATIONSHIP_ACCESS_CONTROL );
    testChangeType( "Parent-Child Relationship AccessControl Changed", ChangeType.EDIT_PARENT_CHILD_RELATIONSHIP_ACCESS_CONTROL );
    testChangeType( "Change Removed", ChangeType.DELETE_CHANGE );
    testChangeType( "Admin Label Set", ChangeType.SET_ADMIN_LABEL );
    testChangeType( "Admin Label Cleared", ChangeType.CLEAR_ADMIN_LABEL );
    testChangeType( "Not A Match Declaration Set", ChangeType.SET_NOT_A_MATCH_DECLARATION );
    testChangeType( "Not A Match Declaration Cleared", ChangeType.CLEAR_NOT_A_MATCH_DECLARATION );
    */
    testChangeType( "Set Person Not a Match", ChangeType.ADD_PERSON_NOT_A_MATCH);
    testChangeType( "Remove Person Not a Match", ChangeType.REMOVE_PERSON_NOT_A_MATCH);
    testChangeType( "Person Source Reference Added", ChangeType.ADD_PERSON_SOURCE_REFERENCE);
    testChangeType( "Person Source Reference Changed", ChangeType.EDIT_PERSON_SOURCE_REFERENCE);
    testChangeType( "Person Source Reference Removed", ChangeType.DELETE_PERSON_SOURCE_REFERENCE);
    testChangeType( "Couple Source Reference Added", ChangeType.ADD_COUPLE_SOURCE_REFERENCE);
    testChangeType( "Couple Source Reference Changed", ChangeType.EDIT_COUPLE_SOURCE_REFERENCE);
    testChangeType( "Couple Source Reference Removed", ChangeType.DELETE_COUPLE_SOURCE_REFERENCE);
    testChangeType( "Child and Parents Source Reference Added", ChangeType.ADD_CHILD_PARENTS_SOURCE_REFERENCE);
    testChangeType( "Child and Parents Source Reference Changed", ChangeType.EDIT_CHILD_PARENTS_SOURCE_REFERENCE);
    testChangeType( "Child and Parents Source Reference Removed", ChangeType.DELETE_CHILD_PARENTS_SOURCE_REFERENCE);
    testChangeType( "Person Discussion Reference Added", ChangeType.ADD_PERSON_DISCUSSION_REFERENCE);
    testChangeType( "Person Discussion Reference Changed", ChangeType.EDIT_PERSON_DISCUSSION_REFERENCE);
    testChangeType( "Person Discussion Reference Removed", ChangeType.DELETE_PERSON_DISCUSSION_REFERENCE);
    testChangeType( "Couple Discussion Reference Added", ChangeType.ADD_COUPLE_DISCUSSION_REFERENCE);
    testChangeType( "Couple Discussion Reference Changed", ChangeType.EDIT_COUPLE_DISCUSSION_REFERENCE);
    testChangeType( "Couple Discussion Reference Removed", ChangeType.DELETE_COUPLE_DISCUSSION_REFERENCE);
    testChangeType( "Child and Parents Discussion Reference Added", ChangeType.ADD_CHILD_PARENTS_DISCUSSION_REFERENCE);
    testChangeType( "Child and Parents Discussion Reference Changed", ChangeType.EDIT_CHILD_PARENTS_DISCUSSION_REFERENCE);
    testChangeType( "Child and Parents Discussion Reference Removed", ChangeType.DELETE_CHILD_PARENTS_DISCUSSION_REFERENCE);
    testChangeType( "Tree Person Reference Added", ChangeType.ADD_TREE_PERSON_REFERENCE);
    testChangeType( "Tree Person Reference Changed", ChangeType.EDIT_TREE_PERSON_REFERENCE);
    testChangeType( "Tree Person Reference Removed", ChangeType.DELETE_TREE_PERSON_REFERENCE);
    testChangeType( "Person Evidence Reference Added", ChangeType.ADD_PERSON_EVIDENCE_REFERENCE);
    testChangeType( "Person Evidence Reference Changed", ChangeType.EDIT_PERSON_EVIDENCE_REFERENCE);
    testChangeType( "Person Evidence Reference Removed", ChangeType.DELETE_PERSON_EVIDENCE_REFERENCE);
    testChangeType( "Couple Evidence Reference Added", ChangeType.ADD_COUPLE_EVIDENCE_REFERENCE);
    testChangeType( "Couple Evidence Reference Changed", ChangeType.EDIT_COUPLE_EVIDENCE_REFERENCE);
    testChangeType( "Couple Evidence Reference Removed", ChangeType.DELETE_COUPLE_EVIDENCE_REFERENCE);
    testChangeType( "Child and Parents Evidence Reference Added", ChangeType.ADD_CHILD_PARENTS_EVIDENCE_REFERENCE);
    testChangeType( "Child and Parents Evidence Reference Changed", ChangeType.EDIT_CHILD_PARENTS_EVIDENCE_REFERENCE);
    testChangeType( "Child and Parents Evidence Reference Removed", ChangeType.DELETE_CHILD_PARENTS_EVIDENCE_REFERENCE);
    testChangeType( "Affiliation Added", ChangeType.ADD_AFFILIATION );
    testChangeType( "Affiliation Changed", ChangeType.EDIT_AFFILIATION );
    testChangeType( "Affiliation Removed", ChangeType.DELETE_AFFILIATION );
    testChangeType( "Bar Mitzvah Added", ChangeType.ADD_BAR_MITZVAH );
    testChangeType( "Bar Mitzvah Changed", ChangeType.EDIT_BAR_MITZVAH );
    testChangeType( "Bar Mitzvah Removed", ChangeType.DELETE_BAR_MITZVAH );
    testChangeType( "Bat Mitzvah Added", ChangeType.ADD_BAS_MITZVAH);
    testChangeType( "Bat Mitzvah Changed", ChangeType.EDIT_BAS_MITZVAH);
    testChangeType( "Bat Mitzvah Removed", ChangeType.DELETE_BAS_MITZVAH);
    testChangeType( "Birth Added", ChangeType.ADD_BIRTH );
    testChangeType( "Birth Changed", ChangeType.EDIT_BIRTH );
    testChangeType( "Birth Removed", ChangeType.DELETE_BIRTH );
    testChangeType( "Burial Added", ChangeType.ADD_BURIAL );
    testChangeType( "Burial Changed", ChangeType.EDIT_BURIAL );
    testChangeType( "Burial Removed", ChangeType.DELETE_BURIAL );
    testChangeType( "Christening Added", ChangeType.ADD_CHRISTENING );
    testChangeType( "Christening Changed", ChangeType.EDIT_CHRISTENING );
    testChangeType( "Christening Removed", ChangeType.DELETE_CHRISTENING );
    testChangeType( "Cremation Added", ChangeType.ADD_CREMATION );
    testChangeType( "Cremation Changed", ChangeType.EDIT_CREMATION );
    testChangeType( "Cremation Removed", ChangeType.DELETE_CREMATION );
    testChangeType( "Death Added", ChangeType.ADD_DEATH );
    testChangeType( "Death Changed", ChangeType.EDIT_DEATH );
    testChangeType( "Death Removed", ChangeType.DELETE_DEATH );
    testChangeType( "Military Service Added", ChangeType.ADD_MILITARY_SERVICE );
    testChangeType( "Military Service Changed", ChangeType.EDIT_MILITARY_SERVICE );
    testChangeType( "Military Service Removed", ChangeType.DELETE_MILITARY_SERVICE );
    testChangeType( "Naturalization Added", ChangeType.ADD_NATURALIZATION );
    testChangeType( "Naturalization Changed", ChangeType.EDIT_NATURALIZATION );
    testChangeType( "Naturalization Removed", ChangeType.DELETE_NATURALIZATION );
    testChangeType( "Immigration Added", ChangeType.ADD_IMMIGRATION );
    testChangeType( "Immigration Changed", ChangeType.EDIT_IMMIGRATION );
    testChangeType( "Immigration Removed", ChangeType.DELETE_IMMIGRATION );
    testChangeType( "Title of Nobility Added", ChangeType.ADD_NOBILITY_TYPE );
    testChangeType( "Title of Nobility Changed", ChangeType.EDIT_NOBILITY_TYPE );
    testChangeType( "Title of Nobility Removed", ChangeType.DELETE_NOBILITY_TYPE );
    testChangeType( "Occupation Added", ChangeType.ADD_OCCUPATION );
    testChangeType( "Occupation Changed", ChangeType.EDIT_OCCUPATION );
    testChangeType( "Occupation Removed", ChangeType.DELETE_OCCUPATION );
    testChangeType( "Religion Added", ChangeType.ADD_RELIGIOUS_AFFILIATION );
    testChangeType( "Religion Changed", ChangeType.EDIT_RELIGIOUS_AFFILIATION );
    testChangeType( "Religion Removed", ChangeType.DELETE_RELIGIOUS_AFFILIATION );
    testChangeType( "Residence Added", ChangeType.ADD_RESIDENCE );
    testChangeType( "Residence Changed", ChangeType.EDIT_RESIDENCE );
    testChangeType( "Residence Removed", ChangeType.DELETE_RESIDENCE );
    testChangeType( "Stillbirth Added", ChangeType.ADD_STILLBORN );
    testChangeType( "Stillbirth Changed", ChangeType.EDIT_STILLBORN );
    testChangeType( "Stillbirth Removed", ChangeType.DELETE_STILLBORN );
    testChangeType( "Couple Event Added", ChangeType.ADD_COUPLE_EVENT );
    testChangeType( "Couple Event Changed", ChangeType.EDIT_COUPLE_EVENT );
    testChangeType( "Couple Event Removed", ChangeType.DELETE_COUPLE_EVENT );
    testChangeType( "Other Event Added", ChangeType.ADD_OTHER_EVENT );
    testChangeType( "Other Event Changed", ChangeType.EDIT_OTHER_EVENT );
    testChangeType( "Other Event Removed", ChangeType.DELETE_OTHER_EVENT );
    testChangeType( "Caste Added", ChangeType.ADD_CASTE_NAME );
    testChangeType( "Caste Changed", ChangeType.EDIT_CASTE_NAME );
    testChangeType( "Caste Removed", ChangeType.DELETE_CASTE_NAME );
    testChangeType( "Clan Added", ChangeType.ADD_CLAN_NAME );
    testChangeType( "Clan Changed", ChangeType.EDIT_CLAN_NAME );
    testChangeType( "Clan Removed", ChangeType.DELETE_CLAN_NAME );
    testChangeType( "Died Before Eight Added", ChangeType.ADD_DIED_BEFORE_EIGHT );
    testChangeType( "Died Before Eight Changed", ChangeType.EDIT_DIED_BEFORE_EIGHT );
    testChangeType( "Died Before Eight Removed", ChangeType.DELETE_DIED_BEFORE_EIGHT );
    testChangeType( "Life Sketch Added", ChangeType.ADD_LIFE_SKETCH );
    testChangeType( "Life Sketch Changed", ChangeType.EDIT_LIFE_SKETCH );
    testChangeType( "Life Sketch Removed", ChangeType.DELETE_LIFE_SKETCH );
    testChangeType( "National Id Added", ChangeType.ADD_NATIONAL_ID );
    testChangeType( "National Id Changed", ChangeType.EDIT_NATIONAL_ID );
    testChangeType( "National Id Removed", ChangeType.DELETE_NATIONAL_ID );
    testChangeType( "Nationality Added", ChangeType.ADD_NATIONAL_ORIGIN );
    testChangeType( "Nationality Changed", ChangeType.EDIT_NATIONAL_ORIGIN );
    testChangeType( "Nationality Removed", ChangeType.DELETE_NATIONAL_ORIGIN );
    testChangeType( "Physical Description Added", ChangeType.ADD_PHYSICAL_DESCRIPTION );
    testChangeType( "Physical Description Changed", ChangeType.EDIT_PHYSICAL_DESCRIPTION );
    testChangeType( "Physical Description Removed", ChangeType.DELETE_PHYSICAL_DESCRIPTION );
    testChangeType( "Ethnicity Added", ChangeType.ADD_RACE );
    testChangeType( "Ethnicity Changed", ChangeType.EDIT_RACE );
    testChangeType( "Ethnicity Removed", ChangeType.DELETE_RACE );
    testChangeType( "Tribe Name Added", ChangeType.ADD_TRIBE_NAME );
    testChangeType( "Tribe Name Changed", ChangeType.EDIT_TRIBE_NAME );
    testChangeType( "Tribe Name Removed", ChangeType.DELETE_TRIBE_NAME );
    testChangeType( "Other Fact Added", ChangeType.ADD_OTHER_FACT );
    testChangeType( "Other Fact Changed", ChangeType.EDIT_OTHER_FACT );
    testChangeType( "Other Fact Removed", ChangeType.DELETE_OTHER_FACT );
    testChangeType( "Gender Added", ChangeType.ADD_GENDER );
    testChangeType( "Gender Changed", ChangeType.EDIT_GENDER );
    testChangeType( "Gender Removed", ChangeType.DELETE_GENDER );
    testChangeType( "Birth Name Added", ChangeType.ADD_BIRTH_NAME);
    testChangeType( "Birth Name Changed", ChangeType.EDIT_BIRTH_NAME);
    testChangeType( "Birth Name Removed", ChangeType.DELETE_BIRTH_NAME);
    testChangeType( "Aka Name Added", ChangeType.ADD_ALTERNATE_NAME );
    testChangeType( "Aka Name Changed", ChangeType.EDIT_ALTERNATE_NAME );
    testChangeType( "Aka Name Removed", ChangeType.DELETE_ALTERNATE_NAME );
    testChangeType( "Aka Name Added", ChangeType.ADD_AKA_NAME );
    testChangeType( "Aka Name Changed", ChangeType.EDIT_AKA_NAME );
    testChangeType( "Aka Name Removed", ChangeType.DELETE_AKA_NAME );
    testChangeType( "Married Name Added", ChangeType.ADD_MARRIED_NAME );
    testChangeType( "Married Name Changed", ChangeType.EDIT_MARRIED_NAME );
    testChangeType( "Married Name Removed", ChangeType.DELETE_MARRIED_NAME );
    testChangeType( "Other Name Added", ChangeType.ADD_OTHER_NAME );
    testChangeType( "Other Name Changed", ChangeType.EDIT_OTHER_NAME );
    testChangeType( "Other Name Removed", ChangeType.DELETE_OTHER_NAME );
    testChangeType( "Nickname Added", ChangeType.ADD_NICK_NAME );
    testChangeType( "Nickname Changed", ChangeType.EDIT_NICK_NAME );
    testChangeType( "Nickname Removed", ChangeType.DELETE_NICK_NAME );
    testChangeType( "Lineage Added", ChangeType.ADD_LINEAGE );
    testChangeType( "Lineage Changed", ChangeType.EDIT_LINEAGE );
    testChangeType( "Lineage Removed", ChangeType.DELETE_LINEAGE );
    testChangeType( "Person Note Added", ChangeType.ADD_PERSON_NOTE);
    testChangeType( "Person Note Changed", ChangeType.EDIT_PERSON_NOTE);
    testChangeType( "Person Note Removed", ChangeType.DELETE_PERSON_NOTE);
    testChangeType( "Couple Note Added", ChangeType.ADD_COUPLE_NOTE);
    testChangeType( "Couple Note Changed", ChangeType.EDIT_COUPLE_NOTE);
    testChangeType( "Couple Note Removed", ChangeType.DELETE_COUPLE_NOTE);
    testChangeType( "Child and Parents Note Added", ChangeType.ADD_CHILD_PARENTS_NOTE);
    testChangeType( "Child and Parents Note Changed", ChangeType.EDIT_CHILD_PARENTS_NOTE);
    testChangeType( "Child and Parents Note Removed", ChangeType.DELETE_CHILD_PARENTS_NOTE);
    testChangeType( "Association Relationship Created", ChangeType.CREATE_ASSOCIATION_RELATIONSHIP );
    testChangeType( "Association Relationship Updated", ChangeType.UPDATE_ASSOCIATION_RELATIONSHIP );
    testChangeType( "Association Relationship Removed", ChangeType.DELETE_ASSOCIATION_RELATIONSHIP );
    testChangeType( "Association Relationship Restored", ChangeType.UNTOMBSTONE_ASSOCIATION_RELATIONSHIP );
    testChangeType( "Person1 Added", ChangeType.ADD_PERSON1 );
    testChangeType( "Person1 Changed", ChangeType.EDIT_PERSON1 );
    testChangeType( "Person2 Added", ChangeType.ADD_PERSON2 );
    testChangeType( "Person2 Changed", ChangeType.EDIT_PERSON2 );
    testChangeType( "Association Fact Added", ChangeType.ADD_ASSOCIATION_FACT );
    testChangeType( "Association Fact Changed", ChangeType.EDIT_ASSOCIATION_FACT );
    testChangeType( "Association Fact Removed", ChangeType.DELETE_ASSOCIATION_FACT );
    testChangeType( "Association Source Reference Added", ChangeType.ADD_ASSOCIATION_SOURCE_REFERENCE );
    testChangeType( "Association Source Reference Changed", ChangeType.EDIT_ASSOCIATION_SOURCE_REFERENCE );
    testChangeType( "Association Source Reference Removed", ChangeType.DELETE_ASSOCIATION_SOURCE_REFERENCE );
    testChangeType( "Association Discussion Reference Added", ChangeType.ADD_ASSOCIATION_DISCUSSION_REFERENCE );
    testChangeType( "Association Discussion Reference Changed", ChangeType.EDIT_ASSOCIATION_DISCUSSION_REFERENCE );
    testChangeType( "Association Discussion Reference Removed", ChangeType.DELETE_ASSOCIATION_DISCUSSION_REFERENCE );
    testChangeType( "Association Evidence Reference Added", ChangeType.ADD_ASSOCIATION_EVIDENCE_REFERENCE );
    testChangeType( "Association Evidence Reference Changed", ChangeType.EDIT_ASSOCIATION_EVIDENCE_REFERENCE );
    testChangeType( "Association Evidence Reference Removed", ChangeType.DELETE_ASSOCIATION_EVIDENCE_REFERENCE );
    testChangeType( "Association Note Added", ChangeType.ADD_ASSOCIATION_NOTE );
    testChangeType( "Association Note Changed", ChangeType.EDIT_ASSOCIATION_NOTE );
    testChangeType( "Association Note Removed", ChangeType.DELETE_ASSOCIATION_NOTE );
  }

  @Test
  void associationRowsHaveExpectedUniqueTriples() {
    // one row per association constant: constant, operation, object type, modifier (null for the lifecycle rows)
    expectedAssociationTypes = EnumSet.noneOf( ChangeType.class );
    assertTriple( ChangeType.CREATE_ASSOCIATION_RELATIONSHIP, ChangeOperation.Create, ChangeObjectType.Association, null );
    assertTriple( ChangeType.UPDATE_ASSOCIATION_RELATIONSHIP, ChangeOperation.Update, ChangeObjectType.Association, null );
    assertTriple( ChangeType.DELETE_ASSOCIATION_RELATIONSHIP, ChangeOperation.Delete, ChangeObjectType.Association, null );
    assertTriple( ChangeType.UNTOMBSTONE_ASSOCIATION_RELATIONSHIP, ChangeOperation.Restore, ChangeObjectType.Association, null );
    assertTriple( ChangeType.ADD_PERSON1, ChangeOperation.Create, ChangeObjectType.Person1, ChangeObjectModifier.Association );
    assertTriple( ChangeType.EDIT_PERSON1, ChangeOperation.Update, ChangeObjectType.Person1, ChangeObjectModifier.Association );
    assertTriple( ChangeType.ADD_PERSON2, ChangeOperation.Create, ChangeObjectType.Person2, ChangeObjectModifier.Association );
    assertTriple( ChangeType.EDIT_PERSON2, ChangeOperation.Update, ChangeObjectType.Person2, ChangeObjectModifier.Association );
    assertTriple( ChangeType.ADD_ASSOCIATION_FACT, ChangeOperation.Create, ChangeObjectType.Fact, ChangeObjectModifier.Association );
    assertTriple( ChangeType.EDIT_ASSOCIATION_FACT, ChangeOperation.Update, ChangeObjectType.Fact, ChangeObjectModifier.Association );
    assertTriple( ChangeType.DELETE_ASSOCIATION_FACT, ChangeOperation.Delete, ChangeObjectType.Fact, ChangeObjectModifier.Association );
    assertTriple( ChangeType.ADD_ASSOCIATION_SOURCE_REFERENCE, ChangeOperation.Create, ChangeObjectType.SourceReference, ChangeObjectModifier.Association );
    assertTriple( ChangeType.EDIT_ASSOCIATION_SOURCE_REFERENCE, ChangeOperation.Update, ChangeObjectType.SourceReference, ChangeObjectModifier.Association );
    assertTriple( ChangeType.DELETE_ASSOCIATION_SOURCE_REFERENCE, ChangeOperation.Delete, ChangeObjectType.SourceReference, ChangeObjectModifier.Association );
    assertTriple( ChangeType.ADD_ASSOCIATION_DISCUSSION_REFERENCE, ChangeOperation.Create, ChangeObjectType.DiscussionReference, ChangeObjectModifier.Association );
    assertTriple( ChangeType.EDIT_ASSOCIATION_DISCUSSION_REFERENCE, ChangeOperation.Update, ChangeObjectType.DiscussionReference, ChangeObjectModifier.Association );
    assertTriple( ChangeType.DELETE_ASSOCIATION_DISCUSSION_REFERENCE, ChangeOperation.Delete, ChangeObjectType.DiscussionReference, ChangeObjectModifier.Association );
    assertTriple( ChangeType.ADD_ASSOCIATION_EVIDENCE_REFERENCE, ChangeOperation.Create, ChangeObjectType.EvidenceReference, ChangeObjectModifier.Association );
    assertTriple( ChangeType.EDIT_ASSOCIATION_EVIDENCE_REFERENCE, ChangeOperation.Update, ChangeObjectType.EvidenceReference, ChangeObjectModifier.Association );
    assertTriple( ChangeType.DELETE_ASSOCIATION_EVIDENCE_REFERENCE, ChangeOperation.Delete, ChangeObjectType.EvidenceReference, ChangeObjectModifier.Association );
    assertTriple( ChangeType.ADD_ASSOCIATION_NOTE, ChangeOperation.Create, ChangeObjectType.Note, ChangeObjectModifier.Association );
    assertTriple( ChangeType.EDIT_ASSOCIATION_NOTE, ChangeOperation.Update, ChangeObjectType.Note, ChangeObjectModifier.Association );
    assertTriple( ChangeType.DELETE_ASSOCIATION_NOTE, ChangeOperation.Delete, ChangeObjectType.Note, ChangeObjectModifier.Association );

    // no two ChangeType values share an effective triple among rows that touch the new vocabulary
    // (effective modifier: a null modifier counts as the ChangeObjectModifier named like the object type, mirroring findChangeType)
    Map<String, ChangeType> seenTriples = new HashMap<>();
    Set<ChangeType> touchingNewVocabulary = EnumSet.noneOf( ChangeType.class );
    for ( ChangeType changeType : ChangeType.values() ) {
      if ( touchesAssociationVocabulary( changeType ) ) {
        touchingNewVocabulary.add( changeType );
        String triple = changeType.getOperation() + "|" + changeType.getObjectType() + "|" + effectiveModifierName( changeType );
        ChangeType previous = seenTriples.put( triple, changeType );
        assertNull( previous, changeType + " duplicates the triple of " + previous + ": " + triple );
      }
    }

    // the vocabulary is exactly the rows asserted above: nothing missing and nothing extra
    // (extend this test when adding association rows, or this assertion fails for the new ChangeType)
    assertEquals( expectedAssociationTypes, touchingNewVocabulary );
  }

  @Test
  void associationVocabularyResolvesToFamilySearchNamespace() {
    // unannotated constants land in the FamilySearch namespace; the mapper and Platform API compare these by URI
    assertEquals( URI.create( "http://familysearch.org/v1/Association" ), ChangeObjectType.Association.toQNameURI() );
    assertEquals( URI.create( "http://familysearch.org/v1/Association" ), ChangeObjectModifier.Association.toQNameURI() );
    assertEquals( URI.create( "http://familysearch.org/v1/Person1" ), ChangeObjectType.Person1.toQNameURI() );
    assertEquals( URI.create( "http://familysearch.org/v1/Person2" ), ChangeObjectType.Person2.toQNameURI() );

    // and each URI maps back to its constant
    assertEquals( ChangeObjectType.Association, ChangeObjectType.fromQNameURI( URI.create( "http://familysearch.org/v1/Association" ) ) );
    assertEquals( ChangeObjectModifier.Association, ChangeObjectModifier.fromQNameURI( URI.create( "http://familysearch.org/v1/Association" ) ) );
    assertEquals( ChangeObjectType.Person1, ChangeObjectType.fromQNameURI( URI.create( "http://familysearch.org/v1/Person1" ) ) );
    assertEquals( ChangeObjectType.Person2, ChangeObjectType.fromQNameURI( URI.create( "http://familysearch.org/v1/Person2" ) ) );
  }

  private void assertTriple( ChangeType changeType, ChangeOperation operation, ChangeObjectType objectType, ChangeObjectModifier modifier ) {
    assertEquals( operation, changeType.getOperation(), changeType + " operation" );
    assertEquals( objectType, changeType.getObjectType(), changeType + " object type" );
    assertEquals( modifier, changeType.getObjectModifier(), changeType + " object modifier" );
    expectedAssociationTypes.add( changeType );
  }

  private static boolean touchesAssociationVocabulary( ChangeType changeType ) {
    ChangeObjectType objectType = changeType.getObjectType();
    return ChangeObjectModifier.Association.name().equals( effectiveModifierName( changeType ) )
      || objectType == ChangeObjectType.Association
      || objectType == ChangeObjectType.Person1
      || objectType == ChangeObjectType.Person2;
  }

  // a null modifier matches the ChangeObjectModifier whose name equals the object type's name
  private static String effectiveModifierName( ChangeType changeType ) {
    ChangeObjectModifier modifier = changeType.getObjectModifier();
    return modifier != null ? modifier.name() : changeType.getObjectType().name();
  }

  private void testChangeType( String enumStr, ChangeType changeType ) {
    String typeString = changeType.toString();
    assertEquals( enumStr, typeString );
    typesTested.add( changeType );
  }
}
