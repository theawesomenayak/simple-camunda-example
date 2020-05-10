package org.genesys.simpleclients.camunda.rest;

import java.util.Map;
import javax.inject.Inject;
import javax.inject.Named;
import org.genesys.simpleclients.camunda.rest.api.CreateDeployment;
import org.genesys.simpleclients.camunda.rest.api.StartProcess;
import org.genesys.simpleclients.camunda.rest.request.DeploymentRequest;
import org.genesys.simpleclients.camunda.rest.request.StartProcessRequest;
import org.genesys.simpleclients.camunda.rest.request.Variable;

@Named
public final class RestClient {

  private final CreateDeployment createDeployment;

  private final StartProcess startProcess;

  @Inject
  public RestClient(final CreateDeployment createDeployment, final StartProcess startProcess) {

    this.createDeployment = createDeployment;
    this.startProcess = startProcess;
  }

  public void createDeployment(final String name, final String... resources) {

    final DeploymentRequest deploymentRequest = DeploymentRequest.builder()
      .name(name)
      .files(resources)
      .build();
    createDeployment.invoke(deploymentRequest);
  }

  public void startProcess(final String key, final Map<String, Variable> variables) {

    final StartProcessRequest startProcessRequest = StartProcessRequest.builder()
      .key(key)
      .variables(variables)
      .build();
    startProcess.invoke(startProcessRequest);
  }
}
