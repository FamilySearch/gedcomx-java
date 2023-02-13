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
import org.familysearch.platform.FamilySearchPlatform;
import org.familysearch.platform.ct.ChildAndParentsRelationship;
import org.gedcomx.conclusion.Person;
import org.gedcomx.conclusion.Relationship;
import org.gedcomx.links.Link;
import org.gedcomx.links.SupportsLinks;
import org.gedcomx.rs.client.GedcomxApplicationState;
import org.gedcomx.rs.client.StateTransitionOption;
import org.gedcomx.rt.Rel;

import javax.ws.rs.HttpMethod;
import java.util.List;

/**
 * @author Ryan Heaton
 */
public class FamilyTreePersonFamiliesState extends GedcomxApplicationState<FamilySearchPlatform> {

  protected FamilyTreePersonFamiliesState(ClientRequest request, ClientResponse response, String accessToken, FamilyTreeStateFactory stateFactory) {
    super(request, response, accessToken, stateFactory);
  }

  @Override
  protected SupportsLinks getMainDataElement() {
    return getEntity();
  }

  @Override
  protected FamilyTreePersonFamiliesState clone(ClientRequest request, ClientResponse response) {
    return new FamilyTreePersonFamiliesState(request, response, this.accessToken, (FamilyTreeStateFactory) this.stateFactory);
  }

  @Override
  protected FamilySearchPlatform loadEntityConditionally(ClientResponse response) {
    if (HttpMethod.GET.equals(request.getMethod()) && (response.getClientResponseStatus() == ClientResponse.Status.OK
          || response.getClientResponseStatus() == ClientResponse.Status.GONE)) {
      return loadEntity(response);
    }
    else {
      return null;
    }
  }

  @Override
  protected FamilySearchPlatform loadEntity(ClientResponse response) {
    return response.getEntity(FamilySearchPlatform.class);
  }

  @Override
  public FamilySearchPlatform getEntity() {
    return super.getEntity();
  }

  @Override
  public FamilyTreePersonFamiliesState ifSuccessful() {
    return (FamilyTreePersonFamiliesState) super.ifSuccessful();
  }

  @Override
  public FamilyTreePersonFamiliesState head(StateTransitionOption... options) {
    return (FamilyTreePersonFamiliesState) super.head(options);
  }

  @Override
  public FamilyTreePersonFamiliesState get(StateTransitionOption... options) {
    return (FamilyTreePersonFamiliesState) super.get(options);
  }

  @Override
  public FamilyTreePersonFamiliesState options(StateTransitionOption... options) {
    return (FamilyTreePersonFamiliesState) super.options(options);
  }

  public FamilyTreePersonState readPerson(StateTransitionOption... options) {
    Link link = getLink(Rel.PERSON);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return ((FamilyTreeStateFactory)this.stateFactory).newPersonState(request, invoke(request, options), this.accessToken);
  }

 public FamilyTreePersonState readPerson(Person person, StateTransitionOption... options) {
   Link link = person.getLink(Rel.PERSON);
   if (link == null || link.getHref() == null) {
     return null;
   }

   ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
   return ((FamilyTreeStateFactory)this.stateFactory).newPersonState(request, invoke(request, options), this.accessToken);
  }

  public FamilyTreeRelationshipState readRelationship(Relationship relationship, StateTransitionOption... options) {
    Link link = relationship.getLink(Rel.RELATIONSHIP);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return ((FamilyTreeStateFactory)this.stateFactory).newRelationshipState(request, invoke(request, options), this.accessToken);
  }

  public ChildAndParentsRelationshipState readRelationship(ChildAndParentsRelationship relationship, StateTransitionOption... options) {
    Link link = relationship.getLink(Rel.RELATIONSHIP);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return ((FamilyTreeStateFactory)this.stateFactory).newChildAndParentsRelationshipState(request, invoke(request, options), this.accessToken);
  }

  public Person getPerson() {
    return getPersons() == null ? null : getPersons().isEmpty() ? null : getPersons().get(0);
  }

  public List<Person> getPersons() {
    return getEntity() == null ? null : getEntity().getPersons();
  }

  public List<Relationship> getRelationships() {
    return getEntity() == null ? null : getEntity().getRelationships();
  }

  public List<ChildAndParentsRelationship> getChildAndParentsRelationships() {
    return getEntity() == null ? null : getEntity().getChildAndParentsRelationships();
  }

}
