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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.familysearch.platform.rt.FamilySearchPlatformModelVisitor;
import org.gedcomx.common.ResourceReference;
import org.gedcomx.rt.json.JsonElementWrapper;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * @author Mike Gardiner
 */

@XmlRootElement
@JsonElementWrapper ( name = "mergeAnalysis" )
@XmlType ( name = "MergeAnalysis", propOrder = {"survivorResources", "duplicateResources", "conflictingResources", "survivor", "duplicate"} )
@JsonInclude ( JsonInclude.Include.NON_NULL )
@Schema(description = "A merge analysis.")
public class MergeAnalysis {

  @Schema(description = "List of survivor resources.")
  private List<ResourceReference> survivorResources;

  @Schema(description = "List of duplicate resources.")
  private List<ResourceReference> duplicateResources;

  @Schema(description = "List of resources that are in conflict.")
  private List<MergeConflict> conflictingResources;

  @Schema(description = "The surviving person.")
  private ResourceReference survivor;

  @Schema(description = "The duplicate person.")
  private ResourceReference duplicate;

  @XmlElement (name="survivorResource")
  @JsonProperty ("survivorResources")
  public List<ResourceReference> getSurvivorResources() {
    return survivorResources;
  }

  public void setSurvivorResources( List<ResourceReference> survivorResources ) {
    this.survivorResources = survivorResources;
  }

  @XmlElement (name="duplicateResource")
  @JsonProperty ("duplicateResources")
  public List<ResourceReference> getDuplicateResources() {
    return duplicateResources;
  }

  public void setDuplicateResources( List<ResourceReference> duplicateResources ) {
    this.duplicateResources = duplicateResources;
  }

  @XmlElement (name="conflictingResource")
  @JsonProperty ("conflictingResources")
  public List<MergeConflict> getConflictingResources() {
    return conflictingResources;
  }

  public void setConflictingResources(List<MergeConflict> conflictingResources) {
    this.conflictingResources = conflictingResources;
  }


  @XmlElement (name="survivor")
  @JsonProperty ("survivor")
  public ResourceReference getSurvivor() {
    return survivor;
  }

  public void setSurvivor(ResourceReference survivor) {
    this.survivor = survivor;
  }

  @XmlElement (name="duplicate")
  @JsonProperty ("duplicate")
  public ResourceReference getDuplicate() {
    return duplicate;
  }

  public void setDuplicate( ResourceReference duplicate ) {
    this.duplicate = duplicate;
  }

  /**
   * Accept a visitor.
   *
   * @param visitor The visitor to accept.
   */
  public void accept(FamilySearchPlatformModelVisitor visitor) {
    visitor.visitMerge(this);
  }
}
