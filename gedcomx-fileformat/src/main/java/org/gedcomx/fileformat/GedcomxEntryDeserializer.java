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
package org.gedcomx.fileformat;

import java.io.IOException;
import java.io.InputStream;

/**
 * Basic interface abstracting deserialization of an entry.
 *
 * @author Ryan Heaton
 */
public interface GedcomxEntryDeserializer {

  /**
   * Deserialize the resource from the specified input stream.
   *
   * @param in The input stream.
   * @param mediaType The media type of the input stream.
   * @return the resource.
   * @throws IOException a IOException
   */
  Object deserialize(InputStream in, String mediaType) throws IOException;
}
