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
public class GedcomxPersonSearchQueryBuilder extends GedcomxBaseSearchQueryBuilder {

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

  public GedcomxPersonSearchQueryBuilder param(String name, String value) {
    return param(name, value, false);
  }

  public GedcomxPersonSearchQueryBuilder param(String name, String value, boolean exact) {
    return param(null, name, value, exact);
  }

  public GedcomxPersonSearchQueryBuilder param(String prefix, String name, String value, boolean exact) {
    super.parameters.add(new SearchParameter(prefix, name, value, exact));
    return this;
  }

  public GedcomxPersonSearchQueryBuilder name(String value) {
    return name(value, false);
  }

  public GedcomxPersonSearchQueryBuilder name(String value, boolean exact) {
    return name(value, exact, false);
  }

  public GedcomxPersonSearchQueryBuilder name(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, NAME, value, exact);
  }

  public GedcomxPersonSearchQueryBuilder givenName(String value) {
    return givenName(value, false);
  }

  public GedcomxPersonSearchQueryBuilder givenName(String value, boolean exact) {
    return givenName(value, exact, false);
  }

  public GedcomxPersonSearchQueryBuilder givenName(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, GIVEN_NAME, value, exact);
  }

  public GedcomxPersonSearchQueryBuilder surname(String value) {
    return surname(value, false);
  }

  public GedcomxPersonSearchQueryBuilder surname(String value, boolean exact) {
    return surname(value, exact, false);
  }

  public GedcomxPersonSearchQueryBuilder surname(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, SURNAME, value, exact);
  }

  public GedcomxPersonSearchQueryBuilder gender(String value) {
    return gender(value, false);
  }

  public GedcomxPersonSearchQueryBuilder gender(String value, boolean exact) {
    return gender(value, exact, false);
  }

  public GedcomxPersonSearchQueryBuilder gender(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, GENDER, value, exact);
  }

  public GedcomxPersonSearchQueryBuilder birthDate(String value) {
    return birthDate(value, false);
  }

  public GedcomxPersonSearchQueryBuilder birthDate(String value, boolean exact) {
    return birthDate(value, exact, false);
  }

  public GedcomxPersonSearchQueryBuilder birthDate(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, BIRTH_DATE, value, exact);
  }

  public GedcomxPersonSearchQueryBuilder birthPlace(String value) {
    return birthPlace(value, false);
  }

  public GedcomxPersonSearchQueryBuilder birthPlace(String value, boolean exact) {
    return birthPlace(value, exact, false);
  }

  public GedcomxPersonSearchQueryBuilder birthPlace(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, BIRTH_PLACE, value, exact);
  }

  public GedcomxPersonSearchQueryBuilder deathDate(String value) {
    return deathDate(value, false);
  }

  public GedcomxPersonSearchQueryBuilder deathDate(String value, boolean exact) {
    return deathDate(value, exact, false);
  }

  public GedcomxPersonSearchQueryBuilder deathDate(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, DEATH_DATE, value, exact);
  }

  public GedcomxPersonSearchQueryBuilder deathPlace(String value) {
    return deathPlace(value, false);
  }

  public GedcomxPersonSearchQueryBuilder deathPlace(String value, boolean exact) {
    return deathPlace(value, exact, false);
  }

  public GedcomxPersonSearchQueryBuilder deathPlace(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, DEATH_PLACE, value, exact);
  }

  public GedcomxPersonSearchQueryBuilder marriageDate(String value) {
    return marriageDate(value, false);
  }

  public GedcomxPersonSearchQueryBuilder marriageDate(String value, boolean exact) {
    return marriageDate(value, exact, false);
  }

  public GedcomxPersonSearchQueryBuilder marriageDate(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, MARRIAGE_DATE, value, exact);
  }

  public GedcomxPersonSearchQueryBuilder marriagePlace(String value) {
    return marriagePlace(value, false);
  }

  public GedcomxPersonSearchQueryBuilder marriagePlace(String value, boolean exact) {
    return marriagePlace(value, exact, false);
  }

  public GedcomxPersonSearchQueryBuilder marriagePlace(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, MARRIAGE_PLACE, value, exact);
  }

  public GedcomxPersonSearchQueryBuilder fatherName(String value) {
    return fatherName(value, false);
  }

  public GedcomxPersonSearchQueryBuilder fatherName(String value, boolean exact) {
    return fatherName(value, exact, false);
  }

  public GedcomxPersonSearchQueryBuilder fatherName(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, FATHER_NAME, value, exact);
  }

  public GedcomxPersonSearchQueryBuilder fatherGivenName(String value) {
    return fatherGivenName(value, false);
  }

  public GedcomxPersonSearchQueryBuilder fatherGivenName(String value, boolean exact) {
    return fatherGivenName(value, exact, false);
  }

  public GedcomxPersonSearchQueryBuilder fatherGivenName(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, FATHER_GIVEN_NAME, value, exact);
  }

  public GedcomxPersonSearchQueryBuilder fatherSurname(String value) {
    return fatherSurname(value, false);
  }

  public GedcomxPersonSearchQueryBuilder fatherSurname(String value, boolean exact) {
    return fatherSurname(value, exact, false);
  }

  public GedcomxPersonSearchQueryBuilder fatherSurname(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, FATHER_SURNAME, value, exact);
  }

  public GedcomxPersonSearchQueryBuilder fatherGender(String value) {
    return fatherGender(value, false);
  }

  public GedcomxPersonSearchQueryBuilder fatherGender(String value, boolean exact) {
    return fatherGender(value, exact, false);
  }

  public GedcomxPersonSearchQueryBuilder fatherGender(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, FATHER_GENDER, value, exact);
  }

  public GedcomxPersonSearchQueryBuilder fatherBirthDate(String value) {
    return fatherBirthDate(value, false);
  }

  public GedcomxPersonSearchQueryBuilder fatherBirthDate(String value, boolean exact) {
    return fatherBirthDate(value, exact, false);
  }

  public GedcomxPersonSearchQueryBuilder fatherBirthDate(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, FATHER_BIRTH_DATE, value, exact);
  }

  public GedcomxPersonSearchQueryBuilder fatherBirthPlace(String value) {
    return fatherBirthPlace(value, false);
  }

  public GedcomxPersonSearchQueryBuilder fatherBirthPlace(String value, boolean exact) {
    return fatherBirthPlace(value, exact, false);
  }

  public GedcomxPersonSearchQueryBuilder fatherBirthPlace(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, FATHER_BIRTH_PLACE, value, exact);
  }

  public GedcomxPersonSearchQueryBuilder fatherDeathDate(String value) {
    return fatherDeathDate(value, false);
  }

  public GedcomxPersonSearchQueryBuilder fatherDeathDate(String value, boolean exact) {
    return fatherDeathDate(value, exact, false);
  }

  public GedcomxPersonSearchQueryBuilder fatherDeathDate(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, FATHER_DEATH_DATE, value, exact);
  }

  public GedcomxPersonSearchQueryBuilder fatherDeathPlace(String value) {
    return fatherDeathPlace(value, false);
  }

  public GedcomxPersonSearchQueryBuilder fatherDeathPlace(String value, boolean exact) {
    return fatherDeathPlace(value, exact, false);
  }

  public GedcomxPersonSearchQueryBuilder fatherDeathPlace(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, FATHER_DEATH_PLACE, value, exact);
  }

  public GedcomxPersonSearchQueryBuilder fatherMarriageDate(String value) {
    return fatherMarriageDate(value, false);
  }

  public GedcomxPersonSearchQueryBuilder fatherMarriageDate(String value, boolean exact) {
    return fatherMarriageDate(value, exact, false);
  }

  public GedcomxPersonSearchQueryBuilder fatherMarriageDate(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, FATHER_MARRIAGE_DATE, value, exact);
  }

  public GedcomxPersonSearchQueryBuilder fatherMarriagePlace(String value) {
    return fatherMarriagePlace(value, false);
  }

  public GedcomxPersonSearchQueryBuilder fatherMarriagePlace(String value, boolean exact) {
    return fatherMarriagePlace(value, exact, false);
  }

  public GedcomxPersonSearchQueryBuilder fatherMarriagePlace(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, FATHER_MARRIAGE_PLACE, value, exact);
  }
  
  public GedcomxPersonSearchQueryBuilder motherName(String value) {
    return motherName(value, false);
  }

  public GedcomxPersonSearchQueryBuilder motherName(String value, boolean exact) {
    return motherName(value, exact, false);
  }

  public GedcomxPersonSearchQueryBuilder motherName(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, MOTHER_NAME, value, exact);
  }

  public GedcomxPersonSearchQueryBuilder motherGivenName(String value) {
    return motherGivenName(value, false);
  }

  public GedcomxPersonSearchQueryBuilder motherGivenName(String value, boolean exact) {
    return motherGivenName(value, exact, false);
  }

  public GedcomxPersonSearchQueryBuilder motherGivenName(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, MOTHER_GIVEN_NAME, value, exact);
  }

  public GedcomxPersonSearchQueryBuilder motherSurname(String value) {
    return motherSurname(value, false);
  }

  public GedcomxPersonSearchQueryBuilder motherSurname(String value, boolean exact) {
    return motherSurname(value, exact, false);
  }

  public GedcomxPersonSearchQueryBuilder motherSurname(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, MOTHER_SURNAME, value, exact);
  }

  public GedcomxPersonSearchQueryBuilder motherGender(String value) {
    return motherGender(value, false);
  }

  public GedcomxPersonSearchQueryBuilder motherGender(String value, boolean exact) {
    return motherGender(value, exact, false);
  }

  public GedcomxPersonSearchQueryBuilder motherGender(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, MOTHER_GENDER, value, exact);
  }

  public GedcomxPersonSearchQueryBuilder motherBirthDate(String value) {
    return motherBirthDate(value, false);
  }

  public GedcomxPersonSearchQueryBuilder motherBirthDate(String value, boolean exact) {
    return motherBirthDate(value, exact, false);
  }

  public GedcomxPersonSearchQueryBuilder motherBirthDate(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, MOTHER_BIRTH_DATE, value, exact);
  }

  public GedcomxPersonSearchQueryBuilder motherBirthPlace(String value) {
    return motherBirthPlace(value, false);
  }

  public GedcomxPersonSearchQueryBuilder motherBirthPlace(String value, boolean exact) {
    return motherBirthPlace(value, exact, false);
  }

  public GedcomxPersonSearchQueryBuilder motherBirthPlace(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, MOTHER_BIRTH_PLACE, value, exact);
  }

  public GedcomxPersonSearchQueryBuilder motherDeathDate(String value) {
    return motherDeathDate(value, false);
  }

  public GedcomxPersonSearchQueryBuilder motherDeathDate(String value, boolean exact) {
    return motherDeathDate(value, exact, false);
  }

  public GedcomxPersonSearchQueryBuilder motherDeathDate(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, MOTHER_DEATH_DATE, value, exact);
  }

  public GedcomxPersonSearchQueryBuilder motherDeathPlace(String value) {
    return motherDeathPlace(value, false);
  }

  public GedcomxPersonSearchQueryBuilder motherDeathPlace(String value, boolean exact) {
    return motherDeathPlace(value, exact, false);
  }

  public GedcomxPersonSearchQueryBuilder motherDeathPlace(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, MOTHER_DEATH_PLACE, value, exact);
  }

  public GedcomxPersonSearchQueryBuilder motherMarriageDate(String value) {
    return motherMarriageDate(value, false);
  }

  public GedcomxPersonSearchQueryBuilder motherMarriageDate(String value, boolean exact) {
    return motherMarriageDate(value, exact, false);
  }

  public GedcomxPersonSearchQueryBuilder motherMarriageDate(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, MOTHER_MARRIAGE_DATE, value, exact);
  }

  public GedcomxPersonSearchQueryBuilder motherMarriagePlace(String value) {
    return motherMarriagePlace(value, false);
  }

  public GedcomxPersonSearchQueryBuilder motherMarriagePlace(String value, boolean exact) {
    return motherMarriagePlace(value, exact, false);
  }

  public GedcomxPersonSearchQueryBuilder motherMarriagePlace(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, MOTHER_MARRIAGE_PLACE, value, exact);
  }

  public GedcomxPersonSearchQueryBuilder spouseName(String value) {
    return spouseName(value, false);
  }

  public GedcomxPersonSearchQueryBuilder spouseName(String value, boolean exact) {
    return spouseName(value, exact, false);
  }

  public GedcomxPersonSearchQueryBuilder spouseName(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, SPOUSE_NAME, value, exact);
  }

  public GedcomxPersonSearchQueryBuilder spouseGivenName(String value) {
    return spouseGivenName(value, false);
  }

  public GedcomxPersonSearchQueryBuilder spouseGivenName(String value, boolean exact) {
    return spouseGivenName(value, exact, false);
  }

  public GedcomxPersonSearchQueryBuilder spouseGivenName(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, SPOUSE_GIVEN_NAME, value, exact);
  }

  public GedcomxPersonSearchQueryBuilder spouseSurname(String value) {
    return spouseSurname(value, false);
  }

  public GedcomxPersonSearchQueryBuilder spouseSurname(String value, boolean exact) {
    return spouseSurname(value, exact, false);
  }

  public GedcomxPersonSearchQueryBuilder spouseSurname(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, SPOUSE_SURNAME, value, exact);
  }

  public GedcomxPersonSearchQueryBuilder spouseGender(String value) {
    return spouseGender(value, false);
  }

  public GedcomxPersonSearchQueryBuilder spouseGender(String value, boolean exact) {
    return spouseGender(value, exact, false);
  }

  public GedcomxPersonSearchQueryBuilder spouseGender(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, SPOUSE_GENDER, value, exact);
  }

  public GedcomxPersonSearchQueryBuilder spouseBirthDate(String value) {
    return spouseBirthDate(value, false);
  }

  public GedcomxPersonSearchQueryBuilder spouseBirthDate(String value, boolean exact) {
    return spouseBirthDate(value, exact, false);
  }

  public GedcomxPersonSearchQueryBuilder spouseBirthDate(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, SPOUSE_BIRTH_DATE, value, exact);
  }

  public GedcomxPersonSearchQueryBuilder spouseBirthPlace(String value) {
    return spouseBirthPlace(value, false);
  }

  public GedcomxPersonSearchQueryBuilder spouseBirthPlace(String value, boolean exact) {
    return spouseBirthPlace(value, exact, false);
  }

  public GedcomxPersonSearchQueryBuilder spouseBirthPlace(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, SPOUSE_BIRTH_PLACE, value, exact);
  }

  public GedcomxPersonSearchQueryBuilder spouseDeathDate(String value) {
    return spouseDeathDate(value, false);
  }

  public GedcomxPersonSearchQueryBuilder spouseDeathDate(String value, boolean exact) {
    return spouseDeathDate(value, exact, false);
  }

  public GedcomxPersonSearchQueryBuilder spouseDeathDate(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, SPOUSE_DEATH_DATE, value, exact);
  }

  public GedcomxPersonSearchQueryBuilder spouseDeathPlace(String value) {
    return spouseDeathPlace(value, false);
  }

  public GedcomxPersonSearchQueryBuilder spouseDeathPlace(String value, boolean exact) {
    return spouseDeathPlace(value, exact, false);
  }

  public GedcomxPersonSearchQueryBuilder spouseDeathPlace(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, SPOUSE_DEATH_PLACE, value, exact);
  }

  public GedcomxPersonSearchQueryBuilder spouseMarriageDate(String value) {
    return spouseMarriageDate(value, false);
  }

  public GedcomxPersonSearchQueryBuilder spouseMarriageDate(String value, boolean exact) {
    return spouseMarriageDate(value, exact, false);
  }

  public GedcomxPersonSearchQueryBuilder spouseMarriageDate(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, SPOUSE_MARRIAGE_DATE, value, exact);
  }

  public GedcomxPersonSearchQueryBuilder spouseMarriagePlace(String value) {
    return spouseMarriagePlace(value, false);
  }

  public GedcomxPersonSearchQueryBuilder spouseMarriagePlace(String value, boolean exact) {
    return spouseMarriagePlace(value, exact, false);
  }

  public GedcomxPersonSearchQueryBuilder spouseMarriagePlace(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, SPOUSE_MARRIAGE_PLACE, value, exact);
  }

  public GedcomxPersonSearchQueryBuilder parentName(String value) {
    return parentName(value, false);
  }

  public GedcomxPersonSearchQueryBuilder parentName(String value, boolean exact) {
    return parentName(value, exact, false);
  }

  public GedcomxPersonSearchQueryBuilder parentName(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, PARENT_NAME, value, exact);
  }

  public GedcomxPersonSearchQueryBuilder parentGivenName(String value) {
    return parentGivenName(value, false);
  }

  public GedcomxPersonSearchQueryBuilder parentGivenName(String value, boolean exact) {
    return parentGivenName(value, exact, false);
  }

  public GedcomxPersonSearchQueryBuilder parentGivenName(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, PARENT_GIVEN_NAME, value, exact);
  }

  public GedcomxPersonSearchQueryBuilder parentSurname(String value) {
    return parentSurname(value, false);
  }

  public GedcomxPersonSearchQueryBuilder parentSurname(String value, boolean exact) {
    return parentSurname(value, exact, false);
  }

  public GedcomxPersonSearchQueryBuilder parentSurname(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, PARENT_SURNAME, value, exact);
  }

  public GedcomxPersonSearchQueryBuilder parentGender(String value) {
    return parentGender(value, false);
  }

  public GedcomxPersonSearchQueryBuilder parentGender(String value, boolean exact) {
    return parentGender(value, exact, false);
  }

  public GedcomxPersonSearchQueryBuilder parentGender(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, PARENT_GENDER, value, exact);
  }

  public GedcomxPersonSearchQueryBuilder parentBirthDate(String value) {
    return parentBirthDate(value, false);
  }

  public GedcomxPersonSearchQueryBuilder parentBirthDate(String value, boolean exact) {
    return parentBirthDate(value, exact, false);
  }

  public GedcomxPersonSearchQueryBuilder parentBirthDate(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, PARENT_BIRTH_DATE, value, exact);
  }

  public GedcomxPersonSearchQueryBuilder parentBirthPlace(String value) {
    return parentBirthPlace(value, false);
  }

  public GedcomxPersonSearchQueryBuilder parentBirthPlace(String value, boolean exact) {
    return parentBirthPlace(value, exact, false);
  }

  public GedcomxPersonSearchQueryBuilder parentBirthPlace(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, PARENT_BIRTH_PLACE, value, exact);
  }

  public GedcomxPersonSearchQueryBuilder parentDeathDate(String value) {
    return parentDeathDate(value, false);
  }

  public GedcomxPersonSearchQueryBuilder parentDeathDate(String value, boolean exact) {
    return parentDeathDate(value, exact, false);
  }

  public GedcomxPersonSearchQueryBuilder parentDeathDate(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, PARENT_DEATH_DATE, value, exact);
  }

  public GedcomxPersonSearchQueryBuilder parentDeathPlace(String value) {
    return parentDeathPlace(value, false);
  }

  public GedcomxPersonSearchQueryBuilder parentDeathPlace(String value, boolean exact) {
    return parentDeathPlace(value, exact, false);
  }

  public GedcomxPersonSearchQueryBuilder parentDeathPlace(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, PARENT_DEATH_PLACE, value, exact);
  }

  public GedcomxPersonSearchQueryBuilder parentMarriageDate(String value) {
    return parentMarriageDate(value, false);
  }

  public GedcomxPersonSearchQueryBuilder parentMarriageDate(String value, boolean exact) {
    return parentMarriageDate(value, exact, false);
  }

  public GedcomxPersonSearchQueryBuilder parentMarriageDate(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, PARENT_MARRIAGE_DATE, value, exact);
  }

  public GedcomxPersonSearchQueryBuilder parentMarriagePlace(String value) {
    return parentMarriagePlace(value, false);
  }

  public GedcomxPersonSearchQueryBuilder parentMarriagePlace(String value, boolean exact) {
    return parentMarriagePlace(value, exact, false);
  }

  public GedcomxPersonSearchQueryBuilder parentMarriagePlace(String value, boolean exact, boolean required) {
    return param(required ? "+" : null, PARENT_MARRIAGE_PLACE, value, exact);
  }

}
