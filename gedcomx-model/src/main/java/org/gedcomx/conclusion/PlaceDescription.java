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
package org.gedcomx.conclusion;

import org.codehaus.enunciate.Facet;
import org.codehaus.enunciate.json.JsonName;
import org.codehaus.jackson.annotate.JsonProperty;
import org.gedcomx.common.*;
import org.gedcomx.links.Link;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.rt.GedcomxModelVisitor;
import org.gedcomx.source.SourceDescription;
import org.gedcomx.source.SourceReference;
import org.gedcomx.types.ConfidenceLevel;

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
@XmlType ( name = "PlaceDescription", propOrder = { "names", "temporalDescription", "latitude", "longitude", "spatialDescription", "place", "jurisdiction", "displayExtension" } )
public class PlaceDescription extends Subject {

  private List<TextValue> names;
  private URI type;
  private Date temporalDescription;
  private Double latitude;
  private Double longitude;
  private ResourceReference place;
  private ResourceReference spatialDescription;
  private ResourceReference jurisdiction;
  private PlaceDisplayProperties display;

  @Override
  public PlaceDescription id(String id) {
    return (PlaceDescription) super.id(id);
  }

  @Override
  public PlaceDescription link(String rel, URI href) {
    return (PlaceDescription) super.link(rel, href);
  }

  @Override
  public PlaceDescription link(Link link) {
    return (PlaceDescription) super.link(link);
  }

  @Override
  public PlaceDescription lang(String lang) {
    return (PlaceDescription) super.lang(lang);
  }

  @Override
  public PlaceDescription confidence(URI confidence) {
    return (PlaceDescription) super.confidence(confidence);
  }

  @Override
  public PlaceDescription confidence(ConfidenceLevel confidence) {
    return (PlaceDescription) super.confidence(confidence);
  }

  @Override
  public PlaceDescription source(SourceReference sourceReference) {
    return (PlaceDescription) super.source(sourceReference);
  }

  @Override
  public PlaceDescription source(SourceDescription source) {
    return (PlaceDescription) super.source(source);
  }

  @Override
  public PlaceDescription note(Note note) {
    return (PlaceDescription) super.note(note);
  }

  @Override
  public PlaceDescription analysis(ResourceReference analysis) {
    return (PlaceDescription) super.analysis(analysis);
  }

  @Override
  public PlaceDescription attribution(Attribution attribution) {
    return (PlaceDescription) super.attribution(attribution);
  }

  @Override
  public PlaceDescription analysis(Document analysis) {
    return (PlaceDescription) super.analysis(analysis);
  }

  @Override
  public PlaceDescription analysis(URI analysis) {
    return (PlaceDescription) super.analysis(analysis);
  }

  @Override
  public PlaceDescription extracted(Boolean extracted) {
    return (PlaceDescription) super.extracted(extracted);
  }

  @Override
  public PlaceDescription identifier(Identifier identifier) {
    return (PlaceDescription) super.identifier(identifier);
  }

  @Override
  public PlaceDescription evidence(EvidenceReference evidence) {
    return (PlaceDescription) super.evidence(evidence);
  }

  public PlaceDescription evidence(PlaceDescription evidence) {
    if (evidence.getId() == null) {
      throw new IllegalArgumentException("Unable to add event as evidence: no id.");
    }
    return (PlaceDescription) super.evidence(new EvidenceReference(URI.create("#" + evidence.getId())));
  }

  @Override
  public PlaceDescription media(SourceReference media) {
    return (PlaceDescription) super.media(media);
  }

  @Override
  public PlaceDescription media(SourceDescription media) {
    return (PlaceDescription) super.media(media);
  }

  @Override
  public PlaceDescription sortKey(String sortKey) {
    return (PlaceDescription) super.sortKey(sortKey);
  }

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
   * Build out this description with a name.
   *
   * @param name The name.
   * @return this.
   */
  public PlaceDescription name(TextValue name) {
    addName(name);
    return this;
  }

