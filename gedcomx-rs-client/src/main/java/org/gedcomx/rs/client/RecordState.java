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

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import org.gedcomx.Gedcomx;
import org.gedcomx.links.SupportsLinks;
import org.gedcomx.rs.Rel;
import org.gedcomx.rt.GedcomxConstants;

import javax.ws.rs.HttpMethod;
import java.net.URI;

/**
 * @author Ryan Heaton
 */
public class RecordState extends GedcomxApplicationState<Gedcomx> {

  public RecordState(URI uri) {
    this(uri, new StateFactory());
  }

  private RecordState(URI uri, StateFactory stateFactory) {
    this(uri, stateFactory.loadDefaultClient(), stateFactory);
  }

  private RecordState(URI uri, Client client, StateFactory stateFactory) {
    this(ClientRequest.create().accept(GedcomxConstants.GEDCOMX_JSON_MEDIA_TYPE).build(uri, HttpMethod.GET), client, stateFactory);
  }

  private RecordState(ClientRequest request, Client client, StateFactory stateFactory) {
    this(request, client.handle(request), null, stateFactory);
  }

  public RecordState(ClientRequest request, ClientResponse response, String accessToken, StateFactory stateFactory) {
    super(request, response, accessToken, stateFactory);
  }

  @Override
  public String getSelfRel() {
    return Rel.RECORD;
  }

  @Override
  protected RecordState clone(ClientRequest request, ClientResponse response) {
    return new RecordState(request, response, this.accessToken, this.stateFactory);
  }

  @Override
  public RecordState ifSuccessful() {
    return (RecordState) super.ifSuccessful();
  }

  @Override
  public RecordState head(StateTransitionOption... options) {
    return (RecordState) super.head(options);
  }

  @Override
  public RecordState options(StateTransitionOption... options) {
    return (RecordState) super.options(options);
  }

  @Override
  public RecordState get(StateTransitionOption... options) {
    return (RecordState) super.get(options);
  }

  @Override
  public RecordState delete(StateTransitionOption... options) {
    return (RecordState) super.delete(options);
  }

  @Override
  public RecordState put(Gedcomx e, StateTransitionOption... options) {
    return (RecordState) super.put(e, options);
  }

  @Override
  public RecordState post(Gedcomx entity, StateTransitionOption... options) {
    return (RecordState) super.post(entity, options);
  }

  @Override
  protected Gedcomx loadEntity(ClientResponse response) {
    return response.getEntity(Gedcomx.class);
  }

  @Override
  protected SupportsLinks getMainDataElement() {
    return getEntity();
  }

}
