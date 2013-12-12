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
import org.gedcomx.agent.Agent;
import org.gedcomx.common.Note;
import org.gedcomx.links.SupportsLinks;
import org.gedcomx.rt.GedcomxConstants;

import javax.ws.rs.HttpMethod;
import java.net.URI;

/**
 * @author Ryan Heaton
 */
public class NoteState extends GedcomxApplicationState<Gedcomx> {

  public NoteState(URI discoveryUri) {
    this(discoveryUri, loadDefaultClient());
  }

  public NoteState(URI discoveryUri, Client client) {
    this(ClientRequest.create().accept(GedcomxConstants.GEDCOMX_JSON_MEDIA_TYPE).build(discoveryUri, HttpMethod.GET), client, null);
  }

  public NoteState(ClientRequest request, Client client, String accessToken) {
    super(request, client, accessToken);
  }

  @Override
  protected NoteState newApplicationState(ClientRequest request, Client client, String accessToken) {
    return new NoteState(request, client, accessToken);
  }

  @Override
  protected Gedcomx loadEntity(ClientResponse response) {
    return response.getClientResponseStatus() == ClientResponse.Status.OK ? response.getEntity(Gedcomx.class) : null;
  }

  @Override
  public NoteState ifSuccessfull() {
    return (NoteState) super.ifSuccessfull();
  }

  @Override
  protected SupportsLinks getScope() {
    return getNote();
  }

  public Note getNote() {
    Gedcomx entity = getEntity();
    Note note = entity == null ? null : entity.getPersons() == null ? null : entity.getPersons().isEmpty() ? null : entity.getPersons().get(0).getNotes() == null ? null : entity.getPersons().get(0).getNotes().isEmpty() ? null : entity.getPersons().get(0).getNotes().get(0);
    note = note != null ? note : entity == null ? null : entity.getRelationships() == null ? null : entity.getRelationships().isEmpty() ? null : entity.getRelationships().get(0).getNotes() == null ? null : entity.getRelationships().get(0).getNotes().isEmpty() ? null : entity.getRelationships().get(0).getNotes().get(0);
    return note;
  }

}
