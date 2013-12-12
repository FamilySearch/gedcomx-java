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

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import org.gedcomx.Gedcomx;
import org.gedcomx.common.ResourceReference;
import org.gedcomx.conclusion.Person;
import org.gedcomx.conclusion.Relationship;
import org.gedcomx.links.Link;
import org.gedcomx.links.SupportsLinks;
import org.gedcomx.rs.Rel;
import org.gedcomx.rt.GedcomxConstants;

import javax.ws.rs.HttpMethod;
import java.net.URI;
import java.util.List;

/**
 * @author Ryan Heaton
 */
public class PersonParentsState extends GedcomxApplicationState<Gedcomx> {

  public PersonParentsState(URI discoveryUri) {
    this(discoveryUri, loadDefaultClient());
  }

  public PersonParentsState(URI discoveryUri, Client client) {
    this(ClientRequest.create().accept(GedcomxConstants.GEDCOMX_JSON_MEDIA_TYPE).build(discoveryUri, HttpMethod.GET), client, null);
  }

  public PersonParentsState(ClientRequest request, Client client, String accessToken) {
    super(request, client, accessToken);
  }

  @Override
  protected PersonParentsState newApplicationState(ClientRequest request, Client client, String accessToken) {
    return new PersonParentsState(request, client, accessToken);
  }

  @Override
  public PersonParentsState ifSuccessful() {
    return (PersonParentsState) super.ifSuccessful();
  }

  @Override
  public PersonParentsState head() {
    return (PersonParentsState) super.head();
  }

  @Override
  public PersonParentsState get() {
    return (PersonParentsState) super.get();
  }

  @Override
  public PersonParentsState delete() {
    return (PersonParentsState) super.delete();
  }

  @Override
  public PersonParentsState put(Gedcomx e) {
    return (PersonParentsState) super.put(e);
  }

  @Override
  protected Gedcomx loadEntity(ClientResponse response) {
    return response.getClientResponseStatus() == ClientResponse.Status.OK ? response.getEntity(Gedcomx.class) : null;
  }

  @Override
  protected SupportsLinks getScope() {
    return getEntity();
  }

  public List<Person> getPersons() {
    return this.entity == null ? null : this.entity.getPersons();
  }

  public List<Relationship> getRelationships() {
    return this.entity == null ? null : this.entity.getRelationships();
  }

  public Relationship findRelationshipTo(Person parent) {
    List<Relationship> relationships = getRelationships();
    if (relationships != null) {
      for (Relationship relationship : relationships) {
        ResourceReference parentReference = relationship.getPerson1();
        if (parentReference != null) {
          String reference = parentReference.getResource().toString();
          if (reference.equals("#" + parent.getId())) {
            return relationship;
          }
        }
      }
    }
    return null;
  }

  public PersonState readPerson() {
    Link link = getLink(Rel.PERSON);
    if (link == null || link.getHref() == null) {
      return null;
    }

    return new PersonState(createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET), this.client, this.accessToken);
  }

  public PersonState readParent(Person person) {
    Link link = person.getLink(Rel.PERSON);
    link = link == null ? person.getLink(Rel.SELF) : link;
    if (link == null || link.getHref() == null) {
      return null;
    }

    return new PersonState(createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET), this.client, this.accessToken);
  }

  public RelationshipState readRelationship(Relationship relationship) {
    Link link = relationship.getLink(Rel.RELATIONSHIP);
    link = link == null ? relationship.getLink(Rel.SELF) : link;
    if (link == null || link.getHref() == null) {
      return null;
    }

    return new RelationshipState(createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET), this.client, this.accessToken);
  }

  public RelationshipState removeRelationship(Relationship relationship) {
    Link link = relationship.getLink(Rel.RELATIONSHIP);
    link = link == null ? relationship.getLink(Rel.SELF) : link;
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException("Unable to remove relationship: missing link.");
    }

    return new RelationshipState(createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.DELETE), this.client, this.accessToken);
  }

  public RelationshipState removeRelationshipTo(Person parent) {
    Relationship relationship = findRelationshipTo(parent);
    if (relationship == null) {
      throw new GedcomxApplicationException("Unable to remove relationship: not found.");
    }

    Link link = relationship.getLink(Rel.RELATIONSHIP);
    link = link == null ? relationship.getLink(Rel.SELF) : link;
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException("Unable to remove relationship: missing link.");
    }

    return new RelationshipState(createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.DELETE), this.client, this.accessToken);
  }

}
