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
 * Enumeration of change actions.
 *
 * @author Cody Hawkins
 */
@XmlTransient
public enum ChangeType {

  CREATE_PERSON("Person Created", ChangeOperation.Create, ChangeObjectType.Person, null),
  DELETE_PERSON("Person Removed", ChangeOperation.Delete, ChangeObjectType.Person, null),
  MERGE_PERSON("Person Merged", ChangeOperation.Merge, ChangeObjectType.Person, null),
  UNMERGE_PERSON("Person Unmerged", ChangeOperation.Unmerge, ChangeObjectType.Person, null),
  UNTOMBSTONE_PERSON("Person Restored", ChangeOperation.Restore, ChangeObjectType.Person, null),
  ADD_PERSON_NOT_A_MATCH("Set Person Not a Match", ChangeOperation.Create, ChangeObjectType.NotAMatch, ChangeObjectModifier.Person),
  REMOVE_PERSON_NOT_A_MATCH("Remove Person Not a Match", ChangeOperation.Delete, ChangeObjectType.NotAMatch, ChangeObjectModifier.Person),
  CREATE_COUPLE_RELATIONSHIP("Couple Relationship Created", ChangeOperation.Create, ChangeObjectType.Couple, null),
  UPDATE_COUPLE_RELATIONSHIP("Couple Relationship Updated", ChangeOperation.Update, ChangeObjectType.Couple, null),
  DELETE_COUPLE_RELATIONSHIP("Couple Relationship Removed", ChangeOperation.Delete, ChangeObjectType.Couple, null),


  ADD_SPOUSE1("Spouse1 Added", ChangeOperation.Create, ChangeObjectType.Spouse1, ChangeObjectModifier.Couple),
  EDIT_SPOUSE1("Spouse1 Changed", ChangeOperation.Update, ChangeObjectType.Spouse1, ChangeObjectModifier.Couple),
  ADD_SPOUSE2("Spouse2 Added", ChangeOperation.Create, ChangeObjectType.Spouse2, ChangeObjectModifier.Couple),
  EDIT_SPOUSE2("Spouse2 Changed", ChangeOperation.Update, ChangeObjectType.Spouse2, ChangeObjectModifier.Couple),


  MERGE_COUPLE_RELATIONSHIP("Couple Relationship Merged", ChangeOperation.Merge, ChangeObjectType.Couple, null),
  UNMERGE_COUPLE_RELATIONSHIP("Couple Relationship Unmerged", ChangeOperation.Unmerge, ChangeObjectType.Couple, null),
  UNTOMBSTONE_COUPLE_RELATIONSHIP("Couple Relationship Restored", ChangeOperation.Restore, ChangeObjectType.Couple, null),
  CREATE_CHILD_AND_PARENTS_RELATIONSHIP("Child and Parents Relationship Created", ChangeOperation.Create, ChangeObjectType.ChildAndParentsRelationship, null),
  UPDATE_CHILD_AND_PARENTS_RELATIONSHIP("Child and Parents Relationship Updated", ChangeOperation.Update, ChangeObjectType.ChildAndParentsRelationship, null),
  DELETE_CHILD_AND_PARENTS_RELATIONSHIP("Child and Parents Relationship Removed", ChangeOperation.Delete, ChangeObjectType.ChildAndParentsRelationship, null),


  ADD_PARENT1("Parent1 Added", ChangeOperation.Create, ChangeObjectType.Parent1, ChangeObjectModifier.ChildAndParentsRelationship),
  EDIT_PARENT1("Parent1 Changed", ChangeOperation.Update, ChangeObjectType.Parent1, ChangeObjectModifier.ChildAndParentsRelationship),
  REMOVE_PARENT1("Parent1 Removed", ChangeOperation.Delete, ChangeObjectType.Parent1, ChangeObjectModifier.ChildAndParentsRelationship),
  ADD_PARENT2("Parent2 Added", ChangeOperation.Create, ChangeObjectType.Parent2, ChangeObjectModifier.ChildAndParentsRelationship),
  EDIT_PARENT2("Parent2 Changed", ChangeOperation.Update, ChangeObjectType.Parent2, ChangeObjectModifier.ChildAndParentsRelationship),
  REMOVE_PARENT2("Parent2 Removed", ChangeOperation.Delete, ChangeObjectType.Parent2, ChangeObjectModifier.ChildAndParentsRelationship),

