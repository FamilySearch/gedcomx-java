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
import org.gedcomx.Gedcomx;
import org.gedcomx.links.SupportsLinks;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.source.SourceDescription;

import javax.ws.rs.HttpMethod;
import java.net.URI;
import java.util.Arrays;

/**
 * @author Ryan Heaton
 */
public class SourceDescriptionState extends GedcomxApplicationState<Gedcomx> {

  public SourceDescriptionState(URI discoveryUri) {
    this(discoveryUri, loadDefaultClient());
  }

  public SourceDescriptionState(URI discoveryUri, Client client) {
    this(ClientRequest.create().accept(GedcomxConstants.GEDCOMX_JSON_MEDIA_TYPE).build(discoveryUri, HttpMethod.GET), client, null);
  }

  public SourceDescriptionState(ClientRequest request, Client client, String accessToken) {
    super(request, client, accessToken);
  }

  @Override
  protected SourceDescriptionState newApplicationState(ClientRequest request, Client client, String accessToken) {
    return new SourceDescriptionState(request, client, accessToken);
  }

  @Override
  public SourceDescriptionState ifSuccessful() {
    return (SourceDescriptionState) super.ifSuccessful();
  }

  @Override
  public SourceDescriptionState head() {
    return (SourceDescriptionState) super.head();
  }

  @Override
  public SourceDescriptionState get() {
    return (SourceDescriptionState) super.get();
  }

  @Override
  public SourceDescriptionState delete() {
    return (SourceDescriptionState) super.delete();
  }

  @Override
  public SourceDescriptionState put(Gedcomx e) {
    return (SourceDescriptionState) super.put(e);
  }

  @Override
  protected Gedcomx loadEntity(ClientResponse response) {
    return response.getClientResponseStatus() == ClientResponse.Status.OK ? response.getEntity(Gedcomx.class) : null;
  }

  @Override
  protected SupportsLinks getScope() {
    return getSourceDescription();
  }

  public SourceDescription getSourceDescription() {
    return getEntity() == null ? null : getEntity().getSourceDescriptions() == null ? null : getEntity().getSourceDescriptions().isEmpty() ? null : getEntity().getSourceDescriptions().get(0);
  }

  public SourceDescriptionState update(SourceDescription description) {
    Gedcomx entity = new Gedcomx();
    entity.setSourceDescriptions(Arrays.asList(description));
    return new SourceDescriptionState(createAuthenticatedGedcomxRequest().entity(entity).build(getSelfUri(), HttpMethod.POST), this.client, this.accessToken);
  }

}
