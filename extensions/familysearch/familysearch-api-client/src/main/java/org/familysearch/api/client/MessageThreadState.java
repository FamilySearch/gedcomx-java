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

import java.net.URI;
import java.util.Arrays;
import javax.ws.rs.HttpMethod;

import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import org.gedcomx.links.Link;
import org.gedcomx.links.SupportsLinks;
import org.gedcomx.rs.client.GedcomxApplicationException;
import org.gedcomx.rs.client.GedcomxApplicationState;
import org.gedcomx.rs.client.StateTransitionOption;

import org.familysearch.api.client.util.RequestUtil;
import org.familysearch.platform.FamilySearchPlatform;
import org.familysearch.platform.messages.Message;
import org.familysearch.platform.messages.MessageThread;

public class MessageThreadState extends GedcomxApplicationState<FamilySearchPlatform> {

  public MessageThreadState(ClientRequest request, ClientResponse response, String accessToken, FamilySearchStateFactory stateFactory) {
    super(request, response, accessToken, stateFactory);
  }

  @Override
  protected MessageThreadState clone(ClientRequest request, ClientResponse response) {
    return new MessageThreadState(request, response, this.accessToken, (FamilySearchStateFactory) this.stateFactory);
  }

  @Override
  public MessageThreadState ifSuccessful() {
    return (MessageThreadState) super.ifSuccessful();
  }

  @Override
  public MessageThreadState get(StateTransitionOption... options) {
    return (MessageThreadState) super.get(options);
  }

  @Override
  public MessageThreadState delete(StateTransitionOption... options) {
    return (MessageThreadState) super.delete(options);
  }

  @Override
  protected FamilySearchPlatform loadEntity(ClientResponse response) {
    return response.getClientResponseStatus() == ClientResponse.Status.OK ? response.getEntity(FamilySearchPlatform.class) : null;
  }

  @Override
  protected SupportsLinks getMainDataElement() {
    return getMessageThread();
  }

  @Override
  public MessageThreadState readNextPage(StateTransitionOption... options) {
    return (MessageThreadState) super.readNextPage(options);
  }

  @Override
  public MessageThreadState readPreviousPage(StateTransitionOption... options) {
    return (MessageThreadState) super.readPreviousPage(options);
  }

  @Override
  public MessageThreadState readFirstPage(StateTransitionOption... options) {
    return (MessageThreadState) super.readFirstPage(options);
  }

  public MessageThread getMessageThread() {
    return getEntity() == null ? null : getEntity().getMessageThreads() == null ? null : getEntity().getMessageThreads().isEmpty() ? null : getEntity().getMessageThreads().get(0);
  }

  protected MessageThread createEmptySelf() {
    MessageThread messageThread = new MessageThread();
    messageThread.setId(getLocalSelfId());
    return messageThread;
  }

  protected String getLocalSelfId() {
    MessageThread me = getMessageThread();
    return me == null ? null : me.getId();
  }

  public MessageThreadState deleteMessageThread(StateTransitionOption... options) {
    Link link = getLink(Rel.MESSAGE_THREAD);
    link = link == null ? getLink(Rel.SELF) : link;
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException("Message thread cannot be deleted: missing link.");
    }

    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest()).build(link.getHref().toURI(), HttpMethod.DELETE);
    return ((FamilySearchStateFactory)this.stateFactory).newMessageThreadState(request, invoke(request, options), this.accessToken);
  }

  @Override
  protected ClientRequest.Builder createRequestForEmbeddedResource(String rel) {
    return RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest());
  }

  public MessageThreadState addMessage(Message message, StateTransitionOption... options) {
    return addMessages(new Message[]{message}, options);
  }

  private MessageThreadState addMessages(Message[] messages, StateTransitionOption... options) {
    MessageThread messageThread = createEmptySelf();
    messageThread.setMessages(Arrays.asList(messages));
    return updateMessages(messageThread, options);
  }

  private MessageThreadState updateMessages(MessageThread messageThread, StateTransitionOption... options) {
    URI target = getSelfUri();
    Link link = getLink(Rel.MESSAGES);
    if (link != null && link.getHref() != null) {
      target = link.getHref().toURI();
    }

    FamilySearchPlatform gx = new FamilySearchPlatform();
    gx.setMessageThreads(Arrays.asList(messageThread));
    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest()).entity(gx).build(target, HttpMethod.POST);
    return ((FamilySearchStateFactory)this.stateFactory).newMessageThreadState(request, invoke(request, options), this.accessToken);
  }

}
