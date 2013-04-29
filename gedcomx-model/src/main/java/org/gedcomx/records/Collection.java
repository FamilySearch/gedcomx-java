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
@XmlType ( name = "Collection" )
public class Collection extends HypermediaEnabledData implements Attributable {

  private String lang;
  private ResourceReference collectionRef;
  private String title;
  private String description;
  private Integer itemCount;
  private Float completeness;
  private CollectionCoverage coverage;
  private Attribution attribution;
  private List<Topic> topics;

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
   * The coverage of the collection.
   *
   * @return The coverage of the collection.
   */
  public CollectionCoverage getCoverage() {
    return coverage;
  }

  /**
   * The coverage of the collection.
   *
   * @param coverage The coverage of the collection.
   */
  public void setCoverage(CollectionCoverage coverage) {
    this.coverage = coverage;
  }

  /**
   * The count of the total number of items in this collection.
   *
   * @return The count of the total number of items in this collection.
   */
  @XmlAttribute
  public Integer getItemCount() {
    return itemCount;
  }

  /**
   * The count of the total number of items in this collection.
   *
   * @param itemCount The count of the total number of items in this collection.
   */
  public void setItemCount(Integer itemCount) {
    this.itemCount = itemCount;
  }

  /**
   * A completeness factor for the collection, i.e. what percentage of the known items are actually included in the collection. Used to determine, for example,
   * reliability of a search of the data in this collection. The completeness factor is a value between 0 and 1.
   *
   * @return A completeness factor for the collection, a value between 0 and 1.
   */
  public Float getCompleteness() {
    return completeness;
  }

  /**
   * A completeness factor for the collection, i.e. what percentage of the known items are actually included in the collection. Used to determine, for example,
   * reliability of a search of the data in this collection. The completeness factor is a value between 0 and 1.
   *
   * @param completeness A completeness factor for the collection, a value between 0 and 1.
   */
  public void setCompleteness(Float completeness) {
    this.completeness = completeness;
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
   * Accept a visitor.
   *
   * @param visitor The visitor.
   */
  public void accept(GedcomxModelVisitor visitor) {
    visitor.visitCollection(this);
  }

  /**
   * The list of browse-able topics for the collection, used to describe the applicable facets of the collection for convenience in browsing.
   *
   * @return The list of browse-able topics for the collection.
   */
  @XmlElement ( name = "topic" )
  @JsonName ( "topics" )
  @JsonProperty ( "topics" )
  public List<Topic> getTopics() {
    return topics;
  }

  /**
   * The list of browse-able topics for the collection, used to describe the applicable facets of the collection for convenience in browsing.
   *
   * @param topics The list of browse-able topics for the collection.
   */
  @JsonProperty ( "topics" )
  public void setTopics(List<Topic> topics) {
    this.topics = topics;
  }
}
