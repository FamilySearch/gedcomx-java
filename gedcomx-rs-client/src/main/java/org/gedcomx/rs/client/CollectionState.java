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
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;
import org.gedcomx.Gedcomx;
import org.gedcomx.common.ResourceReference;
import org.gedcomx.common.TextValue;
import org.gedcomx.conclusion.Fact;
import org.gedcomx.conclusion.Person;
import org.gedcomx.conclusion.Relationship;
import org.gedcomx.links.Link;
import org.gedcomx.links.SupportsLinks;
import org.gedcomx.records.Collection;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.rt.Rel;
import org.gedcomx.rt.util.PersonSearchQueryBuilder;
import org.gedcomx.source.SourceCitation;
import org.gedcomx.source.SourceDescription;
import org.gedcomx.types.RelationshipType;

import javax.activation.DataSource;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.List;

/**
 * @author Ryan Heaton
 */
public class CollectionState extends GedcomxApplicationState<Gedcomx> {

  public CollectionState(URI uri) {
    this(uri, new StateFactory());
  }

  private CollectionState(URI uri, StateFactory stateFactory) {
    this(uri, stateFactory.loadDefaultClient(), stateFactory);
  }

  private CollectionState(URI uri, Client client, StateFactory stateFactory) {
    this(ClientRequest.create().accept(GedcomxConstants.GEDCOMX_JSON_MEDIA_TYPE).build(uri, HttpMethod.GET), client, stateFactory);
  }

  private CollectionState(ClientRequest request, Client client, StateFactory stateFactory) {
    this(request, client.handle(request), null, stateFactory);
  }

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
  protected SupportsLinks getMainDataElement() {
    Collection collection = getCollection();
    return collection == null ? getDescription() : collection;
  }

  @Override
  public CollectionState head(StateTransitionOption... options) {
    return (CollectionState) super.head(options);
  }

  @Override
  public CollectionState options(StateTransitionOption... options) {
    return (CollectionState) super.options(options);
  }

  @Override
  public CollectionState get(StateTransitionOption... options) {
    return (CollectionState) super.get(options);
  }

  @Override
  public CollectionState delete(StateTransitionOption... options) {
    return (CollectionState) super.delete(options);
  }

  @Override
  public CollectionState put(Gedcomx e, StateTransitionOption... options) {
    return (CollectionState) super.put(e, options);
  }

