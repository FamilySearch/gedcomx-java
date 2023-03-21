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
import org.gedcomx.rs.client.PersonChildrenState;
import org.gedcomx.rs.client.StateTransitionOption;

import jakarta.ws.rs.HttpMethod;
import java.util.List;

/**
 * @author Ryan Heaton
 */
public class FamilyTreePersonChildrenState extends PersonChildrenState {

  protected FamilyTreePersonChildrenState(ClientRequest request, ClientResponse response, String accessToken, FamilyTreeStateFactory stateFactory) {
    super(request, response, accessToken, stateFactory);
  }

  @Override
  protected FamilyTreePersonChildrenState clone(ClientRequest request, ClientResponse response) {
    return new FamilyTreePersonChildrenState(request, response, this.accessToken, (FamilyTreeStateFactory) this.stateFactory);
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
  public FamilyTreePersonChildrenState ifSuccessful() {
    return (FamilyTreePersonChildrenState) super.ifSuccessful();
  }

  @Override
  public FamilyTreePersonChildrenState head(StateTransitionOption... options) {
    return (FamilyTreePersonChildrenState) super.head(options);
  }

  @Override
  public FamilyTreePersonChildrenState get(StateTransitionOption... options) {
    return (FamilyTreePersonChildrenState) super.get(options);
  }

  @Override
  public FamilyTreePersonChildrenState delete(StateTransitionOption... options) {
    return (FamilyTreePersonChildrenState) super.delete(options);
  }

  @Override
  public FamilyTreePersonChildrenState put(Gedcomx e, StateTransitionOption... options) {
    return (FamilyTreePersonChildrenState) super.put(e, options);
  }

  @Override
  public FamilyTreePersonChildrenState post(Gedcomx entity, StateTransitionOption... options) {
    return (FamilyTreePersonChildrenState) super.post(entity, options);
  }

  @Override
  public FamilyTreePersonChildrenState options(StateTransitionOption... options) {
    return (FamilyTreePersonChildrenState) super.options(options);
  }

  @Override
  public FamilyTreePersonState readPerson(StateTransitionOption... options) {
    return (FamilyTreePersonState) super.readPerson(options);
  }

  @Override
  public FamilyTreePersonState readChild(Person person, StateTransitionOption... options) {
    return (FamilyTreePersonState) super.readChild(person, options);
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

  public ChildAndParentsRelationship findChildAndParentsRelationshipTo(Person child) {
    List<ChildAndParentsRelationship> relationships = getChildAndParentsRelationships();
    if (relationships != null) {
      for (ChildAndParentsRelationship relationship : relationships) {
        ResourceReference personReference = relationship.getChild();
        if (personReference != null) {
          String reference = personReference.getResource().toString();
          if (reference.equals("#" + child.getId())) {
            return relationship;
          }
        }
      }
    }
    return null;
  }
}
