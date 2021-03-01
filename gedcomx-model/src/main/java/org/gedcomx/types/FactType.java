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
package org.gedcomx.types;

import com.webcohesion.enunciate.metadata.Facet;
import com.webcohesion.enunciate.metadata.qname.XmlQNameEnum;
import com.webcohesion.enunciate.metadata.qname.XmlUnknownQNameEnumValue;
import org.gedcomx.common.URI;
import org.gedcomx.rt.ControlledVocabulary;
import org.gedcomx.rt.EnumURIMap;
import org.gedcomx.rt.GedcomxConstants;

import javax.xml.bind.annotation.XmlTransient;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

/**
 * Enumeration of standard fact types.
 */
@XmlQNameEnum (
  base = XmlQNameEnum.BaseType.URI
)
public enum FactType implements ControlledVocabulary {

  // facts generally applicable within the scope of a person.

  /**
   * A fact of a person's adoption. In the context of a parent-child relationship, it describes a fact of the adoption of a child by a parent.
   */
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  Adoption,

  /**
   * A fact of a person's christening as an adult.
   */
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  AdultChristening,

  /**
   * A fact of a person's amnesty.
   */
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  Amnesty,

  /**
   * A fact of a person's apprenticeship.
   */
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  Apprenticeship,

  /**
   * A fact of a person's arrest.
   */
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  Arrest,

  /**
   * A fact of a person's award (medal, honor).
   */
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  Award,

  /**
   * A fact of a person's baptism.
   */
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  Baptism,

  /**
   * A fact of a person's bar mitzvah.
   */
  BarMitzvah,

  /**
   * A fact of a person's bat mitzvah.
   */
  BatMitzvah,

  /**
   * A fact of a person's birth.
   */
  Birth,

  /**
   * A fact of a person's birth notice, such as posted in a newspaper or other publishing medium.
   */
  BirthNotice,

  /**
   * A fact of an official blessing received by a person, such as at the hands of a clergy member or at another religious rite.
   */
  Blessing,

  /**
   * A fact of the burial of person's body after death.
   */
  Burial,

  /**
   * A fact of a person's caste.
   */
  Caste,

  /**
   * A fact of a person's participation in a census.
   */
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  Census,

  /**
   * A fact of a person's christening *at birth*. Note: use `AdultChristening` for the christening as an adult.
   */
  Christening,

  /**
   * A fact of a person's circumcision.
   */
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  Circumcision,

  /**
   * A fact of a person's clan.
   */
  Clan,

  /**
   * A fact of a person's confirmation (or other rite of initiation) in a church or religion.
   */
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  Confirmation,

  /**
   * A fact of the appearance of a person in a court proceeding.
   */
  Court,

  /**
   * A fact of the cremation of person's body after death.
   */
  Cremation,

  /**
   * A fact of the death of a person.
   */
  Death,

  /**
   * A fact of an education of a person.
   */
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  Education,

  /**
   * A fact of a person's enrollment in an educational program or institution.
   */
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  EducationEnrollment,

  /**
   * A fact of the emigration of a person.
   */
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  Emigration,

  /**
   * A fact of a person's enslavement.
   */
  Enslavement,

  /**
   * A fact of a person's ethnicity or race.
   */
  Ethnicity,

  /**
   * A fact of a person's excommunication from a church.
   */
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  Excommunication,

  /**
   * A fact of a person's first communion in a church.
   */
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  FirstCommunion,

  /**
   * A fact of a person's funeral.
   */
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  Funeral,

  /**
   * A fact of a person's gender change.
   */
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  GenderChange,

  /**
   * A fact of a person's graduation from a scholastic institution.
   */
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  Graduation,

  /**
   * A fact of a person's heimat. "Heimat" refers to a person's affiliation by birth to a specific geographic place.
   * Distinct heimaten are often useful as indicators that two persons of the same name are not likely to be closely related genealogically.
   * In English, "heimat" may be described using terms like "ancestral home", "homeland", or "place of origin".
   */
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  Heimat,