  ADD_CHILD("Child Added", ChangeOperation.Create, ChangeObjectType.Child, ChangeObjectModifier.ChildAndParentsRelationship),
  EDIT_CHILD("Child Changed", ChangeOperation.Update, ChangeObjectType.Child, ChangeObjectModifier.ChildAndParentsRelationship),
  MERGE_CHILD_AND_PARENTS_RELATIONSHIP("Child and Parents Relationship Merged", ChangeOperation.Merge, ChangeObjectType.ChildAndParentsRelationship, null),
  UNMERGE_CHILD_AND_PARENTS_RELATIONSHIP("Child and Parents Relationship Unmerged", ChangeOperation.Unmerge, ChangeObjectType.ChildAndParentsRelationship, null),
  UNTOMBSTONE_CHILD_AND_PARENTS_RELATIONSHIP("Child and Parents Relationship Restored", ChangeOperation.Restore, ChangeObjectType.ChildAndParentsRelationship, null),

  /* Not currently supported
  EDIT_PERSON_ACCESS_CONTROL("PersonAccessControl Changed", , ),
  EDIT_COUPLE_RELATIONSHIP_ACCESS_CONTROL("Couple Relationship AccessControl Changed", , ),
  EDIT_PARENT_CHILD_RELATIONSHIP_ACCESS_CONTROL("Parent-Child Relationship AccessControl Changed", , ),
  DELETE_CHANGE("Change Removed", , ),
  SET_ADMIN_LABEL("Admin Label Set", , ),
  CLEAR_ADMIN_LABEL("Admin Label Cleared", , ),
  SET_NOT_A_MATCH_DECLARATION("Not A Match Declaration Set", , ),
  CLEAR_NOT_A_MATCH_DECLARATION("Not A Match Declaration Cleared", , ),
  */

  // SOURCE REFERENCE
  ADD_PERSON_SOURCE_REFERENCE("Person Source Reference Added", ChangeOperation.Create, ChangeObjectType.SourceReference, ChangeObjectModifier.Person),
  EDIT_PERSON_SOURCE_REFERENCE("Person Source Reference Changed", ChangeOperation.Update, ChangeObjectType.SourceReference, ChangeObjectModifier.Person),
  DELETE_PERSON_SOURCE_REFERENCE("Person Source Reference Removed", ChangeOperation.Delete, ChangeObjectType.SourceReference, ChangeObjectModifier.Person),
  ADD_COUPLE_SOURCE_REFERENCE("Couple Source Reference Added", ChangeOperation.Create, ChangeObjectType.SourceReference, ChangeObjectModifier.Couple),
  EDIT_COUPLE_SOURCE_REFERENCE("Couple Source Reference Changed", ChangeOperation.Update, ChangeObjectType.SourceReference, ChangeObjectModifier.Couple),
  DELETE_COUPLE_SOURCE_REFERENCE("Couple Source Reference Removed", ChangeOperation.Delete, ChangeObjectType.SourceReference, ChangeObjectModifier.Couple),
  ADD_CHILD_PARENTS_SOURCE_REFERENCE("Child and Parents Source Reference Added", ChangeOperation.Create, ChangeObjectType.SourceReference, ChangeObjectModifier.ChildAndParentsRelationship),
  EDIT_CHILD_PARENTS_SOURCE_REFERENCE("Child and Parents Source Reference Changed", ChangeOperation.Update, ChangeObjectType.SourceReference, ChangeObjectModifier.ChildAndParentsRelationship),
  DELETE_CHILD_PARENTS_SOURCE_REFERENCE("Child and Parents Source Reference Removed", ChangeOperation.Delete, ChangeObjectType.SourceReference, ChangeObjectModifier.ChildAndParentsRelationship),

