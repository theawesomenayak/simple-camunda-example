package org.github.theawesomenayak.camunda.client;

import java.util.Map;
import javax.inject.Named;
import lombok.AllArgsConstructor;
import org.github.theawesomenayak.camunda.client.api.CreateDeployment;
import org.github.theawesomenayak.camunda.client.api.StartProcess;
import org.github.theawesomenayak.camunda.client.request.DeploymentRequest;
import org.github.theawesomenayak.camunda.client.request.StartProcessRequest;
import org.github.theawesomenayak.camunda.client.variable.Variable;

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
    createDeployment.execute(deploymentRequest);
  }

  public void startProcess(final String key, final Map<String, Variable> variables) {

    final StartProcessRequest startProcessRequest = StartProcessRequest.builder()
        .key(key)
        .variables(variables)
        .build();
    startProcess.execute(startProcessRequest);
  }
}