  /**
   * Build out this description with a name.
   *
   * @param name The name.
   * @return this.
   */
  public PlaceDescription name(String name) {
    addName(new TextValue(name));
    return this;
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
   * Build out this place description with a type.
   * @param type The type.
   * @return this.
   */
  public PlaceDescription type(URI type) {
    setType(type);
    return this;
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
   * Build out this place description with a temporal description.
   * @param temporalDescription the temporal description.
   * @return this.
   */
  public PlaceDescription temporalDescription(Date temporalDescription) {
    setTemporalDescription(temporalDescription);
    return this;
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
   * Build out this place description with a latitude.
   *
   * @param latitude The latitude.
   * @return this.
   */
  public PlaceDescription latitude(Double latitude) {
    setLatitude(latitude);
    return this;
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
   * Build out this place description with a longitude.
   * @param longitude The longitude.
   * @return this.
   */
  public PlaceDescription longitude(Double longitude) {
    setLongitude(longitude);
    return this;
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
   * Build out this place description with a spacial description.
   * @param spatialDescription The spatial description.
   * @return this
   */
  public PlaceDescription spatialDescription(ResourceReference spatialDescription) {
    setSpatialDescription(spatialDescription);
    return this;
  }

  /**
   * A reference to a description of the jurisdiction this place.
   *
   * @return A reference to a description of the jurisdiction this place.
   */
  public ResourceReference getJurisdiction() {
    return jurisdiction;
  }

  /**
   *  A reference to a description of the jurisdiction this place.
   *
   * @param jurisdiction A reference to a description of the jurisdiction this place.
   */
  public void setJurisdiction(ResourceReference jurisdiction) {
    this.jurisdiction = jurisdiction;
  }

  /**
   * Build out this place description with a jurisdiction.
   * @param jurisdiction The reference to the jurisdiction.
   * @return this
   */
  public PlaceDescription jurisdiction(ResourceReference jurisdiction) {
    setJurisdiction(jurisdiction);
    return this;
  }

  /**
   * A reference to the place being described.
   *
   * @return A reference to the place being described.
   */
  public ResourceReference getPlace() {
    return place;
  }

  /**
   *  A reference to the place being described.
   *
   * @param place A reference to the place being described.
   */
  public void setPlace(ResourceReference place) {
    this.place = place;
  }

  /**
   * Build out this place description with a place.
   *
   * @param place The reference to the place.
   * @return this
   */
  public PlaceDescription place(ResourceReference place) {
    setPlace(place);
    return this;
  }

  /**
   * Display properties for the place. Display properties are not specified by GEDCOM X core, but as extension elements by GEDCOM X RS.
   *
   * @return Display properties for the place. Display properties are not specified by GEDCOM X core, but as extension elements by GEDCOM X RS.
   */
  @XmlElement(name = "display")
  @JsonProperty("display")
  @Facet ( name = GedcomxConstants.FACET_GEDCOMX_RS )
  public PlaceDisplayProperties getDisplayExtension() {
    return display;
  }

  /**
   * Display properties for the place. Display properties are not specified by GEDCOM X core, but as extension elements by GEDCOM X RS.
   *
   * @param display Display properties for the place. Display properties are not specified by GEDCOM X core, but as extension elements by GEDCOM X RS.
   */
  @JsonProperty("display")
  public void setDisplayExtension(PlaceDisplayProperties display) {
    this.display = display;
  }

  /**
   * Build out this place with a display exension.
   *
   * @param display the display.
   * @return this
   */
  public PlaceDescription displayExtension(PlaceDisplayProperties display) {
    setDisplayExtension(display);
    return this;
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
    this.jurisdiction = this.jurisdiction == null ? place.jurisdiction : this.jurisdiction;
    if (this.display != null && place.display != null) {
      this.display.embed(place.display);
    }
    super.embed(place);
  }
}
