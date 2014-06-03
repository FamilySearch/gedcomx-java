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
package org.gedcomx.rs.client.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GedcomxBaseSearchQueryBuilder {

  protected final List<SearchParameter> parameters = new ArrayList<SearchParameter>();

  public String build() {
    StringBuilder builder = new StringBuilder();
    Iterator<SearchParameter> it = this.parameters.iterator();
    while (it.hasNext()) {
      builder.append(it.next());
      if (it.hasNext()) {
        builder.append(' ');
      }
    }
    return builder.toString();
  }

  public static class SearchParameter {
    private final String name;
    private final String value;
    private final boolean exact;

    public SearchParameter(String name, String value, boolean exact) {
      if (name == null) {
        throw new NullPointerException("parameter name must not be null");
      }
      this.exact = exact;
      this.value = value;
      this.name = name;
    }

    public String getName() {
      return name;
    }

    public String getValue() {
      return value;
    }

    public boolean isExact() {
      return exact;
    }

    @Override
    public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append(this.name);
      if (this.value != null) {
        builder.append(':');
        String escaped = this.value.replace('\n', ' ').replace('\t', ' ').replace('\f', ' ').replace('\013', ' ').replace("\"", "\\\"");
        boolean needsQuote = escaped.indexOf(' ') != -1;
        if (needsQuote) {
          builder.append('\"');
        }
        builder.append(escaped);
        if (needsQuote) {
          builder.append('\"');
        }
        if (!this.exact) {
          builder.append('~');
        }
      }
      return builder.toString();
    }
  }

}
