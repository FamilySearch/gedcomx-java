package org.gedcomx.rs.client;

import org.gedcomx.atom.Entry;
import org.gedcomx.common.EvidenceReference;
import org.gedcomx.common.Note;
import org.gedcomx.conclusion.*;
import org.gedcomx.rt.util.PersonSearchQueryBuilder;
import org.gedcomx.source.SourceDescription;
import org.gedcomx.source.SourceReference;
import org.gedcomx.types.FactType;
import org.gedcomx.types.GenderType;

import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import java.net.URI;
import java.util.List;

import static org.gedcomx.rs.client.options.QueryParameter.generations;

/**
 * @author Ryan Heaton
 */
public class Examples {

  public void readCollection() {
    //start with the URI to the collection.
    URI collectionUri = URI.create("...");

    //read the collection.
    CollectionState collection = new CollectionState(collectionUri);

    //authenticate if you need to.
    String username = "...";
    String password = "...";
    String clientId = "...";
    collection.authenticateViaOAuth2Password(username, password, clientId);
  }

  public void searchForRecordsOfPersons() {
    //the collection to search.
    CollectionState collection = null;

    //put together a search query
    PersonSearchQueryBuilder query = new PersonSearchQueryBuilder()
      //for a John Smith
      .name("John Smith")
      //born 1/1/1900
      .birthDate("1 January 1900")
        //son of Peter.
      .fatherName("Peter Smith");

    //search the collection
    PersonSearchResultsState results = collection.searchForPersons(query);

    //iterate through the results...
    List<Entry> entries = results.getResults().getEntries();

    //read the record of one of the results.
    RecordState record = results.readRecord(entries.get(0));

    //or, read the person that was considered a "hit"
    PersonState person = results.readPerson(entries.get(0));
  }

  public void createAPerson() {
    //the collection to which the person is to be added
    CollectionState collection = null;

    //add a person
    PersonState person = collection.addPerson(new Person()
      //named John Smith
      .name("John Smith")
      //male
      .gender(GenderType.Male)
      //residing in chicago in 1940
      .fact(new Fact(FactType.Residence, "4 April 1940", "Chicago, Illinois")));
  }

  public void createRelationships() {
    //the collection to which the relationship is to be added.
    CollectionState collection = null;

    PersonState spouse1 = null;
    PersonState spouse2 = null;
    PersonState child = null;

    RelationshipState coupleRelationship = collection.addSpouseRelationship(spouse1, spouse2);
    RelationshipState childRelationship1 = collection.addParentChildRelationship(spouse1, child);
    RelationshipState childRelationship2 = collection.addParentChildRelationship(spouse2, child);
  }
  
  public void createASourceDescription() {
    //the collection to which the source is to be added
    CollectionState collection = null;
    
    //add a source description
    SourceDescriptionState source = collection.addSourceDescription(new SourceDescription()
      //with a title.
      .title("Birth Certificate for John Smith")
      //and a citation
      .citation("Citation for the birth certificate")
    );
  }

  public void addAnArtifact() {
    //the collection to which the artifact is to be added
    CollectionState collection = null;
    DataSource digitalImage = new FileDataSource("/path/to/img.jpg");
    
    //add an artifact
    SourceDescriptionState artifact = collection.addArtifact(new SourceDescription()
      //with a title
      .title("Death Certificate for John Smith")
      //and a citation
      .citation("Citation for the death certificate"), 
      digitalImage
    );
  }

  public void citeARecordOrSourceOrArtifact() {
    //the person that will be citing the record, source, or artifact.
    PersonState person = null;

    RecordState record = null;
    SourceDescriptionState source = null;
    SourceDescriptionState artifact = null;
    
    person.addSourceReference(record); //cite the record.
    person.addSourceReference(source); //cite the source.
    person.addSourceReference(artifact); //cite the artifact.
  }
  
  public void extractAPersonaFromAnArtifact() {
    //the artifact from which a persona will be extracted.
    SourceDescriptionState artifact = null;
    
    //add the persona
    PersonState persona = artifact.addPersona(new Person()
      //named John Smith
      .name("John Smith")
      //male
      .gender(GenderType.Male)
      //residing in chicago in 1940
      .fact(new Fact(FactType.Residence, "4 April 1940", "Chicago, Illinois")));
  }
  
