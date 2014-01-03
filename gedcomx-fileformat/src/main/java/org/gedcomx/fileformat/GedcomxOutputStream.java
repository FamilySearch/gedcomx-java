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

import org.gedcomx.Gedcomx;
import org.gedcomx.rt.GedcomxConstants;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Map;
import java.util.jar.*;


/**
 * Class to help in writing a GEDCOM X file.
 */
public class GedcomxOutputStream {

  private final GedcomxEntrySerializer serializer;
  private final JarOutputStream gedxOutputStream;
  private final Manifest mf;
  private int entryCount = 0;

  public GedcomxOutputStream(OutputStream gedxOutputStream, GedcomxEntrySerializer serializer) throws IOException {
    this.serializer = serializer;
    this.gedxOutputStream = new JarOutputStream(gedxOutputStream);
    this.mf = new Manifest();
    this.mf.getMainAttributes().put(Attributes.Name.MANIFEST_VERSION, "1.0");

  }

    /**
    * Constructs a GEDCOM X output stream.
    *
    * NOTE: This class uses the GedcomXFileJAXBContextFactory to create a JAXB context from which to derive the marshaller that is used to marshal resources into the output stream.
    * GedcomXFileJAXBContextFactory creates a context that includes some default resource classes.  The classes passed via this constructor will supplement these defaults; they will
    * not overwrite or replace these defaults.  Please see the documentation for GedcomXFileJAXBContextFactory to review the list of default classes.
    *
    * @param gedxOutputStream an output stream to which the GEDCOM X resources will appended
    * @param classes classes representing resources that will be marshaled (via JAXB) into the GEDCOM X output stream
    *
    * @throws IOException
    */
  public GedcomxOutputStream(OutputStream gedxOutputStream, Class<?>... classes) throws IOException {
    this(gedxOutputStream, new DefaultXMLSerialization(classes));
  }

  /**
   * Add an attribute to the GEDCOM X output stream.
   *
   * @param name The name of the attribute.
   * @param value The value of the attribute.
   */
  public void addAttribute(String name, String value) {
    this.mf.getMainAttributes().putValue(name, value);
  }

  /**
   * Add a resource to the GEDCOM X output stream.
   *
   * @param resource The resource.
   * @throws IOException
   */
  public void addResource(Gedcomx resource) throws IOException {
    addResource(resource, new Date());
  }

  /**
   * Add a resource to the GEDCOM X output stream.
   *
   * @param resource The resource.
   * @param lastModified timestamp when the resource was last modified (can be null)
   * @throws IOException
   */
  public void addResource(Gedcomx resource, Date lastModified) throws IOException {
    StringBuilder entryName = new StringBuilder("tree");
    if (this.entryCount > 0) {
      entryName.append(this.entryCount);
    }
    entryName.append(".xml");
    addResource(entryName.toString(), resource, lastModified);
  }

  /**
   * Add a resource to the GEDCOM X output stream.
   *
   * @param entryName The name by which this resource shall be known within the GEDCOM X file.
   * @param resource The resource.
   * @param lastModified timestamp when the resource was last modified (can be null)
   * @throws IOException
   */
  public void addResource(String entryName, Gedcomx resource, Date lastModified) throws IOException {
    addResource(GedcomxConstants.GEDCOMX_XML_MEDIA_TYPE, entryName, resource, lastModified, null);
  }

  /**
   * Add a resource to the GEDCOM X output stream.
   *
   *
   * @param contentType The content type of the resource.
   * @param entryName The name by which this resource shall be known within the GEDCOM X file.
   * @param resource The resource.
   * @param lastModified timestamp when the resource was last modified (can be null)
   * @throws IOException
   */
  public void addResource(String contentType, String entryName, Object resource, Date lastModified) throws IOException {
    addResource(contentType, entryName, resource, lastModified, null);
  }

  /**
   * Add a resource to the GEDCOM X output stream.
   *
   * @param contentType The content type of the resource.
   * @param entryName The name by which this resource shall be known within the GEDCOM X file.
   * @param resource The resource.
   * @param lastModified timestamp when the resource was last modified (can be null)
   * @param attributes The attributes of the resource.
   *
   * @throws IOException
   */
  public void addResource(String contentType, String entryName, Object resource, Date lastModified, Map<String, String> attributes) throws IOException {
    if (contentType.trim().length() == 0) {
      throw new IllegalArgumentException("contentType must not be null or empty.");
    }

    entryName = entryName.replaceAll("\\\\", "/");
    entryName = entryName.charAt(0) == '/' ? entryName.substring(1) : entryName;

    JarEntry gedxEntry = new JarEntry(entryName); // will throw a runtime exception if entryName is not okay
    Attributes entryAttrs = new Attributes();

    if (lastModified != null) {
      entryAttrs.putValue("X-DC-modified", GedcomxTimeStampUtil.formatAsXmlUTC(lastModified));
    }

    if (!isKnownContentType(contentType)) {
      entryAttrs.put(Attributes.Name.CONTENT_TYPE, contentType);
    }

    if (attributes != null) {
      for (Map.Entry<String, String> entry : attributes.entrySet()) {
        entryAttrs.putValue(entry.getKey(), entry.getValue());
      }
    }

    if (!entryAttrs.isEmpty()) {
      this.mf.getEntries().put(entryName, entryAttrs);
    }

    this.gedxOutputStream.putNextEntry(gedxEntry);
    this.serializer.serialize(resource, this.gedxOutputStream);
    this.entryCount++;
  }

  private boolean isKnownContentType(String contentType) {
    return this.serializer.isKnownContentType(contentType);
  }

  /**
   * Closes the GEDCOM X output stream as well as the stream being filtered.
   *
   * @throws IOException
   */
  public void close() throws IOException {
    this.gedxOutputStream.putNextEntry(new JarEntry(JarFile.MANIFEST_NAME));
    this.mf.write(this.gedxOutputStream);
    this.gedxOutputStream.close();
  }
}
