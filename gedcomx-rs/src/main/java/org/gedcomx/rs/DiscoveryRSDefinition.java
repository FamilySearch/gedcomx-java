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
package org.gedcomx.rs;

import org.gedcomx.atom.Feed;
import org.gedcomx.rt.rs.ResourceDefinition;
import org.gedcomx.rt.rs.ResponseCode;
import org.gedcomx.rt.rs.StateTransition;
import org.gedcomx.rt.rs.StatusCodes;

import javax.ws.rs.GET;
import javax.ws.rs.core.Response;

/**
 * <p>The Discovery resource is the index of all available resources in the application and the starting point for a Web service API. The Discovery resource
 * allows a consumer of the API to use links to locate all the resources at runtime. To access resources, consumers are no longer required to hard-code endpoint
 * URLs nor to plug in identifiers to a URL template. This means that consumers of the API only the URL to the Discovery resource.</p>
 *
 * <p>The links provided by the Discovery resource should include:</p>
 *
 * <ul>
 *   <li>Links to the authentication mechanism that the system supports.</li>
 *   <li>Links to the root-level resources of the system.</li>
 *   <li>Links that describe the policies for using the application.</li>
 *   <li>Links that assert conformance to the various application profiles supported by the system.</li>
 * </ul>
 *
 * <p>The Discovery resource uses an <a href="http://www.ietf.org/rfc/rfc4287">Atom Feed</a> to supply all its links.</p>
 *
 * <p>It is recommended that the discovery resource be mounted at the <tt>/.well-known/app-meta</tt> endpoint. For more information on "well-known" URIs, see
 * <a href="http://tools.ietf.org/html/rfc5785">RFC5785</a>.</p>
 *
 * @author Mike Gardiner
 * @author Ryan Heaton
 */
@ResourceDefinition(
  name = "Discovery",
  id = "discovery",
  description = "The root index of the application, providing links to the various application states.",
  resourceElement = Feed.class,
  transitions = {
    @StateTransition (rel = ConclusionRSDefinition.REL, description = "The templated link to the conclusion resources of the application.", template = true, conditional = true, targetResource = ConclusionRSDefinition.class ),
    @StateTransition (rel = ConclusionsRSDefinition.REL, description = "The templated link to the conclusions resources of the application.", template = true, conditional = true, targetResource = ConclusionsRSDefinition.class ),
    @StateTransition (rel = AgentRSDefinition.REL, description = "The templated link to the contributor resources of the application.", template = true, targetResource = AgentRSDefinition.class ),
    @StateTransition (rel = CurrentUserPersonQuery.REL, description = "The link to the current user person of the application.", conditional = true, targetResource = CurrentUserPersonQuery.class ),
    @StateTransition (rel = NoteRSDefinition.REL, description = "The templated link to the note resources of the application.", template = true, conditional = true, targetResource = NoteRSDefinition.class ),
    @StateTransition (rel = NotesRSDefinition.REL, description = "The templated link to the notes resources of the application.", template = true, conditional = true, targetResource = NotesRSDefinition.class ),
    @StateTransition (rel = PersonRelationshipsRSDefinition.CHILD_RELATIONSHIPS_REL, description = "The templated link to the child relationships resources of the application.", template = true, conditional = true, targetResource = PersonRelationshipsRSDefinition.class ),
    @StateTransition (rel = PersonRelationshipsRSDefinition.SPOUSE_RELATIONSHIPS_REL, description = "The templated link to the spouse relationships resources of the application.", template = true, conditional = true, targetResource = PersonRelationshipsRSDefinition.class ),
    @StateTransition (rel = PersonRelationshipsRSDefinition.PARENT_RELATIONSHIPS_REL, description = "The templated link to the parent relationships resources of the application.", template = true, conditional = true, targetResource = PersonRelationshipsRSDefinition.class ),
    @StateTransition (rel = PersonRSDefinition.REL, description = "The templated link to the person resources of the application.", template = true, targetResource = PersonRSDefinition.class ),
    @StateTransition (rel = PersonsRSDefinition.REL, description = "The link to the persons of the application.", conditional = true, targetResource = PersonsRSDefinition.class ),
    @StateTransition (rel = RelationshipRSDefinition.REL, description = "The templated link to the relationship resources of the application.", template = true, targetResource = RelationshipRSDefinition.class ),
    @StateTransition (rel = RelationshipsRSDefinition.REL, description = "The link to the relationships for this application.", conditional = true, targetResource = RelationshipsRSDefinition.class ),
    @StateTransition (rel = SourceReferencesRSDefinition.REL, description = "The templated link to the source references resources of the application.", template = true, conditional = true, targetResource = SourceReferencesRSDefinition.class ),
    @StateTransition (rel = SourceReferenceRSDefinition.REL, description = "The templated link to the source reference resources of the application.", template = true, conditional = true, targetResource = SourceReferenceRSDefinition.class ),
    @StateTransition (rel = SourceDescriptionRSDefinition.REL, description = "The templated link to the source description resources for this application.", template = true, targetResource = SourceDescriptionRSDefinition.class ),
    @StateTransition (rel = SourceDescriptionsRSDefinition.REL, description = "The source descriptions resource for this application.", targetResource = SourceDescriptionsRSDefinition.class ),
    @StateTransition (rel = PersonSearchQuery.REL, description = "The person search query for this application.", template = true, targetResource = PersonSearchQuery.class )
  }
)
public interface DiscoveryRSDefinition {

  /**
   * Read the host metadata.
   *
   * @return The host metadata.
   */
  @GET
  @StatusCodes({
    @ResponseCode(code = 200, condition = "Upon a successful read.")
  })
  Response get();

}
