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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Ryan Heaton
 */
public class GedcomxSearchQueryBuilder {

  public static final String NAME = "name";
  public static final String GIVEN_NAME = "givenName";
  public static final String SURNAME = "surname";
  public static final String GENDER = "gender";
  public static final String BIRTH_DATE = "birthDate";
  public static final String BIRTH_PLACE = "birthPlace";
  public static final String DEATH_DATE = "deathDate";
  public static final String DEATH_PLACE = "deathPlace";
  public static final String MARRIAGE_DATE = "marriageDate";
  public static final String MARRIAGE_PLACE = "marriagePlace";
  public static final String FATHER_NAME = "fatherName";
  public static final String FATHER_GIVEN_NAME = "fatherGivenName";
  public static final String FATHER_SURNAME = "fatherSurname";
  public static final String FATHER_GENDER = "fatherGender";
  public static final String FATHER_BIRTH_DATE = "fatherBirthDate";
  public static final String FATHER_BIRTH_PLACE = "fatherBirthPlace";
  public static final String FATHER_DEATH_DATE = "fatherDeathDate";
  public static final String FATHER_DEATH_PLACE = "fatherDeathPlace";
  public static final String FATHER_MARRIAGE_DATE = "fatherMarriageDate";
  public static final String FATHER_MARRIAGE_PLACE = "fatherMarriagePlace";
  public static final String MOTHER_NAME = "motherName";
  public static final String MOTHER_GIVEN_NAME = "motherGivenName";
  public static final String MOTHER_SURNAME = "motherSurname";
  public static final String MOTHER_GENDER = "motherGender";
  public static final String MOTHER_BIRTH_DATE = "motherBirthDate";
  public static final String MOTHER_BIRTH_PLACE = "motherBirthPlace";
  public static final String MOTHER_DEATH_DATE = "motherDeathDate";
  public static final String MOTHER_DEATH_PLACE = "motherDeathPlace";
  public static final String MOTHER_MARRIAGE_DATE = "motherMarriageDate";
  public static final String MOTHER_MARRIAGE_PLACE = "motherMarriagePlace";
  public static final String SPOUSE_NAME = "spouseName";
  public static final String SPOUSE_GIVEN_NAME = "spouseGivenName";
  public static final String SPOUSE_SURNAME = "spouseSurname";
  public static final String SPOUSE_GENDER = "spouseGender";
  public static final String SPOUSE_BIRTH_DATE = "spouseBirthDate";
  public static final String SPOUSE_BIRTH_PLACE = "spouseBirthPlace";
  public static final String SPOUSE_DEATH_DATE = "spouseDeathDate";
  public static final String SPOUSE_DEATH_PLACE = "spouseDeathPlace";
  public static final String SPOUSE_MARRIAGE_DATE = "spouseMarriageDate";
  public static final String SPOUSE_MARRIAGE_PLACE = "spouseMarriagePlace";
  public static final String PARENT_NAME = "parentName";
  public static final String PARENT_GIVEN_NAME = "parentGivenName";
  public static final String PARENT_SURNAME = "parentSurname";
  public static final String PARENT_GENDER = "parentGender";
  public static final String PARENT_BIRTH_DATE = "parentBirthDate";
  public static final String PARENT_BIRTH_PLACE = "parentBirthPlace";
  public static final String PARENT_DEATH_DATE = "parentDeathDate";
  public static final String PARENT_DEATH_PLACE = "parentDeathPlace";
  public static final String PARENT_MARRIAGE_DATE = "parentMarriageDate";
  public static final String PARENT_MARRIAGE_PLACE = "parentMarriagePlace";

  private final List<SearchParameter> parameters = new ArrayList<SearchParameter>();

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
  
  public GedcomxSearchQueryBuilder param(String name, String value) {
    return param(name, value, false);
  }

  public GedcomxSearchQueryBuilder param(String name, String value, boolean exact) {
    this.parameters.add(new SearchParameter(name, value, exact));
    return this;
  }

  public GedcomxSearchQueryBuilder name(String value) {
    return name(value, false);
  }

  public GedcomxSearchQueryBuilder name(String value, boolean exact) {
    return param(NAME, value, exact);
  }

  public GedcomxSearchQueryBuilder givenName(String value) {
    return givenName(value, false);
  }

  public GedcomxSearchQueryBuilder givenName(String value, boolean exact) {
    return param(GIVEN_NAME, value, exact);
  }

