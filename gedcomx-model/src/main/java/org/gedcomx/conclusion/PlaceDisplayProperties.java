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
import org.gedcomx.common.ExtensibleData;
import org.gedcomx.rt.GedcomxConstants;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlType;


/**
 * A set of display properties for places for the convenience of quick display, such as for
 * a Web-based application. All display properties are provided in the default locale for the current
 * application context and are NOT considered canonical for the purposes of data exchange.
 */
@XmlType ( name = "PlaceDisplayProperties" )
@Facet ( GedcomxConstants.FACET_GEDCOMX_RS )
@JsonInclude ( JsonInclude.Include.NON_NULL )
@Schema(description = "A set of display properties for places for the convenience of quick display, such as for a Web-based application. " +
    "All display properties are provided in the default locale for the current application context and are NOT considered canonical for the purposes of data exchange.")
public class PlaceDisplayProperties extends ExtensibleData {

  @Schema(description = "The displayable name of the place.")
  private String name;

  @Schema(description = "The displayable full name of the place.")
  private String fullName;

  @Schema(description = "The displayable type of the place.")
  private String type;

  public PlaceDisplayProperties() {
  }

  public PlaceDisplayProperties(PlaceDisplayProperties copy) {
    super(copy);
    this.name = copy.name;
    this.fullName = copy.fullName;
    this.type = copy.type;
  }

  @Override
  public PlaceDisplayProperties id(String id) {
    return (PlaceDisplayProperties) super.id(id);
  }

  @Override
  public PlaceDisplayProperties extensionElement(Object element) {
    return (PlaceDisplayProperties) super.extensionElement(element);
  }

  /**
   * The displayable name of the place.
   *
   * @return The displayable name of the place.
   */
  public String getName() {
    return name;
  }

  /**
   * The displayable name of the place.
   *
   * @param name The displayable name of the place.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Build up these properties with a name.
   * 
   * @param name The name.
   * @return this.
   */
  public PlaceDisplayProperties name(String name) {
    setName(name);
    return this;
  }

  /**
   * The displayable full name of the place.
   *
   * @return The displayable full name of the place.
   */
  public String getFullName() {
    return fullName;
  }

  /**
   * The displayable full name of the place.
   *
   * @param name The displayable full name of the place.
   */
  public void setFullName(String name) {
    this.fullName = name;
  }

  /**
   * Build up these properties with a full name.
   * 
   * @param name The name.
   * @return this.
   */
  public PlaceDisplayProperties fullName(String name) {
    setFullName(name);
    return this;
  }

  /**
   * The displayable type of the place.
   *
   * @return The displayable type of the place.
   */
  public String getType() {
    return type;
  }

  /**
   * The displayable type of the place.
   *
   * @param type The displayable type of the place.
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * Build up these properties with a type.
   *
   * @param type The type.
   * @return this.
   */
  public PlaceDisplayProperties type(String type) {
    setType(type);
    return this;
  }

  /**
   * Embed a set of display properties.
   *
   * @param data The data to embed.
   */
  public void embed(PlaceDisplayProperties data) {
    this.name = this.name == null ? data.name : this.name;
    this.fullName = this.fullName == null ? data.fullName : this.fullName;
    this.type = this.type == null ? data.type : this.type;
    super.embed(data);
  }
}
