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
package org.gedcomx.records;

import org.codehaus.enunciate.json.JsonName;
import org.codehaus.jackson.annotate.JsonProperty;
import org.gedcomx.common.Attributable;
import org.gedcomx.common.Attribution;
import org.gedcomx.links.HypermediaEnabledData;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.rt.GedcomxModelVisitor;
import org.gedcomx.rt.json.JsonElementWrapper;

import javax.xml.XMLConstants;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * A collection of genealogical resources.
 *
 * @author Ryan Heaton
 */
@XmlRootElement
@JsonElementWrapper ( name = "collections" )
@XmlType ( name = "Collection", propOrder = { "title", "size", "content", "facets", "attribution" })
@org.codehaus.enunciate.Facet ( name = GedcomxConstants.FACET_GEDCOMX_RECORD )
public class Collection extends HypermediaEnabledData implements Attributable {

  private String lang;
  private List<CollectionContent> content; //todo: let's just use facets for this...
  private String title;
  private Integer size;
  private Attribution attribution;
  private List<Field> facets; //todo: facets need to be nested?

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
   * A title for the collection.
   *
   * @return A title for the collection.
   */
  public String getTitle() {
    return title;
  }

  /**
   * A title for the collection.
   *
   * @param title A title for the collection.
   */
  public void setTitle(String title) {
    this.title = title;
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
   * The size of the collection, in terms of the number of items in this collection.
   *
   * @return The size of the collection, in terms of the number of items in this collection.
   */
  public Integer getSize() {
    return size;
  }

  /**
   * The size of the collection, in terms of the number of items in this collection.
   *
   * @param size The size of the collection, in terms of the number of items in this collection.
   */
  public void setSize(Integer size) {
    this.size = size;
  }

  /**
   * The list of facets for the collection, used for convenience in browsing and filtering.
   *
   * @return The list of facets for the collection, used for convenience in browsing and filtering.
   */
  @XmlElement ( name = "facet" )
  @JsonName ( "facets" )
  @JsonProperty ( "facets" )
  public List<Field> getFacets() {
    return facets;
  }

  /**
   * The list of facets for the collection, used for convenience in browsing and filtering.
   *
   * @param facets The list of facets for the collection, used for convenience in browsing and filtering.
   */
  @JsonProperty ( "facets" )
  public void setFacets(List<Field> facets) {
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

  /**
   * Embed another collection.
   * 
   * @param collection The collection to embed.
   */
  public void embed(Collection collection) {
    this.lang = this.lang == null ? collection.lang : this.lang;
    this.title = this.title == null ? collection.title : this.title;
    this.size = this.size == null ? collection.size : this.size;
    this.attribution = this.attribution == null ? collection.attribution : this.attribution;
    this.attribution = this.attribution == null ? collection.attribution : this.attribution;
    if (collection.content != null) {
      this.content = this.content == null ? new ArrayList<CollectionContent>() : this.content;
      this.content.addAll(collection.content);
    }
    if (collection.facets != null) {
      this.facets = this.facets == null ? new ArrayList<Field>() : this.facets;
      this.facets.addAll(collection.facets);
    }

    super.embed(collection);
  }
}
