package org.github.theawesomenayak.camunda.client.api;

import java.io.File;
import java.util.Objects;
import javax.inject.Named;
import kong.unirest.HttpResponse;
import kong.unirest.MultipartBody;
import kong.unirest.Unirest;
import lombok.extern.slf4j.Slf4j;
import org.github.theawesomenayak.camunda.client.request.DeploymentRequest;
import org.github.theawesomenayak.camunda.common.Constants;

@Slf4j
@Named
public final class CreateDeployment implements RestApi<DeploymentRequest> {

  private static final String URI = Constants.CAMUNDA_BASE_URL + "/deployment/create";

  @Override
  public void execute(final DeploymentRequest request) {

    log.info("Invoking API = {} with URI = {}", this.getClass().getSimpleName(), URI);
    final MultipartBody multipartBody = Unirest.post(URI)
        .field("deployment-name", request.getName())
        .field("enable-duplicate-filtering", "true")
        .field("deploy-changed-only", "true");

    for (final String resource : request.getFiles()) {
      final File resourceFile = new File(Objects.requireNonNull(
          this.getClass().getClassLoader().getResource(resource)).getFile());
      multipartBody.field(resourceFile.getName(), resourceFile);
    }
    final HttpResponse<String> response = multipartBody.asString();
    log.info("Finished API = {} with Response = {}", this.getClass().getSimpleName(),
        response.getStatus());
  }
}
