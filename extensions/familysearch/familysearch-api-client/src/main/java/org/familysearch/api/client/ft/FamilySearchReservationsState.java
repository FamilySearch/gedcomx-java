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
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.familysearch.api.client.FamilySearchCollectionState;
import org.familysearch.api.client.FamilySearchReferenceEnvironment;
import org.familysearch.api.client.Rel;
import org.familysearch.api.client.util.RequestUtil;
import org.familysearch.platform.FamilySearchPlatform;
import org.familysearch.platform.artifacts.ArtifactMetadata;
import org.familysearch.platform.artifacts.ArtifactType;
import org.familysearch.platform.reservations.Reservation;
import org.gedcomx.Gedcomx;
import org.gedcomx.links.Link;
import org.gedcomx.rs.client.SourceDescriptionState;
import org.gedcomx.rs.client.StateTransitionOption;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.source.SourceDescription;

import javax.activation.DataSource;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MultivaluedMap;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.familysearch.api.client.util.FamilySearchOptions.artifactType;

public class FamilySearchReservationsState extends FamilySearchCollectionState {

  public static final String URI = "https://familysearch.org/platform/collections/reservations";
  public static final String SANDBOX_URI = "https://sandbox.familysearch.org/platform/collections/reservations";

  public FamilySearchReservationsState() {
    this(false);
  }

  public FamilySearchReservationsState(boolean sandbox) {
    this(sandbox ? FamilySearchReferenceEnvironment.SANDBOX : FamilySearchReferenceEnvironment.PRODUCTION);
  }

  public FamilySearchReservationsState(FamilySearchReferenceEnvironment env) {
    this(env.getOrdinanceReservationsUri());
  }

  public FamilySearchReservationsState(java.net.URI uri) {
    this(uri, new FamilyTreeStateFactory());
  }

  private FamilySearchReservationsState(URI uri, FamilyTreeStateFactory stateFactory) {
    this(uri, stateFactory.loadDefaultClient(), stateFactory);
  }

  private FamilySearchReservationsState(URI uri, com.sun.jersey.api.client.Client client, FamilyTreeStateFactory stateFactory) {
    this(ClientRequest.create().accept(GedcomxConstants.GEDCOMX_JSON_MEDIA_TYPE).build(uri, HttpMethod.GET), client, stateFactory);
  }

  private FamilySearchReservationsState(ClientRequest request, com.sun.jersey.api.client.Client client, FamilyTreeStateFactory stateFactory) {
    this(request, client.handle(request), null, stateFactory);
  }

  protected FamilySearchReservationsState(ClientRequest request, ClientResponse client, String accessToken, FamilyTreeStateFactory stateFactory) {
    super(request, client, accessToken, stateFactory);
  }

  @Override
  protected FamilySearchReservationsState clone(ClientRequest request, ClientResponse response) {
    return new FamilySearchReservationsState(request, response, this.accessToken, (FamilyTreeStateFactory) this.stateFactory);
  }

  @Override
  public FamilySearchReservationsState ifSuccessful() {
    return (FamilySearchReservationsState) super.ifSuccessful();
  }

  @Override
  public FamilySearchReservationsState head(StateTransitionOption... options) {
    return (FamilySearchReservationsState) super.head(options);
  }

  @Override
  public FamilySearchReservationsState get(StateTransitionOption... options) {
    return (FamilySearchReservationsState) super.get(options);
  }

  @Override
  public FamilySearchReservationsState delete(StateTransitionOption... options) {
    return (FamilySearchReservationsState) super.delete(options);
  }

  @Override
  public FamilySearchReservationsState put(Gedcomx e, StateTransitionOption... options) {
    return (FamilySearchReservationsState) super.put(e, options);
  }

  @Override
  public FamilySearchReservationsState post(Gedcomx entity, StateTransitionOption... options) {
    return (FamilySearchReservationsState) super.post(entity, options);
  }

  @Override
  public FamilySearchReservationsState authenticateViaOAuth2Password(String username, String password, String clientId) {
    return (FamilySearchReservationsState) super.authenticateViaOAuth2Password(username, password, clientId);
  }

  @Override
  public FamilySearchReservationsState authenticateViaOAuth2Password(String username, String password, String clientId, String clientSecret) {
    return (FamilySearchReservationsState) super.authenticateViaOAuth2Password(username, password, clientId, clientSecret);
  }

  @Override
  public FamilySearchReservationsState authenticateViaOAuth2AuthCode(String authCode, String redirect, String clientId, String clientSecret) {
    return (FamilySearchReservationsState) super.authenticateViaOAuth2AuthCode(authCode, redirect, clientId, clientSecret);
  }

