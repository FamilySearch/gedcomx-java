package org.gedcomx.atom;

import org.gedcomx.conclusion.Conclusion;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlRootElement ( namespace = "urn:custom" )
@XmlType ( namespace = "urn:custom" )
public class JunkEntity extends Conclusion {
}
