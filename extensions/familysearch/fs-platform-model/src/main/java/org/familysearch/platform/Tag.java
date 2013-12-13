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

import org.gedcomx.common.URI;
import org.gedcomx.rt.json.JsonElementWrapper;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * A tag in the FamilySearch system.
 */
@XmlRootElement
@JsonElementWrapper(name = "tags")
@XmlType( name = "Tag" )
@SuppressWarnings("gedcomx:no_id")
public class Tag implements Serializable {

  private URI resource;

  public Tag() {
  }

  public Tag(Enum value) {
    this.resource = URI.create(org.codehaus.enunciate.XmlQNameEnumUtil.toURIValue(value));
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
}
