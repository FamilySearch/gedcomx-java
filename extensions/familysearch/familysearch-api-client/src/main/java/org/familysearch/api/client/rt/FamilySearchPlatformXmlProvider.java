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
import org.gedcomx.rt.xml.GedcomxXmlProvider;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.ext.Providers;
import javax.xml.namespace.QName;

/**
 * @author Ryan Heaton
 */
@Provider
@Produces ( { GedcomxConstants.GEDCOMX_XML_MEDIA_TYPE, FamilySearchPlatform.XML_MEDIA_TYPE } )
@Consumes ( { GedcomxConstants.GEDCOMX_XML_MEDIA_TYPE, FamilySearchPlatform.XML_MEDIA_TYPE } )
public class FamilySearchPlatformXmlProvider extends GedcomxXmlProvider {

  public FamilySearchPlatformXmlProvider(@Context Providers ps) {
    super(ps, MediaType.valueOf(FamilySearchPlatform.XML_MEDIA_TYPE), FamilySearchPlatform.class, new QName(FamilySearchPlatform.NAMESPACE, "familysearch"));
  }

}
