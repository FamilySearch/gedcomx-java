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

import com.github.jsonldjava.jena.JenaJSONLD;
import com.hp.hpl.jena.rdf.model.*;
import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import org.apache.jena.riot.RDFDataMgr;
import org.gedcomx.common.URI;
import org.gedcomx.links.SupportsLinks;
import org.gedcomx.rs.Rel;
import org.gedcomx.rs.client.util.VocabConstants;
import org.gedcomx.vocab.VocabElement;
import org.gedcomx.vocab.VocabElementList;

public class VocabElementListState extends GedcomxApplicationState<Model> {

  private Resource resourceDescribingList;
  private Model model;

  static {
    JenaJSONLD.init();
  }

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
  public VocabElementState put(Model e, StateTransitionOption... options) {
    return (VocabElementState) super.put(e, options);
  }

  @Override
  public VocabElementState post(Model entity, StateTransitionOption... options) {
    return (VocabElementState) super.post(entity, options);
  }
//
//  public String getTitle() {
//    return getResourceDescribingList().getRequiredProperty(model.createProperty(VocabConstants.DC_NAMESPACE, "title")).getString();
////    return resourceDescribingList.getRequiredProperty(model.createProperty(VocabConstants.DC_NAMESPACE, "title")).getString());
////    LinkedHashMap<String, String> map = (LinkedHashMap<String, String>)entity;
////    return map.get("title");
//  }
//
//  public String getDescription() {
//    return getResourceDescribingList().getRequiredProperty(model.createProperty(VocabConstants.DC_NAMESPACE, "description")).getString();
////    return resourceDescribingList.getRequiredProperty(model.createProperty(VocabConstants.DC_NAMESPACE, "description")).getString());
////    LinkedHashMap<String, String> map = (LinkedHashMap<String, String>)entity;
////    return map.get("description");
//  }
//
//  public String getId() {
//    LinkedHashMap<String, String> map = (LinkedHashMap<String, String>)entity;
//    return map.get("@id");
//  }
//
//  public List<TextValue> getLabels(String vocabElementId) {
//    List<TextValue> labels = new ArrayList<TextValue>();
//    StmtIterator rdfLabels = getResourceDescribingList().listProperties(model.createProperty(VocabConstants.RDFS_NAMESPACE, "label"));
//    while ((rdfLabels.hasNext()) && 0 == (labels.size())) {
//      Statement rdfLabel = rdfLabels.nextStatement();
//      if (rdfLabel.toString().endsWith(vocabElementId)) {
//        TextValue label = new TextValue();
//        label.setLang(rdfLabel.getLanguage().toLowerCase());
//        label.setValue(rdfLabel.toString());
//        labels.add(label);
//      }
//    }
//    return labels;
////    LinkedHashMap<String, ArrayList> map = (LinkedHashMap<String, ArrayList>)entity;
////    return map.get("elements");
//  }
//
//  public List<TextValue> getDescriptions() {
//    List<TextValue> descriptions = new ArrayList<TextValue>();
//    StmtIterator rdfDescriptions = getResourceDescribingList().listProperties(this.model.createProperty(VocabConstants.RDFS_NAMESPACE, "description"));
//    while ((rdfDescriptions.hasNext()) && (0 == descriptions.size())) {
//      Statement rdfDescription = rdfDescriptions.nextStatement();
//      TextValue label = new TextValue();
//      label.setLang(rdfDescription.getLanguage().toLowerCase());
//      label.setValue(rdfDescription.toString());
//      descriptions.add(label);
//    }
//    return descriptions;
////    Map<String, List> map = (Map<String, List>)entity;
////    return map.get("descriptions");
//  }

  public VocabElementList getVocabElementList() {

    // Create and initialize the vocabulary element list
//    vocabElementList.setId();
    VocabElementList vocabElementList = new VocabElementList();
    vocabElementList.setUri(URI.create(getResourceDescribingList().getURI().toString()));
    vocabElementList.setTitle(getResourceDescribingList().getRequiredProperty(model.createProperty(VocabConstants.DC_NAMESPACE, "title")).getString());
    vocabElementList.setDescription(getResourceDescribingList().getRequiredProperty(model.createProperty(VocabConstants.DC_NAMESPACE, "description")).getString());

    // Initialize the list of vocabulary elements within the vocabulary element list
//    Seq seq = resourceDescribingList.as(Seq.class);
//    for (int i = 0; i < seq.size(); i++) {
//      mapVocabElement(seq.getResource(i + 1));
//    }

    return vocabElementList;
  }

  @Override
  protected Model loadEntity(ClientResponse response) {
//    Object entity;
//    try {
//      entity = JsonUtils.fromInputStream(response.getEntity(InputStream.class));
//    }
//    catch (IOException ioe) {
//      throw new GedcomxApplicationException(ioe);
//    }
//    return entity;

    model = ModelFactory.createDefaultModel();
    RDFDataMgr.read(model, response.getEntityInputStream(), null, JenaJSONLD.JSONLD);
    this.resourceDescribingList = model.getResource(this.request.getURI().toString());
//    StmtIterator it = resourceDescribingList.listProperties();
//    while (it.hasNext()) {
//      Statement statement = it.nextStatement();
//    }

    return model;
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

  private Resource getResourceDescribingList() {
    if (null == this.resourceDescribingList) {
      this.resourceDescribingList = model.getResource(this.request.getURI().toString());
    }
    return this.resourceDescribingList;
  }

  /**
   * Map a RDF resource that represents a vocabulary element to a GedcomX vocabulary element
   *
   * @param resourceDescribingElement the RDF resource that represents a vocabulary element
   * @return a GedcomX vocabulary element that corresponds to the given RDF resource
   */
  private VocabElement mapVocabElement(Resource resourceDescribingElement) {

    VocabElement vocabElement = new VocabElement();
    vocabElement.setSubclass(URI.create(resourceDescribingElement.getRequiredProperty(resourceDescribingElement.getModel().createProperty(VocabConstants.RDFS_NAMESPACE, "subClassOf")).getResource().getURI()));
    vocabElement.setType(URI.create(resourceDescribingElement.getRequiredProperty(resourceDescribingElement.getModel().createProperty(VocabConstants.DC_NAMESPACE, "type")).getResource().getURI()));
    vocabElement.setId(resourceDescribingElement.getRequiredProperty(resourceDescribingElement.getModel().createProperty(VocabConstants.DC_NAMESPACE, "identifier")).getString());
    vocabElement.setUri(URI.create(resourceDescribingElement.getURI().toString()));

    StmtIterator labels = resourceDescribingElement.listProperties(resourceDescribingElement.getModel().createProperty(VocabConstants.RDFS_NAMESPACE, "label"));
    while (labels.hasNext()) {
      Statement next = labels.next();
      vocabElement.addLabel(next.toString(), next.getLanguage().toLowerCase());
    }

    StmtIterator descriptions = resourceDescribingElement.listProperties(resourceDescribingElement.getModel().createProperty(VocabConstants.RDFS_NAMESPACE, "comment"));
    while (descriptions.hasNext()) {
      Statement next = descriptions.next();
      vocabElement.addDescription(next.toString(), next.getLanguage().toLowerCase());
    }
    return vocabElement;
  }
}
