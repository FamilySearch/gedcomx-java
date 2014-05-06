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
package org.familysearch.api.client.util;

import org.familysearch.platform.artifacts.ArtifactType;
import org.familysearch.platform.ct.MatchStatus;
import org.gedcomx.common.URI;
import org.gedcomx.rs.client.options.QueryParameter;
import org.gedcomx.types.ConfidenceLevel;

/**
 * @author Ryan Heaton
 */
public class FamilySearchOptions {

  public static final String COLLECTION = "collection";
  public static final String CONFIDENCE = "confidence";
  public static final String DEFAULT = "default";
  public static final String DESCRIPTION = "description";
  public static final String FILENAME = "filename";
  public static final String FILTER = "filter";
  public static final String INCLUDE_MARRIAGE_DETAILS = "marriageDetails";
  public static final String INCLUDE_PERSON_DETAILS = "personDetails";
  public static final String INCLUDE_PERSONS = "persons";
  public static final String SPOUSE_ID = "spouse";
  public static final String STATUS = "status";
  public static final String TITLE = "title";
  public static final String TYPE = "type";

  private FamilySearchOptions(){}

  public static QueryParameter collection(URI value) {
    return new QueryParameter(true, COLLECTION, value.toString());
  }

  public static QueryParameter confidence(ConfidenceLevel confidence) {
    return new QueryParameter(true, CONFIDENCE, confidence.toQNameURI().toString());
  }

  public static QueryParameter defaultUri(URI defaultUri) {
    return new QueryParameter(true, DEFAULT, defaultUri.toString());
  }

  public static QueryParameter description(String description) {
    return new QueryParameter(true, DESCRIPTION, description);
  }

  public static QueryParameter filename(String filename) {
    return new QueryParameter(true, FILENAME, filename);
  }

  public static QueryParameter title(String title) {
    return new QueryParameter(true, TITLE, title);
  }

  public static QueryParameter artifactType(ArtifactType type) {
    return new QueryParameter(true, TYPE, type.toQNameURI().toString());
  }

  public static QueryParameter mergeAnalysisFilter(MergeAnalysisFilter filter) {
    return new QueryParameter(true, FILTER, filter.name());
  }

  public static QueryParameter includePersons() {
    return new QueryParameter(true, INCLUDE_PERSONS, "true");
  }

  public static QueryParameter spouseId(String spouseId) {
    return new QueryParameter(true, SPOUSE_ID, spouseId);
  }

  public static QueryParameter includePersonDetails() {
    return new QueryParameter(true, INCLUDE_PERSON_DETAILS, "true");
  }

  public static QueryParameter includeMarriageDetails() {
    return new QueryParameter(true, INCLUDE_MARRIAGE_DETAILS, "true");
  }

  public static QueryParameter matchStatus(MatchStatus status) {
    return new QueryParameter(false, STATUS, status.toQNameURI().toString());
  }
}
