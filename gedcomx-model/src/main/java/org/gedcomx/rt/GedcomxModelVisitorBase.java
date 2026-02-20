/**
 * Copyright Intellectual Reserve, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gedcomx.rt;

import org.gedcomx.Gedcomx;
import org.gedcomx.agent.Agent;
import org.gedcomx.common.EvidenceReference;
import org.gedcomx.common.Note;
import org.gedcomx.conclusion.*;
import org.gedcomx.records.*;
import org.gedcomx.source.SourceCitation;
import org.gedcomx.source.SourceDescription;
import org.gedcomx.source.SourceReference;

import jakarta.xml.bind.annotation.XmlTransient;
import java.util.LinkedList;
import java.util.List;

/**
 * Basic, no-op implementation of the GEDCOM X model visitor. Intended to be extended and appropriate methods overridden as needed.
 * 
 * @author Ryan Heaton
 */
@XmlTransient
public class GedcomxModelVisitorBase implements GedcomxModelVisitor {

  protected final LinkedList<Object> contextStack = new LinkedList<>();
  
  @Override
  public void visitGedcomx(Gedcomx gx) {
    this.contextStack.push(gx);
    visitComponents(gx);
    this.contextStack.pop();
  }

  protected void visitComponents(Gedcomx gx) {
    List<Person> persons = gx.getPersons();
    if (persons != null) {
      for (Person person : persons) {
        if (person != null) {
          person.accept(this);
        }
      }
    }

    List<Relationship> relationships = gx.getRelationships();
    if (relationships != null) {
      for (Relationship relationship : relationships) {
        if (relationship != null) {
          relationship.accept(this);
        }
      }
    }

    List<SourceDescription> sourceDescriptions = gx.getSourceDescriptions();
    if (sourceDescriptions != null) {
      for (SourceDescription sourceDescription : sourceDescriptions) {
        if (sourceDescription != null) {
          sourceDescription.accept(this);
        }
      }
    }

    List<Agent> agents = gx.getAgents();
    if (agents != null) {
      for (Agent agent : agents) {
        if (agent != null) {
          agent.accept(this);
        }
      }
    }

    List<Event> events = gx.getEvents();
    if (events != null) {
      for (Event event : events) {
        if (event != null) {
          event.accept(this);
        }
      }
    }

    List<PlaceDescription> places = gx.getPlaces();
    if (places != null) {
      for (PlaceDescription place : places) {
        if (place != null) {
          place.accept(this);
        }
      }
    }

    List<Document> documents = gx.getDocuments();
    if (documents != null) {
      for (Document document : documents) {
        if (document != null) {
          document.accept(this);
        }
      }
    }

    List<Field> fields = gx.getFields();
    if (fields != null) {
      for (Field field : fields) {
        if (field != null) {
          field.accept(this);
        }
      }
    }

    List<RecordDescriptor> recordDescriptors = gx.getRecordDescriptors();
    if (recordDescriptors != null) {
      for (RecordDescriptor rd : recordDescriptors) {
        if (rd != null) {
          rd.accept(this);
        }
      }
    }

    List<Collection> collections = gx.getCollections();
    if (collections != null) {
      for (Collection collection : collections) {
        if (collection != null) {
          collection.accept(this);
        }
      }
    }
  }

  @Override
  public void visitDocument(Document document) {
    this.contextStack.push(document);
    visitComponents(document);
    this.contextStack.pop();
  }

  protected void visitComponents(Document document) {
    visitConclusion(document);
  }

  @Override
  public void visitPlaceDescription(PlaceDescription place) {
    this.contextStack.push(place);
    visitComponents(place);
    this.contextStack.pop();
  }

  protected void visitComponents(PlaceDescription place) {
    visitSubject(place);
  }

  @Override
  public void visitEvent(Event event) {
    this.contextStack.push(event);
    visitComponents(event);
    this.contextStack.pop();
  }

  protected void visitComponents(Event event) {
    visitSubject(event);
    Date date = event.getDate();
    if (date != null) {
      date.accept(this);
    }

    PlaceReference place = event.getPlace();
    if (place != null) {
      place.accept(this);
    }

    List<EventRole> roles = event.getRoles();
    if (roles != null) {
      for (EventRole role : roles) {
        if (role != null) {
          role.accept(this);
        }
      }
    }
  }

