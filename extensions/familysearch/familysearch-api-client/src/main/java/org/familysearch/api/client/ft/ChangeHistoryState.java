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
import org.familysearch.api.client.util.ChangeEntry;
import org.familysearch.api.client.util.ChangeHistoryPage;
import org.gedcomx.atom.Entry;
import org.gedcomx.atom.Feed;
import org.gedcomx.links.SupportsLinks;
import org.gedcomx.rs.client.GedcomxApplicationState;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ryan Heaton
 */
public class ChangeHistoryState extends GedcomxApplicationState<Feed> {

  protected ChangeHistoryState(ClientRequest request, ClientResponse response, String accessToken, FamilyTreeStateFactory stateFactory) {
    super(request, response, accessToken, stateFactory);
  }

  @Override
  protected ChangeHistoryState clone(ClientRequest request, ClientResponse response) {
    return new ChangeHistoryState(request, response, this.accessToken, (FamilyTreeStateFactory) this.stateFactory);
  }

  @Override
  protected Feed loadEntity(ClientResponse response) {
    return response.getEntity(Feed.class);
  }

  @Override
  protected SupportsLinks getScope() {
    return getEntity();
  }

  @Override
  public ChangeHistoryState ifSuccessful() {
    return (ChangeHistoryState) super.ifSuccessful();
  }

  @Override
  public ChangeHistoryState head() {
    return (ChangeHistoryState) super.head();
  }

  @Override
  public ChangeHistoryState get() {
    return (ChangeHistoryState) super.get();
  }

  @Override
  public ChangeHistoryState delete() {
    return (ChangeHistoryState) super.delete();
  }

  @Override
  public ChangeHistoryState put(Feed e) {
    return (ChangeHistoryState) super.put(e);
  }

  @Override
  public ChangeHistoryState readNextPage() {
    return (ChangeHistoryState) super.readNextPage();
  }

  @Override
  public ChangeHistoryState readPreviousPage() {
    return (ChangeHistoryState) super.readPreviousPage();
  }

  @Override
  public ChangeHistoryState readFirstPage() {
    return (ChangeHistoryState) super.readFirstPage();
  }

  @Override
  public ChangeHistoryState readLastPage() {
    return (ChangeHistoryState) super.readLastPage();
  }

  public ChangeHistoryPage getPage() {
    Feed feed = getEntity();
    return feed == null ? null : new ChangeHistoryPage(feed);
  }

}
