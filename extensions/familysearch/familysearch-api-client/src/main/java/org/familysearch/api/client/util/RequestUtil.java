package org.familysearch.api.client.util;

import com.sun.jersey.api.client.ClientRequest;
import org.familysearch.platform.FamilySearchConstants;

/**
 * @author Ryan Heaton
 */
public class RequestUtil {

  public static ClientRequest.Builder applyFamilySearchContent(ClientRequest.Builder request) {
    return request.accept(FamilySearchConstants.FS_PLATFORM_V1_JSON_MEDIA_TYPE).type(FamilySearchConstants.FS_PLATFORM_V1_JSON_MEDIA_TYPE);
  }
}
