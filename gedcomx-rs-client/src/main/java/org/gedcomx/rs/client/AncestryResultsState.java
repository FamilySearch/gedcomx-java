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
import org.gedcomx.links.Link;
import org.gedcomx.links.SupportsLinks;
import org.gedcomx.rs.Rel;
import org.gedcomx.rs.client.util.AncestryTree;

import javax.ws.rs.HttpMethod;
import java.net.URI;

/**
 * @author Ryan Heaton
 */
public class AncestryResultsState extends GedcomxApplicationState<Gedcomx> {

  protected AncestryResultsState(ClientRequest request, ClientResponse response, String accessToken, StateFactory stateFactory) {
    super(request, response, accessToken, stateFactory);
  }

  @Override
  protected AncestryResultsState clone(ClientRequest request, ClientResponse response) {
    return new AncestryResultsState(request, response, this.accessToken, this.stateFactory);
  }

  @Override
  public String getSelfRel() {
    return Rel.ANCESTRY;
  }

  @Override
  protected Gedcomx loadEntity(ClientResponse response) {
    return response.getEntity(Gedcomx.class);
  }

  @Override
  public AncestryResultsState ifSuccessful() {
    return (AncestryResultsState) super.ifSuccessful();
  }

  @Override
  public AncestryResultsState head(StateTransitionOption... options) {
    return (AncestryResultsState) super.head(options);
  }

  @Override
  public AncestryResultsState options(StateTransitionOption... options) {
    return (AncestryResultsState) super.options(options);
  }

  @Override
  public AncestryResultsState get(StateTransitionOption... options) {
    return (AncestryResultsState) super.get(options);
  }

  @Override
  public AncestryResultsState delete(StateTransitionOption... options) {
    return (AncestryResultsState) super.delete(options);
  }

  @Override
  public AncestryResultsState put(Gedcomx e, StateTransitionOption... options) {
    return (AncestryResultsState) super.put(e, options);
  }

  @Override
  public AncestryResultsState post(Gedcomx entity, StateTransitionOption... options) {
    return (AncestryResultsState) super.post(entity, options);
  }

  @Override
  protected SupportsLinks getScope() {
    return getEntity();
  }

  public AncestryTree getTree() {
    return getEntity() != null ? new AncestryTree(getEntity()) : null;
  }

  public PersonState readPerson(int ancestorNumber, StateTransitionOption... options) {
    AncestryTree.AncestryNode ancestor = getTree().getAncestor(ancestorNumber);
    if (ancestor == null) {
      return null;
    }

    Link selfLink = ancestor.getPerson().getLink(Rel.PERSON);
    if (selfLink == null || selfLink.getHref() == null) {
      selfLink = ancestor.getPerson().getLink(Rel.SELF);
    }

    URI personUri = selfLink == null || selfLink.getHref() == null ? null : selfLink.getHref().toURI();
    if (personUri == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(personUri, HttpMethod.GET);
    return this.stateFactory.newPersonState(request, invoke(request, options), this.accessToken);
  }
}
