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
import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;
import org.gedcomx.Gedcomx;
import org.gedcomx.common.ResourceReference;
import org.gedcomx.common.TextValue;
import org.gedcomx.conclusion.Person;
import org.gedcomx.conclusion.Relationship;
import org.gedcomx.links.Link;
import org.gedcomx.links.SupportsLinks;
import org.gedcomx.records.Collection;
import org.gedcomx.rs.Rel;
import org.gedcomx.rs.client.util.GedcomxSearchQueryBuilder;
import org.gedcomx.source.SourceCitation;
import org.gedcomx.source.SourceDescription;
import org.gedcomx.types.RelationshipType;

import javax.activation.DataSource;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Map;

/**
 * @author Ryan Heaton
 */
public class CollectionState extends GedcomxApplicationState<Gedcomx> {

  protected CollectionState(ClientRequest request, ClientResponse response, String accessToken, StateFactory stateFactory) {
    super(request, response, accessToken, stateFactory);
  }

  @Override
  protected CollectionState clone(ClientRequest request, ClientResponse response) {
    return new CollectionState(request, response, this.accessToken, this.stateFactory);
  }

  @Override
  protected Gedcomx loadEntity(ClientResponse response) {
    return response.getEntity(Gedcomx.class);
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
  public CollectionState options() {
    return (CollectionState) super.options();
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
  public CollectionState authenticateWithAccessToken(String accessToken) {
    return (CollectionState) super.authenticateWithAccessToken(accessToken);
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

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return this.stateFactory.newRecordsState(request, invoke(request), this.accessToken);
  }

  public RecordState addRecord(Gedcomx record) {
    Link link = getLink(Rel.RECORDS);
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException(String.format("Collection at %s doesn't support adding records.", getUri()));
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().entity(record).build(link.getHref().toURI(), HttpMethod.POST);
    return this.stateFactory.newRecordState(request, invoke(request), this.accessToken);
  }

  public PersonsState readPersons() {
    Link link = getLink(Rel.PERSONS);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return this.stateFactory.newPersonsState(request, invoke(request), this.accessToken);
  }

  public PersonState addPerson(Person person) {
    Gedcomx entity = new Gedcomx();
    entity.addPerson(person);
    return addPerson(entity);
  }

  public PersonState addPerson(Gedcomx entity) {
    Link link = getLink(Rel.PERSONS);
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException(String.format("Collection at %s doesn't support adding persons.", getUri()));
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().entity(entity).build(link.getHref().toURI(), HttpMethod.POST);
    return this.stateFactory.newPersonState(request, invoke(request), this.accessToken);
  }

  public PersonState readPersonForCurrentUser() {
    Link currentPersonLink = getLink(Rel.CURRENT_USER_PERSON);
    if (currentPersonLink == null || currentPersonLink.getHref() == null) {
      return null;
    }
    URI currentUserPersonUri = currentPersonLink.getHref().toURI();

    ClientRequest request = createAuthenticatedGedcomxRequest().build(currentUserPersonUri, HttpMethod.GET);
    return this.stateFactory.newPersonState(request, invoke(request), this.accessToken);
  }

  public PersonState readPerson(URI personUri) {
    if (personUri == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(personUri, HttpMethod.GET);
    return this.stateFactory.newPersonState(request, invoke(request), this.accessToken);
  }

  public PersonSearchResultsState searchForPersons(GedcomxSearchQueryBuilder query) {
    return searchForPersons(query.build());
  }

  public PersonSearchResultsState searchForPersons(GedcomxSearchQueryBuilder query, QueryParameters params) {
    return searchForPersons(query.build(), params);
  }

  public PersonSearchResultsState searchForPersons(String query) {
    return searchForPersons(query, null);
  }

  public PersonSearchResultsState searchForPersons(String query, QueryParameters params) {
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

    UriBuilder uriBuilder = UriBuilder.fromUri(uri);
    if (params != null) {
      for (Map.Entry<String, String> param : params.getParams().entrySet()) {
        uriBuilder = uriBuilder.queryParam(param.getKey(), param.getValue());
      }
    }
    ClientRequest request = createAuthenticatedFeedRequest().build(uriBuilder.build(), HttpMethod.GET);
    return this.stateFactory.newPersonSearchResultsState(request, invoke(request), this.accessToken);
  }

  public RelationshipsState readRelationships() {
    Link link = getLink(Rel.RELATIONSHIPS);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return this.stateFactory.newRelationshipsState(request, invoke(request), this.accessToken);
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
    ClientRequest request = createAuthenticatedGedcomxRequest().entity(entity).build(link.getHref().toURI(), HttpMethod.POST);
    return this.stateFactory.newRelationshipState(request, invoke(request), this.accessToken);
  }

  public SourceDescriptionState addArtifact(DataSource artifact) {
    return addArtifact(null, artifact);
  }

  public SourceDescriptionState addArtifact(SourceDescription description, DataSource artifact) {
    Link link = getLink(Rel.ARTIFACTS);
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException(String.format("Collection at %s doesn't support adding artifacts.", getUri()));
    }

    FormDataMultiPart multiPart = new FormDataMultiPart();
    String mediaType =  artifact.getContentType();

    if (description != null) {
      if (description.getTitles() != null) {
        for (TextValue value : description.getTitles()) {
          multiPart.field("title", value.getValue());
        }
      }
      if (description.getDescription() != null) {
        for (TextValue value : description.getDescription()) {
          multiPart.field("description", value.getValue());
        }
      }
      if (description.getCitations() != null) {
        for (SourceCitation citation : description.getCitations()) {
          multiPart.field("citation", citation.getValue());
        }
      }
      if (description.getMediaType() != null) {
        mediaType = description.getMediaType();
      }
    }

    if (mediaType == null) {
      mediaType = MediaType.APPLICATION_OCTET_STREAM;//default to octet stream?
    }

    InputStream inputStream;
    try {
      inputStream = artifact.getInputStream();
    }
    catch (IOException e) {
      throw new IllegalArgumentException(e);
    }

    FormDataContentDisposition.FormDataContentDispositionBuilder cd = FormDataContentDisposition.name("artifact");
    if (artifact.getName() != null) {
      cd = cd.fileName(artifact.getName());
    }

    FormDataBodyPart artifactPart = new FormDataBodyPart(cd.build(), inputStream, MediaType.valueOf(mediaType));
    multiPart.getBodyParts().add(artifactPart);
    ClientRequest request = createAuthenticatedGedcomxRequest().type(MediaType.MULTIPART_FORM_DATA_TYPE).entity(multiPart).build(link.getHref().toURI(), HttpMethod.POST);
    return this.stateFactory.newSourceDescriptionState(request, invoke(request), this.accessToken);
  }

  public SourceDescriptionsState readSourceDescriptions() {
    Link link = getLink(Rel.SOURCE_DESCRIPTIONS);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return this.stateFactory.newSourceDescriptionsState(request, invoke(request), this.accessToken);
  }

  public SourceDescriptionState addSourceDescription(SourceDescription source) {
    Link link = getLink(Rel.SOURCE_DESCRIPTIONS);
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException(String.format("Collection at %s doesn't support adding source descriptions.", getUri()));
    }

    Gedcomx entity = new Gedcomx();
    entity.addSourceDescription(source);
    ClientRequest request = createAuthenticatedGedcomxRequest().entity(entity).build(link.getHref().toURI(), HttpMethod.POST);
    return this.stateFactory.newSourceDescriptionState(request, invoke(request), this.accessToken);
  }

  public CollectionState readCollection() {
    Link link = getLink(Rel.COLLECTION);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return this.stateFactory.newCollectionState(request, invoke(request), this.accessToken);
  }

  public CollectionsState readSubcollections() {
    Link link = getLink(Rel.COLLECTIONS);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return this.stateFactory.newCollectionsState(request, invoke(request), this.accessToken);
  }

  public CollectionState addCollection(Collection collection) {
    Link link = getLink(Rel.COLLECTIONS);
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException(String.format("Collection at %s doesn't support adding subcollections.", getUri()));
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.POST);
    return this.stateFactory.newCollectionState(request, invoke(request), this.accessToken).ifSuccessful();
  }

  public SourceDescriptionsState readResourcesOfCurrentUser() {
    Link link = getLink(Rel.CURRENT_USER_RESOURCES);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return this.stateFactory.newSourceDescriptionsState(request, invoke(request), this.accessToken);
  }

}