  public GedcomxSearchQueryBuilder surname(String value) {
    return surname(value, false);
  }

  public GedcomxSearchQueryBuilder surname(String value, boolean exact) {
    return param(SURNAME, value, exact);
  }

  public GedcomxSearchQueryBuilder gender(String value) {
    return gender(value, false);
  }

  public GedcomxSearchQueryBuilder gender(String value, boolean exact) {
    return param(GENDER, value, exact);
  }

  public GedcomxSearchQueryBuilder birthDate(String value) {
    return birthDate(value, false);
  }

  public GedcomxSearchQueryBuilder birthDate(String value, boolean exact) {
    return param(BIRTH_DATE, value, exact);
  }

  public GedcomxSearchQueryBuilder birthPlace(String value) {
    return birthPlace(value, false);
  }

  public GedcomxSearchQueryBuilder birthPlace(String value, boolean exact) {
    return param(BIRTH_PLACE, value, exact);
  }

  public GedcomxSearchQueryBuilder deathDate(String value) {
    return deathDate(value, false);
  }

  public GedcomxSearchQueryBuilder deathDate(String value, boolean exact) {
    return param(DEATH_DATE, value, exact);
  }

  public GedcomxSearchQueryBuilder deathPlace(String value) {
    return deathPlace(value, false);
  }

  public GedcomxSearchQueryBuilder deathPlace(String value, boolean exact) {
    return param(DEATH_PLACE, value, exact);
  }

  public GedcomxSearchQueryBuilder marriageDate(String value) {
    return marriageDate(value, false);
  }

  public GedcomxSearchQueryBuilder marriageDate(String value, boolean exact) {
    return param(MARRIAGE_DATE, value, exact);
  }

  public GedcomxSearchQueryBuilder marriagePlace(String value) {
    return marriagePlace(value, false);
  }

  public GedcomxSearchQueryBuilder marriagePlace(String value, boolean exact) {
    return param(MARRIAGE_PLACE, value, exact);
  }

  public GedcomxSearchQueryBuilder fatherName(String value) {
    return fatherName(value, false);
  }

  public GedcomxSearchQueryBuilder fatherName(String value, boolean exact) {
    return param(FATHER_NAME, value, exact);
  }

  public GedcomxSearchQueryBuilder fatherGivenName(String value) {
    return fatherGivenName(value, false);
  }

  public GedcomxSearchQueryBuilder fatherGivenName(String value, boolean exact) {
    return param(FATHER_GIVEN_NAME, value, exact);
  }

  public GedcomxSearchQueryBuilder fatherSurname(String value) {
    return fatherSurname(value, false);
  }

  public GedcomxSearchQueryBuilder fatherSurname(String value, boolean exact) {
    return param(FATHER_SURNAME, value, exact);
  }

  public GedcomxSearchQueryBuilder fatherGender(String value) {
    return fatherGender(value, false);
  }

  public GedcomxSearchQueryBuilder fatherGender(String value, boolean exact) {
    return param(FATHER_GENDER, value, exact);
  }

  public GedcomxSearchQueryBuilder fatherBirthDate(String value) {
    return fatherBirthDate(value, false);
  }

  public GedcomxSearchQueryBuilder fatherBirthDate(String value, boolean exact) {
    return param(FATHER_BIRTH_DATE, value, exact);
  }

  public GedcomxSearchQueryBuilder fatherBirthPlace(String value) {
    return fatherBirthPlace(value, false);
  }

  public GedcomxSearchQueryBuilder fatherBirthPlace(String value, boolean exact) {
    return param(FATHER_BIRTH_PLACE, value, exact);
  }

  public GedcomxSearchQueryBuilder fatherDeathDate(String value) {
    return fatherDeathDate(value, false);
  }

  public GedcomxSearchQueryBuilder fatherDeathDate(String value, boolean exact) {
    return param(FATHER_DEATH_DATE, value, exact);
  }

  public GedcomxSearchQueryBuilder fatherDeathPlace(String value) {
    return fatherDeathPlace(value, false);
  }

  public GedcomxSearchQueryBuilder fatherDeathPlace(String value, boolean exact) {
    return param(FATHER_DEATH_PLACE, value, exact);
  }

  public GedcomxSearchQueryBuilder fatherMarriageDate(String value) {
    return fatherMarriageDate(value, false);
  }

  public GedcomxSearchQueryBuilder fatherMarriageDate(String value, boolean exact) {
    return param(FATHER_MARRIAGE_DATE, value, exact);
  }

