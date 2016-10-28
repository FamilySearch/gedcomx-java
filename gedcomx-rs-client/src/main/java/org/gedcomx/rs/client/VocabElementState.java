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
import org.apache.jena.rdf.model.*;
import org.gedcomx.common.URI;
import org.gedcomx.links.SupportsLinks;
import org.gedcomx.rs.Rel;
import org.gedcomx.rs.client.util.VocabConstants;
import org.gedcomx.vocab.VocabElement;

public class VocabElementState extends GedcomxApplicationState<Model> {

  private Resource resourceDescribingElement;
  private Model model;

  protected VocabElementState(ClientRequest request, ClientResponse response, String accessToken, StateFactory stateFactory) {
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
  public VocabElementState put(Model e, StateTransitionOption... options) {
    return (VocabElementState) super.put(e, options);
  }

  @Override
  public VocabElementState post(Model entity, StateTransitionOption... options) {
    return (VocabElementState) super.post(entity, options);
  }

  @Override
  protected Model loadEntity(ClientResponse response) {
    model = ModelFactory.createDefaultModel();
    model.read(response.getEntityInputStream(), null, "JSONLD");
    this.resourceDescribingElement = model.getResource(this.request.getURI().toString());
    return model;
  }

  public VocabElement getVocabElement() {
    VocabElement vocabElement = new VocabElement();
    Model model = resourceDescribingElement.getModel();

    // Map required attributes into the VocabElement
    vocabElement.setId(resourceDescribingElement.getRequiredProperty(model.createProperty(VocabConstants.DC_NAMESPACE, "identifier")).getString());
    vocabElement.setUri(URI.create(resourceDescribingElement.getURI()));

    // Get optional attributes into the VocabElement
    Property property = model.createProperty(VocabConstants.RDFS_NAMESPACE, "subClassOf");
    if (resourceDescribingElement.hasProperty(property)) {
      vocabElement.setSubclass(URI.create(resourceDescribingElement.getRequiredProperty(model.createProperty(VocabConstants.RDFS_NAMESPACE, "subClassOf")).getResource().getURI()));
    }
    property = model.createProperty(VocabConstants.DC_NAMESPACE, "type");
    if (resourceDescribingElement.hasProperty(property)) {
      vocabElement.setType(URI.create(resourceDescribingElement.getRequiredProperty(model.createProperty(VocabConstants.DC_NAMESPACE, "type")).getResource().getURI()));
    }

    // Map the labels into the VocabElement
    StmtIterator labels = resourceDescribingElement.listProperties(model.createProperty(VocabConstants.RDFS_NAMESPACE, "label"));
    while (labels.hasNext()) {
      Statement next = labels.next();
      vocabElement.addLabel(next.getString(), next.getLanguage().toLowerCase());
    }

    // Map the descriptions into the VocabElement
    StmtIterator descriptions = resourceDescribingElement.listProperties(model.createProperty(VocabConstants.RDFS_NAMESPACE, "comment"));
    while (descriptions.hasNext()) {
      Statement next = descriptions.next();
      vocabElement.addDescription(next.getString(), next.getLanguage().toLowerCase());
    }
    return vocabElement;
  }

  @Override
  protected SupportsLinks getMainDataElement() {
//    return getPlaceDescription();
    return null;
  }

  private Resource getResourceDescribingElement() {
    if (null == this.resourceDescribingElement) {
      this.resourceDescribingElement = this.model.getResource(this.request.getURI().toString());
    }
    return this.resourceDescribingElement;
  }
}
