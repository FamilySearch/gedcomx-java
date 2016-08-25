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

import org.gedcomx.rs.client.util.BaseSearchQueryBuilder;

public class NameSearchQueryBuilder extends BaseSearchQueryBuilder {

  public static final String NAME = "name";
  public static final String DATE = "date";
  public static final String PLACE_ID = "placeId";
  public static final String TYPE = "type";
  public static final String GENDER = "gender";

  public NameSearchQueryBuilder param(String name, String value, boolean exact) {
    super.parameters.add(new SearchParameter(null, name, value, exact));
    return this;
  }

  public NameSearchQueryBuilder name(String value) {
    return name(value, true);
  }

  public NameSearchQueryBuilder name(String value, boolean exact) {
    return param(NAME, value, exact);
  }

  public NameSearchQueryBuilder date(String value) {
    return param(DATE, value, true);
  }

  public NameSearchQueryBuilder placeId(String value) {
    return param(PLACE_ID, value, true);
  }

  public NameSearchQueryBuilder type(String value) {
    return param(TYPE, value, true);
  }

  public NameSearchQueryBuilder gender(String value) {
    return param(GENDER, value, true);
  }

}
