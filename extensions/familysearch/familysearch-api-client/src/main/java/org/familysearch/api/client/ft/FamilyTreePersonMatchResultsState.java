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
import org.familysearch.api.client.FamilySearchStateFactory;
import org.familysearch.api.client.PersonMatchResultsState;
import org.familysearch.api.client.Rel;
import org.familysearch.api.client.util.RequestUtil;
import org.gedcomx.atom.Entry;
import org.gedcomx.links.Link;
import org.gedcomx.rs.client.StateTransitionOption;

import javax.ws.rs.HttpMethod;

public class FamilyTreePersonMatchResultsState extends PersonMatchResultsState {

  protected FamilyTreePersonMatchResultsState(ClientRequest request, ClientResponse response, String accessToken, FamilySearchStateFactory stateFactory) {
    super(request, response, accessToken, stateFactory);
  }

  @Override
  protected PersonMatchResultsState clone(ClientRequest request, ClientResponse response) {
    return new FamilyTreePersonMatchResultsState(request, response, this.accessToken, (FamilySearchStateFactory) this.stateFactory);
  }

  public FamilyTreePersonMergeState readMergeOptions(Entry entry, StateTransitionOption... options) {
    Link link = entry.getLink(Rel.MERGE);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest()).build(link.getHref().toURI(), HttpMethod.OPTIONS);
    return ((FamilyTreeStateFactory)this.stateFactory).newPersonMergeState(request, invoke(request, options), this.accessToken);
  }

  public FamilyTreePersonMergeState readMergeAnalysis(Entry entry, StateTransitionOption... options) {
    Link link = entry.getLink(Rel.MERGE);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest()).build(link.getHref().toURI(), HttpMethod.GET);
    return ((FamilyTreeStateFactory)this.stateFactory).newPersonMergeState(request, invoke(request, options), this.accessToken);
  }

}
