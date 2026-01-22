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
package org.gedcomx.search;

import com.fasterxml.jackson.annotation.JsonValue;
import com.webcohesion.enunciate.metadata.Facet;
import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;
import org.gedcomx.rt.GedcomxConstants;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.ValueDeserializer;
import tools.jackson.databind.annotation.JsonDeserialize;

/**
 * The levels of confidence of a search result.
 *
 * @author Ryan Heaton
 */
@XmlType ( name = "ResultConfidence" )
@XmlEnum ( Integer.class )
@JsonDeserialize(using = ResultConfidence.ResultConfidenceDeserializer.class)
@Facet ( GedcomxConstants.FACET_GEDCOMX_RS )
public enum ResultConfidence {

  /**
   * Result confidence level 1 (lowest).
   */
  @XmlEnumValue("1")
  one,

  /**
   * Result confidence level 2.
   */
  @XmlEnumValue("2")
  two,

  /**
   * Result confidence level 3.
   */
  @XmlEnumValue("3")
  three,

  /**
   * Result confidence level 4.
   */
  @XmlEnumValue("4")
  four,

  /**
   * Result confidence level 5 (highest).
   */
  @XmlEnumValue("5")
  five;

  @JsonValue
  public Integer value() {
    return switch (this) {
      case one -> 1;
      case two -> 2;
      case three -> 3;
      case four -> 4;
      case five -> 5;
    };
  }

  static class ResultConfidenceDeserializer extends ValueDeserializer<ResultConfidence>
  {
    @Override
    public ResultConfidence deserialize(final JsonParser parser, final DeserializationContext context) {
      int i = parser.getIntValue();
      return switch (i) {
        case 1 -> ResultConfidence.one;
        case 2 -> ResultConfidence.two;
        case 3 -> ResultConfidence.three;
        case 4 -> ResultConfidence.four;
        case 5 -> ResultConfidence.five;
        default -> null;
      };
    }
  }
}
