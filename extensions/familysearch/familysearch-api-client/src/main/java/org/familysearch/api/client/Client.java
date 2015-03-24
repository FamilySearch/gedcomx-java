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

import org.familysearch.api.client.ft.FamilySearchFamilyTree;

/**
 * Convenience client for accessing the FamilySearch API.
 *
 * @author Ryan Heaton
 */
public class Client {

  private final FamilySearchReferenceEnvironment env;

  private FamilySearchCollectionState home;

  public Client() {
    this(FamilySearchReferenceEnvironment.PRODUCTION);
  }

  public Client(FamilySearchReferenceEnvironment env) {
    this.env = env;
  }

  public void authenticate(String appKey, String username, String password) {
    if (this.home == null) {
      this.home = new FamilySearchCollectionState(this.env).ifSuccessful();
    }

    this.home = this.home.authenticateViaOAuth2Password(username, password, appKey);
  }

  public FamilySearchFamilyTree familyTree() {
    FamilySearchFamilyTree fsft = new FamilySearchFamilyTree(this.env);
    if (this.home != null && this.home.isAuthenticated()) {
      fsft = (FamilySearchFamilyTree) fsft.authenticateWithAccessToken(this.home.getAccessToken());
    }
    return fsft;
  }
}
