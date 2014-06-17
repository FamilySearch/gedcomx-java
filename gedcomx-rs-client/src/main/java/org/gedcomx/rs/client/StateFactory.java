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
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.client.urlconnection.URLConnectionClientHandler;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.rt.json.GedcomJsonProvider;
import org.gedcomx.rt.json.GedcomxAtomJsonProvider;
import org.gedcomx.rt.xml.GedcomxXmlProvider;

import javax.ws.rs.HttpMethod;
import java.net.URI;

/**
 * @author Ryan Heaton
 */
public class StateFactory {

  public CollectionState newCollectionState(URI discoveryUri) {
    return newCollectionState(discoveryUri, loadDefaultClient());
  }

  public CollectionState newCollectionState(URI discoveryUri, Client client) {
    return newCollectionState(discoveryUri, client, HttpMethod.GET);
  }

  public CollectionState newCollectionState(URI discoveryUri, Client client, String method) {
    ClientRequest request = ClientRequest.create().accept(GedcomxConstants.GEDCOMX_JSON_MEDIA_TYPE).build(discoveryUri, method);
    return newCollectionState(request, client.handle(request), null);
  }

  public PersonState newPersonState(URI discoveryUri) {
    return newPersonState(discoveryUri, loadDefaultClient());
  }

  public PersonState newPersonState(URI discoveryUri, Client client) {
    return newPersonState(discoveryUri, client, HttpMethod.GET);
  }

  public PersonState newPersonState(URI discoveryUri, Client client, String method) {
    ClientRequest request = ClientRequest.create().accept(GedcomxConstants.GEDCOMX_JSON_MEDIA_TYPE).build(discoveryUri, method);
    return newPersonState(request, client.handle(request), null);
  }

  public RecordState newRecordState(URI discoveryUri) {
    return newRecordState(discoveryUri, loadDefaultClient());
  }

  public RecordState newRecordState(URI discoveryUri, Client client) {
    return newRecordState(discoveryUri, client, HttpMethod.GET);
  }

  public RecordState newRecordState(URI discoveryUri, Client client, String method) {
    ClientRequest request = ClientRequest.create().accept(GedcomxConstants.GEDCOMX_JSON_MEDIA_TYPE).build(discoveryUri, method);
    return newRecordState(request, client.handle(request), null);
  }

  protected Client loadDefaultClient() {
    return new Client(new URLConnectionClientHandler(), new DefaultClientConfig(GedcomJsonProvider.class, GedcomxXmlProvider.class, GedcomxAtomJsonProvider.class, JacksonJsonProvider.class));
  }

  protected AgentState newAgentState(ClientRequest request, ClientResponse response, String accessToken) {
    return new AgentState(request, response, accessToken, this);
  }

  protected AncestryResultsState newAncestryResultsState(ClientRequest request, ClientResponse response, String accessToken) {
    return new AncestryResultsState(request, response, accessToken, this);
  }

  protected CollectionsState newCollectionsState(ClientRequest request, ClientResponse response, String accessToken) {
    return new CollectionsState(request, response, accessToken, this);
  }

  protected CollectionState newCollectionState(ClientRequest request, ClientResponse response, String accessToken) {
    return new CollectionState(request, response, accessToken, this);
  }

  protected DescendancyResultsState newDescendancyResultsState(ClientRequest request, ClientResponse response, String accessToken) {
    return new DescendancyResultsState(request, response, accessToken, this);
  }

  protected PersonChildrenState newPersonChildrenState(ClientRequest request, ClientResponse response, String accessToken) {
    return new PersonChildrenState(request, response, accessToken, this);
  }

  protected PersonParentsState newPersonParentsState(ClientRequest request, ClientResponse response, String accessToken) {
    return new PersonParentsState(request, response, accessToken, this);
  }

  protected PersonSearchResultsState newPersonSearchResultsState(ClientRequest request, ClientResponse response, String accessToken) {
    return new PersonSearchResultsState(request, response, accessToken, this);
  }

  protected PlaceSearchResultsState newPlaceSearchResultsState(ClientRequest request, ClientResponse response, String accessToken) {
    return new PlaceSearchResultsState(request, response, accessToken, this);
  }

  protected PlaceDescriptionState newPlaceDescriptionState(ClientRequest request, ClientResponse response, String accessToken) {
    return new PlaceDescriptionState(request, response, accessToken, this);
  }

  protected PlaceDescriptionsState newPlaceDescriptionsState(ClientRequest request, ClientResponse response, String accessToken) {
    return new PlaceDescriptionsState(request, response, accessToken, this);
  }

  protected PersonSpousesState newPersonSpousesState(ClientRequest request, ClientResponse response, String accessToken) {
    return new PersonSpousesState(request, response, accessToken, this);
  }

  protected PersonsState newPersonsState(ClientRequest request, ClientResponse response, String accessToken) {
    return new PersonsState(request, response, accessToken, this);
  }

  protected PersonState newPersonState(ClientRequest request, ClientResponse response, String accessToken) {
    return new PersonState(request, response, accessToken, this);
  }

  protected RecordsState newRecordsState(ClientRequest request, ClientResponse response, String accessToken) {
    return new RecordsState(request, response, accessToken, this);
  }

  protected RecordState newRecordState(ClientRequest request, ClientResponse response, String accessToken) {
    return new RecordState(request, response, accessToken, this);
  }

  protected RelationshipsState newRelationshipsState(ClientRequest request, ClientResponse response, String accessToken) {
    return new RelationshipsState(request, response, accessToken, this);
  }

  protected RelationshipState newRelationshipState(ClientRequest request, ClientResponse response, String accessToken) {
    return new RelationshipState(request, response, accessToken, this);
  }

  protected SourceDescriptionsState newSourceDescriptionsState(ClientRequest request, ClientResponse response, String accessToken) {
    return new SourceDescriptionsState(request, response, accessToken, this);
  }

  protected SourceDescriptionState newSourceDescriptionState(ClientRequest request, ClientResponse response, String accessToken) {
    return new SourceDescriptionState(request, response, accessToken, this);
  }

}
