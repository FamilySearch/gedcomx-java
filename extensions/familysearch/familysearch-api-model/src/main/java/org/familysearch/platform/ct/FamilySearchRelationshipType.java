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
package org.familysearch.platform.ct;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

import com.webcohesion.enunciate.metadata.qname.XmlQNameEnum;
import com.webcohesion.enunciate.metadata.qname.XmlQNameEnumValue;
import com.webcohesion.enunciate.metadata.qname.XmlUnknownQNameEnumValue;
import org.familysearch.platform.FamilySearchPlatform;
import org.gedcomx.common.URI;
import org.gedcomx.rt.ControlledVocabulary;
import org.gedcomx.rt.EnumURIMap;
import org.gedcomx.rt.GedcomxConstants;


/**
 * FamilySearch extension of the {@code org.gedcomx.types.RelationshipType} vocabulary, covering the
 * association types FamilySearch recognizes between two persons.
 * <p>
 * An association is not a distinct kind of model object: it is an ordinary
 * {@code org.gedcomx.conclusion.Relationship} living in the usual {@code Gedcomx.relationships}
 * list, identified by its {@code type} URI. Some of the URIs below are defined by the GEDCOM X
 * conclusion vocabulary and are redeclared here so that a single enum answers "is this
 * relationship an association, and which one?". Redeclaring mints no new URI — the redirected
 * constants emit byte-identical strings to their core counterparts.
 * <p>
 * When the type implies a direction, the relationship runs <em>from</em> person1 <em>to</em>
 * person2 (for example, {@link #GodparentToGodchild} has the godparent as person1).
 * <p>
 * Three things to know before using this enum:
 * <ol>
 *   <li><strong>{@link #fromQNameURI(URI)} is not null-safe.</strong> It dereferences the URI, so a
 *   relationship with no type set will throw. Core's {@code Relationship.getKnownType()} <em>is</em>
 *   null-safe, so the asymmetry surprises people. Guard the call:
 *   <pre>
 *   FamilySearchRelationshipType type = relationship.getType() == null
 *       ? null : FamilySearchRelationshipType.fromQNameURI(relationship.getType());
 *   </pre>
 *   Note also that {@code Relationship.getKnownType()} returns {@code null} — not
 *   {@code OTHER} — for an untyped relationship, so a consumer switching on it faces three
 *   outcomes: a real value, {@code OTHER}, or {@code null}.</li>
 *
 *   <li><strong>An association can resolve on both vocabularies.</strong> For a redirected type,
 *   {@code relationship.getKnownType()} returns a real core value
 *   ({@code RelationshipType.Godparent}); for a FamilySearch-only type it returns
 *   {@code RelationshipType.OTHER}. So the pre-filter "if {@code getKnownType() == OTHER}, try the
 *   FamilySearch enum" silently skips the redirected types, and a {@code switch} on
 *   {@code getKnownType()} handles some association types while missing the rest — code that looks
 *   half-correct. Always resolve against this enum directly.</li>
 *
 *   <li><strong>{@code OTHER.toQNameURI()} throws.</strong> The unknown-value constant has no map
 *   entry by design, so calling {@code toQNameURI()} on it raises
 *   {@code IllegalStateException}. Skip {@code OTHER} when iterating {@code values()}.</li>
 * </ol>
 */
@XmlQNameEnum (
  base = XmlQNameEnum.BaseType.URI
)
public enum FamilySearchRelationshipType implements ControlledVocabulary {

  /**
   * A relationship from an ancestor to a descendant. Defined by the GEDCOM X conclusion
   * vocabulary; redeclared here so associations resolve against a single enum.
   */
  @XmlQNameEnumValue ( namespace = GedcomxConstants.GEDCOMX_TYPES_NAMESPACE )
  AncestorToDescendant,

  /**
   * A relationship from an employer to an employee.
   */
  EmployerToEmployee,

  /**
   * A relationship from an enslaved person to the enslaver or slaveholder of that person. Defined
   * by the GEDCOM X conclusion vocabulary; redeclared here so associations resolve against a single
   * enum. Note the direction: person1 is the enslaved person.
   */
  @XmlQNameEnumValue ( namespace = GedcomxConstants.GEDCOMX_TYPES_NAMESPACE )
  SlaveholderToEnslaved,

  /**
   * A relationship from a godparent to a godchild. Defined by the GEDCOM X conclusion vocabulary;
   * redeclared here so associations resolve against a single enum.
   */
  @XmlQNameEnumValue ( namespace = GedcomxConstants.GEDCOMX_TYPES_NAMESPACE )
  GodparentToGodchild,

  /**
   * A relationship from the head of a household to an occupant of that household.
   */
  HeadOfHouseholdToOccupant,

  /**
   * A relationship from a master to an apprentice.
   */
  MasterToApprentice,

  /**
   * A relationship between two neighbors. Symmetric, so the constant carries a single name.
   */
  NeighborToNeighbor,

  /**
   * A relationship between two people who are related in an unspecified way. Symmetric, so the
   * constant carries a single name.
   */
  RelativeToRelative,

  /**
   * Custom
   */
  @XmlUnknownQNameEnumValue
  OTHER;

  /**
   * The subset of this vocabulary that denotes an association between two persons. Every declared
   * type except {@code OTHER} belongs to it today, but this enum extends the whole
   * {@code RelationshipType} vocabulary, so a future constant need not be an association.
   */
  public static final Set<FamilySearchRelationshipType> ASSOCIATION_TYPES = Collections.unmodifiableSet(
      EnumSet.of(AncestorToDescendant, EmployerToEmployee, SlaveholderToEnslaved, GodparentToGodchild,
              HeadOfHouseholdToOccupant, MasterToApprentice, NeighborToNeighbor, RelativeToRelative));

  private static final EnumURIMap<FamilySearchRelationshipType> URI_MAP = new EnumURIMap<FamilySearchRelationshipType>(FamilySearchRelationshipType.class, FamilySearchPlatform.NAMESPACE);

  /**
   * Whether this type denotes an association between two persons.
   *
   * @return Whether this type denotes an association.
   */
  public boolean isAssociationType() {
    return ASSOCIATION_TYPES.contains(this);
  }

  /**
   * Return the QName value for this enum.
   *
   * @return The QName value for this enum.
   */
  public URI toQNameURI() {
    return URI_MAP.toURIValue(this);
  }

  /**
   * Get the enumeration from the QName.
   *
   * @param qname The qname.
   * @return The enumeration.
   */
  public static FamilySearchRelationshipType fromQNameURI(URI qname) {
    return URI_MAP.fromURIValue(qname);
  }

}
