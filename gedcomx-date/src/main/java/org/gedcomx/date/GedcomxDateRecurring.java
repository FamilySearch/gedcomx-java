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
public class GedcomxDateRecurring extends GedcomxDate {

  private Integer count = null;
  private GedcomxDateRange range;
  private GedcomxDateSimple end = null;

  public GedcomxDateRecurring(String str) {

    if(str == null || str.length() < 3) {
      throw new GedcomxDateException("Invalid Recurring Date");
    }

    if(str.charAt(0) != 'R') {
      throw new GedcomxDateException("Invalid Recurring Date: Must start with R");
    }

    String[] parts = str.split("/");

    if(parts.length != 3) {
      throw new GedcomxDateException("Invalid Recurring Date: Must contain 3 parts");
    }

    // We must have a start and end
    if(parts[1].equals("") || parts[2].equals("")) {
      throw new GedcomxDateException("Invalid Recurring Date: Range must have a start and an end");
    }

    String countNum = parts[0].substring(1);
    char[] countNumChars = parts[0].substring(1).toCharArray();

    if(countNumChars.length > 0) {
      for (char c : countNumChars) {
        if (!Character.isDigit(c)) {
          throw new GedcomxDateException("Invalid Recurring Date: Malformed Count");
        }
      }
      count = Integer.valueOf(countNum);
    }
    try {
      range = new GedcomxDateRange(parts[1] + "/" + parts[2]);
    } catch(GedcomxDateException e) {
      throw new GedcomxDateException(e.getMessage() + " in Recurring Range");
    }

    // If we have a count set end
    if(count != null) {
      end = getNth(count);
    }
  }

  public Integer getCount() {
    return count;
  }

  public GedcomxDateRange getRange() {
    return range;
  }

  public GedcomxDateSimple getStart() {
    return range.getStart();
  }

  public GedcomxDateDuration getDuration() {
    return range.getDuration();
  }

  public GedcomxDateSimple getEnd() {
    return end;
  }

  public GedcomxDateSimple getNth(Integer count) {

    GedcomxDateDuration duration = GedcomxDateUtil.multiplyDuration(range.getDuration(), count);

    return GedcomxDateUtil.addDuration(range.getStart(), duration);
  }

  @Override
  public GedcomxDateType getType() {
    return GedcomxDateType.RECURRING;
  }

  @Override
  public boolean isApproximate() {
    return false;
  }

  @Override
  public String toFormalString() {
    if(count != null) {
      return "R"+count+"/"+range.toFormalString();
    } else {
      return "R/"+range.toFormalString();
    }
  }
}
