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
package org.gedcomx.rs.client.options;

import com.sun.jersey.api.client.ClientRequest;
import org.gedcomx.rs.client.GedcomxApplicationState;
import org.gedcomx.rs.client.StateTransitionOption;

import java.util.Date;

public class Preconditions implements StateTransitionOption {

  private final String etag;
  private final Date lastModified;

  public Preconditions(GedcomxApplicationState state) {
    this(state.getETag().toString(), state.getLastModified());
  }

  public Preconditions(Date lastModified) {
    this(null, lastModified);
  }

  public Preconditions(String etag) {
    this(etag, null);
  }

  public Preconditions(String etag, Date lastModified) {
    this.etag = etag;
    this.lastModified = lastModified;
  }

  @Override
  public void apply(ClientRequest request) {
    if (this.etag != null) {
      request.getHeaders().putSingle(HeaderParameter.IF_MATCH, this.etag);
    }

    if (this.lastModified != null) {
      request.getHeaders().putSingle(HeaderParameter.IF_UNMODIFIED_SINCE, this.lastModified);
    }
  }

}