  /**
   * A fact of a person's immigration.
   */
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  Immigration,

  /**
   * A fact of a person's imprisonment.
   */
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  Imprisonment,

  /**
   * A legal inquest. Inquests usually only occur when there’s something suspicious about the death. Inquests might in
   * some instances lead to a murder investigation. Most people that die have a death certificate wherein a doctor indicates
   * the cause of death and often indicates when the decedent was last seen by that physician; these require no inquest.
   */
  Inquest,

  /**
   * A fact of a land transaction enacted by a person.
   */
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  LandTransaction,

  /**
   * A fact of a language spoken by a person.
   */
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  Language,

  /**
   * A fact of a record of a person's living for a specific period. This is designed to include "flourish", defined to mean the time period
   * in an adult's life where he was most productive, perhaps as a writer or member of the state assembly. It does not reflect the person's birth and death dates.
   */
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  Living,

  /**
   * A fact of a person's marital status.
   */
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  MaritalStatus,

  /**
   * A fact of a person's medical record, such as for an illness or hospital stay.
   */
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  Medical,

  /**
   * A fact of a person's military award.
   */
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  MilitaryAward,

  /**
   * A fact of a person's military discharge.
   */
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  MilitaryDischarge,

  /**
   * A fact of a person's registration for a military draft.
   */
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  MilitaryDraftRegistration,

  /**
   * A fact of a person's military induction.
   */
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  MilitaryInduction,

  /**
   * A fact of a person's militray service.
   */
  MilitaryService,

  /**
   * A fact of a person's church mission.
   */
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  Mission,

  /**
   * A fact of a person's move (i.e. change of residence) from a location.
   */
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  MoveFrom,

  /**
   * A fact of a person's move (i.e. change of residence) to a new location.
   */
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  MoveTo,

  /**
   * A fact that a person was born as part of a multiple birth (e.g. twin, triplet, etc.)
   */
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  MultipleBirth,

  /**
   * A fact of a person's national id (e.g. social security number).
   */
  NationalId,

  /**
   * A fact of a person's nationality.
   */
  Nationality,

  /**
   * A fact of a person's naturalization (i.e. acquisition of citizenship and nationality).
   */
  Naturalization,

  /**
   * A fact of a person's number of marriages.
   */
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  NumberOfMarriages,

  /**
   * A fact of a person's obituary.
   */
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  Obituary,

  /**
   * A fact of a person's occupation or employment.
   */
  Occupation,

  /**
   * A fact of a person's ordination to a stewardship in a church.
   */
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  Ordination,

  /**
   * A fact of a person's legal pardon.
   */
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  Pardon,

  /**
   * A fact of a person's physical description.
   */
  PhysicalDescription,

  /**
   * A fact of a receipt of probate of a person's property.
   */
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  Probate,

  /**
   * A fact of a person's property or possessions.
   */
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  Property,

  /**
   * A fact of the declaration of a person's race, presumably in a historical document.
   */
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  Race,

  /**
   * A fact of a person's religion.
   */
  Religion,

  /**
   * A fact of a person's residence.
   */
  Residence,

  /**
   * A fact of a person's retirement.
   */
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  Retirement,

  /**
   * A fact of a person's stillbirth.
   */
  Stillbirth,

  /**
   * A fact of a person's tax assessment.
   */
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  TaxAssessment,

  /**
   * A fact of a person's tribe.
   */
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  Tribe,

  /**
   * A fact of a person's will.
   */
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  Will,

  /**
   * A fact of a person's visit to a place different from the person's residence.
   */
  Visit,

  /**
   * A fact of a person's _yahrzeit_ date.  A person's yahzeit is the anniversary of their death as measured by the Hebrew calendar.
   */
  Yahrzeit,

  // facts generally applicable within the scope of a couple.

  /**
   * The fact of an annulment of a marriage.
   */
  Annulment,

  /**
   * The fact of a marriage by common law.
   */
  CommonLawMarriage,

  /**
   * The fact of a civil union.
   */
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  CivilUnion,

