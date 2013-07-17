/**
 * Copyright 2011-2012 Intellectual Reserve, Inc.
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
package org.gedcomx.records;

import org.codehaus.enunciate.json.JsonName;
import org.codehaus.jackson.annotate.JsonProperty;
import org.gedcomx.common.Attributable;
import org.gedcomx.common.Attribution;
import org.gedcomx.common.ResourceReference;
import org.gedcomx.links.HypermediaEnabledData;
import org.gedcomx.rt.GedcomxModelVisitor;
import org.gedcomx.rt.json.JsonElementWrapper;

import javax.xml.XMLConstants;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * A collection of genealogical resources.
 *
 * @author Ryan Heaton
 */
@XmlRootElement
@JsonElementWrapper ( name = "collections" )
@XmlType ( name = "Collection", propOrder = { "collectionRef", "content", "facets", "attribution" })
public class Collection extends HypermediaEnabledData implements Attributable {

  private String lang;
  private ResourceReference collectionRef;
  private List<CollectionContent> content; //todo: let's just use facets for this...
  private Attribution attribution;
  private List<Facet> facets;

  /**
   * The language of this description of the collection. See <a href="http://www.w3.org/International/articles/language-tags/">http://www.w3.org/International/articles/language-tags/</a>
   *
   * @return The language of this description of the collection
   */
  @XmlAttribute ( namespace = XMLConstants.XML_NS_URI )
  public String getLang() {
    return lang;
  }

  /**
   * The language of this description of the collection. See <a href="http://www.w3.org/International/articles/language-tags/">http://www.w3.org/International/articles/language-tags/</a>
   *
   * @param lang The language of this description of the collection.
   */
  public void setLang(String lang) {
    this.lang = lang;
  }

  /**
   * A reference to the parent of this collection; the collection containing this collection.
   *
   * @return A reference to the collection containing this collection.
   */
  @XmlElement ( name = "collection" )
  @JsonName ( "collection" )
  @JsonProperty ( "collection" )
  public ResourceReference getCollectionRef() {
    return collectionRef;
  }

  /**
   * A reference to the parent of this collection; the collection containing this collection.
   *
   * @param collectionRef A reference to the collection containing this collection.
   */
  @JsonProperty ( "collection" )
  public void setCollectionRef(ResourceReference collectionRef) {
    this.collectionRef = collectionRef;
  }

  /**
   * Descriptions of the content of this collection.
   *
   * @return Descriptions of the content of this collection.
   */
  public List<CollectionContent> getContent() {
    return content;
  }

  /**
   * Descriptions of the content of this collection.
   *
   * @param content Descriptions of the content of this collection.
   */
  public void setContent(List<CollectionContent> content) {
    this.content = content;
  }

  /**
   * Attribution metadata for this collection.
   *
   * @return Attribution metadata for this collection.
   */
  @Override
  public Attribution getAttribution() {
    return attribution;
  }

  /**
   * Attribution metadata for this collection.
   *
   * @param attribution Attribution metadata for this collection.
   */
  @Override
  public void setAttribution(Attribution attribution) {
    this.attribution = attribution;
  }

  /**
   * The list of facets for the collection, used for convenience in browsing and filtering.
   *
   * @return The list of facets for the collection, used for convenience in browsing and filtering.
   */
  @XmlElement ( name = "facet" )
  @JsonName ( "facets" )
  @JsonProperty ( "facets" )
  public List<Facet> getFacets() {
    return facets;
  }

  /**
   * The list of facets for the collection, used for convenience in browsing and filtering.
   *
   * @param facets The list of facets for the collection, used for convenience in browsing and filtering.
   */
  @JsonProperty ( "facets" )
  public void setFacets(List<Facet> facets) {
    this.facets = facets;
  }

  /**
   * Accept a visitor.
   *
   * @param visitor The visitor.
   */
  public void accept(GedcomxModelVisitor visitor) {
    visitor.visitCollection(this);
  }

}
