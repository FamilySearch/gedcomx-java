/**
 * Copyright Intellectual Reserve, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gedcomx.util;

import org.gedcomx.Gedcomx;
import org.gedcomx.records.RecordSet;
import org.gedcomx.rt.GedcomxConstants;

import javax.xml.bind.*;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Class for streaming a RecordSet to an OutputStream in XML as records are being added.
 *
 * User: Randy Wilson
 * Date: 12/4/13
 */
public class RecordSetWriter {
  Marshaller marshaller;
  private XMLStreamWriter xmlWriter;
  // Metadata, if any
  private Gedcomx metadata;
  // Flag for whether any records have been written so far
  private boolean wroteRecords = false;
  // Flag for whether the metadata was already written before the records.
  private boolean wroteMetadata = false;

  // Stream to write data to
  private OutputStream outputStream;

  /**
   * Constructor. Prepares to write GedcomX document records to the given output stream (which may well be a
   *   GZIPOutputStream), so that only one such document needs to be fully instantiated in memory at once.
   *   This constructor includes only Gedcomx.class and RecordSet.class in the underlying JAXBContext marshaller.
   * @param outputStream - OutputStream to write XML to.
   */
  public RecordSetWriter(OutputStream outputStream) {
    this(outputStream, Gedcomx.class, RecordSet.class);
  }

  /**
   * Constructor. Prepares to write GedcomX document records to the given output stream (which may well be a
   *   GZIPOutputStream), so that only one such document needs to be fully instantiated in memory at once.
   *   Only the supplied list of Classes will be included in the JAXBContext marshaller.
   * @param outputStream      - OutputStream to write XML to.
   * @param marshallerClasses - A list of Classes to include in the underlying JAXBContext marshaller.
   */
  public RecordSetWriter(OutputStream outputStream, Class... marshallerClasses) {
    this(outputStream, true, marshallerClasses);
  }

  /**
   * Constructor. Prepares to write GedcomX document records to the given output stream (which may well be a
   *   GZIPOutputStream), so that only one such document needs to be fully instantiated in memory at once.
   *   Only the supplied list of Classes will be included in the JAXBContext marshaller.
   * @param outputStream      - OutputStream to write XML to.
   * @param shouldFilter      - Flag for whether to use a CleanXMLStreamWriter to convert invalid XML characters
   *                            (such as a vertical tab) into the Unicode REPLACEMENT_CHARACTER (0xFFFD), so that
   *                            the XML will unmarshal without throwing an exception.
   * @param marshallerClasses - A list of Classes to include in the underlying JAXBContext marshaller.
   */
  protected RecordSetWriter(OutputStream outputStream, boolean shouldFilter, Class... marshallerClasses) {
    try {
      marshaller = JAXBContext.newInstance(marshallerClasses).createMarshaller();
      marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);

      this.outputStream = outputStream;
      xmlWriter = XMLOutputFactory.newFactory().createXMLStreamWriter(outputStream, "UTF-8");
      if (shouldFilter) {
        // Use a CleanXMLStreamWriter to avoid illegal XML characters in the marshalled output, such as a vertical tab character.
        xmlWriter = new CleanXMLStreamWriter(xmlWriter);
      }
      xmlWriter.setDefaultNamespace(GedcomxConstants.GEDCOMX_NAMESPACE);
      xmlWriter.writeStartDocument();
      xmlWriter.writeStartElement(GedcomxConstants.GEDCOMX_NAMESPACE, "records");
      xmlWriter.writeNamespace("", GedcomxConstants.GEDCOMX_NAMESPACE);
    }
    catch (XMLStreamException e) {
      throw new RuntimeException(e);
    }
    catch (PropertyException e) {
      throw new RuntimeException(e);
    }
    catch (JAXBException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Write the given record to the underlying byte-level (and possibly GZipped) output stream.
   * @param record - GedcomX document to add as a 'record' to the RecordSet OutputStream.
   * @throws JAXBException If there's a problem with the XML.
   */
  public synchronized void writeRecord(Gedcomx record) throws JAXBException {
    writeRecord(record, "record");
    wroteRecords = true;
  }

  /**
   * Set the 'metadata' document, which will be written after all of the records. Often used for the collection-level
   *   information such as collection source descriptions, record descriptors, etc. May describe a collection that
   *   goes beyond the records contained within this same GedcomX RecordSet.
   * @param metadata - GedcomX document with group-level information.
   * @throws JAXBException If there's a problem with the XML.
   */
  public synchronized void setMetadata(Gedcomx metadata) throws JAXBException {
    if (wroteMetadata) {
      throw new IllegalStateException("Already wrote metadata to stream. Can't change it now.");
    }
    if (wroteRecords) {
      // Already wrote some records, so hold onto the metadata document until the end.
      this.metadata = metadata;
    }
    else {
      // Haven't written records yet, so write metadata immediately, at the top of the document, for better readability.
      // (Don't set this.metadata, because it would waste memory, and we need it to be null during close() so it doesn't get written again.
      writeRecord(metadata, "metadata");
      wroteMetadata = true;
    }
  }

  private void writeRecord(Gedcomx record, String label) throws JAXBException {
    marshaller.marshal(new JAXBElement<Gedcomx>(new QName(GedcomxConstants.GEDCOMX_NAMESPACE, label), Gedcomx.class, record), xmlWriter);
  }

  /**
   * Finish writing the file, including metadata (if set), and the closing tag. Closes the writers and output stream.
   * @throws IOException If there's an I/O problem.
   */
  public synchronized void close() throws IOException {
    try {
      if (!wroteMetadata && metadata != null) {
        writeRecord(metadata, "metadata");
      }
      xmlWriter.writeEndElement();
      xmlWriter.close();
      outputStream.close();
    }
    catch (XMLStreamException e) {
      throw new RuntimeException(e);
    }
    catch (JAXBException e) {
      throw new RuntimeException(e);
    }
  }
}
