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
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.client.urlconnection.URLConnectionClientHandler;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.familysearch.api.client.rt.FamilySearchPlatformJsonProvider;
import org.familysearch.api.client.util.ExperimentsFilter;
import org.familysearch.platform.*;
import org.familysearch.platform.Error;
import org.familysearch.platform.artifacts.ArtifactMetadata;
import org.familysearch.platform.ct.*;
import org.familysearch.platform.discussions.Discussion;
import org.familysearch.platform.ordinances.Ordinance;
import org.familysearch.platform.ordinances.OrdinanceInfo;
import org.familysearch.platform.users.User;
import org.gedcomx.common.ExtensibleData;
import org.gedcomx.rs.client.*;
import org.gedcomx.rs.client.util.HttpWarning;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.rt.json.GedcomxAtomJsonProvider;

import javax.ws.rs.HttpMethod;
import java.io.*;
import java.net.URI;
import java.util.List;

/**
 * @author Ryan Heaton
 */
public class FamilySearchStateFactory extends StateFactory {

  protected DiscussionsState newDiscussionsState(ClientRequest request, ClientResponse response, String accessToken) {
    return new DiscussionsState(request, response, accessToken, this);
  }

  protected DiscussionState newDiscussionState(ClientRequest request, ClientResponse response, String accessToken) {
    return new DiscussionState(request, response, accessToken, this);
  }

  protected UserState newUserState(ClientRequest request, ClientResponse response, String accessToken) {
    return new UserState(request, response, accessToken, this);
  }

  protected UserHistoryState newUserHistoryState(ClientRequest request, ClientResponse response, String accessToken) {
    return new UserHistoryState(request, response, accessToken, this);
  }

  protected PersonMergeState newPersonMergeState(ClientRequest request, ClientResponse response, String accessToken) {
    return new PersonMergeState(request, response, accessToken, this);
  }

  protected PersonMatchResultsState newPersonMatchResultsState(ClientRequest request, ClientResponse response, String accessToken) {
    return new PersonMatchResultsState(request, response, accessToken, this);
  }

  /**
   * Create a new places state with the given URI
   *
   * @param discoveryUri the discovery URI for places
   * @return a new places state created with with the given URI
   */
  public FamilySearchPlaces newPlacesState(URI discoveryUri) {
    return newPlacesState(discoveryUri, loadDefaultClient());
  }

  /**
   * Create a new places state with the given URI
   *
   * @param discoveryUri the discovery URI for places
   * @param client the client that will use the new places state
   * @return a new places state created with with the given URI
   */
  public FamilySearchPlaces newPlacesState(URI discoveryUri, Client client) {
    return newPlacesState(discoveryUri, client, HttpMethod.GET);
  }

  /**
   * Create a new places state with the given URI
   *
   * @param discoveryUri the discovery URI for places
   * @param client the client that will use the new places state
   * @param method the HTTP method to call
   * @return a new places state created with with the given URI
   */
  public FamilySearchPlaces newPlacesState(URI discoveryUri, Client client, String method) {
    ClientRequest request = ClientRequest.create().accept(GedcomxConstants.GEDCOMX_JSON_MEDIA_TYPE).build(discoveryUri, method);
    return newPlacesState(request, client.handle(request), null);
  }

  protected FamilySearchPlaces newPlacesState(ClientRequest request, ClientResponse response, String accessToken) {
    return new FamilySearchPlaces(request, response, accessToken, this);
  }

  @Override
  protected FamilySearchCollectionState newCollectionState(ClientRequest request, ClientResponse response, String accessToken) {
    return new FamilySearchCollectionState(request, response, accessToken, this);
  }

  @Override
  protected FamilySearchSourceDescriptionState newSourceDescriptionState(ClientRequest request, ClientResponse response, String accessToken) {
    return new FamilySearchSourceDescriptionState(request, response, accessToken, this);
  }

  @Override
  protected PersonState newPersonState(ClientRequest request, ClientResponse response, String accessToken) {
    return super.newPersonState(request, response, accessToken);
  }

  protected PersonNonMatchesState newPersonNonMatchesState(ClientRequest request, ClientResponse response, String accessToken) {
    return new PersonNonMatchesState(request, response, accessToken, this);
  }

  protected FamilySearchPlaceState newPlaceState(ClientRequest request, ClientResponse response, String accessToken) {
    return new FamilySearchPlaceState(request, response, accessToken, this);
  }

  @Override
  protected PlaceDescriptionState newPlaceDescriptionState(ClientRequest request, ClientResponse response, String accessToken) {
    return new FamilySearchPlaceDescriptionState(request, response, accessToken, this);
  }

  @Override
  protected RecordsState newRecordsState(ClientRequest request, ClientResponse response, String accessToken) {
    return super.newRecordsState(request, response, accessToken);
  }

  @Override
  public Client loadDefaultClient() {
    DefaultClientConfig config = new DefaultClientConfig();
    Class<?>[] extensionClasses = new Class[]{ FamilySearchPlatform.class, ArtifactMetadata.class, ChangeInfo.class,
      ChildAndParentsRelationship.class, Discussion.class, DiscussionReference.class,
      Error.class, MatchInfo.class, SearchInfo.class, Merge.class, MergeAnalysis.class, MergeConflict.class,
      Tag.class, User.class, Ordinance.class, OrdinanceInfo.class };
    config.getSingletons().add( new FamilySearchPlatformJsonProvider(extensionClasses) );
    config.getSingletons().add( new GedcomxAtomJsonProvider(extensionClasses) );
    config.getSingletons().add( new JacksonJsonProvider() );
    Client client = new Client(new URLConnectionClientHandler(), config);
    //how to add an experiment:
    //client.addFilter(new ExperimentsFilter("experiment-name", "experiment-name"));
    if (Boolean.valueOf(System.getProperty(ENABLE_JERSEY_LOGGING_ENV_NAME))) {     // handles null
      client.addFilter(new com.sun.jersey.api.client.filter.LoggingFilter());
    }
    return client;
  }

  @Override
  protected String buildErrorMessage(GedcomxApplicationState state) {
    StringBuilder builder = new StringBuilder("Unsuccessful ").append(state.getRequest().getMethod()).append(" to ").append(state.getUri()).append(" (").append(state.getResponse().getStatus()).append(")");
    if (state.getEntity() instanceof ExtensibleData) {
      org.familysearch.platform.Error errorInfo = ((ExtensibleData) state.getEntity()).findExtensionOfType(Error.class);
      if (errorInfo != null) {
        if (errorInfo.getMessage() != null) {
          builder.append(": ").append(errorInfo.getMessage());
        }

        if (errorInfo.getStacktrace() != null) {
          BufferedReader reader = new BufferedReader(new StringReader(errorInfo.getStacktrace()));
          try {
            String line = reader.readLine();
            while (line != null) {
              builder.append("\n    ").append(line);
              line = reader.readLine();
            }
          }
          catch (IOException e) {
            throw new IllegalStateException(e);
          }
        }
      }
    }

    List<HttpWarning> warnings = state.getWarnings();
    if (warnings != null && warnings.size() > 0) {
      for (HttpWarning warning : warnings) {
        builder.append("\nWarning: ").append(warning.getMessage());
      }
    }
    return builder.toString();
  }
}
