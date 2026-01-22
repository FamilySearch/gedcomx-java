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
package org.gedcomx.rt;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.stream.StreamSource;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchema;
import org.gedcomx.rt.json.GedcomJacksonModule;
import org.w3c.dom.Document;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.databind.node.ObjectNode;

/**
 * @author Ryan Heaton
 */
public class SerializationUtil {

  @SuppressWarnings ( {"unchecked"} )
  public static <C> C processThroughXml(Object reference, SerializationProcessListener... listeners) throws JAXBException, UnsupportedEncodingException {
    return (C) processThroughXml(reference, reference.getClass(), listeners);
  }

  public static <C> C processThroughXml(Object reference, Class<? extends C> instanceClass, SerializationProcessListener... listeners) throws JAXBException, UnsupportedEncodingException {
    return processThroughXml(reference, instanceClass, JAXBContext.newInstance(instanceClass), listeners);
  }

  public static <C> C processThroughXml(Object reference, Class<? extends C> instanceClass, JAXBContext context, SerializationProcessListener... listeners) throws JAXBException, UnsupportedEncodingException {
    byte[] out = toXmlStream(reference, instanceClass, context, listeners);
    JAXBElement<? extends C> element = context.createUnmarshaller().unmarshal(new StreamSource(new ByteArrayInputStream(out)), instanceClass);
    return element.getValue();
  }

  public static byte[] toXmlStream(Object reference, SerializationProcessListener... listeners) throws JAXBException {
    return toXmlStream(reference, reference.getClass(), listeners);
  }

  public static void writeXml(Object reference, OutputStream out) throws JAXBException {
    writeXml(reference, reference.getClass(), out);
  }

  public static <C> byte[] toXmlStream(Object reference, Class<? extends C> instanceClass, SerializationProcessListener... listeners) throws JAXBException {
    return toXmlStream(reference, instanceClass, JAXBContext.newInstance(instanceClass), listeners);
  }

  public static <C> void writeXml(Object reference, Class<? extends C> instanceClass, OutputStream out) throws JAXBException {
    writeXml(reference, instanceClass, JAXBContext.newInstance(instanceClass), out);
  }

  public static <C> byte[] toXmlStream(Object reference, Class<? extends C> instanceClass, JAXBContext context, SerializationProcessListener... listeners) throws JAXBException {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    writeXml(reference, instanceClass, context, out);
    if ("true".equals(System.getProperty("show.output"))) {
      System.out.println(out.toString(StandardCharsets.UTF_8));
    }
    if (listeners != null && listeners.length > 0) {
      String xml = out.toString(StandardCharsets.UTF_8);
      for (SerializationProcessListener listener : listeners) {
        listener.xmlProcessed(reference, instanceClass, context, xml);
      }
    }
    return out.toByteArray();
  }

  @SuppressWarnings ( {"unchecked"} )
  public static <C> void writeXml(Object reference, Class<? extends C> instanceClass, JAXBContext context, OutputStream out) throws JAXBException {
    Marshaller marshaller = context.createMarshaller();
    marshaller.setProperty("jaxb.formatted.output", Boolean.TRUE);
    Object el = isRootElement(instanceClass) ? reference : null;
    if (el == null) {
      String ns = "";
      if (instanceClass.getPackage() != null && instanceClass.getPackage().getAnnotation(XmlSchema.class) != null) {
        ns = instanceClass.getPackage().getAnnotation(XmlSchema.class).namespace();
      }
      el = new JAXBElement(new QName(ns, instanceClass.getSimpleName()), instanceClass, reference);
    }
    marshaller.setProperty("org.glassfish.jaxb.namespacePrefixMapper", new GedcomNamespaceManager(instanceClass));
    marshaller.marshal(el, out);
  }

  private static <C> boolean isRootElement(Class<? extends C> instanceClass) {
    return instanceClass.isAnnotationPresent(XmlRootElement.class) || JAXBElement.class.isAssignableFrom(instanceClass);
  }

