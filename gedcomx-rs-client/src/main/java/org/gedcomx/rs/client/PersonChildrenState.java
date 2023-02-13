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
import org.gedcomx.common.ResourceReference;
import org.gedcomx.conclusion.Person;
import org.gedcomx.conclusion.Relationship;
import org.gedcomx.links.Link;
import org.gedcomx.links.SupportsLinks;
import org.gedcomx.rt.Rel;

import javax.ws.rs.HttpMethod;
import java.util.List;

/**
 * @author Ryan Heaton
 */
public class PersonChildrenState extends GedcomxApplicationState<Gedcomx> {

  protected PersonChildrenState(ClientRequest request, ClientResponse response, String accessToken, StateFactory stateFactory) {
    super(request, response, accessToken, stateFactory);
  }

  @Override
  protected PersonChildrenState clone(ClientRequest request, ClientResponse response) {
    return new PersonChildrenState(request, response, this.accessToken, this.stateFactory);
  }

  @Override
  public PersonChildrenState ifSuccessful() {
    return (PersonChildrenState) super.ifSuccessful();
  }

  @Override
  public PersonChildrenState head(StateTransitionOption... options) {
    return (PersonChildrenState) super.head(options);
  }

  @Override
  public PersonChildrenState options(StateTransitionOption... options) {
    return (PersonChildrenState) super.head(options);
  }

  @Override
  public PersonChildrenState get(StateTransitionOption... options) {
    return (PersonChildrenState) super.get(options);
  }

  @Override
  public PersonChildrenState delete(StateTransitionOption... options) {
    return (PersonChildrenState) super.delete(options);
  }

  @Override
  public PersonChildrenState put(Gedcomx e, StateTransitionOption... options) {
    return (PersonChildrenState) super.put(e, options);
  }

  @Override
  public PersonChildrenState post(Gedcomx entity, StateTransitionOption... options) {
    return (PersonChildrenState) super.post(entity, options);
  }

  @Override
  protected Gedcomx loadEntity(ClientResponse response) {
    return response.getEntity(Gedcomx.class);
  }

  @Override
  protected SupportsLinks getMainDataElement() {
    return getEntity();
  }

  public List<Person> getPersons() {
    return this.entity == null ? null : this.entity.getPersons();
  }

  public List<Relationship> getRelationships() {
    return this.entity == null ? null : this.entity.getRelationships();
  }

  public Relationship findRelationshipTo(Person child) {
    List<Relationship> relationships = getRelationships();
    if (relationships != null) {
      for (Relationship relationship : relationships) {
        ResourceReference childReference = relationship.getPerson2();
        if (childReference != null) {
          String reference = childReference.getResource().toString();
          if (reference.equals("#" + child.getId())) {
            return relationship;
          }
        }
      }
    }
    return null;
  }

  public PersonState readPerson(StateTransitionOption... options) {
    Link link = getLink(Rel.PERSON);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return this.stateFactory.newPersonState(request, invoke(request, options), this.accessToken);
  }

  public PersonState readChild(Person person, StateTransitionOption... options) {
    Link link = person.getLink(Rel.PERSON);
    link = link == null ? person.getLink(Rel.SELF) : link;
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return this.stateFactory.newPersonState(request, invoke(request, options), this.accessToken);
  }

  public RelationshipState readRelationship(Relationship relationship, StateTransitionOption... options) {
    Link link = relationship.getLink(Rel.RELATIONSHIP);
    link = link == null ? relationship.getLink(Rel.SELF) : link;
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return this.stateFactory.newRelationshipState(request, invoke(request, options), this.accessToken);
  }

  public RelationshipState removeRelationship(Relationship relationship, StateTransitionOption... options) {
    Link link = relationship.getLink(Rel.RELATIONSHIP);
    link = link == null ? relationship.getLink(Rel.SELF) : link;
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException("Unable to remove relationship: missing link.");
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.DELETE);
    return this.stateFactory.newRelationshipState(request, invoke(request, options), this.accessToken);
  }

  public RelationshipState removeRelationshipTo(Person child, StateTransitionOption... options) {
    Relationship relationship = findRelationshipTo(child);
    if (relationship == null) {
      throw new GedcomxApplicationException("Unable to remove relationship: not found.");
    }

    Link link = relationship.getLink(Rel.RELATIONSHIP);
    link = link == null ? relationship.getLink(Rel.SELF) : link;
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException("Unable to remove relationship: missing link.");
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.DELETE);
    return this.stateFactory.newRelationshipState(request, invoke(request, options), this.accessToken);
  }

}
