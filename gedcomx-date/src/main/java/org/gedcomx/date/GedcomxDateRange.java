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
public class GedcomxDateRange extends GedcomxDate {

  private boolean approximate = false;
  private GedcomxDateSimple start = null;
  private GedcomxDateDuration duration = null;
  private GedcomxDateSimple end = null;

  public GedcomxDateRange(String str) {

    if(str == null || str.length() < 1) {
      throw new GedcomxDateException("Invalid Range");
    }

    String range = str;

    // If range starts with A it is recurring
    if(str.charAt(0) == 'A') {
      approximate = true;
      range = str.substring(1);
    }

    // / is required
    if(!str.contains("/")) {
      throw new GedcomxDateException("Invalid Range: / is required");
    }

    /**
     * range -> parts
     * / -> []
     * +1000/ -> ["+1000"]
     * /+1000 -> ["","+1000"]
     * +1000/+2000 -> ["+1000","+2000"]
     */
    String[] parts = range.split("/");

    if(parts.length < 1 || parts.length > 2) {
      throw new GedcomxDateException("Invalid Range: One or two parts are required");
    }

    if(!parts[0].equals("")) {
      try {
        start = new GedcomxDateSimple(parts[0]);
      } catch (GedcomxDateException e) {
        throw new GedcomxDateException(e.getMessage() + " in Range Start Date");
      }
    }

    if(parts.length == 2) {
      if (parts[1].charAt(0) == 'P') {
        if(start == null) {
          throw new GedcomxDateException("Invalid Range: A range may not end with a duration if missing a start date");
        }
        try {
          duration = new GedcomxDateDuration(parts[1]);
        } catch(GedcomxDateException e) {
          throw new GedcomxDateException(e.getMessage() + " in Range End Duration");
        }
        // Use the duration to calculate the end date
        end = GedcomxDateUtil.addDuration(start, duration);
      } else {
        try {
          end = new GedcomxDateSimple(parts[1]);
        } catch(GedcomxDateException e) {
          throw new GedcomxDateException(e.getMessage() + " in Range End Date");
        }
        if(start != null) {
          duration = GedcomxDateUtil.getDuration(start, end);
        }
      }
    }
  }

  public GedcomxDateSimple getStart() {
    return start;
  }

  public GedcomxDateDuration getDuration() {
    return duration;
  }

  public GedcomxDateSimple getEnd() {
    return end;
  }

  @Override
  public GedcomxDateType getType() {
    return GedcomxDateType.RANGE;
  }

  @Override
  public boolean isApproximate() {
    return approximate;
  }

  @Override
  public String toFormalString() {
    StringBuilder range = new StringBuilder();

    if(approximate) {
      range.append('A');
    }

    if(start != null) {
      range.append(start.toFormalString());
    }

    range.append('/');

    if(duration != null) {
      range.append(duration.toFormalString());
    } else if(end != null) {
      range.append(end.toFormalString());
    }

    return range.toString();
  }
}
