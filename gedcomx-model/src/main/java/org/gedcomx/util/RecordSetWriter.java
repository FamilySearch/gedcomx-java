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

import com.sun.xml.txw2.output.IndentingXMLStreamWriter;
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
 * Class for streaming a RecordSet to an OutputStream as records are being added.
 * User: Randy Wilson
 * Date: 12/4/13
 */
public class RecordSetWriter {
  Marshaller marshaller;
  private XMLStreamWriter xmlWriter;
  // Metadata, if any
  private Gedcomx metadata;

  // Stream to write data to
  private OutputStream outputStream;

  /**
   * Constructor. Prepares to write GedcomX document records to the given output stream (which may well be a
   *   GZIPOutputStream), so that only one such document needs to be fully instantiated in memory at once.
   * @param outputStream - OutputStream to write XML to.
   */
  public RecordSetWriter(OutputStream outputStream) {
    try {
      marshaller = JAXBContext.newInstance(Gedcomx.class, RecordSet.class).createMarshaller();
      marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);

      this.outputStream = outputStream;
      // Use a CleanXMLStreamWriter to avoid illegal XML characters in the marshalled output, such as a vertical tab character.
      xmlWriter = new CleanXMLStreamWriter(XMLOutputFactory.newFactory().createXMLStreamWriter(outputStream, "UTF-8"));
      xmlWriter.setDefaultNamespace(GedcomxConstants.GEDCOMX_NAMESPACE);
      xmlWriter = new IndentingXMLStreamWriter(xmlWriter);
      xmlWriter.writeStartDocument();
      xmlWriter.writeStartElement(GedcomxConstants.GEDCOMX_NAMESPACE, "records");
      xmlWriter.writeNamespace("", GedcomxConstants.GEDCOMX_NAMESPACE);
    } catch (XMLStreamException e) {
      throw new RuntimeException(e);
    } catch (PropertyException e) {
      throw new RuntimeException(e);
    } catch (JAXBException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Write the given record to the underlying byte-level (and possibly GZipped) output stream.
   * @param record - GedcomX document to add as a 'record' to the RecordSet OutputStream.
   * @throws JAXBException
   */
  public void writeRecord(Gedcomx record) throws JAXBException {
    writeRecord(record, "record");
  }

  /**
   * Set the 'metadata' document, which will be written after all of the records. Often used for the collection-level
   *   information such as collection source descriptions, record descriptors, etc. May describe a collection that
   *   goes beyond the records contained within this same GedcomX RecordSet.
   * @param metadata - GedcomX document with group-level information.
   */
  public void setMetadata(Gedcomx metadata) {
    if (metadata != null) {
      throw new IllegalStateException("Cannot have two metadata elements in a RecordSet");
    }
    this.metadata = metadata;
  }

  private void writeRecord(Gedcomx record, String label) throws JAXBException {
    marshaller.marshal(new JAXBElement<Gedcomx>(new QName(GedcomxConstants.GEDCOMX_NAMESPACE, label), Gedcomx.class, record), xmlWriter);
  }

  /**
   * Finish writing the file, including metadata (if set), and the closing tag. Closes the writers and output stream.
   * @throws IOException
   */
  public void close() throws IOException {
    try {
      if (metadata != null) {
        writeRecord(metadata, "metadata");
      }
      xmlWriter.writeEndElement();
      xmlWriter.close();
      outputStream.close();
    } catch (XMLStreamException e) {
      throw new RuntimeException(e);
    } catch (JAXBException e) {
      throw new RuntimeException(e);
    }
  }
}
