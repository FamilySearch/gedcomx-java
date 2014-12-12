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

public class GedcomxPlaceSearchQueryBuilder extends GedcomxBaseSearchQueryBuilder {

  public static final String NAME = "name";
  public static final String DATE = "date";
  public static final String PARENT_ID = "parentId";
  public static final String TYPE_ID = "typeId";
  public static final String TYPE_GROUP_ID = "typeGroupId";
  public static final String LATITUDE = "latitude";
  public static final String LONGITUDE = "longitude";
  public static final String DISTANCE = "distance";

  public GedcomxPlaceSearchQueryBuilder param(String name, String value) {
    return param(name, value, true);
  }

  public GedcomxPlaceSearchQueryBuilder param(String name, String value, boolean exact) {
    return param(null, name, value, exact);
  }

  public GedcomxPlaceSearchQueryBuilder param(String prefix, String name, String value, boolean exact) {
    super.parameters.add(new SearchParameter(null, name, value, exact));
    return this;
  }

  public GedcomxPlaceSearchQueryBuilder name(String value) {
    return name(value, true);
  }

  public GedcomxPlaceSearchQueryBuilder name(String value, boolean exact) {
    return name(value, exact, false);
  }

  public GedcomxPlaceSearchQueryBuilder name(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, NAME, value, exact);
  }

  public GedcomxPlaceSearchQueryBuilder nameNot(String value) {
    return param("-", NAME, value, false);
  }

  public GedcomxPlaceSearchQueryBuilder date(String value) {
    return date(value, true);
  }

  public GedcomxPlaceSearchQueryBuilder date(String value, boolean exact) {
    return date(value, exact, false);
  }

  public GedcomxPlaceSearchQueryBuilder date(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, DATE, value, exact);
  }

  public GedcomxPlaceSearchQueryBuilder dateNot(String value) {
    return param("-", DATE, value, false);
  }

  public GedcomxPlaceSearchQueryBuilder parentId(String value) {
    return parentId(value, true);
  }

  public GedcomxPlaceSearchQueryBuilder parentId(String value, boolean exact) {
    return parentId(value, exact, false);
  }

  public GedcomxPlaceSearchQueryBuilder parentId(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, PARENT_ID, value, exact);
  }

  public GedcomxPlaceSearchQueryBuilder parentIdNot(String value) {
    return param("-", PARENT_ID, value, false);
  }

  public GedcomxPlaceSearchQueryBuilder typeId(String value) {
    return typeId(value, true);
  }

  public GedcomxPlaceSearchQueryBuilder typeId(String value, boolean exact) {
    return typeId(value, exact, false);
  }

  public GedcomxPlaceSearchQueryBuilder typeId(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, TYPE_ID, value, exact);
  }

  public GedcomxPlaceSearchQueryBuilder typeIdNot(String value) {
    return param("-", TYPE_ID, value, false);
  }

  public GedcomxPlaceSearchQueryBuilder typeGroupId(String value) {
    return typeGroupId(value, true);
  }

  public GedcomxPlaceSearchQueryBuilder typeGroupId(String value, boolean exact) {
    return typeGroupId(value, exact, false);
  }

  public GedcomxPlaceSearchQueryBuilder typeGroupId(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, TYPE_GROUP_ID, value, exact);
  }

  public GedcomxPlaceSearchQueryBuilder typeGroupIdNot(String value) {
    return param("-", TYPE_GROUP_ID, value, false);
  }

  public GedcomxPlaceSearchQueryBuilder latitude(String value) {
    return latitude(value, true);
  }

  public GedcomxPlaceSearchQueryBuilder latitude(String value, boolean exact) {
    return latitude(value, exact, false);
  }

  public GedcomxPlaceSearchQueryBuilder latitude(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, LATITUDE, value, exact);
  }

  public GedcomxPlaceSearchQueryBuilder latitudeNot(String value) {
    return param("-", LATITUDE, value, false);
  }

  public GedcomxPlaceSearchQueryBuilder longitude(String value) {
    return longitude(value, true);
  }

  public GedcomxPlaceSearchQueryBuilder longitude(String value, boolean exact) {
    return longitude(value, exact, false);
  }

  public GedcomxPlaceSearchQueryBuilder longitude(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, LONGITUDE, value, exact);
  }

  public GedcomxPlaceSearchQueryBuilder longitudeNot(String value) {
    return param("-", LONGITUDE, value, false);
  }

  public GedcomxPlaceSearchQueryBuilder distance(String value) {
    return distance(value, true);
  }

  public GedcomxPlaceSearchQueryBuilder distance(String value, boolean exact) {
    return distance(value, exact, false);
  }

  public GedcomxPlaceSearchQueryBuilder distance(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, DISTANCE, value, exact);
  }

  public GedcomxPlaceSearchQueryBuilder distanceNot(String value) {
    return param("-", DISTANCE, value, false);
  }
}
