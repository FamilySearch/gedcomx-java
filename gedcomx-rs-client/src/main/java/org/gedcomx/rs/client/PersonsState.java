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
import org.gedcomx.rs.Rel;

import javax.ws.rs.HttpMethod;
import java.net.URI;

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
  public PersonsState head() {
    return (PersonsState) super.head();
  }

  @Override
  public PersonsState get() {
    return (PersonsState) super.get();
  }

  @Override
  public PersonsState delete() {
    return (PersonsState) super.delete();
  }

  @Override
  public PersonsState put(Gedcomx e) {
    return (PersonsState) super.put(e);
  }

  @Override
  protected Gedcomx loadEntity(ClientResponse response) {
    return response.getClientResponseStatus() == ClientResponse.Status.OK ? response.getEntity(Gedcomx.class) : null;
  }

  @Override
  protected SupportsLinks getScope() {
    return getEntity();
  }

  public CollectionState readCollection() {
    Link link = getLink(Rel.COLLECTION);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return this.stateFactory.newCollectionState(request, invoke(request), this.accessToken);
  }

  public PersonState addPerson(Person person) {
    Link link = getLink("self");
    URI href = link == null ? null : link.getHref() == null ? null : link.getHref().toURI();
    href = href == null ? getUri() : href;

    ClientRequest request = createAuthenticatedGedcomxRequest().build(href, HttpMethod.POST);
    return this.stateFactory.newPersonState(request, invoke(request), this.accessToken).ifSuccessful();
  }

  @Override
  public PersonsState readNextPage() {
    return (PersonsState) super.readNextPage();
  }

  @Override
  public PersonsState readPreviousPage() {
    return (PersonsState) super.readPreviousPage();
  }

  @Override
  public PersonsState readFirstPage() {
    return (PersonsState) super.readFirstPage();
  }

  @Override
  public PersonsState readLastPage() {
    return (PersonsState) super.readLastPage();
  }
}
