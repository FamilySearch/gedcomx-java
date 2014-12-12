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
import org.familysearch.platform.discussions.Comment;
import org.familysearch.platform.discussions.Discussion;
import org.gedcomx.links.Link;
import org.gedcomx.links.SupportsLinks;
import org.gedcomx.rs.client.GedcomxApplicationException;
import org.gedcomx.rs.client.GedcomxApplicationState;
import org.gedcomx.rs.client.StateTransitionOption;

import javax.ws.rs.HttpMethod;
import java.net.URI;
import java.util.Arrays;

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
  public DiscussionState head(StateTransitionOption... options) {
    return (DiscussionState) super.head(options);
  }

  @Override
  public DiscussionState get(StateTransitionOption... options) {
    return (DiscussionState) super.get(options);
  }

  @Override
  public DiscussionState delete(StateTransitionOption... options) {
    return (DiscussionState) super.delete(options);
  }

  @Override
  public DiscussionState put(FamilySearchPlatform e, StateTransitionOption... options) {
    return (DiscussionState) super.put(e, options);
  }

  @Override
  public DiscussionState post(FamilySearchPlatform entity, StateTransitionOption... options) {
    return (DiscussionState) super.post(entity, options);
  }

  @Override
  protected FamilySearchPlatform loadEntity(ClientResponse response) {
    return response.getClientResponseStatus() == ClientResponse.Status.OK ? response.getEntity(FamilySearchPlatform.class) : null;
  }

  @Override
  protected SupportsLinks getMainDataElement() {
    return getDiscussion();
  }

  public Discussion getDiscussion() {
    return getEntity() == null ? null : getEntity().getDiscussions() == null ? null : getEntity().getDiscussions().isEmpty() ? null : getEntity().getDiscussions().get(0);
  }

  protected Discussion createEmptySelf() {
    Discussion discussion = new Discussion();
    discussion.setId(getLocalSelfId());
    return discussion;
  }

  protected String getLocalSelfId() {
    Discussion me = getDiscussion();
    return me == null ? null : me.getId();
  }

  public DiscussionState loadComments(StateTransitionOption... options) {
    Link link = getLink(Rel.COMMENTS);
    if (this.entity != null && link != null && link.getHref() != null) {
      embed(link, this.entity, options);
    }

    return this;
  }

  @Override
  protected ClientRequest.Builder createRequestForEmbeddedResource(String rel) {
    return RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest());
  }

  public DiscussionState update(Discussion discussion, StateTransitionOption... options) {
    FamilySearchPlatform fsp = new FamilySearchPlatform();
    fsp.addDiscussion(discussion);
    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest()).entity(fsp).build(getSelfUri(), HttpMethod.POST);
    return ((FamilySearchStateFactory)this.stateFactory).newDiscussionState(request, invoke(request, options), this.accessToken);
  }

  public DiscussionState addComment(String comment, StateTransitionOption... options) {
    return addComments(new Comment[]{new Comment(comment)}, options);
  }

  public DiscussionState addComment(Comment comment, StateTransitionOption... options) {
    return addComments(new Comment[]{comment}, options);
  }

  public DiscussionState addComments(Comment[] comments, StateTransitionOption... options) {
    Discussion discussion = createEmptySelf();
    discussion.setComments(Arrays.asList(comments));
    return updateComments(discussion, options);
  }

  public DiscussionState updateComment(Comment comment, StateTransitionOption... options) {
    return updateComments(new Comment[]{comment}, options);
  }

  public DiscussionState updateComments(Comment[] comments, StateTransitionOption... options) {
    Discussion discussion = createEmptySelf();
    discussion.setComments(Arrays.asList(comments));
    return updateComments(discussion, options);
  }

  protected DiscussionState updateComments(Discussion discussion, StateTransitionOption... options) {
    URI target = getSelfUri();
    Link link = getLink(Rel.COMMENTS);
    if (link != null && link.getHref() != null) {
      target = link.getHref().toURI();
    }

    FamilySearchPlatform gx = new FamilySearchPlatform();
    gx.setDiscussions(Arrays.asList(discussion));
    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest()).entity(gx).build(target, HttpMethod.POST);
    return ((FamilySearchStateFactory)this.stateFactory).newDiscussionState(request, invoke(request, options), this.accessToken);
  }

  public DiscussionState deleteComment(Comment comment, StateTransitionOption... options) {
    Link link = comment.getLink(Rel.COMMENT);
    link = link == null ? comment.getLink(Rel.SELF) : link;
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException("Comment cannot be deleted: missing link.");
    }

    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest()).build(link.getHref().toURI(), HttpMethod.DELETE);
    return ((FamilySearchStateFactory)this.stateFactory).newDiscussionState(request, invoke(request, options), this.accessToken);
  }

}
