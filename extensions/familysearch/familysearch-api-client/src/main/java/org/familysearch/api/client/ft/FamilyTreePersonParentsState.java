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
import org.gedcomx.Gedcomx;
import org.gedcomx.common.ResourceReference;
import org.gedcomx.conclusion.Person;
import org.gedcomx.conclusion.Relationship;
import org.gedcomx.rs.client.PersonParentsState;
import org.gedcomx.rs.client.StateTransitionOption;

import javax.ws.rs.HttpMethod;
import java.util.List;

/**
 * @author Ryan Heaton
 */
public class FamilyTreePersonParentsState extends PersonParentsState {

  protected FamilyTreePersonParentsState(ClientRequest request, ClientResponse response, String accessToken, FamilyTreeStateFactory stateFactory) {
    super(request, response, accessToken, stateFactory);
  }

  @Override
  protected FamilyTreePersonParentsState clone(ClientRequest request, ClientResponse response) {
    return new FamilyTreePersonParentsState(request, response, this.accessToken, (FamilyTreeStateFactory) this.stateFactory);
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
    return (FamilySearchPlatform) super.getEntity();
  }

  public List<ChildAndParentsRelationship> getChildAndParentsRelationships() {
    return getEntity() == null ? null : getEntity().getChildAndParentsRelationships();
  }

  @Override
  public FamilyTreePersonParentsState ifSuccessful() {
    return (FamilyTreePersonParentsState) super.ifSuccessful();
  }

  @Override
  public FamilyTreePersonParentsState head(StateTransitionOption... options) {
    return (FamilyTreePersonParentsState) super.head(options);
  }

  @Override
  public FamilyTreePersonParentsState get(StateTransitionOption... options) {
    return (FamilyTreePersonParentsState) super.get(options);
  }

  @Override
  public FamilyTreePersonParentsState delete(StateTransitionOption... options) {
    return (FamilyTreePersonParentsState) super.delete(options);
  }

  @Override
  public FamilyTreePersonParentsState put(Gedcomx e, StateTransitionOption... options) {
    return (FamilyTreePersonParentsState) super.put(e, options);
  }

  @Override
  public FamilyTreePersonParentsState post(Gedcomx entity, StateTransitionOption... options) {
    return (FamilyTreePersonParentsState) super.post(entity, options);
  }

  @Override
  public FamilyTreePersonParentsState options(StateTransitionOption... options) {
    return (FamilyTreePersonParentsState) super.options(options);
  }

  @Override
  public FamilyTreePersonState readPerson(StateTransitionOption... options) {
    return (FamilyTreePersonState) super.readPerson(options);
  }

  @Override
  public FamilyTreePersonState readParent(Person person, StateTransitionOption... options) {
    return (FamilyTreePersonState) super.readParent(person, options);
  }

  @Override
  public FamilyTreeRelationshipState readRelationship(Relationship relationship, StateTransitionOption... options) {
    return (FamilyTreeRelationshipState) super.readRelationship(relationship, options);
  }

  @Override
  public FamilyTreeRelationshipState removeRelationship(Relationship relationship, StateTransitionOption... options) {
    return (FamilyTreeRelationshipState) super.removeRelationship(relationship, options);
  }

  @Override
  public FamilyTreeRelationshipState removeRelationshipTo(Person parent, StateTransitionOption... options) {
    return (FamilyTreeRelationshipState) super.removeRelationshipTo(parent, options);
  }

  public ChildAndParentsRelationship findChildAndParentsRelationshipTo(Person spouse) {
    List<ChildAndParentsRelationship> relationships = getChildAndParentsRelationships();
    if (relationships != null) {
      for (ChildAndParentsRelationship relationship : relationships) {
        ResourceReference personReference = relationship.getParent1();
        if (personReference == null) {
          personReference = relationship.getFather();
        }
        if (personReference != null) {
          String reference = personReference.getResource().toString();
          if (reference.equals("#" + spouse.getId())) {
            return relationship;
          }
        }
        personReference = relationship.getParent2();
        if (personReference == null) {
          personReference = relationship.getMother();
        }
        if (personReference != null) {
          String reference = personReference.getResource().toString();
          if (reference.equals("#" + spouse.getId())) {
            return relationship;
          }
        }
      }
    }
    return null;
  }
}
