package org.gedcomx.conclusion;

import org.gedcomx.common.*;
import org.gedcomx.source.SourceReference;
import org.gedcomx.types.DocumentType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class DocumentTest {
  @Test
  void document() throws Exception {
    Document document = new Document();
    document.setKnownType(DocumentType.Abstract);

    assertNull(document.getId());
    assertNull(document.getText());
    assertNull(document.getSources());
    assertNull(document.getAttribution());
    assertNull(document.getNotes());
    assertNull(document.getExtensionElements());

    document.setId("DDDD-DDD");
    document.setText("(The text of the document abstract goes here.)");
    document.setLang("en-US");
    document.addSource(new SourceReference());
    document.getSources().get(0).setDescriptionRef(URI.create("urn:original-source1"));
    document.addSource(new SourceReference());
    document.getSources().get(1).setDescriptionRef(URI.create("urn:original-source2"));
    document.setAttribution(new Attribution());
    document.getAttribution().setContributor(new ResourceReference(URI.create("urn:contributor-id")));
    document.addNote(new Note());
    document.getNotes().get(0).setText("(This is an example note #1, though not one to be emulated too closely.)");
    document.addNote(new Note());
    document.getNotes().get(1).setText("(This is an example note #2, though not one to be emulated too closely.)");
    document.addExtensionElement(new CustomEntity("1234"));
    document.addExtensionElement(new CustomEntity("2345"));

    assertEquals(DocumentType.Abstract, document.getKnownType());
    assertEquals("DDDD-DDD", document.getId());
    assertEquals("en-US", document.getLang());
    assertEquals("(The text of the document abstract goes here.)", document.getText());
    assertEquals(2, document.getSources().size());
    assertEquals("urn:original-source1", document.getSources().get(0).getDescriptionRef().toURI().toString());
    assertEquals("urn:original-source2", document.getSources().get(1).getDescriptionRef().toURI().toString());
    assertEquals(2, document.getNotes().size());
    assertEquals("(This is an example note #1, though not one to be emulated too closely.)", document.getNotes().get(0).getText());
    assertEquals("(This is an example note #2, though not one to be emulated too closely.)", document.getNotes().get(1).getText());
    assertNotNull(document.getExtensionElements());
    assertEquals(2, document.getExtensionElements().size());
    assertEquals("1234", ((CustomEntity) document.getExtensionElements().get(0)).getId());
    assertEquals("2345", ((CustomEntity) document.getExtensionElements().get(1)).getId());
    assertNull(document.findExtensionOfType(String.class));
    assertEquals("1234", document.findExtensionOfType(CustomEntity.class).getId());
    assertEquals(0, document.findExtensionsOfType(String.class).size());
    assertEquals(2, document.findExtensionsOfType(CustomEntity.class).size());
    assertEquals("2345", document.findExtensionsOfType(CustomEntity.class).get(1).getId());

    document.setSources(null);
    document.addSource(null);
    document.setNotes(null);
    document.addNote(null);
    document.setExtensionElements(null);
    assertNull(document.getSources());
    assertNull(document.getNotes());
    assertNull(document.findExtensionOfType(CustomEntity.class));
    assertEquals(0, document.findExtensionsOfType(CustomEntity.class).size());
  }

}
