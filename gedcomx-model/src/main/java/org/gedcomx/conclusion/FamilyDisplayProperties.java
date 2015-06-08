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

import org.gedcomx.rt.json.JsonElementWrapper;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Display properties for a family.
 */
@XmlRootElement
@JsonElementWrapper( name = "display" )
@XmlType( name = "FamilyDisplayProperties", propOrder = { "includesKnownChildren", "preferred"} )
public class FamilyDisplayProperties {
  private Boolean includesKnownChildren; // Flag for whether this family includes the known children.
  private Boolean isPreferred; // Flag for whether this family has the preferred parents or spouse for the main person.

  public FamilyDisplayProperties() {
  }

  public FamilyDisplayProperties(Boolean includesKnownChildren, Boolean isPreferred) {
    this.includesKnownChildren = includesKnownChildren;
    this.isPreferred = isPreferred;
  }

  /**
   * Flag indicating whether the list of known children are included in this family view.
   * If false, then there may be only one child (for the main person), but no siblings included, for example.
   * Note that even when false, there may not actually be any (additional) known children for the family. This
   * flag just indicates that even if they are more children, they weren't included.
   * @return true if this family object contains all of the known children, false or null otherwise.
   */
  public Boolean getIncludesKnownChildren() {
    return includesKnownChildren;
  }

  public void setIncludesKnownChildren(Boolean includesKnownChildren) {
    this.includesKnownChildren = includesKnownChildren;
  }

  /**
   * Flag indicating whether this family is the "preferred" one in the list of families as spouses or
   *   families as parents for the main person in a GedcomX document. Note that this preference is likely
   *   a per-user setting.
   * @return true if this family is the preferred set of parents or spouse, depending on whether the main person
   *   is a child or a parent in the family, respectively.
   */
  public Boolean getPreferred() {
    return isPreferred;
  }

  public void setPreferred(Boolean isPreferred) {
    this.isPreferred = isPreferred;
  }
}
