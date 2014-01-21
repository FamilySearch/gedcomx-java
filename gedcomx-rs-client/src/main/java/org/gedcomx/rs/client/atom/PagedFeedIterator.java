package org.gedcomx.rs.client.atom;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.gedcomx.atom.Feed;
import org.gedcomx.common.URI;
import org.gedcomx.links.Link;

import javax.ws.rs.core.MediaType;
import java.util.*;

/**
 * A paged {@link org.gedcomx.atom.Feed feed} iterator.
 * <p/>
 * The HTTP GET requests can be customized by using a custom {@link WebResourceProvider} and/or adding {@link
 * WebResourceBuilderExtension}s.
 *
 * @see <a href="http://tools.ietf.org/search/rfc5005#section-3">Paged Feeds</a>
 */
public class PagedFeedIterator implements Iterator<Feed> {
  /**
   * The default {@link WebResourceProvider} which simply calls {@link com.sun.jersey.api.client.Client#resource(String)}.
   */
  public static WebResourceProvider DEFAULT_WEB_RESOURCE_PROVIDER = new WebResourceProvider() {
    @Override
    public WebResource provide(Client client, URI uri) {
      return client.resource(uri.toURI());
    }
  };
  private final List<WebResourceBuilderExtension> extensions = new ArrayList<WebResourceBuilderExtension>();
  private Client client;
  private WebResourceProvider webResourceProvider = DEFAULT_WEB_RESOURCE_PROVIDER;
  private URI first = null;
  private URI last = null;
  private URI previous = null;
  private URI next = null;

  private PagedFeedIterator(URI uri) {
    next = uri;
  }

  private PagedFeedIterator(Feed feed) {
    loadHRefsFromFeed(feed);
  }

  /**
   * Creates a new paged feed iterator using the specified URI.
   *
   * @param uri the {@link org.gedcomx.common.URI} to use to get the initial (next) paged feed document and from which
   *            future values of first, last, previous, and next hypertext references will be acquired.
   * @return a new {@link org.gedcomx.rs.client.atom.PagedFeedIterator}
   */
  public static PagedFeedIterator fromUri(URI uri) {
    return new PagedFeedIterator(uri);
  }

  /**
   * Creates a new paged feed iterator using the specified {@link org.gedcomx.atom.Feed}.
   *
   * @param feed the feed document from which the initial first, last, previous, and next hypertext references will be
   *             acquired.
   * @return a new {@link org.gedcomx.rs.client.atom.PagedFeedIterator}
   */
  public static PagedFeedIterator fromFeed(Feed feed) {
    return new PagedFeedIterator(feed);
  }

  /**
   * Gets the hypertext reference from the specified {@link org.gedcomx.atom.Feed} for the specified rel link.
   *
   * @param feed the source feed document
   * @param rel  the desired rel
   * @return the hypertext reference, if it exists; otherwise {@code null}
   */
  public static URI getLinkRelHref(Feed feed, String rel) {
    Link link = feed.getLink(rel);
    return link == null ? null : link.getHref();
  }

  public List<WebResourceBuilderExtension> getWebResourceBuilderExtensions() {
    return Collections.unmodifiableList(this.extensions);
  }

  /**
   * Adds a {@link WebResourceBuilderExtension} in order to add cookies, header values, etc. to the paged feed document
   * GET request.
   *
   * @param extension the extension to add
   */
  public synchronized void addWebResourceBuilderExtension(WebResourceBuilderExtension extension) {
    this.extensions.add(extension);
  }

  /**
   * Removes a {@link WebResourceBuilderExtension}.
   *
   * @param extension the extension to remove
   */
  public synchronized void removeWebResourceBuilderExtension(WebResourceBuilderExtension extension) {
    this.extensions.remove(extension);
  }

  public synchronized void clearWebResourceBuilderExtensions() {
    this.extensions.clear();
  }

  /**
   * Adds a {@link WebResourceBuilderExtension} in order to add cookies, header values, etc. to the paged feed document
   * GET request.
   *
   * @param extension the extension
   * @return a reference to this {@link org.gedcomx.rs.client.atom.PagedFeedIterator} for fluent configuration chaining
   */
  public PagedFeedIterator withWebResourceBuilderExtension(WebResourceBuilderExtension extension) {
    addWebResourceBuilderExtension(extension);
    return this;
  }

  /**
   * Gets the {@link com.sun.jersey.api.client.Client} being used to get paged feed documents.
   *
   * @return the {@link com.sun.jersey.api.client.Client} being used to get paged feed documents.
   */
  public Client getClient() {
    if (client == null) {
      withClient(Client.create());
    }
    return client;
  }

  /**
   * Sets the {@link com.sun.jersey.api.client.Client} to use to get paged feed documents.
   *
   * @param client the {@link com.sun.jersey.api.client.Client} to use to get paged feed documents.
   */
  public synchronized void setClient(Client client) {
    this.client = client;
  }

