package org.gedcomx.conclusion;

import org.gedcomx.common.Attribution;
import org.gedcomx.common.ResourceReference;
import org.gedcomx.common.TextValue;
import org.gedcomx.common.URI;
import org.gedcomx.types.IdentifierType;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


class PlaceTest {
  @Test
  void placeDescriptionTikhvin() throws Exception {
    PlaceDescription tikhvinDesc = new PlaceDescription();

    assertNull(tikhvinDesc.getNames());
    assertNull(tikhvinDesc.getType());
    assertNull(tikhvinDesc.getTemporalDescription());
    assertNull(tikhvinDesc.getLatitude());
    assertNull(tikhvinDesc.getLongitude());
    assertNull(tikhvinDesc.getSpatialDescription());
    assertNull(tikhvinDesc.getAttribution());
    assertNull(tikhvinDesc.getExtensionElements());

    tikhvinDesc.setNames(new ArrayList<TextValue>());
    tikhvinDesc.getNames().add(new TextValue());
    tikhvinDesc.getNames().add(new TextValue());
    tikhvinDesc.getNames().add(new TextValue());
    tikhvinDesc.getNames().get(0).setLang("ru-Cyrl");
    tikhvinDesc.getNames().get(0).setValue("Ти́хвин, Ленингра́дская о́бласть, Россия");
    tikhvinDesc.getNames().get(1).setLang("ru-Latn");
    tikhvinDesc.getNames().get(1).setValue("Tikhvin, Leningradskaya Oblast', Rossiya");
    tikhvinDesc.getNames().get(2).setLang("en-Latn");
    tikhvinDesc.getNames().get(2).setValue("Tikhvin, Leningrad Oblast, Russia");
    tikhvinDesc.setType(URI.create("urn:place-authority/city"));
    tikhvinDesc.setTemporalDescription(new Date());
    tikhvinDesc.getTemporalDescription().setFormal("A+1383/");
    tikhvinDesc.setLatitude(59.6436111d);
    tikhvinDesc.setLongitude(33.5094444d);
    tikhvinDesc.setSpatialDescription(new ResourceReference(URI.create("data:application/vnd.google-earth.kml+xml;base64," +
                                                                         "PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4NCjxrbWwgeG1sbnM9Imh0dHA6" +
                                                                         "Ly93d3cub3Blbmdpcy5uZXQva21sLzIuMiIgeG1sbnM6Z3g9Imh0dHA6Ly93d3cuZ29vZ2xlLmNv" +
                                                                         "bS9rbWwvZXh0LzIuMiIgeG1sbnM6a21sPSJodHRwOi8vd3d3Lm9wZW5naXMubmV0L2ttbC8yLjIi" +
                                                                         "IHhtbG5zOmF0b209Imh0dHA6Ly93d3cudzMub3JnLzIwMDUvQXRvbSI+DQo8UGxhY2VtYXJrIGlk" +
                                                                         "PSIyMSI+DQoJPG5hbWU+VGlraHZpbiwgTGVuaW5ncmFkIE9ibGFzdCwgUnVzc2lhPC9uYW1lPg0K" +
                                                                         "CTxNdWx0aUdlb21ldHJ5Pg0KCQk8UG9pbnQ+DQoJCTxjb29yZGluYXRlcz4zMy41LDU5LjYzMzMz" +
                                                                         "MzAwMDAwMDAxLDA8L2Nvb3JkaW5hdGVzPg0KCQk8L1BvaW50Pg0KCQk8TGluZWFyUmluZz4NCgkJ" +
                                                                         "CTxjb29yZGluYXRlcz4NCgkJCQkzMy40NTA5Niw1OS42MTYxNTk0LDAgMzMuNDUwOTYsNTkuNjUw" +
                                                                         "NTA2NiwwIDMzLjU0OTA0LDU5LjY1MDUwNjYsMCAzMy41NDkwNCw1OS42MTYxNTk0LDAgMzMuNDUw" +
                                                                         "OTYsNTkuNjE2MTU5NCwwIA0KCQkJPC9jb29yZGluYXRlcz4NCgkJPC9MaW5lYXJSaW5nPg0KCTwv" +
                                                                         "TXVsdGlHZW9tZXRyeT4NCjwvUGxhY2VtYXJrPg0KPC9rbWw+DQo=")));
    tikhvinDesc.setIdentifiers(new ArrayList<Identifier>());
    tikhvinDesc.getIdentifiers().add(new Identifier());
    tikhvinDesc.getIdentifiers().get(0).setKnownType(IdentifierType.Primary);
    tikhvinDesc.getIdentifiers().get(0).setValue(URI.create("https://labs.familysearch.org/stdfinder/PlaceDetail.jsp?placeId=3262902#placeDescriptionId"));
    tikhvinDesc.setAttribution(new Attribution());
    tikhvinDesc.getAttribution().setContributor(new ResourceReference(URI.create("urn:contributorId")));
    tikhvinDesc.getAttribution().setModified(new java.util.Date(1321027871111L)); // 11 Nov 2011 11:11:11.111
    tikhvinDesc.addExtensionElement("tikhvinDesc-junkExtensionElement");

    assertEquals("ru-Cyrl", tikhvinDesc.getNames().get(0).getLang());
    assertEquals("Ти́хвин, Ленингра́дская о́бласть, Россия", tikhvinDesc.getNames().get(0).getValue());
    assertEquals("ru-Latn", tikhvinDesc.getNames().get(1).getLang());
    assertEquals("Tikhvin, Leningradskaya Oblast', Rossiya", tikhvinDesc.getNames().get(1).getValue());
    assertEquals("en-Latn", tikhvinDesc.getNames().get(2).getLang());
    assertEquals("Tikhvin, Leningrad Oblast, Russia", tikhvinDesc.getNames().get(2).getValue());
    assertEquals("urn:place-authority/city", tikhvinDesc.getType().toURI().toString());
    assertEquals("A+1383/", tikhvinDesc.getTemporalDescription().getFormal());
    assertEquals(59.6436111d, tikhvinDesc.getLatitude(), 0d);
    assertEquals(33.5094444d, tikhvinDesc.getLongitude(), 0d);
    assertEquals(tikhvinDesc.getSpatialDescription().getResource().toURI().toString(), "data:application/vnd.google-earth.kml+xml;base64," +
      "PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4NCjxrbWwgeG1sbnM9Imh0dHA6" +
      "Ly93d3cub3Blbmdpcy5uZXQva21sLzIuMiIgeG1sbnM6Z3g9Imh0dHA6Ly93d3cuZ29vZ2xlLmNv" +
      "bS9rbWwvZXh0LzIuMiIgeG1sbnM6a21sPSJodHRwOi8vd3d3Lm9wZW5naXMubmV0L2ttbC8yLjIi" +
      "IHhtbG5zOmF0b209Imh0dHA6Ly93d3cudzMub3JnLzIwMDUvQXRvbSI+DQo8UGxhY2VtYXJrIGlk" +
      "PSIyMSI+DQoJPG5hbWU+VGlraHZpbiwgTGVuaW5ncmFkIE9ibGFzdCwgUnVzc2lhPC9uYW1lPg0K" +
      "CTxNdWx0aUdlb21ldHJ5Pg0KCQk8UG9pbnQ+DQoJCTxjb29yZGluYXRlcz4zMy41LDU5LjYzMzMz" +
      "MzAwMDAwMDAxLDA8L2Nvb3JkaW5hdGVzPg0KCQk8L1BvaW50Pg0KCQk8TGluZWFyUmluZz4NCgkJ" +
      "CTxjb29yZGluYXRlcz4NCgkJCQkzMy40NTA5Niw1OS42MTYxNTk0LDAgMzMuNDUwOTYsNTkuNjUw" +
      "NTA2NiwwIDMzLjU0OTA0LDU5LjY1MDUwNjYsMCAzMy41NDkwNCw1OS42MTYxNTk0LDAgMzMuNDUw" +
      "OTYsNTkuNjE2MTU5NCwwIA0KCQkJPC9jb29yZGluYXRlcz4NCgkJPC9MaW5lYXJSaW5nPg0KCTwv" +
      "TXVsdGlHZW9tZXRyeT4NCjwvUGxhY2VtYXJrPg0KPC9rbWw+DQo=");
    assertEquals(IdentifierType.Primary, tikhvinDesc.getIdentifiers().get(0).getKnownType());
    assertEquals("https://labs.familysearch.org/stdfinder/PlaceDetail.jsp?placeId=3262902#placeDescriptionId", tikhvinDesc.getIdentifiers().get(0).getValue().toURI().toString());
    assertEquals("urn:contributorId", tikhvinDesc.getAttribution().getContributor().getResource().toURI().toString());
    assertEquals(1321027871111L, tikhvinDesc.getAttribution().getModified().getTime());
    assertEquals("tikhvinDesc-junkExtensionElement", tikhvinDesc.findExtensionOfType(String.class));
  }

