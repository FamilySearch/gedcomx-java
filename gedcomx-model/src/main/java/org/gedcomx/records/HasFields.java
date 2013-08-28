/**
 * Copyright 2011-2012 Intellectual Reserve, Inc.
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
package org.gedcomx.records;

import java.util.List;

/**
 * Interface indicating that a class as a list of record Fields.
 * The list of fields is either directly used as evidence for a value or entity, or is otherwise viewed as logically
 *   being strongly associated with that value or entity, such that it makes sense to group them within it.
 * @author Randy Wilson
 */
public interface HasFields {

  /**
   * Get the fields being used as evidence.
   *
   * @return The references to the record fields being used as evidence.
   */
  public List<Field> getFields();

  /**
   * Set the list of fields being used as evidence.
   *
   * @param fields - List of fields
   */
  public void setFields(List<Field> fields);
}
