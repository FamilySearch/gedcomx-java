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
import org.gedcomx.links.Link;
import org.gedcomx.rs.client.GedcomxApplicationException;
import org.gedcomx.rs.client.StateTransitionOption;
import org.gedcomx.rt.GedcomxConstants;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

/**
 * @author Tygan Shelton
 */
public class ArtifactsCollection extends FamilySearchCollectionState {

  public static final String URI = "https://api.familysearch.org/platform/collections/artifacts";
  public static final String SANDBOX_URI = "https://api-integ.familysearch.org/platform/collections/artifacts";
  public static final String DGS_FOLDER_PARAM = "folder";
  public static final String START_PARAM = "start";
  public static final String COUNT_PARAM = "count";

  public ArtifactsCollection() {
    this(false);
  }

  public ArtifactsCollection(boolean sandbox) {
    this(sandbox ? FamilySearchReferenceEnvironment.SANDBOX : FamilySearchReferenceEnvironment.PRODUCTION);
  }

  public ArtifactsCollection(FamilySearchReferenceEnvironment env) {
    this(env.getHistoricalRecordsArchiveUri());
  }

  public ArtifactsCollection(URI uri) {
    this(uri, new FamilySearchStateFactory());
  }

  private ArtifactsCollection(URI uri, FamilySearchStateFactory stateFactory) {
    this(uri, stateFactory.loadDefaultClient(), stateFactory);
  }

  private ArtifactsCollection(URI uri, Client client, FamilySearchStateFactory stateFactory) {
    this(ClientRequest.create().accept(GedcomxConstants.GEDCOMX_JSON_MEDIA_TYPE).build(uri, HttpMethod.GET), client, stateFactory);
  }

  private ArtifactsCollection(ClientRequest request, Client client, FamilySearchStateFactory stateFactory) {
    this(request, client.handle(request), null, stateFactory);
  }

  protected ArtifactsCollection(ClientRequest request, ClientResponse client, String accessToken, FamilySearchStateFactory stateFactory) {
    super(request, client, accessToken, stateFactory);
  }

  @Override
  protected ArtifactsCollection clone(ClientRequest request, ClientResponse response) {
    return new ArtifactsCollection(request, response, this.accessToken, (FamilySearchStateFactory) this.stateFactory);
  }

  @Override
  public ArtifactsCollection ifSuccessful() {
    return (ArtifactsCollection) super.ifSuccessful();
  }

  @Override
  public ArtifactsCollection head(StateTransitionOption... options) {
    return (ArtifactsCollection) super.head(options);
  }

  @Override
  public ArtifactsCollection get(StateTransitionOption... options) {
    return (ArtifactsCollection) super.get(options);
  }

  @Override
  public ArtifactsCollection authenticateViaOAuth2Password(String username, String password, String clientId) {
    return (ArtifactsCollection) super.authenticateViaOAuth2Password(username, password, clientId);
  }

  @Override
  public ArtifactsCollection authenticateViaOAuth2Password(String username, String password, String clientId, String clientSecret) {
    return (ArtifactsCollection) super.authenticateViaOAuth2Password(username, password, clientId, clientSecret);
  }

  @Override
  public ArtifactsCollection authenticateViaOAuth2AuthCode(String authCode, String redirect, String clientId, String clientSecret) {
    return (ArtifactsCollection) super.authenticateViaOAuth2AuthCode(authCode, redirect, clientId, clientSecret);
  }

  @Override
  public ArtifactsCollection authenticateViaOAuth2AuthCode(String authCode, String redirect, String clientId) {
    return (ArtifactsCollection) super.authenticateViaOAuth2AuthCode(authCode, redirect, clientId);
  }

  @Override
  public ArtifactsCollection authenticateViaOAuth2ClientCredentials(String clientId, String clientSecret) {
    return (ArtifactsCollection) super.authenticateViaOAuth2ClientCredentials(clientId, clientSecret);
  }

  @Override
  public ArtifactsCollection authenticateViaOAuth2(MultivaluedMap<String, String> formData, StateTransitionOption... options) {
    return (ArtifactsCollection) super.authenticateViaOAuth2(formData);
  }

  @Override
  public FamilySearchCollectionState readCollection(StateTransitionOption... options) {
    return (FamilySearchCollectionState) super.readCollection();
  }

  public FamilySearchCollectionState readFolder(String dgsFolder, StateTransitionOption... options) {
    Link artifactFolderLink = getLink(Rel.ARTIFACTS_DGS_FOLDER);
    if (artifactFolderLink == null || artifactFolderLink.getTemplate() == null) {
      return null;
    }
    String template = artifactFolderLink.getTemplate();

    String artifactFolderUri;
    try {
      artifactFolderUri = UriTemplate.fromTemplate(template).set(DGS_FOLDER_PARAM, dgsFolder).expand();
    }
    catch (VariableExpansionException e) {
      throw new GedcomxApplicationException(e);
    }
    catch (MalformedUriTemplateException e) {
      throw new GedcomxApplicationException(e);
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(java.net.URI.create(artifactFolderUri), HttpMethod.GET);
    return ((FamilySearchStateFactory)this.stateFactory).newCollectionState(request, invoke(request, options), this.accessToken);
  }

  public FamilySearchCollectionState readFolderArtifacts(String dgsFolder, int count, int start, StateTransitionOption... options) {
    Link artifactLink = getLink(Rel.ARTIFACTS);
    if (artifactLink == null || artifactLink.getTemplate() == null) {
      return null;
    }
    String template = artifactLink.getTemplate();

    String artifactUri;
    try {

      artifactUri = UriTemplate.fromTemplate(template).set(DGS_FOLDER_PARAM, dgsFolder).expand();
    }
    catch (VariableExpansionException e) {
      throw new GedcomxApplicationException(e);
    }
    catch (MalformedUriTemplateException e) {
      throw new GedcomxApplicationException(e);
    }

    URI uri = UriBuilder.fromUri(artifactUri).queryParam("count", Integer.toString(count)).queryParam("start", Integer.toString(start)).build();
    ClientRequest request = createAuthenticatedGedcomxRequest().build(uri, HttpMethod.GET);
    return ((FamilySearchStateFactory)this.stateFactory).newCollectionState(request, invoke(request, options), this.accessToken);
  }
}
