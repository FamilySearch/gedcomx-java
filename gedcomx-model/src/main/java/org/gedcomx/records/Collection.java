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
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.gedcomx.common.Attributable;
import org.gedcomx.common.Attribution;
import org.gedcomx.common.ResourceReference;
import org.gedcomx.common.URI;
import org.gedcomx.conclusion.Identifier;
import org.gedcomx.links.HypermediaEnabledData;
import org.gedcomx.rt.GedcomxModelVisitor;
import org.gedcomx.rt.json.JsonElementWrapper;
import org.gedcomx.source.Coverage;
import org.gedcomx.types.IdentifierType;

import javax.xml.XMLConstants;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A collection of genealogical resources.
 *
 * @author Ryan Heaton
 */
@XmlRootElement
@JsonElementWrapper ( name = "collections" )
@XmlType ( name = "Collection", propOrder = { "title", "description", "identifiers", "collectionRef", "size", "coverage", "content", "facets", "attribution" })
public class Collection extends HypermediaEnabledData implements Attributable {

  private String lang;
  private ResourceReference collectionRef;
  private List<Identifier> identifiers;
  private String title;
  private String description;
  private Integer size;
  private Coverage coverage;
  private List<CollectionContent> content;
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
   * A description for the collection.
   *
   * @return A description for the collection.
   */
  public String getDescription() {
    return description;
  }

  /**
   * A description for the collection.
   *
   * @param description A description for the collection.
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Find the long-term, persistent identifier for this person from the list of identifiers.
   *
   * @return The long-term, persistent identifier for this person.
   */
  @XmlTransient
  @JsonIgnore
  public URI getPersistentId() {
    URI identifier = null;
    if (this.identifiers != null) {
      for (Identifier id : this.identifiers) {
        if (IdentifierType.Persistent.equals(id.getKnownType())) {
          identifier = id.getValue();
          break;
        }
      }
    }
    return identifier;
  }

  /**
   * A long-term, persistent, globally unique identifier for this person.
   *
   * @param persistentId A long-term, persistent, globally unique identifier for this person.
   */
  @JsonIgnore
  public void setPersistentId(URI persistentId) {
    if (this.identifiers == null) {
      this.identifiers = new ArrayList<Identifier>();
    }

    //clear out any other primary ids.
    Iterator<Identifier> it = this.identifiers.iterator();
    while (it.hasNext()) {
      if (IdentifierType.Persistent.equals(it.next().getKnownType())) {
        it.remove();
      }
    }

    Identifier identifier = new Identifier();
    identifier.setKnownType(IdentifierType.Persistent);
    identifier.setValue(persistentId);
    this.identifiers.add(identifier);
  }

  /**
   * The list of identifiers for the person.
   *
   * @return The list of identifiers for the person.
   */
  @XmlElement (name="identifier")
  @JsonProperty ("identifiers")
  @JsonName ("identifiers")
  public List<Identifier> getIdentifiers() {
    return identifiers;
  }

  /**
   * The list of identifiers of the person.
   *
   * @param identifiers The list of identifiers of the person.
   */
  @JsonProperty ("identifiers")
  public void setIdentifiers(List<Identifier> identifiers) {
    this.identifiers = identifiers;
  }

  /**
   * The coverage of the collection.
   *
   * @return The coverage of the collection.
   */
  public Coverage getCoverage() {
    return coverage;
  }

  /**
   * The coverage of the collection.
   *
   * @param coverage The coverage of the collection.
   */
  public void setCoverage(Coverage coverage) {
    this.coverage = coverage;
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
