/**
 * Copyright 2011-2012 Intellectual Reserve, Inc.
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
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;
import org.gedcomx.Gedcomx;
import org.gedcomx.conclusion.Person;
import org.gedcomx.conclusion.Relationship;
import org.gedcomx.links.Link;
import org.gedcomx.rs.Rel;
import org.gedcomx.rt.GedcomxConstants;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Ryan Heaton
 */
public class BasicGedcomxClient implements GedcomxApi {

  protected final GedcomxApiDescriptor descriptor;
  protected String currentAccessToken;
  private final Set<String> embeddedRels;

  public BasicGedcomxClient(String discoveryUri) {
    this(new GedcomxApiDescriptor(discoveryUri));
  }

  public BasicGedcomxClient(String discoveryUri, Client client) {
    this(new GedcomxApiDescriptor(discoveryUri, client));
  }

  public BasicGedcomxClient(GedcomxApiDescriptor descriptor) {
    this.descriptor = descriptor;
    this.embeddedRels = loadEmbeddedRels();
  }

  protected TreeSet<String> loadEmbeddedRels() {
    return new TreeSet<String>(Arrays.asList(Rel.CHILD_RELATIONSHIPS, Rel.CONCLUSIONS, Rel.NOTES, Rel.PARENT_RELATIONSHIPS, Rel.SOURCE_REFERENCES, Rel.SPOUSE_RELATIONSHIPS));
  }

  public Client getClient() {
    return this.descriptor.getClient(); //we'll just use the descriptors client as ours.
  }

  public GedcomxApiDescriptor getDescriptor() {
    return descriptor;
  }

  public String getCurrentAccessToken() {
    return currentAccessToken;
  }

  public boolean isAuthenticated() {
    return this.currentAccessToken != null;
  }

  @Override
  public URI buildOAuth2AuthenticationUri(String clientId, String redirectUri) {
    String authenticationUri = this.descriptor.getOAuth2AuthenticationUri();
    if (authenticationUri == null) {
      throw new GedcomxApiException(String.format("No OAuth2 authentication URI supplied for API at %s.", this.descriptor.getDiscoveryUri()));
    }
    return UriBuilder.fromUri(authenticationUri).queryParam("response_type", "code").queryParam("client_id", clientId).queryParam("redirect_uri", redirectUri).build();
  }

  public BasicGedcomxClient authenticateViaOAuth2Password(String username, String password, String clientId) {
    return authenticateViaOAuth2Password(username, password, clientId, null);
  }

  public BasicGedcomxClient authenticateViaOAuth2Password(String username, String password, String clientId, String clientSecret) {
    MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
    formData.putSingle("grant_type", "password");
    formData.putSingle("username", username);
    formData.putSingle("password", password);
    formData.putSingle("client_id", clientId);
    if (clientSecret != null) {
      formData.putSingle("client_secret", clientSecret);
    }
    return authenticateViaOAuth2(formData);
  }

  public BasicGedcomxClient authenticateViaOAuth2AuthCode(String authCode, String redirect, String clientId) {
    return authenticateViaOAuth2Password(authCode, authCode, clientId, null);
  }

  public BasicGedcomxClient authenticateViaOAuth2AuthCode(String authCode, String redirect, String clientId, String clientSecret) {
    MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
    formData.putSingle("grant_type", "authorization_code");
    formData.putSingle("code", authCode);
    formData.putSingle("redirect_uri", redirect);
    formData.putSingle("client_id", clientId);
    if (clientSecret != null) {
      formData.putSingle("client_secret", clientSecret);
    }
    return authenticateViaOAuth2(formData);
  }

  public BasicGedcomxClient authenticateViaOAuth2ClientCredentials(String clientId, String clientSecret) {
    MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
    formData.putSingle("grant_type", "client_credentials");
    formData.putSingle("client_id", clientId);
    if (clientSecret != null) {
      formData.putSingle("client_secret", clientSecret);
    }
    return authenticateViaOAuth2(formData);
  }

