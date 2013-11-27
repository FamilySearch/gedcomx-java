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
package org.gedcomx.rs.client;

import com.sun.jersey.api.client.ClientResponse;

/**
 * @author Ryan Heaton
 */
public class GedcomxApiException extends RuntimeException {

  private ClientResponse response;

  public GedcomxApiException() {
  }

  public GedcomxApiException(String message) {
    super(message);
  }

  public GedcomxApiException(String message, ClientResponse response) {
    super(message);
    this.response = response;
  }

  public GedcomxApiException(ClientResponse response) {
    this.response = response;
  }

  public ClientResponse getResponse() {
    return response;
  }

  public ClientResponse.Status getResponseStatus() {
    return this.response == null ? null : this.response.getClientResponseStatus();
  }
}
