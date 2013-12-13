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

import com.damnhandy.uri.template.MalformedUriTemplateException;
import com.damnhandy.uri.template.UriTemplate;
import com.damnhandy.uri.template.VariableExpansionException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import org.gedcomx.Gedcomx;
import org.gedcomx.common.ResourceReference;
import org.gedcomx.conclusion.Person;
import org.gedcomx.conclusion.Relationship;
import org.gedcomx.links.Link;
import org.gedcomx.links.SupportsLinks;
import org.gedcomx.records.Collection;
import org.gedcomx.rs.Rel;
import org.gedcomx.rs.client.util.GedcomxSearchQueryBuilder;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.source.SourceDescription;
import org.gedcomx.types.RelationshipType;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MultivaluedMap;
import java.io.InputStream;
import java.net.URI;

/**
 * @author Ryan Heaton
 */
public class CollectionState extends GedcomxApplicationState<Gedcomx> {

  public CollectionState(URI discoveryUri) {
    this(discoveryUri, loadDefaultClient());
  }

  public CollectionState(URI discoveryUri, Client client) {
    this(discoveryUri, client, HttpMethod.GET);
  }

  public CollectionState(URI discoveryUri, Client client, String method) {
    this(ClientRequest.create().accept(GedcomxConstants.GEDCOMX_JSON_MEDIA_TYPE).build(discoveryUri, method), client, null);
  }

  public CollectionState(ClientRequest request, Client client, String accessToken) {
    super(request, client, accessToken);
  }

  @Override
  protected CollectionState newApplicationState(ClientRequest request, Client client, String accessToken) {
    return new CollectionState(request, client, accessToken);
  }

  @Override
  protected Gedcomx loadEntity(ClientResponse response) {
    return response.getClientResponseStatus() == ClientResponse.Status.OK ? response.getEntity(Gedcomx.class) : null;
  }

  @Override
  public CollectionState ifSuccessful() {
    return (CollectionState) super.ifSuccessful();
  }

  @Override
  protected SupportsLinks getScope() {
    return getCollection();
  }

  @Override
  public CollectionState head() {
    return (CollectionState) super.head();
  }

  @Override
  public CollectionState get() {
    return (CollectionState) super.get();
  }

  @Override
  public CollectionState delete() {
    return (CollectionState) super.delete();
  }

  @Override
  public CollectionState put(Gedcomx e) {
    return (CollectionState) super.put(e);
  }

  public Collection getCollection() {
    return getEntity() == null ? null : getEntity().getCollections() == null ? null : getEntity().getCollections().isEmpty() ? null : getEntity().getCollections().get(0);
  }

  @Override
  public CollectionState authenticateViaOAuth2Password(String username, String password, String clientId) {
    return (CollectionState) super.authenticateViaOAuth2Password(username, password, clientId);
  }

  @Override
  public CollectionState authenticateViaOAuth2Password(String username, String password, String clientId, String clientSecret) {
    return (CollectionState) super.authenticateViaOAuth2Password(username, password, clientId, clientSecret);
  }

  @Override
  public CollectionState authenticateViaOAuth2AuthCode(String authCode, String redirect, String clientId) {
    return (CollectionState) super.authenticateViaOAuth2AuthCode(authCode, redirect, clientId);
  }

  @Override
  public CollectionState authenticateViaOAuth2AuthCode(String authCode, String redirect, String clientId, String clientSecret) {
    return (CollectionState) super.authenticateViaOAuth2AuthCode(authCode, redirect, clientId, clientSecret);
  }

  @Override
  public CollectionState authenticateViaOAuth2ClientCredentials(String clientId, String clientSecret) {
    return (CollectionState) super.authenticateViaOAuth2ClientCredentials(clientId, clientSecret);
  }

  @Override
  public CollectionState authenticateViaOAuth2(MultivaluedMap<String, String> formData) {
    return (CollectionState) super.authenticateViaOAuth2(formData);
  }