  /**
   * The fact of a divorce of a couple.
   */
  Divorce,

  /**
   * The fact of a filing for divorce.
   */
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  DivorceFiling,

  /**
   * The fact of a domestic partnership.
   */
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  DomesticPartnership,

  /**
   * The fact of an engagement to be married.
   */
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  Engagement,

  /**
   * The fact of a marriage.
   */
  Marriage,

  /**
   * The fact of a marriage banns.
   */
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  MarriageBanns,

  /**
   * The fact of a marriage contract.
   */
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  MarriageContract,

  /**
   * The fact of a marriage license.
   */
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  MarriageLicense,

  /**
   * The fact of a marriage notice.
   */
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  MarriageNotice,

  /**
   * A fact of the number of children of a person or relationship.
   */
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  NumberOfChildren,

  /**
   * A fact of a couple's separation.
   */
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  Separation,

  // facts generally applicable within the scope of a parent-child relationship.

  /**
   * A fact about an adoptive relationship between a parent an a child.
   */
  AdoptiveParent,

  /**
   * A fact the biological relationship between a parent and a child.
   */
  BiologicalParent,

  /**
   * A fact about a foster relationship between a foster parent and a child.
   */
  FosterParent,

  /**
   * A fact about a legal guardianship between a parent and a child.
   */
  GuardianParent,

  /**
   * A fact about the step relationship between a parent and a child.
   */
  StepParent,

  /**
   * A fact about a sociological relationship between a parent and a child, but not definable in typical legal or biological terms.
   */
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  SociologicalParent,

  /**
   * A fact about a pregnancy surrogate relationship between a parent and a child.
   */
  @Facet ( GedcomxConstants.FACET_FS_FT_UNSUPPORTED )
  SurrogateParent,

  @XmlUnknownQNameEnumValue
  OTHER;

  public final static Set<FactType> BIRTHLIKE_FACT_TYPES = Collections.unmodifiableSet(EnumSet.of(Baptism, Birth, BirthNotice, Christening, Blessing, Circumcision, Adoption));
  public final static Set<FactType> DEATHLIKE_FACT_TYPES = Collections.unmodifiableSet(EnumSet.of(Death, Burial, Cremation, Funeral, Obituary, Probate, Will));
  public final static Set<FactType> MARRIAGELIKE_FACT_TYPES = Collections.unmodifiableSet(EnumSet.of(Marriage, Engagement, MarriageBanns, MarriageContract, MarriageLicense, MarriageNotice));
  public final static Set<FactType> DIVORCELIKE_FACT_TYPES = Collections.unmodifiableSet(EnumSet.of(Divorce, DivorceFiling, Annulment, Separation));
  public final static Set<FactType> MIGRATIONLIKE_FACT_TYPES = Collections.unmodifiableSet(EnumSet.of(Immigration, Emigration, Naturalization, MoveTo, MoveFrom));

  public boolean isBirthLike() {
    return BIRTHLIKE_FACT_TYPES.contains(this);
  }

  public boolean isDeathLike() {
    return DEATHLIKE_FACT_TYPES.contains(this);
  }

  public boolean isMarriageLike() {
    return MARRIAGELIKE_FACT_TYPES.contains(this);
  }

  public boolean isDivorceLike() {
    return DIVORCELIKE_FACT_TYPES.contains(this);
  }

  public boolean isMigrationLike() {
    return MIGRATIONLIKE_FACT_TYPES.contains(this);
  }

  private static final EnumURIMap<FactType> URI_MAP = new EnumURIMap<FactType>(FactType.class, GedcomxConstants.GEDCOMX_TYPES_NAMESPACE);

  /**
   * Return the QName value for this enum.
   *
   * @return The QName value for this enum.
   */
  public URI toQNameURI() {
    return URI_MAP.toURIValue(this);
  }

  /**
   * Get the enumeration from the QName.
   *
   * @param qname The qname.
   * @return The enumeration.
   */
  public static FactType fromQNameURI(URI qname) {
    return URI_MAP.fromURIValue(qname);
  }

