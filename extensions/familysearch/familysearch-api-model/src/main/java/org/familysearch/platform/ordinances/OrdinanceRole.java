package org.familysearch.platform.ordinances;

import org.codehaus.enunciate.qname.XmlQNameEnumRef;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.gedcomx.common.ResourceReference;
import org.gedcomx.common.URI;
import org.gedcomx.conclusion.Person;
import org.gedcomx.rt.RDFRange;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "OrdinanceRole", propOrder = { "person"} )
public class OrdinanceRole {

  private ResourceReference person;
  private URI type;

  /**
   * Reference to the person playing the role in the ordinance.
   *
   * @return Reference to the person playing the role in the ordinance.
   */
  @RDFRange(Person.class)
  public ResourceReference getPerson() {
    return person;
  }

  /**
   * Reference to the person playing the role in the ordinance.
   *
   * @param person Reference to the person playing the role in the ordinance.
   */
  public void setPerson(ResourceReference person) {
    this.person = person;
  }

  /**
   * gets the type of role the person played in the ordinance
   * @return the type of role the person played in the ordinance
   */
  @XmlAttribute
  @XmlQNameEnumRef(OrdinanceRoleType.class)
  public URI getType() {
    return type;
  }

  public void setType(URI type) {
    this.type = type;
  }

  /**
   * The enum referencing the known type of role, or {@link OrdinanceRoleType#OTHER} if not known.
   *
   * @return The enum referencing the known type of role, or {@link OrdinanceRoleType#OTHER} if not known.
   */
  @XmlTransient
  @JsonIgnore
  public OrdinanceRoleType getKnownType() {
    return getType() == null ? null : OrdinanceRoleType.fromQNameURI(getType());
  }

  /**
   * Set the type of role from a known enumeration of type types.
   *
   * @param knownType the type of role in the ordinance
   */
  @JsonIgnore
  public void setKnownType(OrdinanceRoleType knownType) {
    setType(knownType == null ? null : URI.create(org.codehaus.enunciate.XmlQNameEnumUtil.toURIValue(knownType)));
  }

}
