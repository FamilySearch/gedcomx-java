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

/**
 * @author Ryan Heaton
 */
public class PersonSearchQueryBuilder extends BaseSearchQueryBuilder {

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

  public PersonSearchQueryBuilder param(String name, String value) {
    return param(name, value, false);
  }

  public PersonSearchQueryBuilder param(String name, String value, boolean exact) {
    return param(null, name, value, exact);
  }

  public PersonSearchQueryBuilder param(String prefix, String name, String value, boolean exact) {
    super.parameters.add(new SearchParameter(prefix, name, value, exact));
    return this;
  }

  public PersonSearchQueryBuilder name(String value) {
    return name(value, false);
  }

  public PersonSearchQueryBuilder name(String value, boolean exact) {
    return name(value, exact, false);
  }

  public PersonSearchQueryBuilder name(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, NAME, value, exact);
  }

  public PersonSearchQueryBuilder givenName(String value) {
    return givenName(value, false);
  }

  public PersonSearchQueryBuilder givenName(String value, boolean exact) {
    return givenName(value, exact, false);
  }

  public PersonSearchQueryBuilder givenName(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, GIVEN_NAME, value, exact);
  }

  public PersonSearchQueryBuilder surname(String value) {
    return surname(value, false);
  }

  public PersonSearchQueryBuilder surname(String value, boolean exact) {
    return surname(value, exact, false);
  }

  public PersonSearchQueryBuilder surname(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, SURNAME, value, exact);
  }

  public PersonSearchQueryBuilder gender(String value) {
    return gender(value, false);
  }

  public PersonSearchQueryBuilder gender(String value, boolean exact) {
    return gender(value, exact, false);
  }

  public PersonSearchQueryBuilder gender(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, GENDER, value, exact);
  }

  public PersonSearchQueryBuilder birthDate(String value) {
    return birthDate(value, false);
  }

  public PersonSearchQueryBuilder birthDate(String value, boolean exact) {
    return birthDate(value, exact, false);
  }

  public PersonSearchQueryBuilder birthDate(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, BIRTH_DATE, value, exact);
  }

  public PersonSearchQueryBuilder birthPlace(String value) {
    return birthPlace(value, false);
  }

  public PersonSearchQueryBuilder birthPlace(String value, boolean exact) {
    return birthPlace(value, exact, false);
  }

  public PersonSearchQueryBuilder birthPlace(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, BIRTH_PLACE, value, exact);
  }

  public PersonSearchQueryBuilder deathDate(String value) {
    return deathDate(value, false);
  }

  public PersonSearchQueryBuilder deathDate(String value, boolean exact) {
    return deathDate(value, exact, false);
  }

  public PersonSearchQueryBuilder deathDate(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, DEATH_DATE, value, exact);
  }

  public PersonSearchQueryBuilder deathPlace(String value) {
    return deathPlace(value, false);
  }

  public PersonSearchQueryBuilder deathPlace(String value, boolean exact) {
    return deathPlace(value, exact, false);
  }

  public PersonSearchQueryBuilder deathPlace(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, DEATH_PLACE, value, exact);
  }

  public PersonSearchQueryBuilder marriageDate(String value) {
    return marriageDate(value, false);
  }

  public PersonSearchQueryBuilder marriageDate(String value, boolean exact) {
    return marriageDate(value, exact, false);
  }

  public PersonSearchQueryBuilder marriageDate(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, MARRIAGE_DATE, value, exact);
  }

  public PersonSearchQueryBuilder marriagePlace(String value) {
    return marriagePlace(value, false);
  }

  public PersonSearchQueryBuilder marriagePlace(String value, boolean exact) {
    return marriagePlace(value, exact, false);
  }

  public PersonSearchQueryBuilder marriagePlace(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, MARRIAGE_PLACE, value, exact);
  }

  public PersonSearchQueryBuilder fatherName(String value) {
    return fatherName(value, false);
  }

  public PersonSearchQueryBuilder fatherName(String value, boolean exact) {
    return fatherName(value, exact, false);
  }

  public PersonSearchQueryBuilder fatherName(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, FATHER_NAME, value, exact);
  }

  public PersonSearchQueryBuilder fatherGivenName(String value) {
    return fatherGivenName(value, false);
  }

  public PersonSearchQueryBuilder fatherGivenName(String value, boolean exact) {
    return fatherGivenName(value, exact, false);
  }

  public PersonSearchQueryBuilder fatherGivenName(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, FATHER_GIVEN_NAME, value, exact);
  }

  public PersonSearchQueryBuilder fatherSurname(String value) {
    return fatherSurname(value, false);
  }

  public PersonSearchQueryBuilder fatherSurname(String value, boolean exact) {
    return fatherSurname(value, exact, false);
  }

  public PersonSearchQueryBuilder fatherSurname(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, FATHER_SURNAME, value, exact);
  }

  public PersonSearchQueryBuilder fatherGender(String value) {
    return fatherGender(value, false);
  }

  public PersonSearchQueryBuilder fatherGender(String value, boolean exact) {
    return fatherGender(value, exact, false);
  }

  public PersonSearchQueryBuilder fatherGender(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, FATHER_GENDER, value, exact);
  }

  public PersonSearchQueryBuilder fatherBirthDate(String value) {
    return fatherBirthDate(value, false);
  }

  public PersonSearchQueryBuilder fatherBirthDate(String value, boolean exact) {
    return fatherBirthDate(value, exact, false);
  }

  public PersonSearchQueryBuilder fatherBirthDate(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, FATHER_BIRTH_DATE, value, exact);
  }

  public PersonSearchQueryBuilder fatherBirthPlace(String value) {
    return fatherBirthPlace(value, false);
  }

  public PersonSearchQueryBuilder fatherBirthPlace(String value, boolean exact) {
    return fatherBirthPlace(value, exact, false);
  }

  public PersonSearchQueryBuilder fatherBirthPlace(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, FATHER_BIRTH_PLACE, value, exact);
  }

  public PersonSearchQueryBuilder fatherDeathDate(String value) {
    return fatherDeathDate(value, false);
  }

  public PersonSearchQueryBuilder fatherDeathDate(String value, boolean exact) {
    return fatherDeathDate(value, exact, false);
  }

  public PersonSearchQueryBuilder fatherDeathDate(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, FATHER_DEATH_DATE, value, exact);
  }

  public PersonSearchQueryBuilder fatherDeathPlace(String value) {
    return fatherDeathPlace(value, false);
  }

  public PersonSearchQueryBuilder fatherDeathPlace(String value, boolean exact) {
    return fatherDeathPlace(value, exact, false);
  }

  public PersonSearchQueryBuilder fatherDeathPlace(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, FATHER_DEATH_PLACE, value, exact);
  }

  public PersonSearchQueryBuilder fatherMarriageDate(String value) {
    return fatherMarriageDate(value, false);
  }

  public PersonSearchQueryBuilder fatherMarriageDate(String value, boolean exact) {
    return fatherMarriageDate(value, exact, false);
  }

  public PersonSearchQueryBuilder fatherMarriageDate(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, FATHER_MARRIAGE_DATE, value, exact);
  }

  public PersonSearchQueryBuilder fatherMarriagePlace(String value) {
    return fatherMarriagePlace(value, false);
  }

  public PersonSearchQueryBuilder fatherMarriagePlace(String value, boolean exact) {
    return fatherMarriagePlace(value, exact, false);
  }

  public PersonSearchQueryBuilder fatherMarriagePlace(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, FATHER_MARRIAGE_PLACE, value, exact);
  }
  
  public PersonSearchQueryBuilder motherName(String value) {
    return motherName(value, false);
  }

  public PersonSearchQueryBuilder motherName(String value, boolean exact) {
    return motherName(value, exact, false);
  }

  public PersonSearchQueryBuilder motherName(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, MOTHER_NAME, value, exact);
  }

  public PersonSearchQueryBuilder motherGivenName(String value) {
    return motherGivenName(value, false);
  }

  public PersonSearchQueryBuilder motherGivenName(String value, boolean exact) {
    return motherGivenName(value, exact, false);
  }

  public PersonSearchQueryBuilder motherGivenName(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, MOTHER_GIVEN_NAME, value, exact);
  }

  public PersonSearchQueryBuilder motherSurname(String value) {
    return motherSurname(value, false);
  }

  public PersonSearchQueryBuilder motherSurname(String value, boolean exact) {
    return motherSurname(value, exact, false);
  }

  public PersonSearchQueryBuilder motherSurname(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, MOTHER_SURNAME, value, exact);
  }

  public PersonSearchQueryBuilder motherGender(String value) {
    return motherGender(value, false);
  }

  public PersonSearchQueryBuilder motherGender(String value, boolean exact) {
    return motherGender(value, exact, false);
  }

  public PersonSearchQueryBuilder motherGender(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, MOTHER_GENDER, value, exact);
  }

  public PersonSearchQueryBuilder motherBirthDate(String value) {
    return motherBirthDate(value, false);
  }

  public PersonSearchQueryBuilder motherBirthDate(String value, boolean exact) {
    return motherBirthDate(value, exact, false);
  }

  public PersonSearchQueryBuilder motherBirthDate(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, MOTHER_BIRTH_DATE, value, exact);
  }

  public PersonSearchQueryBuilder motherBirthPlace(String value) {
    return motherBirthPlace(value, false);
  }

  public PersonSearchQueryBuilder motherBirthPlace(String value, boolean exact) {
    return motherBirthPlace(value, exact, false);
  }

  public PersonSearchQueryBuilder motherBirthPlace(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, MOTHER_BIRTH_PLACE, value, exact);
  }

  public PersonSearchQueryBuilder motherDeathDate(String value) {
    return motherDeathDate(value, false);
  }

  public PersonSearchQueryBuilder motherDeathDate(String value, boolean exact) {
    return motherDeathDate(value, exact, false);
  }

  public PersonSearchQueryBuilder motherDeathDate(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, MOTHER_DEATH_DATE, value, exact);
  }

  public PersonSearchQueryBuilder motherDeathPlace(String value) {
    return motherDeathPlace(value, false);
  }

  public PersonSearchQueryBuilder motherDeathPlace(String value, boolean exact) {
    return motherDeathPlace(value, exact, false);
  }

  public PersonSearchQueryBuilder motherDeathPlace(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, MOTHER_DEATH_PLACE, value, exact);
  }

  public PersonSearchQueryBuilder motherMarriageDate(String value) {
    return motherMarriageDate(value, false);
  }

  public PersonSearchQueryBuilder motherMarriageDate(String value, boolean exact) {
    return motherMarriageDate(value, exact, false);
  }

  public PersonSearchQueryBuilder motherMarriageDate(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, MOTHER_MARRIAGE_DATE, value, exact);
  }

  public PersonSearchQueryBuilder motherMarriagePlace(String value) {
    return motherMarriagePlace(value, false);
  }

  public PersonSearchQueryBuilder motherMarriagePlace(String value, boolean exact) {
    return motherMarriagePlace(value, exact, false);
  }

  public PersonSearchQueryBuilder motherMarriagePlace(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, MOTHER_MARRIAGE_PLACE, value, exact);
  }

  public PersonSearchQueryBuilder spouseName(String value) {
    return spouseName(value, false);
  }

  public PersonSearchQueryBuilder spouseName(String value, boolean exact) {
    return spouseName(value, exact, false);
  }

  public PersonSearchQueryBuilder spouseName(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, SPOUSE_NAME, value, exact);
  }

  public PersonSearchQueryBuilder spouseGivenName(String value) {
    return spouseGivenName(value, false);
  }

  public PersonSearchQueryBuilder spouseGivenName(String value, boolean exact) {
    return spouseGivenName(value, exact, false);
  }

  public PersonSearchQueryBuilder spouseGivenName(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, SPOUSE_GIVEN_NAME, value, exact);
  }

  public PersonSearchQueryBuilder spouseSurname(String value) {
    return spouseSurname(value, false);
  }

  public PersonSearchQueryBuilder spouseSurname(String value, boolean exact) {
    return spouseSurname(value, exact, false);
  }

  public PersonSearchQueryBuilder spouseSurname(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, SPOUSE_SURNAME, value, exact);
  }

  public PersonSearchQueryBuilder spouseGender(String value) {
    return spouseGender(value, false);
  }

  public PersonSearchQueryBuilder spouseGender(String value, boolean exact) {
    return spouseGender(value, exact, false);
  }

  public PersonSearchQueryBuilder spouseGender(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, SPOUSE_GENDER, value, exact);
  }

  public PersonSearchQueryBuilder spouseBirthDate(String value) {
    return spouseBirthDate(value, false);
  }

  public PersonSearchQueryBuilder spouseBirthDate(String value, boolean exact) {
    return spouseBirthDate(value, exact, false);
  }

  public PersonSearchQueryBuilder spouseBirthDate(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, SPOUSE_BIRTH_DATE, value, exact);
  }

  public PersonSearchQueryBuilder spouseBirthPlace(String value) {
    return spouseBirthPlace(value, false);
  }

  public PersonSearchQueryBuilder spouseBirthPlace(String value, boolean exact) {
    return spouseBirthPlace(value, exact, false);
  }

  public PersonSearchQueryBuilder spouseBirthPlace(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, SPOUSE_BIRTH_PLACE, value, exact);
  }

  public PersonSearchQueryBuilder spouseDeathDate(String value) {
    return spouseDeathDate(value, false);
  }

  public PersonSearchQueryBuilder spouseDeathDate(String value, boolean exact) {
    return spouseDeathDate(value, exact, false);
  }

  public PersonSearchQueryBuilder spouseDeathDate(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, SPOUSE_DEATH_DATE, value, exact);
  }

  public PersonSearchQueryBuilder spouseDeathPlace(String value) {
    return spouseDeathPlace(value, false);
  }

  public PersonSearchQueryBuilder spouseDeathPlace(String value, boolean exact) {
    return spouseDeathPlace(value, exact, false);
  }

  public PersonSearchQueryBuilder spouseDeathPlace(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, SPOUSE_DEATH_PLACE, value, exact);
  }

  public PersonSearchQueryBuilder spouseMarriageDate(String value) {
    return spouseMarriageDate(value, false);
  }

  public PersonSearchQueryBuilder spouseMarriageDate(String value, boolean exact) {
    return spouseMarriageDate(value, exact, false);
  }

  public PersonSearchQueryBuilder spouseMarriageDate(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, SPOUSE_MARRIAGE_DATE, value, exact);
  }

  public PersonSearchQueryBuilder spouseMarriagePlace(String value) {
    return spouseMarriagePlace(value, false);
  }

  public PersonSearchQueryBuilder spouseMarriagePlace(String value, boolean exact) {
    return spouseMarriagePlace(value, exact, false);
  }

  public PersonSearchQueryBuilder spouseMarriagePlace(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, SPOUSE_MARRIAGE_PLACE, value, exact);
  }

  public PersonSearchQueryBuilder parentName(String value) {
    return parentName(value, false);
  }

  public PersonSearchQueryBuilder parentName(String value, boolean exact) {
    return parentName(value, exact, false);
  }

  public PersonSearchQueryBuilder parentName(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, PARENT_NAME, value, exact);
  }

  public PersonSearchQueryBuilder parentGivenName(String value) {
    return parentGivenName(value, false);
  }

  public PersonSearchQueryBuilder parentGivenName(String value, boolean exact) {
    return parentGivenName(value, exact, false);
  }

  public PersonSearchQueryBuilder parentGivenName(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, PARENT_GIVEN_NAME, value, exact);
  }

  public PersonSearchQueryBuilder parentSurname(String value) {
    return parentSurname(value, false);
  }

  public PersonSearchQueryBuilder parentSurname(String value, boolean exact) {
    return parentSurname(value, exact, false);
  }

  public PersonSearchQueryBuilder parentSurname(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, PARENT_SURNAME, value, exact);
  }

  public PersonSearchQueryBuilder parentGender(String value) {
    return parentGender(value, false);
  }

  public PersonSearchQueryBuilder parentGender(String value, boolean exact) {
    return parentGender(value, exact, false);
  }

  public PersonSearchQueryBuilder parentGender(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, PARENT_GENDER, value, exact);
  }

  public PersonSearchQueryBuilder parentBirthDate(String value) {
    return parentBirthDate(value, false);
  }

  public PersonSearchQueryBuilder parentBirthDate(String value, boolean exact) {
    return parentBirthDate(value, exact, false);
  }

  public PersonSearchQueryBuilder parentBirthDate(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, PARENT_BIRTH_DATE, value, exact);
  }

  public PersonSearchQueryBuilder parentBirthPlace(String value) {
    return parentBirthPlace(value, false);
  }

  public PersonSearchQueryBuilder parentBirthPlace(String value, boolean exact) {
    return parentBirthPlace(value, exact, false);
  }

  public PersonSearchQueryBuilder parentBirthPlace(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, PARENT_BIRTH_PLACE, value, exact);
  }

  public PersonSearchQueryBuilder parentDeathDate(String value) {
    return parentDeathDate(value, false);
  }

  public PersonSearchQueryBuilder parentDeathDate(String value, boolean exact) {
    return parentDeathDate(value, exact, false);
  }

  public PersonSearchQueryBuilder parentDeathDate(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, PARENT_DEATH_DATE, value, exact);
  }

  public PersonSearchQueryBuilder parentDeathPlace(String value) {
    return parentDeathPlace(value, false);
  }

  public PersonSearchQueryBuilder parentDeathPlace(String value, boolean exact) {
    return parentDeathPlace(value, exact, false);
  }

  public PersonSearchQueryBuilder parentDeathPlace(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, PARENT_DEATH_PLACE, value, exact);
  }

  public PersonSearchQueryBuilder parentMarriageDate(String value) {
    return parentMarriageDate(value, false);
  }

  public PersonSearchQueryBuilder parentMarriageDate(String value, boolean exact) {
    return parentMarriageDate(value, exact, false);
  }

  public PersonSearchQueryBuilder parentMarriageDate(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, PARENT_MARRIAGE_DATE, value, exact);
  }

  public PersonSearchQueryBuilder parentMarriagePlace(String value) {
    return parentMarriagePlace(value, false);
  }

  public PersonSearchQueryBuilder parentMarriagePlace(String value, boolean exact) {
    return parentMarriagePlace(value, exact, false);
  }

  public PersonSearchQueryBuilder parentMarriagePlace(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, PARENT_MARRIAGE_PLACE, value, exact);
  }

}
