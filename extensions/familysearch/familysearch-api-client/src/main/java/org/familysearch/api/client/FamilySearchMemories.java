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

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.familysearch.platform.artifacts.ArtifactMetadata;
import org.familysearch.platform.artifacts.ArtifactType;
import org.gedcomx.Gedcomx;
import org.gedcomx.rs.client.SourceDescriptionState;
import org.gedcomx.rs.client.StateTransitionOption;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.source.SourceDescription;

import javax.activation.DataSource;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MultivaluedMap;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;

import static org.familysearch.api.client.util.FamilySearchOptions.artifactType;

/**
 * @author Ryan Heaton
 */
public class FamilySearchMemories extends FamilySearchCollectionState {

  public static final String URI = "https://familysearch.org/platform/collections/memories";
  public static final String SANDBOX_URI = "https://sandbox.familysearch.org/platform/collections/memories";

  public FamilySearchMemories() {
    this(false);
  }

  public FamilySearchMemories(boolean sandbox) {
    this(java.net.URI.create(sandbox ? SANDBOX_URI : URI));
  }

  public FamilySearchMemories(URI uri) {
    this(uri, new FamilySearchStateFactory());
  }

  private FamilySearchMemories(URI uri, FamilySearchStateFactory stateFactory) {
    this(uri, stateFactory.loadDefaultClient(), stateFactory);
  }

  private FamilySearchMemories(URI uri, Client client, FamilySearchStateFactory stateFactory) {
    this(ClientRequest.create().accept(GedcomxConstants.GEDCOMX_JSON_MEDIA_TYPE).build(uri, HttpMethod.GET), client, stateFactory);
  }

  private FamilySearchMemories(ClientRequest request, Client client, FamilySearchStateFactory stateFactory) {
    this(request, client.handle(request), null, stateFactory);
  }

  protected FamilySearchMemories(ClientRequest request, ClientResponse client, String accessToken, FamilySearchStateFactory stateFactory) {
    super(request, client, accessToken, stateFactory);
  }

  @Override
  protected FamilySearchMemories clone(ClientRequest request, ClientResponse response) {
    return new FamilySearchMemories(request, response, this.accessToken, (FamilySearchStateFactory) this.stateFactory);
  }

  @Override
  public FamilySearchMemories ifSuccessful() {
    return (FamilySearchMemories) super.ifSuccessful();
  }

  @Override
  public FamilySearchMemories head(StateTransitionOption... options) {
    return (FamilySearchMemories) super.head(options);
  }

  @Override
  public FamilySearchMemories get(StateTransitionOption... options) {
    return (FamilySearchMemories) super.get(options);
  }

  @Override
  public FamilySearchMemories delete(StateTransitionOption... options) {
    return (FamilySearchMemories) super.delete(options);
  }

  @Override
  public FamilySearchMemories put(Gedcomx e, StateTransitionOption... options) {
    return (FamilySearchMemories) super.put(e, options);
  }

  @Override
  public FamilySearchMemories post(Gedcomx entity, StateTransitionOption... options) {
    return (FamilySearchMemories) super.post(entity, options);
  }

  @Override
  public FamilySearchMemories authenticateViaOAuth2Password(String username, String password, String clientId) {
    return (FamilySearchMemories) super.authenticateViaOAuth2Password(username, password, clientId);
  }

  @Override
  public FamilySearchMemories authenticateViaOAuth2Password(String username, String password, String clientId, String clientSecret) {
    return (FamilySearchMemories) super.authenticateViaOAuth2Password(username, password, clientId, clientSecret);
  }

  @Override
  public FamilySearchMemories authenticateViaOAuth2AuthCode(String authCode, String redirect, String clientId, String clientSecret) {
    return (FamilySearchMemories) super.authenticateViaOAuth2AuthCode(authCode, redirect, clientId, clientSecret);
  }

  @Override
  public FamilySearchMemories authenticateViaOAuth2AuthCode(String authCode, String redirect, String clientId) {
    return (FamilySearchMemories) super.authenticateViaOAuth2AuthCode(authCode, redirect, clientId);
  }

  @Override
  public FamilySearchMemories authenticateViaOAuth2ClientCredentials(String clientId, String clientSecret) {
    return (FamilySearchMemories) super.authenticateViaOAuth2ClientCredentials(clientId, clientSecret);
  }

  @Override
  public FamilySearchMemories authenticateViaOAuth2(MultivaluedMap<String, String> formData, StateTransitionOption... options) {
    return (FamilySearchMemories) super.authenticateViaOAuth2(formData);
  }

  public FamilySearchMemories authenticateViaUnauthenticatedAccess(String clientId, String ipAddress) {

    MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
    formData.add("grant_type", "unauthenticated_session");
    formData.add("client_id", clientId);
    formData.add("ip_address", ipAddress);

    return this.authenticateViaOAuth2(formData);
  }

  @Override
  public FamilySearchCollectionState readCollection(StateTransitionOption... options) {
    return (FamilySearchCollectionState) super.readCollection();
  }

  @Override
  public SourceDescriptionState addArtifact(SourceDescription description, DataSource artifact, StateTransitionOption... options) {
    if (description != null) {
      ArtifactMetadata artifactMetadata = description.findExtensionOfType(ArtifactMetadata.class);
      if (artifactMetadata != null) {
        ArtifactType type = artifactMetadata.getKnownType();
        if (type != null) {
          ArrayList<StateTransitionOption> newOptions = new ArrayList<StateTransitionOption>(Arrays.asList(options));
          newOptions.add(artifactType(type));
          options = newOptions.toArray(new StateTransitionOption[newOptions.size()]);
        }
      }
    }
    return super.addArtifact(description, artifact, options);
  }
}
