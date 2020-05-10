package org.genesys.simpleclients.camunda.rest.api;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import javax.inject.Named;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.mime.FileBody;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.entity.mime.StringBody;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpResponse;
import org.genesys.simpleclients.camunda.exception.ApiException;
import org.genesys.simpleclients.camunda.rest.request.DeploymentRequest;

@Slf4j
@Named
public final class CreateDeployment implements RestApi<DeploymentRequest> {

  private static final String URI = "http://localhost:8080/engine-rest/deployment/create";

  @Override
  public void execute(final DeploymentRequest request, final HttpClient httpClient) {

    log.info("Invoking API = {} with URI = {}", this.getClass().getName(), URI);
    final HttpPost httpPost = new HttpPost(URI);

    final StringBody deploymentName = new StringBody(request.getName(), ContentType.TEXT_PLAIN);
    final StringBody enableDuplicateFiltering = new StringBody("true", ContentType.TEXT_PLAIN);
    final StringBody deployChangedOnly = new StringBody("true", ContentType.TEXT_PLAIN);

    final MultipartEntityBuilder builder = MultipartEntityBuilder.create()
      .addPart("deployment-name", deploymentName)
      .addPart("enable-duplicate-filtering", enableDuplicateFiltering)
      .addPart("deploy-changed-only", deployChangedOnly);

    for (final String resource : request.getFiles()) {
      final File resourceFile = new File(Objects.requireNonNull(
        this.getClass().getClassLoader().getResource(resource)).getFile());
      final FileBody fileBody = new FileBody(resourceFile);
      builder.addPart(resourceFile.getName(), fileBody);
    }

    final HttpEntity httpEntity = builder.build();
    httpPost.setEntity(httpEntity);

    try {
      final HttpResponse response = httpClient.execute(httpPost);
      log.info("Finished API = {} with Response = {}", this.getClass().getName(), response);
    } catch (final IOException e) {
      throw new ApiException(e);
    }
  }
}
