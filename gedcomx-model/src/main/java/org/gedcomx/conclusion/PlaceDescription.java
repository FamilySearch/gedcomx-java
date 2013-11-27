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
package org.gedcomx.conclusion;

import org.codehaus.enunciate.Facet;
import org.codehaus.enunciate.json.JsonName;
import org.codehaus.jackson.annotate.JsonProperty;
import org.gedcomx.common.ResourceReference;
import org.gedcomx.common.TextValue;
import org.gedcomx.common.URI;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.rt.GedcomxModelVisitor;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * A PlaceDescription is used to describe the details of a place in terms of its name
 * and possibly its type, time period, and/or a geospatial description -- a description
 * of a place as a snapshot in time.
 */
@XmlType ( name = "PlaceDescription", propOrder = { "names", "temporalDescription", "latitude", "longitude", "spatialDescription" } )
public class PlaceDescription extends Subject {

  private List<TextValue> names;
  private URI type;
  private Date temporalDescription;
  private Double latitude;
  private Double longitude;
  private ResourceReference spatialDescription;

  /**
   * An ordered list of standardized (or normalized), fully-qualified (in terms of what is known of the applicable jurisdictional hierarchy) names for this place that are applicable to this description of this place.
   *
   * The list MUST include at least one value. It is RECOMMENDED that instances include a single name and any equivalents from other cultural contexts;
   * name variants should more typically be described in separate PlaceDescription instances.  The list is assumed to be given in order of preference,
   * with the most preferred value in the first position in the list.
   *
   * @return An ordered list of standardized (or normalized), fully-qualified (in terms of what is known of the applicable jurisdictional hierarchy) names for this place that are applicable to this description of this place.
   */
  @XmlElement (name = "name")
  @JsonName ("names")
  @JsonProperty ("names")
  public List<TextValue> getNames() {
    return names;
  }

  /**
   * An ordered list of standardized (or normalized), fully-qualified (in terms of what is known of the applicable jurisdictional hierarchy) names for this place that are applicable to this description of this place.
   *
   * The list MUST include at least one value. It is RECOMMENDED that instances include a single name and any equivalents from other cultural contexts;
   * name variants should more typically be described in separate PlaceDescription instances.  The list is assumed to be given in order of preference,
   * with the most preferred value in the first position in the list.
   *
   * @param names An ordered list of standardized (or normalized), fully-qualified (in terms of what is known of the applicable jurisdictional hierarchy) names for this place that are applicable to this description of this place.
   */
  @JsonProperty ("names")
  public void setNames(List<TextValue> names) {
    this.names = names;
  }

  /**
   * Add a name to the list of standardized names.
   *
   * @param name The name to be added.
   */
  public void addName(TextValue name) {
    if (name != null) {
      if (names == null) {
        names = new LinkedList<TextValue>();
      }
      names.add(name);
    }
  }

  /**
   * An implementation-specific uniform resource identifier (URI) used to identify the type of a place (e.g., address, city, county, province, state, country, etc.).
   *
   * @return An implementation-specific uniform resource identifier (URI) used to identify the type of a place (e.g., address, city, county, province, state, country, etc.).
   */
  @XmlAttribute
  @Facet ( name = GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  public URI getType() {
    return type;
  }

  /**
   * An implementation-specific uniform resource identifier (URI) used to identify the type of a place (e.g., address, city, county, province, state, country, etc.).
   *
   * @param type An implementation-specific uniform resource identifier (URI) used to identify the type of a place (e.g., address, city, county, province, state, country, etc.).
   */
  public void setType(URI type) {
    this.type = type;
  }

  /**
   * A description of the time period to which this place description is relevant.
   *
   * @return A description of the time period to which this place description is relevant.
   */
  @Facet ( name = GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  public Date getTemporalDescription() {
    return temporalDescription;
  }

  /**
   *  A description of the time period to which this place description is relevant.
   *
   * @param temporalDescription A description of the time period to which this place description is relevant.
   */
  public void setTemporalDescription(Date temporalDescription) {
    this.temporalDescription = temporalDescription;
  }

  /**
   * Degrees north or south of the Equator (0.0 degrees).   Values range from −90.0 degrees (south) to 90.0 degrees (north).
   *
   * @return Degrees north or south of the Equator.
   */
  public Double getLatitude() {
    return latitude;
  }

  /**
   * Degrees north or south of the Equator (0.0 degrees).   Values range from −90.0 degrees (south) to 90.0 degrees (north).
   *
   * @param latitude Degrees north or south of the Equator.
   */
  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  /**
   * Angular distance in degrees, relative to the Prime Meridian.   Values range from −180.0 degrees (west of the Meridian) to 180.0 degrees (east of the Meridian).
   *
   * @return Angular distance in degrees, relative to the Prime Meridian.
   */
  public Double getLongitude() {
    return longitude;
  }

  /**
   * Angular distance in degrees, relative to the Prime Meridian.   Values range from −180.0 degrees (west of the Meridian) to 180.0 degrees (east of the Meridian).
   *
   * @param longitude Angular distance in degrees, relative to the Prime Meridian.
   */
  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  /**
   * A reference to a geospatial description of this place.
   *
   * It is RECOMMENDED that this description resolve to a KML document.
   *
   * @return  A reference to a geospatial description of this place.
   */
  @Facet ( name = GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  public ResourceReference getSpatialDescription() {
    return spatialDescription;
  }

  /**
   *  A reference to a geospatial description of this place.
   *
   *  It is RECOMMENDED that this description resolve to a KML document.
   *
   * @param spatialDescription A reference to a geospatial description of this place.
   */
  public void setSpatialDescription(ResourceReference spatialDescription) {
    this.spatialDescription = spatialDescription;
  }

  /**
   * Accept a visitor.
   *
   * @param visitor The visitor.
   */
  public void accept(GedcomxModelVisitor visitor) {
    visitor.visitPlaceDescription(this);
  }

  /**
   * Embed another place.
   * 
   * @param place The place to embed.
   */
  public void embed(PlaceDescription place) {
    if (place.names != null) {
      this.names = this.names == null ? new ArrayList<TextValue>() : this.names;
      this.names.addAll(place.names);
    }
    this.type = this.type == null ? place.type : this.type;
    this.temporalDescription = this.temporalDescription == null ? place.temporalDescription : this.temporalDescription;
    this.latitude = this.latitude == null ? place.latitude : this.latitude;
    this.longitude = this.longitude == null ? place.longitude : this.longitude;
    this.spatialDescription = this.spatialDescription == null ? place.spatialDescription : this.spatialDescription;
    super.embed(place);
  }
}
