package org.github.theawesomenayak.camunda.client.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder
public final class DeploymentRequest implements ApiRequest {

  @NonNull
  private final String name;

  @NonNull
  private final String[] files;
}
