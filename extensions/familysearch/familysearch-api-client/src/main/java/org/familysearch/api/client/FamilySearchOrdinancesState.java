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
import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.familysearch.api.client.ft.FamilyTreeStateFactory;
import org.familysearch.api.client.util.RequestUtil;
import org.gedcomx.Gedcomx;
import org.gedcomx.links.Link;
import org.gedcomx.rs.client.GedcomxApplicationException;
import org.gedcomx.rs.client.StateTransitionOption;
import org.gedcomx.rt.GedcomxConstants;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MultivaluedMap;
import java.net.URI;

public class FamilySearchOrdinancesState extends FamilySearchCollectionState {

  public static final String URI = "https://familysearch.org/platform/collections/ordinances";
  public static final String SANDBOX_URI = "https://sandbox.familysearch.org/platform/collections/ordinances";

  public FamilySearchOrdinancesState() {
    this(false);
  }

  public FamilySearchOrdinancesState(boolean sandbox) {
    this(sandbox ? FamilySearchReferenceEnvironment.SANDBOX : FamilySearchReferenceEnvironment.PRODUCTION);
  }

  public FamilySearchOrdinancesState(FamilySearchReferenceEnvironment env) {
    this(env.getOrdinanceReservationsUri());
  }

  public FamilySearchOrdinancesState(java.net.URI uri) {
    this(uri, new FamilyTreeStateFactory());
  }

  private FamilySearchOrdinancesState(URI uri, FamilyTreeStateFactory stateFactory) {
    this(uri, stateFactory.loadDefaultClient(), stateFactory);
  }

  private FamilySearchOrdinancesState(URI uri, com.sun.jersey.api.client.Client client, FamilyTreeStateFactory stateFactory) {
    this(ClientRequest.create().accept(GedcomxConstants.GEDCOMX_JSON_MEDIA_TYPE).build(uri, HttpMethod.GET), client, stateFactory);
  }

  private FamilySearchOrdinancesState(ClientRequest request, com.sun.jersey.api.client.Client client, FamilyTreeStateFactory stateFactory) {
    this(request, client.handle(request), null, stateFactory);
  }

  protected FamilySearchOrdinancesState(ClientRequest request, ClientResponse client, String accessToken, FamilyTreeStateFactory stateFactory) {
    super(request, client, accessToken, stateFactory);
  }

  @Override
  protected FamilySearchOrdinancesState clone(ClientRequest request, ClientResponse response) {
    return new FamilySearchOrdinancesState(request, response, this.accessToken, (FamilyTreeStateFactory) this.stateFactory);
  }

  @Override
  public FamilySearchOrdinancesState ifSuccessful() {
    return (FamilySearchOrdinancesState) super.ifSuccessful();
  }

  @Override
  public FamilySearchOrdinancesState head(StateTransitionOption... options) {
    return (FamilySearchOrdinancesState) super.head(options);
  }

  @Override
  public FamilySearchOrdinancesState get(StateTransitionOption... options) {
    return (FamilySearchOrdinancesState) super.get(options);
  }

  @Override
  public FamilySearchOrdinancesState delete(StateTransitionOption... options) {
    return (FamilySearchOrdinancesState) super.delete(options);
  }

  @Override
  public FamilySearchOrdinancesState put(Gedcomx e, StateTransitionOption... options) {
    return (FamilySearchOrdinancesState) super.put(e, options);
  }

  @Override
  public FamilySearchOrdinancesState post(Gedcomx entity, StateTransitionOption... options) {
    return (FamilySearchOrdinancesState) super.post(entity, options);
  }

  @Override
  public FamilySearchOrdinancesState authenticateViaOAuth2Password(String username, String password, String clientId) {
    return (FamilySearchOrdinancesState) super.authenticateViaOAuth2Password(username, password, clientId);
  }

  @Override
  public FamilySearchOrdinancesState authenticateViaOAuth2Password(String username, String password, String clientId, String clientSecret) {
    return (FamilySearchOrdinancesState) super.authenticateViaOAuth2Password(username, password, clientId, clientSecret);
  }

  @Override
  public FamilySearchOrdinancesState authenticateViaOAuth2AuthCode(String authCode, String redirect, String clientId, String clientSecret) {
    return (FamilySearchOrdinancesState) super.authenticateViaOAuth2AuthCode(authCode, redirect, clientId, clientSecret);
  }

  @Override
  public FamilySearchOrdinancesState authenticateViaOAuth2AuthCode(String authCode, String redirect, String clientId) {
    return (FamilySearchOrdinancesState) super.authenticateViaOAuth2AuthCode(authCode, redirect, clientId);
  }

  @Override
  public FamilySearchOrdinancesState authenticateViaOAuth2ClientCredentials(String clientId, String clientSecret) {
    return (FamilySearchOrdinancesState) super.authenticateViaOAuth2ClientCredentials(clientId, clientSecret);
  }

  @Override
  public FamilySearchOrdinancesState authenticateViaOAuth2(MultivaluedMap<String, String> formData, StateTransitionOption... options) {
    return (FamilySearchOrdinancesState) super.authenticateViaOAuth2(formData);
  }

  public FamilySearchOrdinancesState authenticateViaUnauthenticatedAccess(String clientId, String ipAddress) {

    MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
    formData.add("grant_type", "unauthenticated_session");
    formData.add("client_id", clientId);
    formData.add("ip_address", ipAddress);

    return this.authenticateViaOAuth2(formData);
  }

  @Override
  public FamilySearchCollectionState readCollection(StateTransitionOption... options) {
    return (FamilySearchCollectionState) super.readCollection();
  }

  public OrdinanceStatusState readPersonOrdinanceStatus(String personId, StateTransitionOption... options) {
    Link link = getLink(Rel.ORDINANCES_STATUS);
    if (link == null || link.getTemplate() == null) {
      return null;
    }
    String template = link.getTemplate();

    String uri;
    try {
      uri = UriTemplate.fromTemplate(template).set("person", personId).expand().replace("\"", "%22");   //UriTemplate does not encode DQUOTE: see RFC 6570 (http://tools.ietf.org/html/rfc6570#section-2.1)
    }
    catch (VariableExpansionException e) {
      throw new GedcomxApplicationException(e);
    }
    catch (MalformedUriTemplateException e) {
      throw new GedcomxApplicationException(e);
    }

    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest()).build(java.net.URI.create(uri), HttpMethod.GET);
    return ((FamilySearchStateFactory)this.stateFactory).newOrdinanceStatusState(request, invoke(request, options), this.accessToken);
  }

  public OrdinanceStatusState readFamilyOrdinanceStatus(String fatherId, String motherId, StateTransitionOption... options) {
    Link link = getLink(Rel.ORDINANCES_STATUS);
    if (link == null || link.getTemplate() == null) {
      return null;
    }
    String template = link.getTemplate();

    String uri;
    try {
      uri = UriTemplate.fromTemplate(template).set("father", fatherId).set("mother", motherId).expand().replace("\"", "%22");   //UriTemplate does not encode DQUOTE: see RFC 6570 (http://tools.ietf.org/html/rfc6570#section-2.1)
    }
    catch (VariableExpansionException e) {
      throw new GedcomxApplicationException(e);
    }
    catch (MalformedUriTemplateException e) {
      throw new GedcomxApplicationException(e);
    }

    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest()).build(java.net.URI.create(uri), HttpMethod.GET);
    return ((FamilySearchStateFactory)this.stateFactory).newOrdinanceStatusState(request, invoke(request, options), this.accessToken);
  }

}
