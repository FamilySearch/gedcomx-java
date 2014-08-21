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
package org.gedcomx.date;

/**
 * @author John Clark.
 */
public class GedcomxDateDuration extends GedcomxDate {

  public GedcomxDateDuration(String str) {

    // Durations must start with P
    if(str.length() < 1 || str.charAt(0) != 'P') {
      throw new GedcomxDateException("Invalid Duration - Must start with P");
    }

    String duration = str.substring(1);

    if(duration.length() < 1) {
      throw new GedcomxDateException("Invalid Duration - You must have a duration value");
    }

    // 5.3.2 allows for NON normalized durations
    // We assume that if there is a space, it is non-normalized
    if(duration.contains(" ")) {
      throw new GedcomxDateException("Not Implemented - Non normalized durations are not implemented yet");
    }

  }



  @Override
  public GedcomxDateType getType() {
    return GedcomxDateType.DURATION;
  }

  /**
   * A Duration is NEVER Approximate
   * @return
   */
  @Override
  public boolean isApproximate() {
    return false;
  }

  @Override
  public String toFormalString() {
    return null;
  }
}
