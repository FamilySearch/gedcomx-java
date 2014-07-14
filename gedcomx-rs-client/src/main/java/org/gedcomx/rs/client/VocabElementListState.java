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

import com.github.jsonldjava.utils.JsonUtils;
import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import org.gedcomx.links.SupportsLinks;
import org.gedcomx.rs.Rel;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class VocabElementListState extends GedcomxApplicationState<Object> {

  protected VocabElementListState(ClientRequest request, ClientResponse response, String accessToken, StateFactory stateFactory) {
    super(request, response, accessToken, stateFactory);
  }

  @Override
  public String getSelfRel() {
    return Rel.DESCRIPTION;
  }

  @Override
  protected VocabElementState clone(ClientRequest request, ClientResponse response) {
    return new VocabElementState(request, response, this.accessToken, this.stateFactory);
  }

  @Override
  public VocabElementState ifSuccessful() {
    return (VocabElementState) super.ifSuccessful();
  }

  @Override
  public VocabElementState head(StateTransitionOption... options) {
    return (VocabElementState) super.head(options);
  }

  @Override
  public VocabElementState options(StateTransitionOption... options) {
    return (VocabElementState) super.options(options);
  }

  @Override
  public VocabElementState get(StateTransitionOption... options) {
    return (VocabElementState) super.get(options);
  }

  @Override
  public VocabElementState delete(StateTransitionOption... options) {
    return (VocabElementState) super.delete(options);
  }

  @Override
  public VocabElementState put(Object e, StateTransitionOption... options) {
    return (VocabElementState) super.put(e, options);
  }

  @Override
  public VocabElementState post(Object entity, StateTransitionOption... options) {
    return (VocabElementState) super.post(entity, options);
  }

  public String getTitle() {
    LinkedHashMap<String, String> map = (LinkedHashMap<String, String>)entity;
    return map.get("title");
  }

  public String getDescription() {
    LinkedHashMap<String, String> map = (LinkedHashMap<String, String>)entity;
    return map.get("description");
  }

  public String getId() {
    LinkedHashMap<String, String> map = (LinkedHashMap<String, String>)entity;
    return map.get("@id");
  }

  public List<Map> getElements() {
    LinkedHashMap<String, ArrayList> map = (LinkedHashMap<String, ArrayList>)entity;
    return map.get("elements");
  }

  public Map<String, Map> getContext() {
    LinkedHashMap<String, Map> map = (LinkedHashMap<String, Map>)entity;
    return map.get("@context");
  }

  @Override
  protected Object loadEntity(ClientResponse response) {
    Object entity;
    try {
      entity = JsonUtils.fromInputStream(response.getEntity(InputStream.class));
    }
    catch (IOException ioe) {
      throw new GedcomxApplicationException(ioe);
    }
    return entity;
  }

  @Override
  protected SupportsLinks getMainDataElement() {
//    return getPlaceDescription();
    return null;
  }

//  public PlaceDescription getPlaceDescription() {
//    return getEntity() == null ? null : getEntity().getPlaces() == null ? null : getEntity().getPlaces().isEmpty() ? null : getEntity().getPlaces().get(0);
//  }
//
//  public PlaceDescriptionsState readChildren(StateTransitionOption... options) {
//    Link link = getLink(Rel.CHILDREN);
//    if (link == null || link.getHref() == null) {
//      return null;
//    }
//
//    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
//    return this.stateFactory.newPlaceDescriptionsState(request, invoke(request, options), this.accessToken);
//  }
//
}
