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
package org.familysearch.platform.search;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.gedcomx.rt.json.JsonElementWrapper;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Objects.isNull;

@XmlRootElement
@JsonElementWrapper(name="facets")
@JsonInclude(JsonInclude.Include.NON_NULL)
@XmlType( name = "Facet" )
@Schema(description = "A facet in a search result.")
public class Facet {

  @Schema(description = "The localized name of this facet.")
  private String displayName;

  @Schema(description = "The localized count of this facet.")
  private String displayCount;

  @Schema(description = "The API parameters used to filter and count at this facet level.")
  private String params;

  @Schema(description = "The numeric count of this facet.")
  private long count;

  @Schema(description = "The facets nested inside this facet.")
  private List<Facet> facets;

  /**
   *
   */
  public Facet() {
  }

  /**
   * Constructor will all arguments.
   *
   * @param displayName Localized name of this facet.
   * @param displayCount Localized count of this facet.
   * @param params API parameters used to filter and count at this facet level.
   * @param count The numeric count of this facet.
   * @param facets The facets nested inside this facet.
   */
  public Facet(String displayName, String displayCount, String params, long count, List<Facet> facets) {
    this.displayName = displayName;
    this.displayCount = displayCount;
    this.params = params;
    this.count = count;
    this.facets = facets;
  }

  /**
   * Get the localized name of this facet
   *
   * @return Localized name of this facet.
   */
  public String getDisplayName() {
    return displayName;
  }

  /**
   * Set the localized name of this facet
   *
   * @param displayName the localized name of this facet.
   */
  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  /**
   * Build out the localized name of this facet
   *
   * @param displayName the localized name of this facet.
   * @return this.
   */
  public Facet displayName(String displayName) {
    setDisplayName(displayName);
    return this;
  }

  /**
   * Get the localized count of this facet.
   *
   * @return the Localized count of this facet.
   */
  public String getDisplayCount() {
    return displayCount;
  }

  /**
   * Set the localized count of this facet.
   *
   * @param displayCount the localized count of this facet.
   */
  public void setDisplayCount(String displayCount) {
    this.displayCount = displayCount;
  }

  /**
   * Build out the localized count of this facet.
   *
   * @param displayCount the localized count of this facet.
   * @return this
   */
  public Facet displayCount(String displayCount) {
    setDisplayCount(displayCount);
    return this;
  }

  /**
   * Get the API parameters used to filter at this facet level.
   *
   * @return the API parameters used to filter at this facet level.
   */
  public String getParams() {
    return params;
  }

  /**
   * Set the API parameters used to filter at this facet level.
   *
   * @param params the API parameters used to filter and count at this facet level.
   */
  public void setParams(String params) {
    this.params = params;
  }

  /**
   * Build out the API parameters used to filter at this facet level.
   *
   * @param params the API parameters used to filter and count at this facet level.
   * @return this
   */
  public Facet params(String params) {
    setParams(params);
    return this;
  }

  /**
   * Get the count numeric count of this facet.
   *
   * @return the count numeric count of this facet.
   */
  public long getCount() {
    return count;
  }

  /**
   * Set the count numeric count of this facet.
   *
   * @param count the count numeric count of this facet.
   */
  public void setCount(long count) {
    this.count = count;
  }

  /**
   * Build out the count numeric count of this facet.
   *
   * @param count the count numeric count of this facet.
   * @return this
   */
  public Facet count(long count) {
    setCount(count);
    return this;
  }

  /**
   * Get the facets nested inside this facet.
   *
   * @return The facets nested inside this facet.
   */
  public List<Facet> getFacets() {
    return facets;
  }

  /**
   * Create a stream for the persons in this data set.
   *
   * @return The stream of facets nested inside this facet.
   */
  public Stream<Facet> facets() {
    return isNull(facets) ? Stream.empty() : facets.stream();
  }

  /**
   * Get the facets nested inside this facet.
   *
   * @param facets The facets nested inside this facet.
   */
  public void setFacets(List<Facet> facets) {
    this.facets = facets;
  }

  /**
   * Build out the facets nested inside this facet.
   *
   * @param facets The facets nested inside this facet.
   * @return this
   */
  public Facet facets(List<Facet> facets) {
    setFacets(facets);
    return this;
  }

}
