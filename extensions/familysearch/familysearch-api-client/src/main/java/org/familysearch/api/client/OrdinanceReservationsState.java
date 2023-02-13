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
import org.familysearch.platform.FamilySearchPlatform;
import org.gedcomx.conclusion.Person;
import org.gedcomx.links.Link;
import org.gedcomx.links.SupportsLinks;
import org.gedcomx.rs.client.CollectionState;
import org.gedcomx.rs.client.GedcomxApplicationState;
import org.gedcomx.rs.client.StateTransitionOption;
import org.gedcomx.rt.Rel;

import javax.ws.rs.HttpMethod;
import java.util.List;

/**
 * @author Ryan Heaton
 */
public class OrdinanceReservationsState extends GedcomxApplicationState<FamilySearchPlatform> {

  public OrdinanceReservationsState(ClientRequest request, ClientResponse response, String accessToken, FamilySearchStateFactory stateFactory) {
    super(request, response, accessToken, stateFactory);
  }

  @Override
  protected OrdinanceReservationsState clone(ClientRequest request, ClientResponse response) {
    return new OrdinanceReservationsState(request, response, this.accessToken, (FamilySearchStateFactory) this.stateFactory);
  }

  @Override
  public OrdinanceReservationsState ifSuccessful() {
    return (OrdinanceReservationsState) super.ifSuccessful();
  }

  @Override
  public OrdinanceReservationsState head(StateTransitionOption... options) {
    return (OrdinanceReservationsState) super.head(options);
  }

  @Override
  public OrdinanceReservationsState get(StateTransitionOption... options) {
    return (OrdinanceReservationsState) super.get(options);
  }

  @Override
  public OrdinanceReservationsState delete(StateTransitionOption... options) {
    return (OrdinanceReservationsState) super.delete(options);
  }

  @Override
  public OrdinanceReservationsState put(FamilySearchPlatform e, StateTransitionOption... options) {
    return (OrdinanceReservationsState) super.put(e, options);
  }

  @Override
  public OrdinanceReservationsState post(FamilySearchPlatform entity, StateTransitionOption... options) {
    return (OrdinanceReservationsState) super.post(entity, options);
  }

  @Override
  protected FamilySearchPlatform loadEntity(ClientResponse response) {
    return response.getClientResponseStatus() == ClientResponse.Status.OK ? response.getEntity(FamilySearchPlatform.class) : null;
  }

  public List<Person> getReservations() {
    return getEntity() == null ? null : getEntity().getPersons();
  }

  @Override
  protected SupportsLinks getMainDataElement() {
    return getEntity();
  }

  public CollectionState readCollection(StateTransitionOption... options) {
    Link link = getLink(Rel.COLLECTION);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return ((FamilySearchStateFactory)this.stateFactory).newCollectionState(request, invoke(request, options), this.accessToken);
  }

}
