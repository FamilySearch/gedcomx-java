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
 * A Formal Gedcomx Date
 * @author John Clark.
 */
public abstract class GedcomxDate implements Comparable<GedcomxDate> {

  /**
   * Return the type of date
   * @return The Type
   */
  public abstract GedcomxDateType getType();

  /**
   * Whether or not this date is approximate
   * @return True if this date is approximate
   */
  public abstract boolean isApproximate();

  /**
   * The formal representation of this date
   * @return The formal string
   */
  public abstract String toFormalString();

  /**
   * This method MAY not be implemented for each subclass.  Specific behavior should be documented
   * in each subclass implementation.
   * @param o the object to be compared.
   * @return If implemented - a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object
   * @throws UnsupportedOperationException if the sublcass does not support this method.
   */
  @Override
  public int compareTo(GedcomxDate o) {
    throw new UnsupportedOperationException();
  }

}