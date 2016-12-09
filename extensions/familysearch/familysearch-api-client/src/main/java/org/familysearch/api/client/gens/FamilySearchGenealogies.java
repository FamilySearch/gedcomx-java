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
package org.familysearch.api.client.gens;

import com.damnhandy.uri.template.MalformedUriTemplateException;
import com.damnhandy.uri.template.UriTemplate;
import com.damnhandy.uri.template.VariableExpansionException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.familysearch.api.client.FamilySearchCollectionState;
import org.familysearch.api.client.FamilySearchPersonState;
import org.familysearch.api.client.FamilySearchReferenceEnvironment;
import org.familysearch.api.client.Rel;
import org.familysearch.api.client.util.RequestUtil;
import org.gedcomx.Gedcomx;
import org.gedcomx.conclusion.Person;
import org.gedcomx.links.Link;
import org.gedcomx.records.Collection;
import org.gedcomx.rs.client.GedcomxApplicationException;
import org.gedcomx.rs.client.StateTransitionOption;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.source.SourceDescription;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MultivaluedMap;
import java.net.URI;

/**
 * @author Ryan Heaton
 */
public class FamilySearchGenealogies extends FamilySearchCollectionState {

  public static final String URI = "https://familysearch.org/platform/collections/genealogies";
  public static final String SANDBOX_URI = "https://integration.familysearch.org/platform/collections/genealogies";

  public FamilySearchGenealogies() {
    this(false);
  }

  public FamilySearchGenealogies(boolean sandbox) {
    this(sandbox ? FamilySearchReferenceEnvironment.SANDBOX : FamilySearchReferenceEnvironment.PRODUCTION);
  }

  public FamilySearchGenealogies(FamilySearchReferenceEnvironment env) {
    this(env.getMemoriesUri());
  }

  public FamilySearchGenealogies(URI uri) {
    this(uri, new GenealogiesStateFactory());
  }

  private FamilySearchGenealogies(URI uri, GenealogiesStateFactory stateFactory) {
    this(uri, stateFactory.loadDefaultClient(), stateFactory);
  }

  private FamilySearchGenealogies(URI uri, Client client, GenealogiesStateFactory stateFactory) {
    this(ClientRequest.create().accept(GedcomxConstants.GEDCOMX_JSON_MEDIA_TYPE).build(uri, HttpMethod.GET), client, stateFactory);
  }

  private FamilySearchGenealogies(ClientRequest request, Client client, GenealogiesStateFactory stateFactory) {
    this(request, client.handle(request), null, stateFactory);
  }

  protected FamilySearchGenealogies(ClientRequest request, ClientResponse client, String accessToken, GenealogiesStateFactory stateFactory) {
    super(request, client, accessToken, stateFactory);
  }

  @Override
  protected FamilySearchGenealogies clone(ClientRequest request, ClientResponse response) {
    return new FamilySearchGenealogies(request, response, this.accessToken, (GenealogiesStateFactory) this.stateFactory);
  }

  @Override
  public FamilySearchGenealogies ifSuccessful() {
    return (FamilySearchGenealogies) super.ifSuccessful();
  }

  @Override
  public FamilySearchGenealogies head(StateTransitionOption... options) {
    return (FamilySearchGenealogies) super.head(options);
  }

  @Override
  public FamilySearchGenealogies get(StateTransitionOption... options) {
    return (FamilySearchGenealogies) super.get(options);
  }

  @Override
  public FamilySearchGenealogies delete(StateTransitionOption... options) {
    return (FamilySearchGenealogies) super.delete(options);
  }

  @Override
  public FamilySearchGenealogies put(Gedcomx e, StateTransitionOption... options) {
    return (FamilySearchGenealogies) super.put(e, options);
  }

  @Override
  public FamilySearchGenealogies post(Gedcomx entity, StateTransitionOption... options) {
    return (FamilySearchGenealogies) super.post(entity, options);
  }

  @Override
  public FamilySearchGenealogies authenticateViaOAuth2Password(String username, String password, String clientId) {
    return (FamilySearchGenealogies) super.authenticateViaOAuth2Password(username, password, clientId);
  }

  @Override
  public FamilySearchGenealogies authenticateViaOAuth2Password(String username, String password, String clientId, String clientSecret) {
    return (FamilySearchGenealogies) super.authenticateViaOAuth2Password(username, password, clientId, clientSecret);
  }

  @Override
  public FamilySearchGenealogies authenticateViaOAuth2AuthCode(String authCode, String redirect, String clientId, String clientSecret) {
    return (FamilySearchGenealogies) super.authenticateViaOAuth2AuthCode(authCode, redirect, clientId, clientSecret);
  }

  @Override
  public FamilySearchGenealogies authenticateViaOAuth2AuthCode(String authCode, String redirect, String clientId) {
    return (FamilySearchGenealogies) super.authenticateViaOAuth2AuthCode(authCode, redirect, clientId);
  }

  @Override
  public FamilySearchGenealogies authenticateViaOAuth2ClientCredentials(String clientId, String clientSecret) {
    return (FamilySearchGenealogies) super.authenticateViaOAuth2ClientCredentials(clientId, clientSecret);
  }

  @Override
  public FamilySearchGenealogies authenticateViaOAuth2(MultivaluedMap<String, String> formData, StateTransitionOption... options) {
    return (FamilySearchGenealogies) super.authenticateViaOAuth2(formData);
  }

  public FamilySearchGenealogies authenticateViaUnauthenticatedAccess(String clientId, String ipAddress) {

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

  public FamilySearchPersonState readPersonById(String id, StateTransitionOption... options) {
    Link link = getLink(Rel.PERSON);
    if (link == null || link.getTemplate() == null) {
      return null;
    }

    String template = link.getTemplate();
    String uri;
    try{
      uri = UriTemplate.fromTemplate(template).set("pid", id).expand();
    }
    catch (VariableExpansionException e) {
      throw new GedcomxApplicationException(e);
    }
    catch (MalformedUriTemplateException e) {
      throw new GedcomxApplicationException(e);
    }

    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest()).build(java.net.URI.create(uri), HttpMethod.GET);
    return ((GenealogiesStateFactory)this.stateFactory).newPersonState(request, invoke(request, options), this.accessToken);
  }

  @Override
  public GenealogiesTreeState addCollection(Collection collection, StateTransitionOption... options) {
    return (GenealogiesTreeState) super.addCollection(collection, options);
  }

  @Override
  public GenealogiesTreeState addCollection(SourceDescription sourceDescription, StateTransitionOption... options) {
    return addCollection(null, sourceDescription, options);
  }

  @Override
  public GenealogiesTreeState addCollection(Collection collection, SourceDescription sourceDescription, StateTransitionOption... options) {
    return (GenealogiesTreeState) super.addCollection(collection, sourceDescription, options);
  }

  @Override
  public GenealogiesPersonState readPerson(Person person, StateTransitionOption... options) {
    return (GenealogiesPersonState) super.readPerson(person, options);
  }
}