  // DISCUSSION REFERENCE
  ADD_PERSON_DISCUSSION_REFERENCE("Person Discussion Reference Added", ChangeOperation.Create, ChangeObjectType.DiscussionReference, ChangeObjectModifier.Person),
  EDIT_PERSON_DISCUSSION_REFERENCE("Person Discussion Reference Changed", ChangeOperation.Update, ChangeObjectType.DiscussionReference, ChangeObjectModifier.Person),
  DELETE_PERSON_DISCUSSION_REFERENCE("Person Discussion Reference Removed", ChangeOperation.Delete, ChangeObjectType.DiscussionReference, ChangeObjectModifier.Person),
  ADD_COUPLE_DISCUSSION_REFERENCE("Couple Discussion Reference Added", ChangeOperation.Create, ChangeObjectType.DiscussionReference, ChangeObjectModifier.Couple),
  EDIT_COUPLE_DISCUSSION_REFERENCE("Couple Discussion Reference Changed", ChangeOperation.Update, ChangeObjectType.DiscussionReference, ChangeObjectModifier.Couple),
  DELETE_COUPLE_DISCUSSION_REFERENCE("Couple Discussion Reference Removed", ChangeOperation.Delete, ChangeObjectType.DiscussionReference, ChangeObjectModifier.Couple),
  ADD_CHILD_PARENTS_DISCUSSION_REFERENCE("Child and Parents Discussion Reference Added", ChangeOperation.Create, ChangeObjectType.DiscussionReference, ChangeObjectModifier.ChildAndParentsRelationship),
  EDIT_CHILD_PARENTS_DISCUSSION_REFERENCE("Child and Parents Discussion Reference Changed", ChangeOperation.Update, ChangeObjectType.DiscussionReference, ChangeObjectModifier.ChildAndParentsRelationship),
  DELETE_CHILD_PARENTS_DISCUSSION_REFERENCE("Child and Parents Discussion Reference Removed", ChangeOperation.Delete, ChangeObjectType.DiscussionReference, ChangeObjectModifier.ChildAndParentsRelationship),

  // EVIDENCE REFERENCE
  ADD_PERSON_EVIDENCE_REFERENCE("Person Evidence Reference Added", ChangeOperation.Create, ChangeObjectType.EvidenceReference, ChangeObjectModifier.Person),
  EDIT_PERSON_EVIDENCE_REFERENCE("Person Evidence Reference Changed", ChangeOperation.Update, ChangeObjectType.EvidenceReference, ChangeObjectModifier.Person),
  DELETE_PERSON_EVIDENCE_REFERENCE("Person Evidence Reference Removed", ChangeOperation.Delete, ChangeObjectType.EvidenceReference, ChangeObjectModifier.Person),
  ADD_COUPLE_EVIDENCE_REFERENCE("Couple Evidence Reference Added", ChangeOperation.Create, ChangeObjectType.EvidenceReference, ChangeObjectModifier.Couple),
  EDIT_COUPLE_EVIDENCE_REFERENCE("Couple Evidence Reference Changed", ChangeOperation.Update, ChangeObjectType.EvidenceReference, ChangeObjectModifier.Couple),
  DELETE_COUPLE_EVIDENCE_REFERENCE("Couple Evidence Reference Removed", ChangeOperation.Delete, ChangeObjectType.EvidenceReference, ChangeObjectModifier.Couple),
  ADD_CHILD_PARENTS_EVIDENCE_REFERENCE("Child and Parents Evidence Reference Added", ChangeOperation.Create, ChangeObjectType.EvidenceReference, ChangeObjectModifier.ChildAndParentsRelationship),
  EDIT_CHILD_PARENTS_EVIDENCE_REFERENCE("Child and Parents Evidence Reference Changed", ChangeOperation.Update, ChangeObjectType.EvidenceReference, ChangeObjectModifier.ChildAndParentsRelationship),
  DELETE_CHILD_PARENTS_EVIDENCE_REFERENCE("Child and Parents Evidence Reference Removed", ChangeOperation.Delete, ChangeObjectType.EvidenceReference, ChangeObjectModifier.ChildAndParentsRelationship),

