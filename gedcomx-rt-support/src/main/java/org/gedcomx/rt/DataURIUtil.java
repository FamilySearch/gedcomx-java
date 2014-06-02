package org.gedcomx.rt;

import org.apache.commons.codec.binary.Base64;

import javax.ws.rs.core.MediaType;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;

/**
 * Utilities for encoding, decoding data URIs.
 *
 * @author Ryan Heaton
 * @see <a href="http://tools.ietf.org/html/rfc2397">RFC 2397</a>
 */
public class DataURIUtil {

  private DataURIUtil() { }

  /**
   * Get the string data from the specified URI.
   *
   * @param uri the URI.
   * @return The string form of the data, or null if the data URI doesn't contain a string.
   */
  public static String getValueAsString(URI uri) {
    if ("data".equals(uri.getScheme())) {
      String ssp;
      try {
        ssp = URLDecoder.decode(uri.getRawSchemeSpecificPart(), "utf-8");
      }
      catch (UnsupportedEncodingException e) {
        return null;
      }

      int commaIndex = ssp.indexOf(',');
      if (commaIndex >= 0) {
        String meta = ssp.substring(0, commaIndex);
        try {
          boolean base64 = meta.endsWith(";base64");
          if (base64) {
            meta = meta.substring(0, meta.indexOf(";base64"));
          }

          MediaType mediaType = meta.trim().length() > 0 ? MediaType.valueOf(meta) : MediaType.TEXT_PLAIN_TYPE;
          if (MediaType.TEXT_PLAIN_TYPE.isCompatible(mediaType)) {
            String value = ssp.substring(commaIndex + 1);
            if (base64) {
              byte[] decoded = Base64.decodeBase64(value);
              String charset = mediaType.getParameters().get("charset");
              if (charset == null) {
                charset = "utf-8";
              }
              return new String(decoded, charset);
            }
            else {
              return value;
            }
          }
        }
        catch (IllegalArgumentException e) {
          //fall through...
        }
        catch (UnsupportedEncodingException e) {
          //fall through...
        }
      }
    }

    return null;
  }

  /**
   * Encode custom text as a data URI.
   *
   * @param text The text to encode.
   * @return The URI.
   */
  public static URI encodeDataURI(String text) {
    try {
      return new URI("data", "," + text, null);
    }
    catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }

}
