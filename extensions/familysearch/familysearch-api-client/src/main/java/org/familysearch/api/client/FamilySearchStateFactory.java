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
import org.familysearch.api.client.PersonNonMatchesState;
import org.familysearch.api.client.rt.FamilySearchPlatformJsonProvider;
import org.familysearch.api.client.util.ExperimentsFilter;
import org.familysearch.platform.FamilySearchPlatform;
import org.familysearch.platform.HealthConfig;
import org.familysearch.platform.Tag;
import org.familysearch.platform.artifacts.ArtifactMetadata;
import org.familysearch.platform.ct.*;
import org.familysearch.platform.discussions.Discussion;
import org.familysearch.platform.users.User;
import org.gedcomx.rs.client.PersonState;
import org.gedcomx.rs.client.SourceDescriptionState;
import org.gedcomx.rs.client.StateFactory;
import org.gedcomx.rt.json.GedcomxAtomJsonProvider;

/**
 * @author Ryan Heaton
 */
public class FamilySearchStateFactory extends StateFactory {

  protected CommentsState newCommentsState(ClientRequest request, ClientResponse response, String accessToken) {
    return new CommentsState(request, response, accessToken, this);
  }

  protected DiscussionsState newDiscussionsState(ClientRequest request, ClientResponse response, String accessToken) {
    return new DiscussionsState(request, response, accessToken, this);
  }

  protected DiscussionState newDiscussionState(ClientRequest request, ClientResponse response, String accessToken) {
    return new DiscussionState(request, response, accessToken, this);
  }

  protected UserState newUserState(ClientRequest request, ClientResponse response, String accessToken) {
    return new UserState(request, response, accessToken, this);
  }

  protected PersonMergeState newPersonMergeState(ClientRequest request, ClientResponse response, String accessToken) {
    return new PersonMergeState(request, response, accessToken, this);
  }

  protected PersonMatchResultsState newPersonMatchResultsState(ClientRequest request, ClientResponse response, String accessToken) {
    return new PersonMatchResultsState(request, response, accessToken, this);
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

  @Override
  protected Client loadDefaultClient() {
    DefaultClientConfig config = new DefaultClientConfig();
    Class<?>[] extensionClasses = new Class[]{ FamilySearchPlatform.class, ArtifactMetadata.class, ChangeInfo.class,
      ChildAndParentsRelationship.class, Discussion.class, DiscussionReference.class,
      Error.class, HealthConfig.class, MatchInfo.class, Merge.class, MergeAnalysis.class, MergeConflict.class,
      Tag.class, User.class };
    config.getSingletons().add( new FamilySearchPlatformJsonProvider(extensionClasses) );
    config.getSingletons().add( new GedcomxAtomJsonProvider(extensionClasses) );
    config.getSingletons().add( new JacksonJsonProvider() );
    Client client = new Client(new URLConnectionClientHandler(), config);
    client.addFilter(new ExperimentsFilter("discussion-reference-json-fix", "parent-child-relationship-resources-consolidation", "current-user-person-401"));
    return client;
  }
}