  public void addAPersonaReferenceToAPerson() {
    //the person that will be citing the record, source, or artifact.
    PersonState person = null;

    //the persona that was extracted from a record or artifact.
    PersonState persona = null;
    
    //add the persona reference.
    person.addPersonaReference(persona);
  }
  
  public void attachAPhotoToAPerson() {
    //the person to which the photo will be attached.
    PersonState person = null;
    DataSource digitalImage = new FileDataSource("/path/to/img.jpg");

    //add an artifact
    SourceDescriptionState artifact = person.addArtifact(new SourceDescription()
      //with a title
      .title("Portrait of John Smith"),
      digitalImage
    );
  }
  
  public void attachAPhotoToMultiplePersons() {
    //the collection to which the artifact is to be added
    CollectionState collection = null;

    //the persons to which the photo will be attached.
    PersonState person1 = null;
    PersonState person2 = null;
    PersonState person3 = null;
    DataSource digitalImage = new FileDataSource("/path/to/img.jpg");

    //add an artifact
    SourceDescriptionState artifact = collection.addArtifact(new SourceDescription()
      //with a title
      .title("Family of John Smith"),
      digitalImage
    );

    person1.addMediaReference(artifact); //attach to person1
    person2.addMediaReference(artifact); //attach to person2
    person3.addMediaReference(artifact); //attach to person3
  }
  
  public void readPersonForCurrentUser() {
    //the collection containing the person for the current user.
    CollectionState collection = null;

    PersonState person = collection.readPersonForCurrentUser();
  }
  
  public void readParentsOrChildrenOrSpouses() {
    //the person for which to read the parents, spouses, children
    PersonState person = null;

    PersonChildrenState children = person.readChildren(); //read the children
    PersonParentsState parents = person.readParents(); //read the parents
    PersonSpousesState spouses = person.readSpouses(); //read the spouses
  }

  public void readSourceReferences() {
    //the person on which to read the source references.
    PersonState person = null;

    //load the source references for the person.
    person.loadSourceReferences();

    //read the source references.
    List<SourceReference> sourceRefs = person.getPerson().getSources();
  }

  public void readEvidenceReferences() {
    //the person on which to read the persona references.
    PersonState person = null;

    //load the persona references for the person.
    person.loadPersonaReferences();

    //read the persona references.
    List<EvidenceReference> personaRefs = person.getPerson().getEvidence();
  }

  public void readNotes() {
    //the person on which to read the notes.
    PersonState person = null;

    //load the notes for the person.
    person.loadNotes();

    //read the discussion references.
    List<Note> notes = person.getPerson().getNotes();
  }

  public void readAncestryOrDescendancy() {
    //the person for which to read the ancestry or descendancy
    PersonState person = null;

    person.readAncestry(); //read the ancestry
    person.readAncestry(generations(8)); //read 8 generations of the ancestry
    person.readDescendancy(); //read the descendancy
    person.readDescendancy(generations(3)); //read 3 generations of the descendancy
  }
  
  public void addNameOrGenderOrFact() {
    //the person to which to add the name, gender, or fact.
    PersonState person = null;

    person.addName(new Name("Johnny Smith")); //add name
    person.addGender(new Gender(GenderType.Male)); //add gender
    person.addFact(new Fact(FactType.Death, "date", "place")); //add death fact
  }
  
  public void updateNameOrGenderOrFact() {
    //the person to which to update the name, gender, or fact.
    PersonState person = null;

    Name name = person.getName();
    name.getNameForm().setFullText("Joanna Smith");
    person.updateName(name); //update name

    Gender gender = person.getGender();
    gender.setKnownType(GenderType.Female);
    person.updateGender(gender); //update gender

    Fact death = person.getPerson().getFirstFactOfType(FactType.Death);
    death.setDate(new Date().original("new date"));
    person.updateFact(death);
  }

}
