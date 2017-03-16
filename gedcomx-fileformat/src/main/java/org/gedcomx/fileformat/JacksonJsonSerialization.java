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
package org.gedcomx.fileformat;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.gedcomx.Gedcomx;
import org.gedcomx.rt.json.GedcomJacksonModule;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * A class for creating instances of <code>JAXBContext</code> appropriate for reading and writing GEDCOM X files.
 */
public class JacksonJsonSerialization implements GedcomxEntrySerializer, GedcomxEntryDeserializer {

  private final ObjectMapper mapper;

  public JacksonJsonSerialization(Class<?>... classes) {
    this(true, classes);
  }

  public JacksonJsonSerialization(boolean pretty, Class<?>... classes) {
    this(createObjectMapper(pretty, classes));
  }

  public JacksonJsonSerialization(ObjectMapper mapper) {
    this.mapper = mapper;
  }

  public static ObjectMapper createObjectMapper(boolean pretty, Class<?>... classes) {
    return GedcomJacksonModule.createObjectMapper(classes);
  }

  @Override
  public Object deserialize(InputStream in, String mediaType) throws IOException {
    Class<?> clazz = findClass(mediaType);
    return clazz == null ? in : this.mapper.readValue(in, clazz);
  }

  protected Class<?> findClass(String mediaType) {
    return mediaType.endsWith("json") ? Gedcomx.class : null;
  }

  @Override
  public void serialize(Object resource, OutputStream out) throws IOException {
    JsonGenerator generator = this.mapper.getFactory().createGenerator(out); //we're creating a generator so that the stream doesn't get auto-closed
    this.mapper.writeValue(generator, resource);
  }

  @Override
  public String suggestFilenameExtension() {
    return ".json";
  }

}