  /* Conclusion changes */
  // EVENT
  ADD_AFFILIATION("Affiliation Added", ChangeOperation.Create, ChangeObjectType.Affiliation, ChangeObjectModifier.Person),
  EDIT_AFFILIATION("Affiliation Changed", ChangeOperation.Update, ChangeObjectType.Affiliation, ChangeObjectModifier.Person),
  DELETE_AFFILIATION("Affiliation Removed", ChangeOperation.Delete, ChangeObjectType.Affiliation, ChangeObjectModifier.Person),
  ADD_ANNULMENT("Annulment Added", ChangeOperation.Create, ChangeObjectType.Annulment, ChangeObjectModifier.Couple),
  EDIT_ANNULMENT("Annulment Changed", ChangeOperation.Update, ChangeObjectType.Annulment, ChangeObjectModifier.Couple),
  DELETE_ANNULMENT("Annulment Removed", ChangeOperation.Delete, ChangeObjectType.Annulment, ChangeObjectModifier.Couple),
  ADD_BAR_MITZVAH("Bar Mitzvah Added", ChangeOperation.Create, ChangeObjectType.BarMitzvah, ChangeObjectModifier.Person),
  EDIT_BAR_MITZVAH("Bar Mitzvah Changed", ChangeOperation.Update, ChangeObjectType.BarMitzvah, ChangeObjectModifier.Person),
  DELETE_BAR_MITZVAH("Bar Mitzvah Removed", ChangeOperation.Delete, ChangeObjectType.BarMitzvah, ChangeObjectModifier.Person),
  ADD_BAS_MITZVAH("Bat Mitzvah Added", ChangeOperation.Create, ChangeObjectType.BatMitzvah, ChangeObjectModifier.Person),
  EDIT_BAS_MITZVAH("Bat Mitzvah Changed", ChangeOperation.Update, ChangeObjectType.BatMitzvah, ChangeObjectModifier.Person),
  DELETE_BAS_MITZVAH("Bat Mitzvah Removed", ChangeOperation.Delete, ChangeObjectType.BatMitzvah, ChangeObjectModifier.Person),
  ADD_BIRTH("Birth Added", ChangeOperation.Create, ChangeObjectType.Birth, ChangeObjectModifier.Person),
  EDIT_BIRTH("Birth Changed", ChangeOperation.Update, ChangeObjectType.Birth, ChangeObjectModifier.Person),
  DELETE_BIRTH("Birth Removed", ChangeOperation.Delete, ChangeObjectType.Birth, ChangeObjectModifier.Person),
  ADD_BURIAL("Burial Added", ChangeOperation.Create, ChangeObjectType.Burial, ChangeObjectModifier.Person),
  EDIT_BURIAL("Burial Changed", ChangeOperation.Update, ChangeObjectType.Burial, ChangeObjectModifier.Person),
  DELETE_BURIAL("Burial Removed", ChangeOperation.Delete, ChangeObjectType.Burial, ChangeObjectModifier.Person),
  ADD_CHRISTENING("Christening Added", ChangeOperation.Create, ChangeObjectType.Christening, ChangeObjectModifier.Person),
  EDIT_CHRISTENING("Christening Changed", ChangeOperation.Update, ChangeObjectType.Christening, ChangeObjectModifier.Person),
  DELETE_CHRISTENING("Christening Removed", ChangeOperation.Delete, ChangeObjectType.Christening, ChangeObjectModifier.Person),
  ADD_COMMON_LAW_MARRIAGE("Common Law Marriage Added", ChangeOperation.Create, ChangeObjectType.CommonLawMarriage, ChangeObjectModifier.Couple),
  EDIT_COMMON_LAW_MARRIAGE("Common Law Marriage Changed", ChangeOperation.Update, ChangeObjectType.CommonLawMarriage, ChangeObjectModifier.Couple),
  DELETE_COMMON_LAW_MARRIAGE("Common Law Marriage Removed", ChangeOperation.Delete, ChangeObjectType.CommonLawMarriage, ChangeObjectModifier.Couple),
  ADD_CREMATION("Cremation Added", ChangeOperation.Create, ChangeObjectType.Cremation, ChangeObjectModifier.Person),
  EDIT_CREMATION("Cremation Changed", ChangeOperation.Update, ChangeObjectType.Cremation, ChangeObjectModifier.Person),
  DELETE_CREMATION("Cremation Removed", ChangeOperation.Delete, ChangeObjectType.Cremation, ChangeObjectModifier.Person),
  ADD_DEATH("Death Added", ChangeOperation.Create, ChangeObjectType.Death, ChangeObjectModifier.Person),
  EDIT_DEATH("Death Changed", ChangeOperation.Update, ChangeObjectType.Death, ChangeObjectModifier.Person),
  DELETE_DEATH("Death Removed", ChangeOperation.Delete, ChangeObjectType.Death, ChangeObjectModifier.Person),
  ADD_LIVING_STATUS("Living Status Set", ChangeOperation.Create, ChangeObjectType.LivingStatus, ChangeObjectModifier.Person),
  EDIT_LIVING_STATUS("Living Status Changed", ChangeOperation.Update, ChangeObjectType.LivingStatus, ChangeObjectModifier.Person),
  DELETE_LIVING_STATUS("Living Status Removed", ChangeOperation.Delete, ChangeObjectType.LivingStatus, ChangeObjectModifier.Person),
  ADD_DIVORCE("Divorce Added", ChangeOperation.Create, ChangeObjectType.Divorce, ChangeObjectModifier.Couple),
  EDIT_DIVORCE("Divorce Changed", ChangeOperation.Update, ChangeObjectType.Divorce, ChangeObjectModifier.Couple),
  DELETE_DIVORCE("Divorce Removed", ChangeOperation.Delete, ChangeObjectType.Divorce, ChangeObjectModifier.Couple),
  ADD_MARRIAGE("Marriage Added", ChangeOperation.Create, ChangeObjectType.Marriage, ChangeObjectModifier.Couple),
  EDIT_MARRIAGE("Marriage Changed", ChangeOperation.Update, ChangeObjectType.Marriage, ChangeObjectModifier.Couple),
  DELETE_MARRIAGE("Marriage Removed", ChangeOperation.Delete, ChangeObjectType.Marriage, ChangeObjectModifier.Couple),
  ADD_MILITARY_SERVICE("Military Service Added", ChangeOperation.Create, ChangeObjectType.MilitaryService, ChangeObjectModifier.Person),
  EDIT_MILITARY_SERVICE("Military Service Changed", ChangeOperation.Update, ChangeObjectType.MilitaryService, ChangeObjectModifier.Person),
  DELETE_MILITARY_SERVICE("Military Service Removed", ChangeOperation.Delete, ChangeObjectType.MilitaryService, ChangeObjectModifier.Person),
  ADD_NATURALIZATION("Naturalization Added", ChangeOperation.Create, ChangeObjectType.Naturalization, ChangeObjectModifier.Person),
  EDIT_NATURALIZATION("Naturalization Changed", ChangeOperation.Update, ChangeObjectType.Naturalization, ChangeObjectModifier.Person),
  DELETE_NATURALIZATION("Naturalization Removed", ChangeOperation.Delete, ChangeObjectType.Naturalization, ChangeObjectModifier.Person),
  ADD_NOBILITY_TYPE("Title of Nobility Added", ChangeOperation.Create, ChangeObjectType.TitleOfNobility, ChangeObjectModifier.Person),
  EDIT_NOBILITY_TYPE("Title of Nobility Changed", ChangeOperation.Update, ChangeObjectType.TitleOfNobility, ChangeObjectModifier.Person),
  DELETE_NOBILITY_TYPE("Title of Nobility Removed", ChangeOperation.Update, ChangeObjectType.TitleOfNobility, ChangeObjectModifier.Person),
  ADD_OCCUPATION("Occupation Added", ChangeOperation.Create, ChangeObjectType.Occupation, ChangeObjectModifier.Person),
  EDIT_OCCUPATION("Occupation Changed", ChangeOperation.Update, ChangeObjectType.Occupation, ChangeObjectModifier.Person),
  DELETE_OCCUPATION("Occupation Removed", ChangeOperation.Delete, ChangeObjectType.Occupation, ChangeObjectModifier.Person),
  ADD_RELIGIOUS_AFFILIATION("Religion Added", ChangeOperation.Create, ChangeObjectType.Religion, ChangeObjectModifier.Person),
  EDIT_RELIGIOUS_AFFILIATION("Religion Changed", ChangeOperation.Update, ChangeObjectType.Religion, ChangeObjectModifier.Person),
  DELETE_RELIGIOUS_AFFILIATION("Religion Removed", ChangeOperation.Delete, ChangeObjectType.Religion, ChangeObjectModifier.Person),
  ADD_RESIDENCE("Residence Added", ChangeOperation.Create, ChangeObjectType.Residence, ChangeObjectModifier.Person),
  EDIT_RESIDENCE("Residence Changed", ChangeOperation.Update, ChangeObjectType.Residence, ChangeObjectModifier.Person),
  DELETE_RESIDENCE("Residence Removed", ChangeOperation.Delete, ChangeObjectType.Residence, ChangeObjectModifier.Person),
  ADD_STILLBORN("Stillbirth Added", ChangeOperation.Create, ChangeObjectType.Stillbirth, ChangeObjectModifier.Person),
  EDIT_STILLBORN("Stillbirth Changed", ChangeOperation.Update, ChangeObjectType.Stillbirth, ChangeObjectModifier.Person),
  DELETE_STILLBORN("Stillbirth Removed", ChangeOperation.Delete, ChangeObjectType.Stillbirth, ChangeObjectModifier.Person),
  ADD_COUPLE_EVENT("Couple Event Added", ChangeOperation.Create, ChangeObjectType.Fact, ChangeObjectModifier.Couple),
  EDIT_COUPLE_EVENT("Couple Event Changed", ChangeOperation.Update, ChangeObjectType.Fact, ChangeObjectModifier.Couple),
  DELETE_COUPLE_EVENT("Couple Event Removed", ChangeOperation.Delete, ChangeObjectType.Fact, ChangeObjectModifier.Couple),
  //Todo: Do changes of type "Other Event" exist?
  ADD_OTHER_EVENT("Other Event Added", ChangeOperation.Create, ChangeObjectType.Fact, ChangeObjectModifier.Person),
  EDIT_OTHER_EVENT("Other Event Changed", ChangeOperation.Update, ChangeObjectType.Fact, ChangeObjectModifier.Person),
  DELETE_OTHER_EVENT("Other Event Removed", ChangeOperation.Delete, ChangeObjectType.Fact, ChangeObjectModifier.Person),

