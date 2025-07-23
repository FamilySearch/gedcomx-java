/*
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

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

import org.gedcomx.common.Attribution;
import org.gedcomx.common.ResourceReference;
import org.gedcomx.links.HypermediaEnabledData;
import org.gedcomx.rt.json.JsonElementWrapper;

/**
 * A reference to another person in a different FamilySearch tree that may be a representation of the same individual.
 */
@XmlRootElement(name = "tree-person-reference")
@JsonElementWrapper(name = "tree-person-references")
@XmlType( name = "TreePersonReference" )
@JsonInclude( JsonInclude.Include.NON_NULL )
@Schema(description = "A reference to another person in a different FamilySearch tree that may be a representation of the same individual.")
public class TreePersonReference extends HypermediaEnabledData {
  @Schema(description = "The person referenced.")
  private ResourceReference treePerson;

  @Schema(description = "The tree containing the person referenced.")
  private ResourceReference tree;

  @Schema(description = "The attribution metadata for this tree person reference.")
  private Attribution attribution;

  /**
   * Gets the reference to the person in the tree.
   *
   * @return the referenced person
   */
  public ResourceReference getTreePerson() {
    return treePerson;
  }

  /**
   * Sets the reference to the person in the tree.
   *
   * @param treePerson the referenced person
   */
  public void setTreePerson(ResourceReference treePerson) {
    this.treePerson = treePerson;
  }

  /**
   * Gets the reference to the tree containing the person.
   *
   * @return the referenced tree
   */
  public ResourceReference getTree() {
    return tree;
  }

  /**
   * Sets the reference to the tree containing the person.
   *
   * @param tree the referenced tree
   */
  public void setTree(ResourceReference tree) {
    this.tree = tree;
  }

  /**
   * Gets the attribution metadata for this tree person reference.
   *
   * @return the attribution metadata
   */
  public Attribution getAttribution() {
    return attribution;
  }

  /**
   * Sets the attribution metadata for this tree person reference.
   *
   * @param attribution the attribution metadata
   */
  public void setAttribution(Attribution attribution) {
    this.attribution = attribution;
  }

  /**
   * Build up this tree person reference with attribution.
   *
   * @param attribution The attribution.
   * @return this.
   */
  public TreePersonReference attribution(Attribution attribution) {
    setAttribution(attribution);
    return this;
  }

  /**
   * Build up this tree person reference with tree person reference.
   *
   * @param treePerson The tree person referenced by this object.
   * @return this.
   */
  public TreePersonReference treePerson(ResourceReference treePerson) {
    if (treePerson != null && (treePerson.getResourceId() != null || treePerson.getResource() != null)) {
      setTreePerson(treePerson);
    }
    return this;
  }

  /**
   * Build up this tree person reference with a tree reference.
   *
   * @param tree The tree containing the person in this reference.
   * @return this.
   */
  public TreePersonReference tree(ResourceReference tree) {
    if (tree != null && (tree.getResourceId() != null || tree.getResource() != null)) {
      setTree(tree);
    }
    return this;
  }
}
