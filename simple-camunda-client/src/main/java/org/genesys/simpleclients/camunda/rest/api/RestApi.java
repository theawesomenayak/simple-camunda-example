package org.genesys.simpleclients.camunda.rest.api;

import java.io.IOException;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.genesys.simpleclients.camunda.exception.ApiException;
import org.genesys.simpleclients.camunda.rest.request.ApiRequest;

public interface RestApi<T extends ApiRequest> {

  default void invoke(final T request) {

    try (final CloseableHttpClient httpClient = HttpClients.createDefault()) {
      execute(request, httpClient);
    } catch (final IOException e) {
      throw new ApiException(e);
    }
  }

  void execute(final T request, final HttpClient httpClient);
}
