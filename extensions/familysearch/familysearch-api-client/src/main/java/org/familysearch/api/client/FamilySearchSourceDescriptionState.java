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
import org.gedcomx.Gedcomx;
import org.gedcomx.links.Link;
import org.gedcomx.rs.client.SourceDescriptionState;
import org.gedcomx.source.SourceDescription;

import javax.ws.rs.HttpMethod;

/**
 * @author Ryan Heaton
 */
public class FamilySearchSourceDescriptionState extends SourceDescriptionState {

  protected FamilySearchSourceDescriptionState(ClientRequest request, ClientResponse client, String accessToken, FamilySearchStateFactory stateFactory) {
    super(request, client, accessToken, stateFactory);
  }

  @Override
  protected FamilySearchSourceDescriptionState clone(ClientRequest request, ClientResponse response) {
    return new FamilySearchSourceDescriptionState(request, response, this.accessToken, (FamilySearchStateFactory) this.stateFactory);
  }

  @Override
  public FamilySearchSourceDescriptionState ifSuccessful() {
    return (FamilySearchSourceDescriptionState) super.ifSuccessful();
  }

  @Override
  public FamilySearchSourceDescriptionState head() {
    return (FamilySearchSourceDescriptionState) super.head();
  }

  @Override
  public FamilySearchSourceDescriptionState options() {
    return (FamilySearchSourceDescriptionState) super.options();
  }

  @Override
  public FamilySearchSourceDescriptionState get() {
    return (FamilySearchSourceDescriptionState) super.get();
  }

  @Override
  public FamilySearchSourceDescriptionState delete() {
    return (FamilySearchSourceDescriptionState) super.delete();
  }

  @Override
  public FamilySearchSourceDescriptionState put(Gedcomx e) {
    return (FamilySearchSourceDescriptionState) super.put(e);
  }

  @Override
  public FamilySearchSourceDescriptionState update(SourceDescription description) {
    return (FamilySearchSourceDescriptionState) super.update(description);
  }

  public CommentsState readComments() {
    Link link = getLink(Rel.COMMENTS);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest()).build(link.getHref().toURI(), HttpMethod.GET);
    return ((FamilySearchStateFactory)this.stateFactory).newCommentsState(request, invoke(request), this.accessToken);
  }
}
