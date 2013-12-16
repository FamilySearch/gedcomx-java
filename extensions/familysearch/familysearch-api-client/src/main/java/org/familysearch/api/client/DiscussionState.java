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
import org.gedcomx.rs.client.GedcomxApplicationState;

import javax.ws.rs.HttpMethod;

/**
 * @author Ryan Heaton
 */
public class DiscussionState extends GedcomxApplicationState<FamilySearchPlatform> {

  public DiscussionState(ClientRequest request, ClientResponse response, String accessToken, FamilySearchStateFactory stateFactory) {
    super(request, response, accessToken, stateFactory);
  }

  @Override
  protected DiscussionState clone(ClientRequest request, ClientResponse response) {
    return new DiscussionState(request, response, this.accessToken, (FamilySearchStateFactory) this.stateFactory);
  }

  @Override
  public DiscussionState ifSuccessful() {
    return (DiscussionState) super.ifSuccessful();
  }

  @Override
  public DiscussionState head() {
    return (DiscussionState) super.head();
  }

  @Override
  public DiscussionState get() {
    return (DiscussionState) super.get();
  }

  @Override
  public DiscussionState delete() {
    return (DiscussionState) super.delete();
  }

  @Override
  public DiscussionState put(FamilySearchPlatform e) {
    return (DiscussionState) super.put(e);
  }

  @Override
  protected FamilySearchPlatform loadEntity(ClientResponse response) {
    return response.getClientResponseStatus() == ClientResponse.Status.OK ? response.getEntity(FamilySearchPlatform.class) : null;
  }

  @Override
  protected SupportsLinks getScope() {
    return getDiscussion();
  }

  public Discussion getDiscussion() {
    return getEntity() == null ? null : getEntity().getDiscussions() == null ? null : getEntity().getDiscussions().isEmpty() ? null : getEntity().getDiscussions().get(0);
  }

  public CommentsState readComments() {
    Link link = getLink(Rel.COMMENTS);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest()).build(link.getHref().toURI(), HttpMethod.GET);
    return ((FamilySearchStateFactory)this.stateFactory).newCommentsState(request, invoke(request), this.accessToken);
  }

  public DiscussionState update(Discussion discussion) {
    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest()).build(getSelfUri(), HttpMethod.POST);
    return ((FamilySearchStateFactory)this.stateFactory).newDiscussionState(request, invoke(request), this.accessToken);
  }
}
