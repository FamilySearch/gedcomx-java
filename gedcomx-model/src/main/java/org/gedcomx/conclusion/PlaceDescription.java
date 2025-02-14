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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.webcohesion.enunciate.metadata.Facet;
import org.gedcomx.common.*;
import org.gedcomx.links.Link;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.rt.GedcomxModelVisitor;
import org.gedcomx.source.SourceDescription;
import org.gedcomx.source.SourceReference;
import org.gedcomx.types.ConfidenceLevel;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;


/**
 * A PlaceDescription is used to describe the details of a place in terms of its name
 * and possibly its type, time period, and/or a geospatial description -- a description
 * of a place as a snapshot in time.
 */
@XmlType ( name = "PlaceDescription", propOrder = { "names", "temporalDescription", "latitude", "longitude", "spatialDescription", "place", "jurisdiction", "displayExtension" } )
@JsonInclude ( JsonInclude.Include.NON_NULL )
@Schema(description = "A PlaceDescription is used to describe the details of a place in terms of its name and possibly its type, time period, and/or a " +
    "geospatial description -- a description of a place as a snapshot in time.")
public class PlaceDescription extends Subject {

  @Schema(description = "An ordered list of standardized (or normalized), fully-qualified (in terms of what is known of the applicable jurisdictional hierarchy) " +
      "names for this place that are applicable to this description of this place.")
  private List<TextValue> names;

  @Schema(description = "An implementation-specific uniform resource identifier (URI) used to identify the type of a place (e.g., address, city, county, province, state, country, etc.).")
  private URI type;

  @Schema(description = "A description of the time period to which this place description is relevant.")
  private Date temporalDescription;

  @Schema(description = "Degrees north or south of the Equator (0.0 degrees).   Values range from −90.0 degrees (south) to 90.0 degrees (north).")
  private Double latitude;

  @Schema(description = "Angular distance in degrees, relative to the Prime Meridian.   Values range from −180.0 degrees (west of the Meridian) to 180.0 degrees (east of the Meridian).")
  private Double longitude;

  @Schema(description = "A reference to a geospatial description of this place.")
  private ResourceReference place;

  @Schema(description = "A reference to a geospatial description of this place.")
  private ResourceReference spatialDescription;

  @Schema(description = "A reference to a description of the jurisdiction this place.")
  private ResourceReference jurisdiction;

  @Schema(description = "Display properties for the place. Display properties are not specified by GEDCOM X core, but as extension elements by GEDCOM X RS.")
  private PlaceDisplayProperties display;

  public PlaceDescription() {
  }

  public PlaceDescription(PlaceDescription copy) {
    super(copy);
    this.names = copy.names == null ? null : new ArrayList<>(copy.names.stream().map(TextValue::new).toList());
    this.type = copy.type;
    this.temporalDescription = copy.temporalDescription == null ? null : new Date(copy.temporalDescription);
    this.latitude = copy.latitude;
    this.longitude = copy.longitude;
    this.place = copy.place == null ? null : new ResourceReference(copy.place);
    this.spatialDescription = copy.spatialDescription == null ? null : new ResourceReference(copy.spatialDescription);
    this.jurisdiction = copy.jurisdiction == null ? null : new ResourceReference(copy.jurisdiction);
    this.display = copy.display == null ? null : new PlaceDisplayProperties(copy.display);
  }

  @Override
  public PlaceDescription id(String id) {
    return (PlaceDescription) super.id(id);
  }

  @Override
  public PlaceDescription extensionElement(Object element) {
    return (PlaceDescription) super.extensionElement(element);
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
   * Create a stream for the names.
   *
   * @return a stream for the names.
   */
  public Stream<TextValue> names() {
    return this.names == null ? Stream.empty() : this.names.stream();
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
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
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
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
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
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
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
  @Facet ( GedcomxConstants.FACET_GEDCOMX_RS )
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final PlaceDescription that = (PlaceDescription) o;
    return Objects.equals(display, that.display) &&
           Objects.equals(jurisdiction, that.jurisdiction) &&
           Objects.equals(latitude, that.latitude) &&
           Objects.equals(longitude, that.longitude) &&
           Objects.equals(names, that.names) &&
           Objects.equals(place, that.place) &&
           Objects.equals(spatialDescription, that.spatialDescription) &&
           Objects.equals(temporalDescription, that.temporalDescription) &&
           Objects.equals(type, that.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(display, jurisdiction, latitude, longitude, names, place, spatialDescription, temporalDescription, type);
  }
}