  /**
   * Sets the {@link com.sun.jersey.api.client.Client} to use to get paged feed documents.
   *
   * @param client the {@link com.sun.jersey.api.client.Client} to use to get paged feed documents.
   * @return a reference to this {@link org.gedcomx.rs.client.atom.PagedFeedIterator} for fluent configuration chaining
   */
  public PagedFeedIterator withClient(Client client) {
    setClient(client);
    return this;
  }

  /**
   * Gets the {@link WebResourceProvider}.
   *
   * @return the {@link WebResourceProvider}.
   * @see #DEFAULT_WEB_RESOURCE_PROVIDER
   */
  public WebResourceProvider getWebResourceProvider() {
    return webResourceProvider;
  }

  /**
   * Sets the {@link WebResourceProvider}.
   *
   * @param webResourceProvider the desired {@link WebResourceProvider}.
   * @see #DEFAULT_WEB_RESOURCE_PROVIDER
   */
  public void setWebResourceProvider(WebResourceProvider webResourceProvider) {
    this.webResourceProvider = webResourceProvider;
  }

  /**
   * Sets the {@link WebResourceProvider}.
   *
   * @param webResourceProvider the desired {@link WebResourceProvider}.
   * @return a reference to this {@link org.gedcomx.rs.client.atom.PagedFeedIterator} for fluent configuration chaining
   * @see #DEFAULT_WEB_RESOURCE_PROVIDER
   */
  public PagedFeedIterator withWebResourceProvider(WebResourceProvider webResourceProvider) {
    setWebResourceProvider(webResourceProvider);
    return this;
  }

  public boolean hasFirst() {
    return first != null;
  }

  public URI firstHRef() {
    return first;
  }

  public Feed first() {
    if (!hasFirst()) {
      throw new NoSuchElementException();
    }
    return getFeed(first);
  }

  public URI lastHRef() {
    return last;
  }

  public boolean hasLast() {
    return last != null;
  }

  public Feed last() {
    if (!hasLast()) {
      throw new NoSuchElementException();
    }
    return getFeed(last);
  }

  public URI previousHRef() {
    return previous;
  }

  public boolean hasPrevious() {
    return previous != null;
  }

  public Feed previous() {
    if (!hasPrevious()) {
      throw new NoSuchElementException();
    }
    return getFeed(previous);
  }

  public URI nextHRef() {
    return next;
  }

  @Override
  public boolean hasNext() {
    return next != null;
  }

  @Override
  public Feed next() {
    if (!hasNext()) {
      throw new NoSuchElementException();
    }
    return getFeed(next);
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException();
  }

  private void loadHRefsFromFeed(Feed feed) {
    first = getLinkRelHref(feed, "first");
    last = getLinkRelHref(feed, "last");
    previous = getLinkRelHref(feed, "previous");
    next = getLinkRelHref(feed, "next");
  }

  private synchronized Feed getFeed(URI uri) {
    WebResource.Builder builder = webResourceProvider.provide(getClient(), uri)
        .accept(MediaType.APPLICATION_ATOM_XML_TYPE);
    for (WebResourceBuilderExtension extension : this.extensions) {
      builder = extension.extend(builder);
    }
    ClientResponse clientResponse = builder.get(ClientResponse.class);
    ClientResponse.Status status = clientResponse.getClientResponseStatus();
    switch (status) {
      case OK:
        Feed feed = clientResponse.getEntity(Feed.class);
        loadHRefsFromFeed(feed);
        return feed;
      case NO_CONTENT:
        return null;
      default:
        throw new IllegalStateException(status.toString());
    }
  }

  /**
   * Interface for providing {@link com.sun.jersey.api.client.WebResource}s to use for fetching paged feed documents.
   */
  public static interface WebResourceProvider {
    /**
     * Provide the {@link com.sun.jersey.api.client.WebResource} to use for fetching a paged feed document.
     *
     * @param client the configured client to use
     * @param uri    the specified {@link org.gedcomx.common.URI} for the first, last, previous, and/or next paged feed
     *               document
     * @return a {@link com.sun.jersey.api.client.WebResource} for acquiring the desired paged feed document
     */
    WebResource provide(Client client, URI uri);
  }

  /**
   * Interface for extending HTTP GET requests (e.g. add cookies, header values, etc.).
   */
  public static interface WebResourceBuilderExtension {
    /**
     * Extends a {@link com.sun.jersey.api.client.WebResource.Builder}.
     *
     * @param builder the base {@link com.sun.jersey.api.client.WebResource.Builder}
     * @return the extended {@link com.sun.jersey.api.client.WebResource.Builder}
     */
    WebResource.Builder extend(WebResource.Builder builder);
  }
}