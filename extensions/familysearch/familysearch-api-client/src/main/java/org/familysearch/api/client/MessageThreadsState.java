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
import org.familysearch.platform.messages.MessageThread;
import org.familysearch.platform.messages.UserMessageThreadsSummary;

import org.gedcomx.links.Link;
import org.gedcomx.links.SupportsLinks;
import org.gedcomx.rs.Rel;
import org.gedcomx.rs.client.CollectionState;
import org.gedcomx.rs.client.GedcomxApplicationState;
import org.gedcomx.rs.client.StateTransitionOption;

import javax.ws.rs.HttpMethod;
import java.util.List;

/**
 * @author Todd Chapman
 */
public class MessageThreadsState extends GedcomxApplicationState<FamilySearchPlatform> {

  public MessageThreadsState(ClientRequest request, ClientResponse response, String accessToken, FamilySearchStateFactory stateFactory) {
    super(request, response, accessToken, stateFactory);
  }

  @Override
  protected MessageThreadsState clone(ClientRequest request, ClientResponse response) {
    return new MessageThreadsState(request, response, this.accessToken, (FamilySearchStateFactory) this.stateFactory);
  }

  @Override
  public MessageThreadsState ifSuccessful() {
    return (MessageThreadsState) super.ifSuccessful();
  }

  @Override
  public MessageThreadsState head(StateTransitionOption... options) {
    return (MessageThreadsState) super.head(options);
  }

  @Override
  public MessageThreadsState get(StateTransitionOption... options) {
    return (MessageThreadsState) super.get(options);
  }

  @Override
  public MessageThreadsState delete(StateTransitionOption... options) {
    return (MessageThreadsState) super.delete(options);
  }

  @Override
  public MessageThreadsState post(FamilySearchPlatform entity, StateTransitionOption... options) {
    return (MessageThreadsState) super.post(entity, options);
  }

  @Override
  protected FamilySearchPlatform loadEntity(ClientResponse response) {
    return response.getClientResponseStatus() == ClientResponse.Status.OK ? response.getEntity(FamilySearchPlatform.class) : null;
  }

  public List<UserMessageThreadsSummary> getUserMessageThreadsSummary() {
    return getEntity() == null ? null : getEntity().getUserMessageThreadsSummaries();
  }

  @Override
  protected SupportsLinks getMainDataElement() {
    return getEntity();
  }

  @Override
  public MessageThreadsState readNextPage(StateTransitionOption... options) {
    return (MessageThreadsState) super.readNextPage(options);
  }

  @Override
  public MessageThreadsState readPreviousPage(StateTransitionOption... options) {
    return (MessageThreadsState) super.readPreviousPage(options);
  }

  @Override
  public MessageThreadsState readFirstPage(StateTransitionOption... options) {
    return (MessageThreadsState) super.readFirstPage(options);
  }

  @Override
  public MessageThreadsState readLastPage(StateTransitionOption... options) {
    return (MessageThreadsState) super.readLastPage(options);
  }

  public CollectionState readCollection(StateTransitionOption... options) {
    Link link = getLink(Rel.COLLECTION);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return ((FamilySearchStateFactory)this.stateFactory).newCollectionState(request, invoke(request, options), this.accessToken);
  }

  public MessageThreadState addMessageThread(MessageThread messageThread, StateTransitionOption... options) {
    FamilySearchPlatform entity = new FamilySearchPlatform();
    entity.addMessageThread(messageThread);
    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedGedcomxRequest()).entity(entity).build(getSelfUri(), HttpMethod.POST);
    return ((FamilySearchStateFactory)this.stateFactory).newMessageThreadState(request, invoke(request, options), this.accessToken);
  }

}
