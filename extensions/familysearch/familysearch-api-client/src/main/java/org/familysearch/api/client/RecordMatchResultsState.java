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
import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;

import org.familysearch.api.client.util.FamilySearchOptions;
import org.familysearch.api.client.util.RequestUtil;
import org.familysearch.platform.ct.MatchStatus;
import org.gedcomx.Gedcomx;
import org.gedcomx.atom.AtomModel;
import org.gedcomx.atom.Entry;
import org.gedcomx.atom.Feed;
import org.gedcomx.conclusion.Identifier;
import org.gedcomx.conclusion.Person;
import org.gedcomx.links.Link;
import org.gedcomx.rs.client.PersonSearchResultsState;
import org.gedcomx.rs.client.PersonState;
import org.gedcomx.rs.client.StateTransitionOption;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.types.IdentifierType;

/**
 *
 * @author: Family History Department
 * @author: wilfordej
 **/
public class RecordMatchResultsState extends PersonSearchResultsState {
  protected RecordMatchResultsState(ClientRequest request, ClientResponse response, String accessToken, FamilySearchStateFactory stateFactory) {
    super(request, response, accessToken, stateFactory);
  }

  @Override
  protected RecordMatchResultsState clone(ClientRequest request, ClientResponse response) {
    return new RecordMatchResultsState(request, response, this.accessToken, (FamilySearchStateFactory) this.stateFactory);
  }

  @Override
  public RecordMatchResultsState ifSuccessful() {
    return (RecordMatchResultsState) super.ifSuccessful();
  }

  @Override
  public RecordMatchResultsState head(StateTransitionOption... options) {
    return (RecordMatchResultsState) super.head(options);
  }

  @Override
  public RecordMatchResultsState get(StateTransitionOption... options) {
    return (RecordMatchResultsState) super.get(options);
  }

  @Override
  public RecordMatchResultsState delete(StateTransitionOption... options) {
    return (RecordMatchResultsState) super.delete(options);
  }

  @Override
  public RecordMatchResultsState put(Feed e, StateTransitionOption... options) {
    return (RecordMatchResultsState) super.put(e, options);
  }

  @Override
  public RecordMatchResultsState post(Feed entity, StateTransitionOption... options) {
    return (RecordMatchResultsState) super.post(entity, options);
  }

  @Override
  public RecordMatchResultsState readNextPage(StateTransitionOption... options) {
    return null;
  }   //Not supported

  @Override
  public RecordMatchResultsState readPreviousPage(StateTransitionOption... options) {
    return null;
  }  //Not supported

  @Override
  public RecordMatchResultsState readFirstPage(StateTransitionOption... options) {
    return null;
  }  //Not supported

  @Override
  public RecordMatchResultsState readLastPage(StateTransitionOption... options) {
    return null;
  }  //Not supported

  public PersonState readPerson(StateTransitionOption... options) {
    Link link = getLink(org.gedcomx.rs.Rel.PERSON);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return ((FamilySearchStateFactory)this.stateFactory).newPersonState(request, invoke(request, options), this.accessToken);
  }
}
