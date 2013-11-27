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
import org.gedcomx.common.Qualifier;
import org.gedcomx.common.URI;
import org.gedcomx.records.Field;
import org.gedcomx.records.HasFields;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.rt.GedcomxModelVisitor;
import org.gedcomx.rt.json.JsonElementWrapper;
import org.gedcomx.types.FactType;

import javax.xml.bind.annotation.*;
import java.util.LinkedList;
import java.util.List;

/**
 * A conclusion about a fact applicable to a person or relationship.
 */
@XmlType ( name = "Fact", propOrder = { "date", "place", "value", "qualifiers", "fields"})
@XmlRootElement
@JsonElementWrapper ( name = "facts" )
public class Fact extends Conclusion implements HasDateAndPlace, HasFields {

  private URI type;
  private Date date;
  private PlaceReference place;
  private String value;
  private List<Qualifier> qualifiers;
  private List<Field> fields;
  private Boolean primary;

  /**
   * Create a fact.
   */
  public Fact() {
  }

  /**
   * Create a fact with the passed in type and values.
   *
   * @param factType the fact type.
   * @param value The value as supplied by the user.
   */
  public Fact(FactType factType, String value) {
    setKnownType(factType);
    setValue(value);
  }

  /**
   * Create a date/place fact with the passed in type and values.
   *
   * @param factType the fact type.
   * @param date The date of applicability of this fact.
   * @param place The place of applicability of this fact.
   * @param value The value as supplied by the user.
   */
  public Fact(FactType factType, Date date, PlaceReference place, String value) {
    setKnownType(factType);
    setDate(date);
    setPlace( place );
    setValue(value);
  }

  /**
   * The type of the fact.
   *
   * @return The type of the fact.
   */
  @XmlAttribute
  @XmlQNameEnumRef (FactType.class)
  public URI getType() {
    return type;
  }

  /**
   * The type of the fact.
   *
   * @param type The type of the fact.
   */
  public void setType(URI type) {
    this.type = type;
  }

  /**
   * The enum referencing the known type of the fact, or {@link org.gedcomx.types.FactType#OTHER} if not known.
   *
   * @return The enum referencing the known type of the fact, or {@link org.gedcomx.types.FactType#OTHER} if not known.
   */
  @XmlTransient
  @JsonIgnore
  public org.gedcomx.types.FactType getKnownType() {
    return getType() == null ? null : FactType.fromQNameURI(getType());
  }

  /**
   * Set the type of this fact from a known enumeration of fact types.
   *
   * @param knownType the fact type.
   */
  @JsonIgnore
  public void setKnownType(org.gedcomx.types.FactType knownType) {
    setType(knownType == null ? null : URI.create(org.codehaus.enunciate.XmlQNameEnumUtil.toURIValue(knownType)));
  }

  /**
   * Indicator of whether this fact is the "primary" fact of the record from which the subject was extracted. Applicable
   * only to extracted persons/relationships. The meaning of this flag outside the scope of an extracted subject is undefined.
   *
   * @return Whether this fact is the primary fact of the record from which the subject was extracted.
   */
  @XmlAttribute
  @Facet( name = GedcomxConstants.FACET_GEDCOMX_RECORD)
  public Boolean getPrimary() {
    return primary;
  }

  /**
   * Indicator of whether this fact is the "primary" fact of the record from which the subject was extracted. Applicable
   * only to extracted persons/relationships. The meaning of this flag outside the scope of an extracted subject is undefined.
   *
   * @param primary Whether this fact is the primary fact of the record from which the subject was extracted.
   */
  public void setPrimary(Boolean primary) {
    this.primary = primary;
  }

  /**
   * The date of applicability of this fact.
   *
   * @return The date of applicability of this fact.
   */
  public Date getDate() {
    return date;
  }

  /**
   * The date of applicability of this fact.
   *
   * @param date The date of applicability of this fact.
   */
  public void setDate(Date date) {
    this.date = date;
  }

  /**
   * The place of applicability of this fact.
   *
   * @return The place of applicability of this fact.
   */
  public PlaceReference getPlace() {
    return place;
  }

  /**
   * The place of applicability of this fact.
   *
   * @param place The place of applicability of this fact.
   */
  public void setPlace(PlaceReference place) {
    this.place = place;
  }

  /**
   * The value as supplied by the user.
   *
   * @return The value as supplied by the user.
   */
  public String getValue() {
    return value;
  }

  /**
   * The value as supplied by the user.
   *
   * @param value The value as supplied by the user.
   */
  public void setValue(String value) {
    this.value = value;
  }

  /**
   * The qualifiers associated with this fact.
   *
   * @return The qualifiers associated with this fact.
   */
  @XmlElement (name = "qualifier")
  @JsonName ("qualifiers")
  @JsonProperty ("qualifiers")
  @Facet ( name = GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  public List<Qualifier> getQualifiers() {
    return qualifiers;
  }

  /**
   * Set the qualifiers associated with this fact.
   *
   * @param qualifiers qualifiers to associate with this fact.
   */
  @JsonProperty ("qualifiers")
  public void setQualifiers(List<Qualifier> qualifiers) {
    this.qualifiers = qualifiers;
  }

  /**
   * Get the fields being used as evidence.
   *
   * @return The references to the record fields being used as evidence.
   */
  @XmlElement( name = "field" )
  @JsonProperty( "fields" )
  @JsonName( "fields" )
  @Facet ( name = GedcomxConstants.FACET_GEDCOMX_RECORD )
  public List<Field> getFields() {
    return fields;
  }

  /**
   * Set the list of fields being used as evidence.
   *
   * @param fields - List of fields
   */
  @JsonProperty( "fields" )
  public void setFields(List<Field> fields) {
    this.fields = fields;
  }

  /**
   * Add a reference to the record field values being used as evidence.
   *
   * @param field The field to be added.
   */
  public void addField(Field field) {
    if (field != null) {
      if (fields == null) {
        fields = new LinkedList<Field>();
      }
      fields.add(field);
    }
  }

  @Override
  public String toString() {
    return "type=" + getKnownType() + ",value=" + getValue() + ",date=" + getDate() + ",place=" + getPlace();
  }

  /**
   * Accept a visitor.
   *
   * @param visitor The visitor.
   */
  public void accept(GedcomxModelVisitor visitor) {
    visitor.visitFact(this);
  }
}
