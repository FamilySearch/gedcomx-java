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
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


/**
 * The <code>GedcomxFile</code> class is used to read the contents of a GEDCOM X file
 * from any file that can be opened with <code>java.util.jar.JarFile</code>.
 */
public class GedcomxFile {

  private final JarFile gedxFile;
  private final GedcomxEntryDeserializer deserializer;

  public GedcomxFile(JarFile gedxFile, GedcomxEntryDeserializer deserializer) {
    this.gedxFile = gedxFile;
    this.deserializer = deserializer;
  }

  /**
   * Creates a new <code>GedcomxFile</code> to read from the specified file <code>JarFile</code>.
   *
   * @param jarFile the jar file to be read
   * @throws IOException if an I/O error has occurred
   */
  public GedcomxFile(JarFile jarFile, Class<?>... classes) throws IOException {
    this(jarFile, new JacksonJsonSerialization(classes));
  }

  /**
   * Get the value of the specified attribute for this GEDCOM X file.
   *
   * @param name The attribute name.
   * @return The attribute value.
   */
  public String getAttribute(String name) throws IOException {
    return this.gedxFile.getManifest().getMainAttributes().getValue(name);
  }

  /**
   * Get the attributes that have been associated with this GEDCOM X file.
   *
   * @return The attributes.
   */
  public Map<String, String> getAttributes() throws IOException {
    Map<String, String> attributes = new HashMap<String, String>();

    for (Map.Entry<Object, Object> entry : this.gedxFile.getManifest().getMainAttributes().entrySet()) {
      String key = entry.getKey().toString();
      String value = (String)entry.getValue();
      attributes.put(key, value);
    }

    return attributes;
  }

  /**
   * Get the entries found in this GEDCOM X file.
   *
   * @return The GEDCOM X file entries.
   */
  public Iterable<GedcomxFileEntry> getEntries() {
    final Enumeration<JarEntry> jarEntries = this.gedxFile.entries();
    return new Iterable<GedcomxFileEntry>() {
      @Override
      public Iterator<GedcomxFileEntry> iterator() {
        return new Iterator<GedcomxFileEntry>() {
          @Override
          public boolean hasNext() {
            return jarEntries.hasMoreElements();
          }

          @Override
          public GedcomxFileEntry next() {
            return new GedcomxFileEntry(jarEntries.nextElement());
          }

          @Override
          public void remove() {
            throw new UnsupportedOperationException();
          }
        };
      }
    };
  }

  /**
   * Get the input stream of the resource in the given entry.
   *
   * @param gedxEntry The entry that contains the desired resource.
   * @return The input stream that constitutes the nature of the resource.
   */
  public InputStream getResourceStream(GedcomxFileEntry gedxEntry) throws IOException {
    return this.gedxFile.getInputStream(gedxEntry.getJarEntry());
  }

  /**
   * Unmarshal the resource contained in the given entry as an object.
   *
   * @param gedxEntry The entry that contains the desired resource.
   * @return The resource.
   *
   * @throws IOException If there was a problem unmarshalling the resource.
   */
  public Object readResource(GedcomxFileEntry gedxEntry) throws IOException {
    return this.deserializer.deserialize(getResourceStream(gedxEntry), gedxEntry.getContentType());
  }


  /**
   * Closes the GEDCOM X file.
   *
   * @throws IOException
   */
  public void close() throws IOException {
    this.gedxFile.close();
  }
}