  @Override
  public FamilySearchReservationsState authenticateViaOAuth2AuthCode(String authCode, String redirect, String clientId) {
    return (FamilySearchReservationsState) super.authenticateViaOAuth2AuthCode(authCode, redirect, clientId);
  }

  @Override
  public FamilySearchReservationsState authenticateViaOAuth2ClientCredentials(String clientId, String clientSecret) {
    return (FamilySearchReservationsState) super.authenticateViaOAuth2ClientCredentials(clientId, clientSecret);
  }

  @Override
  public FamilySearchReservationsState authenticateViaOAuth2(MultivaluedMap<String, String> formData, StateTransitionOption... options) {
    return (FamilySearchReservationsState) super.authenticateViaOAuth2(formData);
  }

  public FamilySearchReservationsState authenticateViaUnauthenticatedAccess(String clientId, String ipAddress) {

    MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
    formData.add("grant_type", "unauthenticated_session");
    formData.add("client_id", clientId);
    formData.add("ip_address", ipAddress);

    return this.authenticateViaOAuth2(formData);
  }

  @Override
  public FamilySearchCollectionState readCollection(StateTransitionOption... options) {
    return (FamilySearchCollectionState) super.readCollection();
  }

  @Override
  public SourceDescriptionState addArtifact(SourceDescription description, DataSource artifact, StateTransitionOption... options) {
    if (description != null) {
      ArtifactMetadata artifactMetadata = description.findExtensionOfType(ArtifactMetadata.class);
      if (artifactMetadata != null) {
        ArtifactType type = artifactMetadata.getKnownType();
        if (type != null) {
          ArrayList<StateTransitionOption> newOptions = new ArrayList<StateTransitionOption>(Arrays.asList(options));
          newOptions.add(artifactType(type));
          options = newOptions.toArray(new StateTransitionOption[newOptions.size()]);
        }
      }
    }
    return super.addArtifact(description, artifact, options);
  }

  public OrdinanceReservationsState readReservations(StateTransitionOption... options) {
    Link link = getLink(Rel.RESERVATIONS);
    if (link == null || link.getHref() == null) {
      return null;
    }

    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest()).build(link.getHref().toURI(), HttpMethod.GET);
    return ((FamilyTreeStateFactory)this.stateFactory).newOrdinanceReservationsState(request, invoke(request, options), this.accessToken);
  }

  /**
   * Utility method to create a single list of Reservation objects from the reservations found in a list of
   *   OrdinanceReservationState objects. Can handle 0 or more arguments, each of which can be null, or have a null
   *   or empty or non-empty list of Reservation objects.
   * @param reservationStates - List of OrdinanceReservationState objects
   * @return List of Reservation objects, or null if there none.
   */
  public static List<Reservation> gatherReservations(OrdinanceReservationsState... reservationStates) {
    List<Reservation> list = new ArrayList<Reservation>();
    if (reservationStates != null) {
      for (OrdinanceReservationsState reservationState : reservationStates) {
        if (reservationState != null && reservationState.getReservations() != null) {
          list.addAll(reservationState.getReservations());
        }
      }
    }
    return list.size() == 0 ? null : list;
  }

  /**
   * Given a list of LDS temple ordinance reservations, do a POST to cause a PDF of temple cards to be created for that
   *   set of ordinances. Return a TempleCardPrintSetState that contains the URL of where to retrieve the PDF file from.
   *   Do get() on the resulting TempleCardPrintSetState to actually fetch the PDF InputStream.
   * Note that a FamilySearchReservationState object typically has ordinances for a person's individual ordinances,
   *   a person's sealing-to-parent ordinances, or a couple's sealing-to-spouse ordinances, but not a combination of these.
   *   This method takes a list of Reservation objects that can be gathered from one or more of these kinds of reservations
   *   (via getReservations() on each).
   * @param reservationList - List of LDS temple ordinance reservations to generate temple cards for
   * @param options - Optional options
   * @return TempleCardPrintSetState, which is the result of doing a POST to generate the cards.
   */
  public TempleCardPrintSetState createPrintSet(List<Reservation> reservationList, StateTransitionOption... options) {
    Link link = getLink(Rel.TEMPLE_CARD_PRINT_SETS);
    FamilySearchPlatform fsp = new FamilySearchPlatform();
    fsp.setReservations(reservationList);
    ClientRequest request = RequestUtil.applyFamilySearchConneg(createAuthenticatedRequest()).entity(fsp).build(link.getHref().toURI(), HttpMethod.POST);
    return ((FamilyTreeStateFactory)this.stateFactory).newTempleCardPrintSetState(request, invoke(request, options), this.accessToken);
  }
}
