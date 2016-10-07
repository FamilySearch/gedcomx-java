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
import com.fasterxml.jackson.annotation.JsonProperty;
import org.gedcomx.common.ResourceReference;
import org.gedcomx.rt.json.JsonElementWrapper;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Mike Gardiner
 */
@XmlRootElement
@JsonElementWrapper ( name = "mergeConflict" )
@XmlType ( name = "MergeConflict", propOrder = {"survivorResource", "duplicateResource"} )
public class MergeConflict {
  private ResourceReference survivorResource;
  private ResourceReference duplicateResource;

  public MergeConflict() {

  }

  public MergeConflict(ResourceReference survivorResource, ResourceReference duplicateResource ) {
    this.survivorResource = survivorResource;
    this.duplicateResource = duplicateResource;
  }

  @XmlElement (name="survivorResource")
  @JsonProperty ("survivorResource")
  @JsonName ("survivorResource")
  public ResourceReference getSurvivorResource() {
    return survivorResource;
  }

  public void setSurvivorResource( ResourceReference survivorResource ) {
    this.survivorResource = survivorResource;
  }

  @XmlElement (name="duplicateResource")
  @JsonProperty ("duplicateResource")
  @JsonName ("duplicateResource")
  public ResourceReference getDuplicateResource() {
    return duplicateResource;
  }

  public void setDuplicateResource( ResourceReference duplicateResource ) {
    this.duplicateResource = duplicateResource;
  }
}
