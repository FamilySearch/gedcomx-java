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

import java.util.List;
import javax.ws.rs.HttpMethod;

import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import org.gedcomx.Gedcomx;
import org.gedcomx.links.Link;
import org.gedcomx.links.SupportsLinks;
import org.gedcomx.rs.Rel;
import org.gedcomx.source.SourceDescription;

/**
 */
public class ImageRecordsState extends GedcomxApplicationState<Gedcomx> {

  protected ImageRecordsState(ClientRequest request, ClientResponse response, String accessToken, StateFactory stateFactory) {
    super(request, response, accessToken, stateFactory);
  }

  @Override
  public String getSelfRel() {
    return Rel.DESCRIPTION;
  }

  @Override
  protected ImageRecordsState clone(ClientRequest request, ClientResponse response) {
    return new ImageRecordsState(request, response, this.accessToken, this.stateFactory);
  }

  @Override
  public ImageRecordsState ifSuccessful() {
    return (ImageRecordsState) super.ifSuccessful();
  }

  @Override
  public ImageRecordsState head(StateTransitionOption... options) {
    return (ImageRecordsState) super.head(options);
  }

//  @Override
//  public ImageRecordsState options(StateTransitionOption... options) {
//    return (ImageRecordsState) super.options(options);
//  }

  @Override
  public ImageRecordsState get(StateTransitionOption... options) {
    return (ImageRecordsState) super.get(options);
  }

//  @Override
//  public ImageRecordsState delete(StateTransitionOption... options) {
//    return (ImageRecordsState) super.delete(options);
//  }
//
//  @Override
//  public ImageRecordsState put(Gedcomx e, StateTransitionOption... options) {
//    return (ImageRecordsState) super.put(e, options);
//  }
//
//  @Override
//  public ImageRecordsState post(Gedcomx entity, StateTransitionOption... options) {
//    return (ImageRecordsState) super.post(entity, options);
//  }

  @Override
  protected Gedcomx loadEntity(ClientResponse response) {
    return response.getEntity(Gedcomx.class);
  }

  @Override
  protected SupportsLinks getMainDataElement() {
    return getEntity();
  }

  public List<SourceDescription> getRecords() {
    return getEntity().getSourceDescriptions();
  }

}
