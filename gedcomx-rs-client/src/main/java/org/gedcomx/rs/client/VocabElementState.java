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
import org.gedcomx.common.TextValue;
import org.gedcomx.links.SupportsLinks;
import org.gedcomx.rs.Rel;
import org.gedcomx.rs.client.util.VocabConstants;

import java.util.ArrayList;
import java.util.List;

public class VocabElementState extends GedcomxApplicationState<Model> {

  private Resource resourceDescribingList;
  private Model model;

  static {
    JenaJSONLD.init();
  }

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
    StmtIterator it = resourceDescribingList.listProperties();
//    while (it.hasNext()) {
//      Statement statement = it.nextStatement();
//    }

    return model;
  }

  public String getSubclass() {
    return getResourceDescribingList().getRequiredProperty(model.createProperty(VocabConstants.RDFS_NAMESPACE, "subClassOf")).getResource().getURI();
  }

  public String getType() {
    return getResourceDescribingList().getRequiredProperty(model.createProperty(VocabConstants.DC_NAMESPACE, "type")).getResource().getURI();
  }

  public List<TextValue> getLabels() {
    List<TextValue> labels = new ArrayList<TextValue>();
    StmtIterator rdfLabels = getResourceDescribingList().listProperties(model.createProperty(VocabConstants.RDFS_NAMESPACE, "label"));
    while (rdfLabels.hasNext()) {
      Statement rdfLabel = rdfLabels.nextStatement();
      TextValue label = new TextValue();
      label.setLang(rdfLabel.getLanguage().toLowerCase());
      label.setValue(rdfLabel.toString());
      labels.add(label);
    }
    return labels;
//    Map<String, List> map = (Map<String, List>)entity;
//    return map.get("labels");
  }

  public List<TextValue> getDescriptions() {
    List<TextValue> descriptions = new ArrayList<TextValue>();
    StmtIterator rdfDescriptions = getResourceDescribingList().listProperties(this.model.createProperty(VocabConstants.RDFS_NAMESPACE, "description"));
    while (rdfDescriptions.hasNext()) {
      Statement rdfDescription = rdfDescriptions.nextStatement();
      TextValue label = new TextValue();
      label.setLang(rdfDescription.getLanguage().toLowerCase());
      label.setValue(rdfDescription.toString());
      descriptions.add(label);
    }
    return descriptions;
//    Map<String, List> map = (Map<String, List>)entity;
//    return map.get("descriptions");
  }

  @Override
  protected SupportsLinks getMainDataElement() {
//    return getPlaceDescription();
    return null;
  }
//
//  public PlaceDescription getPlaceDescription() {
//    return getEntity() == null ? null : getEntity().getPlaces() == null ? null : getEntity().getPlaces().isEmpty() ? null : getEntity().getPlaces().get(0);
//  }
//

  private Resource getResourceDescribingList() {
    if (null == this.resourceDescribingList) {
      this.resourceDescribingList = this.model.getResource(this.request.getURI().toString());
    }
    return this.resourceDescribingList;
  }
}
