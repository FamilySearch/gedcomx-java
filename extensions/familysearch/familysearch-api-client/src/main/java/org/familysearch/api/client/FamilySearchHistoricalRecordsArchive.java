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

import com.damnhandy.uri.template.MalformedUriTemplateException;
import com.damnhandy.uri.template.UriTemplate;
import com.damnhandy.uri.template.VariableExpansionException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import org.familysearch.api.rt.Rel;
import org.gedcomx.Gedcomx;
import org.gedcomx.links.Link;
import org.gedcomx.rs.client.GedcomxApplicationException;
import org.gedcomx.rs.client.RecordsState;
import org.gedcomx.rs.client.SourceDescriptionState;
import org.gedcomx.rs.client.StateTransitionOption;
import org.gedcomx.rt.GedcomxConstants;


import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MultivaluedMap;
import java.net.URI;

/**
 * @author Ryan Heaton
 */
public class FamilySearchHistoricalRecordsArchive extends FamilySearchCollectionState {

  public static final String URI = "https://api.familysearch.org/platform/collections/records";
  public static final String SANDBOX_URI = "https://api-integ.familysearch.org/platform/collections/records";

  public FamilySearchHistoricalRecordsArchive() {
    this(false);
  }

  public FamilySearchHistoricalRecordsArchive(boolean sandbox) {
    this(sandbox ? FamilySearchReferenceEnvironment.SANDBOX : FamilySearchReferenceEnvironment.PRODUCTION);
  }

  public FamilySearchHistoricalRecordsArchive(FamilySearchReferenceEnvironment env) {
    this(env.getHistoricalRecordsArchiveUri());
  }

  public FamilySearchHistoricalRecordsArchive(URI uri) {
    this(uri, new FamilySearchStateFactory());
  }

  private FamilySearchHistoricalRecordsArchive(URI uri, FamilySearchStateFactory stateFactory) {
    this(uri, stateFactory.loadDefaultClient(), stateFactory);
  }

  private FamilySearchHistoricalRecordsArchive(URI uri, Client client, FamilySearchStateFactory stateFactory) {
    this(ClientRequest.create().accept(GedcomxConstants.GEDCOMX_JSON_MEDIA_TYPE).build(uri, HttpMethod.GET), client, stateFactory);
  }

  private FamilySearchHistoricalRecordsArchive(ClientRequest request, Client client, FamilySearchStateFactory stateFactory) {
    this(request, client.handle(request), null, stateFactory);
  }

  protected FamilySearchHistoricalRecordsArchive(ClientRequest request, ClientResponse client, String accessToken, FamilySearchStateFactory stateFactory) {
    super(request, client, accessToken, stateFactory);
  }

  @Override
  protected FamilySearchHistoricalRecordsArchive clone(ClientRequest request, ClientResponse response) {
    return new FamilySearchHistoricalRecordsArchive(request, response, this.accessToken, (FamilySearchStateFactory) this.stateFactory);
  }

  @Override
  public FamilySearchHistoricalRecordsArchive ifSuccessful() {
    return (FamilySearchHistoricalRecordsArchive) super.ifSuccessful();
  }

  @Override
  public FamilySearchHistoricalRecordsArchive head(StateTransitionOption... options) {
    return (FamilySearchHistoricalRecordsArchive) super.head(options);
  }

  @Override
  public FamilySearchHistoricalRecordsArchive get(StateTransitionOption... options) {
    return (FamilySearchHistoricalRecordsArchive) super.get(options);
  }

  @Override
  public FamilySearchHistoricalRecordsArchive delete(StateTransitionOption... options) {
    return (FamilySearchHistoricalRecordsArchive) super.delete(options);
  }

  @Override
  public FamilySearchHistoricalRecordsArchive put(Gedcomx e, StateTransitionOption... options) {
    return (FamilySearchHistoricalRecordsArchive) super.put(e, options);
  }

  @Override
  public FamilySearchHistoricalRecordsArchive post(Gedcomx entity, StateTransitionOption... options) {
    return (FamilySearchHistoricalRecordsArchive) super.post(entity, options);
  }

  @Override
  public FamilySearchHistoricalRecordsArchive authenticateViaOAuth2Password(String username, String password, String clientId) {
    return (FamilySearchHistoricalRecordsArchive) super.authenticateViaOAuth2Password(username, password, clientId);
  }

  @Override
  public FamilySearchHistoricalRecordsArchive authenticateViaOAuth2Password(String username, String password, String clientId, String clientSecret) {
    return (FamilySearchHistoricalRecordsArchive) super.authenticateViaOAuth2Password(username, password, clientId, clientSecret);
  }

  @Override
  public FamilySearchHistoricalRecordsArchive authenticateViaOAuth2AuthCode(String authCode, String redirect, String clientId, String clientSecret) {
    return (FamilySearchHistoricalRecordsArchive) super.authenticateViaOAuth2AuthCode(authCode, redirect, clientId, clientSecret);
  }

  @Override
  public FamilySearchHistoricalRecordsArchive authenticateViaOAuth2AuthCode(String authCode, String redirect, String clientId) {
    return (FamilySearchHistoricalRecordsArchive) super.authenticateViaOAuth2AuthCode(authCode, redirect, clientId);
  }

