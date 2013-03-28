package org.gedcomx.records;

import org.codehaus.enunciate.json.JsonName;
import org.codehaus.jackson.annotate.JsonProperty;
import org.gedcomx.common.TextValue;
import org.gedcomx.common.URI;
import org.gedcomx.conclusion.Date;
import org.gedcomx.conclusion.PlaceReference;
import org.gedcomx.links.HypermediaEnabledData;
import org.gedcomx.rt.json.JsonElementWrapper;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * A collection of genealogical data.
 *
 * @author Ryan Heaton
 */
@XmlRootElement
@JsonElementWrapper ( name = "collections" )
@XmlType ( name = "Collection" )
public class Collection extends HypermediaEnabledData {

  private List<TextValue> titles;
  private PlaceReference spatialCoverage;
  private Date temporalCoverage;
  private URI itemType;
  private Integer itemCount;
  private URI collectionRef;

  /**
   * A list of titles for this collection.
   *
   * @return A list of titles for this collection.
   */
  @XmlElement (name="title")
  @JsonProperty ("titles")
  @JsonName ("titles")
  public List<TextValue> getTitles() {
    return titles;
  }

  /**
   * A list of titles for this collection.
   *
   * @param titles A list of titles for this collection.
   */
  @JsonProperty ("titles")
  public void setTitles(List<TextValue> titles) {
    this.titles = titles;
  }

  /**
   * The spatial (geographic) coverage of this collection.
   *
   * @return The spatial (geographic) coverage of this collection.
   */
  public PlaceReference getSpatialCoverage() {
    return spatialCoverage;
  }

  /**
   * The spatial (geographic) coverage of this collection.
   *
   * @param spatialCoverage The spatial (geographic) coverage of this collection.
   */
  public void setSpatialCoverage(PlaceReference spatialCoverage) {
    this.spatialCoverage = spatialCoverage;
  }

  /**
   * The temporal coverage (e.g. time period) of this collection.
   *
   * @return The temporal coverage (e.g. time period) of this collection.
   */
  public Date getTemporalCoverage() {
    return temporalCoverage;
  }

  /**
   * The temporal coverage (e.g. time period) of this collection.
   *
   * @param temporalCoverage The temporal coverage (e.g. time period) of this collection.
   */
  public void setTemporalCoverage(Date temporalCoverage) {
    this.temporalCoverage = temporalCoverage;
  }

  /**
   * The data type of the individual items contained in the collection.
   *
   * @return The data type of the individual items contained in the collection.
   */
  public URI getItemType() {
    return itemType;
  }

  /**
   * The data type of the individual items contained in the collection.
   *
   * @param itemType The data type of the individual items contained in the collection.
   */
  public void setItemType(URI itemType) {
    this.itemType = itemType;
  }

  /**
   * The count of the total number of items in this collection.
   *
   * @return The count of the total number of items in this collection.
   */
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
   * A reference to the collection containing this collection.
   *
   * @return A reference to the collection containing this collection.
   */
  @XmlAttribute ( name = "collection" )
  @JsonName ( "collection" )
  @JsonProperty ( "collection" )
  public URI getCollectionRef() {
    return collectionRef;
  }

  /**
   * A reference to the collection containing this collection.
   *
   * @param collectionRef A reference to the collection containing this collection.
   */
  @JsonProperty ( "collection" )
  public void setCollectionRef(URI collectionRef) {
    this.collectionRef = collectionRef;
  }
}
