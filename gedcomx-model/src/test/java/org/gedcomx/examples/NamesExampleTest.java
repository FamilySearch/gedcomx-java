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

  public void testMultipleJapaneseForms() throws Exception {
    NameForm kanji = new NameForm("山田太郎")
      .lang("ja-Hani")
      .part(NamePartType.Surname, "山田")
      .part(NamePartType.Given, "太郎");
    NameForm katakana = new NameForm("ヤマダタロー")
      .lang("ja-Kana")
      .part(NamePartType.Surname, "ヤマダ")
      .part(NamePartType.Given, "タロー");
    NameForm romanized = new NameForm("Yamada Tarō")
      .lang("ja-Latn")
      .part(NamePartType.Surname, "Tarō")
      .part(NamePartType.Given, "Yamada");
    Name name = new Name().nameForm(kanji).nameForm(katakana).nameForm(romanized);

    Gedcomx gx = new Gedcomx().person(new Person().name(name));
    SerializationUtil.processThroughXml(gx);
    SerializationUtil.processThroughJson(gx);
  }

}
