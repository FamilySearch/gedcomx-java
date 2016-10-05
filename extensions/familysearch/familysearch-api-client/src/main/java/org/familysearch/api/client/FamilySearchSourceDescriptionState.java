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
import org.gedcomx.Gedcomx;
import org.gedcomx.links.Link;
import org.gedcomx.rs.client.CollectionState;
import org.gedcomx.rs.client.GedcomxApplicationException;
import org.gedcomx.rs.client.SourceDescriptionState;
import org.gedcomx.rs.client.StateTransitionOption;
import org.gedcomx.source.SourceDescription;

import javax.ws.rs.HttpMethod;

/**
 * @author Ryan Heaton
 */
public class FamilySearchSourceDescriptionState extends SourceDescriptionState {

  protected FamilySearchSourceDescriptionState(ClientRequest request, ClientResponse client, String accessToken, FamilySearchStateFactory stateFactory) {
    super(request, client, accessToken, stateFactory);
  }

  @Override
  protected FamilySearchSourceDescriptionState clone(ClientRequest request, ClientResponse response) {
    return new FamilySearchSourceDescriptionState(request, response, this.accessToken, getStateFactory());
  }

  @Override
  public FamilySearchSourceDescriptionState ifSuccessful() {
    return (FamilySearchSourceDescriptionState) super.ifSuccessful();
  }

  @Override
  public FamilySearchSourceDescriptionState head(StateTransitionOption... options) {
    return (FamilySearchSourceDescriptionState) super.head(options);
  }

  @Override
  public FamilySearchSourceDescriptionState options(StateTransitionOption... options) {
    return (FamilySearchSourceDescriptionState) super.options(options);
  }

  @Override
  public FamilySearchSourceDescriptionState get(StateTransitionOption... options) {
    return (FamilySearchSourceDescriptionState) super.get(options);
  }

  @Override
  public FamilySearchSourceDescriptionState delete(StateTransitionOption... options) {
    return (FamilySearchSourceDescriptionState) super.delete(options);
  }

  @Override
  public FamilySearchSourceDescriptionState put(Gedcomx e, StateTransitionOption... options) {
    return (FamilySearchSourceDescriptionState) super.put(e, options);
  }

  @Override
  public FamilySearchSourceDescriptionState post(Gedcomx entity, StateTransitionOption... options) {
    return (FamilySearchSourceDescriptionState) super.post(entity, options);
  }

  @Override
  public FamilySearchSourceDescriptionState update(SourceDescription description, StateTransitionOption... options) {
    return (FamilySearchSourceDescriptionState) super.update(description);
  }

  public FamilySearchSourceDescriptionState deleteCoverage(StateTransitionOption... options) {
    Link link = getLink(Rel.COVERAGE);
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException("Coverage cannot be deleted: missing link.");
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.DELETE);
    return getStateFactory().newSourceDescriptionState(request, invoke(request, options), this.accessToken);
  }

  public DiscussionState readComments(StateTransitionOption... options) {
    Link link = getLink(Rel.COMMENTS);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest()).build(link.getHref().toURI(), HttpMethod.GET);
    return getStateFactory().newDiscussionState(request, invoke(request, options), this.accessToken);
  }

  //TODO: Create FamilysearchSourceReferencesQueryState class, add it to FamilySearchStateFactory when link is created
/*  public FamilySearchSourceReferencesQueryState readSourceReferencesQuery() {
    Link link = getLink( //TODO: Put Rel here when added );
    if (link == null || link.getHref() = null) {
      return null;
    }

    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest()).build(link.getHref().toURI(), HttpMethod.GET);
    return getStateFactory().newFamilySearchSourceReferencesQueryState(request, invoke(request), this.accessToken);
  }  */

  public FamilySearchSourceDescriptionState moveToCollection(CollectionState collection, StateTransitionOption... options) {
    Link link = collection.getLink(Rel.SOURCE_DESCRIPTIONS);
    if (link == null || link.getHref() == null) {
      return null;
    }

    SourceDescription me = getSourceDescription();
    if (me == null || me.getId() == null) {
      return null;
    }

    Gedcomx gx = new Gedcomx().sourceDescription(new SourceDescription().id(me.getId()));
    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest()).entity(gx).build(link.getHref().toURI(), HttpMethod.POST);
    return getStateFactory().newSourceDescriptionState(request, invoke(request, options), this.accessToken);
  }

  private FamilySearchStateFactory getStateFactory() {
    return (FamilySearchStateFactory)this.stateFactory;
  }
}
