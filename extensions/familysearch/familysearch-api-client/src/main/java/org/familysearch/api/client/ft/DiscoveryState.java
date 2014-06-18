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
package org.familysearch.api.client.ft;

import com.damnhandy.uri.template.MalformedUriTemplateException;
import com.damnhandy.uri.template.UriTemplate;
import com.damnhandy.uri.template.VariableExpansionException;
import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import org.gedcomx.atom.Feed;
import org.gedcomx.links.Link;
import org.gedcomx.links.SupportsLinks;
import org.gedcomx.rs.Rel;
import org.gedcomx.rs.client.GedcomxApplicationException;
import org.gedcomx.rs.client.GedcomxApplicationState;
import org.gedcomx.rs.client.PersonSearchResultsState;
import org.gedcomx.rs.client.StateTransitionOption;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MultivaluedMap;
import java.net.URI;

/**
 * @author Ryan Heaton
 */
public class DiscoveryState extends GedcomxApplicationState<Feed> {

  protected DiscoveryState(ClientRequest request, ClientResponse response, String accessToken, FamilyTreeStateFactory stateFactory) {
    super(request, response, accessToken, stateFactory);
  }

  @Override
  protected DiscoveryState clone(ClientRequest request, ClientResponse response) {
    return new DiscoveryState(request, response, this.accessToken, (FamilyTreeStateFactory) this.stateFactory);
  }

  @Override
  protected Feed loadEntity(ClientResponse response) {
    return response.getEntity(Feed.class);
  }

  @Override
  protected SupportsLinks getMainDataElement() {
    return getEntity();
  }

  @Override
  public DiscoveryState ifSuccessful() {
    return (DiscoveryState) super.ifSuccessful();
  }

  @Override
  public DiscoveryState head(StateTransitionOption... options) {
    return (DiscoveryState) super.head(options);
  }

  @Override
  public DiscoveryState get(StateTransitionOption... options) {
    return (DiscoveryState) super.get(options);
  }

  @Override
  public DiscoveryState delete(StateTransitionOption... options) {
    return (DiscoveryState) super.delete(options);
  }

  @Override
  public DiscoveryState put(Feed e, StateTransitionOption... options) {
    return (DiscoveryState) super.put(e, options);
  }

  @Override
  public DiscoveryState post(Feed entity, StateTransitionOption... options) {
    return (DiscoveryState) super.post(entity, options);
  }

  @Override
  protected DiscoveryState authenticateViaOAuth2Password(String username, String password, String clientId) {
    return (DiscoveryState) super.authenticateViaOAuth2Password(username, password, clientId);
  }

  @Override
  protected DiscoveryState authenticateViaOAuth2Password(String username, String password, String clientId, String clientSecret) {
    return (DiscoveryState) super.authenticateViaOAuth2Password(username, password, clientId, clientSecret);
  }

  @Override
  protected DiscoveryState authenticateViaOAuth2AuthCode(String authCode, String redirect, String clientId) {
    return (DiscoveryState) super.authenticateViaOAuth2AuthCode(authCode, redirect, clientId);
  }

  @Override
  protected DiscoveryState authenticateViaOAuth2AuthCode(String authCode, String redirect, String clientId, String clientSecret) {
    return (DiscoveryState) super.authenticateViaOAuth2AuthCode(authCode, redirect, clientId, clientSecret);
  }

  @Override
  protected DiscoveryState authenticateViaOAuth2ClientCredentials(String clientId, String clientSecret) {
    return (DiscoveryState) super.authenticateViaOAuth2ClientCredentials(clientId, clientSecret);
  }

  @Override
  protected DiscoveryState authenticateWithAccessToken(String accessToken) {
    return (DiscoveryState) super.authenticateWithAccessToken(accessToken);
  }

  @Override
  protected DiscoveryState authenticateViaOAuth2(MultivaluedMap<String, String> formData, StateTransitionOption... options) {
    return (DiscoveryState) super.authenticateViaOAuth2(formData, options);
  }

  public FamilyTreePersonState readPersonWithRelationships(String personId, StateTransitionOption... options) {
    Link pwrTemplate = getLink("person-with-relationships-query");
    if (pwrTemplate == null || pwrTemplate.getTemplate() == null) {
      return null;
    }
    String template = pwrTemplate.getTemplate();

    String uri;
    try {
      uri = UriTemplate.fromTemplate(template).set("person", personId).expand();
    }
    catch (VariableExpansionException e) {
      throw new GedcomxApplicationException(e);
    }
    catch (MalformedUriTemplateException e) {
      throw new GedcomxApplicationException(e);
    }

    ClientRequest request = createAuthenticatedFeedRequest().build(URI.create(uri), HttpMethod.GET);
    return ((FamilyTreeStateFactory)this.stateFactory).newPersonState(request, invoke(request, options), this.accessToken);
  }

  //todo: methods for all the other state transitions?

}
