package org.familysearch.platform.ordinances;

import org.codehaus.enunciate.qname.XmlQNameEnumRef;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.gedcomx.common.URI;
import org.gedcomx.rt.json.JsonElementWrapper;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@JsonElementWrapper(name = "ordinanceInfo")
@XmlType( name = "OrdinanceInfo" )
public class OrdinanceInfo {

  private URI status;

  /**
   * gets the rollup status for this person or couple
   * @return the rollup status for this person or couple
   */
  @XmlAttribute
  @XmlQNameEnumRef(OrdinanceStatus.class)
  public URI getStatus() {
    return status;
  }

  /**
   * sets the rollup status for this person or couple
   * @param status the rollup status for this person or couple
   */
  public void setStatus(URI status) {
    this.status = status;
  }

  /**
   * The enum referencing the known ordinance rollup status, or {@link org.familysearch.platform.ordinances.OrdinanceStatus#OTHER} if not known.
   *
   * @return The enum referencing the known ordinance rollup status, or {@link org.familysearch.platform.ordinances.OrdinanceStatus#OTHER} if not known.
   */
  @XmlTransient
  @JsonIgnore
  public OrdinanceStatus getKnownStatus() {
    return getStatus() == null ? null : OrdinanceStatus.fromQNameURI(getStatus());
  }

  /**
   * Set the ordinance status from an enumeration of known ordinance rollup statuses.
   *
   * @param knownStatus The ordinance rollup status.
   */
  @JsonIgnore
  public void setKnownStatus(OrdinanceStatus knownStatus) {
    setStatus(knownStatus == null ? null : knownStatus.toQNameURI());
  }

}
