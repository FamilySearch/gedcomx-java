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
import org.gedcomx.atom.Feed;
import org.gedcomx.rs.client.GedcomxApplicationState;
import org.gedcomx.rs.client.PersonSearchResultsState;
import org.gedcomx.rs.client.StateTransitionOption;

/**
 * @author Ryan Heaton
 */
public class PersonMatchResultsState extends PersonSearchResultsState {

  protected PersonMatchResultsState(ClientRequest request, ClientResponse response, String accessToken, FamilySearchStateFactory stateFactory) {
    super(request, response, accessToken, stateFactory);
  }

  @Override
  protected PersonMatchResultsState clone(ClientRequest request, ClientResponse response) {
    return new PersonMatchResultsState(request, response, this.accessToken, (FamilySearchStateFactory) this.stateFactory);
  }

  @Override
  public PersonMatchResultsState ifSuccessful() {
    return (PersonMatchResultsState) super.ifSuccessful();
  }

  @Override
  public PersonMatchResultsState head(StateTransitionOption... options) {
    return (PersonMatchResultsState) super.head(options);
  }

  @Override
  public PersonMatchResultsState get(StateTransitionOption... options) {
    return (PersonMatchResultsState) super.get(options);
  }

  @Override
  public PersonMatchResultsState delete(StateTransitionOption... options) {
    return (PersonMatchResultsState) super.delete(options);
  }

  @Override
  public PersonMatchResultsState put(Feed e, StateTransitionOption... options) {
    return (PersonMatchResultsState) super.put(e, options);
  }

  @Override
  public PersonMatchResultsState post(Feed entity, StateTransitionOption... options) {
    return (PersonMatchResultsState) super.post(entity, options);
  }

  @Override
  public PersonMatchResultsState readNextPage(StateTransitionOption... options) {
    return (PersonMatchResultsState) super.readNextPage(options);
  }

  @Override
  public PersonMatchResultsState readPreviousPage(StateTransitionOption... options) {
    return (PersonMatchResultsState) super.readPreviousPage(options);
  }

  @Override
  public PersonMatchResultsState readFirstPage(StateTransitionOption... options) {
    return (PersonMatchResultsState) super.readFirstPage(options);
  }

  @Override
  public PersonMatchResultsState readLastPage(StateTransitionOption... options) {
    return (PersonMatchResultsState) super.readLastPage(options);
  }
}
