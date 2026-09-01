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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.familysearch.platform.rt.FamilySearchPlatformModelVisitor;
import org.gedcomx.conclusion.Relationship;
import org.gedcomx.rt.GedcomxModelVisitor;
import org.gedcomx.rt.json.JsonElementWrapper;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlType;

/**
 * The FamilySearch-proprietary model for an association between two persons.
 * The type vocabulary for an Association is {@link AssociationType}, not {@code RelationshipType}.
 * An Association belongs in {@code FamilySearchPlatform.associations}, never in {@code Gedcomx.relationships}.
 */
@XmlRootElement
@JsonElementWrapper( name = "associations" )
@XmlType ( name = "Association" )
@JsonInclude ( JsonInclude.Include.NON_NULL )
@Schema(description = "The FamilySearch-proprietary model for an association between two persons.")
public class Association extends Relationship {

  public Association() {
  }

  public Association(Association copy) {
    super(copy);
  }

  /**
   * The enum referencing the known type of the association, or {@link AssociationType#OTHER} if not known.
   *
   * @return The enum referencing the known type of the association, or {@link AssociationType#OTHER} if not known.
   */
  @XmlTransient
  @JsonIgnore
  public AssociationType getKnownAssociationType() {
    return getType() == null ? null : AssociationType.fromQNameURI(getType());
  }

  /**
   * Set the association type from a known enumeration of association types.
   *
   * @param type The association type.
   */
  @JsonIgnore
  public void setKnownAssociationType(AssociationType type) {
    setType(type == null ? null : type.toQNameURI());
  }

  public void accept(FamilySearchPlatformModelVisitor visitor) {
    visitor.visitAssociation(this);
  }

  @Override
  public void accept(GedcomxModelVisitor visitor) {
    if (visitor instanceof FamilySearchPlatformModelVisitor) {
      ((FamilySearchPlatformModelVisitor) visitor).visitAssociation(this);
    }
    else {
      super.accept(visitor);
    }
  }

}
