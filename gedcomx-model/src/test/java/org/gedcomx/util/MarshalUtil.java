package org.gedcomx.util;

import org.gedcomx.Gedcomx;
import org.gedcomx.records.RecordSet;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import java.io.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * Convenience class for converting a GedcomX document or RecordSet to and from XML.
 * User: Randy Wilson
 * Date: 7/31/2014
 * Time: 2:17 PM
 */
public class MarshalUtil {
  private static JAXBContext jxbc;

  static {
    try {
      jxbc = JAXBContext.newInstance(org.gedcomx.Gedcomx.class, org.gedcomx.records.RecordSet.class);
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  private static Marshaller getMarshaller(boolean prettyFormat) throws JAXBException {
    Marshaller m = jxbc.createMarshaller();
    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, prettyFormat);
    try {
      m.setProperty("com.sun.xml.bind.indentString", "  ");
    } catch (Exception e) {
      // Ignore if not supported by the JAXB implementation
    }
    return m;
  }

  public static Unmarshaller createUnmarshaller() throws JAXBException {
    return jxbc.createUnmarshaller();
  }

  /**
   * Output a RecordSet to an OutputStream as XML
   * @param outputStream - OutputStream to write the RecordSet to
   * @param recordSet - RecordSet to write
   * @param prettyFormat - flag for whether to use newlines and indentation in the output
   * @throws JAXBException
   */
  public static void output(OutputStream outputStream, RecordSet recordSet, boolean prettyFormat) throws JAXBException {
    getMarshaller(prettyFormat).marshal(recordSet, outputStream);
  }

  /**
   * Output a Gedcomx document to an OutputStream as XML
   * @param outputStream - OutputStream to write the record to
   * @param doc - GedcomX document to write
   * @param prettyFormat - flag for whether to use newlines and indentation in the output
   * @throws JAXBException
   */
  public static void output(OutputStream outputStream, Gedcomx doc, boolean prettyFormat) throws JAXBException {
    getMarshaller(prettyFormat).marshal(doc, outputStream);
  }

  /**
   * Unmarshal a Gedcomx document from an InputStream
   * @param inputStream - InputStream to read Gedcomx document from
   * @return Gedcomx document
   * @throws JAXBException
   */
  public static Gedcomx unmarshal(InputStream inputStream) throws JAXBException {
    return (Gedcomx)createUnmarshaller().unmarshal(inputStream);
  }

  /**
   * Convert a GedcomX document to XML (with newlines and indentation)
   * @param doc - GedcomX document to convert
   * @return XML string representing the GedcomX document
   */
  public static String toXml(Gedcomx doc) {
    try {
      StringWriter sw = new StringWriter();
      getMarshaller(false).marshal(doc, sw); // Disable JAXB pretty-printing
      return formatXml(sw.toString(), 2);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Convert a GedcomX RecordSet to XML (with newlines and indentation)
   * @param recordSet - GedcomX RecordSet to convert
   * @return XML string representing the GedcomX RecordSet
   */
  public static String toXml(RecordSet recordSet) {
    try {
      StringWriter sw = new StringWriter();
      getMarshaller(false).marshal(recordSet, sw); // Disable JAXB pretty-printing
      return formatXml(sw.toString(), 2);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Utility to format XML with a specified indent (in spaces).
   */
  private static String formatXml(String xml, int indent) throws Exception {
    TransformerFactory factory = TransformerFactory.newInstance();
    try {
      factory.setAttribute("indent-number", indent);
    } catch (IllegalArgumentException ignored) {
      // Some factories do not support this attribute
    }
    Transformer transformer = factory.newTransformer();
    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
    transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
    StringWriter writer = new StringWriter();
    transformer.transform(new StreamSource(new StringReader(xml)), new StreamResult(writer));
    // Collapse multiple newlines into a single newline to avoid extra blank lines
    return writer.toString().replaceAll("(\r?\n){2,}", "\n");
  }
}
