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
package org.familysearch.api.client.ft;

import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import org.familysearch.api.client.util.ChangeHistoryPage;
import org.familysearch.api.client.util.RequestUtil;
import org.gedcomx.atom.Entry;
import org.gedcomx.atom.Feed;
import org.gedcomx.links.Link;
import org.gedcomx.links.SupportsLinks;
import org.gedcomx.rs.client.GedcomxApplicationException;
import org.gedcomx.rs.client.GedcomxApplicationState;
import org.gedcomx.rs.client.StateTransitionOption;

import javax.ws.rs.HttpMethod;

/**
 * @author Ryan Heaton
 */
public class ChangeHistoryState extends GedcomxApplicationState<Feed> {

  protected ChangeHistoryState(ClientRequest request, ClientResponse response, String accessToken, FamilyTreeStateFactory stateFactory) {
    super(request, response, accessToken, stateFactory);
  }

  @Override
  protected ChangeHistoryState clone(ClientRequest request, ClientResponse response) {
    return new ChangeHistoryState(request, response, this.accessToken, (FamilyTreeStateFactory) this.stateFactory);
  }

  @Override
  protected Feed loadEntity(ClientResponse response) {
    return response.getEntity(Feed.class);
  }

  @Override
  protected SupportsLinks getMainDataElement() {
    return getEntity();
  }

  @Override
  public ChangeHistoryState ifSuccessful() {
    return (ChangeHistoryState) super.ifSuccessful();
  }

  @Override
  public ChangeHistoryState head(StateTransitionOption... options) {
    return (ChangeHistoryState) super.head(options);
  }

  @Override
  public ChangeHistoryState get(StateTransitionOption... options) {
    return (ChangeHistoryState) super.get(options);
  }

  @Override
  public ChangeHistoryState delete(StateTransitionOption... options) {
    return (ChangeHistoryState) super.delete(options);
  }

  @Override
  public ChangeHistoryState put(Feed e, StateTransitionOption... options) {
    return (ChangeHistoryState) super.put(e, options);
  }

  @Override
  public ChangeHistoryState post(Feed entity, StateTransitionOption... options) {
    return (ChangeHistoryState) super.post(entity, options);
  }

  @Override
  public ChangeHistoryState readNextPage(StateTransitionOption... options) {
    return (ChangeHistoryState) super.readNextPage(options);
  }

  @Override
  public ChangeHistoryState readPreviousPage(StateTransitionOption... options) {
    return (ChangeHistoryState) super.readPreviousPage(options);
  }

  @Override
  public ChangeHistoryState readFirstPage(StateTransitionOption... options) {
    return (ChangeHistoryState) super.readFirstPage(options);
  }

  @Override
  public ChangeHistoryState readLastPage(StateTransitionOption... options) {
    return (ChangeHistoryState) super.readLastPage(options);
  }

  public ChangeHistoryPage getPage() {
    Feed feed = getEntity();
    return feed == null ? null : new ChangeHistoryPage(feed);
  }

  public ChangeHistoryState restoreChange(Entry change, StateTransitionOption... options) {
    Link link = change.getLink(org.familysearch.api.client.Rel.RESTORE);
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException("Unrestorable change: " + change.getId());
    }

    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest()).build(link.getHref().toURI(), HttpMethod.POST);
    return ((FamilyTreeStateFactory)this.stateFactory).newChangeHistoryState(request, invoke(request, options), this.accessToken);
  }
}
