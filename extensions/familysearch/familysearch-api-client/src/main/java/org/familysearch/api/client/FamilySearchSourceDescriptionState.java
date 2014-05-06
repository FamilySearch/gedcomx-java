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
import org.gedcomx.rs.client.GedcomxApplicationState;
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
    return new FamilySearchSourceDescriptionState(request, response, this.accessToken, (FamilySearchStateFactory) this.stateFactory);
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

  public CommentsState readComments(StateTransitionOption... options) {
    Link link = getLink(Rel.COMMENTS);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest()).build(link.getHref().toURI(), HttpMethod.GET);
    return ((FamilySearchStateFactory)this.stateFactory).newCommentsState(request, invoke(request, options), this.accessToken);
  }

  //TODO: Create FamilysearchSourceReferencesQueryState class, add it to FamilySearchStateFactory when link is created
/*  public FamilySearchSourceReferencesQueryState readSourceReferencesQuery() {
    Link link = getLink( //TODO: Put Rel here when added );
    if (link == null || link.getHref() = null) {
      return null;
    }

    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest()).build(link.getHref().toURI(), HttpMethod.GET);
    return ((FamilySearchStateFactory)this.stateFactory).newFamilySearchSourceReferencesQueryState(request, invoke(request), this.accessToken);
  }  */
}
