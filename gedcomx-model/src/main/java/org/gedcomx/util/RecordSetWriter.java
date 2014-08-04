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

  // Stream to write data to
  private OutputStream outputStream;

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

  public void writeRecord(Gedcomx record) throws JAXBException {
    marshaller.marshal(new JAXBElement<Gedcomx>(new QName(GedcomxConstants.GEDCOMX_NAMESPACE, "record"), Gedcomx.class, record), xmlWriter);
  }

  public void close() throws IOException {
    try {
      xmlWriter.writeEndElement();
      xmlWriter.close();
      outputStream.close();
    } catch (XMLStreamException e) {
      throw new RuntimeException(e);
    }
  }
}
