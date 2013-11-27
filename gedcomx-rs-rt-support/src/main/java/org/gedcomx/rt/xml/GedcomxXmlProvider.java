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
package org.gedcomx.rt.xml;

import com.sun.jersey.core.provider.jaxb.AbstractRootElementProvider;
import org.gedcomx.Gedcomx;
import org.gedcomx.rt.GedcomxConstants;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.Providers;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.transform.stream.StreamSource;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

/**
 * JAX-RS provider for servicing requests for application/x-gedcom-v1+xml.
 *
 * @author Ryan Heaton
 */
@Provider
@Produces ( GedcomxConstants.GEDCOMX_XML_MEDIA_TYPE)
@Consumes ( GedcomxConstants.GEDCOMX_XML_MEDIA_TYPE)
public class GedcomxXmlProvider extends AbstractRootElementProvider {

  private final Class<?> rootClass;
  private final Class<?> instanceClass;
  private final MediaType mt;
  private final QName qName;

  public GedcomxXmlProvider(@Context Providers ps) {
    this(ps, MediaType.valueOf(GedcomxConstants.GEDCOMX_XML_MEDIA_TYPE), null, new QName(GedcomxConstants.GEDCOMX_NAMESPACE, "gedcomx"));
  }

  protected GedcomxXmlProvider(Providers ps, MediaType mt, Class<?> instanceClass, QName qName) {
    super(ps);
    this.rootClass = Gedcomx.class;
    this.mt = mt;
    this.qName = qName;
    this.instanceClass = instanceClass == null ? this.rootClass : instanceClass;
  }

  @Override
  public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
    return this.rootClass.isAssignableFrom(type) && this.mt.isCompatible(mediaType);
  }

  @Override
  public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
    return this.rootClass.isAssignableFrom(type) && this.mt.isCompatible(mediaType);
  }

  @Override
  protected Object readFrom(Class<Object> type, MediaType mediaType, Unmarshaller u, InputStream entityStream) throws JAXBException {
    try {
      return u.unmarshal(new StreamSource(entityStream), this.instanceClass).getValue();
    }
    catch (Exception e) {
      String msg = "299 Malformed payload";
      if (e.getMessage() != null)
        msg += ": " + e.getMessage();
      if (e.getCause() != null && e.getCause().getMessage() != null)
        msg += ": " + e.getCause().getMessage();
      Response response = Response.status(Response.Status.BAD_REQUEST).header("Warning", msg).build();
      throw new WebApplicationException( e, response );
    }
  }

  @Override
  protected void writeTo(Object t, MediaType mediaType, Charset c, Marshaller m, OutputStream entityStream) throws JAXBException {
    m.marshal(new JAXBElement(qName, t.getClass(), t), entityStream);
  }
}
