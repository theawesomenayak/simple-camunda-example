package org.github.theawesomenayak.camunda.rest;

import java.util.Map;
import javax.inject.Named;
import lombok.AllArgsConstructor;
import org.github.theawesomenayak.camunda.rest.api.CreateDeployment;
import org.github.theawesomenayak.camunda.rest.api.StartProcess;
import org.github.theawesomenayak.camunda.rest.request.DeploymentRequest;
import org.github.theawesomenayak.camunda.rest.request.StartProcessRequest;
import org.github.theawesomenayak.camunda.rest.request.Variable;

@Named
@AllArgsConstructor
public final class CamundaClient {

  private final CreateDeployment createDeployment;

  private final StartProcess startProcess;

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
