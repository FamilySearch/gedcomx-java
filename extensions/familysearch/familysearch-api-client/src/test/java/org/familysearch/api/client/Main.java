package org.familysearch.api.client;

import org.gedcomx.rs.client.PersonState;

/**
 * @author Ryan Heaton
 */
public class Main {

  public static void main(String[] args) {
    //supply the app key.
    String appKey = "...";
    //supply the URI where the user will be redirected after successfully authenticating to FamilySearch.
    String redirect = "http://localhost:8000/myapp/handle-redirect";

    //set up the configuration for the client.
    Client.Config config = new Client.Config()
      .appKey(appKey)
      .redirectUri(redirect);

    Client client = new Client(config);
    String authUri = client.getOAuth2AuthorizationUri();

    //now redirect the user to 'authUri'
    String authCode = redirectUserAndWaitForAuthCodeResponse(authUri);

    //authenticate the client:
    client.authenticateWithAuthCode(authCode);

    //now you can do work with the Family Tree:
    PersonState personState = client.familyTree().readPersonForCurrentUser();
  }

  private static String redirectUserAndWaitForAuthCodeResponse(String authUri) {
    return null;
  }

}