  @Override
  public CollectionState post(Gedcomx entity, StateTransitionOption... options) {
    return (CollectionState) super.post(entity, options);
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
  public CollectionState authenticateViaOAuth2(MultivaluedMap<String, String> formData, StateTransitionOption... options) {
    return (CollectionState) super.authenticateViaOAuth2(formData, options);
  }

  public CollectionState update(Collection collection, StateTransitionOption... options) {
    return post(new Gedcomx().collection(collection), options);
  }

  public RecordsState readRecords(StateTransitionOption... options) {
    Link link = getLink(Rel.RECORDS);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return this.stateFactory.newRecordsState(request, invoke(request, options), this.accessToken);
  }

  public RecordState addRecord(Gedcomx record, StateTransitionOption... options) {
    Link link = getLink(Rel.RECORDS);
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException(String.format("Collection at %s doesn't support adding records.", getUri()));
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().entity(record).build(link.getHref().toURI(), HttpMethod.POST);
    return this.stateFactory.newRecordState(request, invoke(request, options), this.accessToken);
  }

  public PersonsState readPersons(StateTransitionOption... options) {
    Link link = getLink(Rel.PERSONS);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return this.stateFactory.newPersonsState(request, invoke(request, options), this.accessToken);
  }

  public PersonState addPerson(Person person, StateTransitionOption... options) {
    Gedcomx entity = new Gedcomx();
    entity.addPerson(person);
    return addPerson(entity, options);
  }

  public PersonState addPerson(Gedcomx entity, StateTransitionOption... options) {
    Link link = getLink(Rel.PERSONS);
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException(String.format("Collection at %s doesn't support adding persons.", getUri()));
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().entity(entity).build(link.getHref().toURI(), HttpMethod.POST);
    return this.stateFactory.newPersonState(request, invoke(request, options), this.accessToken);
  }

  public PersonState readPersonForCurrentUser(StateTransitionOption... options) {
    Link currentPersonLink = getLink(Rel.CURRENT_USER_PERSON);
    if (currentPersonLink == null || currentPersonLink.getHref() == null) {
      return null;
    }
    URI currentUserPersonUri = currentPersonLink.getHref().toURI();

    ClientRequest request = createAuthenticatedGedcomxRequest().build(currentUserPersonUri, HttpMethod.GET);
    return this.stateFactory.newPersonState(request, invoke(request, options), this.accessToken);
  }

  public PersonState readPerson(URI personUri, StateTransitionOption... options) {
    if (personUri == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(personUri, HttpMethod.GET);
    return this.stateFactory.newPersonState(request, invoke(request, options), this.accessToken);
  }

  public PersonSearchResultsState searchForPersons(PersonSearchQueryBuilder query, StateTransitionOption... options) {
    return searchForPersons(query.build(), options);
  }

  public PersonSearchResultsState searchForPersons(String query, StateTransitionOption... options) {
    Link searchLink = getLink(Rel.PERSON_SEARCH);
    if (searchLink == null || searchLink.getTemplate() == null) {
      return null;
    }
    String template = searchLink.getTemplate();

    String uri;
    try {
      uri = UriTemplate.fromTemplate(template).set("q", query).expand().replace("\"", "%22");   //UriTemplate does not encode DQUOTE: see RFC 6570 (http://tools.ietf.org/html/rfc6570#section-2.1)
    }
    catch (VariableExpansionException e) {
      throw new GedcomxApplicationException(e);
    }
    catch (MalformedUriTemplateException e) {
      throw new GedcomxApplicationException(e);
    }

    ClientRequest request = createAuthenticatedFeedRequest().build(URI.create(uri), HttpMethod.GET);
    return this.stateFactory.newPersonSearchResultsState(request, invoke(request, options), this.accessToken);
  }

  public RelationshipsState readRelationships(StateTransitionOption... options) {
    Link link = getLink(Rel.RELATIONSHIPS);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return this.stateFactory.newRelationshipsState(request, invoke(request, options), this.accessToken);
  }

  public RelationshipState addSpouseRelationship(PersonState person1, PersonState person2, StateTransitionOption... options) {
    return addSpouseRelationship(person1, person2, null, options);
  }

  public RelationshipState addSpouseRelationship(PersonState person1, PersonState person2, Fact fact, StateTransitionOption... options) {
    Relationship relationship = new Relationship();
    relationship.setPerson1(new ResourceReference(new org.gedcomx.common.URI(person1.getSelfUri().toString())));
    relationship.setPerson2(new ResourceReference(new org.gedcomx.common.URI(person2.getSelfUri().toString())));
    relationship.setKnownType(RelationshipType.Couple);
    relationship.addFact(fact);
    return addRelationship(relationship, options);
  }

  public RelationshipState addParentChildRelationship(PersonState parent, PersonState child, StateTransitionOption... options) {
    return addParentChildRelationship(parent, child, null, options);
  }

  public RelationshipState addParentChildRelationship(PersonState parent, PersonState child, Fact fact, StateTransitionOption... options) {
    Relationship relationship = new Relationship();
    relationship.setPerson1(new ResourceReference(new org.gedcomx.common.URI(parent.getSelfUri().toString())));
    relationship.setPerson2(new ResourceReference(new org.gedcomx.common.URI(child.getSelfUri().toString())));
    relationship.setKnownType(RelationshipType.ParentChild);
    relationship.addFact(fact);
    return addRelationship(relationship, options);
  }

  public RelationshipState addRelationship(Relationship relationship, StateTransitionOption... options) {
    Link link = getLink(Rel.RELATIONSHIPS);
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException(String.format("Collection at %s doesn't support adding relationships.", getUri()));
    }

    Gedcomx entity = new Gedcomx();
    entity.addRelationship(relationship);
    ClientRequest request = createAuthenticatedGedcomxRequest().entity(entity).build(link.getHref().toURI(), HttpMethod.POST);
    return this.stateFactory.newRelationshipState(request, invoke(request, options), this.accessToken);
  }

  public RelationshipsState addRelationships(List<Relationship> relationships, StateTransitionOption... options) {
    Link link = getLink(Rel.RELATIONSHIPS);
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException(String.format("Collection at %s doesn't support adding relationships.", getUri()));
    }

    Gedcomx entity = new Gedcomx();
    entity.setRelationships(relationships);
    ClientRequest request = createAuthenticatedGedcomxRequest().entity(entity).build(link.getHref().toURI(), HttpMethod.POST);
    return this.stateFactory.newRelationshipsState(request, invoke(request, options), this.accessToken);
  }

  public SourceDescriptionState addArtifact(DataSource artifact, StateTransitionOption... options) {
    return addArtifact(null, artifact, options);
  }

