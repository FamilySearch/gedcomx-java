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
import org.familysearch.api.client.util.RequestUtil;
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
public class FamilySearchPlaces extends FamilySearchCollectionState {

  public static final String URI = "https://familysearch.org/platform/collections/places";
  public static final String SANDBOX_URI = "https://sandbox.familysearch.org/platform/collections/places";

  public FamilySearchPlaces() {
    this(false);
  }

  public FamilySearchPlaces(boolean sandbox) {
    this(java.net.URI.create(sandbox ? SANDBOX_URI : URI));
  }

  public FamilySearchPlaces(URI uri) {
    this(uri, new FamilySearchStateFactory());
  }

  private FamilySearchPlaces(URI uri, FamilySearchStateFactory stateFactory) {
    this(uri, stateFactory.loadDefaultClient(), stateFactory);
  }

  private FamilySearchPlaces(URI uri, Client client, FamilySearchStateFactory stateFactory) {
    this(ClientRequest.create().accept(GedcomxConstants.GEDCOMX_JSON_MEDIA_TYPE).build(uri, HttpMethod.GET), client, stateFactory);
  }

  private FamilySearchPlaces(ClientRequest request, Client client, FamilySearchStateFactory stateFactory) {
    this(request, client.handle(request), null, stateFactory);
  }

  protected FamilySearchPlaces(ClientRequest request, ClientResponse client, String accessToken, FamilySearchStateFactory stateFactory) {
    super(request, client, accessToken, stateFactory);
  }

  @Override
  protected FamilySearchPlaces clone(ClientRequest request, ClientResponse response) {
    return new FamilySearchPlaces(request, response, this.accessToken, (FamilySearchStateFactory)this.stateFactory);
  }

  @Override
  public FamilySearchPlaces ifSuccessful() {
    return (FamilySearchPlaces) super.ifSuccessful();
  }

  @Override
  public FamilySearchPlaces head(StateTransitionOption... options) {
    return (FamilySearchPlaces) super.head(options);
  }

  @Override
  public FamilySearchPlaces get(StateTransitionOption... options) {
    return (FamilySearchPlaces) super.get(options);
  }

  @Override
  public FamilySearchPlaces delete(StateTransitionOption... options) {
    return (FamilySearchPlaces) super.delete(options);
  }

  @Override
  public FamilySearchPlaces put(Gedcomx e, StateTransitionOption... options) {
    return (FamilySearchPlaces) super.put(e, options);
  }

  @Override
  public FamilySearchPlaces post(Gedcomx entity, StateTransitionOption... options) {
    return (FamilySearchPlaces) super.post(entity, options);
  }

  @Override
  public FamilySearchPlaces authenticateViaOAuth2Password(String username, String password, String clientId) {
    return (FamilySearchPlaces) super.authenticateViaOAuth2Password(username, password, clientId);
  }

  @Override
  public FamilySearchPlaces authenticateViaOAuth2Password(String username, String password, String clientId, String clientSecret) {
    return (FamilySearchPlaces) super.authenticateViaOAuth2Password(username, password, clientId, clientSecret);
  }

  @Override
  public FamilySearchPlaces authenticateViaOAuth2AuthCode(String authCode, String redirect, String clientId, String clientSecret) {
    return (FamilySearchPlaces) super.authenticateViaOAuth2AuthCode(authCode, redirect, clientId, clientSecret);
  }

  @Override
  public FamilySearchPlaces authenticateViaOAuth2AuthCode(String authCode, String redirect, String clientId) {
    return (FamilySearchPlaces) super.authenticateViaOAuth2AuthCode(authCode, redirect, clientId);
  }

  @Override
  public FamilySearchPlaces authenticateViaOAuth2ClientCredentials(String clientId, String clientSecret) {
    return (FamilySearchPlaces) super.authenticateViaOAuth2ClientCredentials(clientId, clientSecret);
  }

  @Override
  public FamilySearchPlaces authenticateViaOAuth2(MultivaluedMap<String, String> formData, StateTransitionOption... options) {
    return (FamilySearchPlaces) super.authenticateViaOAuth2(formData);
  }

  public FamilySearchPlaces authenticateViaUnauthenticatedAccess(String clientId, String ipAddress) {

    MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
    formData.add("grant_type", "unauthenticated_session");
    formData.add("client_id", clientId);
    formData.add("ip_address", ipAddress);

    return this.authenticateViaOAuth2(formData);
  }

