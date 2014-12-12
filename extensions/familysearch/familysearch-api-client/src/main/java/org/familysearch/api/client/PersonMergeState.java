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

import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import org.familysearch.api.client.util.RequestUtil;
import org.familysearch.platform.FamilySearchPlatform;
import org.familysearch.platform.ct.Merge;
import org.familysearch.platform.ct.MergeAnalysis;
import org.gedcomx.links.Link;
import org.gedcomx.links.SupportsLinks;
import org.gedcomx.rs.client.GedcomxApplicationState;
import org.gedcomx.rs.client.PersonState;
import org.gedcomx.rs.client.StateTransitionOption;

import javax.ws.rs.HttpMethod;

/**
 * @author Ryan Heaton
 */
public class PersonMergeState extends GedcomxApplicationState<FamilySearchPlatform> {

  protected PersonMergeState(ClientRequest request, ClientResponse response, String accessToken, FamilySearchStateFactory stateFactory) {
    super(request, response, accessToken, stateFactory);
  }

  @Override
  protected PersonMergeState clone(ClientRequest request, ClientResponse response) {
    return new PersonMergeState(request, response, this.accessToken, (FamilySearchStateFactory) this.stateFactory);
  }

  @Override
  public PersonMergeState ifSuccessful() {
    return (PersonMergeState) super.ifSuccessful();
  }

  @Override
  public PersonMergeState options(StateTransitionOption... options) {
    return (PersonMergeState) super.options(options);
  }

  @Override
  public PersonMergeState head(StateTransitionOption... options) {
    return (PersonMergeState) super.head(options);
  }

  @Override
  public PersonMergeState get(StateTransitionOption... options) {
    return (PersonMergeState) super.get(options);
  }

  @Override
  public PersonMergeState delete(StateTransitionOption... options) {
    return (PersonMergeState) super.delete(options);
  }

  @Override
  public PersonMergeState put(FamilySearchPlatform e, StateTransitionOption... options) {
    return (PersonMergeState) super.put(e, options);
  }

  @Override
  public PersonMergeState post(FamilySearchPlatform entity, StateTransitionOption... options) {
    return (PersonMergeState) super.post(entity, options);
  }

  @Override
  protected FamilySearchPlatform loadEntity(ClientResponse response) {
    return response.getClientResponseStatus() == ClientResponse.Status.OK ? response.getEntity(FamilySearchPlatform.class) : null;
  }

  @Override
  protected SupportsLinks getMainDataElement() {
    return getEntity();
  }

  public MergeAnalysis getAnalysis() {
    return getEntity() == null ? null : getEntity().getMergeAnalyses() == null ? null : getEntity().getMergeAnalyses().isEmpty() ? null : getEntity().getMergeAnalyses().get(0);
  }

  public boolean isAllowed() { //If there is no 'Allow' header, it is allowed
    return this.response.getHeaders().getFirst("Allow") == null || String.valueOf(this.response.getHeaders().getFirst("Allow")).toUpperCase().contains(HttpMethod.POST);
  }

  public PersonMergeState readMergeMirror(StateTransitionOption... options) {
    Link link = getLink(Rel.MERGE_MIRROR);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest()).build(link.getHref().toURI(), HttpMethod.GET);
    return ((FamilySearchStateFactory)this.stateFactory).newPersonMergeState(request, invoke(request, options), this.accessToken);
  }

  public PersonState readSurvivor(StateTransitionOption... options) {
    Link link = getLink(Rel.PERSON);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest()).build(link.getHref().toURI(), HttpMethod.GET);
    return ((FamilySearchStateFactory)this.stateFactory).newPersonState(request, invoke(request, options), this.accessToken);
  }

  public PersonMergeState doMerge(Merge merge, StateTransitionOption... options) {
    FamilySearchPlatform entity = new FamilySearchPlatform();
    entity.addMerge(merge);
    return doMerge(entity, options);
  }

  public PersonMergeState doMerge(FamilySearchPlatform entity, StateTransitionOption... options) {
    return post(entity, options);
  }

}
