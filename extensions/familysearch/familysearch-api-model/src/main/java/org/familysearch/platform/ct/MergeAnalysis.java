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

import org.codehaus.enunciate.json.JsonName;
import org.codehaus.jackson.annotate.JsonProperty;
import org.familysearch.platform.rt.FamilySearchPlatformModelVisitor;
import org.gedcomx.common.ResourceReference;
import org.gedcomx.rt.json.JsonElementWrapper;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * @author Mike Gardiner
 */

@XmlRootElement
@JsonElementWrapper ( name = "mergeAnalysis" )
@XmlType ( name = "MergeAnalysis", propOrder = {"survivorResources", "duplicateResources", "conflictingResources", "survivor", "duplicate"} )
public class MergeAnalysis
{
  private List<ResourceReference> survivorResources;
  private List<ResourceReference> duplicateResources;
  private List<MergeConflict> conflictingResources;
  private ResourceReference survivor;
  private ResourceReference duplicate;


  @XmlElement (name="survivorResource")
  @JsonProperty ("survivorResources")
  @JsonName ("survivorResources")
  public List<ResourceReference> getSurvivorResources() {
    return survivorResources;
  }

  public void setSurvivorResources( List<ResourceReference> survivorResources ) {
    this.survivorResources = survivorResources;
  }

  @XmlElement (name="duplicateResource")
  @JsonProperty ("duplicateResources")
  @JsonName ("duplicateResources")
  public List<ResourceReference> getDuplicateResources() {
    return duplicateResources;
  }

  public void setDuplicateResources( List<ResourceReference> duplicateResources ) {
    this.duplicateResources = duplicateResources;
  }

  @XmlElement (name="conflictingResource")
  @JsonProperty ("conflictingResources")
  @JsonName ("conflictingResources")
  public List<MergeConflict> getConflictingResources() {
    return conflictingResources;
  }

  public void setConflictingResources(List<MergeConflict> conflictingResources) {
    this.conflictingResources = conflictingResources;
  }


  @XmlElement (name="survivor")
  @JsonProperty ("survivor")
  @JsonName ("survivor")
  public ResourceReference getSurvivor() {
    return survivor;
  }

  public void setSurvivor(ResourceReference survivor) {
    this.survivor = survivor;
  }

  @XmlElement (name="duplicate")
  @JsonProperty ("duplicate")
  @JsonName ("duplicate")
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
