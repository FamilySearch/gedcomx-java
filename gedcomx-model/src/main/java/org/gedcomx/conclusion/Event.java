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
import org.codehaus.enunciate.qname.XmlQNameEnumRef;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.gedcomx.common.URI;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.rt.GedcomxModelVisitor;
import org.gedcomx.rt.json.JsonElementWrapper;
import org.gedcomx.types.EventType;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * A historical event.
 *
 * @author Ryan Heaton
 */
@XmlRootElement
@JsonElementWrapper (name = "events")
@XmlType ( name = "Event", propOrder = { "date", "place", "roles" } )
@Facet ( name = GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
public class Event extends Subject implements HasDateAndPlace {

  private URI type;
  private Date date;
  private PlaceReference place;
  private List<EventRole> roles;

  /**
   * Create an event.
   */
  public Event() {
  }

  /**
   * Create an event with the passed in type and values.
   *
   * @param EventType the event type.
   */
  public Event(EventType EventType) {
    setKnownType(EventType);
  }

  /**
   * Create a date/place event with the passed in type and values.
   *
   * @param EventType the event type.
   * @param date The date of applicability of this event.
   * @param place The place of applicability of this event.
   */
  public Event(EventType EventType, Date date, PlaceReference place) {
    setKnownType(EventType);
    setDate(date);
    setPlace( place );
  }

  /**
   * The type of the event.
   *
   * @return The type of the event.
   */
  @XmlAttribute
  @XmlQNameEnumRef (org.gedcomx.types.EventType.class)
  public URI getType() {
    return type;
  }

  /**
   * The type of the event.
   *
   * @param type The type of the event.
   */
  public void setType(URI type) {
    this.type = type;
  }

  /**
   * The enum referencing the known type of the event, or {@link org.gedcomx.types.EventType#OTHER} if not known.
   *
   * @return The enum referencing the known type of the event, or {@link org.gedcomx.types.EventType#OTHER} if not known.
   */
  @XmlTransient
  @JsonIgnore
  public org.gedcomx.types.EventType getKnownType() {
    return getType() == null ? null : EventType.fromQNameURI(getType());
  }

  /**
   * Set the type of this event from a known enumeration of event types.
   *
   * @param knownType the event type.
   */
  @JsonIgnore
  public void setKnownType(org.gedcomx.types.EventType knownType) {
    setType(knownType == null ? null : URI.create(org.codehaus.enunciate.XmlQNameEnumUtil.toURIValue(knownType)));
  }

  /**
   * The date of this event.
   *
   * @return The date of this event.
   */
  @Override
  public Date getDate() {
    return date;
  }

  /**
   * The date of this event.
   *
   * @param date The date of this event.
   */
  @Override
  public void setDate(Date date) {
    this.date = date;
  }

  /**
   * The place of this event.
   *
   * @return The place of this event.
   */
  @Override
  public PlaceReference getPlace() {
    return place;
  }

  /**
   * The place of this event.
   *
   * @param place The place of this event.
   */
  @Override
  public void setPlace(PlaceReference place) {
    this.place = place;
  }

  /**
   * The roles played in this event.
   *
   * @return The roles played in this event.
   */
  @XmlElement (name="role")
  @JsonProperty ("roles")
  @JsonName ("roles")
  public List<EventRole> getRoles() {
    return roles;
  }

  /**
   * The roles played in this event.
   *
   * @param roles The roles played in this event.
   */
  public void setRoles(List<EventRole> roles) {
    this.roles = roles;
  }

  /**
   * Add a role to the list of roles played in this event.
   *
   * @param role The role to be added.
   */
  public void addRole(EventRole role) {
    if (role != null) {
      if (roles == null) {
        roles = new LinkedList<EventRole>();
      }
      roles.add(role);
    }
  }

  /**
   * Accept a visitor.
   *
   * @param visitor The visitor.
   */
  public void accept(GedcomxModelVisitor visitor) {
    visitor.visitEvent(this);
  }

  /**
   * Embed another event.
   *
   * @param event The event to embed.
   */
  public void embed(Event event) {
    this.type = this.type == null ? event.type : this.type;
    this.date = this.date == null ? event.date : this.date;
    this.place = this.place == null ? event.place : this.place;
    if (event.roles != null) {
      this.roles = this.roles == null ? new ArrayList<EventRole>() : this.roles;
      this.roles.addAll(event.roles);
    }
    super.embed(event);
  }
}
