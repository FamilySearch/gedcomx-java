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
package org.familysearch.platform;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.gedcomx.common.URI;
import org.gedcomx.rt.ControlledVocabulary;
import org.gedcomx.rt.json.JsonElementWrapper;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * A tag in the FamilySearch system.
 */
@XmlRootElement
@JsonElementWrapper(name = "tags")
@XmlType( name = "Tag" )
@JsonInclude ( JsonInclude.Include.NON_NULL )
@SuppressWarnings("gedcomx:no_id")
public class Tag implements Serializable {

  private URI resource;
  private String conclusionId;

  public Tag() {
  }

  public Tag(ControlledVocabulary value) {
    this.resource = value == null ? null : value.toQNameURI();
    this.conclusionId = null;
  }

  public Tag(String conclusionId) {
    this.resource = null;
    this.conclusionId = conclusionId;
  }

  /**
   * A reference to the value of the tag.
   *
   * @return A reference to the value of the tag.
   */
  @XmlAttribute
  public URI getResource() {
    return resource;
  }

  /**
   * A reference to the value of the tag.
   *
   * @param resource A reference to the value of the tag.
   */
  public void setResource(URI resource) {
    this.resource = resource;
  }

  /**
   * The conclusionId associated with this tag.
   *
   * @return The conclusionId associated with this tag.
   */
  @XmlAttribute
  public String getConclusionId() { return conclusionId; }

  /**
   * The conclusionId to associate with this tag.
   *
   * @param conclusionId The conclusionId to associate with this tag.
   */
  public void setConclusionId(String conclusionId) { this.conclusionId = conclusionId; }
}
