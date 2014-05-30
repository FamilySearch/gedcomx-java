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
import org.gedcomx.source.SourceDescription;

import javax.ws.rs.HttpMethod;
import java.net.URI;
import java.util.Arrays;

/**
 * @author Ryan Heaton
 */
public class SourceDescriptionState extends GedcomxApplicationState<Gedcomx> {

  protected SourceDescriptionState(ClientRequest request, ClientResponse response, String accessToken, StateFactory stateFactory) {
    super(request, response, accessToken, stateFactory);
  }

  @Override
  public String getSelfRel() {
    return Rel.DESCRIPTION;
  }

  @Override
  protected SourceDescriptionState clone(ClientRequest request, ClientResponse response) {
    return new SourceDescriptionState(request, response, this.accessToken, this.stateFactory);
  }

  @Override
  public SourceDescriptionState ifSuccessful() {
    return (SourceDescriptionState) super.ifSuccessful();
  }

  @Override
  public SourceDescriptionState head(StateTransitionOption... options) {
    return (SourceDescriptionState) super.head(options);
  }

  @Override
  public SourceDescriptionState options(StateTransitionOption... options) {
    return (SourceDescriptionState) super.options(options);
  }

  @Override
  public SourceDescriptionState get(StateTransitionOption... options) {
    return (SourceDescriptionState) super.get(options);
  }

  @Override
  public SourceDescriptionState delete(StateTransitionOption... options) {
    return (SourceDescriptionState) super.delete(options);
  }

  @Override
  public SourceDescriptionState put(Gedcomx e, StateTransitionOption... options) {
    return (SourceDescriptionState) super.put(e, options);
  }

  @Override
  public SourceDescriptionState post(Gedcomx entity, StateTransitionOption... options) {
    return (SourceDescriptionState) super.post(entity, options);
  }

  @Override
  protected Gedcomx loadEntity(ClientResponse response) {
    return response.getEntity(Gedcomx.class);
  }

  @Override
  protected SupportsLinks getScope() {
    return getSourceDescription();
  }

  public SourceDescription getSourceDescription() {
    return getEntity() == null ? null : getEntity().getSourceDescriptions() == null ? null : getEntity().getSourceDescriptions().isEmpty() ? null : getEntity().getSourceDescriptions().get(0);
  }

  public SourceDescriptionState update(SourceDescription description, StateTransitionOption... options) {
    Gedcomx entity = new Gedcomx();
    entity.setSourceDescriptions(Arrays.asList(description));
    ClientRequest request = createAuthenticatedGedcomxRequest().entity(entity).build(getSelfUri(), HttpMethod.POST);
    return this.stateFactory.newSourceDescriptionState(request, invoke(request, options), this.accessToken);
  }

  public PersonsState readPersonas(StateTransitionOption... options) {
    Link link = getLink(Rel.PERSONS);
    if (link == null || link.getHref() == null) {
      return this.stateFactory.newPersonsState(this.request, this.response, this.accessToken);
    }
    else {
      ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
      return this.stateFactory.newPersonsState(request, invoke(request, options), this.accessToken);
    }
  }

  public PersonState addPersona(Person person, StateTransitionOption... options) {
    Gedcomx entity = new Gedcomx();
    entity.addPerson(person);
    return addPersona(entity, options);
  }

  public PersonState addPersona(Gedcomx entity, StateTransitionOption... options) {
    URI target = getSelfUri();
    Link link = getLink(Rel.PERSONS);
    if (link != null && link.getHref() != null) {
      target = link.getHref().toURI();
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().entity(entity).build(target, HttpMethod.POST);
    return this.stateFactory.newPersonState(request, invoke(request, options), this.accessToken);
  }

  public SourceDescriptionState queryAttachedReferences(StateTransitionOption... options) {
    Link link = getLink(Rel.SOURCE_REFERENCES_QUERY);
    if (link == null || link.getHref() == null) {
      return null;
    }
    else {
      ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
      return this.stateFactory.newSourceDescriptionState(request, invoke(request, options), this.accessToken);
    }
  }

  public CollectionState readCollection(StateTransitionOption... options) {
    Link link = getLink(Rel.COLLECTION);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return this.stateFactory.newCollectionState(request, invoke(request, options), this.accessToken);
  }
}
