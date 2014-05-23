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
import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import org.familysearch.api.client.ft.FamilyTreeStateFactory;
import org.familysearch.api.client.util.RequestUtil;
import org.familysearch.platform.FamilySearchPlatform;
import org.familysearch.platform.discussions.Discussion;
import org.gedcomx.Gedcomx;
import org.gedcomx.links.Link;
import org.gedcomx.rs.client.CollectionState;
import org.gedcomx.rs.client.GedcomxApplicationException;
import org.gedcomx.rs.client.StateTransitionOption;
import org.gedcomx.rs.client.util.GedcomxSearchQueryBuilder;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MultivaluedMap;
import java.net.URI;

/**
 * @author Ryan Heaton
 */
public class FamilySearchCollectionState extends CollectionState {

  protected FamilySearchCollectionState(ClientRequest request, ClientResponse client, String accessToken, FamilySearchStateFactory stateFactory) {
    super(request, client, accessToken, stateFactory);
  }

  @Override
  protected FamilySearchCollectionState clone(ClientRequest request, ClientResponse response) {
    return new FamilySearchCollectionState(request, response, this.accessToken, (FamilySearchStateFactory) this.stateFactory);
  }

  @Override
  public FamilySearchCollectionState ifSuccessful() {
    return (FamilySearchCollectionState) super.ifSuccessful();
  }

  @Override
  public FamilySearchCollectionState head(StateTransitionOption... options) {
    return (FamilySearchCollectionState) super.head(options);
  }

  @Override
  public FamilySearchCollectionState get(StateTransitionOption... options) {
    return (FamilySearchCollectionState) super.get(options);
  }

  @Override
  public FamilySearchCollectionState delete(StateTransitionOption... options) {
    return (FamilySearchCollectionState) super.delete(options);
  }

  @Override
  public FamilySearchCollectionState put(Gedcomx e, StateTransitionOption... options) {
    return (FamilySearchCollectionState) super.put(e, options);
  }

  @Override
  public FamilySearchCollectionState post(Gedcomx entity, StateTransitionOption... options) {
    return (FamilySearchCollectionState) super.post(entity, options);
  }

  @Override
  public FamilySearchCollectionState authenticateViaOAuth2Password(String username, String password, String clientId) {
    return (FamilySearchCollectionState) super.authenticateViaOAuth2Password(username, password, clientId);
  }

  @Override
  public FamilySearchCollectionState authenticateViaOAuth2Password(String username, String password, String clientId, String clientSecret) {
    return (FamilySearchCollectionState) super.authenticateViaOAuth2Password(username, password, clientId, clientSecret);
  }

  @Override
  public FamilySearchCollectionState authenticateViaOAuth2AuthCode(String authCode, String redirect, String clientId, String clientSecret) {
    return (FamilySearchCollectionState) super.authenticateViaOAuth2AuthCode(authCode, redirect, clientId, clientSecret);
  }

  @Override
  public FamilySearchCollectionState authenticateViaOAuth2AuthCode(String authCode, String redirect, String clientId) {
    return (FamilySearchCollectionState) super.authenticateViaOAuth2AuthCode(authCode, redirect, clientId);
  }

  @Override
  public FamilySearchCollectionState authenticateViaOAuth2ClientCredentials(String clientId, String clientSecret) {
    return (FamilySearchCollectionState) super.authenticateViaOAuth2ClientCredentials(clientId, clientSecret);
  }

  @Override
  public FamilySearchCollectionState authenticateViaOAuth2(MultivaluedMap<String, String> formData, StateTransitionOption... options) {
    return (FamilySearchCollectionState) super.authenticateViaOAuth2(formData, options);
  }

  public UserState readCurrentUser(StateTransitionOption... options) {
    Link link = getLink(Rel.CURRENT_USER);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest()).build(link.getHref().toURI(), HttpMethod.GET);
    return ((FamilyTreeStateFactory)this.stateFactory).newUserState(request, invoke(request, options), this.accessToken);
  }

  public PersonMatchResultsState searchForPersonMatches(GedcomxSearchQueryBuilder query, StateTransitionOption... options) {
    return searchForPersonMatches(query.build(), options);
  }

  public PersonMatchResultsState searchForPersonMatches(String query, StateTransitionOption... options) {
    Link searchLink = getLink(Rel.PERSON_MATCHES_QUERY);
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
    return ((FamilySearchStateFactory)this.stateFactory).newPersonMatchResultsState(request, invoke(request, options), this.accessToken);
  }

  public DiscussionsState readDiscussions(StateTransitionOption... options) {
    Link link = getLink(Rel.DISCUSSIONS);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest()).build(link.getHref().toURI(), HttpMethod.GET);
    return ((FamilyTreeStateFactory)this.stateFactory).newDiscussionsState(request, invoke(request, options), this.accessToken);
  }

  public DiscussionState addDiscussion(Discussion discussion, StateTransitionOption... options) {
    Link link = getLink(Rel.DISCUSSIONS);
    if (link == null || link.getHref() == null) {
      throw new GedcomxApplicationException("Unable to add discussion: missing link.");
    }

    FamilySearchPlatform entity = new FamilySearchPlatform();
    entity.addDiscussion(discussion);
    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest()).entity(entity).build(link.getHref().toURI(), HttpMethod.POST);
    return ((FamilyTreeStateFactory)this.stateFactory).newDiscussionState(request, invoke(request, options), this.accessToken);
  }

}