  // FACT
  ADD_CASTE_NAME("Caste Added", ChangeOperation.Create, ChangeObjectType.Caste, ChangeObjectModifier.Person),
  EDIT_CASTE_NAME("Caste Changed", ChangeOperation.Update, ChangeObjectType.Caste, ChangeObjectModifier.Person),
  DELETE_CASTE_NAME("Caste Removed", ChangeOperation.Delete, ChangeObjectType.Caste, ChangeObjectModifier.Person),
  ADD_CLAN_NAME("Clan Added", ChangeOperation.Create, ChangeObjectType.Clan, ChangeObjectModifier.Person),
  EDIT_CLAN_NAME("Clan Changed", ChangeOperation.Update, ChangeObjectType.Clan, ChangeObjectModifier.Person),
  DELETE_CLAN_NAME("Clan Removed", ChangeOperation.Delete, ChangeObjectType.Clan, ChangeObjectModifier.Person),
  ADD_DIED_BEFORE_EIGHT("Died Before Eight Added", ChangeOperation.Create, ChangeObjectType.DiedBeforeEight, ChangeObjectModifier.Person),
  EDIT_DIED_BEFORE_EIGHT("Died Before Eight Changed", ChangeOperation.Update, ChangeObjectType.DiedBeforeEight, ChangeObjectModifier.Person),
  DELETE_DIED_BEFORE_EIGHT("Died Before Eight Removed", ChangeOperation.Delete, ChangeObjectType.DiedBeforeEight, ChangeObjectModifier.Person),
  ADD_LIFE_SKETCH("Life Sketch Added", ChangeOperation.Create, ChangeObjectType.LifeSketch, ChangeObjectModifier.Person),
  EDIT_LIFE_SKETCH("Life Sketch Changed", ChangeOperation.Update, ChangeObjectType.LifeSketch, ChangeObjectModifier.Person),
  DELETE_LIFE_SKETCH("Life Sketch Removed", ChangeOperation.Delete, ChangeObjectType.LifeSketch, ChangeObjectModifier.Person),
  ADD_NATIONAL_ID("National Id Added", ChangeOperation.Create, ChangeObjectType.NationalId, ChangeObjectModifier.Person),
  EDIT_NATIONAL_ID("National Id Changed", ChangeOperation.Update, ChangeObjectType.NationalId, ChangeObjectModifier.Person),
  DELETE_NATIONAL_ID("National Id Removed", ChangeOperation.Delete, ChangeObjectType.NationalId, ChangeObjectModifier.Person),
  ADD_NATIONAL_ORIGIN("Nationality Added", ChangeOperation.Create, ChangeObjectType.Nationality, ChangeObjectModifier.Person),
  EDIT_NATIONAL_ORIGIN("Nationality Changed", ChangeOperation.Update, ChangeObjectType.Nationality, ChangeObjectModifier.Person),
  DELETE_NATIONAL_ORIGIN("Nationality Removed", ChangeOperation.Delete, ChangeObjectType.Nationality, ChangeObjectModifier.Person),
  ADD_PHYSICAL_DESCRIPTION("Physical Description Added", ChangeOperation.Create, ChangeObjectType.PhysicalDescription, ChangeObjectModifier.Person),
  EDIT_PHYSICAL_DESCRIPTION("Physical Description Changed", ChangeOperation.Update, ChangeObjectType.PhysicalDescription, ChangeObjectModifier.Person),
  DELETE_PHYSICAL_DESCRIPTION("Physical Description Removed", ChangeOperation.Delete, ChangeObjectType.PhysicalDescription, ChangeObjectModifier.Person),
  ADD_RACE("Ethnicity Added", ChangeOperation.Create, ChangeObjectType.Ethnicity, ChangeObjectModifier.Person),
  EDIT_RACE("Ethnicity Changed", ChangeOperation.Update, ChangeObjectType.Ethnicity, ChangeObjectModifier.Person),
  DELETE_RACE("Ethnicity Removed", ChangeOperation.Delete, ChangeObjectType.Ethnicity, ChangeObjectModifier.Person),
  ADD_TRIBE_NAME("Tribe Name Added", ChangeOperation.Create, ChangeObjectType.TribeName, ChangeObjectModifier.Person),
  EDIT_TRIBE_NAME("Tribe Name Changed", ChangeOperation.Update, ChangeObjectType.TribeName, ChangeObjectModifier.Person),
  DELETE_TRIBE_NAME("Tribe Name Removed", ChangeOperation.Delete, ChangeObjectType.TribeName, ChangeObjectModifier.Person),
  ADD_OTHER_FACT("Other Fact Added", ChangeOperation.Create, ChangeObjectType.Fact, ChangeObjectModifier.Person),
  EDIT_OTHER_FACT("Other Fact Changed", ChangeOperation.Update, ChangeObjectType.Fact, ChangeObjectModifier.Person),
  DELETE_OTHER_FACT("Other Fact Removed", ChangeOperation.Delete, ChangeObjectType.Fact, ChangeObjectModifier.Person),

