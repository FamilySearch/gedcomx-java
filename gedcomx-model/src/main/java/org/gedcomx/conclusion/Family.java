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
import org.gedcomx.common.ResourceReference;
import org.gedcomx.links.HypermediaEnabledData;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.rt.GedcomxModelVisitor;
import org.gedcomx.rt.json.JsonElementWrapper;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>A family view precomputed based on the underlying relationships, in order to simplify display and other logic.</p>
 * <p>At least one parent must be specified. (If only one parent is specified, then nothing is being said about the other one,
 *   not even that all of the children had the same 'other parent'.)</p>
 * <p>A family view does not necessarily contain all of the known children. For example, a GedcomX document might contain
 *   relationships to a person's parents but not include references to their siblings, in which case a Family view
 *   would contain just one child even though it is possible that there are more. The display property
 *   'includesKnownChildren' is used to indicate whether the known children have been included. If false, then one
 *   would have to look to see if there are any more children in the family.</p>
 * <p>Facts on children and couples are still found in the corresponding relationships. Convenience methods are provided
 *   to help look up relationships corresponding to a particular child.</p>
 */
@XmlRootElement
@JsonElementWrapper( name = "families" )
@XmlType( name = "Family", propOrder = { "parent1", "parent2", "children", "displayExtension"} )
public class Family extends HypermediaEnabledData {
  private ResourceReference parent1; // First parent, i.e., the father or husband
  private ResourceReference parent2; // Second parent, i.e., the mother or wife
  private List<ResourceReference> children; // List of children
  private FamilyDisplayProperties display;

  /**
   * Get the first parent (i.e., the one with the role of "father" or "husband" in the family).
   * Note that either parent might be null, indicating that nothing is known about
   * @return the first parent
   */
  public ResourceReference getParent1() {
    return parent1;
  }

  /**
   * Set the first parent (i.e., the one with the role of "father" or "husband" in the family)
   * @param parent1 - First parent
   */
  public void setParent1(ResourceReference parent1) {
    this.parent1 = parent1;
  }

  /**
   * Get the second parent (i.e., the one with the role of "mother" or "wife" in the family)
   * @return the second parent
   */
  public ResourceReference getParent2() {
    return parent2;
  }

  /**
   * Set the second parent (i.e., the one with the role of "mother" or "wife" in the family)
   * @param parent2 - Second parent
   */
  public void setParent2(ResourceReference parent2) {
    this.parent2 = parent2;
  }

  /**
   * Get the list of children for the given set of parents. Note that in some data profiles, not all of the
   *   children are included. For example, when drawing a pedigree, a family might contain just one child
   *   but not the siblings. The flag use getDisplayExtension().includesAllKnownChildren() to determine
   *   if there is a possibility of additional children for this family.
   * @return List of children for the given set of parents.
   */
  @XmlElement(name="child")
  @JsonProperty("children")
  @JsonName("children")
  public List<ResourceReference> getChildren() {
    return children;
  }

  @JsonProperty("children")
  public void setChildren(List<ResourceReference> children) {
    this.children = children;
  }

  /**
   * Add a child to the list of children for this set of parents.
   * @param child - ResourceReference to the child being added.
   */
  public void addChild(ResourceReference child) {
    if (children == null) {
      children = new ArrayList<ResourceReference>();
    }
    children.add(child);
  }

  /**
   * Display properties for the person. Display properties are not specified by GEDCOM X core, but as extension elements by GEDCOM X RS.
   *
   * @return Display properties for the person. Display properties are not specified by GEDCOM X core, but as extension elements by GEDCOM X RS.
   */
  @XmlElement(name = "display")
  @JsonProperty("display")
  @Facet( name = GedcomxConstants.FACET_GEDCOMX_RS )
  public FamilyDisplayProperties getDisplayExtension() {
    return display;
  }

  /**
   * Display properties for the person. Display properties are not specified by GEDCOM X core, but as extension elements by GEDCOM X RS.
   *
   * @param display Display properties for the person. Display properties are not specified by GEDCOM X core, but as extension elements by GEDCOM X RS.
   */
  @JsonProperty("display")
  public void setDisplayExtension(FamilyDisplayProperties display) {
    this.display = display;
  }

  /**
   * Build out this family with a display exension.
   *
   * @param display the display.
   * @return this
   */
  public Family displayExtension(FamilyDisplayProperties display) {
    setDisplayExtension(display);
    return this;
  }

  /**
   * Accept a visitor.
   *
   * @param visitor The visitor to accept.
   */
  public void accept(GedcomxModelVisitor visitor) {
    visitor.visitFamily(this);
  }

  public void embed(Family family) {
    if (family.children != null) {
      if (children == null) {
        children = new ArrayList<ResourceReference>();
      }
      children.addAll(family.children);
    }
    super.embed(family);
  }
}