  @Override
  public void visitEventRole(EventRole role) {
    this.contextStack.push(role);
    visitComponents(role);
    this.contextStack.pop();
  }

  protected void visitComponents(EventRole role) {
    visitConclusion(role);
  }

  @Override
  public void visitAgent(Agent agent) {
    //no-op.
  }

  @Override
  public void visitSourceDescription(SourceDescription sourceDescription) {
    this.contextStack.push(sourceDescription);
    visitComponents(sourceDescription);
    this.contextStack.pop();
  }

  protected void visitComponents(SourceDescription sourceDescription) {
    List<SourceReference> sources = sourceDescription.getSources();
    if (sources != null) {
      for (SourceReference source : sources) {
        if (source != null) {
          source.accept(this);
        }
      }
    }

    List<Note> notes = sourceDescription.getNotes();
    if (notes != null) {
      for (Note note : notes) {
        if (note != null) {
          note.accept(this);
        }
      }
    }

    List<SourceCitation> citations = sourceDescription.getCitations();
    if (citations != null) {
      for (SourceCitation citation : citations) {
        if (citation != null) {
          citation.accept(this);
        }
      }
    }

    List<Field> fields = sourceDescription.getFields();
    if (fields != null) {
      for (Field field : fields) {
        if (field != null) {
          field.accept(this);
        }
      }
    }
  }

  @Override
  public void visitSourceCitation(SourceCitation citation) {
    //no-op.
  }

  @Override
  public void visitCollection(Collection collection) {
  }

  @Override
  public void visitRecordDescriptor(RecordDescriptor recordDescriptor) {
    //no-op.
  }

  @Override
  public void visitField(Field field) {
    this.contextStack.push(field);
    visitComponents(field);
    this.contextStack.pop();
  }

  protected void visitComponents(Field field) {
    visitConclusion(field);

    List<FieldValue> values = field.getValues();
    if (values != null) {
      for (FieldValue value : values) {
        if (value != null) {
          value.accept(this);
        }
      }
    }
  }

  @Override
  public void visitFieldValue(FieldValue fieldValue) {
    this.contextStack.push(fieldValue);
    visitComponents(fieldValue);
    this.contextStack.pop();
  }

  protected void visitComponents(FieldValue fieldValue) {
    visitConclusion(fieldValue);
  }

  @Override
  public void visitRelationship(Relationship relationship) {
    this.contextStack.push(relationship);
    visitComponents(relationship);
    this.contextStack.pop();
  }

  protected void visitComponents(Relationship relationship) {
    visitSubject(relationship);

    List<Fact> facts = relationship.getFacts();
    if (facts != null) {
      for (Fact fact : facts) {
        if (fact != null) {
          fact.accept(this);
        }
      }
    }

    List<Field> fields = relationship.getFields();
    if (fields != null) {
      for (Field field : fields) {
        if (field != null) {
          field.accept(this);
        }
      }
    }
  }

  protected void visitConclusion(Conclusion conclusion) {
    List<SourceReference> sourceReferences = conclusion.getSources();
    if (sourceReferences != null) {
      for (SourceReference sourceReference : sourceReferences) {
        if (sourceReference != null) {
          sourceReference.accept(this);
        }
      }
    }

    List<Note> notes = conclusion.getNotes();
    if (notes != null) {
      for (Note note : notes) {
        if (note != null) {
          note.accept(this);
        }
      }
    }
  }

  protected void visitSubject(Subject subject) {
    visitConclusion(subject);

    List<SourceReference> media = subject.getMedia();
    if (media != null) {
      for (SourceReference reference : media) {
        if (reference != null) {
          reference.accept(this);
        }
      }
    }

    List<EvidenceReference> evidence = subject.getEvidence();
    if (evidence != null) {
      for (EvidenceReference evidenceReference : evidence) {
        if (evidenceReference != null) {
          evidenceReference.accept(this);
        }
      }
    }
  }

  @Override
  public void visitPerson(Person person) {
    this.contextStack.push(person);
    visitComponents(person);
    this.contextStack.pop();
  }