  @Test
  void placeReferenceTikhvin() throws Exception {
    PlaceReference tikhvinRef = new PlaceReference();

    assertNull(tikhvinRef.getOriginal());
    assertNull(tikhvinRef.getDescriptionRef());
    assertNull(tikhvinRef.getExtensionElements());

    tikhvinRef.setOriginal("Tikhvin, Leningradskaya Oblast, Russia");
    tikhvinRef.setDescriptionRef(URI.create("#tikhvinDesc1"));
    tikhvinRef.addExtensionElement("tikhvinRef-junkExtensionElement");

    assertEquals("Tikhvin, Leningradskaya Oblast, Russia", tikhvinRef.getOriginal());
    assertEquals("#tikhvinDesc1", tikhvinRef.getDescriptionRef().toURI().toString());
    assertEquals("tikhvinRef-junkExtensionElement", tikhvinRef.findExtensionOfType(String.class));
    assertEquals("PlaceReference{original='Tikhvin, Leningradskaya Oblast, Russia', descriptionRef='#tikhvinDesc1'}", tikhvinRef.toString());
  }

  @Test
  void placeDescriptionLuga() throws Exception {
    PlaceDescription lugaDesc = new PlaceDescription();

    assertNull(lugaDesc.getNames());
    assertNull(lugaDesc.getType());
    assertNull(lugaDesc.getTemporalDescription());
    assertNull(lugaDesc.getLatitude());
    assertNull(lugaDesc.getLongitude());
    assertNull(lugaDesc.getSpatialDescription());
    assertNull(lugaDesc.getAttribution());
    assertNull(lugaDesc.getExtensionElements());

    lugaDesc.setNames(new ArrayList<TextValue>());
    lugaDesc.getNames().add(new TextValue());
    lugaDesc.getNames().add(new TextValue());
    lugaDesc.getNames().add(new TextValue());
    lugaDesc.getNames().get(0).setLang("ru-Cyrl");
    lugaDesc.getNames().get(0).setValue("Лу́га, Новгоро́дская о́бласть, Россия");
    lugaDesc.getNames().get(1).setLang("ru-Latn");
    lugaDesc.getNames().get(1).setValue("Luga, Leningradskaya Oblast', Rossiya");
    lugaDesc.getNames().get(2).setLang("en-Latn");
    lugaDesc.getNames().get(2).setValue("Luga, Leningrad Oblast, Russia");
    lugaDesc.setType(URI.create("urn:place-authority/city"));
    lugaDesc.setTemporalDescription(new Date());
    lugaDesc.getTemporalDescription().setFormal("+1777-08-03/");
    lugaDesc.setLatitude(58.7372222d);
    lugaDesc.setLongitude(29.8452778d);
    lugaDesc.setSpatialDescription(new ResourceReference(URI.create("data:application/vnd.google-earth.kml+xml;base64," +
                                                                      "PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4NCjxrbWwgeG1sbnM9Imh0dHA6" +
                                                                      "Ly93d3cub3Blbmdpcy5uZXQva21sLzIuMiIgeG1sbnM6Z3g9Imh0dHA6Ly93d3cuZ29vZ2xlLmNv" +
                                                                      "bS9rbWwvZXh0LzIuMiIgeG1sbnM6a21sPSJodHRwOi8vd3d3Lm9wZW5naXMubmV0L2ttbC8yLjIi" +
                                                                      "IHhtbG5zOmF0b209Imh0dHA6Ly93d3cudzMub3JnLzIwMDUvQXRvbSI+DQo8UGxhY2VtYXJrPg0K" +
                                                                      "CTxuYW1lPkx1Z2EsIExlbmluZ3JhZCBPYmxhc3QsIFJ1c3NpYTwvbmFtZT4NCgk8TXVsdGlHZW9t" +
                                                                      "ZXRyeT4NCgkJPFBvaW50Pg0KCQk8Y29vcmRpbmF0ZXM+MjkuODQ3OTY2LDU4LjczNTIxMywwPC9j" +
                                                                      "b29yZGluYXRlcz4NCgkJPC9Qb2ludD4NCgkJPExpbmVhclJpbmc+DQoJCQk8Y29vcmRpbmF0ZXM+" +
                                                                      "DQoJCQkJMjkuODA1NTQzMiw1OC43MDA4MjY2LDAgMjkuODA1NTQzMiw1OC43Njk1OTk0LDAgMjku" +
                                                                      "ODkwMzg4OCw1OC43Njk1OTk0LDAgMjkuODkwMzg4OCw1OC43MDA4MjY2LDAgMjkuODA1NTQzMiw1" +
                                                                      "OC43MDA4MjY2LDAgDQoJCQk8L2Nvb3JkaW5hdGVzPg0KCQk8L0xpbmVhclJpbmc+DQoJPC9NdWx0" +
                                                                      "aUdlb21ldHJ5Pg0KPC9QbGFjZW1hcms+DQo8L2ttbD4NCg==")));
    lugaDesc.setIdentifiers(new ArrayList<Identifier>());
    lugaDesc.getIdentifiers().add(new Identifier());
    lugaDesc.getIdentifiers().get(0).setKnownType(IdentifierType.Primary);
    lugaDesc.getIdentifiers().get(0).setValue(URI.create("https://labs.familysearch.org/stdfinder/PlaceDetail.jsp?placeId=3314013#placeDescriptionId"));
    lugaDesc.setAttribution(new Attribution());
    lugaDesc.getAttribution().setContributor(new ResourceReference(URI.create("urn:contributorId")));
    lugaDesc.getAttribution().setModified(new java.util.Date(1321027871111L)); // 11 Nov 2011 11:11:11.111
    lugaDesc.addExtensionElement("lugaDesc-junkExtensionElement");

    assertEquals("ru-Cyrl", lugaDesc.getNames().get(0).getLang());
    assertEquals("Лу́га, Новгоро́дская о́бласть, Россия", lugaDesc.getNames().get(0).getValue());
    assertEquals("ru-Latn", lugaDesc.getNames().get(1).getLang());
    assertEquals("Luga, Leningradskaya Oblast', Rossiya", lugaDesc.getNames().get(1).getValue());
    assertEquals("en-Latn", lugaDesc.getNames().get(2).getLang());
    assertEquals("Luga, Leningrad Oblast, Russia", lugaDesc.getNames().get(2).getValue());
    assertEquals("urn:place-authority/city", lugaDesc.getType().toURI().toString());
    assertEquals("+1777-08-03/", lugaDesc.getTemporalDescription().getFormal());
    assertEquals(58.7372222d, lugaDesc.getLatitude(), 0d);
    assertEquals(29.8452778d, lugaDesc.getLongitude(), 0d);
    assertEquals(lugaDesc.getSpatialDescription().getResource().toURI().toString(), "data:application/vnd.google-earth.kml+xml;base64," +
      "PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4NCjxrbWwgeG1sbnM9Imh0dHA6" +
      "Ly93d3cub3Blbmdpcy5uZXQva21sLzIuMiIgeG1sbnM6Z3g9Imh0dHA6Ly93d3cuZ29vZ2xlLmNv" +
      "bS9rbWwvZXh0LzIuMiIgeG1sbnM6a21sPSJodHRwOi8vd3d3Lm9wZW5naXMubmV0L2ttbC8yLjIi" +
      "IHhtbG5zOmF0b209Imh0dHA6Ly93d3cudzMub3JnLzIwMDUvQXRvbSI+DQo8UGxhY2VtYXJrPg0K" +
      "CTxuYW1lPkx1Z2EsIExlbmluZ3JhZCBPYmxhc3QsIFJ1c3NpYTwvbmFtZT4NCgk8TXVsdGlHZW9t" +
      "ZXRyeT4NCgkJPFBvaW50Pg0KCQk8Y29vcmRpbmF0ZXM+MjkuODQ3OTY2LDU4LjczNTIxMywwPC9j" +
      "b29yZGluYXRlcz4NCgkJPC9Qb2ludD4NCgkJPExpbmVhclJpbmc+DQoJCQk8Y29vcmRpbmF0ZXM+" +
      "DQoJCQkJMjkuODA1NTQzMiw1OC43MDA4MjY2LDAgMjkuODA1NTQzMiw1OC43Njk1OTk0LDAgMjku" +
      "ODkwMzg4OCw1OC43Njk1OTk0LDAgMjkuODkwMzg4OCw1OC43MDA4MjY2LDAgMjkuODA1NTQzMiw1" +
      "OC43MDA4MjY2LDAgDQoJCQk8L2Nvb3JkaW5hdGVzPg0KCQk8L0xpbmVhclJpbmc+DQoJPC9NdWx0" +
      "aUdlb21ldHJ5Pg0KPC9QbGFjZW1hcms+DQo8L2ttbD4NCg==");
    assertEquals(IdentifierType.Primary, lugaDesc.getIdentifiers().get(0).getKnownType());
    assertEquals("https://labs.familysearch.org/stdfinder/PlaceDetail.jsp?placeId=3314013#placeDescriptionId", lugaDesc.getIdentifiers().get(0).getValue().toURI().toString());
    assertEquals("urn:contributorId", lugaDesc.getAttribution().getContributor().getResource().toURI().toString());
    assertEquals(1321027871111L, lugaDesc.getAttribution().getModified().getTime());
    assertEquals("lugaDesc-junkExtensionElement", lugaDesc.findExtensionOfType(String.class));
  }

  @Test
  void placeReferenceLuga() throws Exception {
    PlaceReference lugaRef = new PlaceReference();

    assertNull(lugaRef.getOriginal());
    assertNull(lugaRef.getDescriptionRef());
    assertNull(lugaRef.getExtensionElements());

    lugaRef.setOriginal("Luga, Leningradskaya Oblast', Russia");
    lugaRef.setDescriptionRef(URI.create("#lugaDesc1"));
    lugaRef.addExtensionElement("lugaRef-junkExtensionElement");

    assertEquals("Luga, Leningradskaya Oblast', Russia", lugaRef.getOriginal());
    assertEquals("#lugaDesc1", lugaRef.getDescriptionRef().toURI().toString());
    assertEquals("lugaRef-junkExtensionElement", lugaRef.findExtensionOfType(String.class));
    assertEquals("PlaceReference{original='Luga, Leningradskaya Oblast', Russia', descriptionRef='#lugaDesc1'}", lugaRef.toString());
  }
}
