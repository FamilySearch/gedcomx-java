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
import com.fasterxml.jackson.annotation.JsonProperty;
import org.gedcomx.common.Attributable;
import org.gedcomx.common.Attribution;
import org.gedcomx.common.ExtensibleData;
import org.gedcomx.common.URI;
import org.gedcomx.conclusion.Identifier;
import org.gedcomx.links.HypermediaEnabledData;
import org.gedcomx.links.Link;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.rt.GedcomxModelVisitor;
import org.gedcomx.rt.json.JsonElementWrapper;

import javax.xml.XMLConstants;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * A collection of genealogical resources.
 *
 * @author Ryan Heaton
 */
@XmlRootElement
@JsonElementWrapper ( name = "collections" )
@XmlType ( name = "Collection", propOrder = { "identifiers", "title", "size", "content", "attribution" })
@org.codehaus.enunciate.Facet ( name = GedcomxConstants.FACET_GEDCOMX_RECORD )
public class Collection extends HypermediaEnabledData implements Attributable {

  private String lang;
  private List<Identifier> identifiers;
  private List<CollectionContent> content;
  private String title;
  private Integer size;
  private Attribution attribution;

  @Override
  public Collection id(String id) {
    return (Collection) super.id(id);
  }

  @Override
  public Collection extensionElement(Object element) {
    return (Collection) super.extensionElement(element);
  }

  @Override
  public Collection link(Link link) {
    return (Collection) super.link(link);
  }

  @Override
  public Collection link(String rel, URI href) {
    return (Collection) super.link(rel, href);
  }

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
   * Build out this collection with a lang.
   * @param lang The lang.
   * @return this.
   */
  public Collection lang(String lang) {
    setLang(lang);
    return this;
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
   * Build out this collection with a title.
   * @param title the title.
   * @return this.
   */
  public Collection title(String title) {
    setTitle(title);
    return this;
  }

  /**
   * The list of identifiers for the source.
   *
   * @return The list of identifiers for the source.
   */
  @XmlElement(name="identifier")
  @JsonProperty("identifiers")
  @JsonName("identifiers")
  public List<Identifier> getIdentifiers() {
    return identifiers;
  }

  /**
   * The list of identifiers of the source.
   *
   * @param identifiers The list of identifiers of the source.
   */
  @JsonProperty ("identifiers")
  public void setIdentifiers(List<Identifier> identifiers) {
    this.identifiers = identifiers;
  }

  /**
   * Build out this collection with an identifier.
   * @param identifier the identifier.
   * @return this.
   */
  public Collection identifier(Identifier identifier) {
    if (this.identifiers == null) {
      this.identifiers = new ArrayList<Identifier>();
    }
    this.identifiers.add(identifier);
    return this;
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
   * Build out this collection with content.
   * @param content The content.
   * @return this.
   */
  public Collection content(CollectionContent content) {
    addContent(content);
    return this;
  }

  /**
   * Add a reference to the record content values being used as evidence.
   *
   * @param content The content to be added.
   */
  public void addContent(CollectionContent content) {
    if (content != null) {
      if (this.content == null) {
        this.content = new LinkedList<CollectionContent>();
      }
      this.content.add(content);
    }
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
   * Build out this collection with attribution.
   * @param attribution The attribution.
   * @return this.
   */
  public Collection attribution(Attribution attribution) {
    setAttribution(attribution);
    return this;
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
   * Build out this collection with size.
   *
   * @param size the size.
   * @return this.
   */
  public Collection size(Integer size) {
    setSize(size);
    return this;
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

    super.embed(collection);
  }
}
