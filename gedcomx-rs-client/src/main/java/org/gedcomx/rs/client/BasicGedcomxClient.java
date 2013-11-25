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

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

/**
 * @author Ryan Heaton
 */
public class BasicGedcomxClient implements GedcomxApi {

  private final GedcomxApiDescriptor descriptor;
  private String accessToken;

  public BasicGedcomxClient(String discoveryUri) {
    this(new GedcomxApiDescriptor(discoveryUri));
  }

  public BasicGedcomxClient(String discoveryUri, Client client) {
    this(new GedcomxApiDescriptor(discoveryUri, client));
  }

  public BasicGedcomxClient(GedcomxApiDescriptor descriptor) {
    this.descriptor = descriptor;
  }

  @Override
  public URI buildOAuth2AuthenticationUri(String clientId, String redirectUri) {
    String authenticationUri = this.descriptor.getOAuth2AuthenticationUri();
    if (authenticationUri == null) {
      throw new GedcomxApiException(String.format("No OAuth2 authentication URI supplied for API at %s", this.descriptor.getDiscoveryUri()));
    }
    return UriBuilder.fromUri(authenticationUri).queryParam("response_type", "code").queryParam("client_id", clientId).queryParam("redirect_uri", redirectUri).build();
  }
}
