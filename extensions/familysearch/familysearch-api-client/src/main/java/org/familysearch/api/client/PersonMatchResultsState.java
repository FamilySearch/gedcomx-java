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

import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;

import org.familysearch.api.client.util.FamilySearchOptions;
import org.familysearch.api.client.util.RequestUtil;
import org.familysearch.api.rt.Rel;
import org.familysearch.platform.ct.MatchStatus;
import org.gedcomx.Gedcomx;
import org.gedcomx.atom.AtomModel;
import org.gedcomx.atom.Entry;
import org.gedcomx.atom.Feed;
import org.gedcomx.conclusion.Identifier;
import org.gedcomx.conclusion.Person;
import org.gedcomx.links.Link;
import org.gedcomx.rs.client.PersonSearchResultsState;
import org.gedcomx.rs.client.PersonState;
import org.gedcomx.rs.client.StateTransitionOption;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.types.IdentifierType;

import jakarta.ws.rs.HttpMethod;
import jakarta.ws.rs.core.UriBuilder;
import java.net.URI;

/**
 * @author Ryan Heaton
 */
public class PersonMatchResultsState extends PersonSearchResultsState {

  protected PersonMatchResultsState(ClientRequest request, ClientResponse response, String accessToken, FamilySearchStateFactory stateFactory) {
    super(request, response, accessToken, stateFactory);
  }

  @Override
  protected PersonMatchResultsState clone(ClientRequest request, ClientResponse response) {
    return new PersonMatchResultsState(request, response, this.accessToken, (FamilySearchStateFactory) this.stateFactory);
  }

  @Override
  public PersonMatchResultsState ifSuccessful() {
    return (PersonMatchResultsState) super.ifSuccessful();
  }

  @Override
  public PersonMatchResultsState head(StateTransitionOption... options) {
    return (PersonMatchResultsState) super.head(options);
  }

  @Override
  public PersonMatchResultsState get(StateTransitionOption... options) {
    return (PersonMatchResultsState) super.get(options);
  }

  @Override
  public PersonMatchResultsState delete(StateTransitionOption... options) {
    return (PersonMatchResultsState) super.delete(options);
  }

  @Override
  public PersonMatchResultsState put(Feed e, StateTransitionOption... options) {
    return (PersonMatchResultsState) super.put(e, options);
  }

  @Override
  public PersonMatchResultsState post(Feed entity, StateTransitionOption... options) {
    return (PersonMatchResultsState) super.post(entity, options);
  }

  @Override
  public PersonMatchResultsState readNextPage(StateTransitionOption... options) {
    return (PersonMatchResultsState) super.readNextPage(options);
  }

  @Override
  public PersonMatchResultsState readPreviousPage(StateTransitionOption... options) {
    return (PersonMatchResultsState) super.readPreviousPage(options);
  }

  @Override
  public PersonMatchResultsState readFirstPage(StateTransitionOption... options) {
    return (PersonMatchResultsState) super.readFirstPage(options);
  }

  @Override
  public PersonMatchResultsState readLastPage(StateTransitionOption... options) {
    return (PersonMatchResultsState) super.readLastPage(options);
  }

  public PersonState readPerson(StateTransitionOption... options) {
    Link link = getLink(Rel.PERSON);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return ((FamilySearchStateFactory)this.stateFactory).newPersonState(request, invoke(request, options), this.accessToken);
  }

  public PersonNonMatchesState addNonMatch(Entry entry, StateTransitionOption... options) {
    Link link = getLink(Rel.NOT_A_MATCHES);
    if (link == null || link.getHref() == null) {
      return null;
    }

    Gedcomx entity = new Gedcomx();
    entity.addPerson(new Person().id(entry.getId().toString()));
    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest()).entity(entity).build(link.getHref().toURI(), HttpMethod.POST);
    return ((FamilySearchStateFactory)this.stateFactory).newPersonNonMatchesState(request, invoke(request, options), this.accessToken);
  }

  public PersonMatchResultsState updateMatchStatus(Entry entry, MatchStatus status, StateTransitionOption... options) {
    URI updateStatusUri = UriBuilder.fromUri(getSelfUri()).replaceQueryParam(FamilySearchOptions.STATUS, status.name().toLowerCase()).build();
    ClientRequest request = createAuthenticatedRequest()
      .type(GedcomxConstants.GEDCOMX_JSON_MEDIA_TYPE)
      .accept(AtomModel.ATOM_GEDCOMX_JSON_MEDIA_TYPE)
      .entity(new Gedcomx().person(new Person().identifier(new Identifier(entry.getId(), IdentifierType.Persistent))))
      .build(updateStatusUri, HttpMethod.POST);
    return ((FamilySearchStateFactory)this.stateFactory).newPersonMatchResultsState(request, invoke(request, options), this.accessToken);
  }

}