  public BasicGedcomxClient authenticateViaOAuth2(MultivaluedMap<String, String> formData) {
    String tokenUri = this.descriptor.getOAuth2TokenUri();
    if (tokenUri == null) {
      throw new GedcomxApiException(String.format("No OAuth2 token URI supplied for API at %s.", this.descriptor.getDiscoveryUri()));
    }

    ClientResponse response = getClient()
      .resource(tokenUri)
      .accept(MediaType.APPLICATION_JSON_TYPE)
      .type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
      .post(ClientResponse.class, formData);
    if (response.getClientResponseStatus().getFamily() == Response.Status.Family.SUCCESSFUL) {
      ObjectNode accessToken = response.getEntity(ObjectNode.class);
      JsonNode access_token = accessToken.get("access_token");
      if (access_token == null) {
        throw new GedcomxApiException("Illegal access token response: no access_token provided.", response);
      }
      this.currentAccessToken = access_token.getValueAsText();
    }
    else {
      throw new GedcomxApiException("Unable to obtain an access token.", response);
    }
    return this;
  }

  public ApplicationState<Gedcomx> getPersonForCurrentUser() {
    return getPersonForCurrentUser(true);
  }

  public ApplicationState<Gedcomx> getPersonForCurrentUser(boolean includeEmbedded) {
    String currentUserPersonUri = this.descriptor.getCurrentUserPersonUri();
    if (currentUserPersonUri == null) {
      throw new GedcomxApiException(String.format("No current user person URI supplied for API at %s.", this.descriptor.getDiscoveryUri()));
    }

    ClientResponse response = authenticatedRequest(currentUserPersonUri)
      .accept(GedcomxConstants.GEDCOMX_JSON_MEDIA_TYPE)
      .get(ClientResponse.class);

    Gedcomx entity = null;
    ClientResponse.Status responseCode = response.getClientResponseStatus();
    switch (responseCode) {
      case OK:
        entity = response.getEntity(Gedcomx.class);
        break;
      default:
        switch (responseCode.getFamily()) {
          case CLIENT_ERROR:
          case SERVER_ERROR:
            throw new GedcomxApiException(responseCode.getReasonPhrase(), response);
        }
    }

    if (entity != null && includeEmbedded) {
      includeEmbeddedResources(entity);
    }

    return new ApplicationState<Gedcomx>(response, entity);
  }

  protected void includeEmbeddedResources(Gedcomx entity) {
    List<Person> persons = entity.getPersons();
    if (persons != null) {
      for (Person person : persons) {
        for (String embeddedRel : this.embeddedRels) {
          Link link = person.getLink(embeddedRel);
          if (link != null) {
            embed(link.getHref().toString(), entity);
          }
        }
      }
    }

    List<Relationship> relationships = entity.getRelationships();
    if (relationships != null) {
      for (Relationship relationship : relationships) {
        for (String embeddedRel : this.embeddedRels) {
          Link link = relationship.getLink(embeddedRel);
          if (link != null) {
            embed(link.getHref().toString(), entity);
          }
        }
      }
    }
  }

  private WebResource.Builder authenticatedRequest(String uri) {
    return getClient()
      .resource(uri)
      .header("Authorization", "Bearer");
  }

  protected void embed(String href, Gedcomx entity) {
    ClientResponse response = authenticatedRequest(href).get(ClientResponse.class);
    if (response.getClientResponseStatus() == ClientResponse.Status.OK) {
      embed(response.getEntity(Gedcomx.class), entity);
    }
    else {
      //todo: log a warning? throw an error?
    }
  }

  protected void embed(Gedcomx embedded, Gedcomx entity) {
    List<Person> persons = embedded.getPersons();
    if (persons != null && entity.getPersons() != null) {
      for (Person person : persons) {
        if (person.getId() != null) {
          for (Person target : entity.getPersons()) {
            if (person.getId().equals(target.getId())) {
              target.embed(person);
            }
          }
        }
      }
    }
    List<Relationship> relationships = embedded.getRelationships();
    if (relationships != null && entity.getRelationships() != null) {
      for (Relationship relationship : relationships) {
        if (relationship.getId() != null) {
          for (Relationship target : entity.getRelationships()) {
            if (relationship.getId().equals(target.getId())) {
              target.embed(relationship);
            }
          }
        }
      }
    }
  }

}
