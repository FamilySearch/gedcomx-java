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
package org.gedcomx.util;

import org.gedcomx.Gedcomx;

import java.util.Iterator;

/**
 * Class for iterating through the 'record' elements (GedcomX documents) in a RecordSet one at a time
 *   from a stream (e.g., a gzipped byte array) without having to inflate all the records at once.
 *
 * User: Brent Hale
 * Date: 6/9/2015
 */
public interface RecordSetIterator extends Iterator<Gedcomx> {

  /**
   * Tell whether the RecordIterator has another GedcomX record to return.
   *
   * @return true if there is another record to read; false otherwise.
   */
  @Override
  boolean hasNext();

  /**
   * Get the next Gedcomx Record from the RecordSet.
   *
   * @return the next Gedcomx Record from the RecordSet.
   */
  @Override
  Gedcomx next();

  /**
   * Retrieve the metadata for the RecordSet.
   *
   * @return A Gedcomx document representing the metadata (or collection information)
   * of the RecordSet.
   */
  Gedcomx getMetadata();

  @Override
  void remove();

  /**
   * Close the input stream and/or accompanying reader if they are still open.
   */
  void close();
}
