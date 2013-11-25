/**
 * Copyright 2011-2012 Intellectual Reserve, Inc.
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
package org.gedcomx.rs.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

/**
 * @author Ryan Heaton
 */
public class BasicGedcomxClient implements GedcomxApi {

  protected final GedcomxApiDescriptor descriptor;
  protected String accessToken;

  public BasicGedcomxClient(String discoveryUri) {
    this(new GedcomxApiDescriptor(discoveryUri));
  }

  public BasicGedcomxClient(String discoveryUri, Client client) {
    this(new GedcomxApiDescriptor(discoveryUri, client));
  }

  public BasicGedcomxClient(GedcomxApiDescriptor descriptor) {
    this.descriptor = descriptor;
  }

  public Client getClient() {
    return this.descriptor.getClient(); //we'll just use the descriptors client as ours.
  }

  @Override
  public URI buildOAuth2AuthenticationUri(String clientId, String redirectUri) {
    String authenticationUri = this.descriptor.getOAuth2AuthenticationUri();
    if (authenticationUri == null) {
      throw new GedcomxApiException(String.format("No OAuth2 authentication URI supplied for API at %s", this.descriptor.getDiscoveryUri()));
    }
    return UriBuilder.fromUri(authenticationUri).queryParam("response_type", "code").queryParam("client_id", clientId).queryParam("redirect_uri", redirectUri).build();
  }

  public void authenticateViaOAuth2Password(String username, String password, String clientId) {
    authenticateViaOAuth2Password(username, password, clientId, null);
  }

  public void authenticateViaOAuth2Password(String username, String password, String clientId, String clientSecret) {
    MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
    formData.putSingle("grant_type", "password");
    formData.putSingle("username", username);
    formData.putSingle("password", password);
    formData.putSingle("client_id", clientId);
    if (clientSecret != null) {
      formData.putSingle("client_secret", clientSecret);
    }
    authenticateViaOAuth2(formData);
  }

  public void authenticateViaOAuth2AuthCode(String authCode, String redirect, String clientId) {
    authenticateViaOAuth2Password(authCode, authCode, clientId, null);
  }

  public void authenticateViaOAuth2AuthCode(String authCode, String redirect, String clientId, String clientSecret) {
    MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
    formData.putSingle("grant_type", "authorization_code");
    formData.putSingle("code", authCode);
    formData.putSingle("redirect_uri", redirect);
    formData.putSingle("client_id", clientId);
    if (clientSecret != null) {
      formData.putSingle("client_secret", clientSecret);
    }
    authenticateViaOAuth2(formData);
  }

  public void authenticateViaOAuth2ClientCredentials(String clientId, String clientSecret) {
    MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
    formData.putSingle("grant_type", "client_credentials");
    formData.putSingle("client_id", clientId);
    if (clientSecret != null) {
      formData.putSingle("client_secret", clientSecret);
    }
    authenticateViaOAuth2(formData);
  }

  public void authenticateViaOAuth2(MultivaluedMap<String, String> formData) {
    String tokenUri = this.descriptor.getOAuth2TokenUri();
    if (tokenUri == null) {
      throw new GedcomxApiException(String.format("No OAuth2 token URI supplied for API at %s", this.descriptor.getDiscoveryUri()));
    }

    ClientResponse response = getClient()
      .resource(tokenUri)
      .type(MediaType.APPLICATION_JSON_TYPE)
      .type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
      .post(ClientResponse.class, formData);
    if (response.getClientResponseStatus().getFamily() == Response.Status.Family.SUCCESSFUL) {
      ObjectNode accessToken = response.getEntity(ObjectNode.class);
      JsonNode access_token = accessToken.get("access_token");
      if (access_token == null) {
        throw new GedcomxApiException("Illegal access token response: no access_token provided.", response);
      }
      this.accessToken = access_token.getValueAsText();
    }
    else {
      throw new GedcomxApiException("Unable to obtain an access token.", response);
    }
  }

  public boolean isAuthenticated() {
    return this.accessToken != null;
  }
}
