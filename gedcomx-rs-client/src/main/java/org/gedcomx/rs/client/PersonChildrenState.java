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

import javax.ws.rs.HttpMethod;
import java.net.URI;

/**
 * @author Ryan Heaton
 */
public class PersonChildrenState extends GedcomxApplicationState<Gedcomx> {

  public PersonChildrenState(URI discoveryUri) {
    this(discoveryUri, loadDefaultClient());
  }

  public PersonChildrenState(URI discoveryUri, Client client) {
    this(ClientRequest.create().accept(GedcomxConstants.GEDCOMX_JSON_MEDIA_TYPE).build(discoveryUri, HttpMethod.GET), client, null);
  }

  public PersonChildrenState(ClientRequest request, Client client, String accessToken) {
    super(request, client, accessToken);
  }

  @Override
  protected PersonChildrenState newApplicationState(ClientRequest request, Client client, String accessToken) {
    return new PersonChildrenState(request, client, accessToken);
  }

  @Override
  public PersonChildrenState ifSuccessful() {
    return (PersonChildrenState) super.ifSuccessful();
  }

  @Override
  public PersonChildrenState head() {
    return (PersonChildrenState) super.head();
  }

  @Override
  public PersonChildrenState get() {
    return (PersonChildrenState) super.get();
  }

  @Override
  public PersonChildrenState delete() {
    return (PersonChildrenState) super.delete();
  }

  @Override
  public PersonChildrenState put(Gedcomx e) {
    return (PersonChildrenState) super.put(e);
  }

  @Override
  protected Gedcomx loadEntity(ClientResponse response) {
    return response.getClientResponseStatus() == ClientResponse.Status.OK ? response.getEntity(Gedcomx.class) : null;
  }

  @Override
  protected SupportsLinks getScope() {
    return getEntity();
  }

}
