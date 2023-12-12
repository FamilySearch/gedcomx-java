package org.gedcomx.source;

import org.gedcomx.common.*;
import org.gedcomx.conclusion.Identifier;
import org.gedcomx.conclusion.PlaceDescription;
import org.gedcomx.conclusion.PlaceDisplayProperties;
import org.gedcomx.conclusion.PlaceReference;
import org.gedcomx.date.GedcomxDateSimple;
import org.gedcomx.records.Field;
import org.gedcomx.records.FieldValue;
import org.gedcomx.rt.SerializationUtil;
import org.gedcomx.types.ConfidenceLevel;
import org.gedcomx.types.IdentifierType;
import org.gedcomx.types.ResourceStatusType;
import org.junit.jupiter.api.Test;

import jakarta.xml.bind.JAXBException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * User: Randy Wilson
 */
public class SourceDescriptionTest {

  private static final String TEST_STRING = "test";
  private static final TextValue TEST_TEXT_VALUE = new TextValue(TEST_STRING).lang(TEST_STRING);
  private static final URI TEST_URI = URI.create("http://test.test/");
  private static final ResourceReference TEST_RESOURCE_REFERENCE = new ResourceReference(TEST_URI);
  private static final Attribution TEST_ATTRIBUTION = new Attribution()
    .contributor(TEST_RESOURCE_REFERENCE)
    .creator(TEST_RESOURCE_REFERENCE)
    .modified(new Date())
    .created(new Date())
    .changeMessage(TEST_STRING)
    .changeMessageResource(TEST_URI);
  private static final Qualifier TEST_QUALIFIER = new Qualifier(TEST_URI, TEST_STRING);
  private static final SourceReference TEST_SOURCE_REFERENCE = new SourceReference()
    .descriptionRef(TEST_URI)
    .descriptionId(TEST_STRING)
    .attribution(TEST_ATTRIBUTION)
    .qualifier(TEST_QUALIFIER);
  private static final Field TEST_FIELD = new Field()
    .type(TEST_URI)
    .value(new FieldValue(TEST_STRING));

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
  public void testPersistentIdHelpers() throws Exception {
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

  @Test
  public void equals() {
    final Date date = new Date();
    final SourceDescription sd1 = buildFullSourceDescription(date);
    final SourceDescription sd2 = buildFullSourceDescription(date);
    assertThat(sd1).isEqualTo(sd2);
  }

  private SourceDescription buildFullSourceDescription(Date date) {
    final org.gedcomx.conclusion.Date testDate = new org.gedcomx.conclusion.Date()
      .confidence(ConfidenceLevel.High)
      .field(new Field())
      .formal(new GedcomxDateSimple("+1970"))
      .original(TEST_STRING);
    testDate.setNormalizedExtensions(List.of(TEST_TEXT_VALUE));

    final SourceDescription sd = new SourceDescription()
      .lang(TEST_STRING)
      .citation(TEST_STRING)
      .mediaType(TEST_STRING)
      .about(TEST_URI)
      .mediator(TEST_RESOURCE_REFERENCE)
      .publisher(TEST_RESOURCE_REFERENCE)
      .authors(TEST_URI)
      .source(TEST_SOURCE_REFERENCE)
      .analysis(TEST_RESOURCE_REFERENCE)
      .componentOf(TEST_SOURCE_REFERENCE)
      .title(TEST_TEXT_VALUE)
      .titleLabel(TEST_TEXT_VALUE)
      .note(new Note()
              .id(TEST_STRING)
              .lang(TEST_STRING)
              .subject(TEST_STRING)
              .text(TEST_STRING)
              .attribution(TEST_ATTRIBUTION))
      .attribution(TEST_ATTRIBUTION)
      .resourceType(TEST_URI)
      .rights(TEST_URI)
      .sortKey(TEST_STRING)
      .description(TEST_TEXT_VALUE)
      .identifier(new Identifier(TEST_URI, IdentifierType.Primary))
      .created(date)
      .modified(date)
      .coverage(new Coverage()
                  .spatial(new PlaceReference()
                             .original(TEST_STRING)
                             .description(new PlaceDescription()
                                            .name(TEST_TEXT_VALUE)
                                            .type(TEST_URI)
                                            .temporalDescription(testDate)
                                            .latitude(35.0)
                                            .longitude(-35.0)
                                            .place(TEST_RESOURCE_REFERENCE)
                                            .spatialDescription(TEST_RESOURCE_REFERENCE)
                                            .jurisdiction(TEST_RESOURCE_REFERENCE)
                                            .displayExtension(new PlaceDisplayProperties()
                                                                .name(TEST_STRING)
                                                                .fullName(TEST_STRING)
                                                                .type(TEST_STRING))))
                  .temporal(testDate)
                  .recordType(TEST_URI))
      .field(TEST_FIELD)
      .repository(TEST_RESOURCE_REFERENCE)
      .descriptorRef(TEST_RESOURCE_REFERENCE)
      .version(TEST_STRING);
    sd.setReplaces(List.of(TEST_URI));
    sd.setReplacedBy(TEST_URI);
    return sd;
  }
}
