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
import org.gedcomx.rt.json.JsonElementWrapper;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Information about a search.
 */
@XmlRootElement
@JsonElementWrapper ( name = "searchInfo" )
@XmlType ( name = "SearchInfo" )
@JsonInclude ( JsonInclude.Include.NON_NULL )
public class SearchInfo {

  private Integer totalHits;
  private Integer closeHits;

  /**
   * The total number of hits.
   *
   * @return The total number of hits.
   */
  public Integer getTotalHits() {
    return totalHits;
  }

  /**
   * The total number of hits.
   *
   * @param totalHits The total number of hits.
   */
  public void setTotalHits(Integer totalHits) {
    this.totalHits = totalHits;
  }

  /**
   * The number of close hits.
   *
   * @return The number of close hits.
   */
  public Integer getCloseHits() {
    return closeHits;
  }

  /**
   * The number of close hits.
   *
   * @param closeHits The number of close hits.
   */
  public void setCloseHits(Integer closeHits) {
    this.closeHits = closeHits;
  }
}
