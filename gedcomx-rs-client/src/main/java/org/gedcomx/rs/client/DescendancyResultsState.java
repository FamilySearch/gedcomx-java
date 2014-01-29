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
import org.gedcomx.rs.client.util.DescendancyTree;

/**
 * @author Ryan Heaton
 */
public class DescendancyResultsState<E> extends GedcomxApplicationState<Gedcomx> {

  protected DescendancyResultsState(ClientRequest request, ClientResponse response, String accessToken, StateFactory stateFactory) {
    super(request, response, accessToken, stateFactory);
  }

  @Override
  protected DescendancyResultsState clone(ClientRequest request, ClientResponse response) {
    return new DescendancyResultsState(request, response, this.accessToken, this.stateFactory);
  }

  @Override
  protected Gedcomx loadEntity(ClientResponse response) {
    return response.getEntity(Gedcomx.class);
  }

  @Override
  public DescendancyResultsState head() {
    return (DescendancyResultsState) super.head();
  }

  @Override
  public DescendancyResultsState options() {
    return (DescendancyResultsState) super.options();
  }

  @Override
  public DescendancyResultsState get() {
    return (DescendancyResultsState) super.get();
  }

  @Override
  public DescendancyResultsState delete() {
    return (DescendancyResultsState) super.delete();
  }

  @Override
  public DescendancyResultsState put(Gedcomx e) {
    return (DescendancyResultsState) super.put(e);
  }

  @Override
  protected SupportsLinks getScope() {
    return getEntity();
  }

  public DescendancyTree getTree() {
    return getEntity() != null ? new DescendancyTree(getEntity()) : null;
  }

}
