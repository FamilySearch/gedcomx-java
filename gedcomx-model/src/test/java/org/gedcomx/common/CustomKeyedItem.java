package org.gedcomx.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.gedcomx.rt.json.HasJsonKey;
import org.gedcomx.rt.json.JsonElementWrapper;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author Ryan Heaton
 */
@XmlRootElement
@JsonElementWrapper(name = "customkeys")
public class CustomKeyedItem implements HasJsonKey {

  private String key = String.valueOf(hashCode());
  private String val1;
  private String val2;

  @JsonIgnore @org.codehaus.jackson.annotate.JsonIgnore
  @XmlTransient
  @Override
  public boolean isHasUniqueKey() {
    return false;
  }

  @JsonIgnore @org.codehaus.jackson.annotate.JsonIgnore
  @XmlTransient
  @Override
  public String getJsonKey() {
    return getKey();
  }

  @JsonIgnore @org.codehaus.jackson.annotate.JsonIgnore
  @Override
  public void setJsonKey(String jsonKey) {
    setKey(jsonKey);
  }

  @JsonIgnore @org.codehaus.jackson.annotate.JsonIgnore
  public String getKey() {
    return key;
  }

  @JsonIgnore @org.codehaus.jackson.annotate.JsonIgnore
  public void setKey(String key) {
    this.key = key;
  }

  public String getVal1() {
    return val1;
  }

  public void setVal1(String val1) {
    this.val1 = val1;
  }

  public String getVal2() {
    return val2;
  }

  public void setVal2(String val2) {
    this.val2 = val2;
  }
}
