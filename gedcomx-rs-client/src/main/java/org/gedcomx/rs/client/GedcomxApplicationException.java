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
import org.gedcomx.rs.client.util.HttpWarning;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ryan Heaton
 */
public class GedcomxApplicationException extends RuntimeException {

  private ClientResponse response;

  public GedcomxApplicationException() {
  }

  public GedcomxApplicationException(String message) {
    super(message);
  }

  public GedcomxApplicationException(Throwable cause) {
    super(cause);
  }

  public GedcomxApplicationException(String message, ClientResponse response) {
    super(message);
    this.response = response;
  }

  public GedcomxApplicationException(ClientResponse response) {
    this.response = response;
  }

  public ClientResponse getResponse() {
    return response;
  }

  public ClientResponse.Status getResponseStatus() {
    return this.response == null ? null : this.response.getClientResponseStatus();
  }

  public List<HttpWarning> getWarnings() {
    ArrayList<HttpWarning> warnings = null;

    if (this.response != null) {
      List<String> values = this.response.getHeaders().get("Warning");
      if (values != null && !values.isEmpty()) {
        warnings = new ArrayList<HttpWarning>(values.size());
        for (String value : values) {
          warnings.add(HttpWarning.parse(value));
        }
      }
    }

    return warnings;
  }

  @Override
  public String getMessage() {
    String message = super.getMessage();
    List<HttpWarning> warnings = getWarnings();
    if (message != null || warnings.size() > 0) {
      StringBuilder builder = new StringBuilder(message == null ? "" : message);
      if(warnings != null){
        for (HttpWarning warning : warnings) {
          builder.append(" Warning: ").append(warning.getMessage());
        }
      }
      message = builder.toString();
    }
    return message;
  }
}
