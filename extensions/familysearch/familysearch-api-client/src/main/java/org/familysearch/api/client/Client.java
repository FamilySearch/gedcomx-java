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
import org.gedcomx.common.URI;
import org.gedcomx.links.Link;
import org.gedcomx.rs.client.GedcomxApplicationException;

import javax.ws.rs.core.UriBuilder;

/**
 * Convenience client for accessing the FamilySearch API.
 *
 * @author Ryan Heaton
 */
public class Client {

  private final Config config;
  private FamilySearchCollectionState home;

  /**
   * Construct a client with default configuration, connecting to the FamilySearch production environment.
   */
  public Client() {
    this(FamilySearchReferenceEnvironment.PRODUCTION);
  }

  /**
   * Construct a client connecting to the specified environment.
   *
   * @param env The environment.
   */
  public Client(FamilySearchReferenceEnvironment env) {
    this(new Config().environment(env));
  }

  /**
   * Construct a client connecting to the specified environment with the specified app key.
   *
   * @param env The environment.
   * @param appKey The app key.
   */
  public Client(FamilySearchReferenceEnvironment env, String appKey) {
    this(new Config().environment(env).appKey(appKey));
  }

  /**
   * Construct a client connecting to the specified environment with the specified app key and redirect URI.
   *
   * @param env The environment.
   * @param appKey The app key.
   * @param redirectUri The redirect URI.
   */
  public Client(FamilySearchReferenceEnvironment env, String appKey, String redirectUri) {
    this(env, appKey, URI.create(redirectUri));
  }

  /**
   * Construct a client connecting to the specified environment with the specified app key and redirect URI.
   *
   * @param env The environment.
   * @param appKey The app key.
   * @param redirectUri The redirect URI.
   */
  public Client(FamilySearchReferenceEnvironment env, String appKey, URI redirectUri) {
    this(new Config().environment(env).appKey(appKey).redirectUri(redirectUri));
  }

  /**
   * Construct a client with the provided configuration.
   *
   * @param config The client configuration.
   */
  public Client(Config config) {
    this.config = config;
  }

  /**
   * Authenticate this client with the given credentials.
   *
   * @param appKey The app key.
   * @param username The username of the user.
   * @param password The password of the user.
   */
  public void authenticate(String appKey, String username, String password) {
    if (this.home == null) {
      this.home = new FamilySearchCollectionState(this.config.env).ifSuccessful();
    }

    this.home = this.home.authenticateViaOAuth2Password(username, password, appKey);
  }

  /**
   * Authenticate using an OAuth 2 authorization code.
   *
   * @param authCode The authorization code to use to authenticate.
   */
  public void authenticateWithAuthCode(String authCode) {
    if (this.home == null) {
      this.home = new FamilySearchCollectionState(this.config.env).ifSuccessful();
    }

    this.home = this.home.authenticateViaOAuth2AuthCode(authCode, this.config.redirectUri == null ? null : this.config.redirectUri.toString(), this.config.appKey);
  }

  /**
   * Get the OAuth2 Authorization URI where a user should be redirected to authenticate to FamilySearch in a web browser
   * using the OAuth2 "Authorization Code" flow.
   *
   * @return The authorization URI.
   */
  public String getOAuth2AuthorizationUri() {
    return getOAuth2AuthorizationUri(null);
  }

  /**
   * Get the OAuth2 Authorization URI where a user should be redirected to authenticate to FamilySearch in a web browser
   * using the OAuth2 "Authorization Code" flow.
   *
   * @param state Some client state that will be preserved by the OAuth2 flow.
   * @return The authorization URI.
   */
  public String getOAuth2AuthorizationUri(String state) {
    return getOAuth2AuthorizationUri(state, this.config.appKey);
  }

  /**
   * Get the OAuth2 Authorization URI where a user should be redirected to authenticate to FamilySearch in a web browser
   * using the OAuth2 "Authorization Code" flow.
   *
   * @param state Some client state that will be preserved by the OAuth2 flow.
   * @param appKey The application key.
   * @return The authorization URI.
   */
  public String getOAuth2AuthorizationUri(String state, String appKey) {
    if (this.home == null) {
      this.home = new FamilySearchCollectionState(this.config.env).ifSuccessful();
    }

    if (appKey == null) {
      throw new GedcomxApplicationException("Unable to build authorization URI: no application key provided.");
    }

    Link authorizationLink = this.home.getLink(Rel.OAUTH2_AUTHORIZE);
    if (authorizationLink == null || authorizationLink.getHref() == null) {
      throw new GedcomxApplicationException(String.format("No OAuth2 authorization URI supplied for resource at %s.", this.home.getUri()));
    }

    UriBuilder uriBuilder = UriBuilder.fromUri(authorizationLink.getHref().toString()).queryParam("response_type", "code");
    if (this.config.redirectUri != null) {
      uriBuilder = uriBuilder.queryParam("redirect_uri", this.config.redirectUri.toString());
    }
    uriBuilder = uriBuilder.queryParam("client_id", appKey);
    if (state != null) {
      uriBuilder = uriBuilder.queryParam("state", state);
    }
    return uriBuilder.build().toString();
  }

  /**
   * Get the FamilySearch Family Tree.
   *
   * @return The FamilySearch Family Tree.
   */
  public FamilySearchFamilyTree familyTree() {
    FamilySearchFamilyTree fsft = new FamilySearchFamilyTree(this.config.env);
    if (this.home != null && this.home.isAuthenticated()) {
      fsft = (FamilySearchFamilyTree) fsft.authenticateWithAccessToken(this.home.getAccessToken());
    }
    return fsft;
  }

  /**
   * The config for the client.
   */
  public static class Config {

    private FamilySearchReferenceEnvironment env = FamilySearchReferenceEnvironment.PRODUCTION;
    private String appKey;
    private URI redirectUri;

    public Config environment(FamilySearchReferenceEnvironment env) {
      if (env == null) {
        throw new IllegalArgumentException();
      }
      this.env = env;
      return this;
    }

    public Config appKey(String appKey) {
      this.appKey = appKey;
      return this;
    }

    public Config redirectUri(URI redirectUri) {
      this.redirectUri = redirectUri;
      return this;
    }

    public Config redirectUri(String redirectUri) {
      return this.redirectUri(URI.create(redirectUri));
    }

  }
}
