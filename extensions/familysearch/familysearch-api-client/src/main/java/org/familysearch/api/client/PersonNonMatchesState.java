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
import org.familysearch.api.client.util.RequestUtil;
import org.familysearch.platform.rt.FamilySearchPlatformAnchorFinder;
import org.gedcomx.Gedcomx;
import org.gedcomx.conclusion.Person;
import org.gedcomx.links.AnchorElementSupport;
import org.gedcomx.links.Link;
import org.gedcomx.links.SupportsLinks;
import org.gedcomx.rs.client.PersonsState;
import org.gedcomx.rs.client.StateFactory;
import org.gedcomx.rs.client.StateTransitionOption;

import javax.ws.rs.HttpMethod;
import java.util.List;

/**
 * @author Ryan Heaton
 */
public class PersonNonMatchesState extends PersonsState {

  protected PersonNonMatchesState(ClientRequest request, ClientResponse response, String accessToken, StateFactory stateFactory) {
    super(request, response, accessToken, stateFactory);
  }

  @Override
  protected PersonNonMatchesState clone(ClientRequest request, ClientResponse response) {
    return new PersonNonMatchesState(request, response, this.accessToken, this.stateFactory);
  }

  @Override
  public PersonNonMatchesState ifSuccessful() {
    return (PersonNonMatchesState) super.ifSuccessful();
  }

  @Override
  public PersonNonMatchesState head(StateTransitionOption... options) {
    return (PersonNonMatchesState) super.head(options);
  }

  @Override
  public PersonNonMatchesState options(StateTransitionOption... options) {
    return (PersonNonMatchesState) super.options(options);
  }

  @Override
  public PersonNonMatchesState get(StateTransitionOption... options) {
    return (PersonNonMatchesState) super.get(options);
  }

  @Override
  public PersonNonMatchesState delete(StateTransitionOption... options) {
    return (PersonNonMatchesState) super.delete(options);
  }

  @Override
  public PersonNonMatchesState put(Gedcomx e, StateTransitionOption... options) {
    return (PersonNonMatchesState) super.put(e, options);
  }

  @Override
  public PersonNonMatchesState post(Gedcomx entity, StateTransitionOption... options) {
    return (PersonNonMatchesState) super.post(entity, options);
  }

  @Override
  protected Gedcomx loadEntity(ClientResponse response) {
    return response.getEntity(Gedcomx.class);
  }

  @Override
  protected AnchorElementSupport findAnchor() {
    AnchorElementSupport anchor = null;
    if (this.entity != null) {
      anchor = FamilySearchPlatformAnchorFinder.findAnchor(this.entity);
    }
    return anchor;
  }

  public List<Person> getPersons() {
    Gedcomx entity = getEntity();
    return entity == null ? null : entity.getPersons();
  }

  public PersonNonMatchesState addNonMatch(Person person, StateTransitionOption... options) {
    return post(new Gedcomx().person(person), options);
  }

  public PersonNonMatchesState removeNonMatch(Person nonMatch, StateTransitionOption... options) {
    Link link = nonMatch.getLink(Rel.NOT_A_MATCH);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest()).build(link.getHref().toURI(), HttpMethod.DELETE);
    return ((FamilySearchStateFactory)this.stateFactory).newPersonNonMatchesState(request, invoke(request, options), this.accessToken);
  }

  @Override
  public PersonNonMatchesState readNextPage(StateTransitionOption... options) {
    return (PersonNonMatchesState) super.readNextPage(options);
  }

  @Override
  public PersonNonMatchesState readPreviousPage(StateTransitionOption... options) {
    return (PersonNonMatchesState) super.readPreviousPage(options);
  }

  @Override
  public PersonNonMatchesState readFirstPage(StateTransitionOption... options) {
    return (PersonNonMatchesState) super.readFirstPage(options);
  }

  @Override
  public PersonNonMatchesState readLastPage(StateTransitionOption... options) {
    return (PersonNonMatchesState) super.readLastPage(options);
  }
}
