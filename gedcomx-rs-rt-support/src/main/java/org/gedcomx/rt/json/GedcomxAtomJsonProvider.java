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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.cfg.Annotations;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.gedcomx.atom.AtomModel;
import org.gedcomx.atom.Feed;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
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
@Produces ( AtomModel.ATOM_GEDCOMX_JSON_MEDIA_TYPE )
@Consumes ( AtomModel.ATOM_GEDCOMX_JSON_MEDIA_TYPE )
public class GedcomxAtomJsonProvider extends JacksonJaxbJsonProvider {

  private final Class<?> rootClass;
  private final Class<?> instanceClass;
  private final MediaType mt;

  public GedcomxAtomJsonProvider() {
    this(GedcomJacksonModule.createObjectMapper(), DEFAULT_ANNOTATIONS, null, MediaType.valueOf(AtomModel.ATOM_GEDCOMX_JSON_MEDIA_TYPE));
  }

  public GedcomxAtomJsonProvider(Class<?>... classes) {
    this(GedcomJacksonModule.createObjectMapper(classes), DEFAULT_ANNOTATIONS, null, MediaType.valueOf(AtomModel.ATOM_GEDCOMX_JSON_MEDIA_TYPE));
  }

  protected GedcomxAtomJsonProvider(ObjectMapper mapper, Annotations[] annotationsToUse, Class<?> instanceClass, MediaType mt) {
    super(mapper, annotationsToUse);

    this.rootClass = Feed.class;
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
