package org.gedcomx.examples;

import org.gedcomx.Gedcomx;
import org.gedcomx.conclusion.Name;
import org.gedcomx.conclusion.NameForm;
import org.gedcomx.conclusion.Person;
import org.gedcomx.rt.SerializationUtil;
import org.gedcomx.types.NamePartType;
import org.testng.annotations.Test;

/**
 * @author Ryan Heaton
 */
@Test
public class NamesExampleTest {

  public void testBasicWesternName() throws Exception {
    NameForm nameForm = new NameForm("John Fitzgerald Kennedy")
      .lang("en")
      .part(NamePartType.Given, "John")
      .part(NamePartType.Given, "Fitzgerald")
      .part(NamePartType.Surname, "Kennedy");
    Name name = new Name().nameForm(nameForm);

    Gedcomx gx = new Gedcomx().person(new Person().name(name));
    SerializationUtil.processThroughXml(gx);
    SerializationUtil.processThroughJson(gx);
  }

}
