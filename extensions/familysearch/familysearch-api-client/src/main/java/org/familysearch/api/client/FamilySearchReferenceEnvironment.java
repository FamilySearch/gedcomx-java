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
package org.familysearch.api.client;

import java.net.URI;

/**
 * @author Ryan Heaton
 */
public enum FamilySearchReferenceEnvironment {

  PRODUCTION("https://api.familysearch.org/platform"),

  BETA("https://apibeta.familysearch.org/platform"),

  SANDBOX("https://api-integ.familysearch.org/platform");

  private final String base;

  FamilySearchReferenceEnvironment(String base) {
    this.base = base;
  }

  public URI getRootUri() {
    return URI.create(this.base + "/collection");
  }

  public URI getHistoricalRecordsArchiveUri() {
    return URI.create(this.base + "/collections/records");
  }

  public URI getMemoriesUri() {
    return URI.create(this.base + "/collections/memories");
  }

  public URI getPlacesUri() {
    return URI.create(this.base + "/collections/places");
  }

  public URI getNamesUri() {
    return URI.create(this.base + "/collections/names");
  }

  public URI getFamilyTreeUri() {
    return URI.create(this.base + "/collections/tree");
  }
}
