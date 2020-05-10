package org.genesys.simpleclients.camunda.rest.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import java.util.Map;
import javax.inject.Named;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.genesys.simpleclients.camunda.exception.ApiException;
import org.genesys.simpleclients.camunda.rest.request.StartProcessRequest;
import org.genesys.simpleclients.camunda.rest.request.Variable;

@Slf4j
@Named
public final class StartProcess implements RestApi<StartProcessRequest> {

  private static final String URI = "http://localhost:8080/engine-rest/process-definition/key/%s/start";

  @Override
  public void execute(final StartProcessRequest request, final HttpClient httpClient) {

    final String resolvedUri = String.format(URI, request.getKey());
    log.info("Invoking API = {} with URI = {}", this.getClass().getName(), resolvedUri);
    final HttpPost httpPost = new HttpPost(resolvedUri);
    try {
      final ObjectMapper mapper = new ObjectMapper();
      final Map<String, Map<String, Variable>> variables = ImmutableMap
        .of("variables", request.getVariables());
      final String json = mapper.writeValueAsString(variables);
      final StringEntity entity = new StringEntity(json);

      httpPost.setEntity(entity);
      httpPost.setHeader("Content-type", "application/json");

      final HttpResponse response = httpClient.execute(httpPost);
      log.info("Finished API = {} with Response = {}", this.getClass().getName(), response);
    } catch (final IOException e) {
      throw new ApiException(e);
    }
  }
}
