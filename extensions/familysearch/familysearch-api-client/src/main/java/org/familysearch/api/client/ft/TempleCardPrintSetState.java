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
package org.familysearch.api.client.ft;

import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import org.gedcomx.links.SupportsLinks;
import org.gedcomx.rs.client.GedcomxApplicationState;
import org.gedcomx.rs.client.StateFactory;
import org.gedcomx.rs.client.StateTransitionOption;

import javax.ws.rs.HttpMethod;
import java.io.InputStream;

/**
 * Class representing a set of LDS ordinance temple cards to be printed.
 * User: Randy Wilson
 * Date: 23 February 2016
 */
public class TempleCardPrintSetState extends GedcomxApplicationState<InputStream> {

  protected TempleCardPrintSetState(ClientRequest request, ClientResponse response, String accessToken, StateFactory stateFactory) {
    super(request, response, accessToken, stateFactory);
  }

  @Override
  protected GedcomxApplicationState clone(ClientRequest request, ClientResponse response) {
    return new TempleCardPrintSetState(request, response, this.accessToken, this.stateFactory);
  }

  @Override
  protected InputStream loadEntity(ClientResponse response) {
    return response.getEntityInputStream();
  }

  @Override
  protected SupportsLinks getMainDataElement() {
    return null;
  }

  @Override
  public TempleCardPrintSetState ifSuccessful() {
    return (TempleCardPrintSetState) super.ifSuccessful();
  }

  @Override
  public TempleCardPrintSetState head(StateTransitionOption... options) {
    ClientRequest.Builder builder = createAuthenticatedRequest();
    builder = builder.accept("application/pdf");
    ClientRequest request = builder.build(getSelfUri(), HttpMethod.HEAD);
    ClientResponse response = invoke(request, options);
    return (TempleCardPrintSetState) clone(request, response);
  }

  @Override
  public TempleCardPrintSetState get(StateTransitionOption... options) {
    ClientRequest.Builder builder = createAuthenticatedRequest();
    builder = builder.accept("application/pdf");
    ClientRequest request = builder.build(getSelfUri(), HttpMethod.GET);
    ClientResponse response = invoke(request, options);
    return (TempleCardPrintSetState) clone(request, response);
  }
}
