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

import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import org.familysearch.api.client.FamilySearchCollectionState;
import org.familysearch.api.client.FamilySearchReferenceEnvironment;
import org.gedcomx.Gedcomx;
import org.gedcomx.conclusion.Identifier;
import org.gedcomx.conclusion.Person;
import org.gedcomx.links.Link;
import org.gedcomx.records.Collection;
import org.gedcomx.rs.client.PersonState;
import org.gedcomx.rs.client.SourceDescriptionsState;
import org.gedcomx.rs.client.StateTransitionOption;
import org.gedcomx.source.SourceDescription;
import org.gedcomx.types.ResourceType;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MultivaluedMap;
import java.net.URI;
import java.util.List;

public class GenealogiesTreeState extends FamilySearchCollectionState {

  public GenealogiesTreeState() {
    super();
  }

  public GenealogiesTreeState(FamilySearchReferenceEnvironment env) {
    super(env);
  }

  public GenealogiesTreeState(URI uri) {
    super(uri);
  }

  public GenealogiesTreeState(ClientRequest request, ClientResponse client, String accessToken, GenealogiesStateFactory stateFactory) {
    super(request, client, accessToken, stateFactory);
  }

  @Override
  protected GenealogiesTreeState clone(ClientRequest request, ClientResponse response) {
    return new GenealogiesTreeState(request, response, this.accessToken, (GenealogiesStateFactory) this.stateFactory);
  }

  @Override
  public GenealogiesTreeState ifSuccessful() {
    return (GenealogiesTreeState) super.ifSuccessful();
  }

  @Override
  public GenealogiesTreeState head(StateTransitionOption... options) {
    return (GenealogiesTreeState) super.head(options);
  }

  @Override
  public GenealogiesTreeState get(StateTransitionOption... options) {
    return (GenealogiesTreeState) super.get(options);
  }

  @Override
  public GenealogiesTreeState delete(StateTransitionOption... options) {
    return (GenealogiesTreeState) super.delete(options);
  }

  @Override
  public GenealogiesTreeState put(Gedcomx e, StateTransitionOption... options) {
    return (GenealogiesTreeState) super.put(e, options);
  }

  @Override
  public GenealogiesTreeState post(Gedcomx entity, StateTransitionOption... options) {
    return (GenealogiesTreeState) super.post(entity, options);
  }

  @Override
  public GenealogiesTreeState authenticateViaOAuth2Password(String username, String password, String clientId) {
    return (GenealogiesTreeState) super.authenticateViaOAuth2Password(username, password, clientId);
  }

  @Override
  public GenealogiesTreeState authenticateViaOAuth2Password(String username, String password, String clientId, String clientSecret) {
    return (GenealogiesTreeState) super.authenticateViaOAuth2Password(username, password, clientId, clientSecret);
  }

  @Override
  public GenealogiesTreeState authenticateViaOAuth2AuthCode(String authCode, String redirect, String clientId, String clientSecret) {
    return (GenealogiesTreeState) super.authenticateViaOAuth2AuthCode(authCode, redirect, clientId, clientSecret);
  }

  @Override
  public GenealogiesTreeState authenticateViaOAuth2AuthCode(String authCode, String redirect, String clientId) {
    return (GenealogiesTreeState) super.authenticateViaOAuth2AuthCode(authCode, redirect, clientId);
  }

  @Override
  public GenealogiesTreeState authenticateViaOAuth2ClientCredentials(String clientId, String clientSecret) {
    return (GenealogiesTreeState) super.authenticateViaOAuth2ClientCredentials(clientId, clientSecret);
  }

  @Override
  public GenealogiesTreeState authenticateViaOAuth2(MultivaluedMap<String, String> formData, StateTransitionOption... options) {
    return (GenealogiesTreeState) super.authenticateViaOAuth2(formData, options);
  }

  @Override
  public FamilySearchCollectionState addCollection(Collection collection, StateTransitionOption... options) {
    throw new UnsupportedOperationException("Can't add a collection to a tree");
  }

  @Override
  public FamilySearchCollectionState addCollection(Collection collection, SourceDescription sourceDescription, StateTransitionOption... options) {
    throw new UnsupportedOperationException("Can't add a collection to a tree");
  }

  @Override
  public GenealogiesPersonState addPerson(Person person, StateTransitionOption... options) {
    Gedcomx entity = new Gedcomx();
    entity.addPerson(person);
    return addPerson(entity, options);
  }

  @Override
  public GenealogiesPersonState addPerson(Gedcomx gx, StateTransitionOption... options) {
    return (GenealogiesPersonState) super.addPerson(gx, options);
  }

  @Override
  public GenealogiesPersonState readPerson(Person person, StateTransitionOption... options) {
    return (GenealogiesPersonState) super.readPerson(person, options);
  }

  public SourceDescriptionsState readPersonsByExternalIds(List<Identifier> identifiers, StateTransitionOption... options) {
    Link link = getLink("persons-by-external-ids");
    if (link == null || link.getHref() == null) {
      return null;
    }

    Gedcomx query = new Gedcomx();
    for (Identifier identifier : identifiers) {
      query = query.sourceDescription(new SourceDescription().resourceType(ResourceType.Person).identifier(identifier));
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().entity(query).build(link.getHref().toURI(), HttpMethod.POST);
    return ((GenealogiesStateFactory)this.stateFactory).newSourceDescriptionsState(request, invoke(request, options), this.accessToken);
  }
}