  public static Document toXmlDom(Object reference) throws JAXBException {
    return toXmlDom(reference, reference.getClass());
  }

  public static Document toXmlDom(Object reference, Class<?> instanceClass) throws JAXBException {
    byte[] out = toXmlStream(reference, instanceClass);
    try {
      DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
      builderFactory.setNamespaceAware(true);
      DocumentBuilder builder = builderFactory.newDocumentBuilder();
      return builder.parse(new ByteArrayInputStream(out));
    }
    catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static Document toXmlDom(Object reference, Class<?> instanceClass, JAXBContext context) throws JAXBException {
    byte[] out = toXmlStream(reference, instanceClass, context);
    try {
      return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(out));
    }
    catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @SuppressWarnings ( {"unchecked"} )
  public static <C> C processThroughJson(Object reference, SerializationProcessListener... listeners) {
    return (C) processThroughJson(reference, reference.getClass(), listeners);
  }

  public static <C> C processThroughJson(Object reference, Class<? extends C> instanceClass, SerializationProcessListener... listeners) {
    return processThroughJson(reference, instanceClass, GedcomJacksonModule.createJsonMapper(instanceClass), listeners);
  }

  public static <C> C processThroughJson(Object reference, Class<? extends C> instanceClass, JsonMapper mapper, SerializationProcessListener... listeners) {
    byte[] buffer = toJsonStream(reference, instanceClass, mapper, listeners);
    return mapper.readValue(new ByteArrayInputStream(buffer), instanceClass);
  }

  public static byte[] toJsonStream(Object reference, SerializationProcessListener... listeners) {
    return toJsonStream(reference, reference.getClass(), listeners);
  }

  public static void writeJson(Object reference, OutputStream out) {
    writeJson(reference, reference.getClass(), out);
  }

  public static <C> byte[] toJsonStream(Object reference, Class<? extends C> instanceClass, SerializationProcessListener... listeners) {
    return toJsonStream(reference, instanceClass, GedcomJacksonModule.createJsonMapper(instanceClass), listeners);
  }

  public static <C> void writeJson(Object reference, Class<? extends C> instanceClass, OutputStream out) {
    writeJson(reference, instanceClass, GedcomJacksonModule.createJsonMapper(instanceClass), out);
  }

  protected static <C> byte[] toJsonStream(Object reference, Class<? extends C> instanceClass, JsonMapper mapper, SerializationProcessListener... listeners) {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    writeJson(reference, instanceClass, mapper, out);
    if ("true".equals(System.getProperty("show.output"))) {
      System.out.println(out.toString(StandardCharsets.UTF_8));
    }

    if (listeners != null && listeners.length > 0) {
      String json = out.toString(StandardCharsets.UTF_8);
      for (SerializationProcessListener listener : listeners) {
        listener.jsonProcessed(reference, instanceClass, mapper, json);
      }
    }

    return out.toByteArray();
  }

  public static <C> void writeJson(Object reference, Class<? extends C> instanceClass, JsonMapper mapper, OutputStream out) {
    GedcomNamespaceManager.registerKnownJsonType(instanceClass);
    mapper.rebuild()
      .enable(SerializationFeature.INDENT_OUTPUT)
      .changeDefaultPropertyInclusion(
        incl -> JsonInclude.Value.construct(JsonInclude.Include.NON_NULL, JsonInclude.Include.NON_NULL))
      .build()
      .writeValue(out, reference);
  }

  public static ObjectNode toJsonNode(Object reference) {
    return toJsonNode(reference, reference.getClass());
  }

  public static ObjectNode toJsonNode(Object reference, Class<?> instanceClass) {
    return toJsonNode(reference, instanceClass, GedcomJacksonModule.createJsonMapper(instanceClass));
  }

  public static ObjectNode toJsonNode(Object reference, Class<?> instanceClass, JsonMapper mapper) {
    byte[] out = toJsonStream(reference, instanceClass, mapper);
    return mapper.readValue(new ByteArrayInputStream(out), ObjectNode.class);
  }

}
