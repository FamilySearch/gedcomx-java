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

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import jakarta.xml.bind.annotation.XmlType;

/**
 * Conclusion data that has facts.
 *
 * @author Ryan Heaton
 */
@XmlType(name = "HasFacts")
public interface HasFacts {

  /**
   * The conclusions about the facts of a conclusion resource.
   *
   * @return The conclusions about the facts of a conclusion resource.
   */
  List<Fact> getFacts();

  /**
   * The fact conclusions for the person.
   *
   * @param facts The fact conclusions for the person.
   */
  void setFacts(List<Fact> facts);

  /**
   * Create a stream of facts.
   *
   * @return a stream of facts.
   */
  default Stream<Fact> facts() {
    return Optional.ofNullable(getFacts())
        .map(List::stream)
        .orElse(Stream.empty());
  }
}