  public RecordsState readRecords() {
    Link link = getLink(Rel.RECORDS);
    if (link == null || link.getHref() == null) {
      return null;
    }

    return new RecordsState(createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET), this.client, this.accessToken);
  }

  public RecordState addRecord(Gedcomx record) {
    Link link = getLink(Rel.RECORDS);
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException(String.format("Collection at %s doesn't support adding records.", getUri()));
    }

    return new RecordState(createAuthenticatedGedcomxRequest().entity(record).build(link.getHref().toURI(), HttpMethod.POST), this.client, this.accessToken);
  }

  public PersonsState readPersons() {
    Link link = getLink(Rel.PERSONS);
    if (link == null || link.getHref() == null) {
      return null;
    }

    return new PersonsState(createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET), this.client, this.accessToken);
  }

  public PersonState addPerson(Person person) {
    Link link = getLink(Rel.PERSONS);
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException(String.format("Collection at %s doesn't support adding persons.", getUri()));
    }

    Gedcomx entity = new Gedcomx();
    entity.addPerson(person);
    return new PersonState(createAuthenticatedGedcomxRequest().entity(entity).build(link.getHref().toURI(), HttpMethod.POST), this.client, this.accessToken);
  }

  public PersonState getPersonForCurrentUser() {
    Link currentPersonLink = getLink(Rel.CURRENT_USER_PERSON);
    if (currentPersonLink == null || currentPersonLink.getHref() == null) {
      return null;
    }
    URI currentUserPersonUri = currentPersonLink.getHref().toURI();

    return new PersonState(createAuthenticatedGedcomxRequest().build(currentUserPersonUri, HttpMethod.GET), this.client, this.accessToken);
  }

  public PersonSearchResultsState searchForPersons(GedcomxSearchQueryBuilder query) {
    return searchForPersons(query.build());
  }

  public RelationshipsState readRelationships() {
    Link link = getLink(Rel.RELATIONSHIPS);
    if (link == null || link.getHref() == null) {
      return null;
    }

    return new RelationshipsState(createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET), this.client, this.accessToken);
  }

  public RelationshipState addSpouseRelationship(PersonState person1, PersonState person2) {
    Relationship relationship = new Relationship();
    relationship.setPerson1(new ResourceReference(new org.gedcomx.common.URI(person1.getSelfUri().toString())));
    relationship.setPerson2(new ResourceReference(new org.gedcomx.common.URI(person2.getSelfUri().toString())));
    relationship.setKnownType(RelationshipType.Couple);
    return addRelationship(relationship);
  }

  public RelationshipState addParentChildRelationship(PersonState parent, PersonState child) {
    Relationship relationship = new Relationship();
    relationship.setPerson1(new ResourceReference(new org.gedcomx.common.URI(parent.getSelfUri().toString())));
    relationship.setPerson2(new ResourceReference(new org.gedcomx.common.URI(child.getSelfUri().toString())));
    relationship.setKnownType(RelationshipType.ParentChild);
    return addRelationship(relationship);
  }

  public RelationshipState addRelationship(Relationship relationship) {
    Link link = getLink(Rel.RELATIONSHIPS);
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException(String.format("Collection at %s doesn't support adding relationships.", getUri()));
    }

    Gedcomx entity = new Gedcomx();
    entity.addRelationship(relationship);
    return new RelationshipState(createAuthenticatedGedcomxRequest().entity(entity).build(link.getHref().toURI(), HttpMethod.POST), this.client, this.accessToken);
  }

  public SourceDescriptionState addArtifact(InputStream artifact) {
    Link link = getLink(Rel.ARTIFACTS);
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException(String.format("Collection at %s doesn't support adding artifacts.", getUri()));
    }

    return new SourceDescriptionState(createAuthenticatedGedcomxRequest().entity(artifact).build(link.getHref().toURI(), HttpMethod.POST), this.client, this.accessToken);
  }

  public SourceDescriptionsState readSourceDescriptions() {
    Link link = getLink(Rel.SOURCE_DESCRIPTIONS);
    if (link == null || link.getHref() == null) {
      return null;
    }

    return new SourceDescriptionsState(createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET), this.client, this.accessToken);
  }

  public SourceDescriptionState addSourceDescription(SourceDescription source) {
    Link link = getLink(Rel.SOURCE_DESCRIPTIONS);
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException(String.format("Collection at %s doesn't support adding source descriptions.", getUri()));
    }

    Gedcomx entity = new Gedcomx();
    entity.addSourceDescription(source);
    return new SourceDescriptionState(createAuthenticatedGedcomxRequest().entity(entity).build(link.getHref().toURI(), HttpMethod.POST), this.client, this.accessToken);
  }

  public CollectionState readCollection() {
    Link link = getLink(Rel.COLLECTION);
    if (link == null || link.getHref() == null) {
      return null;
    }

    return new CollectionState(createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET), this.client, this.accessToken);
  }

  public CollectionsState readSubcollections() {
    Link link = getLink(Rel.COLLECTIONS);
    if (link == null || link.getHref() == null) {
      return null;
    }

    return new CollectionsState(createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET), this.client, this.accessToken);
  }

  public CollectionState addCollection(Collection collection) {
    Link link = getLink(Rel.COLLECTIONS);
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException(String.format("Collection at %s doesn't support adding subcollections.", getUri()));
    }

    return new CollectionState(createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.POST), this.client, this.accessToken).ifSuccessful();
  }

  public PersonSearchResultsState searchForPersons(String query) {
    Link searchLink = getLink(Rel.PERSON_SEARCH);
    if (searchLink == null || searchLink.getTemplate() == null) {
      return null;
    }
    String template = searchLink.getTemplate();

    String uri;
    try {
      uri = UriTemplate.fromTemplate(template).set("q", query).expand();
    }
    catch (VariableExpansionException e) {
      throw new GedcomxApplicationException(e);
    }
    catch (MalformedUriTemplateException e) {
      throw new GedcomxApplicationException(e);
    }

    return new PersonSearchResultsState(createAuthenticatedFeedRequest().build(URI.create(uri), HttpMethod.GET), this.client, this.accessToken);
  }

}
