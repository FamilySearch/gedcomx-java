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
import java.util.HashMap;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;


/**
 * A class to represent an entry from a GEDCOM X file.
 */
public class GedcomxFileEntry {
  private final JarEntry jarEntry;

  /**
   * Constructs an instance of GedcomxFileEntry by wrapping a JarEntry from the underlying Jar file.
   *
   * @param jarEntry The JarEntry instance being wrapped.
   */
  public GedcomxFileEntry(JarEntry jarEntry) {
    if (jarEntry == null) {
      throw new NullPointerException("jarEntry");
    }
    else if (jarEntry.getName().equals("META-INF/MANIFEST.MF")) {
      throw new IllegalStateException("Manifest can't be a gx file entry.");
    }

    this.jarEntry = jarEntry;
  }

  /**
   * Gets the underlying JarEntry object used to construct this entry.
   *
   * @return The underlying JarEntry object used to construct this entry.
   */
  public JarEntry getJarEntry() {
    return jarEntry;
  }

  /**
   * The content type of this entry.
   *
   * @return The content type of the part.
   * @throws IOException if an I/O error has occurred
   */
  public String getContentType() throws IOException {
    Attributes attributes = this.jarEntry.getAttributes();
    return attributes != null ? attributes.getValue(Attributes.Name.CONTENT_TYPE) : null;
  }

  /**
   * Get the value of the specified per-entry attribute.
   *
   * @param name The name of the per-entry attribute.
   * @return The value of the per-entry attribute.
   * @throws IOException if an I/O error has occurred
   */
  public String getAttribute(String name) throws IOException {
    Attributes attributes = this.jarEntry.getAttributes();
    return attributes != null ? attributes.getValue(name) : null;
  }

  /**
   * Gets the attributes associated with this entry.
   *
   * @return The attributes associated with this entry.
   *
   * @throws IOException if an I/O error has occurred
   */
  public Map<String, String> getAttributes() throws IOException {
    Map<String, String> attributesMap = new HashMap<String, String>();

    Attributes attributes = this.jarEntry.getAttributes();
    if (attributes != null) {
      for (Attributes.Entry entry : attributes.entrySet()) {
        String key = entry.getKey().toString();
        String value = (String)entry.getValue();
        attributesMap.put(key, value);
      }
    }

    return attributesMap;
  }
}
