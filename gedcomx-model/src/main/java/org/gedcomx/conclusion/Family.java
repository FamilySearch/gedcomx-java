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
import org.gedcomx.common.Attribution;
import org.gedcomx.common.Note;
import org.gedcomx.common.ResourceReference;
import org.gedcomx.common.URI;
import org.gedcomx.links.Link;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.rt.GedcomxModelVisitor;
import org.gedcomx.rt.json.JsonElementWrapper;
import org.gedcomx.source.SourceDescription;
import org.gedcomx.source.SourceReference;
import org.gedcomx.types.ConfidenceLevel;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * A family view, meaning up to two parents and a list of children who have those parents in common.
 * Relationships carry the canonical information for this view, and the relationships must be used
 * to get Facts (lineage types, marriages, etc.) about the relationships covered by a Family.
 * The Family class provides a convenient way to see the typical family views without having to do
 * the calculations to derive them. There should only be one family for each unique set of parents,
 * and only one for each single-parent family with a particular parent. While in theory a Family
 * object could
 */
@XmlRootElement
@Facet (name = GedcomxConstants.FACET_GEDCOMX_RS)
@JsonElementWrapper( name = "families" )
@XmlType( name = "Family", propOrder = { "parent1", "parent2", "children"} )
public class Family extends Conclusion {

  private ResourceReference parent1; // First parent, i.e., the father or husband
  private ResourceReference parent2; // Second parent, i.e., the mother or wife
  private List<ResourceReference> children; // List of children
  private Boolean includesKnownChildren; // Flag for whether this family includes the known children.
  private Boolean isPreferred; // Flag for whether this family has the preferred parents or spouse for the main person.

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
  public Family parent1(ResourceReference parent1) {
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
  public Family parent2(ResourceReference parent2) {
    setParent2(parent2);
    return this;
  }

  /**
   * A list of references to the children of this family.
   *
   * @return A list of references to the children of this family.
   */
  @XmlElement(name="child")
  @JsonProperty("children")
  @JsonName("children")
  public List<ResourceReference> getChildren() {
    return children;
  }

  /**
   * A list of references to the children of this family.
   *
   * @param children A list of references to the children of this family.
   */
  @JsonProperty("children")
  public void setChildren(List<ResourceReference> children) {
    this.children = children;
  }

  /**
   * Build out this family by adding a child.
   *
   * @param child The child to add.
   * @return this.
   */
  public Family child(ResourceReference child) {
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

  @Override
  public Family id(String id) {
    return (Family) super.id(id);
  }

  @Override
  public Family link(String rel, URI href) {
    return (Family) super.link(rel, href);
  }

  @Override
  public Family link(Link link) {
    return (Family) super.link(link);
  }

  @Override
  public Family lang(String lang) {
    return (Family) super.lang(lang);
  }

  @Override
  public Family confidence(URI confidence) {
    return (Family) super.confidence(confidence);
  }

  @Override
  public Family confidence(ConfidenceLevel confidence) {
    return (Family) super.confidence(confidence);
  }

  @Override
  public Family source(SourceReference sourceReference) {
    return (Family) super.source(sourceReference);
  }

  @Override
  public Family source(SourceDescription source) {
    return (Family) super.source(source);
  }

  @Override
  public Family note(Note note) {
    return (Family) super.note(note);
  }

  @Override
  public Family analysis(ResourceReference analysis) {
    return (Family) super.analysis(analysis);
  }

  @Override
  public Family attribution(Attribution attribution) {
    return (Family) super.attribution(attribution);
  }

  @Override
  public Family analysis(Document analysis) {
    return (Family) super.analysis(analysis);
  }

  @Override
  public Family analysis(URI analysis) {
    return (Family) super.analysis(analysis);
  }

  /**
   * Flag indicating whether the list of known children are included in this family view.
   * If false, then there may be only one child (for the main person), but no siblings included, for example.
   * Note that even when false, there may not actually be any (additional) known children for the family. This
   * flag just indicates that even if they are more children, they weren't included.
   * @return true if this family object contains all of the known children, false or null otherwise.
   */
  @XmlAttribute
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
  @XmlAttribute
  public Boolean getPreferred() {
    return isPreferred;
  }

  public void setPreferred(Boolean isPreferred) {
    this.isPreferred = isPreferred;
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
    this.parent1 = this.parent1 == null ? family.parent1 : this.parent1;
    this.parent2 = this.parent2 == null ? family.parent2 : this.parent2;
    if (family.children != null) {
      if (children == null) {
        children = new ArrayList<ResourceReference>();
      }
      children.addAll(family.children);
    }
    this.includesKnownChildren = this.includesKnownChildren == null ? family.includesKnownChildren : this.includesKnownChildren;
    this.isPreferred = this.isPreferred == null ? family.isPreferred : this.isPreferred;
    super.embed(family);
  }
}
