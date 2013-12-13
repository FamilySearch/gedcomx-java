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
package org.familysearch.platform;

import org.gedcomx.atom.AtomModel;
import org.gedcomx.rt.GedcomxConstants;

import javax.xml.bind.annotation.XmlTransient;

@XmlTransient
public class FamilySearchConstants {

  private FamilySearchConstants() {}

  public static final String FS_PROJECT_ID = "fs-platform";
  public static final String FS_PLATFORM_V1_NAMESPACE = "http://familysearch.org/v1/";
  public static final String FS_PLATFORM_V1_XML_MEDIA_TYPE = "application/x-fs-v1+xml";
  public static final String FS_PLATFORM_V1_JSON_MEDIA_TYPE = "application/x-fs-v1+json";
  public static final String GEDCOMX_XML_MEDIA_TYPE = GedcomxConstants.GEDCOMX_XML_MEDIA_TYPE;
  public static final String GEDCOMX_JSON_MEDIA_TYPE = GedcomxConstants.GEDCOMX_JSON_MEDIA_TYPE;
  public static final String ATOM_XML_MEDIA_TYPE = AtomModel.ATOM_XML_MEDIA_TYPE;
  public static final String ATOM_JSON_MEDIA_TYPE = AtomModel.ATOM_GEDCOMX_JSON_MEDIA_TYPE;

  public static final String PDF_MEDIA_TYPE = "application/pdf";

  public static final String AUTHORITIES_SERVICE_NAMESPACE = "http://familysearch.org/platform/authorities";
  public static final String AUTHENTICATION_SERVICE_NAMESPACE = "http://familysearch.org/platform/authentication";
  public static final String DISCUSSIONS_SERVICE_NAMESPACE = "http://familysearch.org/platform/discussions";
  public static final String DISCOVERY_SERVICE_NAMESPACE = "http://familysearch.org/platform/discovery";
  public static final String TREE_SERVICE_NAMESPACE = "http://familysearch.org/platform/tree";
  public static final String RECORD_SERVICE_NAMESPACE = "http://familysearch.org/platform/records";
  public static final String SOURCES_SERVICE_NAMESPACE = "http://familysearch.org/platform/sources";
  public static final String USERS_SERVICE_NAMESPACE = "http://familysearch.org/platform/users";
  public static final String ORDINANCES_SERVICE_NAMESPACE = "http://familysearch.org/platform/ordinances";
  public static final String MEMORIES_SERVICE_NAMESPACE = "http://familysearch.org/platform/memories";
  public static final String HINTING_SERVICE_NAMESPACE = "http://familysearch.org/platform/hinting";

  public static final String PLACE_DESCRIPTION_TRANSIENT_PROPERTY = "org.familysearch.platform.FamilySearchConstants#PLACE_DESCRIPTION_TRANSIENT_PROPERTY";
  public static final String HAS_ADDITIONAL_SPOUSES_PROPERTY = "org.familysearch.platform.FamilySearchConstants#HAS_ADDITIONAL_SPOUSES_PROPERTY";
  public static final String HAS_ADDITIONAL_PARENTS_PROPERTY = "org.familysearch.platform.FamilySearchConstants#HAS_ADDITIONAL_PARENTS_PROPERTY";
  public static final String HAS_CHILDREN_PROPERTY = "org.familysearch.platform.FamilySearchConstants#HAS_CHILDREN_PROPERTY";
  public static final String SOURCE_REFERENCE_ID_PROPERTY = "org.familysearch.platform.FamilySearchConstants#SOURCE_REFERENCE_ID_PROPERTY";
  public static final String DISCUSSION_REFERENCE_ID_PROPERTY = "org.familysearch.platform.FamilySearchConstants#DISCUSSION_REFERENCE_ID_PROPERTY";
  public static final String COLLECTION_ID_PROPERTY_NAME = "org.familysearch.platform.FamilySearchConstants#COLLECTION_ID";
  public static final String CONTRIBUTOR_ID_PROPERTY_NAME = "org.familysearch.platform.FamilySearchConstants#CONTRIBUTOR_ID_PROPERTY_NAME";
  public static final String LAST_PAGE_INDEX_PROPERTY = "org.familysearch.platform.FamilySearchConstants#LAST_PAGE_INDEX_PROPERTY";
  public static final String NEXT_PAGE_INDEX_PROPERTY = "org.familysearch.platform.FamilySearchConstants#NEXT_PAGE_INDEX_PROPERTY";
  public static final String PREV_PAGE_INDEX_PROPERTY = "org.familysearch.platform.FamilySearchConstants#PREV_PAGE_INDEX_PROPERTY";
  public static final String FIRST_PAGE_INDEX_PROPERTY = "org.familysearch.platform.FamilySearchConstants#FIRST_PAGE_INDEX_PROPERTY";
  public static final String LAST_ENTRY_PROPERTY = "org.familysearch.platform.FamilySearchConstants#LAST_ENTRY_PROPERTY";
  public static final String SUPPORTS_RESTORE_PROPERTY = "org.familysearch.platform.FamilySearchConstants#SUPPORTS_RESTORE_PROPERTY";
  public static final String CHANGE_ID_PROPERTY = "org.familysearch.platform.FamilySearchConstants#CHANGE_ID_PROPERTY";
  public static final String PARENT_CHILD_RELATIONSHIP_CHILD_ID_PROPERTY = "org.familysearch.platform.FamilySearchConstants#PARENT_CHILD_RELATIONSHIP_CHILD_ID_PROPERTY";
  public static final String PARENT_CHILD_RELATIONSHIP_FATHER_ID_PROPERTY = "org.familysearch.platform.FamilySearchConstants#PARENT_CHILD_RELATIONSHIP_FATHER_ID_PROPERTY";
  public static final String PARENT_CHILD_RELATIONSHIP_MOTHER_ID_PROPERTY = "org.familysearch.platform.FamilySearchConstants#PARENT_CHILD_RELATIONSHIP_MOTHER_ID_PROPERTY";
  public static final String PERSON_ID_OVERRIDE_PROPERTY = "org.familysearch.platform.FamilySearchConstants#PERSON_ID_OVERRIDE_PROPERTY";

  public static final String PAGE_CONTEXT_HEADER = "X-FS-Page-Context";

  public static final String FACET_FS_BETA = "https://familysearch.org#BETA";
  public static final String FACET_FS_PROTOTYPE = "https://familysearch.org#PROTOTYPE";
  public static final String FACET_FS_HIDDEN = "https://familysearch.org#HIDDEN";

  public static final String FEATURE_RELATIONSHIP_PERSONS_LOCAL_HREFS = "relationship-persons-local-hrefs";
  public static final String FEATURE_XML_ROOT_NO_XMLNS_PREFIX = "root-xml-element-without-xmlns-prefix";
  public static final String FEATURE_JSON_DISCUSSION_REFERENCE_FIX = "discussion-reference-json-fix";
  public static final String FEATURE_PARENT_CHILD_RELATIONSHIP_RESOURCES_CONSOLIDATION = "parent-child-relationship-resources-consolidation";
  public static final String FEATURE_CHILD_AND_PARENTS_RELATIONSHIP_CHANGE_OBJECT_RENAME = "child-and-parents-relationship-change-object-rename";
  public static final String FEATURE_REMOVE_CONFIDENCE = "remove-conclusion-confidence-level";

}
