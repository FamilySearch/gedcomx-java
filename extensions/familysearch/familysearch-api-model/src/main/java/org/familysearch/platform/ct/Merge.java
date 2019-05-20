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

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * @author Mike Gardiner
 */

@XmlRootElement
@JsonElementWrapper ( name = "merge" )
@XmlType ( name = "Merge", propOrder = {"resourcesToDelete", "resourcesToCopy"} )
@JsonInclude ( JsonInclude.Include.NON_NULL )
public class Merge {

  private List<ResourceReference> resourcesToDelete;
  private List<ResourceReference> resourcesToCopy;

  /**
   * @return List of resources to remove from the survivor person.
   */
  @XmlElement (name="resourceToDelete")
  @JsonProperty ("resourcesToDelete")
  public List<ResourceReference> getResourcesToDelete() {
    return resourcesToDelete;
  }

  public void setResourcesToDelete( List<ResourceReference> resourcesToDelete ) {
    this.resourcesToDelete = resourcesToDelete;
  }

  /**
   *
   * @return List of resources to copy from the duplicate person to survivor person.
   */
  @XmlElement (name="resourceToCopy")
  @JsonProperty ("resourcesToCopy")
  public List<ResourceReference> getResourcesToCopy() {
    return resourcesToCopy;
  }

  public void setResourcesToCopy( List<ResourceReference> resourcesToCopy ) {
    this.resourcesToCopy = resourcesToCopy;
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
