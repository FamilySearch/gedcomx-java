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
package org.gedcomx.rs.client;

import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import org.gedcomx.atom.Entry;
import org.gedcomx.atom.Feed;
import org.gedcomx.conclusion.Person;
import org.gedcomx.links.Link;
import org.gedcomx.links.SupportsLinks;
import org.gedcomx.rs.Rel;

import javax.ws.rs.HttpMethod;

/**
 * @author Ryan Heaton
 */
public class PersonSearchResultsState extends GedcomxApplicationState<Feed> {

  protected PersonSearchResultsState(ClientRequest request, ClientResponse response, String accessToken, StateFactory stateFactory) {
    super(request, response, accessToken, stateFactory);
  }

  @Override
  protected PersonSearchResultsState clone(ClientRequest request, ClientResponse response) {
    return new PersonSearchResultsState(request, response, this.accessToken, this.stateFactory);
  }

  @Override
  protected Feed loadEntity(ClientResponse response) {
    return response.getEntity(Feed.class);
  }

  @Override
  public PersonSearchResultsState ifSuccessful() {
    return (PersonSearchResultsState) super.ifSuccessful();
  }

  @Override
  public PersonSearchResultsState head() {
    return (PersonSearchResultsState) super.head();
  }

  @Override
  public PersonSearchResultsState options() {
    return (PersonSearchResultsState) super.options();
  }

  @Override
  public PersonSearchResultsState get() {
    return (PersonSearchResultsState) super.get();
  }

  @Override
  public PersonSearchResultsState delete() {
    return (PersonSearchResultsState) super.delete();
  }

  @Override
  public PersonSearchResultsState put(Feed e) {
    return (PersonSearchResultsState) super.put(e);
  }

  @Override
  protected SupportsLinks getScope() {
    return getEntity();
  }

  public Feed getResults() {
    return getEntity();
  }

  public PersonState readPerson(Entry person) {
    Link link = person.getLink(Rel.PERSON);
    link = link == null ? person.getLink(Rel.SELF) : link;
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return this.stateFactory.newPersonState(request, invoke(request), this.accessToken);
  }

  public PersonState readPerson(Person person) {
    Link link = person.getLink(Rel.PERSON);
    link = link == null ? person.getLink(Rel.SELF) : link;
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return this.stateFactory.newPersonState(request, invoke(request), this.accessToken);
  }

  @Override
  public PersonSearchResultsState readNextPage() {
    return (PersonSearchResultsState) super.readNextPage();
  }

  @Override
  public PersonSearchResultsState readPreviousPage() {
    return (PersonSearchResultsState) super.readPreviousPage();
  }

  @Override
  public PersonSearchResultsState readFirstPage() {
    return (PersonSearchResultsState) super.readFirstPage();
  }

  @Override
  public PersonSearchResultsState readLastPage() {
    return (PersonSearchResultsState) super.readLastPage();
  }
}
