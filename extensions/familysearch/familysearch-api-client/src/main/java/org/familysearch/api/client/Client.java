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
