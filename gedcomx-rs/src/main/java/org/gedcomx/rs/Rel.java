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
package org.gedcomx.rs;

/**
 * Constants for link rels.
 * 
 * @author Ryan Heaton
 */
public class Rel {

  protected Rel(){}

  //Standard well-known RELs
  public static final String SELF = "self";
  public static final String NEXT = "next";
  public static final String PREV = "prev";
  public static final String PREVIOUS = PREV;
  public static final String FIRST = "first";
  public static final String LAST = "last";


  //GEDCOM-X specific rels.
  public static final String AGENT = "agent";
  public static final String ANCESTRY = "ancestry";
  public static final String ARTIFACTS = "artifacts";
  public static final String CHILDREN = "children";
  public static final String CHILD_RELATIONSHIPS = "child-relationships";
  public static final String COLLECTION = "collection";
  public static final String COLLECTIONS = "collections";
  public static final String CONCLUSION = "conclusion";
  public static final String CONCLUSIONS = "conclusions";
  public static final String CURRENT_USER_PERSON = "current-user-person";
  public static final String CURRENT_USER_RESOURCES = "current-user-resources";
  public static final String DESCENDANCY = "descendancy";
  public static final String DESCRIPTION = "description";
  public static final String EVIDENCE_REFERENCE = "evidence-reference";
  public static final String EVIDENCE_REFERENCES = "evidence-references";
  public static final String MATCHES = "matches";
  public static final String MEDIA_REFERENCE = "media-reference";
  public static final String MEDIA_REFERENCES = "media-references";
  public static final String NOTE = "note";
  public static final String NOTES = "notes";
  public static final String OAUTH2_AUTHORIZE = "http://oauth.net/core/2.0/endpoint/authorize";
  public static final String OAUTH2_TOKEN = "http://oauth.net/core/2.0/endpoint/token";
  public static final String PARENT_RELATIONSHIPS = "parent-relationships";
  public static final String PARENTS = "parents";
  public static final String PERSON1 = "person1";
  public static final String PERSON2 = "person2";
  public static final String PERSON = "person";
  public static final String PERSONS = "persons";
  public static final String PERSON_SEARCH = "person-search";
  public static final String PLACE = "place";
  public static final String PLACE_SEARCH = "place-search";
  public static final String PROFILE = "profile";
  public static final String RECORD = "record";
  public static final String RECORDS = "records";
  public static final String RELATIONSHIP = "relationship";
  public static final String RELATIONSHIPS = "relationships";
  public static final String SOURCE_DESCRIPTIONS = "source-descriptions";
  public static final String SOURCE_REFERENCE = "source-reference";
  public static final String SOURCE_REFERENCES = "source-references";
  public static final String SOURCE_REFERENCES_QUERY = "source-references-query";
  public static final String SPOUSES = "spouses";
  public static final String SPOUSE_RELATIONSHIPS = "spouse-relationships";
  public static final String DISCUSSION_REFERENCE = "discussion-reference";
  public static final String DISCUSSION_REFERENCES = "discussion-references";

}
