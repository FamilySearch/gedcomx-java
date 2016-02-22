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

import javax.xml.namespace.NamespaceContext;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Delegating {@link XMLStreamWriter} that filters out UTF-8 characters that
 * are illegal in XML, replacing them with a
 *
 * @author Erik van Zijst (small change by Lennart Schedin, and
 *         isLegalXmlCharacter() expanded to XML 1.0 spec by Randy Wilson)
 */
public class CleanXMLStreamWriter implements XMLStreamWriter {

  private final XMLStreamWriter writer;
  // Unicode "REPLACEMENT_CHARACTER" (looks like a black diamond with a white question mark in the middle).
  public static final char REPLACEMENT_CHARACTER = '\uFFFD';

  public CleanXMLStreamWriter(XMLStreamWriter writer) {
    if (null == writer) {
      throw new IllegalArgumentException("null");
    }
    else {
      this.writer = writer;
    }
  }

  /**
   * Substitutes all illegal characters in the given string by the value of
   * {@link CleanXMLStreamWriter#REPLACEMENT_CHARACTER}.  If no illegal characters
   * were found, no copy is made and the given string is returned.
   *
   * @param string the string
   * @return same string, if not illegal characters detected;
   *         otherwise, string with illegal characters replaced with {@link CleanXMLStreamWriter#REPLACEMENT_CHARACTER}
   */
  protected static String escapeCharacters(String string) {
    char[] copy = null;
    boolean copied = false;
    for (int i = 0; i < string.length();) {
      int codePoint = string.codePointAt(i);
      int size = Character.charCount(codePoint);
      if (!isLegalXmlCodePoint(codePoint)) {
        if (!copied) {
          copy = string.toCharArray();
          copied = true;
        }
        for (int j = 0; j < size; j++) {
          copy[i + j] = REPLACEMENT_CHARACTER;
        }
      }
      i += size;
    }
    return copied ? new String(copy) : string;
  }

  public void writeStartElement(String s) throws XMLStreamException {
    writer.writeStartElement(s);
  }

  public void writeStartElement(String s, String s1) throws XMLStreamException {
    writer.writeStartElement(s, s1);
  }

  public void writeStartElement(String s, String s1, String s2)
          throws XMLStreamException {
    writer.writeStartElement(s, s1, s2);
  }

  public void writeEmptyElement(String s, String s1) throws XMLStreamException {
    writer.writeEmptyElement(s, s1);
  }

  public void writeEmptyElement(String s, String s1, String s2)
          throws XMLStreamException {
    writer.writeEmptyElement(s, s1, s2);
  }

  public void writeEmptyElement(String s) throws XMLStreamException {
    writer.writeEmptyElement(s);
  }

  public void writeEndElement() throws XMLStreamException {
    writer.writeEndElement();
  }

  public void writeEndDocument() throws XMLStreamException {
    writer.writeEndDocument();
  }

  public void close() throws XMLStreamException {
    writer.close();
  }

  public void flush() throws XMLStreamException {
    writer.flush();
  }

  public void writeAttribute(String localName, String value) throws XMLStreamException {
    writer.writeAttribute(localName, escapeCharacters(value));
  }

  public void writeAttribute(String prefix, String namespaceUri, String localName, String value)
          throws XMLStreamException {
    writer.writeAttribute(prefix, namespaceUri, localName, escapeCharacters(value));
  }

  public void writeAttribute(String namespaceUri, String localName, String value)
          throws XMLStreamException {
    writer.writeAttribute(namespaceUri, localName, escapeCharacters(value));
  }

  public void writeNamespace(String s, String s1) throws XMLStreamException {
    writer.writeNamespace(s, s1);
  }

  public void writeDefaultNamespace(String s) throws XMLStreamException {
    writer.writeDefaultNamespace(s);
  }

  public void writeComment(String s) throws XMLStreamException {
    writer.writeComment(s);
  }

  public void writeProcessingInstruction(String s) throws XMLStreamException {
    writer.writeProcessingInstruction(s);
  }

  public void writeProcessingInstruction(String s, String s1)
          throws XMLStreamException {
    writer.writeProcessingInstruction(s, s1);
  }

  public void writeCData(String s) throws XMLStreamException {
    writer.writeCData(escapeCharacters(s));
  }

  public void writeDTD(String s) throws XMLStreamException {
    writer.writeDTD(s);
  }

  public void writeEntityRef(String s) throws XMLStreamException {
    writer.writeEntityRef(s);
  }

  public void writeStartDocument() throws XMLStreamException {
    writer.writeStartDocument();
  }

  public void writeStartDocument(String s) throws XMLStreamException {
    writer.writeStartDocument(s);
  }

  public void writeStartDocument(String s, String s1)
          throws XMLStreamException {
    writer.writeStartDocument(s, s1);
  }

  public void writeCharacters(String s) throws XMLStreamException {
    writer.writeCharacters(escapeCharacters(s));
  }

  public void writeCharacters(char[] chars, int start, int len)
          throws XMLStreamException {
    writer.writeCharacters(escapeCharacters(new String(chars, start, len)));
  }

  public String getPrefix(String s) throws XMLStreamException {
    return writer.getPrefix(s);
  }

  public void setPrefix(String s, String s1) throws XMLStreamException {
    writer.setPrefix(s, s1);
  }

  public void setDefaultNamespace(String s) throws XMLStreamException {
    writer.setDefaultNamespace(s);
  }

  public void setNamespaceContext(NamespaceContext namespaceContext)
          throws XMLStreamException {
    writer.setNamespaceContext(namespaceContext);
  }

  public NamespaceContext getNamespaceContext() {
    return writer.getNamespaceContext();
  }

  public Object getProperty(String s) throws IllegalArgumentException {
    return writer.getProperty(s);
  }

  /**
   * Tell whether the given character is valid in an XML document.
   *
   * @param c - character
   * @return true if valid in XML, false if it would make an XML invalid.
   */
  protected static boolean isLegalXmlCodePoint(int c) {
    /* From the XML 1.0 specifications document at http://www.w3.org/TR/REC-xml/#charsets:
         Char :: = #x9 | #xA | #xD | [#x20-#xD7FF] | [#xE000-#xFFFD] | [#x10000-#x10FFFF]
      */
    return (c >= 0x20 && c <= 0xD7FF) ||
            (c >= 0xE000 && c <= 0xFFFD) ||
            (c >= 0x10000 && c <= 0x10FFFF) ||
            (c == 0x09 || c == 0x0A || c == 0x0D);
    /* The XML spec also discouraged use of the following, though they were not forbidden,
       because they were either control characters or permanently undefined Unicode characters:
        [#x7F-#x84], [#x86-#x9F], [#xFDD0-#xFDEF],
        [#x1FFFE-#x1FFFF], [#x2FFFE-#x2FFFF], [#x3FFFE-#x3FFFF],
        [#x4FFFE-#x4FFFF], [#x5FFFE-#x5FFFF], [#x6FFFE-#x6FFFF],
        [#x7FFFE-#x7FFFF], [#x8FFFE-#x8FFFF], [#x9FFFE-#x9FFFF],
        [#xAFFFE-#xAFFFF], [#xBFFFE-#xBFFFF], [#xCFFFE-#xCFFFF],
        [#xDFFFE-#xDFFFF], [#xEFFFE-#xEFFFF], [#xFFFFE-#xFFFFF],
        [#x10FFFE-#x10FFFF].
       Since they are not forbidden, however, this reader will not make the decision to get rid of them.
     */
  }
}
