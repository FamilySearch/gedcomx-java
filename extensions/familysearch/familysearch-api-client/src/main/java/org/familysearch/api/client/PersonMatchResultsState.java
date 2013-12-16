package org.familysearch.api.client;

import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import org.gedcomx.atom.Feed;
import org.gedcomx.rs.client.PersonSearchResultsState;

/**
 * @author Ryan Heaton
 */
public class PersonMatchResultsState extends PersonSearchResultsState {

  protected PersonMatchResultsState(ClientRequest request, ClientResponse response, String accessToken, FamilySearchStateFactory stateFactory) {
    super(request, response, accessToken, stateFactory);
  }

  @Override
  protected PersonMatchResultsState clone(ClientRequest request, ClientResponse response) {
    return new PersonMatchResultsState(request, response, this.accessToken, (FamilySearchStateFactory) this.stateFactory);
  }

  @Override
  public PersonMatchResultsState ifSuccessful() {
    return (PersonMatchResultsState) super.ifSuccessful();
  }

  @Override
  public PersonMatchResultsState head() {
    return (PersonMatchResultsState) super.head();
  }

  @Override
  public PersonMatchResultsState get() {
    return (PersonMatchResultsState) super.get();
  }

  @Override
  public PersonMatchResultsState delete() {
    return (PersonMatchResultsState) super.delete();
  }

  @Override
  public PersonMatchResultsState put(Feed e) {
    return (PersonMatchResultsState) super.put(e);
  }

  @Override
  public PersonMatchResultsState readNextPage() {
    return (PersonMatchResultsState) super.readNextPage();
  }

  @Override
  public PersonMatchResultsState readPreviousPage() {
    return (PersonMatchResultsState) super.readPreviousPage();
  }

  @Override
  public PersonMatchResultsState readFirstPage() {
    return (PersonMatchResultsState) super.readFirstPage();
  }

  @Override
  public PersonMatchResultsState readLastPage() {
    return (PersonMatchResultsState) super.readLastPage();
  }
}