  @Override
  public FamilySearchHistoricalRecordsArchive authenticateViaOAuth2ClientCredentials(String clientId, String clientSecret) {
    return (FamilySearchHistoricalRecordsArchive) super.authenticateViaOAuth2ClientCredentials(clientId, clientSecret);
  }

  @Override
  public FamilySearchHistoricalRecordsArchive authenticateViaOAuth2(MultivaluedMap<String, String> formData, StateTransitionOption... options) {
    return (FamilySearchHistoricalRecordsArchive) super.authenticateViaOAuth2(formData);
  }

  @Override
  public FamilySearchCollectionState readCollection(StateTransitionOption... options) {
    return (FamilySearchCollectionState) super.readCollection();
  }

  public RecordsState readImageRecords(String imageid, StateTransitionOption... options) {
    Link imageRecordsLink = getLink(Rel.IMAGE_RECORDS);
    if (imageRecordsLink == null || imageRecordsLink.getTemplate() == null) {
      return null;
    }
    String template = imageRecordsLink.getTemplate();

    String imageRecordsUri;
    try {
      imageRecordsUri = UriTemplate.fromTemplate(template).set("iid", imageid).expand();
    }
    catch (VariableExpansionException e) {
      throw new GedcomxApplicationException(e);
    }
    catch (MalformedUriTemplateException e) {
      throw new GedcomxApplicationException(e);
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(java.net.URI.create(imageRecordsUri), HttpMethod.GET);
    return ((FamilySearchStateFactory)this.stateFactory).newRecordsState(request, invoke(request, options), this.accessToken);
  }

  public SourceDescriptionState readImageMetadata(String imageid, StateTransitionOption... options) {
    Link imageRecordsLink = getLink(Rel.IMAGE_METADATA);
    if (imageRecordsLink == null || imageRecordsLink.getTemplate() == null) {
      return null;
    }
    String template = imageRecordsLink.getTemplate();

    String imageRecordsUri;
    try {
      imageRecordsUri = UriTemplate.fromTemplate(template).set("iid", imageid).expand();
    }
    catch (VariableExpansionException e) {
      throw new GedcomxApplicationException(e);
    }
    catch (MalformedUriTemplateException e) {
      throw new GedcomxApplicationException(e);
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(java.net.URI.create(imageRecordsUri), HttpMethod.GET);
    return ((FamilySearchStateFactory)this.stateFactory).newSourceDescriptionState(request, invoke(request, options), this.accessToken);
  }

  public SourceDescriptionState readWaypoint(String waypointId, String collectionContext, StateTransitionOption... options) {
    Link waypointLink = getLink(Rel.IMAGE_WAYPOINT);
    if (waypointLink == null || waypointLink.getTemplate() == null) {
      return null;
    }
    String template = waypointLink.getTemplate();

    String waypointUri;
    try {
      waypointUri = UriTemplate.fromTemplate(template).set("wid", waypointId).set("cc", collectionContext).expand();
    }
    catch (VariableExpansionException e) {
      throw new GedcomxApplicationException(e);
    }
    catch (MalformedUriTemplateException e) {
      throw new GedcomxApplicationException(e);
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(java.net.URI.create(waypointUri ), HttpMethod.GET);
    return ((FamilySearchStateFactory)this.stateFactory).newSourceDescriptionState(request, invoke(request, options), this.accessToken);
  }

  public SourceDescriptionState readCollectionWaypoints(String collectionId, StateTransitionOption... options) {
    Link waypointLink = getLink(Rel.COLLECTION_WAYPOINTS);
    if (waypointLink == null || waypointLink.getTemplate() == null) {
      return null;
    }
    String template = waypointLink.getTemplate();

    String waypointUri;
    try {
      waypointUri = UriTemplate.fromTemplate(template).set("clid", collectionId).expand();
    }
    catch (VariableExpansionException e) {
      throw new GedcomxApplicationException(e);
    }
    catch (MalformedUriTemplateException e) {
      throw new GedcomxApplicationException(e);
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(java.net.URI.create(waypointUri ), HttpMethod.GET);
    return ((FamilySearchStateFactory)this.stateFactory).newSourceDescriptionState(request, invoke(request, options), this.accessToken);
  }

  public RecordMatchResultsState matchPersona(String personaId, StateTransitionOption... options) {
    Link matchLink = getLink(Rel.MATCHES);
    if (matchLink == null || matchLink.getTemplate() == null) {
      return null;
    }
    String template = matchLink.getTemplate();

    String matchUri;
    try {
      matchUri = UriTemplate.fromTemplate(template).set("pid", personaId).expand();
    }
    catch (VariableExpansionException e) {
      throw new GedcomxApplicationException(e);
    }
    catch(MalformedUriTemplateException e) {
      throw new GedcomxApplicationException(e);
    }

    ClientRequest request = createAuthenticatedFeedRequest().build(java.net.URI.create(matchUri), HttpMethod.GET);
    return ((FamilySearchStateFactory)this.stateFactory).newRecordMatchResultsState(request, invoke(request, options), this.accessToken);
  }

}
