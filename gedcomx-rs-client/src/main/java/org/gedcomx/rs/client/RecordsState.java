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
import org.gedcomx.atom.Entry;
import org.gedcomx.atom.Feed;
import org.gedcomx.links.Link;
import org.gedcomx.links.SupportsLinks;
import org.gedcomx.rs.Rel;

import javax.ws.rs.HttpMethod;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ryan Heaton
 */
public class RecordsState extends GedcomxApplicationState<Feed> {

  protected RecordsState(ClientRequest request, ClientResponse response, String accessToken, StateFactory stateFactory) {
    super(request, response, accessToken, stateFactory);
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
  public RecordsState head() {
    return (RecordsState) super.head();
  }

  @Override
  public RecordsState options() {
    return (RecordsState) super.options();
  }

  @Override
  public RecordsState get() {
    return (RecordsState) super.get();
  }

  @Override
  public RecordsState delete() {
    return (RecordsState) super.delete();
  }

  @Override
  public RecordsState put(Feed e) {
    return (RecordsState) super.put(e);
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
  public RecordsState readNextPage() {
    return (RecordsState) super.readNextPage();
  }

  @Override
  public RecordsState readPreviousPage() {
    return (RecordsState) super.readPreviousPage();
  }

  @Override
  public RecordsState readFirstPage() {
    return (RecordsState) super.readFirstPage();
  }

  @Override
  public RecordsState readLastPage() {
    return (RecordsState) super.readLastPage();
  }

  public List<Gedcomx> getRecords() {
    ArrayList<Gedcomx> records = null;

    Feed feed = getEntity();
    if (feed != null && feed.getEntries() != null && feed.getEntries().size() > 0) {
      records = new ArrayList<Gedcomx>();
      for (Entry entry : feed.getEntries()) {
        if (entry.getContent() != null && entry.getContent().getGedcomx() != null) {
          records.add(entry.getContent().getGedcomx());
        }
      }
    }

    return records;
  }

  public RecordState readRecord(Entry entry) {
    Link link = entry.getLink(Rel.RECORD);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return this.stateFactory.newRecordState(request, invoke(request), this.accessToken);
  }

  public RecordState readRecord(Gedcomx record) {
    Link link = record.getLink(Rel.RECORD);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(link.getHref().toURI(), HttpMethod.GET);
    return this.stateFactory.newRecordState(request, invoke(request), this.accessToken);
  }
}