  // GENDER
  ADD_GENDER("Gender Added", ChangeOperation.Create, ChangeObjectType.Gender, ChangeObjectModifier.Person),
  EDIT_GENDER("Gender Changed", ChangeOperation.Update, ChangeObjectType.Gender, ChangeObjectModifier.Person),
  DELETE_GENDER("Gender Removed", ChangeOperation.Delete, ChangeObjectType.Gender, ChangeObjectModifier.Person),

  // NAME
  ADD_BIRTH_NAME("Birth Name Added", ChangeOperation.Create, ChangeObjectType.BirthName, ChangeObjectModifier.Person),
  EDIT_BIRTH_NAME("Birth Name Changed", ChangeOperation.Update, ChangeObjectType.BirthName, ChangeObjectModifier.Person),
  DELETE_BIRTH_NAME("Birth Name Removed", ChangeOperation.Delete, ChangeObjectType.BirthName, ChangeObjectModifier.Person),
  ADD_AKA_NAME("Aka Name Added", ChangeOperation.Create, ChangeObjectType.AlsoKnownAs, ChangeObjectModifier.Person),
  EDIT_AKA_NAME("Aka Name Changed", ChangeOperation.Update, ChangeObjectType.AlsoKnownAs, ChangeObjectModifier.Person),
  DELETE_AKA_NAME("Aka Name Removed", ChangeOperation.Delete, ChangeObjectType.AlsoKnownAs, ChangeObjectModifier.Person),
  ADD_ALTERNATE_NAME("Aka Name Added", ChangeOperation.Create, ChangeObjectType.AlsoKnownAs, ChangeObjectModifier.Person),
  EDIT_ALTERNATE_NAME("Aka Name Changed", ChangeOperation.Update, ChangeObjectType.AlsoKnownAs, ChangeObjectModifier.Person),
  DELETE_ALTERNATE_NAME("Aka Name Removed", ChangeOperation.Delete, ChangeObjectType.AlsoKnownAs, ChangeObjectModifier.Person),
  ADD_MARRIED_NAME("Married Name Added", ChangeOperation.Create, ChangeObjectType.MarriedName, ChangeObjectModifier.Person),
  EDIT_MARRIED_NAME("Married Name Changed", ChangeOperation.Update, ChangeObjectType.MarriedName, ChangeObjectModifier.Person),
  DELETE_MARRIED_NAME("Married Name Removed", ChangeOperation.Delete, ChangeObjectType.MarriedName, ChangeObjectModifier.Person),
  ADD_NICK_NAME("Nickname Added", ChangeOperation.Create, ChangeObjectType.Nickname, ChangeObjectModifier.Person),
  EDIT_NICK_NAME("Nickname Changed", ChangeOperation.Update, ChangeObjectType.Nickname, ChangeObjectModifier.Person),
  DELETE_NICK_NAME("Nickname Removed", ChangeOperation.Delete, ChangeObjectType.Nickname, ChangeObjectModifier.Person),
  ADD_OTHER_NAME("Other Name Added", ChangeOperation.Create, ChangeObjectType.Name, ChangeObjectModifier.Person),
  EDIT_OTHER_NAME("Other Name Changed", ChangeOperation.Update, ChangeObjectType.Name, ChangeObjectModifier.Person),
  DELETE_OTHER_NAME("Other Name Removed", ChangeOperation.Delete, ChangeObjectType.Name, ChangeObjectModifier.Person),