  public GedcomxSearchQueryBuilder fatherMarriagePlace(String value) {
    return fatherMarriagePlace(value, false);
  }

  public GedcomxSearchQueryBuilder fatherMarriagePlace(String value, boolean exact) {
    return param(FATHER_MARRIAGE_PLACE, value, exact);
  }

  public GedcomxSearchQueryBuilder motherName(String value) {
    return motherName(value, false);
  }

  public GedcomxSearchQueryBuilder motherName(String value, boolean exact) {
    return param(MOTHER_NAME, value, exact);
  }

  public GedcomxSearchQueryBuilder motherGivenName(String value) {
    return motherGivenName(value, false);
  }

  public GedcomxSearchQueryBuilder motherGivenName(String value, boolean exact) {
    return param(MOTHER_GIVEN_NAME, value, exact);
  }

  public GedcomxSearchQueryBuilder motherSurname(String value) {
    return motherSurname(value, false);
  }

  public GedcomxSearchQueryBuilder motherSurname(String value, boolean exact) {
    return param(MOTHER_SURNAME, value, exact);
  }

  public GedcomxSearchQueryBuilder motherGender(String value) {
    return motherGender(value, false);
  }

  public GedcomxSearchQueryBuilder motherGender(String value, boolean exact) {
    return param(MOTHER_GENDER, value, exact);
  }

  public GedcomxSearchQueryBuilder motherBirthDate(String value) {
    return motherBirthDate(value, false);
  }

  public GedcomxSearchQueryBuilder motherBirthDate(String value, boolean exact) {
    return param(MOTHER_BIRTH_DATE, value, exact);
  }

  public GedcomxSearchQueryBuilder motherBirthPlace(String value) {
    return motherBirthPlace(value, false);
  }

  public GedcomxSearchQueryBuilder motherBirthPlace(String value, boolean exact) {
    return param(MOTHER_BIRTH_PLACE, value, exact);
  }

  public GedcomxSearchQueryBuilder motherDeathDate(String value) {
    return motherDeathDate(value, false);
  }

  public GedcomxSearchQueryBuilder motherDeathDate(String value, boolean exact) {
    return param(MOTHER_DEATH_DATE, value, exact);
  }

  public GedcomxSearchQueryBuilder motherDeathPlace(String value) {
    return motherDeathPlace(value, false);
  }

  public GedcomxSearchQueryBuilder motherDeathPlace(String value, boolean exact) {
    return param(MOTHER_DEATH_PLACE, value, exact);
  }

  public GedcomxSearchQueryBuilder motherMarriageDate(String value) {
    return motherMarriageDate(value, false);
  }

  public GedcomxSearchQueryBuilder motherMarriageDate(String value, boolean exact) {
    return param(MOTHER_MARRIAGE_DATE, value, exact);
  }

  public GedcomxSearchQueryBuilder motherMarriagePlace(String value) {
    return motherMarriagePlace(value, false);
  }

  public GedcomxSearchQueryBuilder motherMarriagePlace(String value, boolean exact) {
    return param(MOTHER_MARRIAGE_PLACE, value, exact);
  }

  public GedcomxSearchQueryBuilder spouseName(String value) {
    return spouseName(value, false);
  }

  public GedcomxSearchQueryBuilder spouseName(String value, boolean exact) {
    return param(SPOUSE_NAME, value, exact);
  }

  public GedcomxSearchQueryBuilder spouseGivenName(String value) {
    return spouseGivenName(value, false);
  }

  public GedcomxSearchQueryBuilder spouseGivenName(String value, boolean exact) {
    return param(SPOUSE_GIVEN_NAME, value, exact);
  }

  public GedcomxSearchQueryBuilder spouseSurname(String value) {
    return spouseSurname(value, false);
  }

  public GedcomxSearchQueryBuilder spouseSurname(String value, boolean exact) {
    return param(SPOUSE_SURNAME, value, exact);
  }

  public GedcomxSearchQueryBuilder spouseGender(String value) {
    return spouseGender(value, false);
  }

  public GedcomxSearchQueryBuilder spouseGender(String value, boolean exact) {
    return param(SPOUSE_GENDER, value, exact);
  }

  public GedcomxSearchQueryBuilder spouseBirthDate(String value) {
    return spouseBirthDate(value, false);
  }