  public SourceDescriptionState addArtifact(SourceDescription description, DataSource artifact, StateTransitionOption... options) {
    return addArtifact(this, description, artifact, options);
  }

  static SourceDescriptionState addArtifact(GedcomxApplicationState state, SourceDescription description, DataSource artifact, StateTransitionOption... options) {
    Link link = state.getLink(Rel.ARTIFACTS);
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException(String.format("Resource at %s doesn't support adding artifacts.", state.getUri()));
    }

    FormDataMultiPart multiPart = new FormDataMultiPart();
    String mediaType =  artifact.getContentType();

    if (description != null) {
      if (description.getTitles() != null) {
        for (TextValue value : description.getTitles()) {
          multiPart.field("title", value.getValue());
        }
      }
      if (description.getDescriptions() != null) {
        for (TextValue value : description.getDescriptions()) {
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
    ClientRequest request = state.createAuthenticatedGedcomxRequest().type(MediaType.MULTIPART_FORM_DATA_TYPE).entity(multiPart).build(link.getHref().toURI(), HttpMethod.POST);
    return state.stateFactory.newSourceDescriptionState(request, state.invoke(request, options), state.accessToken);
  }

  public SourceDescriptionsState readSourceDescriptions(StateTransitionOption... options) {
    Link link = getLink(Rel.SOURCE_DESCRIPTIONS);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return this.stateFactory.newSourceDescriptionsState(request, invoke(request, options), this.accessToken);
  }

  public SourceDescriptionState addSourceDescription(SourceDescription source, StateTransitionOption... options) {
    Link link = getLink(Rel.SOURCE_DESCRIPTIONS);
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException(String.format("Collection at %s doesn't support adding source descriptions.", getUri()));
    }

    Gedcomx entity = new Gedcomx();
    entity.addSourceDescription(source);
    ClientRequest request = createAuthenticatedGedcomxRequest().entity(entity).build(link.getHref().toURI(), HttpMethod.POST);
    return this.stateFactory.newSourceDescriptionState(request, invoke(request, options), this.accessToken);
  }

  public CollectionState readCollection(StateTransitionOption... options) {
    Link link = getLink(Rel.COLLECTION);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return this.stateFactory.newCollectionState(request, invoke(request, options), this.accessToken);
  }

  public CollectionsState readSubcollections(StateTransitionOption... options) {
    Link link = getLink(Rel.SUBCOLLECTIONS);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return this.stateFactory.newCollectionsState(request, invoke(request, options), this.accessToken);
  }

  public CollectionState addCollection(Collection collection, StateTransitionOption... options) {
    return addCollection(collection, null, options);
  }

  public CollectionState addCollection(SourceDescription sourceDescription, StateTransitionOption... options) {
    return addCollection(null, sourceDescription, options);
  }

  public CollectionState addCollection(Collection collection, SourceDescription sourceDescription, StateTransitionOption... options) {
    Link link = getLink(Rel.SUBCOLLECTIONS);
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException(String.format("Collection at %s doesn't support adding subcollections.", getUri()));
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().entity(new Gedcomx().collection(collection).sourceDescription(sourceDescription)).build(link.getHref().toURI(), HttpMethod.POST);
    return this.stateFactory.newCollectionState(request, invoke(request, options), this.accessToken);
  }

  public SourceDescriptionsState readResourcesById(StateTransitionOption... options) {
    Link link = getLink(Rel.ARTIFACTS);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return this.stateFactory.newSourceDescriptionsState(request, invoke(request, options), this.accessToken);
  }

  public SourceDescriptionsState readResourcesOfCurrentUser(StateTransitionOption... options) {
    Link link = getLink(Rel.CURRENT_USER_RESOURCES);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return this.stateFactory.newSourceDescriptionsState(request, invoke(request, options), this.accessToken);
  }

  public List<SourceDescription> getSourceDescriptions() {
    return this.entity == null ? null : this.entity.getSourceDescriptions();
  }

  public SourceDescriptionState readSourceDescription(SourceDescription sourceDescription, StateTransitionOption... options) {
    Link link = sourceDescription.getLink(Rel.DESCRIPTION);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return this.stateFactory.newSourceDescriptionState(request, invoke(request, options), this.accessToken);
  }

  public PersonState readPerson(Person person, StateTransitionOption... options) {
    Link link = person.getLink(Rel.PERSON);
    if (link == null) {
      link = person.getLink(Rel.SELF);
    }

    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return this.stateFactory.newPersonState(request, invoke(request, options), this.accessToken);
  }
}
