package org.gedcomx.examples;

import org.gedcomx.Gedcomx;
import org.gedcomx.conclusion.Name;
import org.gedcomx.conclusion.NameForm;
import org.gedcomx.conclusion.NamePart;
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
    NameForm nameForm = new NameForm()
      .lang("en")
      .fullText("John Fitzgerald Kennedy")
      .part(new NamePart().type(NamePartType.Given).value("John"))
      .part(new NamePart().type(NamePartType.Given).value("Fitzgerald"))
      .part(new NamePart().type(NamePartType.Surname).value("Kennedy"));
    Name name = new Name().nameForm(nameForm);

    Gedcomx gx = new Gedcomx().person(new Person().name(name));
    SerializationUtil.processThroughXml(gx);
    SerializationUtil.processThroughJson(gx);
  }

}
