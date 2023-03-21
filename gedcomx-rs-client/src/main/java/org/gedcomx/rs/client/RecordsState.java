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

import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import org.gedcomx.Gedcomx;
import org.gedcomx.links.Link;
import org.gedcomx.links.SupportsLinks;
import org.gedcomx.rt.Rel;
import org.gedcomx.source.SourceDescription;

import jakarta.ws.rs.HttpMethod;
import java.util.List;

/**
 * @author Ryan Heaton
 */
public class RecordsState extends GedcomxApplicationState<Gedcomx> {

  protected RecordsState(ClientRequest request, ClientResponse response, String accessToken, StateFactory stateFactory) {
    super(request, response, accessToken, stateFactory);
  }

  @Override
  public String getSelfRel() {
    return Rel.RECORDS;
  }

  @Override
  protected RecordsState clone(ClientRequest request, ClientResponse response) {
    return new RecordsState(request, response, this.accessToken, this.stateFactory);
  }

  @Override
  public RecordsState ifSuccessful() {
    return (RecordsState) super.ifSuccessful();
  }

  @Override
  public RecordsState head(StateTransitionOption... options) {
    return (RecordsState) super.head(options);
  }

  @Override
  public RecordsState options(StateTransitionOption... options) {
    return (RecordsState) super.options(options);
  }

  @Override
  public RecordsState get(StateTransitionOption... options) {
    return (RecordsState) super.get(options);
  }

  @Override
  public RecordsState delete(StateTransitionOption... options) {
    return (RecordsState) super.delete(options);
  }

  @Override
  public RecordsState put(Gedcomx e, StateTransitionOption... options) {
    return (RecordsState) super.put(e, options);
  }

  @Override
  public RecordsState post(Gedcomx entity, StateTransitionOption... options) {
    return (RecordsState) super.post(entity, options);
  }

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

  public RecordState readRecord(SourceDescription sourceDescription, StateTransitionOption... options) {
    Link link = sourceDescription.getLink(Rel.RECORD);
    if (link == null || link.getHref() == null) {
      return null;
    }
    
    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return this.stateFactory.newRecordState(request, invoke(request, options), this.accessToken);
  }
}
