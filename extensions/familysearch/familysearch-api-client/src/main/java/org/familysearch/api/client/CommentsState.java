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

import javax.ws.rs.HttpMethod;
import java.util.Arrays;
import java.util.List;

/**
 * @author Ryan Heaton
 */
public class CommentsState extends GedcomxApplicationState<FamilySearchPlatform> {

  public CommentsState(ClientRequest request, ClientResponse response, String accessToken, FamilySearchStateFactory stateFactory) {
    super(request, response, accessToken, stateFactory);
  }

  @Override
  protected CommentsState clone(ClientRequest request, ClientResponse response) {
    return new CommentsState(request, response, this.accessToken, (FamilySearchStateFactory) this.stateFactory);
  }

  @Override
  public CommentsState ifSuccessful() {
    return (CommentsState) super.ifSuccessful();
  }

  @Override
  public CommentsState head() {
    return (CommentsState) super.head();
  }

  @Override
  public CommentsState get() {
    return (CommentsState) super.get();
  }

  @Override
  public CommentsState delete() {
    return (CommentsState) super.delete();
  }

  @Override
  public CommentsState put(FamilySearchPlatform e) {
    return (CommentsState) super.put(e);
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

  public List<Comment> getComments() {
    return getDiscussion() == null ? null : getDiscussion().getComments();
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

  public CommentsState addComment(Comment comment) {
    return addComments(comment);
  }

  public CommentsState addComments(Comment... comments) {
    Discussion discussion = createEmptySelf();
    discussion.setComments(Arrays.asList(comments));
    return updateComments(discussion);
  }

  public CommentsState updateComment(Comment comment) {
    return updateComments(comment);
  }

  public CommentsState updateComments(Comment... comments) {
    Discussion discussion = createEmptySelf();
    discussion.setComments(Arrays.asList(comments));
    return updateComments(discussion);
  }

  protected CommentsState updateComments(Discussion discussion) {
    FamilySearchPlatform gx = new FamilySearchPlatform();
    gx.setDiscussions(Arrays.asList(discussion));
    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest()).entity(gx).build(getSelfUri(), HttpMethod.POST);
    return ((FamilySearchStateFactory)this.stateFactory).newCommentsState(request, invoke(request), this.accessToken);
  }

  public CommentsState deleteComment(Comment comment) {
    Link link = comment.getLink(Rel.COMMENT);
    link = link == null ? comment.getLink(Rel.SELF) : link;
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException("Comment cannot be deleted: missing link.");
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.DELETE);
    return ((FamilySearchStateFactory)this.stateFactory).newCommentsState(request, invoke(request), this.accessToken);
  }

  @Override
  protected CommentsState readNextPage() {
    return (CommentsState) super.readNextPage();
  }

  @Override
  protected CommentsState readPreviousPage() {
    return (CommentsState) super.readPreviousPage();
  }

  @Override
  protected CommentsState readFirstPage() {
    return (CommentsState) super.readFirstPage();
  }

  @Override
  protected CommentsState readLastPage() {
    return (CommentsState) super.readLastPage();
  }
}
