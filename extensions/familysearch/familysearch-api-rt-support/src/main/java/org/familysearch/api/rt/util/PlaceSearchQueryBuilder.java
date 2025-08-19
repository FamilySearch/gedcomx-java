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
package org.familysearch.api.rt.util;

import org.gedcomx.rt.util.BaseSearchQueryBuilder;

public class PlaceSearchQueryBuilder extends BaseSearchQueryBuilder {

  public static final String NAME = "name";
  public static final String PARTIAL_NAME = "partialName";
  public static final String DATE = "date";
  public static final String PARENT_ID = "parentId";
  public static final String TYPE_ID = "typeId";
  public static final String TYPE_GROUP_ID = "typeGroupId";
  public static final String LATITUDE = "latitude";
  public static final String LONGITUDE = "longitude";
  public static final String DISTANCE = "distance";
  public static final String PLACE_HINT = "placeHint";

  public PlaceSearchQueryBuilder param(String name, String value) {
    return param(name, value, true);
  }

  public PlaceSearchQueryBuilder param(String name, String value, boolean exact) {
    return param(null, name, value, exact);
  }

  public PlaceSearchQueryBuilder param(String prefix, String name, String value, boolean exact) {
    super.parameters.add(new SearchParameter(null, name, value, exact));
    return this;
  }

  public PlaceSearchQueryBuilder name(String value) {
    return name(value, true);
  }

  public PlaceSearchQueryBuilder name(String value, boolean exact) {
    return name(value, exact, false);
  }

  public PlaceSearchQueryBuilder name(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, NAME, value, exact);
  }

  public PlaceSearchQueryBuilder partialName(String value) {return param(null, PARTIAL_NAME, value, false); }

  public PlaceSearchQueryBuilder nameNot(String value) {
    return param("-", NAME, value, false);
  }

  public PlaceSearchQueryBuilder date(String value) {
    return date(value, true);
  }

  public PlaceSearchQueryBuilder date(String value, boolean exact) {
    return date(value, exact, false);
  }

  public PlaceSearchQueryBuilder date(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, DATE, value, exact);
  }

  public PlaceSearchQueryBuilder dateNot(String value) {
    return param("-", DATE, value, false);
  }

  public PlaceSearchQueryBuilder parentId(String value) {
    return parentId(value, true);
  }

  public PlaceSearchQueryBuilder parentId(String value, boolean exact) {
    return parentId(value, exact, false);
  }

  public PlaceSearchQueryBuilder parentId(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, PARENT_ID, value, exact);
  }

  public PlaceSearchQueryBuilder parentIdNot(String value) {
    return param("-", PARENT_ID, value, false);
  }

  public PlaceSearchQueryBuilder typeId(String value) {
    return typeId(value, true);
  }

  public PlaceSearchQueryBuilder typeId(String value, boolean exact) {
    return typeId(value, exact, false);
  }

  public PlaceSearchQueryBuilder typeId(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, TYPE_ID, value, exact);
  }

  public PlaceSearchQueryBuilder typeIdNot(String value) {
    return param("-", TYPE_ID, value, false);
  }

  public PlaceSearchQueryBuilder typeGroupId(String value) {
    return typeGroupId(value, true);
  }

  public PlaceSearchQueryBuilder typeGroupId(String value, boolean exact) {
    return typeGroupId(value, exact, false);
  }

  public PlaceSearchQueryBuilder typeGroupId(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, TYPE_GROUP_ID, value, exact);
  }

  public PlaceSearchQueryBuilder typeGroupIdNot(String value) {
    return param("-", TYPE_GROUP_ID, value, false);
  }

  public PlaceSearchQueryBuilder latitude(String value) {
    return latitude(value, true);
  }

  public PlaceSearchQueryBuilder latitude(String value, boolean exact) {
    return latitude(value, exact, false);
  }

  public PlaceSearchQueryBuilder latitude(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, LATITUDE, value, exact);
  }

  public PlaceSearchQueryBuilder latitudeNot(String value) {
    return param("-", LATITUDE, value, false);
  }

  public PlaceSearchQueryBuilder longitude(String value) {
    return longitude(value, true);
  }

  public PlaceSearchQueryBuilder longitude(String value, boolean exact) {
    return longitude(value, exact, false);
  }

  public PlaceSearchQueryBuilder longitude(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, LONGITUDE, value, exact);
  }

  public PlaceSearchQueryBuilder longitudeNot(String value) {
    return param("-", LONGITUDE, value, false);
  }

  public PlaceSearchQueryBuilder distance(String value) {
    return distance(value, true);
  }

  public PlaceSearchQueryBuilder distance(String value, boolean exact) {
    return distance(value, exact, false);
  }

  public PlaceSearchQueryBuilder distance(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, DISTANCE, value, exact);
  }

  public PlaceSearchQueryBuilder distanceNot(String value) {
    return param("-", DISTANCE, value, false);
  }

  public PlaceSearchQueryBuilder placeHint(String value) {
    return param(PLACE_HINT, value);
  }
}