  /**
   * Enumeration of types that are applicable to a person.
   */
  @XmlTransient
  public static final class Person {
    private Person(){}

    private static final EnumSet<FactType> PERSON_FACT_TYPES = EnumSet.noneOf(FactType.class);
    private static FactType include(FactType type) {
      PERSON_FACT_TYPES.add(type);
      return type;
    }
    
    public static final FactType Adoption = include(FactType.Adoption);
    public static final FactType AdultChristening = include(FactType.AdultChristening);
    public static final FactType Amnesty = include(FactType.Amnesty);
    public static final FactType Apprenticeship = include(FactType.Apprenticeship);
    public static final FactType Arrest = include(FactType.Arrest);
    public static final FactType Award = include(FactType.Award);
    public static final FactType Baptism = include(FactType.Baptism);
    public static final FactType BarMitzvah = include(FactType.BarMitzvah);
    public static final FactType BatMitzvah = include(FactType.BatMitzvah);
    public static final FactType Birth = include(FactType.Birth);
    public static final FactType BirthNotice = include(FactType.BirthNotice);
    public static final FactType Blessing = include(FactType.Blessing);
    public static final FactType Burial = include(FactType.Burial);
    public static final FactType Caste = include(FactType.Caste);
    public static final FactType Census = include(FactType.Census);
    public static final FactType Christening = include(FactType.Christening);
    public static final FactType Circumcision = include(FactType.Circumcision);
    public static final FactType Clan = include(FactType.Clan);
    public static final FactType Confirmation = include(FactType.Confirmation);
    public static final FactType Court = include(FactType.Court);
    public static final FactType Cremation = include(FactType.Cremation);
    public static final FactType Death = include(FactType.Death);
    public static final FactType Education = include(FactType.Education);
    public static final FactType EducationEnrollment = include(FactType.EducationEnrollment);
    public static final FactType Emigration = include(FactType.Emigration);
    public static final FactType Ethnicity = include(FactType.Ethnicity);
    public static final FactType Excommunication = include(FactType.Excommunication);
    public static final FactType FirstCommunion = include(FactType.FirstCommunion);
    public static final FactType Funeral = include(FactType.Funeral);
    public static final FactType GenderChange = include(FactType.GenderChange);
    public static final FactType Graduation = include(FactType.Graduation);
    public static final FactType Heimat = include(FactType.Heimat);
    public static final FactType Immigration = include(FactType.Immigration);
    public static final FactType Imprisonment = include(FactType.Imprisonment);
    public static final FactType Inquest = include(FactType.Inquest);
    public static final FactType LandTransaction = include(FactType.LandTransaction);
    public static final FactType Language = include(FactType.Language);
    public static final FactType Living = include(FactType.Living);
    public static final FactType MaritalStatus = include(FactType.MaritalStatus);
    public static final FactType Medical = include(FactType.Medical);
    public static final FactType MilitaryAward = include(FactType.MilitaryAward);
    public static final FactType MilitaryDischarge = include(FactType.MilitaryDischarge);
    public static final FactType MilitaryDraftRegistration = include(FactType.MilitaryDraftRegistration);
    public static final FactType MilitaryInduction = include(FactType.MilitaryInduction);
    public static final FactType MilitaryService = include(FactType.MilitaryService);
    public static final FactType Mission = include(FactType.Mission);
    public static final FactType MoveFrom = include(FactType.MoveFrom);
    public static final FactType MoveTo = include(FactType.MoveTo);
    public static final FactType MultipleBirth = include(FactType.MultipleBirth);
    public static final FactType NationalId = include(FactType.NationalId);
    public static final FactType Nationality = include(FactType.Nationality);
    public static final FactType Naturalization = include(FactType.Naturalization);
    public static final FactType NumberOfChildren = include(FactType.NumberOfChildren);
    public static final FactType NumberOfMarriages = include(FactType.NumberOfMarriages);
    public static final FactType Obituary = include(FactType.Obituary);
    public static final FactType Occupation = include(FactType.Occupation);
    public static final FactType Ordination = include(FactType.Ordination);
    public static final FactType Pardon = include(FactType.Pardon);
    public static final FactType PhysicalDescription = include(FactType.PhysicalDescription);
    public static final FactType Probate = include(FactType.Probate);
    public static final FactType Property = include(FactType.Property);
    public static final FactType Race = include(FactType.Race);
    public static final FactType Religion = include(FactType.Religion);
    public static final FactType Residence = include(FactType.Residence);
    public static final FactType Retirement = include(FactType.Retirement);
    public static final FactType Stillbirth = include(FactType.Stillbirth);
    public static final FactType TaxAssessment = include(FactType.TaxAssessment);
    public static final FactType Tribe = include(FactType.Tribe);
    public static final FactType Will = include(FactType.Will);
    public static final FactType Visit = include(FactType.Visit);
    public static final FactType Yahrzeit = include(FactType.Yahrzeit);

