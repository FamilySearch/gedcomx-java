package org.familysearch.api.client;

import org.familysearch.api.client.ft.ChildAndParentsRelationshipState;
import org.familysearch.api.client.ft.FamilySearchFamilyTree;
import org.familysearch.api.client.ft.FamilyTreePersonState;
import org.familysearch.api.client.util.ChangeEntry;
import org.familysearch.platform.ct.DiscussionReference;
import org.familysearch.platform.discussions.Discussion;
import org.gedcomx.atom.Entry;
import org.gedcomx.common.EvidenceReference;
import org.gedcomx.common.Note;
import org.gedcomx.conclusion.*;
import org.gedcomx.rs.client.*;
import org.gedcomx.rs.client.util.PersonSearchQueryBuilder;
import org.gedcomx.source.SourceDescription;
import org.gedcomx.source.SourceReference;
import org.gedcomx.types.FactType;
import org.gedcomx.types.GenderType;
import org.gedcomx.types.NamePartType;
import org.gedcomx.types.NameType;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import java.util.List;

import static org.familysearch.api.client.util.FamilySearchOptions.reason;
import static org.gedcomx.rs.client.options.QueryParameter.generations;

/**
 * @author Ryan Heaton
 */
public class Examples {

  public void readFamilySearchFamilyTree() {
    boolean useSandbox = true; //whether to use the sandbox reference.
    String username = "...";
    String password = "...";
    String developerKey = "...";

    //read the Family Tree
    FamilySearchFamilyTree ft = new FamilySearchFamilyTree(useSandbox)
      //and authenticate.
      .authenticateViaOAuth2Password(username, password, developerKey);
  }

  public void searchForPersonsOrPersonMatchesInFamilyTree() {
    FamilySearchFamilyTree ft = null;

    //put together a search query
    PersonSearchQueryBuilder query = new PersonSearchQueryBuilder()
      //for a John Smith
      .name("John Smith")
      //born 1/1/1900
      .birthDate("1 January 1900")
        //son of Peter.
      .fatherName("Peter Smith");

    //search the collection
    PersonSearchResultsState results = ft.searchForPersons(query);
    //iterate through the results...
    List<Entry> entries = results.getResults().getEntries();
    //read the person that was hit
    PersonState person = results.readPerson(entries.get(0));

    //search the collection for matches
    PersonMatchResultsState matches = ft.searchForPersonMatches(query);
    //iterate through the results...
    entries = results.getResults().getEntries();
    //read the person that was matched
    person = results.readPerson(entries.get(0));
  }

  public void createAPersonInFamilyTree() {
    FamilySearchFamilyTree ft = null;

    //add a person
    PersonState person = ft.addPerson(new Person()
      //named John Smith
      .name(new Name("John Smith", new NamePart(NamePartType.Given, "John"), new NamePart(NamePartType.Surname, "Smith")))
      //male
      .gender(GenderType.Male)
      //born in chicago in 1920
      .fact(new Fact(FactType.Birth, "1 January 1920", "Chicago, Illinois"))
      //died in new york 1980
      .fact(new Fact(FactType.Death, "1 January 1980", "New York, New York")),
      //with a change message.
      reason("Because I said so.")
    );
  }

  public void createCoupleRelationshipInFamilyTree() {
    FamilySearchFamilyTree ft = null;

    PersonState husband = null;
    PersonState wife = null;

    RelationshipState coupleRelationship = ft.addSpouseRelationship(husband, wife, reason("Because I said so."));
  }

  public void createChildAndParentsRelationshipInFamilyTree() {
    FamilySearchFamilyTree ft = null;

    PersonState father = null;
    PersonState mother = null;
    PersonState child = null;

    ChildAndParentsRelationshipState chap = ft.addChildAndParentsRelationship(child, father, mother, reason("Because I said so."));
  }

  public void createASource() {
    FamilySearchFamilyTree ft = null;

    //add a source description
    SourceDescriptionState source = ft.addSourceDescription(new SourceDescription()
      //about some resource.
      .about(org.gedcomx.common.URI.create("http://familysearch.org/ark:/..."))
      //with a title.
      .title("Birth Certificate for John Smith")
      //and a citation
      .citation("Citation for the birth certificate")
      //and a note
      .note(new Note().text("Some note for the source.")),
      //with a change message.
      reason("Because I said so.")
    );
  }

  public void createASourceReference() {
    //the person that will be citing the record, source, or artifact.
    PersonState person = null;

    SourceDescriptionState source = null;

    person.addSourceReference(source, reason("Because I said so.")); //cite the source.
  }

  public void readEverythingAttachedToASource() {
    //the source.
    SourceDescriptionState source = null;

    SourceDescriptionState attachedReferences = ((FamilySearchSourceDescriptionState) source).queryAttachedReferences();

    //iterate through the persons attached to the source
    List<Person> persons = attachedReferences.getEntity().getPersons();
  }

