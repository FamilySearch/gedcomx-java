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
package org.gedcomx.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.webcohesion.enunciate.metadata.qname.XmlQNameEnum;
import com.webcohesion.enunciate.metadata.qname.XmlUnknownQNameEnumValue;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;

import org.gedcomx.common.URI;
import org.gedcomx.rt.ControlledVocabulary;
import org.gedcomx.rt.EnumURIMap;
import org.gedcomx.rt.GedcomxConstants;

/**
 * Enumeration of standard identifier types.
 *
 * @author Ryan Heaton
 */
@XmlQNameEnum (
  base = XmlQNameEnum.BaseType.URI
)
@Schema(description = "IdentifierType")
public enum IdentifierType implements ControlledVocabulary {

  /**
   * The primary identifier for the resource.
   */
  @JsonProperty(value = "http://gedcomx.org/Primary")
  Primary,

  /**
   * An identifier for the evidence that supports the resource. For example, when a conclusion
   * about a person is extracted, analyzed and evaluated atomically within the context of a
   * single source, it takes the form of a (extracted) person conclusion, and the extracted conclusion
   * may supply an identifier for the person. As all evidence for the person is gathered, the
   * (working) person conclusion identifies the evidence used to support the conclusion by including
   * each evidence identifier in the list of identifiers for the person.
   */
  @JsonProperty(value = "http://gedcomx.org/Evidence")
  Evidence,

  /**
   * An identifier that has been relegated, deprecated, or otherwise downgraded. This
   * identifier is commonly used as the result of a merge when what was once a primary
   * identifier for a person is no longer primary.
   */
  @JsonProperty(value = "http://gedcomx.org/Deprecated")
  Deprecated,

  /**
   * An identifier that is considered to be a long-term persistent identifier. Applications
   * that provide persistent identifiers are claiming that links to the resource using the identifier
   * won't break.
   */
  @JsonProperty(value = "http://gedcomx.org/Persistent")
  Persistent,

  @XmlUnknownQNameEnumValue
  @Hidden
  OTHER;

  private static final EnumURIMap<IdentifierType> URI_MAP = new EnumURIMap<IdentifierType>(IdentifierType.class, GedcomxConstants.GEDCOMX_TYPES_NAMESPACE);

  /**
   * Return the QName value for this enum.
   *
   * @return The QName value for this enum.
   */
  public URI toQNameURI() {
    return URI_MAP.toURIValue(this);
  }

  /**
   * Get the enumeration from the QName.
   *
   * @param qname The qname.
   * @return The enumeration.
   */
  public static IdentifierType fromQNameURI(URI qname) {
    return URI_MAP.fromURIValue(qname);
  }

}
