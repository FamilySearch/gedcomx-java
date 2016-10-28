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

import java.net.URI;
import java.util.List;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MultivaluedMap;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import org.gedcomx.Gedcomx;
import org.gedcomx.rs.client.PersonsState;
import org.gedcomx.rs.client.StateTransitionOption;

import org.familysearch.api.client.Rel;
import org.familysearch.platform.FamilySearchPlatform;
import org.familysearch.platform.ct.ChildAndParentsRelationship;

/*
    Family tree specific persons state
 */
public class FamilyTreePersonsState extends PersonsState {

  private final String selfRel;

  public FamilyTreePersonsState(URI uri) {
    this(uri, new FamilyTreeStateFactory());
  }

  private FamilyTreePersonsState(URI uri, FamilyTreeStateFactory stateFactory) {
    this(uri, stateFactory.loadDefaultClient(), stateFactory);
  }

  private FamilyTreePersonsState(URI uri, Client client, FamilyTreeStateFactory stateFactory) {
    this(ClientRequest.create().accept(FamilySearchPlatform.JSON_MEDIA_TYPE).build(uri, HttpMethod.GET), client, stateFactory);
  }

  private FamilyTreePersonsState(ClientRequest request, Client client, FamilyTreeStateFactory stateFactory) {
    this(request, client.handle(request), null, stateFactory);
  }

  protected FamilyTreePersonsState(ClientRequest request, ClientResponse response, String accessToken, FamilyTreeStateFactory stateFactory) {
    this(request, response, accessToken, Rel.PERSONS, stateFactory);
  }

  private FamilyTreePersonsState(ClientRequest request, ClientResponse response, String accessToken, String selfRel, FamilyTreeStateFactory stateFactory) {
    super(request, response, accessToken, stateFactory);
    this.selfRel = selfRel;
  }

  @Override
  public String getSelfRel() {
    return this.selfRel;
  }

  @Override
  protected FamilyTreePersonsState clone(ClientRequest request, ClientResponse response) {
    return new FamilyTreePersonsState(request, response, this.accessToken, getSelfRel(), (FamilyTreeStateFactory) this.stateFactory);
  }

  @Override
  protected FamilySearchPlatform loadEntity(ClientResponse response) {
    return response.getEntity(FamilySearchPlatform.class);
  }

  @Override
  public FamilySearchPlatform getEntity() {
    return (FamilySearchPlatform) super.getEntity();
  }

  public List<ChildAndParentsRelationship> getChildAndParentsRelationships() {
    return getEntity() == null ? null : getEntity().getChildAndParentsRelationships();
  }

  @Override
  public FamilyTreePersonsState ifSuccessful() {
    return (FamilyTreePersonsState) super.ifSuccessful();
  }

  @Override
  public FamilyTreePersonsState head(StateTransitionOption... options) {
    return (FamilyTreePersonsState) super.head(options);
  }

  @Override
  public FamilyTreePersonsState get(StateTransitionOption... options) {
    return (FamilyTreePersonsState) super.get(options);
  }

  @Override
  public FamilyTreePersonsState delete(StateTransitionOption... options) {
    return (FamilyTreePersonsState) super.delete(options);
  }

  @Override
  public FamilyTreePersonsState put(Gedcomx e, StateTransitionOption... options) {
    return (FamilyTreePersonsState) super.put(e, options);
  }

  @Override
  public FamilyTreePersonsState post(Gedcomx entity, StateTransitionOption... options) {
    return (FamilyTreePersonsState)super.post(entity, options);
  }

  @Override
  public FamilyTreePersonsState authenticateViaOAuth2Password(String username, String password, String clientId) {
    return (FamilyTreePersonsState) super.authenticateViaOAuth2Password(username, password, clientId);
  }

  @Override
  public FamilyTreePersonsState authenticateViaOAuth2Password(String username, String password, String clientId, String clientSecret) {
    return (FamilyTreePersonsState) super.authenticateViaOAuth2Password(username, password, clientId, clientSecret);
  }

  @Override
  public FamilyTreePersonsState authenticateViaOAuth2AuthCode(String authCode, String redirect, String clientId) {
    return (FamilyTreePersonsState) super.authenticateViaOAuth2AuthCode(authCode, redirect, clientId);
  }

  @Override
  public FamilyTreePersonsState authenticateViaOAuth2AuthCode(String authCode, String redirect, String clientId, String clientSecret) {
    return (FamilyTreePersonsState) super.authenticateViaOAuth2AuthCode(authCode, redirect, clientId, clientSecret);
  }

  @Override
  public FamilyTreePersonsState authenticateViaOAuth2ClientCredentials(String clientId, String clientSecret) {
    return (FamilyTreePersonsState) super.authenticateViaOAuth2ClientCredentials(clientId, clientSecret);
  }

  @Override
  public FamilyTreePersonsState authenticateViaOAuth2(MultivaluedMap<String, String> formData, StateTransitionOption... options) {
    return (FamilyTreePersonsState) super.authenticateViaOAuth2(formData, options);
  }

  @Override
  public FamilySearchFamilyTree readCollection(StateTransitionOption... options) {
    return (FamilySearchFamilyTree) super.readCollection(options);
  }

}
