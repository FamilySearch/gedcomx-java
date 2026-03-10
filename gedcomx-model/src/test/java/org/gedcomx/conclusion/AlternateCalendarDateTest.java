package org.gedcomx.conclusion;

import org.gedcomx.Gedcomx;
import org.gedcomx.records.Field;
import org.gedcomx.records.FieldValue;
import org.gedcomx.records.HasFields;
import org.gedcomx.rt.json.GedcomJacksonModule;
import org.gedcomx.types.CalendarType;
import org.gedcomx.types.FieldType;
import org.gedcomx.types.FieldValueType;
import org.gedcomx.util.MarshalUtil;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class AlternateCalendarDateTest {
  @Test
  void testAlternateCalendarDate() {
    Date date = new Date();
    date.setOriginal("28 March 2021");
    date.setFormal("+2021-03-28");
    assertEquals("28 March 2021", date.getOriginal());
    assertNull(date.getAlternateCalendars());

    AlternateCalendarDate altDate = new AlternateCalendarDate(CalendarType.Hebrew);
    altDate.setOriginal("15 Nisan 5781");
    date.addAlternateCalendar(altDate);
    assertEquals(1, date.getAlternateCalendars().size());
    assertEquals("15 Nisan 5781", altDate.getOriginal());
    assertEquals(CalendarType.Hebrew, altDate.getCalendar());
  }

  @Test
  void testBuilders() {
    Date date = new Date();
    date.setOriginal("10 February 1741");
    date.setFormal("+1741-02-10");
    assertNull(date.getAlternateCalendars());

    AlternateCalendarDate altDate = new AlternateCalendarDate(CalendarType.Hebrew)
        .calendar(CalendarType.Julian);
    altDate.original("10 February 1740");
    date.alternateCalendar(altDate);
    assertEquals(1, date.getAlternateCalendars().size());
    assertEquals("10 February 1741", date.getOriginal());
    assertEquals("10 February 1740", altDate.getOriginal());
    assertEquals(CalendarType.Julian, altDate.getCalendar());
  }

  @Test
  void testMarshallAlternateDate() {
    Date date = new Date();
    date.setOriginal("28 March 2021");
    date.setFormal("+2021-03-28");
    date.field(new Field().type(FieldType.Date)
        .value(new FieldValue("28 March 2021").type(FieldValueType.Interpreted)));

    AlternateCalendarDate altDate = new AlternateCalendarDate(CalendarType.Hebrew);
    altDate.setOriginal("15 Nisan 5781");
    altDate.field(new Field().type(FieldType.Date)
        // Hebrew date in the original Hebrew script
        .value(new FieldValue("ט״ו בְּנִיסָן ה׳תשפ״א").type(FieldValueType.Original))
        // Same Hebrew date romanized
        .value(new FieldValue("15 Nisan 5781").type(FieldValueType.Interpreted)));
    date.addAlternateCalendar(altDate);

    Gedcomx doc = new Gedcomx()
        .person(new Person()
            .fact(new Fact()
                .date(date)));
    //Test XML marshalling/unmarshalling
    String xml = MarshalUtil.toXml(doc);
    Gedcomx unmarshalledDoc = fromXml(xml);
    checkAlternateDate(unmarshalledDoc, date, altDate);

    //Test JSON marshalling/unmarshalling
    ObjectMapper mapper = GedcomJacksonModule.createJsonMapper(Gedcomx.class);
    String json = mapper.writeValueAsString(doc);
    Gedcomx unmarshalledJsonDoc = mapper.readValue(json, Gedcomx.class);
    checkAlternateDate(unmarshalledJsonDoc, date, altDate);
  }

  private static void checkAlternateDate(Gedcomx unmarshalledDoc, Date date, AlternateCalendarDate altDate) {
    Date unmarshalledDate = unmarshalledDoc.getPersons().get(0).getFacts().get(0).getDate();
    assertEquals(date.getOriginal(), unmarshalledDate.getOriginal());
    assertEquals(date.getFormal(), unmarshalledDate.getFormal());
    assertEquals(1, unmarshalledDate.getAlternateCalendars().size());
    checkfields(date, unmarshalledDate);
    AlternateCalendarDate unmarshalledAltDate = unmarshalledDate.getAlternateCalendars().get(0);
    assertEquals(altDate.getOriginal(), unmarshalledAltDate.getOriginal());
    assertEquals(altDate.getCalendar(), unmarshalledAltDate.getCalendar());
    checkfields(altDate, unmarshalledAltDate);
  }

  private static void checkfields(HasFields expectedFields, HasFields actualFields) {
    assertEquals(expectedFields.getFields().size(), actualFields.getFields().size());
    for (int i = 0; i < expectedFields.getFields().size(); i++) {
      Field expectedField = expectedFields.getFields().get(i);
      Field actualField = actualFields.getFields().get(i);
      assertEquals(expectedField.getType(), actualField.getType());
      assertEquals(expectedField.getValues().size(), actualField.getValues().size());
      for (int j = 0; j < expectedField.getValues().size(); j++) {
        FieldValue expectedValue = expectedField.getValues().get(j);
        FieldValue actualValue = actualField.getValues().get(j);
        assertEquals(expectedValue.getType(), actualValue.getType());
        assertEquals(expectedValue.getText(), actualValue.getText());
      }
    }
  }

  private Gedcomx fromXml(String xml) {
    try {
      return MarshalUtil.unmarshal(new java.io.ByteArrayInputStream(xml.getBytes()));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
