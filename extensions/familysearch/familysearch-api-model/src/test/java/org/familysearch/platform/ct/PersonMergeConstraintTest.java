package org.familysearch.platform.ct;

import org.testng.annotations.Test;


import static org.testng.AssertJUnit.assertEquals;

/**
 * @author Mike Gardiner
 */
public class PersonMergeConstraintTest {
  @Test
  public void testOrdinals() {
    assertEquals(16, PersonMergeConstraint.values().length);

    assertEquals(447, PersonMergeConstraint.CAN_MERGE_ANY_ORDER.getStatusCode());
    assertEquals(448, PersonMergeConstraint.CAN_MERGE_OTHER_ORDER_ONLY.getStatusCode());
    assertEquals(449, PersonMergeConstraint.CAN_MERGE_THIS_ORDER_ONLY.getStatusCode());

    assertEquals(472, PersonMergeConstraint.CANNOT_MERGE_ANY_ORDER_GENDER_MISMATCH.getStatusCode());
    assertEquals(474, PersonMergeConstraint.CANNOT_MERGE_ANY_ORDER_LIVING_STATUS_MISMATCH.getStatusCode());
    assertEquals(475, PersonMergeConstraint.CANNOT_MERGE_ANY_ORDER_PARENT_AND_CHILD.getStatusCode());
    assertEquals(476, PersonMergeConstraint.CANNOT_MERGE_ANY_ORDER_NON_UNIQUE_IDS.getStatusCode());
    assertEquals(477, PersonMergeConstraint.CANNOT_MERGE_ANY_ORDER_TOMBSTONED_PERSON.getStatusCode());
    assertEquals(478, PersonMergeConstraint.CANNOT_MERGE_ANY_ORDER_FORWARDED_PERSON.getStatusCode());
    assertEquals(479, PersonMergeConstraint.CANNOT_MERGE_ANY_ORDER_PERSON_NOT_FOUND.getStatusCode());
    assertEquals(480, PersonMergeConstraint.CANNOT_MERGE_ANY_ORDER_LOCKED_PERSON.getStatusCode());
    assertEquals(481, PersonMergeConstraint.CANNOT_MERGE_ANY_ORDER_LOCKED_RELATIONSHIP.getStatusCode());
    assertEquals(482, PersonMergeConstraint.CANNOT_MERGE_ANY_ORDER_DIFFERENT_SPACES.getStatusCode());
    assertEquals(483, PersonMergeConstraint.CANNOT_MERGE_ANY_ORDER_HIDDEN_PERSON.getStatusCode());
    assertEquals(484, PersonMergeConstraint.CANNOT_MERGE_ANY_ORDER_RESTRICTED_PERSON.getStatusCode());

    assertEquals(499, PersonMergeConstraint.UNKNOWN_MERGE_CONSTRAINT.getStatusCode());
  }

}
