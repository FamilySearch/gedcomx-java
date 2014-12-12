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
package org.familysearch.api.client.util;

import com.sun.jersey.api.client.ClientRequest;
import org.familysearch.platform.FamilySearchPlatform;

import javax.ws.rs.core.MediaType;

/**
 * @author Ryan Heaton
 */
public class RequestUtil {

  public static ClientRequest.Builder applyFamilySearchConneg(ClientRequest.Builder request) {
    return request.accept(FamilySearchPlatform.JSON_MEDIA_TYPE).type(FamilySearchPlatform.JSON_MEDIA_TYPE);
  }

  /**
   * Add Accept and Content-Type headers for basic JSON; this is used when retrieving RDF data
   *
   * @param request the request to add the headers to
   * @return the request with Accept and Content-Type headers for basic JSON added
   */
  public static ClientRequest.Builder applyFamilySearchJson(ClientRequest.Builder request) {
    return request.accept(MediaType.APPLICATION_JSON_TYPE).type(MediaType.APPLICATION_JSON_TYPE);
  }
}
