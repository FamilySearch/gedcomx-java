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

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.webcohesion.enunciate.metadata.Facet;
import com.fasterxml.jackson.annotation.JsonValue;
import org.gedcomx.rt.GedcomxConstants;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;
import java.io.IOException;

/**
 * The levels of confidence of a search result.
 *
 * @author Ryan Heaton
 */
@XmlType ( name = "ResultConfidence" )
@XmlEnum ( Integer.class )
@JsonDeserialize (using = ResultConfidence.ResultConfidenceDeserializer.class)
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
    switch(this) {
      case one:
        return 1;
      case two:
        return 2;
      case three:
        return 3;
      case four:
        return 4;
      case five:
        return 5;
      default:
        return null;
    }
  }

  static class ResultConfidenceDeserializer extends JsonDeserializer<ResultConfidence>
  {
    @Override
    public ResultConfidence deserialize(final JsonParser parser, final DeserializationContext context) throws IOException
    {
      int i = parser.getIntValue();
      switch(i) {
        case 1:
          return ResultConfidence.one;
        case 2:
          return ResultConfidence.two;
        case 3:
          return ResultConfidence.three;
        case 4:
          return ResultConfidence.four;
        case 5:
          return ResultConfidence.five;
        default:
          return null;
      }
    }
  }
}
