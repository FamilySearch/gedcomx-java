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
 * Class representing the status of a resource (such as a person, historical record or image).
 * User: Randy Wilson
 * Date: 11/25/2014
 * Time: 9:58 AM
 */
@XmlQNameEnum(
        base = XmlQNameEnum.BaseType.URI
)
@Schema(description = "ResourceStatusType")
public enum ResourceStatusType implements ControlledVocabulary {

  @JsonProperty(value = "http://gedcomx.org/Deleted")
  Deleted,    // (May be 'replacedBy' another resource, as in the case of a merge).

  @JsonProperty(value = "http://gedcomx.org/Deprecated")
  Deprecated, // Still available, but no longer the latest or best version. Often 'replacedBy' another resource.

  @JsonProperty(value = "http://gedcomx.org/Duplicate")
  Duplicate,  // Duplicate of another resource (like a film that has a picture of the same page twice), and therefore can be ignored.

  @JsonProperty(value = "http://gedcomx.org/Blank")
  Blank,      // Blank (like an image of a blank page in a book, or a black 'filler' image).

  @JsonProperty(value = "http://gedcomx.org/NoData")
  NoData,     // Not blank, but no extractable or relevant data (like an image of a table of contents).

  @JsonProperty(value = "http://gedcomx.org/NeedsReview")
  NeedsReview,// Record is awaiting human review before being used further.

  @JsonProperty(value = "http://gedcomx.org/Unreadable")
  Unreadable, // Resource appears to contain data, but it is unreadable (like a terribly underexposed page).

  @JsonProperty(value = "http://gedcomx.org/Published")
  Published,  // Resource has been published

  @JsonProperty(value = "http://gedcomx.org/Unpublished")
  Unpublished,// Resource is in an unpublished state (i.e., has never been published, or was later unpublished.)

  /**
   * Custom
   */
  @XmlUnknownQNameEnumValue
  @Hidden
  OTHER;

  private static final EnumURIMap<ResourceStatusType> URI_MAP = new EnumURIMap<ResourceStatusType>(ResourceStatusType.class, GedcomxConstants.GEDCOMX_TYPES_NAMESPACE);

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
  public static ResourceStatusType fromQNameURI(URI qname) {
    return URI_MAP.fromURIValue(qname);
  }
}