  // LINEAGE
  ADD_LINEAGE("Lineage Added", ChangeOperation.Create, ChangeObjectType.Fact, ChangeObjectModifier.ChildAndParentsRelationship),
  EDIT_LINEAGE("Lineage Changed", ChangeOperation.Update, ChangeObjectType.Fact, ChangeObjectModifier.ChildAndParentsRelationship),
  DELETE_LINEAGE("Lineage Removed", ChangeOperation.Delete, ChangeObjectType.Fact, ChangeObjectModifier.ChildAndParentsRelationship),

  // ORDINANCE
  COMPLETE_BAPTISM("The Church of Jesus Christ of Latter-day Saints Baptism Completed", ChangeOperation.Create, ChangeObjectType.Baptism, ChangeObjectModifier.Person),
  COMPLETE_CONFIRMATION("The Church of Jesus Christ of Latter-day Saints Confirmation Completed", ChangeOperation.Create, ChangeObjectType.Confirmation, ChangeObjectModifier.Person),
  COMPLETE_INITIATORY("The Church of Jesus Christ of Latter-day Saints Initiatory Completed", ChangeOperation.Create, ChangeObjectType.Initiatory, ChangeObjectModifier.Person),
  COMPLETE_ENDOWMENT("The Church of Jesus Christ of Latter-day Saints Endowment Completed", ChangeOperation.Create, ChangeObjectType.Endowment, ChangeObjectModifier.Person),
  COMPLETE_COUPLE_SEALING("The Church of Jesus Christ of Latter-day Saints Couple Sealing Completed", ChangeOperation.Create, ChangeObjectType.Sealing, ChangeObjectModifier.Couple),
  COMPLETE_SEALING_TO_PARENTS("The Church of Jesus Christ of Latter-day Saints Sealing to Parents Completed", ChangeOperation.Create, ChangeObjectType.Sealing, ChangeObjectModifier.ChildAndParentsRelationship),

