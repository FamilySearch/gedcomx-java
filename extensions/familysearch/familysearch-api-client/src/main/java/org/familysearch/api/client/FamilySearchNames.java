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

import com.damnhandy.uri.template.MalformedUriTemplateException;
import com.damnhandy.uri.template.UriTemplate;
import com.damnhandy.uri.template.VariableExpansionException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.familysearch.api.client.util.NameSearchQueryBuilder;
import org.gedcomx.Gedcomx;
import org.gedcomx.links.Link;
import org.gedcomx.rs.client.*;
import org.gedcomx.rt.GedcomxConstants;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MultivaluedMap;
import java.net.URI;

/**
 * @author chapmantk
 * @since 2014-07-08
 */
public class FamilySearchNames extends FamilySearchCollectionState {

  public static final String URI = "https://api.familysearch.org/platform/collections/names";
  public static final String SANDBOX_URI = "https://api-integ.familysearch.org/platform/collections/names";

  public FamilySearchNames() {
    this(false);
  }

  public FamilySearchNames(boolean sandbox) {
    this(sandbox ? FamilySearchReferenceEnvironment.SANDBOX : FamilySearchReferenceEnvironment.PRODUCTION);
  }

  public FamilySearchNames(FamilySearchReferenceEnvironment env) {
    this(env.getNamesUri());
  }

  public FamilySearchNames(URI uri) {
    this(uri, new FamilySearchStateFactory());
  }

  private FamilySearchNames(URI uri, FamilySearchStateFactory stateFactory) {
    this(uri, stateFactory.loadDefaultClient(), stateFactory);
  }

  private FamilySearchNames(URI uri, Client client, FamilySearchStateFactory stateFactory) {
    this(ClientRequest.create().accept(GedcomxConstants.GEDCOMX_JSON_MEDIA_TYPE).build(uri, HttpMethod.GET), client, stateFactory);
  }

  private FamilySearchNames(ClientRequest request, Client client, FamilySearchStateFactory stateFactory) {
    this(request, client.handle(request), null, stateFactory);
  }

  protected FamilySearchNames(ClientRequest request, ClientResponse client, String accessToken, FamilySearchStateFactory stateFactory) {
    super(request, client, accessToken, stateFactory);
  }

  @Override
  protected FamilySearchNames clone(ClientRequest request, ClientResponse response) {
    return new FamilySearchNames(request, response, this.accessToken, (FamilySearchStateFactory)this.stateFactory);
  }

  @Override
  public FamilySearchNames ifSuccessful() {
    return (FamilySearchNames) super.ifSuccessful();
  }

  @Override
  public FamilySearchNames head(StateTransitionOption... options) {
    return (FamilySearchNames) super.head(options);
  }

  @Override
  public FamilySearchNames get(StateTransitionOption... options) {
    return (FamilySearchNames) super.get(options);
  }

  @Override
  public FamilySearchNames delete(StateTransitionOption... options) {
    return (FamilySearchNames) super.delete(options);
  }

  @Override
  public FamilySearchNames put(Gedcomx e, StateTransitionOption... options) {
    return (FamilySearchNames) super.put(e, options);
  }

  @Override
  public FamilySearchNames post(Gedcomx entity, StateTransitionOption... options) {
    return (FamilySearchNames) super.post(entity, options);
  }

  @Override
  public FamilySearchNames authenticateViaOAuth2Password(String username, String password, String clientId) {
    return (FamilySearchNames) super.authenticateViaOAuth2Password(username, password, clientId);
  }

  @Override
  public FamilySearchNames authenticateViaOAuth2Password(String username, String password, String clientId, String clientSecret) {
    return (FamilySearchNames) super.authenticateViaOAuth2Password(username, password, clientId, clientSecret);
  }

  @Override
  public FamilySearchNames authenticateViaOAuth2AuthCode(String authCode, String redirect, String clientId, String clientSecret) {
    return (FamilySearchNames) super.authenticateViaOAuth2AuthCode(authCode, redirect, clientId, clientSecret);
  }

  @Override
  public FamilySearchNames authenticateViaOAuth2AuthCode(String authCode, String redirect, String clientId) {
    return (FamilySearchNames) super.authenticateViaOAuth2AuthCode(authCode, redirect, clientId);
  }

  @Override
  public FamilySearchNames authenticateViaOAuth2ClientCredentials(String clientId, String clientSecret) {
    return (FamilySearchNames) super.authenticateViaOAuth2ClientCredentials(clientId, clientSecret);
  }

  @Override
  public FamilySearchNames authenticateViaOAuth2(MultivaluedMap<String, String> formData, StateTransitionOption... options) {
    return (FamilySearchNames) super.authenticateViaOAuth2(formData);
  }

  public FamilySearchNames authenticateViaUnauthenticatedAccess(String clientId, String ipAddress) {

    MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
    formData.add("grant_type", "unauthenticated_session");
    formData.add("client_id", clientId);
    formData.add("ip_address", ipAddress);

    return this.authenticateViaOAuth2(formData);
  }

  public NameSearchResultsState searchForNames(NameSearchQueryBuilder query, StateTransitionOption... options) {
    return searchForNames(query.build(), options);
  }

  public NameSearchResultsState searchForNames(String query, StateTransitionOption... options) {
    Link searchLink = getLink(Rel.NAME_SEARCH);
    if (searchLink == null || searchLink.getTemplate() == null) {
      return null;
    }
    String template = searchLink.getTemplate();

    String uri;
    try {
      uri = UriTemplate.fromTemplate(template).set("q", query).expand().replace("\"", "%22");   //UriTemplate does not encode DQUOTE: see RFC 6570 (http://tools.ietf.org/html/rfc6570#section-2.1)
    }
    catch (VariableExpansionException e) {
      throw new GedcomxApplicationException(e);
    }
    catch (MalformedUriTemplateException e) {
      throw new GedcomxApplicationException(e);
    }

    ClientRequest request = createAuthenticatedFeedRequest().build(java.net.URI.create(uri), HttpMethod.GET);
    return ((FamilySearchStateFactory)this.stateFactory).newNameSearchResultsState(request, invoke(request, options), this.accessToken);
  }

}
