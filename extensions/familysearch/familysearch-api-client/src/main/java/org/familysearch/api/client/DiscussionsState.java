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
import org.familysearch.platform.FamilySearchPlatform;
import org.familysearch.platform.discussions.Discussion;
import org.gedcomx.links.Link;
import org.gedcomx.links.SupportsLinks;
import org.gedcomx.rs.Rel;
import org.gedcomx.rs.client.CollectionState;
import org.gedcomx.rs.client.GedcomxApplicationState;
import org.gedcomx.rs.client.StateTransitionOption;

import javax.ws.rs.HttpMethod;
import java.util.List;

/**
 * @author Ryan Heaton
 */
public class DiscussionsState extends GedcomxApplicationState<FamilySearchPlatform> {

  public DiscussionsState(ClientRequest request, ClientResponse response, String accessToken, FamilySearchStateFactory stateFactory) {
    super(request, response, accessToken, stateFactory);
  }

  @Override
  protected DiscussionsState clone(ClientRequest request, ClientResponse response) {
    return new DiscussionsState(request, response, this.accessToken, (FamilySearchStateFactory) this.stateFactory);
  }

  @Override
  public DiscussionsState ifSuccessful() {
    return (DiscussionsState) super.ifSuccessful();
  }

  @Override
  public DiscussionsState head(StateTransitionOption... options) {
    return (DiscussionsState) super.head(options);
  }

  @Override
  public DiscussionsState get(StateTransitionOption... options) {
    return (DiscussionsState) super.get(options);
  }

  @Override
  public DiscussionsState delete(StateTransitionOption... options) {
    return (DiscussionsState) super.delete(options);
  }

  @Override
  public DiscussionsState put(FamilySearchPlatform e, StateTransitionOption... options) {
    return (DiscussionsState) super.put(e, options);
  }

  @Override
  public DiscussionsState post(FamilySearchPlatform entity, StateTransitionOption... options) {
    return (DiscussionsState) super.post(entity, options);
  }

  @Override
  protected FamilySearchPlatform loadEntity(ClientResponse response) {
    return response.getClientResponseStatus() == ClientResponse.Status.OK ? response.getEntity(FamilySearchPlatform.class) : null;
  }

  public List<Discussion> getDiscussions() {
    return getEntity() == null ? null : getEntity().getDiscussions();
  }

  @Override
  protected SupportsLinks getScope() {
    return getEntity();
  }

  @Override
  public DiscussionsState readNextPage(StateTransitionOption... options) {
    return (DiscussionsState) super.readNextPage(options);
  }

  @Override
  public DiscussionsState readPreviousPage(StateTransitionOption... options) {
    return (DiscussionsState) super.readPreviousPage(options);
  }

  @Override
  public DiscussionsState readFirstPage(StateTransitionOption... options) {
    return (DiscussionsState) super.readFirstPage(options);
  }

  @Override
  public DiscussionsState readLastPage(StateTransitionOption... options) {
    return (DiscussionsState) super.readLastPage(options);
  }

  public CollectionState readCollection(StateTransitionOption... options) {
    Link link = getLink(Rel.COLLECTION);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return ((FamilySearchStateFactory)this.stateFactory).newCollectionState(request, invoke(request, options), this.accessToken);
  }

  public DiscussionState addDiscussion(Discussion discussion, StateTransitionOption... options) {
    FamilySearchPlatform entity = new FamilySearchPlatform();
    entity.addDiscussion(discussion);
    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedGedcomxRequest()).entity(entity).build(getSelfUri(), HttpMethod.POST);
    return ((FamilySearchStateFactory)this.stateFactory).newDiscussionState(request, invoke(request, options), this.accessToken);
  }

}