  // NOTES
  ADD_PERSON_NOTE("Person Note Added", ChangeOperation.Create, ChangeObjectType.Note, ChangeObjectModifier.Person),
  EDIT_PERSON_NOTE("Person Note Changed", ChangeOperation.Update, ChangeObjectType.Note, ChangeObjectModifier.Person),
  DELETE_PERSON_NOTE("Person Note Removed", ChangeOperation.Delete, ChangeObjectType.Note, ChangeObjectModifier.Person),
  ADD_CHILD_PARENTS_NOTE("Child and Parents Note Added", ChangeOperation.Create, ChangeObjectType.Note, ChangeObjectModifier.ChildAndParentsRelationship),
  EDIT_CHILD_PARENTS_NOTE("Child and Parents Note Changed", ChangeOperation.Update, ChangeObjectType.Note, ChangeObjectModifier.ChildAndParentsRelationship),
  DELETE_CHILD_PARENTS_NOTE("Child and Parents Note Removed", ChangeOperation.Delete, ChangeObjectType.Note, ChangeObjectModifier.ChildAndParentsRelationship),
  ADD_COUPLE_NOTE("Couple Note Added", ChangeOperation.Create, ChangeObjectType.Note, ChangeObjectModifier.Couple),
  EDIT_COUPLE_NOTE("Couple Note Changed", ChangeOperation.Update, ChangeObjectType.Note, ChangeObjectModifier.Couple),
  DELETE_COUPLE_NOTE("Couple Note Removed", ChangeOperation.Delete, ChangeObjectType.Note, ChangeObjectModifier.Couple);

  private final String displayName;
  private final ChangeOperation operation;
  private final ChangeObjectType objectType;
  private final ChangeObjectModifier objectModifier;

  private ChangeType(String displayName, ChangeOperation operation, ChangeObjectType objectType, ChangeObjectModifier objectModifier) {
    this.displayName = displayName;
    this.operation = operation;
    this.objectModifier = objectModifier;
    this.objectType = objectType;
    if (objectType == null) {
      throw new NullPointerException();
    }
  }

  public String toString() {
    return this.displayName;
  }

  public String getDisplayName() {
    return displayName;
  }

  public ChangeOperation getOperation() {
    return operation;
  }

  public ChangeObjectType getObjectType() {
    return objectType;
  }

  public ChangeObjectModifier getObjectModifier() {
    return objectModifier;
  }
}
