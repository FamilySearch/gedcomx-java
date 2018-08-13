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
package org.familysearch.platform.ct;

import javax.xml.bind.annotation.XmlTransient;

/**
 * @author Mike Gardiner
 */
@XmlTransient
public enum PersonMergeConstraint {
  CAN_MERGE_ANY_ORDER(447, "Can merge regardless of order"),
  CAN_MERGE_OTHER_ORDER_ONLY(448, "Can merge, but must switch order"),
  CAN_MERGE_THIS_ORDER_ONLY(449, "Can merge in this order only"),

//  CANNOT_MERGE_ANY_ORDER_CP_CONSTRAINT(450, "No longer used - Cannot merge any order (CP constraint)"),
//  CANNOT_MERGE_ANY_ORDER_CP_CONSTRAINT_ALREADY_MERGED(451,"No longer used - Cannot merge any order (CP constraint - already merged)"),
//  CANNOT_MERGE_ANY_ORDER_CP_CONSTRAINT_AT_LEASE_TWO_UNIQUE_MERGE_CANDIDATES_REQUIRED(452,"No longer used - Cannot merge any order (CP constraint - at least two unique merge candidates required)"),
//  CANNOT_MERGE_ANY_ORDER_CP_CONSTRAINT_MISMATCHED_GENDERS(453,"No longer used - Cannot merge any order (CP constraint - mismatched genders)"),
//  CANNOT_MERGE_ANY_ORDER_CP_CONSTRAINT_NO_EDIT_RIGHTS(454,"No longer used - Cannot merge any order (CP constraint - no edit rights)"),
//  CANNOT_MERGE_ANY_ORDER_CP_CONSTRAINT_CONTRIBUTOR_CANNOT_WRITE_TO_START_PERSON(455,"No longer used - Cannot merge any order (CP constraint - contributor cannot write to start person)"),
//  CANNOT_MERGE_ANY_ORDER_CP_CONSTRAINT_CONTRIBUTOR_CANNOT_WRITE_TO_CANDIDATE_PERSON(456,"No longer used - Cannot merge any order (CP constraint - contributor cannot write to candidate person)"),
//  CANNOT_MERGE_ANY_ORDER_CP_CONSTRAINT_CONTRIBUTOR_CANNOT_WRITE_TO_PERSON(457,"No longer used - Cannot merge any order (CP constraint - contributor cannot write to person)"),
//  CANNOT_MERGE_ANY_ORDER_CP_CONSTRAINT_CMIS_CANT_MERGE_NONCMIS_PERSONS(458,"No longer used - Cannot merge any order (CP constraint - cmis cannot merge non cmis persons)"),
//  CANNOT_MERGE_ANY_ORDER_CP_CONSTRAINT_MULTIPLE_CMIS(459,"No longer used - Cannot merge any order (CP constraint - multiple cmis)"),
//  CANNOT_MERGE_ANY_ORDER_CP_CONSTRAINT_MULTIPLE_CMIS_OR_USERS(460,"No longer used - Cannot merge any order (CP constraint - multiple cmis or users)"),
//  CANNOT_MERGE_ANY_ORDER_CP_CONSTRAINT_MULTIPLE_CMIS_OR_CONTROLLED(461,"No longer used - Cannot merge any order (CP constraint - multiple cmis or controlled)"),
//  CANNOT_MERGE_ANY_ORDER_CP_CONSTRAINT_NO_CMIS_EDIT_RIGHTS(462,"No longer used - Cannot merge any order (CP constraint - no cmis edit rights)"),
//  CANNOT_MERGE_ANY_ORDER_CP_CONSTRAINT_START_PERSON_HAS_NO_CMIS_EDIT_RIGHTS(463,"No longer used - Cannot merge any order (CP constraint - start person has no cmis edit rights)"),
//  CANNOT_MERGE_ANY_ORDER_CP_CONSTRAINT_MERGING_WOULD_HIDE_STUFF(464,"No longer used - Cannot merge any order (CP constraint - merging would hide stuff)"),
//  CANNOT_MERGE_ANY_ORDER_CP_CONSTRAINT_MERGING_WOULD_HIDE_STUFF_ON_CMIS_RECORDS(465,"No longer used - Cannot merge any order (CP constraint - merging would hide stuff on cmis records)"),
//  CANNOT_MERGE_ANY_ORDER_CP_CONSTRAINT_MERGING_WOULD_CAUSE_LOOP(466,"No longer used - Cannot merge any order (CP constraint - merging would cause loop)"),
//  CANNOT_MERGE_ANY_ORDER_CP_CONSTRAINT_PRIMARY_MERGE_CANDIDATE_CANNOT_BE_MERGED(467,"No longer used - Cannot merge any order (CP constraint - primary merge candidate cannot be merged)"),
//  CANNOT_MERGE_ANY_ORDER_CP_CONSTRAINT_ALL_OTHER_MERGE_CANDIDATES_CANNOT_BE_MERGED(468,"No longer used - Cannot merge any order (CP constraint - all other merge candidates cannot be merged)"),
//  CANNOT_MERGE_ANY_ORDER_CP_CONSTRAINT_TOO_MANY_INNER_PERSONS(469,"No longer used - Cannot merge any order (CP constraint - too many inner persons)"),
//  CANNOT_MERGE_ANY_ORDER_CP_CONSTRAINT_MERGE_START_PERSON_NOT_FOUND(470,"No longer used - Cannot merge any order (CP constraint - merge start person not found)"),
//  CANNOT_MERGE_ANY_ORDER_CP_CONSTRAINT_MERGE_CANDIDATE_NOT_FOUND(471,"No longer used - Cannot merge any order (CP constraint - merge candidate not found)"),
  CANNOT_MERGE_ANY_ORDER_GENDER_MISMATCH(472, "Genders must be equal when merging persons"),
//  CANNOT_MERGE_ANY_ORDER_LIVING_CONTRIBUTOR_MISMATCH(473,"No longer used"),
  CANNOT_MERGE_ANY_ORDER_LIVING_STATUS_MISMATCH(474, "Living status must be equal when merging persons"),
  CANNOT_MERGE_ANY_ORDER_PARENT_AND_CHILD(475, "Cannot merge a parent-child into a single person"),
  CANNOT_MERGE_ANY_ORDER_NON_UNIQUE_IDS(476, "Must have two unique persons to merge"),
  CANNOT_MERGE_ANY_ORDER_TOMBSTONED_PERSON(477, "Neither person being merged can be tombstoned"),
  CANNOT_MERGE_ANY_ORDER_FORWARDED_PERSON(478, "Neither person being merged can forward to another person"),
  CANNOT_MERGE_ANY_ORDER_PERSON_NOT_FOUND(479, "Both persons being merged must be found in the system"),
  CANNOT_MERGE_ANY_ORDER_LOCKED_PERSON(480, "Neither person being merged can be locked"),
  CANNOT_MERGE_ANY_ORDER_LOCKED_RELATIONSHIP(481, "Neither person being merged can be in a locked relationship"),
  CANNOT_MERGE_ANY_ORDER_DIFFERENT_SPACES(482, "Both persons being merged must exist in the same space"),
  CANNOT_MERGE_ANY_ORDER_HIDDEN_PERSON(483, "Neither person being merged can be hidden"),
  CANNOT_MERGE_ANY_ORDER_RESTRICTED_PERSON(484, "Neither person being merged can be restricted"),

  UNKNOWN_MERGE_CONSTRAINT(499, "Unknown merge constraint");

  final private int statusCode;
  final private String defaultDescription;

  /**
   * Constructor.
   *
   * @param statusCode The globally-recognized number code identifying the merge constraint. This is a leftover from CT
   * (and possibly CP).
   * @param defaultDescription The default description of the merge constraint.
   */
  PersonMergeConstraint(int statusCode, String defaultDescription) {
    this.statusCode = statusCode;
    this.defaultDescription = defaultDescription;
  }

  /**
   * Get the code identifying the merge constraint.
   *
   * @return The code identifying the merge constraint.
   */
  public int getStatusCode() {
    return statusCode;
  }

  /**
   * Get the default description of the merge constraint.
   *
   * @return The default description of the merge constraint.
   */
  public String getDefaultDescription() {
    return defaultDescription;
  }

  /**
   * Get whether or not this enum indicates the persons can be merged.
   *
   * @return True if the persons can be merged; false otherwise.
   */
  public boolean canMerge() {
    return statusCode < 450;
  }

}

