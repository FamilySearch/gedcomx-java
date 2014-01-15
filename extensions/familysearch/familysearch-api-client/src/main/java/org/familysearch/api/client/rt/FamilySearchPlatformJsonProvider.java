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
package org.familysearch.api.client.rt;

import org.familysearch.platform.FamilySearchPlatform;
import org.gedcomx.rt.GedcomxConstants;
import org.gedcomx.rt.json.GedcomJacksonModule;
import org.gedcomx.rt.json.GedcomJsonProvider;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

/**
 * @author Ryan Heaton
 */
@Provider
@Produces ( { GedcomxConstants.GEDCOMX_JSON_MEDIA_TYPE, FamilySearchPlatform.JSON_MEDIA_TYPE } )
@Consumes ( { GedcomxConstants.GEDCOMX_JSON_MEDIA_TYPE, FamilySearchPlatform.JSON_MEDIA_TYPE } )
public class FamilySearchPlatformJsonProvider extends GedcomJsonProvider {

  public FamilySearchPlatformJsonProvider() {
    this(new Class<?>[0]);
  }

  public FamilySearchPlatformJsonProvider(Class<?>... classes) {
    super(GedcomJacksonModule.createObjectMapper(classes), DEFAULT_ANNOTATIONS, FamilySearchPlatform.class, MediaType.WILDCARD_TYPE);
  }

}