  public void readPersonForCurrentUser() {
    FamilySearchFamilyTree ft = null;

    PersonState person = ft.readPersonForCurrentUser();
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

  public void readDiscussionReferences() {
    //the person on which to read the discussion references.
    PersonState person = null;

    //load the discussion references for the person.
    ((FamilyTreePersonState) person).loadDiscussionReferences();

    //read the discussion references.
    List<DiscussionReference> discussionRefs = person.getPerson().findExtensionsOfType(DiscussionReference.class);
  }

  public void readNotes() {
    //the person on which to read the notes.
    PersonState person = null;

    //load the notes for the person.
    person.loadNotes();

    //read the discussion references.
    List<Note> notes = person.getPerson().getNotes();
  }

  public void readParentsOrChildrenOrSpouses() {
    //the person for which to read the parents, spouses, children
    PersonState person = null;

    PersonChildrenState children = person.readChildren(); //read the children
    PersonParentsState parents = person.readParents(); //read the parents
    PersonSpousesState spouses = person.readSpouses(); //read the spouses
  }

  public void readAncestryOrDescendancy() {
    //the person for which to read the ancestry or descendancy
    PersonState person = null;

    person.readAncestry(); //read the ancestry
    person.readAncestry(generations(8)); //read 8 generations of the ancestry
    person.readDescendancy(); //read the descendancy
    person.readDescendancy(generations(3)); //read 3 generations of the descendancy
  }

  public void readPersonMatches() {
    //the person for which to read the matches
    PersonState person = null;

    PersonMatchResultsState matches = ((FamilyTreePersonState) person).readMatches();

    //iterate through the matches.
    List<Entry> entries = matches.getResults().getEntries();
  }

  public void setNotAMatch() {
    //the match results
    PersonMatchResultsState matches = null;

    //iterate through the matches.
    List<Entry> entries = matches.getResults().getEntries();

    matches.addNonMatch(entries.get(2), reason("Because I said so."));
  }

  public void addNameOrFact() {
    //the person to which to add the name, gender, or fact.
    PersonState person = null;

    Name name = null;
    person.addName(name.type(NameType.AlsoKnownAs), reason("Because I said so.")); //add name
    person.addFact(new Fact(FactType.Death, "date", "place"), reason("Because I said so.")); //add death fact
  }

  public void updateNameOrGenderOrFact() {
    //the person to which to update the name, gender, or fact.
    PersonState person = null;

    Name name = person.getName();
    name.getNameForm().setFullText("Joanna Smith");
    person.updateName(name, reason("Because I said so.")); //update name

    Gender gender = person.getGender();
    gender.setKnownType(GenderType.Female);
    person.updateGender(gender, reason("Because I said so.")); //update gender

    Fact death = person.getPerson().getFirstFactOfType(FactType.Death);
    death.setDate(new Date().original("new date"));
    person.updateFact(death, reason("Because I said so."));
  }

  public void readChangeHistoryAndRestoreAChange() {
    //the person to which to read the change history.
    PersonState person = null;

    ChangeHistoryState changeHistory = ((FamilyTreePersonState) person).readChangeHistory();

    //iterate through each entry.
    List<ChangeEntry> entries = changeHistory.getPage().getEntries();

    //restore a change
    changeHistory.restoreChange(entries.get(0), reason("Because I said so."));
  }

  public void createADiscussion() {
    FamilySearchFamilyTree ft = null;

    //add a discussion description
    DiscussionState discussion = ft.addDiscussion(new Discussion()
      //with a title.
      .title("What about this"),
      //with a change message.
      reason("Because I said so.")
    );
  }

  public void createADiscussionReference() {
    //the person that will be referencing the discussion.
    PersonState person = null;

    DiscussionState discussion = null;

    ((FamilyTreePersonState) person).addDiscussionReference(discussion, reason("Because I said so.")); //reference the discussion.
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

  public void readFamilySearchMemories() {
    boolean useSandbox = true; //whether to use the sandbox reference.
    String username = "...";
    String password = "...";
    String developerKey = "...";

    //read the Family Tree
    FamilySearchMemories fsMemories = new FamilySearchMemories(useSandbox)
      //and authenticate.
      .authenticateViaOAuth2Password(username, password, developerKey);
  }

  public void addAnArtifact() {
    FamilySearchMemories fsMemories = null;
    DataSource digitalImage = new FileDataSource("/path/to/img.jpg");

    //add an artifact
    SourceDescriptionState artifact = fsMemories.addArtifact(new SourceDescription()
      //with a title
      .title("Death Certificate for John Smith")
      //and a citation
      .citation("Citation for the death certificate"),
      digitalImage
    );
  }

  public void extractAPersonaFromAnArtifact() {
    //the artifact from which a persona will be extracted.
    SourceDescriptionState artifact = null;

    //add the persona
    PersonState persona = artifact.addPersona(new Person()
      //named John Smith
      .name("John Smith"));
  }

  public void addAPersonaReferenceToAPerson() {
    //the person that will be citing the record, source, or artifact.
    PersonState person = null;

    //the persona that was extracted from a record or artifact.
    PersonState persona = null;

    //add the persona reference.
    person.addPersonaReference(persona);
  }

  public void attachAPhotoToMultiplePersons() {
    //the collection to which the artifact is to be added
    CollectionState fsMemories = null;

    //the persons to which the photo will be attached.
    PersonState person1 = null;
    PersonState person2 = null;
    PersonState person3 = null;
    DataSource digitalImage = new FileDataSource("/path/to/img.jpg");

    //add an artifact
    SourceDescriptionState artifact = fsMemories.addArtifact(new SourceDescription()
      //with a title
      .title("Family of John Smith"),
      digitalImage
    );

    person1.addMediaReference(artifact); //attach to person1
    person2.addMediaReference(artifact); //attach to person2
    person3.addMediaReference(artifact); //attach to person3
  }

  public void readFamilySearchPlaces() {
    boolean useSandbox = true; //whether to use the sandbox reference.
    String username = "...";
    String password = "...";
    String developerKey = "...";

    //read the Family Tree
    FamilySearchPlaces fsPlaces = new FamilySearchPlaces(useSandbox)
            //and authenticate.
            .authenticateViaOAuth2Password(username, password, developerKey);
  }

}
