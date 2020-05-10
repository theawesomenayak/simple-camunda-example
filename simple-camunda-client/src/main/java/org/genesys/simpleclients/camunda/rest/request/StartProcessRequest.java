package org.genesys.simpleclients.camunda.rest.request;

import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder
public final class StartProcessRequest implements ApiRequest {

  @NonNull
  private final String key;

  @NonNull
  private final Map<String, Variable> variables;
}