  public GedcomxSearchQueryBuilder spouseBirthDate(String value, boolean exact) {
    return param(SPOUSE_BIRTH_DATE, value, exact);
  }

  public GedcomxSearchQueryBuilder spouseBirthPlace(String value) {
    return spouseBirthPlace(value, false);
  }

  public GedcomxSearchQueryBuilder spouseBirthPlace(String value, boolean exact) {
    return param(SPOUSE_BIRTH_PLACE, value, exact);
  }

  public GedcomxSearchQueryBuilder spouseDeathDate(String value) {
    return spouseDeathDate(value, false);
  }

  public GedcomxSearchQueryBuilder spouseDeathDate(String value, boolean exact) {
    return param(SPOUSE_DEATH_DATE, value, exact);
  }

  public GedcomxSearchQueryBuilder spouseDeathPlace(String value) {
    return spouseDeathPlace(value, false);
  }

  public GedcomxSearchQueryBuilder spouseDeathPlace(String value, boolean exact) {
    return param(SPOUSE_DEATH_PLACE, value, exact);
  }

  public GedcomxSearchQueryBuilder spouseMarriageDate(String value) {
    return spouseMarriageDate(value, false);
  }

  public GedcomxSearchQueryBuilder spouseMarriageDate(String value, boolean exact) {
    return param(SPOUSE_MARRIAGE_DATE, value, exact);
  }

  public GedcomxSearchQueryBuilder spouseMarriagePlace(String value) {
    return spouseMarriagePlace(value, false);
  }

  public GedcomxSearchQueryBuilder spouseMarriagePlace(String value, boolean exact) {
    return param(SPOUSE_MARRIAGE_PLACE, value, exact);
  }

  public GedcomxSearchQueryBuilder parentName(String value) {
    return parentName(value, false);
  }

  public GedcomxSearchQueryBuilder parentName(String value, boolean exact) {
    return param(PARENT_NAME, value, exact);
  }

  public GedcomxSearchQueryBuilder parentGivenName(String value) {
    return parentGivenName(value, false);
  }

  public GedcomxSearchQueryBuilder parentGivenName(String value, boolean exact) {
    return param(PARENT_GIVEN_NAME, value, exact);
  }

  public GedcomxSearchQueryBuilder parentSurname(String value) {
    return parentSurname(value, false);
  }

  public GedcomxSearchQueryBuilder parentSurname(String value, boolean exact) {
    return param(PARENT_SURNAME, value, exact);
  }

  public GedcomxSearchQueryBuilder parentGender(String value) {
    return parentGender(value, false);
  }

  public GedcomxSearchQueryBuilder parentGender(String value, boolean exact) {
    return param(PARENT_GENDER, value, exact);
  }

  public GedcomxSearchQueryBuilder parentBirthDate(String value) {
    return parentBirthDate(value, false);
  }

  public GedcomxSearchQueryBuilder parentBirthDate(String value, boolean exact) {
    return param(PARENT_BIRTH_DATE, value, exact);
  }

  public GedcomxSearchQueryBuilder parentBirthPlace(String value) {
    return parentBirthPlace(value, false);
  }

  public GedcomxSearchQueryBuilder parentBirthPlace(String value, boolean exact) {
    return param(PARENT_BIRTH_PLACE, value, exact);
  }

  public GedcomxSearchQueryBuilder parentDeathDate(String value) {
    return parentDeathDate(value, false);
  }

  public GedcomxSearchQueryBuilder parentDeathDate(String value, boolean exact) {
    return param(PARENT_DEATH_DATE, value, exact);
  }

  public GedcomxSearchQueryBuilder parentDeathPlace(String value) {
    return parentDeathPlace(value, false);
  }

  public GedcomxSearchQueryBuilder parentDeathPlace(String value, boolean exact) {
    return param(PARENT_DEATH_PLACE, value, exact);
  }

  public GedcomxSearchQueryBuilder parentMarriageDate(String value) {
    return parentMarriageDate(value, false);
  }

  public GedcomxSearchQueryBuilder parentMarriageDate(String value, boolean exact) {
    return param(PARENT_MARRIAGE_DATE, value, exact);
  }

  public GedcomxSearchQueryBuilder parentMarriagePlace(String value) {
    return parentMarriagePlace(value, false);
  }

  public GedcomxSearchQueryBuilder parentMarriagePlace(String value, boolean exact) {
    return param(PARENT_MARRIAGE_PLACE, value, exact);
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