    /**
     * Whether the given fact type is applicable to a person.
     * 
     * @param type The fact type.
     * @return Whether the given fact type is applicable to a person.
     */
    public static boolean isApplicable(FactType type) {
      return PERSON_FACT_TYPES.contains(type);
    }
  }

  @XmlTransient
  public static final class Couple {
    private Couple() {}

    private static final EnumSet<FactType> COUPLE_FACT_TYPES = EnumSet.noneOf(FactType.class);
    private static FactType include(FactType type) {
      COUPLE_FACT_TYPES.add(type);
      return type;
    }
    
    public static final FactType Annulment = include(FactType.Annulment);
    public static final FactType CommonLawMarriage = include(FactType.CommonLawMarriage);
    public static final FactType CivilUnion = include(FactType.CivilUnion);
    public static final FactType DomesticPartnership = include(FactType.DomesticPartnership);
    public static final FactType Divorce = include(FactType.Divorce);
    public static final FactType DivorceFiling = include(FactType.DivorceFiling);
    public static final FactType Engagement = include(FactType.Engagement);
    public static final FactType Marriage = include(FactType.Marriage);
    public static final FactType MarriageBanns = include(FactType.MarriageBanns);
    public static final FactType MarriageContract = include(FactType.MarriageContract);
    public static final FactType MarriageLicense = include(FactType.MarriageLicense);
    public static final FactType MarriageNotice = include(FactType.MarriageNotice);
    public static final FactType NumberOfChildren = include(FactType.NumberOfChildren);
    public static final FactType Separation = include(FactType.Separation);

    /**
     * Whether the given fact type is applicable to a couple.
     * 
     * @param type The fact type.
     * @return Whether the given fact type is applicable to a couple.
     */
    public static boolean isApplicable(FactType type) {
      return COUPLE_FACT_TYPES.contains(type);
    }
  }

  @XmlTransient
  public static final class ParentChild {
    private ParentChild() {}

    private static final EnumSet<FactType> PARENT_CHILD_FACT_TYPES = EnumSet.noneOf(FactType.class);
    private static FactType include(FactType type) {
      PARENT_CHILD_FACT_TYPES.add(type);
      return type;
    }

    public static final FactType AdoptiveParent = include(FactType.AdoptiveParent);
    public static final FactType BiologicalParent = include(FactType.BiologicalParent);
    public static final FactType FosterParent = include(FactType.FosterParent);
    public static final FactType GuardianParent = include(FactType.GuardianParent);
    public static final FactType StepParent = include(FactType.StepParent);
    public static final FactType SociologicalParent = include(FactType.SociologicalParent);
    public static final FactType SurrogateParent = include(FactType.SurrogateParent);

    /**
     * Whether the given fact type is applicable to a parent-child relationship.
     * 
     * @param type The fact type.
     * @return Whether the given fact type is applicable to a parent-child relationship.
     */
    public static boolean isApplicable(FactType type) {
      return PARENT_CHILD_FACT_TYPES.contains(type);
    }
  }

}
