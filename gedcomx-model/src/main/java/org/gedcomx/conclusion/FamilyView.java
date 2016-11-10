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
import com.webcohesion.enunciate.metadata.Facet;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.gedcomx.common.ResourceReference;
import org.gedcomx.links.HypermediaEnabledData;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.rt.json.JsonElementWrapper;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * A family view, meaning up to two parents and a list of children who have those parents in common.
 * Relationships carry the canonical information for this view, and the relationships must be used
 * to get Facts (lineage types, marriages, etc.) about the relationships covered by a Family.
 * The Family data type provides a convenient way to see the typical family views without having to do
 * the calculations to derive them. There should only be one family for each unique set of parents,
 * and only one for each single-parent family with a particular parent.
 */
@XmlRootElement( name = "family" )
@Facet (GedcomxConstants.FACET_GEDCOMX_RS)
@JsonElementWrapper( name = "families" )
@XmlType( name = "FamilyView", propOrder = { "parent1", "parent2", "children"} )
@JsonInclude ( JsonInclude.Include.NON_NULL )
public class FamilyView extends HypermediaEnabledData {

  private ResourceReference parent1; // First parent, i.e., the father or husband
  private ResourceReference parent2; // Second parent, i.e., the mother or wife
  private List<ResourceReference> children; // List of children

  /**
   * A reference to a parent in the family. The name "parent1" is used only to distinguish it from
   * the other parent in this family and implies neither order nor role.
   *
   * @return A reference to a parent in the family. The name "parent1" is used only to distinguish it from
   * the other parent in this family and implies neither order nor role.
   */
  public ResourceReference getParent1() {
    return parent1;
  }

  /**
   * A reference to a parent in the family. The name "parent1" is used only to distinguish it from
   * the other parent in this family and implies neither order nor role.
   *
   * @param parent1 A reference to a parent in the family. The name "parent1" is used only to distinguish it from
   * the other parent in this family and implies neither order nor role.
   */
  public void setParent1(ResourceReference parent1) {
    this.parent1 = parent1;
  }

  /**
   * Build out this family with a reference to parent1.
   * 
   * @param parent1 Parent 1.
   * @return this.
   */
  public FamilyView parent1(ResourceReference parent1) {
    setParent1(parent1);
    return this;
  }

  /**
   * A reference to a parent in the family. The name "parent2" is used only to distinguish it from
   * the other parent in this family and implies neither order nor role.
   *
   * @return A reference to a parent in the family. The name "parent2" is used only to distinguish it from
   * the other parent in this family and implies neither order nor role.
   */
  public ResourceReference getParent2() {
    return parent2;
  }

  /**
   * A reference to a parent in the family. The name "parent2" is used only to distinguish it from
   * the other parent in this family and implies neither order nor role.
   *
   * @param parent2 A reference to a parent in the family. The name "parent2" is used only to distinguish it from
   * the other parent in this family and implies neither order nor role.
   */
  public void setParent2(ResourceReference parent2) {
    this.parent2 = parent2;
  }

  /**
   * Build out this family with a reference to parent2.
   *
   * @param parent2 Parent 2.
   * @return this.
   */
  public FamilyView parent2(ResourceReference parent2) {
    setParent2(parent2);
    return this;
  }

  /**
   * A list of references to the children of this family.
   *
   * @return A list of references to the children of this family.
   */
  @XmlElement(name="child")
  @JsonProperty("children") @org.codehaus.jackson.annotate.JsonProperty("children")
  public List<ResourceReference> getChildren() {
    return children;
  }

  /**
   * A list of references to the children of this family.
   *
   * @param children A list of references to the children of this family.
   */
  @JsonProperty("children") @org.codehaus.jackson.annotate.JsonProperty("children")
  public void setChildren(List<ResourceReference> children) {
    this.children = children;
  }

  /**
   * Build out this family by adding a child.
   *
   * @param child The child to add.
   * @return this.
   */
  public FamilyView child(ResourceReference child) {
    addChild(child);
    return this;
  }

  /**
   * Add a child.
   *
   * @param child The child to add.
   */
  public void addChild(ResourceReference child) {
    if (children == null) {
      children = new ArrayList<ResourceReference>();
    }
    children.add(child);
  }

  public void embed(FamilyView family) {
    this.parent1 = this.parent1 == null ? family.parent1 : this.parent1;
    this.parent2 = this.parent2 == null ? family.parent2 : this.parent2;
    if (family.children != null) {
      if (children == null) {
        children = new ArrayList<ResourceReference>();
      }
      children.addAll(family.children);
    }
  }
}