  protected void visitComponents(Person person) {
    visitSubject(person);

    if (person.getGender() != null) {
      person.getGender().accept(this);
    }

    List<Name> names = person.getNames();
    if (names != null) {
      for (Name name : names) {
        if (name != null) {
          name.accept(this);
        }
      }
    }

    List<Fact> facts = person.getFacts();
    if (facts != null) {
      for (Fact fact : facts) {
        if (fact != null) {
          fact.accept(this);
        }
      }
    }

    List<Field> fields = person.getFields();
    if (fields != null) {
      for (Field field : fields) {
        if (field != null) {
          field.accept(this);
        }
      }
    }
  }

  @Override
  public void visitFact(Fact fact) {
    this.contextStack.push(fact);
    visitComponents(fact);
    this.contextStack.pop();
  }

  protected void visitComponents(Fact fact) {
    visitConclusion(fact);
    Date date = fact.getDate();
    if (date != null) {
      date.accept(this);
    }

    PlaceReference place = fact.getPlace();
    if (place != null) {
      place.accept(this);
    }

    List<Field> fields = fact.getFields();
    if (fields != null) {
      for (Field field : fields) {
        if (field != null) {
          field.accept(this);
        }
      }
    }
  }

  @Override
  public void visitPlaceReference(PlaceReference place) {
    this.contextStack.push(place);
    visitComponents(place);
    this.contextStack.pop();
  }

  protected void visitComponents(PlaceReference place) {
    List<Field> fields = place.getFields();
    if (fields != null) {
      for (Field field : fields) {
        if (field != null) {
          field.accept(this);
        }
      }
    }
  }

  @Override
  public void visitDate(Date date) {
    this.contextStack.push(date);
    visitComponents(date);
    this.contextStack.pop();
  }

  protected void visitComponents(Date date) {
    List<AlternateCalendarDate> alternateCalendars = date.getAlternateCalendars();
    if (alternateCalendars != null) {
      for (AlternateCalendarDate alternateCalendar : alternateCalendars) {
        if (alternateCalendar != null) {
          alternateCalendar.accept(this);
        }
      }
    }

    List<Field> fields = date.getFields();
    if (fields != null) {
      for (Field field : fields) {
        if (field != null) {
          field.accept(this);
        }
      }
    }
  }

  @Override
  public void visitName(Name name) {
    this.contextStack.push(name);
    visitComponents(name);
    this.contextStack.pop();
  }

  protected void visitComponents(Name name) {
    visitConclusion(name);

    List<NameForm> forms = name.getNameForms();
    if (forms != null) {
      for (NameForm form : forms) {
        if (form != null) {
          form.accept(this);
        }
      }
    }
  }

  @Override
  public void visitNameForm(NameForm form) {
    this.contextStack.push(form);
    visitComponents(form);
    this.contextStack.pop();
  }

  protected void visitComponents(NameForm form) {
    List<NamePart> parts = form.getParts();
    if (parts != null) {
      for (NamePart part : parts) {
        if (part != null) {
          part.accept(this);
        }
      }
    }

    List<Field> fields = form.getFields();
    if (fields != null) {
      for (Field field : fields) {
        if (field != null) {
          field.accept(this);
        }
      }
    }
  }

  @Override
  public void visitNamePart(NamePart part) {
    this.contextStack.push(part);
    visitComponents(part);
    this.contextStack.pop();
  }

  protected void visitComponents(NamePart part) {
    List<Field> fields = part.getFields();
    if (fields != null) {
      for (Field field : fields) {
        if (field != null) {
          field.accept(this);
        }
      }
    }
  }

  @Override
  public void visitGender(Gender gender) {
    this.contextStack.push(gender);
    visitComponents(gender);
    this.contextStack.pop();
  }

  protected void visitComponents(Gender gender) {
    visitConclusion(gender);

    List<Field> fields = gender.getFields();
    if (fields != null) {
      for (Field field : fields) {
        if (field != null) {
          field.accept(this);
        }
      }
    }
  }

  @Override
  public void visitSourceReference(SourceReference sourceReference) {
    //no-op
  }

  @Override
  public void visitNote(Note note) {
    //no-op.
  }

  @Override
  public void visitEvidenceReference(EvidenceReference evidenceReference) {
    //no-op
  }

  public LinkedList<Object> getContextStack() {
    return contextStack;
  }

}
