package org.github.theawesomenayak.camunda.client.api;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import javax.inject.Named;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import lombok.extern.slf4j.Slf4j;
import org.github.theawesomenayak.camunda.client.request.StartProcessRequest;
import org.github.theawesomenayak.camunda.client.variable.Variable;
import org.github.theawesomenayak.camunda.common.Constants;

@Slf4j
@Named
public final class StartProcess implements RestApi<StartProcessRequest> {

  private static final String URI = Constants.CAMUNDA_BASE_URL + "/process-definition/key/%s/start";

  @Override
  public void execute(final StartProcessRequest request) {

    final String resolvedUri = String.format(URI, request.getKey());
    log.info("Invoking API = {} with URI = {}", this.getClass().getSimpleName(), resolvedUri);
    final Map<String, Map<String, Variable>> variables = ImmutableMap
        .of("variables", request.getVariables());
    final HttpResponse<String> response = Unirest.post(resolvedUri)
        .header("Content-type", "application/json")
        .body(variables)
        .asString();
    log.info("Finished API = {} with Response = {}", this.getClass().getSimpleName(),
        response.getStatus());
  }
}
