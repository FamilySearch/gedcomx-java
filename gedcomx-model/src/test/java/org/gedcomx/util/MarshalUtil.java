package org.gedcomx.util;

import org.gedcomx.Gedcomx;
import org.gedcomx.records.RecordSet;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

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
    m.setProperty("com.sun.xml.bind.indentString", "  ");
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
   * @throws java.io.IOException
   */
  public static void output(OutputStream outputStream, RecordSet recordSet, boolean prettyFormat) throws JAXBException, IOException {
    getMarshaller(prettyFormat).marshal(recordSet, outputStream);
  }

  /**
   * Output a Gedcomx document to an OutputStream as XML
   * @param outputStream - OutputStream to write the record to
   * @param doc - GedcomX document to write
   * @param prettyFormat - flag for whether to use newlines and indentation in the output
   * @throws JAXBException
   * @throws IOException
   */
  public static void output(OutputStream outputStream, Gedcomx doc, boolean prettyFormat) throws JAXBException, IOException {
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
      getMarshaller(true).marshal(doc, sw);
      return sw.toString();
    } catch (JAXBException e) {
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
      getMarshaller(true).marshal(recordSet, sw);
      return sw.toString();
    } catch (JAXBException e) {
      e.printStackTrace();
    }
    return null;
  }
}
