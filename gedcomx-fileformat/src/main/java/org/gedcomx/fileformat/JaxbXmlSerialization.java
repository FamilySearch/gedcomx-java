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
import org.gedcomx.rt.GedcomNamespaceManager;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


/**
 * A class for creating instances of <code>JAXBContext</code> appropriate for reading and writing GEDCOM X files.
 */
public class JaxbXmlSerialization implements GedcomxEntrySerializer, GedcomxEntryDeserializer {

  private final Unmarshaller unmarshaller;
  private final Marshaller marshaller;

  public JaxbXmlSerialization(Class<?>... classes) {
    this(true, classes);
  }

  public JaxbXmlSerialization(boolean pretty, Class<?>... classes) {
    try {
      JAXBContext context = newContext(classes);
      this.unmarshaller = context.createUnmarshaller();
      this.marshaller = context.createMarshaller();
      this.marshaller.setProperty("org.glassfish.jaxb.namespacePrefixMapper", new GedcomNamespaceManager(Gedcomx.class));
      if (pretty) {
        this.marshaller.setProperty("jaxb.formatted.output", Boolean.TRUE);
      }
    }
    catch (JAXBException e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Override
  public Object deserialize(InputStream in, String mediaType) throws IOException {
    if (!isKnownContentType(mediaType)) {
      return in;
    }

    try {
      return this.unmarshaller.unmarshal(in);
    }
    catch (JAXBException e) {
      throw new IOException(e);
    }
  }

  private boolean isKnownContentType(String mediaType) {
    return mediaType.endsWith("xml");
  }

  @Override
  public void serialize(Object resource, OutputStream out) throws IOException {
    try {
      this.marshaller.marshal(resource, out);
    }
    catch (JAXBException e) {
      throw new IOException(e);
    }
  }

  /**
   * Factory method for creating a new instance of a <code>JAXBContext</code> appropriate for reading and/or writing a GEDCOM X file.
   *
   * The created <code>JAXBContext</code> references the following classes by default:
   *   org.gedcomx.conclusion.Person
   *   org.gedcomx.conclusion.Relationship
   *   org.gedcomx.metadata.dc.ObjectFactory
   *   org.gedcomx.metadata.foaf.Person
   *   org.gedcomx.contributor.Agent
   *   org.gedcomx.metadata.rdf.Description
   * Any additional classes needed can be passed to this call to supplement (not override) these defaults
   *
   * @param classes Additional classes to supplement (not override) the provided defaults
   * @return A JAXBContext
   *
   * @throws JAXBException
   */
  private static JAXBContext newContext(Class<?>... classes) throws JAXBException {
    Set<Class<?>> contextClasses = new HashSet<Class<?>>();
    contextClasses.add(Gedcomx.class);
    contextClasses.addAll(Arrays.asList(classes));
    return JAXBContext.newInstance((Class<?>[]) contextClasses.toArray(new Class<?>[contextClasses.size()]));
  }

  @Override
  public String suggestFilenameExtension() {
    return ".xml";
  }
}
