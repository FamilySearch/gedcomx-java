package org.gedcomx.source;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import javax.xml.bind.JAXBException;

import org.gedcomx.common.URI;
import org.gedcomx.rt.SerializationUtil;
import org.gedcomx.types.ResourceStatusType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * User: Randy Wilson
 */
public class SourceDescriptionTest {


  @Test
  public void testXml() throws JAXBException, UnsupportedEncodingException {
    SourceDescription sd = new SourceDescription();
    sd.setAbout(new URI("https://company.com/resource/id"));
    sd.addKnownStatus(ResourceStatusType.Deprecated);
    sd.addStatus(new URI("http://company.com/custom/resource/status/Forged"));
    sd.setReplacedBy(new URI("http://company.com/updated/id"));
    sd.setReplaces(Arrays.asList(new URI("http://company.com/old/id"), new URI("http://company.com/old/id2")));
    sd.setVersion("1");

    sd = SerializationUtil.processThroughXml(sd);

    assertThat(sd.getAbout().toString()).isEqualTo("https://company.com/resource/id");
    assertThat(ResourceStatusType.fromQNameURI(sd.getStatuses().get(0))).isEqualTo(ResourceStatusType.Deprecated);
    assertThat(sd.getStatuses().get(1).toString()).isEqualTo("http://company.com/custom/resource/status/Forged");
    assertThat(sd.getReplacedBy().toString()).isEqualTo("http://company.com/updated/id");
    assertThat(sd.getReplaces().get(0).toString()).isEqualTo("http://company.com/old/id");
    assertThat(sd.getReplaces().get(1).toString()).isEqualTo("http://company.com/old/id2");
    assertThat(sd.getVersion()).isEqualTo("1");
    assertThat(sd.version("2").getVersion()).isEqualTo("2");
  }

  @Test
  public void testsdPersistentIdHelpers() throws Exception {
    final SourceDescription sd = new SourceDescription();

    sd.setPersistentId(URI.create("urn:pal"));
    assertThat(sd.getPersistentId().toURI().toString()).isEqualTo("urn:pal");

    sd.getIdentifiers().clear();
    assertThat(sd.getPersistentId()).isNull();

    sd.setIdentifiers(null);
    assertThat(sd.getPersistentId()).isNull();

    sd.setPersistentId(URI.create("urn:pal"));
    assertThat(sd.getPersistentId().toURI().toString()).isEqualTo("urn:pal");

    final SourceDescription ref = sd.persistentId(URI.create("urn:pal"));
    assertThat(sd.getPersistentId().toURI().toString()).isEqualTo("urn:pal");
    assertThat(ref).isSameAs(sd);
  }

}
