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

import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.filter.ClientFilter;

public class ExperimentsFilter extends ClientFilter {

  private final String experiments;

  public ExperimentsFilter(String... experiments) {
    StringBuilder experimentsList = new StringBuilder();
    for (int i = 0; i < experiments.length; i++) {
      String experiment = experiments[i];
      experimentsList.append(experiment);
      if (i + 1 < experiments.length) {
        experimentsList.append(',');
      }
    }
    this.experiments = experimentsList.toString();
  }

  @Override
  public ClientResponse handle(ClientRequest clientRequest) throws ClientHandlerException {
    clientRequest.getHeaders().add("X-FS-Feature-Tag", this.experiments);
    return getNext().handle(clientRequest);
  }
}
