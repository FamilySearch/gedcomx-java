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
package org.familysearch.platform.users;

import org.gedcomx.links.AnchorElementSupport;
import org.gedcomx.links.HypermediaEnabledData;
import org.gedcomx.rt.json.JsonElementWrapper;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Family Tree User Service User
 *
 */
@XmlRootElement
@XmlType ( name = "User" )
@JsonElementWrapper ( name = "users" )
public class User extends HypermediaEnabledData implements AnchorElementSupport {

  private Boolean anchor;
  private String contactName;
  private String helperAccessPin;
  private String fullName;
  private String givenName;
  private String familyName;
  private String email;
  private String alternateEmail;
  private String country;
  private String gender;
  private String birthDate;
  private String phoneNumber;
  private String mailingAddress;
  private String preferredLanguage;
  private String displayName;
  private String personId;
  private String treeUserId;
  private Boolean ldsMemberAccount;

  @Override
  public Boolean getAnchor() {
    return anchor;
  }

  public void setAnchor(Boolean anchor) {
    this.anchor = anchor;
  }

  public String getContactName() {
    return contactName;
  }

  public void setContactName( String contactName ) {
    this.contactName = contactName;
  }

  public String getHelperAccessPin() {
    return helperAccessPin;
  }

  public void setHelperAccessPin( String helperAccessPin ) {
    this.helperAccessPin = helperAccessPin;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName( String fullName ) {
    this.fullName = fullName;
  }

  public String getGivenName() {
    return givenName;
  }

  public void setGivenName( String givenName ) {
    this.givenName = givenName;
  }

  public String getFamilyName() {
    return familyName;
  }

  public void setFamilyName( String familyName ) {
    this.familyName = familyName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail( String email ) {
    this.email = email;
  }

  public String getAlternateEmail() {
    return alternateEmail;
  }

  public void setAlternateEmail( String alternateEmail ) {
    this.alternateEmail = alternateEmail;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry( String country ) {
    this.country = country;
  }

  public String getGender() {
    return gender;
  }

  public void setGender( String gender ) {
    this.gender = gender;
  }

  public String getBirthDate() {
    return birthDate;
  }

  public void setBirthDate( String birthDate ) {
    this.birthDate = birthDate;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber( String phoneNumber ) {
    this.phoneNumber = phoneNumber;
  }

  public String getMailingAddress() {
    return mailingAddress;
  }

  public void setMailingAddress( String mailingAddress ) {
    this.mailingAddress = mailingAddress;
  }

  public String getPreferredLanguage() {
    return preferredLanguage;
  }

  public void setPreferredLanguage( String preferredLanguage ) {
    this.preferredLanguage = preferredLanguage;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName( String displayName ) {
    this.displayName = displayName;
  }

  public String getPersonId() {
    return personId;
  }

  public void setPersonId( String personId ) {
    this.personId = personId;
  }

  public String getTreeUserId() {
    return treeUserId;
  }

  public void setTreeUserId(String treeUserId) {
    this.treeUserId = treeUserId;
  }

  public Boolean getLdsMemberAccount() {
    return ldsMemberAccount;
  }

  public void setLdsMemberAccount(Boolean ldsMemberAccount) {
    this.ldsMemberAccount = ldsMemberAccount;
  }
}
