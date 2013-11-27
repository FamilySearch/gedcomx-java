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
package org.gedcomx.rt.json;

import org.codehaus.jackson.jaxrs.Annotations;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.codehaus.jackson.map.ObjectMapper;
import org.gedcomx.Gedcomx;
import org.gedcomx.rt.GedcomxConstants;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/**
 * The custom json provider for GEDCOM JSON data.
 *
 * @author Ryan Heaton
 */
@Provider
@Produces ( GedcomxConstants.GEDCOMX_JSON_MEDIA_TYPE )
@Consumes ( GedcomxConstants.GEDCOMX_JSON_MEDIA_TYPE )
public class GedcomJsonProvider extends JacksonJaxbJsonProvider {

  private final Class<?> rootClass;
  private final Class<?> instanceClass;
  private final MediaType mt;

  public GedcomJsonProvider() {
    this(GedcomJacksonModule.createObjectMapper(), DEFAULT_ANNOTATIONS, null, MediaType.valueOf(GedcomxConstants.GEDCOMX_JSON_MEDIA_TYPE));
  }

  public GedcomJsonProvider(Class<?>... classes) {
    this(GedcomJacksonModule.createObjectMapper(classes), DEFAULT_ANNOTATIONS, null, MediaType.valueOf(GedcomxConstants.GEDCOMX_JSON_MEDIA_TYPE));
  }

  protected GedcomJsonProvider(ObjectMapper mapper, Annotations[] annotationsToUse, Class<?> instanceClass, MediaType mt) {
    super(mapper, annotationsToUse);

    this.rootClass = Gedcomx.class;
    this.mt = mt;
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
  public Object readFrom(Class<Object> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException {
    try {
      return super.readFrom((Class<Object>) this.instanceClass, this.instanceClass, annotations, mediaType, httpHeaders, entityStream);
    }
    catch (IOException e) {
      String msg = "299 Malformed payload";
      if (e.getMessage() != null)
        msg += ": " + e.getMessage();
      if (e.getCause() != null && e.getCause().getMessage() != null)
        msg += ": " + e.getCause().getMessage();
      Response response = Response.status(Response.Status.BAD_REQUEST).header("Warning", msg).build();
      throw  new WebApplicationException( e, response );
    }
  }

  @Override
  public void writeTo(Object value, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException {
    super.writeTo(value, value.getClass(), genericType, annotations, mediaType, httpHeaders, entityStream);
  }

}
