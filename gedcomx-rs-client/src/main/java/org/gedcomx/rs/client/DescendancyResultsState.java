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
import org.gedcomx.rs.Rel;
import org.gedcomx.rs.client.util.DescendancyTree;

import javax.ws.rs.HttpMethod;
import java.net.URI;
import java.util.List;

/**
 * @author Ryan Heaton
 */
public class DescendancyResultsState<E> extends GedcomxApplicationState<Gedcomx> {

  protected DescendancyResultsState(ClientRequest request, ClientResponse response, String accessToken, StateFactory stateFactory) {
    super(request, response, accessToken, stateFactory);
  }

  @Override
  protected DescendancyResultsState clone(ClientRequest request, ClientResponse response) {
    return new DescendancyResultsState(request, response, this.accessToken, this.stateFactory);
  }

  @Override
  protected Gedcomx loadEntity(ClientResponse response) {
    return response.getEntity(Gedcomx.class);
  }

  @Override
  public Gedcomx getEntity() {
    return super.getEntity();
  }

  @Override
  public DescendancyResultsState head(StateTransitionOption... options) {
    return (DescendancyResultsState) super.head(options);
  }

  @Override
  public DescendancyResultsState options(StateTransitionOption... options) {
    return (DescendancyResultsState) super.options(options);
  }

  @Override
  public DescendancyResultsState get(StateTransitionOption... options) {
    return (DescendancyResultsState) super.get(options);
  }

  @Override
  public DescendancyResultsState delete(StateTransitionOption... options) {
    return (DescendancyResultsState) super.delete(options);
  }

  @Override
  public DescendancyResultsState put(Gedcomx e, StateTransitionOption... options) {
    return (DescendancyResultsState) super.put(e, options);
  }

  @Override
  public DescendancyResultsState post(Gedcomx entity, StateTransitionOption... options) {
    return (DescendancyResultsState) super.post(entity, options);
  }

  @Override
  public DescendancyResultsState ifSuccessful() {
    return (DescendancyResultsState) super.ifSuccessful();
  }

  @Override
  protected SupportsLinks getMainDataElement() {
    return getEntity();
  }

  public DescendancyTree getTree() {
    return getEntity() != null ? new DescendancyTree(getEntity()) : null;
  }

  public PersonState readPerson(String descendantNumber, StateTransitionOption... options) {
    //descendantNumber must be written in the d'Aboville method (a string of ints separated by periods),
    // e.g. "1.2.10" to retrieve this person's 2nd child's 10th child

    //parse string
    int[] indexes;
    try {
      String[] indexStrings = descendantNumber.split("\\.");
      indexes = new int[indexStrings.length];
      for (int i = 0; i < indexStrings.length; i++) {
        indexes[i] = Integer.parseInt(indexStrings[i]);
      }
    }
    catch (NumberFormatException e) {
      return null;
    }

    DescendancyResultsState descendancyResultsState = this;
    DescendancyTree.DescendancyNode descendancyNode = descendancyResultsState.getTree().getRoot();
    List<DescendancyTree.DescendancyNode> descendantChildren;

    //first index in d'Aboville System must be 1 to represent this person
    if (indexes[0]!=1) {
      return null;
    }

    //iterate through children with indexes; skip first index
    for(int i = 1; i < indexes.length; i++) {
      int childNumber = indexes[i];
      descendantChildren = descendancyNode.getChildren();
      //return null if specified child doesn't exist
      if (null == descendantChildren) {
        return null;
      }
      if (childNumber > descendantChildren.size() || childNumber < 0) {
        return null;
      }
      //select next child; subtract 1 to index properly
      descendancyNode = descendantChildren.get(childNumber - 1);
    }

    Link selfLink = descendancyNode.getPerson().getLink(Rel.PERSON);
    if (selfLink == null || selfLink.getHref() == null) {
      selfLink = descendancyNode.getPerson().getLink(Rel.SELF);
     }

    URI personUri = selfLink == null || selfLink.getHref() == null ? null : selfLink.getHref().toURI();
    if (personUri == null) {
      return null;
    }

    ClientRequest request = createAuthenticatedGedcomxRequest().build(personUri, HttpMethod.GET);
    return this.stateFactory.newPersonState(request, invoke(request, options), this.accessToken);
  }
}
