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
import org.gedcomx.Gedcomx;
import org.gedcomx.conclusion.Person;
import org.gedcomx.links.Link;
import org.gedcomx.links.SupportsLinks;
import org.gedcomx.rt.Rel;

import jakarta.ws.rs.HttpMethod;
import java.net.URI;
import java.util.List;

/**
 * @author Ryan Heaton
 */
public class PersonsState extends GedcomxApplicationState<Gedcomx> {

  protected PersonsState(ClientRequest request, ClientResponse response, String accessToken, StateFactory stateFactory) {
    super(request, response, accessToken, stateFactory);
  }

  @Override
  protected PersonsState clone(ClientRequest request, ClientResponse response) {
    return new PersonsState(request, response, this.accessToken, this.stateFactory);
  }

  @Override
  public PersonsState ifSuccessful() {
    return (PersonsState) super.ifSuccessful();
  }

  @Override
  public PersonsState head(StateTransitionOption... options) {
    return (PersonsState) super.head(options);
  }

  @Override
  public PersonsState options(StateTransitionOption... options) {
    return (PersonsState) super.options(options);
  }

  @Override
  public PersonsState get(StateTransitionOption... options) {
    return (PersonsState) super.get(options);
  }

  @Override
  public PersonsState delete(StateTransitionOption... options) {
    return (PersonsState) super.delete(options);
  }

  @Override
  public PersonsState put(Gedcomx e, StateTransitionOption... options) {
    return (PersonsState) super.put(e, options);
  }

  @Override
  public PersonsState post(Gedcomx entity, StateTransitionOption... options) {
    return (PersonsState) super.post(entity, options);
  }

  @Override
  protected Gedcomx loadEntity(ClientResponse response) {
    return response.getEntity(Gedcomx.class);
  }

  @Override
  protected SupportsLinks getMainDataElement() {
    return getEntity();
  }

  public CollectionState readCollection(StateTransitionOption... options) {
    Link link = getLink(Rel.COLLECTION);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return this.stateFactory.newCollectionState(request, invoke(request, options), this.accessToken);
  }

  public List<Person> getPersons() {
    Gedcomx entity = getEntity();
    return entity == null ? null : entity.getPersons();
  }

  public PersonState addPerson(Person person, StateTransitionOption... options) {
    Link link = getLink("self");
    URI href = link == null ? null : link.getHref() == null ? null : link.getHref().toURI();
    href = href == null ? getUri() : href;

    ClientRequest request = createAuthenticatedGedcomxRequest().build(href, HttpMethod.POST);
    return this.stateFactory.newPersonState(request, invoke(request, options), this.accessToken).ifSuccessful();
  }

  @Override
  public PersonsState readNextPage(StateTransitionOption... options) {
    return (PersonsState) super.readNextPage(options);
  }

  @Override
  public PersonsState readPreviousPage(StateTransitionOption... options) {
    return (PersonsState) super.readPreviousPage(options);
  }

  @Override
  public PersonsState readFirstPage(StateTransitionOption... options) {
    return (PersonsState) super.readFirstPage(options);
  }

  @Override
  public PersonsState readLastPage(StateTransitionOption... options) {
    return (PersonsState) super.readLastPage(options);
  }

  public PersonState readPerson(Person person, StateTransitionOption... options) {
    Link link = person.getLink(Rel.PERSON);
    if (link == null) {
      link = person.getLink(Rel.SELF);
    }

    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return this.stateFactory.newPersonState(request, invoke(request, options), this.accessToken);
  }
}
