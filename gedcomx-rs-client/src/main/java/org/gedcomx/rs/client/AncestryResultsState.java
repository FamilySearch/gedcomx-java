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

import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import org.gedcomx.Gedcomx;
import org.gedcomx.links.SupportsLinks;
import org.gedcomx.rs.client.util.AncestryTree;

/**
 * @author Ryan Heaton
 */
public class AncestryResultsState extends GedcomxApplicationState<Gedcomx> {

  protected AncestryResultsState(ClientRequest request, ClientResponse response, String accessToken, StateFactory stateFactory) {
    super(request, response, accessToken, stateFactory);
  }

  @Override
  protected AncestryResultsState clone(ClientRequest request, ClientResponse response) {
    return new AncestryResultsState(request, response, this.accessToken, this.stateFactory);
  }

  @Override
  protected Gedcomx loadEntity(ClientResponse response) {
    return response.getClientResponseStatus() == ClientResponse.Status.OK ? response.getEntity(Gedcomx.class) : null;
  }

  @Override
  public AncestryResultsState ifSuccessful() {
    return (AncestryResultsState) super.ifSuccessful();
  }

  @Override
  public AncestryResultsState head() {
    return (AncestryResultsState) super.head();
  }

  @Override
  public AncestryResultsState get() {
    return (AncestryResultsState) super.get();
  }

  @Override
  public AncestryResultsState delete() {
    return (AncestryResultsState) super.delete();
  }

  @Override
  public AncestryResultsState put(Gedcomx e) {
    return (AncestryResultsState) super.put(e);
  }

  @Override
  protected SupportsLinks getScope() {
    return getEntity();
  }

  public AncestryTree getTree() {
    return getEntity() != null ? new AncestryTree(getEntity()) : null;
  }

}
