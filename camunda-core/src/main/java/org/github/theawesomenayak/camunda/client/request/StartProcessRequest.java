package org.github.theawesomenayak.camunda.client.request;

import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import org.github.theawesomenayak.camunda.client.variable.Variable;

@Getter
@Builder
public final class StartProcessRequest implements ApiRequest {

  @NonNull
  private final String key;

  @NonNull
  private final Map<String, Variable> variables;
}
