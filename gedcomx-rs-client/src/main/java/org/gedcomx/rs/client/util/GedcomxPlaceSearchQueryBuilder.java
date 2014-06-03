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
    super.parameters.add(new SearchParameter(name, value, exact));
    return this;
  }

  public GedcomxPlaceSearchQueryBuilder name(String value) {
    return name(value, true);
  }

  public GedcomxPlaceSearchQueryBuilder name(String value, boolean exact) {
    return param(NAME, value, exact);
  }

  public GedcomxPlaceSearchQueryBuilder date(String value) {
    return date(value, true);
  }

  public GedcomxPlaceSearchQueryBuilder date(String value, boolean exact) {
    return param(DATE, value, exact);
  }

  public GedcomxPlaceSearchQueryBuilder parentId(String value) {
    return parentId(value, true);
  }

  public GedcomxPlaceSearchQueryBuilder parentId(String value, boolean exact) {
    return param(PARENT_ID, value, exact);
  }

  public GedcomxPlaceSearchQueryBuilder typeId(String value) {
    return typeId(value, true);
  }

  public GedcomxPlaceSearchQueryBuilder typeId(String value, boolean exact) {
    return param(TYPE_ID, value, exact);
  }

  public GedcomxPlaceSearchQueryBuilder typeGroupId(String value) {
    return typeGroupId(value, true);
  }

  public GedcomxPlaceSearchQueryBuilder typeGroupId(String value, boolean exact) {
    return param(TYPE_GROUP_ID, value, exact);
  }

  public GedcomxPlaceSearchQueryBuilder latitude(String value) {
    return latitude(value, true);
  }

  public GedcomxPlaceSearchQueryBuilder latitude(String value, boolean exact) {
    return param(LATITUDE, value, exact);
  }
  public GedcomxPlaceSearchQueryBuilder longitude(String value) {
    return longitude(value, true);
  }

  public GedcomxPlaceSearchQueryBuilder longitude(String value, boolean exact) {
    return param(LONGITUDE, value, exact);
  }
  public GedcomxPlaceSearchQueryBuilder distance(String value) {
    return distance(value, true);
  }

  public GedcomxPlaceSearchQueryBuilder distance(String value, boolean exact) {
    return param(DISTANCE, value, exact);
  }
}
