package org.familysearch.platform.ct;

import org.testng.annotations.Test;


import static org.testng.AssertJUnit.assertEquals;

/**
 * @author Mike Gardiner
 */
public class PersonMergeConstraintTest {
  @Test
  public void testOrdinals() {
    assertEquals(36, PersonMergeConstraint.values().length);
    assertEquals(0, PersonMergeConstraint.CAN_MERGE_ANY_ORDER.ordinal());
    assertEquals(1, PersonMergeConstraint.CAN_MERGE_OTHER_ORDER_ONLY.ordinal());
    assertEquals(2, PersonMergeConstraint.CAN_MERGE_THIS_ORDER_ONLY.ordinal());
    assertEquals(3, PersonMergeConstraint.CANNOT_MERGE_ANY_ORDER_CP_CONSTRAINT.ordinal());
    assertEquals(4, PersonMergeConstraint.CANNOT_MERGE_ANY_ORDER_CP_CONSTRAINT_ALREADY_MERGED.ordinal());
    assertEquals(5, PersonMergeConstraint.CANNOT_MERGE_ANY_ORDER_CP_CONSTRAINT_AT_LEASE_TWO_UNIQUE_MERGE_CANDIDATES_REQUIRED.ordinal());
    assertEquals(6, PersonMergeConstraint.CANNOT_MERGE_ANY_ORDER_CP_CONSTRAINT_MISMATCHED_GENDERS.ordinal());
    assertEquals(7, PersonMergeConstraint.CANNOT_MERGE_ANY_ORDER_CP_CONSTRAINT_NO_EDIT_RIGHTS.ordinal());
    assertEquals(8, PersonMergeConstraint.CANNOT_MERGE_ANY_ORDER_CP_CONSTRAINT_CONTRIBUTOR_CANNOT_WRITE_TO_START_PERSON.ordinal());
    assertEquals(9, PersonMergeConstraint.CANNOT_MERGE_ANY_ORDER_CP_CONSTRAINT_CONTRIBUTOR_CANNOT_WRITE_TO_CANDIDATE_PERSON.ordinal());
    assertEquals(10, PersonMergeConstraint.CANNOT_MERGE_ANY_ORDER_CP_CONSTRAINT_CONTRIBUTOR_CANNOT_WRITE_TO_PERSON.ordinal());
    assertEquals(11, PersonMergeConstraint.CANNOT_MERGE_ANY_ORDER_CP_CONSTRAINT_CMIS_CANT_MERGE_NONCMIS_PERSONS.ordinal());
    assertEquals(12, PersonMergeConstraint.CANNOT_MERGE_ANY_ORDER_CP_CONSTRAINT_MULTIPLE_CMIS.ordinal());
    assertEquals(13, PersonMergeConstraint.CANNOT_MERGE_ANY_ORDER_CP_CONSTRAINT_MULTIPLE_CMIS_OR_USERS.ordinal());
    assertEquals(14, PersonMergeConstraint.CANNOT_MERGE_ANY_ORDER_CP_CONSTRAINT_MULTIPLE_CMIS_OR_CONTROLLED.ordinal());
    assertEquals(15, PersonMergeConstraint.CANNOT_MERGE_ANY_ORDER_CP_CONSTRAINT_NO_CMIS_EDIT_RIGHTS.ordinal());
    assertEquals(16, PersonMergeConstraint.CANNOT_MERGE_ANY_ORDER_CP_CONSTRAINT_START_PERSON_HAS_NO_CMIS_EDIT_RIGHTS.ordinal());
    assertEquals(17, PersonMergeConstraint.CANNOT_MERGE_ANY_ORDER_CP_CONSTRAINT_MERGING_WOULD_HIDE_STUFF.ordinal());
    assertEquals(18, PersonMergeConstraint.CANNOT_MERGE_ANY_ORDER_CP_CONSTRAINT_MERGING_WOULD_HIDE_STUFF_ON_CMIS_RECORDS.ordinal());
    assertEquals(19, PersonMergeConstraint.CANNOT_MERGE_ANY_ORDER_CP_CONSTRAINT_MERGING_WOULD_CAUSE_LOOP.ordinal());
    assertEquals(20, PersonMergeConstraint.CANNOT_MERGE_ANY_ORDER_CP_CONSTRAINT_PRIMARY_MERGE_CANDIDATE_CANNOT_BE_MERGED.ordinal());
    assertEquals(21, PersonMergeConstraint.CANNOT_MERGE_ANY_ORDER_CP_CONSTRAINT_ALL_OTHER_MERGE_CANDIDATES_CANNOT_BE_MERGED.ordinal());
    assertEquals(22, PersonMergeConstraint.CANNOT_MERGE_ANY_ORDER_CP_CONSTRAINT_TOO_MANY_INNER_PERSONS.ordinal());
    assertEquals(23, PersonMergeConstraint.CANNOT_MERGE_ANY_ORDER_CP_CONSTRAINT_MERGE_START_PERSON_NOT_FOUND.ordinal());
    assertEquals(24, PersonMergeConstraint.CANNOT_MERGE_ANY_ORDER_CP_CONSTRAINT_MERGE_CANDIDATE_NOT_FOUND.ordinal());
    assertEquals(25, PersonMergeConstraint.CANNOT_MERGE_ANY_ORDER_GENDER_MISMATCH.ordinal());
    assertEquals(26, PersonMergeConstraint.CANNOT_MERGE_ANY_ORDER_LIVING_CONTRIBUTOR_MISMATCH.ordinal());
    assertEquals(27, PersonMergeConstraint.CANNOT_MERGE_ANY_ORDER_LIVING_STATUS_MISMATCH.ordinal());
    assertEquals(28, PersonMergeConstraint.CANNOT_MERGE_ANY_ORDER_PARENT_AND_CHILD.ordinal());
    assertEquals(29, PersonMergeConstraint.CANNOT_MERGE_ANY_ORDER_NON_UNIQUE_IDS.ordinal());
  }
}