  /**
   * Read the list of place type groups
   *
   * @param options state transition options to be included
   * @return the list of place type groups
   */
  public VocabElementListState readPlaceTypeGroups(StateTransitionOption... options) {
    return this.readPlaceElementList(Rel.PLACE_TYPE_GROUPS, options);
  }

  /**
   * Read the list of place types
   *
   * @param options state transition options to be included
   * @return the list of place types
   */
  public VocabElementListState readPlaceTypes(StateTransitionOption... options) {
    return this.readPlaceElementList(Rel.PLACE_TYPES, options);
  }

  /**
   * Read the VocabElementList from the given path
   *
   * @param options state transition options to be included
   * @return a VocabElementListState from the given path
   */
  private VocabElementListState readPlaceElementList(String path, StateTransitionOption... options) {
    Link link = getLink(path);
    if (null == link || null == link.getTemplate()) {
      return null;
    }

    String template = link.getTemplate();
    String uri;
    try{
      uri = UriTemplate.fromTemplate(template).expand();
    }
    catch (VariableExpansionException e) {
      throw new GedcomxApplicationException(e);
    }
    catch (MalformedUriTemplateException e) {
      throw new GedcomxApplicationException(e);
    }

    ClientRequest request = RequestUtil.applyFamilySearchJson(createAuthenticatedRequest()).build(java.net.URI.create(uri), HttpMethod.GET);
    return this.stateFactory.newVocabElementListState(request, invoke(request, options), this.accessToken);
  }

  /**
   * Read the place type group with the given id
   *
   * @param id the id of the place type group to be read
   * @param options state transition options to be included
   * @return the place type group with the given id
   */
  public VocabElementListState readPlaceTypeGroupById(String id, StateTransitionOption... options) {
    Link link = getLink(Rel.PLACE_TYPE_GROUP);
    if (link == null || link.getTemplate() == null) {
      return null;
    }

    String template = link.getTemplate();
    String uri;
    try{
      uri = UriTemplate.fromTemplate(template).set("ptgid", id).expand();
    }
    catch (VariableExpansionException e) {
      throw new GedcomxApplicationException(e);
    }
    catch (MalformedUriTemplateException e) {
      throw new GedcomxApplicationException(e);
    }

    ClientRequest request = RequestUtil.applyFamilySearchJson(createAuthenticatedRequest()).build(java.net.URI.create(uri), HttpMethod.GET);
    return this.stateFactory.newVocabElementListState(request, invoke(request, options), this.accessToken);
  }

  /**
   * Read the place type with the given id
   *
   * @param id the id of the place type to be read
   * @param options state transition options to be included
   * @return the place type with the given id
   */
  public VocabElementState readPlaceTypeById(String id, StateTransitionOption... options) {
    Link link = getLink(Rel.PLACE_TYPE);
    if (link == null || link.getTemplate() == null) {
      return null;
    }

    String template = link.getTemplate();
    String uri;
    try{
      uri = UriTemplate.fromTemplate(template).set("ptid", id).expand();
    }
    catch (VariableExpansionException e) {
      throw new GedcomxApplicationException(e);
    }
    catch (MalformedUriTemplateException e) {
      throw new GedcomxApplicationException(e);
    }

    ClientRequest request = RequestUtil.applyFamilySearchJson(createAuthenticatedRequest()).build(java.net.URI.create(uri), HttpMethod.GET);
    return this.stateFactory.newVocabElementState(request, invoke(request, options), this.accessToken);
  }

  /**
   *
   */
  public PlaceGroupState readPlaceGroupById(String id, StateTransitionOption... options) {
    Link link = getLink(Rel.PLACE_GROUP);
    if (link == null || link.getTemplate() == null) {
      return null;
    }
    String template = link.getTemplate();
    String uri;
    try {
      uri = UriTemplate.fromTemplate(template).set("pgid", id).expand();
    }
    catch (VariableExpansionException e) {
      throw new GedcomxApplicationException(e);
    }
    catch (MalformedUriTemplateException e) {
      throw new GedcomxApplicationException(e);
    }

    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest()).build(java.net.URI.create(uri), HttpMethod.GET);
    return this.stateFactory.newPlaceGroupState(request, invoke(request, options), this.accessToken);
  }

}
